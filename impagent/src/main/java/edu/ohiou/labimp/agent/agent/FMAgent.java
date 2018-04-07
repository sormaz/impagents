package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import javax.swing.*;

import java.awt.*;

public class FMAgent  extends ServiceAgent {
  public FMAgent(int serverPort) {
    super("FMAgent", serverPort);
  }

  public FMAgent(String name, int serverPort) {
    super(name, serverPort);
  }

  public IMPAgentInfo getInfo(){
    return new IMPAgentInfo(IMPTask.FM,state.getCurrent(),name,agentQueue.size());
  }

  public Object serviceTask(IMPTask task){
    System.out.println("Processing FM task...");
    return new Object();
  }

  public static void main (String args [] ) {
    FMAgent agent = new FMAgent("fm", 0);
    agent.display();
  }

@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}
}
