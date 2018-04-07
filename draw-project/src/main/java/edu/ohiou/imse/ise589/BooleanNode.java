package edu.ohiou.imse.ise589;

import java.awt.geom.Area;
import java.util.LinkedList;

import javax.swing.JPanel;
import javax.swing.tree.DefaultMutableTreeNode;

abstract public class BooleanNode extends DrawObject {
	
	DrawObject left;
	DrawObject right;

	public BooleanNode(DrawObject l, DrawObject r) {
		super(l.position.x, l.position.y);
		left = l;
		right = r;
	}
	
	public DrawObject getLeft () {
		return left;
	}

	public DrawObject getRight () {
		return right;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public JPanel makeEditPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
	
	public DefaultMutableTreeNode getNode() {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(this);
		node.add(left.getNode());
		node.add(right.getNode());
		
		return node;
	}

}
