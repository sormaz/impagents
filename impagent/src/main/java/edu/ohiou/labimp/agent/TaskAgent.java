package edu.ohiou.labimp.agent;

import static edu.ohiou.labimp.agent.TaskAgent.stateStrings;
import edu.ohiou.imse.ise589.*;

import javax.swing.*;

import java.util.*;
//import edu.ohiou.implanner.parts.MfgPartModel;
//import edu.ohiou.implanner.geometry.PartModel;
import java.io.*;
import java.net.ConnectException;
import java.awt.*;
import java.awt.event.*;

public abstract class TaskAgent
extends IMPAgent
implements Serializable {

	protected DefaultListModel saGUIdata = new DefaultListModel();
	protected DefaultListModel taskHistoryListModel = new DefaultListModel();
	
	transient protected ServiceAgent currentSA = null;
	transient JTextField statusText;
	transient JTextField contentText;

	static String [] stateStrings;
	public Object text;
	//a task might continuous change the shape of an object (contents), 
	protected Object contents; //this content keeps updaing 
	//inContents is a static shape of an object right before starting a new task 
	protected Object inContents; 
	  static final long timeout;
	  static {
			timeout = Long.parseLong(properties.
					getProperty("edu.ohiou.labimp.agent.TaskAgent.timeout", "" + TIMEOUT));
		}


	static String defaultHostPort = properties.
	getProperty("edu.ohiou.labimp.agent.TaskAgent.defaultHostPort", "132.235.017.054:1111");


	static {
		StringTokenizer stateTokens = new StringTokenizer(
				properties.getProperty("edu.ohiou.labimp.agent.TaskAgent.states"),",");
		stateStrings = new String [stateTokens.countTokens()];
		for (int i = 0; i < stateStrings.length; i++) {
			stateStrings[i] = stateTokens.nextToken();
		}
	}

	public TaskAgent() {
		this("Task Agent");
	}

	public TaskAgent(String name) {
		super(name);
	}

	public void updateStatus(boolean isRunning) {}

	abstract public void reconfigure();

	public IMPAgentInfo getInfo() {
		return null;
	}

//	public IMPTask createTask() {
//	if (contents instanceof File && ( (File) contents).getName().endsWith(".prt"))
//	return new UGTask(contents);
//	else if (contents instanceof PartModel)
//	return new FMTask(contents);
//	else if (contents instanceof MfgPartModel)
//	return new PSTask(contents);
//	else if (contents instanceof File && ( (File) contents).getName().endsWith(".txt"))
//	return new TXTask(contents);
//	else if (contents instanceof File && ( (File) contents).getName().endsWith(".csv"))
//	return new XLTask(contents);
//	return null;
//	}

//	public IMPTask createTask(int taskID){
//	switch(taskID){
//	case IMPTask.UG:  return new UGTask(contents);
//	case IMPTask.FM:  return new FMTask(contents);
//	case IMPTask.PS:	return new PSTask(contents);
//	case IMPTask.MC:	return new MCTask(contents);
//	case IMPTask.TX:	return new TXTask(contents);
//	case IMPTask.XL:	return new XLTask(contents);
//	default:	return null;
//	}
//	}

	protected String serviceSequence[] = null;
	protected int stationIndex = 0;

	public void setServiceSequence() {
		String tasks = properties.getProperty(this.getClass().getName() +"."+this.getName()+ ".tasks");
		if (tasks==null){
			tasks = properties.getProperty(this.getClass().getName() + ".tasks");
		}
		StringTokenizer tokens = new StringTokenizer(tasks, ",");
		serviceSequence = new String[tokens.countTokens()];
		for (int i = 0; tokens.hasMoreTokens(); i++) {
			serviceSequence[i] = tokens.nextToken();
		}
	}
	
	public void setHostPortList () {
		String ports = properties.getProperty(
				this.getClass().getName() +"."+this.getName()+ ".hostPortList");
		if (ports==null){
			ports = properties.getProperty(this.getClass().getName() + ".hostPortList", "");
		}
		StringTokenizer tokens = new StringTokenizer(ports, ",");
//		serviceSequence = new String[tokens.countTokens()];
		for (int i = 0; tokens.hasMoreTokens(); i++) {
			saGUIdata.addElement(tokens.nextToken());
		}
	}

	public IMPTask createTask() {
		IMPTask task = null;
		if (stationIndex < serviceSequence.length) {
			Class[] argTypes = {
					Object.class};
				Object[] args = {
						inContents};	
			try {
				String taskString=  serviceSequence[stationIndex];
				Class taskClass = Class.forName(taskString);
				java.lang.reflect.Constructor c = taskClass.getConstructor(argTypes);
				task = (IMPTask) c.newInstance(args);
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return task;
	}

	protected StreamClient search(int taskID)   {
		StreamClient answer = null;
		String localPort=properties.getProperty(this.getClass().getName() +"."+this.getName()+ ".localPort");
		if(localPort == null){
			localPort = properties.getProperty(this.getClass().getName() + ".localPort", "1112");
		}
		System.out.println("Local port is set to " + localPort);
		
		StreamClient client = new StreamClient(Integer.parseInt(localPort));
		
//		while (answer == null) {
			for (int i = 0; i < saGUIdata.size(); i++) {
				System.out.println("searching for service at " + saGUIdata.get(i));
				String ip = (String) saGUIdata.get(i);
				if (currentSA == null ||
						(currentSA != null && !currentSA.getIPString().equalsIgnoreCase(ip))) {
					String host = ip.substring(0, ip.length() - 5);
					int port = Integer.parseInt(ip.substring(ip.length() - 4, ip.length()));
					try {
						client.connect(host, port);
						Object object = client.send(IMPAgentInfo.INFO_REQUEST, true); //true to get reply
						if (object != null && object instanceof IMPAgentInfo) {
							IMPAgentInfo info = (IMPAgentInfo) object;
							if (info.id == taskID) {
								answer = client; //////////////////////CHECK QUEUE SIZE ?
								break;
							}
							else {
								client.disconnect();
							}
						}
					} catch( ConnectException e) {
//						System.err.println (e.getMessage());			
					}catch (IOException e) {
						
						e.printStackTrace();
					}
				}
			}
			if (answer == null)
				System.out.println("Can't find available " + IMPTask.toString(taskID) +
						" agent at this time. will try again in  " + timeout /1000 + " sec.");
//		}
		return answer;
	}

	public boolean acceptTask(IMPTask task) {
		boolean ok = false;
		StreamClient client = null;
		if (task == null)
			return ok;
		try {
			client = search(task.id());
		}
		catch (Exception ex1) {
			System.err.println("msg: " + ex1.getMessage());
			ex1.printStackTrace();
		}
		try {
			if (client != null) {
				contents = task.execute();
				currentSA = null;
				state.setCurrent(IMPState.IN_TRANSIT);
				client.send(this);
				client.disconnect();
				ok = true;
			}
		}
		catch (Exception ex) {
			System.out.println(" - - - Connection Problems!!! - - - ");
			System.err.println(ex.getMessage());
		}
		return ok;
	}

	public void accept(Object o) {
		contents = o;
	}
	
	public void updateContent() {
		inContents = contents;
	}

	public void run() {
		boolean running = true;
		while (running) {
			System.out.println("in TaskAgent run: " + toString());
			if (state.getCurrent() == IMPState.IN_TRANSIT) {
				System.out.println("we need to close agent");
				running = false;
				break;
			}
			if (state.getCurrent() == IMPState.CONTENT_SET_NOW_SEARCHING) {
//				keep the informaion about orignial shape of this content
				IMPTask t=createTask();
				if (!acceptTask(t)) {
//					System.out.println("Failed to accept task - closing TA");
					System.out.println("Failed to accept task - will try" +
							          " again in " + timeout/1000. + " seconds");
//					running = false;
//					break;
				}
			}
			try {
				runner.sleep(timeout);
			}
			catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	abstract public JPanel getSpecificPanel();

	public JPanel getPanel() {
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2));


		final JPanel genericPanel = new JPanel();
		genericPanel.setLayout(new BorderLayout());

		JPanel infoPanel = new JPanel();
		JPanel labelPanel = new JPanel();
		JPanel dataPanel = new JPanel();
		
		infoPanel.setLayout(new BorderLayout());
		labelPanel.setLayout(new GridLayout(4, 1));
		dataPanel.setLayout(new GridLayout(4, 1));

		JLabel nameLabel = new JLabel("Name: ");
		final JTextField nameTextField = new JTextField(name);
		
		nameTextField.addFocusListener(new FocusAdapter (){
			
			public void focusLost (FocusEvent e) {
				name = nameTextField.getText();
			}
		});

		JLabel agentLabel = new JLabel("Service agent: ");
		JTextField agentText = new JTextField( (currentSA != null ?
				currentSA.getIPString() : "none"));
		JLabel statusLabel = new JLabel("Status: ");
		statusText = new JTextField (stateStrings[this.getState().getCurrent()]);

		JLabel contentLabel = new JLabel("Contents: ");
		contentText = new JTextField( (contents != null ?
				contents.toString() : "none"));
		labelPanel.add(nameLabel);
		labelPanel.add(contentLabel);
		labelPanel.add(agentLabel);
		labelPanel.add(statusLabel);
		dataPanel.add(nameTextField);
		dataPanel.add(contentText);
		dataPanel.add(agentText);
		dataPanel.add(statusText);
		infoPanel.add(labelPanel, BorderLayout.WEST);
		infoPanel.add(dataPanel, BorderLayout.CENTER);
		
		genericPanel.add(infoPanel, BorderLayout.NORTH);



		final JTextField listenerIPnPortTextField = new JTextField(defaultHostPort);
		final JList listenersList = new JList();
		JLabel lHostPortLabel = new JLabel("Listeners\' host:port");
		lHostPortLabel.setHorizontalAlignment(SwingConstants.CENTER);
		JButton addListenerInfoButton = new JButton("A");
		JButton removeListenerInfoButton = new JButton("R");
		
		listenerIPnPortTextField.setMinimumSize(new Dimension(6, 50));

		class MyListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				String ip = listenerIPnPortTextField.getText();
				String port = ip.substring(ip.indexOf(":")>0?ip.indexOf(":"):0);
				if (port.length() == 5) {
					saGUIdata.addElement(ip);
					listenersList.setModel(saGUIdata);
				}
				else
					new JOptionPane().showMessageDialog(genericPanel, "Port must be 4 chars!",
							"Port Error", JOptionPane.ERROR_MESSAGE, null);
				listenerIPnPortTextField.setText(defaultHostPort);
			}
		}

		MyListener ml = new MyListener();

		addListenerInfoButton.addActionListener(ml);
		addListenerInfoButton.setToolTipText("Add host and port for listener");
		listenerIPnPortTextField.addActionListener(ml);

		removeListenerInfoButton.setToolTipText("Remove selected listener(s)");
		removeListenerInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] indices = listenersList.getSelectedIndices();
				for (int i = 0; i < indices.length; i++)
					saGUIdata.removeElementAt(indices[i] - i);
				listenersList.setModel(saGUIdata);
			}
		});

		listenersList.setModel(saGUIdata);

		JPanel listenerPanel = new JPanel();
		JPanel listActionPanel = new JPanel();

		listActionPanel.setLayout(new BorderLayout());
		listActionPanel.add(addListenerInfoButton, BorderLayout.WEST);
		listActionPanel.add(listenerIPnPortTextField, BorderLayout.CENTER);
		listActionPanel.add(removeListenerInfoButton, BorderLayout.EAST);

		listenerPanel.setLayout(new BorderLayout());
		listenerPanel.add(lHostPortLabel, BorderLayout.NORTH);
		listenerPanel.add(new JScrollPane(listenersList), BorderLayout.CENTER);
		listenerPanel.add(listActionPanel, BorderLayout.SOUTH);
		
		JPanel taskPanel = new JPanel();
		taskPanel.setLayout (new BorderLayout());
		taskPanel.add(new JLabel(" Agent's tasks:", SwingConstants.CENTER), BorderLayout.NORTH);
		JList taskList = new JList();
		taskList.setListData(this.serviceSequence);
		taskPanel.add(taskList, BorderLayout.CENTER);
		

		if (this.getCurrentSA()!=null)
		    taskHistoryListModel.addElement(this.getCurrentSA().toString());
		JPanel historyPanel = new JPanel();
		historyPanel.setLayout (new BorderLayout());
		historyPanel.add(new JLabel(" Service Hisotry:", SwingConstants.CENTER), BorderLayout.NORTH);
		JList HistoryList = new JList(taskHistoryListModel);
		historyPanel.add(HistoryList, BorderLayout.CENTER);
		JSplitPane taskSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
//		taskSplitPane.setDividerSize(1);
		taskSplitPane.setTopComponent(taskPanel);
		taskSplitPane.setBottomComponent(historyPanel);

		
		JSplitPane centerPanel = new JSplitPane();
		centerPanel.setDividerSize(1);
		centerPanel.setLeftComponent(listenerPanel);
		centerPanel.setRightComponent(taskSplitPane);
		
		genericPanel.add(centerPanel, BorderLayout.CENTER);
		topPanel.add(genericPanel, BorderLayout.WEST);
		topPanel.add(getSpecificPanel(), BorderLayout.CENTER);

		return topPanel;
	}

	public Object getContents() {
		return inContents;
	}


	public void setCurrentSA(ServiceAgent serviceAgent) { 
		currentSA = serviceAgent;
	}

	public ServiceAgent getCurrentSA() {
		return currentSA;
	}
	public void incrementTask() {
		if (stationIndex != serviceSequence.length -1) {
			stationIndex++;
			state.setCurrent(IMPState.CONTENT_SET_NOW_SEARCHING);
		}
		else {
			state.setCurrent(IMPState.COMPLETE);
			display("Agent Completed->" + toString());	
		}

		
	}

	public String toString() {
		String taskString = ".NoTask";
		if (serviceSequence != null && serviceSequence.length > 0) {
		 taskString = serviceSequence[stationIndex];
		}
		String taskName = taskString.substring(taskString.lastIndexOf(".")+1);
		return super.toString() + ":" + name + " - " +
				taskName + "[" + (stationIndex + 1) + "]" + " - " + 
				state.toString();
		 
	}
	
	public void updateUI () {
		statusText.setText(stateStrings[getState().getCurrent()]);
		contentText.setText(inContents.toString());
	}
}
