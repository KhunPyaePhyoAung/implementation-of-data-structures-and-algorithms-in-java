package me.khun.datastructure.queue;

import me.khun.datastructure.adt.IQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MinBinaryHeapPriorityQueueTest extends MinPriorityQueueTest {

    @Test
    public void testToArrayMethod() {
        var queue = createQueue(Integer.class);
        assertArrayEquals(new Integer[0], queue.toArray());

        queue.offer(1);
        queue.offer(4);
        queue.offer(5);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(0);
        assertArrayEquals(new Integer[]{0, 1, 1, 4, 3, 5, 2}, queue.toArray());
    }

    @Test
    public void testToStringMethod() {
        var queue = createQueue(Integer.class);
        assertEquals("[]", queue.toString());

        queue.offer(1);
        queue.offer(4);
        queue.offer(5);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(0);
        assertEquals("[0, 1, 1, 4, 3, 5, 2]", queue.toString());
    }

    @Test
    public void testIteratorRemoveMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(3);
        queue.offer(4);
        queue.offer(2);
        queue.offer(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(1);

        assertArrayEquals(new Integer[]{1, 2, 1, 5, 4, 3, 2}, queue.toArray());

        var iterator = queue.iterator();

        assertEquals(1, iterator.next());
        iterator.remove();

        assertEquals(1, iterator.next());

        assertEquals(2, iterator.next());
        iterator.remove();

        assertEquals(3, iterator.next());

        assertEquals(2, iterator.next());
        iterator.remove();

        assertEquals(4, iterator.next());

        assertEquals(5, iterator.next());
        iterator.remove();

        assertArrayEquals(new Integer[]{1, 3, 4}, queue.toArray());
    }

    @Test
    public void testIteratorIterationOfNotEmptyList() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(4);
        queue.offer(5);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(0);

        var iterator = queue.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(0, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(4, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(5, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorRemoveOddNumbers() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(4);
        queue.offer(5);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(0);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 1) {
                iterator.remove();
            }
        }

        assertArrayEquals(new Integer[]{0, 2, 4}, queue.toArray());
    }

    @Test
    public void testIteratorRemoveEvenNumbers() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(4);
        queue.offer(5);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(0);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }

        assertArrayEquals(new Integer[]{1, 3, 1, 5}, queue.toArray());
    }

    @Test
    public void testIteratorRemoveLessThan() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(4);
        queue.offer(5);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(0);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() < 3) {
                iterator.remove();
            }
        }

        assertArrayEquals(new Integer[]{3, 4, 5}, queue.toArray());
    }

    @Test
    public void testIteratorRemoveGreaterThan() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(4);
        queue.offer(5);
        queue.offer(3);
        queue.offer(1);
        queue.offer(2);
        queue.offer(0);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() > 2) {
                iterator.remove();
            }
        }

        assertArrayEquals(new Integer[]{0, 1, 1, 2}, queue.toArray());
    }

    @Override
    public <T extends Comparable<? super T>> IQueue<T> createQueue(Class<T> type) {
        return new BinaryHeapPriorityQueue<>();
    }
}
