package androidx.core.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Landroidx/core/util/Pools;", "", "()V", "Pool", "SimplePool", "SynchronizedPool", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Pools {
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002J\u000F\u0010\u0003\u001A\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00028\u0000H&¢\u0006\u0002\u0010\bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\tÀ\u0006\u0001"}, d2 = {"Landroidx/core/util/Pools$Pool;", "T", "", "acquire", "()Ljava/lang/Object;", "release", "", "instance", "(Ljava/lang/Object;)Z", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public interface Pool {
        Object acquire();

        boolean release(Object arg1);
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u000F\u0012\b\b\u0001\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000F\u0010\u000B\u001A\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\fJ\u0015\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0010J\u0015\u0010\u0011\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0010R\u0018\u0010\u0007\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000E\u0010\n\u001A\u00020\u0005X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/core/util/Pools$SimplePool;", "T", "", "Landroidx/core/util/Pools$Pool;", "maxPoolSize", "", "(I)V", "pool", "", "[Ljava/lang/Object;", "poolSize", "acquire", "()Ljava/lang/Object;", "isInPool", "", "instance", "(Ljava/lang/Object;)Z", "release", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class SimplePool implements Pool {
        private final Object[] pool;
        private int poolSize;

        public SimplePool(int v) {
            if(v <= 0) {
                throw new IllegalArgumentException("The max pool size must be > 0");
            }
            this.pool = new Object[v];
        }

        @Override  // androidx.core.util.Pools$Pool
        public Object acquire() {
            int v = this.poolSize;
            if(v > 0) {
                Object object0 = this.pool[v - 1];
                Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type T of androidx.core.util.Pools.SimplePool");
                this.pool[v - 1] = null;
                --this.poolSize;
                return object0;
            }
            return null;
        }

        private final boolean isInPool(Object object0) {
            int v = this.poolSize;
            for(int v1 = 0; v1 < v; ++v1) {
                if(this.pool[v1] == object0) {
                    return true;
                }
            }
            return false;
        }

        @Override  // androidx.core.util.Pools$Pool
        public boolean release(Object object0) {
            Intrinsics.checkNotNullParameter(object0, "instance");
            if(this.isInPool(object0)) {
                throw new IllegalStateException("Already in the pool!");
            }
            int v = this.poolSize;
            Object[] arr_object = this.pool;
            if(v < arr_object.length) {
                arr_object[v] = object0;
                this.poolSize = v + 1;
                return true;
            }
            return false;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0003\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000F\u0010\b\u001A\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\tJ\u0015\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\rR\u000E\u0010\u0007\u001A\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000E"}, d2 = {"Landroidx/core/util/Pools$SynchronizedPool;", "T", "", "Landroidx/core/util/Pools$SimplePool;", "maxPoolSize", "", "(I)V", "lock", "acquire", "()Ljava/lang/Object;", "release", "", "instance", "(Ljava/lang/Object;)Z", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static class SynchronizedPool extends SimplePool {
        private final Object lock;

        public SynchronizedPool(int v) {
            super(v);
            this.lock = new Object();
        }

        @Override  // androidx.core.util.Pools$SimplePool
        public Object acquire() {
            synchronized(this.lock) {
                return super.acquire();
            }
        }

        @Override  // androidx.core.util.Pools$SimplePool
        public boolean release(Object object0) {
            Intrinsics.checkNotNullParameter(object0, "instance");
            synchronized(this.lock) {
                return super.release(object0);
            }
        }
    }

}

