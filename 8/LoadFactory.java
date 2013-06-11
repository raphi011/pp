import java.util.Scanner;

public class LoadFactory implements Factory
{
	public LoadFactory() {} 

	public Operation create(Scanner scanner) throws FactoryException 
	{
		boolean hasInput = true;
		
		if (!scanner.hasNext()) throw new FactoryException();
	
		String eof = scanner.nextLine().trim();

		int currentHeight = 0;
			
		String input = "";
	
//		scanner.next();

		do 
		{
			if (!scanner.hasNextLine())
				throw new FactoryException();

		 	String nextLine = scanner.nextLine();
			
			if (nextLine.equals(eof))
				hasInput = false;
	    		else
			{
	        		input += nextLine + "\n";
				currentHeight++;
			}
		}
		while (hasInput);			
	
		return new LoadOperation(input);
	}
}
