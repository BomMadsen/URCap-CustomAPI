package com.jbm.urcap.customAPI.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class ParentProgramNodeView implements SwingProgramNodeView<ParentProgramNodeContribution>{

	private JButton ImplementButton = new JButton();
	private JComboBox<String> ColorCombo = new JComboBox<String>();
	
	@Override
	public void buildUI(JPanel panel, ContributionProvider<ParentProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(createBuildChildTreeButtonBox(ImplementButton, provider));
		panel.add(createSpacer(30));
		panel.add(createColorChooserComboBox(ColorCombo, provider));
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
	
	public void setColorComboBoxEnabled(boolean enabled) {
		ColorCombo.setEnabled(enabled);
		if(enabled) {
			ColorCombo.setForeground(new Color(0,0,0));
		} else {
			ColorCombo.setForeground(new Color(192, 192, 192));
		}
	}
	
	public void setColorComboBoxItems(String[] items) {
		ColorCombo.removeAllItems();
		ColorCombo.setModel(new DefaultComboBoxModel<String>(items));
	}
	
	public void setColorComboBoxSelectedItem(String item) {
		ColorCombo.setSelectedItem(item);
	}

	private Box createBuildChildTreeButtonBox(final JButton button, 
			final ContributionProvider<ParentProgramNodeContribution> provider) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		JLabel label = new JLabel("Push button to insert child node: ");
		
		button.setPreferredSize(new Dimension(161, 50));
		button.setFocusable(false);
		
		button.setText("IMPLEMENT");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				provider.get().requestToAddChildNode();
			}
		});
		
		box.add(label);
		box.add(button);
		
		return box;
	}
	
	private Box createColorChooserComboBox(final JComboBox<String> combo,
			final ContributionProvider<ParentProgramNodeContribution> provider) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Box.CENTER_ALIGNMENT);
		
		JLabel label = new JLabel("Select the color of the child node");
		
		combo.setPreferredSize(new Dimension(104, 30));
		combo.setMaximumSize(combo.getPreferredSize());
		
		combo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					provider.get(); // TODO Call contribution
				}
			}
		});
		
		box.add(label);
		box.add(combo);
		
		return box;
	}
	
	private Component createSpacer(int height) {
		return Box.createRigidArea(new Dimension(0, height));
	}
	
}
