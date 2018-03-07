package ch.hslu.AD.SW02.ArrayStack;

/**
 * Übung: Arrays, Listen, Stack und Queue (D1)
 * Aufgabe: Implementation eines Stacks mit Hilfe eines Array
 *
 * @author Fabian Gröger
 * @version 07.03.2018
 */
public class ArrayStackApp {

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>(3);

        try {
            stack.push("toll");
            stack.push("sind");
            stack.push("Datenstrukturen");
        } catch (StackFullException e) {
            e.printStackTrace();
        }

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
