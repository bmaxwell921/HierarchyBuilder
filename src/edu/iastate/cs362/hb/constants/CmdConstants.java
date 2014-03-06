package edu.iastate.cs362.hb.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Class holding all of the command names and flags to look for when parsing
 * 
 * @author Brandon
 * 
 */
public class CmdConstants {

	public static final String EXIT_NAME = "exit";

	public CmdConstants() {
		initializeArguments();
	}

	private List<Argument> argList = new ArrayList<Argument>();

	public List<Argument> getArgs() {
		return argList;
	}

	public void initializeArguments() {
		// Exit Argument
		argList.add(new Argument("EXIT", false, null, false));

		// Create Argument
		Argument create = new Argument("CREATE", false, null, false);
		// sub arguments
		ArrayList<Argument> createSubArg = new ArrayList<Argument>();
		createSubArg.add(new Argument("DESIGN", false, null, false));
		createSubArg.add(new Argument("CLASS", false, null, false));
		createSubArg.add(new Argument("INTERFACE", false, null, false));
		createSubArg.add(new Argument("NAME", true, null, false));
		createSubArg.add(new Argument("HELP", false, null, false));
		create.subArguments = createSubArg;
		argList.add(create);

	}

}
