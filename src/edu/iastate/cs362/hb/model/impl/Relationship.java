package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IRelationship;

public class Relationship implements IRelationship{
	
	private String name;

	public Relationship(String relationship) {
		name = relationship;
	}
	
	@Override
	public String getName(){
		return name;
	}

}
