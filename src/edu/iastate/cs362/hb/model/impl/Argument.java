package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IArgument;

/**
 * Concrete type of Argument
 * @author Brandon
 *
 */
public class Argument implements IArgument {

	// The type of the argument
	private String type;
	
	// The name of the argument
	private String name;
	
	public Argument(String type, String name) {
		this.type = type;
		this.name = name;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getName() {
		return name;
	}

}
