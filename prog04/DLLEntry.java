package prog04;
import prog02.DirectoryEntry;

/** Entry in doubly linked list
 */
public class DLLEntry extends DirectoryEntry {
  /** Creates a new instance of DLLEntry
      @param name name of person
      @param number number of person
  */
  public DLLEntry (String name, String number) {
    super(name, number);
  }

  /** The next entry in the list. */
  private DLLEntry next;

  /** The previous entry in the list. */
  private DLLEntry previous;

  /** Gets the next entry in the list.
      @return the next entry
  */
  public DLLEntry getNext () {
    return next;
  }

  /** Sets the next entry in the list.
      @param next the new next entry
  */
  public void setNext (DLLEntry next) {
    this.next = next;
  }

  /** Gets the previous entry in the list.
      @return the previous entry
  */
  public DLLEntry getPrevious () {
    return previous;
  }

  /** Sets the previous entry in the list.
      @param previous the new previous entry
  */
  public void setPrevious (DLLEntry previous) {
    this.previous = previous;
  }
}


