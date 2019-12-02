/**
 * A list of integers that hides away nakedness of 
 * underlying recursive data structure
 */
public class SLList<T> {
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

    public SLList() {
        _size = 0;
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public SLList(T x) {
        sentinel = new Node(null, sentinel, sentinel);
        Node tmp = new Node(x, sentinel, sentinel);
        sentinel.next = tmp;
        sentinel.prev = tmp;
        _size = 1;
    }

    public void addFirst(T x) {
        Node tmp = new Node(x, sentinel.next, sentinel);
        sentinel.next.prev = tmp;
        sentinel.next = tmp;
        _size += 1;
    }

    public T getFirst() {
        return sentinel.next.item;
    }

    public void addLast(T x) {
        Node tmp = new Node(x, sentinel, sentinel.prev);
        sentinel.prev.next = tmp;
        sentinel.prev = tmp;
        _size += 1;
    }

    public T getLast() {
        if (size() == 0)
            throw new IndexOutOfBoundsException();
        return sentinel.prev.item;
    }

    public T popFirst() {
        if (size() == 0)
            throw new IllegalStateException("Cannot pop from empty SLList");
        Node tmp = sentinel.next;
        sentinel.next = tmp.next;
        tmp.next.prev = sentinel;
        _size -= 1;
        tmp.next = null;
        tmp.prev = null;
        return tmp.item;
    }

    public T popLast() {
        if (size() == 0)
            throw new IllegalStateException("Cannot pop from empty SLList");
        Node tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        tmp.prev.next = sentinel;
        _size -= 1;
        tmp.next = null;
        tmp.prev = null;
        return tmp.item;
    }

    /** 
     * Returns the size of the list that starts at Node p 
     * NOTE: Unused in the version of SLList that uses sentinels
    private static int size(Node p) {
        if (p.next == null)
            return 1;
        return 1 + size(p.next);
    }
    **/

    public int size() {
        return _size;
    }

    public void display() {
        Node current = sentinel.next;
        while (current.next != sentinel) {
            System.out.print(current.item + " => ");
            current = current.next;
        }
        System.out.println(current.item);
    }

    public static void main(String[] args) {
        SLList<Integer> L = new SLList<>();
        L.addFirst(5);
        L.addFirst(10);
        L.display();
        L.addLast(13);
        L.addLast(17);
        L.addLast(100);
        L.addFirst(-1);
        L.display();

        while (L.size() != 0) {
            System.out.println(L.popFirst());
        }
    }
}
