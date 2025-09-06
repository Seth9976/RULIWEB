package com.google.android.gms.internal.fido;

import java.util.Arrays;
import javax.annotation.CheckForNull;

public final class zzaj {
    private final String zza;
    private final zzah zzb;
    private zzah zzc;

    zzaj(String s, zzai zzai0) {
        zzah zzah0 = new zzah(null);
        this.zzb = zzah0;
        this.zzc = zzah0;
        s.getClass();
        this.zza = s;
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder(0x20);
        stringBuilder0.append(this.zza);
        stringBuilder0.append('{');
        zzah zzah0 = this.zzb.zzc;
        for(String s = ""; zzah0 != null; s = ", ") {
            Object object0 = zzah0.zzb;
            stringBuilder0.append(s);
            String s1 = zzah0.zza;
            if(s1 != null) {
                stringBuilder0.append(s1);
                stringBuilder0.append('=');
            }
            if(object0 == null || !object0.getClass().isArray()) {
                stringBuilder0.append(object0);
            }
            else {
                String s2 = Arrays.deepToString(new Object[]{object0});
                stringBuilder0.append(s2, 1, s2.length() - 1);
            }
            zzah0 = zzah0.zzc;
        }
        stringBuilder0.append('}');
        return stringBuilder0.toString();
    }

    public final zzaj zza(String s, int v) {
        zzaf zzaf0 = new zzaf(null);
        this.zzc.zzc = zzaf0;
        this.zzc = zzaf0;
        zzaf0.zzb = String.valueOf(v);
        zzaf0.zza = "errorCode";
        return this;
    }

    public final zzaj zzb(String s, @CheckForNull Object object0) {
        zzah zzah0 = new zzah(null);
        this.zzc.zzc = zzah0;
        this.zzc = zzah0;
        zzah0.zzb = object0;
        zzah0.zza = s;
        return this;
    }
}

