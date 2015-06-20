package com.doubly.linked.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.lang.reflect.Field;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest {
    private DoublyLinkedList list;

    @Before
    public void initLinkedList() {
        this.list = new DoublyLinkedList();
    }

    @Test
    public void constructor() throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        Field first = this.list.getClass().getDeclaredField("first");
        first.setAccessible(true);
        Field last = this.list.getClass().getDeclaredField("last");
        last.setAccessible(true);
        assertNull(first.get(this.list));
        assertNull(last.get(this.list));
    }

    @Test(expected = NoSuchElementException.class)
    public void getFirstWithNullFirst() {
        this.list.getFirst();
    }

    @Test
    public void getFirst() {
        this.list.addLast('A');
        assertEquals(this.list.getFirst(), 'A');
    }

    @Test
    public void addFirst() throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        Field first = this.list.getClass().getDeclaredField("first");
        first.setAccessible(true);
        this.list.addFirst('a');
        assertNotNull(first.get(this.list));
        assertEquals(1, this.list.size);
    }

    @Test
    public void addFirstAgain() throws NoSuchFieldException, SecurityException,
    IllegalArgumentException, IllegalAccessException {
        Field first = this.list.getClass().getDeclaredField("first");
        first.setAccessible(true);
        this.list.addFirst('a');
        this.list.addFirst('a');
        assertNotNull(first.get(this.list));
        assertEquals(2, this.list.size);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstWithNullFirst() {
        this.list.removeFirst();
    }

    @Test
    public void removeFirst() {
        this.list.addFirst('A');
        assertEquals('A', this.list.removeFirst());
        assertEquals(0, this.list.size);
    }

    @Test(expected = NoSuchElementException.class)
    public void getLastWithNullLast() {
        this.list.getLast();
    }

    @Test
    public void getLast() {
        this.list.addLast('a');
        assertEquals('a', this.list.getLast());
    }

    @Test
    public void addLast() {
        this.list.addLast('a');
        this.list.addLast('b');
        assertEquals(2, this.list.size);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastWithNullLast() {
        this.list.removeLast();
    }

    @Test
    public void removeLast() {
        this.list.addLast('a');
        assertEquals('a', this.list.removeLast());
        assertEquals(0, this.list.size);
    }
}
