
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
            throw new IndexOutOfBoundsException("Cannot removeLast from emptyArrayDeque");
        last = decrement(last);
        T lastItem = items[last];
        items[last] = null;
        size -= 1;
        checkAndResizeIfNecessary();
        return lastItem;
    }

    public T removeFirst() {
        if (isEmpty())
            throw new IndexOutOfBoundsException("Cannot removeFirst from empty ArrayDeque");
        checkAndResizeIfNecessary();
        first = increment(first);
        T firstItem = items[first];
        items[first] = null;
        size -= 1;
        checkAndResizeIfNecessary();
        return firstItem;
    }

    private int increment(int i) {
        int newIndex = i+1;
        return newIndex < capacity ? newIndex : 0;
    }

    private int decrement(int i) {
        int newIndex = i-1;
        return newIndex >= 0 ? newIndex : capacity-1;
    }

    private void checkAndResizeIfNecessary() {
        int newCapacity = capacity;

        if (size == capacity) {
            newCapacity = capacity * RESIZE_FACTOR;
            grow(newCapacity);
        } else if ((capacity > MINIMUM_CAPACITY) && ((float)size/capacity <= SHRINK_THRESHOLD)) {
            newCapacity = capacity / RESIZE_FACTOR;
            shrink(newCapacity);
        }
    }

    private void grow(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];

        int itemsToCopy_1 = capacity - first - 1;
        int itemsToCopy_2 = size - itemsToCopy_1;

        System.arraycopy(items, increment(first), newItems, 0, itemsToCopy_1);
        System.arraycopy(items, 0, newItems, itemsToCopy_1, itemsToCopy_2);

        capacity = newCapacity;
        items = newItems;
        last = itemsToCopy_1 + itemsToCopy_2;
        first = capacity - 1;
    }

    private void shrink(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];

        /* Capacity = 16, Size = 4, L = 2, F = 13
         *       L                     F 
         * [ * * - - - - - - - - - - - - * * ]
         */
        if (last < first) {
            int itemsToCopy_1 = capacity - first - 1;
            int itemsToCopy_2 = size - itemsToCopy_1;

            System.arraycopy(items, increment(first), newItems, 0, itemsToCopy_1);
            System.arraycopy(items, 0, newItems, itemsToCopy_1, itemsToCopy_2);

            capacity = newCapacity;
            items = newItems;
            last = itemsToCopy_1 + itemsToCopy_2;
            first = capacity - 1;

         /* F = 5, L = 8, Size = 2, Capacity = 16
          *             F     L           
          * [ - - - - - - * * - - - - - - - - ]
          */
        } else {
            int itemsToCopy = last - first - 1;
            System.arraycopy(items, increment(first), newItems, 0, itemsToCopy);

            capacity = newCapacity;
            items = newItems;
            last = itemsToCopy;
            first = capacity - 1;
        }
    }

    public void DEBUG_PRINT() {
        for(int i=0; i<capacity; i++) {
            System.out.print(items[i] == null ? " . " : " " + items[i] + " ");
        }
        System.out.println();
    }

    private static void testGrow() {
        System.out.println("Testing Growth of Capacity when Last less than First");
        boolean passed = true;
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        for(int i=0; i<8; i++) {
            AD.addLast(i);
            passed = AD.capacity == 8 & passed;
        }
        for(int i=100; i<100+8; i++) {
            AD.addLast(i);
            passed = AD.capacity == 16 & passed;
        }
        // TODO Count items in array manually
        System.out.println(passed ? "Success" : "Fail");

    }

    private static void testGrowAndShrink() {
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
        passed = AD.capacity == 16 && passed;

        while (AD.size() > 4) {
            AD.removeLast();
        }

        passed = AD.capacity == 8 && passed;

        System.out.println(passed ? "Success" : "Fail");
    }

    public static void testShrink_LastLessThanFirst() {
        System.out.println("Testing Shrink when Last less than First");
        boolean passed = true;
        Integer[] items = new Integer[]{0, 1, 9, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 2, 3};
        ArrayDeque<Integer> AD = new ArrayDeque<>();
        AD.items = items;
        AD.capacity = 16;
        AD.size = 5;
        AD.last = 3;
        AD.first = 13;

        AD.removeLast();
        passed = AD.capacity == 8 && passed;

        passed = AD.removeFirst() == 2 && passed;
        passed = AD.removeFirst() == 3 && passed;
        passed = AD.removeFirst() == 0 && passed;
        passed = AD.removeLast() == 1 && passed;

        System.out.println(passed ? "Success" : "Fail");
    }

    public static void main(String[] args) {
        testGrow();
        testGrowAndShrink();
        testShrink_LastLessThanFirst();
    }
}
