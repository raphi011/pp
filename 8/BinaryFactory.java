import java.util.Scanner;

public class BinaryFactory implements Factory
{
	public BinaryFactory()
	{
	}

	public Operation create(Scanner scanner) throws FactoryException
	{
		if (scanner == null) throw new FactoryException();
		
		char threshold = scanner.next().charAt(0);

		return new BinaryOperation(threshold);
	}
}
