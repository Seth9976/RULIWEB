package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.WeakHashMap;

public final class zaad {
    private final Map zaa;
    private final Map zab;

    public zaad() {
        this.zaa = Collections.synchronizedMap(new WeakHashMap());
        this.zab = Collections.synchronizedMap(new WeakHashMap());
    }

    static Map zaa(zaad zaad0) {
        return zaad0.zaa;
    }

    static Map zab(zaad zaad0) {
        return zaad0.zab;
    }

    final void zac(BasePendingResult basePendingResult0, boolean z) {
        this.zaa.put(basePendingResult0, Boolean.valueOf(z));
        basePendingResult0.addStatusListener(new zaab(this, basePendingResult0));
    }

    final void zad(TaskCompletionSource taskCompletionSource0, boolean z) {
        this.zab.put(taskCompletionSource0, Boolean.valueOf(z));
        taskCompletionSource0.getTask().addOnCompleteListener(new zaac(this, taskCompletionSource0));
    }

    final void zae(int v, String s) {
        StringBuilder stringBuilder0 = new StringBuilder("The connection to Google Play services was lost");
        if(v == 1) {
            stringBuilder0.append(" due to service disconnection.");
        }
        else if(v == 3) {
            stringBuilder0.append(" due to dead object exception.");
        }
        if(s != null) {
            stringBuilder0.append(" Last reason for disconnect: ");
            stringBuilder0.append(s);
        }
        this.zah(true, new Status(20, stringBuilder0.toString()));
    }

    public final void zaf() {
        this.zah(false, GoogleApiManager.zaa);
    }

    // 去混淆评级： 低(20)
    final boolean zag() {
        return !this.zaa.isEmpty() || !this.zab.isEmpty();
    }

    private final void zah(boolean z, Status status0) {
        synchronized(this.zaa) {
            HashMap hashMap0 = new HashMap(this.zaa);
        }
        synchronized(this.zab) {
            HashMap hashMap1 = new HashMap(this.zab);
        }
        for(Object object0: hashMap0.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(z || ((Boolean)map$Entry0.getValue()).booleanValue()) {
                ((BasePendingResult)map$Entry0.getKey()).forceFailureUnlessReady(status0);
            }
        }
        for(Object object1: hashMap1.entrySet()) {
            Map.Entry map$Entry1 = (Map.Entry)object1;
            if(z || ((Boolean)map$Entry1.getValue()).booleanValue()) {
                ((TaskCompletionSource)map$Entry1.getKey()).trySetException(new ApiException(status0));
            }
        }
    }
}

