package prog08;

import java.io.File;
import java.util.Scanner;

import prog02.UserInterface;
import prog02.ConsoleUI;
import prog02.GUI;
import java.util.Map;
import java.util.TreeMap;
import prog02.ArrayBasedPD;
import prog02.SortedPD;
import prog04.SortedDLLPD;
import prog07.BST;

public class Jumble {
  /**
   * Sort the letters in a word.
   * @param word Input word to be sorted, like "computer".
   * @return Sorted version of word, like "cemoptru".
   */
  public static String sort (String word) {
    char[] sorted = new char[word.length()];
    for (int n = 0; n < word.length(); n++) {
      char c = word.charAt(n);
      int i = n;

      while (i > 0 && c < sorted[i-1]) {
        sorted[i] = sorted[i-1];
        i--;
      }

      sorted[i] = c;
    }

    return new String(sorted, 0, word.length());
  }

  public static void main (String[] args) {
    UserInterface ui = new GUI("Jumble Solver");
    // Map<String,String> map = new TreeMap<String,String>();
    //Map<String,String> map = new PDMap(new ArrayBasedPD());
    //Map<String,String> map = new PDMap(new SortedPD());
    //Map<String,String> map = new PDMap(new SortedDLLPD());
    //Map<String,String> map = new BST<String,String>();
     Map<String,String> map = new DLLTree<String,String>();

    Scanner in = null;
    do {
      try {
        in = new Scanner(new File(ui.getInfo("Enter word file.")));
      } catch (Exception e) {
        System.out.println(e);
        System.out.println("Try again.");
      }
    } while (in == null);
	    
    int n = 0;
    while (in.hasNextLine()) {
      String word = in.nextLine();
      if (n++ % 1000 == 0)
	      System.out.println(word + " sorted is " + sort(word));
      
      // EXERCISE: Insert an entry for word into map.
      // What is the key?  What is the value?
      map.put(sort(word), word);
      //sort(word) is the key and word is the value
    }
    

    while (true) {
      String jumble = ui.getInfo("Enter jumble.");
      if (jumble == null)
        return;

      // EXERCISE:  Look up the jumble in the map.
      // What key do you use?
      	String word = map.get(sort(jumble));


      if (word == null)
        ui.sendMessage("No match for " + jumble);
      else
        ui.sendMessage(jumble + " unjumbled is " + word);
    }
  }
}

        
    

