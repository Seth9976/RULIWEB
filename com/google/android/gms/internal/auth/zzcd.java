package com.google.android.gms.internal.auth;

import android.content.Context;
import javax.annotation.Nullable;

final class zzcd extends zzda {
    private final Context zza;
    private final zzdj zzb;

    zzcd(Context context0, @Nullable zzdj zzdj0) {
        this.zza = context0;
        this.zzb = zzdj0;
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(object0 instanceof zzda) {
            Context context0 = ((zzda)object0).zza();
            if(this.zza.equals(context0)) {
                zzdj zzdj0 = ((zzda)object0).zzb();
                return this.zzb.equals(zzdj0);
            }
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return (this.zza.hashCode() ^ 1000003) * 1000003 ^ this.zzb.hashCode();
    }

    @Override
    public final String toString() {
        return "FlagsContext{context=" + this.zza.toString() + ", hermeticFileOverrides=" + this.zzb.toString() + "}";
    }

    @Override  // com.google.android.gms.internal.auth.zzda
    final Context zza() {
        return this.zza;
    }

    @Override  // com.google.android.gms.internal.auth.zzda
    @Nullable
    final zzdj zzb() {
        return this.zzb;
    }
}

