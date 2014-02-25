package edu.iastate.cs362.hb.model;

/**
 * Interface used to describe behavior for the graph that represents the
 * design document classes/interfaces/relationships
 * @author Brandon
 *
 */
public interface IGraph {
	
	public IClass getClass(String className);
	
	public boolean addClass(IClass newClass);
	
	public boolean addRelationship(IClass fromClass, IClass toClass, IRelationship relationship);
	
	public boolean removeRelationship(IClass fromClass, IClass toClass);
	
	public boolean removeClass(IClass toRemove);
}
