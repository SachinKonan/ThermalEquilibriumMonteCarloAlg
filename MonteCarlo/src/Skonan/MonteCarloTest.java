package Skonan;

public class MonteCarloTest {

	public static void main(String[] args) 
	{
		
		MonteCarlo test = new MonteCarlo(10, 10, new double[]{10.0,25.0,100.0,64.0}, 1000);
		test.moveAround();
		test.printer();
	}

}
