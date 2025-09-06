package com.google.android.gms.tasks;

import android.app.Activity;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.LifecycleFragment;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

final class zzv extends LifecycleCallback {
    private final List zza;

    private zzv(LifecycleFragment lifecycleFragment0) {
        super(lifecycleFragment0);
        this.zza = new ArrayList();
        this.mLifecycleFragment.addCallback("TaskOnStopCallback", this);
    }

    @Override  // com.google.android.gms.common.api.internal.LifecycleCallback
    public final void onStop() {
        synchronized(this.zza) {
            for(Object object0: this.zza) {
                zzq zzq0 = (zzq)((WeakReference)object0).get();
                if(zzq0 != null) {
                    zzq0.zzc();
                }
            }
            this.zza.clear();
        }
    }

    public static zzv zza(Activity activity0) {
        LifecycleFragment lifecycleFragment0 = zzv.getFragment(activity0);
        synchronized(lifecycleFragment0) {
            zzv zzv0 = (zzv)lifecycleFragment0.getCallbackOrNull("TaskOnStopCallback", zzv.class);
            if(zzv0 == null) {
                zzv0 = new zzv(lifecycleFragment0);
            }
            return zzv0;
        }
    }

    public final void zzb(zzq zzq0) {
        synchronized(this.zza) {
            WeakReference weakReference0 = new WeakReference(zzq0);
            this.zza.add(weakReference0);
        }
    }
}

