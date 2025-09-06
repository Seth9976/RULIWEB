package com.google.android.gms.internal.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jspecify.nullness.NullMarked;

@NullMarked
public final class zzx {
    private final zzo zza;
    private final boolean zzb;
    private final zzu zzc;

    private zzx(zzu zzu0, boolean z, zzo zzo0, int v) {
        this.zzc = zzu0;
        this.zzb = z;
        this.zza = zzo0;
    }

    static zzo zza(zzx zzx0) {
        return zzx0.zza;
    }

    public final zzx zzb() {
        return new zzx(this.zzc, true, this.zza, 0x7FFFFFFF);
    }

    public static zzx zzc(zzo zzo0) {
        return new zzx(new zzu(zzo0), false, zzn.zza, 0x7FFFFFFF);
    }

    public final Iterable zzd(CharSequence charSequence0) {
        return new zzv(this, charSequence0);
    }

    static Iterator zze(zzx zzx0, CharSequence charSequence0) {
        return zzx0.zzh(charSequence0);
    }

    public final List zzf(CharSequence charSequence0) {
        charSequence0.getClass();
        Iterator iterator0 = this.zzh(charSequence0);
        ArrayList arrayList0 = new ArrayList();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            arrayList0.add(((String)object0));
        }
        return Collections.unmodifiableList(arrayList0);
    }

    static boolean zzg(zzx zzx0) {
        return zzx0.zzb;
    }

    private final Iterator zzh(CharSequence charSequence0) {
        return new zzt(this.zzc, this, charSequence0);
    }
}

