package com.google.android.gms.internal.measurement;

import android.content.Context;
import javax.annotation.Nullable;

final class zzhc extends zzhz {
    private final Context zza;
    private final zzim zzb;

    zzhc(Context context0, @Nullable zzim zzim0) {
        this.zza = context0;
        this.zzb = zzim0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof zzhz) {
            Context context0 = ((zzhz)object0).zza();
            if(this.zza.equals(context0)) {
                return this.zzb == null ? ((zzhz)object0).zzb() == null : this.zzb.equals(((zzhz)object0).zzb());
            }
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int v = this.zza.hashCode();
        return this.zzb == null ? (v ^ 1000003) * 1000003 : (v ^ 1000003) * 1000003 ^ this.zzb.hashCode();
    }

    @Override
    public final String toString() {
        return "FlagsContext{context=" + this.zza.toString() + ", hermeticFileOverrides=" + this.zzb + "}";
    }

    @Override  // com.google.android.gms.internal.measurement.zzhz
    final Context zza() {
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhz
    @Nullable
    final zzim zzb() {
        return this.zzb;
    }
}

