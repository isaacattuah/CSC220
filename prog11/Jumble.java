package prog11;

import java.io.File;
import java.util.Scanner;

import prog02.UserInterface;
import prog02.ConsoleUI;
import prog02.GUI;
import java.util.Map;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Jumble {
  /**
   * Sort the letters in a word.
   * @param word Input word to be sorted, like "computer".
   * @return Sorted version of word, like "cemoptru".
   */
  public static String sort (String word) {
    char[] chars = word.toCharArray();
    Arrays.sort(chars);
    return new String(chars);
  }

  public static void main (String[] args) {
    UserInterface ui = new GUI("Jumble Solver");
    // EXERCISE:
    // Need to change this to allow multiple words with the same key.
    //Map<String, String> map = new ChainedHashTable<String, String>();
   // Map<String, List<String>> map = new ChainedHashTable<String, List<String>>();
    //Map<String, String> map = new OpenHashTable<String, String>();
    Map<String, List<String>> map = new Trie <List<String>>();
    
    

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
      
      String sorted = sort(word);

      // EXERCISE
      // Comment this out.
      //map.put(sorted, word);

      if (!map.containsKey(sorted)) {
        // EXERCISE 

        // Suppose word is "dare" (the first word with a,d,e, and r).
        // Create empty list.
    	  List<String>wordList = new ArrayList<String>();

        // Add "dare" to it.
    	  wordList.add(word);

        // key is "ader", value is the list ["dare"]
        // Store that value for that key.
    	  map.put(sorted, wordList);

      }
      else {
        // EXERCISE
        // Suppose word is "read" (another word with a,d,e and r).
        // Key is "ader" and value is ["dare","dear"]
        // Get the value.
    	  List<String>wordList2 = map.get(sorted);
        // Add "read" to the list.
    	  wordList2.add(word);
        // List is now ["dare","dear","read"]
        // Update the value for that key.
    	  map.put(sorted, wordList2);
      }
    }

    String jumble = ui.getInfo("Enter jumble.");
    while (jumble != null) {
      //String word = map.get(sort(jumble));
      /*if (word == null)
        ui.sendMessage("No match for " + jumble);
      else
        ui.sendMessage(jumble + " unjumbled is " + word);*/

      // EXERCISE:
      // Get the list of words for that jumble.
      List<String>listOfWords = map.get(sort(jumble));
      // Send a message if there is no list:
      if(listOfWords == null)
			ui.sendMessage("No match for: " + jumble);
      // "No match for " + jumble
      // Send a message if there is
      // jumble + " unjumbled is " + listOfWords
      else
			ui.sendMessage(jumble + " unjumbled is " + listOfWords);
      jumble = ui.getInfo("Enter jumble.");
    }

    while (true) {
      String letters = ui.getInfo("Enter letters from clues in any order.");
      if (letters == null)
        return;
      String sortedLetters = sort(letters);

      int l = 0;
      do {
        String number = ui.getInfo("How many letters in the first word?");
        try {
          l = Integer.parseInt(number);
          if (l <= 0)
            ui.sendMessage(number + " is not positive");
        } catch (Exception e) {
          ui.sendMessage(number + " is not a number");
        }
      } while (l <= 0);

      for (String key1 : map.keySet()) {
        // EXERCISE:
        // Check if key1 has the right length.
    	  if(key1.length() == l) {
    		  
    	          String key2 = "";
    	          int j = 0;
        // Add the letters in sortedLetters that aren't in key1 to key2.
    	          for(int i = 0; i < sortedLetters.length(); i++) {
						if(j < key1.length() && sortedLetters.charAt(i) == key1.charAt(j))
							j++;
						else
							key2 = key2 + sortedLetters.charAt(i);
					}
        // If sortedLetters had all the letters in key1 and
        // if key2 has a list of words,
        // show the lists for key1 and key2.
    	          if(j == key1.length()) {
						if(map.containsKey(key2)) 
							ui.sendMessage(map.get(key1) + " " + map.get(key2) );
					}


      }
    }
  }
}
}
        
    

