package me.khun.datastructure.list;

import java.util.*;

public class CircularSinglyLinkedList<E> implements List<E> {

    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value) {
            this(null, value);
        }

        Node(Node<T> next, T value) {
            this.next = next;
            this.value = value;
        }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;
    private long modificationCount = 0;

    public CircularSinglyLinkedList() {}

    public CircularSinglyLinkedList(Collection<? extends E> c) {
        addAll(c);
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
            var previousNode = getPreviousNode(indexNode);
            linkNodes(previousNode, node);
            linkNodes(node, indexNode);
        }

        linkNodes(tail, head);

        size++;
        modificationCount++;
    }

    // Time Complexity = O(m), m = size if c
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    // Time Complexity = O(n + m), m = size of c
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
            var previousNode = getPreviousNode(indexNode);
            linkNodes(previousNode, cHead);
            linkNodes(cTail, indexNode);
        }

        linkNodes(tail, head);

        size += c.size();
        modificationCount++;
        return true;
    }

    private void linkNodes(Node<E> left, Node<E> right) {
        if (left != null)
            left.next = right;
    }

    private Node<E> getPreviousNode(Node<E> node) {

        if (node == head)
            return tail;

        var traverse = head;

        while (traverse.next != node)
            traverse = traverse.next;

        return traverse;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();

        if (index == 0)
            return head;
        else if (index == size - 1)
            return tail;

        var traverse = head;
        for (int i = 0; i < index; i++)
            traverse = traverse.next;

        return traverse;
    }

    // Time Complexity = O(n)
    @Override
    public E remove(int index) {
        return remove(getNode(index));
    }

    // Time Complexity = O(n)
    @Override
    public boolean remove(Object o) {
        if (isEmpty())
            return false;

        var traverse = head;

        do {
            if (Objects.equals(o, traverse.value)) {
                remove(traverse);
                return true;
            }
            traverse = traverse.next;
        } while (traverse != head);

        return false;
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean removeAll(Collection<?> c) {

        if (isEmpty() || c.isEmpty())
            return false;

        var sizeBefore = size;

        var traverse = head;

        for (int i = 0; i < sizeBefore; i++) {
            var next = traverse.next;
            if (c.contains(traverse.value))
                remove(traverse);
            traverse = next;
        }
        return sizeBefore != size;
    }

    // Time Complexity = O(n)
    private E remove(Node<E> node) {

        var value = node.value;

        if (size == 1) {
            head.next = head = tail = null;
        } else {
            var previousNode = getPreviousNode(node);
            var nextNode = node.next;
            linkNodes(previousNode, nextNode);
            if (node == head)
                head = nextNode;
            if (node == tail)
                tail = previousNode;
        }

        node.next = node = null;
        linkNodes(tail, head);
        size--;
        modificationCount++;
        return value;
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean retainAll(Collection<?> c) {

        var sizeBefore = size;

        if (c.isEmpty()) {
            clear();
            return size != sizeBefore;
        }

        if (isEmpty())
            return false;

        var traverse = head;

        for (int i = 0; i < sizeBefore; i++) {
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(traverse.value, o))
                return i;
            traverse = traverse.next;
        }
        return -1;
    }

    // Time Complexity = O(n)
    @Override
    public int lastIndexOf(Object o) {
        var lastIndex = -1;
        var traverse = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(traverse.value, o))
                lastIndex = i;
            traverse = traverse.next;
        }
        return lastIndex;
    }

    // Time Complexity = O(n)
    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean containsAll(Collection<?> c) {
        if (isEmpty())
            return c.isEmpty();

        for (Object o : c)
            if (!contains(o))
                return false;
        return true;
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
        if (!isEmpty())
            head.next = head = tail = null;
        size = 0;
        modificationCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            long expectedModificationCount = -1;
            Node<E> traverse = head;
            Node<E> previous = null;
            boolean flag = head != null;
            boolean currentElementExists = false;

            @Override
            public boolean hasNext() {
                checkModificationCount();
                return traverse != head || flag;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();

                flag = false;

                var value = traverse.value;
                previous = traverse;
                traverse = traverse.next;
                currentElementExists = true;
                return value;
            }

            @Override
            public void remove() {
                checkModificationCount();
                if (!currentElementExists)
                    throw new IllegalStateException();
                CircularSinglyLinkedList.this.remove(previous);
                currentElementExists = false;
                modificationCount--;
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

        var list = new CircularSinglyLinkedList<E>();

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
        boolean comma = false;
        for (E e : this) {
            if (comma)
                sb.append(", ");
            sb.append(e);
            comma = true;
        }
        sb.append("]");
        return sb.toString();
    }
}
