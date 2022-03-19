package me.khun.datastructure.list;

import org.junit.jupiter.api.BeforeEach;

import java.util.List;

public class ListTest2 {

    private List<Integer> list1;
    private List<Integer> list2;
    private List<Integer> list3;
    private List<Integer> list4;
    private List<Integer> list12;
    private List<Integer> list13;
    private List<Integer> list23;

    @BeforeEach
    public void setupLists() {
        list1 = newList(Integer.class);
        list1.add(-5);
        list1.add(-4);
        list1.add(-3);
        list1.add(-2);
        list1.add(-1);

        list2 = newList(Integer.class);
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);

        list3 = newList(Integer.class);
        list3.add(6);
        list3.add(7);
        list3.add(8);
        list3.add(9);
        list3.add(10);

        list4 = newList(Integer.class);
        list4.add(11);
        list4.add(12);
        list4.add(13);
        list4.add(14);
        list4.add(15);

        list12 = newList(Integer.class);
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

        list13 = newList(Integer.class);
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

        list23 = newList(Integer.class);
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

    }

    public void testAddMethod() {

    }

    private <T> List<T> newList(Class<T> type) {
        return new ArrayList<>();
    }

}
