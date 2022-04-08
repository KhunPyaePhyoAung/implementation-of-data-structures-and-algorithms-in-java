package me.khun.datastructure.list;

import me.khun.datastructure.adt.IList;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ListTest {

    @Test
    public void testAddMethodWithNullElement() {
        var list = createList(Integer.class);
        assertTrue(list.add(null));
        assertArrayEquals(new Integer[]{null}, list.toArray());
    }

    @Test
    public void testAddMethodWithNullElements() {
        var list = createList(Integer.class);
        assertTrue(list.add(null));
        assertTrue(list.add(null));
        assertTrue(list.add(null));
        assertArrayEquals(new Integer[]{null, null, null}, list.toArray());
    }

    @Test
    public void testAddMethodWithNonNullElement() {
        var list = createList(Integer.class);
        assertTrue(list.add(0));
        assertArrayEquals(new Integer[]{0}, list.toArray());
    }

    @Test
    public void testAddMethodWithNonNullElements() {
        var list = createList(Integer.class);
        assertTrue(list.add(0));
        assertTrue(list.add(1));
        assertTrue(list.add(2));
        assertArrayEquals(new Integer[]{0, 1, 2}, list.toArray());
    }

    @Test
    public void testAddMethodWithNullAndNonNullElements() {
        var list = createList(Integer.class);
        assertTrue(list.add(null));
        assertTrue(list.add(-1));
        assertTrue(list.add(0));
        assertTrue(list.add(null));
        assertTrue(list.add(1));
        assertTrue(list.add(null));
        assertArrayEquals(new Integer[]{null, -1, 0, null, 1, null}, list.toArray());
    }

    @Test
    public void testAddElementAtIndexMethod() {
        var list = createList(Integer.class);
        list.add(0, 0);
        list.add(1, 2);
        list.add(2, 4);
        list.add(3, null);
        list.add(0, null);
        list.add(2, 1);
        list.add(4, 3);
        list.add(7, 5);

        assertArrayEquals(new Integer[]{null, 0, 1, 2, 3, 4, null, 5}, list.toArray());
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingIndexLessThanZeroToAddAtIndexMethodOfEmptyList() {
        var list = createList(Integer.class);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 0));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingIndexLessThanZeroToAddAtIndexMethodOfNotEmptyList() {
        var list = createList(Integer.class);
        list.add(0);
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 0));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingIndexGreaterThanSizeToAddAtIndexMethodOfEmptyList() {
        var list = createList(Integer.class);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, 0));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingIndexGreaterThanSizeToAddAtIndexMethodOfNotEmptyList() {
        var list = createList(Integer.class);
        list.add(0);
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, 0));
    }

    @Test
    public void testAddAllMethod() {
        var list1 = createList(Integer.class);
        list1.add(0);
        list1.add(1);
        list1.add(2);

        var list2 = createList(Integer.class);
        list2.addAll(list1);

        var list3 = createList(Integer.class);
        list3.add(-3);
        list3.add(-2);
        list3.add(-1);
        list3.addAll(list2);

        assertArrayEquals(new Integer[]{-3, -2, -1, 0, 1, 2}, list3.toArray());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenPassingNullCollectionToAddAllMethod() {
        var list = createList(Integer.class);
        assertThrows(NullPointerException.class, () -> list.addAll(null));
    }

    @Test
    public void testAddAllAtIndexMethod() {
        var list1 = createList(Integer.class);
        list1.add(-3);
        list1.add(-2);
        list1.add(-1);

        var list2 = createList(Integer.class);
        list2.add(0);
        list2.add(1);
        list2.add(2);

        var list3 = createList(Integer.class);
        list3.add(3);
        list3.add(4);
        list3.add(5);

        var list4 = createList(Integer.class);
        list4.addAll(0, list1);
        list4.addAll(3, list3);
        list4.addAll(3, list2);

        assertArrayEquals(new Integer[]{-3, -2, -1, 0, 1, 2, 3, 4, 5}, list4.toArray());
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingIndexLessThanZeroAndNullCollectionToAddAllAtIndexMethod() {
        var list = createList(Integer.class);
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(-1, null));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingIndexLessThanZeroAndNonNullCollectionToAddAllAtIndexMethod() {
        var list1 = createList(Integer.class);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);

        assertThrows(IndexOutOfBoundsException.class, () -> list1.addAll(-1, list2));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingIndexGreaterThanSizeAndNullCollectionToAddAllAtIndexMethod() {
        var list = createList(Integer.class);
        assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(list.size() + 1, null));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingIndexGreaterThanSizeAndNonNullCollectionToAddAllAtIndexMethod() {
        var list1 = createList(Integer.class);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);

        assertThrows(IndexOutOfBoundsException.class, () -> list1.addAll(list1.size() + 1, list2));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenPassingNullCollectionToAddAllAtIndexMethod() {
        var list = createList(Integer.class);
        assertThrows(NullPointerException.class, () -> list.addAll(0, null));
    }

    @Test
    public void shouldReturnCorrespondingIndexWhenGettingIndexOfExistingValue() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(0, list.indexOf(1));
        assertEquals(1, list.indexOf(2));
        assertEquals(2, list.indexOf(3));
        assertEquals(6, list.indexOf(4));
    }

    @Test
    public void shouldReturnNegativeOneWhenGettingIndexOfNonExistingValue() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertEquals(-1, list.indexOf(3));
    }

    @Test
    public void shouldReturnCorrespondingIndexWhenGettingLastIndexOfExistingValue() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertEquals(3, list.lastIndexOf(1));
        assertEquals(4, list.lastIndexOf(2));
        assertEquals(5, list.lastIndexOf(3));
        assertEquals(6, list.lastIndexOf(4));
    }

    @Test
    public void shouldReturnNegativeOneWhenGettingLastIndexOfNonExistingValue() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertEquals(-1, list.lastIndexOf(3));
    }

    @Test
    public void shouldReturnTrueWhenTheListContainsTheGivenObject() {
        var list = createList(Integer.class);
        list.add(0);
        list.add(1);
        list.add(2);

        assertTrue(list.contains(0));
        assertTrue(list.contains(1));
        assertTrue(list.contains(2));
    }

    @Test
    public void shouldReturnFalseWhenTheListDoesNotContainTheGivenObject() {
        var list = createList(Integer.class);
        list.add(0);
        list.add(1);
        list.add(2);
        assertFalse(list.contains(3));
    }

    @Test
    public void shouldReturnTrueWhenTheListContainsAllElementsOfAnotherList() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(1);
        list2.add(2);
        list2.add(2);

        assertTrue(list1.containsAll(list2));
        assertTrue(list1.containsAll(list1));
        assertTrue(list2.containsAll(list2));
    }

    @Test
    public void shouldReturnTrueWhenTheEmptyListContainsAllElementsOfAnotherEmptyList() {
        var list1 = createList(Integer.class);
        var list2 = createList(Integer.class);
        assertTrue(list1.containsAll(list2));
    }

    @Test
    public void shouldReturnTrueWhenTheNotEmptyListContainsAllElementsOfTheEmptyList() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);

        assertTrue(list1.containsAll(list2));
    }

    @Test
    public void shouldReturnFalseWhenTheListDoesNotContainAllElementsOfAnotherList() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);

        assertFalse(list1.containsAll(list2));
    }

    @Test
    public void shouldReturnFalseWhenTheEmptyListDoesNotContainAllElementsOfAnotherList() {
        var list1 = createList(Integer.class);
        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        assertFalse(list1.containsAll(list2));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenPassingNullCollectionParameterToContainsAllMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertThrows(NullPointerException.class, () -> list.containsAll(null));
    }

    @Test
    public void shouldReturnCorrespondingValueWhenGettingElementByIndex() {
        var list = createList(Integer.class);
        list.add(null);
        list.add(0);
        list.add(1);
        list.add(2);

        assertNull(list.get(0));
        assertEquals(0, list.get(1));
        assertEquals(1, list.get(2));
        assertEquals(2, list.get(3));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenGettingElementBuyIndexLessThanZero() {
        var list = createList(Integer.class);
        list.add(0);
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenGettingElementBuyIndexGreaterThanOrEqualsSize() {
        var list = createList(Integer.class);
        list.add(0);
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(list.size() + 1));
    }

    @Test
    public void testSetMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(30);

        assertEquals(1, list.set(0, 10));
        assertEquals(2, list.set(1, 20));
        assertEquals(30, list.set(2, 30));
        assertArrayEquals(new Integer[]{10, 20, 30}, list.toArray());
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenSettingTheElementWithNewValueAtIndexLessThanZero() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 10));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenSettingTheElementWithNewValueAtIndexGreaterThanOrEqualsSize() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(list.size(), 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(list.size() + 1, 10));
    }

    @Test
    public void testClearMethod() {
        var list1 = createList(Integer.class);
        list1.clear();
        assertTrue(list1.isEmpty());
        assertArrayEquals(new Integer[0], list1.toArray());

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.clear();
        assertTrue(list2.isEmpty());
        assertArrayEquals(new Integer[0], list2.toArray());
    }

    @Test
    public void testSizeMethod() {
        var list = createList(Integer.class);

        assertEquals(0, list.size());

        list.add(0);
        assertEquals(1, list.size());

        list.add(1);
        list.add(2);
        assertEquals(3, list.size());

    }

    @Test
    public void shouldReturnTrueWhenTheListIsEmpty() {
        var list = createList(Integer.class);
        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldReturnFalseWhenTheListIsNotEmpty() {
        var list = createList(Integer.class);
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testRemoveIndexMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        assertEquals(1, list.remove(0));
        assertEquals(5, list.remove(3));
        assertEquals(3, list.remove(1));
        assertArrayEquals(new Integer[]{2, 4}, list.toArray());

        assertEquals(2, list.remove(0));
        assertEquals(4, list.remove(0));
        assertArrayEquals(new Integer[0], list.toArray());
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenRemovingIndexLessThanZero() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenRemovingIndexGreaterThanOrEqualsSize() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size() + 1));
    }

    @Test
    public void testRemoveObjectMethod() {
        var list = createList(Object.class);
        list.add(null);
        list.add("string");
        list.add(1);
        list.add(1.2);

        assertTrue(list.remove(null));
        assertArrayEquals(new Object[]{"string", 1, 1.2}, list.toArray());

        assertFalse(list.remove(null));
        assertArrayEquals(new Object[]{"string", 1, 1.2}, list.toArray());

        assertFalse(list.remove(false));
        assertArrayEquals(new Object[]{"string", 1, 1.2}, list.toArray());

        assertTrue(list.remove("string"));
        assertArrayEquals(new Object[]{1, 1.2}, list.toArray());

        assertTrue(list.remove(1.2));
        assertArrayEquals(new Object[]{1}, list.toArray());

        assertTrue(list.remove(Integer.valueOf(1)));
        assertArrayEquals(new Object[]{}, list.toArray());

        assertFalse(list.remove(Integer.valueOf(1)));
        assertArrayEquals(new Object[]{}, list.toArray());
    }

    @Test
    public void testRemoveAllMethod() {
        var list1 = createList(Integer.class);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(1);
        list2.add(2);

        var list3 = createList(Integer.class);
        list3.add(1);
        list3.add(2);

        var list4 = createList(Integer.class);
        list4.add(3);
        list4.add(10);
        list4.add(20);

        assertFalse(list1.removeAll(list2));
        assertArrayEquals(new Integer[0], list1.toArray());

        assertTrue(list2.removeAll(list3));
        assertArrayEquals(new Integer[]{3}, list2.toArray());

        assertTrue(list2.removeAll(list4));
        assertArrayEquals(new Integer[0], list2.toArray());

        assertFalse(list3.removeAll(list4));
        assertArrayEquals(new Integer[]{1, 2}, list3.toArray());

        assertTrue(list4.removeAll(list4));
        assertArrayEquals(new Integer[0], list4.toArray());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenPassingNullCollectionToRemoveAllMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertThrows(NullPointerException.class, () -> list.removeAll(null));
    }

    @Test
    public void testRetainAllMethod() {
        var list1 = createList(Integer.class);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(1);
        list2.add(2);

        var list3 = createList(Integer.class);
        list3.add(1);
        list3.add(2);
        list3.add(1);
        list3.add(2);
        list3.add(3);

        var list4 = createList(Integer.class);
        list4.add(1);
        list4.add(2);

        var list5 = createList(Integer.class);
        list5.add(10);
        list5.add(20);

        assertFalse(list1.retainAll(list1));
        assertArrayEquals(new Integer[0], list1.toArray());

        assertFalse(list1.retainAll(list2));
        assertArrayEquals(new Integer[0], list1.toArray());

        assertFalse(list2.retainAll(list3));
        assertArrayEquals(new Integer[]{1, 1, 2}, list2.toArray());

        assertTrue(list3.retainAll(list4));
        assertArrayEquals(new Integer[]{1, 2, 1, 2}, list3.toArray());

        assertFalse(list3.retainAll(list4));
        assertArrayEquals(new Integer[]{1, 2, 1, 2}, list3.toArray());

        assertTrue(list3.retainAll(list5));
        assertArrayEquals(new Integer[0], list3.toArray());

        assertFalse(list4.retainAll(list4));
        assertArrayEquals(new Integer[]{1, 2}, list4.toArray());

        assertTrue(list4.retainAll(list1));
        assertArrayEquals(new Integer[0], list4.toArray());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenPassingNullCollectionToRetainAllMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertThrows(NullPointerException.class, () -> list.retainAll(null));
    }

    @Test
    public void testSubListMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        assertArrayEquals(new Integer[0], list.subList(0, 0).toArray());
        assertArrayEquals(new Integer[0], list.subList(1, 1).toArray());
        assertArrayEquals(new Integer[0], list.subList(2, 2).toArray());
        assertArrayEquals(new Integer[0], list.subList(3, 3).toArray());
        assertArrayEquals(new Integer[0], list.subList(4, 4).toArray());
        assertArrayEquals(new Integer[]{1}, list.subList(0, 1).toArray());
        assertArrayEquals(new Integer[]{1, 2}, list.subList(0, 2).toArray());
        assertArrayEquals(new Integer[]{1, 2, 3}, list.subList(0, 3).toArray());
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, list.subList(0, 4).toArray());
        assertArrayEquals(new Integer[]{2, 3}, list.subList(1, 3).toArray());
        assertArrayEquals(new Integer[]{2, 3, 4}, list.subList(1, 4).toArray());
        assertArrayEquals(new Integer[]{3}, list.subList(2, 3).toArray());
        assertArrayEquals(new Integer[]{3, 4}, list.subList(2, 4).toArray());
        assertArrayEquals(new Integer[]{4}, list.subList(3, 4).toArray());
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingFromIndexLessThanZeroToSubListMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, 1));
    }

    @Test
    public void shouldThrowIndexOutOfBoundsExceptionWhenPassingToIndexGreaterThanSizeToSubListMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> list.subList(0, list.size() + 1));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenPassingFromIndexGreaterThanToIndexToSubListMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        assertThrows(IllegalArgumentException.class, () -> list.subList(2, 1));
    }

    @Test
    public void testToArrayMethod() {
        var list = createList(Integer.class);

        assertArrayEquals(new Integer[0], list.toArray());

        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);
        assertArrayEquals(new Integer[]{1, 2, 1, 2}, list.toArray());
    }

    @Test
    public void testIteratorIterationOfEmptyList() {
        var list = createList(Integer.class);
        var iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorIterationOfNotEmptyList() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

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
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        var iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertArrayEquals(new Integer[0], list.toArray());
    }

    @Test
    public void testIteratorRemoveOddNumbers() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        var iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 1) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[]{2, 4, 6}, list.toArray());
    }

    @Test
    public void testIteratorRemoveEvenNumbers() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        var iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[]{1, 3, 5}, list.toArray());
    }

    @Test
    public void testIteratorRemoveLessThan() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        var iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() < 4) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[]{4, 5, 6}, list.toArray());
    }

    @Test
    public void testIteratorRemoveGreaterThan() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);

        var iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() > 3) {
                iterator.remove();
            }
        }
        assertArrayEquals(new Integer[]{1, 2, 3}, list.toArray());
    }

    @Test
    public void shouldNotThrowConcurrentModificationExceptionAfterRemoveObjectMethodIsInvoked() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

        while (iterator.hasNext()) {
            list.remove(Integer.valueOf(4));
            iterator.next();
        }
    }

    @Test
    public void shouldNotThrowConcurrentModificationExceptionAfterRemoveAllMethodIsInvoked() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();

        while (iterator.hasNext()) {
            list1.removeAll(list2);
            iterator.next();
        }
    }

    @Test
    public void shouldNotThrowConcurrentModificationExceptionAfterRetainAllMethodIsInvoked() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);

        var iterator = list1.iterator();

        while (iterator.hasNext()) {
            list1.retainAll(list2);
            iterator.next();
        }
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenEmptyListIteratorHasNoNextElement() {
        var list = createList(Integer.class);
        var iterator = list.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void shouldThrowNoSuchElementExceptionWhenNotEmptyListIteratorHasNoNextElement() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next();
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenEmptyListIteratorRemoveMethodIsInvokedBeforeTraversing() {
        var list = createList(Integer.class);
        var iterator = list.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenNotEmptyListIteratorRemoveMethodIsInvokedBeforeTraversing() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        var iterator = list.iterator();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedWithoutCallingNext() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var iterator1 = list1.iterator();

        assertThrows(IllegalStateException.class, () -> {
            while (iterator1.hasNext()) {
                iterator1.next();
                iterator1.remove();
                iterator1.remove();
            }
        });

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);

        var iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            iterator2.next();
            iterator2.remove();
        }
        assertThrows(IllegalStateException.class, iterator2::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterAddMethodIsInvoked() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

        iterator.next();
        iterator.remove();
        list.add(4);
       assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterAddAtIndexMethodIsInvoked() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

        iterator.next();
        iterator.remove();
        list.add(2, 4);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterAddAllMethodIsInvoked() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();

        iterator.next();
        iterator.remove();
        list1.addAll(list2);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterAddAllAtIndexMethodIsInvoked() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();

        iterator.next();
        iterator.remove();
        list1.addAll(2, list2);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterRemoveObjectMethodIsInvoked() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

        iterator.next();
        iterator.remove();
        list.remove(Integer.valueOf(1));
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterRemoveIndexMethodIsInvoked() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

        iterator.next();
        iterator.remove();
        list.remove(1);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterRemoveAllMethodIsInvoked() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);

        var iterator = list1.iterator();

        iterator.next();
        iterator.remove();
        list1.removeAll(list2);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterRetainAllMethodIsInvoked() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);

        var iterator = list1.iterator();

        iterator.next();
        iterator.remove();
        list1.retainAll(list2);
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenIteratorRemoveMethodIsInvokedAfterClearMethodIsInvoked() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

        iterator.next();
        iterator.remove();
        list.clear();
        assertThrows(IllegalStateException.class, iterator::remove);
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddMethodForEmptyList() {
        var list = createList(Integer.class);
        var iterator = list.iterator();
        list.add(1);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddMethodForNotEmptyList() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        var iterator = list.iterator();
        list.add(4);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddAtIndexMethodForEmptyList() {
        var list = createList(Integer.class);
        var iterator = list.iterator();
        list.add(0, 4);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddAtIndexMethodForNotEmptyList() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);
        var iterator = list.iterator();
        list.add(3, 4);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddAllMethodForEmptyList() {
        var list1 = createList(Integer.class);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);

        var iterator = list1.iterator();
        list1.addAll(list2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddAllMethodForNotEmptyList() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();
        list1.addAll(list2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddAllAtIndexMethodForEmptyList() {
        var list1 = createList(Integer.class);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);

        var iterator = list1.iterator();
        list1.addAll(0, list2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByAddAllAtIndexMethodForNotEmptyList() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();
        list1.addAll(3, list2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByRemoveObjectMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();
        list.remove(Integer.valueOf(2));
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByRemoveIndexMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();
        list.remove(2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByRemoveAllMethod() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);

        var iterator = list1.iterator();
        list1.removeAll(list2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByRetainAllMethod() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);

        var iterator = list1.iterator();
        list1.retainAll(list2);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldReturnFalseWhenHasNextMethodIsInvokedAfterConcurrentModificationByClearMethod() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();
        list.clear();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddMethodIsInvokedForEmptyList() {
        var list = createList(Integer.class);
        var iterator = list.iterator();
        list.add(10);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddMethodIsInvokedForNotEmptyList() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

        list.add(10);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddAtIndexMethodIsInvokedForEmptyList() {
        var list = createList(Integer.class);

        var iterator = list.iterator();

        list.add(0, 10);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddAtIndexMethodIsInvokedForNotEmptyList() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();

        list.add(3, 10);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddAllMethodIsInvokedForEmptyList() {
        var list1 = createList(Integer.class);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();

        list1.addAll(list2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddAllMethodIsInvokedForNotEmptyList() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();

        list1.addAll(list2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddAllAtIndexMethodIsInvokedForEmptyList() {
        var list1 = createList(Integer.class);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();

        list1.addAll(0, list2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterAddAllAtIndexMethodIsInvokedForNotEmptyList() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(4);
        list2.add(5);
        list2.add(6);

        var iterator = list1.iterator();

        list1.addAll(3, list2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterRemoveObjectMethodIsInvoked() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();
        list.remove(Integer.valueOf(3));
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterRemoveIndexMethodIsInvoked() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();
        list.remove(2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterRemoveAllMethodIsInvoked() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);

        var iterator = list1.iterator();
        list1.removeAll(list2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterRetainAllMethodIsInvoked() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);

        var iterator = list1.iterator();
        list1.retainAll(list2);
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterClearMethodIsInvokedForEmptyList() {
        var list = createList(Integer.class);
        var iterator = list.iterator();
        list.clear();
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void shouldThrowConcurrentModificationExceptionAfterClearMethodIsInvokedForNotEmptyList() {
        var list = createList(Integer.class);
        list.add(1);
        list.add(2);
        list.add(3);

        var iterator = list.iterator();
        list.clear();
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testEqualsMethodForEquals() {
        var list1 = createList(Integer.class);

        assertEquals(list1, createList(Byte.class));
        assertEquals(list1, createList(Short.class));
        assertEquals(list1, createList(Integer.class));
        assertEquals(list1, createList(Long.class));
        assertEquals(list1, createList(Double.class));
        assertEquals(list1, createList(String.class));
        assertEquals(list1, createList(Date.class));

        list1.add(-1);
        list1.add(0);
        list1.add(1);

        var list2 = createList(Integer.class);
        list2.add(-1);
        list2.add(0);
        list2.add(1);

        assertEquals(list1, list2);
    }

    @Test
    public void testEqualsMethodForNotEquals() {
        var list1 = createList(Integer.class);

        var list2 = createList(Long.class);
        list2.add(-1L);
        list2.add(0L);
        list2.add(1L);

        var list3 = createList(Integer.class);
        list3.add(-1);
        list3.add(0);
        list3.add(1);

        assertNotEquals(list1, null);
        assertNotEquals(list2, null);
        assertNotEquals(list2, list1);
        assertNotEquals(list2, list3);
    }

    @Test
    public void testHashCodeMethodForEquals() {
        var list1 = createList(Integer.class);

        assertEquals(list1.hashCode(), createList(Byte.class).hashCode());
        assertEquals(list1.hashCode(), createList(Short.class).hashCode());
        assertEquals(list1.hashCode(), createList(Integer.class).hashCode());
        assertEquals(list1.hashCode(), createList(Long.class).hashCode());
        assertEquals(list1.hashCode(), createList(Double.class).hashCode());
        assertEquals(list1.hashCode(), createList(String.class).hashCode());

        var list2 = createList(Integer.class);
        list2.add(1);
        list2.add(2);
        list2.add(3);

        var list3 = createList(Long.class);
        list3.add(1L);
        list3.add(2L);
        list3.add(3L);

        assertEquals(list2.hashCode(), list3.hashCode());

        var list4 = createList(Integer.class);
        list4.add(-3);
        list4.add(-2);
        list4.add(-1);

        var list5 = createList(Integer.class);
        list5.add(-3);
        list5.add(-2);
        list5.add(-1);

        assertEquals(list4.hashCode(), list5.hashCode());
    }

    @Test
    public void testHashCodeMethodForNotEquals() {
        var list1 = createList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        var list2 = createList(Integer.class);
        list2.add(3);
        list2.add(2);
        list2.add(1);

        assertNotEquals(list1.hashCode(), list2.hashCode());

        var list3 = createList(Integer.class);
        list3.add(-1);
        list3.add(0);
        list3.add(1);

        var list4 = createList(Long.class);
        list4.add(-1L);
        list4.add(0L);
        list4.add(1L);

        assertNotEquals(list3.hashCode(), list4.hashCode());
    }

    @Test
    public void testToStringMethod() {
        var list = createList(Integer.class);
        assertEquals("[]", list.toString());

        list.add(-1);
        list.add(0);
        list.add(1);
        assertEquals("[-1, 0, 1]", list.toString());
    }

    protected abstract <T>IList<T> createList(Class<T> type);

}
