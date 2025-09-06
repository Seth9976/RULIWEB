package androidx.core.os;

import android.os.Build.VERSION;
import android.os.UserHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserHandleCompat {
    static class Api24Impl {
        static UserHandle getUserHandleForUid(int v) {
            return UserHandle.getUserHandleForUid(v);
        }
    }

    private static Method sGetUserIdMethod;
    private static Constructor sUserHandleConstructor;

    private static Method getGetUserIdMethod() throws NoSuchMethodException {
        if(UserHandleCompat.sGetUserIdMethod == null) {
            Method method0 = UserHandle.class.getDeclaredMethod("getUserId", Integer.TYPE);
            UserHandleCompat.sGetUserIdMethod = method0;
            method0.setAccessible(true);
        }
        return UserHandleCompat.sGetUserIdMethod;
    }

    private static Constructor getUserHandleConstructor() throws NoSuchMethodException {
        if(UserHandleCompat.sUserHandleConstructor == null) {
            Constructor constructor0 = UserHandle.class.getDeclaredConstructor(Integer.TYPE);
            UserHandleCompat.sUserHandleConstructor = constructor0;
            constructor0.setAccessible(true);
        }
        return UserHandleCompat.sUserHandleConstructor;
    }

    public static UserHandle getUserHandleForUid(int v) {
        if(Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.getUserHandleForUid(v);
        }
        try {
            Integer integer0 = (Integer)UserHandleCompat.getGetUserIdMethod().invoke(null, v);
            return (UserHandle)UserHandleCompat.getUserHandleConstructor().newInstance(integer0);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            NoSuchMethodError noSuchMethodError0 = new NoSuchMethodError();
            noSuchMethodError0.initCause(noSuchMethodException0);
            throw noSuchMethodError0;
        }
        catch(IllegalAccessException illegalAccessException0) {
            IllegalAccessError illegalAccessError0 = new IllegalAccessError();
            illegalAccessError0.initCause(illegalAccessException0);
            throw illegalAccessError0;
        }
        catch(InstantiationException instantiationException0) {
            InstantiationError instantiationError0 = new InstantiationError();
            instantiationError0.initCause(instantiationException0);
            throw instantiationError0;
        }
        catch(InvocationTargetException invocationTargetException0) {
            throw new RuntimeException(invocationTargetException0);
        }
    }
}

