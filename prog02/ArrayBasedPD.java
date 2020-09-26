package prog02;

import java.io.*;

import java.util.Scanner;

/** This is an implementation of the PhoneDirectory interface that uses
 *   an unsorted array to store the entries.
 */

public class ArrayBasedPD
implements PhoneDirectory {
	/** The initial capacity of the array */
	protected static final int INITIAL_CAPACITY = 5 ;

	/** The current size of the array (number of directory entries) */
	protected int size = 0;

	/** The array to contain the directory data */
	protected DirectoryEntry[] theDirectory =
			new DirectoryEntry[INITIAL_CAPACITY];

	/** The data file that contains the directory data */
	protected String sourceName = null;

	/** Method to load the data file.
      pre:  The directory storage has been created and it is empty.
      If the file exists, it consists of name-number pairs
      on adjacent lines.
      post: The data from the file is loaded into the directory.
      @param sourceName The name of the data file
	 */
	public void loadData (String sourceName) {
		// Remember the source name.
		this.sourceName = sourceName;
		try {
			// Create a BufferedReader for the file.
			Scanner in = new Scanner(new File(sourceName));
			String name;
			String number;

			// Read each name and number and add the entry to the array.
			while (in.hasNextLine()) {
				name = in.nextLine();
				number = in.nextLine();
				// Add an entry for this name and number.
				addOrChangeEntry(name, number);
			}

			// Close the file.
			in.close();
		} catch (FileNotFoundException ex) {
			// Do nothing: no data to load.
			System.out.println(sourceName + ": file not found.");
			System.out.println(ex);
			return;
		}
	}

	/** Add an entry or change an existing entry.
      @param name The name of the person being added or changed
      @param number The new number to be assigned
      @return The old number or, if a new entry, null
	 */
	public String addOrChangeEntry (String name, String number) {
		int index = find(name);
		if (index >= 0) {
			String oldNumber = theDirectory[index].getNumber();
			theDirectory[index].setNumber(number);
			return oldNumber;
		} else {
			add(-index - 1, name, number);
			return null;
		}
	}

	/** Find an entry in the directory.
      @param name The name to be found
      @return the index of the entry with that name or, if it is not
      there, (-insert_index - 1), where insert_index is the index
      where should be added
	 */
	protected int find (String name) {
		for (int i = 0; i < size; i++)
			if (theDirectory[i].getName().equals(name))
				return i;
		
		return -size - 1;
	}

	/** Add an entry to the directory.
      @param index The index at which to add the entry in theDirectory.
      @param name The name in the new entry.
      @param number The number in the new entry.
      @return The DirectoryEntry that was just added.
	 */
	protected DirectoryEntry add (int index, String name, String number) {
		if (size == theDirectory.length)
			reallocate();
		theDirectory[size] = theDirectory[index];
		theDirectory[index] = new DirectoryEntry(name, number);
		size++;
		return theDirectory[index];
	}

	/** Remove an entry from the directory.
      @param index The index in theDirectory of the entry to remove.
      @return The DirectoryEntry that was just removed.
	 */
	protected DirectoryEntry remove (int index) {
		DirectoryEntry entry = theDirectory[index];
		theDirectory[index] = theDirectory[size-1];
		size--;
		return entry;
	}

	/** Allocate a new array to hold the directory. */
	protected void reallocate() {
		DirectoryEntry[] newDirectory =
				new DirectoryEntry[2 * theDirectory.length];
		System.arraycopy(theDirectory, 0, newDirectory, 0,
				theDirectory.length);
		theDirectory = newDirectory;
	}

	/** Remove an entry from the directory.
      @param name The name of the person to be removed
      @return The current number. If not in directory, null is
      returned
	 */
	public String removeEntry (String name) {
		int index = find(name);
		if (index >= 0)
			return remove(index).getNumber();
		else
			return null;
	}

	/** Method to save the directory.
      pre:  The directory has been loaded with data.
      post: Contents of directory written back to the file in the
      form of name-number pairs on adjacent lines.
	 */
	public void save() {
		try {
			// Create PrintWriter for the file.
			PrintWriter out = new PrintWriter(
					new FileWriter(sourceName));

			// Write each directory entry to the file.
			for (int i = 0; i < size; i++) {
				// Write the name.
				out.println(theDirectory[i].getName());
				// Write the number.
				out.println(theDirectory[i].getNumber());
			}

			out.close();
		} catch (Exception ex) {
			System.err.println("Save of directory failed");
			ex.printStackTrace();
			System.exit(1);
		}
	}

	/** Look up an entry.
      @param name The name of the person
      @return The number. If not in the directory, null is returned
	 */
	public String lookupEntry (String name) {
		int index = find(name);
		if (index >= 0)
			return theDirectory[index].getNumber();
		else
			return null;
	}
}

