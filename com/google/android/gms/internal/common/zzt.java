package com.google.android.gms.internal.common;

final class zzt extends zzw {
    final zzu zza;

    zzt(zzu zzu0, zzx zzx0, CharSequence charSequence0) {
        this.zza = zzu0;
        super(zzx0, charSequence0);
    }

    @Override  // com.google.android.gms.internal.common.zzw
    final int zzc(int v) {
        return v + 1;
    }

    @Override  // com.google.android.gms.internal.common.zzw
    final int zzd(int v) {
        CharSequence charSequence0 = this.zzb;
        int v1 = charSequence0.length();
        zzs.zzb(v, v1, "index");
        while(v < v1) {
            int v2 = charSequence0.charAt(v);
            if(this.zza.zza.zza(((char)v2))) {
                return v;
            }
            ++v;
        }
        return -1;
    }
}

