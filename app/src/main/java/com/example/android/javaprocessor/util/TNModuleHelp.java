package com.example.android.javaprocessor.util;

import com.example.android.javaprocessor.base.TNModule;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class TNModuleHelp {

    static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(1, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
    private final ModuleCachePool cachePool = new ModuleCachePool();

    private TNModuleHelp() {
        EXECUTOR.allowCoreThreadTimeOut(true);
    }

    private static class Lazy {
        static TNModuleHelp sRouterHelper = new TNModuleHelp();
    }

    public static TNModuleHelp getInstance() {
        return TNModuleHelp.Lazy.sRouterHelper;
    }

    public TNModule findTNModuleByKey(String key) {
        return cachePool.findTNModuleByName(key);
    }

    public void addToTNModulePool(String key, TNModule m) {
        cachePool.addToMirrorPool(key, m);
    }


}
