package prog06;

import java.util.Queue;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements the Queue interface using a single-linked list.
 **/
public class LinkedQueue<E> extends AbstractQueue<E>
        implements Queue<E> {

    // Data Fields
    /** Reference to first item in the queue. */
    private Node<E> first;
    /** Reference to last item in the queue. */
    private Node<E> last;
    /** Size of queue. */
    private int size;

    /** A Node is the building block for a single-linked list. */
    private static class Node<E> {
        // Data Fields

        /** The reference to the data. */
        private E item;
        /** The reference to the next node. */
        private Node<E> next;

        // Constructors
        /**
         * Creates a new node with a null next field.
         * @param item The item stored
         */
        private Node (E item) {
          this.item = item;
          next = null;
        }

        /**
         * Creates a new node that references another node.
         * @param item The item stored
         * @param next The node referenced by new node
         */
        private Node (E item, Node<E> next) {
          this.item = item;
          this.next = next;
        }
    } //end class Node

    // Methods
    /**
     * Insert an item at the back of the queue.
     * @post item is added to the back of the queue.
     * @param item The element to add
     * @return true (always successful)
     */
    @Override
    public boolean offer (E item) {
        // Check for empty queue.
        if (first == null) {
            last = new Node<E>(item);
            first = last;
        } else {
            // Add a new node with the item to the back of the queue.
            last.next = new Node<E>(item);
            last = last.next;
        }
        size++;
        return true;
    }

    /**
     * Remove the entry at the front of the queue and return it
     * if the queue is not empty.  Return null otherwise.
     * @post first references item that was second in the queue.
     * @return The item removed if successful, or null if not.
     */
    @Override
    public E poll () {
      // EXERCISE
    	if (size == 0)
    	{
    		return null;
    	}
    	
    	E entry = first.item;
    	first = first.next;
    	size--;
    	return entry;
    	





    }

    /**
     * Return the item at the front of the queue without removing it.
     * @return The item at the front of the queue if successful;
     * return null if the queue is empty
     */
    @Override
    public E peek () {
        if (size == 0)
            return null;
        return first.item;
    }

    /**
     * Returns the size of the queue
     * @return the size of the queue
     */
    @Override
      public int size () {
      return size;
    }

    /**
     * Returns an iterator to the contents of this queue
     * @return an iterator to the contents of this queue
     */
    public Iterator<E> iterator () {
      return new Iter();
    }

    /**
     * Inner class to provide an iterator to the contents of
     * the queue.
     */
    private class Iter implements Iterator<E> {
      Node<E> next = first;
      /**
       * Returns true if there are more elements in the iteration
       * @return true if there are more elements in the iteration
       */
      @Override
      public boolean hasNext () {
        // EXERCISE
        // Look at the initial value of next (8 lines up from here ^).
        // Return true if there is a next element to return.
    	  if (next == null)
    	  {
    		  return false;
    	  }
    	  return true;
      }
      /**
       * Return the next item in the iteration and advance the iterator
       * @return the next item in the iteration
       * @throws NoSuchElementException if the iteration has no more elements
       */
      @Override
      public E next() {
        if (!hasNext())
          throw new NoSuchElementException();
        // EXERCISE
        // Return the next element and go forward.
         E val = next.item;
         next = next.next;
         return val;
        


      }
      /**
       * Removes the last item returned by this iteration
       * @throws UnsupportedOperationException this operation is not
       * supported
       */
      @Override
        public void remove () {
        throw new UnsupportedOperationException();
      }
    }
}
/*</listing>*/
