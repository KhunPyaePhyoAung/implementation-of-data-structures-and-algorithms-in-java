package me.khun.datastructure.queue;

import org.junit.Test;

import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class DoublyLinkedListImplementedQueueTest {

    @Test
    public void test1(){
        Queue<Integer> q1 = new DoublyLinkedListImplementedQueue<>();
        q1.offer(1);
        q1.offer(2);
        q1.add(3);
        q1.add(4);

        Queue<Integer> q2 = new DoublyLinkedListImplementedQueue<>(q1);
        q2.offer(5);
        q2.add(6);

        assertEquals("[1, 2, 3, 4, 5, 6]", q2.toString());
    }
}
