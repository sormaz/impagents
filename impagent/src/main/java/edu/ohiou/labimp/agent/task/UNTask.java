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
public class UNTask extends CSGTask {
	/**
	 * 
	 */
	public UNTask() {
		this (null, IMPTask.UN);
	}
	
	public UNTask(Object contents) {
		this(contents, IMPTask.UN);
	}

	/**
	 * @param c
	 * @param id
	 */
	public UNTask(Object c, int id) {
		super(c, id);
		contents = new Union((DrawObject)CSGAgent.inContents, (DrawObject) c);
		//contents = new Union(this.triangle, (DrawObject) c);
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
//		a.add(((BooleanNode)contents).getRight().getArea());
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
		UNTask task = new UNTask (new Circle(2,4,4));
		Thread thread = new Thread (task);
		thread.start();
		((DrawObject)task.contents).display();
	}

}
