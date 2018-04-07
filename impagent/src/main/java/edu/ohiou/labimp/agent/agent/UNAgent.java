package edu.ohiou.labimp.agent.agent;

import java.awt.BorderLayout;

import edu.ohiou.imse.ise589.DrawObject;
import edu.ohiou.labimp.agent.IMPAgentInfo;
import edu.ohiou.labimp.agent.IMPTask;
import edu.ohiou.labimp.agent.ServiceAgent;
import edu.ohiou.labimp.agent.task.DRTask;
import edu.ohiou.labimp.agent.task.UNTask;
//import edu.ohiou.labimp.basis.Draw2DApplet;
//import edu.ohiou.labimp.basis.Draw2DPanel;
//import edu.ohiou.labimp.basis.Drawable2D;

public class UNAgent extends CSGAgent {

	public UNAgent(String name, int port) {
		super(name, port);
}

	public UNAgent() {
		this ("UNAgent", 0);
	}
	
	  public IMPAgentInfo getInfo() {
		    return new IMPAgentInfo(IMPTask.UN,state.getCurrent(),name,agentQueue.size());
		  }

	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   UNAgent agent = new UNAgent("UN",0);
		    agent.display();

	}

}
