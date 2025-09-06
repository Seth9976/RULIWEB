package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class zziz extends zzf {
    protected zzir zza;
    private volatile zzir zzb;
    private volatile zzir zzc;
    private final Map zzd;
    private Activity zze;
    private volatile boolean zzf;
    private volatile zzir zzg;
    private zzir zzh;
    private boolean zzi;
    private final Object zzj;

    public zziz(zzgd zzgd0) {
        super(zzgd0);
        this.zzj = new Object();
        this.zzd = new ConcurrentHashMap();
    }

    private final void zzA(zzir zzir0, zzir zzir1, long v, boolean z, Bundle bundle0) {
        long v4;
        this.zzg();
        boolean z1 = false;
        boolean z2 = zzir1 == null || zzir1.zzc != zzir0.zzc || !zzis.zza(zzir1.zzb, zzir0.zzb) || !zzis.zza(zzir1.zza, zzir0.zza);
        if(z && this.zza != null) {
            z1 = true;
        }
        if(z2) {
            Bundle bundle1 = bundle0 == null ? new Bundle() : new Bundle(bundle0);
            zzlp.zzK(zzir0, bundle1, true);
            if(zzir1 != null) {
                String s = zzir1.zza;
                if(s != null) {
                    bundle1.putString("_pn", s);
                }
                String s1 = zzir1.zzb;
                if(s1 != null) {
                    bundle1.putString("_pc", s1);
                }
                bundle1.putLong("_pi", zzir1.zzc);
            }
            if(z1) {
                zzkp zzkp0 = this.zzt.zzu();
                long v1 = v - zzkp0.zzb.zzb;
                zzkp0.zzb.zzb = v;
                if(v1 > 0L) {
                    this.zzt.zzv().zzI(bundle1, v1);
                }
            }
            if(!this.zzt.zzf().zzu()) {
                bundle1.putLong("_mst", 1L);
            }
            String s2 = zzir0.zze ? "app" : "auto";
            long v2 = this.zzt.zzax().currentTimeMillis();
            if(zzir0.zze) {
                long v3 = zzir0.zzf;
                v4 = v3 == 0L ? v2 : v3;
            }
            else {
                v4 = v2;
            }
            this.zzt.zzq().zzH(s2, "_vs", v4, bundle1);
        }
        if(z1) {
            this.zzB(this.zza, true, v);
        }
        this.zza = zzir0;
        if(zzir0.zze) {
            this.zzh = zzir0;
        }
        this.zzt.zzt().zzG(zzir0);
    }

    private final void zzB(zzir zzir0, boolean z, long v) {
        this.zzt.zzd().zzf(this.zzt.zzax().elapsedRealtime());
        boolean z1 = zzir0 != null && zzir0.zzd;
        if(this.zzt.zzu().zzb.zzd(z1, z, v) && zzir0 != null) {
            zzir0.zzd = false;
        }
    }

    @Override  // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    static zzir zzh(zziz zziz0) {
        return zziz0.zzh;
    }

    public final zzir zzi() {
        return this.zzb;
    }

    public final zzir zzj(boolean z) {
        this.zza();
        this.zzg();
        if(!z) {
            return this.zza;
        }
        return this.zza == null ? this.zzh : this.zza;
    }

    final String zzl(Class class0, String s) {
        String s1 = class0.getCanonicalName();
        if(s1 == null) {
            return "Activity";
        }
        String[] arr_s = s1.split("\\.");
        String s2 = arr_s.length <= 0 ? "" : arr_s[arr_s.length - 1];
        return s2.length() <= 100 ? s2 : s2.substring(0, 100);
    }

    static void zzm(zziz zziz0, zzir zzir0) {
        zziz0.zzh = null;
    }

    static void zzo(zziz zziz0, zzir zzir0, zzir zzir1, long v, boolean z, Bundle bundle0) {
        zziz0.zzA(zzir0, zzir1, v, z, null);
    }

    static void zzp(zziz zziz0, Bundle bundle0, zzir zzir0, zzir zzir1, long v) {
        bundle0.remove("screen_name");
        bundle0.remove("screen_class");
        zziz0.zzA(zzir0, zzir1, v, true, zziz0.zzt.zzv().zzu(null, "screen_view", bundle0, null, false));
    }

    static void zzq(zziz zziz0, zzir zzir0, boolean z, long v) {
        zziz0.zzB(zzir0, false, v);
    }

    public final void zzr(Activity activity0, Bundle bundle0) {
        if(this.zzt.zzf().zzu() && bundle0 != null) {
            Bundle bundle1 = bundle0.getBundle("com.google.app_measurement.screen_service");
            if(bundle1 != null) {
                zzir zzir0 = new zzir(bundle1.getString("name"), bundle1.getString("referrer_name"), bundle1.getLong("id"));
                this.zzd.put(activity0, zzir0);
            }
        }
    }

    public final void zzs(Activity activity0) {
        synchronized(this.zzj) {
            if(activity0 == this.zze) {
                this.zze = null;
            }
        }
        if(!this.zzt.zzf().zzu()) {
            return;
        }
        this.zzd.remove(activity0);
    }

    public final void zzt(Activity activity0) {
        synchronized(this.zzj) {
            this.zzi = false;
            this.zzf = true;
        }
        long v = this.zzt.zzax().elapsedRealtime();
        if(!this.zzt.zzf().zzu()) {
            this.zzb = null;
            this.zzt.zzaB().zzp(new zziw(this, v));
            return;
        }
        zzir zzir0 = this.zzy(activity0);
        this.zzc = this.zzb;
        this.zzb = null;
        this.zzt.zzaB().zzp(new zzix(this, zzir0, v));
    }

    public final void zzu(Activity activity0) {
        synchronized(this.zzj) {
            this.zzi = true;
            if(activity0 != this.zze) {
                synchronized(this.zzj) {
                    this.zze = activity0;
                    this.zzf = false;
                }
                if(this.zzt.zzf().zzu()) {
                    this.zzg = null;
                    this.zzt.zzaB().zzp(new zziy(this));
                }
            }
        }
        if(!this.zzt.zzf().zzu()) {
            this.zzb = this.zzg;
            this.zzt.zzaB().zzp(new zziv(this));
            return;
        }
        this.zzz(activity0, this.zzy(activity0), false);
        zzd zzd0 = this.zzt.zzd();
        long v1 = zzd0.zzt.zzax().elapsedRealtime();
        zzd0.zzt.zzaB().zzp(new zzc(zzd0, v1));
    }

    public final void zzv(Activity activity0, Bundle bundle0) {
        if(this.zzt.zzf().zzu() && bundle0 != null) {
            zzir zzir0 = (zzir)this.zzd.get(activity0);
            if(zzir0 != null) {
                Bundle bundle1 = new Bundle();
                bundle1.putLong("id", zzir0.zzc);
                bundle1.putString("name", zzir0.zza);
                bundle1.putString("referrer_name", zzir0.zzb);
                bundle0.putBundle("com.google.app_measurement.screen_service", bundle1);
            }
        }
    }

    @Deprecated
    public final void zzw(Activity activity0, String s, String s1) {
        if(!this.zzt.zzf().zzu()) {
            this.zzt.zzaA().zzl().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
            return;
        }
        zzir zzir0 = this.zzb;
        if(zzir0 == null) {
            this.zzt.zzaA().zzl().zza("setCurrentScreen cannot be called while no activity active");
            return;
        }
        if(this.zzd.get(activity0) == null) {
            this.zzt.zzaA().zzl().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }
        if(s1 == null) {
            s1 = this.zzl(activity0.getClass(), "Activity");
        }
        if(zzis.zza(zzir0.zzb, s1) && zzis.zza(zzir0.zza, s)) {
            this.zzt.zzaA().zzl().zza("setCurrentScreen cannot be called with the same class and name");
            return;
        }
        if(s != null && (s.length() <= 0 || s.length() > 100)) {
            this.zzt.zzaA().zzl().zzb("Invalid screen name length in setCurrentScreen. Length", s.length());
            return;
        }
        if(s1 != null && (s1.length() <= 0 || s1.length() > 100)) {
            this.zzt.zzaA().zzl().zzb("Invalid class name length in setCurrentScreen. Length", s1.length());
            return;
        }
        this.zzt.zzaA().zzj().zzc("Setting current screen to name, class", (s == null ? "null" : s), s1);
        zzir zzir1 = new zzir(s, s1, this.zzt.zzv().zzq());
        this.zzd.put(activity0, zzir1);
        this.zzz(activity0, zzir1, true);
    }

    public final void zzx(Bundle bundle0, long v) {
        String s1;
        String s;
        synchronized(this.zzj) {
            if(!this.zzi) {
                this.zzt.zzaA().zzl().zza("Cannot log screen view event when the app is in the background.");
                return;
            }
            s = bundle0.getString("screen_name");
            if(s != null && (s.length() <= 0 || s.length() > 100)) {
                this.zzt.zzaA().zzl().zzb("Invalid screen name length for screen view. Length", s.length());
                return;
            }
            s1 = bundle0.getString("screen_class");
            if(s1 != null && (s1.length() <= 0 || s1.length() > 100)) {
                this.zzt.zzaA().zzl().zzb("Invalid screen class length for screen view. Length", s1.length());
                return;
            }
            if(s1 == null) {
                s1 = this.zze == null ? "Activity" : this.zzl(this.zze.getClass(), "Activity");
            }
            zzir zzir0 = this.zzb;
            if(this.zzf && zzir0 != null) {
                this.zzf = false;
                if(zzis.zza(zzir0.zzb, s1) && zzis.zza(zzir0.zza, s)) {
                    this.zzt.zzaA().zzl().zza("Ignoring call to log screen view event with duplicate parameters.");
                    return;
                }
            }
        }
        this.zzt.zzaA().zzj().zzc("Logging screen view with name, class", (s == null ? "null" : s), (s1 == null ? "null" : s1));
        zzir zzir1 = this.zzb == null ? this.zzc : this.zzb;
        zzir zzir2 = new zzir(s, s1, this.zzt.zzv().zzq(), true, v);
        this.zzb = zzir2;
        this.zzc = zzir1;
        this.zzg = zzir2;
        long v2 = this.zzt.zzax().elapsedRealtime();
        this.zzt.zzaB().zzp(new zzit(this, bundle0, zzir2, zzir1, v2));
    }

    private final zzir zzy(Activity activity0) {
        Preconditions.checkNotNull(activity0);
        zzir zzir0 = (zzir)this.zzd.get(activity0);
        if(zzir0 == null) {
            zzir zzir1 = new zzir(null, this.zzl(activity0.getClass(), "Activity"), this.zzt.zzv().zzq());
            this.zzd.put(activity0, zzir1);
            zzir0 = zzir1;
        }
        return this.zzg == null ? zzir0 : this.zzg;
    }

    private final void zzz(Activity activity0, zzir zzir0, boolean z) {
        zzir zzir2;
        zzir zzir1 = this.zzb == null ? this.zzc : this.zzb;
        if(zzir0.zzb == null) {
            String s = activity0 == null ? null : this.zzl(activity0.getClass(), "Activity");
            zzir2 = new zzir(zzir0.zza, s, zzir0.zzc, zzir0.zze, zzir0.zzf);
        }
        else {
            zzir2 = zzir0;
        }
        this.zzc = this.zzb;
        this.zzb = zzir2;
        long v = this.zzt.zzax().elapsedRealtime();
        this.zzt.zzaB().zzp(new zziu(this, zzir2, zzir1, v, z));
    }
}

