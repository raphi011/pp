import java.util.Scanner;

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

	final static int increaseSize = 3;

	private static AsciiStack asciiStack;
	private static AsciiImage img;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		asciiStack = new AsciiStack(increaseSize);

		try {
			while (sc.hasNextLine())
				doOperation(sc);
		}
		// Various error messages ...
		catch (InputMismatchException e) {
			System.out.println("INPUT MISMATCH");
		}
		catch (OperationException e) {
			System.out.println("OPERATION FAILED");
		}
		catch (UnknownCommandException e) {
			System.out.println("UNKNOWN COMMAND");	
		}
	}

	// puts the current image onto the stack and replaces it with an identical copy
	private static void makeImgCopy()
	{
		AsciiImage imgCopy = new AsciiImage(img);
	
		img = imgCopy;
	}

	public static void doOperation(Scanner scanner) throws InputMismatchException, OperationException, UnknownCommandException
	{
		Operation operation = null;

		String fillParamString = scanner.nextLine().trim();

		if (fillParamString.length() == 0)
			return;

		String[] tokens = fillParamString.split(" ");
		String cmd = tokens[0];

		if (createCmd.equals(cmd))
		{
			if (tokens.length != 4)
				throw new InputMismatchException();

			if (img != null) 
				throw new UnknownCommandException();

			int width;
			int height;

			try {
				width = Integer.parseInt(tokens[1]);
				height = Integer.parseInt(tokens[2]);
			}
			catch (NumberFormatException e) {
				System.out.println("error parsing");
				throw new InputMismatchException();
			}

			if (width < 1 || height < 1)
				throw new InputMismatchException();
			
			String charset = tokens[3];

			img = new ClearOperation().execute(new AsciiImage(width, height, charset));
		}
		else if (straightenCmd.equals(cmd))
		{
			if (tokens.length != 2)
				throw new InputMismatchException();
		
			char straightenChar = tokens[1].charAt(0);
			
			operation = new StraightenRegionOperation(straightenChar);
		}
		else if (growCmd.equals(cmd))
		{
			if (tokens.length != 2)
				throw new InputMismatchException();

			char growChar = tokens[1].charAt(0);
		
			operation = new GrowRegionOperation(growChar);
		}
		else if (centroidCmd.equals(cmd))
		{
			if (tokens.length != 2)
				throw new InputMismatchException();

			char centroidChar = tokens[1].charAt(0);
				
			AsciiPoint p = img.getCentroid(centroidChar);

			if (p == null)
				System.out.println("null");
			else
				System.out.println(p.toString());
		}
		else if (undoCmd.equals(cmd))
		{
			if (asciiStack.empty())
				System.out.println("STACK EMPTY");
			else
				img = asciiStack.pop();
		}
		else if (loadCmd.equals(cmd))
		{
			if (tokens.length != 2)
				throw new InputMismatchException();
			
			if (img == null)
				throw new InputMismatchException();
			
			boolean hasInput = true;
			String eof = tokens[1];
			int currentHeight = 0;
			String input = "";

			do 
			{
				if (!scanner.hasNextLine())
					throw new InputMismatchException();

			 	String nextLine = scanner.nextLine();
				
				if (nextLine.trim().equals(eof))
					hasInput = false;
		    		else
				{
		        		input += nextLine + "\n";
					currentHeight++;
				}
			}
			while (hasInput);			

			operation = new LoadOperation(input);
		}
		else if (printCmd.equals(cmd))
			System.out.println(img.toString());
		else if (clearCmd.equals(cmd))
			operation = new ClearOperation();
		else if (transposeCmd.equals(cmd))
			operation = new TransposeOperation();
		else if (replaceCmd.equals(cmd))
		{			
			if (tokens.length != 3)
				throw new InputMismatchException();

			char oldChar = tokens[1].charAt(0);
			char newChar = tokens[2].charAt(0);
			
			operation = new ReplaceOperation(oldChar, newChar);
		}
		else if (lineCmd.equals(cmd))
		{
			if (tokens.length != 6)
				throw new InputMismatchException();

			if (tokens[5].length() != 1)
				throw new InputMismatchException();

			try {
				int x0 = Integer.parseInt(tokens[1]);
				int y0 = Integer.parseInt(tokens[2]);
				int x1 = Integer.parseInt(tokens[3]);
				int y1 = Integer.parseInt(tokens[4]);
			
				char c = tokens[5].charAt(0);
			
				operation = new LineOperation(x0,y0,x1,y1,c);
			}
			catch (NumberFormatException e) {
				throw new InputMismatchException();
			}
		}
		else if (fillCmd.equals(cmd))
		{
			if (tokens.length != 4)
				throw new InputMismatchException();

			if (tokens[3].length() != 1)
				throw new InputMismatchException();
			
			try {
				int x = Integer.parseInt(tokens[1]);
				int y = Integer.parseInt(tokens[2]);
				char c = tokens[3].charAt(0);
		
				operation = new FillOperation(x,y,c);
			}
			catch (NumberFormatException e) {
				throw new InputMismatchException();
			}
		}
		else
			throw new UnknownCommandException();
	
		if (operation != null)
		{
			asciiStack.push(img);					
			img = operation.execute(img);
		}
	}
}
