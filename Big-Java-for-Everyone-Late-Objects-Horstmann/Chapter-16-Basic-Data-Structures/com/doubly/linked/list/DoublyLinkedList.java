package com.doubly.linked.list;

import java.util.NoSuchElementException;

public class DoublyLinkedList {
    private Node first;
    private Node last;
    public int size;

    public DoublyLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    private void checkFirst() {
        if (this.first == null) {
            throw new NoSuchElementException();
        }
    }

    public Object getFirst() {
        this.checkFirst();
        return this.first.data;
    }

    public void addFirst(Object data) {
        Node newNode = new Node(null, data, this.first);
        if (this.first == null) {
            this.last = newNode;
        } else {
            this.first.previous = newNode;
        }
        this.first = newNode;
        this.size++;
    }

    public Object removeFirst() {
        this.checkFirst();
        Object element = this.first.data;
        this.first = this.first.next;
        if (this.first == null) {
            this.last = null;
        } else {
            this.first.previous = null;
        }
        this.size--;
        return element;
    }

    private void checkLast() {
        if (this.last == null) {
            throw new NoSuchElementException();
        }
    }

    public Object getLast() {
        this.checkLast();
        return this.last.data;
    }

    public Object removeLast() {
        this.checkLast();
        Object data = this.last.data;
        this.last = this.last.previous;
        if (this.last == null) {
            this.first = null;
        } else {
            this.last.next = null;
        }
        this.size--;
        return data;
    }

    public void addLast(Object data) {
        Node newNode = new Node(this.last, data, null);
        if (this.last == null) {
            this.first = newNode;
        } else {
            this.last.next = newNode;
        }
        this.size++;
        this.last = newNode;
    }

    class Node {
        public Node previous;
        public Object data;
        public Node next;

        public Node(Node previous, Object data, Node next) {
            this.previous = previous;
            this.data = data;
            this.next = next;
        }
    }

    public ListIterator listIterator() {
        return new LinkedListIterator();
    }

    class LinkedListIterator implements ListIterator {
        private Node position;
        private boolean isAfterNext;
        private boolean isAfterPrevious;

        public LinkedListIterator() {
            this.position = null;
            this.isAfterNext = this.isAfterPrevious = false;
        }

        private void setForward() {
            this.isAfterNext = true;
            this.isAfterPrevious = false;
        }

        private void setBackwards() {
            this.isAfterNext = false;
            this.isAfterPrevious = true;
        }

        @Override
        public Object next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            this.setForward();
            if (this.position == null) {
                this.position = DoublyLinkedList.this.first;
            } else {
                this.position = this.position.next;
            }
            return this.position.data;
        }

        @Override
        public Object previous() {
            if (!this.hasPrevious()) {
                throw new NoSuchElementException();
            }
            this.setBackwards();
            Object data = this.position.data;
            this.position = this.position.previous;
            return data;
        }

        @Override
        public boolean hasNext() {
            if (this.position == null) {
                return DoublyLinkedList.this.first != null;
            } else {
                return this.position.next != null;
            }
        }

        @Override
        public boolean hasPrevious() {
            return this.position != null;
        }

        @Override
        public void add(Object element) {
            if (this.position == null) {
                DoublyLinkedList.this.addFirst(element);
                this.position = DoublyLinkedList.this.first;
            } else {
                Node newNode = new Node(this.position, element,
                        this.position.next);
                newNode.previous.next = newNode;
                newNode.next.previous = newNode;
                this.position = newNode;
            }
            this.isAfterNext = false;
            this.isAfterPrevious = false;
        }

        @Override
        public void remove() {
            Node toBeRemoved = this.lastPosition();

            if (toBeRemoved == DoublyLinkedList.this.first) {
                DoublyLinkedList.this.removeFirst();
            } else if (toBeRemoved == DoublyLinkedList.this.last) {
                DoublyLinkedList.this.removeLast();
            } else {
                toBeRemoved.previous.next = toBeRemoved.next;
                toBeRemoved.next.previous = toBeRemoved.previous;
            }

            if (this.isAfterNext) {
                this.position = this.position.previous;
            }
            this.isAfterNext = false;
            this.isAfterPrevious = false;
        }

        @Override
        public void set(Object element) {
            Node toBeSet = this.lastPosition();
            toBeSet.data = element;
        }

        private Node lastPosition() {
            if (!this.isAfterNext && !this.isAfterPrevious) {
                throw new IllegalStateException();
            } else if (this.isAfterNext) {
                return this.position;
            } else {
                if (this.position == null) {
                    return DoublyLinkedList.this.first;
                } else {
                    return this.position.next;
                }
            }
        }
    }
}
