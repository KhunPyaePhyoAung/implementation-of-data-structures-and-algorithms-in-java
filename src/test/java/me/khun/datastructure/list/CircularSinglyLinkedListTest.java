package me.khun.datastructure.list;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CircularSinglyLinkedListTest {

    @Test
    public void test1() {
        List<String> list1 = new CircularSinglyLinkedList<>();
        list1.add("Java");
        list1.add("PHP");
        list1.add("Python");

        assertEquals("[Java, PHP, Python]", list1.toString());

        List<String> list2 = new CircularSinglyLinkedList<>(list1);
        list2.add("JavaScript");
        list2.add(0, "C");

        assertEquals("[C, Java, PHP, Python, JavaScript]", list2.toString());

    }


}
