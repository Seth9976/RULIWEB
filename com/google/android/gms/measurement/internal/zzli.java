package com.google.android.gms.measurement.internal;

import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;

public final class zzli {
    final Context zza;

    public zzli(Context context0) {
        Preconditions.checkNotNull(context0);
        Context context1 = context0.getApplicationContext();
        Preconditions.checkNotNull(context1);
        this.zza = context1;
    }
}

