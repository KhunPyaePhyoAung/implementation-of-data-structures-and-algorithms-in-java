package me.khun.datastructure.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    private DoublyLinkedStack<Integer> stack0;
    private DoublyLinkedStack<Integer> stack1;

    @BeforeEach
    public void setupStack() {
        stack0 = new DoublyLinkedStack<>();

        stack1 = new DoublyLinkedStack<>();
        stack1.push(0);
        stack1.push(1);
        stack1.push(2);
        stack1.push(0);
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        stack1.push(4);
        stack1.push(5);
    }

    @Test
    public void testPushMethod() {
        assertEquals(6, stack1.push(6));
        assertEquals(7, stack1.push(7));
        assertEquals(8, stack1.push(8));
        assertEquals(9, stack1.push(9));
        assertEquals(10, stack1.push(10));
        assertEquals("[0, 1, 2, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", stack1.toString());
    }

    @Test
    public void testPopMethod() {
        assertEquals(5, stack1.pop());
        assertEquals(4, stack1.pop());
        assertEquals(3, stack1.pop());
        assertEquals(2, stack1.pop());
        assertEquals(1, stack1.pop());
        assertEquals(0, stack1.pop());
        assertEquals("[0, 1, 2]", stack1.toString());

        assertEquals(2, stack1.pop());
        assertEquals(1, stack1.pop());
        assertEquals(0, stack1.pop());
        assertEquals("[]", stack1.toString());
    }

    @Test
    public void testPopMethodException() {
        assertThrows(EmptyStackException.class, stack0::pop);
    }

    @Test
    public void testPeekMethod() {
        assertEquals(5, stack1.peek());
        assertEquals(5, stack1.pop());
        assertEquals(4, stack1.peek());
        assertEquals(4, stack1.pop());
        assertEquals(3, stack1.peek());
        assertEquals(3, stack1.pop());
        assertEquals(2, stack1.peek());
        assertEquals(2, stack1.pop());
        assertEquals(1, stack1.peek());
        assertEquals(1, stack1.pop());
        assertEquals(0, stack1.peek());
        assertEquals(0, stack1.pop());
        assertEquals(2, stack1.peek());
        assertEquals(2, stack1.pop());
        assertEquals(1, stack1.peek());
        assertEquals(1, stack1.pop());
        assertEquals(0, stack1.peek());
        assertEquals(0, stack1.pop());
    }

    @Test
    public void testPeekMethodException() {
        assertThrows(EmptyStackException.class, stack0::peek);
        testPeekMethod();
        assertThrows(EmptyStackException.class, stack1::peek);
    }

    @Test
    public void testSearchMethod() {
        assertEquals(-1, stack0.search(0));
        assertEquals(-1, stack0.search(1));
        assertEquals(-1, stack0.search(2));

        assertEquals(1, stack1.search(5));
        assertEquals(2, stack1.search(4));
        assertEquals(3, stack1.search(3));
        assertEquals(4, stack1.search(2));
        assertEquals(5, stack1.search(1));
        assertEquals(6, stack1.search(0));
        assertEquals(4, stack1.search(2));
        assertEquals(5, stack1.search(1));
        assertEquals(6, stack1.search(0));
        assertEquals(-1, stack1.search(-1));
        assertEquals(-1, stack1.search(11));
    }

    @Test
    public void testClearMethod() {
        assertEquals("[]", stack0.toString());
        stack0.clear();
        assertEquals("[]", stack0.toString());

        assertEquals("[0, 1, 2, 0, 1, 2, 3, 4, 5]", stack1.toString());
        stack1.clear();
        assertEquals("[]", stack1.toString());
    }

    @Test
    public void testSizeMethod() {
        assertEquals(0, stack0.size());

        stack0.push(1);
        assertEquals(1, stack0.size());

        assertEquals(9, stack1.size());

        stack1.pop();
        assertEquals(8, stack1.size());

        stack1.clear();
        assertEquals(0, stack1.size());
    }

    @Test
    public void testIsEmptyMethod() {
        assertTrue(stack0.isEmpty());

        stack0.push(1);
        assertFalse(stack0.isEmpty());

        assertFalse(stack1.isEmpty());

        stack1.clear();
        assertTrue(stack1.isEmpty());
    }

    @Test
    public void testEqualsMethodForEquals() {
        assertEquals(new DoublyLinkedStack<String>(), stack0);
        assertEquals(new DoublyLinkedStack<Short>(), stack0);
        assertEquals(new DoublyLinkedStack<Long>(), stack0);
        assertEquals(new DoublyLinkedStack<Double>(), stack0);
        assertEquals(stack0, stack0);

        stack0.push(0);
        stack0.push(1);
        stack0.push(2);
        stack0.push(0);
        stack0.push(1);
        stack0.push(2);
        stack0.push(3);
        stack0.push(4);
        stack0.push(5);

        assertEquals(stack0, stack1);
    }

    @Test
    public void testEqualsMethodForNotEquals() {
        assertNotEquals(null, stack0);
        assertNotEquals(null, stack1);
        assertNotEquals(stack0, stack1);

        var s1 = new DoublyLinkedStack<Short>();
        s1.push((short) 0);
        s1.push((short) 1);
        s1.push((short) 2);
        s1.push((short) 0);
        s1.push((short) 1);
        s1.push((short) 2);
        s1.push((short) 3);
        s1.push((short) 4);
        s1.push((short) 5);

        assertNotEquals(s1, stack1);
    }

    @Test
    public void testHashCodeMethodForEquals() {
        assertEquals(new DoublyLinkedStack<Integer>().hashCode(), stack0.hashCode());
        assertEquals(new DoublyLinkedStack<String>().hashCode(), stack0.hashCode());
        assertEquals(new DoublyLinkedStack<Short>().hashCode(), stack0.hashCode());
        assertEquals(new DoublyLinkedStack<Long>().hashCode(), stack0.hashCode());
        assertEquals(new DoublyLinkedStack<Double>().hashCode(), stack0.hashCode());

        var s1 = new DoublyLinkedStack<Short>();
        s1.push((short) 0);
        s1.push((short) 1);
        s1.push((short) 2);
        s1.push((short) 0);
        s1.push((short) 1);
        s1.push((short) 2);
        s1.push((short) 3);
        s1.push((short) 4);
        s1.push((short) 5);

        assertEquals(s1.hashCode(), stack1.hashCode());

        var s2 = new DoublyLinkedStack<Long>();
        s2.push(0L);
        s2.push(1L);
        s2.push(2L);
        s2.push(0L);
        s2.push(1L);
        s2.push(2L);
        s2.push(3L);
        s2.push(4L);
        s2.push(5L);

        assertEquals(s1.hashCode(), stack1.hashCode());
    }

    @Test
    public void testHashCodeMethodForNotEquals() {
        assertNotEquals(stack0.hashCode(), stack1.hashCode());

        stack0.push(0);
        stack0.push(1);
        stack0.push(2);
        stack0.push(3);
        stack0.push(4);
        stack0.push(5);

        assertNotEquals(stack0.hashCode(), stack1.hashCode());

        var s1 = new DoublyLinkedStack<Integer>();
        s1.push(-1);
        s1.push(-2);
        s1.push(-3);
        s1.push(-4);
        s1.push(-5);

        var s2 = new DoublyLinkedStack<Long>();
        s2.push(-1L);
        s2.push(-2L);
        s2.push(-3L);
        s2.push(-4L);
        s2.push(-5L);

        assertNotEquals(s2.hashCode(), stack1.hashCode());
    }
}
