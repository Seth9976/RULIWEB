package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.internal.Preconditions;

public class LifecycleActivity {
    private final Object zza;

    public LifecycleActivity(Activity activity0) {
        Preconditions.checkNotNull(activity0, "Activity must not be null");
        this.zza = activity0;
    }

    public LifecycleActivity(ContextWrapper contextWrapper0) {
        throw new UnsupportedOperationException();
    }

    public final Activity zza() {
        return (Activity)this.zza;
    }

    public final FragmentActivity zzb() {
        return (FragmentActivity)this.zza;
    }

    public final boolean zzc() {
        return this.zza instanceof Activity;
    }

    public final boolean zzd() {
        return this.zza instanceof FragmentActivity;
    }
}

