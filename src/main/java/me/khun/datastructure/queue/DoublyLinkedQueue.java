package me.khun.datastructure.queue;

import me.khun.datastructure.list.DoublyLinkedList;

import java.util.*;

public class DoublyLinkedQueue<E> implements Queue<E> {

    protected final DoublyLinkedList<E> CONTAINER;

    public DoublyLinkedQueue() {
        CONTAINER = new DoublyLinkedList<>();
    }

    public DoublyLinkedQueue(Collection<? extends E> c) {
        Objects.requireNonNull(c);
        CONTAINER = new DoublyLinkedList<>(c);
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public boolean offer(E element) {
        return add(element);
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public boolean add(E element) {
        return CONTAINER.add(element);
    }

    /*
     * Time Complexity = O(m)
     * m = size of c
     */
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return CONTAINER.addAll(c);
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public E element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return CONTAINER.get(0);
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public E peek() {
        return isEmpty() ? null : CONTAINER.get(0);
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public E poll() {
        return isEmpty() ? null : CONTAINER.remove(0);
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public E remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return CONTAINER.remove(0);
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean remove(Object object) {
        return CONTAINER.remove(object);
    }

    /*
     * Time Complexity = O(nm)
     * m = size of c
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        return CONTAINER.removeAll(c);
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean contains(Object object) {
        return CONTAINER.contains(object);
    }

    /*
     * Time Complexity = O(nm)
     * m = size of c
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return CONTAINER.containsAll(c);
    }

    /*
     * Time Complexity = O(nm)
     * m = size of c
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return CONTAINER.retainAll(c);
    }

    @Override
    public int size() {
        return CONTAINER.size();
    }

    @Override
    public boolean isEmpty() {
        return CONTAINER.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return CONTAINER.iterator();
    }

    @Override
    public Object[] toArray() {
        return CONTAINER.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return CONTAINER.toArray(a);
    }

    @Override
    public void clear() {
        CONTAINER.clear();
    }

    @Override
    public String toString() {
        return CONTAINER.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Queue<?> otherQueue) || (this.size() != otherQueue.size())) {
            return false;
        }

        var thisIterator = this.iterator();
        var otherIterator = otherQueue.iterator();

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
        return CONTAINER.hashCode();
    }
}
