package bearmaps.proj2ab;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.NoSuchElementException;


/** This is a PQ implementation you can use if you don't want to use
 *  your ArrayHeapMinPQ from proj2a.
 **/
public class DoubleMapPQ<T> implements ExtrinsicMinPQ<T> {
    TreeMap<Double, Set<T>> priorityToItem = new TreeMap<>();
    HashMap<T, Double> itemToPriority = new HashMap<>();

    private static <K> K getItem(Set<K> s) {
        Iterator<K> i = s.iterator();
        return i.next();
    }

    @Override
    public void add(T item, double priority) {
        if (itemToPriority.containsKey(item)) {
            throw new IllegalArgumentException("Already contains " + item);
        }
        if (!priorityToItem.containsKey(priority)) {
            priorityToItem.put(priority, new HashSet<T>());
        }
        Set<T> itemsWithPriority = priorityToItem.get(priority);
        itemsWithPriority.add(item);
        itemToPriority.put(item, priority);
    }

    @Override
    public boolean contains(T item) {
        return itemToPriority.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (itemToPriority.size() == 0) {
            throw new NoSuchElementException("PQ is empty.");
        }
        Set<T> itemsWithlowestPriority = priorityToItem.get(priorityToItem.firstKey());
        return getItem(itemsWithlowestPriority);
    }

    @Override
    public T removeSmallest() {
        if (itemToPriority.size() == 0) {
            throw new NoSuchElementException("PQ is empty.");
        }

        double lowestPriority = priorityToItem.firstKey();

        Set<T> itemsWithlowestPriority = priorityToItem.get(lowestPriority);
        T item = getItem(itemsWithlowestPriority);

        itemsWithlowestPriority.remove(item);
        if (itemsWithlowestPriority.size() == 0) {
            priorityToItem.remove(lowestPriority);
        }
        itemToPriority.remove(item);
        return item;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new IllegalArgumentException(item + " not in PQ.");
        }

        double oldP = itemToPriority.get(item);
        Set<T> itemsWithOldPriority = priorityToItem.get(oldP);
        itemsWithOldPriority.remove(item);

        if (itemsWithOldPriority.size() == 0) {
            priorityToItem.remove(oldP);
        }

        itemToPriority.remove(item);
        add(item, priority);
    }

    @Override
    public int size() {
        return itemToPriority.size();
    }
}
