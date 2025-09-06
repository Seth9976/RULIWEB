package kotlinx.coroutines.android;

import android.os.Build.VERSION;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandler;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0016J\n\u0010\f\u001A\u0004\u0018\u00010\rH\u0002R\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0005X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/android/AndroidExceptionPreHandler;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/CoroutineExceptionHandler;", "()V", "_preHandler", "", "handleException", "", "context", "Lkotlin/coroutines/CoroutineContext;", "exception", "", "preHandler", "Ljava/lang/reflect/Method;", "kotlinx-coroutines-android"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class AndroidExceptionPreHandler extends AbstractCoroutineContextElement implements CoroutineExceptionHandler {
    private volatile Object _preHandler;

    public AndroidExceptionPreHandler() {
        super(CoroutineExceptionHandler.Key);
        this._preHandler = this;
    }

    @Override  // kotlinx.coroutines.CoroutineExceptionHandler
    public void handleException(CoroutineContext coroutineContext0, Throwable throwable0) {
        if(26 <= Build.VERSION.SDK_INT && Build.VERSION.SDK_INT < 28) {
            Method method0 = this.preHandler();
            Thread.UncaughtExceptionHandler thread$UncaughtExceptionHandler0 = null;
            Object object0 = method0 == null ? null : method0.invoke(null, null);
            if(object0 instanceof Thread.UncaughtExceptionHandler) {
                thread$UncaughtExceptionHandler0 = (Thread.UncaughtExceptionHandler)object0;
            }
            if(thread$UncaughtExceptionHandler0 != null) {
                thread$UncaughtExceptionHandler0.uncaughtException(Thread.currentThread(), throwable0);
            }
        }
    }

    private final Method preHandler() {
        Method method0;
        Object object0 = this._preHandler;
        if(object0 != this) {
            return (Method)object0;
        }
        try {
            method0 = null;
            Method method1 = Thread.class.getDeclaredMethod("getUncaughtExceptionPreHandler", null);
            if(Modifier.isPublic(method1.getModifiers()) && Modifier.isStatic(method1.getModifiers())) {
                method0 = method1;
            }
        }
        catch(Throwable unused_ex) {
        }
        this._preHandler = method0;
        return method0;
    }
}

