package com.android.volley.toolbox;

import com.android.volley.Cache.Entry;
import com.android.volley.Cache;

public class NoCache implements Cache {
    @Override  // com.android.volley.Cache
    public void clear() {
    }

    @Override  // com.android.volley.Cache
    public Entry get(String s) {
        return null;
    }

    @Override  // com.android.volley.Cache
    public void initialize() {
    }

    @Override  // com.android.volley.Cache
    public void invalidate(String s, boolean z) {
    }

    @Override  // com.android.volley.Cache
    public void put(String s, Entry cache$Entry0) {
    }

    @Override  // com.android.volley.Cache
    public void remove(String s) {
    }
}

