package edu.ohiou.labimp.agent.agent;

import edu.ohiou.imse.ise589.Circle;
import edu.ohiou.imse.ise589.DrawObject;
import edu.ohiou.labimp.agent.*;
import edu.ohiou.labimp.agent.task.DRTask;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DApplet;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DPanel;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Shape;
import java.io.*;
import java.util.LinkedList;

//draw rectangle

public class DRAgent extends ServiceAgent {
	
  public DRAgent() {
    super ("DRAgent", 1111);
  }

  public DRAgent(String name, int port){
    super(name, port);
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.DR,state.getCurrent(),name,agentQueue.size());
  }

  public Object sserviceTask(IMPTask task) {
	DRTask gt=(DRTask) task;
	LinkedList<Shape> dl= gt.getList();
	 IMPAgentViewer iw= new IMPAgentViewer();
	 iw.setDrawList(dl);
	graphPanel.add(new Draw2DPanel(iw),BorderLayout.CENTER); 
	graphPanel.updateUI();
    return null;
  }
  Runnable growth;
  
  public Object serviceTask (IMPTask task) {
	  
	  graphPanel.add(new Draw2DPanel((DrawObject) task.getContents(), new Draw2DApplet()),BorderLayout.CENTER);
	  graphPanel.updateUI();
//		System.out.println("\nin DR agent content: " + contents);
		System.out.println("\nin DR task content bef: " + task.getContents());

	  Thread runner = new Thread ((DRTask) task);
	  runner.start();
	  return task.getContents();
  }

  public static void main(String [] args){
    DRAgent agent = new DRAgent("DR",0);
    agent.display();
  }

@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}

}

