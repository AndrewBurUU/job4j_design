package ru.job4j.gc.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key) {
        cache.put(key, new SoftReference<>(load(key)));
    }

    public V get(K key) {
        return cache.get(key).get();
    }

    protected abstract V load(K key);

}
