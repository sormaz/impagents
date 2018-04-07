package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import java.io.*;
import javax.swing.*;

import java.awt.*;

//import edu.ohiou.implanner.geometry.PartModel;

public class UGAgent
    extends ServiceAgent {
  public UGAgent(int serverPort) {
    super("UGAgent", serverPort);
  }

  public UGAgent(String name, int serverPort) {
    super(name, serverPort);
    defaultFileName = "part.prt";
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.UG, state.getCurrent(), name,
                            agentQueue.size());
  }

  public Object serviceTask(IMPTask task) {
//    System.out.println("Processing UG task...");
//    File file = (File) task.execute(); //executing FileWriteTask, not UGTask
//    PartModel part = new PartModel(file);
//    part.display();
    return null;//part;
  }

  public static void main(String args[]) {
    UGAgent agent = new UGAgent("ug", 0);
    agent.display();
  }

@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}
}