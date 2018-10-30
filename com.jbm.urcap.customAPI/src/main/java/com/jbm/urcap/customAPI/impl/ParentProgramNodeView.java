package com.jbm.urcap.customAPI.impl;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class ParentProgramNodeView implements SwingProgramNodeView<ParentProgramNodeContribution>{

	@Override
	public void buildUI(JPanel panel, ContributionProvider<ParentProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
	}

}
