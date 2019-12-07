package com.icecaptain.Datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AList<T> implements Iterable<T> {
    /** Creates an empty list. */
    public AList() {
        arr = (T []) new Object[100];
        size = 0;
    }

    public AList(int arrSize) {
        arr = (T []) new Object[arrSize];
        size = 0;
    }
    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        arr[size] = x;
    }

    /** Returns the item from the back of the list. */
    public T getLast() {
        return arr[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        if (i > 100) throw new ArrayIndexOutOfBoundsException("out of bounds");
        return arr[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public T removeLast() {
        return arr[size--];
    }

    private T[] arr;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new AListiterator();
    }

    public class AListiterator implements Iterator<T> {
        public AListiterator() {
            pos = 0;
        }
        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T returnItem = arr[pos];
            pos += 1;
            return returnItem;
        }
        private int pos;
    }
}
