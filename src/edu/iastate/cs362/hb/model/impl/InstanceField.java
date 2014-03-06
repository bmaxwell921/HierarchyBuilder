package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IInstanceField;

public class InstanceField implements IInstanceField {
	
	private String name;
	
	public InstanceField(String name, String... modifiers){
		this.name = name;
	}
}
