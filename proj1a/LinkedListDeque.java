
/**
 * @author yangshuai
 * @Description: Performs ArrayDeque Test
 * @date 2020/6/19 4:40 下午
 */

public class LinkedListDeque<T> extends AbstractDeque<T> {

    /**
     * 头节点，next指向队列中的第一个元素
     */
    private Node head;

    /**
     * 队列尾部，指向队列中的最后一个元素
     */
    private Node tail;

    public LinkedListDeque() {
        head = new Node(null);
        tail = new Node(null, null, head);
        head.next = tail;
        head.prev = null;
    }

    @Override
    public void addFirst(T item) {
        Node<T> node = new Node<>(item, head.next, head);
        head.next.prev = node;
        head.next = node;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node<T> node = new Node<>(item, tail, tail.prev);
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }

    @Override
    public void printDeque() {
        Node<T> node = head;
        while (node.next != tail) {
            System.out.print(node.next.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        Node<T> first = head.next;
        first.next.prev = head;
        head.next = first.next;
        first.next = null;
        first.prev = null;
        size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        Node<T> last = tail.prev;
        tail.prev = last.prev;
        last.prev.next = tail;
        last.prev = null;
        last.next = null;
        size--;
        return last.item;

    }

    @Override
    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node<T> iterator = head.next;
        for (int i = 0; i < index; i++) {
            iterator = iterator.next;
        }
        return iterator.item;
    }

    /**
     * 使用递归方式获取元素
     *
     * @param index
     * @return
     */
    public T getRecursive(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        Node<T> node = recursiveGetNode(index, head.next);
        return node.item;
    }

    private Node<T> recursiveGetNode(int index, Node node) {
        if (index == 0) {
            return node;
        }
        return recursiveGetNode(--index, node.next);
    }

    /**
     * 内部节点类
     *
     * @param <T>
     */
    private static class Node<T> {
        T item;
        Node next;
        Node prev;

        public Node(T item) {
            this.item = item;
            this.next = null;
            this.prev = null;
        }

        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
}
