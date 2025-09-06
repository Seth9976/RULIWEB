package com.google.android.gms.internal.fido;

import java.util.Arrays;

public final class zzbj extends zzbk {
    private final String zza;

    zzbj(String s) {
        this.zza = s;
    }

    @Override
    public final int compareTo(Object object0) {
        ((zzbk)object0).zza();
        String s = ((zzbj)(((zzbk)object0))).zza;
        return this.zza.length() == s.length() ? this.zza.compareTo(s) : this.zza.length() - s.length();
    }

    @Override
    public final boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(object0 == null) {
            return false;
        }
        return this.getClass() == object0.getClass() ? this.zza.equals(((zzbj)object0).zza) : false;
    }

    @Override
    public final int hashCode() {
        return Arrays.hashCode(new Object[]{3, this.zza});
    }

    @Override
    public final String toString() {
        return "\"" + this.zza + "\"";
    }

    @Override  // com.google.android.gms.internal.fido.zzbk
    protected final int zza() {
        return 3;
    }
}

