package com.singly.linked.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList {
    private Node first;

    private void checkFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }
    }

    public SinglyLinkedList() {
        this.first = null;
    }

    public Object getFirst() {
        this.checkFirst();
        return this.first.data;
    }

    public Object removeFirst() {
        this.checkFirst();
        Object element = this.first.data;
        this.first = this.first.next;
        return element;
    }

    public void addFirst(Object data) {
        Node newNode = new Node(data, this.first);
        this.first = newNode;
    }

    public void addLast(Object data) {
        if (this.first == null) {
            this.addFirst(data);
        } else {
            Node last = this.first;
            while (last.next != null) {
                last = last.next;
            }
            Node newNode = new Node(data);
            last.next = newNode;
        }
    }

    public ListIterator listIterator() {
        return new SinglyLinkedListIterator();
    }

    class Node {
        protected Object data;
        protected Node next;

        public Node(Object data) {
            this.data = data;
        }

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    class SinglyLinkedListIterator implements ListIterator {
        private Node position;
        private Node previous;
        private boolean isAfterNext;

        public SinglyLinkedListIterator() {
            this.position = null;
            this.position = null;
            this.isAfterNext = false;
        }

        @Override
        public boolean hasNext() {
            if (!this.isAfterNext) {
                return SinglyLinkedList.this.first != null;
            }
            return this.position.next != null;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.previous = this.position;
            this.isAfterNext = true;
            if (this.position == null) {
                this.position = SinglyLinkedList.this.first;
            } else {
                this.position = this.position.next;
            }
            return this.position.data;
        }

        @Override
        public void add(Object element) {
            if (this.position == null) {
                SinglyLinkedList.this.addFirst(element);
                this.position = SinglyLinkedList.this.first;
            } else {
                Node newNode = new Node(element, this.position.next);
                this.position.next = newNode;
                this.position = newNode;
            }
            this.isAfterNext = false;
        }

        @Override
        public void remove() {
            if (!this.isAfterNext) {
                throw new IllegalStateException();
            }
            if (this.position == SinglyLinkedList.this.first) {
                SinglyLinkedList.this.removeFirst();
            } else {
                this.previous.next = this.position.next;
            }
            this.position = this.previous;
            this.isAfterNext = false;
        }

        @Override
        public void set(Object element) {
            if (!this.isAfterNext) {
                throw new IllegalStateException();
            }
            this.position.data = element;
        }
    }
}