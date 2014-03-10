package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IRelationship;

public class HBRelationship implements IRelationship {
	
	private String name;

	public HBRelationship(String relationship) {
		name = relationship;
	}
	
	@Override
	public String getName(){
		return name;
	}

	@Override
	public boolean hasName(String name) {
		return this.name != null && this.name.equals(name);
	}

}
