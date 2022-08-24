package ru.job4j.collection;

import java.util.*;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> first;
    private Node<E> last;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int point = 0;
        E res = first.item;
        while (point <= index) {
            res = iterator().next();
            point++;
        }
        return res;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> eNode = first;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E e = eNode.item;
                eNode = eNode.next;
                point++;
                return e;
            }
        };
    }

    public static void main(String[] args) {
        LinkedList<Integer> list;
        list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.get(1));
    }
}
