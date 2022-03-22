package me.khun.datastructure.queue;

import me.khun.datastructure.list.DoublyLinkedList;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;

public class Stack<E> {

    private final List<E> list = new DoublyLinkedList<>();

    // Time Complexity = O(1)
    public E push(E e) {
        list.add(e);
        return e;
    }

    // Time Complexity = O(1)
    public E pop() {
        if (isEmpty())
            throw new EmptyStackException();
        return list.remove(list.size() - 1);
    }

    // Time Complexity = O(1)
    public E peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return list.get(list.size() - 1);
    }

    // Time Complexity = O(n)
    public int search(E o) {
        int index = list.lastIndexOf(o);
        if (index == -1)
            return -1;
        return list.size() - index;
    }

    // Time Complexity = O(1)
    public void clear() {
        list.clear();
    }

    // Time Complexity = O(1)
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // Time Complexity = O(1)
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof Stack<?> that) || this.size() != that.size())
            return false;

        var thisIterator = this.list.iterator();
        var thatIterator = that.list.iterator();

        while (thisIterator.hasNext())
            if (!Objects.equals(thisIterator.next(), thatIterator.next()))
                return false;

        return true;
    }

    @Override
    public int hashCode() {
        var hashCode = 1;
        for (E e : this.list)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }
}
