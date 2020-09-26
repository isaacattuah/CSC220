package prog03;
import prog02.UserInterface;
import prog02.GUI;
import prog02.PhoneDirectory;


/**
 *
 * @author vjm
 */
public class Main {
  /** Use this variable to store the result of each call to fib. */
  public static double fibn;

  /** Determine the average time in microseconds it takes to calculate
      the n'th Fibonacci number.
      @param fib an object that implements the Fib interface
      @param n the index of the Fibonacci number to calculate
      @param ncalls the number of calls to average over
      @return the average time per call
  */
  public static double averageTime (Fib fib, int n, int ncalls) {
    // Get the current time in nanoseconds.
    long start = System.nanoTime();

    // Call fib(n) ncalls times (needs a loop!).
    for (int i = 0; i < ncalls; i++)
    {
      fibn = fib.fib(n);
    }
    // Get the current time in nanoseconds.
    long end = System.nanoTime();

    // Return the average time converted to microseconds averaged over ncalls.
    return (end - start) / 1000.0 / ncalls;
  }

  /** Determine the time in microseconds it takes to to calculate the
      n'th Fibonacci number.  Average over enough calls for a total
      time of at least one second.
      @param fib an object that implements the Fib interface
      @param n the index of the Fibonacci number to calculate
      @return the time it it takes to compute the n'th Fibonacci number
  */
  public static double accurateTime (Fib fib, int n) {
    // Get the time in microseconds using the time method above.
    double t = averageTime(fib, n, 1);

    // If the time is (equivalent to) more than a second, return it.
    	if (t > Math.pow(10,6))
    	{
    		return t;
    	}


    // Estimate the number of calls that would add up to one second.
    // Use   (int)(YOUR EXPESSION)   so you can save it into an int variable.
    int numcalls = (int)((Math.pow(10,6))/t);


    // Get the average time using averageTime above and that many
    // calls and return it.
    return averageTime(fib, n, numcalls);
  }

  //private static UserInterface ui = new GUI("Fibonacci experiments");
  private static UserInterface ui = new TestUI();
  public static void doExperiments (Fib fib) {
    System.out.println("doExperiments " + fib);
    // EXERCISES 8 and 9
    double runTime = 0, esTime, runConstant = 0;
    String inputString;
    int choice = 0;
    int n = 0;
   while (true)
    {
	    inputString = ui.getInfo("Enter an integer: ");
	    if (inputString == null)
	    {
	    	break;
	    }
	    
	     
		    try 
		    {
		    	n = Integer.parseInt(inputString);
		    	if (n < 0)
		    	{
		    		throw new IllegalArgumentException();
		    	}
		    	runTime = accurateTime(fib, n);
		    	if (runConstant != 0)
				   {
				     esTime = runConstant * fib.O(n);
				     if (esTime > 3.6E9D) {
				            ui.sendMessage("Estimated time is more than an hour. Do you really want to run it.");
				            
				            String[] choices = { "yes", "no" };
				            choice = ui.getCommand(choices);
				          }
					 ui.sendMessage("Estimated time on new input is " + esTime + " microseconds");
					 ui.sendMessage("Actual time is " + runTime + "   Percentage error " + 100 * (esTime - runTime)/runTime  + " %  ");
				   }
		    	
		 	    runConstant = runTime/fib.O(n);
		 	    ui.sendMessage (" n: " + n + "\n fib(" + n + "): " + fibn + "\n Running Time: " + runTime + " microseconds  \n Constant for O(): " + runConstant);
		 	    
		    	
	
		 	    
		    }
		    
		    
		    catch (NumberFormatException nfe)
		    {
		    	ui.sendMessage( inputString + " is not an integer.");
		    	
		    }
		    catch (IllegalArgumentException nfe)
		    {
		    	ui.sendMessage( inputString + " is a negative integer. Invalid!");
		    }
		    
		    
		 	   
		    
    }
	  
	   
   
    
   
	
	
  }

  

  public static void doExperiments () {
    // EXERCISE 10
	  String[] choices = { "ExponentialFib", "LinearFib", "LogFib", "ConstantFib", "MysteryFib", "EXIT" };
	  while (true)
	  {
	      int choice = ui.getCommand(choices);
	      switch (choice) {
	      case 0: 
	        doExperiments(new ExponentialFib());
	        break;
	      case 1: 
	        doExperiments(new LinearFib());
	        break;
	      case 2: 
	        doExperiments(new LogFib());
	        break;
	      case 3: 
	        doExperiments(new ConstantFib());
	        break;
	      case 4: 
	        doExperiments(new MysteryFib());
	      case 5:
	    	  return;
	      }
	  }
  }
  

  static void labExperiments () {
    // Create (Exponential time) Fib object and test it.
    Fib efib = new LinearFib();
    //Fib efib = new LogFib();
    //Fib efib = new ConstantFib();
    System.out.println(efib);
    for (int i = 0; i < 11; i++)
      System.out.println(i + " " + efib.fib(i));
    
    // Determine running time for n1 = 20 and print it out.
    int n1 = 20;
    double time1 = accurateTime(efib, n1);
    System.out.println("n1 " + n1 + " time1 " + time1);
    
    //insert code here
    //calculating ncalls = ?
    //calculating averageTime() with ncalls
    //calculate accurateTime()
    //print the results
    
    
    
    
    
    // Calculate constant:  time = constant times O(n).
    double c = time1 / efib.O(n1);
    System.out.println("c " + c);
    
    // Estimate running time for n2=30.
    int n2 = 30;
    double time2est = c * efib.O(n2);
    System.out.println("n2 " + n2 + " estimated time " + time2est);
    
    // Calculate actual running time for n2=30.
    double time2 = accurateTime(efib, n2);
    System.out.println("n2 " + n2 + " actual time " + time2);
    
    int n3 = 100;
    double time3est = c * efib.O(n3);
    System.out.println("n3 " + n3 + " estimated time " + time3est);
    
    
  }

  //insert code here
  //calculating ncalls = ?
  //calculating averageTime() with ncalls
  //calculate accurateTime()
  //print the results
  
  /**
   * @param args the command line arguments
   */
  public static void main (String[] args) {
    labExperiments();
    //doExperiments(new ExponentialFib());
    doExperiments();
  }
}
