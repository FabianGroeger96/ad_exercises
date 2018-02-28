package ch.hslu.AD.SW02.ArrayStack;

public interface Stack<E> {
    /**
     * Überprüft ob der Stack leer ist
     * @return ob der Stack leer ist
     */
    public boolean isEmpty();

    /**
     * Überprüft ob der Stack voll ist
     * @return ob der Stack voll ist
     */
    public boolean isFull();

    /**
     * Anzahl der Elemente im Stack
     * @return Grösse des stacks
     */
    public int size();

    /**
     * Setzt ein Element oben auf den Stack
     * @param element hinzuzugügendes Element
     * @return ob der Vorgang geklappt hat
     */
    public boolean push(E element) throws StackFullException;

    /**
     * Nimmt das Element an oberster Stelle auf dem Stack und löscht es
     * @return das Element an oberster Stelle
     */
    public E pop();

    /**
     * Nimmt das Element an oberster Stelle auf dem Stack ohne es zu löschen
     * @return das Element an oberster Stelle
     */
    public E peek();
}
