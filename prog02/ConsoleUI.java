package prog02;

import java.io.*;
import java.util.*;

/**
 * An implementation of UserInterface using the console and text.
 * @author vjm
 */
public class ConsoleUI implements UserInterface {

	/** Scanner to read from input console. */
	private Scanner scIn = null;

	/** Creates a new instance of ConsoleUI */
	public ConsoleUI() {
		scIn = new Scanner(System.in);
	}

	/** presents set of commands for user to choose one of
        @param commands the commands to choose from
        @return the index of the command in the array
	 */
	public int getCommand (String[] commands) {
		int choice = -1;
		do {
			for (int i = 0; i < commands.length; i++) {
				System.out.println("Select " + i + ": "
						+ commands[i]);
			}
			try {
				choice = scIn.nextInt(); // Read the next choice.
				scIn.nextLine(); // Skip trailing newline.

				if (choice >= 0 && choice < commands.length)
					return choice;
				System.out.println("*** Invalid choice "
						+ choice
						+ " - try again!");
				choice = -1;
			}
			catch (InputMismatchException ex) {
				System.out.println("*** Incorrect data entry - "
						+ "enter an integer between 0 and "
						+ (commands.length-1));
				scIn.nextLine(); // Discard bad input.
				choice = -1;
			}
		} while (choice == -1);
		return -1;
	}

	/** tell the user something
	@param message string to print out to the user
	 */
	public void sendMessage (String message) {
		System.out.println(message);
	}

	/** prompts the user for a string
	@param prompt the request
	@return what the user enters, null if nothing
	 */
	public String getInfo (String prompt) {
		System.out.println(prompt + " ");
		String result = scIn.nextLine();
		if (result.equals(""))
			return null;
		return result;
	}

	public static void main (String[] args) {
		UserInterface ui = new ConsoleUI();
		String[] commands = { "hello", "how", "are", "you" };
		int choice = ui.getCommand(commands);
		ui.sendMessage("You chose " + choice);
		String result = ui.getInfo("say something");
		ui.sendMessage("You said " + result);
	}
}
