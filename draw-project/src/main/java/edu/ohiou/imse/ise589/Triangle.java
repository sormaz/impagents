package edu.ohiou.imse.ise589;

import java.lang.*;
import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.text.*;
import java.sql.*;

public class Triangle extends DrawObject {
  private double x1, y1, x2, y2, x3, y3;
  public Triangle(double x1, double y1, double x2, double y2, double x3,
                  double y3) {
    super( (x1+x2+x3)/3, (y1+y2+y3)/3);
    this.x1 = x1;
    this.x2 = x2;
    this.x3 = x3;
    this.y1 = y1;
    this.y2 = y2;
    this.y3 = y3;
  }
  public void printout() {
    System.out.println(super.toString());
  }
  private class TrianglePanel
      extends JPanel {
    public TrianglePanel() {
      add(new JLabel("Triangle:"));
      add(new JTextField(Triangle.this.toString()));
    }
  }
  private class EditPanel
      extends JPanel {
    JFormattedTextField x1text, y1text, x2text, y2text, x3text, y3text;
    public EditPanel() {
      JPanel westPanel = new JPanel();
      JPanel northPanel = new JPanel();
      northPanel.setLayout(new BorderLayout());

      setLayout(new BorderLayout());
      westPanel.setLayout(new GridLayout(6, 1));
      westPanel.add(new JLabel("x1"));
      westPanel.add(new JLabel("y1"));
      westPanel.add(new JLabel("x2"));
      westPanel.add(new JLabel("y2"));
      westPanel.add(new JLabel("x3"));
      westPanel.add(new JLabel("y3"));
      northPanel.add(westPanel, BorderLayout.WEST);
      JPanel centerPanel = new JPanel();
      x1text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x1text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          x1 = Double.parseDouble(x1text.getText());
        }
      });
      x1text.setBounds(10, 10, 10, 10);
      y1text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y1text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          y1 = Double.parseDouble(y1text.getText());
        }
      });
      y1text.setBounds(10, 10, 10, 10);
      x2text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x2text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          x2 = Double.parseDouble(x2text.getText());
        }
      });
      x2text.setBounds(10, 10, 10, 10);
      y2text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y2text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          y2 = Double.parseDouble(y2text.getText());
        }
      });
      y2text.setBounds(10, 10, 10, 10);
      x3text = new JFormattedTextField(NumberFormat.getNumberInstance());
      x3text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          x3 = Double.parseDouble(x3text.getText());
        }
      });
      x3text.setBounds(10, 10, 10, 10);
      y3text = new JFormattedTextField(NumberFormat.getNumberInstance());
      y3text.addFocusListener(new FocusAdapter() {
        public void focusLost(FocusEvent focus) {
          y3 = Double.parseDouble(y3text.getText());
        }
      });
      y3text.setBounds(10, 10, 10, 10);
      centerPanel.setLayout(new GridLayout(6, 1));
      centerPanel.add(x1text);
      centerPanel.add(y1text);
      centerPanel.add(x2text);
      centerPanel.add(y2text);
      centerPanel.add(x3text);
      centerPanel.add(y3text);
      northPanel.add(centerPanel, BorderLayout.CENTER);
      add(northPanel, BorderLayout.NORTH);
    }
  }
  public void init() {
    panel = new TrianglePanel();
  }
  public Area getArea() {
	  GeneralPath path = new GeneralPath();
    path.moveTo(x1, y1);
    path.lineTo(x2, y2);
    path.lineTo(x3, y3);
    path.lineTo(x1, y1);
    return new Area(path);
  }
  public JPanel makeEditPanel() {
    panel = new EditPanel();
    return panel;
  }
  public void update(){
    position.x = (x1+x2+x3)/3;
    position.y = (y1+y2+y3)/3;
    Timestamp t = new Timestamp(System.currentTimeMillis());
    Time = t.getTime();
    time = t.toString();
  }
  
  public void move (double deltaX, double deltaY) {
	  x1 += deltaX;
	  y1 += deltaY;
	  x2 += deltaX;
	  y2 += deltaY;
	  x3 += deltaX;
	  y3 += deltaY;
	  update();
	  
  }
  public static void main(String args[]) {
    Triangle triangle = new Triangle(1, 2, 2, 6, 8, 5);
    triangle.setNeedUpdate(true);
    triangle.display();
  }
}