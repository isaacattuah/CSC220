package prog04;

import prog02.PhoneDirectory;
import java.io.*;
import java.util.Scanner;

/** This is an implementation of the prog02.PhoneDirectory interface
 *   that uses a doubly linked list to store the data.
 *   @author vjm
 */
public class DLLBasedPD implements PhoneDirectory {
  /** The head (first entry) and tail (last entry) of the linked list
   * that stores the directory data */
  protected DLLEntry head, tail;
  
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
      // Create a Scanner for the file.
      Scanner in = new Scanner(new File(sourceName));

      // Read each name and number and add the entry to the array.
      while (in.hasNextLine()) {
        String name = in.nextLine();
        String number = in.nextLine();
        // Add an entry for this name and number.
        addOrChangeEntry(name, number);
      }
      // Close the file.
      in.close();
    } catch (FileNotFoundException ex) {
      // Do nothing: no data to load.
      return;
    } catch (Exception ex) {
      System.err.println("Load of directory failed.");
      ex.printStackTrace();
      System.exit(1);
    }
  }
    
  /** Method to save the directory.
      pre:  The directory has been loaded with data.
      post: Contents of directory written back to the file in the
      form of name-number pairs on adjacent lines.
  */
  public void save () {
    try {
      // Create PrintStream for the file.
      PrintStream out = new PrintStream(new File(sourceName));
      
      // EXERCISE
      // Write each directory entry to the file.
      for (DLLEntry entry = head   ;  entry != null  ; entry = entry.getNext()   ) {
        // Write the name.
        out.println(entry.getName());
        // EXERCISE
        // Write the number.
        out.println(entry.getNumber());

      }
      
      // Close the file.
      out.close();
    } catch (Exception ex) {
      System.err.println("Save of directory failed");
      ex.printStackTrace();
      System.exit(1);
    }
  }
    
  /** Add an entry or change an existing entry.
      @param name The name of the person being added or changed
      @param number The new number to be assigned
      @return The old number or, if a new entry, null
  */
  public String addOrChangeEntry (String name, String number) {
    DLLEntry location = find(name);
    if (found(location, name)) {
      String oldNumber = location.getNumber();
      location.setNumber(number);
      return oldNumber;
    } else {
      add(location, name, number);
      return null;
    }
  }
    
  /** Add a new entry at a location.
      @param location The location to add the new entry, null to add
      it at the end of the list
      @param name The name in the new entry
      @param number The number in the new entry
      @return the new entry
  */
  protected DLLEntry add (DLLEntry location, String name, String number) {
    DLLEntry entry = new DLLEntry(name, number);

    // EXERCISE
    // Add entry to the end of the list.
    if (head == null || tail == null)
    {
      head = entry;
      tail = entry;
    }
    else
    {
    	tail.setNext(entry);
        entry.setPrevious(tail);
        tail = entry;
        
    }
      

    return entry;
  }

  /** Find an entry in the directory.
      @param name The name to be found
      @return The entry with the same name or null if it is not there.
  */
  protected DLLEntry find (String name) {
    // EXERCISE
    // For each entry in the directory.
    // What is the first?  What is the next?  How do you know you got them all?
	  for (DLLEntry entry = head   ;  entry != null  ; entry = entry.getNext() ) 
	  {
      // If this is the entry you want
		  	if (entry.getName().contentEquals(name))
		  	{
		  	return entry;
		  	}
	  }
        // return it.

    return null; // Name not found.
  }
  
  /** Check if a name is found at a location.
      @param location The location to check
      @param name The name to look for at that location
      @return false, if location is null or it does not have that
      name; true, otherwise.
  */
  protected boolean found (DLLEntry location, String name) {
    if (location == null)
      return false;
    return true;
  }

  /** Remove an entry from the directory.
      @param name The name of the person to be removed
      @return The current number. If not in directory, null is
      returned
  */
  public String removeEntry (String name) {
    // Call find to find the entry to remove.
    DLLEntry entry = find(name);
    // If it is not there, forget it!
    
    
    if (!found(entry, name))
      return null;

    // EXERCISE
    // Get the next entry and the previous entry.
    	DLLEntry prev = entry.getPrevious();
    	DLLEntry next = entry.getNext();
    // EXERCISE
    // Two cases:  previous is null (entry is head) or not
      if (prev == null)
      {
    	  head = next;
      }
      else
      {
    	  prev.setNext(next);
      }
    // EXERCISE
    // Two cases:  next is null (entry is tail) or not
      if (next == null)
      {
    	  tail = prev;
      }
      else
      {
    	  next.setPrevious(prev);
      }

    return entry.getNumber();
  }

  /** Look up an entry.
      @param name The name of the person
      @return The number. If not in the directory, null is returned
  */
  public String lookupEntry (String name) {
    DLLEntry entry = find(name);
    if (entry != null && entry.getName().equals(name))
      return entry.getNumber();
    return null;
  }
}
