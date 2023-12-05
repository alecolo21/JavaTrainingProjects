/**
 *Reti e laboratorio III - A.A. 2022/2023
 *Soluzione del sesto ASSIGNMENT
 *
 */
public class Element implements Comparable<Element> {
    public final int id; // Numero della riga.
    public final String line; // Contenuto della riga.

    public Element(int id, String line) {
        this.id = id;
        this.line = line;
    }

    /**
     * Confronta l'elemento corrente con il parametro.
     *
     * @param o elemento da confrontare con quello corrente
     * @return il valore 0 se <code>this.id == o.id</code>,
     * un valore < 0 se <code>this.id < o.id</code>
     * e un valore > 0 se <code>this.id > o.id</code>.
     */
    @Override
    public int compareTo(Element o) {
        return Integer.compare(this.id, o.id);
    }
}