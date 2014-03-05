package edu.iastate.cs362.hb.main;

import java.util.Scanner;

import edu.iastate.cs362.hb.commands.ICommandParser;

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
		while(true){
			String command = in.nextLine();
			commander.parseCommand(command);
			//Magic happens here.
		}
	}
}