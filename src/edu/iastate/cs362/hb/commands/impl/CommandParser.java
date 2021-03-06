package edu.iastate.cs362.hb.commands.impl;

import edu.iastate.cs362.hb.commands.ICommand;
import edu.iastate.cs362.hb.commands.ICommandParser;
import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;

/**
 * Class used to parse a given string into a command
 * Part of the UI, will not show up in the design
 * 
 * @author Brandon, Rob
 * 
 */
public class CommandParser implements ICommandParser {

	/**
	 * Parses a string command into a command object
	 */
	@Override
	public ICommand parseCommand(String command) throws MalformattedCommandException {

		String[] commandList = command.split(" ");
		String[] nonChangedList = new String[commandList.length];
		for (int i = 0; i < commandList.length; i++) {

			// Force all to lower.
			nonChangedList[i] = commandList[i]; // Only needs to be used for
												// flag values
			commandList[i] = commandList[i].toLowerCase();

		}

		Command c;
		switch (commandList[0]) {
		case CmdConstants.CmdNames.ADD:
			c = new Command(CmdConstants.CmdNames.ADD);
			break;
		case CmdConstants.CmdNames.CREATE:
			c = new Command(CmdConstants.CmdNames.CREATE);
			break;
		case CmdConstants.CmdNames.EXIT:
			c = new Command(CmdConstants.CmdNames.EXIT);
			break;
		case CmdConstants.CmdNames.REMOVE:
			c = new Command(CmdConstants.CmdNames.REMOVE);
			break;
		case CmdConstants.CmdNames.IMPORT:
			c = new Command(CmdConstants.CmdNames.IMPORT);
			break;
		case CmdConstants.CmdNames.EXPORT:
			c = new Command(CmdConstants.CmdNames.EXPORT);
			break;
		case CmdConstants.CmdNames.CHANGE:
			c = new Command(CmdConstants.CmdNames.CHANGE);
			break;
		case CmdConstants.CmdNames.LIST:
			c = new Command(CmdConstants.CmdNames.LIST);
			break;
		case CmdConstants.CmdNames.HELP:
			// Quit fast here
			c = new Command(CmdConstants.CmdNames.HELP);
			c.setSubCommand("");
			return c;
		default:
			throw new MalformattedCommandException("Malformatted command name!", commandList[0]);
		}

		// Subcommand expects a dash
		// Fixed exception happening when commands like exit are called
		if (commandList.length > 1) {
			addSubCommand(c, commandList[1]);
		}

		for (int i = 2; i < commandList.length; i++) {
			if (commandList[i].charAt(0) == '-') {
				// Flag
				boolean valueUsed = addFlag(c, commandList[i].substring(1), (i + 1) >= nonChangedList.length ? null : nonChangedList[i + 1]);
				if (valueUsed) {
					i++;
				}
			}

		}

		return c;
	}

	/**
	 * Adds a flag to the existing command
	 * 
	 * @param c
	 *            The Command to add the flag to
	 * @param input
	 *            The input, e.g. name
	 * @param value
	 *            The value for the input (where applicable), like the name of a
	 *            class
	 * @return True if we needed the value, false if not
	 * @throws MalformattedCommandException
	 *             If the input is not a known flag, this exception is thrown
	 */
	public boolean addFlag(Command c, String input, String value) throws MalformattedCommandException {
		boolean valueUsed = false;
		if (input.matches(CmdConstants.Flags.HELP_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.HELP, null);
		} else if (input.matches(CmdConstants.Flags.NAME_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.NAME, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.OLDNAME_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.OLDNAME, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.PARAMETERS_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.PARAMETERS, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.PATH_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.PATH, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.ID_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.ID, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.TYPE_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.TYPE, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.ARGUMENTS_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.ARGUMENTS, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.RETURN_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.RETURN, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.SUPERTYPE_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.SUPERTYPE, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.SUBTYPE_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.SUBTYPE, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.OBJECT_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.OBJECT, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.METHOD_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.METHOD, null);
		} else if (input.matches(CmdConstants.Flags.MODIFIER_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.MODIFIER, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.INSTANCE_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.INSTANCE, null);
		} else {
			throw new MalformattedCommandException("Malformatted flag values!", input, value);
		}

		return valueUsed;
	}

	/**
	 * Adds the sub command from the input into c
	 * 
	 * @param c
	 *            The command to modify
	 * @param input
	 *            The sub command, e.g. design
	 * @throws MalformattedCommandException
	 *             If sub-command is not known, this is thrown
	 */
	public void addSubCommand(Command c, String input) throws MalformattedCommandException {
		if (!input.matches(CmdConstants.SubCmdNames.ANY)) {
			throw new MalformattedCommandException("Unknown Subcommand name: %s", input);
		}
		c.setSubCommand(input);
	}
}
