package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Pair;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.measurement.zzcf;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public final class zzjz extends zzf {
    private final zzjy zza;
    private zzej zzb;
    private volatile Boolean zzc;
    private final zzan zzd;
    private final zzkq zze;
    private final List zzf;
    private final zzan zzg;

    protected zzjz(zzgd zzgd0) {
        super(zzgd0);
        this.zzf = new ArrayList();
        this.zze = new zzkq(zzgd0.zzax());
        this.zza = new zzjy(this);
        this.zzd = new zzjj(this, zzgd0);
        this.zzg = new zzjl(this, zzgd0);
    }

    protected final void zzA(zzau zzau0, String s) {
        Preconditions.checkNotNull(zzau0);
        this.zzg();
        this.zza();
        boolean z = this.zzt.zzi().zzo(zzau0);
        this.zzR(new zzjo(this, true, this.zzO(true), z, zzau0, s));
    }

    public final void zzB(zzcf zzcf0, zzau zzau0, String s) {
        this.zzg();
        this.zza();
        if(this.zzt.zzv().zzo(12451000) != 0) {
            this.zzt.zzaA().zzk().zza("Not bundling data. Service unavailable or out of date");
            this.zzt.zzv().zzT(zzcf0, new byte[0]);
            return;
        }
        this.zzR(new zzjk(this, zzau0, s, zzcf0));
    }

    protected final void zzC() {
        this.zzg();
        this.zza();
        zzq zzq0 = this.zzO(false);
        this.zzt.zzi().zzj();
        this.zzR(new zzjd(this, zzq0));
    }

    final void zzD(zzej zzej0, AbstractSafeParcelable abstractSafeParcelable0, zzq zzq0) {
        int v2;
        this.zzg();
        this.zza();
        int v = 0;
        for(int v1 = 100; v < 1001 && v1 == 100; v1 = v2) {
            ArrayList arrayList0 = new ArrayList();
            List list0 = this.zzt.zzi().zzi(100);
            if(list0 == null) {
                v2 = 0;
            }
            else {
                arrayList0.addAll(list0);
                v2 = list0.size();
            }
            if(abstractSafeParcelable0 != null && v2 < 100) {
                arrayList0.add(abstractSafeParcelable0);
            }
            int v3 = arrayList0.size();
            for(int v4 = 0; v4 < v3; ++v4) {
                AbstractSafeParcelable abstractSafeParcelable1 = (AbstractSafeParcelable)arrayList0.get(v4);
                if(abstractSafeParcelable1 instanceof zzau) {
                    try {
                        zzej0.zzk(((zzau)abstractSafeParcelable1), zzq0);
                    }
                    catch(RemoteException remoteException0) {
                        this.zzt.zzaA().zzd().zzb("Failed to send event to the service", remoteException0);
                    }
                }
                else if(abstractSafeParcelable1 instanceof zzlk) {
                    try {
                        zzej0.zzt(((zzlk)abstractSafeParcelable1), zzq0);
                    }
                    catch(RemoteException remoteException1) {
                        this.zzt.zzaA().zzd().zzb("Failed to send user property to the service", remoteException1);
                    }
                }
                else if(abstractSafeParcelable1 instanceof zzac) {
                    try {
                        zzej0.zzn(((zzac)abstractSafeParcelable1), zzq0);
                    }
                    catch(RemoteException remoteException2) {
                        this.zzt.zzaA().zzd().zzb("Failed to send conditional user property to the service", remoteException2);
                    }
                }
                else {
                    this.zzt.zzaA().zzd().zza("Discarding data. Unrecognized parcel type.");
                }
            }
            ++v;
        }
    }

    protected final void zzE(zzac zzac0) {
        Preconditions.checkNotNull(zzac0);
        this.zzg();
        this.zza();
        boolean z = this.zzt.zzi().zzn(zzac0);
        zzac zzac1 = new zzac(zzac0);
        this.zzR(new zzjp(this, true, this.zzO(true), z, zzac1, zzac0));
    }

    protected final void zzF(boolean z) {
        this.zzg();
        this.zza();
        if(z) {
            this.zzt.zzi().zzj();
        }
        if(this.zzM()) {
            this.zzR(new zzjn(this, this.zzO(false)));
        }
    }

    protected final void zzG(zzir zzir0) {
        this.zzg();
        this.zza();
        this.zzR(new zzjh(this, zzir0));
    }

    public final void zzH(Bundle bundle0) {
        this.zzg();
        this.zza();
        this.zzR(new zzji(this, this.zzO(false), bundle0));
    }

    protected final void zzI() {
        this.zzg();
        this.zza();
        this.zzR(new zzjm(this, this.zzO(true)));
    }

    protected final void zzJ(zzej zzej0) {
        this.zzg();
        Preconditions.checkNotNull(zzej0);
        this.zzb = zzej0;
        this.zzQ();
        this.zzP();
    }

    protected final void zzK(zzlk zzlk0) {
        this.zzg();
        this.zza();
        boolean z = this.zzt.zzi().zzp(zzlk0);
        this.zzR(new zzjc(this, this.zzO(true), z, zzlk0));
    }

    public final boolean zzL() {
        this.zzg();
        this.zza();
        return this.zzb != null;
    }

    final boolean zzM() {
        this.zzg();
        this.zza();
        return !this.zzN() || this.zzt.zzv().zzm() >= ((int)(((Integer)zzeg.zzah.zza(null))));
    }

    final boolean zzN() {
        this.zzg();
        this.zza();
        if(this.zzc == null) {
            this.zzg();
            this.zza();
            zzfi zzfi0 = this.zzt.zzm();
            zzfi0.zzg();
            boolean z = false;
            Boolean boolean0 = zzfi0.zza().contains("use_service") ? Boolean.valueOf(zzfi0.zza().getBoolean("use_service", false)) : null;
            boolean z1 = true;
            if(boolean0 == null || !boolean0.booleanValue()) {
                if(this.zzt.zzh().zzh() == 1) {
                    z = true;
                }
                else {
                    this.zzt.zzaA().zzj().zza("Checking service availability");
                    int v = this.zzt.zzv().zzo(12451000);
                    switch(v) {
                        case 0: {
                            this.zzt.zzaA().zzj().zza("Service available");
                            z = true;
                            break;
                        }
                        case 1: {
                            this.zzt.zzaA().zzj().zza("Service missing");
                            break;
                        }
                        case 2: {
                            this.zzt.zzaA().zzc().zza("Service container out of date");
                            if(this.zzt.zzv().zzm() >= 0x4423) {
                                if(boolean0 != null) {
                                    z1 = false;
                                }
                                z = z1;
                                z1 = false;
                            }
                            break;
                        }
                        case 3: {
                            this.zzt.zzaA().zzk().zza("Service disabled");
                            z1 = false;
                            break;
                        }
                        case 9: {
                            this.zzt.zzaA().zzk().zza("Service invalid");
                            z1 = false;
                            break;
                        }
                        case 18: {
                            this.zzt.zzaA().zzk().zza("Service updating");
                            z = true;
                            break;
                        }
                        default: {
                            this.zzt.zzaA().zzk().zzb("Unexpected service status", v);
                            z1 = false;
                        }
                    }
                }
                if(!z && this.zzt.zzf().zzx()) {
                    this.zzt.zzaA().zzd().zza("No way to upload. Consider using the full version of Analytics");
                }
                else if(z1) {
                    zzfi zzfi1 = this.zzt.zzm();
                    zzfi1.zzg();
                    SharedPreferences.Editor sharedPreferences$Editor0 = zzfi1.zza().edit();
                    sharedPreferences$Editor0.putBoolean("use_service", z);
                    sharedPreferences$Editor0.apply();
                }
                z1 = z;
            }
            this.zzc = Boolean.valueOf(z1);
        }
        return this.zzc.booleanValue();
    }

    private final zzq zzO(boolean z) {
        zzek zzek0 = this.zzt.zzh();
        String s = null;
        if(z) {
            zzet zzet0 = this.zzt.zzaA();
            if(zzet0.zzt.zzm().zzb != null) {
                Pair pair0 = zzet0.zzt.zzm().zzb.zza();
                if(pair0 != null && pair0 != zzfi.zza) {
                    s = pair0.second + ":" + ((String)pair0.first);
                }
            }
        }
        return zzek0.zzj(s);
    }

    private final void zzP() {
        this.zzg();
        this.zzt.zzaA().zzj().zzb("Processing queued up service tasks", this.zzf.size());
        for(Object object0: this.zzf) {
            Runnable runnable0 = (Runnable)object0;
            try {
                runnable0.run();
            }
            catch(RuntimeException runtimeException0) {
                this.zzt.zzaA().zzd().zzb("Task exception while flushing queue", runtimeException0);
            }
        }
        this.zzf.clear();
        this.zzg.zzb();
    }

    private final void zzQ() {
        this.zzg();
        this.zze.zzb();
        long v = (long)(((Long)zzeg.zzJ.zza(null)));
        this.zzd.zzd(v);
    }

    private final void zzR(Runnable runnable0) throws IllegalStateException {
        this.zzg();
        if(this.zzL()) {
            runnable0.run();
            return;
        }
        if(((long)this.zzf.size()) >= 1000L) {
            this.zzt.zzaA().zzd().zza("Discarding data. Max runnable queue size reached");
            return;
        }
        this.zzf.add(runnable0);
        this.zzg.zzd(60000L);
        this.zzr();
    }

    private final boolean zzS() {
        return true;
    }

    @Override  // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    static zzej zzh(zzjz zzjz0) {
        return zzjz0.zzb;
    }

    static zzjy zzi(zzjz zzjz0) {
        return zzjz0.zza;
    }

    final Boolean zzj() {
        return this.zzc;
    }

    static void zzl(zzjz zzjz0, zzej zzej0) {
        zzjz0.zzb = null;
    }

    static void zzm(zzjz zzjz0) {
        zzjz0.zzP();
    }

    static void zzo(zzjz zzjz0, ComponentName componentName0) {
        zzjz0.zzg();
        if(zzjz0.zzb != null) {
            zzjz0.zzb = null;
            zzjz0.zzt.zzaA().zzj().zzb("Disconnected from device MeasurementService", componentName0);
            zzjz0.zzg();
            zzjz0.zzr();
        }
    }

    static void zzp(zzjz zzjz0) {
        zzjz0.zzQ();
    }

    protected final void zzq() {
        this.zzg();
        this.zza();
        zzq zzq0 = this.zzO(true);
        this.zzt.zzi().zzk();
        this.zzR(new zzjg(this, zzq0));
    }

    final void zzr() {
        this.zzg();
        this.zza();
        if(!this.zzL()) {
            if(this.zzN()) {
                this.zza.zzc();
            }
            else if(!this.zzt.zzf().zzx()) {
                List list0 = this.zzt.zzaw().getPackageManager().queryIntentServices(new Intent().setClassName(this.zzt.zzaw(), "com.google.android.gms.measurement.AppMeasurementService"), 0x10000);
                if(list0 != null && !list0.isEmpty()) {
                    Intent intent0 = new Intent("com.google.android.gms.measurement.START");
                    intent0.setComponent(new ComponentName(this.zzt.zzaw(), "com.google.android.gms.measurement.AppMeasurementService"));
                    this.zza.zzb(intent0);
                    return;
                }
                this.zzt.zzaA().zzd().zza("Unable to use remote or local measurement implementation. Please register the AppMeasurementService service in the app manifest");
            }
        }
    }

    public final void zzs() {
        this.zzg();
        this.zza();
        this.zza.zzd();
        try {
            ConnectionTracker.getInstance().unbindService(this.zzt.zzaw(), this.zza);
        }
        catch(IllegalStateException | IllegalArgumentException unused_ex) {
        }
        this.zzb = null;
    }

    public final void zzt(zzcf zzcf0) {
        this.zzg();
        this.zza();
        this.zzR(new zzjf(this, this.zzO(false), zzcf0));
    }

    public final void zzu(AtomicReference atomicReference0) {
        this.zzg();
        this.zza();
        this.zzR(new zzje(this, atomicReference0, this.zzO(false)));
    }

    protected final void zzv(zzcf zzcf0, String s, String s1) {
        this.zzg();
        this.zza();
        this.zzR(new zzjr(this, s, s1, this.zzO(false), zzcf0));
    }

    protected final void zzw(AtomicReference atomicReference0, String s, String s1, String s2) {
        this.zzg();
        this.zza();
        this.zzR(new zzjq(this, atomicReference0, null, s1, s2, this.zzO(false)));
    }

    protected final void zzx(AtomicReference atomicReference0, boolean z) {
        this.zzg();
        this.zza();
        this.zzR(new zzjb(this, atomicReference0, this.zzO(false), z));
    }

    protected final void zzy(zzcf zzcf0, String s, String s1, boolean z) {
        this.zzg();
        this.zza();
        this.zzR(new zzja(this, s, s1, this.zzO(false), z, zzcf0));
    }

    protected final void zzz(AtomicReference atomicReference0, String s, String s1, String s2, boolean z) {
        this.zzg();
        this.zza();
        this.zzR(new zzjs(this, atomicReference0, null, s1, s2, this.zzO(false), z));
    }
}

