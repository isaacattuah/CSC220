package prog05;

/** A Stack is a data structure in which objects are inserted into
 *  and removed from the same end (i.e., Last-In, First-Out).
 *  @author vjm
 */

public interface StackInt<E> {
  /** Pushes an item onto the top of the stack and returns the item
      that was pushed.
      @param obj The object to be inserted.
      @return The object inserted.
   */
  E push(E obj);

  /** Returns the object at the top of the stack without removing it
      or changing the stack.
      @return The object at the top of the stack.
      @throws EmptyStackException if stack is empty.
   */
  E peek();

  /** Returns the object at the top of the stack and removes it.
      post: The stack has one less item.
      @return The object at the top of the stack.
      @throws EmptyStackException if stack is empty.
   */
  E pop();

  /** Returns true if the stack is empty; otherwise it returns false.
      @return true if the stack is empty; false if not
   */
  boolean empty();
}
