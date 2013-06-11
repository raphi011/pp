/**
 * Diese Klasse repräsentiert einen Studierendeneintrag
 * mit Name, Telefonnummer und Matrikelnummer. Die Datei ist
 * bereits vollständig vorgegeben und wird nicht bewertet.
 */
public class Student extends Person {
	
	private int matrnr;
	
	public Student(String name, String phone, int matrnr) {
		
		super(name, phone);
		this.matrnr = matrnr;
		
	}
	
	public String toString() {
		
		return super.toString() + ", " + String.format("%7d", this.matrnr);
		
	}
	
}
