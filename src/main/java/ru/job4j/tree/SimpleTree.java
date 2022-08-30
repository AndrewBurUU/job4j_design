package ru.job4j.tree;

import java.util.*;
import java.util.function.*;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> aParent = findBy(parent);
        if (aParent.isPresent()) {
            if (findBy(child).isEmpty()) {
                Node<E> nodeParent = aParent.get();
                Node<E> nodeChild = new Node<>(child);
                nodeParent.children.add(nodeChild);
                rsl = true;
            }
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> condition = eNode -> eNode.value.equals(value);
        return findByPredicate(condition);
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> condition = eNode -> eNode.children.size() > 2;
        return findByPredicate(condition).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
