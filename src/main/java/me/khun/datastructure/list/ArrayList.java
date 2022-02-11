package me.khun.datastructure.list;

import java.util.*;

@SuppressWarnings("unchecked")
public class ArrayList<E> implements List<E> {

    private E[] container;
    private static final int DEFAULT_CAPACITY = 10;
    private int size;
    private int capacity;
    private long modificationCount;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {

        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity : " + initialCapacity);

        this.capacity = initialCapacity;
        this.container = (E[]) new Object[this.capacity];
        this.size = 0;
        this.modificationCount = 0;
    }

    public ArrayList(Collection<? extends E> c) {
        this(c.size());
        addAll(c);
    }

    //Time Complexity : O(n)
    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    //Time Complexity : O(n)
    @Override
    public void add(int index, E element) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);

        var newSize = size + 1;

        if (newSize > capacity)
            container = copySkip(container, (E[]) new Object[capacity = newCapacity(newSize)], index, 1, size);
        else
            System.arraycopy(container, index, container, index + 1, size - index);

        container[index] = element;
        size++;
        modificationCount++;
    }

    //Time Complexity : O(n + m), m = size of c
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    //Time Complexity : O(n + m), m = size of c
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);

        if (c.isEmpty())
            return true;

        var newSize = size + c.size();

        if (newSize > capacity)
            container = copySkip(container, (E[]) new Object[capacity = newCapacity(newSize)], index, c.size(), size);
        else
            System.arraycopy(container, index, container, index + c.size(), size - index);

        var addIndex = index;
        for (E e : c)
            container[addIndex++] = e;

        size = newSize;
        modificationCount++;

        return true;
    }

    private E[] copySkip(E[] src, E[] des, int desSkipFrom, int desSkipLength, int length) {

        System.arraycopy(src, 0, des, 0, desSkipFrom);
        System.arraycopy(src, desSkipFrom, des, desSkipFrom + desSkipLength, length - desSkipFrom);
        return des;
    }

    private int newCapacity(int min) {
        var newCapacity =  capacity < min ? Math.max((capacity + (capacity >> 1)), min) : capacity;
        if (newCapacity < capacity)
            throw new OutOfMemoryError();
        return newCapacity;
    }

    //Time Complexity : O(1)
    @Override
    public int size() {
        return size;
    }

    //Time Complexity : O(1)
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //Time Complexity : O(n)
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    //Time Complexity : O(nm), m = size of c
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    //Time Complexity : O(n)
    @Override
    public E remove(int index) {

        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);

        E value = get(index);

        System.arraycopy(container, index + 1, container, index, size - index - 1);

        container[--size] = null;
        modificationCount++;

        return value;
    }

    //Time Complexity : O(n)
    @Override
    public boolean remove(Object o) {
        final var index = indexOf(o);
        if (index != -1)
            remove(index);
        return index != -1;
    }

    //Time Complexity : O(n^2)
    @Override
    public boolean removeAll(Collection<?> c) {

        if (c.isEmpty() || isEmpty())
            return false;

        var sizeBefore = size;

        for (Object o : c)
            for (int i = 0; i < size; i++)
                if (Objects.equals(o, container[i]))
                    remove(i--);

        return sizeBefore != size;
    }

    //Time Complexity : O(nm), m = size of c
    @Override
    public boolean retainAll(Collection<?> c) {

        var sizeBefore = size;

        if (c.isEmpty()) {
            clear();
            return sizeBefore != size;
        }

        if (c == this || isEmpty())
            return false;


        var retainedContainer = new Object[capacity];
        var retainedCount = 0;

        for (E e: container)
            for (E o : (Collection<? extends E>)c)
                if (Objects.equals(o, e))
                    retainedContainer[retainedCount++] = e;

        container = (E[]) retainedContainer;
        size = retainedCount;
        modificationCount++;

        return sizeBefore != size;
    }

    //Time Complexity : O(1)
    @Override
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        return container[index];
    }

    //Time Complexity : O(1)
    @Override
    public E set(int index, E element) {
        E value = get(index);
        container[index] = element;
        return value;
    }


    //Time Complexity : O(n)
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++)
            if (Objects.equals(o, container[i]))
                return i;
        return -1;
    }

    //Time Complexity : O(n)
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--)
            if (Objects.equals(o, container[i]))
                return i;
        return -1;
    }

    //Time Complexity : O(n)
    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            container[i] = null;
        size = 0;
        modificationCount++;
    }

    //Time Complexity : O(1)
    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {

            int currentIndex = 0;
            long expectedModificationCount = -1;
            boolean currentElementExists = false;

            @Override
            public boolean hasNext() {
                checkModificationCount();
                return currentIndex < size;
            }

            @Override
            public E next() {

                if (!hasNext())
                    throw new NoSuchElementException("No Such Element.");

                currentElementExists = true;

                return container[currentIndex++];
            }

            @Override
            public void remove() {

                if (!currentElementExists)
                    throw new IllegalStateException();

                checkModificationCount();

                var removeIndex = currentIndex - 1;

                ArrayList.this.remove(removeIndex);
                modificationCount--;
                currentIndex--;
                currentElementExists = false;

            }

            private void checkModificationCount() {
                if (expectedModificationCount == -1)
                    expectedModificationCount = modificationCount;

                if (expectedModificationCount != modificationCount)
                    throw new ConcurrentModificationException("Concurrent Modification Occurred.");
            }
        };
    }

    //Time Complexity : O(n)
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(container, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Unsupported operation");
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    //Time Complexity : O(n)
    @Override
    public List<E> subList(int fromIndex, int toIndex) {

        if (fromIndex > toIndex)
            throw new IllegalArgumentException("FromIndex cannot be greater than ToIndex");
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("FromIndex out of bounds : " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("ToIndex out of bounds : " + toIndex);

        var list = new ArrayList<E>(toIndex - fromIndex);

        for (int i = fromIndex; i < toIndex; i++)
            list.add(get(i));

        return list;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != 0)
                sb.append(", ");
            sb.append(container[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}
