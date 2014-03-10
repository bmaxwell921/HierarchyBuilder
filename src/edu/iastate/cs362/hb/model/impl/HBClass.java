package edu.iastate.cs362.hb.model.impl;

import java.util.ArrayList;
import java.util.List;

import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IInstanceField;

/**
 * Class representing a Class in the Hierarchy
 * @author Brandon
 *
 */
public class HBClass extends AHBObject implements IClass{
	
	private List<IInstanceField> fields;
	public HBClass(String name){
		super(name);
		fields = new ArrayList<IInstanceField>();
	}

	@Override
	public boolean addInstanceField(IInstanceField i) {
		return fields.add(i);
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
}
