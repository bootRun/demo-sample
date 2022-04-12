package com.demo.caffeine;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PreloadCache {

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

        cache = Caffeine.newBuilder().build(loader);

        //3.创建一个map
        Map<String,String> map=new HashMap<String,String>();
        map.put("first", "FIRST");
        map.put("second", "SECOND");

        cache.putAll(map);


        Map<@NonNull String, @NonNull String> all = cache.getAll(Arrays.asList("first", "second"));
        System.out.println("all = " + all);

    }

}
