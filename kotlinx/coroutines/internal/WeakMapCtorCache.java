package kotlinx.coroutines.internal;

import java.util.WeakHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J*\u0010\u000B\u001A\u0014\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\tj\u0002`\n2\u000E\u0010\f\u001A\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007H\u0016R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R4\u0010\u0005\u001A(\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\b0\u0007\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\b\u0012\u0006\u0012\u0004\u0018\u00010\b0\tj\u0002`\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/internal/WeakMapCtorCache;", "Lkotlinx/coroutines/internal/CtorCache;", "()V", "cacheLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "exceptionCtors", "Ljava/util/WeakHashMap;", "Ljava/lang/Class;", "", "Lkotlin/Function1;", "Lkotlinx/coroutines/internal/Ctor;", "get", "key", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class WeakMapCtorCache extends CtorCache {
    public static final WeakMapCtorCache INSTANCE;
    private static final ReentrantReadWriteLock cacheLock;
    private static final WeakHashMap exceptionCtors;

    static {
        WeakMapCtorCache.INSTANCE = new WeakMapCtorCache();
        WeakMapCtorCache.cacheLock = new ReentrantReadWriteLock();
        WeakMapCtorCache.exceptionCtors = new WeakHashMap();
    }

    @Override  // kotlinx.coroutines.internal.CtorCache
    public Function1 get(Class class0) {
        Function1 function10;
        Function1 function12;
        Function1 function11;
        ReentrantReadWriteLock reentrantReadWriteLock0 = WeakMapCtorCache.cacheLock;
        ReentrantReadWriteLock.ReadLock reentrantReadWriteLock$ReadLock0 = reentrantReadWriteLock0.readLock();
        reentrantReadWriteLock$ReadLock0.lock();
        try {
            function10 = (Function1)WeakMapCtorCache.exceptionCtors.get(class0);
        }
        finally {
            reentrantReadWriteLock$ReadLock0.unlock();
        }
        if(function10 != null) {
            return function10;
        }
        ReentrantReadWriteLock.ReadLock reentrantReadWriteLock$ReadLock1 = reentrantReadWriteLock0.readLock();
        int v2 = reentrantReadWriteLock0.getWriteHoldCount() == 0 ? reentrantReadWriteLock0.getReadHoldCount() : 0;
        for(int v3 = 0; v3 < v2; ++v3) {
            reentrantReadWriteLock$ReadLock1.unlock();
        }
        ReentrantReadWriteLock.WriteLock reentrantReadWriteLock$WriteLock0 = reentrantReadWriteLock0.writeLock();
        reentrantReadWriteLock$WriteLock0.lock();
        try {
            WeakHashMap weakHashMap0 = WeakMapCtorCache.exceptionCtors;
            function11 = (Function1)weakHashMap0.get(class0);
            if(function11 == null) {
                function12 = ExceptionsConstructorKt.createConstructor(class0);
                weakHashMap0.put(class0, function12);
                goto label_33;
            }
            goto label_39;
        }
        catch(Throwable throwable0) {
            for(int v1 = 0; v1 < v2; ++v1) {
                reentrantReadWriteLock$ReadLock1.lock();
            }
            reentrantReadWriteLock$WriteLock0.unlock();
            throw throwable0;
        }
    label_33:
        while(v1 < v2) {
            reentrantReadWriteLock$ReadLock1.lock();
            ++v1;
        }
        reentrantReadWriteLock$WriteLock0.unlock();
        return function12;
    label_39:
        while(v1 < v2) {
            reentrantReadWriteLock$ReadLock1.lock();
            ++v1;
        }
        reentrantReadWriteLock$WriteLock0.unlock();
        return function11;
    }
}

