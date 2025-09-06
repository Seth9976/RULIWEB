package com.google.android.gms.internal.fido;

import java.io.IOException;
import javax.annotation.CheckForNull;

final class zzbd extends zzbe {
    zzbd(String s, String s1, @CheckForNull Character character0) {
        zzbb zzbb0 = new zzbb(s, s1.toCharArray());
        super(zzbb0, character0);
        zzam.zzc(zzbb0.zzf.length == 0x40);
    }

    @Override  // com.google.android.gms.internal.fido.zzbe
    final void zza(Appendable appendable0, byte[] arr_b, int v, int v1) throws IOException {
        int v2 = 0;
        zzam.zze(0, v1, arr_b.length);
        for(int v3 = v1; v3 >= 3; v3 -= 3) {
            zzbb zzbb0 = this.zzb;
            int v4 = (arr_b[v2 + 1] & 0xFF) << 8 | (arr_b[v2] & 0xFF) << 16 | arr_b[v2 + 2] & 0xFF;
            appendable0.append(zzbb0.zza(v4 >>> 18));
            appendable0.append(this.zzb.zza(v4 >>> 12 & 0x3F));
            appendable0.append(this.zzb.zza(v4 >>> 6 & 0x3F));
            appendable0.append(this.zzb.zza(v4 & 0x3F));
            v2 += 3;
        }
        if(v2 < v1) {
            this.zzc(appendable0, arr_b, v2, v1 - v2);
        }
    }
}

