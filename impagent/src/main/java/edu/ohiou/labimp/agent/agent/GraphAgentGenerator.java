package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.IMPState;



public class GraphAgentGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int count = 0, number = 5;
		if (args.length >0) {
		try {
			number =  Integer.parseInt(args[0]);
		} catch (NumberFormatException e) {
			// noting to do if error we will use default = 5
		}
		}
		while (count < number) {
			GraphAgent agent = new GraphAgent("GraphAgent" + count);
			agent.randomizeSequence();
			agent.randomizeContent();
			agent.getState().setCurrent(IMPState.CONTENT_SET_NOW_SEARCHING);
			agent.display();
			try {
				Thread.currentThread().sleep(30000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}

	}

}
