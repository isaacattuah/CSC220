package prog07;

import java.util.*;

public class TestHeap {
  static class Reverser implements Comparator<String> {
    public int compare (String a, String b) {
      return b.compareTo(a);
    }
  }

  public static void main (String[] args) {
    System.out.println("format is test: CORRECT ANSWER ? YOUR ANSWER");

    Queue<String> pq = new PriorityQueue<String>(1000, new Reverser());
    Queue<String> heap = new Heap<String>(new Reverser());
    
    String[] names = { "Victor", "Irina", "Negin", "Nick", "Joe", "Aaron", "Zoe" };

    boolean bpq = false, bhp = false;
    String spq = null, shp = null;
    for (int i = 0; i < names.length; i++) {
      bpq = pq.offer(names[i]);
      bhp = heap.offer(names[i]);
      if (bpq != bhp)
        System.out.println("offer(" + names[i] + "): " + bpq + " ? " + bhp);
      bpq = pq.remove(names[i]);
      bhp = heap.remove(names[i]);
      if (bpq != bhp)
        System.out.println("remove(" + names[i] + "): " + bpq + " ? " + bhp);
      bpq = pq.remove(names[i]);
      bhp = heap.remove(names[i]);
      if (bpq != bhp)
        System.out.println("remove(" + names[i] + "): " + bpq + " ? " + bhp);
      bpq = pq.offer(names[i]);
      bhp = heap.offer(names[i]);
      if (bpq != bhp)
        System.out.println("offer(" + names[i] + "): " + bpq + " ? " + bhp);
      spq = pq.peek();
      shp = heap.peek();
      if (spq != shp)
        System.out.println("peek(): " + spq + " ? " + shp);
      spq = pq.poll();
      shp = heap.poll();
      if (spq != shp)
        System.out.println("poll(): " + spq + " ? " + shp);
      bpq = pq.offer(spq);
      bhp = heap.offer(spq);
      if (bpq != bhp)
        System.out.println("offer(" + spq + "): " + bpq + " ? " + bhp);
    }
    for (int i = 0; i < names.length; i++) {
      spq = pq.poll();
      shp = heap.poll();
      if (spq != shp)
        System.out.println("poll(): " + spq + " ? " + shp);
    }
  }
}

