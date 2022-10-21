package ru.job4j.gc.cache;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V v) {
        cache.put(key, new SoftReference<>(v));
    }

    public V get(K key) throws IOException {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            value = this.load(key);
            this.put(key, value);
        }
        return value;
    }

    protected abstract V load(K key) throws IOException;

}
