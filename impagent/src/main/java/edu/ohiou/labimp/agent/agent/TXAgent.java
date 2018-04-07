package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import javax.swing.*;

import java.io.*;

public class TXAgent extends ServiceAgent {
  public TXAgent() {
    super ("TXAgent", 1111);
  }

  public TXAgent(String name, int port){
    super(name, port);
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.TX,state.getCurrent(),name,agentQueue.size());
  }

  public Object serviceTask(IMPTask task) {
    System.out.println("Processing TX task...");
    File file = (File) task.execute();//executing FileWriteTask
    System.out.println("file " + file.getAbsolutePath());
    String command = "C:/WINDOWS/SYSTEM32/notepad.exe \"" + file.getAbsolutePath() + "\"";
    System.out.println("command " + command);
    try {
      Runtime.getRuntime().exec(command);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
    return file;
  }

  public static void main(String [] args){
    TXAgent agent = new TXAgent("tx",0);
    agent.display();
  }

@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}

}
