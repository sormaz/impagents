package edu.ohiou.labimp.agent.task;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.awt.geom.Point2D;

import edu.ohiou.imse.ise589.*;
import edu.ohiou.labimp.agent.IMPTask;
import edu.ohiou.labimp.agent.agent.CSGAgent;

abstract public class CSGTask extends IMPTask implements Runnable {
	double minX = 0, minY = 0, maxX = 10, maxY = 10,  minR =2, maxR = 4, radius = 5;
	double incrX = 0.03, incrY = 0.05, incrR = 0.01, originX = 0, originY = 0, alpha = 0, incrAlpha = 0.01;
	int dirX = 1, dirY = 0, dirR = 1;
	
	public CSGTask(Object c, int id) {
		super(c, id);
		// TODO Auto-generated constructor stub
	}
	
	public void updateDrawing(){
		DrawObject drawObj = (DrawObject)CSGAgent.inContents;
		
		if(drawObj instanceof Triangle || drawObj instanceof Star ){
			Point2D.Double p = drawObj.gettPosition();
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
			
			drawObj.move (incrX * (double)dirX, incrY * (double)dirY);
			((DrawObject)contents).repaint();
		}
		
		if(drawObj instanceof Circle ){
			Point2D.Double p = drawObj.gettPosition();
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
		if(drawObj instanceof Rectangle ){
			Point2D.Double p = drawObj.gettPosition();
			p.x = originX + radius * cos (alpha);
			p.y = originY + radius * sin (alpha);
			alpha += incrAlpha;
			((DrawObject)contents).repaint();
		}
	}


}
