package me.khun.datastructure.list;

import me.khun.datastructure.adt.IList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest extends ListTest {

    @Test
    public void testDefaultConstructor() {
        var list = new DoublyLinkedList<Integer>();
        list.add(-5);
        list.add(-4);
        list.add(-3);
        list.add(-2);
        list.add(-1);
        assertArrayEquals(new Integer[]{-5, -4, -3, -2, -1}, list.toArray());
    }

    @Test
    public void testConstructorWithCollection() {
        var list1 = new DoublyLinkedList<Integer>();
        list1.add(-5);
        list1.add(-4);
        list1.add(-3);
        list1.add(-2);
        list1.add(-1);

        var list2 = new DoublyLinkedList<>(list1);
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);

        assertArrayEquals(new Integer[]{-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5}, list2.toArray());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenPassingNullToConstructorWithCollection() {
        assertThrows(NullPointerException.class, () -> new DoublyLinkedList<>(null));
    }

    @Override
    protected <T> IList<T> createList(Class<T> type) {
        return new DoublyLinkedList<>();
    }
}
