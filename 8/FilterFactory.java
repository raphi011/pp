import java.util.Scanner;

public class FilterFactory implements Factory
{
	public FilterFactory() { 
	
	}

	public Operation create(Scanner scanner) throws FactoryException
	{
		String filterType = scanner.next(); 

		if (!"median".equals(filterType))
			throw new FactoryException(); //InputMismatchException();

		return new MedianOperation();
	}
	}
