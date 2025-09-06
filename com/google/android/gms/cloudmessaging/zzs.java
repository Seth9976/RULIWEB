package com.google.android.gms.cloudmessaging;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class zzs {
    private static zzs zza;
    private final Context zzb;
    private final ScheduledExecutorService zzc;
    private zzm zzd;
    private int zze;

    zzs(Context context0, ScheduledExecutorService scheduledExecutorService0) {
        this.zzd = new zzm(this, null);
        this.zze = 1;
        this.zzc = scheduledExecutorService0;
        this.zzb = context0.getApplicationContext();
    }

    static Context zza(zzs zzs0) {
        return zzs0.zzb;
    }

    public static zzs zzb(Context context0) {
        synchronized(zzs.class) {
            if(zzs.zza == null) {
                zzs.zza = new zzs(context0, Executors.unconfigurableScheduledExecutorService(Executors.newScheduledThreadPool(1, new NamedThreadFactory("MessengerIpcClient"))));
            }
            return zzs.zza;
        }
    }

    public final Task zzc(int v, Bundle bundle0) {
        return this.zzg(new zzo(this.zzf(), 2, bundle0));
    }

    public final Task zzd(int v, Bundle bundle0) {
        return this.zzg(new zzr(this.zzf(), 1, bundle0));
    }

    static ScheduledExecutorService zze(zzs zzs0) {
        return zzs0.zzc;
    }

    private final int zzf() {
        int v;
        synchronized(this) {
            v = this.zze;
            this.zze = v + 1;
        }
        return v;
    }

    private final Task zzg(zzp zzp0) {
        synchronized(this) {
            if(Log.isLoggable("MessengerIpcClient", 3)) {
                Log.d("MessengerIpcClient", "Queueing " + zzp0);
            }
            if(!this.zzd.zzg(zzp0)) {
                zzm zzm0 = new zzm(this, null);
                this.zzd = zzm0;
                zzm0.zzg(zzp0);
            }
        }
        return zzp0.zzb.getTask();
    }
}

