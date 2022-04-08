package me.khun.datastructure.queue;

import me.khun.datastructure.adt.IQueue;
import me.khun.datastructure.list.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class DoublyLinkedDequeTest extends LinearQueueTest{

    @org.junit.jupiter.api.Test
    public void testDefaultConstructor() {
        var queue = new DoublyLinkedDeque<>();
        queue.offer(null);
        queue.offer(1);
        queue.offer(2);
        assertArrayEquals(new Integer[]{null, 1, 2}, queue.toArray());
    }

    @Test
    public void testConstructorWithCollection() {
        var list = new ArrayList<>();
        list.add(null);
        list.add(1);
        list.add(2);

        var queue = new DoublyLinkedDeque<>(list);

        assertArrayEquals(new Integer[]{null, 1, 2}, queue.toArray());
    }

    @Test
    public void testConstructorWithQueue() {
        var queue1 = new DoublyLinkedDeque<>();
        queue1.offer(null);
        queue1.offer(1);
        queue1.offer(2);

        var queue2 = new DoublyLinkedDeque<>(queue1);

        assertArrayEquals(new Integer[]{null, 1, 2}, queue2.toArray());
    }

    @Override
    public <T> IQueue<T> createQueue(Class<T> type) {
        return new DoublyLinkedDeque<>();
    }
}
