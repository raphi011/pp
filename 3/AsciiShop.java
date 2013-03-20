import java.util.Scanner;

public class AsciiShop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String command = sc.next();

        if (!command.equals("read") || !sc.hasNextInt()) {
            System.out.println("INPUT MISMATCH");
            return;
        }

        int lineCount = sc.nextInt();
        sc.nextLine();

        // Variable initialisieren
        String[] lines = new String[lineCount];

        int columnCount = 0;

        boolean inputIsCorrect = true;

        for (int i = 0; i < lineCount; i++) {
            // Wenn weniger Zeilen als angegeben... Fehler
            if (!sc.hasNextLine()) {
                inputIsCorrect = false;
                break;
            }

            String input = sc.nextLine();
            lines[i] = input;

            // Entweder initial Spaltenbreite setzen bzw. wenn bereits gesetzt überprüfen ob sie ident mit der ersten Spaltenbreite ist
            if (columnCount == 0)
                columnCount = input.length();
            else if (input.length() != columnCount)
                inputIsCorrect = false;
        }

        boolean fillCoordinatesAreCorrect = true;

        if (inputIsCorrect && sc.hasNextLine()) {

            ParseResult parseResult;

            do {
                FillParams fp = new FillParams();

                parseResult = fp.Parse(sc);

                // Parse Ergebnis überprüfen
                if (parseResult == parseResult.Error) inputIsCorrect = false;
                if (parseResult == ParseResult.Error || parseResult == ParseResult.Empty) break;

                if (fp.y >= lines.length || fp.y < 0 || fp.x >= lines[fp.y].length() || fp.x < 0) {
                    fillCoordinatesAreCorrect = false;
                    break;
                }

                fill(lines, fp.x, fp.y, fp.c);

            } while (parseResult == ParseResult.Ok);
        }

        if (!inputIsCorrect)
            System.out.println("INPUT MISMATCH");
        else if (!fillCoordinatesAreCorrect)
            System.out.println("OPERATION FAILED");
        else {
            // Ausgabe des Ergebnisses
            for (int i = 0; i < lines.length; i++)
                System.out.println(lines[i]);

            System.out.println(columnCount + " " + lines.length);
        }
    }

    public static void fill(String[] image, int x, int y, char c) {
        char charToReplace = image[y].charAt(x);

        // Zeichen ersetzen!
        image[y] = replaceCharAt(image[y], x, c);

        //links
        if (x - 1 >= 0 && image[y].charAt(x - 1) == charToReplace)
            fill(image, x - 1, y, c);
        // oben
        if (y - 1 >= 0 && image[y - 1].charAt(x) == charToReplace)
            fill(image, x, y - 1, c);
        // rechts
        if (x + 1 < image[y].length() && image[y].charAt(x + 1) == charToReplace)
            fill(image, x + 1, y, c);
        // unten
        if (y + 1 < image.length && image[y + 1].charAt(x) == charToReplace)
            fill(image, x, y + 1, c);
    }

    public static String replaceCharAt(String s, int pos, char c) {
        StringBuffer buffer = new StringBuffer(s);
        buffer.setCharAt(pos, c);
        return buffer.toString();
    }
}

enum ParseResult {
    Error,
    Empty,
    Ok,
}

class FillParams {
    public int x;
    public int y;
    public char c;

    public ParseResult Parse(Scanner s) {

        if (!s.hasNextLine())
            return ParseResult.Empty;

        String fillParamString = s.nextLine().trim();

        if (fillParamString.length() == 0)
            return ParseResult.Empty;

        String[] tokens = fillParamString.split(" ");

        // Es müssen 4 Argumente sein
        if (tokens.length != 4)
            return ParseResult.Error;

        // Das zu erzeichende Zeichen darf nur 1 lang sein
        if (tokens[3].length() != 1)
            return ParseResult.Error;

        // Befehl überprüfen
        if (!tokens[0].equals("fill"))
            return ParseResult.Error;

        c = tokens[3].charAt(0);

        // Koordinaten parsen
        try {
            x = Integer.parseInt(tokens[1]);
            y = Integer.parseInt(tokens[2]);
        } catch (NumberFormatException e) {
            return ParseResult.Error;
        }

        return ParseResult.Ok;
    }
}
