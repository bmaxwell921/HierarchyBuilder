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
}
