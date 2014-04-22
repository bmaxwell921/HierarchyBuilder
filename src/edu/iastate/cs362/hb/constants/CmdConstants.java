package edu.iastate.cs362.hb.constants;


/**
 * Class holding all of the command names and flags to look for when parsing. I organized
 * this into inner classes. Now you use: CmdConstants.CmdNames.CREATE to get it
 * 
 * @author Brandon
 * 
 */
public class CmdConstants {

	// Regular expressions used to check if commands are valid
	public class Validation {
		public class Create {
			public static final String COMMON = "-(name|n)\\s+\\w+";
			public static final String DESIGN = CmdNames.CREATE + "\\s+design\\s+" + COMMON;
			public static final String CLASS = CmdNames.CREATE + "\\s+class\\s+" + COMMON;
			public static final String INTERFACE = CmdNames.CREATE + "\\s+interface\\s+" + COMMON;
		}
		
		public class Add {
			public static final String COMMON = "-(object|o)\\s+\\w+\\s+-(name|n)\\s+\\w+";
			public static final String FIELD_COMMON = "";
			public static final String FIELD = CmdNames.ADD + "\\s+field\\s+" + COMMON + "\\s+" + FIELD_COMMON;
		}
	}
	
	public class CmdNames {
		public static final String CREATE = "create";
		public static final String ADD = "add";
		public static final String REMOVE = "remove";
		public static final String EXIT = "exit";
		//Added for iteration 2
		public static final String EXPORT = "export";
		public static final String IMPORT = "import";
		public static final String CHANGE = "change";
		public static final String LIST = "list";
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
		
		public static final String RELATIONSHIP="relationship";
		private static final String RELATIONSHIP_SHORT = "r";
		public static final String RELATIONSHIP_REGEX = RELATIONSHIP + RegexOp.OR + RELATIONSHIP_SHORT;
		
		//EXPORT / IMPORT COMMANDS
		public static final String XML = "xml";
		private static final String XML_SHORT = "x";
		public static final String XML_REGEX = XML + RegexOp.OR + XML_SHORT;
		
		public static final String JSON = "json";
		private static final String JSON_SHORT = "j";
		public static final String JSON_REGEX = JSON + RegexOp.OR + JSON_SHORT;
		
		public static final String SOURCE = "source";
		private static final String SOURCE_SHORT = "s";
		public static final String SOURCE_REGEX = SOURCE + RegexOp.OR + SOURCE_SHORT;
		
	}
	
	public class Flags {
		// TODO we need to handle the short versions of the names as well
		public static final String NAME = "name";
		private static final String NAME_SHORT = "n";
		public static final String NAME_REGEX = NAME + RegexOp.OR + NAME_SHORT;
		
		public static final String ARGUMENTS = "arguments";
		private static final String ARGUMENTS_SHORT = "a";
		public static final String ARGUMENTS_REGEX = ARGUMENTS + RegexOp.OR + ARGUMENTS_SHORT;
		
		public static final String ID = "id";
		public static final String ID_REGEX = ID;
		
		public static final String TO_CLASS_NAME = "toClassName";
		private static final String TO_CLASS_NAME_SHORT = "tcn";
		public static final String TO_CLASS_NAME_REGEX = TO_CLASS_NAME + RegexOp.OR + TO_CLASS_NAME_SHORT;
		
		public static final String TO_CLASS_ID = "toClassId";
		private static final String TO_CLASS_ID_SHORT = "tcid";
		public static final String TO_CLASS_ID_REGEX = TO_CLASS_ID + RegexOp.OR + TO_CLASS_ID_SHORT;
		
		public static final String FROM_CLASS_NAME = "fromClassName";
		private static final String FROM_CLASS_NAME_SHORT = "fcn";
		public static final String FROM_CLASS_NAME_REGEX = FROM_CLASS_NAME + RegexOp.OR + FROM_CLASS_NAME_SHORT;
		
		public static final String FROM_CLASS_ID = "fromClassId";
		private static final String FROM_CLASS_ID_SHORT = "fcid";
		public static final String FROM_CLASS_ID_REGEX = FROM_CLASS_ID + RegexOp.OR + FROM_CLASS_ID_SHORT;
		
		public static final String TYPE = "type";
		public static final String TYPE_REGEX = TYPE;
		
		public static final String CONTAINER_NAME = "containername";
		private static final String CONTAINER_ABBREV = "cname"; //May not use
		private static final String CONTAINER_NAME_SHORT = "cn";
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
		
		//Path for importers/exporters
		public static final String PATH = "path";
		private static final String PATH_SHORT = "pA";
		public static final String PATH_REGEX = PATH + RegexOp.OR + PATH_SHORT; //Might have some confliction with p from Parameters
	}
	
	public class RegexOp {
		public static final String OR = "|";
	}
}
