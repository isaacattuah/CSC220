package prog03;

/**
 *
 * @author vjm
 */
public class ExponentialFib extends PowerFib {
    /** The Fibonacci number generator 0, 1, 1, 2, 3, 5, ...
	@param n index
	@return nth Fibonacci number
    */
    public double fib (int n) {
	if (n <= 1)
            return n;
        return fib(n-2) + fib(n-1);
    }

    /** The order O() of the implementation.
	@param n index
	@return the function of n inside the O()
    */
    public double O (int n) {
	double r = (1 + Math.sqrt(5)) / 2;
        return Math.pow(r, n);
    }
}
