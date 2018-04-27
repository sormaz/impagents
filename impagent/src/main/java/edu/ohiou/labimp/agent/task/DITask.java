/**
 * 
 */
package edu.ohiou.labimp.agent.task;

import edu.ohiou.imse.ise589.*;
import edu.ohiou.labimp.agent.IMPAgent;
import edu.ohiou.labimp.agent.IMPTask;
import edu.ohiou.labimp.agent.agent.CSGAgent;
//import edu.ohiou.labimp.basis.*;



import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.LinkedList;

import static java.lang.Math.*;

/**
 * @author sormaz
 *
 */
public class DITask extends CSGTask {
	
	
//	Rectangle rectangle = new Rectangle(originX + radius * cos (alpha),
//										originY+ radius * sin (alpha),
//										7,4);

	/**
	 * 
	 */
	public DITask() {
		this (null, IMPTask.DI);
	}
	
	public DITask(Object contents) {
		this(contents, IMPTask.DI);
	}


	/**
	 * @param c
	 * @param id
	 */
	public DITask(Object c, int id) {
		super(c, id);
		contents = new SetDifference((DrawObject) c, (DrawObject)CSGAgent.inContents );
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
	
	public static void main (String args []) {
		DITask task = new DITask (new Triangle(3,4,8,5,0,0));
		Thread thread = new Thread (task);
		thread.start();
		((DrawObject)task.contents).display();
	}

}

