package androidx.transition;

import android.graphics.Canvas;
import android.os.Build.VERSION;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class CanvasUtils {
    static class Api29Impl {
        static void disableZ(Canvas canvas0) {
            canvas0.disableZ();
        }

        static void enableZ(Canvas canvas0) {
            canvas0.enableZ();
        }
    }

    private static Method sInorderBarrierMethod;
    private static boolean sOrderMethodsFetched;
    private static Method sReorderBarrierMethod;

    static void enableZ(Canvas canvas0, boolean z) {
        if(Build.VERSION.SDK_INT >= 29) {
            if(z) {
                Api29Impl.enableZ(canvas0);
                return;
            }
            Api29Impl.disableZ(canvas0);
            return;
        }
        if(Build.VERSION.SDK_INT == 28) {
            throw new IllegalStateException("This method doesn\'t work on Pie!");
        }
        if(!CanvasUtils.sOrderMethodsFetched) {
            try {
                Method method0 = Canvas.class.getDeclaredMethod("insertReorderBarrier", null);
                CanvasUtils.sReorderBarrierMethod = method0;
                method0.setAccessible(true);
                Method method1 = Canvas.class.getDeclaredMethod("insertInorderBarrier", null);
                CanvasUtils.sInorderBarrierMethod = method1;
                method1.setAccessible(true);
            }
            catch(NoSuchMethodException unused_ex) {
            }
            CanvasUtils.sOrderMethodsFetched = true;
        }
        try {
            if(z) {
                Method method2 = CanvasUtils.sReorderBarrierMethod;
                if(method2 != null) {
                    method2.invoke(canvas0, null);
                }
            }
            if(!z) {
                Method method3 = CanvasUtils.sInorderBarrierMethod;
                if(method3 != null) {
                    method3.invoke(canvas0, null);
                    return;
                }
            }
            return;
        }
        catch(IllegalAccessException unused_ex) {
            return;
        }
        catch(InvocationTargetException invocationTargetException0) {
        }
        throw new RuntimeException(invocationTargetException0.getCause());
    }
}

