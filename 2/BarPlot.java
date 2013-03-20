import java.lang.EnumConstantNotPresentException;
import java.lang.Math;
import java.lang.String;
import java.lang.System;
import java.util.Scanner;
import java.util.Properties;

public class BarPlot 
{
	// Um magic numbers zu verhindern werde diese Werte in Variablen 
	// für die bessere Übersicht gespeichert
    static int MAXBARSIZE = 30;
    static int MAXLABELSIZE = 8;

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
                String output = "";

                // In einer Schleife den Input auslesen
        while (sc.hasNext())
        {
        // Label einlesen
            String input = sc.next();
            String newLine = "";    
            String label = drawLabel(input,MAXLABELSIZE);

            if (sc.hasNextInt())
            	    newLine = drawBar(label,sc.nextInt());       
            else if (sc.hasNextDouble())
                newLine = drawBar(label,sc.nextDouble());
            else // Falls weder ein double noch ein int eingelesen werden können --> Fehler
            	    	    newLine = "INPUT ERROR";

            	    // Neue Zeile zu dem Rest hinzufügen
            output += newLine + System.getProperty("line.separator");

            if (newLine == "INPUT ERROR")
            	    break;
        }
        
                    System.out.print(output);
	}
	
	static String repeat(char c, int n)
	{
        String repeatedString = "";

        for (int i = 0; i < n; i++)
            repeatedString += c;

        return repeatedString;
	}
	
	static String drawLabel(String label, int n)
	{
        int labelCharLength = label.length();

		if (labelCharLength > n )
            return label.substring(0,n);
        else if (labelCharLength == n)
            return label;
        else
        {
        	// Ausrechnen wieviele Zeichen fehlen
            int emptySpacesToAdd = n - label.length();

            for (int i = 0; i < emptySpacesToAdd;i++)
                label += " ";

            return label;
        }
	}

	static String drawBar(String label, int value)
	{
		// Error handling
		if (value > 30)
			return "INPUT ERROR";
		
        String bar = "|";

        for (int i = 0; i < MAXBARSIZE; i++ )
        {
        	// Rauten und Leerzeichen werden in einer Schleife hinzugefügt
            if (i < value)
                bar += "#";
            else
                bar += " ";
        }

        bar += "|";

        return label + bar;
	}
		
	static String drawBar(String label, double value)
	{
		// Double Wert zu int umwandeln um die mit
		// int parameter überladene "drawBar" Methode aufzurufen
        int intValue = (int)Math.round(value * MAXBARSIZE);

        return drawBar(label, intValue);
	}
}
