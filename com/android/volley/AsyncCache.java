package com.android.volley;

public abstract class AsyncCache {
    public interface OnGetCompleteCallback {
        void onGetComplete(Entry arg1);
    }

    public interface OnWriteCompleteCallback {
        void onWriteComplete();
    }

    public abstract void clear(OnWriteCompleteCallback arg1);

    public abstract void get(String arg1, OnGetCompleteCallback arg2);

    public abstract void initialize(OnWriteCompleteCallback arg1);

    public abstract void invalidate(String arg1, boolean arg2, OnWriteCompleteCallback arg3);

    public abstract void put(String arg1, Entry arg2, OnWriteCompleteCallback arg3);

    public abstract void remove(String arg1, OnWriteCompleteCallback arg2);
}

