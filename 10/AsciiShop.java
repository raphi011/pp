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
	final static String saveCmd = "save";
	final static String searchCmd = "search";
	final static String printSavedCmd = "printsaved";

	private static HashMap<String, Factory> factories = new HashMap<String, Factory>();	

    private static MetricSet<AsciiImage> savedImages = new MetricSet<AsciiImage>();

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
        factories.put(createCmd, new CreateFactory());
        factories.put(saveCmd, new SaveFactory(savedImages));
        factories.put(searchCmd, new SearchFactory(savedImages));
	}

	public static void doOperation(Scanner scanner) throws InputMismatchException, OperationException, UnknownCommandException, FactoryException
	{
		Factory factory = null;

		String cmd = scanner.next();
		
		if ((factory = factories.get(cmd)) != null)
		{
		    asciiStack.push(img);
			img = factory.create(scanner).execute(img);
		}
        else if (printSavedCmd.equals(cmd))
        {
            if (savedImages.isEmpty())
                System.out.println("NO SAVED IMAGES");
            else {
                for (AsciiImage savedImage : savedImages)
                    System.out.println(savedImage);
            }
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
