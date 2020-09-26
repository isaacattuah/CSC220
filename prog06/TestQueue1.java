package prog06;
import java.util.*;

public class TestQueue1 {
  static void test (Queue<Integer> queue) {
    int n = 0;
    System.out.println("test: []? " + queue);
    System.out.println("test: peek() = null? " + queue.peek());
    System.out.println("test: poll() = null? " + queue.poll());
    for (int i = 0; i < 10; i++)
      queue.offer(n++);
    for (int i = 0; i < 5; i++) {
      queue.poll();
      queue.offer(n++);
    }
    System.out.println("test: [5, 6, 7, 8, 9, 10, 11, 12, 13, 14]? " + queue);
    for (int i = 0; i < 10;i++)
      queue.offer(n++);
    for (int i = 0; i < 10; i++)
      queue.poll();
    for (int i = 0; i < 9; i++)
      queue.poll();
    System.out.println("test: [24]? " + queue);
    System.out.println("test: peek() = 24? " + queue.peek());
    System.out.println("test: poll() = 24? " + queue.poll());
    System.out.println("test: peek() = null? " + queue.peek());
    System.out.println("test: poll() = null? " + queue.poll());
    System.out.println("test: []? " + queue);
    System.out.println();
  }

  public static void main (String[] args) {
    System.out.println("test: Testing ArrayQueue");
    System.out.println("both labReallocate and labIterator should be set to true");
    System.out.println("format is test: CORRECT ANSWER ? YOUR ANSWER");
    try {
      test(new ArrayQueue<Integer>());
    } catch (Exception e) {
      System.out.println("test: exception " + e);
    }
  }
}
