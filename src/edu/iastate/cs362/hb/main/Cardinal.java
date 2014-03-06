package edu.iastate.cs362.hb.main;

import java.util.Scanner;

import edu.iastate.cs362.hb.commands.ICommand;
import edu.iastate.cs362.hb.commands.ICommandParser;
import edu.iastate.cs362.hb.constants.CmdConstants;
import edu.iastate.cs362.hb.exceptions.MalformattedCommandException;
/**
 * 
 * @author Alex
 *
 */
public class Cardinal {

	private ICommandParser commander;
	
	public static void main(String[] args){
		new Cardinal().run();
		System.out.println("Thanks for using our software!");
	}
	
	/**
	 * This method takes no arguments.
	 * The method continuously runs and gets String commands.
	 */
	private void run(){
		Scanner in = new Scanner(System.in);
		String commandLine = null;
		while(true){
			commandLine = in.nextLine();
			ICommand command = null;
			try{
				command = commander.parseCommand(commandLine);
			}catch(MalformattedCommandException me){
				System.out.println(me.getMessage());
				break;
			}
			if(command.getName().equals(CmdConstants.EXIT_NAME)){
				break;
			}
			/*Then we do what the command says to do!*/
		}
		in.close();
	}
}