package me.khun.datastructure.list;

import java.util.*;
import java.util.function.Predicate;

public class CircularDoublyLinkedList<E> implements List<E> {
    private static class Node<E> {
        private Node<E> previous;
        private Node<E> next;
        private E value;

        Node(E value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;
    private long modificationCount;

    public CircularDoublyLinkedList() {}

    public CircularDoublyLinkedList(Collection<? extends E> c) {
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
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);

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
            linkNodes(indexNode.previous, node);
            linkNodes(node, indexNode);
        }

        linkNodes(tail, head);
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
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);

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

        linkNodes(tail, head);
        size += c.size();
        modificationCount++;

        return true;
    }

    // Time Complexity = O(n)
    private Node<E> getNode(int index) {
        if (index <0 || index >= size)
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);

        if (index == 0)
            return head;
        else if (index == size - 1)
            return tail;

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

    private void linkNodes(Node<E> left, Node<E> right) {
        if (left != null)
            left.next = right;
        if (right != null)
            right.previous = left;
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
        if (c.isEmpty() || isEmpty())
            return false;

        return removeAllIf(c::contains);
    }

    // Time Complexity = O(n) * O(m) , O(m) = Time Complexity of predicate
    private boolean removeAllIf(Predicate<E> predicate) {
        if (isEmpty())
            return false;

        var sizeBefore = size;
        var traverse = head;

        for (int i = 0; i < sizeBefore; i++) {
            var next = traverse.next;
            if (predicate.test(traverse.value))
                remove(traverse);
            traverse = next;
        }

        return sizeBefore != size;
    }

    // Time Complexity = O(1)
    private E remove(Node<E> node) {
        var value = node.value;

        if (size == 1) {
            head = tail = null;
        } else {
            linkNodes(node.previous, node.next);

            if (node == head)
                head = node.next;
            if (node == tail)
                tail = node.previous;
        }

        node.value = null;
        node.previous = node.next = node = null;
        size--;
        modificationCount++;
        return value;
    }

    // Time Complexity = O(nm) , m = size of c
    @Override
    public boolean retainAll(Collection<?> c) {

        if (this == c)
            return false;

        var sizeBefore = size;

        if (c.isEmpty()) {
            clear();
            return sizeBefore != size;
        }

        return removeAllIf(e -> !c.contains(e));
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
        for (int i = size - 1; i >= 0; i--,traverse = traverse.previous)
            if (Objects.equals(o, traverse.value))
                return i;
        return -1;
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
            head.previous = head.next = head = null;
        if (tail != null)
            tail.previous = tail.next = tail = null;
        size = 0;
        modificationCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            final long expectedModificationCount = modificationCount;
            Node<E> traverse = null;
            boolean currentElementExists = false;

            @Override
            public boolean hasNext() {
                if (traverse == null)
                    return head != null;
                return traverse.next != head;
            }

            @Override
            public E next() {

                checkModificationCount();

                if (!hasNext())
                    throw new NoSuchElementException();

                traverse = traverse == null ? head : traverse.next;
                var value = traverse.value;
                currentElementExists = true;
                return value;
            }

            @Override
            public void remove() {

                if (!currentElementExists)
                    throw new IllegalStateException();

                checkModificationCount();

                var previous = traverse == head ? null : traverse.previous;
                CircularDoublyLinkedList.this.remove(traverse);
                traverse = previous;
                modificationCount--;
                currentElementExists = false;
            }

            private void checkModificationCount() {
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
        for (int i = 0; i < size; i++, traverse = traverse.next)
            array[i] = traverse.value;
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

        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);

        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);

        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(%d) > toIndex(%d)".formatted(fromIndex, toIndex));

        var list = new CircularDoublyLinkedList<E>();

        if (fromIndex == toIndex || isEmpty())
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;

        if (!(o instanceof List<?> that) || this.size() != that.size())
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
        var hashCode = 1;
        for (E e : this)
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        return hashCode;
    }
}
