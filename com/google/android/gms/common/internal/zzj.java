package com.google.android.gms.common.internal;

import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class zzj {
    static final ExecutorService zza;

    static {
        NamedThreadFactory namedThreadFactory0 = new NamedThreadFactory("CallbackExecutor");
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), namedThreadFactory0);
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        zzj.zza = Executors.unconfigurableExecutorService(threadPoolExecutor0);
    }
}

