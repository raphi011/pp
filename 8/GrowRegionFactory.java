import java.util.Scanner;

public class GrowRegionFactory implements Factory
{
	public GrowRegionFactory() {}

	public Operation create(Scanner scanner)
	{
		char growChar = scanner.next().charAt(0);
	
		return new GrowRegionOperation(growChar);
	}
}
