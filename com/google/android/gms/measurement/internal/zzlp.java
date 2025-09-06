package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzcf;
import com.google.android.gms.internal.measurement.zzpq;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import javax.security.auth.x500.X500Principal;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

public final class zzlp extends zzgx {
    private static final String[] zza;
    private static final String[] zzb;
    private SecureRandom zzc;
    private final AtomicLong zzd;
    private int zze;
    private Integer zzf;

    static {
        zzlp.zza = new String[]{"firebase_", "google_", "ga_"};
        zzlp.zzb = new String[]{"_err"};
    }

    zzlp(zzgd zzgd0) {
        super(zzgd0);
        this.zzf = null;
        this.zzd = new AtomicLong(0L);
    }

    final Object zzA(String s, Object object0) {
        if("_ev".equals(s)) {
            return this.zzat(0x100, object0, true, true);
        }
        return zzlp.zzaj(s) ? this.zzat(0x100, object0, false, true) : this.zzat(100, object0, false, true);
    }

    // 去混淆评级： 低(20)
    final Object zzB(String s, Object object0) {
        return "_ldl".equals(s) ? this.zzat(this.zzas(s), object0, true, false) : this.zzat(this.zzas(s), object0, false, false);
    }

    final String zzC() {
        byte[] arr_b = new byte[16];
        this.zzG().nextBytes(arr_b);
        return String.format(Locale.US, "%032x", new BigInteger(1, arr_b));
    }

    public final String zzD(String s, int v, boolean z) {
        if(s == null) {
            return null;
        }
        if(s.codePointCount(0, s.length()) > v) {
            return z ? s.substring(0, s.offsetByCodePoints(0, v)) + "..." : null;
        }
        return s;
    }

    public final URL zzE(long v, String s, String s1, long v1) {
        try {
            Preconditions.checkNotEmpty(s1);
            Preconditions.checkNotEmpty(s);
            String s2 = String.format("https://www.googleadservices.com/pagead/conversion/app/deeplink?id_type=adid&sdk_version=%s&rdid=%s&bundleid=%s&retry=%s", String.format("v%s.%s", 79000L, this.zzm()), s1, s, v1);
            if(s.equals(this.zzt.zzf().zzm())) {
                s2 = s2 + "&ddl_test=1";
            }
            return new URL(s2);
        }
        catch(MalformedURLException | IllegalArgumentException malformedURLException0) {
            this.zzt.zzaA().zzd().zzb("Failed to create BOW URL for Deferred Deep Link. exception", malformedURLException0.getMessage());
            return null;
        }
    }

    static MessageDigest zzF() {
        for(int v = 0; v < 2; ++v) {
            try {
                MessageDigest messageDigest0 = MessageDigest.getInstance("MD5");
                if(messageDigest0 != null) {
                    return messageDigest0;
                }
            }
            catch(NoSuchAlgorithmException unused_ex) {
            }
        }
        return null;
    }

    @EnsuresNonNull({"this.secureRandom"})
    final SecureRandom zzG() {
        this.zzg();
        if(this.zzc == null) {
            this.zzc = new SecureRandom();
        }
        return this.zzc;
    }

    public static ArrayList zzH(List list0) {
        if(list0 == null) {
            return new ArrayList(0);
        }
        ArrayList arrayList0 = new ArrayList(list0.size());
        for(Object object0: list0) {
            Bundle bundle0 = new Bundle();
            bundle0.putString("app_id", ((zzac)object0).zza);
            bundle0.putString("origin", ((zzac)object0).zzb);
            bundle0.putLong("creation_timestamp", ((zzac)object0).zzd);
            bundle0.putString("name", ((zzac)object0).zzc.zzb);
            zzgz.zzb(bundle0, Preconditions.checkNotNull(((zzac)object0).zzc.zza()));
            bundle0.putBoolean("active", ((zzac)object0).zze);
            String s = ((zzac)object0).zzf;
            if(s != null) {
                bundle0.putString("trigger_event_name", s);
            }
            zzau zzau0 = ((zzac)object0).zzg;
            if(zzau0 != null) {
                bundle0.putString("timed_out_event_name", zzau0.zza);
                zzas zzas0 = zzau0.zzb;
                if(zzas0 != null) {
                    bundle0.putBundle("timed_out_event_params", zzas0.zzc());
                }
            }
            bundle0.putLong("trigger_timeout", ((zzac)object0).zzh);
            zzau zzau1 = ((zzac)object0).zzi;
            if(zzau1 != null) {
                bundle0.putString("triggered_event_name", zzau1.zza);
                zzas zzas1 = zzau1.zzb;
                if(zzas1 != null) {
                    bundle0.putBundle("triggered_event_params", zzas1.zzc());
                }
            }
            bundle0.putLong("triggered_timestamp", ((zzac)object0).zzc.zzc);
            bundle0.putLong("time_to_live", ((zzac)object0).zzj);
            zzau zzau2 = ((zzac)object0).zzk;
            if(zzau2 != null) {
                bundle0.putString("expired_event_name", zzau2.zza);
                zzas zzas2 = zzau2.zzb;
                if(zzas2 != null) {
                    bundle0.putBundle("expired_event_params", zzas2.zzc());
                }
            }
            arrayList0.add(bundle0);
        }
        return arrayList0;
    }

    final void zzI(Bundle bundle0, long v) {
        long v1 = bundle0.getLong("_et");
        if(v1 == 0L) {
            v1 = 0L;
        }
        else {
            this.zzt.zzaA().zzk().zzb("Params already contained engagement", v1);
        }
        bundle0.putLong("_et", v + v1);
    }

    final void zzJ(Bundle bundle0, int v, String s, String s1, Object object0) {
        if(zzlp.zzaq(bundle0, v)) {
            bundle0.putString("_ev", this.zzD(s, 40, true));
            if(object0 != null) {
                Preconditions.checkNotNull(bundle0);
                if(object0 instanceof String || object0 instanceof CharSequence) {
                    bundle0.putLong("_el", ((long)object0.toString().length()));
                }
            }
        }
    }

    public static void zzK(zzir zzir0, Bundle bundle0, boolean z) {
        if(bundle0 != null && zzir0 != null) {
            if(bundle0.containsKey("_sc") && !z) {
                z = false;
                goto label_16;
            }
            String s = zzir0.zza;
            if(s == null) {
                bundle0.remove("_sn");
            }
            else {
                bundle0.putString("_sn", s);
            }
            String s1 = zzir0.zzb;
            if(s1 == null) {
                bundle0.remove("_sc");
            }
            else {
                bundle0.putString("_sc", s1);
            }
            bundle0.putLong("_si", zzir0.zzc);
            return;
        }
    label_16:
        if(bundle0 != null && zzir0 == null && z) {
            bundle0.remove("_sn");
            bundle0.remove("_sc");
            bundle0.remove("_si");
        }
    }

    final void zzL(Bundle bundle0, Bundle bundle1) {
        if(bundle1 != null) {
            for(Object object0: bundle1.keySet()) {
                String s = (String)object0;
                if(!bundle0.containsKey(s)) {
                    this.zzt.zzv().zzP(bundle0, s, bundle1.get(s));
                }
            }
        }
    }

    final void zzM(Parcelable[] arr_parcelable, int v, boolean z) {
        Preconditions.checkNotNull(arr_parcelable);
        for(int v1 = 0; v1 < arr_parcelable.length; ++v1) {
            Bundle bundle0 = (Bundle)arr_parcelable[v1];
            int v2 = 0;
            for(Object object0: new TreeSet(bundle0.keySet())) {
                String s = (String)object0;
                if(zzlp.zzak(s) && !zzlp.zzau(s, zzhd.zzd)) {
                    ++v2;
                    if(v2 > v) {
                        if(z) {
                            this.zzt.zzaA().zze().zzc("Param can\'t contain more than " + v + " item-scoped custom parameters", this.zzt.zzj().zze(s), this.zzt.zzj().zzb(bundle0));
                            zzlp.zzaq(bundle0, 28);
                        }
                        else {
                            this.zzt.zzaA().zze().zzc("Param cannot contain item-scoped custom parameters", this.zzt.zzj().zze(s), this.zzt.zzj().zzb(bundle0));
                            zzlp.zzaq(bundle0, 23);
                        }
                        bundle0.remove(s);
                    }
                }
            }
        }
    }

    final void zzN(zzeu zzeu0, int v) {
        int v1 = 0;
        for(Object object0: new TreeSet(zzeu0.zzd.keySet())) {
            String s = (String)object0;
            if(zzlp.zzak(s)) {
                ++v1;
                if(v1 > v) {
                    this.zzt.zzaA().zze().zzc("Event can\'t contain more than " + v + " params", this.zzt.zzj().zzd(zzeu0.zza), this.zzt.zzj().zzb(zzeu0.zzd));
                    zzlp.zzaq(zzeu0.zzd, 5);
                    zzeu0.zzd.remove(s);
                }
            }
        }
    }

    final void zzO(zzlo zzlo0, String s, int v, String s1, String s2, int v1) {
        Bundle bundle0 = new Bundle();
        zzlp.zzaq(bundle0, v);
        if(!TextUtils.isEmpty(s1) && !TextUtils.isEmpty(s2)) {
            bundle0.putString(s1, s2);
        }
        if(v == 2 || v == 6 || v == 7) {
            bundle0.putLong("_el", ((long)v1));
        }
        zzlo0.zza(s, "_err", bundle0);
    }

    final void zzP(Bundle bundle0, String s, Object object0) {
        if(bundle0 != null) {
            if(object0 instanceof Long) {
                bundle0.putLong(s, ((long)(((Long)object0))));
                return;
            }
            if(object0 instanceof String) {
                bundle0.putString(s, String.valueOf(object0));
                return;
            }
            if(object0 instanceof Double) {
                bundle0.putDouble(s, ((double)(((Double)object0))));
                return;
            }
            if(object0 instanceof Bundle[]) {
                bundle0.putParcelableArray(s, ((Bundle[])object0));
                return;
            }
            if(s != null) {
                String s1 = object0 == null ? null : object0.getClass().getSimpleName();
                this.zzt.zzaA().zzl().zzc("Not putting event parameter. Invalid value type. name, type", this.zzt.zzj().zze(s), s1);
            }
        }
    }

    public final void zzQ(zzcf zzcf0, boolean z) {
        Bundle bundle0 = new Bundle();
        bundle0.putBoolean("r", z);
        try {
            zzcf0.zze(bundle0);
        }
        catch(RemoteException remoteException0) {
            this.zzt.zzaA().zzk().zzb("Error returning boolean value to wrapper", remoteException0);
        }
    }

    public final void zzR(zzcf zzcf0, ArrayList arrayList0) {
        Bundle bundle0 = new Bundle();
        bundle0.putParcelableArrayList("r", arrayList0);
        try {
            zzcf0.zze(bundle0);
        }
        catch(RemoteException remoteException0) {
            this.zzt.zzaA().zzk().zzb("Error returning bundle list to wrapper", remoteException0);
        }
    }

    public final void zzS(zzcf zzcf0, Bundle bundle0) {
        try {
            zzcf0.zze(bundle0);
        }
        catch(RemoteException remoteException0) {
            this.zzt.zzaA().zzk().zzb("Error returning bundle value to wrapper", remoteException0);
        }
    }

    public final void zzT(zzcf zzcf0, byte[] arr_b) {
        Bundle bundle0 = new Bundle();
        bundle0.putByteArray("r", arr_b);
        try {
            zzcf0.zze(bundle0);
        }
        catch(RemoteException remoteException0) {
            this.zzt.zzaA().zzk().zzb("Error returning byte array to wrapper", remoteException0);
        }
    }

    public final void zzU(zzcf zzcf0, int v) {
        Bundle bundle0 = new Bundle();
        bundle0.putInt("r", v);
        try {
            zzcf0.zze(bundle0);
        }
        catch(RemoteException remoteException0) {
            this.zzt.zzaA().zzk().zzb("Error returning int value to wrapper", remoteException0);
        }
    }

    public final void zzV(zzcf zzcf0, long v) {
        Bundle bundle0 = new Bundle();
        bundle0.putLong("r", v);
        try {
            zzcf0.zze(bundle0);
        }
        catch(RemoteException remoteException0) {
            this.zzt.zzaA().zzk().zzb("Error returning long value to wrapper", remoteException0);
        }
    }

    public final void zzW(zzcf zzcf0, String s) {
        Bundle bundle0 = new Bundle();
        bundle0.putString("r", s);
        try {
            zzcf0.zze(bundle0);
        }
        catch(RemoteException remoteException0) {
            this.zzt.zzaA().zzk().zzb("Error returning string value to wrapper", remoteException0);
        }
    }

    final void zzX(String s, String s1, String s2, Bundle bundle0, List list0, boolean z) {
        String s4;
        int v4;
        int v2;
        if(bundle0 != null) {
            zzag zzag0 = this.zzt.zzf();
            zzpq.zzc();
            int v = !zzag0.zzt.zzf().zzs(null, zzeg.zzaz) || !zzag0.zzt.zzv().zzai(231100000, true) ? 0 : 35;
            int v1 = 0;
            for(Object object0: new TreeSet(bundle0.keySet())) {
                String s3 = (String)object0;
                if(list0 == null || !list0.contains(s3)) {
                    int v3 = z ? 0 : this.zzj(s3);
                    if(v3 == 0) {
                        v3 = this.zzi(s3);
                    }
                    v2 = v3;
                }
                else {
                    v2 = 0;
                }
                switch(v2) {
                    case 0: {
                        if(this.zzag(bundle0.get(s3))) {
                            this.zzt.zzaA().zzl().zzd("Nested Bundle parameters are not allowed; discarded. event name, param name, child param name", s1, s2, s3);
                            v4 = 22;
                        }
                        else {
                            v4 = this.zza(s, s1, s3, bundle0.get(s3), bundle0, list0, z, false);
                        }
                        if(v4 == 0 || "_ev".equals(s3)) {
                            if(!zzlp.zzak(s3) || zzlp.zzau(s3, zzhd.zzd)) {
                                continue;
                            }
                            ++v1;
                            if(this.zzai(231100000, true)) {
                                if(v1 <= v) {
                                    continue;
                                }
                                zzpq.zzc();
                                if(this.zzt.zzf().zzs(null, zzeg.zzaz)) {
                                    this.zzt.zzaA().zze().zzc("Item can\'t contain more than " + v + " item-scoped custom params", this.zzt.zzj().zzd(s1), this.zzt.zzj().zzb(bundle0));
                                    zzlp.zzaq(bundle0, 28);
                                }
                                else {
                                    this.zzt.zzaA().zze().zzc("Item cannot contain custom parameters", this.zzt.zzj().zzd(s1), this.zzt.zzj().zzb(bundle0));
                                    zzlp.zzaq(bundle0, 23);
                                }
                            }
                            else {
                                this.zzt.zzaA().zze().zzc("Item array not supported on client\'s version of Google Play Services (Android Only)", this.zzt.zzj().zzd(s1), this.zzt.zzj().zzb(bundle0));
                                zzlp.zzaq(bundle0, 23);
                            }
                        }
                        else {
                            this.zzJ(bundle0, v4, s3, s3, bundle0.get(s3));
                        }
                        bundle0.remove(s3);
                        continue;
                    }
                    case 3: {
                        s4 = s3;
                        break;
                    }
                    default: {
                        s4 = null;
                    }
                }
                this.zzJ(bundle0, v2, s3, s3, s4);
                bundle0.remove(s3);
            }
        }
    }

    final boolean zzY(String s, String s1) {
        if(!TextUtils.isEmpty(s)) {
            if(zzlp.zzar(s)) {
                return true;
            }
            if(this.zzt.zzL()) {
                this.zzt.zzaA().zze().zzb("Invalid google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI. provided id", zzet.zzn(s));
            }
            return false;
        }
        if(!TextUtils.isEmpty(s1)) {
            if(!zzlp.zzar(s1)) {
                this.zzt.zzaA().zze().zzb("Invalid admob_app_id. Analytics disabled.", zzet.zzn(s1));
                return false;
            }
            return true;
        }
        if(this.zzt.zzL()) {
            this.zzt.zzaA().zze().zza("Missing google_app_id. Firebase Analytics disabled. See https://goo.gl/NAOOOI");
        }
        return false;
    }

    final boolean zzZ(String s, int v, String s1) {
        if(s1 == null) {
            this.zzt.zzaA().zze().zzb("Name is required and can\'t be null. Type", s);
            return false;
        }
        if(s1.codePointCount(0, s1.length()) > v) {
            this.zzt.zzaA().zze().zzd("Name is too long. Type, maximum supported length, name", s, v, s1);
            return false;
        }
        return true;
    }

    final int zza(String s, String s1, String s2, Object object0, Bundle bundle0, List list0, boolean z, boolean z1) {
        int v1;
        int v;
        this.zzg();
        if(this.zzag(object0)) {
            if(!z1) {
                return 21;
            }
            if(!zzlp.zzau(s2, zzhd.zzc)) {
                return 20;
            }
            zzjz zzjz0 = this.zzt.zzt();
            zzjz0.zzg();
            zzjz0.zza();
            if(zzjz0.zzN() && zzjz0.zzt.zzv().zzm() < 200900) {
                return 25;
            }
            if(object0 instanceof Parcelable[]) {
                v = ((Parcelable[])object0).length;
                goto label_15;
            }
            else if(object0 instanceof ArrayList) {
                v = ((ArrayList)object0).size();
            label_15:
                if(v > 200) {
                    this.zzt.zzaA().zzl().zzd("Parameter array is too long; discarded. Value kind, name, array length", "param", s2, v);
                    v1 = 17;
                    if(!(object0 instanceof Parcelable[])) {
                        if(object0 instanceof ArrayList && ((ArrayList)object0).size() > 200) {
                            bundle0.putParcelableArrayList(s2, new ArrayList(((ArrayList)object0).subList(0, 200)));
                        }
                    }
                    else if(((Parcelable[])object0).length > 200) {
                        bundle0.putParcelableArray(s2, ((Parcelable[])Arrays.copyOf(((Parcelable[])object0), 200)));
                    }
                }
                else {
                    v1 = 0;
                }
            }
            else {
                v1 = 0;
            }
        }
        else {
            v1 = 0;
        }
        if(!this.zzab("param", s2, (zzlp.zzaj(s1) || zzlp.zzaj(s2) ? 0x100 : 100), object0)) {
            if(z1) {
                if(object0 instanceof Bundle) {
                    this.zzX(s, s1, s2, ((Bundle)object0), list0, z);
                    return v1;
                }
                if(object0 instanceof Parcelable[]) {
                    for(int v2 = 0; v2 < ((Parcelable[])object0).length; ++v2) {
                        Parcelable parcelable0 = ((Parcelable[])object0)[v2];
                        if(!(parcelable0 instanceof Bundle)) {
                            this.zzt.zzaA().zzl().zzc("All Parcelable[] elements must be of type Bundle. Value type, name", parcelable0.getClass(), s2);
                            return 4;
                        }
                        this.zzX(s, s1, s2, ((Bundle)parcelable0), list0, z);
                    }
                    return v1;
                }
                if(object0 instanceof ArrayList) {
                    int v3 = ((ArrayList)object0).size();
                    for(int v4 = 0; v4 < v3; ++v4) {
                        Object object1 = ((ArrayList)object0).get(v4);
                        if(!(object1 instanceof Bundle)) {
                            zzer zzer0 = this.zzt.zzaA().zzl();
                            Class class0 = object1 == null ? "null" : object1.getClass();
                            zzer0.zzc("All ArrayList elements must be of type Bundle. Value type, name", class0, s2);
                            return 4;
                        }
                        this.zzX(s, s1, s2, ((Bundle)object1), list0, z);
                    }
                    return v1;
                }
            }
            return 4;
        }
        return v1;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgx
    protected final void zzaC() {
        this.zzg();
        SecureRandom secureRandom0 = new SecureRandom();
        long v = secureRandom0.nextLong();
        if(v == 0L) {
            v = secureRandom0.nextLong();
            if(v == 0L) {
                this.zzt.zzaA().zzk().zza("Utils falling back to Random for random id");
            }
        }
        this.zzd.set(v);
    }

    final boolean zzaa(String s, String[] arr_s, String[] arr_s1, String s1) {
        if(s1 == null) {
            this.zzt.zzaA().zze().zzb("Name is required and can\'t be null. Type", s);
            return false;
        }
        Preconditions.checkNotNull(s1);
        String[] arr_s2 = zzlp.zza;
        for(int v = 0; v < 3; ++v) {
            if(s1.startsWith(arr_s2[v])) {
                this.zzt.zzaA().zze().zzc("Name starts with reserved prefix. Type, name", s, s1);
                return false;
            }
        }
        if(arr_s != null && zzlp.zzau(s1, arr_s) && (arr_s1 == null || !zzlp.zzau(s1, arr_s1))) {
            this.zzt.zzaA().zze().zzc("Name is reserved. Type, name", s, s1);
            return false;
        }
        return true;
    }

    final boolean zzab(String s, String s1, int v, Object object0) {
        if(object0 == null) {
            return true;
        }
        if(!(object0 instanceof Long) && !(object0 instanceof Float) && !(object0 instanceof Integer) && !(object0 instanceof Byte) && !(object0 instanceof Short) && !(object0 instanceof Boolean) && !(object0 instanceof Double)) {
            if(!(object0 instanceof String) && !(object0 instanceof Character) && !(object0 instanceof CharSequence)) {
                return false;
            }
            String s2 = object0.toString();
            if(s2.codePointCount(0, s2.length()) > v) {
                this.zzt.zzaA().zzl().zzd("Value is too long; discarded. Value kind, name, value length", s, s1, s2.length());
                return false;
            }
        }
        return true;
    }

    final boolean zzac(String s, String s1) {
        if(s1 == null) {
            this.zzt.zzaA().zze().zzb("Name is required and can\'t be null. Type", s);
            return false;
        }
        if(s1.length() == 0) {
            this.zzt.zzaA().zze().zzb("Name is required and can\'t be empty. Type", s);
            return false;
        }
        int v = s1.codePointAt(0);
        if(!Character.isLetter(v)) {
            if(v == 0x5F) {
                v = 0x5F;
                goto label_13;
            }
            this.zzt.zzaA().zze().zzc("Name must start with a letter or _ (underscore). Type, name", s, s1);
            return false;
        }
    label_13:
        int v1 = s1.length();
        for(int v2 = Character.charCount(v); v2 < v1; v2 += Character.charCount(v3)) {
            int v3 = s1.codePointAt(v2);
            if(v3 != 0x5F && !Character.isLetterOrDigit(v3)) {
                this.zzt.zzaA().zze().zzc("Name must consist of letters, digits or _ (underscores). Type, name", s, s1);
                return false;
            }
        }
        return true;
    }

    final boolean zzad(String s, String s1) {
        if(s1 == null) {
            this.zzt.zzaA().zze().zzb("Name is required and can\'t be null. Type", s);
            return false;
        }
        if(s1.length() == 0) {
            this.zzt.zzaA().zze().zzb("Name is required and can\'t be empty. Type", s);
            return false;
        }
        int v = s1.codePointAt(0);
        if(!Character.isLetter(v)) {
            this.zzt.zzaA().zze().zzc("Name must start with a letter. Type, name", s, s1);
            return false;
        }
        int v1 = s1.length();
        for(int v2 = Character.charCount(v); v2 < v1; v2 += Character.charCount(v3)) {
            int v3 = s1.codePointAt(v2);
            if(v3 != 0x5F && !Character.isLetterOrDigit(v3)) {
                this.zzt.zzaA().zze().zzc("Name must consist of letters, digits or _ (underscores). Type, name", s, s1);
                return false;
            }
        }
        return true;
    }

    final boolean zzae(String s) {
        this.zzg();
        if(Wrappers.packageManager(this.zzt.zzaw()).checkCallingOrSelfPermission(s) == 0) {
            return true;
        }
        this.zzt.zzaA().zzc().zzb("Permission not granted", s);
        return false;
    }

    // 去混淆评级： 低(20)
    final boolean zzaf(String s) {
        return TextUtils.isEmpty(s) ? false : this.zzt.zzf().zzl().equals(s);
    }

    // 去混淆评级： 低(30)
    final boolean zzag(Object object0) {
        return object0 instanceof Parcelable[] || object0 instanceof ArrayList || object0 instanceof Bundle;
    }

    final boolean zzah(Context context0, String s) {
        X500Principal x500Principal0 = new X500Principal("CN=Android Debug,O=Android,C=US");
        try {
            PackageInfo packageInfo0 = Wrappers.packageManager(context0).getPackageInfo(s, 0x40);
            if(packageInfo0 != null && packageInfo0.signatures != null && packageInfo0.signatures.length > 0) {
                Signature signature0 = packageInfo0.signatures[0];
                return ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signature0.toByteArray()))).getSubjectX500Principal().equals(x500Principal0);
            }
        }
        catch(CertificateException certificateException0) {
            this.zzt.zzaA().zzd().zzb("Error obtaining certificate", certificateException0);
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            this.zzt.zzaA().zzd().zzb("Package name not found", packageManager$NameNotFoundException0);
        }
        return true;
    }

    public final boolean zzai(int v, boolean z) {
        Boolean boolean0 = this.zzt.zzt().zzj();
        return this.zzm() >= v / 1000 || boolean0 != null && !boolean0.booleanValue();
    }

    // 去混淆评级： 低(20)
    static boolean zzaj(String s) {
        return !TextUtils.isEmpty(s) && s.startsWith("_");
    }

    static boolean zzak(String s) {
        Preconditions.checkNotEmpty(s);
        return s.charAt(0) != 0x5F || s.equals("_ep");
    }

    static boolean zzal(Context context0) {
        Preconditions.checkNotNull(context0);
        try {
            PackageManager packageManager0 = context0.getPackageManager();
            if(packageManager0 == null) {
                return false;
            }
            ActivityInfo activityInfo0 = packageManager0.getReceiverInfo(new ComponentName(context0, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0);
            if(activityInfo0 != null) {
                return activityInfo0.enabled;
            }
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
        }
        return false;
    }

    static boolean zzam(Context context0, boolean z) {
        Preconditions.checkNotNull(context0);
        return Build.VERSION.SDK_INT < 24 ? zzlp.zzav(context0, "com.google.android.gms.measurement.AppMeasurementService") : zzlp.zzav(context0, "com.google.android.gms.measurement.AppMeasurementJobService");
    }

    // 去混淆评级： 低(30)
    public static boolean zzan(String s) {
        return !"_err".equals(s);
    }

    final boolean zzao(String s, String s1, String s2, String s3) {
        boolean z = TextUtils.isEmpty(s);
        boolean z1 = TextUtils.isEmpty(s1);
        if(!z && !z1) {
            Preconditions.checkNotNull(s);
            return !s.equals(s1);
        }
        if(z && z1) {
            return TextUtils.isEmpty(s2) || TextUtils.isEmpty(s3) ? !TextUtils.isEmpty(s3) : !s2.equals(s3);
        }
        return z || !TextUtils.isEmpty(s3) ? TextUtils.isEmpty(s2) || !s2.equals(s3) : false;
    }

    final byte[] zzap(Parcelable parcelable0) {
        if(parcelable0 == null) {
            return null;
        }
        Parcel parcel0 = Parcel.obtain();
        try {
            parcelable0.writeToParcel(parcel0, 0);
            return parcel0.marshall();
        }
        finally {
            parcel0.recycle();
        }
    }

    static final boolean zzaq(Bundle bundle0, int v) {
        if(bundle0 == null) {
            return false;
        }
        if(bundle0.getLong("_err") == 0L) {
            bundle0.putLong("_err", ((long)v));
            return true;
        }
        return false;
    }

    static final boolean zzar(String s) {
        Preconditions.checkNotNull(s);
        return s.matches("^(1:\\d+:android:[a-f0-9]+|ca-app-pub-.*)$");
    }

    private final int zzas(String s) {
        if("_ldl".equals(s)) {
            return 0x800;
        }
        if("_id".equals(s)) {
            return 0x100;
        }
        return "_lgclid".equals(s) ? 100 : 36;
    }

    private final Object zzat(int v, Object object0, boolean z, boolean z1) {
        if(object0 == null) {
            return null;
        }
        if(!(object0 instanceof Long) && !(object0 instanceof Double)) {
            if(object0 instanceof Integer) {
                return (long)(((int)(((Integer)object0))));
            }
            if(object0 instanceof Byte) {
                return (long)(((byte)(((Byte)object0))));
            }
            if(object0 instanceof Short) {
                return (long)(((short)(((Short)object0))));
            }
            if(object0 instanceof Boolean) {
                return ((Boolean)object0).booleanValue() ? 1L : 0L;
            }
            if(object0 instanceof Float) {
                return ((Float)object0).doubleValue();
            }
            if(!(object0 instanceof String) && !(object0 instanceof Character) && !(object0 instanceof CharSequence)) {
                if(z1 && (object0 instanceof Bundle[] || object0 instanceof Parcelable[])) {
                    ArrayList arrayList0 = new ArrayList();
                    for(int v1 = 0; v1 < ((Parcelable[])object0).length; ++v1) {
                        Parcelable parcelable0 = ((Parcelable[])object0)[v1];
                        if(parcelable0 instanceof Bundle) {
                            Bundle bundle0 = this.zzt(((Bundle)parcelable0));
                            if(!bundle0.isEmpty()) {
                                arrayList0.add(bundle0);
                            }
                        }
                    }
                    return arrayList0.toArray(new Bundle[arrayList0.size()]);
                }
                return null;
            }
            return this.zzD(object0.toString(), v, z);
        }
        return object0;
    }

    private static boolean zzau(String s, String[] arr_s) {
        Preconditions.checkNotNull(arr_s);
        for(int v = 0; v < arr_s.length; ++v) {
            if(zzln.zza(s, arr_s[v])) {
                return true;
            }
        }
        return false;
    }

    private static boolean zzav(Context context0, String s) {
        try {
            PackageManager packageManager0 = context0.getPackageManager();
            if(packageManager0 == null) {
                return false;
            }
            ServiceInfo serviceInfo0 = packageManager0.getServiceInfo(new ComponentName(context0, s), 0);
            if(serviceInfo0 != null) {
                return serviceInfo0.enabled;
            }
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
        }
        return false;
    }

    // 去混淆评级： 低(40)
    final int zzd(String s, Object object0) {
        return ("_ldl".equals(s) ? this.zzab("user property referrer", s, this.zzas(s), object0) : this.zzab("user property", s, this.zzas(s), object0)) ? 0 : 7;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgx
    protected final boolean zzf() {
        return true;
    }

    final int zzh(String s) {
        if(!this.zzac("event", s)) {
            return 2;
        }
        if(!this.zzaa("event", zzhc.zza, zzhc.zzb, s)) {
            return 13;
        }
        return this.zzZ("event", 40, s) ? 0 : 2;
    }

    final int zzi(String s) {
        if(!this.zzac("event param", s)) {
            return 3;
        }
        if(!this.zzaa("event param", null, null, s)) {
            return 14;
        }
        return this.zzZ("event param", 40, s) ? 0 : 3;
    }

    final int zzj(String s) {
        if(!this.zzad("event param", s)) {
            return 3;
        }
        if(!this.zzaa("event param", null, null, s)) {
            return 14;
        }
        return this.zzZ("event param", 40, s) ? 0 : 3;
    }

    final int zzl(String s) {
        if(!this.zzac("user property", s)) {
            return 6;
        }
        if(!this.zzaa("user property", zzhe.zza, null, s)) {
            return 15;
        }
        return this.zzZ("user property", 24, s) ? 0 : 6;
    }

    @EnsuresNonNull({"this.apkVersion"})
    public final int zzm() {
        if(this.zzf == null) {
            this.zzf = (int)(GoogleApiAvailabilityLight.getInstance().getApkVersion(this.zzt.zzaw()) / 1000);
        }
        return (int)this.zzf;
    }

    public final int zzo(int v) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(this.zzt.zzaw(), 12451000);
    }

    static long zzp(byte[] arr_b) {
        Preconditions.checkNotNull(arr_b);
        int v = 0;
        Preconditions.checkState(arr_b.length > 0);
        int v1 = arr_b.length - 1;
        long v2 = 0L;
        while(v1 >= 0 && v1 >= arr_b.length - 8) {
            v2 += (((long)arr_b[v1]) & 0xFFL) << v;
            v += 8;
            --v1;
        }
        return v2;
    }

    public final long zzq() {
        int v2;
        long v1;
        if(this.zzd.get() == 0L) {
            synchronized(this.zzd) {
                v1 = new Random(System.nanoTime() ^ this.zzt.zzax().currentTimeMillis()).nextLong();
                v2 = this.zze + 1;
                this.zze = v2;
            }
            return v1 + ((long)v2);
        }
        synchronized(this.zzd) {
            this.zzd.compareAndSet(-1L, 1L);
        }
        return this.zzd.getAndIncrement();
    }

    public final long zzr(long v, long v1) {
        return (v + v1 * 60000L) / 86400000L;
    }

    final Bundle zzs(Uri uri0, boolean z) {
        String s7;
        String s6;
        String s5;
        String s4;
        String s3;
        String s2;
        String s1;
        String s;
        if(uri0 != null) {
            try {
                if(uri0.isHierarchical()) {
                    s = uri0.getQueryParameter("utm_campaign");
                    s1 = uri0.getQueryParameter("utm_source");
                    s2 = uri0.getQueryParameter("utm_medium");
                    s3 = uri0.getQueryParameter("gclid");
                    s4 = uri0.getQueryParameter("utm_id");
                    s5 = uri0.getQueryParameter("dclid");
                    s6 = uri0.getQueryParameter("srsltid");
                    s7 = z ? uri0.getQueryParameter("sfmc_id") : null;
                }
                else {
                    s = null;
                    s1 = null;
                    s2 = null;
                    s3 = null;
                    s4 = null;
                    s5 = null;
                    s6 = null;
                    s7 = null;
                }
            }
            catch(UnsupportedOperationException unsupportedOperationException0) {
                this.zzt.zzaA().zzk().zzb("Install referrer url isn\'t a hierarchical URI", unsupportedOperationException0);
                return null;
            }
            if(TextUtils.isEmpty(s) && TextUtils.isEmpty(s1) && TextUtils.isEmpty(s2) && TextUtils.isEmpty(s3) && TextUtils.isEmpty(s4) && TextUtils.isEmpty(s5) && TextUtils.isEmpty(s6) && (!z || TextUtils.isEmpty(s7))) {
                return null;
            }
            Bundle bundle0 = new Bundle();
            if(!TextUtils.isEmpty(s)) {
                bundle0.putString("campaign", s);
            }
            if(!TextUtils.isEmpty(s1)) {
                bundle0.putString("source", s1);
            }
            if(!TextUtils.isEmpty(s2)) {
                bundle0.putString("medium", s2);
            }
            if(!TextUtils.isEmpty(s3)) {
                bundle0.putString("gclid", s3);
            }
            String s8 = uri0.getQueryParameter("utm_term");
            if(!TextUtils.isEmpty(s8)) {
                bundle0.putString("term", s8);
            }
            String s9 = uri0.getQueryParameter("utm_content");
            if(!TextUtils.isEmpty(s9)) {
                bundle0.putString("content", s9);
            }
            String s10 = uri0.getQueryParameter("aclid");
            if(!TextUtils.isEmpty(s10)) {
                bundle0.putString("aclid", s10);
            }
            String s11 = uri0.getQueryParameter("cp1");
            if(!TextUtils.isEmpty(s11)) {
                bundle0.putString("cp1", s11);
            }
            String s12 = uri0.getQueryParameter("anid");
            if(!TextUtils.isEmpty(s12)) {
                bundle0.putString("anid", s12);
            }
            if(!TextUtils.isEmpty(s4)) {
                bundle0.putString("campaign_id", s4);
            }
            if(!TextUtils.isEmpty(s5)) {
                bundle0.putString("dclid", s5);
            }
            String s13 = uri0.getQueryParameter("utm_source_platform");
            if(!TextUtils.isEmpty(s13)) {
                bundle0.putString("source_platform", s13);
            }
            String s14 = uri0.getQueryParameter("utm_creative_format");
            if(!TextUtils.isEmpty(s14)) {
                bundle0.putString("creative_format", s14);
            }
            String s15 = uri0.getQueryParameter("utm_marketing_tactic");
            if(!TextUtils.isEmpty(s15)) {
                bundle0.putString("marketing_tactic", s15);
            }
            if(!TextUtils.isEmpty(s6)) {
                bundle0.putString("srsltid", s6);
            }
            if(z && !TextUtils.isEmpty(s7)) {
                bundle0.putString("sfmc_id", s7);
            }
            return bundle0;
        }
        return null;
    }

    final Bundle zzt(Bundle bundle0) {
        Bundle bundle1 = new Bundle();
        if(bundle0 != null) {
            for(Object object0: bundle0.keySet()) {
                String s = (String)object0;
                Object object1 = this.zzA(s, bundle0.get(s));
                if(object1 == null) {
                    this.zzt.zzaA().zzl().zzb("Param value can\'t be null", this.zzt.zzj().zze(s));
                }
                else {
                    this.zzP(bundle1, s, object1);
                }
            }
        }
        return bundle1;
    }

    final Bundle zzu(String s, String s1, Bundle bundle0, List list0, boolean z) {
        String s3;
        int v2;
        boolean z1 = zzlp.zzau(s1, zzhc.zzd);
        if(bundle0 != null) {
            Bundle bundle1 = new Bundle(bundle0);
            int v = this.zzt.zzf().zzc();
            int v1 = 0;
            for(Object object0: new TreeSet(bundle0.keySet())) {
                String s2 = (String)object0;
                if(list0 == null || !list0.contains(s2)) {
                    int v3 = z ? 0 : this.zzj(s2);
                    v2 = v3 == 0 ? this.zzi(s2) : v3;
                }
                else {
                    v2 = 0;
                }
                switch(v2) {
                    case 0: {
                        int v4 = this.zza(s, s1, s2, bundle0.get(s2), bundle1, list0, z, z1);
                        if(v4 == 17) {
                            this.zzJ(bundle1, 17, s2, s2, Boolean.FALSE);
                        }
                        else if(v4 != 0 && !"_ev".equals(s2)) {
                            this.zzJ(bundle1, v4, (v4 == 21 ? s1 : s2), s2, bundle0.get(s2));
                            bundle1.remove(s2);
                            continue;
                        }
                        if(!zzlp.zzak(s2)) {
                            continue;
                        }
                        ++v1;
                        if(v1 <= v) {
                            continue;
                        }
                        this.zzt.zzaA().zze().zzc("Event can\'t contain more than " + v + " params", this.zzt.zzj().zzd(s1), this.zzt.zzj().zzb(bundle0));
                        zzlp.zzaq(bundle1, 5);
                        bundle1.remove(s2);
                        continue;
                    }
                    case 3: {
                        s3 = s2;
                        break;
                    }
                    default: {
                        s3 = null;
                    }
                }
                this.zzJ(bundle1, v2, s2, s2, s3);
                bundle1.remove(s2);
            }
            return bundle1;
        }
        return null;
    }

    final zzau zzz(String s, String s1, Bundle bundle0, String s2, long v, boolean z, boolean z1) {
        if(TextUtils.isEmpty(s1)) {
            return null;
        }
        if(this.zzh(s1) == 0) {
            Bundle bundle1 = bundle0 == null ? new Bundle() : new Bundle(bundle0);
            bundle1.putString("_o", s2);
            Bundle bundle2 = this.zzu(s, s1, bundle1, CollectionUtils.listOf("_o"), true);
            if(z) {
                bundle2 = this.zzt(bundle2);
            }
            Preconditions.checkNotNull(bundle2);
            return new zzau(s1, new zzas(bundle2), s2, v);
        }
        this.zzt.zzaA().zzd().zzb("Invalid conditional property event name", this.zzt.zzj().zzf(s1));
        throw new IllegalArgumentException();
    }
}

