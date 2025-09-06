package com.google.android.gms.internal.auth;

import android.content.Context;
import android.net.Uri;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

public abstract class zzdc {
    private static final Object zza = null;
    final zzcz zzb;
    final String zzc;
    public static final int zzd = 0;
    @Nullable
    private static volatile zzda zze = null;
    private static volatile boolean zzf = false;
    private static final AtomicReference zzg;
    private static final zzde zzh;
    private static final AtomicInteger zzi;
    private final Object zzj;
    private volatile int zzk;
    private volatile Object zzl;

    static {
        zzdc.zza = new Object();
        zzdc.zzg = new AtomicReference();
        zzdc.zzh = new zzde(zzcu.zza);
        zzdc.zzi = new AtomicInteger();
    }

    zzdc(zzcz zzcz0, String s, Object object0, boolean z, zzdb zzdb0) {
        this.zzk = -1;
        if(zzcz0.zza == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zzb = zzcz0;
        this.zzc = s;
        this.zzj = object0;
    }

    abstract Object zza(Object arg1);

    public final Object zzb() {
        Object object2;
        zzdd zzdd0;
        String s;
        int v = zzdc.zzi.get();
        if(this.zzk < v) {
            synchronized(this) {
                if(this.zzk < v) {
                    zzda zzda0 = zzdc.zze;
                    zzdh zzdh0 = zzdh.zzc();
                    Object object0 = null;
                    if(zzda0 == null) {
                        s = null;
                    }
                    else {
                        zzdh0 = (zzdh)zzda0.zzb().zza();
                        s = zzdh0.zzb() ? ((zzci)zzdh0.zza()).zza(this.zzb.zza, null, this.zzb.zzc, this.zzc) : null;
                    }
                    if(zzda0 == null) {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                    Uri uri0 = this.zzb.zza;
                    if(uri0 == null) {
                        zzdd0 = zzdd.zza(zzda0.zza(), null, () -> zzdc.zzi.incrementAndGet());
                    }
                    else if(zzcq.zza(zzda0.zza(), uri0)) {
                        zzdd0 = zzcg.zza(zzda0.zza().getContentResolver(), this.zzb.zza, () -> zzdc.zzi.incrementAndGet());
                    }
                    else {
                        zzdd0 = null;
                    }
                    if(zzdd0 == null) {
                        object2 = null;
                    }
                    else {
                        Object object1 = zzdd0.zzb(this.zzc);
                        object2 = object1 == null ? null : this.zza(object1);
                    }
                    if(object2 == null) {
                        if(!this.zzb.zzd) {
                            String s1 = zzco.zza(zzda0.zza()).zzc((this.zzb.zzd ? null : this.zzc));
                            if(s1 != null) {
                                object0 = this.zza(s1);
                            }
                        }
                        object2 = object0 == null ? this.zzj : object0;
                    }
                    if(zzdh0.zzb()) {
                        object2 = s == null ? this.zzj : this.zza(s);
                    }
                    this.zzl = object2;
                    this.zzk = v;
                }
                return this.zzl;
            }
        }
        return this.zzl;
    }

    // 检测为 Lambda 实现
    public static void zzc() [...]

    public static void zzd(Context context0) {
        if(zzdc.zze == null && context0 != null) {
            Object object0 = zzdc.zza;
            synchronized(object0) {
                if(zzdc.zze == null) {
                    synchronized(object0) {
                        zzda zzda0 = zzdc.zze;
                        Context context1 = context0.getApplicationContext();
                        if(context1 != null) {
                            context0 = context1;
                        }
                        if(zzda0 == null || zzda0.zza() != context0) {
                            zzcg.zzd();
                            zzdd.zzc();
                            zzco.zze();
                            zzdc.zze = new zzcd(context0, zzdo.zza(new zzct(context0)));
                            zzdc.zzi.incrementAndGet();
                        }
                    }
                }
            }
        }
    }
}

