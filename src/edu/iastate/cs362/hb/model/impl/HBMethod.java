package edu.iastate.cs362.hb.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.model.IArgument;
import edu.iastate.cs362.hb.model.IMethod;


/**
 * Class to represent a method. Comes from AHBObject because
 * it's got useful stuff that Methods need
 * @author Brandon
 *
 */
public class HBMethod implements IMethod {
	
	// This guy's id
	private int id;
	
	// Name of the method
	private String name;
	
	// All the arguments
	private List<IArgument> args;
	
	// Modifiers for this method
	private Set<String> modifiers;
	
	public HBMethod(String name) {
		this.name = name;
		this.args = new ArrayList<>();
		this.modifiers = new HashSet<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasName(String name) {
		return this.name != null && this.name.equals(name);
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public boolean hasId(long id) {
		return this.id == id;
	}

	@Override
	public void addModifiers(String... modifier) {
		this.modifiers.addAll(Arrays.asList(modifier));
	}

	@Override
	public boolean removeModifier(String modifier) {
		return this.modifiers.remove(modifier);
	}

	@Override
	public Set<String> getModifiers() {
		return modifiers;
	}

	@Override
	public boolean isStatic() {
		return this.modifiers.contains(CmdConstants.Flags.STATIC);
	}

	@Override
	public List<IArgument> getArguments() {
		return args;
	}

}
