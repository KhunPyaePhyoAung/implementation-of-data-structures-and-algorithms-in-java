package me.khun.datastructure.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayListTest {

    @Test
    public void testDefaultConstructor() {
        var list = new ArrayList<Integer>();
        list.add(-5);
        list.add(-4);
        list.add(-3);
        list.add(-2);
        list.add(-1);
        assertEquals("[-5, -4, -3, -2, -1]", list.toString());
    }

    @Test
    public void testConstructorWithInitialCapacityOfZero() {
        var list = new ArrayList<Integer>(0);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        assertEquals("[6, 7, 8, 9, 10]", list.toString());
    }

    @Test
    public void testConstructorWithInitialCapacity() {
        var list = new ArrayList<Integer>(5);
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertEquals("[0, 1, 2, 3, 4, 5]", list.toString());
    }

    @Test
    public void testConstructorWithCollection() {
        var list1 = new ArrayList<Integer>();
        list1.add(-5);
        list1.add(-4);
        list1.add(-3);
        list1.add(-2);
        list1.add(-1);
        assertEquals("[-5, -4, -3, -2, -1]", list1.toString());

        var list2 = new ArrayList<Integer>(5);
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        assertEquals("[0, 1, 2, 3, 4, 5]", list2.toString());

        var list3 = new ArrayList<>(list1);
        list3.addAll(list2);

        assertEquals("[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", list3.toString());
    }
}
