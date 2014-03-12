package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IObjectBox;

/**
 * Concrete implementation of IObjectBox
 * @author Brandon
 *
 */
public class HBObjectBox implements IObjectBox {

	private long id;
	
	private String name;
	
	private String pkg;
	
	public HBObjectBox(long id, String name, String pkg) {
		this.id = id;
		this.name = name;
		this.pkg = pkg;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPkg() {
		return pkg;
	}
	
	@Override
	public String toString() {
		return String.format("Id: %d, Name: %s, Package: %s", id, name, pkg);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		HBObjectBox other = (HBObjectBox) obj;
		if (id != other.id)
			return false;
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
