package me.khun.datastructure.queue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoublyLinkedQueueTest {

    @Test
    public void testConstructors(){
        var q1 = new DoublyLinkedQueue<Integer>();
        q1.offer(1);
        q1.offer(2);
        q1.add(3);
        q1.add(4);
        assertEquals("[1, 2, 3, 4]", q1.toString());

        var q2 = new DoublyLinkedQueue<>(q1);
        q2.offer(5);
        q2.add(6);
        assertEquals("[1, 2, 3, 4, 5, 6]", q2.toString());
    }
}
