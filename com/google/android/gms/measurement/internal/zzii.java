package com.google.android.gms.measurement.internal;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzra;

final class zzii implements Runnable {
    final boolean zza;
    final Uri zzb;
    final String zzc;
    final String zzd;
    final zzij zze;

    zzii(zzij zzij0, boolean z, Uri uri0, String s, String s1) {
        this.zze = zzij0;
        this.zza = z;
        this.zzb = uri0;
        this.zzc = s;
        this.zzd = s1;
        super();
    }

    @Override
    public final void run() {
        Bundle bundle0;
        zzij zzij0 = this.zze;
        boolean z = this.zza;
        Uri uri0 = this.zzb;
        String s = this.zzc;
        String s1 = this.zzd;
        zzij0.zza.zzg();
        try {
            zzlp zzlp0 = zzij0.zza.zzt.zzv();
            zzra.zzc();
            boolean z1 = zzij0.zza.zzt.zzf().zzs(null, zzeg.zzav);
            if(TextUtils.isEmpty(s1)) {
                bundle0 = null;
            }
            else if(s1.contains("gclid") || s1.contains("utm_campaign") || s1.contains("utm_source") || s1.contains("utm_medium") || s1.contains("utm_id") || s1.contains("dclid") || s1.contains("srsltid")) {
            label_16:
                bundle0 = zzlp0.zzs(Uri.parse(("https://google.com/search?" + s1)), z1);
                if(bundle0 != null) {
                    bundle0.putString("_cis", "referrer");
                }
            }
            else if(z1 && s1.contains("sfmc_id")) {
                z1 = true;
                goto label_16;
            }
            else {
                zzlp0.zzt.zzaA().zzc().zza("Activity created with data \'referrer\' without required params");
                bundle0 = null;
            }
            if(z) {
                zzlp zzlp1 = zzij0.zza.zzt.zzv();
                zzra.zzc();
                Bundle bundle1 = zzlp1.zzs(uri0, zzij0.zza.zzt.zzf().zzs(null, zzeg.zzav));
                if(bundle1 != null) {
                    bundle1.putString("_cis", "intent");
                    if(!bundle1.containsKey("gclid") && bundle0 != null && bundle0.containsKey("gclid")) {
                        bundle1.putString("_cer", String.format("gclid=%s", bundle0.getString("gclid")));
                    }
                    zzij0.zza.zzG(s, "_cmp", bundle1);
                    zzij0.zza.zzb.zza(s, bundle1);
                }
            }
            if(!TextUtils.isEmpty(s1)) {
                zzij0.zza.zzt.zzaA().zzc().zzb("Activity created with referrer", s1);
                if(zzij0.zza.zzt.zzf().zzs(null, zzeg.zzaa)) {
                    goto label_40;
                }
                if(!s1.contains("gclid") || !s1.contains("utm_campaign") && !s1.contains("utm_source") && !s1.contains("utm_medium") && !s1.contains("utm_term") && !s1.contains("utm_content")) {
                    zzij0.zza.zzt.zzaA().zzc().zza("Activity created with data \'referrer\' without required params");
                    return;
                label_40:
                    if(bundle0 == null) {
                        zzij0.zza.zzt.zzaA().zzc().zzb("Referrer does not contain valid parameters", s1);
                    }
                    else {
                        zzij0.zza.zzG(s, "_cmp", bundle0);
                        zzij0.zza.zzb.zza(s, bundle0);
                    }
                    zzij0.zza.zzW("auto", "_ldl", null, true);
                }
                else if(!TextUtils.isEmpty(s1)) {
                    zzij0.zza.zzW("auto", "_ldl", s1, true);
                }
            }
        }
        catch(RuntimeException runtimeException0) {
            zzij0.zza.zzt.zzaA().zzd().zzb("Throwable caught in handleReferrerForOnActivityCreated", runtimeException0);
        }
    }
}

