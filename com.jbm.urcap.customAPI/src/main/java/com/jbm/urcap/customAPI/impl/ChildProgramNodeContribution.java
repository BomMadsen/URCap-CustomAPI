package com.jbm.urcap.customAPI.impl;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;
import com.ur.urcap.api.domain.undoredo.UndoRedoManager;
import com.ur.urcap.api.domain.undoredo.UndoableChanges;

// The ChildProgramNodeCOntribution implements the MyCustomAPI interface, 
// and must hence override the methods inherited from this interface
public class ChildProgramNodeContribution implements ProgramNodeContribution, MyCustomAPI {

	private final ProgramAPIProvider apiProvider;
	private final ChildProgramNodeView view;
	private final DataModel model;
	private final UndoRedoManager undoRedoManager;
	
	private static final String COLOR_KEY = "colorKey";
	private static final String DEFAULT_COLOR = MyColor.RED.toString();
	
	public ChildProgramNodeContribution(ProgramAPIProvider apiProvider, ChildProgramNodeView view,
			DataModel model) {
		this.apiProvider = apiProvider;
		this.view = view;
		this.model = model;
		this.undoRedoManager = this.apiProvider.getProgramAPI().getUndoRedoManager();
	}
	
	@Override
	public void openView() {
		view.updateColor(getColor());
	}

	@Override
	public void closeView() {
	}

	@Override
	public String getTitle() {
		return getColor().toString()+" Child node";
	}

	@Override
	public boolean isDefined() {
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		writer.appendLine("popup(\"This is a "+getColor()+" Child node\", \"Child node popup\", blocking=True)");
	}

	/*****
	 * Overriding the getColor() method to comply with the MyCustomAPI interface
	 */
	@Override
	public MyColor getColor() {
		String colorInModel = model.get(COLOR_KEY, DEFAULT_COLOR);
		if(colorInModel.equals(MyColor.RED.toString())) {
			return MyColor.RED;
		} else if(colorInModel.equals(MyColor.GREEN.toString())) {
			return MyColor.GREEN;
		} else {
			return MyColor.BLUE;
		}
	}
	
	/*****
	 * Overriding the setColor() method to comply with the MyCustomAPI interface
	 */
	@Override
	public void setColor(final MyColor color) {
		undoRedoManager.recordChanges(new UndoableChanges() {
			
			@Override
			public void executeChanges() {
				model.set(COLOR_KEY, color.toString());
			}
		});
	}

}
