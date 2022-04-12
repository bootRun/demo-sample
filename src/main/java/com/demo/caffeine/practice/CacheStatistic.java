package com.demo.caffeine.practice;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CacheStatistic {

    public static LoadingCache<String, String> cache;

    static {
        Executors.newSingleThreadScheduledExecutor()
                .scheduleWithFixedDelay(() -> {
                    CacheStats cacheStats = cache.stats();
                    System.out.println("cacheStats = " + cacheStats);
                    System.out.println("cacheStats.hitCount() = " + cacheStats.hitCount());
                }, 2, 2, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {

        CacheLoader<String, String> loader;

        loader = new CacheLoader<String, String>() {
            @Override
            public @Nullable String load(@NonNull String key) throws Exception {
                return key.toUpperCase();
            }
        };


        cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .recordStats()
                .build(loader);

        for (int i = 0; i < 10; i++) {
            String value = cache.get("hello-" + i);
            System.out.println("value = " + value);
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("cache.get(\"HELLO-1\") = " + cache.get("HELLO-1"));
        System.out.println("cache.get(\"hello-1\") = " + cache.get("hello-1"));
    }
}
