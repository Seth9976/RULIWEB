package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
public abstract class StatsEvent extends AbstractSafeParcelable implements ReflectedParcelable {
    public interface Types {
        public static final int EVENT_TYPE_ACQUIRE_WAKE_LOCK = 7;
        public static final int EVENT_TYPE_RELEASE_WAKE_LOCK = 8;

    }

    @Override
    public final String toString() {
        return this.zzb() + "\t" + this.zza() + "\t-1" + this.zzc();
    }

    public abstract int zza();

    public abstract long zzb();

    public abstract String zzc();
}

