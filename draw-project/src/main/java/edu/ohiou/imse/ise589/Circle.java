package edu.ohiou.imse.ise589;

import java.lang.*;
import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.text.*;
import java.sql.*;

public class Circle extends DrawObject {
  private double rad;
  int i;
  double x1,y1;
  public Circle (double x1, double y1, double rad) {
    super (x1,y1);
    this.rad = rad;
    this.x1=x1;
    this.y1=y1;
  }
  
  public void setRadius(double r) {
	  rad = r;
	  
  }
  
  public double getRadius() {
	  return rad;
  }
  
  public void printout() {
    System.out.println(super.toString());
  }
  
  private class CirclePanel extends JPanel {
    public CirclePanel() {
      add(new JLabel("Circle:"));
      add(new JTextField (Circle.this.toString()));
    }
  }
  private class EditPanel extends JPanel {
    JFormattedTextField x1text, y1text, radtext;
    public EditPanel() {
      JPanel westPanel = new JPanel();
      JPanel northPanel = new JPanel();
      northPanel.setLayout(new BorderLayout());
      setLayout(new BorderLayout());
      westPanel.setLayout (new GridLayout (3,1));
      westPanel.add(new JLabel("x1"));
      westPanel.add(new JLabel("y1"));
      westPanel.add(new JLabel("Radius:"));
      northPanel.add(westPanel, BorderLayout.WEST);
      JPanel centerPanel = new JPanel();
      x1text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x1text.addFocusListener(new FocusAdapter() {
        public void focusLost (FocusEvent focus) {
          position.x = Double.parseDouble(x1text.getText());
        }
      });
      x1text.setBounds(10,10,10,10);
      y1text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y1text.addFocusListener(new FocusAdapter() {
        public void focusLost (FocusEvent focus) {
          position.y = Double.parseDouble(y1text.getText());
        }
      });
      y1text.setBounds(10,10,10,10);
      radtext = new JFormattedTextField(NumberFormat.getNumberInstance());
      radtext.addFocusListener(new FocusAdapter() {
        public void focusLost (FocusEvent focus) {
          rad = Double.parseDouble(radtext.getText());
        }
      });
      radtext.setBounds(10,10,10,10);
      centerPanel.setLayout (new GridLayout(3,1));
      centerPanel.add(x1text);
      centerPanel.add(y1text);
      centerPanel.add(radtext);
      northPanel.add(centerPanel,BorderLayout.CENTER);
      add(northPanel, BorderLayout.NORTH);
    }
  }
  public void init() {
    panel = new CirclePanel();
  }
  
  public Area getArea() {
	  Ellipse2D.Double ellipse = new Ellipse2D.Double(position.x - rad, 
			  							position.y - rad, 2 * rad, 2* rad);
	  
	    return new Area(ellipse);
  }
  public JPanel makeEditPanel() {
    panel = new EditPanel();
    return panel;
  }
  public void update(){
	position.x=x1;
	position.y=y1;
    Timestamp t = new Timestamp(System.currentTimeMillis());
    Time = t.getTime();
    time = t.toString();
  }
  
  public void move (double deltaX, double deltaY) {
	  x1 += deltaX;
	  y1 += deltaY;
	
	  update();
	  
  }
  public static void main (String args []) {
    Circle circle = new Circle(10,20,30);
//    circle.setNeedUpdate(true);
    circle.display();
  }
}
