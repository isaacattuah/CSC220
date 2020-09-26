package prog06;
import prog02.UserInterface;
import java.util.*;
import prog02.GUI;
import java.io.*;

public class WordStep 
{
	 static UserInterface ui = new GUI("WordStep");
	 //static UserInterface ui = new TestUI2();
	  List<String> words = new ArrayList<String>(); //words is my list
	
	public static void main (String [] args)
	{
		WordStep game = new WordStep();
	
		
		String filename = null;
	    do {
	      filename = ui.getInfo("Enter word file:");
	      if (filename == null)
	        return;
	    } 
	    while (!game.loadWords(filename));
		
		
		
		
		String startWord, targetWord;
		String[] commands = { "Human plays.", "Computer plays." };
		
	    startWord = ui.getInfo("Starting Word: ");
	    if (startWord == null)
		{
			return;
		}
	    
	    while (game.find(startWord) == -1)
	    {
	    	ui.sendMessage(startWord + " is not a word");
	    	startWord = ui.getInfo("Try Again: ");
	    }
	    
	    targetWord = ui.getInfo("Target Word: ");
	    if (targetWord == null)
	    {
	    	return;
	    }
	    
	    if (game.find(targetWord) == -1)
	    {
	    	ui.sendMessage(targetWord + " is not a word");
	    	targetWord = ui.getInfo("Try Again: ");
	    }
	    
	    int c = ui.getCommand(commands);
	    
	    switch (c) {
	    	case -1:
	    		return;
	    	case 0:
	    		game.play(startWord, targetWord);break;
	    	case 1:
	    		game.solve(startWord, targetWord);break;
	    
	    }
	    
	}
	void play (String start, String target) { 
		
		while(true)
		{
			
			ui.sendMessage("Current word: " + start + "\nTarget word: " + target);
			String oldStart = start;
			start = ui.getInfo("What is your next word ?");
			 if (start == null)
			{
				return;
			}
			 
			if (find(start) == -1)
		    {
		    	ui.sendMessage(start + " is not a word");
		    	start = ui.getInfo("Try Again: ");
		    }
			
			else if (start.equals(target))
			{
				ui.sendMessage("You win!");
				return;
			}
			else if (offBy1(oldStart,start) == false)
			{
				ui.sendMessage("Sorry, "+ start +" differs by more than one letter from " + oldStart);
				start = oldStart;
			}
			
			
			
			
			
		}
		
	}
	  
	void solve (String start, String target) 
	{
		int[] parents = new int [words.size()];
		
		Arrays.fill(parents, -1);
		ArrayQueue<Integer> nums = new ArrayQueue<Integer>(); //nums is my array queue
		
		int removeIndex;
		String currentWord;
		String currentWord2;
		
		int j = find(start);
		nums.offer(Integer.valueOf(j));
		while(!nums.isEmpty())
		{
			removeIndex = nums.poll();
			currentWord = words.get(removeIndex);
			for (int i = 0;  i < words.size();i++)
			{
				currentWord2 = words.get(i);
				if (i!= j && (parents[i] == -1))
				{
					if(offBy1(currentWord, currentWord2))
							{
								parents[i] = removeIndex;
								nums.offer(Integer.valueOf(i));
								System.out.print (" " + currentWord2);
								
								if (currentWord2.equals(target))
								{
									ui.sendMessage("Go to " + target + " from "+ currentWord);
									String str3 = currentWord2 + "\n" + target;
									while (i != j)
									{
										i = parents[i];
										str3 = words.get(i) + "\n" + str3;
									}
									ui.sendMessage(str3);
									return;
								}
							}
						
				}
			}
			
		}
		
		
	}
	
	static boolean offBy1(String firstWord, String secondWord)
	{
		if (firstWord.length() != secondWord.length() )
			return false;
		int i = 0;
		for (int j = 0; j < firstWord.length(); j++)
		      if (firstWord.charAt(j) != secondWord.charAt(j))
		        i++;
		    return i == 1;
	}
	
	boolean loadWords (String file)
	{
		try
		{
			Scanner fStream = new Scanner(new File(file));
			
			while (fStream.hasNextLine())
			{
				words.add(new String(fStream.nextLine()));
			}
			fStream.close();
			return true;
		}
		catch (FileNotFoundException ex)
		{
			ui.sendMessage("Uh oh: " + ex);
			return false;
		}
		
	}
	
       int find(String word)
	{
		for (String element: words) 
		{
			if (word.equals(element))
			{
				return words.indexOf(element);
			}
		}
		return -1;
		
	}
}
