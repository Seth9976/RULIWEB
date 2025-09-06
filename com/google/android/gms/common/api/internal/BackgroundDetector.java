package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean arg1);
    }

    private static final BackgroundDetector zza;
    private final AtomicBoolean zzb;
    private final AtomicBoolean zzc;
    private final ArrayList zzd;
    private boolean zze;

    static {
        BackgroundDetector.zza = new BackgroundDetector();
    }

    private BackgroundDetector() {
        this.zzb = new AtomicBoolean();
        this.zzc = new AtomicBoolean();
        this.zzd = new ArrayList();
        this.zze = false;
    }

    public void addListener(BackgroundStateChangeListener backgroundDetector$BackgroundStateChangeListener0) {
        synchronized(BackgroundDetector.zza) {
            this.zzd.add(backgroundDetector$BackgroundStateChangeListener0);
        }
    }

    public static BackgroundDetector getInstance() {
        return BackgroundDetector.zza;
    }

    public static void initialize(Application application0) {
        BackgroundDetector backgroundDetector0 = BackgroundDetector.zza;
        synchronized(backgroundDetector0) {
            if(!backgroundDetector0.zze) {
                application0.registerActivityLifecycleCallbacks(backgroundDetector0);
                application0.registerComponentCallbacks(backgroundDetector0);
                backgroundDetector0.zze = true;
            }
        }
    }

    public boolean isInBackground() {
        return this.zzb.get();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity0, Bundle bundle0) {
        boolean z = this.zzb.compareAndSet(true, false);
        this.zzc.set(true);
        if(z) {
            this.zza(false);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity0) {
        boolean z = this.zzb.compareAndSet(true, false);
        this.zzc.set(true);
        if(z) {
            this.zza(false);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity0) {
    }

    @Override  // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration0) {
    }

    @Override  // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override  // android.content.ComponentCallbacks2
    public final void onTrimMemory(int v) {
        if(v == 20 && this.zzb.compareAndSet(false, true)) {
            this.zzc.set(true);
            this.zza(true);
        }
    }

    public boolean readCurrentStateIfPossible(boolean z) {
        if(!this.zzc.get()) {
            ActivityManager.RunningAppProcessInfo activityManager$RunningAppProcessInfo0 = new ActivityManager.RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(activityManager$RunningAppProcessInfo0);
            if(!this.zzc.getAndSet(true) && activityManager$RunningAppProcessInfo0.importance > 100) {
                this.zzb.set(true);
            }
        }
        return this.isInBackground();
    }

    private final void zza(boolean z) {
        synchronized(BackgroundDetector.zza) {
            for(Object object0: this.zzd) {
                ((BackgroundStateChangeListener)object0).onBackgroundStateChanged(z);
            }
        }
    }
}

