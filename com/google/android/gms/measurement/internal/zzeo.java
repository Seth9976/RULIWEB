package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public final class zzeo {
    protected static final AtomicReference zza;
    protected static final AtomicReference zzb;
    protected static final AtomicReference zzc;
    private final zzen zzd;

    static {
        zzeo.zza = new AtomicReference();
        zzeo.zzb = new AtomicReference();
        zzeo.zzc = new AtomicReference();
    }

    public zzeo(zzen zzen0) {
        this.zzd = zzen0;
    }

    protected final String zza(Object[] arr_object) {
        if(arr_object == null) {
            return "[]";
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("[");
        for(int v = 0; v < arr_object.length; ++v) {
            Object object0 = arr_object[v];
            String s = object0 instanceof Bundle ? this.zzb(((Bundle)object0)) : String.valueOf(object0);
            if(s != null) {
                if(stringBuilder0.length() != 1) {
                    stringBuilder0.append(", ");
                }
                stringBuilder0.append(s);
            }
        }
        stringBuilder0.append("]");
        return stringBuilder0.toString();
    }

    protected final String zzb(Bundle bundle0) {
        String s;
        if(bundle0 == null) {
            return null;
        }
        if(!this.zzd.zza()) {
            return bundle0.toString();
        }
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append("Bundle[{");
        for(Object object0: bundle0.keySet()) {
            if(stringBuilder0.length() != 8) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(this.zze(((String)object0)));
            stringBuilder0.append("=");
            Object object1 = bundle0.get(((String)object0));
            if(object1 instanceof Bundle) {
                s = this.zza(new Object[]{object1});
            }
            else if(object1 instanceof Object[]) {
                s = this.zza(((Object[])object1));
            }
            else {
                s = object1 instanceof ArrayList ? this.zza(((ArrayList)object1).toArray()) : String.valueOf(object1);
            }
            stringBuilder0.append(s);
        }
        stringBuilder0.append("}]");
        return stringBuilder0.toString();
    }

    protected final String zzc(zzau zzau0) {
        String s;
        if(!this.zzd.zza()) {
            return zzau0.toString();
        }
        StringBuilder stringBuilder0 = new StringBuilder("origin=");
        stringBuilder0.append(zzau0.zzc);
        stringBuilder0.append(",name=");
        stringBuilder0.append(this.zzd(zzau0.zza));
        stringBuilder0.append(",params=");
        zzas zzas0 = zzau0.zzb;
        if(zzas0 == null) {
            s = null;
        }
        else {
            s = this.zzd.zza() ? this.zzb(zzas0.zzc()) : zzas0.toString();
        }
        stringBuilder0.append(s);
        return stringBuilder0.toString();
    }

    protected final String zzd(String s) {
        if(s == null) {
            return null;
        }
        return this.zzd.zza() ? zzeo.zzg(s, zzhc.zzc, zzhc.zza, zzeo.zza) : s;
    }

    protected final String zze(String s) {
        if(s == null) {
            return null;
        }
        return this.zzd.zza() ? zzeo.zzg(s, zzhd.zzb, zzhd.zza, zzeo.zzb) : s;
    }

    protected final String zzf(String s) {
        if(s == null) {
            return null;
        }
        if(!this.zzd.zza()) {
            return s;
        }
        return s.startsWith("_exp_") ? "experiment_id(" + s + ")" : zzeo.zzg(s, zzhe.zzb, zzhe.zza, zzeo.zzc);
    }

    private static final String zzg(String s, String[] arr_s, String[] arr_s1, AtomicReference atomicReference0) {
        Preconditions.checkNotNull(arr_s);
        Preconditions.checkNotNull(arr_s1);
        Preconditions.checkNotNull(atomicReference0);
        int v = 0;
        Preconditions.checkArgument(arr_s.length == arr_s1.length);
        while(v < arr_s.length) {
            String s1 = arr_s[v];
            if(s != s1 && !s.equals(s1)) {
                ++v;
            }
            else {
                synchronized(atomicReference0) {
                    String[] arr_s2 = (String[])atomicReference0.get();
                    if(arr_s2 == null) {
                        arr_s2 = new String[arr_s1.length];
                        atomicReference0.set(arr_s2);
                    }
                    String s2 = arr_s2[v];
                    if(s2 == null) {
                        s2 = arr_s1[v] + "(" + arr_s[v] + ")";
                        arr_s2[v] = s2;
                    }
                    return s2;
                }
            }
        }
        return s;
    }
}

