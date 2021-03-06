package com.jbm.urcap.customAPI.impl;

import java.awt.Dimension;

import javax.swing.JPanel;

import com.jbm.urcap.customAPI.impl.MyCustomAPI.MyColor;
import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class ChildProgramNodeView implements SwingProgramNodeView<ChildProgramNodeContribution>{

	private final JPanel colorPanel = new JPanel();
	
	@Override
	public void buildUI(JPanel panel, ContributionProvider<ChildProgramNodeContribution> provider) {
		
		colorPanel.setPreferredSize(new Dimension(100, 100));
		panel.add(colorPanel);
		
	}

	public void updateColor(MyColor color) {
		colorPanel.setBackground(color.getColor());
	}
	
}
