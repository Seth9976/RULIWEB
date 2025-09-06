package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    private static final Object zza;
    private boolean zzb;

    static {
        DowngradeableSafeParcel.zza = new Object();
    }

    public DowngradeableSafeParcel() {
        this.zzb = false;
    }

    protected static boolean canUnparcelSafely(String s) {
        synchronized(DowngradeableSafeParcel.zza) {
        }
        return true;
    }

    protected static Integer getUnparcelClientVersion() {
        synchronized(DowngradeableSafeParcel.zza) {
        }
        return null;
    }

    protected abstract boolean prepareForClientVersion(int arg1);

    public void setShouldDowngrade(boolean z) {
        this.zzb = z;
    }

    protected boolean shouldDowngrade() {
        return this.zzb;
    }
}

