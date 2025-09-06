package com.android.volley;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface Cache {
    public static class Entry {
        public List allResponseHeaders;
        public byte[] data;
        public String etag;
        public long lastModified;
        public Map responseHeaders;
        public long serverDate;
        public long softTtl;
        public long ttl;

        public Entry() {
            this.responseHeaders = Collections.EMPTY_MAP;
        }

        public boolean isExpired() {
            return this.isExpired(System.currentTimeMillis());
        }

        boolean isExpired(long v) {
            return this.ttl < v;
        }

        public boolean refreshNeeded() {
            return this.refreshNeeded(System.currentTimeMillis());
        }

        boolean refreshNeeded(long v) {
            return this.softTtl < v;
        }
    }

    void clear();

    Entry get(String arg1);

    void initialize();

    void invalidate(String arg1, boolean arg2);

    void put(String arg1, Entry arg2);

    void remove(String arg1);
}

