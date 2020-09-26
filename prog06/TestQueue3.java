package prog06;
import java.util.*;

public class TestQueue3 {
  static void compare (Queue<Integer> queue, String s) {
    System.out.println("test: " + s + "? " + queue);
    if (!s.equals("" + queue))
      System.out.print("wrong");
    else
      System.out.print("correct");
  }

  static void test (Queue<Integer> queue) {
    int n = 0;
    for (int i = 0; i < 10; i++)
      queue.offer(n++);
    for (int i = 0; i < 5; i++) {
      queue.poll();
      queue.offer(n++);
    }
    compare(queue, "[5, 6, 7, 8, 9, 10, 11, 12, 13, 14]");
    queue.offer(n++);
    //compare(queue, "[5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]");
  }

  public static void main (String[] args) {
    System.out.println("test: Testing ArrayQueue");
    System.out.println("both labReallocate and labIterator should be set to false");
    System.out.println("format is test: CORRECT ANSWER ? YOUR ANSWER");
    try {
      test(new ArrayQueue<Integer>());
    } catch (Exception e) {
      System.out.println("test: exception " + e);
    }
  }
}
