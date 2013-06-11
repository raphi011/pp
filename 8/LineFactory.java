import java.util.*;

public class LineFactory implements Factory
{
	public LineFactory() {}

	public Operation create(Scanner scanner) throws FactoryException
	{
		try {
			int x0 = scanner.nextInt(); 
			int y0 = scanner.nextInt(); 
			int x1 = scanner.nextInt();
			int y1 = scanner.nextInt();
		
			char c = scanner.next().charAt(0);
		
			return new LineOperation(x0,y0,x1,y1,c);
		}
		catch (Exception e) {
			throw new FactoryException();
		}
	}
}
