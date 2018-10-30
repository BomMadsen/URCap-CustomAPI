package com.jbm.urcap.customAPI.impl;

import java.awt.Color;

public interface MyCustomAPI {

	/*****
	 * 
	 * This is an example of a Custom API for a program node
	 * This interface should be implemented, by the child nodes that should be configured
	 * In this example, the parent node will be able to respectively get and set a Color, from the Child node.
	 * However any other getters, setters etc. could be used. 
	 * 
	 */
	
	public enum MyColor{
		RED(Color.RED, "Red"),
		GREEN(Color.GREEN, "Green"),
		BLUE(Color.BLUE, "Blue");
		
		MyColor(Color color, String name){
			this.color = color;
			this.name= name;
		}
		
		final Color color;
		final String name;
	}
	
	/*****
	 * Method to get the Color of a URCapProgramNode implementing this interface
	 * @return the Color the node has
	 */
	public MyColor getColor();
	
	/*****
	 * Method to set the Color of a URCapProgramNode implementing this interface
	 * @param color the Color of the node
	 */
	public void setColor(MyColor color);
	
}
