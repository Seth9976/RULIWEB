package com.google.android.gms.internal.common;

import javax.annotation.CheckForNull;

abstract class zzw extends zzj {
    final CharSequence zzb;
    final zzo zzc;
    final boolean zzd;
    int zze;
    int zzf;

    protected zzw(zzx zzx0, CharSequence charSequence0) {
        this.zze = 0;
        this.zzc = zzx.zza(zzx0);
        this.zzd = zzx.zzg(zzx0);
        this.zzf = 0x7FFFFFFF;
        this.zzb = charSequence0;
    }

    @Override  // com.google.android.gms.internal.common.zzj
    @CheckForNull
    protected final Object zza() {
        int v3;
        int v = this.zze;
        int v1;
        while((v1 = this.zze) != -1) {
            int v2 = this.zzd(v1);
            if(v2 == -1) {
                v2 = this.zzb.length();
                this.zze = -1;
                v3 = -1;
            }
            else {
                v3 = this.zzc(v2);
                this.zze = v3;
            }
            if(v3 == v) {
                this.zze = v3 + 1;
                if(v3 + 1 <= this.zzb.length()) {
                    continue;
                }
                this.zze = -1;
                continue;
            }
            if(v < v2) {
                this.zzb.charAt(v);
            }
            if(v < v2) {
                this.zzb.charAt(v2 - 1);
            }
            if(this.zzd && v == v2) {
                v = this.zze;
                continue;
            }
            int v4 = this.zzf;
            if(v4 == 1) {
                v2 = this.zzb.length();
                this.zze = -1;
                if(v2 > v) {
                    this.zzb.charAt(v2 - 1);
                    return this.zzb.subSequence(v, v2).toString();
                }
            }
            else {
                this.zzf = v4 - 1;
            }
            return this.zzb.subSequence(v, v2).toString();
        }
        this.zzb();
        return null;
    }

    abstract int zzc(int arg1);

    abstract int zzd(int arg1);
}

