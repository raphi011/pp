/**
 * ClipString repräsentiert einen String der vorne und hinten um dieselbe
 * Anzahl von Zeichen verkürzt werden kann. Dazu verfügt die Klasse über
 * entsprechende Methoden.
 */
public class ClipString {
    
    private String s;
    
    /**
     * Konstruktor: setzt den String.
     * @param s ClipString wird mit s initialisiert
     */
    public ClipString(String s) {
        this.s = s;
    }
    
    /**
     * Schneidet das erste und das letzten Zeichen des Strings ab.
     */
    public void clip() {
        s = s.substring(1, s.length()-1);
    }
    
    /**
     * Schneidet die n ersten und die n letzten Zeichen des Strings ab.
     */
    public void clip(int n) {
        for (int i = 0; i < n; i++) {
            clip();
        }
    }
    
    /**
     * @return der String
     */
    public String toString() {
        return s;
    }
    
}
