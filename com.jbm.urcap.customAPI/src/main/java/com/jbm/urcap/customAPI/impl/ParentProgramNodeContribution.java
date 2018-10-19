package com.jbm.urcap.customAPI.impl;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class ParentProgramNodeContribution implements ProgramNodeContribution{

	private final ProgramAPIProvider apiProvider;
	private final ParentProgramNodeView view;
	
	public ParentProgramNodeContribution(ProgramAPIProvider apiProvider, ParentProgramNodeView view) {
		this.apiProvider = apiProvider;
		this.view = view;
	}
	
	@Override
	public void openView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDefined() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void generateScript(ScriptWriter writer) {
		// TODO Auto-generated method stub
		
	}

}
