package edu.ohiou.labimp.agent.agent;

import java.awt.BorderLayout;

import edu.ohiou.imse.ise589.DrawObject;
import edu.ohiou.labimp.agent.IMPAgentInfo;
import edu.ohiou.labimp.agent.IMPTask;
import edu.ohiou.labimp.agent.ServiceAgent;
import edu.ohiou.labimp.agent.task.DITask;
import edu.ohiou.labimp.agent.task.DRTask;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DApplet;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DPanel;

public class DIAgent extends CSGAgent {

	public DIAgent(String name, int port) {
		super(name, port);
		// TODO Auto-generated constructor stub
	}

	public DIAgent() {
		this("DIAgent", 0);
	}
	
	  public IMPAgentInfo getInfo() {
		    return new IMPAgentInfo(IMPTask.DI,state.getCurrent(),name,agentQueue.size());
		  }

	


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		   DIAgent agent = new DIAgent("DI",0);
		   agent.display();

	}

}

