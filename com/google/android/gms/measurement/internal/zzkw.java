package com.google.android.gms.measurement.internal;

import android.net.Uri.Builder;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzrd;

public final class zzkw extends zzkt {
    zzkw(zzlh zzlh0) {
        super(zzlh0);
    }

    public final zzkv zza(String s) {
        zzrd.zzc();
        if(this.zzt.zzf().zzs(null, zzeg.zzaq)) {
            this.zzt.zzaA().zzj().zza("sgtm feature flag enabled.");
            zzh zzh0 = this.zzf.zzh().zzj(s);
            if(zzh0 == null) {
                return new zzkv(this.zzd(s));
            }
            if(zzh0.zzap()) {
                this.zzt.zzaA().zzj().zza("sgtm upload enabled in manifest.");
                boolean z = this.zzf.zzm().zze(zzh0.zzv()) == null;
            }
        }
        return new zzkv(this.zzd(s));
    }

    private final String zzd(String s) {
        String s1 = this.zzf.zzm().zzi(s);
        if(!TextUtils.isEmpty(s1)) {
            Uri uri0 = Uri.parse("https://app-measurement.com/a");
            Uri.Builder uri$Builder0 = uri0.buildUpon();
            uri$Builder0.authority(s1 + "." + uri0.getAuthority());
            return uri$Builder0.build().toString();
        }
        return "https://app-measurement.com/a";
    }
}

