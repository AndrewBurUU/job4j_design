package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        head = new Node<T>(value, head);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T t = head.value;
        Node<T> delHead = head;
        head = head.next;
        delHead.next = null;
        delHead.value = null;
        return t;
    }

    public boolean revert() {
        boolean res = !(head == null || head.next == null);
        if (res) {
            Node<T> prevEl = null;
            Node<T> curEl = head;
            Node<T> nextEl = head.next;
            while (curEl != null) {
                nextEl = curEl.next;
                curEl.next = prevEl;
                prevEl = curEl;
                curEl = nextEl;
            }
            head = prevEl;
        }
        return res;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
