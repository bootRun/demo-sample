package com.demo.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.TimeUnit;

public class App2 {
    public static void main(String[] args) {

        // 缓存
        LoadingCache<String, String> cache;

        // 缓存加载器
        CacheLoader<String, String> loader;
        loader = new CacheLoader<String, String>() {
            @Override
            public @Nullable String load(@NonNull String key) throws Exception {
                return key.toUpperCase();
            }
        };

        cache = Caffeine.newBuilder()
                .maximumSize(2)
                .build(loader);

        cache.get("first");
        cache.get("second");
        cache.get("third");
        cache.get("forth");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("cache.estimatedSize() = " + cache.estimatedSize());

        //
        System.out.println("获取first键的值 "+cache.getIfPresent("first"));
        //
        System.out.println("获取的forth键的值  "+cache.getIfPresent("forth"));

    }
}
