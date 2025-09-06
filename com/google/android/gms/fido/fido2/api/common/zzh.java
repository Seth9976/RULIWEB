package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Arrays;

public final class zzh extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR;
    private final boolean zza;
    private final byte[] zzb;

    static {
        zzh.CREATOR = new zzi();
    }

    public zzh(boolean z, byte[] arr_b) {
        this.zza = z;
        this.zzb = arr_b;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(Object object0) {
        return object0 instanceof zzh ? this.zza == ((zzh)object0).zza && Arrays.equals(this.zzb, ((zzh)object0).zzb) : false;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(new Object[]{Boolean.valueOf(this.zza), this.zzb});
    }

    @Override  // android.os.Parcelable
    public final void writeToParcel(Parcel parcel0, int v) {
        int v1 = SafeParcelWriter.beginObjectHeader(parcel0);
        SafeParcelWriter.writeBoolean(parcel0, 1, this.zza);
        SafeParcelWriter.writeByteArray(parcel0, 2, this.zzb, false);
        SafeParcelWriter.finishObjectHeader(parcel0, v1);
    }
}

