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
		public static final String HELP = "help";
	}
	
	public class SubCmdNames {
		// Create subcommands
		public static final String DESIGN = "design";
		public static final String CLASS = "class";
		public static final String INTERFACE = "interface";
		
		// Add subcommands
		public static final String PACKAGE = "pkg";
		public static final String FIELD = "field";
		public static final String METHOD = "method";
		public static final String RELATIONSHIP="relation";
		public static final String CACHE = "cache";
		
		// Remove subcommands
		public static final String OBJECT = "object";
		// Also has package, method, and relation
		
		//Change subcommands need this
		public static final String MODIFIER = "modifier";
		
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
		
		public static final String ANY = DESIGN + RegexOp.OR + CLASS + RegexOp.OR + INTERFACE + RegexOp.OR + PACKAGE + RegexOp.OR + 
				FIELD + RegexOp.OR + METHOD  + RegexOp.OR + RELATIONSHIP + RegexOp.OR + CACHE + RegexOp.OR + OBJECT + RegexOp.OR + 
				XML_REGEX + RegexOp.OR + JSON_REGEX + RegexOp.OR + SOURCE_REGEX;
	}
	
	public class Flags {
		
		//Create flags
		public static final String NAME = "name";
		private static final String NAME_SHORT = "n";
		public static final String NAME_REGEX = NAME + RegexOp.OR + NAME_SHORT;
		
		public static final String OLDNAME = "oldName";
		private static final String OLDNAME_SHORT = "oldN";
		public static final String OLDNAME_REGEX = OLDNAME + RegexOp.OR + OLDNAME_SHORT;
		
		public static final String ARGUMENTS = "args";
		private static final String ARGUMENTS_SHORT = "a";
		public static final String ARGUMENTS_REGEX = ARGUMENTS + RegexOp.OR + ARGUMENTS_SHORT;
		
		public static final String ID = "id";
		public static final String ID_REGEX = ID;
		
		public static final String TYPE = "type";
		private static final String TYPE_SHORT = "t";
		public static final String TYPE_REGEX = TYPE + RegexOp.OR + TYPE_SHORT;
		
		public static final String HELP = "help";
		private static final String HELP_SHORT = "h";
		public static final String HELP_REGEX = HELP + RegexOp.OR + HELP_SHORT;
		
		public static final String PARAMETERS = "parameters";
		private static final String PARAMETERS_SHORT = "p";
		public static final String PARAMETERS_REGEX = PARAMETERS + RegexOp.OR + PARAMETERS_SHORT;
		
		public static final String SUPERTYPE = "superType";
		private static final String SUPERTYPE_SHORT = "sup";
		public static final String SUPERTYPE_REGEX = SUPERTYPE + RegexOp.OR + SUPERTYPE_SHORT;
		
		public static final String SUBTYPE = "subtype";
		private static final String SUBTYPE_SHORT = "sub";
		public static final String SUBTYPE_REGEX = SUBTYPE + RegexOp.OR + SUBTYPE_SHORT;
		
		public static final String OBJECT = "object";
		private static final String OBJECT_SHORT = "obj";
		public static final String OBJECT_REGEX = OBJECT + RegexOp.OR + OBJECT_SHORT;
		//Path for importers/exporters
		public static final String PATH = "path";
		private static final String PATH_SHORT = "p";
		public static final String PATH_REGEX = PATH + RegexOp.OR + PATH_SHORT;
		
		//Iteration 3
		public static final String METHOD = "method";
		private static final String METHOD_SHORT = "m";
		public static final String METHOD_REGEX = METHOD + RegexOp.OR + METHOD_SHORT;
		
		public static final String MODIFIER = "modifiers";
		private static final String MODIFIER_SHORT = "mod";
		public static final String MODIFIER_REGEX = MODIFIER + RegexOp.OR + MODIFIER_SHORT;
		
		public static final String RETURN = "return";
		private static final String RETURN_SHORT = "r";
		public static final String RETURN_REGEX = RETURN + RegexOp.OR + RETURN_SHORT;
		
		public static final String INSTANCE = "instance";
		private static final String INSTANCE_SHORT = "i";
		public static final String INSTANCE_REGEX = INSTANCE + RegexOp.OR + INSTANCE_SHORT;
		
		public static final String STATIC = "static";		
	}
	
	public class RegexOp {
		public static final String OR = "|";
	}
}
