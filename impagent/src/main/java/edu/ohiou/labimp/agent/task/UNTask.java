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
	Triangle triangle = new Triangle(0,0,0,8,6.92,4);
	
	double minX = 0, minY = 0, maxX = 10, maxY = 10,  minR =2, maxR = 4, radius = 5;
	double incrX = 0.03, incrY = 0.05, incrR = 0.01, originX = 0, originY = 0, alpha = 0, incrAlpha = 0.01;
	int dirX = 1, dirY = 0, dirR = 1;

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
			
		if((DrawObject)CSGAgent.inContents instanceof Triangle || (DrawObject)CSGAgent.inContents instanceof Star ){
				Point2D.Double p = ((DrawObject)CSGAgent.inContents).gettPosition();
				//Point2D.Double p = triangle.gettPosition();
				if (p.x >= maxX) {
					dirX = 0;
					dirY = 1;
				}
				if (p.x <= minX && p.y > maxY) {
					dirX = 0;
					dirY = -1;
				}
				if (p.y >= maxY && p.x > maxX) {
					dirY = 0;
					dirX = -1;
				}
				if (p.y <= minY && p.x < minX) {
					dirY = 0;
					dirX = 1;
				}
				
				//this.triangle.move (incrX * (double)dirX, incrY * (double)dirY);
				
				DrawObject draw= (DrawObject)CSGAgent.inContents;
				draw.move (incrX * (double)dirX, incrY * (double)dirY);
				((DrawObject)contents).repaint();
			}
			if((DrawObject)CSGAgent.inContents instanceof Circle ){
				Point2D.Double p = ((Circle)CSGAgent.inContents).gettPosition();
				double r = ((Circle)CSGAgent.inContents).getRadius();
				if (r >= maxR) { 
					dirR = -1;
				}
				if (r <= minR) {
					dirR = 1;
				}
				if (p.x >= maxX) {
					dirX = -1;
				}
				if (p.x <= minX) {
					dirX = 1;
				}
				if (p.y >= maxY) {
					dirY = -1;
				}
				if (p.y <= minY) {
					dirY = 1;
				}
				
				p.x += incrX * dirX;
				p.y += incrY * dirY;
				 ((Circle)CSGAgent.inContents).setRadius( r + incrR * dirR);
				((DrawObject)contents).repaint();
			}
			if((DrawObject)CSGAgent.inContents instanceof Rectangle ){
				Point2D.Double p = ((Rectangle)CSGAgent.inContents).gettPosition();


				
					p.x = originX + radius * cos (alpha);
					p.y = originY + radius * sin (alpha);
					alpha += incrAlpha;
					((DrawObject)contents).repaint();
			}
			
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
