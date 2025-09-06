package com.google.android.gms.internal.auth;

import android.net.Uri;
import androidx.collection.SimpleArrayMap;
import javax.annotation.Nullable;

public final class zzci {
    private final SimpleArrayMap zza;

    zzci(SimpleArrayMap simpleArrayMap0) {
        this.zza = simpleArrayMap0;
    }

    @Nullable
    public final String zza(@Nullable Uri uri0, @Nullable String s, @Nullable String s1, String s2) {
        SimpleArrayMap simpleArrayMap0 = uri0 == null ? null : ((SimpleArrayMap)this.zza.get(uri0.toString()));
        return simpleArrayMap0 == null ? null : ((String)simpleArrayMap0.get("" + s2));
    }
}

