package me.khun.datastructure.list;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ListTest {

    private List<Integer> list0;
    private List<Integer> list1;
    private List<Integer> list2;
    private List<Integer> list3;
    private List<Integer> list4;
    private List<Integer> list12;
    private List<Integer> list13;
    private List<Integer> list23;
    private List<Object> objectList1;
    private List<Object> objectList2;

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testAddMethods(Class<? extends List<?>> c) {
        setup(c);

        list0.add(1);
        list0.add(0, 0);
        list0.add(3);
        list0.add(3, 4);
        list0.add(2, 2);
        list0.add(5);
        assertEquals("[0, 1, 2, 3, 4, 5]", list0.toString());
        assertEquals(list2.toString(), list0.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testAddMethodException(Class<? extends List<?>> c) {
        setup(c);

        var exception = assertThrows(IndexOutOfBoundsException.class, () -> list0.add(1, 0));
        assertEquals("Index out of bounds : 1", exception.getMessage());

        list0.add(0, 1);
        exception = assertThrows(IndexOutOfBoundsException.class, () -> list0.add(2, 1));
        assertEquals("Index out of bounds : 2", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> list0.add(-1, 1));
        assertEquals("Index out of bounds : -1", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testAddAllMethods(Class<? extends List<?>> c) {
        setup(c);

        list0.addAll(list1);
        list0.addAll(list2);
        assertEquals("[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", list0.toString());
        assertEquals(list12.toString(), list0.toString());

        var l2 = createList(c, Integer.class);
        l2.add(0);
        l2.add(1);
        l2.add(2);
        l2.add(3);
        l2.add(4);
        l2.add(5);
        l2.addAll(list3);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", l2.toString());

        list2.addAll(list2);
        assertEquals("[0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5]", list2.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testAddAllMethodException(Class<? extends List<?>> c) {
        setup(c);

        assertThrows(NullPointerException.class, () -> list0.addAll(null));

        var exception1 = assertThrows(IndexOutOfBoundsException.class, () -> list0.addAll(1, list1));
        assertEquals("Index out of bounds : 1", exception1.getMessage());

        list0.addAll(0, list1);
        var exception2 = assertThrows(IndexOutOfBoundsException.class, () -> list0.addAll(list1.size() + 1, list2));
        assertEquals("Index out of bounds : " + (list1.size() + 1), exception2.getMessage());

        var exception3 = assertThrows(IndexOutOfBoundsException.class, () -> list0.addAll( -1, list2));
        assertEquals("Index out of bounds : -1", exception3.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIndexOfMethods(Class<? extends List<?>> c) {
        setup(c);

        assertEquals(-1, list0.indexOf(0));
        assertEquals(-1, list0.indexOf(1));
        assertEquals(-1, list0.indexOf(2));
        assertEquals(-1, list0.indexOf(3));
        assertEquals(-1, list0.indexOf(4));
        assertEquals(-1, list0.indexOf(5));

        list2.addAll(list2);
        assertEquals(0, list2.indexOf(0));
        assertEquals(1, list2.indexOf(1));
        assertEquals(2, list2.indexOf(2));
        assertEquals(3, list2.indexOf(3));
        assertEquals(4, list2.indexOf(4));
        assertEquals(5, list2.indexOf(5));
        assertEquals(-1, list2.indexOf(6));
        assertEquals(-1, list2.indexOf("String"));

        assertEquals(6, list2.lastIndexOf(0));
        assertEquals(7, list2.lastIndexOf(1));
        assertEquals(8, list2.lastIndexOf(2));
        assertEquals(9, list2.lastIndexOf(3));
        assertEquals(10, list2.lastIndexOf(4));
        assertEquals(11, list2.lastIndexOf(5));
        assertEquals(-1, list2.lastIndexOf(6));
        assertEquals(-1, list2.lastIndexOf("String"));
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testContainsMethod(Class<? extends List<?>> c) {
        setup(c);

        assertTrue(list1.contains(-5));
        assertTrue(list1.contains(-4));
        assertTrue(list1.contains(-3));
        assertTrue(list1.contains(-2));
        assertTrue(list1.contains(-1));
        assertFalse(list1.contains(100));
        assertFalse(list1.contains("String"));

        assertFalse(list1.contains(null));
        list1.add(null);
        assertTrue(list1.contains(null));
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testContainsAllMethod(Class<? extends List<?>> c) {
        setup(c);

        assertFalse(list0.containsAll(list1));
        assertTrue(list1.containsAll(list0));
        assertTrue(list2.containsAll(list0));
        assertTrue(list3.containsAll(list0));

        assertTrue(list12.containsAll(list1));
        assertTrue(list12.containsAll(list2));
        assertTrue(list13.containsAll(list1));
        assertTrue(list13.containsAll(list3));
        assertTrue(list23.containsAll(list2));
        assertTrue(list23.containsAll(list3));
        assertTrue(list4.containsAll(list4));

        assertFalse(list1.containsAll(list4));
        assertFalse(list1.containsAll(list12));
        assertFalse(list1.containsAll(list13));
        assertFalse(list12.containsAll(list4));
        assertFalse(list12.containsAll(list13));

        assertFalse(objectList1.containsAll(list1));
        assertFalse(objectList1.containsAll(list2));
        assertFalse(objectList2.containsAll(list1));
        assertTrue(objectList2.containsAll(list2));
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testContainsAllMethodException(Class<? extends List<?>> c) {
        setup(c);

        assertThrows(NullPointerException.class, () -> list1.containsAll(null));
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testGetMethod(Class<? extends List<?>> c) {
        setup(c);

        assertEquals(-5, list1.get(0));
        assertEquals(-4, list1.get(1));
        assertEquals(-3, list1.get(2));
        assertEquals(-2, list1.get(3));
        assertEquals(-1, list1.get(4));
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testGetMethodException(Class<? extends List<?>> c) {
        setup(c);

        var exception = assertThrows(IndexOutOfBoundsException.class, () -> list0.get(0));
        assertEquals("Index out of bounds : 0", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> list1.get(-1));
        assertEquals("Index out of bounds : -1", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> list1.get(5));
        assertEquals("Index out of bounds : 5", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testSetMethod(Class<? extends List<?>> c) {
        setup(c);

        assertEquals(-5, list1.set(0, 0));
        assertEquals(-4, list1.set(1, 10));
        assertEquals(-3, list1.set(2, 20));
        assertEquals(-2, list1.set(3, 30));
        assertEquals(-1, list1.set(4, 40));
        assertEquals("[0, 10, 20, 30, 40]", list1.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testSetMethodException(Class<? extends List<?>> c) {
        setup(c);

        var exception = assertThrows(IndexOutOfBoundsException.class, () -> list0.set(0, -100));
        assertEquals("Index out of bounds : 0", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> list1.set(-1, -100));
        assertEquals("Index out of bounds : -1", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> list1.set(5, 100));
        assertEquals("Index out of bounds : 5", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testClearMethod(Class<? extends List<?>> c) {
        setup(c);

        list0.clear();
        assertEquals(0, list0.size());
        assertEquals("[]", list0.toString());

        assertEquals("[-5, -4, -3, -2, -1]", list1.toString());
        list1.clear();
        assertEquals(0, list1.size());
        assertEquals("[]", list1.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testSizeMethod(Class<? extends List<?>> c) {
        setup(c);

        assertEquals(0, list0.size());
        assertEquals(5, list1.size());
        assertEquals(6, list2.size());
        assertEquals(11, list12.size());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIsEmptyMethod(Class<? extends List<?>> c) {
        setup(c);

        assertTrue(list0.isEmpty());
        assertFalse(list1.isEmpty());
        assertFalse(list2.isEmpty());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testRemoveIndexMethod(Class<? extends List<?>> c) {
        setup(c);

        assertEquals(-5, list1.remove(0));
        assertEquals("[-4, -3, -2, -1]", list1.toString());

        assertEquals(-4, list1.remove(0));
        assertEquals("[-3, -2, -1]", list1.toString());

        assertEquals(-1, list1.remove(list1.size() - 1));
        assertEquals("[-3, -2]", list1.toString());

        assertEquals(-2, list1.remove(1));
        assertEquals("[-3]", list1.toString());

        assertEquals(-3, list1.remove(0));
        assertEquals("[]", list1.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testRemoveIndexMethodException(Class<? extends List<?>> c) {
        setup(c);

        var exception = assertThrows(IndexOutOfBoundsException.class, () -> list0.remove(0));
        assertEquals("Index out of bounds : 0", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(-1));
        assertEquals("Index out of bounds : -1", exception.getMessage());

        exception = assertThrows(IndexOutOfBoundsException.class, () -> list1.remove(5));
        assertEquals("Index out of bounds : 5", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testRemoveObjectMethod(Class<? extends List<?>> c) {
        setup(c);

        list0.addAll(list2);
        list0.addAll(list3);
        list0.addAll(list2);
        list0.add(null);

        assertTrue(list0.remove(Integer.valueOf(0)));
        assertTrue(list0.remove(Integer.valueOf(1)));
        assertTrue(list0.remove(Integer.valueOf(2)));
        assertTrue(list0.remove(Integer.valueOf(3)));
        assertTrue(list0.remove(Integer.valueOf(4)));
        assertTrue(list0.remove(Integer.valueOf(5)));
        assertTrue(list0.remove(Integer.valueOf(0)));
        assertTrue(list0.remove(null));

        assertFalse(list0.remove(Integer.valueOf(0)));
        assertFalse(list0.remove(Integer.valueOf(-1)));
        assertFalse(list0.remove(Integer.valueOf(-2)));
        assertFalse(list0.remove(Integer.valueOf(100)));
        assertFalse(list0.remove(Integer.valueOf(200)));
        assertFalse(list0.remove(null));

        assertFalse(list0.remove("String"));
        assertFalse(list0.remove(1.2));

        assertEquals("[6, 7, 8, 9, 10, 1, 2, 3, 4, 5]", list0.toString());

        assertTrue(list0.remove(Integer.valueOf(1)));
        assertTrue(list0.remove(Integer.valueOf(2)));
        assertTrue(list0.remove(Integer.valueOf(3)));
        assertTrue(list0.remove(Integer.valueOf(4)));
        assertTrue(list0.remove(Integer.valueOf(5)));
        assertTrue(list0.remove(Integer.valueOf(6)));
        assertTrue(list0.remove(Integer.valueOf(7)));
        assertTrue(list0.remove(Integer.valueOf(8)));
        assertTrue(list0.remove(Integer.valueOf(9)));
        assertTrue(list0.remove(Integer.valueOf(10)));

        assertEquals("[]", list0.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testRemoveAllMethod(Class<? extends List<?>> c) {
        setup(c);

        assertFalse(list0.removeAll(list12));
        assertEquals("[]", list0.toString());

        assertTrue(list12.removeAll(list1));
        assertEquals("[0, 1, 2, 3, 4, 5]", list12.toString());

        assertTrue(list12.removeAll(list2));
        assertEquals("[]", list12.toString());

        assertTrue(list13.removeAll(list1));
        assertEquals("[6, 7, 8, 9, 10]", list3.toString());

        assertTrue(list13.removeAll(list3));
        assertEquals("[]", list13.toString());

        assertTrue(list23.removeAll(list2));
        assertEquals("[6, 7, 8, 9, 10]", list23.toString());

        assertTrue(list23.removeAll(list3));
        assertEquals("[]", list23.toString());

        assertFalse(list12.removeAll(list1));
        assertEquals("[]", list12.toString());

        assertFalse(list12.removeAll(list2));
        assertEquals("[]", list12.toString());

        assertFalse(list13.removeAll(list1));
        assertEquals("[]", list13.toString());

        assertFalse(list13.removeAll(list3));
        assertEquals("[]", list13.toString());

        assertFalse(list23.removeAll(list2));
        assertEquals("[]", list23.toString());

        assertFalse(list23.removeAll(list3));
        assertEquals("[]", list23.toString());

        assertFalse(list4.removeAll(list1));
        assertEquals("[11, 12, 13, 14, 15]", list4.toString());

        assertTrue(list4.removeAll(list4));
        assertEquals("[]", list4.toString());

        assertFalse(list1.removeAll(objectList1));
        assertFalse(list1.removeAll(objectList2));
        assertTrue(list2.removeAll(objectList2));
        assertEquals("[]", list2.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testRemoveAllMethodException(Class<? extends List<?>> c) {
        setup(c);

        assertThrows(NullPointerException.class, () -> list0.removeAll(null));
        assertThrows(NullPointerException.class, () -> list1.removeAll(null));
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testRetainAllMethod(Class<? extends List<?>> c) {
        setup(c);

        assertFalse(list0.retainAll(list12));
        assertEquals("[]", list0.toString());

        assertTrue(list12.retainAll(list1));
        assertEquals("[-5, -4, -3, -2, -1]", list12.toString());

        assertTrue(list12.retainAll(list2));
        assertEquals("[]", list12.toString());

        assertTrue(list13.retainAll(list2));
        assertEquals("[]", list13.toString());

        assertFalse(list2.retainAll(list23));
        assertEquals("[0, 1, 2, 3, 4, 5]", list2.toString());

        assertFalse(list12.retainAll(list1));
        assertEquals("[]", list12.toString());

        assertFalse(list12.retainAll(list13));
        assertEquals("[]", list12.toString());

        assertTrue(list4.retainAll(list12));
        assertEquals("[]", list4.toString());

        var l1 = createList(c, Integer.class);
        l1.addAll(list23);
        l1.addAll(list1);
        l1.addAll(list2);
        l1.addAll(list3);
        assertTrue(l1.retainAll(list2));
        assertEquals("[0, 1, 2, 3, 4, 5, 0, 1, 2, 3, 4, 5]", l1.toString());

        assertTrue(list1.retainAll(objectList1));
        assertEquals("[]", list1.toString());

        assertFalse(list2.retainAll(objectList2));
        assertEquals("[0, 1, 2, 3, 4, 5]", list2.toString());

        assertTrue(list2.retainAll(objectList1));
        assertEquals("[1]", list2.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testRetainAllMethodException(Class<? extends List<?>> c) {
        setup(c);

        assertThrows(NullPointerException.class, () -> list1.retainAll(null));
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorIteration(Class<? extends List<?>> c) {
        setup(c);

        var l1 = createList(c, Integer.class);
        var l1Iterator1 = l1.iterator();

        assertFalse(l1Iterator1.hasNext());

        var list0Iterator = list0.iterator();
        var str = new StringBuilder();
        while (list0Iterator.hasNext())
            str.append(list0Iterator.next());
        assertEquals("", str.toString());

        var list1Iterator = list1.iterator();

        while (list1Iterator.hasNext())
            l1.add(list1Iterator.next());

        assertEquals(list1.toString(), l1.toString());

        var l2 = createList(c, Integer.class);
        var l2Iterator = l2.iterator();
        assertFalse(l2Iterator.hasNext());
        l2.add(10);
        assertTrue(l2Iterator.hasNext());
        l2.remove(Integer.valueOf(10));
        assertFalse(l2Iterator.hasNext());

        var list3Iterator = list3.iterator();
        assertEquals(6, list3Iterator.next());
        assertEquals(7, list3Iterator.next());
        assertEquals(8, list3Iterator.next());
        assertEquals(9, list3Iterator.next());
        assertEquals(10, list3Iterator.next());

        var list4Iterator = list4.iterator();
        list4Iterator.hasNext();
        list4Iterator.hasNext();
        list4Iterator.hasNext();
        list4Iterator.hasNext();
        list4Iterator.hasNext();
        assertEquals(11, list4Iterator.next());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorRemove(Class<? extends List<?>> c) {
        setup(c);

        var list1Iterator = list1.iterator();
        while (list1Iterator.hasNext()) {
            list1Iterator.next();
            list1Iterator.remove();
        }

        assertEquals(0, list1.size());
        assertEquals("[]", list1.toString());

        var list2Iterator = list2.iterator();
        while (list2Iterator.hasNext())
            if (list2Iterator.next() % 2 == 0)
                list2Iterator.remove();
        assertEquals("[1, 3, 5]", list2.toString());

        var list3Iterator = list3.iterator();
        while (list3Iterator.hasNext())
            if (list3Iterator.next() > 8)
                list3Iterator.remove();
        assertEquals("[6, 7, 8]", list3.toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorWithRemoveMethods(Class<? extends List<?>> c) {
        setup(c);

        var list12Iterator = list12.iterator();
        assertEquals(-5, list12Iterator.next());
        assertEquals(-4, list12Iterator.next());
        list12.remove(Integer.valueOf(100));
        list12.remove(Integer.valueOf(200));
        assertEquals(-3, list12Iterator.next());
        assertEquals(-2, list12Iterator.next());

        var list23Iterator = list23.iterator();
        assertEquals(0, list23Iterator.next());
        assertEquals(1, list23Iterator.next());
        list23.removeAll(list1);
        assertEquals(2, list23Iterator.next());
        assertEquals(3, list23Iterator.next());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorWithRetainAllMethod(Class<? extends List<?>> c) {
        setup(c);

        var list1Iterator = list1.iterator();
        assertEquals(-5, list1Iterator.next());
        assertEquals(-4, list1Iterator.next());
        list1.retainAll(list12);
        assertEquals(-3, list1Iterator.next());
        assertEquals(-2, list1Iterator.next());
        assertEquals(-1, list1Iterator.next());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorNoSuchElementException(Class<? extends List<?>> c) {
        setup(c);

        var list0Iterator = list0.iterator();
        assertThrows(NoSuchElementException.class, list0Iterator::next);

        var list1Iterator = list1.iterator();
        while (list1Iterator.hasNext())
            list1Iterator.next();

        assertThrows(NoSuchElementException.class, list1Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorIllegalStateException(Class<? extends List<?>> c) {
        setup(c);

        var list0Iterator = list0.iterator();
        assertThrows(IllegalStateException.class, list0Iterator::remove);

        var list1Iterator = list1.iterator();
        list1Iterator.hasNext();
        list1Iterator.next();
        list1Iterator.remove();
        assertThrows(IllegalStateException.class, list1Iterator::remove);

        var list2Iterator = list2.iterator();
        while (list2Iterator.hasNext()) {
            list2Iterator.next();
            list2Iterator.remove();
        }
        assertThrows(IllegalStateException.class, list2Iterator::remove);

        var list3Iterator = list3.iterator();
        assertEquals(6, list3Iterator.next());
        list3Iterator.remove();
        list3.add(100);
        assertThrows(IllegalStateException.class, list3Iterator::remove);
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorConcurrentModificationExceptionForAdding(Class<? extends List<?>> c) {
        setup(c);

        var list0Iterator = list0.iterator();
        list0.add(10);
        assertThrows(ConcurrentModificationException.class, list0Iterator::next);

        var list1Iterator = list1.iterator();
        list1Iterator.hasNext();
        list1.add(10);
        assertThrows(ConcurrentModificationException.class, list1Iterator::next);

        var list2Iterator = list2.iterator();
        list2Iterator.hasNext();
        list2Iterator.next();
        list2.add(10);
        list2Iterator.hasNext();
        assertThrows(ConcurrentModificationException.class, list2Iterator::next);

        var list3Iterator = list3.iterator();
        list3Iterator.hasNext();
        list3.addAll(list1);
        list3Iterator.hasNext();
        assertThrows(ConcurrentModificationException.class, list3Iterator::next);

        var list4Iterator = list4.iterator();
        list4Iterator.hasNext();
        list4Iterator.next();
        list4.addAll(list1);
        list4Iterator.hasNext();
        assertThrows(ConcurrentModificationException.class, list4Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorConcurrentModificationExceptionForRemoving(Class<? extends List<?>> c) {
        setup(c);

        var list1Iterator = list1.iterator();
        list1.remove(2);
        list1Iterator.hasNext();
        assertThrows(ConcurrentModificationException.class, list1Iterator::next);

        var list2Iterator = list2.iterator();
        list2Iterator.hasNext();
        list2Iterator.next();
        list2.remove(Integer.valueOf(0));
        list2Iterator.hasNext();
        assertThrows(ConcurrentModificationException.class, list2Iterator::next);

        var list12Iterator = list12.iterator();
        list12Iterator.hasNext();
        list12Iterator.next();
        list12Iterator.hasNext();
        list12Iterator.next();
        list12.removeAll(list1);
        list12Iterator.hasNext();
        assertThrows(ConcurrentModificationException.class, list12Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorConcurrentModificationExceptionForRetaining(Class<? extends List<?>> c) {
        setup(c);

        var list12Iterator = list12.iterator();
        assertEquals(-5, list12Iterator.next());
        assertEquals(-4, list12Iterator.next());
        assertEquals(-3, list12Iterator.next());
        list12.retainAll(list1);
        assertThrows(ConcurrentModificationException.class, list12Iterator::next);

        var list13Iterator = list13.iterator();
        list13.retainAll(list1);
        list13Iterator.hasNext();
        assertThrows(ConcurrentModificationException.class, list13Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testIteratorConcurrentModificationExceptionForClearing(Class<? extends List<?>> c) {
        setup(c);

        var list0Iterator = list0.iterator();
        list0.clear();
        assertThrows(ConcurrentModificationException.class, list0Iterator::next);

        var list1Iterator = list1.iterator();
        list1.clear();
        assertThrows(ConcurrentModificationException.class, list1Iterator::next);
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testSubListMethod(Class<? extends List<?>> c) {
        setup(c);

        assertEquals("[]", list0.subList(0,0).toString());
        assertEquals("[]", list1.subList(0, 0).toString());
        assertEquals("[-5]", list1.subList(0, 1).toString());
        assertEquals("[-5, -4]", list1.subList(0, 2).toString());
        assertEquals("[-5, -4, -3]", list1.subList(0, 3).toString());
        assertEquals("[-3, -2]", list1.subList(2, 4).toString());
        assertEquals("[-4, -3]", list1.subList(1, 3).toString());
        assertEquals("[-5, -4, -3, -2, -1]", list1.subList(0, 5).toString());
        assertEquals("[]", list1.subList(1, 1).toString());
        assertEquals("[]", list1.subList(2, 2).toString());
        assertEquals("[]", list1.subList(5, 5).toString());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testSubListMethodException(Class<? extends List<?>> c) {
        setup(c);

        var indexOutOfBoundException = assertThrows(
                IndexOutOfBoundsException.class,
                () -> list0.subList(0, 1));
        assertEquals("toIndex = 1", indexOutOfBoundException.getMessage());

        indexOutOfBoundException = assertThrows(
                IndexOutOfBoundsException.class,
                () -> list1.subList(-1, 0));
        assertEquals("fromIndex = -1", indexOutOfBoundException.getMessage());

        indexOutOfBoundException = assertThrows(
                IndexOutOfBoundsException.class,
                () -> list1.subList(0, 9));
        assertEquals("toIndex = 9", indexOutOfBoundException.getMessage());

        indexOutOfBoundException = assertThrows(
                IndexOutOfBoundsException.class,
                () -> list1.subList(9, 9));
        assertEquals("toIndex = 9", indexOutOfBoundException.getMessage());

        indexOutOfBoundException = assertThrows(
                IndexOutOfBoundsException.class,
                () -> list1.subList(9, 6));
        assertEquals("toIndex = 6", indexOutOfBoundException.getMessage());

        var illegalArgException = assertThrows(
                IllegalArgumentException.class,
                () -> list1.subList(9, 4));
        assertEquals("fromIndex(9) > toIndex(4)", illegalArgException.getMessage());

        illegalArgException = assertThrows(
                IllegalArgumentException.class,
                () -> list1.subList(4, 3));
        assertEquals("fromIndex(4) > toIndex(3)", illegalArgException.getMessage());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testToArrayMethod(Class<? extends List<?>> c) {
        setup(c);

        assertEquals(Arrays.toString(new Integer[0]), Arrays.toString(list0.toArray()));
        assertEquals(Arrays.toString(new Integer[]{}), Arrays.toString(list0.toArray()));
        assertEquals(Arrays.toString(new Integer[]{-5, -4, -3, -2, -1}), Arrays.toString(list1.toArray()));
        assertEquals(Arrays.toString(new Integer[]{0, 1, 2, 3, 4, 5}), Arrays.toString(list2.toArray()));
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testEqualsMethodForEquals(Class<? extends List<?>> c) {
        setup(c);

        assertEquals(createList(c, Integer.class), list0);
        assertEquals(createList(c, Short.class), list0);
        assertEquals(createList(c, Long.class), list0);
        assertEquals(createList(c, String.class), list0);

        list0.addAll(list1);
        assertEquals(list1, list0);

        list0.addAll(list2);
        assertEquals(list12, list0);
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testEqualsMethodForNotEquals(Class<? extends List<?>> c) {
        setup(c);

        assertNotEquals(null, list0);
        assertNotEquals(null, list1);
        assertNotEquals(list0, list1);
        assertNotEquals(list1, list2);
        assertNotEquals(list12, list13);
        assertNotEquals(list23, list12);
        assertNotEquals(list23, list13);

        var l1 = createList(c, Long.class);
        l1.add(0L);
        l1.add(1L);
        l1.add(2L);
        l1.add(3L);
        l1.add(4L);
        l1.add(5L);
        assertEquals(list2.toString(), l1.toString());
        assertNotEquals(list2, l1);

        var l2 = createList(c, Short.class);
        l2.add((short) 0);
        l2.add((short) 1);
        l2.add((short) 2);
        l2.add((short) 3);
        l2.add((short) 4);
        l2.add((short) 5);
        assertEquals(list2.toString(), l2.toString());
        assertNotEquals(list2, l2);
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testHashCodeForEquals(Class<? extends List<?>> c) {
        setup(c);

        assertEquals(createList(c, Integer.class).hashCode(), list0.hashCode());
        assertEquals(createList(c, Short.class).hashCode(), list0.hashCode());
        assertEquals(createList(c, Long.class).hashCode(), list0.hashCode());
        assertEquals(createList(c, String.class).hashCode(), list0.hashCode());
        assertEquals(createList(c, Date.class).hashCode(), list0.hashCode());

        var l1 = createList(c, Long.class);
        l1.add(0L);
        l1.add(1L);
        l1.add(2L);
        l1.add(3L);
        l1.add(4L);
        l1.add(5L);
        assertEquals(list2.hashCode(), l1.hashCode());

        var l2 = createList(c, Short.class);
        l2.add((short) -5);
        l2.add((short) -4);
        l2.add((short) -3);
        l2.add((short) -2);
        l2.add((short) -1);
        assertEquals(list1.hashCode(), l2.hashCode());
    }

    @ParameterizedTest
    @MethodSource(value = "getListClasses")
    public void testHashCodeForNotEquals(Class<? extends List<?>> c) {
        setup(c);

        assertNotEquals(list0.hashCode(), list1.hashCode());
        assertNotEquals(list1.hashCode(), list2.hashCode());

        list0.add(-5);
        list0.add(-4);
        list0.add(-3);
        list0.add(-1);
        list0.add(-2);
        assertNotEquals(list1.hashCode(), list0.hashCode());

        var l1 = createList(c, String.class);
        l1.add("-5");
        l1.add("-4");
        l1.add("-3");
        l1.add("-2");
        l1.add("-1");
        assertEquals(list1.toString(), l1.toString());
        assertNotEquals(list1.hashCode(), l1.hashCode());

        var l2 = createList(c, Long.class);
        l2.add(-5L);
        l2.add(-4L);
        l2.add(-3L);
        l2.add(-2L);
        l2.add(-1L);
        assertEquals(list1.toString(), l2.toString());
        assertNotEquals(list1.hashCode(), l2.hashCode());
    }

    private <L extends List, T> List<T> createList(Class<L> list, Class<? extends T> type) {
        if (list == ArrayList.class) {
            return new ArrayList<>();
        } else if (list == SinglyLinkedList.class) {
            return new SinglyLinkedList<>();
        } else if (list == CircularSinglyLinkedList.class) {
            return new CircularSinglyLinkedList<>();
        } else if (list == DoublyLinkedList.class) {
            return new DoublyLinkedList<>();
        } else if (list == CircularDoublyLinkedList.class) {
            return new CircularDoublyLinkedList<>();
        }

        throw new IllegalArgumentException();
    }

    private static List<Class<? extends List>> getListClasses() {
        return List.of(ArrayList.class,
                        SinglyLinkedList.class,
                        CircularSinglyLinkedList.class,
                        DoublyLinkedList.class,
                        CircularDoublyLinkedList.class
                );
    }

    private <L extends List<?>> void setup(Class<L> c) {
        list0 = createList(c, Integer.class);

        list1 = createList(c, Integer.class);
        list1.add(-5);
        list1.add(-4);
        list1.add(-3);
        list1.add(-2);
        list1.add(-1);

        list2 = createList(c, Integer.class);
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);

        list3 = createList(c, Integer.class);
        list3.add(6);
        list3.add(7);
        list3.add(8);
        list3.add(9);
        list3.add(10);

        list4 = createList(c, Integer.class);
        list4.add(11);
        list4.add(12);
        list4.add(13);
        list4.add(14);
        list4.add(15);

        list12 = createList(c, Integer.class);
        list12.add(-5);
        list12.add(-4);
        list12.add(-3);
        list12.add(-2);
        list12.add(-1);
        list12.add(0);
        list12.add(1);
        list12.add(2);
        list12.add(3);
        list12.add(4);
        list12.add(5);

        list13 = createList(c, Integer.class);
        list13.add(-5);
        list13.add(-4);
        list13.add(-3);
        list13.add(-2);
        list13.add(-1);
        list13.add(6);
        list13.add(7);
        list13.add(8);
        list13.add(9);
        list13.add(10);

        list23 = createList(c, Integer.class);
        list23.add(0);
        list23.add(1);
        list23.add(2);
        list23.add(3);
        list23.add(4);
        list23.add(5);
        list23.add(6);
        list23.add(7);
        list23.add(8);
        list23.add(9);
        list23.add(10);

        objectList1 = createList(c, Object.class);
        objectList1.add("String");
        objectList1.add(1);
        objectList1.add(false);
        objectList1.add(8);
        objectList1.add(true);
        objectList1.add(15);
        objectList1.add(1.5);

        objectList2 = createList(c, Object.class);
        objectList2.add(0);
        objectList2.add(true);
        objectList2.add(1);
        objectList2.add(false);
        objectList2.add(2);
        objectList2.add("String");
        objectList2.add(3);
        objectList2.add(1.2);
        objectList2.add(4);
        objectList2.add('a');
        objectList2.add(5);
    }

}
