package prog05;
import prog02.UserInterface;
import prog02.GUI;

public class Tower {
 static UserInterface ui = new GUI("Towers of Hanoi");
 // static UserInterface ui = new TestUI2();
  static public void main (String[] args) {
    int n = getInt("How many disks?");
    if (n <= 0)
      return;
    Tower tower = new Tower(n);

    String[] commands = { "Human plays.", "Computer plays." };
    int c = ui.getCommand(commands);
    if (c == -1)
      return;
    if (c == 0)
      tower.play();
    else
      tower.solve();
  }

  /** Get an integer from the user using prompt as the request.
   *  Return 0 if user cancels.  */
  static int getInt (String prompt) {
    while (true) {
      String number = ui.getInfo(prompt);
      if (number == null)
        return 0;
      try {
        return Integer.parseInt(number);
      } catch (Exception e) {
        ui.sendMessage(number + " is not a number.  Try again.");
      }
    }
  }

  int nDisks;
  StackInt<Integer>[] pegs = new ArrayStack[3];

  Tower (int nDisks) {
    this.nDisks = nDisks;
    for (int i = 0; i < pegs.length; i++)
      pegs[i] = new ArrayStack<Integer>();

    // EXERCISE: Initialize game with pile of nDisks disks on peg 'a' (pegs[0]).
    for (int i = nDisks; i > 0; i--)
        pegs[0].push(i);

  }

  void play () {
    String[] moves = { "ab", "ac", "ba", "bc", "ca", "cb" };

    while (!(pegs[0].empty() && pegs[1].empty())) 
    {
      displayPegs();
      int imove = ui.getCommand(moves);
      if (imove == -1)
        return;
      String move = moves[imove];
      int from = move.charAt(0) - 'a';
      int to = move.charAt(1) - 'a';
      move(from, to);
    }

    ui.sendMessage("You win!");
  }

  String stackToString (StackInt<Integer> peg) {
    StackInt<Integer> helper = new ArrayStack<Integer>();

    // String to append items to.
    String s = "";
    

    // EXERCISE:  append the items in peg to s from bottom to top.
    while(!(peg.empty()))
    {
    	helper.push(peg.pop());
    }

    while (!(helper.empty()))
    {
    	
    	Integer val = helper.pop();
    	peg.push(val);
    	s += " " + val.toString() + " ";
    }
    	return s;
  }

  void displayPegs () {
    String s = "";
    for (int i = 0; i < pegs.length; i++) {
      char abc = (char) ('a' + i);
      s = s + abc + stackToString(pegs[i]);
      if (i < pegs.length-1)
        s = s + "\n";
    }
    ui.sendMessage(s);
  }

  void move (int from, int to) {
    // EXERCISE:  move one disk form pegs[from] to pegs[to].
    // Don't allow illegal moves:  send a warning message instead.
    // For example "Cannot place disk 2 on top of disk 1."
    // Use ui.sendMessage() to send messages.
	  if (pegs[from].empty() ) 
	  {
		  ui.sendMessage("You cannot remove from an empty peg");
	  }
	  else if (!(pegs[to].empty()) && pegs[from].peek() > pegs[to].peek() )
	  {
		  ui.sendMessage("You cannot move  "+ pegs[from].peek() + " to " + pegs[to].peek());
	  }
	  else
	  {
		  pegs[to].push(pegs[from].pop());
	  }






  }
StackInt <Goal> goals = new LinkedStack<Goal>();
  // EXERCISE:  create Goal class.
  class Goal {
    // Data.
	  int num;
	  int fromPeg;
	  int toPeg;



    // Constructor.
	  Goal (int num, int fromPeg, int toPeg)
	  {
		  this.num = num;
		  this.fromPeg = fromPeg;
		  this.toPeg = toPeg;
	  }






    @Override
	public String toString () {
      String[] pegNames = { "a", "b", "c" };
      String s = "";

      s = s + "Move " + num + " disks";
      if (num > 1)
    	  s = s + "s";
      s = s + " from peg " + pegs[fromPeg];
      s = s + " to peg " + pegs[toPeg] + ".";

   return s;
   
    }
  }
  


  // EXERCISE:  display contents of a stack of goals
 void displayGoals()
 {
	 StackInt<Goal> helper = new ListStack<Goal>();
	 String s = "";
	 while (!goals.empty()) {
		 s += "Move " + goals.peek().num + " disk(s) from " +  (char) ('a' + goals.peek().fromPeg) + " to " + (char) ('a' + goals.peek().toPeg) + ".\n";
		 helper.push(goals.pop());
	 }
		 ui.sendMessage(s);
	while(!helper.empty()) 
		goals.push(helper.pop());
 }


  
  void solve () {
    // EXERCISE
	  goals.push(new Goal(nDisks, 0, 2));
		 while(!goals.empty()) {
			 int num = goals.peek().num;
			 int source = goals.peek().fromPeg;
			 int dest = goals.peek().toPeg;
			 int transfer;
			 if((source+dest) == 1)
				 transfer = 2;
			 else if((source + dest) == 3)
				 transfer = 0;
			 else 
				 transfer = 1;
			 displayGoals();
			 goals.pop();
			 if(num == 1) {
				 move(source, dest);
				 displayPegs();
			 } else {
				 goals.push(new Goal(num - 1, transfer, dest));
				 goals.push(new Goal(1, source, dest ));
				 goals.push(new Goal(num - 1, source, transfer));
			 }
		 }	  
	  }        



  }        

