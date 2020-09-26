package prog03;

import javax.swing.*;
import prog02.UserInterface;

/**
 *
 * @author vjm
 */
public class TestUI implements UserInterface {

  /** Creates a new instance of TestUI */
  public TestUI() {
  }

  /** Creates a new instance of TestUI */
  public TestUI(String title) {
  }

  int iCase = -1;
  int[] cases = { 0, 1, 2, 3, 4, 0, 5 };
  int iInfo = 0;
  String[][] info  =
  { { "", "abc", "10", "10", "10", "20", "30", "40", null },
    { "100", "100", "100", "200", "400", "800", null },
    { "100", "100", "100", "1000", "10000", "100000", null },
    { "100", "100", "100", "1000", "10000", "100000", null },
    { "100", "100", "100", "200", "500", "1000", null },
    { "10", "20", "30", "40", "100", null },
    { }, };

  /** presents set of commands for user to choose one of
      @param commands the commands to choose from
      @return the index of the command in the array
  */
  public int getCommand (String[] commands) {
    for (int i = 0; i < commands.length; i++)
      System.out.println("UI " + i + ":" + commands[i]);

    if (commands.length == 2) {
      if (commands[0].toLowerCase().indexOf("no") != -1) {
        System.out.println("UI " + "command 0");
        return 0;
      }
      if (commands[1].toLowerCase().indexOf("no") != -1) {
        System.out.println("UI " + "command 1");
        return 1;
      }
      error(-5, "could not find yes or no");
      System.out.println("UI " + "command 1");
      return 1;
    }

    String[] choices = { "ExponentialFib", "LinearFib", "LogFib", "ConstantFib", "MysteryFib", "EXIT" };
    if (commands.length != choices.length) {
      System.out.print("UI Choices should be: ");
      for (int i = 0; i < choices.length; i++)
        System.out.print(" " + choices[i]);
      System.out.println();
      System.out.print("UI Instead of: ");
      for (int i = 0; i < commands.length; i++)
        System.out.print(" " + commands[i]);
      System.out.println();
      System.out.println("UI " + "command " + (commands.length-1));
      return commands.length-1;
    }
    for (int j = 0; j < choices.length; j++)
      if (!commands[j].equalsIgnoreCase(choices[j]))  {
      System.out.print("UI Choices should be: ");
      for (int i = 0; i < choices.length; i++)
        System.out.print(" " + choices[i]);
      System.out.println();
      System.out.print("UI Instead of: ");
      for (int i = 0; i < commands.length; i++)
        System.out.print(" " + commands[i]);
      System.out.println();
      System.out.println("command " + (commands.length-1));
      return commands.length-1;
    }

    if (iCase >= 0 && iInfo < info[iCase].length)
      error(-5, "break too soon");
    System.out.println("UI " + "command " + cases[++iCase]);
    iInfo = 0;
    return cases[iCase];
  }

  /** tell the user something
      @param message string to print out to the user
  */
  public void sendMessage (String message) {
    if (message == null || message.contains("null"))
      error(-5, "message contains null");
    System.out.println("UI " + message);
  }

  /** prompts the user for a string
      @param prompt the request
      @return what the user enters, null if nothing
  */
  public String getInfo (String prompt) {
    System.out.println("UI " + "\t" + prompt);
    if (iInfo == info[iCase].length) {
      error(-5, "missing break");
      return null;
    }
    String ret = info[iCase][iInfo++];
    System.out.println("UI " + "\t" + "? " + ret);
    return ret;
  }

  void error (int off, String mess) {
    System.out.println("ERROR (" + off + " points):  " + mess + ".");
  }

  public static void main (String[] args) {
    UserInterface ui = new TestUI();
    String[] commands = { "hello", "how", "are", "you" };
    int choice = ui.getCommand(commands);
    ui.sendMessage("You chose " + choice);
    String result = ui.getInfo("say something");
    ui.sendMessage("You said " + result);
  }
}
