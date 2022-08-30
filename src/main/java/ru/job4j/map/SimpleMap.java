package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean res = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int bucket = getIndex(key);
        if (table[bucket] == null) {
            table[bucket] = new MapEntry<>(key, value);
            count++;
            modCount++;
            res = true;
        }
        return res;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        modCount = 0;
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> mapEntry = table[i];
                newTable[getIndex(mapEntry.key)] = new MapEntry<>(mapEntry.key, mapEntry.value);
            }
        }
        table = newTable;
    }

    private int getIndex(K key) {
        int res = hash(getHashCode(key));
        return indexFor(res);
    }

    private int getHashCode(K key) {
        return key == null ? 0 : key.hashCode();
    }

    @Override
    public V get(K key) {
        V v = null;
        if (table[getIndex(key)] != null) {
            MapEntry<K, V> mapEntry = table[getIndex(key)];
            if (mapEntry.key == null && key == null) {
                v = mapEntry.value;
            } else {
                int elementHashCode = Objects.hashCode(mapEntry.key);
                if (elementHashCode == getHashCode(key)) {
                    if (Objects.equals(mapEntry.key, key)) {
                        v = mapEntry.value;
                    }
                }
            }
        }
        return v;
    }

    @Override
    public boolean remove(K key) {
        boolean res = true;
        modCount++;
        V v = get(key);
        if (v == null) {
            res = false;
        } else {
            int keyBucket = getIndex(key);
            MapEntry<K, V> mapEntry = table[keyBucket];
            if (mapEntry.key == null && key == null) {
                table[keyBucket] = null;
            } else {
                int elementHashCode = Objects.hashCode(mapEntry.key);
                if (elementHashCode == getHashCode(key)) {
                    if (Objects.equals(mapEntry.key, key)) {
                        table[keyBucket] = null;
                    }
                }
            }
            count--;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int expectedModCount = modCount;
            int point = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (point < table.length && table[point] == null) {
                    point++;
                }
                return point < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MapEntry<K, V> mapEntry = table[point];
                point++;
                return mapEntry.key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

    public static void main(String[] args) {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "3");
        map.put(4, "4");
        map.put(null, "0000");
        map.put(15, "15");
    }
}
