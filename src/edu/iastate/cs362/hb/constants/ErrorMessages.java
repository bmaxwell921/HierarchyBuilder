package edu.iastate.cs362.hb.constants;

/**
 * Class holding onto the error messages found in the
 * Hierarchy Builder project
 * @author Brandon
 *
 */
public class ErrorMessages {

	public static final String OBJECT_NOT_FOUND = "Couldn't find the requested class: %s.";
	public static final String DUPLICATE_OBJECT_FOUND = "The class %s in the package %s already exists.";
	
	public static final String DUPLICATE_METHOD = "The class %s already has a method that is the same as %s";
	
	public static final String MALFORMATTED_PARAM_LIST = "The given parameter list was formatted incorrectly: %s";

	public static final String NULL_OBJ_NAME = "The given object name was null";
	
	public static final String MULT_OBJECT_WITH_NAME = "Multiple objects exist with the name: %s";
}
