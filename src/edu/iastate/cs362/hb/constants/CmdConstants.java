package edu.iastate.cs362.hb.constants;


/**
 * Class holding all of the command names and flags to look for when parsing. I organized
 * this into inner classes. Now you use: CmdConstants.CmdNames.CREATE to get it
 * 
 * @author Brandon
 * 
 */
public class CmdConstants {


	public class CmdNames {
		public static final String CREATE = "create";
		public static final String ADD = "add";
		public static final String REMOVE = "remove";
		public static final String EXIT = "exit";
	}
	
	public class SubCmdNames {
		public static final String DESIGN = "design";
		private static final String DESIGN_SHORT = "d";
		public static final String DESIGN_REGEX = DESIGN + RegexOp.OR + DESIGN_SHORT;
		
		public static final String CLASS = "class";
		private static final String CLASS_SHORT = "c";
		public static final String CLASS_REGEX = CLASS + RegexOp.OR + CLASS_SHORT;
		
		public static final String INTERFACE = "interface";
		private static final String INTERFACE_SHORT = "i";
		public static final String INTERFACE_REGEX = INTERFACE + RegexOp.OR + INTERFACE_SHORT;
		
		public static final String PACKAGE = "package";
		private static final String PACKAGE_SHORT = "p";
		public static final String PACKAGE_REGEX = PACKAGE + RegexOp.OR + PACKAGE_SHORT;
		
		public static final String METHOD = "method";
		private static final String METHOD_SHORT = "m";
		public static final String METHOD_REGEX = METHOD + RegexOp.OR + METHOD_SHORT;
		
	}
	
	public class Flags {
		// TODO we need to handle the short versions of the names as well
		public static final String NAME = "name";
		private static final String NAME_SHORT = "n";
		public static final String NAME_REGEX = NAME + RegexOp.OR + NAME_SHORT;
		
		public static final String CONTAINER_NAME = "containername";
		private static final String CONTAINER_ABBREV = "cname"; //May not use
		private static final String CONTAINER_NAME_SHORT = "cN";
		public static final String CONTAINER_NAME_REGEX = CONTAINER_NAME + RegexOp.OR + CONTAINER_ABBREV + RegexOp.OR + CONTAINER_NAME_SHORT;
		
		public static final String STATIC = "static";
		private static final String STATIC_SHORT = "s";
		public static final String STATIC_REGEX = STATIC + RegexOp.OR + STATIC_SHORT;
		
		public static final String INSTANCE = "instance";
		private static final String INSTANCE_SHORT = "i";
		public static final String INSTANCE_REGEX = INSTANCE + RegexOp.OR + INSTANCE_SHORT;
		
		public static final String HELP = "help";
		private static final String HELP_SHORT = "h";
		public static final String HELP_REGEX = HELP + RegexOp.OR + HELP_SHORT;
		
		public static final String PARAMETERS = "parameters";
		private static final String PARAMETERS_SHORT = "p";
		public static final String PARAMETERS_REGEX = PARAMETERS + RegexOp.OR + PARAMETERS_SHORT;
	}
	
	public class RegexOp {
		public static final String OR = "|";
	}
}
