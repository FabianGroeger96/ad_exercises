package ch.hslu.AD.SW01.Ackermann;

/**
 * Übung: Einführung E1
 * Aufgabe: Zum Einstieg
 *
 * @author Fabian Gröger
 * @version 28.02.2018
 */
public class App {

    /**
     * Aufgabe a)
     * ----------
     *
     * Für ack(0, 4)
     * | Rekursion | IN: m | IN: n | m == 0 | n == 0 | OUT: m | OUT: n |
     * | 0         | 0     | 4     | true   | N/A    | 5      | 4      |  <-- Recursion end due to m == 0 -> Result: 5
     *
     *
     * Für ack(1, 2)
     *                | Rekursion | IN: m | IN: n | m == 0 | n == 0 | OUT: m | OUT: n       |
     *                | 0         | 1     | 2     | false  | false  | 0      | ack(1, 1): x |
     * ack(1, 1) ->   | 1         | 1     | 1     | false  | false  | 0      | ack(1, 0): y |
     * ack(1, 0) ->   | 2         | 1     | 0     | false  | true   | 0      | 1            |
     * ack(0, 1) ->   | 3         | 0     | 1     | true   | N/A    | 0      | 2            |
     * call y:        | 4         | 0     | 2     | true   | N/A    | 0      | 3            |
     * call x:        | 5         | 0     | 3     | true   | N/A    | 0      | 4            | <-- Recursion end due to m == 0 -> Result: 4
     *
     * Aufgabe b)
     * ----------
     *
     * Für ack(2, 2)
     *
     * Max frames on stack: 8 when reaching recursion 21
     * Total recursions: 27
     *
     * Aufgabe c)
     * ----------
     *
     * Ist nicht primitiv rekursiv und nicht linear weil die Rekursionsaufrufe verschachtelt sind.
     */

    public static void main( String[] args ) {
        //ack(-2, -2);
    }

    /**
     * Nur für positive n & m aufrufbar,
     * für negative n gibt es einen StackOverflow
     */
    public static long ack(final long n, final long m){
        if (n == 0){
            return m + 1;
        } else {
            if((m == 0) && n > 0){
                return ack(n -1, 1);
            } else if ((m > 0) && (n > 0)){
                return ack(n - 1, ack(n, m-1));
            } else{
                throw new IllegalArgumentException("Ackermann-Funktion kann nur für postive Zahlen aufgerufen werden");
            }
        }
    }
}
