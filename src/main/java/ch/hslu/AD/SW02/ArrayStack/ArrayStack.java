package ch.hslu.AD.SW02.ArrayStack;

import java.util.NoSuchElementException;

/**
 * Übung: Arrays, Listen, Stack und Queue (D1)
 * Aufgabe: Implementation eines Stacks mit Hilfe eines Array
 *
 * @author Fabian Gröger
 * @version 07.03.2018
 */
public class ArrayStack<T> implements Stack<T> {

    private int index = 0;
    private T[] stack;

    @SuppressWarnings("unchecked")
    public ArrayStack(final int size) {
        // See http://stackoverflow.com/a/530289/1336014
        this.stack = (T[]) new Object[size];
    }

    @Override
    public boolean isEmpty() {
        if (this.index == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isFull() {
        if (this.index == this.stack.length) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() {
        return this.stack.length;
    }

    @Override
    public boolean push(final T element) throws StackFullException {
        // Wenn der Stack voll ist wird eine Exception erzeugt
        if (isFull()) {
            throw new StackFullException(stack.length);
        }

        stack[this.index++] = element;
        return true;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        T element = stack[--index];
        stack[index] = null; // remove element from stack to allow Garbage Collector to delete object
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }

        return stack[index - 1];
    }
}
