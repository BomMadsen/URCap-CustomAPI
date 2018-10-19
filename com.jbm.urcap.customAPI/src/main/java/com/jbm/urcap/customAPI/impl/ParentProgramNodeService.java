package com.jbm.urcap.customAPI.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class ParentProgramNodeService implements SwingProgramNodeService<ParentProgramNodeContribution, ParentProgramNodeView>{

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParentProgramNodeView createView(ViewAPIProvider apiProvider) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParentProgramNodeContribution createNode(ProgramAPIProvider apiProvider, ParentProgramNodeView view,
			DataModel model, CreationContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
