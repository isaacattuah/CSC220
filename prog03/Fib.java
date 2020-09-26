package prog03;

/**
 *
 * @author vjm
 */
public interface Fib {
    /** The Fibonacci number generator 0, 1, 1, 2, 3, 5, ...
	@param n index
	@return nth Fibonacci number
    */
    double fib (int n);

    /** The order O() of the implementation.
	@param n index
	@return the function of n inside the O()
    */
    double O (int n);
}
