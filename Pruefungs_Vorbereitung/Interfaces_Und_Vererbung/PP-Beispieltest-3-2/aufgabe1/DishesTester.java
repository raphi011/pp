/**
* Testet die Funktionen der gefragten Klassen. Es können neue
* Testfälle hinzugefügt werden. Der Inhalt dieser Datei wird
* nicht beurteilt.
*/

public class DishesTester {
	
	public static void main (String [] args) {

		//mögliche Testanweisungen
		
		
		Plate wp = new WoodPlate(33.5);
		Plate sp = new SoupPlate(24.2,true);
		Plate mp = new MeatPlate(34.6,false);
		
		PlateStack ps = new PlateStack();
		
		ps.push(sp);
		ps.push(mp);
		ps.push(wp);
		
		System.out.println(ps.toString());		

		Knife k = new Knife(30);
		System.out.println(k.toString());

		/* Ausgabe:
				
		serialNr: 1, diameter: 33.5, depth: 2
		serialNr: 3, diameter: 34.6, depth: 1, washing cycles: 1000, gold rim: false
		serialNr: 2, diameter: 24.2, depth: 3, washing cycles: 1000, gold rim: true
		
		knife length: 30, washing cycles: 700
		
		*/

	}
	
}
