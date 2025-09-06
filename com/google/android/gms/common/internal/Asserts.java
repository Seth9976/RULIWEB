package com.google.android.gms.common.internal;

import android.os.Looper;
import android.util.Log;
import javax.annotation.Nullable;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class Asserts {
    private Asserts() {
        throw new AssertionError("Uninstantiable");
    }

    public static void checkMainThread(String s) {
        if(Looper.getMainLooper().getThread() == Thread.currentThread()) {
            return;
        }
        Log.e("Asserts", "checkMainThread: current thread Thread[jeb-dexdec-sb-st-13239,5,main] IS NOT the main thread " + Looper.getMainLooper().getThread() + "!");
        throw new IllegalStateException(s);
    }

    public static void checkNotMainThread(String s) {
        if(Looper.getMainLooper().getThread() != Thread.currentThread()) {
            return;
        }
        Log.e("Asserts", "checkNotMainThread: current thread Thread[jeb-dexdec-sb-st-13184,5,main] IS the main thread " + Looper.getMainLooper().getThread() + "!");
        throw new IllegalStateException(s);
    }

    @EnsuresNonNull({"#1"})
    public static void checkNotNull(@Nullable Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("null reference");
        }
    }

    @EnsuresNonNull({"#1"})
    public static void checkNotNull(@Nullable Object object0, Object object1) {
        if(object0 == null) {
            throw new IllegalArgumentException(String.valueOf(object1));
        }
    }

    public static void checkNull(@Nullable Object object0) {
        if(object0 != null) {
            throw new IllegalArgumentException("non-null reference");
        }
    }

    public static void checkNull(@Nullable Object object0, Object object1) {
        if(object0 != null) {
            throw new IllegalArgumentException(String.valueOf(object1));
        }
    }

    public static void checkState(boolean z) {
        if(!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, Object object0) {
        if(!z) {
            throw new IllegalStateException(String.valueOf(object0));
        }
    }
}

