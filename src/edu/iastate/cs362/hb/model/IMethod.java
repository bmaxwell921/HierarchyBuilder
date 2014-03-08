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
	public List<IArgument> getArguments();
}
