package com.google.android.gms.measurement.internal;

import android.net.Uri.Builder;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;

public final class zzs {
    private final zzgd zza;

    public zzs(zzgd zzgd0) {
        this.zza = zzgd0;
    }

    final void zza(String s, Bundle bundle0) {
        CharSequence charSequence0;
        this.zza.zzaB().zzg();
        if(!this.zza.zzJ()) {
            if(bundle0.isEmpty()) {
                charSequence0 = null;
            }
            else {
                if(s.isEmpty()) {
                    s = "auto";
                }
                Uri.Builder uri$Builder0 = new Uri.Builder();
                uri$Builder0.path(s);
                for(Object object0: bundle0.keySet()) {
                    uri$Builder0.appendQueryParameter(((String)object0), bundle0.getString(((String)object0)));
                }
                charSequence0 = uri$Builder0.build().toString();
            }
            if(!TextUtils.isEmpty(charSequence0)) {
                this.zza.zzm().zzq.zzb(((String)charSequence0));
                zzfi zzfi0 = this.zza.zzm();
                long v = this.zza.zzax().currentTimeMillis();
                zzfi0.zzr.zzb(v);
            }
        }
    }

    final void zzb() {
        this.zza.zzaB().zzg();
        if(!this.zzd()) {
            return;
        }
        if(this.zze()) {
            this.zza.zzm().zzq.zzb(null);
            Bundle bundle0 = new Bundle();
            bundle0.putString("source", "(not set)");
            bundle0.putString("medium", "(not set)");
            bundle0.putString("_cis", "intent");
            bundle0.putLong("_cc", 1L);
            this.zza.zzq().zzG("auto", "_cmpx", bundle0);
        }
        else {
            String s = this.zza.zzm().zzq.zza();
            if(TextUtils.isEmpty(s)) {
                this.zza.zzaA().zzh().zza("Cache still valid but referrer not found");
            }
            else {
                long v = this.zza.zzm().zzr.zza();
                Uri uri0 = Uri.parse(s);
                Bundle bundle1 = new Bundle();
                Pair pair0 = new Pair(uri0.getPath(), bundle1);
                for(Object object0: uri0.getQueryParameterNames()) {
                    bundle1.putString(((String)object0), uri0.getQueryParameter(((String)object0)));
                }
                ((Bundle)pair0.second).putLong("_cc", (v / 3600000L - 1L) * 3600000L);
                String s1 = pair0.first == null ? "app" : ((String)pair0.first);
                this.zza.zzq().zzG(s1, "_cmp", ((Bundle)pair0.second));
            }
            this.zza.zzm().zzq.zzb(null);
        }
        this.zza.zzm().zzr.zzb(0L);
    }

    final void zzc() {
        if(this.zzd() && this.zze()) {
            this.zza.zzm().zzq.zzb(null);
        }
    }

    final boolean zzd() {
        return this.zza.zzm().zzr.zza() > 0L;
    }

    final boolean zze() {
        return this.zzd() ? this.zza.zzax().currentTimeMillis() - this.zza.zzm().zzr.zza() > this.zza.zzf().zzi(null, zzeg.zzS) : false;
    }
}

