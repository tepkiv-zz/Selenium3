package com.utils;

import java.util.List;
import java.util.Random;

import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;

public class ModifiedList<T> extends ForwardingList<T> {

    private List<T> list = Lists.newArrayList();

    public ModifiedList() {
    }

    public ModifiedList(ModifiedList<T> listToCopy) {
        list = Lists.newArrayList(listToCopy);
    }

    @Override
    protected List<T> delegate() {
        return list;
    }

    public ModifiedList<T> withAppended(T item) {
        ModifiedList<T> newItems = new ModifiedList<T>();
        newItems.list = Lists.newArrayList(this.list);
        newItems.list.add(item);
        return newItems;
    }

    public ModifiedList<T> without(T item) {
        ModifiedList<T> newItems = new ModifiedList<T>();
        newItems.list = Lists.newArrayList(this.list);
        newItems.list.remove(item);
        return newItems;
    }

    public ModifiedList<T> withPrepended(T item) {
        ModifiedList<T> newItems = new ModifiedList<T>();
        newItems.list = Lists.newArrayList();
        newItems.list.add(item);
        newItems.list.addAll(this.list);
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