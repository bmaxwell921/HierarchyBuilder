package edu.iastate.cs362.hb.model.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import edu.iastate.cs362.hb.model.IInstanceField;

public class HBInstanceField implements IInstanceField {
	
	private long id;
	
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

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public boolean hasId(long id) {
		return this.id == id;
	}
}
