package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteException;
import android.os.Binder;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.GoogleSignatureVerifier;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.UidVerifier;
import com.google.android.gms.internal.measurement.zzaa;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzd;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public final class zzgv extends zzei {
    private final zzlh zza;
    private Boolean zzb;
    private String zzc;

    public zzgv(zzlh zzlh0, String s) {
        Preconditions.checkNotNull(zzlh0);
        this.zza = zzlh0;
        this.zzc = null;
    }

    private final void zzA(zzau zzau0, zzq zzq0) {
        this.zza.zzA();
        this.zza.zzE(zzau0, zzq0);
    }

    final zzau zzb(zzau zzau0, zzq zzq0) {
        if("_cmp".equals(zzau0.zza) && (zzau0.zzb != null && zzau0.zzb.zza() != 0)) {
            String s = zzau0.zzb.zzg("_cis");
            if("referrer broadcast".equals(s) || "referrer API".equals(s)) {
                this.zza.zzaA().zzi().zzb("Event has been filtered ", zzau0.toString());
                return new zzau("_cmpx", zzau0.zzb, zzau0.zzc, zzau0.zzd);
            }
        }
        return zzau0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final String zzd(zzq zzq0) {
        this.zzy(zzq0, false);
        return this.zza.zzx(zzq0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zze(zzq zzq0, boolean z) {
        this.zzy(zzq0, false);
        Preconditions.checkNotNull(zzq0.zza);
        Future future0 = this.zza.zzaB().zzh(new zzgs(this, zzq0.zza));
        try {
            List list0 = (List)future0.get();
            List list1 = new ArrayList(list0.size());
            for(Object object0: list0) {
                zzlm zzlm0 = (zzlm)object0;
                if(z || !zzlp.zzaj(zzlm0.zzc)) {
                    list1.add(new zzlk(zzlm0));
                }
            }
            return list1;
        }
        catch(InterruptedException | ExecutionException interruptedException0) {
            this.zza.zzaA().zzd().zzc("Failed to get user properties. appId", zzet.zzn(zzq0.zza), interruptedException0);
            return null;
        }
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zzf(String s, String s1, zzq zzq0) {
        this.zzy(zzq0, false);
        Preconditions.checkNotNull(zzq0.zza);
        Future future0 = this.zza.zzaB().zzh(new zzgj(this, zzq0.zza, s, s1));
        try {
            return (List)future0.get();
        }
        catch(InterruptedException | ExecutionException interruptedException0) {
            this.zza.zzaA().zzd().zzb("Failed to get conditional user properties", interruptedException0);
            return Collections.EMPTY_LIST;
        }
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zzg(String s, String s1, String s2) {
        this.zzz(s, true);
        Future future0 = this.zza.zzaB().zzh(new zzgk(this, s, s1, s2));
        try {
            return (List)future0.get();
        }
        catch(InterruptedException | ExecutionException interruptedException0) {
            this.zza.zzaA().zzd().zzb("Failed to get conditional user properties as", interruptedException0);
            return Collections.EMPTY_LIST;
        }
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zzh(String s, String s1, boolean z, zzq zzq0) {
        this.zzy(zzq0, false);
        Preconditions.checkNotNull(zzq0.zza);
        Future future0 = this.zza.zzaB().zzh(new zzgh(this, zzq0.zza, s, s1));
        try {
            List list0 = (List)future0.get();
            List list1 = new ArrayList(list0.size());
            for(Object object0: list0) {
                zzlm zzlm0 = (zzlm)object0;
                if(z || !zzlp.zzaj(zzlm0.zzc)) {
                    list1.add(new zzlk(zzlm0));
                }
            }
            return list1;
        }
        catch(InterruptedException | ExecutionException interruptedException0) {
            this.zza.zzaA().zzd().zzc("Failed to query user properties. appId", zzet.zzn(zzq0.zza), interruptedException0);
            return Collections.EMPTY_LIST;
        }
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final List zzi(String s, String s1, String s2, boolean z) {
        this.zzz(s, true);
        Future future0 = this.zza.zzaB().zzh(new zzgi(this, s, s1, s2));
        try {
            List list0 = (List)future0.get();
            List list1 = new ArrayList(list0.size());
            for(Object object0: list0) {
                zzlm zzlm0 = (zzlm)object0;
                if(z || !zzlp.zzaj(zzlm0.zzc)) {
                    list1.add(new zzlk(zzlm0));
                }
            }
            return list1;
        }
        catch(InterruptedException | ExecutionException interruptedException0) {
            this.zza.zzaA().zzd().zzc("Failed to get user properties as. appId", zzet.zzn(s), interruptedException0);
            return Collections.EMPTY_LIST;
        }
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzj(zzq zzq0) {
        this.zzy(zzq0, false);
        this.zzx(new zzgt(this, zzq0));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzk(zzau zzau0, zzq zzq0) {
        Preconditions.checkNotNull(zzau0);
        this.zzy(zzq0, false);
        this.zzx(new zzgo(this, zzau0, zzq0));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzl(zzau zzau0, String s, String s1) {
        Preconditions.checkNotNull(zzau0);
        Preconditions.checkNotEmpty(s);
        this.zzz(s, true);
        this.zzx(new zzgp(this, zzau0, s));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzm(zzq zzq0) {
        Preconditions.checkNotEmpty(zzq0.zza);
        this.zzz(zzq0.zza, false);
        this.zzx(new zzgl(this, zzq0));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzn(zzac zzac0, zzq zzq0) {
        Preconditions.checkNotNull(zzac0);
        Preconditions.checkNotNull(zzac0.zzc);
        this.zzy(zzq0, false);
        zzac zzac1 = new zzac(zzac0);
        zzac1.zza = zzq0.zza;
        this.zzx(new zzgf(this, zzac1, zzq0));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzo(zzac zzac0) {
        Preconditions.checkNotNull(zzac0);
        Preconditions.checkNotNull(zzac0.zzc);
        Preconditions.checkNotEmpty(zzac0.zza);
        this.zzz(zzac0.zza, true);
        this.zzx(new zzgg(this, new zzac(zzac0)));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzp(zzq zzq0) {
        Preconditions.checkNotEmpty(zzq0.zza);
        Preconditions.checkNotNull(zzq0.zzv);
        zzgn zzgn0 = new zzgn(this, zzq0);
        Preconditions.checkNotNull(zzgn0);
        if(this.zza.zzaB().zzs()) {
            zzgn0.run();
            return;
        }
        this.zza.zzaB().zzq(zzgn0);
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzq(long v, String s, String s1, String s2) {
        this.zzx(new zzgu(this, s1, s2, s, v));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzr(Bundle bundle0, zzq zzq0) {
        this.zzy(zzq0, false);
        Preconditions.checkNotNull(zzq0.zza);
        this.zzx(() -> {
            zzak zzak0 = this.zza.zzh();
            zzak0.zzg();
            zzak0.zzW();
            zzap zzap0 = new zzap(zzak0.zzt, "", zzq0.zza, "dep", 0L, 0L, bundle0);
            byte[] arr_b = zzak0.zzf.zzu().zzl(zzap0).zzbx();
            zzak0.zzt.zzaA().zzj().zzc("Saving default event parameters, appId, data size", zzak0.zzt.zzj().zzd(zzq0.zza), ((int)arr_b.length));
            ContentValues contentValues0 = new ContentValues();
            contentValues0.put("app_id", zzq0.zza);
            contentValues0.put("parameters", arr_b);
            try {
                if(zzak0.zzh().insertWithOnConflict("default_event_params", null, contentValues0, 5) == -1L) {
                    zzak0.zzt.zzaA().zzd().zzb("Failed to insert default event parameters (got -1). appId", zzet.zzn(zzq0.zza));
                }
            }
            catch(SQLiteException sQLiteException0) {
                zzak0.zzt.zzaA().zzd().zzc("Error storing default event parameters. appId", zzet.zzn(zzq0.zza), sQLiteException0);
            }
        });
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzs(zzq zzq0) {
        this.zzy(zzq0, false);
        this.zzx(new zzgm(this, zzq0));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final void zzt(zzlk zzlk0, zzq zzq0) {
        Preconditions.checkNotNull(zzlk0);
        this.zzy(zzq0, false);
        this.zzx(new zzgr(this, zzlk0, zzq0));
    }

    @Override  // com.google.android.gms.measurement.internal.zzej
    public final byte[] zzu(zzau zzau0, String s) {
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotNull(zzau0);
        this.zzz(s, true);
        this.zza.zzaA().zzc().zzb("Log and bundle. event", this.zza.zzi().zzd(zzau0.zza));
        long v = this.zza.zzax().nanoTime();
        Future future0 = this.zza.zzaB().zzi(new zzgq(this, zzau0, s));
        try {
            byte[] arr_b = (byte[])future0.get();
            if(arr_b == null) {
                this.zza.zzaA().zzd().zzb("Log and bundle returned null. appId", zzet.zzn(s));
                arr_b = new byte[0];
            }
            long v1 = this.zza.zzax().nanoTime();
            this.zza.zzaA().zzc().zzd("Log and bundle processed. event, size, time_ms", this.zza.zzi().zzd(zzau0.zza), ((int)arr_b.length), ((long)(v1 / 1000000L - v / 1000000L)));
            return arr_b;
        }
        catch(InterruptedException | ExecutionException interruptedException0) {
            this.zza.zzaA().zzd().zzd("Failed to log and bundle. appId, event, error", zzet.zzn(s), this.zza.zzi().zzd(zzau0.zza), interruptedException0);
            return null;
        }
    }

    final void zzv(zzau zzau0, zzq zzq0) {
        if(!this.zza.zzm().zzo(zzq0.zza)) {
            this.zzA(zzau0, zzq0);
            return;
        }
        this.zza.zzaA().zzj().zzb("EES config found for", zzq0.zza);
        zzfu zzfu0 = this.zza.zzm();
        zzc zzc0 = TextUtils.isEmpty(zzq0.zza) ? null : ((zzc)zzfu0.zzd.get(zzq0.zza));
        if(zzc0 != null) {
            try {
                Map map0 = this.zza.zzu().zzt(zzau0.zzb.zzc(), true);
                String s = zzhc.zza(zzau0.zza);
                if(s == null) {
                    s = zzau0.zza;
                }
                if(zzc0.zze(new zzaa(s, zzau0.zzd, map0))) {
                    goto label_15;
                }
            }
            catch(zzd unused_ex) {
                this.zza.zzaA().zzd().zzc("EES error. appId, eventName", zzq0.zzb, zzau0.zza);
            }
            this.zza.zzaA().zzj().zzb("EES was not applied to event", zzau0.zza);
            this.zzA(zzau0, zzq0);
            return;
        label_15:
            if(zzc0.zzg()) {
                this.zza.zzaA().zzj().zzb("EES edited event", zzau0.zza);
                this.zzA(this.zza.zzu().zzj(zzc0.zza().zzb()), zzq0);
            }
            else {
                this.zzA(zzau0, zzq0);
            }
            if(zzc0.zzf()) {
                for(Object object0: zzc0.zza().zzc()) {
                    this.zza.zzaA().zzj().zzb("EES logging created event", ((zzaa)object0).zzd());
                    this.zzA(this.zza.zzu().zzj(((zzaa)object0)), zzq0);
                }
            }
            return;
        }
        this.zza.zzaA().zzj().zzb("EES not loaded for", zzq0.zza);
        this.zzA(zzau0, zzq0);
    }

    // 检测为 Lambda 实现
    final void zzw(String s, Bundle bundle0) [...]

    final void zzx(Runnable runnable0) {
        Preconditions.checkNotNull(runnable0);
        if(this.zza.zzaB().zzs()) {
            runnable0.run();
            return;
        }
        this.zza.zzaB().zzp(runnable0);
    }

    private final void zzy(zzq zzq0, boolean z) {
        Preconditions.checkNotNull(zzq0);
        Preconditions.checkNotEmpty(zzq0.zza);
        this.zzz(zzq0.zza, false);
        this.zza.zzv().zzY(zzq0.zzb, zzq0.zzq);
    }

    private final void zzz(String s, boolean z) {
        if(!TextUtils.isEmpty(s)) {
            try {
                if(z) {
                    if(this.zzb == null) {
                        this.zzb = Boolean.valueOf("com.google.android.gms".equals(this.zzc) || UidVerifier.isGooglePlayServicesUid(this.zza.zzaw(), Binder.getCallingUid()) || GoogleSignatureVerifier.getInstance(this.zza.zzaw()).isUidGoogleSigned(Binder.getCallingUid()));
                    }
                    if(!this.zzb.booleanValue()) {
                        goto label_5;
                    }
                    return;
                }
            label_5:
                if(this.zzc == null && GooglePlayServicesUtilLight.uidHasPackageName(this.zza.zzaw(), Binder.getCallingUid(), s)) {
                    this.zzc = s;
                }
                if(!s.equals(this.zzc)) {
                    throw new SecurityException(String.format("Unknown calling package name \'%s\'.", s));
                }
                return;
            }
            catch(SecurityException securityException0) {
                this.zza.zzaA().zzd().zzb("Measurement Service called with invalid calling package. appId", zzet.zzn(s));
                throw securityException0;
            }
        }
        this.zza.zzaA().zzd().zza("Measurement Service called without app package");
        throw new SecurityException("Measurement Service called without app package");
    }
}

