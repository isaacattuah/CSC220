package prog03;

public class LinearFib implements Fib {
	
	public double fib(int n)
	{
		double a, b, c;
		a = 0; b = 1; c= 0;
		
		for (int i = 0; i < n ; i++)
		{
			
			c = b;
			b = a + b;
			a = c;
		}
		return a;
		
		
		
	}
	
	public double O (int n)
	{
		return n;
	}

}
