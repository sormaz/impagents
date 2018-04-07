package edu.ohiou.imse.ise589;
/*
 * Class: ISE589 - Spring 2004
 * Student: MIHNEA ANGHELESCU
 * Title: PROJECT 4
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import edu.ohiou.mfgresearch.labimp.basis.*;

public  class DrawingViewer extends ViewObject {
  Vector vector = new Vector(3);
  
  JList listOfObjects;
  JButton newButton, showButton, deleteButton;
  Draw2DPanel drawPanel;// = new Draw2DPanel();

  public void add(Object object) {
    vector.addElement(object);
  }
  public void delete(int i) {
    vector.removeElementAt(i);
  }
  public String toString() {
    return("The collection") ;
  }
  public class DrawingViewerPanel extends JPanel {
	  
    public DrawingViewerPanel() {
	setLayout(new BorderLayout());
	add(new JLabel("DrawingViewer"), BorderLayout.NORTH);
	listOfObjects = new JList(vector);
	JSplitPane splitPane = new JSplitPane();
	splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
	splitPane.setLeftComponent(new JScrollPane(listOfObjects,
			  ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			  ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
	splitPane.setRightComponent(drawPanel);
	add(splitPane, BorderLayout.CENTER);
	JToolBar toolBar = new JToolBar();
	add(toolBar, BorderLayout.SOUTH);
	newButton = new JButton("New");
	toolBar.add(newButton);
	showButton = new JButton("Show");
	toolBar.add(showButton);
	deleteButton = new JButton("Delete");
	toolBar.add(deleteButton);
	newButton.addActionListener(new newButtonListener());
	showButton.addActionListener(new showButtonListener());
	deleteButton.addActionListener(new deleteButtonListener());
	listOfObjects.addMouseListener(new jListListener());
    }
  }
  
  
  public void init() {
  	drawPanel = new Draw2DPanel(null, new Draw2DApplet());
  	drawPanel.getApplet().setStandAlone(true);
    panel = new DrawingViewerPanel();
  }

  public static void main(String[] args) {
  DrawingViewer viewer = new DrawingViewer();
//  viewer.setApplet(new Draw2DApplet(viewer));
  viewer.display();
}

  class newButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent action) {
      DrawDialog dialog = new DrawDialog(SwingUtilities.getWindowAncestor(panel));
      dialog.setBounds(0,0,400,400);
      dialog.setVisible(true);
      vector.add(dialog.dobj);
      listOfObjects.setListData(vector);
    }
  }
  class showButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent action) {
      DrawObject object = (DrawObject) vector.elementAt(listOfObjects.getSelectedIndex());
  	drawPanel.setTarget(object);
	repaint();
    }
  }
  class deleteButtonListener implements ActionListener {
    public void actionPerformed (ActionEvent action) {
      int i=listOfObjects.getSelectedIndex();
      vector.removeElementAt(i);
      panel.updateUI();
    }
  }
  class jListListener extends MouseAdapter {
    public void mouseClicked (MouseEvent mouse) {
      if (mouse.getClickCount() == 2) {
	DrawObject object = (DrawObject) vector.elementAt(listOfObjects.getSelectedIndex());
//	object.display();
	drawPanel.setTarget(object);
	drawPanel.repaint();
      }
    }
  }


}