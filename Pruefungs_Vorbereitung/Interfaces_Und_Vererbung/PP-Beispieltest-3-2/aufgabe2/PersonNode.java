/**
 * Ein PersonNode ist ein Knoten in einer verketteten Liste. Er repräsentiert 
 * einen einzelnen Personeneintrag. An einen Knoten kann ein weiterer Knoten 
 * angehängt werden (next), wodurch eine Liste von Personen entsteht. 
 * Jeder Knoten ist der Startknoten der Folge, die aus "seinem" 
 * Personeneintrag besteht, und der Restliste, die durch next referenziert wird. 
 */
public class PersonNode {
	
	private Person p;
	private PersonNode next;
	
	/**
	 * Konstruktor: initialisiert diesen Knoten mit einer Person und der
	 * Referenz zum nächsten Knoten.
	 * 
	 * @param p die zu speichernde Person
	 * @param next die Referenz auf den nächsten Knoten
	 */
	public PersonNode(Person p, PersonNode next) {
		
		this.p = p;
		this.next = next;
		
	}
	
	/**
	 * liefert die Anzahl aller Personen in der verketteten Liste, die nicht 
	 * Studierende sind. Diese Methode kann - muss aber nicht - rekursiv 
	 * implementiert werden.
	 * 
	 * @return die Anzahl aller Personen in der verketteten Liste, die nicht 
	 * Studierende sind
	 */
	public int getNumberOfNonStudents() {
		
		//FILL IN
		return -2; //REMOVE THIS LINE
		
	}
	
	/**
	 * Liefert den Index der letzten Person mit spezifiziertem Namen 
	 * in der verketteten Liste (beginnend mit diesem Knoten als Startknoten 
	 * mit Index 0), d.h. wenn es mehrere Personen mit diesem Namen in der
	 * Liste gibt, wird die entsprechende Position mit dem höchsten Index 
	 * geliefert.
	 * Falls keine Person mit diesem Namen gefunden wird, wird -1 geliefert.
	 *
	 * Diese Methode soll rekursiv implementiert werden.
	 * 
	 * @param name Der Name der gesuchten Person
	 * @return Der größte Index einer Person mit dem Namen in der Liste, 
	 * wobei dieser Knoten der Startknoten der Liste ist. Der Index dieses 
	 * Knotens ist 0.
	 */
	public int lastIndexOf(String name) {
		
		//FILL IN
		return -2; //REMOVE THIS LINE
		
	}
	
}
