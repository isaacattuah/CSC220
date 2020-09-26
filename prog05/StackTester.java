package prog05;
import prog02.GUI;
 
public class StackTester {
  public static void main (String[] args) {
    GUI ui = new GUI("Stack Tester");
    StackInt<String> stack = new ArrayStack<String>();
    //StackInt<String> stack = new LinkedStack<String>();
    //StackInt<String> stack = new ListStack<String>();
    
    String[] commands = { "quit", "empty", "peek", "pop", "push" };
    String item;

    while (true) {
      int c = ui.getCommand(commands);
      switch (c) {
      case 0:
        return;
      case 1:
        ui.sendMessage("empty() returns " + stack.empty());
        break;
      case 2:
        try {
          ui.sendMessage("peek() returns " + stack.peek());
        } catch (Exception e) {
          ui.sendMessage("peek() threw exception: " + e);
        }
        break;
      case 3:
        try {
        ui.sendMessage("pop() returns " + stack.pop());
        } catch (Exception e) {
          ui.sendMessage("pop() threw exception: " + e);
        }
        break;
      case 4:
        item = ui.getInfo("What do you want to push?");
        if (item == null)
          break;
        ui.sendMessage("push(item) returns " + stack.push(item));
        break;
      };
    }
  }
}

        
        
