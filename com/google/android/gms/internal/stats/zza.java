package com.google.android.gms.internal.stats;

import java.io.Closeable;

class zza implements Closeable, AutoCloseable {
    @Override
    public void close() {
        throw null;
    }

    @Override
    public void finalize() {
        synchronized(this) {
        }
    }
}

