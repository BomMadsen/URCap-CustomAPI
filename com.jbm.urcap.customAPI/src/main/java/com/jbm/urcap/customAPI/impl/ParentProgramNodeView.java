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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jbm.urcap.customAPI.impl.MyCustomAPI.MyColor;
import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class ParentProgramNodeView implements SwingProgramNodeView<ParentProgramNodeContribution>{

	private JButton ImplementButton = new JButton();
	private JComboBox<MyColor> ColorCombo = new JComboBox<MyColor>();
	
	@Override
	public void buildUI(JPanel panel, ContributionProvider<ParentProgramNodeContribution> provider) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(createDescription("Push button to insert child node: "));
		panel.add(createSpacer(10));
		panel.add(createBuildChildTreeButtonBox(ImplementButton, provider));
		panel.add(createSpacer(30));
		panel.add(createDescription("Select the color of the child node: "));
		panel.add(createSpacer(10));
		panel.add(createColorChooserComboBox(ColorCombo, provider));
		
	}
	
	public void setImplementButtonEnabled(boolean enabled) {
		ImplementButton.setEnabled(enabled);
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
	
	public void setColorComboBoxItems(MyColor[] items) {
		ColorCombo.removeAllItems();
		// Since MyColor overrides "toString" we can use this object directly here
		ColorCombo.setModel(new DefaultComboBoxModel<MyColor>(items));
	}
	
	public void setColorComboBoxSelectedItem(MyColor item) {
		ColorCombo.setSelectedItem(item);
	}

	private Box createBuildChildTreeButtonBox(final JButton button, 
			final ContributionProvider<ParentProgramNodeContribution> provider) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Box.LEFT_ALIGNMENT);

		button.setPreferredSize(new Dimension(161, 50));
		button.setFocusable(false);
		
		button.setText("IMPLEMENT");
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				provider.get().requestToAddChildNode();
			}
		});
		
		box.add(button);
		
		return box;
	}
	
	private Box createColorChooserComboBox(final JComboBox<MyColor> combo,
			final ContributionProvider<ParentProgramNodeContribution> provider) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Box.LEFT_ALIGNMENT);
		
		combo.setPreferredSize(new Dimension(104, 30));
		combo.setMaximumSize(combo.getPreferredSize());
		
		combo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					provider.get().requestToReColorChildNode((MyColor) e.getItem());
				}
			}
		});
		
		box.add(combo);
		
		return box;
	}
	
	private Box createDescription(String description) {
		Box box = Box.createHorizontalBox();
		box.setAlignmentX(Box.LEFT_ALIGNMENT);
		
		JLabel label = new JLabel(description);
		
		box.add(label);
		return box;
	}
	
	private Component createSpacer(int height) {
		return Box.createRigidArea(new Dimension(0, height));
	}
	
}
