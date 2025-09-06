package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class ConnectionTelemetryConfiguration extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final RootTelemetryConfiguration zza;
    private final boolean zzb;
    private final boolean zzc;
    private final int[] zzd;
    private final int zze;
    private final int[] zzf;

    static {
        ConnectionTelemetryConfiguration.CREATOR = new zzm();
    }

    public ConnectionTelemetryConfiguration(RootTelemetryConfiguration rootTelemetryConfiguration0, boolean z, boolean z1, int[] arr_v, int v, int[] arr_v1) {
        this.zza = rootTelemetryConfiguration0;
        this.zzb = z;
        this.zzc = z1;
        this.zzd = arr_v;
        this.zze = v;
        this.zzf = arr_v1;
    }

    public int getMaxMethodInvocationsLogged() {
        return this.zze;
    }

    public int[] getMethodInvocationMethodKeyAllowlist() {
        return this.zzd;
    }

    public int[] getMethodInvocationMethodKeyDisallowlist() {
        return this.zzf;
    }

    public boolean getMethodInvocationTelemetryEnabled() {
        return this.zzb;
    }

    public boolean getMethodTimingTelemetryEnabled() {
        return this.zzc;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeParcelable(parcel0, 1, this.zza, v, false);
        SafeParcelWriter.writeBoolean(parcel0, 2, this.getMethodInvocationTelemetryEnabled());
        SafeParcelWriter.writeBoolean(parcel0, 3, this.getMethodTimingTelemetryEnabled());
        SafeParcelWriter.writeIntArray(parcel0, 4, this.getMethodInvocationMethodKeyAllowlist(), false);
        SafeParcelWriter.writeInt(parcel0, 5, this.getMaxMethodInvocationsLogged());
        SafeParcelWriter.writeIntArray(parcel0, 6, this.getMethodInvocationMethodKeyDisallowlist(), false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }

    public final RootTelemetryConfiguration zza() {
        return this.zza;
    }
}

