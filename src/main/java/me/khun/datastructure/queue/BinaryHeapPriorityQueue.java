package me.khun.datastructure.queue;

import me.khun.datastructure.adt.IQueue;
import me.khun.datastructure.list.ArrayList;

import java.util.*;

public class BinaryHeapPriorityQueue<E extends Comparable<? super E>> implements IQueue<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 100;

    private final Comparator<? super E> COMPARATOR;
    private final ArrayList<E>          HEAP;
    private long                        modificationCount;

    public BinaryHeapPriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, Comparator.naturalOrder());
    }

    public BinaryHeapPriorityQueue(Comparator<? super E> comparator) {
        this(DEFAULT_INITIAL_CAPACITY, comparator);
    }

    public BinaryHeapPriorityQueue(int initialCapacity) {
        this(initialCapacity, Comparator.naturalOrder());
    }

    public BinaryHeapPriorityQueue(int initialCapacity, Comparator<? super E> comparator) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity : " + initialCapacity);
        }

        if (comparator == null) {
            throw new NullPointerException("Comparator cannot be null.");
        }

        this.HEAP = new ArrayList<>(initialCapacity);
        this.COMPARATOR = comparator;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean add(E element) {
        Objects.requireNonNull(element);
        HEAP.add(element);
        swim(getLastIndex());
        modificationCount++;
        return true;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean offer(E element) {
        return add(element);
    }

    /*
     * Time Complexity = O(log(n))
     */
    private void swim(int index) {
        checkAndThrowIndexOutOfBoundsException(index);
        var elementIndex = index;
        var parentIndex = parentIndexOf(index);
        while (elementIndex != 0 && isLess(elementIndex, parentIndex)) {
            swap(elementIndex, parentIndex);
            elementIndex = parentIndex;
            parentIndex = parentIndexOf(elementIndex);
        }
    }

    /*
     * Time Complexity = O(log(n))
     */
    private void sink(int index) {
        checkAndThrowIndexOutOfBoundsException(index);

        var elementIndex = index;
        while (true) {
            var leftChildIndex = leftChildIndexOf(elementIndex);
            var rightChildIndex = rightChildIndexOf(elementIndex);
            var smallestIndex = leftChildIndex;

            if (rightChildIndex != -1 && isLess(rightChildIndex, leftChildIndex)) {
                smallestIndex = rightChildIndex;
            }

            if (leftChildIndex == -1 || isLess(elementIndex, smallestIndex)) {
                break;
            }

            swap(elementIndex, smallestIndex);
            elementIndex = smallestIndex;
        }
    }

    /*
     * Time Complexity = O(1)
     */
    private void swap(int index1, int index2) {
        checkAndThrowIndexOutOfBoundsException(index1);
        checkAndThrowIndexOutOfBoundsException(index2);

        if (index1 == index2) {
            return;
        }

        var e1 = get(index1);
        var e2 = get(index2);
        HEAP.set(index1, e2);
        HEAP.set(index2, e1);
    }

    private int leftChildIndexOf(int index) {
        checkAndThrowIndexOutOfBoundsException(index);
        var childIndex = (index * 2) + 1;
        return (childIndex < size())
                ? childIndex
                : -1;
    }

    private int rightChildIndexOf(int index) {
        checkAndThrowIndexOutOfBoundsException(index);
        var childIndex = (index * 2) + 2;
        return (childIndex < size())
                ? childIndex
                : -1;
    }

    private int parentIndexOf(int index) {
        checkAndThrowIndexOutOfBoundsException(index);
        if (index == 0) {
            return -1;
        }
        return (index - 1) / 2;
    }

    private boolean isLess(int i, int j) {
        checkAndThrowIndexOutOfBoundsException(i);
        checkAndThrowIndexOutOfBoundsException(j);
        return compare(get(i), get(j)) < 0;
    }

    private int compare(E e1, E e2) {
        return COMPARATOR.compare(e1, e2);
    }

    private int getLastIndex() {
        return size() - 1;
    }

    private E get(int index) {
        return HEAP.get(index);
    }

    /*
     * Time Complexity = O(log(n))
     */
    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return removeAt(0);
    }

    /*
     * Time Complexity = O(log(n))
     */
    @Override
    public E poll() {
        return removeAt(0);
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean remove(Object o) {
        var index = HEAP.indexOf(o);
        return index != -1 && removeAt(index) != null;
    }

    /*
     * Time Complexity = O(log(n))
     */
    private E removeAt(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }

        var element = get(index);
        var lastIndex = getLastIndex();

        swap(index, lastIndex);
        HEAP.remove(lastIndex);

        if (index == lastIndex) {
            modificationCount++;
            return element;
        }

        var sinkElement = get(index);
        sink(index);
        if (sinkElement == get(index)) {
            swim(index);
        }

        modificationCount++;

        return element;
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return get(0);
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public E peek() {
        return isEmpty() ? null : get(0);
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean contains(Object o) {
        return HEAP.contains(o);
    }

    @Override
    public int size() {
        return HEAP.size();
    }

    @Override
    public boolean isEmpty() {
        return HEAP.isEmpty();
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public void clear() {
        HEAP.clear();
        modificationCount++;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public Object[] toArray() {
        return HEAP.toArray();
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public String toString() {
        return HEAP.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final long EXPECTED_MODIFICATION_COUNT = modificationCount;
            private int currentIndex = 0;
            private boolean currentElementExists = false;

            /*
             * Time Complexity = O(1)
             */
            @Override
            public boolean hasNext() {
                if (EXPECTED_MODIFICATION_COUNT != modificationCount) {
                    return false;
                }
                return currentIndex < size();
            }

            /*
             * Time Complexity = O(1)
             */
            @Override
            public E next() {
                checkModificationCount();

                if (!hasNext()) {
                    throw new NoSuchElementException("No Such Element.");
                }

                currentElementExists = true;

                return HEAP.get(currentIndex++);
            }

            /*
             * Time Complexity = O(log(n))
             */
            @Override
            public void remove() {
                if (!currentElementExists) {
                    throw new IllegalStateException();
                }

                checkModificationCount();

                var removeIndex = currentIndex - 1;
                BinaryHeapPriorityQueue.this.removeAt(removeIndex);

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

    private void checkAndThrowIndexOutOfBoundsException(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }
    }
}
