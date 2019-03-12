package bearmaps;

import java.util.ArrayList;
import java.util.Collections;

/** A very basic implementation of the ExtrinsicMinPQ.
 *  Operations have very poor performance, but it's at least
 *  correct. @author Matt Owen @since 03-11-19 */
public class NaiveMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> items;

    public NaiveMinPQ() {
        items = new ArrayList<>();
    }

    @Override
    public void add(T item, double priority) {
        items.add(new PriorityNode(item, priority));
    }

    @Override
    public boolean contains(T item) {
        return items.contains(new PriorityNode(item, 0));
    }

    /* Returns the minimum item. Also known as "min". */
    @Override
    public T getSmallest() {
        return Collections.min(items).getItem();
    }

    /* Removes and returns the minimum item. Also known as "dequeue". */
    @Override
    public T removeSmallest() {
        int minInd = indOf(getSmallest());
        return items.remove(minInd).getItem();
    }

    /* Changes the priority of the given item. Behavior undefined if item doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
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
