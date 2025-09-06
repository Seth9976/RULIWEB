package com.google.android.gms.common;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.common.zzag;
import java.util.List;

final class zzz {
    private String zza;
    private long zzb;
    private zzag zzc;
    private zzag zzd;

    zzz() {
        this.zza = null;
        this.zzb = -1L;
        this.zzc = zzag.zzl();
        this.zzd = zzag.zzl();
    }

    final zzz zza(long v) {
        this.zzb = v;
        return this;
    }

    final zzz zzb(List list0) {
        Preconditions.checkNotNull(list0);
        this.zzd = zzag.zzk(list0);
        return this;
    }

    final zzz zzc(List list0) {
        Preconditions.checkNotNull(list0);
        this.zzc = zzag.zzk(list0);
        return this;
    }

    final zzz zzd(String s) {
        this.zza = s;
        return this;
    }

    final zzab zze() {
        if(this.zza == null) {
            throw new IllegalStateException("packageName must be defined");
        }
        if(this.zzb < 0L) {
            throw new IllegalStateException("minimumStampedVersionNumber must be greater than or equal to 0");
        }
        if(this.zzc.isEmpty() && this.zzd.isEmpty()) {
            throw new IllegalStateException("Either orderedTestCerts or orderedProdCerts must have at least one cert");
        }
        return new zzab(this.zza, this.zzb, this.zzc, this.zzd, null);
    }
}

