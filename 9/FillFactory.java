import java.util.Scanner;

public class FillFactory implements Factory
{
	public FillFactory() {}

	public Operation create(Scanner scanner) throws FactoryException 
	{
		try {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
	
			char c = scanner.next().charAt(0);
	
			return new FillOperation(x,y,c);
		}
		catch (Exception e) {
			throw new FactoryException();
		}
	}
}
