package ch.hslu.AD.SW04.StackImplementation;

/**
 * Übung: Hashbasierte Datenstrukturen, Performance, Thirdparty-Datenstrukturen (D3)
 * Aufgabe: Performance-Vergleich: Stack-Implementationen
 *
 * @author Fabian Gröger
 * @version 20.03.2018
 */
public final class Stack {

    private final Integer stack[]; // the integer stack array

    private int top = 0; // points to the top free(!) element

    /**
     * Constructor that creates a stack with the given size
     *
     * @param size
     */
    public Stack(int size) {
        stack = new Integer[size];
    }

    /**
     * Pushes an element to the top of the stack
     *
     * @param element the element to push
     */
    public void push(Integer element) {
        if (top < stack.length) {
            stack[top] = element;
            top++;
        } else {
            throw new IllegalStateException("Sorry, the stack is full.");
        }
    }

    /**
     * Returns the element on top of the stack
     *
     * @return the element on top
     */
    public Integer pop() {
        if (top > 0) {
            Integer element = stack[top - 1];
            stack[top - 1] = null;
            top--;
            return element;
        } else {
            throw new IllegalStateException("Sorry, the stack is empty.");
        }
    }

    /**
     * Returns the used size of the stack
     *
     * @return used size
     */
    public int getSize() {
        return top;
    }
}
