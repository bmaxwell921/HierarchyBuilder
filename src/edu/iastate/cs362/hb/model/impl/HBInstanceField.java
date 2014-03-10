package edu.iastate.cs362.hb.model.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.iastate.cs362.hb.model.IInstanceField;
import edu.iastate.cs362.hb.model.attributes.Nameable;

public class HBInstanceField implements IInstanceField {
	
	private String name;
	
	private Set<String> modifiers;
	
	public HBInstanceField(String name){
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

	@Override
	public boolean hasName(String name) {
		if(this.name == null){
			return false;
		}
		return this.name.equals(name);
	}

	@Override
	public boolean removeModifier(String modifier) {
		return modifiers.remove(modifier);
	}

	@Override
	public Set<String> getModifiers() {
		return modifiers;
	}
}
