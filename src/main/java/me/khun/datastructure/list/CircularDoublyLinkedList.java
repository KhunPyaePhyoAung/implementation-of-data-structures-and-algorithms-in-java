package me.khun.datastructure.list;

import me.khun.datastructure.adt.ICollection;
import me.khun.datastructure.adt.IList;

import java.util.*;
import java.util.function.Predicate;

public class CircularDoublyLinkedList<E> implements IList<E> {

    private static class Node<T> {
        Node<T> previous;
        Node<T> next;
        T value;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int     size;
    private long    modificationCount;

    public CircularDoublyLinkedList() {
        this.size = 0;
        this.modificationCount = 0;
    }

    public CircularDoublyLinkedList(ICollection<? extends E> c) {
        this();
        addAll(size, c);
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public boolean add(E element) {
        add(size, element);
        return true;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public void add(int index, E element) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }

        var node = new Node<>(element);

        if (isEmpty()) {
            head = node;
            tail = node;
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

    /*
     * Time Complexity = O(m)
     * m = size of c
     */
    @Override
    public boolean addAll(ICollection<? extends E> c) {
        return addAll(size, c);
    }

    /*
     * Time Complexity = O(n + m)
     * m = size of c
     */
    @Override
    public boolean addAll(int index, ICollection<? extends E> c) {
        if ((index < 0) || (index > size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }

        Objects.requireNonNull(c);

        if (c.isEmpty()) {
            return false;
        }

        Node<E> cHead = null;
        Node<E> cTail = null;

        // Links all elements of Collection c.
        for (E e : c) {
            var node = new Node<>(e);
            if (cHead == null) {
                cHead = node;
            } else {
                linkNodes(cTail, node);
            }
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

    /*
     * Time Complexity = O(n)
     */
    private Node<E> getNode(int index) {
        if ((index <0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }

        if (index == 0) {
            return head;
        } else if (index == size - 1) {
            return tail;
        }

        Node<E> traverse;
        final var HALF_OF_SIZE = size / 2;

        if (index < HALF_OF_SIZE) {
            traverse = head;
            for (var i = 0; i < index; i++) {
                traverse = traverse.next;
            }
        } else {
            traverse = tail;
            for (var i = size - 1; i > index; i--) {
                traverse = traverse.previous;
            }
        }

        return traverse;
    }

    private void linkNodes(Node<E> leftNode, Node<E> rightNode) {
        if (leftNode != null) {
            leftNode.next = rightNode;
        }
        if (rightNode != null) {
            rightNode.previous = leftNode;
        }
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public E remove(int index) {
        return remove(getNode(index));
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean remove(Object object) {
        if (isEmpty()) {
            return false;
        }

        var traverse = head;

        do {
            if (Objects.equals(object, traverse.value)) {
                remove(traverse);
                return true;
            }
            traverse = traverse.next;
        } while (traverse != head);

        return false;
    }

    /*
     * Time Complexity = O(nm)
     *  = size of c
     */
    @Override
    public boolean removeAll(ICollection<?> c) {
        Objects.requireNonNull(c);

        if (c.isEmpty() || isEmpty()) {
            return false;
        }

        return removeAllIf(c::contains);
    }

    /*
     * Time Complexity = O(n) * O(m)
     * O(m) = Time Complexity of predicate
     */
    private boolean removeAllIf(Predicate<? super E> predicate) {
        if (isEmpty()) {
            return false;
        }

        var sizeBefore = size;
        var traverse = head;

        for (var i = 0; i < sizeBefore; i++) {
            var next = traverse.next;

            if (predicate.test(traverse.value)) {
                remove(traverse);
            }

            traverse = next;
        }

        return sizeBefore != size;
    }

    /*
     * Time Complexity = O(1)
     */
    private E remove(Node<E> node) {
        var value = node.value;

        if (size == 1) {
            head = null ;
            tail = null;
        } else {
            linkNodes(node.previous, node.next);

            if (node == head) {
                head = node.next;
            }

            if (node == tail) {
                tail = node.previous;
            }
        }

        node.value = null;
        node.previous = null;
        node.next = null;
        node = null;
        size--;
        modificationCount++;

        return value;
    }

    /*
     * Time Complexity = O(nm)
     * m = size of c
     */
    @Override
    public boolean retainAll(ICollection<?> c) {
        Objects.requireNonNull(c);

        if (this == c) {
            return false;
        }

        var sizeBefore = size;

        if (c.isEmpty()) {
            clear();
            return sizeBefore != size;
        }

        return removeAllIf(e -> !c.contains(e));
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public int indexOf(Object object) {
        var traverse = head;
        for (var i = 0; i < size; i++) {
            if (Objects.equals(object, traverse.value)) {
                return i;
            }
            traverse = traverse.next;
        }
        return -1;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public int lastIndexOf(Object object) {
        var traverse = tail;
        for (var i = size - 1; i >= 0; i--) {
            if (Objects.equals(object, traverse.value)) {
                return i;
            }
            traverse = traverse.previous;
        }
        return -1;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    /*
     * Time Complexity = O(nm)
     * m = size of c
     */
    @Override
    public boolean containsAll(ICollection<?> c) {
        Objects.requireNonNull(c);
        for (var o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public E get(int index) {
        return getNode(index).value;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public E set(int index, E element) {
        var indexNode = getNode(index);
        var value = indexNode.value;
        indexNode.value = element;
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

    /*
     * Time Complexity = O(1)
     */
    @Override
    public void clear() {
        if (head != null) {
            head.previous = null;
            head.next = null;
            head.value = null;
            head = null;
        }

        if (tail != null) {
            tail.previous = null;
            tail.next = null;
            tail.value = null;
            tail = null;
        }

        size = 0;
        modificationCount++;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final long EXPECTED_MODIFICATION_COUNT = modificationCount;
            private Node<E> traverse = null;
            private boolean currentElementExists = false;

            /*
             * Time Complexity = O(1)
             */
            @Override
            public boolean hasNext() {
                if (EXPECTED_MODIFICATION_COUNT != modificationCount) {
                    return false;
                }
                return (traverse == null)
                        ? (head != null)
                        : (traverse.next != head);
            }

            /*
             * Time Complexity = O(1)
             */
            @Override
            public E next() {
                checkModificationCount();

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                traverse = (traverse == null) ? head : traverse.next;
                var value = traverse.value;
                currentElementExists = true;

                return value;
            }

            /*
             * Time Complexity = O(1)
             */
            @Override
            public void remove() {
                if (!currentElementExists) {
                    throw new IllegalStateException();
                }

                checkModificationCount();

                var previous = (traverse == head) ? null : traverse.previous;
                CircularDoublyLinkedList.this.remove(traverse);
                traverse = previous;
                modificationCount--;
                currentElementExists = false;
            }

            private void checkModificationCount() {
                if (EXPECTED_MODIFICATION_COUNT != modificationCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public Object[] toArray() {
        var array = new Object[size];
        var traverse = head;

        for (var i = 0; i < size; i++) {
            array[i] = traverse.value;
            traverse = traverse.next;
        }

        return array;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public IList<E> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }

        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }

        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(%d) > toIndex(%d)".formatted(fromIndex, toIndex));
        }

        var list = new CircularDoublyLinkedList<E>();

        if (fromIndex == toIndex) {
            return list;
        }

        var traverse = getNode(fromIndex);
        for (var i = fromIndex; i < toIndex; i++) {
            list.add(traverse.value);
            traverse = traverse.next;
        }
        return list;
    }

    @Override
    public String toString() {
        var stringBuilder = new StringBuilder();

        stringBuilder.append("[");

        var appendComma = false;

        for (var e : this) {
            if (appendComma) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(e);
            appendComma = true;
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof IList<?> that) || (this.size() != that.size())) {
            return false;
        }

        var thisIterator = this.iterator();
        var otherIterator = that.iterator();

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
        var hashCode = 1;
        for (var e : this) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }
}
