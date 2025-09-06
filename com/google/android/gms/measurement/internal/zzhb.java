package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.EnumMap;

public final class zzhb {
    public static final zzhb zza;
    private final EnumMap zzb;
    private final int zzc;

    static {
        zzhb.zza = new zzhb(null, null, 100);
    }

    public zzhb(Boolean boolean0, Boolean boolean1, int v) {
        EnumMap enumMap0 = new EnumMap(zzha.class);
        this.zzb = enumMap0;
        enumMap0.put(zzha.zza, boolean0);
        enumMap0.put(zzha.zzb, boolean1);
        this.zzc = v;
    }

    public zzhb(EnumMap enumMap0, int v) {
        EnumMap enumMap1 = new EnumMap(zzha.class);
        this.zzb = enumMap1;
        enumMap1.putAll(enumMap0);
        this.zzc = v;
    }

    @Override
    public final boolean equals(Object object0) {
        if(!(object0 instanceof zzhb)) {
            return false;
        }
        zzha[] arr_zzha = zzha.values();
        for(int v = 0; v < arr_zzha.length; ++v) {
            zzha zzha0 = arr_zzha[v];
            if(zzhb.zzo(((Boolean)this.zzb.get(zzha0))) != zzhb.zzo(((Boolean)((zzhb)object0).zzb.get(zzha0)))) {
                return false;
            }
        }
        return this.zzc == ((zzhb)object0).zzc;
    }

    @Override
    public final int hashCode() {
        int v = this.zzc * 17;
        for(Object object0: this.zzb.values()) {
            v = v * 0x1F + zzhb.zzo(((Boolean)object0));
        }
        return v;
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("settings: source=");
        stringBuilder0.append(this.zzc);
        zzha[] arr_zzha = zzha.values();
        for(int v = 0; v < arr_zzha.length; ++v) {
            zzha zzha0 = arr_zzha[v];
            stringBuilder0.append(", ");
            stringBuilder0.append(zzha0.name());
            stringBuilder0.append("=");
            Boolean boolean0 = (Boolean)this.zzb.get(zzha0);
            if(boolean0 == null) {
                stringBuilder0.append("uninitialized");
            }
            else {
                stringBuilder0.append((boolean0.booleanValue() ? "granted" : "denied"));
            }
        }
        return stringBuilder0.toString();
    }

    public final int zza() {
        return this.zzc;
    }

    public static zzhb zzb(Bundle bundle0, int v) {
        if(bundle0 == null) {
            return new zzhb(null, null, v);
        }
        EnumMap enumMap0 = new EnumMap(zzha.class);
        zzha[] arr_zzha = zzha.values();
        for(int v1 = 0; v1 < arr_zzha.length; ++v1) {
            zzha zzha0 = arr_zzha[v1];
            enumMap0.put(zzha0, zzhb.zzp(bundle0.getString(zzha0.zzd)));
        }
        return new zzhb(enumMap0, v);
    }

    public static zzhb zzc(String s, int v) {
        EnumMap enumMap0 = new EnumMap(zzha.class);
        if(s != null) {
            for(int v1 = 0; v1 < 2; ++v1) {
                zzha zzha0 = zzha.zzc[v1];
                if(v1 + 2 < s.length()) {
                    Boolean boolean0 = null;
                    switch(s.charAt(v1 + 2)) {
                        case 0x30: {
                            boolean0 = Boolean.FALSE;
                            break;
                        }
                        case 49: {
                            boolean0 = Boolean.TRUE;
                        }
                    }
                    enumMap0.put(zzha0, boolean0);
                }
            }
        }
        return new zzhb(enumMap0, v);
    }

    public final zzhb zzd(zzhb zzhb0) {
        EnumMap enumMap0 = new EnumMap(zzha.class);
        zzha[] arr_zzha = zzha.values();
        for(int v = 0; v < arr_zzha.length; ++v) {
            zzha zzha0 = arr_zzha[v];
            Boolean boolean0 = (Boolean)this.zzb.get(zzha0);
            Boolean boolean1 = (Boolean)zzhb0.zzb.get(zzha0);
            if(boolean0 == null) {
                boolean0 = boolean1;
            }
            else if(boolean1 != null) {
                boolean0 = Boolean.valueOf(boolean0.booleanValue() && boolean1.booleanValue());
            }
            enumMap0.put(zzha0, boolean0);
        }
        return new zzhb(enumMap0, 100);
    }

    public final zzhb zze(zzhb zzhb0) {
        EnumMap enumMap0 = new EnumMap(zzha.class);
        zzha[] arr_zzha = zzha.values();
        for(int v = 0; v < arr_zzha.length; ++v) {
            zzha zzha0 = arr_zzha[v];
            Boolean boolean0 = (Boolean)this.zzb.get(zzha0);
            if(boolean0 == null) {
                boolean0 = (Boolean)zzhb0.zzb.get(zzha0);
            }
            enumMap0.put(zzha0, boolean0);
        }
        return new zzhb(enumMap0, this.zzc);
    }

    public final Boolean zzf() {
        return (Boolean)this.zzb.get(zzha.zza);
    }

    public final Boolean zzg() {
        return (Boolean)this.zzb.get(zzha.zzb);
    }

    public static String zzh(Bundle bundle0) {
        zzha[] arr_zzha = zzha.values();
        for(int v = 0; v < arr_zzha.length; ++v) {
            zzha zzha0 = arr_zzha[v];
            if(bundle0.containsKey(zzha0.zzd)) {
                String s = bundle0.getString(zzha0.zzd);
                if(s != null && zzhb.zzp(s) == null) {
                    return s;
                }
            }
        }
        return null;
    }

    public final String zzi() {
        int v1;
        StringBuilder stringBuilder0 = new StringBuilder("G1");
        zzha[] arr_zzha = zzha.zzc;
        for(int v = 0; v < 2; ++v) {
            Boolean boolean0 = (Boolean)this.zzb.get(arr_zzha[v]);
            if(boolean0 == null) {
                v1 = 45;
            }
            else {
                v1 = boolean0.booleanValue() ? 49 : 0x30;
            }
            stringBuilder0.append(((char)v1));
        }
        return stringBuilder0.toString();
    }

    public final boolean zzj(zzha zzha0) {
        Boolean boolean0 = (Boolean)this.zzb.get(zzha0);
        return boolean0 == null || boolean0.booleanValue();
    }

    public static boolean zzk(int v, int v1) {
        return v <= v1;
    }

    public final boolean zzl() {
        for(Object object0: this.zzb.values()) {
            if(((Boolean)object0) != null) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    public final boolean zzm(zzhb zzhb0) {
        return this.zzn(zzhb0, ((zzha[])this.zzb.keySet().toArray(new zzha[0])));
    }

    public final boolean zzn(zzhb zzhb0, zzha[] arr_zzha) {
        for(int v = 0; v < arr_zzha.length; ++v) {
            zzha zzha0 = arr_zzha[v];
            Boolean boolean0 = (Boolean)this.zzb.get(zzha0);
            Boolean boolean1 = (Boolean)zzhb0.zzb.get(zzha0);
            if(boolean0 == Boolean.FALSE && boolean1 != Boolean.FALSE) {
                return true;
            }
        }
        return false;
    }

    static final int zzo(Boolean boolean0) {
        if(boolean0 == null) {
            return 0;
        }
        return boolean0.booleanValue() ? 1 : 2;
    }

    private static Boolean zzp(String s) {
        if(s == null) {
            return null;
        }
        if(s.equals("granted")) {
            return true;
        }
        return s.equals("denied") ? false : null;
    }
}

