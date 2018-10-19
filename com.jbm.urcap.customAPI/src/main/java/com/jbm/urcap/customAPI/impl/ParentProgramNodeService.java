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
		return "parentNode";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		configuration.setChildrenAllowed(true);
	}

	@Override
	public String getTitle(Locale locale) {
		return "MyParentNode";
	}

	@Override
	public ParentProgramNodeView createView(ViewAPIProvider apiProvider) {
		return new ParentProgramNodeView();
	}

	@Override
	public ParentProgramNodeContribution createNode(ProgramAPIProvider apiProvider, ParentProgramNodeView view,
			DataModel model, CreationContext context) {
		return new ParentProgramNodeContribution(apiProvider, view);
	}

}
