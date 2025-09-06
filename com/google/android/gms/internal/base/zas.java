package com.google.android.gms.internal.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class zas implements zaq {
    private zas() {
    }

    zas(zar zar0) {
    }

    @Override  // com.google.android.gms.internal.base.zaq
    public final ExecutorService zaa(ThreadFactory threadFactory0, int v) {
        return this.zac(1, threadFactory0, 1);
    }

    @Override  // com.google.android.gms.internal.base.zaq
    public final ExecutorService zab(int v, int v1) {
        return this.zac(4, Executors.defaultThreadFactory(), 2);
    }

    @Override  // com.google.android.gms.internal.base.zaq
    public final ExecutorService zac(int v, ThreadFactory threadFactory0, int v1) {
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(v, v, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), threadFactory0);
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        return Executors.unconfigurableExecutorService(threadPoolExecutor0);
    }
}

