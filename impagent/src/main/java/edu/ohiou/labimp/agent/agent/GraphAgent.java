package edu.ohiou.labimp.agent.agent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

import edu.ohiou.imse.ise589.*;
import edu.ohiou.labimp.agent.*;

public class GraphAgent extends TaskAgent implements Serializable {

	JTree objectTree;
	
	String [] tasks = {"edu.ohiou.labimp.agent.task.DITask",
			"edu.ohiou.labimp.agent.task.INTask",
			"edu.ohiou.labimp.agent.task.UNTask"};
	
	public static final int CIRCLE = 0;
	public static final int RECTANGLE = 1;
	public static final int STAR = 2;
	public static final int TRIANGLE = 3;
	
	public GraphAgent() {
		this("Graph agent");
	}

	public GraphAgent(String name) {
		super(name);
		setServiceSequence();
		setHostPortList();
	}

	public IMPAgentInfo getInfo() {
		return new IMPAgentInfo(IMPTask.GA, state.getCurrent(), name, 0);
	}

	public void reconfigure() {

//		contents = new Circle (3,4,6);

	}
	public void randomizeSequence () {
		int min = 5, max = 5;
		int count = (int) (min + Math.random() * max);
		serviceSequence = new String [count];
		int previous = -1, choice;
		
		for (int i = 0; i< count; i++) {
			do {
				choice = (int) (3 * Math.random());
			} while (previous == choice);
			serviceSequence [i] = tasks[choice];
			previous = choice;
		}
		
	}
	
	public void randomizeContent () {
		int object = (int) (4 * Math.random());
//		object = TRIANGLE;
		switch (object) {
		case CIRCLE:			
			inContents = new Circle(10 * Math.random(), 10 * Math.random(), 
								2 * Math.random() + 2);
			break;
		case RECTANGLE:
			inContents = new Rectangle(10 * Math.random(), 10 * Math.random(),
				2 * Math.random()+2, 3 * Math.random()+3);
			break;
		case STAR:
//			1,1,3,3,1,3,3,1,2,4
			inContents = new Star(4 * Math.random(),3 * Math.random(),
								3 * Math.random()+9, 3 * Math.random()+6,
								3 * Math.random(),3 * Math.random()+5,
								3 * Math.random()+8,3 * Math.random(),
								3 * Math.random()+4,3 * Math.random()+8);
			break;
		case TRIANGLE:
			inContents = new Triangle(4 * Math.random()+2,4 * Math.random()+2,
					4 * Math.random()+7,4 * Math.random()+2,
					3 * Math.random()+4,3 * Math.random()+8);
			break;
			
		}
			
		
	}
	
	public void updateUI () {
		super.updateUI();
		DrawObject d = (DrawObject) inContents;
		objectTree.setModel(new DefaultTreeModel(d.getNode()));
	}

	public JPanel getSpecificPanel () {
		final JPanel panel=new JPanel();
		panel.setLayout(new BorderLayout());
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					state.setCurrent(IMPState.CONTENT_SET_NOW_SEARCHING);
					Window w = SwingUtilities.getWindowAncestor(panel);
					w.dispose();
				} catch (RuntimeException e1) {
					System.out.println ("exception");
					e1.printStackTrace();
				}
			}
		}); 
		JButton createButton = new JButton("Create");

		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inContents = DrawObject.newDrawObject(
						SwingUtilities.getWindowAncestor(panel)); //createTask();
				text = inContents;
				updateUI();
			}
		}); 
			objectTree=new JTree((TreeNode) null);
			panel.add(new JScrollPane (objectTree), BorderLayout.CENTER);
			objectTree.getSelectionModel().setSelectionMode
			(TreeSelectionModel.SINGLE_TREE_SELECTION);

			//Listen for when the selection changes.
//			objectTree.addMouseListener(new MouseAdapter(){

//			public void mouseClicked(MouseEvent e) {

//			if (e.getClickCount() == 2) {
//			DefaultMutableTreeNode node = (DefaultMutableTreeNode)
//			objectTree.getLastSelectedPathComponent();
//			if (node == null)
//			//Nothing is selected.	
//			return;				  
//			DrawObject d = (DrawObject)node.getUserObject();
//			d.display();
//			}
//			}


//			});
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
	}
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(createButton);
		buttonPanel.add(sendButton);
		panel.add(buttonPanel, BorderLayout.NORTH);
		return panel;
	}



	public static void main(String[] args) {
		String name = "GraphAgent";
		if (args.length > 0) {
			name = args[0];
		}

		GraphAgent agent = new GraphAgent(name);
		agent.display();

	}
}
