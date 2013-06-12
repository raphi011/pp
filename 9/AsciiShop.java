import java.util.*;

public class AsciiShop
{
	final static String transposeCmd = "transpose";
	final static String fillCmd = "fill";
	final static String clearCmd = "clear";
	final static String lineCmd = "line";
	final static String loadCmd = "load";
	final static String printCmd = "print";
	final static String replaceCmd = "replace";
	final static String createCmd = "create";
	final static String growCmd = "grow";
	final static String centroidCmd = "centroid";
	final static String undoCmd = "undo";
	final static String straightenCmd = "straighten";	
	final static String filterCmd = "filter";
	final static String binaryCmd = "binary";
	final static String histogramCmd = "histogram";

	private static HashMap<String, Factory> factories = new HashMap<String, Factory>();	

	private static AsciiStack asciiStack;
	private static AsciiImage img;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		asciiStack = new AsciiStack();
		
		createFactories();

		try {
			while (sc.hasNext())
				doOperation(sc);
		}
		// Various error messages ...
		catch (InputMismatchException e) {
			System.out.println("INPUT MISMATCH");
		}
		catch (FactoryException e) {
			System.out.println("INPUT MISMATCH");
		}
		catch (OperationException e) {
			System.out.println("OPERATION FAILED");
		}
		catch (UnknownCommandException e) {
			System.out.println("UNKNOWN COMMAND");	
		}
	}

	private static void createFactories()
	{
		factories.put(transposeCmd, new TransposeFactory());
		factories.put(fillCmd, new FillFactory());
		factories.put(clearCmd, new ClearFactory());
		factories.put(lineCmd, new LineFactory());
		factories.put(loadCmd, new LoadFactory());
		factories.put(replaceCmd, new ReplaceFactory());
		factories.put(growCmd, new GrowRegionFactory());
		factories.put(straightenCmd, new StraightenRegionFactory());
		factories.put(filterCmd, new FilterFactory());
		factories.put(binaryCmd, new BinaryFactory());
	}

	// puts the current image onto the stack and replaces it with an identical copy
	private static void makeImgCopy()
	{
		AsciiImage imgCopy = new AsciiImage(img);
	
		img = imgCopy;
	}

	public static void doOperation(Scanner scanner) throws InputMismatchException, OperationException, UnknownCommandException, FactoryException
	{
		Operation operation = null;

		String cmd = scanner.next();
		
		if ((operation = factories.get(cmd).create(scanner)) != null)
		{
			if (img == null) throw new InputMismatchException();
			
			asciiStack.push(img);					
			img = operation.execute(img);
		}	
		else if (createCmd.equals(cmd))
		{
			if (img != null) 
				throw new UnknownCommandException();

			int width;
			int height;
			String charset;

			try {
				width = scanner.nextInt();
				height = scanner.nextInt();

				charset = scanner.next();
			}
			catch (NoSuchElementException e) {
				throw new InputMismatchException();
			}

			if (width < 1 || height < 1)
				throw new InputMismatchException();
			
			img = new ClearOperation().execute(new AsciiImage(width, height, charset));
		}
		else if (undoCmd.equals(cmd))
		{
			if (asciiStack.empty())
				System.out.println("STACK EMPTY");
			else
				img = asciiStack.pop();
		}
		else if (printCmd.equals(cmd))
			System.out.println(img.toString());
		else if (histogramCmd.equals(cmd))
			System.out.println(Histogram.getHistogram(img).toString()); 
		else
			throw new UnknownCommandException();
	}
}

class UnknownCommandException extends Exception {}
class InputMismatchException extends Exception {}
