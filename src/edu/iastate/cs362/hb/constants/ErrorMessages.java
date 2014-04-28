package edu.iastate.cs362.hb.constants;

/**
 * Class holding onto the error messages found in the
 * Hierarchy Builder project
 * @author Brandon
 *
 */
public class ErrorMessages {

	public static final String OBJECT_NOT_FOUND = "Couldn't find the requested class: %s.";
	public static final String METHOD_NOT_FOUND = "Couldn't find the requested method %s.";
	
	public static final String DUPLICATE_OBJECT_FOUND = "The class %s in the package %s already exists.";
	public static final String DUPLICATE_METHOD = "The class %s already has a method that is the same as %s.";
	public static final String DUPLICATE_RELATION = "There is already a relationship from %s to %s.";
	
	public static final String MALFORMATTED_PARAM_LIST = "The given parameter list was formatted incorrectly: %s.";

	public static final String NULL_OBJ_NAME = "The given object name was null.";
	
	public static final String MULT_OBJECT_WITH_NAME = "Multiple objects exist with the name: %s.";
	public static final String MULT_OBJECT_WITH_ID = "Multiple objects exist with the id: %d.";
	
	public static final String RELATION_NOT_FOUND = "There was no relationship from %s to %s.";
}
