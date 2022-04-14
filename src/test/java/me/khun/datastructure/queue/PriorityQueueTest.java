package me.khun.datastructure.queue;

import me.khun.datastructure.adt.IQueue;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class PriorityQueueTest {

    @Test
    public void testOfferMethod() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.offer(1));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(3));
        assertTrue(queue.offer(2));
        assertTrue(queue.offer(1));
        assertEquals(5, queue.size());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenOfferingNullElement() {
        var queue = createQueue(Integer.class);
        assertThrows(NullPointerException.class, () -> queue.offer(null));
    }

    @Test
    public void tesAddMethod() {
        var queue = createQueue(Integer.class);
        assertTrue(queue.add(1));
        assertTrue(queue.add(2));
        assertTrue(queue.add(3));
        assertTrue(queue.add(2));
        assertTrue(queue.add(1));
        assertEquals(5, queue.size());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenAddingNullElement() {
        var queue = createQueue(Integer.class);
        assertThrows(NullPointerException.class, () -> queue.add(null));
    }

    @Test
    public void shouldReturnNullWhenTheQueueIsEmptyAndPollMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertNull(queue.poll());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTheQueueIsEmptyAndRemoveMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertThrows(NoSuchElementException.class, queue::remove);
    }

    @Test
    public void shouldReturnNullWhenTheQueueIsEmptyAndPeekMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertNull(queue.poll());
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenTheQueueIsEmptyAndElementMethodIsInvoked() {
        var queue = createQueue(Integer.class);
        assertThrows(NoSuchElementException.class, queue::element);
    }

    @Test
    public void testContainsMethod() {
        var queue = createQueue(Integer.class);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertTrue(queue.contains(1));
        assertTrue(queue.contains(2));
        assertTrue(queue.contains(3));

        assertFalse(queue.contains(null));
        assertFalse(queue.contains(-1));
        assertFalse(queue.contains(0));
    }

    @Test
    public void testClearMethod() {
        var queue = createQueue(Integer.class);

        queue.clear();
        assertEquals(0, queue.size());
        assertArrayEquals(new Integer[0], queue.toArray());

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        assertEquals(3, queue.size());

        queue.clear();

        assertEquals(0, queue.size());
        assertArrayEquals(new Integer[0], queue.toArray());
    }

    @Test
    public void testSizeMethod() {
        var queue = createQueue(Integer.class);

        assertEquals(0, queue.size());

        queue.offer(1);
        assertEquals(1, queue.size());

        queue.offer(2);
        queue.offer(3);
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
    public void testIteratorIterationOfEmptyList() {
        var queue = createQueue(Integer.class);
        var iterator = queue.iterator();
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
        queue.poll();
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
        assertEquals(queue1, queue1);

        var queue2 = createQueue(Integer.class);
        queue2.offer(0);
        queue2.offer(1);
        queue2.offer(2);
        assertEquals(queue2, queue2);
    }

    @Test
    public void testEqualsMethodForNotEquals() {
        var queue1 = createQueue(Integer.class);

        assertNotEquals(queue1, createQueue(Byte.class));
        assertNotEquals(queue1, createQueue(Short.class));
        assertNotEquals(queue1, createQueue(Integer.class));
        assertNotEquals(queue1, createQueue(Long.class));
        assertNotEquals(queue1, createQueue(String.class));

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(2);
        queue2.offer(3);

        var queue3 = createQueue(Integer.class);
        queue3.offer(1);
        queue3.offer(2);
        queue3.offer(3);

        assertNotEquals(queue2, queue3);
    }

    @Test
    public void testHashCodeMethodForEquals() {
        var queue1 = createQueue(Integer.class);
        assertEquals(queue1.hashCode(), queue1.hashCode());

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(2);
        queue2.offer(3);
        assertEquals(queue2.hashCode(), queue2.hashCode());
    }

    @Test
    public void testHashCodeMethodForNotEquals() {
        var queue1 = createQueue(Integer.class);

        assertNotEquals(queue1.hashCode(), createQueue(Byte.class).hashCode());
        assertNotEquals(queue1.hashCode(), createQueue(Short.class).hashCode());
        assertNotEquals(queue1.hashCode(), createQueue(Integer.class).hashCode());
        assertNotEquals(queue1.hashCode(), createQueue(Long.class).hashCode());
        assertNotEquals(queue1.hashCode(), createQueue(String.class).hashCode());

        var queue2 = createQueue(Integer.class);
        queue2.offer(1);
        queue2.offer(2);
        queue2.offer(3);

        var queue3 = createQueue(Integer.class);
        queue3.offer(1);
        queue3.offer(2);
        queue3.offer(3);

        assertNotEquals(queue2.hashCode(), queue3.hashCode());
    }

    public abstract <T extends Comparable<? super T>> IQueue<T> createQueue(Class<T> type);
}
