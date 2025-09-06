package com.google.gson.internal;

import java.lang.reflect.Type;

public final class Primitives {
    // 去混淆评级： 低(20)
    public static boolean isPrimitive(Type type0) {
        return type0 instanceof Class && ((Class)type0).isPrimitive();
    }

    public static boolean isWrapperType(Type type0) {
        return type0 == Integer.class || type0 == Float.class || type0 == Byte.class || type0 == Double.class || type0 == Long.class || type0 == Character.class || type0 == Boolean.class || type0 == Short.class || type0 == Void.class;
    }

    public static Class unwrap(Class class0) {
        if(class0 == Integer.class) {
            return Integer.TYPE;
        }
        if(class0 == Float.class) {
            return Float.TYPE;
        }
        if(class0 == Byte.class) {
            return Byte.TYPE;
        }
        if(class0 == Double.class) {
            return Double.TYPE;
        }
        if(class0 == Long.class) {
            return Long.TYPE;
        }
        if(class0 == Character.class) {
            return Character.TYPE;
        }
        if(class0 == Boolean.class) {
            return Boolean.TYPE;
        }
        if(class0 == Short.class) {
            return Short.TYPE;
        }
        return class0 == Void.class ? Void.TYPE : class0;
    }

    public static Class wrap(Class class0) {
        if(class0 == Integer.TYPE) {
            return Integer.class;
        }
        if(class0 == Float.TYPE) {
            return Float.class;
        }
        if(class0 == Byte.TYPE) {
            return Byte.class;
        }
        if(class0 == Double.TYPE) {
            return Double.class;
        }
        if(class0 == Long.TYPE) {
            return Long.class;
        }
        if(class0 == Character.TYPE) {
            return Character.class;
        }
        if(class0 == Boolean.TYPE) {
            return Boolean.class;
        }
        if(class0 == Short.TYPE) {
            return Short.class;
        }
        return class0 == Void.TYPE ? Void.class : class0;
    }
}

