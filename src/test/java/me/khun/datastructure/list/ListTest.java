package me.khun.datastructure.list;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;

public class ListTest {

    @Test
    public void testAddMethods() {
        List<Integer> list1 = newList(Integer.class);
        list1.add(1);
        list1.add(2);
        list1.add(0, 0);
        list1.add(3, 4);
        list1.add(3, 3);

        assertEquals("[0, 1, 2, 3, 4]", list1.toString());

        List<Integer> list2 = newList(Integer.class);
        list2.add(0, -3);
        list2.add(1, -2);
        list2.add(-1);
        assertTrue(list2.addAll(list1));
        assertFalse(list2.addAll(newList(Integer.class)));

        assertEquals("[-3, -2, -1, 0, 1, 2, 3, 4]", list2.toString());

        List<Integer> list3 = newList(Integer.class);
        list3.addAll(0, list1);
        list3.addAll(0, list2);
        list3.addAll(13, list1);
        list3.addAll(13, list2);

        assertEquals("[-3, -2, -1, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, -3, -2, -1, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4]", list3.toString());

        List<Integer> list4 = newList(Integer.class);

        assertThrows(IndexOutOfBoundsException.class, () -> list4.add(-1, 0));

        assertThrows(IndexOutOfBoundsException.class, () -> list4.add(1, 0));

        List<Integer> list5 = newList(Integer.class);
        list5.add(1);

        list4.add(0);
        assertTrue(list4.addAll(list5));

        assertThrows(IndexOutOfBoundsException.class, () -> list4.add(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list4.add(3, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> list4.addAll(-1, list1));
        assertThrows(IndexOutOfBoundsException.class, () -> list4.addAll(3, list1));
        assertThrows(NullPointerException.class, () -> list4.addAll(null));
    }

    @Test
    public void testIndexOfMethods() {
        List<Integer> list1 = newList(Integer.class);
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(0);
        list1.add(1);
        list1.add(5);

        assertEquals("[0, 1, 2, 0, 1, 2, 3, 0, 1, 5]", list1.toString());
        assertEquals(0, list1.indexOf(0));
        assertEquals(7, list1.lastIndexOf(0));
        assertEquals(1, list1.indexOf(1));
        assertEquals(8, list1.lastIndexOf(1));
        assertEquals(2, list1.indexOf(2));
        assertEquals(5, list1.lastIndexOf(2));
        assertEquals(6, list1.indexOf(3));
        assertEquals(6, list1.lastIndexOf(3));
        assertEquals(9, list1.indexOf(5));
        assertEquals(9, list1.lastIndexOf(5));
        assertEquals(-1, list1.indexOf(100));

    }

    @Test
    public void testRemoveMethods() {
        List<Integer> list1 = newList(Integer.class);
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        assertEquals("[0, 1, 2, 3, 4, 5]", list1.toString());
        assertEquals((Integer) 3, list1.remove(3));
        assertTrue(list1.remove(Integer.valueOf(4)));
        assertTrue(list1.remove(Integer.valueOf(5)));
        assertFalse(list1.remove(Integer.valueOf(6)));
        assertEquals("[0, 1, 2]", list1.toString());

        List<Integer> list2 = newList(Integer.class);
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(3);
        list2.add(2);
        list2.add(1);
        list2.add(0);
        list2.add(1);

        assertEquals("[0, 1, 2, 3, 4, 3, 2, 1, 0, 1]", list2.toString());
        assertThrows(IndexOutOfBoundsException.class, () -> list2.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list2.remove(11));
        assertThrows(NullPointerException.class, () -> list2.retainAll(null));
        assertTrue(list2.removeAll(list1));
        assertFalse(list2.removeAll(newList(Integer.class)));
        assertEquals("[3, 4, 3]", list2.toString());

        List<Integer> list3 = newList(Integer.class);
        list3.add(3);
        list3.add(4);
        list3.add(3);

        assertTrue(list2.removeAll(list3));
        assertEquals("[]", list2.toString());

        assertFalse(newList(Integer.class).removeAll(list1));
        assertFalse(newList(Integer.class).removeAll(newList(Integer.class)));

        List<String> list4 = newList(String.class);
        assertFalse(list2.removeAll(list4));

        List<Integer> list5 = newList(Integer.class);
        list5.add(100);
        list5.add(200);
        list5.add(300);

        assertFalse(list2.removeAll(list5));

        List<Integer> list6 = newList(Integer.class);
        list6.add(1);
        list6.remove(0);
        assertEquals("[]", list6.toString());
    }

    @Test
    public void testContainMethods() {

        List<String> list1 = newList(String.class);
        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add(null);

        List<String> list2 = newList(String.class);
        list2.add("d");
        list2.add("e");
        list2.add("f");

        List<String> list3 = newList(String.class);
        list3.add("d");
        list3.add("e");
        list3.add("f");
        list3.add("g");
        list3.add("h");

        List<Integer> list4 = newList(Integer.class);
        list4.add(10);
        list4.add(20);
        list4.add(30);

        assertTrue(list1.contains(null));
        assertFalse(list2.contains(null));
        assertTrue(list3.contains("d"));
        assertFalse(list3.contains("a"));
        assertFalse(list3.contains(1));
        assertTrue(list3.containsAll(list2));
        assertFalse(list3.containsAll(list1));
        assertFalse(list3.containsAll(list4));
        assertTrue(newList(String.class).containsAll(newList(String.class)));
        assertThrows(NullPointerException.class, () -> list3.containsAll(null));
    }

    @Test
    public void testRetainMethod() {
        List<Character> list1 = newList(Character.class);
        list1.add('a');
        list1.add('b');
        list1.add('c');
        list1.add('d');

        List<Character> list2 = newList(Character.class);
        list2.addAll(list1);
        list2.add('c');
        list2.add('d');
        list2.add('a');
        list2.add('b');

        List<Character> list3 = newList(Character.class);
        list3.addAll(list1);
        list3.add('s');
        list3.add('t');
        list3.add('u');
        list3.add('v');
        list3.add('a');
        list3.add('b');
        list3.add('c');

        assertEquals("[a, b, c, d]", list1.toString());
        assertEquals("[a, b, c, d, c, d, a, b]", list2.toString());
        assertEquals("[a, b, c, d, s, t, u, v, a, b, c]", list3.toString());

        assertFalse(list2.retainAll(list1));
        assertEquals("[a, b, c, d, c, d, a, b]", list2.toString());

        assertTrue(list3.retainAll(list1));
        assertEquals("[a, b, c, d, a, b, c]", list3.toString());

        assertTrue(list3.retainAll(newList(String.class)));
        assertEquals("[]", list3.toString());

        assertFalse(list3.retainAll(newList(Character.class)));
        assertEquals("[]", list3.toString());

        assertFalse(list3.retainAll(newList(Character.class)));
        assertEquals("[]", list3.toString());

        assertFalse(list3.retainAll(list1));
        assertEquals("[]", list3.toString());

        assertFalse(list3.retainAll(newList(String.class)));
        assertEquals("[]", list3.toString());

        assertThrows(NullPointerException.class, () -> list3.retainAll(null));

        List<String> list4 = newList(String.class);
        list4.add("a");
        list4.add("b");
        list4.add("c");

        assertTrue(list2.retainAll(list4));
        assertEquals("[]", list2.toString());

    }

    @Test
    public void testGetAndSetMethods() {
        List<Integer> list1 = newList(Integer.class);
        list1.add(5);
        list1.add(4);
        list1.add(3);
        list1.add(2);
        list1.add(1);
        list1.add(0);

        assertEquals("[5, 4, 3, 2, 1, 0]", list1.toString());

        assertEquals(Integer.valueOf(5), list1.get(0));
        assertEquals(Integer.valueOf(4), list1.get(1));
        assertEquals(Integer.valueOf(3), list1.get(2));
        assertEquals(Integer.valueOf(2), list1.get(3));
        assertEquals(Integer.valueOf(1), list1.get(4));
        assertEquals(Integer.valueOf(0), list1.get(5));

        assertEquals(Integer.valueOf(5), list1.set(0, 0));
        assertEquals(Integer.valueOf(4), list1.set(1, 1));
        assertEquals(Integer.valueOf(3), list1.set(2, 2));
        assertEquals(Integer.valueOf(2), list1.set(3, 3));
        assertEquals(Integer.valueOf(1), list1.set(4, 4));
        assertEquals(Integer.valueOf(0), list1.set(5, 5));

        assertEquals("[0, 1, 2, 3, 4, 5]", list1.toString());

        assertThrows(IndexOutOfBoundsException.class, () -> list1.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.get(list1.size()));
        assertThrows(IndexOutOfBoundsException.class, () -> newList(Integer.class).get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.set(-1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.set(list1.size(), 5));
    }

    @Test
    public void testSubListMethod() {
        List<Integer> list1 = newList(Integer.class);
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        assertEquals("[0, 1, 2, 3, 4]", list1.toString());
        assertEquals("[0, 1, 2, 3, 4]", list1.subList(0, 5).toString());
        assertEquals("[0]", list1.subList(0, 1).toString());
        assertEquals("[1, 2]", list1.subList(1, 3).toString());
        assertEquals("[3]", list1.subList(3, 4).toString());
        assertEquals("[3, 4]", list1.subList(3, 5).toString());
        assertEquals("[]", list1.subList(0, 0).toString());
        assertEquals("[]", list1.subList(2, 2).toString());
        assertEquals("[]", list1.subList(4, 4).toString());
        assertEquals("[]", list1.subList(5, 5).toString());
        assertThrows(IndexOutOfBoundsException.class, () -> list1.subList(0, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.subList(-1, 5));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.subList(-1, -1));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.subList(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.subList(-1, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.subList(-1, 7));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.subList(6, 6));
        assertThrows(IndexOutOfBoundsException.class, () -> list1.subList(7, 7));
        assertThrows(IllegalArgumentException.class, () -> list1.subList(6, 0));
        assertThrows(IllegalArgumentException.class, () -> list1.subList(1, 0));
        assertThrows(IllegalArgumentException.class, () -> list1.subList(1, -1));

    }

    @Test
    public void testIterator() {
        List<Integer> list1 = newList(Integer.class);
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(100);
        list1.add(200);
        list1.add(300);
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = newList(Integer.class);

        for (Integer i : list1)
            list2.add(i);

        assertEquals("[0, 1, 2, 3, 100, 200, 300, 0, 1, 2, 3]", list1.toString());
        assertEquals(list1.toString(), list2.toString());

        assertThrows(
                IllegalStateException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
                    iterator.remove();
                }
        );

        assertThrows(
                IllegalStateException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
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
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
                    iterator.next();
                    iterator.remove();
                    iterator.remove();
                }
        );

        assertThrows(
                IllegalStateException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
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
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
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
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext())
                        iterator.next();
                    iterator.next();
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        list.add(3);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        list.addAll(list1);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        list.remove(0);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);
                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        list.remove(list.size() - 1);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);

                    List<Integer> list3 = newList(Integer.class);
                    list3.add(0);
                    list3.add(1);
                    list3.add(2);

                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        list.removeAll(list3);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);

                    List<Integer> list4 = newList(Integer.class);
                    list4.add(100);
                    list4.add(200);
                    list4.add(300);

                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        list.retainAll(list4);
                    }
                }
        );

        assertThrows(
                ConcurrentModificationException.class,
                () -> {
                    List<Integer> list = newList(Integer.class);
                    list.addAll(list1);

                    Iterator<Integer> iterator = list.iterator();
                    while (iterator.hasNext()) {
                        iterator.next();
                        list.clear();
                    }
                }
        );

    }

    @Test
    public void testToArrayMethod() {
        List<Integer> list1 = newList(Integer.class);
        list1.add(0);
        list1.add(1);
        list1.add(2);

        assertEquals(Arrays.toString(new int[]{0, 1, 2}), Arrays.toString(list1.toArray()));
        assertEquals(Arrays.toString(new int[]{}), Arrays.toString(newList(Integer.class).toArray()));
        assertEquals(Arrays.toString(new int[0]), Arrays.toString(newList(Integer.class).toArray()));
    }

    @Test
    public void testOtherMethods() {
        List<Integer> list1 = newList(Integer.class);
        list1.add(0);
        list1.add(1);
        list1.add(2);

        assertEquals("[0, 1, 2]", list1.toString());
        assertEquals(3, list1.size());
        assertFalse(list1.isEmpty());

        list1.clear();
        assertEquals("[]", list1.toString());
        assertEquals(0, list1.size());
        assertTrue(list1.isEmpty());
    }


    private <T> List<T> newList(Class<T> type) {
        return new DoublyLinkedList<>();
    }
}
