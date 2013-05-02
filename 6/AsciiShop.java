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
		catch (OperationFailedException e) {
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
		asciiStack.push(img);
		img = imgCopy;
	}

	public static void doOperation(Scanner scanner) throws InputMismatchException, OperationFailedException, UnknownCommandException
	{
		String fillParamString = scanner.nextLine().trim();

		if (fillParamString.length() == 0)
			return;

		String[] tokens = fillParamString.split(" ");
		String cmd = tokens[0];

		if (createCmd.equals(cmd))
		{
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

			img = new AsciiImage(width, height);

		}
		else if (straightenCmd.equals(cmd))
		{
			if (tokens.length != 2)
				throw new InputMismatchException();
		
			char straightenChar = tokens[1].charAt(0);

			makeImgCopy();

			img.straightenRegion(straightenChar);
		}
		else if (growCmd.equals(cmd))
		{
			if (tokens.length != 2)
				throw new InputMismatchException();

			char growChar = tokens[1].charAt(0);
			
			makeImgCopy();

			img.growRegion(growChar);
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
			{
				img = asciiStack.pop();
				System.out.println("STACK USAGE " + asciiStack.size() + "/" + asciiStack.capacity());
}
		}
		else if (loadCmd.equals(cmd))
		{
			if (img == null)
				throw new InputMismatchException();

			if (tokens.length != 2)
				throw new InputMismatchException();

			String eof = tokens[1];

			makeImgCopy();

			img.load(eof);
			loadImage(scanner);
		}
		else if (printCmd.equals(cmd))
			System.out.println(img.toString());
		else if (clearCmd.equals(cmd))
		{	
			makeImgCopy();

			img.clear();
		}
		else if (transposeCmd.equals(cmd))
		{
			makeImgCopy();

			img.transpose();
		}
		else if (replaceCmd.equals(cmd))
		{			
			if (tokens.length != 3)
				throw new InputMismatchException();

			char oldChar = tokens[1].charAt(0);
			char newChar = tokens[2].charAt(0);

			makeImgCopy();

			img.replace(oldChar,newChar);
		}
		else if (lineCmd.equals(cmd))
		{
			int x0;
			int y0;
			int x1;
			int y1;
			char c;

			if (tokens.length != 6)
				throw new InputMismatchException();

			if (tokens[5].length() != 1)
				throw new InputMismatchException();

			try {
				x0 = Integer.parseInt(tokens[1]);
				y0 = Integer.parseInt(tokens[2]);
				x1 = Integer.parseInt(tokens[3]);
				y1 = Integer.parseInt(tokens[4]);
			}
			catch (NumberFormatException e) {
				throw new InputMismatchException();
			}

			c = tokens[5].charAt(0);

			makeImgCopy();

			img.drawLine(x0,y0,x1,y1,c);
		}
		else if (fillCmd.equals(cmd))
		{
			int x;
			int y;
			char c;

			if (tokens.length != 4)
				throw new InputMismatchException();

			if (tokens[3].length() != 1)
				throw new InputMismatchException();

			c = tokens[3].charAt(0);

			try {
				x = Integer.parseInt(tokens[1]);
				y = Integer.parseInt(tokens[2]);
			}
			catch (NumberFormatException e) {
				throw new InputMismatchException();
			}

			if (y >= img.getHeight() || y < 0 || x >= img.getWidth() || x < 0)
				throw new OperationFailedException();

			img.fill(x,y,c);
		}
		else
			throw new UnknownCommandException();
	}
	
	private static void loadImage(Scanner s) throws InputMismatchException
	{
		// Parses one line at a time
		String nextLine;
		
		boolean hasInput = true;
		
		do 
		{
		 	nextLine = s.nextLine();
			hasInput = img.addLine(nextLine);
		}
		while (hasInput);			
	}
}

class InputMismatchException extends Exception {}
class OperationFailedException extends Exception {}
class UnknownCommandException extends Exception {}

