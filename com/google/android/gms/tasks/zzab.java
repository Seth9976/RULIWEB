package com.google.android.gms.tasks;

import java.util.ArrayList;
import java.util.Collection;

final class zzab implements Continuation {
    final Collection zza;

    zzab(Collection collection0) {
        this.zza = collection0;
        super();
    }

    @Override  // com.google.android.gms.tasks.Continuation
    public final Object then(Task task0) throws Exception {
        ArrayList arrayList0 = new ArrayList();
        arrayList0.addAll(this.zza);
        return Tasks.forResult(arrayList0);
    }
}

