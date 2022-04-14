package me.khun.datastructure.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public abstract class MinPriorityQueueTest extends PriorityQueueTest{

    @Test
    public void shouldReturnTheFirstElementWhenPollMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.offer(4));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(3));
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertEquals(4, queue.poll());
        assertArrayEquals(new Integer[0], queue.toArray());
    }

    @Test
    public void tesRemoveMethod() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.offer(4));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(3));
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));

        assertEquals(1, queue.remove());
        assertEquals(2, queue.remove());
        assertEquals(2, queue.remove());
        assertEquals(3, queue.remove());
        assertEquals(4, queue.remove());
        assertArrayEquals(new Integer[0], queue.toArray());
    }

    @Test
    public void testRemoveObjectMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(2);
        queue.offer(3);

        assertTrue(queue.remove(2));
        assertArrayEquals(new Integer[]{1, 2, 3, 3, 5, 6, 7, 4}, queue.toArray());

        assertTrue(queue.remove(3));
        assertArrayEquals(new Integer[]{1, 2, 4, 3, 5, 6, 7}, queue.toArray());

        assertTrue(queue.remove(2));
        assertArrayEquals(new Integer[]{1, 3, 4, 7, 5, 6}, queue.toArray());

        assertTrue(queue.remove(3));
        assertArrayEquals(new Integer[]{1, 5, 4, 7, 6}, queue.toArray());

        assertFalse(queue.remove(2));
        assertFalse(queue.remove(3));

        assertTrue(queue.remove(1));
        assertArrayEquals(new Integer[]{4, 5, 6, 7}, queue.toArray());

        assertTrue(queue.remove(7));
        assertArrayEquals(new Integer[]{4, 5, 6}, queue.toArray());

        assertTrue(queue.remove(5));
        assertArrayEquals(new Integer[]{4, 6}, queue.toArray());

        assertTrue(queue.remove(4));
        assertArrayEquals(new Integer[]{6}, queue.toArray());

        assertTrue(queue.remove(6));
        assertArrayEquals(new Integer[0], queue.toArray());
    }

    @Test
    public void testPeekMethod() {
        var queue1 = createQueue(Integer.class);
        queue1.offer(2);
        queue1.offer(1);
        queue1.offer(4);
        queue1.offer(3);

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(4);
        queue2.offer(3);
        queue2.offer(2);

        assertEquals(1, queue1.peek());
        assertEquals(1, queue2.peek());
    }

    @Test
    public void testElementMethod() {
        var queue1 = createQueue(Integer.class);
        queue1.offer(2);
        queue1.offer(1);
        queue1.offer(4);
        queue1.offer(3);

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(4);
        queue2.offer(3);
        queue2.offer(2);

        assertEquals(1, queue1.element());
        assertEquals(1, queue2.element());
    }
}
