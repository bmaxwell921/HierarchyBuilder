package edu.iastate.cs362.hb.commands;

import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;

/**
 * Class used to parse a given string into a command
 * 
 * @author Brandon
 * 
 */
public class CommandParser implements ICommandParser {

	/**
	 * Parses a string command into a command object
	 */
	@Override
	public ICommand parseCommand(String command)
			throws MalformattedCommandException {

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
		default:
			throw new MalformattedCommandException(
					"Malformatted command name!", commandList[0]);
		}

		// Subcommand expects a dash
		// Fixed exception happening when commands like exit are called
		if (commandList.length > 1) {
			addSubCommand(c, commandList[1].substring(1));
		}

		for (int i = 2; i < commandList.length; i++) {
			if (commandList[i].charAt(0) == '-') {
				// Flag
				boolean valueUsed = addFlag(c, commandList[i].substring(1),
					 	 (i+1) >= nonChangedList.length ? null : nonChangedList[i + 1]);
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
	public boolean addFlag(Command c, String input, String value)
			throws MalformattedCommandException {
		boolean valueUsed = false;
		if (input.matches(CmdConstants.Flags.CONTAINER_NAME)) {
			c.addFlagValue(CmdConstants.Flags.CONTAINER_NAME, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.HELP_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.HELP, null);
		} else if (input.matches(CmdConstants.Flags.INSTANCE_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.INSTANCE, null);
		} else if (input.matches(CmdConstants.Flags.NAME_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.NAME, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.PARAMETERS_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.PARAMETERS, value);
			valueUsed = true;
		} else if (input.matches(CmdConstants.Flags.STATIC_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.STATIC, null);
		} else if(input.matches(CmdConstants.Flags.PATH_REGEX)){
			c.addFlagValue(CmdConstants.Flags.PATH, value);
			valueUsed = true;
		}else if(input.matches(CmdConstants.Flags.CONTAINER_NAME_REGEX)){
			c.addFlagValue(CmdConstants.Flags.CONTAINER_NAME, value);
			valueUsed = true;
		} else if(input.matches(CmdConstants.Flags.FROM_CLASS_ID_REGEX)){
			c.addFlagValue(CmdConstants.Flags.FROM_CLASS_ID, value);
			valueUsed = true;
		} else if(input.matches(CmdConstants.Flags.FROM_CLASS_NAME_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.FROM_CLASS_NAME_REGEX, value);
			valueUsed = true;
		} else if(input.matches(CmdConstants.Flags.ID_REGEX)) {
			c.addFlagValue(CmdConstants.Flags.ID, value);
			valueUsed = true;
		} else if(input.matches(CmdConstants.Flags.INSTANCE_REGEX)){
			c.addFlagValue(CmdConstants.Flags.INSTANCE, null);
		} else if(input.matches(CmdConstants.Flags.TO_CLASS_ID_REGEX)){
			c.addFlagValue(CmdConstants.Flags.TO_CLASS_ID, value);
			valueUsed = true;
		} else if(input.matches(CmdConstants.Flags.TO_CLASS_NAME_REGEX)){
			c.addFlagValue(CmdConstants.Flags.TO_CLASS_NAME, value);
			valueUsed = true;
		} else if(input.matches(CmdConstants.Flags.TYPE_REGEX)){
			c.addFlagValue(CmdConstants.Flags.TYPE, value);
			valueUsed = true;
		} else {
			throw new MalformattedCommandException("Malformatted flag values!",
					input, value);
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
	public void addSubCommand(Command c, String input)
			throws MalformattedCommandException {
		if (input.matches(CmdConstants.SubCmdNames.CLASS_REGEX)) {
			c.setSubCommand(CmdConstants.SubCmdNames.CLASS);
		} else if (input.matches(CmdConstants.SubCmdNames.DESIGN_REGEX)) {
			c.setSubCommand(CmdConstants.SubCmdNames.DESIGN);
		} else if (input.matches(CmdConstants.SubCmdNames.INTERFACE_REGEX)) {
			c.setSubCommand(CmdConstants.SubCmdNames.INTERFACE);
		} else if (input.matches(CmdConstants.SubCmdNames.METHOD_REGEX)) {
			c.setSubCommand(CmdConstants.SubCmdNames.METHOD);
		} else if (input.matches(CmdConstants.SubCmdNames.PACKAGE_REGEX)) {
			c.setSubCommand(CmdConstants.SubCmdNames.PACKAGE);
		} else if (input.matches(CmdConstants.SubCmdNames.RELATIONSHIP_REGEX)) {
			c.setSubCommand(CmdConstants.SubCmdNames.RELATIONSHIP);
		} else if (input.matches(CmdConstants.SubCmdNames.XML_REGEX)){
			c.setSubCommand(CmdConstants.SubCmdNames.XML);
		} else if (input.matches(CmdConstants.SubCmdNames.JSON_REGEX)){
			c.setSubCommand(CmdConstants.SubCmdNames.JSON);
		} else if (input.matches(CmdConstants.SubCmdNames.SOURCE_REGEX)){
			c.setSubCommand(CmdConstants.SubCmdNames.SOURCE);
		} else {
			throw new MalformattedCommandException(
					"Malformatted sub-command name!", input);
		}

	}
}
