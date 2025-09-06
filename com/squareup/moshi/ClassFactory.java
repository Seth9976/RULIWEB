package com.squareup.moshi;

import com.squareup.moshi.internal.Util;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class ClassFactory {
    public static ClassFactory get(Class class0) {
        try {
            Constructor constructor0 = class0.getDeclaredConstructor(null);
            constructor0.setAccessible(true);
            return new ClassFactory() {
                @Override  // com.squareup.moshi.ClassFactory
                public Object newInstance() throws IllegalAccessException, InvocationTargetException, InstantiationException {
                    return constructor0.newInstance(null);
                }

                @Override
                public String toString() {
                    return class0.getName();
                }
            };
        }
        catch(NoSuchMethodException unused_ex) {
            try {
                Class class1 = Class.forName("sun.misc.Unsafe");
                Field field0 = class1.getDeclaredField("theUnsafe");
                field0.setAccessible(true);
                Object object0 = field0.get(null);
                return new ClassFactory() {
                    @Override  // com.squareup.moshi.ClassFactory
                    public Object newInstance() throws InvocationTargetException, IllegalAccessException {
                        return class1.getMethod("allocateInstance", Class.class).invoke(object0, class0);
                    }

                    @Override
                    public String toString() {
                        return class0.getName();
                    }
                };
            }
            catch(IllegalAccessException unused_ex) {
                throw new AssertionError();
            }
            catch(ClassNotFoundException | NoSuchMethodException | NoSuchFieldException unused_ex) {
                try {
                    Method method0 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    method0.setAccessible(true);
                    int v = (int)(((Integer)method0.invoke(null, Object.class)));
                    Method method1 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    method1.setAccessible(true);
                    return new ClassFactory() {
                        @Override  // com.squareup.moshi.ClassFactory
                        public Object newInstance() throws InvocationTargetException, IllegalAccessException {
                            return method1.invoke(null, class0, v);
                        }

                        @Override
                        public String toString() {
                            return class0.getName();
                        }
                    };
                }
                catch(IllegalAccessException unused_ex) {
                    throw new AssertionError();
                }
                catch(InvocationTargetException invocationTargetException0) {
                    throw Util.rethrowCause(invocationTargetException0);
                }
                catch(NoSuchMethodException unused_ex) {
                    try {
                        Method method2 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                        method2.setAccessible(true);
                        return new ClassFactory() {
                            @Override  // com.squareup.moshi.ClassFactory
                            public Object newInstance() throws InvocationTargetException, IllegalAccessException {
                                return method2.invoke(null, class0, Object.class);
                            }

                            @Override
                            public String toString() {
                                return class0.getName();
                            }
                        };
                    }
                    catch(Exception unused_ex) {
                        throw new IllegalArgumentException("cannot construct instances of " + class0.getName());
                    }
                }
            }
        }
    }

    abstract Object newInstance() throws InvocationTargetException, IllegalAccessException, InstantiationException;
}

