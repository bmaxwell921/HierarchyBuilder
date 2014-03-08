package edu.iastate.cs362.hb.model.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.iastate.cs362.hb.model.IInstanceField;

public class InstanceField implements IInstanceField {
	
	private String name;
	
	private Set<String> modifiers;
	
	public InstanceField(String name){
		this.name = name;
		this.modifiers = new HashSet<String>();
	}
	
	@Override
	public void addModifiers(String... modifier) {
		modifiers.addAll(Arrays.asList(modifier));
	}

	@Override
	public String getName() {
		return name;
	}
}
