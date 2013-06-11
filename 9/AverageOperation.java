import java.util.Arrays;

public class AverageOperation extends FilterOperation
{
	public AverageOperation(BlockGenerator generator) 
	{
	        super(generator);
	}

	public int filter(int[] values)
	{
		double sum = 0;

		for (int val : values)
			sum += (double)val;
		
		return (int)Math.round(sum / values.length);	
	}
}
