package com.icecaptain.Datastructures;
import java.util.Arrays;

public class ArrayDeque<T> {

    public ArrayDeque() {
        arr = (T []) new Object[DEFAULT_SIZE];
        size = 0;
        front = (arr.length / 2) - 1;
        end = front + 1;
    }

    public void addFirst(T item) {
        if (isFull()) return;
        arr[front] = item;
        nextFront();
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) return null;
        return arr[front++];
    }

    public void addLast(T item) {
        if (isFull()) return;
        arr[end] = item;
        nextEnd();
        size++;
    }

    public T removeLast() {
        if (isEmpty()) return null;
        T lastItem = arr[end];
        arr[end] = null;
        end--;
        return lastItem;
    }

    public boolean isEmpty() {
       return size == 0;
    }

    public boolean isFull() {
        return size == arr.length;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        System.out.println(Arrays.toString(arr));
        System.out.println("front -> " + front);
        System.out.println("end -> " + end);
    }

    //TODO: fix get
    public T get(int index) {
        if (index > size) return null;
        return null;
    }

    private void nextFront() {
        front--;
        if (front < 0) {
            front = arr.length - 1;
        }
    }

    private void nextEnd() {
        end++;
        int maxIndex = arr.length - 1;
        if (end > maxIndex) {
            end = 0;
        }
    }

    private void resize() {
        DEFAULT_SIZE *= 2;
       T[] newArr = (T []) new Object[DEFAULT_SIZE];
    }

    private T[] arr;
    private int size;
    private int front;
    private int end;
    private int DEFAULT_SIZE;
}
