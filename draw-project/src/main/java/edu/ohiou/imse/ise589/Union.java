/**
 * 
 */
package edu.ohiou.imse.ise589;

import java.awt.geom.Area;

/**
 * @author sormaz
 *
 */
public class Union extends BooleanNode {

	/**
	 * @param l
	 * @param r
	 */
	public Union(DrawObject l, DrawObject r) {
		super(l, r);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see edu.ohiou.imse.ise589.BooleanNode#getArea()
	 */
	@Override
	public Area getArea() {
		Area result;
//		result=(Area)left.getArea().clone() ;
		result = new Area (left.getArea());
		result.add(right.getArea());
		
//		System.out.println("Left Area(circle):" + left.getArea().getBounds().x 
//				+","+ left.getArea().getBounds().y
//				+","+left.getArea().getBounds().height
//				+","+left.getArea().getBounds().width);
//		System.out.println("Right Area:" + right.getArea().getBounds().x 
//				+","+ right.getArea().getBounds().y
//				+","+right.getArea().getBounds().height
//				+","+right.getArea().getBounds().width);
//		System.out.println("Result:" + result.getBounds().x 
//		+","+ result.getBounds().y
//		+","+result.getBounds().height
//		+","+result.getBounds().width);

		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Circle c1 = new Circle(0,0,2);
		Circle c2 = new Circle (5,5,1);
		Circle c3 = new Circle (7,7,1);
		   Star star = new Star(1,1,3,3,1,3,3,1,2,4);
		Union u = new Union(c1,c2);
		Union unew = new Union((DrawObject)u,c3);
		
		unew.display();
		// TODO Auto-generated method stub

	}

	@Override
	public void move(double deltaX, double deltaY) {
		// TODO Auto-generated method stub
		
	}

}
