package edu.ohiou.labimp.agent.task;

import edu.ohiou.labimp.agent.*;
//import edu.ohiou.labimp.basis.Draw2DPanel;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.*;
import java.io.*;
import java.util.LinkedList;

//draw circle task
public class DCTask extends IMPTask implements Serializable{

  public DCTask() {
    this(null);
  }

  public DCTask(Object contents) {
    super(contents, IMPTask.DC);
  }
  
  public Object execute(){
	  return null;
      
}

  public LinkedList<Shape> getList(){
	  LinkedList<Shape> dl=new LinkedList<Shape>();
	  dl.add(new Ellipse2D.Double(0,0,200,200));
	  return dl;
      
}
  
}


