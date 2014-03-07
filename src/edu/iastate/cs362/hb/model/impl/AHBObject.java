package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.constants.ObjectConstants;
import edu.iastate.cs362.hb.model.IObject;

public class AHBObject implements IObject {

	// Id of this object
	private int id;
	
	// This object's pacakge
	private String pkg;
	
	// This object's name
	private String name;
	
	/**
	 * Creates a new AHBObject with the given name that
	 * goes in the default package
	 * @param name
	 */
	public AHBObject(String name) {
		this.name = name;
		this.pkg = ObjectConstants.DEFAULT_PKG;
	}

	@Override
	public String getName() {
		return this.name;
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
	public boolean addPackage(String packageName) {
		this.pkg = packageName;
		return true;
	}

	@Override
	public boolean removePackage() {
		this.pkg = ObjectConstants.DEFAULT_PKG;
		return true;
	}

	@Override
	public String getPackage() {
		return pkg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pkg == null) ? 0 : pkg.hashCode());
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
		AHBObject other = (AHBObject) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pkg == null) {
			if (other.pkg != null)
				return false;
		} else if (!pkg.equals(other.pkg))
			return false;
		return true;
	}

}
