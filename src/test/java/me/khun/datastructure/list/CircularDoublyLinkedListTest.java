package me.khun.datastructure.list;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CircularDoublyLinkedListTest {

    @Test
    public void test1() {
        List<Integer> list1 = new CircularDoublyLinkedList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);

        assertEquals("[10, 20, 30]", list1.toString());

        List<Integer> list2 = new CircularDoublyLinkedList<>(list1);
        list2.add(40);

        assertEquals("[10, 20, 30, 40]", list2.toString());
    }
}
