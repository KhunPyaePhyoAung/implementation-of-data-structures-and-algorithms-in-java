package me.khun.datastructure.list;

import java.util.*;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public class ArrayList<E> implements List<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private E[]     container;
    private int     size;
    private int     capacity;
    private long    modificationCount;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity : " + initialCapacity);
        }

        this.capacity = initialCapacity;
        this.container = (E[]) new Object[this.capacity];
        this.size = 0;
        this.modificationCount = 0;
    }

    public ArrayList(Collection<? extends E> c) {
        this(c.size());
        addAll(c);
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public void add(int index, E element) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }

        var newSize = size + 1;

        if (newSize > capacity) {
            capacity = getIncreasedCapacity(newSize);
            container = copySkip(container, (E[]) new Object[capacity], index, 1, size);
        } else {
            var destinationPosition = index + 1;
            var length = size - index;
            System.arraycopy(container, index, container, destinationPosition, length);
        }

        container[index] = element;
        size++;
        modificationCount++;
    }

    /*
     * Time Complexity : O(n + m)
     * m = size of c
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    /*
     * Time Complexity : O(n + m)
     * m = size of c
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }

        Objects.requireNonNull(c);

        if (c.isEmpty()) {
            return false;
        }

        var newSize = size + c.size();
        var addIndex = index;

        if (newSize > capacity) {
            capacity = getIncreasedCapacity((newSize));
            container = copySkip(container, (E[]) new Object[capacity], index, c.size(), size);
        } else {
            var destinationPosition = index + c.size();
            var length = size - index;
            System.arraycopy(container, index, container, destinationPosition, length);
        }

        for (var e : c) {
            container[addIndex++] = e;
        }

        size = newSize;
        modificationCount++;
        return true;
    }


    /**
     * Copies elements from the source array to the destination array
     * skipping sequential positions in the destination array.
     *
     * @param src the source array.
     * @param des the destination array.
     * @param desSkipFrom starting position to be skipped.
     * @param desSkipLength the number of positions to be skipped.
     * @param length the number of array elements to be copied.
     * @return the destination array.
     */
    private E[] copySkip(E[] src, E[] des, int desSkipFrom, int desSkipLength, int length) {
        System.arraycopy(src, 0, des, 0, desSkipFrom);
        System.arraycopy(src, desSkipFrom, des, desSkipFrom + desSkipLength, length - desSkipFrom);
        return des;
    }

    private int getIncreasedCapacity(int minCapacity) {
        var newCapacity =  (capacity < minCapacity)
                                ? Math.max((capacity + (capacity >> 1)), minCapacity)
                                : capacity;

        if (newCapacity < capacity) {
            throw new OutOfMemoryError();
        }

        return newCapacity;
    }

    /*
     * Time Complexity : O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * Time Complexity : O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    /*
     * Time Complexity : O(nm)
     * m = size of c
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        for (var o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public E remove(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }

        var value = get(index);
        var length = size - index - 1;

        System.arraycopy(container, index + 1, container, index, length);

        container[--size] = null;
        modificationCount++;

        return value;
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public boolean remove(Object object) {
        var index = indexOf(object);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /*
     * Time Complexity : O(nm)
     * m = size of c
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);

        if (c.isEmpty() || isEmpty()) {
            return false;
        }

        var sizeBefore = size;

        if (this == c) {
            clear();
            return sizeBefore != size;
        }

        return removeAllIf(c::contains);
    }

    /*
     * Time Complexity = O(n) * O(m)
     * O(m) = Time Complexity of predicate
     */
    private boolean removeAllIf(Predicate<? super E> predicate) {
        if (isEmpty()) {
            return false;
        }

        var sizeBefore = size;
        var retainedCount = 0;
        var retainedContainer = (E[]) new Object[capacity];

        for (var i = 0; i < size; i++) {
            var currentElement = container[i];

            if (!predicate.test(currentElement)) {
                retainedContainer[retainedCount++] = currentElement;
            }
        }

        container = retainedContainer;
        size = retainedCount;

        if (sizeBefore != size) {
            modificationCount++;
            return true;
        }

        return false;
    }

    /*
     * Time Complexity : O(nm)
     * m = size of c
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);

        var sizeBefore = size;

        if (c.isEmpty()) {
            clear();
            return sizeBefore != size;
        }

        if ((this == c) || isEmpty()) {
            return false;
        }

        return removeAllIf(e -> !c.contains(e));
    }

    /*
     * Time Complexity : O(1)
     */
    @Override
    public E get(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }
        return container[index];
    }

    /*
     * Time Complexity : O(1)
     */
    @Override
    public E set(int index, E element) {
        var value = get(index);
        container[index] = element;
        return value;
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public int indexOf(Object object) {
        for (var i = 0; i < size; i++) {
            if (Objects.equals(object, container[i])) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public int lastIndexOf(Object object) {
        for (var i = size - 1; i >= 0; i--) {
            if (Objects.equals(object, container[i])) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public void clear() {
        for (var i = 0; i < size; i++) {
            container[i] = null;
        }
        size = 0;
        modificationCount++;
    }

    /*
     * Time Complexity : O(1)
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final long EXPECTED_MODIFICATION_COUNT = modificationCount;
            private int currentIndex = 0;
            private boolean currentElementExists = false;

            /*
             * Time Complexity : O(1)
             */
            @Override
            public boolean hasNext() {
                if (EXPECTED_MODIFICATION_COUNT != modificationCount) {
                    return false;
                }
                return currentIndex < size;
            }

            /*
             * Time Complexity : O(1)
             */
            @Override
            public E next() {
                checkModificationCount();

                if (!hasNext()) {
                    throw new NoSuchElementException("No Such Element.");
                }

                currentElementExists = true;

                return container[currentIndex++];
            }

            /*
             * Time Complexity : O(n)
             */
            @Override
            public void remove() {
                if (!currentElementExists) {
                    throw new IllegalStateException();
                }

                checkModificationCount();

                var removeIndex = currentIndex - 1;

                ArrayList.this.remove(removeIndex);
                currentElementExists = false;
                modificationCount--;
                currentIndex--;
            }

            private void checkModificationCount() {
                if (EXPECTED_MODIFICATION_COUNT != modificationCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(container, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    /*
     * Time Complexity : O(n)
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }

        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }

        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(%d) > toIndex(%d)".formatted(fromIndex, toIndex));
        }

        var subList = new CircularSinglyLinkedList<E>();

        if (fromIndex == toIndex) {
            return subList;
        }

        for (var i = fromIndex; i < toIndex; i++) {
            subList.add(get(i));
        }

        return subList;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        for (var i = 0; i < size; i++) {
            if (i != 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(container[i]);
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof List<?> otherList) || (this.size() != otherList.size())) {
            return false;
        }

        var thisIterator = this.iterator();
        var otherIterator = otherList.iterator();

        while (thisIterator.hasNext()) {
            if (!Objects.equals(
                    thisIterator.next(),
                    otherIterator.next())
            ) {
                return false;
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        var hashCode = 1;
        for (var e : this) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }
}
