import java.util.Scanner; 

public class TransposeFactory implements Factory
{
	public TransposeFactory() {}

	public Operation create(Scanner scanner)
	{
		return new TransposeOperation();	
	}
}
