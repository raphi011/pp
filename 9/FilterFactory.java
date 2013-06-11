import java.util.*;

public class FilterFactory implements Factory
{
	private static final String medianFilter = "median";
	private static final String averageFilter = "average";

	HashMap<String, Operation> operations = new HashMap<String, Operation>();

	public FilterFactory() { 
		operations.put(medianFilter, new MedianOperation(new XBlockGenerator(3)));
		operations.put(averageFilter, new AverageOperation(new XBlockGenerator(3)));
	}

	public Operation create(Scanner scanner) throws FactoryException
	{
		String filterType = scanner.next(); 
		
		Operation operation = operations.get(filterType);

		if (operation != null)
			return operation;
		else
			throw new FactoryException("filter not available");
	}
}
