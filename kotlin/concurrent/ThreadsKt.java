package kotlin.concurrent;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001AJ\u0010\u0000\u001A\u00020\u00012\b\b\u0002\u0010\u0002\u001A\u00020\u00032\b\b\u0002\u0010\u0004\u001A\u00020\u00032\n\b\u0002\u0010\u0005\u001A\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001A\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001A\u00020\n2\f\u0010\u000B\u001A\b\u0012\u0004\u0012\u00020\r0\f\u001A3\u0010\u000E\u001A\u0002H\u000F\"\b\b\u0000\u0010\u000F*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000F0\u00112\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u000F0\fH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"thread", "Ljava/lang/Thread;", "start", "", "isDaemon", "contextClassLoader", "Ljava/lang/ClassLoader;", "name", "", "priority", "", "block", "Lkotlin/Function0;", "", "getOrSet", "T", "", "Ljava/lang/ThreadLocal;", "default", "(Ljava/lang/ThreadLocal;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ThreadsKt {
    private static final Object getOrSet(ThreadLocal threadLocal0, Function0 function00) {
        Intrinsics.checkNotNullParameter(threadLocal0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "default");
        Object object0 = threadLocal0.get();
        if(object0 == null) {
            Object object1 = function00.invoke();
            threadLocal0.set(object1);
            return object1;
        }
        return object0;
    }

    public static final Thread thread(boolean z, boolean z1, ClassLoader classLoader0, String s, int v, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "block");
        kotlin.concurrent.ThreadsKt.thread.thread.1 threadsKt$thread$thread$10 = new Thread() {
            @Override
            public void run() {
                function00.invoke();
            }
        };
        if(z1) {
            threadsKt$thread$thread$10.setDaemon(true);
        }
        if(v > 0) {
            threadsKt$thread$thread$10.setPriority(v);
        }
        if(s != null) {
            threadsKt$thread$thread$10.setName(s);
        }
        if(classLoader0 != null) {
            threadsKt$thread$thread$10.setContextClassLoader(classLoader0);
        }
        if(z) {
            threadsKt$thread$thread$10.start();
        }
        return threadsKt$thread$thread$10;
    }

    public static Thread thread$default(boolean z, boolean z1, ClassLoader classLoader0, String s, int v, Function0 function00, int v1, Object object0) {
        if((v1 & 1) != 0) {
            z = true;
        }
        if((v1 & 2) != 0) {
            z1 = false;
        }
        if((v1 & 4) != 0) {
            classLoader0 = null;
        }
        if((v1 & 8) != 0) {
            s = null;
        }
        return (v1 & 16) == 0 ? ThreadsKt.thread(z, z1, classLoader0, s, v, function00) : ThreadsKt.thread(z, z1, classLoader0, s, -1, function00);
    }
}

