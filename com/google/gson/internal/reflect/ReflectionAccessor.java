package com.google.gson.internal.reflect;

import java.lang.reflect.AccessibleObject;

public abstract class ReflectionAccessor {
    private static final ReflectionAccessor instance;

    static {
        ReflectionAccessor.instance = new PreJava9ReflectionAccessor();
    }

    public static ReflectionAccessor getInstance() {
        return ReflectionAccessor.instance;
    }

    public abstract void makeAccessible(AccessibleObject arg1);
}

