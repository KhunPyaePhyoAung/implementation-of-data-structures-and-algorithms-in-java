package me.khun.datastructure.queue;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedDeque<E> extends DoublyLinkedQueue<E> implements Deque<E> {

    public DoublyLinkedDeque() {
        super();
    }

    public DoublyLinkedDeque(Collection<? extends E> c) {
        super(c);
    }

    @Override
    public void addFirst(E e) {
        super.CONTAINER.add(0, e);
    }

    @Override
    public void addLast(E e) {
        super.CONTAINER.add(e);
    }

    @Override
    public boolean offerFirst(E e) {
        addFirst(e);
        return true;
    }

    @Override
    public boolean offerLast(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return super.CONTAINER.remove(0);
    }

    @Override
    public E removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return super.CONTAINER.remove(size() - 1);
    }

    @Override
    public E pollFirst() {
        return isEmpty() ? null : super.CONTAINER.remove(0);
    }

    @Override
    public E pollLast() {
        return isEmpty() ? null : super.CONTAINER.remove(size() - 1);
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return super.CONTAINER.get(0);
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return super.CONTAINER.get(size() - 1);
    }

    @Override
    public E peekFirst() {
        return isEmpty() ? null : super.CONTAINER.get(0);
    }

    @Override
    public E peekLast() {
        return isEmpty() ? null : super.CONTAINER.get(size() - 1);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return remove(o);
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return super.CONTAINER.remove(o, false);
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    @Override
    public Iterator<E> descendingIterator() {
        throw new UnsupportedOperationException();
    }

}
