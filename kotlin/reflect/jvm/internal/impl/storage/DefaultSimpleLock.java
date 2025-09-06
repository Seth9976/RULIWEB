package kotlin.reflect.jvm.internal.impl.storage;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

public class DefaultSimpleLock implements SimpleLock {
    private final Lock lock;

    public DefaultSimpleLock() {
        this(null, 1, null);
    }

    public DefaultSimpleLock(Lock lock0) {
        Intrinsics.checkNotNullParameter(lock0, "lock");
        super();
        this.lock = lock0;
    }

    public DefaultSimpleLock(Lock lock0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            lock0 = new ReentrantLock();
        }
        this(lock0);
    }

    protected final Lock getLock() {
        return this.lock;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.SimpleLock
    public void lock() {
        this.lock.lock();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.SimpleLock
    public void unlock() {
        this.lock.unlock();
    }
}

