package edu.iastate.cs362.hb.main;

import java.util.List;
import java.util.Scanner;

import edu.iastate.cs362.hb.commands.CommandParser;
import edu.iastate.cs362.hb.commands.ICommand;
import edu.iastate.cs362.hb.commands.ICommandParser;
import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.controller.ISystemController;
import edu.iastate.cs362.hb.controller.impl.SystemController;
import edu.iastate.cs362.hb.exceptions.HBDuplicateMethodException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBDuplicateRelationshipException;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.HBRelationshipNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
import edu.iastate.cs362.hb.model.IObjectBox;
import edu.iastate.cs362.hb.model.impl.HBSystem;

/**
 * 
 * @author Alex, Brandon
 * 
 */
public class Cardinal {
	// TODO! check for id AND name instead of just taking a name

	// Object responsible for parsing commands
	private ICommandParser commander;

	// The system controller!
	private ISystemController isc;

	/*
	 * Constructor for the cardinal class. It's responsible for instantiating
	 * the parser and controller. Left private since no one else needs it
	 */
	private Cardinal() {
		this.commander = new CommandParser();
		this.isc = new SystemController(new HBSystem());
	}

	public static void main(String[] args) {
		new Cardinal().run();
		System.out.println("Thanks for using our software!");
	}

	/**
	 * This method takes no arguments. The method continuously runs and gets
	 * String commands.
	 */
	private void run() {
		Scanner in = new Scanner(System.in);
		String commandLine = null;
		while (true) {
			commandLine = in.nextLine();
			ICommand command = null;
			try {
				command = commander.parseCommand(commandLine);
				// First we check what the command name was
				if (command.getName().equals(CmdConstants.CmdNames.CREATE)) {
					this.doCreate(command);
				} else if (command.getName().equals(CmdConstants.CmdNames.ADD)) {
					doAdd(command);
				} else if (command.getName().equals(
						CmdConstants.CmdNames.REMOVE)) {
					doRemove(command);
				} else if (command.getName().equals(CmdConstants.CmdNames.EXIT)) {
					break;
				}
			} catch (MalformattedCommandException
					| HBDuplicateObjectFoundException
					| HBObjectNotFoundException | HBDuplicateMethodException
					| HBRelationshipNotFoundException
					| HBDuplicateRelationshipException me) {
				System.out.println(me.getMessage());
				break;
			} catch (HBMultipleObjectsFoundException e) {
				System.out.println(e.getMessage());
				printMatchingObjects(command
						.getFlagValue(CmdConstants.Flags.NAME));
			}
		}
		in.close();
	}

	// Method used to get all the objects that match the given name
	private void printMatchingObjects(String name) {
		List<IObjectBox> matches = isc.getMatchingObjects(name);
		for (IObjectBox box : matches) {
			System.out.println(box.toString());
		}

	}

	// Calling of create methods
	private boolean doCreate(ICommand command)
			throws HBDuplicateObjectFoundException {
		if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.CLASS_REGEX)) {
			return isc.createClass(command
					.getFlagValue(CmdConstants.Flags.NAME));
		} else if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.INTERFACE_REGEX)) {
			return isc.createInterface(command
					.getFlagValue(CmdConstants.Flags.NAME));
		} else {
			return isc.createDesign(command
					.getFlagValue(CmdConstants.Flags.NAME));
		}
	}

	// Calling of add methods
	private void doAdd(ICommand command) throws HBObjectNotFoundException,
			MalformattedCommandException, HBDuplicateMethodException,
			HBMultipleObjectsFoundException, HBDuplicateRelationshipException {
		if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.PACKAGE_REGEX)) {
			isc.addPackage(command.getFlagValue(CmdConstants.Flags.NAME),
					command.getFlagValue(CmdConstants.Flags.CONTAINER_NAME));
		} else if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.METHOD_REGEX)) {
			if (command.hasFlag(CmdConstants.Flags.INSTANCE)) {
				isc.addInstanceMethod(
						command.getFlagValue(CmdConstants.Flags.CONTAINER_NAME),
						command.getFlagValue(CmdConstants.Flags.NAME),
						command.getFlagValue(CmdConstants.Flags.PARAMETERS),
						CmdConstants.Flags.INSTANCE);
			} else {
				isc.addStaticMethod(
						command.getFlagValue(CmdConstants.Flags.CONTAINER_NAME),
						command.getFlagValue(CmdConstants.Flags.NAME),
						command.getFlagValue(CmdConstants.Flags.PARAMETERS),
						CmdConstants.Flags.STATIC);
			}
		} else if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.RELATIONSHIP_REGEX)) {
			isc.addRelationship(
					command.getFlagValue(CmdConstants.Flags.FROM_CLASS_NAME),
					command.getFlagValue(CmdConstants.Flags.TO_CLASS_NAME),
					command.getFlagValue(CmdConstants.Flags.TYPE));
		}
	}

	// Calling of Remove methods
	private boolean doRemove(ICommand command)
			throws HBObjectNotFoundException, HBMultipleObjectsFoundException,
			HBRelationshipNotFoundException {
		if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.CLASS_REGEX)
				|| command.getSubCommand().matches(
						CmdConstants.SubCmdNames.INTERFACE_REGEX)) {
			return isc.removeClass(command
					.getFlagValue(CmdConstants.Flags.NAME));
		} else if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.PACKAGE_REGEX)) {
			return isc.removePackage(command
					.getFlagValue(CmdConstants.Flags.CONTAINER_NAME));
		} else if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.METHOD_REGEX)) {
			return isc.removeMethod(
					command.getFlagValue(CmdConstants.Flags.CONTAINER_NAME),
					command.getFlagValue(CmdConstants.Flags.NAME));
		} else if (command.getSubCommand().matches(
				CmdConstants.SubCmdNames.RELATIONSHIP_REGEX)) {
			return isc.removeRelationship(
					command.getFlagValue(CmdConstants.Flags.FROM_CLASS_NAME),
					command.getFlagValue(CmdConstants.Flags.TO_CLASS_NAME));
		} else
			return false;
	}
}