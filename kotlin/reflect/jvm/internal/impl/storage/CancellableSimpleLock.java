package kotlin.reflect.jvm.internal.impl.storage;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

public final class CancellableSimpleLock extends DefaultSimpleLock {
    private final Runnable checkCancelled;
    private final Function1 interruptedExceptionHandler;

    public CancellableSimpleLock(Runnable runnable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(runnable0, "checkCancelled");
        Intrinsics.checkNotNullParameter(function10, "interruptedExceptionHandler");
        this(new ReentrantLock(), runnable0, function10);
    }

    public CancellableSimpleLock(Lock lock0, Runnable runnable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(lock0, "lock");
        Intrinsics.checkNotNullParameter(runnable0, "checkCancelled");
        Intrinsics.checkNotNullParameter(function10, "interruptedExceptionHandler");
        super(lock0);
        this.checkCancelled = runnable0;
        this.interruptedExceptionHandler = function10;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.DefaultSimpleLock
    public void lock() {
        while(true) {
            try {
                if(this.getLock().tryLock(50L, TimeUnit.MILLISECONDS)) {
                    break;
                }
                this.checkCancelled.run();
            }
            catch(InterruptedException interruptedException0) {
                this.interruptedExceptionHandler.invoke(interruptedException0);
                break;
            }
        }
    }
}

