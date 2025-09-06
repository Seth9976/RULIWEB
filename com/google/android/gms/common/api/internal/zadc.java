package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public final class zadc {
    public static final Status zaa;
    final Set zab;
    private final zadb zac;

    static {
        zadc.zaa = new Status(8, "The connection to Google Play services was lost");
    }

    public zadc() {
        this.zab = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
        this.zac = new zadb(this);
    }

    final void zaa(BasePendingResult basePendingResult0) {
        this.zab.add(basePendingResult0);
        basePendingResult0.zan(this.zac);
    }

    public final void zab() {
        BasePendingResult[] arr_basePendingResult = (BasePendingResult[])this.zab.toArray(new BasePendingResult[0]);
        for(int v = 0; v < arr_basePendingResult.length; ++v) {
            BasePendingResult basePendingResult0 = arr_basePendingResult[v];
            basePendingResult0.zan(null);
            if(basePendingResult0.zam()) {
                this.zab.remove(basePendingResult0);
            }
        }
    }
}

