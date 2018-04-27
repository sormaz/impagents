/**
 * 
 */
package edu.ohiou.labimp.agent.task;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import edu.ohiou.imse.ise589.*;
import edu.ohiou.labimp.agent.IMPAgent;
import edu.ohiou.labimp.agent.IMPTask;
import edu.ohiou.labimp.agent.agent.CSGAgent;
//import edu.ohiou.labimp.basis.*;


import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.LinkedList;

/**
 * @author sormaz
 *
 */
public class INTask extends CSGTask implements Runnable {
	/**
	 * 
	 */
	public INTask() {
		this (null, IMPTask.IN);
	}
	
	  public INTask(Object contents) {
		    this(contents, IMPTask.IN);
		  }


	/**
	 * @param c
	 * @param id
	 */
	public INTask(Object c, int id) {
		super(c, id);
		contents = new Intersection((DrawObject)CSGAgent.inContents, (DrawObject) c);
	}


	
	public void run () {

		while (true) {
			updateDrawing();
			
			try {
				Thread.currentThread().sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
//	private boolean isDone () {
//		final Area a = new Area (((BooleanNode)contents).getLeft().getArea());
//		a.intersect(((BooleanNode)contents).getRight().getArea());
//		
//		ViewObject vo = new ViewObject() {
//			public LinkedList<Shape> getDrawList () {
//				LinkedList<Shape> list = new LinkedList<Shape>();
////				list.add(((BooleanNode)contents).getLeft().getArea());
////				list.add(((BooleanNode)contents).getRight().getArea());
////				list.add(a);
//				list.add(((BooleanNode)contents).getArea());
//				return list;
//			}
//		};
//		vo.setApplet (new Draw2DApplet(vo));
////		vo.display();
//		System.out.println("Is empty?" + a.isEmpty());
//		return !a.isEmpty();
//		
//	}
	/**
	 * @param args
	 */
	public static void mmain(String[] args) {
		INTask i=new INTask(new Triangle(0,0,1,1,1,0),IMPTask.IN);
		((DrawObject)i.contents).display();
		i.run();
		
		
		// TODO Auto-generated method stub

	}
	
	public static void main (String args []) {
		INTask task = new INTask (new Triangle(3,4,8,5,0,0));
		Thread thread = new Thread (task);
		thread.start();
		((DrawObject)task.contents).display();
	}

}
