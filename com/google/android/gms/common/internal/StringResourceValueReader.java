package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.R.string;

public class StringResourceValueReader {
    private final Resources zza;
    private final String zzb;

    public StringResourceValueReader(Context context0) {
        Preconditions.checkNotNull(context0);
        Resources resources0 = context0.getResources();
        this.zza = resources0;
        this.zzb = resources0.getResourcePackageName(string.common_google_play_services_unknown_issue);
    }

    public String getString(String s) {
        int v = this.zza.getIdentifier(s, "string", this.zzb);
        return v == 0 ? null : this.zza.getString(v);
    }
}

