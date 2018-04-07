package edu.ohiou.labimp.agent.task;

import edu.ohiou.imse.ise589.Circle;
import edu.ohiou.labimp.agent.*;
//import edu.ohiou.labimp.basis.Draw2DPanel;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.LinkedList;

//draw rectangle task
public class DRTask extends IMPTask implements Serializable, Runnable {

  public DRTask() {
    this(null);
  }

  public DRTask(Object contents) {
    super(contents, IMPTask.DR);
  }
  

  public LinkedList<Shape> getList(){
	  LinkedList<Shape> dl=new LinkedList<Shape>();
	  dl.add(new Rectangle2D.Double(0,0,200,200));
	  return dl;
      
}
  
  public void run () {
	   Circle c = (Circle) contents;
	  while (true) {
	  c.setRadius(c.getRadius() + 0.1);
	  c.repaint();
	  try {
		Thread.currentThread().sleep(500);
	} catch (InterruptedException e) {
		
		e.printStackTrace();
	}
 }
  }
}

