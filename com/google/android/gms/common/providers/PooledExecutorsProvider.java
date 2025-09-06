package com.google.android.gms.common.providers;

import java.util.concurrent.ScheduledExecutorService;

@Deprecated
public class PooledExecutorsProvider {
    public interface PooledExecutorFactory {
        @Deprecated
        ScheduledExecutorService newSingleThreadScheduledExecutor();
    }

    private static PooledExecutorFactory zza;

    @Deprecated
    public static PooledExecutorFactory getInstance() {
        synchronized(PooledExecutorsProvider.class) {
            if(PooledExecutorsProvider.zza == null) {
                PooledExecutorsProvider.zza = new zza();
            }
            return PooledExecutorsProvider.zza;
        }
    }
}

