package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzac implements Iterator {
    final Iterator zza;
    final Iterator zzb;

    zzac(zzae zzae0, Iterator iterator0, Iterator iterator1) {
        this.zza = iterator0;
        this.zzb = iterator1;
        super();
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean hasNext() {
        return this.zza.hasNext() ? true : this.zzb.hasNext();
    }

    @Override
    public final Object next() {
        if(this.zza.hasNext()) {
            Object object0 = this.zza.next();
            return new zzat(((Integer)object0).toString());
        }
        if(!this.zzb.hasNext()) {
            throw new NoSuchElementException();
        }
        Object object1 = this.zzb.next();
        return new zzat(((String)object1));
    }
}

