package edu.iastate.cs362.hb.main;

import java.util.Scanner;

import edu.iastate.cs362.hb.commands.CommandParser;
import edu.iastate.cs362.hb.commands.ICommand;
import edu.iastate.cs362.hb.commands.ICommandParser;
import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.controller.ISystemController;
import edu.iastate.cs362.hb.controller.impl.SystemController;
import edu.iastate.cs362.hb.exceptions.HBDuplicateObjectFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
import edu.iastate.cs362.hb.model.impl.HBSystem;
/**
 * 
 * @author Alex, Brandon
 * 
 */
public class Cardinal {

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
				} else if (command.getName().equals(CmdConstants.CmdNames.EXIT)) {
					break;
				}
			} catch (MalformattedCommandException | HBDuplicateObjectFoundException | HBObjectNotFoundException me) {
				System.out.println(me.getMessage());
				break;
			} 
		}
		in.close();
	}

	// Calling of create methods
	private boolean doCreate(ICommand command) throws HBDuplicateObjectFoundException {
		if (command.getSubCommand().matches(CmdConstants.SubCmdNames.CLASS_REGEX)) {
			return isc.createClass(command.getFlagValue(CmdConstants.Flags.NAME));
		} else if (command.getSubCommand().matches(CmdConstants.SubCmdNames.INTERFACE_REGEX)) {
			return isc.createInterface(command.getFlagValue(CmdConstants.Flags.NAME));
		} else {
			return isc.createDesign(command.getFlagValue(CmdConstants.Flags.NAME));
		}
	}
	
	// Calling of add methods
	private void doAdd(ICommand command) throws HBObjectNotFoundException {
		if (command.getSubCommand().matches(CmdConstants.SubCmdNames.PACKAGE_REGEX)) {
			isc.addPackage(command.getFlagValue(CmdConstants.Flags.NAME), command.getFlagValue(CmdConstants.Flags.CONTAINER_NAME));
		}
	}
}