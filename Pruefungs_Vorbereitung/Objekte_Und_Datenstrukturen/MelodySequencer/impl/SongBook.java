/**
 * Diese Klasse repräsentiert ein Notenbuch. Ein Notenbuch enthält mehrere
 * Melodien und speichert diese in entsprechender Form. Sie stellt Methoden zur
 * Verfügung um Melodien hinzuzufügen oder um auf gespeicherte Melodien
 * zuzugreifen. Diese Klasse liest weder direkt von System.in ein, noch gibt sie
 * direkt auf System.out aus.
 */
public class SongBook {

	// FILL IN

	/**
	 * erzeugt eine neue Instanz von SongBook.
	 */
	public SongBook() {
		
		// FILL IN
		
	}

	/**
	 * speichert im SongBook unter dem angegebenen Titel eine Melodie (Melody)
	 * und gibt true zurück. Gibt es jedoch im SongBook unter diesem Namen
	 * bereits eine Melodie, so wird die übergebene Melodie nicht hinzugefügt,
	 * die Methode gibt false zurück.
	 * 
	 * @param title
	 *            Der Titel der hinzuzufügenden Melody
	 * @param melody
	 *            Die hinzuzufügende Melody
	 * @return true wenn unter dem angegebenen Namen noch keine Melodie
	 *         gespeichert ist und daher die übergebene Melodie erfolgreich
	 *         gespeichert wurde, false andernfalls.
	 */
	public boolean addMelody(String title, Melody melody) {
		
		// FILL IN
		return false; // REMOVE THIS
		
	}

	/**
	 * gibt die unter dem angegeben Titel gespeicherte Melodie zurück. Existiert
	 * keine Melodie unter diesem Titel, so wird null zurückgegeben.
	 * 
	 * @param title
	 *            Der Titel der zurückzugebenden Melody
	 * @return Die Melody die unter dem title gespeichert ist oder null falls es
	 *         keine gibt
	 */
	public Melody getMelody(String title) {
		
		// FILL IN
		return null; // REMOVE THIS
		
	}

}
