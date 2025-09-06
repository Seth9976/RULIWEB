package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.net.Uri;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

public abstract class zzib {
    final zzhy zza;
    final String zzb;
    public static final int zzc = 0;
    private static final Object zzd = null;
    @Nullable
    private static volatile zzhz zze = null;
    private static volatile boolean zzf = false;
    private static final AtomicReference zzg;
    private static final zzid zzh;
    private static final AtomicInteger zzi;
    private final Object zzj;
    private volatile int zzk;
    private volatile Object zzl;

    static {
        zzib.zzd = new Object();
        zzib.zzg = new AtomicReference();
        zzib.zzh = new zzid(zzht.zza);
        zzib.zzi = new AtomicInteger();
    }

    zzib(zzhy zzhy0, String s, Object object0, boolean z, zzia zzia0) {
        this.zzk = -1;
        if(zzhy0.zza == null) {
            throw new IllegalArgumentException("Must pass a valid SharedPreferences file name or ContentProvider URI");
        }
        this.zza = zzhy0;
        this.zzb = s;
        this.zzj = object0;
    }

    abstract Object zza(Object arg1);

    public final Object zzb() {
        Object object2;
        zzic zzic0;
        String s;
        int v = zzib.zzi.get();
        if(this.zzk < v) {
            synchronized(this) {
                if(this.zzk < v) {
                    zzhz zzhz0 = zzib.zze;
                    zzii zzii0 = zzii.zzc();
                    Object object0 = null;
                    if(zzhz0 == null) {
                        s = null;
                    }
                    else {
                        zzii0 = (zzii)zzhz0.zzb().zza();
                        s = zzii0.zzb() ? ((zzhh)zzii0.zza()).zza(this.zza.zza, null, this.zza.zzc, this.zzb) : null;
                    }
                    if(zzhz0 == null) {
                        throw new IllegalStateException("Must call PhenotypeFlag.init() first");
                    }
                    Uri uri0 = this.zza.zza;
                    if(uri0 == null) {
                        zzic0 = zzic.zza(zzhz0.zza(), null, () -> zzib.zzi.incrementAndGet());
                    }
                    else if(zzhp.zza(zzhz0.zza(), uri0)) {
                        zzic0 = zzhf.zza(zzhz0.zza().getContentResolver(), this.zza.zza, () -> zzib.zzi.incrementAndGet());
                    }
                    else {
                        zzic0 = null;
                    }
                    if(zzic0 == null) {
                        object2 = null;
                    }
                    else {
                        Object object1 = zzic0.zzb(this.zzb);
                        object2 = object1 == null ? null : this.zza(object1);
                    }
                    if(object2 == null) {
                        if(!this.zza.zzd) {
                            String s1 = zzhn.zza(zzhz0.zza()).zzc((this.zza.zzd ? null : this.zzb));
                            if(s1 != null) {
                                object0 = this.zza(s1);
                            }
                        }
                        object2 = object0 == null ? this.zzj : object0;
                    }
                    if(zzii0.zzb()) {
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
        if(zzib.zze == null && context0 != null) {
            Object object0 = zzib.zzd;
            synchronized(object0) {
                if(zzib.zze == null) {
                    synchronized(object0) {
                        zzhz zzhz0 = zzib.zze;
                        Context context1 = context0.getApplicationContext();
                        if(context1 != null) {
                            context0 = context1;
                        }
                        if(zzhz0 == null || zzhz0.zza() != context0) {
                            zzhf.zze();
                            zzic.zzc();
                            zzhn.zze();
                            zzib.zze = new zzhc(context0, zzir.zza(new zzhs(context0)));
                            zzib.zzi.incrementAndGet();
                        }
                    }
                }
            }
        }
    }
}

