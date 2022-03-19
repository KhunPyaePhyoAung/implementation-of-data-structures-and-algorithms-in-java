package me.khun.datastructure.queue;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {

    @Test
    public void test1() {
        Queue<Integer> q1 = newQueue(Integer.class);

        assertTrue(q1.isEmpty());
        assertEquals(0, q1.size());
        assertEquals("[]", q1.toString());

        q1.add(1);
        q1.add(2);
        q1.add(3);
        q1.offer(4);
        q1.offer(5);

        assertFalse(q1.isEmpty());
        assertEquals(5, q1.size());
        assertEquals("[1, 2, 3, 4, 5]", q1.toString());

        assertEquals(Integer.valueOf(1), q1.element());
        assertEquals(Integer.valueOf(1), q1.peek());
        assertEquals(Integer.valueOf(1), q1.poll());
        assertEquals(Integer.valueOf(2), q1.element());
        assertEquals(Integer.valueOf(2), q1.peek());
        assertEquals(Integer.valueOf(2), q1.poll());

        assertEquals(3, q1.size());
        assertEquals("[3, 4, 5]", q1.toString());
    }

    @Test
    public void testRemoveAndRetain() {
        Queue<String> q1 = newQueue(String.class);
        q1.offer("Java");
        q1.offer("PHP");
        q1.offer("GO");
        q1.offer("Ruby");

        Queue<String> q2 = newQueue(String.class);
        q2.offer("Java");
        q2.offer("Python");
        q2.offer("PHP");
        q2.offer("JavaScript");
        q2.offer("GO");
        q2.offer("Ruby");
        q2.offer("C++");
        q2.offer("C#");

        assertEquals("[Java, Python, PHP, JavaScript, GO, Ruby, C++, C#]", q2.toString());
        assertTrue(q2.removeAll(q1));
        assertEquals("[Python, JavaScript, C++, C#]", q2.toString());

        Queue<String> q3 = newQueue(String.class);
        q3.addAll(q1);
        q3.addAll(q2);

        assertTrue(q3.retainAll(q2));
        assertEquals("[Python, JavaScript, C++, C#]", q3.toString());

        assertEquals("Python", q3.remove());
        assertTrue(q3.remove("JavaScript"));
        assertFalse(q3.remove("Java"));
        assertEquals("[C++, C#]", q3.toString());

    }

    @Test
    public void testContain() {

        Queue<String> q1 = newQueue(String.class);
        q1.offer("Java");
        q1.offer("PHP");
        q1.offer("GO");
        q1.offer("Ruby");

        Queue<String> q2 = newQueue(String.class);
        q2.offer("Java");
        q2.offer("Python");
        q2.offer("PHP");
        q2.offer("JavaScript");
        q2.offer("GO");
        q2.offer("Ruby");
        q2.offer("C++");
        q2.offer("C#");

        assertFalse(q1.containsAll(q2));
        assertTrue(q2.containsAll(q1));
        assertTrue(q1.contains("Java"));
        assertFalse(q1.contains("JavaScript"));
        assertTrue(q2.contains("Java"));
        assertFalse(q2.contains("C"));
    }

    @Test
    public void testIterator() {

        Queue<Integer> q1 = newQueue(Integer.class);
        q1.offer(0);
        q1.offer(1);
        q1.offer(2);
        q1.offer(3);
        q1.offer(100);
        q1.offer(200);
        q1.offer(300);
        q1.offer(0);
        q1.offer(1);
        q1.offer(2);
        q1.offer(3);

        Queue<Integer> q2 = newQueue(Integer.class);

        for (Integer i : q1)
            q2.offer(i);

        assertEquals("[0, 1, 2, 3, 100, 200, 300, 0, 1, 2, 3]", q1.toString());
        assertEquals(q1.toString(), q2.toString());

        assertThrows(
                IllegalStateException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    iterator.remove();
                }
        );

        assertThrows(
                IllegalStateException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                        iterator.remove();
                    }

                }
        );

        assertThrows(
                IllegalStateException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    iterator.next();
                    iterator.remove();
                    iterator.remove();
                }
        );

        assertThrows(
                IllegalStateException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        iterator.remove();
                    }
                    iterator.remove();
                }
        );

        assertThrows(
                IllegalStateException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                    }
                    iterator.remove();
                    iterator.remove();
                }
        );

        assertThrows(
                NoSuchElementException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext())
                        iterator.next();
                    iterator.next();
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        q.add(3);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        q.addAll(q1);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        q.remove(0);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);
                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        q.remove();
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);

                    Queue<Integer> q3 = newQueue(Integer.class);
                    q3.offer(0);
                    q3.offer(1);
                    q3.offer(2);

                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        q.removeAll(q3);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);

                    Queue<Integer> q4 = newQueue(Integer.class);
                    q4.add(100);
                    q4.add(200);
                    q4.add(300);

                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        q.retainAll(q4);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    Queue<Integer> q = newQueue(Integer.class);
                    q.addAll(q1);

                    Iterator<Integer> iterator = q.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        q.clear();
                    }
                }
        );

    }

    @Test
    public void testToArray() {
        Queue<Integer> q1 = newQueue(Integer.class);
        q1.offer(1);
        q1.offer(2);
        q1.offer(3);
        q1.offer(4);

        assertEquals("[1, 2, 3, 4]", Arrays.toString(q1.toArray()));
    }

    @Test
    public void testClear() {
        Queue<Integer> q1 = newQueue(Integer.class);
        q1.offer(1);
        q1.offer(2);
        q1.offer(3);
        q1.offer(4);

        q1.clear();

        assertEquals("[]", q1.toString());
    }

    @Test
    public void testExceptions() {
        Queue<Integer> q1 = newQueue(Integer.class);

        assertNull(q1.poll());
        assertThrows(NoSuchElementException.class, q1::remove);

        assertNull(q1.peek());
        assertThrows(NoSuchElementException.class, q1::element);
    }

    private <T> Queue<T> newQueue(Class<T> type) {
        return new DoublyLinkedListImplementedQueue<>();
    }
}
