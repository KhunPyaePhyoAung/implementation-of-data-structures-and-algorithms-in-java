package me.khun.datastructure.list;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SinglyLinkedListTest {

    @Test
    public void test1() {
        List<Integer> list1 = new SinglyLinkedList<>();
        list1.add(0);
        list1.add(1);
        list1.add(2);
        list1.add(3);

        List<Integer> list2 = new SinglyLinkedList<>(list1);
        list2.add(4);
        list2.add(5);

        assertEquals("[0, 1, 2, 3, 4, 5]", list2.toString());
    }
}