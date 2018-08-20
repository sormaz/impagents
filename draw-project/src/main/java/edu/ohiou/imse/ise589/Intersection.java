/**
 * 
 */
package edu.ohiou.imse.ise589;

import java.awt.geom.Area;
import java.util.LinkedList;

/**
 * @author sormaz
 *
 */
public class Intersection extends BooleanNode {

	/**
	 * @param l
	 * @param r
	 */
	public Intersection (DrawObject l, DrawObject r) {
		super(l, r);
		// TODO Auto-generated constructor stub
	}

   
	
	/* (non-Javadoc)
	 * @see edu.ohiou.imse.ise589.BooleanNode#getArea()
	 */
	@Override
	public Area getArea() {
		Area result;
		result = new Area (left.getArea());
		result.intersect(right.getArea());	
//		right.getArea().intersect(result);
		
//		System.out.println("Left Area(circle):" + left.getArea().getBounds().x 
//				+","+ left.getArea().getBounds().y
//				+","+left.getArea().getBounds().height
//				+","+left.getArea().getBounds().width);
//		System.out.println("Right Area(circle):" + right.getArea().getBounds().x 
//				+","+ right.getArea().getBounds().y
//				+","+right.getArea().getBounds().height
//				+","+right.getArea().getBounds().width);

		return result;
	}
	
	 	public LinkedList geetDrawList() {
		LinkedList list = super.geetDrawList();
		list.addAll(left.geetDrawList());
//		list.addAll(right.getDrawList());
		return list;
	 	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Circle c1 = new Circle(0,0,2);
		Circle c2 = new Circle (1,1,1);
		Circle c3= new Circle (0,0,5);
		 Star star = new Star(1,1,3,3,1,3,3,1,2,4);
		Intersection u = new Intersection(c1,c2);
		Intersection unew = new Intersection((DrawObject)u,c3);	
		unew.display();  
		
		// TODO Auto-generated method stub

	}



	@Override
	public void move(double deltaX, double deltaY) {
		// TODO Auto-generated method stub
		
	}

}
