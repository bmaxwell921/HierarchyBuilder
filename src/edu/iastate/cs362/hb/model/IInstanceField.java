package edu.iastate.cs362.hb.model;


/**
 * Interface describing behavior for InstanceField objects
 * @author Brandon
 *
 */
public interface IInstanceField {

	public void addModifiers(String... modifier);
	
	public String getName();
}
