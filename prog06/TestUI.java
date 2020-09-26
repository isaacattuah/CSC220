package prog06;

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

  int iComm = 0;
  int[] comm = { 0 };
  int iInfo = 0;
  String[] info  = { "words", "fail", "pass", 
                     "pall", "fail", "fais", "fail",
                     "fall", "pall", "pals", "pass" }; 

  /** presents set of commands for user to choose one of
      @param commands the commands to choose from
      @return the index of the command in the array
  */
  public int getCommand (String[] commands) {
    for (int i = 0; i < commands.length; i++)
      System.out.print((i == 0 ? "" : " ") + i + ":" + commands[i]);
    System.out.println();

    System.out.println("UI " + comm[iComm]);
    return comm[iComm++];
  }

  /** tell the user something
      @param message string to print out to the user
  */
  public void sendMessage (String message) {
    System.out.println(message);
  }

  /** prompts the user for a string
      @param prompt the request
      @return what the user enters, null if nothing
  */
  public String getInfo (String prompt) {
    System.out.println(prompt);
    System.out.println("UI " + info[iInfo]);
    return info[iInfo++];
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
