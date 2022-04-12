package com.demo.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.TimeUnit;

public class App {
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
                .recordStats()
                .expireAfterWrite(100, TimeUnit.MINUTES)
                .build(loader);

        System.out.println("cache.estimatedSize() = " + cache.estimatedSize());

    }
}
