package prog02;

/** The interface for the telephone directory.
 *  @author Koffman and Wolfgang
 */

public interface PhoneDirectory {

	/** Load the data file containing the directory, or
      establish a connection with the data source.
      @param sourceName The name of the file (data source)
      with the phone directory entries
	 */
	void loadData(String sourceName);

	/** Look up an entry.
      @param name The name of the person to look up
      @return The number or null if name is not in the directory
	 */
	String lookupEntry(String name);

	/** Add an entry or change an existing entry.
      @param name The name of the person being added or changed
      @param number The new number to be assigned
      @return The old number or, if a new entry, null
	 */
	String addOrChangeEntry(String name, String number);

	/** Remove an entry from the directory.
      @param name The name of the person to be removed
      @return The current number. If not in directory, null is
      returned
	 */
	String removeEntry(String name);

	/** Method to save the directory.
      pre:  The directory has been loaded with data.
      post: Contents of directory written back to the file in the
      form of name-number pairs on adjacent lines,
      modified is reset to false.
	 */
	void save();
}
