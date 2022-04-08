package me.khun.datastructure.queue;

import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class StackTest {

    @Test
    public void testPushMethod() {
        var stack = createStack(Integer.class);
        assertNull(stack.push(null));
        assertEquals(1, stack.push(1));
        assertEquals(2, stack.push(2));
        assertArrayEquals(new Integer[]{null, 1, 2}, stack.toArray());
    }

    @Test
    public void testPopMethod() {
        var stack = createStack(Integer.class);
        stack.push(null);
        stack.push(1);
        stack.push(2);

        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
        assertNull(stack.pop());
        assertArrayEquals(new Integer[0], stack.toArray());
    }

    @Test
    public void shouldThrowEmptyStackExceptionWhenPoppingFromEmptyStack() {
        var stack = createStack(Integer.class);
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    public void testPeekMethod() {
        var stack = createStack(Integer.class);

        stack.push(null);
        assertNull(stack.peek());

        stack.push(1);
        assertEquals(1, stack.peek());

        stack.push(2);
        assertEquals(2, stack.peek());
    }

    @Test
    public void shouldThrowEmptyStackExceptionWhenPeekingFromEmptyStack() {
        var stack = createStack(Integer.class);
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    public void testSearchMethod() {
        var stack = createStack(Integer.class);
        stack.push(null);
        stack.push(1);
        stack.push(2);

        assertEquals(-1, stack.search(0));
        assertEquals(-1, stack.search(3));
        assertEquals(-1, stack.search(4));

        assertEquals(1, stack.search(2));
        assertEquals(2, stack.search(1));
        assertEquals(3, stack.search(null));
    }

    @Test
    public void testClearMethod() {
        var stack = createStack(Integer.class);
        stack.clear();
        assertArrayEquals(new Integer[0], stack.toArray());

        stack.push(null);
        stack.push(1);
        stack.push(2);
        assertArrayEquals(new Integer[]{null, 1, 2}, stack.toArray());
        stack.clear();
        assertArrayEquals(new Integer[0], stack.toArray());
    }

    @Test
    public void testSizeMethod() {
        var stack = createStack(Integer.class);

        assertEquals(0, stack.size());

        stack.push(null);
        assertEquals(1, stack.size());

        stack.push(1);
        stack.push(2);
        assertEquals(3, stack.size());
    }

    @Test
    public void testIsEmptyMethod() {
        var stack = createStack(Integer.class);

        assertTrue(stack.isEmpty());

        stack.push(1);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testToStringMethod() {
        var stack = createStack(Integer.class);

        assertEquals("[]", stack.toString());

        stack.push(null);
        stack.push(1);
        stack.push(2);
        assertEquals("[null, 1, 2]", stack.toString());
    }

    @Test
    public void testEqualsMethodForEquals() {
        var stack1 = createStack(Integer.class);

        assertEquals(new DoublyLinkedStack<String>(), stack1);
        assertEquals(new DoublyLinkedStack<Short>(), stack1);
        assertEquals(new DoublyLinkedStack<Long>(), stack1);
        assertEquals(new DoublyLinkedStack<Double>(), stack1);
        assertEquals(stack1, stack1);

        stack1.push(0);
        stack1.push(1);
        stack1.push(2);

        var stack2 = createStack(Integer.class);
        stack2.push(0);
        stack2.push(1);
        stack2.push(2);

        assertEquals(stack1, stack2);
    }

    @Test
    public void testEqualsMethodForNotEquals() {
        var stack1 = createStack(Integer.class);

        var stack2 = createStack(Integer.class);
        stack2.push(null);
        stack2.push(1);

        assertNotEquals(null, stack1);
        assertNotEquals(null, stack2);
        assertNotEquals(stack1, stack2);

        var stack3 = createStack(Short.class);
        stack3.push(null);
        stack3.push((short) 1);

        assertNotEquals(stack2, stack3);
    }

    @Test
    public void testHashCodeMethodForEquals() {
        var stack1 = createStack(Integer.class);

        assertEquals(new DoublyLinkedStack<Integer>().hashCode(), stack1.hashCode());
        assertEquals(new DoublyLinkedStack<String>().hashCode(), stack1.hashCode());
        assertEquals(new DoublyLinkedStack<Short>().hashCode(), stack1.hashCode());
        assertEquals(new DoublyLinkedStack<Long>().hashCode(), stack1.hashCode());
        assertEquals(new DoublyLinkedStack<Double>().hashCode(), stack1.hashCode());

        stack1.push(null);
        stack1.push(1);
        stack1.push(2);

        var stack2 = createStack(Short.class);
        stack2.push(null);
        stack2.push((short) 1);
        stack2.push((short) 2);

        assertEquals(stack1.hashCode(), stack2.hashCode());

        var stack3 = createStack(Long.class);
        stack3.push(null);
        stack3.push(1L);
        stack3.push(2L);
        assertEquals(stack1.hashCode(), stack3.hashCode());
    }

    @Test
    public void testHashCodeMethodForNotEquals() {
        var stack1 = createStack(Integer.class);

        var stack2 = createStack(Integer.class);
        stack2.push(null);
        stack2.push(1);

        assertNotEquals(stack1.hashCode(), stack2.hashCode());

        stack1.push(-1);
        stack1.push(-2);
        stack1.push(-3);

        var stack3 = createStack(Long.class);
        stack3.push(-1L);
        stack3.push(-2L);
        stack3.push(-3L);

        assertNotEquals(stack1.hashCode(), stack3.hashCode());
    }

    private <T> DoublyLinkedStack<T> createStack(Class<T> type) {
        return new DoublyLinkedStack<>();
    }

}
