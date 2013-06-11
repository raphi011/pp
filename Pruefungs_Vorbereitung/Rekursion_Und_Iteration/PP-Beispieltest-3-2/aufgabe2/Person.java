/**
 * Diese Klasse repräsentiert einen Personeneintrag
 * mit Name und Telefonnummer. 
 * Die Datei ist bereits vollständig 
 * vorgegeben und wird nicht bewertet.
 */
public class Person {
	
	private final String name;
	private final String phone;
	
	public Person(String name, String phone) {
		
		this.name = name;
		this.phone = phone;
	
	}
	
	public String name() {
	
		return name;
	
	}
	
	public String phone() {
	
		return phone;
	
	}
	
	public String toString() {
	
		return this.name + ": " + this.phone;
	
	}
	
}
