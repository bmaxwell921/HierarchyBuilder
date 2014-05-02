package edu.iastate.cs362.hb.model.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.iastate.cs362.hb.main.IdManager;
import edu.iastate.cs362.hb.model.IVariable;

public class HBVariable implements IVariable {
	
	// It's id
	private long id;
	
	private String name;
	
	// The type of the variable
	private String type;
	
	private Set<String> modifiers;
	
	public HBVariable(String name, String type) {
		this.name = name;
		this.type = type;
		this.modifiers = new HashSet<>();
	}

	public HBVariable() {
		this("", "");
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
		if (modifiers == null || modifiers.length < 1) {
			return;
		}
		this.modifiers.addAll(Arrays.asList(modifiers[0].split(",")));
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

	@Override
	public String list() {
		String toRet = "";
		List<String> mods = new ArrayList<>(modifiers);
		Collections.sort(mods);
		for(String mod: mods){
			toRet += mod + " ";
		}
		toRet += type + " " + name;
		return toRet;
	}

	@Override
	public boolean update(String name, String type, Set<String> modifiers) {
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
		IdManager.getInstance().updateInfo(id, name);
		return true;
	}
	
	
}
