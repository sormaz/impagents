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
public class SetDifference extends BooleanNode {

	/**
	 * @param l
	 * @param r
	 */
	public SetDifference (DrawObject l, DrawObject r) {
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
		result.subtract(right.getArea());
//		result.exclusiveOr(left.getArea());

		return result;
	}
	
 	public LinkedList getDrawList() {
		LinkedList list = super.getDrawList();
		list.addAll(right.getDrawList());
		return list;
	 	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Circle c1 = new Circle(0,0,1);
		Circle c2 = new Circle (1,0,1);
	    SetDifference d= new SetDifference(c1,c2);	
		d.display();  
		
		// TODO Auto-generated method stub

	}



	@Override
	public void move(double deltaX, double deltaY) {
		// TODO Auto-generated method stub
		
	}

}
