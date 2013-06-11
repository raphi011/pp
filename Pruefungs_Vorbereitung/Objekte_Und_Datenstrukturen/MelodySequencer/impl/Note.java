/**
 * Diese Klasse repräsentiert eine Note. Eine Note ist der kleinste Bestandteil
 * einer Melodie und ist charakterisiert durch Höhe und Länge. Die Notenlänge
 * wird in Schlägen angegeben. Es gibt insgesamt 7 Noten. In aufsteigender
 * Reihenfolge: do - re - mi - fa - sol - la - si. Die Notennamen haben
 * numerische Entsprechungen.
 * 
 * do re mi fa sol la si 
 *  0  1  2  3   4  5  6
 * 
 * Speichern Sie die Notennamen in einer geeigneten Datenstruktur und stellen
 * Sie sicher, dass Sie über den numerischen Index auf den Notennamen zugreifen
 * können. Die Namen sollen nicht veränderlich sein und sind außerdem für alle
 * Instanzen der Klasse Note gleich: Wählen Sie geeignete Modifier, um diese
 * zwei Forderungen zu erfüllen.
 */
public class Note {

	// FILL IN

	/**
	 * erzeugt eine neue Instanz von Note. beats gibt die Länge der Note an. Die
	 * Höhe der Note wird als numerischer Wert angegeben (noteIndex). Speichern
	 * Sie den Notennamen nicht als String, sondern behalten Sie ihn als
	 * numerischen Wert.
	 * 
	 * @param noteIndex
	 *            Der Index des Notennamens
	 * @param beats
	 *            Die Dauer der Note in Schlägen
	 */
	public Note(int noteIndex, int beats) {
		
		// FILL IN
		
	}

	/**
	 * ist ein Kopierkonstruktor und gibt eine Kopie der übergebene Instanz von
	 * Note zurück.
	 * 
	 * @param note
	 *            Die zu kopierende Note
	 */
	public Note(Note note) {
		
		// FILL IN
		
	}

	/**
	 * gibt die Notenlänge in Schlägen zurück.
	 * 
	 * @return Die Dauer dieser Note in Schlägen
	 */
	public int getBeats() {
		
		// FILL IN
		return -1; // REMOVE THIS
		
	}

	/**
	 * transponiert die Note um den angegebenen Wert nach oben oder nach unten.
	 * Ist die gespeicherte Note beispielsweise do, so lauten diese nach Aufruf
	 * der Methode mit steps=3 fa. Dabei liegt ein zyklischer Abschluss vor: Die
	 * sieben Noten lauten do - re - mi - fa - sol - la - si. Auf die letzte
	 * Note si folgt wieder do.
	 * 
	 * @param steps
	 *            Um wieviele Schritte transponiert werden soll
	 */
	public void transpose(int steps) {
		
		// FILL IN
		
	}

	/**
	 * gibt den Namen der Note gefolgt von einem Leerzeichen und deren Länge in
	 * Schlägen zurück.
	 * 
	 * do 1
	 * 
	 * @return Eine lesbare Repräsentation dieser Note
	 */
	public String toString() {
		
		// FILL IN
		return ""; // REMOVE THIS
		
	}

}
