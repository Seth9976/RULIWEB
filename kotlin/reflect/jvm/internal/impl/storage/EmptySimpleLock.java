package kotlin.reflect.jvm.internal.impl.storage;

public final class EmptySimpleLock implements SimpleLock {
    public static final EmptySimpleLock INSTANCE;

    static {
        EmptySimpleLock.INSTANCE = new EmptySimpleLock();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.SimpleLock
    public void lock() {
    }

    @Override  // kotlin.reflect.jvm.internal.impl.storage.SimpleLock
    public void unlock() {
    }
}

