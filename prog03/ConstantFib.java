package prog03;

public class ConstantFib extends LogFib {

	 /** The order O() of the implementation.
		@param n index
		@return the function of n inside the O()
	    */
	    public double O (int n) {
	        return 1;
	    }

	    /** Raise x to the n'th power
		@param x x
		@param n n
		@return x to the n'th power
	    */
	    protected double pow (double x, int n) {
		
		    return Math.pow(x,n);
	    }
	}

		
		
