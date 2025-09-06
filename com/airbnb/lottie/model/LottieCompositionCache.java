package com.airbnb.lottie.model;

import androidx.collection.LruCache;
import com.airbnb.lottie.LottieComposition;

public class LottieCompositionCache {
    private static final LottieCompositionCache INSTANCE;
    private final LruCache cache;

    static {
        LottieCompositionCache.INSTANCE = new LottieCompositionCache();
    }

    LottieCompositionCache() {
        this.cache = new LruCache(20);
    }

    public void clear() {
        this.cache.evictAll();
    }

    public LottieComposition get(String s) {
        return s == null ? null : ((LottieComposition)this.cache.get(s));
    }

    public static LottieCompositionCache getInstance() {
        return LottieCompositionCache.INSTANCE;
    }

    public void put(String s, LottieComposition lottieComposition0) {
        if(s == null) {
            return;
        }
        this.cache.put(s, lottieComposition0);
    }

    public void resize(int v) {
        this.cache.resize(v);
    }
}

