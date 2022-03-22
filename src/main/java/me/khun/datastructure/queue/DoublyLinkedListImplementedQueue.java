package me.khun.datastructure.queue;

import me.khun.datastructure.list.DoublyLinkedList;

import java.util.*;

public class DoublyLinkedListImplementedQueue<E> implements Queue<E> {

    private final List<E> list;

    public DoublyLinkedListImplementedQueue() {
        list = new DoublyLinkedList<>();
    }

    public DoublyLinkedListImplementedQueue(Collection<? extends E> c) {
        list = new DoublyLinkedList<>(c);
    }

    // Time Complexity = O(1)
    @Override
    public boolean offer(E e) {
        return add(e);
    }

    // Time Complexity = O(1)
    @Override
    public boolean add(E e) {
        return list.add(e);
    }

    // Time Complexity = O(m) , m = size of c
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return list.addAll(c);
    }

    // Time Complexity = O(1)
    @Override
    public E element() {
        if (isEmpty())
            throw new NoSuchElementException();
        return list.get(0);
    }

    // Time Complexity = O(1)
    @Override
    public E peek() {
        return isEmpty() ? null : list.get(0);
    }

    // Time Complexity = O(1)
    @Override
    public E poll() {
        return isEmpty() ? null : list.remove(0);
    }

    // Time Complexity = O(1)
    @Override
    public E remove() {
        if (isEmpty())
            throw new NoSuchElementException();
        return list.remove(0);
    }

    // Time Complexity = O(n)
    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    // Time Complexity = O(n)
    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Queue<?> that) || this.size() != that.size())
            return false;

        var thisIterator = this.iterator();
        var thatIterator = that.iterator();
        while (thisIterator.hasNext())
            if (!Objects.equals(thisIterator.next(), thatIterator.next()))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }
}
