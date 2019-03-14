package bearmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/** A very basic implementation of the ExtrinsicMinPQ.
 *  Operations have very poor performance, but it's at least
 *  correct, with one exception: The add method
 *  should throw an exception if the item already exists,
 *  but doing so makes the add method painfully slow to the
 *  point where this class is very difficult to use for testing.
 *  @author Matt Owen @since 03-11-19 */
public class NaiveMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> items;

    public NaiveMinPQ() {
        items = new ArrayList<>();
    }

    /** Note this method does not throw the proper exception,
     *  otherwise it is painfully slow (linear time).
     */
    @Override
    public void add(T item, double priority) {
        items.add(new PriorityNode(item, priority));
    }

    @Override
    public boolean contains(T item) {
        return items.contains(new PriorityNode(item, 0));
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return Collections.min(items).getItem();
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        int minInd = indOf(getSmallest());
        return items.remove(minInd).getItem();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (contains(item) == false) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        items.get(indOf(item)).setPriority(priority);
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return items.size();
    }

    private int indOf(T elem) {
        return items.indexOf(new PriorityNode(elem, 0));
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
