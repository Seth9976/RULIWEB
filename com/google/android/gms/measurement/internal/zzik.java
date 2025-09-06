package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.util.Strings;
import com.google.android.gms.internal.measurement.zzos;
import com.google.android.gms.internal.measurement.zzph;
import com.google.android.gms.internal.measurement.zzqu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zzik extends zzf {
    protected zzij zza;
    final zzs zzb;
    protected boolean zzc;
    private zzhf zzd;
    private final Set zze;
    private boolean zzf;
    private final AtomicReference zzg;
    private final Object zzh;
    private zzhb zzi;
    private final AtomicLong zzj;
    private long zzk;
    private final zzlo zzl;

    protected zzik(zzgd zzgd0) {
        super(zzgd0);
        this.zze = new CopyOnWriteArraySet();
        this.zzh = new Object();
        this.zzc = true;
        this.zzl = new zzhz(this);
        this.zzg = new AtomicReference();
        this.zzi = zzhb.zza;
        this.zzk = -1L;
        this.zzj = new AtomicLong(0L);
        this.zzb = new zzs(zzgd0);
    }

    public final void zzA(String s, String s1, Bundle bundle0) {
        long v = this.zzt.zzax().currentTimeMillis();
        Preconditions.checkNotEmpty(s);
        Bundle bundle1 = new Bundle();
        bundle1.putString("name", s);
        bundle1.putLong("creation_timestamp", v);
        if(s1 != null) {
            bundle1.putString("expired_event_name", s1);
            bundle1.putBundle("expired_event_params", bundle0);
        }
        this.zzt.zzaB().zzp(new zzhu(this, bundle1));
    }

    public final void zzB() {
        if(this.zzt.zzaw().getApplicationContext() instanceof Application && this.zza != null) {
            ((Application)this.zzt.zzaw().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.zza);
        }
    }

    final void zzC(Bundle bundle0) {
        if(bundle0 == null) {
            zzfi zzfi0 = this.zzt.zzm();
            Bundle bundle1 = new Bundle();
            zzfi0.zzs.zzb(bundle1);
            return;
        }
        Bundle bundle2 = this.zzt.zzm().zzs.zza();
        for(Object object0: bundle0.keySet()) {
            String s = (String)object0;
            Object object1 = bundle0.get(s);
            if(object1 != null && !(object1 instanceof String) && !(object1 instanceof Long) && !(object1 instanceof Double)) {
                if(this.zzt.zzv().zzag(object1)) {
                    this.zzt.zzv().zzO(this.zzl, null, 27, null, null, 0);
                }
                this.zzt.zzaA().zzl().zzc("Invalid default event parameter type. Name, value", s, object1);
            }
            else if(zzlp.zzaj(s)) {
                this.zzt.zzaA().zzl().zzb("Invalid default event parameter name. Name", s);
            }
            else if(object1 == null) {
                bundle2.remove(s);
            }
            else if(this.zzt.zzv().zzab("param", s, 100, object1)) {
                this.zzt.zzv().zzP(bundle2, s, object1);
            }
        }
        this.zzt.zzv();
        int v = this.zzt.zzf().zzc();
        if(bundle2.size() > v) {
            int v1 = 0;
            for(Object object2: new TreeSet(bundle2.keySet())) {
                String s1 = (String)object2;
                ++v1;
                if(v1 > v) {
                    bundle2.remove(s1);
                }
            }
            this.zzt.zzv().zzO(this.zzl, null, 26, null, null, 0);
            this.zzt.zzaA().zzl().zza("Too many default event parameters set. Discarding beyond event parameter limit");
        }
        this.zzt.zzm().zzs.zzb(bundle2);
        this.zzt.zzt().zzH(bundle2);
    }

    public final void zzD(String s, String s1, Bundle bundle0) {
        this.zzE(s, s1, bundle0, true, true, this.zzt.zzax().currentTimeMillis());
    }

    public final void zzE(String s, String s1, Bundle bundle0, boolean z, boolean z1, long v) {
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        switch(s1) {
            case 0: {
            label_4:
                boolean z2 = !z1 || this.zzd == null || zzlp.zzaj(s1);
                if(s == null) {
                    s = "app";
                }
                this.zzM(s, s1, v, bundle0, z1, z2, z, null);
                return;
            }
            case 0xD8E2: {
                break;
            }
            default: {
                if(!s1.equals("screen_view")) {
                    goto label_4;
                }
            }
        }
        this.zzt.zzs().zzx(bundle0, v);
    }

    public final void zzF(String s, String s1, Bundle bundle0, String s2) {
        zzgd.zzO();
        this.zzM("auto", s1, this.zzt.zzax().currentTimeMillis(), bundle0, false, true, true, s2);
    }

    final void zzG(String s, String s1, Bundle bundle0) {
        this.zzg();
        this.zzH(s, s1, this.zzt.zzax().currentTimeMillis(), bundle0);
    }

    final void zzH(String s, String s1, long v, Bundle bundle0) {
        this.zzg();
        this.zzI(s, s1, v, bundle0, true, this.zzd == null || zzlp.zzaj(s1), true, null);
    }

    protected final void zzI(String s, String s1, long v, Bundle bundle0, boolean z, boolean z1, boolean z2, String s2) {
        Bundle[] arr_bundle;
        ArrayList arrayList1;
        boolean z5;
        long v3;
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotNull(bundle0);
        this.zzg();
        this.zza();
        if(this.zzt.zzJ()) {
            int v1 = 0;
            List list0 = this.zzt.zzh().zzn();
            if(list0 != null && !list0.contains(s1)) {
                this.zzt.zzaA().zzc().zzc("Dropping non-safelisted event. event name, origin", s1, s);
                return;
            }
            if(!this.zzf) {
                try {
                    this.zzf = true;
                    Class class0 = this.zzt.zzN() ? Class.forName("com.google.android.gms.tagmanager.TagManagerService") : Class.forName("com.google.android.gms.tagmanager.TagManagerService", true, this.zzt.zzaw().getClassLoader());
                    try {
                        class0.getDeclaredMethod("initialize", Context.class).invoke(null, this.zzt.zzaw());
                    }
                    catch(Exception exception0) {
                        this.zzt.zzaA().zzk().zzb("Failed to invoke Tag Manager\'s initialize() method", exception0);
                    }
                }
                catch(ClassNotFoundException unused_ex) {
                    this.zzt.zzaA().zzi().zza("Tag Manager is not found and thus will not be used");
                }
            }
            if("_cmp".equals(s1) && bundle0.containsKey("gclid")) {
                this.zzY("auto", "_lgclid", bundle0.getString("gclid"), this.zzt.zzax().currentTimeMillis());
            }
            if(z && zzlp.zzan(s1)) {
                this.zzt.zzv().zzL(bundle0, this.zzt.zzm().zzs.zza());
            }
            if(!z2 && !"_iap".equals(s1)) {
                zzlp zzlp0 = this.zzt.zzv();
                int v2 = 2;
                if(zzlp0.zzad("event", s1)) {
                    if(!zzlp0.zzaa("event", zzhc.zza, zzhc.zzb, s1)) {
                        v2 = 13;
                    }
                    else if(zzlp0.zzZ("event", 40, s1)) {
                        v2 = 0;
                    }
                }
                if(v2 != 0) {
                    this.zzt.zzaA().zze().zzb("Invalid public event name. Event will not be logged (FE)", this.zzt.zzj().zzd(s1));
                    String s3 = this.zzt.zzv().zzD(s1, 40, true);
                    if(s1 != null) {
                        v1 = s1.length();
                    }
                    this.zzt.zzv().zzO(this.zzl, null, v2, "_ev", s3, v1);
                    return;
                }
            }
            zzir zzir0 = this.zzt.zzs().zzj(false);
            if(zzir0 != null && !bundle0.containsKey("_sc")) {
                zzir0.zzd = true;
            }
            zzlp.zzK(zzir0, bundle0, z && !z2);
            boolean z3 = "am".equals(s);
            boolean z4 = zzlp.zzaj(s1);
            if(!z || this.zzd == null || z4) {
                v3 = v;
                z5 = z3;
            }
            else {
                if(z3) {
                    v3 = v;
                    z5 = true;
                    goto label_56;
                }
                this.zzt.zzaA().zzc().zzc("Passing event to registered event handler (FE)", this.zzt.zzj().zzd(s1), this.zzt.zzj().zzb(bundle0));
                Preconditions.checkNotNull(this.zzd);
                this.zzd.interceptEvent(s, s1, bundle0, v);
                return;
            }
        label_56:
            if(this.zzt.zzM()) {
                int v4 = this.zzt.zzv().zzh(s1);
                if(v4 != 0) {
                    this.zzt.zzaA().zze().zzb("Invalid event name. Event will not be logged (FE)", this.zzt.zzj().zzd(s1));
                    String s4 = this.zzt.zzv().zzD(s1, 40, true);
                    if(s1 != null) {
                        v1 = s1.length();
                    }
                    this.zzt.zzv().zzO(this.zzl, s2, v4, "_ev", s4, v1);
                    return;
                }
                List list1 = CollectionUtils.listOf(new String[]{"_o", "_sn", "_sc", "_si"});
                Bundle bundle1 = this.zzt.zzv().zzu(s2, s1, bundle0, list1, z2);
                Preconditions.checkNotNull(bundle1);
                if(this.zzt.zzs().zzj(false) != null && "_ae".equals(s1)) {
                    zzkp zzkp0 = this.zzt.zzu();
                    long v5 = zzkp0.zzb.zzc.zzt.zzax().elapsedRealtime();
                    long v6 = v5 - zzkp0.zzb.zzb;
                    zzkp0.zzb.zzb = v5;
                    if(v6 > 0L) {
                        this.zzt.zzv().zzI(bundle1, v6);
                    }
                }
                zzos.zzc();
                if(this.zzt.zzf().zzs(null, zzeg.zzae)) {
                    if(!"auto".equals(s) && "_ssr".equals(s1)) {
                        zzlp zzlp1 = this.zzt.zzv();
                        String s5 = bundle1.getString("_ffr");
                        if(Strings.isEmptyOrWhitespace(s5)) {
                            s5 = null;
                        }
                        else if(s5 != null) {
                            s5 = s5.trim();
                        }
                        if(!zzln.zza(s5, zzlp1.zzt.zzm().zzp.zza())) {
                            zzlp1.zzt.zzm().zzp.zzb(s5);
                            goto label_94;
                        }
                        zzlp1.zzt.zzaA().zzc().zza("Not logging duplicate session_start_with_rollout event");
                        return;
                    }
                    else if("_ae".equals(s1)) {
                        String s6 = this.zzt.zzv().zzt.zzm().zzp.zza();
                        if(!TextUtils.isEmpty(s6)) {
                            bundle1.putString("_ffr", s6);
                        }
                    }
                }
            label_94:
                ArrayList arrayList0 = new ArrayList();
                arrayList0.add(bundle1);
                boolean z6 = this.zzt.zzf().zzs(null, zzeg.zzaG) ? this.zzt.zzu().zzo() : this.zzt.zzm().zzm.zzb();
                if(this.zzt.zzm().zzj.zza() <= 0L || !this.zzt.zzm().zzk(v3) || !z6) {
                    arrayList1 = arrayList0;
                }
                else {
                    this.zzt.zzaA().zzj().zza("Current session is expired, remove the session number, ID, and engagement time");
                    arrayList1 = arrayList0;
                    this.zzY("auto", "_sid", null, this.zzt.zzax().currentTimeMillis());
                    this.zzY("auto", "_sno", null, this.zzt.zzax().currentTimeMillis());
                    this.zzY("auto", "_se", null, this.zzt.zzax().currentTimeMillis());
                    this.zzt.zzm().zzk.zzb(0L);
                }
                if(bundle1.getLong("extend_session", 0L) == 1L) {
                    this.zzt.zzaA().zzj().zza("EXTEND_SESSION param attached: initiate a new session or extend the current active session");
                    this.zzt.zzu().zza.zzb(v3, true);
                }
                ArrayList arrayList2 = new ArrayList(bundle1.keySet());
                Collections.sort(arrayList2);
                int v7 = arrayList2.size();
                for(int v8 = 0; v8 < v7; ++v8) {
                    String s7 = (String)arrayList2.get(v8);
                    if(s7 != null) {
                        this.zzt.zzv();
                        Object object0 = bundle1.get(s7);
                        if(object0 instanceof Bundle) {
                            arr_bundle = new Bundle[]{((Bundle)object0)};
                        }
                        else if(object0 instanceof Parcelable[]) {
                            arr_bundle = (Bundle[])Arrays.copyOf(((Parcelable[])object0), ((Parcelable[])object0).length, Bundle[].class);
                        }
                        else {
                            arr_bundle = object0 instanceof ArrayList ? ((Bundle[])((ArrayList)object0).toArray(new Bundle[((ArrayList)object0).size()])) : null;
                        }
                        if(arr_bundle != null) {
                            bundle1.putParcelableArray(s7, arr_bundle);
                        }
                    }
                }
                int v9 = 0;
                while(v9 < arrayList1.size()) {
                    Bundle bundle2 = (Bundle)arrayList1.get(v9);
                    bundle2.putString("_o", s);
                    if(z1) {
                        bundle2 = this.zzt.zzv().zzt(bundle2);
                    }
                    Bundle bundle3 = bundle2;
                    zzau zzau0 = new zzau((v9 == 0 ? s1 : "_ep"), new zzas(bundle2), s, v3);
                    this.zzt.zzt().zzA(zzau0, s2);
                    if(!z5) {
                        for(Object object1: this.zze) {
                            ((zzhg)object1).onEvent(s, s1, new Bundle(bundle3), v);
                        }
                    }
                    ++v9;
                    v3 = v;
                }
                if(this.zzt.zzs().zzj(false) != null && "_ae".equals(s1)) {
                    zzkp zzkp1 = this.zzt.zzu();
                    long v10 = this.zzt.zzax().elapsedRealtime();
                    zzkp1.zzb.zzd(true, true, v10);
                }
            }
            return;
        }
        this.zzt.zzaA().zzc().zza("Event not sent since app measurement is disabled");
    }

    public final void zzJ(zzhg zzhg0) {
        this.zza();
        Preconditions.checkNotNull(zzhg0);
        if(!this.zze.add(zzhg0)) {
            this.zzt.zzaA().zzk().zza("OnEventListener already registered");
        }
    }

    public final void zzK(long v) {
        this.zzg.set(null);
        this.zzt.zzaB().zzp(new zzhs(this, v));
    }

    final void zzL(long v, boolean z) {
        this.zzg();
        this.zza();
        this.zzt.zzaA().zzc().zza("Resetting analytics data (FE)");
        zzkp zzkp0 = this.zzt.zzu();
        zzkp0.zzg();
        zzkp0.zzb.zza();
        zzqu.zzc();
        if(this.zzt.zzf().zzs(null, zzeg.zzan)) {
            this.zzt.zzh().zzo();
        }
        boolean z1 = this.zzt.zzJ();
        zzfi zzfi0 = this.zzt.zzm();
        zzfi0.zzc.zzb(v);
        if(!TextUtils.isEmpty(zzfi0.zzt.zzm().zzp.zza())) {
            zzfi0.zzp.zzb(null);
        }
        zzph.zzc();
        if(zzfi0.zzt.zzf().zzs(null, zzeg.zzaf)) {
            zzfi0.zzj.zzb(0L);
        }
        zzfi0.zzk.zzb(0L);
        if(!zzfi0.zzt.zzf().zzv()) {
            zzfi0.zzi(!z1);
        }
        zzfi0.zzq.zzb(null);
        zzfi0.zzr.zzb(0L);
        zzfi0.zzs.zzb(null);
        if(z) {
            this.zzt.zzt().zzC();
        }
        zzph.zzc();
        if(this.zzt.zzf().zzs(null, zzeg.zzaf)) {
            this.zzt.zzu().zza.zza();
        }
        this.zzc = !z1;
    }

    protected final void zzM(String s, String s1, long v, Bundle bundle0, boolean z, boolean z1, boolean z2, String s2) {
        Bundle bundle1 = new Bundle(bundle0);
        Iterator iterator0 = bundle1.keySet().iterator();
        while(iterator0.hasNext()) {
            int v1 = 0;
            Object object0 = iterator0.next();
            String s3 = (String)object0;
            Object object1 = bundle1.get(s3);
            if(object1 instanceof Bundle) {
                bundle1.putBundle(s3, new Bundle(((Bundle)object1)));
            }
            else if(object1 instanceof Parcelable[]) {
                Parcelable[] arr_parcelable = (Parcelable[])object1;
                while(v1 < arr_parcelable.length) {
                    Parcelable parcelable0 = arr_parcelable[v1];
                    if(parcelable0 instanceof Bundle) {
                        arr_parcelable[v1] = new Bundle(((Bundle)parcelable0));
                    }
                    ++v1;
                }
            }
            else if(object1 instanceof List) {
                List list0 = (List)object1;
                while(v1 < list0.size()) {
                    Object object2 = list0.get(v1);
                    if(object2 instanceof Bundle) {
                        list0.set(v1, new Bundle(((Bundle)object2)));
                    }
                    ++v1;
                }
            }
        }
        this.zzt.zzaB().zzp(new zzhp(this, s, s1, v, bundle1, z, z1, z2, s2));
    }

    final void zzN(String s, String s1, long v, Object object0) {
        this.zzt.zzaB().zzp(new zzhq(this, s, s1, object0, v));
    }

    final void zzO(String s) {
        this.zzg.set(s);
    }

    public final void zzP(Bundle bundle0) {
        this.zzQ(bundle0, this.zzt.zzax().currentTimeMillis());
    }

    public final void zzQ(Bundle bundle0, long v) {
        Preconditions.checkNotNull(bundle0);
        Bundle bundle1 = new Bundle(bundle0);
        if(!TextUtils.isEmpty(bundle1.getString("app_id"))) {
            this.zzt.zzaA().zzk().zza("Package name should be null when calling setConditionalUserProperty");
        }
        bundle1.remove("app_id");
        Preconditions.checkNotNull(bundle1);
        zzgz.zza(bundle1, "app_id", String.class, null);
        zzgz.zza(bundle1, "origin", String.class, null);
        zzgz.zza(bundle1, "name", String.class, null);
        zzgz.zza(bundle1, "value", Object.class, null);
        zzgz.zza(bundle1, "trigger_event_name", String.class, null);
        zzgz.zza(bundle1, "trigger_timeout", Long.class, 0L);
        zzgz.zza(bundle1, "timed_out_event_name", String.class, null);
        zzgz.zza(bundle1, "timed_out_event_params", Bundle.class, null);
        zzgz.zza(bundle1, "triggered_event_name", String.class, null);
        zzgz.zza(bundle1, "triggered_event_params", Bundle.class, null);
        zzgz.zza(bundle1, "time_to_live", Long.class, 0L);
        zzgz.zza(bundle1, "expired_event_name", String.class, null);
        zzgz.zza(bundle1, "expired_event_params", Bundle.class, null);
        Preconditions.checkNotEmpty(bundle1.getString("name"));
        Preconditions.checkNotEmpty(bundle1.getString("origin"));
        Preconditions.checkNotNull(bundle1.get("value"));
        bundle1.putLong("creation_timestamp", v);
        String s = bundle1.getString("name");
        Object object0 = bundle1.get("value");
        if(this.zzt.zzv().zzl(s) == 0) {
            if(this.zzt.zzv().zzd(s, object0) == 0) {
                Object object1 = this.zzt.zzv().zzB(s, object0);
                if(object1 == null) {
                    this.zzt.zzaA().zzd().zzc("Unable to normalize conditional user property value", this.zzt.zzj().zzf(s), object0);
                    return;
                }
                zzgz.zzb(bundle1, object1);
                long v1 = bundle1.getLong("trigger_timeout");
                if(!TextUtils.isEmpty(bundle1.getString("trigger_event_name")) && (v1 > 15552000000L || v1 < 1L)) {
                    this.zzt.zzaA().zzd().zzc("Invalid conditional user property timeout", this.zzt.zzj().zzf(s), v1);
                    return;
                }
                long v2 = bundle1.getLong("time_to_live");
                if(v2 <= 15552000000L && v2 >= 1L) {
                    this.zzt.zzaB().zzp(new zzht(this, bundle1));
                    return;
                }
                this.zzt.zzaA().zzd().zzc("Invalid conditional user property time to live", this.zzt.zzj().zzf(s), v2);
                return;
            }
            this.zzt.zzaA().zzd().zzc("Invalid conditional user property value", this.zzt.zzj().zzf(s), object0);
            return;
        }
        this.zzt.zzaA().zzd().zzb("Invalid conditional user property name", this.zzt.zzj().zzf(s));
    }

    public final void zzR(zzhb zzhb0, long v) {
        Throwable throwable2;
        boolean z2;
        boolean z1;
        zzhb zzhb1;
        boolean z;
        this.zza();
        int v1 = zzhb0.zza();
        if(v1 != -10 && zzhb0.zzf() == null && zzhb0.zzg() == null) {
            this.zzt.zzaA().zzl().zza("Discarding empty consent settings");
            return;
        }
        Object object0 = this.zzh;
        __monitor_enter(object0);
        try {
            z = false;
            zzhb1 = this.zzi;
            if(zzhb.zzk(v1, zzhb1.zza())) {
                goto label_10;
            }
            else {
                goto label_21;
            }
            goto label_23;
        }
        catch(Throwable throwable0) {
            goto label_26;
        }
        try {
        label_10:
            z1 = zzhb0.zzm(this.zzi);
            if(zzhb0.zzj(zzha.zzb) && !this.zzi.zzj(zzha.zzb)) {
                z = true;
            }
            zzhb0 = zzhb0.zze(this.zzi);
            this.zzi = zzhb0;
            z2 = z;
            z = true;
            goto label_23;
        }
        catch(Throwable throwable1) {
            throwable2 = throwable1;
            goto label_27;
        }
    label_21:
        z1 = false;
        z2 = false;
        try {
        label_23:
            __monitor_exit(object0);
            goto label_29;
        label_26:
            throwable2 = throwable0;
        label_27:
            __monitor_exit(object0);
        }
        catch(Throwable throwable0) {
            goto label_26;
        }
        throw throwable2;
    label_29:
        if(!z) {
            this.zzt.zzaA().zzi().zzb("Ignoring lower-priority consent settings, proposed settings", zzhb0);
            return;
        }
        long v2 = this.zzj.getAndIncrement();
        if(z1) {
            this.zzg.set(null);
            this.zzt.zzaB().zzq(new zzif(this, zzhb0, v, v2, z2, zzhb1));
            return;
        }
        zzig zzig0 = new zzig(this, zzhb0, v2, z2, zzhb1);
        if(v1 != -10 && v1 != 30) {
            this.zzt.zzaB().zzp(zzig0);
            return;
        }
        this.zzt.zzaB().zzq(zzig0);
    }

    public final void zzS(Bundle bundle0, int v, long v1) {
        this.zza();
        String s = zzhb.zzh(bundle0);
        if(s != null) {
            this.zzt.zzaA().zzl().zzb("Ignoring invalid consent setting", s);
            this.zzt.zzaA().zzl().zza("Valid consent values are \'granted\', \'denied\'");
        }
        this.zzR(zzhb.zzb(bundle0, v), v1);
    }

    public final void zzT(zzhf zzhf0) {
        this.zzg();
        this.zza();
        if(zzhf0 != null) {
            zzhf zzhf1 = this.zzd;
            if(zzhf0 != zzhf1) {
                Preconditions.checkState(zzhf1 == null, "EventInterceptor already set.");
            }
        }
        this.zzd = zzhf0;
    }

    public final void zzU(Boolean boolean0) {
        this.zza();
        this.zzt.zzaB().zzp(new zzie(this, boolean0));
    }

    final void zzV(zzhb zzhb0) {
        this.zzg();
        boolean z = zzhb0.zzj(zzha.zzb) && zzhb0.zzj(zzha.zza) || this.zzt.zzt().zzM();
        if(z != this.zzt.zzK()) {
            this.zzt.zzG(z);
            zzfi zzfi0 = this.zzt.zzm();
            zzfi0.zzg();
            Boolean boolean0 = zzfi0.zza().contains("measurement_enabled_from_api") ? Boolean.valueOf(zzfi0.zza().getBoolean("measurement_enabled_from_api", true)) : null;
            if(!z || boolean0 == null || boolean0.booleanValue()) {
                this.zzaa(Boolean.valueOf(z), false);
            }
        }
    }

    public final void zzW(String s, String s1, Object object0, boolean z) {
        this.zzX("auto", "_ldl", object0, true, this.zzt.zzax().currentTimeMillis());
    }

    public final void zzX(String s, String s1, Object object0, boolean z, long v) {
        int v1;
        if(z) {
            v1 = this.zzt.zzv().zzl(s1);
        }
        else {
            zzlp zzlp0 = this.zzt.zzv();
            if(!zzlp0.zzad("user property", s1)) {
                v1 = 6;
            }
            else if(!zzlp0.zzaa("user property", zzhe.zza, null, s1)) {
                v1 = 15;
            }
            else if(zzlp0.zzZ("user property", 24, s1)) {
                v1 = 0;
            }
            else {
                v1 = 6;
            }
        }
        if(v1 != 0) {
            String s2 = this.zzt.zzv().zzD(s1, 24, true);
            this.zzt.zzv().zzO(this.zzl, null, v1, "_ev", s2, (s1 == null ? 0 : s1.length()));
            return;
        }
        String s3 = s == null ? "app" : s;
        if(object0 != null) {
            int v2 = this.zzt.zzv().zzd(s1, object0);
            if(v2 != 0) {
                String s4 = this.zzt.zzv().zzD(s1, 24, true);
                this.zzt.zzv().zzO(this.zzl, null, v2, "_ev", s4, (object0 instanceof String || object0 instanceof CharSequence ? object0.toString().length() : 0));
                return;
            }
            Object object1 = this.zzt.zzv().zzB(s1, object0);
            if(object1 != null) {
                this.zzN(s3, s1, v, object1);
            }
            return;
        }
        this.zzN(s3, s1, v, null);
    }

    final void zzY(String s, String s1, Object object0, long v) {
        Long long1;
        String s3;
        String s2 = "false";
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotEmpty(s1);
        this.zzg();
        this.zza();
        if(!"allow_personalized_ads".equals(s1)) {
            s3 = s1;
            long1 = object0;
        }
        else if(object0 instanceof String && !TextUtils.isEmpty(((String)object0))) {
            long v1 = "false".equals(((String)object0).toLowerCase(Locale.ENGLISH)) ? 1L : 0L;
            Long long0 = v1;
            zzfh zzfh0 = this.zzt.zzm().zzh;
            long0.getClass();
            if(v1 == 1L) {
                s2 = "true";
            }
            zzfh0.zzb(s2);
            s3 = "_npa";
            long1 = long0;
        }
        else if(object0 == null) {
            this.zzt.zzm().zzh.zzb("unset");
            long1 = null;
            s3 = "_npa";
        }
        else {
            s3 = s1;
            long1 = object0;
        }
        if(!this.zzt.zzJ()) {
            this.zzt.zzaA().zzj().zza("User property not set since app measurement is disabled");
            return;
        }
        if(!this.zzt.zzM()) {
            return;
        }
        zzlk zzlk0 = new zzlk(s3, v, long1, s);
        this.zzt.zzt().zzK(zzlk0);
    }

    public final void zzZ(zzhg zzhg0) {
        this.zza();
        Preconditions.checkNotNull(zzhg0);
        if(!this.zze.remove(zzhg0)) {
            this.zzt.zzaA().zzk().zza("OnEventListener had not been registered");
        }
    }

    private final void zzaa(Boolean boolean0, boolean z) {
        this.zzg();
        this.zza();
        this.zzt.zzaA().zzc().zzb("Setting app measurement enabled (FE)", boolean0);
        this.zzt.zzm().zzh(boolean0);
        if(z) {
            zzfi zzfi0 = this.zzt.zzm();
            zzfi0.zzg();
            SharedPreferences.Editor sharedPreferences$Editor0 = zzfi0.zza().edit();
            if(boolean0 == null) {
                sharedPreferences$Editor0.remove("measurement_enabled_from_api");
            }
            else {
                sharedPreferences$Editor0.putBoolean("measurement_enabled_from_api", boolean0.booleanValue());
            }
            sharedPreferences$Editor0.apply();
        }
        if(!this.zzt.zzK() && (boolean0 == null || boolean0.booleanValue())) {
            return;
        }
        this.zzab();
    }

    private final void zzab() {
        this.zzg();
        String s = this.zzt.zzm().zzh.zza();
        if(s != null) {
            if("unset".equals(s)) {
                this.zzY("app", "_npa", null, this.zzt.zzax().currentTimeMillis());
            }
            else {
                this.zzY("app", "_npa", ((long)("true".equals(s) ? 1L : 0L)), this.zzt.zzax().currentTimeMillis());
            }
        }
        if(this.zzt.zzJ() && this.zzc) {
            this.zzt.zzaA().zzc().zza("Recording app launch after enabling measurement for the first time (FE)");
            this.zzz();
            zzph.zzc();
            if(this.zzt.zzf().zzs(null, zzeg.zzaf)) {
                this.zzt.zzu().zza.zza();
            }
            this.zzt.zzaB().zzp(new zzhn(this));
            return;
        }
        this.zzt.zzaA().zzc().zza("Updating Scion state (FE)");
        this.zzt.zzt().zzI();
    }

    @Override  // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    public final int zzh(String s) {
        Preconditions.checkNotEmpty(s);
        return 25;
    }

    public final Boolean zzi() {
        AtomicReference atomicReference0 = new AtomicReference();
        return (Boolean)this.zzt.zzaB().zzd(atomicReference0, 15000L, "boolean test flag value", new zzhw(this, atomicReference0));
    }

    public final Double zzj() {
        AtomicReference atomicReference0 = new AtomicReference();
        return (Double)this.zzt.zzaB().zzd(atomicReference0, 15000L, "double test flag value", new zzid(this, atomicReference0));
    }

    public final Integer zzl() {
        AtomicReference atomicReference0 = new AtomicReference();
        return (Integer)this.zzt.zzaB().zzd(atomicReference0, 15000L, "int test flag value", new zzic(this, atomicReference0));
    }

    public final Long zzm() {
        AtomicReference atomicReference0 = new AtomicReference();
        return (Long)this.zzt.zzaB().zzd(atomicReference0, 15000L, "long test flag value", new zzib(this, atomicReference0));
    }

    public final String zzo() {
        return (String)this.zzg.get();
    }

    public final String zzp() {
        zzir zzir0 = this.zzt.zzs().zzi();
        return zzir0 == null ? null : zzir0.zzb;
    }

    public final String zzq() {
        zzir zzir0 = this.zzt.zzs().zzi();
        return zzir0 == null ? null : zzir0.zza;
    }

    public final String zzr() {
        AtomicReference atomicReference0 = new AtomicReference();
        return (String)this.zzt.zzaB().zzd(atomicReference0, 15000L, "String test flag value", new zzia(this, atomicReference0));
    }

    public final ArrayList zzs(String s, String s1) {
        if(this.zzt.zzaB().zzs()) {
            this.zzt.zzaA().zzd().zza("Cannot get conditional user properties from analytics worker thread");
            return new ArrayList(0);
        }
        if(zzab.zza()) {
            this.zzt.zzaA().zzd().zza("Cannot get conditional user properties from main thread");
            return new ArrayList(0);
        }
        AtomicReference atomicReference0 = new AtomicReference();
        this.zzt.zzaB().zzd(atomicReference0, 5000L, "get conditional user properties", new zzhv(this, atomicReference0, null, s, s1));
        List list0 = (List)atomicReference0.get();
        if(list0 == null) {
            this.zzt.zzaA().zzd().zzb("Timed out waiting for get conditional user properties", null);
            return new ArrayList();
        }
        return zzlp.zzH(list0);
    }

    public final List zzt(boolean z) {
        this.zza();
        this.zzt.zzaA().zzj().zza("Getting user properties (FE)");
        if(!this.zzt.zzaB().zzs()) {
            if(zzab.zza()) {
                this.zzt.zzaA().zzd().zza("Cannot get all user properties from main thread");
                return Collections.EMPTY_LIST;
            }
            AtomicReference atomicReference0 = new AtomicReference();
            this.zzt.zzaB().zzd(atomicReference0, 5000L, "get user properties", new zzhr(this, atomicReference0, z));
            List list0 = (List)atomicReference0.get();
            if(list0 == null) {
                this.zzt.zzaA().zzd().zzb("Timed out waiting for get user properties, includeInternal", Boolean.valueOf(z));
                return Collections.EMPTY_LIST;
            }
            return list0;
        }
        this.zzt.zzaA().zzd().zza("Cannot get all user properties from analytics worker thread");
        return Collections.EMPTY_LIST;
    }

    public final Map zzu(String s, String s1, boolean z) {
        if(this.zzt.zzaB().zzs()) {
            this.zzt.zzaA().zzd().zza("Cannot get user properties from analytics worker thread");
            return Collections.EMPTY_MAP;
        }
        if(zzab.zza()) {
            this.zzt.zzaA().zzd().zza("Cannot get user properties from main thread");
            return Collections.EMPTY_MAP;
        }
        AtomicReference atomicReference0 = new AtomicReference();
        this.zzt.zzaB().zzd(atomicReference0, 5000L, "get user properties", new zzhx(this, atomicReference0, null, s, s1, z));
        List list0 = (List)atomicReference0.get();
        if(list0 == null) {
            this.zzt.zzaA().zzd().zzb("Timed out waiting for handle get user properties, includeInternal", Boolean.valueOf(z));
            return Collections.EMPTY_MAP;
        }
        Map map0 = new ArrayMap(list0.size());
        for(Object object0: list0) {
            zzlk zzlk0 = (zzlk)object0;
            Object object1 = zzlk0.zza();
            if(object1 != null) {
                map0.put(zzlk0.zzb, object1);
            }
        }
        return map0;
    }

    static void zzv(zzik zzik0, zzhb zzhb0, zzhb zzhb1) {
        zzha[] arr_zzha = {zzha.zzb, zzha.zza};
        for(int v = 0; true; ++v) {
            boolean z = false;
            if(v >= 2) {
                break;
            }
            zzha zzha0 = arr_zzha[v];
            if(!zzhb1.zzj(zzha0) && zzhb0.zzj(zzha0)) {
                z = true;
                break;
            }
        }
        if(!z && !zzhb0.zzn(zzhb1, new zzha[]{zzha.zzb, zzha.zza})) {
            return;
        }
        zzik0.zzt.zzh().zzo();
    }

    static void zzw(zzik zzik0, zzhb zzhb0, long v, boolean z, boolean z1) {
        zzik0.zzg();
        zzik0.zza();
        zzhb zzhb1 = zzik0.zzt.zzm().zzc();
        if(v <= zzik0.zzk && zzhb.zzk(zzhb1.zza(), zzhb0.zza())) {
            zzik0.zzt.zzaA().zzi().zzb("Dropped out-of-date consent setting, proposed settings", zzhb0);
            return;
        }
        zzfi zzfi0 = zzik0.zzt.zzm();
        zzfi0.zzg();
        int v1 = zzhb0.zza();
        if(zzfi0.zzl(v1)) {
            SharedPreferences.Editor sharedPreferences$Editor0 = zzfi0.zza().edit();
            sharedPreferences$Editor0.putString("consent_settings", zzhb0.zzi());
            sharedPreferences$Editor0.putInt("consent_source", v1);
            sharedPreferences$Editor0.apply();
            zzik0.zzk = v;
            zzik0.zzt.zzt().zzF(z);
            if(z1) {
                zzik0.zzt.zzt().zzu(new AtomicReference());
            }
            return;
        }
        zzik0.zzt.zzaA().zzi().zzb("Lower precedence consent source ignored, proposed source", zzhb0.zza());
    }

    static void zzx(zzik zzik0, Boolean boolean0, boolean z) {
        zzik0.zzaa(boolean0, true);
    }

    static void zzy(zzik zzik0) {
        zzik0.zzab();
    }

    public final void zzz() {
        this.zzg();
        this.zza();
        if(this.zzt.zzM()) {
            if(this.zzt.zzf().zzs(null, zzeg.zzZ)) {
                Boolean boolean0 = this.zzt.zzf().zzk("google_analytics_deferred_deep_link_enabled");
                if(boolean0 != null && boolean0.booleanValue()) {
                    this.zzt.zzaA().zzc().zza("Deferred Deep Link feature enabled.");
                    this.zzt.zzaB().zzp(new zzhm(this));
                }
            }
            this.zzt.zzt().zzq();
            this.zzc = false;
            zzfi zzfi0 = this.zzt.zzm();
            zzfi0.zzg();
            String s = zzfi0.zza().getString("previous_os_version", null);
            zzfi0.zzt.zzg().zzv();
            String s1 = Build.VERSION.RELEASE;
            if(!TextUtils.isEmpty(s1) && !s1.equals(s)) {
                SharedPreferences.Editor sharedPreferences$Editor0 = zzfi0.zza().edit();
                sharedPreferences$Editor0.putString("previous_os_version", s1);
                sharedPreferences$Editor0.apply();
            }
            if(!TextUtils.isEmpty(s)) {
                this.zzt.zzg().zzv();
                if(!s.equals(Build.VERSION.RELEASE)) {
                    Bundle bundle0 = new Bundle();
                    bundle0.putString("_po", s);
                    this.zzG("auto", "_ou", bundle0);
                }
            }
        }
    }
}

