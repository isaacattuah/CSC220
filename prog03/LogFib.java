package prog03;

/**
 *
 * @author vjm
 */
public class LogFib extends PowerFib {
    /** The order O() of the implementation.
	@param n index
	@return the function of n inside the O()
    */
    public double O (int n) {
        return Math.log(n);
    }

    /** Raise x to the n'th power
	@param x x
	@param n n
	@return x to the n'th power
    */
    protected double pow (double x, int n) {
	if (n <= 0)
	    return 1;
	double y = pow(x, n / 2);
	if (n % 2 == 0)
	    return y * y;
	else
	    return x * y * y;
    }
}
