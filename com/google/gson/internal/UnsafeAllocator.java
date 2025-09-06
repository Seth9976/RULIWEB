package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class UnsafeAllocator {
    static void assertInstantiable(Class class0) {
        int v = class0.getModifiers();
        if(Modifier.isInterface(v)) {
            throw new UnsupportedOperationException("Interface can\'t be instantiated! Interface name: " + class0.getName());
        }
        if(Modifier.isAbstract(v)) {
            throw new UnsupportedOperationException("Abstract class can\'t be instantiated! Class name: " + class0.getName());
        }
    }

    public static UnsafeAllocator create() {
        try {
            Class class0 = Class.forName("sun.misc.Unsafe");
            Field field0 = class0.getDeclaredField("theUnsafe");
            field0.setAccessible(true);
            Object object0 = field0.get(null);
            return new UnsafeAllocator() {
                @Override  // com.google.gson.internal.UnsafeAllocator
                public Object newInstance(Class class0) throws Exception {
                    com.google.gson.internal.UnsafeAllocator.1.assertInstantiable(class0);
                    return class0.getMethod("allocateInstance", Class.class).invoke(object0, class0);
                }
            };
        }
        catch(Exception unused_ex) {
            try {
                Method method0 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                method0.setAccessible(true);
                int v = (int)(((Integer)method0.invoke(null, Object.class)));
                Method method1 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                method1.setAccessible(true);
                return new UnsafeAllocator() {
                    @Override  // com.google.gson.internal.UnsafeAllocator
                    public Object newInstance(Class class0) throws Exception {
                        com.google.gson.internal.UnsafeAllocator.2.assertInstantiable(class0);
                        return method1.invoke(null, class0, v);
                    }
                };
            }
            catch(Exception unused_ex) {
                try {
                    Method method2 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    method2.setAccessible(true);
                    return new UnsafeAllocator() {
                        @Override  // com.google.gson.internal.UnsafeAllocator
                        public Object newInstance(Class class0) throws Exception {
                            com.google.gson.internal.UnsafeAllocator.3.assertInstantiable(class0);
                            return method2.invoke(null, class0, Object.class);
                        }
                    };
                }
                catch(Exception unused_ex) {
                    return new UnsafeAllocator() {
                        @Override  // com.google.gson.internal.UnsafeAllocator
                        public Object newInstance(Class class0) {
                            throw new UnsupportedOperationException("Cannot allocate " + class0);
                        }
                    };
                }
            }
        }
    }

    public abstract Object newInstance(Class arg1) throws Exception;
}

