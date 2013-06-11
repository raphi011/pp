import java.util.Scanner;

public class MelodySequencer {
    
    /**
     * Test-Methode. 
     * 
     * Testen Sie in dieser Methode die Implementierung 
     * Ihres Programmes durch Objekt-Instanzierungen und
     * Methodenaufrufe. 
     * 
     * Erstellen Sie zuletzt einen sinnvollen Testfall 
     * und beschreiben Sie in einem Kommentar, wieso Sie
     * diesen Testfall gewählt haben und was Sie damit 
     * überprüfen.
     * 
     * (2 Punkte)
     * 
     * Rufen Sie Ihre Anwendung mit 
     * java MelodySequencer
     * auf, um diese Methode auszuführen. 
     */
    public static void testing() {

	
    	// FILL IN
	
	
    }
    
    /**
     * Sammelt verschiedene Testfälle.
     * 
     * Die Methode beinhaltet verschiedene Testfälle, 
     * mit denen Sie die Funktionalität Ihres Programmes
     * testen können.  
     * 
     * Rufen Sie Ihre Anwendung mit 
     * java MelodySequencer [number]
     * auf, wobei [number] die Nummer des Testfalls angibt.
     * 
     * VERÄNDERN SIE DIESE METHODE NICHT! 
     * 
     * Nutzen Sie für eigene Tests die Methode
     * testing() am Beginn dieser Datei. 
     * 
     * @param testCaseNumber Die Nummer des auszuführenden Testfalls.  
     */
    public static void testCases(int testCaseNumber) {

	switch (testCaseNumber) {
	case 1:

	    /*
	     * spec.$1
	     * Überprüft die korrekte Basisimplementierung der Klasse Note.
	     * 
	     * ERWARTETER OUTPUT:
	     * 2
	     * do 2
	     */

		{
		    	
			Note n1 = new Note(0, 2);
			System.out.println(n1.getBeats());
			System.out.println(n1);
		    
		}

	    break;

	case 2:

	    /*
	     * spec.$2
	     * Überprüft die korrekte Basisimplementierung der Klasse Melody.
	     * 
	     * ERWARTETER OUTPUT:
	     * do 2 fa 4 si 6
	     * 6.0 seconds
	     * do 2 fa 4 si 6
	     * 4.5 seconds
	     */

		{
	
			Melody m1 = new Melody(120);
			m1.addNote(new Note(0, 2));
			m1.addNote(new Note(3, 4));
			m1.addNote(new Note(6, 6));
			System.out.println(m1);
			m1.setBPM(160);
			System.out.println(m1);
			
		}

	    break;
	    
	case 3:

	    /*
	     * spec.$3
	     * Überprüft die korrekte Umsetzung der Teilaufgabe 'transponieren'.
	     * 
	     * ERWARTETER OUTPUT:
	     * fa 4
	     * la 4
	     * mi 2 la 4 re 6
	     * 6.0 seconds
	     */

		{
			Note n1 = new Note(1, 4);
			n1.transpose(2);
			System.out.println(n1);
			n1.transpose(-5);
			System.out.println(n1);

			Melody m1 = new Melody(120);	
			m1.addNote(new Note(0, 2));
			m1.addNote(new Note(3, 4));
			m1.addNote(new Note(6, 6));
			m1.transpose(2);
			System.out.println(m1);
			
		}

	    break;
	    
	case 4:

	    /*
	     * spec.$4
	     * Überprüft die korrekte Umsetzung der Teilaufgabe 'kopieren'.
	     * 
	     * ERWARTETER OUTPUT:
	     * fa 4
	     * re 4 mi 1 la 1
	     * 6.0 seconds
	     * re 4 mi 1 la 1
	     * 6.0 seconds
	     */

		{
			
			Note n1 = new Note(3, 4);
			Note n2 = new Note(n1);
			System.out.println(n2);

			Melody m1 = new Melody(60);
			m1.addNote(new Note(0, 2));
			m1.addNote(new Note(3, 4));
			m1.addNote(new Note(1, 4));
			m1.addNote(new Note(2, 1));
			m1.addNote(new Note(5, 1));
			Melody m2 = m1.copy(2);
			System.out.println(m2);
			m1.transpose(1);
			System.out.println(m2);
			
		}

	    break;

	case 5:

	    /*
	     * spec.$5
	     * Überprüft die korrekte Implementierung des Songbooks.
	     * 
	     * ERWARTETER OUTPUT:
	     * true
	     * true
	     * true
	     * false
	     * true
	     */

		{
			
			Melody m1 = new Melody(60);
			m1.addNote(new Note(0, 2));
			m1.addNote(new Note(3, 4));
			SongBook sb1 = new SongBook();
			System.out.println(sb1.addMelody("Testtitel", m1));
			System.out.println(sb1.addMelody("Another Song", new Melody(1)));
			Melody m2 = sb1.getMelody("Testtitel");
			System.out.println(m1 == m2);
			System.out.println(sb1.addMelody("Testtitel", new Melody(2)));
			Melody m3 = sb1.getMelody("Testtitel");
			System.out.println(m1 == m3);
			
		}

	    break;

	}

    }
    
    /**
     * Ausführbare Methode des Programms.
     * 
     * Die Methode verarbeitet das übergebene Argument
     * (falls vorhanden) und ruft den entsprechenden 
     * Testfall auf. 
     * 
     * VERÄNDERN SIE DIESE METHODE NICHT! 
     * 
     * Nutzen Sie für eigene Tests die Methode
     * testing() am Beginn dieser Datei. 
     * 
     * @param args Das erste Argument gibt, falls 
     * 		vorhanden, die Nummer des Testfalls 
     * 		an, der aufgerufen werden soll.
     */
    public static void main(String[] args) {

	if (args.length>0) {

	    Scanner argumentParser=new Scanner(args[0]);
	    if (argumentParser.hasNextInt()) {

		int testCaseNumber=argumentParser.nextInt();
		if (testCaseNumber>=1&&testCaseNumber<=5) {

		    testCases(testCaseNumber);
		    return;

		}

	    }
	    
	    System.err.println("USAGE:");
	    System.err.println("");
	    System.err.println("java MelodySequencer");
	    System.err.println("Ruft die Methode testing() auf.");
	    System.err.println("");
	    System.err.println("java MelodySequencer [number]");
	    System.err.println("Ruft den angegebenen Testfall in testCases() auf.");
	    System.err.println("[number]: Eine Zahl zwischen 1 und 5");

	} else {

	    testing();

	}

    }

}
