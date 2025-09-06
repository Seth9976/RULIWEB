package kotlin.reflect.jvm.internal.impl.storage;

import kotlin.jvm.functions.Function1;

public interface SimpleLock {
    public static final class Companion {
        static final Companion $$INSTANCE;

        static {
            Companion.$$INSTANCE = new Companion();
        }

        public final DefaultSimpleLock simpleLock(Runnable runnable0, Function1 function10) {
            return runnable0 != null && function10 != null ? new CancellableSimpleLock(runnable0, function10) : new DefaultSimpleLock(null, 1, null);
        }
    }

    public static final Companion Companion;

    static {
        SimpleLock.Companion = Companion.$$INSTANCE;
    }

    void lock();

    void unlock();
}

