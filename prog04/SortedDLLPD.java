package prog04;

public class SortedDLLPD extends DLLBasedPD
{

	
	 /** Add a new entry at a location.
    @param location The location to add the new entry, null to add
    it at the end of the list
    @param name The name in the new entry
    @param number The number in the new entry
    @return the new entry
*/
protected DLLEntry add (DLLEntry location, String name, String number) {
  DLLEntry entry = new DLLEntry(name, number);
 
  DLLEntry next =  location;
  
  
  if (head == null || tail == null)
  {
	  head = entry;
	  tail = entry;
  }
  else if (next == head)
  {
	  entry.setNext(head);
	  head.setPrevious(entry);
	  head = entry;
  }
  else if (next == null)
  {
	  entry.setPrevious(tail);
	  tail.setNext(entry);
	  tail = entry;
  }
  else
  {
	  DLLEntry previous = location.getPrevious();
	  previous.setNext(entry);
	  entry.setPrevious(previous);
	  next.setPrevious(entry);
	  entry.setNext(next);
  }
  
    

  return entry;
  }


/** Find an entry in the directory.
    @param name The name to be found
    @return The entry after the name it is looking for.
*/
protected DLLEntry find (String name) {
  // EXERCISE
  // For each entry in the directory.
  // What is the first?  What is the next?  How do you know you got them all?
	  for (DLLEntry entry = head   ;  entry != null ; entry = entry.getNext() ) 
	  {
    
		  
		  
		  
		  	if (entry.getName().compareTo(name) >= 0 )
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
  if (location == null || !(location.getName().contentEquals(name)))
    return false;
  return true;
}


}
