package me.khun.datastructure.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircularDoublyLinkedListTest {

    @Test
    public void testDefaultConstructor() {
        var list = new CircularDoublyLinkedList<Integer>();
        list.add(-5);
        list.add(-4);
        list.add(-3);
        list.add(-2);
        list.add(-1);
        assertEquals("[-5, -4, -3, -2, -1]", list.toString());
    }

    @Test
    public void testConstructorWithCollection() {
        var list1 = new CircularDoublyLinkedList<Integer>();
        list1.add(-5);
        list1.add(-4);
        list1.add(-3);
        list1.add(-2);
        list1.add(-1);
        assertEquals("[-5, -4, -3, -2, -1]", list1.toString());

        var list2 = new CircularDoublyLinkedList<Integer>();
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        assertEquals("[0, 1, 2, 3, 4, 5]", list2.toString());

        var list3 = new CircularDoublyLinkedList<>(list1);
        list3.addAll(list2);

        assertEquals("[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", list3.toString());
    }

}
