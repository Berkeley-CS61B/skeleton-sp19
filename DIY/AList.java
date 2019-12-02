/**
 * Invariants:
 * The position of the next item to be inserted is always size
 * size is always the number of items in the AList
 * The last item in the list is always in size - 1
 */
public class AList<T> {
    private T[] items;
    private int size;
    private int capacity;

    private static final int MINIMUM_CAPACITY = 8;
    private static final int RESIZE_FACTOR = 2;
    private static final float SHRINK_THRESHOLD = 1f / 4;

    public AList() {
        size = 0;
        capacity = MINIMUM_CAPACITY;
        items = (T[]) new Object[capacity];
    }

    public void addLast(T v) {
        checkAndResizeIfNecessary();
        items[size] = v;
        size += 1;
    }

    public T getLast() {
        if (size == 0)
            throw new IndexOutOfBoundsException();
        return items[size-1]; 
    }

    public T get(int i) {
        if (i < 0 || i > size - 1)
            throw new IndexOutOfBoundsException();
        return items[i];
    }

    public int size() {
        return size; 
    }

    /*
    public T getFirst() {}
    public T removeFirst() {}
    */

    public T removeLast() {
        if (size < 1)
            throw new IndexOutOfBoundsException();
        checkAndResizeIfNecessary();
        size -= 1;
        T lastItem = items[size];
        items[size] = null;
        return lastItem;
    }

    public void print() {
        for(int i=0; i<size; i++)
            System.out.print(items[i] + " ");
        System.out.println();
    }

    private void checkAndResizeIfNecessary() {
        int newCapacity = capacity;
        T[] newItems;
        boolean doResize = false;

        if (size >= capacity) {
            newCapacity = capacity * RESIZE_FACTOR;
            doResize = true;
        } else if ((capacity > MINIMUM_CAPACITY) && ((float)size/capacity < SHRINK_THRESHOLD)) {
            newCapacity = capacity / RESIZE_FACTOR;
            doResize = true;
        }

        if (doResize) {
            newItems = (T[]) new Object[newCapacity];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
            capacity = newCapacity;
        }
    }

    public static void main(String[] args) {
        AList<Integer> AL = new AList<>();
        for(int i=0; i<24; i++)
            AL.addLast(i);

        System.out.println("           Size: " + AL.size());
        System.out.print("          Items: "); 
        AL.print();
        System.out.println("       Capacity: " + AL.capacity);
        System.out.println("AL.removeLast(): " + AL.removeLast());
        System.out.println("AL.removeLast(): " + AL.removeLast());
        System.out.print("          Items: "); 
        AL.print();

        try {
            System.out.println("AL.removeLast(): " + AL.removeLast());
        } catch (Exception e) {
            System.out.println("Caught error! " + e);
        }

        AL.addLast(3);

        System.out.println("           Size: " + AL.size());
        System.out.print("          Items: "); 
        AL.print();

        while (AL.size() > 0) {
            System.out.println("AL.removeLast(): " + AL.removeLast());
            System.out.println("       Capacity: " + AL.capacity);
        }


    } 
}
