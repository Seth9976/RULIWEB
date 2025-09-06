package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;

final class zzaa implements Continuation {
    final Collection zza;

    zzaa(Collection collection0) {
        this.zza = collection0;
        super();
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(Task task0) throws Exception {
        ArrayList arrayList0 = new ArrayList();
        for(Object object0: this.zza) {
            arrayList0.add(((Task)object0).getResult());
        }
        return arrayList0;
    }
}

