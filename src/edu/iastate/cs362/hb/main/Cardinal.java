package edu.iastate.cs362.hb.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import edu.iastate.cs362.hb.commands.ICommand;
import edu.iastate.cs362.hb.commands.ICommandParser;
import edu.iastate.cs362.hb.commands.impl.CommandParser;
import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.controller.ISystemController;
import edu.iastate.cs362.hb.controller.impl.SystemController;
import edu.iastate.cs362.hb.exceptions.HBMultipleObjectsFoundException;
import edu.iastate.cs362.hb.exceptions.HBObjectNotFoundException;
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
	// TEEHEE Broke brandon's streak.
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
		doSystemStart();
		new Cardinal().run();
		System.out.println("Thanks for using our software!");
	}

	/**
	 * This method takes no arguments. The method continuously runs and gets
	 * String commands.
	 */
	private void run() {
		Scanner in = new Scanner(System.in);
		String cmdStr = null;
		boolean exit = false;
		while (!exit) {
			cmdStr = in.nextLine();
			ICommand cmd = null;
			try {
				cmd = commander.parseCommand(cmdStr);
				// First we check what the command name was
				if (cmd.getName().equals(CmdConstants.CmdNames.CREATE)) {
					this.doCreate(cmd);
				} else if (cmd.getName().equals(CmdConstants.CmdNames.ADD)) {
					doAdd(cmd);
				} else if (cmd.getName().equals(CmdConstants.CmdNames.REMOVE)) {
					doRemove(cmd);
				} else if (cmd.getName().equals(CmdConstants.CmdNames.CHANGE)) {
					doChange(cmd);
				} else if (cmd.getName().equals(CmdConstants.CmdNames.LIST)) {
					doList(cmd);
				} else if (cmd.getName().equals(CmdConstants.CmdNames.EXPORT)) {
					doExport(cmd);
				} else if (cmd.getName().equals(CmdConstants.CmdNames.IMPORT)) {
					doImport(cmd);
				} else if (cmd.getName().equals(CmdConstants.CmdNames.EXIT)) {
					exit = true;
					break;
				}
				System.out.println(String.format(
						"System - Completed command \"%s\"", cmd.toString()));
			} catch (HBMultipleObjectsFoundException e) {
				System.out.println(e.getMessage());
				printMatchingObjects(cmd.getFlagValue(CmdConstants.Flags.NAME));
			} catch (Exception e) {
				System.out.println(e.getClass() + "\t" + e.getMessage());
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
	private boolean doCreate(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.CLASS_REGEX)) {
			return isc.createClass(cmd.getFlagValue(CmdConstants.Flags.NAME));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.INTERFACE_REGEX)) {
			return isc.createInterface(cmd
					.getFlagValue(CmdConstants.Flags.NAME));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.CACHE_REGEX)) {
			return doCache(cmd);
		} else {
			return isc.createDesign(cmd.getFlagValue(CmdConstants.Flags.NAME));
		}
	}

	// Calling of add methods
	private void doAdd(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.PACKAGE_REGEX)) {
			isc.addPackage(cmd.getFlagValue(CmdConstants.Flags.NAME),
					cmd.getFlagValue(CmdConstants.Flags.CONTAINER_NAME));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.METHOD_REGEX)) {
			if (cmd.hasFlag(CmdConstants.Flags.INSTANCE)) {
				isc.addInstanceMethod(
						cmd.getFlagValue(CmdConstants.Flags.CONTAINER_NAME),
						cmd.getFlagValue(CmdConstants.Flags.NAME),
						cmd.getFlagValue(CmdConstants.Flags.PARAMETERS),
						CmdConstants.Flags.INSTANCE);
			} else {
				isc.addStaticMethod(
						cmd.getFlagValue(CmdConstants.Flags.CONTAINER_NAME),
						cmd.getFlagValue(CmdConstants.Flags.NAME),
						cmd.getFlagValue(CmdConstants.Flags.PARAMETERS),
						CmdConstants.Flags.STATIC);
			}
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.RELATIONSHIP_REGEX)) {
			isc.addRelationship(
					cmd.getFlagValue(CmdConstants.Flags.FROM_CLASS_NAME),
					cmd.getFlagValue(CmdConstants.Flags.TO_CLASS_NAME),
					cmd.getFlagValue(CmdConstants.Flags.TYPE));
		} else if(cmd.getSubCommand().matches(CmdConstants.SubCmdNames.CACHE_REGEX)){
			//Get type
			String type = cmd.getFlagValue(CmdConstants.Flags.TYPE).toUpperCase();
			long id = Long.parseLong(CmdConstants.Flags.ID);
			isc.addObject(type, CacheManager.getInstance().getObject(id));
		}
	}

	// Calling of Remove methods
	private boolean doRemove(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.CLASS_REGEX)
				|| cmd.getSubCommand().matches(
						CmdConstants.SubCmdNames.INTERFACE_REGEX)) {
			return isc.removeClass(cmd.getFlagValue(CmdConstants.Flags.NAME));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.PACKAGE_REGEX)) {
			return isc.removePackage(cmd
					.getFlagValue(CmdConstants.Flags.CONTAINER_NAME));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.METHOD_REGEX)) {
			return isc.removeMethod(
					cmd.getFlagValue(CmdConstants.Flags.CONTAINER_NAME),
					cmd.getFlagValue(CmdConstants.Flags.NAME));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.RELATIONSHIP_REGEX)) {
			return isc.removeRelationship(
					cmd.getFlagValue(CmdConstants.Flags.FROM_CLASS_NAME),
					cmd.getFlagValue(CmdConstants.Flags.TO_CLASS_NAME));
		} else
			return false;
	}

	/**
	 * Calling of Export methods
	 * 
	 * @param cmd
	 * @return
	 */
	private boolean doExport(ICommand cmd) {
		if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.XML_REGEX)) {
			return isc.exportDesignXML(cmd
					.getFlagValue(CmdConstants.Flags.PATH));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.JSON_REGEX)) {
			return isc.exportDesignJSON(cmd
					.getFlagValue(CmdConstants.Flags.PATH));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.SOURCE_REGEX)) {
			return isc.exportDesignSource(cmd
					.getFlagValue(CmdConstants.Flags.PATH));
		} else
			return false;
	}

	private boolean doImport(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.XML_REGEX)) {
			return isc.importDesignXML(cmd
					.getFlagValue(CmdConstants.Flags.PATH));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.JSON_REGEX)) {
			return isc.importDesignJSON(cmd
					.getFlagValue(CmdConstants.Flags.PATH));
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.SOURCE_REGEX)) {
			return isc.importDesignSource(cmd
					.getFlagValue(CmdConstants.Flags.PATH));
		} else
			return false;
	}

	/**
	 * Calling of change methods.
	 * 
	 * @param cmd
	 *            the command holding the requisite information
	 * @return boolean indicating success/failure
	 * @throws HBMultipleObjectsFoundException
	 * @throws HBObjectNotFoundException
	 */
	private boolean doChange(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.CLASS_REGEX)
				|| cmd.getSubCommand().matches(
						CmdConstants.SubCmdNames.INTERFACE_REGEX))
			return isc.changeName(
					cmd.getFlagValue(CmdConstants.Flags.CONTAINER_NAME),
					cmd.getFlagValue(CmdConstants.Flags.NAME));
		else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.PACKAGE_REGEX))
			return isc.changePackage(
					cmd.getFlagValue(CmdConstants.Flags.CONTAINER_NAME),
					cmd.getFlagValue(CmdConstants.Flags.NAME));
		else
			return false;
	}

	/**
	 * Calling of change methods.
	 * 
	 * @param cmd
	 *            the command holding the requisite information
	 * @return boolean indicating success/failure
	 * @throws HBMultipleObjectsFoundException
	 * @throws HBObjectNotFoundException
	 */
	private boolean doList(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.CLASS_REGEX)
				|| cmd.getSubCommand().matches(
						CmdConstants.SubCmdNames.INTERFACE_REGEX)) {
			System.out.println(isc.listObject(cmd
					.getFlagValue(CmdConstants.Flags.NAME)));
			return true;
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.DESIGN_REGEX)) {
			System.out.println(isc.listDesign());
			return true;
		} else if (cmd.getSubCommand().matches(
				CmdConstants.SubCmdNames.CACHE_REGEX)) {
			// get ID
			long id = Long.parseLong(cmd.getFlagValue(CmdConstants.Flags.ID));
			String type = cmd.getFlagValue(CmdConstants.Flags.TYPE);
			if (type == null) {
				return false;
			}
			String listable = "";
			switch (type.toUpperCase()) {
			case "METHOD":
				listable = isc.listCachedMethod(id);
				break;
			case "VARIABLE":
			case "INSTANCE":
				listable = isc.listCachedVariable(id);
				break;
			case "MODIFIER":
				listable = isc.listCachedModifierSet(id);
				break;
			default:
				return false;
			}
			System.out.println(listable);
			return true;
		} else
			return false;
	}

	private static void doSystemStart() {
		System.out.println("Starting system...");
		Random gen = new Random();
		int choice = randRange(gen, 0, 25);
		while (choice < 100) {
			System.out.println(String.format("%d%%...", choice));
			choice = randRange(gen, choice + 1, choice + 25);
			try {
				Thread.sleep(gen.nextInt(1000));
			} catch (InterruptedException e) {

			}
		}
		System.out.println("100%");
		System.out.println("System ready, enter a command.");
	}

	private boolean doCache(ICommand cmd) throws MalformattedCommandException {
		long id = 0;
		// Initialized to null
		Set<String> modifiers = null;
		List<String> arguments = null;
		if (cmd.getFlagValue(CmdConstants.Flags.TYPE).equalsIgnoreCase(
				CmdConstants.SubCmdNames.METHOD)) {
			String methodName = cmd.getFlagValue(CmdConstants.Flags.NAME);
			String returnType = cmd.getFlagValue(CmdConstants.Flags.RETURN);
			String modifiersStr = cmd.getFlagValue(CmdConstants.Flags.MODIFIER);
			// null check
			if (modifiersStr != null) {
				modifiers = new HashSet<String>(Arrays.asList(modifiersStr
						.split(",")));
			}
			String argumentsStr = cmd
					.getFlagValue(CmdConstants.Flags.ARGUMENTS);
			if (argumentsStr != null) {
				arguments = new ArrayList<String>(Arrays.asList(argumentsStr
						.split(",")));
			}
			id = isc.cacheMethod(methodName, returnType, modifiers, arguments);
			if (id == -1) {
				throw new MalformattedCommandException("ID unable to set");
			}
		} else if (cmd.getFlagValue(CmdConstants.Flags.TYPE).equalsIgnoreCase(
				CmdConstants.Flags.INSTANCE)) {
			String methodName = cmd.getFlagValue(CmdConstants.Flags.NAME);
			String returnType = cmd.getFlagValue(CmdConstants.Flags.RETURN);
			String modifiersStr = cmd.getFlagValue(CmdConstants.Flags.MODIFIER);
			if (modifiersStr != null) {
				modifiers = new HashSet<String>(Arrays.asList(modifiersStr
						.split(",")));
			}
			id = isc.cacheVariable(methodName, returnType, modifiers);
			if (id == -1) {
				throw new MalformattedCommandException("ID unable to set");
			}
		} else if (cmd.getFlagValue(CmdConstants.Flags.TYPE).equalsIgnoreCase(
				CmdConstants.Flags.MODIFIER)) {
			String modifiersStr = cmd.getFlagValue(CmdConstants.Flags.MODIFIER);
			if (modifiersStr != null) {
				modifiers = new HashSet<String>(Arrays.asList(modifiersStr
						.split(",")));
			}
			id = isc.cacheModifierSet(modifiers);
			if (id == -1) {
				throw new MalformattedCommandException("ID unable to set");
			}
		} else {
			return false;
		}
		System.out.println("New cache item created with id: " + id);
		System.out.println(CacheManager.getMappings(id));
		return true;
	}

	private static int randRange(Random gen, int low, int high) {
		return gen.nextInt(high - low) + low;
	}
}