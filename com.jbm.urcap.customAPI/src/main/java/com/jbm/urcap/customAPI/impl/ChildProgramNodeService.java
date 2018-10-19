package com.jbm.urcap.customAPI.impl;

import java.util.Locale;

import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.data.DataModel;

public class ChildProgramNodeService implements SwingProgramNodeService<ChildProgramNodeContribution, ChildProgramNodeView>{

	@Override
	public String getId() {
		return "childNode";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration) {
		configuration.setUserInsertable(false);
	}

	@Override
	public String getTitle(Locale locale) {
		return "MyChildNode";
	}

	@Override
	public ChildProgramNodeView createView(ViewAPIProvider apiProvider) {
		return new ChildProgramNodeView();
	}

	@Override
	public ChildProgramNodeContribution createNode(ProgramAPIProvider apiProvider, ChildProgramNodeView view,
			DataModel model, CreationContext context) {
		return new ChildProgramNodeContribution(apiProvider, view, model);
	}

}
