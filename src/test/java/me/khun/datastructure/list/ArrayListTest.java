package me.khun.datastructure.list;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ArrayListTest {

    @Test
    public void test1() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(-5);
        list1.add(-4);
        list1.add(-3);
        list1.add(-2);
        list1.add(-1);

        List<Integer> list2 = new ArrayList<>(5);
        list2.add(0);
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);

        List<Integer> list3 = new ArrayList<>(list1);
        list3.addAll(list2);

        Assert.assertEquals("[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5]", list3.toString());
    }
}
