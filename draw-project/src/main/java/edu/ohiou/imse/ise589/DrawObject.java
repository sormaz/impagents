package edu.ohiou.imse.ise589;

import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.sql.*;
import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.*;
import java.util.LinkedList;
import java.io.Serializable;
import java.lang.reflect.*;
import edu.ohiou.mfgresearch.labimp.basis.*;
import edu.ohiou.mfgresearch.labimp.basis.GraphicsConfiguration;

public abstract class DrawObject implements Viewable, Drawable2D, Serializable {
//  protected double xPosition, yPosition;
  protected Point2D.Double position;
  protected long Time;
  protected String time;
  boolean needUpdate=false;

  transient JPanel panel;
  transient Draw2DApplet applet;
  transient Draw2DPanel canvas;
  transient Color color;

  public DrawObject(double x, double y) {
//    applet = new Draw2DApplet(this);
//    canvas = (Draw2DPanel)applet.getCanvas();
    Timestamp t = new Timestamp(System.currentTimeMillis());
//    xPosition = x;
//    yPosition = y;
    position = new Point2D.Double (x,y);
    Time = t.getTime();
    time = t.toString();
  }
  
  public abstract void move(double deltaX, double deltaY);
  public static DrawObject newDrawObject(Window w) {
      DrawDialog dialog = new DrawDialog(w);
      dialog.setBounds(0,0,400,400);
      dialog.setVisible(true);
      return dialog.dobj;

  }
  public long getTime() {
    return Time;
  }
  
	public DefaultMutableTreeNode getNode() {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(this);
		
		return node;
	}

  public abstract void printout();

  public String toString() {
    return "Object type = " + this.getClass().getName() + ", xPosition = " + (int) this.position.getX() +
	   ",  yPosition = " + (int) this.position.getY() + ",  time = " + this.time;
  }
  public void settCanvas (Draw2DPanel param1) {
    canvas = param1;
  }
  public JPanel gettCanvas () {
    return canvas;
  }
  public void paintComponent (Graphics param1) {
   throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public void paintComponent (Graphics2D param1) {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  abstract public Area getArea();

  public LinkedList getStringList() {
    LinkedList strings = new LinkedList();
    strings.add(new DrawString("this is a test",10,10));
    strings.add(new DrawString("this is a resizable test", 20,20,0.5));
    return strings;
  }
  
  public void settPosition (Point2D point) {  
	  position = new Point2D.Double (point.getX(), point.getY());
  }
  
  public void generateImageList() {
		// TODO Auto-generated method stub
		
	}
	public Point2D.Double gettPosition() {
		// TODO Auto-generated method stub
		return position;
	}
	public Collection giveSelectables() {
		// TODO Auto-generated method stub
		return new LinkedList();
	}
	public void makeDrawSets() {
	    color = Color.black;;
 
	    if (canvas instanceof Draw2DPanel) {
	      Draw2DPanel drawPanel = (Draw2DPanel) canvas;
	      drawPanel.addDrawShapes(Color.red, getDrawList());
	      drawPanel.addFillShapes(Color.blue, getFillList());
	    }
		
	}
	

	public LinkedList getDrawList() {
		LinkedList list = new LinkedList();
		list.add(getArea());
		return list;
	}
	
	public LinkedList getFillList() {
		LinkedList list = new LinkedList();
		list.add(getArea());
		return list;
	}

	public void addButtonOptions(int options) {
		
		canvas.addButtonOptions (options);
	}

	public void removeButtonOptions(int options) {
		
		canvas.removeButtonOptions (options);
	}

	public String toToolTipString() {
		// TODO Auto-generated method stub
		return toString();
	}

  public void setNeedUpdate(boolean param1) {
    ((Drawable2D) canvas).setNeedUpdate(param1);
  }
  public void settPanel (JPanel param1) {
    this.panel = param1;
  }
  public JPanel gettPanel() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public int getPanelLocation() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public int getPanelOrientation() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public JPanel makePanel() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public void addPanel() {
    if (applet instanceof Draw2DApplet) {
      Container appletPanel = applet.getContentPane();
      if (appletPanel instanceof JSplitPane) {
	JSplitPane splitPanel = (JSplitPane) appletPanel;
	splitPanel.setOrientation(JSplitPane.VERTICAL_SPLIT);
	splitPanel.setLeftComponent(((Draw2DApplet)applet).getCanvas());
	splitPanel.setRightComponent(panel);
      }
    }
    else
      applet.getContentPane().add(panel,BorderLayout.CENTER);
  }
  public Color gettColor() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public void settColor(Color param1) {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public abstract void init();

  public void display() {
	  display (toString());
   }
  public void display(String title) {
    display(title, 500, 500);
  }
  public void display(String title, Dimension size) {
	  display(title, size, WindowConstants.DISPOSE_ON_CLOSE);
  }
  public void display(String title, int width, int height) {
	  display (title, new Dimension (width, height));
  }
  public void display(String title, Dimension size, int closing) {
	   if (applet == null) {
		      applet = new Draw2DApplet(null);
		      canvas = (Draw2DPanel)applet.getCanvas();
		      applet.setTarget(this);
		    }
		    applet.display(title, size, closing);
 }
  public void settApplet (GUIApplet param1) {
    this.applet = (Draw2DApplet) param1;
  }
  public GUIApplet gettApplet() {
    return applet;
  }
  public Dimension getAppletSize() {
    try {
      if (canvas == null) {
	return panel.getSize();
      }
      else {
	return new Dimension(500, 400);
      }
    }
      catch (NullPointerException POINTER) {
	return GUIApplet.defaultGUIAppletSize;
      }
    }
  public Viewable gettGuiObject() {
    throw new java.lang.UnsupportedOperationException("Method not yet implemented");
  }
  public void settGuiObject(Viewable param1) {
  }
  public void toggleVisible() {
  }
  public void repaint() {
    try {
      ((JPanel)this.gettCanvas()).repaint();
    }
    catch (NullPointerException e) {}
  }
  public abstract JPanel makeEditPanel();
  public abstract void update();
  
  public GraphicsConfiguration getGraphicsConfig() {
	  
	  return new GraphicsConfiguration();
	  
  }
}