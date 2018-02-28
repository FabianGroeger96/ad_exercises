package ch.hslu.AD.SW02.ArrayStack;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ArrayStackTest {

    /**
     * Test Cases
     */
    @Test
    public void testCreatingStack() {
        ArrayStack<String> stack = new ArrayStack<>(5);
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testAddingElements() throws Exception {
        ArrayStack<String> stack = new ArrayStack<>(5);
        stack.push("Test");
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testFillingStack() throws Exception {
        ArrayStack<String> stack = new ArrayStack<>(1);
        stack.push("Test");
        assertTrue(stack.isFull());
    }

    @Test
    public void testPushAndPop() throws Exception {
        ArrayStack<String> stack = new ArrayStack<>(3);
        stack.push("super");
        stack.push("ist");
        stack.push("AD");

        assertEquals("AD", stack.pop());
        assertEquals("ist", stack.pop());
        assertEquals("super", stack.pop());
    }

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testStackFull() throws StackFullException {
        ArrayStack<String> stack = new ArrayStack<>(1);
        stack.push("Test");

        exception.expect(StackFullException.class);
        stack.push("Auslöser");
    }

    @Test
    public void testPeek() throws StackFullException {
        ArrayStack<String> stack = new ArrayStack<>(1);
        stack.push("Test");
        assertEquals("Test", stack.peek());
        assertFalse(stack.isEmpty());
        assertEquals("Test", stack.pop());
        assertTrue(stack.isEmpty());
    }

}