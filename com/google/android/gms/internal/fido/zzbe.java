package com.google.android.gms.internal.fido;

import java.io.IOException;
import java.math.RoundingMode;
import javax.annotation.CheckForNull;

class zzbe extends zzbf {
    final zzbb zzb;
    @CheckForNull
    final Character zzc;

    zzbe(zzbb zzbb0, @CheckForNull Character character0) {
        this.zzb = zzbb0;
        if(character0 != null) {
            character0.charValue();
            if(zzbb0.zzb('=')) {
                throw new IllegalArgumentException(zzan.zza("Padding character %s was already in alphabet", new Object[]{character0}));
            }
        }
        this.zzc = character0;
    }

    zzbe(String s, String s1, @CheckForNull Character character0) {
        this(new zzbb(s, s1.toCharArray()), character0);
    }

    @Override
    public final boolean equals(@CheckForNull Object object0) {
        if(object0 instanceof zzbe && this.zzb.equals(((zzbe)object0).zzb)) {
            Character character0 = ((zzbe)object0).zzc;
            return this.zzc == character0 || this.zzc != null && this.zzc.equals(character0);
        }
        return false;
    }

    @Override
    public final int hashCode() {
        int v = this.zzb.hashCode();
        return this.zzc == null ? v : v ^ this.zzc.hashCode();
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder("BaseEncoding.");
        stringBuilder0.append(this.zzb);
        if(8 % this.zzb.zzb != 0) {
            if(this.zzc == null) {
                stringBuilder0.append(".omitPadding()");
                return stringBuilder0.toString();
            }
            stringBuilder0.append(".withPadChar(\'");
            stringBuilder0.append(this.zzc);
            stringBuilder0.append("\')");
        }
        return stringBuilder0.toString();
    }

    @Override  // com.google.android.gms.internal.fido.zzbf
    void zza(Appendable appendable0, byte[] arr_b, int v, int v1) throws IOException {
        zzam.zze(0, v1, arr_b.length);
        for(int v2 = 0; v2 < v1; v2 += this.zzb.zzd) {
            this.zzc(appendable0, arr_b, v2, Math.min(this.zzb.zzd, v1 - v2));
        }
    }

    @Override  // com.google.android.gms.internal.fido.zzbf
    final int zzb(int v) {
        int v1 = zzbh.zza(v, this.zzb.zzd, RoundingMode.CEILING);
        return this.zzb.zzc * v1;
    }

    final void zzc(Appendable appendable0, byte[] arr_b, int v, int v1) throws IOException {
        zzam.zze(v, v + v1, arr_b.length);
        int v2 = 0;
        zzam.zzc(v1 <= this.zzb.zzd);
        long v3 = 0L;
        for(int v4 = 0; v4 < v1; ++v4) {
            v3 = (v3 | ((long)(arr_b[v + v4] & 0xFF))) << 8;
        }
        int v5 = (v1 + 1) * 8 - this.zzb.zzb;
        while(v2 < v1 * 8) {
            appendable0.append(this.zzb.zza(this.zzb.zza & ((int)(v3 >>> v5 - v2))));
            v2 += this.zzb.zzb;
        }
        if(this.zzc != null) {
            while(v2 < this.zzb.zzd * 8) {
                this.zzc.charValue();
                appendable0.append('=');
                v2 += this.zzb.zzb;
            }
        }
    }
}

