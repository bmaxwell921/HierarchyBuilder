package edu.iastate.cs362.hb.commands;

import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;

/**
 * Part of the UI, will not show up in the design
 * @author Rob
 *
 */
public interface ICommandParser {

	/**
	 * 
	 * @param command the String version of the command read in from console
	 * @return ICommand object used by Cardinal to determine methods to call
	 * @throws MalformattedCommandException 
	 */
	public ICommand parseCommand(String command) throws MalformattedCommandException;
}
