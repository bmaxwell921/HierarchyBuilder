package edu.iastate.cs362.hb.model;

import java.util.List;

/**
 * Interface representing a Class in a Class Hierarchy
 * @author Brandon
 *
 */
public interface IClass extends IObject {
	
	public boolean addInstanceField(IVariable i);
	
	public List<IMethod> getMethods();
}
