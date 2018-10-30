package com.jbm.urcap.customAPI.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class ParentProgramNodeView implements SwingProgramNodeView<ParentProgramNodeContribution>{

	private JButton ImplementButton = new JButton();
	
	@Override
	public void buildUI(JPanel panel, ContributionProvider<ParentProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(createBuildChildTreeButtonBox(ImplementButton, provider));
	}
	
	public void setImplementButtonEnabled(boolean enabled) {
		ImplementButton.setEnabled(enabled);
		// TODO Also toggle action listener
		if(enabled) {
			ImplementButton.setForeground(new Color(0,0,0));
		} else {
			ImplementButton.setForeground(new Color(192, 192, 192));
		}
	}

	private Box createBuildChildTreeButtonBox(final JButton button, 
			final ContributionProvider<ParentProgramNodeContribution> provider) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		JLabel label = new JLabel("Push button to insert child node: ");
		
		button.setPreferredSize(new Dimension(161, 40));
		button.setFocusable(false);
		
		button.setText("IMPLEMENT");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				provider.get();	// TODO Call event in Contribution
			}
		});
		
		box.add(label);
		box.add(button);
		
		return box;
	}
	
	private Component createSpacer(int height) {
		return Box.createRigidArea(new Dimension(0, height));
	}
	
}
