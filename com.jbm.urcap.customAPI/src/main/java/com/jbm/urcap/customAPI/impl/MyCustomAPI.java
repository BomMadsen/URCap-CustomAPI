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
	
	public Color getColor();
	
	public void setColor(Color color);
	
}
