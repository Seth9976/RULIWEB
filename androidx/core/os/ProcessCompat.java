package androidx.core.os;

import android.os.Build.VERSION;
import android.os.Process;
import android.os.UserHandle;
import java.lang.reflect.Method;

public final class ProcessCompat {
    static class Api19Impl {
        private static Method sMethodUserHandleIsAppMethod;
        private static boolean sResolved;
        private static final Object sResolvedLock;

        static {
            Api19Impl.sResolvedLock = new Object();
        }

        static boolean isApplicationUid(int v) {
            try {
                Object object0 = Api19Impl.sResolvedLock;
                synchronized(object0) {
                    if(!Api19Impl.sResolved) {
                        Api19Impl.sResolved = true;
                        Api19Impl.sMethodUserHandleIsAppMethod = UserHandle.class.getDeclaredMethod("isApp", Integer.TYPE);
                    }
                }
                Method method0 = Api19Impl.sMethodUserHandleIsAppMethod;
                if(method0 != null) {
                    Boolean boolean0 = (Boolean)method0.invoke(null, v);
                    if(boolean0 == null) {
                        throw new NullPointerException();
                    }
                    return boolean0.booleanValue();
                }
            }
            catch(Exception exception0) {
                exception0.printStackTrace();
            }
            return true;
        }
    }

    static class Api24Impl {
        static boolean isApplicationUid(int v) {
            return Process.isApplicationUid(v);
        }
    }

    public static boolean isApplicationUid(int v) {
        return Build.VERSION.SDK_INT < 24 ? Api19Impl.isApplicationUid(v) : Api24Impl.isApplicationUid(v);
    }
}

