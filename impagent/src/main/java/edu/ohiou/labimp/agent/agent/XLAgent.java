package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import javax.swing.*;

import java.io.*;

public class XLAgent extends ServiceAgent {
	
	static String commandFolder;
	
	static {
		commandFolder = IMPAgent.properties.getProperty("edu.ohiou.labimp.agent.agent.XLAgent.commandFolder", "");
	}
	
  public XLAgent() {
    super ("Excel Agent", 1155);
  }

  public XLAgent(String name, int port){
    super(name, port);
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.XL,state.getCurrent(),name,agentQueue.size());
 }

  public Object serviceTask(IMPTask task) {
    System.out.println("Processing XL task...");
    File file = (File) task.execute();//executing FileWriteTask
    System.out.println("file " + file.getAbsolutePath());
    String execCommand = "\"" + commandFolder + "/EXCEL.EXE\" \"" + file.getAbsolutePath() + "\"";
    System.out.println("command " + execCommand);
    try {
      Runtime.getRuntime().exec(execCommand);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    return file;
  }

  public static void main(String [] args){
    XLAgent agent = new XLAgent("xl", 0);
    agent.display();
  }

@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}

}
