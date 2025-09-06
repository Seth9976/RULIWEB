package org.chromium.support_lib_boundary.util;

import android.os.Build;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Collection;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BoundaryInterfaceReflectionUtil {
    static class InvocationHandlerWithDelegateGetter implements InvocationHandler {
        private final Object mDelegate;

        public InvocationHandlerWithDelegateGetter(Object object0) {
            this.mDelegate = object0;
        }

        public Object getDelegate() {
            return this.mDelegate;
        }

        @Override
        public Object invoke(Object object0, Method method0, Object[] arr_object) throws Throwable {
            ClassLoader classLoader0 = this.mDelegate.getClass().getClassLoader();
            try {
                return BoundaryInterfaceReflectionUtil.dupeMethod(method0, classLoader0).invoke(this.mDelegate, arr_object);
            }
            catch(InvocationTargetException invocationTargetException0) {
                throw invocationTargetException0.getTargetException();
            }
            catch(ReflectiveOperationException reflectiveOperationException0) {
                throw new RuntimeException("Reflection failed for method " + method0, reflectiveOperationException0);
            }
        }
    }

    static final boolean $assertionsDisabled;

    static {
    }

    public static Object castToSuppLibClass(Class class0, InvocationHandler invocationHandler0) {
        return invocationHandler0 == null ? null : class0.cast(Proxy.newProxyInstance(BoundaryInterfaceReflectionUtil.class.getClassLoader(), new Class[]{class0}, invocationHandler0));
    }

    // 去混淆评级： 低(30)
    public static boolean containsFeature(Collection collection0, String s) {
        return collection0.contains(s) || BoundaryInterfaceReflectionUtil.isDebuggable() && collection0.contains(s + ":dev");
    }

    public static boolean containsFeature(String[] arr_s, String s) {
        return BoundaryInterfaceReflectionUtil.containsFeature(Arrays.asList(arr_s), s);
    }

    public static InvocationHandler createInvocationHandlerFor(Object object0) {
        return object0 == null ? null : new InvocationHandlerWithDelegateGetter(object0);
    }

    public static InvocationHandler[] createInvocationHandlersForArray(Object[] arr_object) {
        if(arr_object == null) {
            return null;
        }
        InvocationHandler[] arr_invocationHandler = new InvocationHandler[arr_object.length];
        for(int v = 0; v < arr_object.length; ++v) {
            arr_invocationHandler[v] = BoundaryInterfaceReflectionUtil.createInvocationHandlerFor(arr_object[v]);
        }
        return arr_invocationHandler;
    }

    public static Method dupeMethod(Method method0, ClassLoader classLoader0) throws ClassNotFoundException, NoSuchMethodException {
        Class class0 = Class.forName(method0.getDeclaringClass().getName(), true, classLoader0);
        Class[] arr_class = method0.getParameterTypes();
        return class0.getDeclaredMethod(method0.getName(), arr_class);
    }

    public static Object getDelegateFromInvocationHandler(InvocationHandler invocationHandler0) {
        return invocationHandler0 == null ? null : ((InvocationHandlerWithDelegateGetter)invocationHandler0).getDelegate();
    }

    public static boolean instanceOfInOwnClassLoader(Object object0, String s) {
        try {
            return Class.forName(s, false, object0.getClass().getClassLoader()).isInstance(object0);
        }
        catch(ClassNotFoundException unused_ex) {
            return false;
        }
    }

    // 去混淆评级： 低(20)
    private static boolean isDebuggable() {
        return "eng".equals(Build.TYPE) || "userdebug".equals(Build.TYPE);
    }
}

