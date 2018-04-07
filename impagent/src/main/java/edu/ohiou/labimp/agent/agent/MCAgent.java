package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import javax.swing.*;

import java.awt.*;

public class MCAgent extends ServiceAgent {
  public MCAgent(int serverPort) {
    super("MCAgent", serverPort);
  }

  public MCAgent(String name, int serverPort) {
    super(name, serverPort);
  }

  public void processIMPTask(IMPTask task){

  }

  public Object  receive(Object o){
    return null;
  }

  public Object serviceTask (IMPTask task){
  return new Object();
}

  public static void main (String args [] ) {
    UGAgent agent = new UGAgent("mc", 1213);
    agent.display();
  }
  public IMPAgentInfo getInfo(){
    return new IMPAgentInfo(IMPTask.MC,state.getCurrent(),name,agentQueue.size());
  }

  public void run(){

  }

@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}

}