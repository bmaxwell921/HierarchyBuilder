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
		public static final String EXIT = "exit";
	}
	
	public class SubCmdNames {
		private static final String DESIGN = "design";
		private static final String DESIGN_SHORT = "d";
		public static final String DESIGN_REGEX = DESIGN + RegexOp.OR + DESIGN_SHORT;
		
		private static final String CLASS = "class";
		private static final String CLASS_SHORT = "c";
		public static final String CLASS_REGEX = CLASS + RegexOp.OR + CLASS_SHORT;
		
		private static final String INTERFACE = "interface";
		private static final String INTERFACE_SHORT = "i";
		public static final String INTERFACE_REGEX = INTERFACE + RegexOp.OR + INTERFACE_SHORT;
		
		private static final String PACKAGE = "package";
		private static final String PACKAGE_SHORT = "p";
		public static final String PACKAGE_REGEX = PACKAGE + RegexOp.OR + PACKAGE_SHORT;
		
		private static final String METHOD = "method";
		private static final String METHOD_SHORT = "m";
		public static final String METHOD_REGEX = METHOD + RegexOp.OR + METHOD_SHORT;
	}
	
	public class Flags {
		// TODO we need to handle the short versions of the names as well
		public static final String NAME = "n";
		public static final String CONTAINER_NAME = "cN";
		
		public static final String STATIC = "s";
		public static final String INSTANCE = "i";
		public static final String HELP = "h";
		public static final String PARAMETERS = "p";
	}
	
	public class RegexOp {
		public static final String OR = "|";
	}
}
