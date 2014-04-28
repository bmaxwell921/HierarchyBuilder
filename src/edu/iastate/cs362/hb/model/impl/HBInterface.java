package edu.iastate.cs362.hb.model.impl;

import edu.iastate.cs362.hb.model.IVariable;


/**
 * Class representing an interface. Do we even need it if it's empty?? I guess it's nice to have
 * @author Brandon
 *
 */
public class HBInterface extends AHBObject{
	
	public HBInterface(String name) {
		super(name);
	}
	
	// TODO add method should check that it's not an instance method
	
	@Override
	public String list(){
		String toRet = "";
		toRet += this.getName() + "\n" + "Methods: \n";
		for(int i = 0; i < this.getNumMethods(); i++)
		{
			toRet += " " + methods.get(i).list();
			toRet += "\n";
		}
		return toRet;
	}

	@Override
	public boolean changeClassField(long fieldId, IVariable i) {
		return false;
	}
}
