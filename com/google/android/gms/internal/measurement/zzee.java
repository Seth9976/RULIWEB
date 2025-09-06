package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;

final class zzee implements Application.ActivityLifecycleCallbacks {
    final zzef zza;

    zzee(zzef zzef0) {
        this.zza = zzef0;
        super();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity0, Bundle bundle0) {
        zzdx zzdx0 = new zzdx(this, bundle0, activity0);
        zzef.zzu(this.zza, zzdx0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity0) {
        zzed zzed0 = new zzed(this, activity0);
        zzef.zzu(this.zza, zzed0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity0) {
        zzea zzea0 = new zzea(this, activity0);
        zzef.zzu(this.zza, zzea0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity0) {
        zzdz zzdz0 = new zzdz(this, activity0);
        zzef.zzu(this.zza, zzdz0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
        zzbz zzbz0 = new zzbz();
        zzec zzec0 = new zzec(this, activity0, zzbz0);
        zzef.zzu(this.zza, zzec0);
        Bundle bundle1 = zzbz0.zzb(50L);
        if(bundle1 != null) {
            bundle0.putAll(bundle1);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity0) {
        zzdy zzdy0 = new zzdy(this, activity0);
        zzef.zzu(this.zza, zzdy0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity0) {
        zzeb zzeb0 = new zzeb(this, activity0);
        zzef.zzu(this.zza, zzeb0);
    }
}

