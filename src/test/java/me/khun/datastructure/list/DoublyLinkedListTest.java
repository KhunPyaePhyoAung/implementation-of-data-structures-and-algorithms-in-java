package me.khun.datastructure.list;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoublyLinkedListTest {

    @Test
    public void test1() {
        List<Integer> list1 = new DoublyLinkedList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);

        assertEquals("[10, 20, 30]", list1.toString());

        List<Integer> list2 = new DoublyLinkedList<>(list1);

        assertEquals("[10, 20, 30]", list2.toString());
    }
}
