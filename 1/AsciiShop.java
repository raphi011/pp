import java.util.Scanner;

public class AsciiShop 
{
	public static void main(String[] args)
	{
		// Variable initialisieren
		Scanner sc = new Scanner(System.in);
			 						
		int columnCount = 0;
		int rowCount = 0;
		
		boolean inputIsCorrect = true;
		
		// Schleife die den Input einliest
		while (sc.hasNextLine())
		{
			String input = sc.nextLine();
			
			// Entweder initial Spaltenbreite setzen bzw. wenn bereits gesetzt überprüfen ob sie ident mit der ersten Spaltenbreite ist
			if (columnCount == 0)
				columnCount = input.length();		
			else if (input.length() != columnCount)
				inputIsCorrect = false;
			
			rowCount++;
	}
		
		// Ausgabe des Ergebnisses
		if (!inputIsCorrect)
			System.out.println("INPUT MISMATCH");
		else 
			System.out.println(columnCount + " " + rowCount);
	}	
}
