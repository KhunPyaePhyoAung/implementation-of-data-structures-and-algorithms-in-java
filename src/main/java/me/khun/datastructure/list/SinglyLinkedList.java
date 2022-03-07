package me.khun.datastructure.list;

import java.util.*;

@SuppressWarnings("unchecked")
public class SinglyLinkedList<E> implements List<E> {

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size;
    private long modificationCount;

    private static class Node<E> {

        Node<E> next;
        E value;

        Node(E value) {
            this(null, value);
        }

        Node(Node<E> next, E value) {
            this.next = next;
            this.value = value;
        }
    }

    public SinglyLinkedList() {
        this.size = 0;
        this.modificationCount = 0;
    }

    public SinglyLinkedList(Collection<? extends  E> c) {
        this();
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
            throw new IndexOutOfBoundsException("Index out of bounds : "+index);

        Node<E> node = new Node<>(element);

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
            var previousIndexNode = getPreviousNode(indexNode);
            linkNodes(previousIndexNode, node);
            linkNodes(node, indexNode);
        }

        size++;
        modificationCount++;

    }

    private void linkNodes(Node<E> left, Node<E> right) {
        if (left != null)
            left.next = right;
    }

    // Time Complexity = O(m) , m = size of c
    @Override
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    // Time Complexity = O(n)
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {

        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);

        if (c.isEmpty())
            return false;

        Node<E> cHead = null;
        Node<E> cTail = null;

        for (E e : c) {
            var currentNode = new Node<>(e);

            if (cHead == null)
                cHead = currentNode;
            else
                linkNodes(cTail, currentNode);

            cTail = currentNode;
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
            var previousIndexNode = getPreviousNode(indexNode);
            linkNodes(previousIndexNode, cHead);
            linkNodes(cTail, indexNode);
        }

        size += c.size();
        modificationCount++;
        return true;
    }


    // Time Complexity = O(n)
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);

        var traverse = head;
        for (int i = 0; i < index; i++)
            traverse = traverse.next;
        return traverse;
    }

    private Node<E> getPreviousNode(Node<? extends E> node) {
        if (node == head)
            return null;
        var traverse = head;
        while (traverse.next != node)
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
        var traverse = head;
        while (traverse != null) {
            if (Objects.equals(traverse.value, o)) {
                remove(traverse);
                return true;
            }
            traverse = traverse.next;
        }
        return false;
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean removeAll(Collection<?> c) {

        if (c.isEmpty() || isEmpty())
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

    // Time Complexity = O(n)
    private E remove(Node<E> node) {

        var previousNode = getPreviousNode(node);

        if (node == head)
            head = node.next;
        if (node == tail)
            tail = previousNode;

        linkNodes(previousNode, node.next);

        var value = node.value;
        node.next = null;
        node.value = null;

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

        if (c == this)
            return false;

        var sizeBefore = size;

        if (c.isEmpty()) {
            clear();
            return sizeBefore != size;
        }

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
    public E get(int index) {
        return getNode(index).value;
    }

    // Time Complexity = O(n)
    @Override
    public E set(int index, E element) {
        var node = getNode(index);
        E value = node.value;
        node.value = element;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Time Complexity = O(1)
    @Override
    public void clear() {
        if (isEmpty())
            return;
        head.next = null;
        head.value = tail.value = null;
        head = tail = null;
        size = 0;
        modificationCount++;
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
        var lastIndex = -1;
        var traverse = head;

        for (int i = 0; i < size; i++, traverse = traverse.next)
            if (Objects.equals(o, traverse.value))
                lastIndex = i;
        return lastIndex;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            long expectedModificationCount = -1;
            Node<E> traverse = head;
            boolean currentElementExists = false;

            @Override
            public boolean hasNext() {
                checkModificationCount();
                return traverse != null;
            }

            @Override
            public E next() {

                if (!hasNext())
                    throw new NoSuchElementException("No such element.");

                var value = traverse.value;
                traverse = traverse.next;
                currentElementExists = true;
                return value;
            }

            @Override
            public void remove() {

                if (!currentElementExists)
                    throw new IllegalStateException();

                checkModificationCount();

                var previous = getPreviousNode(traverse);
                if (previous != null) {
                    SinglyLinkedList.this.remove(previous);
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
        var array = (E[]) new Object[size];
        var traverse = head;
        for (int i = 0; i < size; traverse = traverse.next)
            array[i++] = traverse.value;
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
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("FromIndex out of bounds : " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("ToIndex out of bounds : " + toIndex);

        List<E> list = new SinglyLinkedList<>();

        if (fromIndex == toIndex)
            return list;

        var traverse = getNode(fromIndex);

        for (int i = fromIndex; i < toIndex; i++, traverse = traverse.next)
            list.add(traverse.value);

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