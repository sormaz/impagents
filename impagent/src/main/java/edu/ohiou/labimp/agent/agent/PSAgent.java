package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import javax.swing.*;

import java.awt.*;

public class PSAgent
    extends ServiceAgent {
  public PSAgent(int serverPort) {
    super("PSAgent", serverPort);
  }

  public PSAgent(String name, int serverPort) {
    super(name, serverPort);
  }

  public Object receive(Object o) {
    return null;

  }

  public Object serviceTask(IMPTask task) {
    return new Object();
  }

  public static void main(String args[]) {
    UGAgent agent = new UGAgent("ps", 1213);
    agent.display();
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.PS, state.getCurrent(), name,
                            agentQueue.size());
  }

  public void run() {

  }

@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}
}