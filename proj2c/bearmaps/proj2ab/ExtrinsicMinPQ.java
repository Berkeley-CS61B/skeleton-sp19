package bearmaps.proj2ab;
/**
 * Priority queue where objects have a priority that is provided
 * extrinsically, i.e. are are supplied as an argument during insertion
 * and can be changed using the changePriority method.
 */
public interface ExtrinsicMinPQ<T> {
    /* Inserts an item with the given priority value. */
    void add(T item, double priority);
    /* Returns true if the PQ contains the given item. */
    boolean contains(T item);
    /* Returns the minimum item. */
    T getSmallest();
    /* Removes and returns the minimum item. */
    T removeSmallest();
    /* Changes the priority of the given item. Behavior undefined if the item doesn't exist. */
    void changePriority(T item, double priority);
    /* Returns the number of items in the PQ. */
    int size();
}
