package com.android.volley.toolbox;

import com.android.volley.AsyncCache.OnGetCompleteCallback;
import com.android.volley.AsyncCache.OnWriteCompleteCallback;
import com.android.volley.AsyncCache;
import com.android.volley.Cache.Entry;

public class NoAsyncCache extends AsyncCache {
    @Override  // com.android.volley.AsyncCache
    public void clear(OnWriteCompleteCallback asyncCache$OnWriteCompleteCallback0) {
        asyncCache$OnWriteCompleteCallback0.onWriteComplete();
    }

    @Override  // com.android.volley.AsyncCache
    public void get(String s, OnGetCompleteCallback asyncCache$OnGetCompleteCallback0) {
        asyncCache$OnGetCompleteCallback0.onGetComplete(null);
    }

    @Override  // com.android.volley.AsyncCache
    public void initialize(OnWriteCompleteCallback asyncCache$OnWriteCompleteCallback0) {
        asyncCache$OnWriteCompleteCallback0.onWriteComplete();
    }

    @Override  // com.android.volley.AsyncCache
    public void invalidate(String s, boolean z, OnWriteCompleteCallback asyncCache$OnWriteCompleteCallback0) {
        asyncCache$OnWriteCompleteCallback0.onWriteComplete();
    }

    @Override  // com.android.volley.AsyncCache
    public void put(String s, Entry cache$Entry0, OnWriteCompleteCallback asyncCache$OnWriteCompleteCallback0) {
        asyncCache$OnWriteCompleteCallback0.onWriteComplete();
    }

    @Override  // com.android.volley.AsyncCache
    public void remove(String s, OnWriteCompleteCallback asyncCache$OnWriteCompleteCallback0) {
        asyncCache$OnWriteCompleteCallback0.onWriteComplete();
    }
}

