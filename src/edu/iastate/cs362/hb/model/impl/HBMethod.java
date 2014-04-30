package edu.iastate.cs362.hb.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.model.IMethod;
import edu.iastate.cs362.hb.model.IVariable;


/**
 * Class to represent a method. Comes from AHBObject because
 * it's got useful stuff that Methods need
 * @author Brandon
 *
 */
public class HBMethod implements IMethod {
	
	private static final String A_MAJ_DEL = ",";
	private static final String A_MIN_DEL = ":";
	
	// This guy's id
	private long id;
	
	// Name of the method
	private String name;
	
	//Return type of method
	private String returnType;
	
	// All the arguments
	private List<IVariable> args;
	
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
	public void setId(long id) {
		this.id = id;
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
	public void addModifiers(Set<String> modifiers){
		this.modifiers.addAll(modifiers);
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
	public List<IVariable> getArguments() {
		return args;
	}

	// Equality and Hashes only depend on the name and arguments
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((args == null) ? 0 : args.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HBMethod other = (HBMethod) obj;
		if (args == null) {
			if (other.args != null)
				return false;
		} else if (!args.equals(other.args))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public void addArguments(String args) {
		// No arguments
		if (args == null) {
			return;
		}
		StringTokenizer st = new StringTokenizer(args, A_MAJ_DEL + CmdConstants.RegexOp.OR + A_MIN_DEL);
		while (st.hasMoreTokens()) {
			String type = st.nextToken();
			String value = st.nextToken();
			this.args.add(new HBVariable(type, value));
		}
	}
	
	@Override
	public void addArguments(List<String> methodArgs){
		for(int i = 0; i < methodArgs.size(); i++){
			StringTokenizer st = new StringTokenizer(methodArgs.get(i), A_MAJ_DEL + CmdConstants.RegexOp.OR + A_MIN_DEL);
			String type = st.nextToken();
			String value = st.nextToken();
			this.args.add(new HBVariable(type, value));
		}
	}

	@Override
	public String list() {
		String toRet = "";
		for(String mod: modifiers){
			toRet += mod + " ";
		}
		toRet += " " + name + "(";
		for(int i = 0; i < args.size(); i++){
			toRet += args.get(i) + ", ";
		}
		toRet += ")";
		return toRet;
	}

	@Override
	public void addReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReturnType() {
		return returnType;
	}
}
