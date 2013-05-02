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

	private static AsciiImage asciiImage;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		try {
			while (sc.hasNextLine())
				DoOperation(sc);
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

	public static void DoOperation(Scanner scanner) throws InputMismatchException, OperationFailedException, UnknownCommandException
	{
		String fillParamString = scanner.nextLine().trim();

		if (fillParamString.length() == 0)
			return;

		String[] tokens = fillParamString.split(" ");
		String cmd = tokens[0];

		if (createCmd.equals(cmd))
		{
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

			asciiImage = new AsciiImage(width, height);

		}
		else if (loadCmd.equals(cmd))
		{
			if (asciiImage == null)
				throw new InputMismatchException();

			if (tokens.length != 2)
				throw new InputMismatchException();

			String eof = tokens[1];

			asciiImage.load(eof);
			loadImage(scanner);
		}
		else if (printCmd.equals(cmd))
			System.out.println(asciiImage.toString());
		else if (clearCmd.equals(cmd))
			asciiImage.clear();
		else if (transposeCmd.equals(cmd))
			asciiImage.transpose();
		else if (replaceCmd.equals(cmd))
		{			
			if (tokens.length != 3)
				throw new InputMismatchException();

			char oldChar = tokens[1].charAt(0);
			char newChar = tokens[2].charAt(0);

			asciiImage.replace(oldChar,newChar);
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

			asciiImage.drawLine(x0,y0,x1,y1,c);
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

			if (y >= asciiImage.getHeight() || y < 0 || x >= asciiImage.getWidth() || x < 0)
				throw new OperationFailedException();

			asciiImage.fill(x,y,c);
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
			hasInput = asciiImage.addLine(nextLine);
		}
		while (hasInput);			
	}
}
class InputMismatchException extends Exception {}
class OperationFailedException extends Exception {}
class UnknownCommandException extends Exception {}

