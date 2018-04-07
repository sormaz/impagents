package edu.ohiou.labimp.agent.agent;

import java.awt.BorderLayout;

import edu.ohiou.imse.ise589.DrawObject;
import edu.ohiou.labimp.agent.IMPAgentInfo;
import edu.ohiou.labimp.agent.IMPTask;
import edu.ohiou.labimp.agent.ServiceAgent;
import edu.ohiou.labimp.agent.task.DRTask;
import edu.ohiou.labimp.agent.task.INTask;
//import edu.ohiou.labimp.basis.Draw2DApplet;
//import edu.ohiou.labimp.basis.Draw2DPanel;

public class INAgent extends CSGAgent {

	public INAgent(String name, int port) {
		super(name, port);
		// TODO Auto-generated constructor stub
	}

	public INAgent() {
		this ("INAgent", 0);
	}
	
	  public IMPAgentInfo getInfo() {
		    return new IMPAgentInfo(IMPTask.IN,state.getCurrent(),name,agentQueue.size());
		  }

	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   INAgent agent = new INAgent("IN",0);
		    agent.display();

	}

}

