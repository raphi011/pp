import java.util.Scanner;
import java.util.Arrays;

public class Scrabble {
	
	/**
	* main-Methode: liest von der Standardeingabe ein und arbeitet die
	* einegegebenen Befehle ab. Erzeugt entsprechende Ausgaben.
	*/
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		
		String eingabe = sc.next();
		
		//macht aus einem String ein char-Array
		char[] charSet = eingabe.toCharArray(); 
		
		while(sc.hasNext()){
			
			String command = sc.next();
			
			if(command.equals("print")) {
				System.out.println(Arrays.toString(charSet));
			} 
		
			//FILL IN
			
		}
	}
	
	/**
	* bewegt alle Vorkommnisse des Zeichens c im Array charSet ganz nach 
	* links. Die Reihenfolge aller anderen Zeichen im Array ist danach 
	* beliebig.
	* @param charSet Das Array mit der Zeichenmenge
	* @param c Alle Vorkommnisse dieses Zeichens sollen im Array ganz nach 
	* links bewegt werden.
	*/
	public static void moveLeft (char[] charSet, char c) {
	
		//FILL IN
		
	}
	
	/**
	* liefert die Häufigkeit des angegebenen Zeichens c in der 
	* Zeichenfolge charSet zurück
	* @param charSet Das Array mit der Zeichenmenge
	* @param c Alle Vorkommnisse dieses Zeichens werden gezählt
	* @return Die Anzahl der Vorkommnisse
	*/
	public static int occurrences(char[] charSet, char c) {
		
		//FILL IN
		return 0; //REMOVE THIS LINE
		
	}
	
	
	/**
	* liefert die Häufigkeit des angegebenen Zeichens c im 
	* String word
	* @param word Eine Zeichenkette in der die Anzahl des angegebenen 
	* Zeichens bestimmt wird
	* @param c Alle Vorkommnisse dieses Zeichens werden gezählt
	* @return Die Anzahl der Vorkommnisse
	*/
	public static int occurrences(String word, char c) {
		
		//FILL IN
		return 0; //REMOVE THIS LINE
		
	}
	
	/**
	* liefert einen String, der alle Zeichen enthält, die auch in word 
	* vorkommen, jedoch keine Zeichen mehrfach enthält. Die Reihenfolge der 
	* Zeichen ist beliebig.
	* @param word Der Eingabestring
	* @return Ein String in dem die Zeichen des Eingabestrings einmalig
	* vorkommen.
	*/
	public static String distinct(String word) {
	
		//FILL IN
		return null; //REMOVE THIS LINE
		
	}
	
	/**
	* liefert einen boolean Wert der angibt, ob das als Parameter 
	* angegebene Wort word aus den im Array from gespeicherten Zeichen
	* gebildet werden kann (true) oder nicht (false).
	* @param word Das zu bildende Wort
	* @param from Aus den Zeichen dieses Arrays soll das Wort gebildet 
	* werden
	* @return true wenn das Wort gebildet werden kann, sonst false
	*/
	public static boolean buildWord(String word, char[] from) {
		
		//FILL IN
		return false; //REMOVE THIS LINE
		
	}
	
}
