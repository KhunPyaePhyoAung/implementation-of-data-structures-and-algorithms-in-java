package me.khun.datastructure.queue;

import me.khun.datastructure.adt.IQueue;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class LinearQueueTest {

    @Test
    public void testOfferMethod() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(3));
        assertTrue(queue.offer(null));
        assertArrayEquals(new Integer[]{1, 2, 3, null}, queue.toArray());
    }

    @Test
    public void tesAddMethod() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.add(1));
        assertTrue(queue.add(2));
        assertTrue(queue.add(3));
        assertTrue(queue.add(null));
        assertArrayEquals(new Integer[]{1, 2, 3, null}, queue.toArray());
    }

    @Test
    public void shouldPollAndReturnTheFistElement() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(3));
        assertTrue(queue.offer(null));

        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
        assertNull(queue.poll());
        assertArrayEquals(new Integer[0], queue.toArray());
    }

    @Test
    public void shouldReturnNullWhenTheQueueIsEmptyAndPollMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertNull(queue.poll());
    }

    @Test
    public void tesRemoveMethod() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(3));
        assertTrue(queue.offer(null));

        assertEquals(1, queue.remove());
        assertEquals(2, queue.remove());
        assertEquals(3, queue.remove());
        assertNull(queue.remove());
        assertArrayEquals(new Integer[0], queue.toArray());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTheQueueIsEmptyAndRemoveMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertThrows(NoSuchElementException.class, queue::remove);
    }

    @Test
    public void testRemoveObjectMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(null);

        assertFalse(queue.remove(0));
        assertFalse(queue.remove(10));

        assertTrue(queue.remove(1));
        assertTrue(queue.remove(null));
        assertTrue(queue.remove(3));
        assertTrue(queue.remove(2));
        assertTrue(queue.remove(4));

        assertFalse(queue.remove(5));
        assertFalse(queue.remove(1));
        assertFalse(queue.remove(2));
        assertFalse(queue.remove(3));
        assertFalse(queue.remove(null));
    }

    @Test
    public void shouldReturnTheFirstElement() {
        var queue1 = createQueue(Integer.class);
        queue1.offer(null);
        queue1.offer(1);

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(2);

        assertNull(queue1.peek());
        assertEquals(1, queue2.peek());
    }

    @Test
    public void shouldReturnNullWhenTheQueueIsEmptyAndPeekMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertNull(queue.poll());
    }

    @Test
    public void testElementMethod() {
        var queue1 = createQueue(Integer.class);
        queue1.offer(null);

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(2);

        assertNull(queue1.element());
        assertEquals(1, queue2.element());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTheQueueIsEmptyAndElementMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertThrows(NoSuchElementException.class, queue::element);
    }

    @Test
    public void testContainsMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(null);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertTrue(queue.contains(null));
        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertTrue(queue.contains(3));

        assertFalse(queue.contains(-1));
        assertFalse(queue.contains(0));
    }

    @Test
    public void testClearMethod() {
        var queue = createQueue(Integer.class);

        queue.clear();
        assertArrayEquals(new Integer[0], queue.toArray());

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(null);

        assertArrayEquals(new Integer[]{1, 2, 3, null}, queue.toArray());

        queue.clear();

        assertArrayEquals(new Integer[0], queue.toArray());
    }

    @Test
    public void testSizeMethod() {
        var queue = createQueue(Integer.class);

        assertEquals(0, queue.size());

        queue.offer(1);
        assertEquals(1, queue.size());

        queue.offer(2);
        queue.offer(null);
        assertEquals(3, queue.size());
    }

    @Test
    public void testIsEmptyMethod() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.isEmpty());
        queue.offer(1);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testToArrayMethod() {
        var queue = createQueue(Integer.class);
        assertArrayEquals(new Integer[0], queue.toArray());

        queue.offer(null);
        queue.offer(1);
        queue.offer(2);
        assertArrayEquals(new Integer[]{null, 1, 2}, queue.toArray());
    }

    @Test
    public void testToStringMethod() {
        var queue = createQueue(Integer.class);

        assertEquals("[]", queue.toString());

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(null);
        assertEquals("[1, 2, 3, null]", queue.toString());
    }

    @Test
    public void testIteratorIterationOfEmptyList() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorIterationOfNotEmptyList() {
        var queue = createQueue(Integer.class);
        queue.add(1);
        queue.add(2);
        queue.add(3);

        var iterator = queue.iterator();

        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorRemoveAll() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertArrayEquals(new Integer[0], queue.toArray());
    }

    @Test
    public void testIteratorRemoveOddNumbers() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 1) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[]{2, 4, 6}, queue.toArray());
    }

    @Test
    public void testIteratorRemoveEvenNumbers() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[]{1, 3, 5}, queue.toArray());
    }

    @Test
    public void testIteratorRemoveLessThan() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() < 4) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[]{4, 5, 6}, queue.toArray());
    }

    @Test
    public void testIteratorRemoveGreaterThan() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() > 3) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[]{1, 2, 3}, queue.toArray());
    }

    @Test
    public void shouldNotThrowConcurrentModificationExceptionAfterRemoveObjectMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();

        while (iterator.hasNext()) {
            queue.remove(4);
            iterator.next();
        }
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenEmptyListIteratorHasNoNextElement() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenNotEmptyListIteratorHasNoNextElement() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenEmptyListIteratorRemoveMethodIsInvokedBeforeTraversing() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenNotEmptyListIteratorRemoveMethodIsInvokedBeforeTraversing() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        var iterator = queue.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedWithoutCallingNext() {
        var queue1 = createQueue(Integer.class);
        queue1.offer(1);
        queue1.offer(2);
        queue1.offer(3);

        var iterator1 = queue1.iterator();

        assertThrows(IllegalStateException.class, () -> {
            while (iterator1.hasNext()) {
                iterator1.next();
                iterator1.remove();
                iterator1.remove();
            }
        });

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(2);
        queue2.offer(3);

        var iterator2 = queue2.iterator();
        while (iterator2.hasNext()) {
            iterator2.next();
            iterator2.remove();
        }
        assertThrows(IllegalStateException.class, iterator2::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterOfferMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();

        iterator.next();
        iterator.remove();
        queue.offer(4);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterAddMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.add(1);
        queue.add(2);
        queue.add(3);

        var iterator = queue.iterator();

        iterator.next();
        iterator.remove();
        queue.add(4);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterRemoveMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();

        iterator.next();
        iterator.remove();
        queue.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterPollMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();

        iterator.next();
        iterator.remove();
        queue.remove();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterRemoveObjectMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();

        iterator.next();
        iterator.remove();
        queue.remove(1);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterClearMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();

        iterator.next();
        iterator.remove();
        queue.clear();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByOfferMethodForEmptyList() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
        queue.offer(1);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddMethodForEmptyList() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
        queue.add(1);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByOfferMethodForNotEmptyList() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        var iterator = queue.iterator();
        queue.offer(4);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddMethodForNotEmptyList() {
        var queue = createQueue(Integer.class);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        var iterator = queue.iterator();
        queue.add(4);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByPollMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        queue.poll();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByRemoveMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        queue.remove();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByRemoveObjectMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        queue.remove(2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByClearMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        queue.clear();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterOfferMethodIsInvokedForEmptyList() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
        queue.offer(10);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddMethodIsInvokedForEmptyList() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
        queue.add(10);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterOfferMethodIsInvokedForNotEmptyList() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();

        queue.offer(10);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddMethodIsInvokedForNotEmptyList() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();

        queue.add(10);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterPollMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        queue.poll();
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterRemoveMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        queue.remove();
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterRemoveObjectMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        queue.remove(3);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterClearMethodIsInvokedForEmptyList() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
        queue.clear();
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterClearMethodIsInvokedForNotEmptyList() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        var iterator = queue.iterator();
        queue.clear();
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testEqualsMethodForEquals() {
        var queue1 = createQueue(Integer.class);

        assertEquals(queue1, createQueue(Byte.class));
        assertEquals(queue1, createQueue(Short.class));
        assertEquals(queue1, createQueue(Integer.class));
        assertEquals(queue1, createQueue(Long.class));
        assertEquals(queue1, createQueue(Double.class));
        assertEquals(queue1, createQueue(String.class));
        assertEquals(queue1, createQueue(Date.class));

        queue1.offer(-1);
        queue1.offer(0);
        queue1.offer(1);

        var queue2 = createQueue(Integer.class);
        queue2.offer(-1);
        queue2.offer(0);
        queue2.offer(1);

        assertEquals(queue1, queue2);
    }

    @Test
    public void testEqualsMethodForNotEquals() {
        var queue1 = createQueue(Integer.class);

        var queue2 = createQueue(Long.class);
        queue2.offer(-1L);
        queue2.offer(0L);
        queue2.offer(1L);

        var queue3 = createQueue(Integer.class);
        queue3.offer(-1);
        queue3.offer(0);
        queue3.offer(1);

        assertNotEquals(queue1, null);
        assertNotEquals(queue2, null);
        assertNotEquals(queue2, queue1);
        assertNotEquals(queue2, queue3);
    }

    @Test
    public void testHashCodeMethodForEquals() {
        var queue1 = createQueue(Integer.class);

        assertEquals(queue1.hashCode(), createQueue(Byte.class).hashCode());
        assertEquals(queue1.hashCode(), createQueue(Short.class).hashCode());
        assertEquals(queue1.hashCode(), createQueue(Integer.class).hashCode());
        assertEquals(queue1.hashCode(), createQueue(Long.class).hashCode());
        assertEquals(queue1.hashCode(), createQueue(Double.class).hashCode());
        assertEquals(queue1.hashCode(), createQueue(String.class).hashCode());

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(2);
        queue2.offer(3);

        var queue3 = createQueue(Long.class);
        queue3.offer(1L);
        queue3.offer(2L);
        queue3.offer(3L);

        assertEquals(queue2.hashCode(), queue3.hashCode());

        var queue4 = createQueue(Integer.class);
        queue4.offer(-3);
        queue4.offer(-2);
        queue4.offer(-1);

        var queue5 = createQueue(Integer.class);
        queue5.offer(-3);
        queue5.offer(-2);
        queue5.offer(-1);

        assertEquals(queue4.hashCode(), queue5.hashCode());
    }

    @Test
    public void testHashCodeMethodForNotEquals() {
        var queue1 = createQueue(Integer.class);
        queue1.offer(1);
        queue1.offer(2);
        queue1.offer(3);

        var queue2 = createQueue(Integer.class);
        queue2.offer(3);
        queue2.offer(2);
        queue2.offer(1);

        assertNotEquals(queue1.hashCode(), queue2.hashCode());

        var queue3 = createQueue(Integer.class);
        queue3.offer(-1);
        queue3.offer(0);
        queue3.offer(1);

        var queue4 = createQueue(Long.class);
        queue4.offer(-1L);
        queue4.offer(0L);
        queue4.offer(1L);

        assertNotEquals(queue3.hashCode(), queue4.hashCode());
    }

    public abstract <T> IQueue<T> createQueue(Class<T> type);
}
