package com.google.firebase.components;

public final class Preconditions {
    public static void checkArgument(boolean z, String s) {
        if(!z) {
            throw new IllegalArgumentException(s);
        }
    }

    public static Object checkNotNull(Object object0) {
        object0.getClass();
        return object0;
    }

    public static Object checkNotNull(Object object0, String s) {
        if(object0 == null) {
            throw new NullPointerException(s);
        }
        return object0;
    }

    public static void checkState(boolean z, String s) {
        if(!z) {
            throw new IllegalStateException(s);
        }
    }
}

