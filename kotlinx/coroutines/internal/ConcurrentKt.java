package kotlinx.coroutines.internal;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A\u001D\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0080\b\u001A\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0000\u001A*\u0010\u000B\u001A\u0002H\f\"\u0004\b\u0000\u0010\f*\u00060\rj\u0002`\u000E2\f\u0010\u000F\u001A\b\u0012\u0004\u0012\u0002H\f0\u0010H\u0080\b¢\u0006\u0002\u0010\u0011\"\u0010\u0010\u0000\u001A\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*\f\b\u0000\u0010\u0012\"\u00020\r2\u00020\r¨\u0006\u0013"}, d2 = {"REMOVE_FUTURE_ON_CANCEL", "Ljava/lang/reflect/Method;", "identitySet", "", "E", "expectedSize", "", "removeFutureOnCancel", "", "executor", "Ljava/util/concurrent/Executor;", "withLock", "T", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "ReentrantLock", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ConcurrentKt {
    private static final Method REMOVE_FUTURE_ON_CANCEL;

    static {
        Method method0 = null;
        try {
            method0 = ScheduledThreadPoolExecutor.class.getMethod("setRemoveOnCancelPolicy", Boolean.TYPE);
        }
        catch(Throwable unused_ex) {
        }
        ConcurrentKt.REMOVE_FUTURE_ON_CANCEL = method0;
    }

    public static void ReentrantLock$annotations() {
    }

    public static final Set identitySet(int v) {
        return Collections.newSetFromMap(new IdentityHashMap(v));
    }

    public static final boolean removeFutureOnCancel(Executor executor0) {
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor0 = executor0 instanceof ScheduledThreadPoolExecutor ? ((ScheduledThreadPoolExecutor)executor0) : null;
            if(scheduledThreadPoolExecutor0 == null) {
                return false;
            }
            Method method0 = ConcurrentKt.REMOVE_FUTURE_ON_CANCEL;
            if(method0 == null) {
                return false;
            }
            method0.invoke(scheduledThreadPoolExecutor0, Boolean.TRUE);
            return true;
        }
        catch(Throwable unused_ex) {
            return false;
        }
    }

    public static final Object withLock(ReentrantLock reentrantLock0, Function0 function00) {
        reentrantLock0.lock();
        try {
            return function00.invoke();
        }
        finally {
            reentrantLock0.unlock();
        }
    }
}

