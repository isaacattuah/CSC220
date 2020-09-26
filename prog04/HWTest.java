package prog04;
import java.io.*;
import java.util.Scanner;

public class HWTest extends SortedDLLPD {
  public static void main (String[] args) {
    HWTest labTest = new HWTest();
    labTest.test();
  }

  void test () {
    int score = 60;
    int foundOff = testFound();
    if (foundOff != 0)
      System.out.println("ERROR: -" + foundOff + " points");
    else
      System.out.println("CORRECT");
    score -= foundOff;
    int addOff = testAdd();
    if (addOff != 0)
      System.out.println("ERROR: -" + addOff + " points");
    else
      System.out.println("CORRECT");
    score -= addOff;
    int findOff = testFind();
    if (findOff != 0)
      System.out.println("ERROR: -" + findOff + " points");
    else
      System.out.println("CORRECT");
    score -= findOff;
    int removeOff = testRemove();
    if (removeOff != 0)
      System.out.println("ERROR: -" + removeOff + " points");
    else
      System.out.println("CORRECT");
    score -= removeOff;
    System.out.println("SCORE: " + score + "/60");
  }

  String fileName = "labtest.txt";
  String[] names = { "Ann", "Bob", "Ian", "Vic", "Zoe" };
  String[] numbers = { "314", "412", "625", "010", "299" };

  void makeList (int n) {
    DLLEntry[] entries = new DLLEntry[n];
    for (int i = 0; i < n; i++)
      entries[i] = new DLLEntry(names[i], numbers[i]);
    for (int i = 1; i < n; i++) {
      entries[i-1].setNext(entries[i]);
      entries[i].setPrevious(entries[i-1]);
    }
    head = tail = null;
    if (n > 0) {
      head = entries[0];
      tail = entries[n-1];
    }
    return;
  }

  int size() {
    DLLEntry entry = head;
    int n = 0;
    while (entry != null) {
      entry = entry.getNext();
      n++;
    }
    return n;
  }

  DLLEntry get (int i) {
    DLLEntry entry = head;
    for (int j = 0; j < i; j++)
      entry = entry.getNext();
    return entry;
  }

  void printList () {
    int n = size();
    for (int i = 0; i < n; i++) {
      DLLEntry entry = get(i);
      System.out.print(entry.getName() + ":" + entry.getNumber() + " ");
    }
    if (tail == null)
      System.out.println("tail=null");
    else
      System.out.println("tail=" + tail.getName() + ":" + tail.getNumber());
  }

  int testFound () {
    makeList(3);
    if (testFound(get(0), names[0], true) &&
        testFound(get(0), names[1], false) &&
        testFound(null, names[0], false)) {
      return 0;
    }
    return 10;
  }
    
  boolean testFound (DLLEntry entry, String name, boolean correct) {
    boolean ret;
    if (entry == null)
      System.out.println("found(null , " + name + ")");
    else
      System.out.println("found(" + entry.getName() + ":" + entry.getNumber() + ", " + name + ")");
    try {
      ret = found(entry, name);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    System.out.println("returns " + ret);
    return ret == correct;
  }

  int testAdd () {
    if (!testAdd(2, 1) && size() == 3 && equals(tail, names[2], numbers[2]))
      return 20;
    if (!testAdd(0, 0) || !testAdd(2, 0) || !testAdd(2, 2))
      return 10;
    return 0;
  }

  boolean testAdd (int n, int i) {
    makeList(n);
    System.out.println("List is:");
    printList();
    System.out.println("Adding " + names[n] + ":" + numbers[n] +
                       " at position " + i);
    try {
      add(get(i), names[n], numbers[n]);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    System.out.println("List is:");
    printList();
    if (size() != n+1)
      return false;
    for (int j = 0; j < i; j++)
      if (!equals(get(j), names[j], numbers[j]))
        return false;
    if (!equals(get(i), names[n], numbers[n]))
      return false;
    for (int j = i+1; j <= n; j++)
      if (!equals(get(j), names[j-1], numbers[j-1]))
        return false;
    if (!equals(tail, get(n).getName(), get(n).getNumber()))
      return false;
    return true;
  }

  boolean equals (DLLEntry entry, String name, String number) {
    return entry != null && entry.getName().equals(name) && entry.getNumber().equals(number);
  }

  String[] fnames = { "Abe", "Ann", "Bab", "Bob", "Eve" };
  int[] fret = { 0, 0, 1, 1, 2 };

  int testFind () {
    makeList(2);
    for (int i = 0; i < fnames.length; i++)
      if (!testFind(fnames[i], get(fret[i])))
        return 20;
    return 0;
  }

  boolean testFind (String name1, DLLEntry entry) {
    String name = new String(name1);
    System.out.println("List is:");
    printList();
    System.out.println("Finding " + name);
    DLLEntry findOut;
    try {
      findOut = find(name);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    if (findOut == null)
      System.out.println("find(" + name + ") returns null");
    else
      System.out.println("find(" + name + ") returns " +
                         findOut.getName() + ":" + findOut.getNumber());
    return findOut == entry;
  }

  boolean overrideFind = false;
  protected DLLEntry find (String name1) {
    String name = new String(name1);
    if (!overrideFind)
      return super.find(name);
    for (int i = 0; i < size(); i++)
      if (name.equals(get(i).getName()))
        return get(i);
    return null;
  }

  protected boolean found (DLLEntry entry, String name1) {
    String name = new String(name1);
    if (!overrideFind)
      return super.found(entry, name);
    return entry != null && name.equals(entry.getName());
  }

  int testRemove () {
    overrideFind = true;
    if (!testRemove(3, 1) || !testRemove(3, 3))
      return 20;
    if (!testRemove(3, 0) || !testRemove(3, 2))
      return 10;
    if (!testRemove(1, 0))
      return 10;
    if (!testRemove(0, 0))
      return 10;
    makeList(3);
    if (removeEntry(names[3]) != null)
      return 15;
    if (removeEntry(names[1]) != numbers[1])
      return 15;
    return 0;
  }

  boolean testRemove (int n, int iR) {
    String name = new String(names[iR]);
    makeList(n);
    System.out.println("list is:");
    printList();
    System.out.println("Removing " + name);
    try {
      removeEntry(name);
    } catch (Exception e) {
      System.out.println(e);
      return false;
    }
    System.out.println("list is:");
    printList();
    if (iR < n && size() != n-1) {
      // System.out.println(-1);
      return false;
    }
    if (iR == n && size() !=  n) {
      // System.out.println(0);
      return false;
    }
    for (int i = 0; i < iR; i++)
      if (!equals(get(i), names[i], numbers[i])) {
        // System.out.println(1);
        return false;
      }
    for (int i = iR; i < n-1; i++)
      if (!equals(get(i), names[i+1], numbers[i+1])) {
        // System.out.println(2);
        return false;
      }
    if (tail != get(size() - 1)) {
      // System.out.println(3);
      return false;
    }
    return true;
  }
}
