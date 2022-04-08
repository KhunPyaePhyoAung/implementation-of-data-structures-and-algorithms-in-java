package me.khun.datastructure.list;

import me.khun.datastructure.adt.IList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ArrayListTest extends ListTest {

    @Test
    public void testDefaultConstructor() {
        var list = new ArrayList<>();
        list.add(-5);
        list.add(-4);
        list.add(-3);
        list.add(-2);
        list.add(-1);
        assertArrayEquals(new Integer[]{-5, -4, -3, -2, -1}, list.toArray());
    }

    @Test
    public void testConstructorWithInitialCapacity() {
        var list = new ArrayList<Integer>(5);
        list.add(-5);
        list.add(-4);
        list.add(-3);
        list.add(-2);
        list.add(-1);
        assertArrayEquals(new Integer[]{-5, -4, -3, -2, -1}, list.toArray());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenPassingInitialCapacityLessThanZeroToConstructor() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayList<>(-1));
    }

    @Test
    public void shouldThrowOutOfMemoryErrorWhenAddMethodIsInvoked() {
        var list = new ArrayList<>();
        assertThrows(OutOfMemoryError.class, () -> {
            while (true) {
                list.add(1);
            }
        });
    }

    @Test
    public void shouldThrowOutOfMemoryErrorWhenAddAtIndexMethodIsInvoked() {
        var list = new ArrayList<>();
        assertThrows(OutOfMemoryError.class, () -> {
            while (true) {
                list.add(list.size(), 1);
            }
        });
    }

    @Test
    public void shouldThrowOutOfMemoryErrorWhenAddAtAllMethodIsInvoked() {
        var list1 = new ArrayList<>();

        var list2 = new ArrayList<>();
        list2.add(1);
        list2.add(1);
        list2.add(1);
        list2.add(1);
        list2.add(1);

        assertThrows(OutOfMemoryError.class, () -> {
            while (true) {
                list1.addAll(list2);
            }
        });
    }

    @Test
    public void shouldThrowOutOfMemoryErrorWhenAddAtAllAtIndexMethodIsInvoked() {
        var list1 = new ArrayList<>();

        var list2 = new ArrayList<>();
        list2.add(1);
        list2.add(1);
        list2.add(1);
        list2.add(1);
        list2.add(1);

        assertThrows(OutOfMemoryError.class, () -> {
            while (true) {
                list1.addAll(list1.size(), list2);
            }
        });
    }

    @Test
    public void testConstructorWithCollection() {
        var list1 = new ArrayList<Integer>();
        list1.add(-5);
        list1.add(-4);
        list1.add(-3);
        list1.add(-2);
        list1.add(-1);

        var list2 = new ArrayList<>(list1);
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
        assertThrows(NullPointerException.class, () -> new ArrayList<>(null));
    }

    @Override
    protected <T> IList<T> createList(Class<T> type) {
        return new ArrayList<>();
    }
}
