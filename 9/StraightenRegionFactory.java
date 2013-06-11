import java.util.Scanner;

public class StraightenRegionFactory implements Factory
{
	public StraightenRegionFactory() {}

	public Operation create(Scanner scanner)
 	{	
		char straightenChar = scanner.next().charAt(0);
	
		return new StraightenRegionOperation(straightenChar);
	}
}
