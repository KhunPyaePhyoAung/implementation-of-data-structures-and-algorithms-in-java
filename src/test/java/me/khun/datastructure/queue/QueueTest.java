package me.khun.datastructure.queue;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QueueTest {

    private Queue<Integer> queue0;
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    private Queue<Integer> queue3;
    private Queue<Integer> queue4;
    private Queue<Integer> queue12;
    private Queue<Integer> queue1212;
    private Queue<Integer> queue13;
    private Queue<Integer> queue23;
    private Queue<Integer> queue123;
    private Queue<Object> objectQueue1;
    private Queue<Object> objectQueue2;

    private static List<Class<? extends Queue>> getQueueClasses() {
        return List.of(
                DoublyLinkedQueue.class,
                DoublyLinkedDeque.class
        );
    }

    private void setup(Class<? extends Queue<?>> q) {
        queue0 = createQueue(q, Integer.class);

        queue1 = createQueue(q, Integer.class);
        queue1.add(-5);
        queue1.add(-4);
        queue1.add(-3);
        queue1.add(-2);
        queue1.add(-1);

        queue2 = createQueue(q, Integer.class);
        queue2.add(0);
        queue2.add(1);
        queue2.add(2);
        queue2.add(3);
        queue2.add(4);
        queue2.add(5);

        queue3 = createQueue(q, Integer.class);
        queue3.add(6);
        queue3.add(7);
        queue3.add(8);
        queue3.add(9);
        queue3.add(10);

        queue4 = createQueue(q, Integer.class);
        queue4.add(11);
        queue4.add(12);
        queue4.add(13);
        queue4.add(14);
        queue4.add(15);

        queue12 = createQueue(q, Integer.class);
        queue12.add(-5);
        queue12.add(-4);
        queue12.add(-3);
        queue12.add(-2);
        queue12.add(-1);
        queue12.add(0);
        queue12.add(1);
        queue12.add(2);
        queue12.add(3);
        queue12.add(4);
        queue12.add(5);

        queue1212 = createQueue(q, Integer.class);
        queue1212.add(-5);
        queue1212.add(-4);
        queue1212.add(-3);
        queue1212.add(-2);
        queue1212.add(-1);
        queue1212.add(0);
        queue1212.add(1);
        queue1212.add(2);
        queue1212.add(3);
        queue1212.add(4);
        queue1212.add(5);
        queue1212.add(-5);
        queue1212.add(-4);
        queue1212.add(-3);
        queue1212.add(-2);
        queue1212.add(-1);
        queue1212.add(0);
        queue1212.add(1);
        queue1212.add(2);
        queue1212.add(3);
        queue1212.add(4);
        queue1212.add(5);

        queue13 = createQueue(q, Integer.class);
        queue13.add(-5);
        queue13.add(-4);
        queue13.add(-3);
        queue13.add(-2);
        queue13.add(-1);
        queue13.add(6);
        queue13.add(7);
        queue13.add(8);
        queue13.add(9);
        queue13.add(10);

        queue23 = createQueue(q, Integer.class);
        queue23.add(0);
        queue23.add(1);
        queue23.add(2);
        queue23.add(3);
        queue23.add(4);
        queue23.add(5);
        queue23.add(6);
        queue23.add(7);
        queue23.add(8);
        queue23.add(9);
        queue23.add(10);

        queue123 = createQueue(q, Integer.class);
        queue123.add(-5);
        queue123.add(-4);
        queue123.add(-3);
        queue123.add(-2);
        queue123.add(-1);
        queue123.add(0);
        queue123.add(1);
        queue123.add(2);
        queue123.add(3);
        queue123.add(4);
        queue123.add(5);
        queue123.add(6);
        queue123.add(7);
        queue123.add(8);
        queue123.add(9);
        queue123.add(10);

        objectQueue1 = createQueue(q, Object.class);
        objectQueue1.add(null);
        objectQueue1.add("String");
        objectQueue1.add(1);
        objectQueue1.add(false);
        objectQueue1.add(8);
        objectQueue1.add(true);
        objectQueue1.add(15);
        objectQueue1.add(1.5);
        objectQueue1.add(null);

        objectQueue2 = createQueue(q, Object.class);
        objectQueue2.add(0);
        objectQueue2.add(true);
        objectQueue2.add(1);
        objectQueue2.add(false);
        objectQueue2.add(2);
        objectQueue2.add("String");
        objectQueue2.add(3);
        objectQueue2.add(1.2);
        objectQueue2.add(4);
        objectQueue2.add('a');
        objectQueue2.add(5);
    }

    private <Q extends Queue, T> Queue<T> createQueue(Class<Q> c, Class<? extends T> type) {
        if (c == DoublyLinkedQueue.class) {
            return new DoublyLinkedQueue<>();
        } else if (c == DoublyLinkedDeque.class) {
            return new DoublyLinkedDeque<>();
        }

        throw new IllegalArgumentException();
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testOfferMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertTrue(queue0.offer(0));
        assertTrue(queue0.offer(1));
        assertTrue(queue0.offer(2));
        assertTrue(queue0.offer(3));
        assertTrue(queue0.offer(4));
        assertTrue(queue0.offer(5));
        assertEquals("[0, 1, 2, 3, 4, 5]", queue0.toString());

        assertTrue(queue1.offer(0));
        assertTrue(queue1.offer(1));
        assertTrue(queue1.offer(2));
        assertTrue(queue1.offer(3));
        assertTrue(queue1.offer(4));
        assertTrue(queue1.offer(5));
        assertEquals("[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", queue1.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testAddMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertTrue(queue0.add(0));
        assertTrue(queue0.add(1));
        assertTrue(queue0.add(2));
        assertTrue(queue0.add(3));
        assertTrue(queue0.add(4));
        assertTrue(queue0.add(5));
        assertEquals("[0, 1, 2, 3, 4, 5]", queue0.toString());

        assertTrue(queue1.add(0));
        assertTrue(queue1.add(1));
        assertTrue(queue1.add(2));
        assertTrue(queue1.add(3));
        assertTrue(queue1.add(4));
        assertTrue(queue1.add(5));
        assertEquals("[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", queue1.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testAddAllMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertFalse(queue0.addAll(queue0));
        assertEquals("[]", queue0.toString());

        assertTrue(queue0.addAll(queue1));
        assertEquals("[-5, -4, -3, -2, -1]", queue0.toString());

        assertTrue(queue1.addAll(queue2));
        assertEquals("[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", queue1.toString());
        assertEquals(queue12.toString(), queue1.toString());

        assertTrue(queue4.addAll(queue4));
        assertEquals("[11, 12, 13, 14, 15, 11, 12, 13, 14, 15]", queue4.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testPollMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertNull(queue0.poll());
        assertNull(queue0.poll());

        assertEquals(-5, queue1.poll());
        assertEquals(-4, queue1.poll());
        assertEquals(-3, queue1.poll());
        assertEquals(-2, queue1.poll());
        assertEquals(-1, queue1.poll());
        assertNull(queue1.poll());
        assertNull(queue1.poll());

        assertEquals(0, queue2.poll());
        assertEquals(1, queue2.poll());
        assertEquals(2, queue2.poll());
        assertEquals(3, queue2.poll());
        assertEquals(4, queue2.poll());
        assertEquals(5, queue2.poll());
        assertNull(queue2.poll());
        assertNull(queue2.poll());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testRemoveMethod(Class<? extends Queue<?>> c) {
        setup(c);
        assertEquals(-5, queue1.remove());
        assertEquals(-4, queue1.remove());
        assertEquals(-3, queue1.remove());
        assertEquals(-2, queue1.remove());
        assertEquals(-1, queue1.remove());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testRemoveMethodException(Class<? extends Queue<?>> c) {
        setup(c);

        assertThrows(NoSuchElementException.class, () -> queue0.remove());

        assertEquals(-5, queue1.remove());
        assertEquals(-4, queue1.remove());
        assertEquals(-3, queue1.remove());
        assertEquals(-2, queue1.remove());
        assertEquals(-1, queue1.remove());
        assertThrows(NoSuchElementException.class, () -> queue1.remove());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testRemoveObjectMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertFalse(queue0.remove(0));
        assertFalse(queue0.remove(1));
        assertFalse(queue0.remove(2));
        assertFalse(queue0.remove(null));
        assertEquals("[]", queue0.toString());

        assertTrue(queue1.remove(-1));
        assertTrue(queue1.remove(-2));
        assertTrue(queue1.remove(-3));
        assertTrue(queue1.remove(-4));
        assertTrue(queue1.remove(-5));
        assertFalse(queue1.remove(-5));
        assertFalse(queue1.remove(-4));
        assertFalse(queue1.remove(-2));
        assertFalse(queue1.remove(-3));
        assertFalse(queue1.remove(-1));
        assertFalse(queue1.remove(-10));
        assertFalse(queue1.remove(-20));
        assertFalse(queue1.remove(-4));
        assertFalse(queue1.remove(-2));
        assertFalse(queue1.remove(null));
        assertFalse(queue1.remove("String"));
        assertFalse(queue1.remove(1.2));
        assertEquals("[]", queue1.toString());

        assertTrue(queue1212.remove(0));
        assertTrue(queue1212.remove(1));
        assertTrue(queue1212.remove(2));
        assertTrue(queue1212.remove(3));
        assertTrue(queue1212.remove(4));
        assertTrue(queue1212.remove(5));
        assertEquals("[-5, -4, -3, -2, -1, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", queue1212.toString());

        assertTrue(objectQueue1.remove(null));
        assertTrue(objectQueue1.remove(null));
        assertFalse(objectQueue1.remove(null));
        assertEquals("[String, 1, false, 8, true, 15, 1.5]", objectQueue1.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testRemoveAllMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertFalse(queue0.removeAll(queue0));
        assertFalse(queue0.removeAll(queue1));
        assertEquals("[]", queue0.toString());

        assertFalse(queue1.removeAll(queue2));
        assertEquals("[-5, -4, -3, -2, -1]", queue1.toString());

        assertTrue(queue123.removeAll(queue1));
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", queue123.toString());
        assertTrue(queue123.removeAll(queue2));
        assertEquals("[6, 7, 8, 9, 10]", queue123.toString());
        assertTrue(queue123.removeAll(queue3));
        assertEquals("[]", queue123.toString());

        assertFalse(queue1.removeAll(objectQueue1));

        assertTrue(queue2.removeAll(objectQueue2));
        assertEquals("[]", queue2.toString());

        assertTrue(queue3.removeAll(queue3));
        assertEquals("[]", queue3.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testRemoveAllMethodException(Class<? extends Queue<?>> c) {
        setup(c);
        assertThrows(NullPointerException.class, () -> queue0.removeAll(null));
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testElementMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertEquals(-5, queue1.element());
        assertEquals(0, queue2.element());
        assertEquals(6, queue3.element());
        assertEquals(11, queue4.element());

        assertEquals(-5, queue1.element());
        assertEquals(-5, queue1.poll());
        assertEquals(-4, queue1.element());
        assertEquals(-4, queue1.poll());
        assertEquals(-3, queue1.element());
        assertEquals(-3, queue1.poll());
        assertEquals(-2, queue1.element());
        assertEquals(-2, queue1.poll());
        assertEquals(-1, queue1.element());
        assertEquals(-1, queue1.poll());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testElementMethodException(Class<? extends Queue<?>> c) {
        setup(c);

        assertThrows(NoSuchElementException.class, queue0::element);

        assertEquals(-5, queue1.element());
        assertEquals(-5, queue1.poll());
        assertEquals(-4, queue1.element());
        assertEquals(-4, queue1.poll());
        assertEquals(-3, queue1.element());
        assertEquals(-3, queue1.poll());
        assertEquals(-2, queue1.element());
        assertEquals(-2, queue1.poll());
        assertEquals(-1, queue1.element());
        assertEquals(-1, queue1.poll());
        assertThrows(NoSuchElementException.class, queue1::element);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testPeekMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertNull(queue0.peek());
        assertEquals(-5, queue1.peek());
        assertEquals(0, queue2.peek());
        assertEquals(6, queue3.peek());
        assertEquals(11, queue4.peek());

        assertEquals(-5, queue1.peek());
        assertEquals(-5, queue1.poll());
        assertEquals(-4, queue1.peek());
        assertEquals(-4, queue1.poll());
        assertEquals(-3, queue1.peek());
        assertEquals(-3, queue1.poll());
        assertEquals(-2, queue1.peek());
        assertEquals(-2, queue1.poll());
        assertEquals(-1, queue1.peek());
        assertEquals(-1, queue1.poll());
        assertNull(queue1.peek());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testRetainAllMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertFalse(queue0.retainAll(queue0));
        assertEquals("[]", queue0.toString());

        assertFalse(queue0.retainAll(queue1));
        assertEquals("[]", queue0.toString());

        assertFalse(queue1.retainAll(queue12));
        assertEquals("[-5, -4, -3, -2, -1]", queue1.toString());

        assertFalse(queue2.retainAll(queue12));
        assertEquals("[0, 1, 2, 3, 4, 5]", queue2.toString());

        assertTrue(queue123.retainAll(queue12));
        assertEquals("[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", queue123.toString());

        assertTrue(queue123.retainAll(queue2));
        assertEquals("[0, 1, 2, 3, 4, 5]", queue123.toString());

        assertFalse(queue123.retainAll(queue2));
        assertEquals("[0, 1, 2, 3, 4, 5]", queue123.toString());

        assertTrue(queue123.retainAll(queue1));
        assertEquals("[]", queue123.toString());

        assertFalse(queue123.retainAll(queue0));
        assertEquals("[]", queue123.toString());

        assertFalse(queue123.retainAll(queue123));
        assertEquals("[]", queue123.toString());

        assertTrue(queue12.retainAll(queue0));
        assertEquals("[]", queue12.toString());

        assertTrue(queue23.retainAll(queue0));
        assertEquals("[]", queue23.toString());

        assertFalse(queue2.retainAll(objectQueue2));
        assertTrue(queue2.retainAll(objectQueue1));
        assertEquals("[1]", queue2.toString());

        assertTrue(objectQueue1.retainAll(queue1));
        assertEquals("[]", objectQueue1.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testRetainAllMethodException(Class<? extends Queue<?>> c) {
        setup(c);
        assertThrows(NullPointerException.class, () -> queue0.retainAll(null));
        assertThrows(NullPointerException.class, () -> queue1.retainAll(null));
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testContainsMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertFalse(queue0.contains(null));
        assertFalse(queue0.contains(0));
        assertFalse(queue0.contains(1));

        assertFalse(queue1.contains(null));
        assertFalse(queue1.contains(0));
        assertFalse(queue1.contains(1));
        assertFalse(queue1.contains(2));
        assertTrue(queue1.contains(-5));
        assertTrue(queue1.contains(-4));
        assertTrue(queue1.contains(-3));
        assertTrue(queue1.contains(-2));
        assertTrue(queue1.contains(-1));
        assertFalse(queue1.contains(null));
        assertFalse(queue1.contains("String"));
        assertFalse(queue1.contains(1.2));

        assertTrue(objectQueue1.contains(null));
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testContainsAllMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertTrue(queue0.containsAll(queue0));
        assertTrue(queue1.containsAll(queue0));
        assertTrue(queue12.containsAll(queue1));
        assertTrue(queue12.containsAll(queue2));

        assertFalse(queue0.containsAll(queue1));
        assertFalse(queue1.containsAll(queue2));

        assertFalse(objectQueue1.containsAll(queue1));
        assertFalse(objectQueue1.containsAll(queue2));
        assertFalse(objectQueue2.containsAll(queue1));
        assertTrue(objectQueue2.containsAll(queue2));
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testContainsAllMethodException(Class<? extends Queue<?>> c) {
        setup(c);
        assertThrows(NullPointerException.class, () -> queue0.containsAll(null));
        assertThrows(NullPointerException.class, () -> queue1.containsAll(null));
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testSizeMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertEquals(0, queue0.size());
        assertEquals(5, queue1.size());
        assertEquals(6, queue2.size());
        assertEquals(5, queue3.size());
        assertEquals(5, queue4.size());
        assertEquals(11, queue12.size());
        assertEquals(10, queue13.size());
        assertEquals(11, queue23.size());
        assertEquals(16, queue123.size());

        queue123.removeAll(queue12);
        assertEquals(5, queue123.size());

        queue123.removeAll(queue3);
        assertEquals(0, queue123.size());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testClearMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertEquals("[]", queue0.toString());
        queue0.clear();
        assertEquals("[]", queue0.toString());

        assertEquals("[-5, -4, -3, -2, -1]", queue1.toString());
        queue1.clear();
        assertEquals("[]", queue1.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIsEmptyMethod(Class<? extends Queue<?>> c) {
        setup(c);

        assertTrue(queue0.isEmpty());
        assertFalse(queue1.isEmpty());
        assertFalse(queue2.isEmpty());
        assertFalse(queue12.isEmpty());
        assertFalse(queue23.isEmpty());

        queue12.removeAll(queue1);
        queue12.removeAll(queue2);
        assertTrue(queue12.isEmpty());

        queue23.retainAll(queue1);
        assertTrue(queue23.isEmpty());

        queue1.clear();
        assertTrue(queue1.isEmpty());

        queue2.remove(0);
        queue2.remove(1);
        queue2.remove();
        queue2.poll();
        queue2.poll();
        queue2.poll();
        assertTrue(queue2.isEmpty());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testToArrayMethod(Class<? extends Queue<?>> c) {
        setup(c);
        assertArrayEquals(new Integer[0], queue0.toArray());
        assertArrayEquals(new Integer[]{}, queue0.toArray());
        assertArrayEquals(new Integer[]{-5, -4, -3, -2, -1}, queue1.toArray());
        assertArrayEquals(new Integer[]{0, 1, 2, 3, 4, 5}, queue2.toArray());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorIteration(Class<? extends Queue<?>> c) {
        setup(c);

        var list0Iterator = queue0.iterator();
        assertFalse(list0Iterator.hasNext());
        var stringBuilder = new StringBuilder();
        while (list0Iterator.hasNext()) {
            stringBuilder.append(list0Iterator.next());
        }
        assertEquals("", stringBuilder.toString());

        var list1Iterator = queue1.iterator();
        while (list1Iterator.hasNext()) {
            queue0.add(list1Iterator.next());
        }
        assertEquals(queue1, queue0);

        var l1 = createQueue(c, Integer.class);
        var l1Iterator = l1.iterator();
        assertFalse(l1Iterator.hasNext());
        l1.add(10);
        assertFalse(l1Iterator.hasNext());
        l1.remove(10);
        assertFalse(l1Iterator.hasNext());

        var list3Iterator = queue3.iterator();
        assertEquals(6, list3Iterator.next());
        assertEquals(7, list3Iterator.next());
        assertEquals(8, list3Iterator.next());
        assertEquals(9, list3Iterator.next());
        assertEquals(10, list3Iterator.next());

        var list4Iterator = queue4.iterator();
        assertTrue(list4Iterator.hasNext());
        assertTrue(list4Iterator.hasNext());
        assertTrue(list4Iterator.hasNext());
        assertTrue(list4Iterator.hasNext());
        assertTrue(list4Iterator.hasNext());
        assertEquals(11, list4Iterator.next());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorRemove(Class<? extends Queue<?>> c) {
        setup(c);

        var list1Iterator = queue1.iterator();
        while (list1Iterator.hasNext()) {
            list1Iterator.next();
            list1Iterator.remove();
        }
        assertEquals(0, queue1.size());
        assertEquals("[]", queue1.toString());

        var list2Iterator = queue2.iterator();
        while (list2Iterator.hasNext()) {
            if (list2Iterator.next() % 2 == 0) {
                list2Iterator.remove();
            }
        }
        assertEquals("[1, 3, 5]", queue2.toString());

        var list3Iterator = queue3.iterator();
        while (list3Iterator.hasNext()) {
            if (list3Iterator.next() > 8) {
                list3Iterator.remove();
            }
        }
        assertEquals("[6, 7, 8]", queue3.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorWithRemoveMethods(Class<? extends Queue<?>> c) {
        setup(c);

        var list12Iterator = queue12.iterator();
        assertEquals(-5, list12Iterator.next());
        assertEquals(-4, list12Iterator.next());
        queue12.remove(100);
        queue12.remove(200);
        assertEquals(-3, list12Iterator.next());
        assertEquals(-2, list12Iterator.next());

        var list23Iterator = queue23.iterator();
        assertEquals(0, list23Iterator.next());
        assertEquals(1, list23Iterator.next());
        queue23.removeAll(queue1);
        assertEquals(2, list23Iterator.next());
        assertEquals(3, list23Iterator.next());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorWithRetainAllMethod(Class<? extends Queue<?>> c) {
        setup(c);

        var list1Iterator = queue1.iterator();
        assertEquals(-5, list1Iterator.next());
        assertEquals(-4, list1Iterator.next());

        queue1.retainAll(queue12);
        assertEquals(-3, list1Iterator.next());
        assertEquals(-2, list1Iterator.next());
        assertEquals(-1, list1Iterator.next());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorNoSuchElementException(Class<? extends Queue<?>> c) {
        setup(c);

        var list0Iterator = queue0.iterator();
        assertThrows(NoSuchElementException.class, list0Iterator::next);

        var list1Iterator = queue1.iterator();
        while (list1Iterator.hasNext()) {
            list1Iterator.next();
        }
        assertThrows(NoSuchElementException.class, list1Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorIllegalStateException(Class<? extends Queue<?>> c) {
        setup(c);

        var list0Iterator = queue0.iterator();
        assertThrows(IllegalStateException.class, list0Iterator::remove);

        var list1Iterator = queue1.iterator();
        assertTrue(list1Iterator.hasNext());
        assertEquals(-5, list1Iterator.next());
        list1Iterator.remove();
        assertThrows(IllegalStateException.class, list1Iterator::remove);

        var list2Iterator = queue2.iterator();
        while (list2Iterator.hasNext()) {
            list2Iterator.next();
            list2Iterator.remove();
        }
        assertThrows(IllegalStateException.class, list2Iterator::remove);

        var list3Iterator = queue3.iterator();
        assertEquals(6, list3Iterator.next());
        list3Iterator.remove();
        queue3.add(100);
        assertThrows(IllegalStateException.class, list3Iterator::remove);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorConcurrentModificationExceptionForAdding(Class<? extends Queue<?>> c) {
        setup(c);

        var list0Iterator = queue0.iterator();
        queue0.add(10);
        assertThrows(ConcurrentModificationException.class, list0Iterator::next);

        var list1Iterator = queue1.iterator();
        assertTrue(list1Iterator.hasNext());
        queue1.add(10);
        assertThrows(ConcurrentModificationException.class, list1Iterator::next);

        var list2Iterator = queue2.iterator();
        assertTrue(list2Iterator.hasNext());
        assertEquals(0, list2Iterator.next());
        queue2.add(10);
        assertFalse(list2Iterator.hasNext());
        assertThrows(ConcurrentModificationException.class, list2Iterator::next);

        var list3Iterator = queue3.iterator();
        assertTrue(list3Iterator.hasNext());
        queue3.addAll(queue1);
        assertFalse(list3Iterator.hasNext());
        assertThrows(ConcurrentModificationException.class, list3Iterator::next);

        var list4Iterator = queue4.iterator();
        assertTrue(list4Iterator.hasNext());
        assertEquals(11, list4Iterator.next());
        queue4.addAll(queue1);
        assertFalse(list4Iterator.hasNext());
        assertThrows(ConcurrentModificationException.class, list4Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorConcurrentModificationExceptionForRemoving(Class<? extends Queue<?>> c) {
        setup(c);

        var list1Iterator = queue1.iterator();
        queue1.remove(-5);
        assertFalse(list1Iterator.hasNext());
        assertThrows(ConcurrentModificationException.class, list1Iterator::next);

        var list2Iterator = queue2.iterator();
        assertTrue(list2Iterator.hasNext());
        assertEquals(0, list2Iterator.next());
        queue2.remove(0);
        assertFalse(list2Iterator.hasNext());
        assertThrows(ConcurrentModificationException.class, list2Iterator::next);

        var list12Iterator = queue12.iterator();
        assertTrue(list12Iterator.hasNext());
        assertEquals(-5, list12Iterator.next());
        assertTrue(list12Iterator.hasNext());
        assertEquals(-4, list12Iterator.next());
        queue12.removeAll(queue1);
        assertFalse(list12Iterator.hasNext());
        assertThrows(ConcurrentModificationException.class, list12Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorConcurrentModificationExceptionForRetaining(Class<? extends Queue<?>> c) {
        setup(c);

        var list12Iterator = queue12.iterator();
        assertEquals(-5, list12Iterator.next());
        assertEquals(-4, list12Iterator.next());
        assertEquals(-3, list12Iterator.next());
        queue12.retainAll(queue1);
        assertThrows(ConcurrentModificationException.class, list12Iterator::next);

        var list13Iterator = queue13.iterator();
        assertTrue(list13Iterator.hasNext());
        queue13.retainAll(queue1);
        assertFalse(list13Iterator.hasNext());
        assertThrows(ConcurrentModificationException.class, list13Iterator::next);

        var list23Iterator = queue23.iterator();
        assertTrue(list23Iterator.hasNext());
        assertEquals(0, list23Iterator.next());
        assertEquals(1, list23Iterator.next());
        assertEquals(2, list23Iterator.next());
        queue23.retainAll(queue3);
        assertFalse(list23Iterator.hasNext());
        assertThrows(ConcurrentModificationException.class, list23Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testIteratorConcurrentModificationExceptionForClearing(Class<? extends Queue<?>> c) {
        setup(c);

        var list0Iterator = queue0.iterator();
        queue0.clear();
        assertThrows(ConcurrentModificationException.class, list0Iterator::next);

        var list1Iterator = queue1.iterator();
        queue1.clear();
        assertThrows(ConcurrentModificationException.class, list1Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testEqualsMethodForEquals(Class<? extends Queue<?>> c) {
        setup(c);

        assertEquals(createQueue(c, Integer.class), queue0);
        assertEquals(createQueue(c, Short.class), queue0);
        assertEquals(createQueue(c, Long.class), queue0);
        assertEquals(createQueue(c, String.class), queue0);

        queue12.addAll(queue12);
        assertEquals(queue1212, queue12);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testEqualsMethodForNotEquals(Class<? extends Queue<?>> c) {
        setup(c);

        assertNotEquals(null, queue0);
        assertNotEquals(null, queue1);
        assertNotEquals(queue0, queue1);
        assertNotEquals(queue1, queue2);
        assertNotEquals(queue12, queue13);
        assertNotEquals(queue23, queue12);
        assertNotEquals(queue23, queue13);

        var l1 = createQueue(c, Long.class);
        l1.add(0L);
        l1.add(1L);
        l1.add(2L);
        l1.add(3L);
        l1.add(4L);
        l1.add(5L);
        assertEquals(queue2.toString(), l1.toString());
        assertNotEquals(queue2, l1);

        var l2 = createQueue(c, Short.class);
        l2.add((short) 0);
        l2.add((short) 1);
        l2.add((short) 2);
        l2.add((short) 3);
        l2.add((short) 4);
        l2.add((short) 5);
        assertEquals(queue2.toString(), l2.toString());
        assertNotEquals(queue2, l2);
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testHashCodeForEquals(Class<? extends Queue<?>> c) {
        setup(c);

        assertEquals(createQueue(c, Integer.class).hashCode(), queue0.hashCode());
        assertEquals(createQueue(c, Short.class).hashCode(), queue0.hashCode());
        assertEquals(createQueue(c, Long.class).hashCode(), queue0.hashCode());
        assertEquals(createQueue(c, String.class).hashCode(), queue0.hashCode());
        assertEquals(createQueue(c, Double.class).hashCode(), queue0.hashCode());

        var l1 = createQueue(c, Long.class);
        l1.add(0L);
        l1.add(1L);
        l1.add(2L);
        l1.add(3L);
        l1.add(4L);
        l1.add(5L);
        assertEquals(queue2.hashCode(), l1.hashCode());

        var l2 = createQueue(c, Short.class);
        l2.add((short) -5);
        l2.add((short) -4);
        l2.add((short) -3);
        l2.add((short) -2);
        l2.add((short) -1);
        assertEquals(queue1.hashCode(), l2.hashCode());
    }

    @ParameterizedTest
    @MethodSource(value = "getQueueClasses")
    public void testHashCodeForNotEquals(Class<? extends Queue<?>> c) {
        setup(c);

        assertNotEquals(queue0.hashCode(), queue1.hashCode());
        assertNotEquals(queue1.hashCode(), queue2.hashCode());

        queue0.add(-5);
        queue0.add(-4);
        queue0.add(-3);
        queue0.add(-1);
        queue0.add(-2);
        assertNotEquals(queue1.hashCode(), queue0.hashCode());

        var l1 = createQueue(c, String.class);
        l1.add("-5");
        l1.add("-4");
        l1.add("-3");
        l1.add("-2");
        l1.add("-1");
        assertEquals(queue1.toString(), l1.toString());
        assertNotEquals(queue1.hashCode(), l1.hashCode());

        var l2 = createQueue(c, Long.class);
        l2.add(-5L);
        l2.add(-4L);
        l2.add(-3L);
        l2.add(-2L);
        l2.add(-1L);
        assertEquals(queue1.toString(), l2.toString());
        assertNotEquals(queue1.hashCode(), l2.hashCode());
    }
}
