package edu.ohiou.labimp.agent.agent;

import edu.ohiou.labimp.agent.*;
import edu.ohiou.labimp.agent.task.*;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DPanel;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Shape;
import java.io.*;
import java.util.LinkedList;

//draw circle

public class DCAgent extends ServiceAgent {
	
  public DCAgent() {
    super ("DCAgent", 1111);
  }

  public DCAgent(String name, int port){
    super(name, port);
  }

  public IMPAgentInfo getInfo() {
    return new IMPAgentInfo(IMPTask.DC,state.getCurrent(),name,agentQueue.size());
  }

  public Object serviceTask(IMPTask task) {
	DCTask gt=(DCTask) task;
	LinkedList<Shape> dl= gt.getList();
	 IMPAgentViewer iw= new IMPAgentViewer();
	 iw.setDrawList(dl);
	graphPanel.add(new Draw2DPanel(iw),BorderLayout.CENTER); 
	graphPanel.updateUI();
    return null;
  }

  public static void main(String [] args){
    DCAgent agent = new DCAgent("DC",0);
    agent.display();
  }

@Override
public JPanel getSpecificPanel() {
	// TODO Auto-generated method stub
	return null;
}

}

