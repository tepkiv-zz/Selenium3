package fw.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;

public class ModifiedSortedList<T extends Comparable<T>> extends ForwardingList<T> {

    private List<T> list = Lists.newArrayList();

    public ModifiedSortedList() {
    }

    public ModifiedSortedList(ModifiedSortedList<T> listToCopy) {
        list = Lists.newArrayList(listToCopy.list);
        Collections.sort(list);
    }

    public ModifiedSortedList(List<T> listToCopy) {
        list = Lists.newArrayList(listToCopy);
        Collections.sort(list);
    }

    @Override
    protected List<T> delegate() {
        return list;
    }

    @Override
    public boolean add(T element) {
        boolean temp = super.add(element);
        Collections.sort(list);
        return temp;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean temp = super.addAll(collection);
        Collections.sort(list);
        return temp;
    }

    public ModifiedSortedList<T> withAdded(T item) {
        ModifiedSortedList<T> newItems = new ModifiedSortedList<T>();
        newItems.list = Lists.newArrayList(this.list);
        newItems.list.add(item);
        Collections.sort(newItems.list);
        return newItems;
    }

    public ModifiedSortedList<T> without(T item) {
        ModifiedSortedList<T> newItems = new ModifiedSortedList<T>();
        newItems.list = Lists.newArrayList(this.list);
        newItems.list.remove(item);
        return newItems;
    }

    public ModifiedSortedList<T> without(int index) {
        ModifiedSortedList<T> newItems = new ModifiedSortedList<T>();
        newItems.list = Lists.newArrayList(this.list);
        newItems.list.remove(index);
        return newItems;
    }

    public T getSome() {
        if (size() == 0) {
            return null;
        } else {
            return list.get(new Random().nextInt(size()));
        }
    }
}