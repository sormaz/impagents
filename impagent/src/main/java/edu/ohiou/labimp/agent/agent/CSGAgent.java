/**
 * 
 */
package edu.ohiou.labimp.agent.agent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.ohiou.imse.ise589.Circle;
import edu.ohiou.imse.ise589.DrawObject;
import edu.ohiou.imse.ise589.Rectangle;
import edu.ohiou.imse.ise589.Star;
import edu.ohiou.imse.ise589.Triangle;
import edu.ohiou.labimp.agent.IMPAgentInfo;
import edu.ohiou.labimp.agent.IMPTask;
import edu.ohiou.labimp.agent.ServiceAgent;
import edu.ohiou.labimp.agent.task.*;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DApplet;
import edu.ohiou.mfgresearch.labimp.basis.Draw2DPanel;
import edu.ohiou.mfgresearch.labimp.basis.Drawable2D;

/**
 * @author sormaz
 *
 */
abstract public class CSGAgent extends ServiceAgent {

	Draw2DPanel canvas;
	protected Object text;
	public static  Object inContents= new Rectangle(2.5,3.4,5,7);
	JTree objectTree;
	/**
	 * @param name
	 * @param port
	 */
	public CSGAgent(String name, int port) {
		super(name, port);
		canvas = new Draw2DPanel(null, new Draw2DApplet());
	    graphPanel.setLayout(new BorderLayout());
		graphPanel.add(canvas,BorderLayout.CENTER);
	}

	  public Object serviceTask (IMPTask task) {
		  canvas.setTarget ((Drawable2D)task.getContents());
		  graphPanel.updateUI();

		 taskRunner = new Thread ((CSGTask) task);
		 taskRunner.start();
		  return task.getContents();
	  }
	  

	  
	  public JPanel getSpecificPanel(){
		  final JPanel panel=new JPanel();
		  panel.setLayout(new BorderLayout());
		  JButton createButton = new JButton("Create");
		  JButton displayButton = new JButton("display");
		  
		  createButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					inContents = DrawObject.newDrawObject(
							SwingUtilities.getWindowAncestor(panel)); //createTask();
					text = inContents;
//					updateUI();
				}
			
		});
		  displayButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					DrawObject d = (DrawObject) inContents;
					d.display();
					}
				
			});
		  
		  /*objectTree=new JTree((TreeNode) null);
			panel.add(new JScrollPane (objectTree), BorderLayout.CENTER);
			objectTree.getSelectionModel().setSelectionMode
			(TreeSelectionModel.SINGLE_TREE_SELECTION);
			
			objectTree.addTreeSelectionListener(new TreeSelectionListener(){
				public void valueChanged(TreeSelectionEvent arg0) {

//					Returns the last path element of the selection.
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)
					objectTree.getLastSelectedPathComponent();
					if (node == null)
						//Nothing is selected.	
						return;

					DrawObject d = (DrawObject)node.getUserObject();
					d.display();


				}});
			
			if (inContents != null) {
				DrawObject d = (DrawObject) inContents;
				objectTree.setModel(new DefaultTreeModel(d.getNode()));
			}*/
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(1,2));
			buttonPanel.add(createButton);
			buttonPanel.add(displayButton);
			//panel.add(new JScrollPane (objectTree), BorderLayout.CENTER);
			panel.add(buttonPanel, BorderLayout.NORTH);
			panel.add(graphPanel, BorderLayout.CENTER);
			return panel;
		  
		  
	  }


}
