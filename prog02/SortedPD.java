package prog02;

import java.io.*;

/**
 * This is an implementation of PhoneDirectory that uses a sorted
 * array to store the entries.
 * @author vjm
 */
public class SortedPD extends ArrayBasedPD {
	

	/** Find an entry in the directory.
    @param name The name to be found
    @return the index of the entry with that name or, if it is not
    there, (-insert_index - 1), where insert_index is the index
    where should be added
	 */
	
	protected int find (String name) {
		int middle = 0;
		int first = 0;
		int last = size - 1;
		while (!(first > last))
		{
			middle = (first + last)/2;
			if (theDirectory[middle].getName().compareTo(name) < 0)
			{
				first = middle + 1;
			}
			else if (theDirectory[middle].getName().compareTo(name) > 0)
			{
				last = middle - 1;
			}
			else
			{
				return middle;
			}
				
			
		}
		
			
		
		return -first - 1;
		
	}

	
	/** Add an entry to the directory.
    @param index The index at which to add the entry in theDirectory.
    @param name The name in the new entry.
    @param number The number in the new entry.
    @return The DirectoryEntry that was just added.
	 */
	
	protected DirectoryEntry add (int index, String name, String number) {
		//index = 0;
		if (size == theDirectory.length)
			reallocate();
		for (int i = size  ; i > index; i--)
		{
			theDirectory[i] = theDirectory[i - 1];
		}
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
		for (int i = index ; i < size - 1 ; i++)
		{
			theDirectory[i] = theDirectory[i + 1];
		}
		size--;
		return entry;
	}
	

}
