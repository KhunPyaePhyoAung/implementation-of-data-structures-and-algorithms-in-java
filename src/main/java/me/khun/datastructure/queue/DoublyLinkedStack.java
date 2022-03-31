package me.khun.datastructure.queue;

import me.khun.datastructure.list.DoublyLinkedList;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;

public class DoublyLinkedStack<E> {

    private final List<E> CONTAINER;

    public DoublyLinkedStack() {
        CONTAINER = new DoublyLinkedList<>();
    }

    /*
     * Time Complexity = O(1)
     */
    public E push(E element) {
        CONTAINER.add(element);
        return element;
    }

    /*
     * Time Complexity = O(1)
     */
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return CONTAINER.remove(CONTAINER.size() - 1);
    }

    /*
     * Time Complexity = O(1)
     */
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return CONTAINER.get(CONTAINER.size() - 1);
    }

    /*
     * Time Complexity = O(n)
     */
    public int search(E target) {
        var index = CONTAINER.lastIndexOf(target);
        if (index == -1) {
            return -1;
        }
        return CONTAINER.size() - index;
    }

    /*
     * Time Complexity = O(1)
     */
    public void clear() {
        CONTAINER.clear();
    }

    /*
     * Time Complexity = O(1)
     */
    public boolean isEmpty() {
        return CONTAINER.isEmpty();
    }

    /*
     * Time Complexity = O(1)
     */
    public int size() {
        return CONTAINER.size();
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

        if (!(other instanceof DoublyLinkedStack<?> otherStack) || (this.size() != otherStack.size())) {
            return false;
        }

        var thisIterator = this.CONTAINER.iterator();
        var otherIterator = otherStack.CONTAINER.iterator();

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
