package me.khun.datastructure.queue;

import me.khun.datastructure.list.DoublyLinkedList;

import java.util.EmptyStackException;
import java.util.List;

public class Stack<E> {

    private final List<E> list = new DoublyLinkedList<>();

    // Time Complexity = O(1)
    public E push(E e) {
        list.add(e);
        return e;
    }

    // Time Complexity = O(1)
    public E pop() {
        if (list.isEmpty())
            throw new EmptyStackException();
        return list.remove(list.size() - 1);
    }

    // Time Complexity = O(1)
    public E peek() {
        if (list.isEmpty())
            throw new EmptyStackException();
        return list.get(list.size() - 1);
    }

    // Time Complexity = O(n)
    public int search(E o) {
        int index = list.indexOf(o);
        if (index == -1)
            return -1;
        return list.size() - list.indexOf(o);
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
}
