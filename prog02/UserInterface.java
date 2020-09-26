package prog02;

/** A general interface for a user interface
 * @author vjm
 */
public interface UserInterface {
	/** presents set of commands for user to choose one of
        @param commands the commands to choose from
        @return the index of the command in the array or -1 if the
        user shuts down the program
	 */
	int getCommand (String[] commands);

	/** tell the user something
	@param message string to print out to the user
	 */
	void sendMessage (String message);

	/** prompts the user for a string
	@param prompt the request
	@return what the user enters, empty string if the user enters
	nothing, null if the user cancels
	 */
	String getInfo (String prompt);    
}
