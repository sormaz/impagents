package edu.ohiou.imse.ise589;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.ohiou.mfgresearch.labimp.basis.*;

public class DrawDialog extends JDialog {
  DrawObject dobj;
  final ButtonGroup group;
  final JRadioButton JRadioButtonTriangle, JRadioButtonCircle, JRadioButtonStar, rectangleButton;
  final ButtonGroup bgroup;
  final JButton okButton, cancelButton;
  public DrawDialog(Window parent) {
    super( (Frame) parent, true);
    setTitle("Create new object");
    JRadioButtonTriangle = new JRadioButton("Triangle");
    JRadioButtonCircle = new JRadioButton("Circle");
    JRadioButtonStar = new JRadioButton("Star");
    rectangleButton = new JRadioButton("Rectangle");
    group = new ButtonGroup();
    group.add(JRadioButtonTriangle);
    group.add(JRadioButtonCircle);
    group.add(JRadioButtonStar);
    group.add(rectangleButton);
    JPanel buttonPanel = new JPanel();
    ButtonListener listener = new ButtonListener();
    JRadioButtonTriangle.addActionListener(listener);
    JRadioButtonCircle.addActionListener(listener);
    JRadioButtonStar.addActionListener(listener);
    rectangleButton.addActionListener(listener);
    buttonPanel.add(JRadioButtonTriangle);
    buttonPanel.add(JRadioButtonCircle);
    buttonPanel.add(JRadioButtonStar);
    buttonPanel.add(rectangleButton);
    getContentPane().add(buttonPanel, BorderLayout.NORTH);
    JPanel bPanel= new JPanel();
    okButton = new JButton("OK");
    bPanel.add(okButton);
    cancelButton = new JButton("Cancel");
    bPanel.add(cancelButton);
    bgroup = new ButtonGroup();
    bgroup.add(okButton);
    bgroup.add(cancelButton);
    getContentPane().add(bPanel, BorderLayout.SOUTH);
    okButton.addActionListener(new okButtonListener());
    cancelButton.addActionListener(new cancelButtonListener());
  }
  class ButtonListener implements ActionListener {

    public void actionPerformed(ActionEvent event) {
      if (dobj != null && dobj.panel != null) {
    	  getContentPane().remove(dobj.panel);
      }
      if (event.getSource() == JRadioButtonTriangle) {
    	  dobj = new Triangle(1, 2, 2, 6, 8, 5);
    	  getContentPane().add(dobj.makeEditPanel(), BorderLayout.CENTER);
      }
      else if (event.getSource() == JRadioButtonCircle) {
    	  dobj = new Circle(10, 20, 30);
    	  getContentPane().add(dobj.makeEditPanel(), BorderLayout.CENTER);
      }
      else if (event.getSource() == JRadioButtonStar) {
    	  dobj = new Star(1, 1, 3, 3, 1, 3, 3, 1, 2, 4);
    	  getContentPane().add(dobj.makeEditPanel(), BorderLayout.CENTER);
      }
      else if (event.getSource() == rectangleButton) {
    		dobj = new Rectangle(1, 1, 3, 3);
    		getContentPane().add(dobj.makeEditPanel(), BorderLayout.CENTER);
    	      }
     DrawDialog.this.validate();
    }
  }
  class okButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent action) {
     dobj.update();
     DrawDialog.this.dispose();
    }
  }
  class cancelButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent action) {
      dobj = null;
      DrawDialog.this.dispose();
    }
  }
}