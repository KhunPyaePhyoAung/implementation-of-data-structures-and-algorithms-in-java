package me.khun.datastructure.list;

import java.util.*;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private Node<E> previous;
        private Node<E> next;
        private E value;

        Node(E value) {
            this(null, null, value);
        }

        Node(Node<E> previous, Node<E> next, E value) {
            this.previous = previous;
            this.next = next;
            this.value = value;
        }
    }

    private int size;
    private long modificationCount;
    private Node<E> head;
    private Node<E> tail;

    public DoublyLinkedList() {}

    public DoublyLinkedList(Collection<? extends E> c) {
        addAll(size, c);
    }

    // Time Complexity = O(1)
    @Override
    public boolean add(E e) {
        add(size, e);
        return true;
    }

    // Time Complexity = O(n)
    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        var node = new Node<>(element);

        if (isEmpty()) {
            head = tail = node;
        } else if (index == 0) {
            linkNodes(node, head);
            head = node;
        } else if (index == size) {
            linkNodes(tail, node);
            tail = node;
        } else {
            var indexNode = getNode(index);
            var previousNode = indexNode.previous;
            linkNodes(previousNode, node);
            linkNodes(node, indexNode);
        }

        size++;
        modificationCount++;
    }

    // Time Complexity = O(m) , m = size of c
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    // Time Complexity = O(n + m) , m = size of c
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();

        if (c.isEmpty())
            return false;

        Node<E> cHead = null;
        Node<E> cTail = null;

        for (E e : c) {

            var node = new Node<>(e);

            if (cHead == null)
                cHead = node;
            else
                linkNodes(cTail, node);

            cTail = node;
        }

        if (isEmpty()) {
            head = cHead;
            tail = cTail;
        } else if (index == 0) {
            linkNodes(cTail, head);
            head = cHead;
        } else if (index == size) {
            linkNodes(tail, cHead);
            tail = cTail;
        } else {
            var indexNode = getNode(index);
            linkNodes(indexNode.previous, cHead);
            linkNodes(cTail, indexNode);
        }

        size += c.size();
        modificationCount++;
        return true;
    }

    private void linkNodes(Node<E> left, Node<E> right) {
        if (left != null)
            left.next = right;
        if (right != null)
            right.previous = left;
    }

    // Time Complexity = O(n)
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        Node<E> traverse;

        if (index < size / 2) {

            traverse = head;

            for (int i = 0; i < index; i++)
                traverse = traverse.next;

        } else {

            traverse = tail;

            for (int i = size - 1; i > index; i--)
                traverse = traverse.previous;

        }
        return traverse;

    }

    // Time Complexity = O(n)
    @Override
    public boolean remove(Object o) {
        var traverse = head;
        while (traverse != null) {
            var next = traverse.next;
            if (Objects.equals(o, traverse.value)) {
                remove(traverse);
                return true;
            }
            traverse = next;
        }
        return false;
    }

    // Time Complexity = O(n)
    @Override
    public E remove(int index) {
        return remove(getNode(index));
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean removeAll(Collection<?> c) {

        if (isEmpty() || c.isEmpty())
            return false;

        var sizeBefore = size;

        var traverse = head;

        while (traverse != null) {
            var next = traverse.next;
            if (c.contains(traverse.value))
                remove(traverse);
            traverse = next;
        }

        return sizeBefore != size;
    }

    // Time Complexity = O(1)
    private E remove(Node<E> node) {
        var value = node.value;

        linkNodes(node.previous, node.next);

        if (node == head)
            head = node.next;
        if (node == tail)
            tail = node.previous;

        node.previous = node.next = node = null;
        size--;
        modificationCount++;

        return value;
    }

    // Time Complexity = O(n)
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean retainAll(Collection<?> c) {

        var sizeBefore = size;

        if (c.isEmpty()) {
            clear();
            return sizeBefore != size;
        }

        if (isEmpty())
            return false;

        var traverse = head;
        while (traverse != null) {
            var next = traverse.next;
            if (!c.contains(traverse.value))
                remove(traverse);
            traverse = next;
        }

        return sizeBefore != size;
    }

    // Time Complexity = O(n)
    @Override
    public int indexOf(Object o) {

        var traverse = head;

        for (int i = 0; i < size; i++, traverse = traverse.next)
            if (Objects.equals(o, traverse.value))
                return i;
        return -1;
    }

    // Time Complexity = O(n)
    @Override
    public int lastIndexOf(Object o) {
        var traverse = tail;
        for (int i = size - 1; i >=0; i--, traverse = traverse.previous)
            if (Objects.equals(o, traverse.value))
                return i;
        return -1;
    }

    // Time Complexity = O(n)
    @Override
    public E get(int index) {
        return getNode(index).value;
    }

    // Time Complexity = O(n)
    @Override
    public E set(int index, E element) {
        var node = getNode(index);
        var value = node.value;
        node.value = element;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    // Time Complexity = O(1)
    @Override
    public void clear() {
        if (head != null)
            head.next = head = null;
        if (tail != null)
            tail.previous = tail = null;

        size = 0;
        modificationCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            long expectedModificationCount = -1;
            Node<E> traverse = head;
            Node<E> previous = null;
            boolean currentElementExists = false;

            @Override
            public boolean hasNext() {
                checkModificationCount();
                return traverse != null;
            }

            @Override
            public E next() {

                if (!hasNext())
                    throw new NoSuchElementException();

                E value = traverse.value;
                traverse = traverse.next;
                previous = traverse;
                currentElementExists = true;
                return value;
            }

            @Override
            public void remove() {
                if (!currentElementExists)
                    throw new IllegalStateException();

                checkModificationCount();

                if (previous != null) {
                    DoublyLinkedList.this.remove(previous);
                    modificationCount--;
                }
                currentElementExists = false;

            }

            private void checkModificationCount() {
                if (expectedModificationCount == -1)
                    expectedModificationCount = modificationCount;
                if (expectedModificationCount != modificationCount)
                    throw new ConcurrentModificationException();
            }
        };
    }

    // Time Complexity = O(n)
    @Override
    public Object[] toArray() {
        var array = new Object[size];
        var traverse = head;
        for (int i = 0; i < size; i++) {
            array[i] = traverse.value;
            traverse = traverse.next;
        }
        return array;
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

    // Time Complexity = O(n)
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("FromIndex cannot be greater than ToIndex.");
        if (fromIndex < 0 || toIndex > size)
            throw new IndexOutOfBoundsException();

        var list = new DoublyLinkedList<E>();

        if (fromIndex == toIndex)
            return list;

        var traverse = getNode(fromIndex);

        for (int i = fromIndex; i < toIndex; i++) {
            list.add(traverse.value);
            traverse = traverse.next;
        }

        return list;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        var traverse = head;
        while (traverse != null) {
            if (traverse != head)
                sb.append(", ");
            sb.append(traverse.value);
            traverse = traverse.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
