package edu.ohiou.imse.ise589;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.sql.Timestamp;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Rectangle extends DrawObject {
	
	double width, height;
	

	public Rectangle(double x, double y, double w, double h) {
		super(x, y);
		width = w;
		height = h;

	}

	  public Area getArea() {
		  Rectangle2D.Double rectangle = new Rectangle2D.Double(position.x, 
				  							position.y, width, height);
		  
		    return new Area(rectangle);
	  }

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public JPanel makeEditPanel() {
	    panel = new EditPanel();
	    return panel;
	}

	@Override
	public void printout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
			    Timestamp t = new Timestamp(System.currentTimeMillis());
			    Time = t.getTime();
			    time = t.toString();
			  

	}
	public void move (double deltaX, double deltaY) {
		  position.x += deltaX;
		  position.y += deltaY;
		
		  update();
		  
	  }
	
	  private class RectanglePanel extends JPanel {
		    public RectanglePanel() {
		      add(new JLabel("Rectangle:"));
		      add(new JTextField (Rectangle.this.toString()));
		    }
		  }
		  private class EditPanel extends JPanel {
		    JFormattedTextField x1text, y1text, widthText, heightText;
		    public EditPanel() {
		      JPanel westPanel = new JPanel();
		      JPanel northPanel = new JPanel();
		      northPanel.setLayout(new BorderLayout());
		      setLayout(new BorderLayout());
		      westPanel.setLayout (new GridLayout (4,1));
		      westPanel.add(new JLabel("x1"));
		      westPanel.add(new JLabel("y1"));
		      westPanel.add(new JLabel("Width:"));
		      westPanel.add(new JLabel("Height:"));
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
		      widthText = new JFormattedTextField(NumberFormat.getNumberInstance());
		      widthText.addFocusListener(new FocusAdapter() {
		        public void focusLost (FocusEvent focus) {
		          width = Double.parseDouble(widthText.getText());
		        }
		      });
		      widthText.setBounds(10,10,10,10);
		      heightText = new JFormattedTextField(NumberFormat.getNumberInstance());
		      heightText.addFocusListener(new FocusAdapter() {
		        public void focusLost (FocusEvent focus) {
		          height = Double.parseDouble(heightText.getText());
		        }
		      });
		      heightText.setBounds(10,10,10,10);
		      centerPanel.setLayout (new GridLayout(4,1));
		      centerPanel.add(x1text);
		      centerPanel.add(y1text);
		      centerPanel.add(widthText);
		      centerPanel.add(heightText);
		      northPanel.add(centerPanel,BorderLayout.CENTER);
		      add(northPanel, BorderLayout.NORTH);
		    }
		  }

}
