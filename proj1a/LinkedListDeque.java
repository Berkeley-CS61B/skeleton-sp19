import jdk.jshell.spi.ExecutionControl;

public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int _size;

    public LinkedListDeque() {
        _size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        Node tmp = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = tmp;
        sentinel.next = tmp;
        _size += 1;
    }

    public void addLast(T item) {
        Node tmp = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = tmp;
        sentinel.prev = tmp;
        _size += 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return _size;
    }

    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        tmp.next.prev = sentinel;
        tmp.next = null;
        tmp.prev = null;
        _size -= 1;
        return tmp.item;
    }

    public T removeLast() {
        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        tmp.prev.next = sentinel;
        tmp.next = null;
        tmp.prev = null;
        _size -= 1;
        return tmp.item;
    }

    private T get(int index, Node p) {
        if (index == 0)
            return p.item;
        return get(index - 1, p.next);
    }

    public T get(int index) {
        if (index < 0 || index > size() - 1)
            throw new IndexOutOfBoundsException();
        return get(index, sentinel.next);
    }
}
