package edu.iastate.cs362.hb.model;

/**
 * Interface used to describe objects that are creatable.
 * @author Brandon
 *
 * @param <T>
 * 			The type to create
 */
public interface Creatable<T> {

	public T create();
}
