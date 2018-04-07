package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import java.io.*; //file, serializable

import javax.swing.JPanel;

import edu.ohiou.labimp.agent.task.*;


public class PartAgent extends TaskAgent implements Serializable {

  public PartAgent() {
  }

  public PartAgent(String name) {
    super(name);
    setServiceSequence();
  }

  public IMPTask createTask() {
    if (contents instanceof File && ( (File) contents).getName().endsWith(".prt"))
      return new UGTask(contents);
   return null;
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.PA, state.getCurrent(), name, 0);
  }

  public void reconfigure() {}
  
  public JPanel getSpecificPanel() {
	  JPanel panel = new JPanel();
	  return panel;
  }

/** @todo dsormaz commentd oout shodl be cleaned *
  public JPanel getPanel() {
    final JTextField listenerIPnPortTextField = new JTextField("localhost:");
    JTextField nameTextField = new JTextField("No name");
    final JList listenersList = new JList();
    JLabel portLabel = new JLabel("SA: "+ ((currentSA != null) ? currentSA.getIPString() : "no SA"));
    JLabel nameLabel = new JLabel("Name");
    JLabel lHostPortLabel = new JLabel("Listener\'s host:port");
    JButton addListenerInfoButton = new JButton("Add");
    JButton removeListenerInfoButton = new JButton("Remove");
    JButton openFileButton = new JButton("Open File...");
    final JPanel customPanel = new JPanel();
    customPanel.setLayout(null);
    portLabel.setBounds(new Rectangle(161, 4, 74, 24));
    listenerIPnPortTextField.setMinimumSize(new Dimension(6, 50));
    lHostPortLabel.setBounds(new Rectangle(5, 31, 230, 25));
    listenerIPnPortTextField.setBounds(new Rectangle(5, 61, 230, 25));
    openFileButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
	JFileChooser fc = new JFileChooser(".");
	fc.setCurrentDirectory(new File(IMPAgentViewer.DEFAUL_FILE_DIR));
	if (fc.showOpenDialog(customPanel) == JFileChooser.APPROVE_OPTION) {
	  File file = fc.getSelectedFile();
	  if (file instanceof File)
	    System.out.println("I am instance of file");
	  contents = file;
	  // dnsormaz state depends on the file type...
	  state.setCurrent(IMPState.CONTENT_SET_NOW_SEARCHING);
	}
	else
	  System.out.println("No file selected.");
      }
    });
    addListenerInfoButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	saGUIdata.addElement(listenerIPnPortTextField.getText());
	listenersList.setModel(saGUIdata);
	listenerIPnPortTextField.setText("localhost:");
      }
    });
    removeListenerInfoButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
	int[] indices = listenersList.getSelectedIndices();
	for (int i = 0; i < indices.length; i++)
	  saGUIdata.removeElementAt(indices[i] - i);
	listenersList.setModel(saGUIdata);
      }
    });
    listenersList.setAlignmentY( (float) 0.5);
    addListenerInfoButton.setBounds(new Rectangle(5, 89, 60, 18));
    removeListenerInfoButton.setBounds(new Rectangle(70, 89, 80, 18));
    listenersList.setBounds(new Rectangle(5, 111, 228, 131));
    nameTextField.setAlignmentX( (float) 0.5);
    nameLabel.setBounds(new Rectangle(5, 4, 44, 24));
    /** @todo dsormaz changed nameLabel to test content *
    if (contents != null)
      nameLabel.setText(contents.toString());
    nameTextField.setBounds(new Rectangle(49, 4, 100, 24));
    openFileButton.setBounds(new Rectangle(40, 250, 150, 24));
    customPanel.add(portLabel, null);
    customPanel.add(lHostPortLabel);
    customPanel.add(nameLabel);
    customPanel.add(listenersList, null);
    customPanel.add(listenerIPnPortTextField, null);
    customPanel.add(nameTextField, null);
    customPanel.add(addListenerInfoButton, null);
    customPanel.add(removeListenerInfoButton, null);
    customPanel.add(openFileButton, null);
    listenersList.setModel(saGUIdata);
    return customPanel;
  }
*/
  public static void main(String[] args) {
    PartAgent agent = new PartAgent("part");
    agent.display();
  }
}
