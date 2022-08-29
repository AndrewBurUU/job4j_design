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
        boolean res = true;
        modCount++;
        if (count / capacity >= LOAD_FACTOR) {
            expand();
            res = false;
        } else {
            table[getIndex(key)] = new MapEntry<>(key, value);
            count++;
        }
        return res;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> capacity);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        modCount = 0;
        capacity = capacity * 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (int i = 0; i < count; i++) {
            MapEntry<K, V> mapEntry = table[i];
            newTable[getIndex(mapEntry.key)] = new MapEntry<>(mapEntry.key, mapEntry.value);
        }
        table = new MapEntry[capacity];
        for (int i = 0; i < newTable.length; i++) {
            if (newTable[i] != null) {
                MapEntry<K, V> mapEntry = newTable[i];
                put(mapEntry.key, mapEntry.value);
            }
        }
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
        MapEntry<K, V> mapEntry = table[getIndex(key)];
        if (mapEntry.key == null && key == null) {
            v = mapEntry.value;
        } else {
            int elementHashCode = mapEntry.key.hashCode();
            if (elementHashCode == getHashCode(key)) {
                if (mapEntry.key.equals(key)) {
                    v = mapEntry.value;
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
                int elementHashCode = mapEntry.key.hashCode();
                if (elementHashCode == getHashCode(key)) {
                    if (mapEntry.key.equals(key)) {
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
                return point < count;
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

}
