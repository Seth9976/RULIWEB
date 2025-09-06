package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

public class DeviceMetaData extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    final int zza;
    private final boolean zzb;
    private final long zzc;
    private final boolean zzd;

    static {
        DeviceMetaData.CREATOR = new zzy();
    }

    DeviceMetaData(int v, boolean z, long v1, boolean z1) {
        this.zza = v;
        this.zzb = z;
        this.zzc = v1;
        this.zzd = z1;
    }

    public long getMinAgeOfLockScreen() {
        return this.zzc;
    }

    public boolean isChallengeAllowed() {
        return this.zzd;
    }

    public boolean isLockScreenSolved() {
        return this.zzb;
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeInt(parcel0, 1, this.zza);
        SafeParcelWriter.writeBoolean(parcel0, 2, this.isLockScreenSolved());
        SafeParcelWriter.writeLong(parcel0, 3, this.getMinAgeOfLockScreen());
        SafeParcelWriter.writeBoolean(parcel0, 4, this.isChallengeAllowed());
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

