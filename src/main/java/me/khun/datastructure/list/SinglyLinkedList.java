package me.khun.datastructure.list;

import me.khun.datastructure.adt.ICollection;
import me.khun.datastructure.adt.IList;

import java.util.*;
import java.util.function.Predicate;

public class SinglyLinkedList<E> implements IList<E> {

    private static class Node<T> {
        Node<T> next;
        T value;

        Node(T value) {
            this(null, value);
        }

        Node(Node<T> next, T value) {
            this.next = next;
            this.value = value;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int     size;
    private long    modificationCount;

    public SinglyLinkedList() {
        this.size = 0;
        this.modificationCount = 0;
    }

    public SinglyLinkedList(ICollection<? extends  E> c) {
        this();
        addAll(c);
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
            var previousIndexNode = getPreviousNode(indexNode);
            linkNodes(previousIndexNode, node);
            linkNodes(node, indexNode);
        }

        size++;
        modificationCount++;
    }

    private void linkNodes(Node<E> leftNode, Node<E> rightNode) {
        if (leftNode != null) {
            leftNode.next = rightNode;
        }
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
     * Time Complexity = O(n)
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
            var currentNode = new Node<>(e);
            if (cHead == null) {
                cHead = currentNode;
            } else {
                linkNodes(cTail, currentNode);
            }
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

    /*
     * Time Complexity = O(n)
     */
    private Node<E> getNode(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }

        if (index == 0) {
            return head;
        } else if (index == size - 1) {
            return tail;
        }

        var traverse = head;

        for (var i = 0; i < index; i++) {
            traverse = traverse.next;
        }

        return traverse;
    }

    /*
     * Time Complexity = O(n)
     */
    private Node<E> getPreviousNode(Node<? extends E> targetNode) {
        if (targetNode == head) {
            return null;
        }

        var traverse = head;
        while (traverse.next != targetNode) {
            traverse = traverse.next;
        }
        return traverse;
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public E remove(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException("Index out of bounds : " + index);
        }

        Node<E> previous = null;
        Node<E> traverse = head;
        for (var i = 0; i < index; i++) {
            previous = traverse;
            traverse = traverse.next;
        }
        return removeNode(traverse, previous);
    }

    /*
     * Time Complexity = O(n)
     */
    @Override
    public boolean remove(Object object) {
        Node<E> previous = null;
        Node<E> traverse = head;
        while (traverse != null) {
            if (Objects.equals(object, traverse.value)) {
                removeNode(traverse, previous);
                return true;
            }
            previous = traverse;
            traverse = traverse.next;
        }
        return false;
    }

    /*
     * Time Complexity = O(n) * O(m)
     * O(m) = Time Complexity Of predicate
     */
    private boolean removeAllIf(Predicate<? super E> predicate) {
        var sizeBefore = size;
        Node<E> previous = null;
        Node<E> traverse = head;

        while (traverse != null) {
            var next = traverse.next;
            if (predicate.test(traverse.value)) {
                removeNode(traverse, previous);
            } else {
                previous = traverse;
            }
            traverse = next;
        }

        return sizeBefore != size;
    }

    /*
     * Time Complexity = O(nm)
     * m = size of c
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
     * Time Complexity = O(1)
     */
    private E removeNode(Node<E> target, Node<E> previous) {
        linkNodes(previous, target.next);

        if (target == head) {
            head = head.next;
        }

        if (target == tail) {
            tail = previous;
        }

        var value = target.value;

        target.value = null;
        target.next = null;
        target = null;

        size--;
        modificationCount++;

        return value;
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

        if (isEmpty()) {
            return c.isEmpty();
        }

        for (var o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
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
        } else {
            removeAllIf(t -> !c.contains(t));
        }
        return sizeBefore != size;
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
        return size == 0;
    }

    /*
     * Time Complexity = O(1)
     */
    @Override
    public void clear() {
        modificationCount++;

        if (isEmpty()) {
            return;
        }

        head.next = null;
        head.value = null;
        head = null;
        tail.value = null;
        tail = null;
        size = 0;
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
        var lastIndex = -1;
        var traverse = head;
        for (var i = 0; i < size; i++, traverse = traverse.next) {
            if (Objects.equals(object, traverse.value)) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final long EXPECTED_MODIFICATION_COUNT = modificationCount;
            private Node<E> traverse = null;
            private Node<E> previous = null;
            private boolean currentElementExists = false;

            /*
             * Time Complexity = O(1)
             */
            @Override
            public boolean hasNext() {
                if (EXPECTED_MODIFICATION_COUNT != modificationCount) {
                    return false;
                }

                if (traverse == null) {
                    return previous == null
                            ? head != null
                            : previous.next != null;
                }

                return traverse.next != null;
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

                previous = traverse == null ? previous : traverse;
                traverse = previous == null ? head : previous.next;

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

                SinglyLinkedList.this.removeNode(traverse, previous);
                traverse = null;
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

        var subList = new SinglyLinkedList<E>();

        if (fromIndex == toIndex) {
            return subList;
        }

        var traverse = getNode(fromIndex);
        for (var i = fromIndex; i < toIndex; i++) {
            subList.add(traverse.value);
            traverse = traverse.next;
        }
        return subList;
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

        if (!(other instanceof IList<?> otherList) || (this.size() != otherList.size())) {
            return false;
        }

        var thisIterator = this.iterator();
        var otherIterator = otherList.iterator();

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
        var hashCode = 0;
        for (var e : this) {
            hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }
}