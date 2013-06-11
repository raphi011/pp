import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Die ausführbare Klasse. 
 */
public class Clipper {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<ClipString> list = new ArrayList<ClipString>();

        list.add(new ClipString("This is a test"));
        list.add(new ClipString(null));
        list.add(new ClipString("short"));
        list.add(new ClipString("long enough"));

        Iterator<ClipString> iter = list.iterator();

        //Alle Einträge kürzen...
        while (true) { //CHANGE THIS BLOCK
            try {

                iter.next().clip(3);
                
            } catch (Exception e) { 
                
                System.out.println("Fehler oder Ende der Liste!");
                break;
                
            }

        }

        //... und dann ausgeben
        iter = list.iterator();
        
        while (iter.hasNext()) { 
                
                System.out.println(iter.next());
        
        }

    }
}
