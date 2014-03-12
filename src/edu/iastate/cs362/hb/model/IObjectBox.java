package edu.iastate.cs362.hb.model;

/**
 * Class used to box up some information about a class
 * @author Brandon
 *
 */
public interface IObjectBox {
	
	/**
	 * Gets the id held in this box
	 * @return
	 */
	public long getId();
	
	/**
	 * Returns the name held in this box
	 * @return
	 */
	public String getName();
	
	/**
	 * Returns the package held in this box
	 * @return
	 */
	public String getPkg();
	
	
}
