package com.icecaptain.Datastructures;

public class LinkedListDeque<T> {
    public LinkedListDeque() {
       sentinelHead = new Node();
       sentinelTail = new Node();
       sentinelHead.next = sentinelTail;
       sentinelTail.prev = sentinelHead;
       sentinelHead.prev = sentinelTail.next = null;
       size = 0;
    }

    public LinkedListDeque LinkedListDeque(LinkedListDeque<T> other) {
        LinkedListDeque newList = new LinkedListDeque<T>();
        Node ptr = other.sentinelHead.next;
        while (ptr != other.sentinelTail) {
            newList.addLast((T)ptr.item);
            ptr = ptr.next;
        }
        return newList;
    }

    public void addFirst(T item) {
        Node newNode = createNode(item);
        newNode.next = sentinelHead.next;
        newNode.prev = sentinelHead;
        newNode.next.prev = newNode;
        newNode.prev.next = newNode;
        size++;
    }

    public T removeFirst() {
        if (sentinelHead.next == sentinelTail) return null;
        Node firstItem = sentinelHead.next;
        sentinelHead.next = firstItem.next;
        sentinelHead.next.prev = sentinelHead;
        T item = firstItem.item;
        firstItem = null;
        size--;
        return item;
    }

    public void addLast(T item) {
        Node newNode = createNode(item);
        newNode.next = sentinelTail;
        newNode.prev = sentinelTail.prev;
        newNode.prev.next = newNode;
        newNode.next.prev = newNode;
        size++;
    }

    public T removeLast() {
        if (sentinelHead.next == sentinelTail) return null;
        Node lastItem = sentinelTail.prev;
        lastItem.prev.next = sentinelTail;
        sentinelTail.prev = lastItem.prev;
        T item = lastItem.item;
        lastItem = null;
        size--;
        return item;
    }

    public T get(int index) {
        if (index > size) return null;
        Node ptr = sentinelHead.next;
        for (int i = 0; i < index; i++) {
           ptr = ptr.next;
        }
        return ptr.item;
    }

    public T getRecursiveHelper(Node node, int index) {
        if (isEmpty()) return null;
        if (index > size) return null;
        if (index == 0) {
            return node.item;
        } else {
            index--;
            return getRecursiveHelper(node.next, index);
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinelHead.next, index);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return sentinelHead.next == sentinelTail;
    }

    public void printDeque() {
       Node p = sentinelHead.next;
       while (p != sentinelTail) {
           System.out.print(p.item + " -> ");
           p = p.next;
       }
        System.out.print("ø");
    }

    public class Node {
        private T item;
        private Node next;
        private Node prev;
    }

    private Node createNode(T item) {
        Node newNode = new Node();
        newNode.item = item;
        newNode.prev = newNode.next = null;
        return newNode;
    }

    private Node sentinelHead;
    private Node sentinelTail;
    private int size;
}
