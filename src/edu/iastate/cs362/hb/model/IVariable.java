package edu.iastate.cs362.hb.model;

import edu.iastate.cs362.hb.model.attributes.Identifiable;
import edu.iastate.cs362.hb.model.attributes.Modifiable;
import edu.iastate.cs362.hb.model.attributes.Nameable;

public interface IVariable extends Nameable, Modifiable, Identifiable {
	
	/**
	 * Returns the type of the variable
	 * @return
	 */
	public String getType();

	public String list();

}
