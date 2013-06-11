/**
 * Testet die Funktion von PersonNode. Der Inhalt dieser Datei wird nicht
 * bewertet.
 */
public class Phonebook {
	
	public static void main (String[] args) {
		
		PersonNode p6 = new PersonNode(new Student("John", "+431436424543", 8797001), null);
		PersonNode p5 = new PersonNode(new Student("Hugo", "+4325424323", 1197111), p6);
		PersonNode p4 = new PersonNode(new Student("Mary", "+39423324323", 1197065), p5);
		PersonNode p3 = new PersonNode(new Person("John", "+43199726542"), p4);
		PersonNode p2 = new PersonNode(new Student("Charlie", "+43134346352", 1197028), p3);
		PersonNode p1 = new PersonNode(new Person("Kate", "+43233428376"), p2);
		
		
		System.out.println(p1.getNumberOfNonStudents());
		// 2
		
		System.out.println(p1.lastIndexOf("Charlie"));
		// 1
		
		System.out.println(p1.lastIndexOf("Hugo"));
		// 4
		
		System.out.println(p1.lastIndexOf("John"));
		// 5
		
		System.out.println(p1.lastIndexOf("Andrea"));
		// -1
		
	}
	
}
