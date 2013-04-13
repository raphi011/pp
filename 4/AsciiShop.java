import java.util.Scanner;

public class AsciiShop
{
	static String uniqueCharsCommand = "uniqueChars";
	static String flipCommand = "flip-v";
	static String transposeCommand = "transpose";
	static String fillCommand = "fill";

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);

		String command = sc.next();

		try {
			if (!command.equals("read") || !sc.hasNextInt())
				throw new InputMismatchException();

			int lineCount = sc.nextInt();
			sc.nextLine();

			// AsciiImage initialisieren
			AsciiImage image = new AsciiImage();

			for (int i = 0; i < lineCount; i++) {
				// Wenn weniger Zeilen als angegeben oder die nŠchste Zeile nicht korrekt ist ... Fehler
				if (!sc.hasNextLine() || !image.addLine(sc.nextLine()))
					throw new InputMismatchException();
			}


			if (sc.hasNextLine()) {
				while (sc.hasNextLine()) {
					DoOperation(sc, image); }
			}

			// Ausgabe des Ergebnisses
			System.out.println(image.toString());
			System.out.println(image.getWidth() + " " + image.getHeight());
		}
		catch (InputMismatchException e) {
			System.out.println("INPUT MISMATCH");
		}
		catch (OperationFailedException e) {
			System.out.println("OPERATION FAILED");
		}
	}

	public static void DoOperation(Scanner scanner, AsciiImage image) throws InputMismatchException, OperationFailedException
	{
		String fillParamString = scanner.nextLine().trim();

		if (fillParamString.length() == 0)
			return;

		String[] tokens = fillParamString.split(" ");
		String cmd = tokens[0];

		if (cmd.equals(uniqueCharsCommand))
			System.out.println(image.getUniqueChars());
		else if (cmd.equals(flipCommand))
			image.flipV();
		else if (cmd.equals(transposeCommand))
			image.transpose();
		else if (cmd.equals(fillCommand))
		{
			int x;
			int y;
			char c;

			// Es mŸssen 4 Argumente sein
			if (tokens.length != 4)
				throw new InputMismatchException();

			// Das zu erzeichende Zeichen darf nur 1 lang sein
			if (tokens[3].length() != 1)
				throw new InputMismatchException();

			// Befehl ŸberprŸfen
			if (!tokens[0].equals("fill"))
				throw new InputMismatchException();

			c = tokens[3].charAt(0);

			// Koordinaten parsen
			try {
				x = Integer.parseInt(tokens[1]);
				y = Integer.parseInt(tokens[2]);
			} catch (NumberFormatException e) {
				throw new InputMismatchException(); }

			if (y >= image.getHeight() || y < 0 || x >= image.getWidth() || x < 0)
				throw new OperationFailedException();

			image.fill(x,y,c);
		}
		else
			throw new InputMismatchException();
	}
}

class InputMismatchException extends Exception {}
class OperationFailedException extends Exception {}
