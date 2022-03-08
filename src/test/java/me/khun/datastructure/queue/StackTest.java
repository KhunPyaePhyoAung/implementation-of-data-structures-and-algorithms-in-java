package me.khun.datastructure.queue;

import org.junit.Test;

import java.util.EmptyStackException;
import static org.junit.Assert.*;

public class StackTest {

    @Test
    public void test1() {
        Stack<Integer> stack1 = new Stack<>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);

        assertEquals("[1, 2, 3]", stack1.toString());
        assertEquals(3, stack1.size());
        assertEquals(3, stack1.search(1));
        assertEquals(2, stack1.search(2));
        assertEquals(1, stack1.search(3));
        assertEquals(-1, stack1.search(4));
        assertEquals(Integer.valueOf(3), stack1.peek());
        assertEquals(Integer.valueOf(3), stack1.pop());
        assertEquals(Integer.valueOf(2), stack1.pop());
        assertEquals(Integer.valueOf(1), stack1.peek());
        assertFalse(stack1.isEmpty());
        assertEquals(Integer.valueOf(1), stack1.pop());
        assertTrue(stack1.isEmpty());
        assertEquals("[]", stack1.toString());
        assertEquals(0, stack1.size());

        assertEquals(Integer.valueOf(10), stack1.push(10));
        assertEquals(Integer.valueOf(20), stack1.push(20));
        assertEquals(Integer.valueOf(30), stack1.push(30));

        assertEquals("[10, 20, 30]", stack1.toString());
        assertEquals(3, stack1.search(10));
        assertEquals(2, stack1.search(20));
        assertEquals(1, stack1.search(30));
        assertEquals(-1, stack1.search(40));

        stack1.clear();

        assertTrue(stack1.isEmpty());
        assertEquals("[]", stack1.toString());

        assertThrows(EmptyStackException.class, stack1::peek);
        assertThrows(EmptyStackException.class, stack1::pop);

    }
}
