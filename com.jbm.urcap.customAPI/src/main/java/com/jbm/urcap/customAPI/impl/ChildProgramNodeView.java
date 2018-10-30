package com.jbm.urcap.customAPI.impl;

import java.awt.Color;

import javax.swing.JPanel;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class ChildProgramNodeView implements SwingProgramNodeView<ChildProgramNodeContribution>{

	private final JPanel colorPanel = new JPanel();
	
	@Override
	public void buildUI(JPanel panel, ContributionProvider<ChildProgramNodeContribution> provider) {
		
		panel.add(colorPanel);
		
	}

	public void pdateColor(Color color) {
		colorPanel.setBackground(color);
	}
	
}
