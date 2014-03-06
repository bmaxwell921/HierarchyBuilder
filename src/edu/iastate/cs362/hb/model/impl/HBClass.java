package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IInstanceField;

public class HBClass implements IClass{

	private String name;
	
	public HBClass(String name){
		this.name = name;
	}
	
	@Override
	public boolean addPackage(String packageName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePackage() {
		// TODO Auto-generated method stub
		return false;
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

}
