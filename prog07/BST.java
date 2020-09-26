package prog07;
import java.util.*;

public class BST <K extends Comparable<K>, V>
  extends AbstractMap<K, V> {

  private class Node <K extends Comparable<K>, V>
    implements Map.Entry<K, V> {
    K key;
    V value;
    Node left, right;
    
    Node (K key, V value) {
      this.key = key;
      this.value = value;
    }

    public K getKey () { return key; }
    public V getValue () { return value; }
    public V setValue (V newValue) {
      V oldValue = value;
      value = newValue;
      return oldValue;
    }
  }
  
  private Node root;
  private int size;

  public int size () { return size; }

  /**
   * Find the node with the given key.
   * @param key The key to be found.
   * @return The node with that key.
   */
  private Node<K, V> find (K key, Node<K,V> root) {
    // EXERCISE:
	 if (root == null)
		 return null;
	 int cmp = key.compareTo(root.key);
	 
	 if (cmp == 0)
		 return root;
	 else if (cmp < 0)
		 return find(key, root.left);
	 else 
		 return find(key, root.right);

  }    

  public boolean containsKey (Object key) {
    return find((K) key, root) != null;
  }
  
  public V get (Object key) {
    Node<K, V> node = find((K) key, root);
    if (node != null)
      return node.value;
    return null;
  }
  
  public boolean isEmpty () { return size == 0; }
  
  /**
   * Add key,value pair to tree rooted at root.
   * Return root of modified tree.
   */
  private Node<K,V> add (K key, V value, Node<K,V> root) {
    // EXERCISE:
	  if (root == null)
		  return new Node <K,V>(key, value);
	  
	  if (key.compareTo(root.key) < 0)
	  {
		  root.left = add(key, value, root.left);
	  }
	  else
	  {
		  root.right = add(key, value, root.right);
	  }
    return root;
  }
  
  int depth (Node root) {
    if (root == null)
      return -1;
    return 1 + Math.max(depth(root.left), depth(root.right));
  }

  public V put (K key, V value) {
    // EXERCISE:
	  Node <K, V> newNode = find(key, root);
	  if (newNode == null)
	  {
		  root = add(key,value, root);
		  size++;
		  return null;
	  }
	  else
	  {
		  V oldValue = newNode.getValue();
		  newNode.setValue(value);
		  return oldValue;
	  }
	  


    
  }      
  
  public V remove (Object keyAsObject) {
    K key = (K) keyAsObject;

    // EXERCISE:  Delete these lines and implement remove.

    Node<K,V> node = find(key, root);
    if (node == null)
      return null;
    root = remove(key, root);
    size--;
    return node.value;

   
  }

  private Node<K,V> remove (K key, Node<K,V> root) {
    // EXERCISE:
	  if (key.compareTo(root.key) == 0)
		  return removeRoot(root);
	  	if (key.compareTo(root.key) < 0) 
	  	{
	  		root.left = remove(key, root.left);
	  		
	  	} 
	  	else 
	  	{
	  		root.right = remove(key, root.right);
	  		
	  	}
	  	return root;
  }

  /**
   * Remove root of tree rooted at root.
   * Return root of BST of remaining nodes.
   */
  private Node removeRoot (Node root) {
    
	  if (root.left == null)
		  return root.right;
	  if (root.right == null)
		  return root.left;
		  Node newRoot = getMinimum(root.right);
		  root.right = removeMinimum(root.right);
		  newRoot.left = root.left;
		  newRoot.right = root.right;
		  root = newRoot;
	 return root;
  }
  
   //IMPLEMENT using getMinimum and removeMinimum
  // Node returned by getMinimum becomes the new root.
  private Node getMinimum (Node root) {
	    // IMPLEMENT
		  if (root.left == null)
			  return root;
		  return getMinimum(root.left);
	  }

  // IMPLEMENT getMinimum and removeMinimum
  private Node removeMinimum (Node root) {
	    // IMPLEMENT
		  if (root.left == null)
			  return root.right;
		  root.left = removeMinimum(root.left);
		  return root;
	  }



  public Set<Map.Entry<K, V>> entrySet () { return null; }
  
  public String toString () {
    return toString(root, 0);
  }
  
  private String toString (Node root, int indent) {
    if (root == null)
      return "";
    String ret = toString(root.right, indent + 2);
    for (int i = 0; i < indent; i++)
      ret = ret + "  ";
    ret = ret + root.key + " " + root.value + "\n";
    ret = ret + toString(root.left, indent + 2);
    return ret;
  }

  public static void main (String[] args) {
    BST<Character, Integer> tree = new BST<Character, Integer>();
    String s = "balanced";
    
    for (int i = 0; i < s.length(); i++) {
      tree.put(s.charAt(i), i);
      System.out.print(tree);
      System.out.println("-------------");
    }

    for (int i = 0; i < s.length(); i++) {
      tree.remove(s.charAt(i));
      System.out.print(tree);
      System.out.println("-------------");
    }
  }
}
