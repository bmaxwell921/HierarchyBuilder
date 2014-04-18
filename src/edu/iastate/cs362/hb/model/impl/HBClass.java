package edu.iastate.cs362.hb.model.impl;

import java.util.ArrayList;
import java.util.List;

import edu.iastate.cs362.hb.model.IClass;
import edu.iastate.cs362.hb.model.IInstanceField;
import edu.iastate.cs362.hb.model.IMethod;

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
	public String list(){
		String toRet = "";
		toRet += this.getName() + "\n" + "Fields: \n";
		for(int i = 0; i < fields.size(); i++)
		{
			toRet += " " + fields.get(i).getName();
			toRet += "\n";
		}
		toRet += "Methods: \n";
		for(int i = 0; i < this.getNumMethods(); i++)
		{
			toRet += " " + methods.get(i).getName();
			toRet += "\n";
		}
		return toRet;
	}

	@Override
	public List<IMethod> getMethods() {
		return methods;
	}
}
