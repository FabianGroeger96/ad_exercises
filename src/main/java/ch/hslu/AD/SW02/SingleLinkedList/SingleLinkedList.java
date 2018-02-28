package ch.hslu.AD.SW02.SingleLinkedList;

import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SingleLinkedList<T> implements List<T> {
    private int size = 0;
    private Node head;

    /**
     * Default Konstruktor
     */
    public SingleLinkedList() {
        head = new Node(null);
    }

    /**
     * Abfragen der Grösse der Liste
     * @return Grösse der Liste
     */
    public int size() {
        return this.size;
    }

    /**
     * Hinzufügen eines Elements am Index 0
     * @param element Hinzuzufügendes Element
     * @return ob der Vorgang geklappt hat
     */
    @Override
    public boolean add(final T element) {
        add(0, element);
        return true;
    }

    /**
     * Hinzufügen eines Elements am gewünschten Index
     * @param index Index zum hinzufügen des Elements
     * @param element Hinzuzufügendes Element
     */
    @Override
    public void add(final int index, final T element) {
        int currentIndex = 0;
        Node currentNode = head;

        while(currentNode.hasNext() && currentIndex < index) {
            currentNode = currentNode.next();
            currentIndex++;
        }

        Node node = new Node(element);
        node.link(currentNode.next());
        currentNode.link(node);
        size++;
    }

    /**
     * Hinzufügen einer Collection zur Liste
     * @param collection Collection um zur Liste hinzuzufügen
     * @return ob der Vorgang geklappt hat
     */
    @Override
    public boolean addAll(final Collection<? extends T> collection) {
        for(T element : collection) {
            add(element);
        }

        return true;
    }

    /**
     * Hinzufügen einer Collection zur Liste am gewünschten Index
     * @param index Index zum hinzufügen der Collection
     * @param collection Collection um zur Liste hinzuzufügen
     * @return ob der Vorgang geklappt hat
     */
    @Override
    public boolean addAll(final int index,final Collection<? extends T> collection) {
        int currentIndex = index;
        for(T element : collection) {
            add(currentIndex, element);
            currentIndex++;
            size++;
        }

        return true;
    }

    /**
     * Löschen der Liste
     */
    @Override
    public void clear() {
        size = 0;
        head = null;
    }

    /**
     * Überprüft ob ein Objekt in der Liste vorhanden ist
     * @param object das zu suchende Object
     * @return Ob das Objekt in der Liste vorhanden ist
     */
    @Override
    public boolean contains(Object object) {
        Node currentNode = head;

        while(currentNode.hasNext()) {
            currentNode = currentNode.next();
            if(object.equals(currentNode.getElement())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Überprüft ob eine Collection in der Liste vorhanden ist
     * @param collection die zu suchende Collection
     * @return Ob die Collection in der Liste vorhanden ist
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    /**
     * Gibt den Node zurück am gewünschten Index
     * @param index Index des zurückzugebenen Nodes
     * @return Node an Index
     */
    private Node getNode(int index) {
        if(index < 0 || index >= size()) {
            return null;
        }

        int currentIndex = 0;
        Node current = head;
        while(currentIndex < index && current.hasNext()) {
            current = current.next();
            currentIndex++;
        }
        return current.next();
    }

    /**
     * Gibt den Node an Index 0 zurück und löscht diesen aus der Liste
     * @return Node an Index 0
     */
    public T get() {
        Node node = getNode(0);
        remove(0);
        return node.getElement();
    }

    @Override
    public T get(int index) {
        Node node = getNode(index);
        if(node == null) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size()));
        }
        return node.getElement();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return size == 0 && head == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator(head);
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        return new SingleLinkedListListIterator(null, head, 0, 0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        Node currentNode = head;
        Node previousNode = head;

        while(currentNode.hasNext()) {
            currentNode = currentNode.next();
            if(o.equals(currentNode.getElement())) {
                previousNode.link(currentNode.next());
                size--;
                return true;
            }
            previousNode = currentNode;
        }
        return false;
    }

    @Override
    public T remove(int index) {
        if(index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size()));
        }

        Node currentNode = head;
        Node previousNode = head;
        int currentIndex = 0;
        while(currentNode.hasNext() && currentIndex <= index) {
            previousNode = currentNode;
            currentNode = currentNode.next();
            currentIndex++;
        }
        T element = currentNode.getElement();
        previousNode.link(currentNode.next());
        size--;
        return element;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        Object[] o = new Object[size];
        int currentIndex = 0;
        for(T element : this) {
            o[currentIndex++] = element;
        }
        return o;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    private class Node {
        private T element;
        private Node next;

        public Node(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void link(Node next) {
            this.next = next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Node next() {
            return next;
        }
    }

    private class SingleLinkedListIterator implements Iterator<T> {
        private Node current;

        public SingleLinkedListIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current.hasNext();
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            current = current.next();
            return current.getElement();
        }
    }

    private class SingleLinkedListListIterator implements ListIterator<T> {

        private Node previous;
        private Node current;
        private int previousIndex = 0;
        private int currentIndex = 0;

        public SingleLinkedListListIterator(Node previous, Node current, int previousIndex, int currentIndex) {
            this.previous = previous;
            this.current = current;
            this.previousIndex = previousIndex;
            this.currentIndex = currentIndex;
        }

        @Override
        public void add(T arg0) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return current.hasNext();
        }

        @Override
        public boolean hasPrevious() {
            return previous != null;
        }

        @Override
        public T next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            previous = current;
            previousIndex = currentIndex++;
            current = current.next();
            return current.getElement();
        }

        @Override
        public int nextIndex() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }

            return currentIndex + 1;
        }

        @Override
        public T previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            if(!hasPrevious()) {
                throw new NoSuchElementException();
            }

            return previousIndex;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T arg0) {
            Node node = new Node(arg0);
            node.link(current.next());
            previous.link(node);
            current = node;
        }

    }
}
