package edu.ohiou.labimp.agent.agent;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import edu.ohiou.labimp.agent.*;

public class FileAgent extends TaskAgent implements Serializable {

  public FileAgent() {
    this("File agent");
  }

  public FileAgent(String name) {
    super(name);
    setServiceSequence();
    for (int i = 0; i < serviceSequence.length;i++) {
      System.out.println("sequence: " +i + "is "+ this.serviceSequence[i]);
    }
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.FA, state.getCurrent(), name, 0);
  }

  public void reconfigure() {

  }
  
  public JPanel getSpecificPanel () {
	  
	  final JPanel panel = new JPanel();
		JButton openFileButton = new JButton("Open File...");

		openFileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser(properties.getProperty("user.home"));
				fc.setCurrentDirectory(new File(IMPAgentViewer.DEFAUL_FILE_DIR));
				if (fc.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
//					if (file instanceof File && file.getName().endsWith(fileExtention) ){
//					System.out.println("I am instance of ." + fileExtention + " file");
					contents = file;
					state.setCurrent(IMPState.CONTENT_SET_NOW_SEARCHING);
					updateUI();
//					} else
//					System.out.println("Incompatible file type - No file selected.");
				}
				else
					System.out.println("Cancel - No file selected.");
			}
		});
		
//		bottomPanel.setLayout(new BorderLayout());
		panel.add( openFileButton, BorderLayout.NORTH);

return panel;
  }

  public static void main(String[] args) {
    FileAgent agent = new FileAgent();
    agent.display();
  }
}
