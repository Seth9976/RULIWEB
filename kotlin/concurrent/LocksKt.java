package kotlin.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001A\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A6\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u001A6\u0010\u0006\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00072\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\b\u001A6\u0010\t\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"read", "T", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "action", "Lkotlin/Function0;", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLock", "Ljava/util/concurrent/locks/Lock;", "(Ljava/util/concurrent/locks/Lock;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "write", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class LocksKt {
    private static final Object read(ReentrantReadWriteLock reentrantReadWriteLock0, Function0 function00) {
        Intrinsics.checkNotNullParameter(reentrantReadWriteLock0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "action");
        ReentrantReadWriteLock.ReadLock reentrantReadWriteLock$ReadLock0 = reentrantReadWriteLock0.readLock();
        reentrantReadWriteLock$ReadLock0.lock();
        try {
            return function00.invoke();
        }
        finally {
            reentrantReadWriteLock$ReadLock0.unlock();
        }
    }

    private static final Object withLock(Lock lock0, Function0 function00) {
        Intrinsics.checkNotNullParameter(lock0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "action");
        lock0.lock();
        try {
            return function00.invoke();
        }
        finally {
            lock0.unlock();
        }
    }

    private static final Object write(ReentrantReadWriteLock reentrantReadWriteLock0, Function0 function00) {
        Intrinsics.checkNotNullParameter(reentrantReadWriteLock0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "action");
        ReentrantReadWriteLock.ReadLock reentrantReadWriteLock$ReadLock0 = reentrantReadWriteLock0.readLock();
        int v1 = reentrantReadWriteLock0.getWriteHoldCount() == 0 ? reentrantReadWriteLock0.getReadHoldCount() : 0;
        for(int v2 = 0; v2 < v1; ++v2) {
            reentrantReadWriteLock$ReadLock0.unlock();
        }
        ReentrantReadWriteLock.WriteLock reentrantReadWriteLock$WriteLock0 = reentrantReadWriteLock0.writeLock();
        reentrantReadWriteLock$WriteLock0.lock();
        try {
            return function00.invoke();
        }
        finally {
            for(int v = 0; v < v1; ++v) {
                reentrantReadWriteLock$ReadLock0.lock();
            }
            reentrantReadWriteLock$WriteLock0.unlock();
        }
    }
}

