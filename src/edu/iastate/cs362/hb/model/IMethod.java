package edu.iastate.cs362.hb.model;

import java.util.List;

import edu.iastate.cs362.hb.model.attributes.Identifiable;
import edu.iastate.cs362.hb.model.attributes.Modifiable;
import edu.iastate.cs362.hb.model.attributes.Nameable;

public interface IMethod extends Nameable, Identifiable, Modifiable {

	/**
	 * Convenience method used to check if this method is static
	 * @return
	 */
	public boolean isStatic();
	
	/**
	 * Returns a list of all the arguments in this method
	 * @return
	 */
	public List<IVariable> getArguments();
	
	/**
	 * Converts the given string to HBArgument objects, then 
	 * adds them to this method. 
	 * 
	 * Args should be formatted as such:
	 * 	type1:name1,type2:name2,etc
	 * @param args 
	 */
	public void addArguments(String args);

	public void addArguments(List<String> methodArgs);

	public void addReturnType(String returnType);
	
	
	public String list();

	public boolean update(IMethod m, List<String> args);

	String getReturnType();
}
