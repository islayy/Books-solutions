package com.singly.linked.list;

public interface ListIterator {
    Object next();
    boolean hasNext();
    void add(Object element);
    void remove();
    void set(Object element);
}
