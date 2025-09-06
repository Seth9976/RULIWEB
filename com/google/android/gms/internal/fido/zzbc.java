package com.google.android.gms.internal.fido;

import java.io.IOException;

final class zzbc extends zzbe {
    final char[] zza;

    zzbc(String s, String s1) {
        zzbb zzbb0 = new zzbb("base16()", "0123456789ABCDEF".toCharArray());
        super(zzbb0, null);
        this.zza = new char[0x200];
        zzam.zzc(zzbb0.zzf.length == 16);
        for(int v = 0; v < 0x100; ++v) {
            this.zza[v] = zzbb0.zza(v >>> 4);
            this.zza[v | 0x100] = zzbb0.zza(v & 15);
        }
    }

    @Override  // com.google.android.gms.internal.fido.zzbe
    final void zza(Appendable appendable0, byte[] arr_b, int v, int v1) throws IOException {
        zzam.zze(0, v1, arr_b.length);
        for(int v2 = 0; v2 < v1; ++v2) {
            int v3 = arr_b[v2] & 0xFF;
            appendable0.append(this.zza[v3]);
            appendable0.append(this.zza[v3 | 0x100]);
        }
    }
}

