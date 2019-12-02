
public class ArrayDeque<T> {
    /**
     * Invariants:
     *  addLast puts item in position last
     *  addFirst puts item in position first
     */
    private static final int MINIMUM_CAPACITY = 8;
    private static final int RESIZE_FACTOR = 2;
    private static final float SHRINK_THRESHOLD = 1.0f / 4;

    private T[] items;
    private int size;
    private int first;
    private int last;
    private int capacity;

    public ArrayDeque() {
        size = 0;
        capacity = MINIMUM_CAPACITY;
        items = (T[]) new Object[capacity];
        last = 0;
        first = capacity - 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T v) {
        checkAndResizeIfNecessary();
        items[first] = v;
        first = decrement(first);
        size += 1;
    }

    public void addLast(T v) {
        checkAndResizeIfNecessary();
        items[last] = v;
        last = increment(last);
        size += 1;
    }

    public T removeLast() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        last = decrement(last);
        T lastItem = items[last];
        items[last] = null;
        size -= 1;
        checkAndResizeIfNecessary();
        return lastItem;
    }

    public T removeFirst() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        checkAndResizeIfNecessary();
        return null;
    }

    public void printDeque() {
    }

    private int increment(int i) {
        int newIndex = i+1;
        return newIndex < capacity ? newIndex : 0;
    }

    private int decrement(int i) {
        int newIndex = i-1;
        return newIndex >= 0 ? newIndex : capacity-1;
    }

    /**
     * TODO Handle resize up when first < last and last < first
     * TODO Handle resize down when first < last and last < first
     */
    private void checkAndResizeIfNecessary() {
        int newCapacity = capacity;
        boolean doResize = false;

        if (size >= capacity) {
            doResize = true;
            newCapacity = capacity * RESIZE_FACTOR;
        } else if ((capacity > MINIMUM_CAPACITY) && ((float)size/capacity < SHRINK_THRESHOLD)) {
            doResize = true;
            newCapacity = capacity / RESIZE_FACTOR;
        }

        if (doResize) {
            if (newCapacity > capacity)
                grow();
            else
                shrink();
        }
    }

    private void grow() {}
    private void shrink() {}

    private static void testGrowCapacity_LastLessThanFirst() {
        System.out.println("Testing Growth of Capacity when Last less than First");
        boolean passed = true;
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        for(int i=0; i<8; i++) {
            AD.addLast(i);
            passed = AD.capacity == 8 & passed;
        }
        for(int i=0; i<8; i++) {
            AD.addLast(i);
            passed = AD.capacity == 16 & passed;
        }
        System.out.println(passed ? "Success" : "Fail");
    }

    private static void testGrowCapacity_FirstLessThanLast() {
        System.out.println("Testing Growth of Capacity when First less than Last");
        boolean passed = true;
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        AD.addFirst(0);
        AD.addFirst(1);
        AD.addFirst(2);
        passed = AD.capacity == 8 && passed; // no change to capacity yet

        passed = AD.removeLast() == 0 && passed;
        passed = AD.capacity == 8 && passed; // no change to capacity yet
        passed = AD.removeLast() == 1 && passed;
        passed = AD.capacity == 8 && passed; // no change to capacity yet
        passed = AD.removeLast() == 2 && passed;
        passed = AD.capacity == 8 && passed; // no change to capacity yet

        passed = AD.isEmpty() && passed;

        // Cause a resize and remove last
        for(int i=0; i<12; i++) {
            AD.addFirst(i);
        }
        passed = AD.capacity == 16 && passed; // no change to capacity yet

        while (AD.size() >= 4) {
            AD.removeLast();
            System.out.println(AD.size() + " / " + AD.capacity);
        }
        passed = AD.capacity == 8 && passed; // no change to capacity yet

        System.out.println(passed ? "Success" : "Fail");
    }

    public static void main(String[] args) {
        testGrowCapacity_LastLessThanFirst();
        testGrowCapacity_FirstLessThanLast();
    }
}
