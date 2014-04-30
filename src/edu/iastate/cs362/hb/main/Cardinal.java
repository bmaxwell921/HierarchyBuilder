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
import edu.iastate.cs362.hb.model.impl.HBSystem;

//import java.util.Random;

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
//		doSystemStart();
		System.out.println("System ready.");
		new Cardinal().run();
		System.out.println("System Exit.");
	}

	/**
	 * This method takes no arguments. The method continuously runs and gets
	 * String commands.
	 */
	private void run() {
		
		Scanner in = new Scanner(System.in);
		String cmdStr = null;
		while (true) {
			cmdStr = in.nextLine();
			ICommand cmd = null;
			try {
				cmd = commander.parseCommand(cmdStr);
				// First we check what the command name was
				if (cmd.getName().equals(CmdConstants.CmdNames.CREATE)) {
					this.doCreate(cmd);
				}
				if (cmd.getName().equals(CmdConstants.CmdNames.ADD)) {
					doAdd(cmd);
				}
				if (cmd.getName().equals(CmdConstants.CmdNames.REMOVE)) {
					doRemove(cmd);
				}
				if (cmd.getName().equals(CmdConstants.CmdNames.CHANGE)) {
					doChange(cmd);
				}
				if (cmd.getName().equals(CmdConstants.CmdNames.LIST)) {
					doList(cmd);
				}
				if (cmd.getName().equals(CmdConstants.CmdNames.EXPORT)) {
					doExport(cmd);
				}
				if (cmd.getName().equals(CmdConstants.CmdNames.IMPORT)) {
					doImport(cmd);
				}
				if (cmd.getName().equals(CmdConstants.CmdNames.EXIT)) {
					break;
				}
				System.out.println(String.format("System - Completed command \"%s\"", cmd.toString()));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		in.close();
	}

	// Calling of create methods
	private boolean doCreate(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.CLASS)) {
			return isc.createClass(cmd.getFlagValue(CmdConstants.Flags.NAME));
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.INTERFACE)) {
			return isc.createInterface(cmd.getFlagValue(CmdConstants.Flags.NAME));
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.CACHE)) {
			return doCache(cmd);
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.DESIGN)) {
			return isc.createDesign(cmd.getFlagValue(CmdConstants.Flags.NAME));
		}
		return false;
	}

	// Calling of add methods
	private void doAdd(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.PACKAGE)) {
			// First get the id of the object to modify, then modify it
			long id = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OBJECT));
			isc.addPackage(id, cmd.getFlagValue(CmdConstants.Flags.NAME));
			return;
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.FIELD)) {
			long id = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OBJECT));
			isc.addInstanceField(id, cmd.getFlagValue(CmdConstants.Flags.NAME), cmd.getFlagValue(CmdConstants.Flags.TYPE),
					cmd.getFlagValue(CmdConstants.Flags.MODIFIER));
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.METHOD)) {
			long id = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OBJECT));
			if (cmd.hasFlag(CmdConstants.Flags.MODIFIER) && !cmd.getFlagValue(CmdConstants.Flags.MODIFIER).contains("static")) {
				isc.addInstanceMethod(id, cmd.getFlagValue(CmdConstants.Flags.NAME), cmd.getFlagValue(CmdConstants.Flags.RETURN),
						cmd.getFlagValue(CmdConstants.Flags.ARGUMENTS), cmd.getFlagValue(CmdConstants.Flags.MODIFIER));
				return;
			}
			isc.addStaticMethod(id, cmd.getFlagValue(CmdConstants.Flags.NAME), cmd.getFlagValue(CmdConstants.Flags.RETURN),
					cmd.getFlagValue(CmdConstants.Flags.ARGUMENTS), cmd.getFlagValue(CmdConstants.Flags.MODIFIER) + ","
							+ CmdConstants.Flags.STATIC);
			return;
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.RELATIONSHIP)) {
			long superId = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.SUPERTYPE));
			long subId = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.SUBTYPE));
			isc.addRelationship(superId, subId, cmd.getFlagValue(CmdConstants.Flags.TYPE));
			return;
		}

		// TODO fix this
		// if
		// (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.CACHE_REGEX)) {
		// // Get type
		// String type =
		// cmd.getFlagValue(CmdConstants.Flags.TYPE).toUpperCase();
		// long id = Long.parseLong(CmdConstants.Flags.ID);
		// isc.addObject(type, CacheManager.getInstance().getObject(id));
		// return;
		// }
	}

	// Calling of Remove methods
	private boolean doRemove(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.CLASS) || cmd.getSubCommand().equals(CmdConstants.SubCmdNames.INTERFACE)) {
			long id = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OBJECT));
			return isc.removeClass(id);
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.PACKAGE)) {
			long id = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OBJECT));
			return isc.removePackage(id);
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.METHOD)) {
			long id = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OBJECT));
			return isc.removeMethod(id, cmd.getFlagValue(CmdConstants.Flags.NAME));
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.RELATIONSHIP)) {
			long superId = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.SUPERTYPE));
			long subId = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.SUBTYPE));
			return isc.removeRelationship(superId, subId);
		}
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
			return isc.exportDesignXML(cmd.getFlagValue(CmdConstants.Flags.PATH));
		} else if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.JSON_REGEX)) {
			return isc.exportDesignJSON(cmd.getFlagValue(CmdConstants.Flags.PATH));
		} else if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.SOURCE_REGEX)) {
			return isc.exportDesignSource(cmd.getFlagValue(CmdConstants.Flags.PATH));
		} else
			return false;
	}

	private boolean doImport(ICommand cmd) throws Exception {
		if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.XML_REGEX)) {
			return isc.importDesignXML(cmd.getFlagValue(CmdConstants.Flags.PATH));
		} else if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.JSON_REGEX)) {
			return isc.importDesignJSON(cmd.getFlagValue(CmdConstants.Flags.PATH));
		} else if (cmd.getSubCommand().matches(CmdConstants.SubCmdNames.SOURCE_REGEX)) {
			return isc.importDesignSource(cmd.getFlagValue(CmdConstants.Flags.PATH));
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
		long id = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OBJECT));
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.CLASS) || cmd.getSubCommand().equals(CmdConstants.SubCmdNames.INTERFACE)) {
			return isc.changeName(id, cmd.getFlagValue(CmdConstants.Flags.NAME));
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.PACKAGE)) {
			return isc.changePackage(id, cmd.getFlagValue(CmdConstants.Flags.NAME));
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.METHOD)) {
			long oldId = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OLDNAME));
			HashSet<String> mods = new HashSet<>(Arrays.asList(CmdConstants.Flags.MODIFIER.split(",")));
			List<String> args = Arrays.asList(CmdConstants.Flags.ARGUMENTS.split(","));
			return isc.changeClassMethod(id, oldId, cmd.getFlagValue(CmdConstants.Flags.NAME), args, mods);
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.MODIFIER)) {
			HashSet<String> mods = new HashSet<>(Arrays.asList(CmdConstants.Flags.MODIFIER.split(",")));
			return isc.changeModifiers(id, mods);
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.FIELD)) {
			long oldId = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OLDNAME));
			HashSet<String> mods = new HashSet<>(Arrays.asList(CmdConstants.Flags.MODIFIER.split(",")));
			return isc.changeClassField(id, oldId, cmd.getFlagValue(CmdConstants.Flags.NAME), cmd.getFlagValue(CmdConstants.Flags.TYPE),
					mods);
		}
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
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.CLASS) || cmd.getSubCommand().matches(CmdConstants.SubCmdNames.INTERFACE)) {
			long id = IdManager.getInstance().accessIdWithKey(cmd.getFlagValue(CmdConstants.Flags.OBJECT));
			System.out.println(isc.listObject(id));
			return true;
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.DESIGN)) {
			System.out.println(isc.listDesign());
			return true;
		}
		if (cmd.getSubCommand().equals(CmdConstants.SubCmdNames.CACHE)) {
			// get ID
			long id = Long.parseLong(cmd.getFlagValue(CmdConstants.Flags.ID));
			String type = cmd.getFlagValue(CmdConstants.Flags.TYPE);
			if (type == null) {
				System.out.println("Please specify a type");
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
				System.out.println("Unknown type given");
				return false;
			}
			System.out.println(listable);
			return true;
		} else
			return false;
	}

	private boolean doCache(ICommand cmd) throws MalformattedCommandException {
		long id = 0;
		// Initialized to null
		Set<String> modifiers = null;
		List<String> arguments = null;
		if (cmd.getFlagValue(CmdConstants.Flags.TYPE).equalsIgnoreCase(CmdConstants.SubCmdNames.METHOD)) {
			String methodName = cmd.getFlagValue(CmdConstants.Flags.NAME);
			String returnType = cmd.getFlagValue(CmdConstants.Flags.RETURN);
			String modifiersStr = cmd.getFlagValue(CmdConstants.Flags.MODIFIER);
			// null check
			if (modifiersStr != null) {
				modifiers = new HashSet<String>(Arrays.asList(modifiersStr.split(",")));
			}
			String argumentsStr = cmd.getFlagValue(CmdConstants.Flags.ARGUMENTS);
			if (argumentsStr != null) {
				arguments = new ArrayList<String>(Arrays.asList(argumentsStr.split(",")));
			}
			id = isc.cacheMethod(methodName, returnType, modifiers, arguments);
			if (id == -1) {
				throw new MalformattedCommandException("ID unable to set");
			}
		} else if (cmd.getFlagValue(CmdConstants.Flags.TYPE).equalsIgnoreCase(CmdConstants.Flags.INSTANCE)) {
			String methodName = cmd.getFlagValue(CmdConstants.Flags.NAME);
			String returnType = cmd.getFlagValue(CmdConstants.Flags.RETURN);
			String modifiersStr = cmd.getFlagValue(CmdConstants.Flags.MODIFIER);
			if (modifiersStr != null) {
				modifiers = new HashSet<String>(Arrays.asList(modifiersStr.split(",")));
			}
			id = isc.cacheVariable(methodName, returnType, modifiers);
			if (id == -1) {
				throw new MalformattedCommandException("ID unable to set");
			}
		} else if (cmd.getFlagValue(CmdConstants.Flags.TYPE).equalsIgnoreCase(CmdConstants.Flags.MODIFIER)) {
			String modifiersStr = cmd.getFlagValue(CmdConstants.Flags.MODIFIER);
			if (modifiersStr != null) {
				modifiers = new HashSet<String>(Arrays.asList(modifiersStr.split(",")));
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
	
	private static int randRange(Random gen, int low, int high) {
		return gen.nextInt(high - low) + low;
	}

}