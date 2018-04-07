package edu.ohiou.labimp.agent;

import edu.ohiou.imse.ise589.*;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DApplet;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DPanel;

import javax.swing.*;

import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

public abstract class ServiceAgent  extends IMPAgent implements Connectable {

  protected int serverPort;
  protected StreamServer server;
  protected IMPAgentQueue agentQueue = new IMPAgentQueue();
  transient JTextField statusText;
  transient JTextField contentText;
  
  protected String defaultFileName = "default.file";
  protected boolean serviceComplete = false;
  protected TaskAgent agent = null;
 
  protected Thread taskRunner;

  transient protected JPanel graphPanel=new JPanel();
  static final int MIN_PORT = 0;
  static final int MAX_PORT = 9999;
  
  static final long timeout;
  //added by JING HUANG
  protected DefaultListModel taskQueueListModel = new DefaultListModel();
  protected DefaultListModel activeTaskModel = new DefaultListModel();
  protected DefaultListModel historyListModel = new DefaultListModel();
  private String noAgent = "No active agent";
  //--------------------------------------
static {
	timeout = Long.parseLong(properties.
			getProperty("edu.ohiou.labimp.agent.ServiceAgent.timeout", "" + TIMEOUT));
}
  public ServiceAgent(String name, int port) {
    super(name + port);
    this.serverPort = port;
    runner = new Thread(this);
    runner.start();
    server = new StreamServer(this, serverPort);
    activeTaskModel.addElement(noAgent);
  }



  public JPanel getSpecificPanel() {
  	// TODO Auto-generated method stub
  	throw new UnsupportedOperationException(this.getClass().getName());
  	
  }
  
  void setServerPort(int port) {
    this.serverPort = port;
  }

  StreamServer getServer() {
    return server;
  }

  int getServerPort() {
    return serverPort;
  }

  public Object receive(Object o) {
    if (o instanceof String &&
	( (String) o).equalsIgnoreCase(IMPAgentInfo.INFO_REQUEST)) {
      return getInfo();
    }
    agentQueue.join( (IMPAgent) o);
    TaskAgent a = (TaskAgent) o;
	System.out.println("\nin SA receive agent content: " + a.contents + a.text);
//	System.out.println("\nin DR task content bef: " + task.getContents());

    a.reconfigure();
    a.state.setCurrent(IMPState.WAITING);
    a.setCurrentSA(this);
    a.display();
    taskQueueListModel.addElement(a);
    return null;
  }

  public String getIPString() {
    String lh = getLocalHost();
    int begIdx = lh.indexOf("1");
    return lh.substring(begIdx>0?begIdx:0) + ":" + serverPort;
  }

  public void updateStatus(boolean isRunning) {
    state.setCurrent(isRunning ? IMPState.IDLE : IMPState.UNINITIATED);
  }

  public void updateStatus(boolean isRunning, int port) {
    serverPort = port;
    state.setCurrent(isRunning ? IMPState.IDLE : IMPState.UNINITIATED);
  }

  public Object serviceTask(IMPTask task) {
    return null;
  }
  
  public void completeTask () {
	  taskRunner.stop();
	  serviceComplete = true;
	  agent.updateContent();
  }


  public boolean agentCompleted(TaskAgent agent) {
    //more may be here
    return serviceComplete;
  }

  public JPanel getPanel () {
      JPanel panel = new JPanel ();
      panel.setLayout(new BorderLayout());
      JPanel topPanel = new JPanel();
      final JCheckBox doneBox = new JCheckBox("task complete");
      doneBox.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e){
    	   completeTask();
         doneBox.setSelected(false);
       }});
      topPanel.setLayout(new BorderLayout());
      topPanel.add (new JLabel (this.toString()), BorderLayout.WEST);
      topPanel.add(doneBox, BorderLayout.EAST);
     // topPanel.add();
      JPanel taskListPanel = new JPanel();
      taskListPanel.setLayout(new BorderLayout());
      //added by JING HUANG, split panel, left side is a JList
      // This JList shows three types of tasks: tasks already completed by this SA,
      // task being processed by this SA, and tasks waiting in the queue
      JList taskQueue=new JList(taskQueueListModel);
      JList activeList = new JList(activeTaskModel);
      JList historyList=new JList(historyListModel);
      taskQueue.setBorder(BorderFactory.createTitledBorder("Task Agent Queue"));
//      taskQueue.setMinimumSize(new Dimension(300,200));
      //right side is a 2DPanel, to show DrawObject, if Task Agent is implementing a 2DTask

      activeList.setBorder(BorderFactory.createTitledBorder("Active Task Agent"));
      historyList.setBorder(BorderFactory.createTitledBorder("History Records"));
      
      taskListPanel.add(new JScrollPane(taskQueue), BorderLayout.CENTER);
      taskListPanel.add(activeList, BorderLayout.NORTH);
      taskListPanel.add(new JScrollPane(historyList), BorderLayout.SOUTH);
//      taskListPanel.add(getSpecificPanel(),BorderLayout.NORTH);
//      graphPanel.add(new JLabel("Graph Area"),BorderLayout.NORTH );
      
      JSplitPane splitPanel = new JSplitPane();
      splitPanel.setDividerSize(1);
      splitPanel.setLeftComponent(taskListPanel);
      splitPanel.setRightComponent(getSpecificPanel());
 
      //--------------------------------------------------------
      panel.add (topPanel, BorderLayout.NORTH);
      panel.add(splitPanel, BorderLayout.CENTER);
      panel.add(getServer().makePanel(), BorderLayout.SOUTH);
      return panel;
    }

  public void run() {
    while (true) {
//      System.out.print("in ServAgent run, agent: " + name + ", state " + state +
//		       "; milliseconds: " +
//		       Calendar.getInstance().getTimeInMillis());
//      System.out.println(" property " + System.getProperty("user.home"));
//   Object c=null; 	
      if (state.getCurrent() == IMPState.IDLE && agentQueue.size() > 0 ) {
	agent = (TaskAgent) agentQueue.next();
	state.setCurrent(IMPState.BUSY);
	agent.runner = new Thread(agent);
	agent.runner.start();
	agent.state.setCurrent(IMPState.IN_SERVICE);
//removed by JING HUANG----------------------------
	taskQueueListModel.removeElement(agent); // GUI update
	activeTaskModel.addElement(agent);// GUI update
	activeTaskModel.removeElement(noAgent);
//	--------------------------------------------------
//	FileWriteTask task = new FileWriteTask(agent,
//					       System.getProperty("user.home") +
//					       "/" + "file1.txt");
//	
//	agent.accept(serviceTask(task));
//	c=serviceTask(agent.currentTask);
//	agent.display("before");
	agent.accept(serviceTask(agent.createTask()));
//	agent.display("After");
	System.out.println("\nin SA run agent content: " + agent.contents + agent.text);
//	System.out.println("\nin DR task content bef: " + task.getContents());

      } else if (state.getCurrent() == IMPState.BUSY && agentCompleted(agent)) {
//        removed by JING HUANG----------------------------
    	    agent.state.setCurrent(IMPState.COMPLETE);
    	    activeTaskModel.removeElement(agent);
    		activeTaskModel.addElement(noAgent);
    		historyListModel.addElement(agent.toString());
//         	-------------------------------------------------- 
    	  state.setCurrent(IMPState.IDLE);
    	  agent.incrementTask();
           serviceComplete = false;
       }
      
      
      try {
	runner.sleep(timeout);
      }
      catch (InterruptedException ex) {
      }
    }
  }

  public ServiceAgent() {
  }
  
  public String toString(){
	  String returnStr="";
	  TaskAgent a=(TaskAgent) agentQueue.peek();
	  if (agentQueue.size()>0)
		  returnStr=a.serviceSequence[a.stationIndex];

	  return returnStr+ " "+this.name + "@" +  getIPString();
  }


}
