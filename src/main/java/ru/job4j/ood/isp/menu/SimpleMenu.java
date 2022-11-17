package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        Optional<ItemInfo> optionalParentItemInfo = findItem(parentName);
        if (optionalParentItemInfo.isEmpty()) {
            SimpleMenuItem parentMenuItem = new SimpleMenuItem(parentName, null);
            SimpleMenuItem childMenuItem = new SimpleMenuItem(childName, actionDelegate);
            parentMenuItem.children.add(childMenuItem);
            rootElements.add(parentMenuItem);
            return true;
        }
        if (findItem(childName).isEmpty()) {
            SimpleMenuItem childMenuItem = new SimpleMenuItem(childName, actionDelegate);
            ItemInfo parentItemInfo = optionalParentItemInfo.get();
            parentItemInfo.menuItem.getChildren().add(childMenuItem);
            rootElements.add(parentItemInfo.menuItem);
            return true;
        }
        return false;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<ItemInfo> optionalItemInfo = findItem(itemName);
        if (optionalItemInfo.isPresent()) {
            ItemInfo itemInfo = optionalItemInfo.get();
            MenuItemInfo menuItemInfo = new MenuItemInfo(
                    itemInfo.menuItem,
                    itemInfo.number);
            return Optional.of(menuItemInfo);
        }
        return null;
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return null;
    }

    private Optional<ItemInfo> findItem(String name) {
        DFSIterator dfsIterator = new DFSIterator();
        while (dfsIterator.hasNext()) {
            ItemInfo iteratorItemInfo = dfsIterator.next();
            if (name.equals(iteratorItemInfo.menuItem.getName())) {
                return Optional.of(iteratorItemInfo);
            }
        }
        return null;
    }

    private static class SimpleMenuItem implements MenuItem {

        private String name;
        private List<MenuItem> children = new ArrayList<>();
        private ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {

        Deque<MenuItem> stack = new LinkedList<>();

        Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }

    }

    private class ItemInfo {

        MenuItem menuItem;
        String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }

}
