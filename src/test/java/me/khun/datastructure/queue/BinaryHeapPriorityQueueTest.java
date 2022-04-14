package me.khun.datastructure.queue;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BinaryHeapPriorityQueueTest {

    @Test
    public void testDefaultConstructor() {
        var queue = new BinaryHeapPriorityQueue<Integer>();

        queue.offer(5);
        queue.offer(3);
        queue.offer(7);
        queue.offer(2);
        queue.offer(6);
        queue.offer(1);
        queue.offer(4);

        assertArrayEquals(new Integer[]{1, 3, 2, 5, 6, 7, 4}, queue.toArray());
    }

    @Test
    public void testConstructorWithInitialCapacity() {
        var queue = new BinaryHeapPriorityQueue<Integer>(10);

        queue.offer(5);
        queue.offer(3);
        queue.offer(7);
        queue.offer(2);
        queue.offer(6);
        queue.offer(1);
        queue.offer(4);

        assertArrayEquals(new Integer[]{1, 3, 2, 5, 6, 7, 4}, queue.toArray());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenPassingInitialCapacityLessThanZeroToConstructors() {
        assertThrows(IllegalArgumentException.class, () -> new BinaryHeapPriorityQueue<Integer>(-1));
        assertThrows(IllegalArgumentException.class, () -> new BinaryHeapPriorityQueue<Integer>(-1, Comparator.naturalOrder()));
    }

    @Test
    public void testConstructorWithNaturalOrderComparator() {
        var queue = new BinaryHeapPriorityQueue<Integer>(Comparator.comparingInt(a -> a));

        queue.offer(5);
        queue.offer(3);
        queue.offer(7);
        queue.offer(2);
        queue.offer(6);
        queue.offer(1);
        queue.offer(4);

        assertArrayEquals(new Integer[]{1, 3, 2, 5, 6, 7, 4}, queue.toArray());
    }

    @Test
    public void testConstructorWithReversedOrderComparator() {
        var queue = new BinaryHeapPriorityQueue<Integer>(Comparator.comparingInt(a -> -a));

        queue.offer(5);
        queue.offer(3);
        queue.offer(7);
        queue.offer(2);
        queue.offer(6);
        queue.offer(1);
        queue.offer(4);

        assertArrayEquals(new Integer[]{7, 6, 5, 2, 3, 1, 4}, queue.toArray());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenPassingNullComparatorToConstructors() {
        assertThrows(NullPointerException.class, () -> new BinaryHeapPriorityQueue<>(null));
        assertThrows(NullPointerException.class, () -> new BinaryHeapPriorityQueue<>(1, null));
    }

    @Test
    public void testConstructorWithInitialCapacityAndNaturalOrderComparator() {
        var queue = new BinaryHeapPriorityQueue<Integer>(0, Comparator.comparingInt(a -> a));

        queue.offer(5);
        queue.offer(3);
        queue.offer(7);
        queue.offer(2);
        queue.offer(6);
        queue.offer(1);
        queue.offer(4);

        assertArrayEquals(new Integer[]{1, 3, 2, 5, 6, 7, 4}, queue.toArray());
    }

    @Test
    public void testConstructorWithInitialCapacityAndReversedOrderComparator() {
        var queue = new BinaryHeapPriorityQueue<Integer>(10, Comparator.comparingInt(a -> -a));

        queue.offer(5);
        queue.offer(3);
        queue.offer(7);
        queue.offer(2);
        queue.offer(6);
        queue.offer(1);
        queue.offer(4);

        assertArrayEquals(new Integer[]{7, 6, 5, 2, 3, 1, 4}, queue.toArray());
    }
}
