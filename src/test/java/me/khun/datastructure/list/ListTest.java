package me.khun.datastructure.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ListTest {

    private List<Integer> l1;
    private List<Integer> l2;
    private List<Integer> l3;
    private List<Integer> l4;
    private List<Integer> l5;

    @Before
    public void init() {
        l1 = newList(Integer.class);
        l1.add(-5);
        l1.add(-4);
        l1.add(-3);
        l1.add(-2);
        l1.add(-1);

        l2 = newList(Integer.class);
        l2.add(0);
        l2.add(1);
        l2.add(2);
        l2.add(3);
        l2.add(4);

        l3 = newList(Integer.class);
        l3.add(5);
        l3.add(6);
        l3.add(7);
        l3.add(8);
        l3.add(9);
        l3.add(10);

        l4 = newList(Integer.class);
        l4.add(11);
        l4.add(12);
        l4.add(13);
        l4.add(14);
        l4.add(15);

        l5 = newList(Integer.class);
        l5.add(-5);
        l5.add(-2);
        l5.add(0);
        l5.add(2);
        l5.add(4);
    }

    @Test
    public void test1() {

        List<Integer> list = newList(Integer.class);
        list.addAll(l2);

        Assert.assertEquals("Size=5:[0, 1, 2, 3, 4]", list.toString());

        list.addAll(0, l1);

        Assert.assertEquals("Size=10:[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4]", list.toString());

        list.addAll(list.size(), l4);

        Assert.assertEquals("Size=15:[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 11, 12, 13, 14, 15]", list.toString());

        list.addAll(10, l3);
        Assert.assertEquals("Size=21:[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", list.toString());

        list.add(16);
        list.add(list.size(), 17);
        list.add(0, -7);
        list.add(1, -6);

        Assert.assertEquals("Size=25:[-7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17]", list.toString());
    }

    @Test
    public void test2() {
        List<Integer> list = newList(Integer.class);
        list.addAll(l1);
        list.addAll(l2);
        list.addAll(l2);
        list.addAll(l1);

        Assert.assertEquals("Size=20:[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 0, 1, 2, 3, 4, -5, -4, -3, -2, -1]", list.toString());
        Assert.assertEquals(5, list.indexOf(0));
        Assert.assertEquals(10, list.lastIndexOf(0));
        Assert.assertEquals(-1, list.indexOf(6));
        Assert.assertEquals(-1, list.lastIndexOf(6));
        Assert.assertEquals(6, list.indexOf(1));
        Assert.assertEquals(11, list.lastIndexOf(1));
        Assert.assertEquals(-1, list.indexOf(10));
        Assert.assertEquals(-1, list.lastIndexOf(10));
    }

    @Test
    public void test3() {
        List<Integer> list1 = newList(Integer.class);
        list1.addAll(l2);
        list1.addAll(l3);
        list1.addAll(l4);

        Assert.assertEquals("Size=16:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]", list1.toString());

        Integer i1 = list1.remove(1);
        Integer i2 = list1.remove(0);
        Integer i3 = list1.remove(list1.size() - 1);


        Assert.assertEquals(Integer.valueOf(1), i1);
        Assert.assertEquals(Integer.valueOf(0), i2);
        Assert.assertEquals(Integer.valueOf(15), i3);
        Assert.assertEquals("Size=13:[2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]", list1.toString());

        Assert.assertTrue(list1.remove(Integer.valueOf(9)));
        Assert.assertFalse(list1.remove(Integer.valueOf(100)));
        Assert.assertEquals("Size=12:[2, 3, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14]", list1.toString());

        Assert.assertFalse(list1.removeAll(newList(Integer.class)));
        list1.add(2);
        list1.add(2);
        list1.add(2);
        list1.add(2);
        list1.add(100);
        Assert.assertTrue(list1.removeAll(l1));
        Assert.assertTrue(list1.removeAll(l2));
        Assert.assertTrue(list1.removeAll(l4));
        Assert.assertEquals("Size=6:[5, 6, 7, 8, 10, 100]", list1.toString());

    }

    @Test
    public void test4() {

        List<Integer> list = newList(Integer.class);

        list.addAll(l1);

        Assert.assertTrue(list.contains(-5));
        Assert.assertFalse(list.contains(100));
        Assert.assertTrue(list.containsAll(l1));

        list.addAll(l2);
        list.addAll(l2);

        Assert.assertTrue(list.containsAll(l2));
        Assert.assertFalse(list.containsAll(l3));
        Assert.assertTrue(list.containsAll(l5));

        Assert.assertTrue(list.retainAll(l2));
        Assert.assertTrue(list.retainAll(newList(Integer.class)));
        Assert.assertEquals("Size=0:[]", list.toString());


    }

    @Test
    public void test5() {
        List<Integer> list = newList(Integer.class);

        Assert.assertTrue(list.isEmpty());

        list.addAll(l1);
        list.addAll(l2);
        list.addAll(l3);

        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(Integer.valueOf(16), (Integer) list.size());
        Assert.assertEquals("Size=16:[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", list.toString());

        Assert.assertEquals(Integer.valueOf(-5), list.get(0));
        Assert.assertEquals(Integer.valueOf(0), list.get(5));
        Assert.assertEquals(Integer.valueOf(1), list.get(6));
        Assert.assertEquals(Integer.valueOf(3), list.get(8));
        Assert.assertEquals(Integer.valueOf(10), list.get(list.size() - 1));

        Assert.assertEquals(Integer.valueOf(-5), list.set(0, -50));
        Assert.assertEquals(Integer.valueOf(0), list.set(5, 50));

        Assert.assertEquals(Integer.valueOf(-50), list.get(0));
        Assert.assertEquals(Integer.valueOf(50), list.get(5));

        list.clear();
        Assert.assertTrue(list.isEmpty());
        Assert.assertEquals(Integer.valueOf(0), (Integer) list.size());

    }

    @Test
    public void test6() {
        List<Integer> list = newList(Integer.class);

        list.addAll(l1);
        list.addAll(l2);
        list.addAll(l3);

        Assert.assertEquals("Size=16:[-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", list.toString());
        Assert.assertEquals("Size=5:[0, 1, 2, 3, 4]", list.subList(5, 10).toString());
        Assert.assertEquals("Size=1:[-5]", list.subList(0, 1).toString());
        Assert.assertEquals("Size=0:[]", list.subList(1, 1).toString());
    }

    @Test
    public void test7() {
        List<Integer> list = newList(Integer.class);
        list.addAll(l1);

        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.add(list.size() + 1, 10));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.addAll(list.size() + 1, newList(Integer.class)));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.get(10));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 10));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.set(10, 10));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(list.size() + 1));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.subList(0, list.size() + 1));
        Assert.assertThrows(IndexOutOfBoundsException.class, () -> list.subList(-1, list.size()));
        Assert.assertThrows(IllegalArgumentException.class, () -> list.subList(1, 0));


        ThrowingRunnable runnable1 = () -> {
            for (Integer next : list)
                if (next == -1)
                    list.remove(next);
        };

        ThrowingRunnable runnable2 = () -> {
            for (Integer next : list) {
                if (next == -2)
                    list.add(5);
            }
        };

        ThrowingRunnable runnable3 = () -> {
            for (Integer next : list) {
                if (next == -3)
                    list.set(0, 5);
            }
        };

        ThrowingRunnable runnable4 = () -> {
            Iterator<Integer> iterator = list.iterator();
            iterator.remove();
        };

        ThrowingRunnable runnable5 = () -> {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext())
                iterator.next();
            iterator.next();
        };

        ThrowingRunnable runnable6 = () -> {
            Iterator<Integer> iterator = list.iterator();
            iterator.next();
            iterator.next();
            list.add(10);
            iterator.remove();
        };

        ThrowingRunnable runnable7 = () -> {
            Iterator<Integer> iterator = list.iterator();
            while (iterator.hasNext())
                iterator.next();
            iterator.remove();
            iterator.remove();
        };

        Assert.assertThrows(ConcurrentModificationException.class, runnable1);
        Assert.assertThrows(ConcurrentModificationException.class, runnable2);
        Assert.assertThrows(ConcurrentModificationException.class, runnable3);
        Assert.assertThrows(IllegalStateException.class, runnable4);
        Assert.assertThrows(NoSuchElementException.class, runnable5);
        Assert.assertThrows(ConcurrentModificationException.class, runnable6);
        Assert.assertThrows(IllegalStateException.class, runnable7);
    }

    @Test
    public void test8() {

        List<Integer> list = newList(Integer.class);
        list.addAll(l2);
        list.addAll(l3);

        Assert.assertEquals("Size=11:[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]", list.toString());

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            var next = iterator.next();
            if (next % 2 == 0)
                iterator.remove();
        }

        Assert.assertEquals("Size=5:[1, 3, 5, 7, 9]", list.toString());
    }

    private <T> List<T> newList(Class<T> type) {
        return new ArrayList<>();
    }
}
