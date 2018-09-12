package com.example.android.javaprocessor.util;

import android.util.LruCache;

import com.example.android.javaprocessor.base.TNModule;


public class ModuleCachePool {

    private final LruCache<String, TNModule> mirrorPool = new LruCache<>(10);

    synchronized void addToMirrorPool(String key, TNModule m) {
        if (key != null && m != null && mirrorPool.get(key) == null)
            mirrorPool.put(key, m);
    }

    synchronized TNModule findTNModuleByName(String key) {
        if (key != null)
            return mirrorPool.get(key);
        return null;
    }

}
