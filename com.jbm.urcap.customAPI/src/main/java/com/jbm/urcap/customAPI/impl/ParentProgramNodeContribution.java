package com.jbm.urcap.customAPI.impl;

import java.awt.Color;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.ProgramAPI;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.program.ProgramModel;
import com.ur.urcap.api.domain.program.nodes.ProgramNodeFactory;
import com.ur.urcap.api.domain.program.nodes.builtin.AssignmentNode;
import com.ur.urcap.api.domain.program.nodes.contributable.URCapProgramNode;
import com.ur.urcap.api.domain.program.structure.ProgramNodeVisitor;
import com.ur.urcap.api.domain.program.structure.TreeNode;
import com.ur.urcap.api.domain.program.structure.TreeStructureException;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;

public class ParentProgramNodeContribution implements ProgramNodeContribution{

	private final ProgramAPIProvider apiProvider;
	private final ParentProgramNodeView view;
	private final UndoRedoManager undoRedoManager;
	
	private static String[] colors = {"Red", "Green", "Blue"};
	
	public ParentProgramNodeContribution(ProgramAPIProvider apiProvider, ParentProgramNodeView view) {
		this.apiProvider = apiProvider;
		this.view = view;
		this.undoRedoManager = this.apiProvider.getProgramAPI().getUndoRedoManager();
	}
	
	public void requestToAddChildNode() {
		if(!childNodeAlreadyExists()) {
			addChildNode();
		}
	}
	
	public void requestToReColorChildNode(String color) {
		if(childNodeAlreadyExists()) {
			setChildNodeColor(colorFromString(color));
		}
	}
	
	private void addChildNode() {
		ProgramModel programModel = apiProvider.getProgramAPI().getProgramModel();
		final ProgramNodeFactory nf = programModel.getProgramNodeFactory();
		final TreeNode root = programModel.getRootTreeNode(this);
		undoRedoManager.recordChanges(new UndoableChanges() {
			@Override
			public void executeChanges() {
				try {
					root.addChild(nf.createURCapProgramNode(ChildProgramNodeService.class));
				} catch (TreeStructureException e) {
					// TODO: handle exception
				}
			}
		});
	}
	
	private boolean childNodeAlreadyExists() {
		final boolean[] foundChild = new boolean[1];
		foundChild[0] = false;
		TreeNode root = apiProvider.getProgramAPI().getProgramModel().getRootTreeNode(this);
		root.traverse(new ProgramNodeVisitor() {
			@Override
			public void visit(URCapProgramNode programNode, int index, int depth) {
				if(programNode.canGetAs(MyCustomAPI.class)) {
					foundChild[0] = true;
				}
//				super.visit(programNode, index, depth);
			}
		});
		return foundChild[0];
	}
	
	private void setChildNodeColor(final Color color) {
		// Get the root-node (this program node)
		TreeNode root = apiProvider.getProgramAPI().getProgramModel().getRootTreeNode(this);
		// Traverse the program tree, using the ProgramNodeVisitor
		root.traverse(new ProgramNodeVisitor() {
			// Override "visit" looking for "URCapProgramNode" nodes
			// Visit will be called for every URCapProgramNode in sub-tree
			@Override
			public void visit(URCapProgramNode programNode, int index, int depth) {
				// Check if the found program node implements the "MyCustomAPI" interface
				if(programNode.canGetAs(MyCustomAPI.class)) {
					// If it did, we found the right node
					// Now we can use "getAs" since we already tested with "canGetAs"
					// After "getAs" we can now call the interface methods 
					programNode.getAs(MyCustomAPI.class).setColor(color);
				}
//				super.visit(programNode, index, depth);
			}
		});
	}
	
	private Color getChildNodeColor() {
		final Color[] color = new Color[1];
		// Get the root-node (this program node)
		TreeNode root = apiProvider.getProgramAPI().getProgramModel().getRootTreeNode(this);
		// Traverse the program tree, using the ProgramNodeVisitor
		root.traverse(new ProgramNodeVisitor() {
			// Override "visit" looking for "URCapProgramNode" nodes
			// Visit will be called for every URCapProgramNode in sub-tree
			@Override
			public void visit(URCapProgramNode programNode, int index, int depth) {
				// Check if the found program node implements the "MyCustomAPI" interface
				if(programNode.canGetAs(MyCustomAPI.class)) {
					// If it did, we found the right node
					// Now we can use "getAs" since we already tested with "canGetAs"
					// After "getAs" we can now call the interface methods 
					color[0] = programNode.getAs(MyCustomAPI.class).getColor();
				}
//				super.visit(programNode, index, depth);
			}
		});
		return color[0];
	}
	
	@Override
	public void openView() {
		view.setColorComboBoxItems(colors);
		
		boolean childExists = childNodeAlreadyExists();
		
		view.setImplementButtonEnabled(!childExists);
		view.setColorComboBoxEnabled(childExists);
		
		if(childExists) {
			view.setColorComboBoxSelectedItem(stringFromColor(getChildNodeColor()));
		}
	}

	@Override
	public void closeView() {
	}

	@Override
	public String getTitle() {
		return "Parent Node";
	}

	@Override
	public boolean isDefined() {
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		writer.writeChildren();
	}

	private Color colorFromString(String string) {
		if(string.equals(colors[0])) {
			return Color.RED;
		} else if(string.equals(colors[1])) {
			return Color.GREEN;
		} else {
			return Color.BLUE;
		}
	}
	
	private String stringFromColor(Color color) {
		if(color.equals(Color.RED)) {
			return colors[0];
		} else if(color.equals(Color.GREEN)) {
			return colors[1];
		} else {
			return colors[2];
		}
	}
	
}
