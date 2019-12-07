package com.icecaptain.Datastructures;

import java.util.*;

public class ArraySet<T> implements Iterable<T> {
    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    public void add(T value) {
        if (value == null) {
            throw new IllegalArgumentException("can't add null");
        }

        if (!contains(value)) {
            items[size++] = value;
        }
    }

    public boolean contains(T value) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(value)) return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("{ ");
       for (int i = 0; i < size - 1; i++) {
           sb.append(items[i].toString());
           sb.append(", ");
       }
       sb.append(items[size - 1]);
       sb.append(" }");
       return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size);
        result = 31 * result + Arrays.hashCode(items);
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null) {
           return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        ArraySet<T> o = (ArraySet<T>) other;
        if (this.size != ((ArraySet<T>) other).size) {
            return false;
        }

        for (T item : this) {
            if (!o.contains(item)) {
                return false;
            }
        }
        return true;
    }

    private T[] items;
    private int size;

    public class ArraySetIterator implements Iterator<T> {
        public ArraySetIterator() {
           pos = 0;
        }
        @Override
        public boolean hasNext() {
           return pos < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException("No more elements");
           T returnItem = items[pos++];
           return returnItem;
        }
        private int pos;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    public String toString2() {
        ArrayList list = new ArrayList<String>();
        for (T t : this) {
           list.add(t.toString());
        }
        return String.join(", ", list);
    }

    public static <G> ArraySet<G> of(G... args) {
       ArraySet<G> returnItems = new ArraySet<G>();
       for (G item : args) {
           returnItems.add(item);
       }
       return returnItems;
    }
}
