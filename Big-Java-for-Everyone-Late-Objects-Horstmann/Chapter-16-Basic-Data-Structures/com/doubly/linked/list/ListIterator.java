package com.doubly.linked.list;

public interface ListIterator {
    Object next();

    Object previous();

    boolean hasNext();

    boolean hasPrevious();

    void add(Object element);

    void remove();

    void set(Object element);
}
