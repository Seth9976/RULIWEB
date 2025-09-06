package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class RootTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final int zza;
    private final boolean zzb;
    private final boolean zzc;
    private final int zzd;
    private final int zze;

    static {
        RootTelemetryConfiguration.CREATOR = new zzaj();
    }

    public RootTelemetryConfiguration(int v, boolean z, boolean z1, int v1, int v2) {
        this.zza = v;
        this.zzb = z;
        this.zzc = z1;
        this.zzd = v1;
        this.zze = v2;
    }

    public int getBatchPeriodMillis() {
        return this.zzd;
    }

    public int getMaxMethodInvocationsInBatch() {
        return this.zze;
    }

    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    public int getVersion() {
        return this.zza;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.getVersion());
        SafeParcelWriter.writeBoolean(parcel0, 2, this.getMethodInvocationTelemetryEnabled());
        SafeParcelWriter.writeBoolean(parcel0, 3, this.getMethodTimingTelemetryEnabled());
        SafeParcelWriter.writeInt(parcel0, 4, this.getBatchPeriodMillis());
        SafeParcelWriter.writeInt(parcel0, 5, this.getMaxMethodInvocationsInBatch());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

