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

    public V get(K key) {
        V value = cache.getOrDefault(key, new SoftReference<>(null)).get();
        if (value == null) {
            try {
                value = this.load(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.put(key, value);
        }
        return value;
    }

    protected abstract V load(K key) throws IOException;

}
