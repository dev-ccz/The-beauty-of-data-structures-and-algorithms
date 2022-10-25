package com.zcc._20_hash;

import java.util.HashMap;

/**
 * @author Zcc
 * created on 22/10/24 20:31
 */
public class LruCacheTest {
    public static void main(String[] args) {
        LruCache<String> cache = new LruCache<>(9);
        cache.add("0");
        cache.add("1");
        cache.add("2");
        cache.add("3");
        cache.add("4");
        cache.add("5");
        cache.add("6");
        cache.add("7");
        cache.add("8");
        cache.add("0");
        cache.add("9");
        cache.delete("0");
        cache.delete("5");
        cache.delete("9");
        cache.delete("2");
        for (;;);
    }
}
