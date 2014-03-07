package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IInstanceField;

public class HBClass implements IClass{

	private String name;
	
	private String hbPackage;
	
	public HBClass(String name){
		this.name = name;
	}
	
	@Override
	public boolean addPackage(String packageName) {
		this.hbPackage = packageName;
		return true;
	}

	@Override
	public boolean removePackage() {
		this.hbPackage = null;
		return true;
	}
	
	@Override
	public String getPackage() {
		return this.hbPackage;
	}

	@Override
	public boolean addInstanceField(IInstanceField i) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addInstanceMethod(String methodName, String... modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addStaticMethod(String methodName, String... modifiers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeMethod(String methodName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public boolean hasName(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean hasId(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hbPackage == null) ? 0 : hbPackage.hashCode());
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
		HBClass other = (HBClass) obj;
		if (hbPackage == null) {
			if (other.hbPackage != null)
				return false;
		} else if (!hbPackage.equals(other.hbPackage))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
