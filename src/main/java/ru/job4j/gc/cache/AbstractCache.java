package ru.job4j.gc.cache;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key) throws IOException {
        cache.put(key, new SoftReference<>(load(key)));
    }

    public V get(K key) throws IOException {
        Object object = new Object();
        object = cache.get(key).get();
        if (object == null) {
            object = this.load(key);
            this.put(key);
        }
        return (V) object;
    }

    protected abstract V load(K key) throws IOException;

}
