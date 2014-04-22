package edu.iastate.cs362.hb.model.impl;

import java.util.Arrays;
import java.util.Set;

import edu.iastate.cs362.hb.model.IVariable;

public class HBVariable implements IVariable {

	// The type of the variable
	private String type;
	
	// It's id
	private long id;
	
	private String name;
	
	private Set<String> modifiers;
	
	public HBVariable(String name, String type) {
		this.name = name;
		this.type = type;
	}

	@Override
	public String getName() {	
		return name;
	}

	@Override
	public boolean hasName(String name) {
		return this.name.equals(name);
	}

	@Override
	public void addModifiers(String... modifiers) {
		this.modifiers.addAll(Arrays.asList(modifiers));
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
	public String getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		HBVariable other = (HBVariable) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}