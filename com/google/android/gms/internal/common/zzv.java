package com.google.android.gms.internal.common;

import java.io.IOException;
import java.util.Iterator;

final class zzv implements Iterable {
    final CharSequence zza;
    final zzx zzb;

    zzv(zzx zzx0, CharSequence charSequence0) {
        this.zzb = zzx0;
        this.zza = charSequence0;
        super();
    }

    @Override
    public final Iterator iterator() {
        return zzx.zze(this.zzb, this.zza);
    }

    @Override
    public final String toString() {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append('[');
        Iterator iterator0 = this.iterator();
        try {
            if(iterator0.hasNext()) {
                Object object0 = iterator0.next();
                stringBuilder0.append(zzq.zza(object0, ", "));
                while(iterator0.hasNext()) {
                    stringBuilder0.append(", ");
                    Object object1 = iterator0.next();
                    stringBuilder0.append(zzq.zza(object1, ", "));
                }
            }
        }
        catch(IOException iOException0) {
            throw new AssertionError(iOException0);
        }
        stringBuilder0.append(']');
        return stringBuilder0.toString();
    }
}

