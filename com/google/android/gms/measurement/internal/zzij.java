package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzpb;

final class zzij implements Application.ActivityLifecycleCallbacks {
    final zzik zza;

    zzij(zzik zzik0) {
        this.zza = zzik0;
        super();
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity0, Bundle bundle0) {
        Uri uri2;
        zzgd zzgd0;
        try {
            try {
                this.zza.zzt.zzaA().zzj().zza("onActivityCreated");
                Intent intent0 = activity0.getIntent();
                if(intent0 == null) {
                    zzgd0 = this.zza.zzt;
                }
                else {
                    zzpb.zzc();
                    Uri uri0 = null;
                    if(this.zza.zzt.zzf().zzs(null, zzeg.zzaF)) {
                        Uri uri1 = intent0.getData();
                        if(uri1 == null || !uri1.isHierarchical()) {
                            Bundle bundle1 = intent0.getExtras();
                            if(bundle1 != null) {
                                String s = bundle1.getString("com.android.vending.referral_url");
                                if(!TextUtils.isEmpty(s)) {
                                    uri0 = Uri.parse(s);
                                }
                            }
                            uri2 = uri0;
                        }
                        else {
                            uri2 = uri1;
                        }
                    }
                    else {
                        uri2 = intent0.getData();
                    }
                    if(uri2 != null && uri2.isHierarchical()) {
                        this.zza.zzt.zzv();
                        String s1 = intent0.getStringExtra("android.intent.extra.REFERRER_NAME");
                        String s2 = uri2.getQueryParameter("referrer");
                        this.zza.zzt.zzaB().zzp(new zzii(this, bundle0 == null, uri2, ("android-app://com.google.android.googlequicksearchbox/https/www.google.com".equals(s1) || "https://www.google.com".equals(s1) || "android-app://com.google.appcrawler".equals(s1) ? "gs" : "auto"), s2));
                    }
                    goto label_28;
                }
                goto label_33;
            }
            catch(RuntimeException runtimeException0) {
                this.zza.zzt.zzaA().zzd().zzb("Throwable caught in onActivityCreated", runtimeException0);
            }
        label_28:
            zzgd0 = this.zza.zzt;
        }
        catch(Throwable throwable0) {
            this.zza.zzt.zzs().zzr(activity0, bundle0);
            throw throwable0;
        }
    label_33:
        zzgd0.zzs().zzr(activity0, bundle0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity0) {
        this.zza.zzt.zzs().zzs(activity0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity0) {
        this.zza.zzt.zzs().zzt(activity0);
        zzkp zzkp0 = this.zza.zzt.zzu();
        long v = zzkp0.zzt.zzax().elapsedRealtime();
        zzkp0.zzt.zzaB().zzp(new zzki(zzkp0, v));
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity0) {
        zzkp zzkp0 = this.zza.zzt.zzu();
        long v = zzkp0.zzt.zzax().elapsedRealtime();
        zzkp0.zzt.zzaB().zzp(new zzkh(zzkp0, v));
        this.zza.zzt.zzs().zzu(activity0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
        this.zza.zzt.zzs().zzv(activity0, bundle0);
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity0) {
    }
}

