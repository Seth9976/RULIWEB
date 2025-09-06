package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.measurement.zzff;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfw;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzga;
import com.google.android.gms.internal.measurement.zzgb;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzgl;
import com.google.android.gms.internal.measurement.zzgm;
import com.google.android.gms.internal.measurement.zzhf;
import com.google.android.gms.internal.measurement.zzhq;
import com.google.android.gms.internal.measurement.zzib;
import com.google.android.gms.internal.measurement.zzlb;
import com.google.android.gms.internal.measurement.zzop;
import com.google.android.gms.internal.measurement.zzpk;
import com.google.android.gms.internal.measurement.zzpn;
import com.google.android.gms.internal.measurement.zzpq;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrd;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.zip.GZIPInputStream;
import jeb.synthetic.TWR;

public final class zzlh implements zzgy {
    private long zzA;
    private final Map zzB;
    private final Map zzC;
    private zzir zzD;
    private String zzE;
    private final zzlo zzF;
    long zza;
    private static volatile zzlh zzb;
    private final zzfu zzc;
    private final zzez zzd;
    private zzak zze;
    private zzfb zzf;
    private zzks zzg;
    private zzaa zzh;
    private final zzlj zzi;
    private zzip zzj;
    private zzkb zzk;
    private final zzkw zzl;
    private zzfl zzm;
    private final zzgd zzn;
    private boolean zzo;
    private boolean zzp;
    private List zzq;
    private int zzr;
    private int zzs;
    private boolean zzt;
    private boolean zzu;
    private boolean zzv;
    private FileLock zzw;
    private FileChannel zzx;
    private List zzy;
    private List zzz;

    zzlh(zzli zzli0, zzgd zzgd0) {
        this.zzo = false;
        this.zzF = new zzlc(this);
        Preconditions.checkNotNull(zzli0);
        this.zzn = zzgd.zzp(zzli0.zza, null, null);
        this.zzA = -1L;
        this.zzl = new zzkw(this);
        zzlj zzlj0 = new zzlj(this);
        zzlj0.zzX();
        this.zzi = zzlj0;
        zzez zzez0 = new zzez(this);
        zzez0.zzX();
        this.zzd = zzez0;
        zzfu zzfu0 = new zzfu(this);
        zzfu0.zzX();
        this.zzc = zzfu0;
        this.zzB = new HashMap();
        this.zzC = new HashMap();
        this.zzaB().zzp(new zzkx(this, zzli0));
    }

    final void zzA() {
        this.zzaB().zzg();
        this.zzB();
        if(!this.zzp) {
            this.zzp = true;
            if(this.zzZ()) {
                FileChannel fileChannel0 = this.zzx;
                this.zzaB().zzg();
                int v = 0;
                if(fileChannel0 == null || !fileChannel0.isOpen()) {
                    this.zzaA().zzd().zza("Bad channel to read from");
                }
                else {
                    ByteBuffer byteBuffer0 = ByteBuffer.allocate(4);
                    try {
                        fileChannel0.position(0L);
                        int v1 = fileChannel0.read(byteBuffer0);
                        if(v1 == 4) {
                            byteBuffer0.flip();
                            v = byteBuffer0.getInt();
                        }
                        else if(v1 != -1) {
                            this.zzaA().zzk().zzb("Unexpected data length. Bytes read", v1);
                        }
                    }
                    catch(IOException iOException0) {
                        this.zzaA().zzd().zzb("Failed to read from channel", iOException0);
                    }
                }
                int v2 = this.zzn.zzh().zzi();
                this.zzaB().zzg();
                if(v > v2) {
                    this.zzaA().zzd().zzc("Panic: can\'t downgrade version. Previous, current version", v, v2);
                    return;
                }
                if(v < v2) {
                    FileChannel fileChannel1 = this.zzx;
                    this.zzaB().zzg();
                    if(fileChannel1 != null && fileChannel1.isOpen()) {
                        ByteBuffer byteBuffer1 = ByteBuffer.allocate(4);
                        byteBuffer1.putInt(v2);
                        byteBuffer1.flip();
                        try {
                            fileChannel1.truncate(0L);
                            fileChannel1.write(byteBuffer1);
                            fileChannel1.force(true);
                            if(fileChannel1.size() != 4L) {
                                this.zzaA().zzd().zzb("Error writing to channel. Bytes written", fileChannel1.size());
                            }
                        }
                        catch(IOException iOException1) {
                            this.zzaA().zzd().zzb("Failed to write to channel", iOException1);
                            this.zzaA().zzd().zzc("Storage version upgrade failed. Previous, current version", v, v2);
                            return;
                        }
                        this.zzaA().zzj().zzc("Storage version upgraded. Previous, current version", v, v2);
                        return;
                    }
                    this.zzaA().zzd().zza("Bad channel to read from");
                    this.zzaA().zzd().zzc("Storage version upgrade failed. Previous, current version", v, v2);
                }
            }
        }
    }

    final void zzB() {
        if(!this.zzo) {
            throw new IllegalStateException("UploadController is not initialized");
        }
    }

    final void zzC(String s, zzgc zzgc0) {
        zzlh.zzal(this.zzc);
        Set set0 = this.zzc.zzk(s);
        if(set0 != null) {
            zzgc0.zzi(set0);
        }
        zzlh.zzal(this.zzc);
        if(this.zzc.zzv(s)) {
            zzgc0.zzp();
        }
        zzlh.zzal(this.zzc);
        if(this.zzc.zzy(s) && !this.zzg().zzs(s, zzeg.zzar)) {
            zzgc0.zzu();
        }
        zzlh.zzal(this.zzc);
        if(this.zzc.zzz(s)) {
            int v = zzlj.zza(zzgc0, "_id");
            if(v != -1) {
                zzgc0.zzB(v);
            }
        }
        zzlh.zzal(this.zzc);
        if(this.zzc.zzx(s)) {
            zzgc0.zzq();
        }
        zzlh.zzal(this.zzc);
        if(this.zzc.zzu(s)) {
            zzgc0.zzn();
            zzlg zzlg0 = (zzlg)this.zzC.get(s);
            if(zzlg0 == null || zzlg0.zzb + this.zzg().zzi(s, zzeg.zzT) < this.zzax().elapsedRealtime()) {
                zzlg0 = new zzlg(this, null);
                this.zzC.put(s, zzlg0);
            }
            zzgc0.zzR(zzlg0.zza);
        }
        zzlh.zzal(this.zzc);
        if(this.zzc.zzw(s)) {
            zzgc0.zzy();
        }
    }

    final void zzD(zzh zzh0) {
        this.zzaB().zzg();
        if(TextUtils.isEmpty(zzh0.zzA()) && TextUtils.isEmpty(zzh0.zzt())) {
            this.zzI(((String)Preconditions.checkNotNull(zzh0.zzv())), 204, null, null, null);
            return;
        }
        zzkw zzkw0 = this.zzl;
        Uri.Builder uri$Builder0 = new Uri.Builder();
        String s = zzh0.zzA();
        if(TextUtils.isEmpty(s)) {
            s = zzh0.zzt();
        }
        ArrayMap arrayMap0 = null;
        Uri.Builder uri$Builder1 = uri$Builder0.scheme("https").encodedAuthority("app-measurement.com").path("config/app/" + s).appendQueryParameter("platform", "android");
        zzkw0.zzt.zzf().zzh();
        uri$Builder1.appendQueryParameter("gmp_version", "79000").appendQueryParameter("runtime_version", "0");
        String s1 = uri$Builder0.build().toString();
        try {
            Object object0 = Preconditions.checkNotNull(zzh0.zzv());
            URL uRL0 = new URL(s1);
            this.zzaA().zzj().zzb("Fetching remote configuration", ((String)object0));
            zzlh.zzal(this.zzc);
            zzff zzff0 = this.zzc.zze(((String)object0));
            zzlh.zzal(this.zzc);
            String s2 = this.zzc.zzh(((String)object0));
            if(zzff0 != null) {
                if(!TextUtils.isEmpty(s2)) {
                    ArrayMap arrayMap1 = new ArrayMap();
                    arrayMap1.put("If-Modified-Since", s2);
                    arrayMap0 = arrayMap1;
                }
                zzlh.zzal(this.zzc);
                String s3 = this.zzc.zzf(((String)object0));
                if(!TextUtils.isEmpty(s3)) {
                    if(arrayMap0 == null) {
                        arrayMap0 = new ArrayMap();
                    }
                    arrayMap0.put("If-None-Match", s3);
                }
            }
            this.zzt = true;
            zzlh.zzal(this.zzd);
            zzkz zzkz0 = new zzkz(this);
            this.zzd.zzg();
            this.zzd.zzW();
            Preconditions.checkNotNull(uRL0);
            Preconditions.checkNotNull(zzkz0);
            this.zzd.zzt.zzaB().zzo(new zzey(this.zzd, ((String)object0), uRL0, null, arrayMap0, zzkz0));
        }
        catch(MalformedURLException unused_ex) {
            this.zzaA().zzd().zzc("Failed to parse config URL. Not fetching. appId", zzet.zzn(zzh0.zzv()), s1);
        }
    }

    final void zzE(zzau zzau0, zzq zzq0) {
        List list3;
        List list2;
        List list1;
        Preconditions.checkNotNull(zzq0);
        Preconditions.checkNotEmpty(zzq0.zza);
        this.zzaB().zzg();
        this.zzB();
        String s = zzq0.zza;
        long v = zzau0.zzd;
        zzeu zzeu0 = zzeu.zzb(zzau0);
        this.zzaB().zzg();
        zzlp.zzK((this.zzD == null || (this.zzE == null || !this.zzE.equals(s)) ? null : this.zzD), zzeu0.zzd, false);
        zzau zzau1 = zzeu0.zza();
        zzlh.zzal(this.zzi);
        if(!zzlj.zzB(zzau1, zzq0)) {
            return;
        }
        if(!zzq0.zzh) {
            this.zzd(zzq0);
            return;
        }
        List list0 = zzq0.zzt;
        if(list0 != null) {
            if(list0.contains(zzau1.zza)) {
                Bundle bundle0 = zzau1.zzb.zzc();
                bundle0.putLong("ga_safelisted", 1L);
                zzas zzas0 = new zzas(bundle0);
                zzau1 = new zzau(zzau1.zza, zzas0, zzau1.zzc, zzau1.zzd);
                goto label_26;
            }
            this.zzaA().zzc().zzd("Dropping non-safelisted event. appId, event name, origin", s, zzau1.zza, zzau1.zzc);
            return;
        }
    label_26:
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        zzak0.zzw();
        try {
            zzak zzak1 = this.zze;
            zzlh.zzal(zzak1);
            Preconditions.checkNotEmpty(s);
            zzak1.zzg();
            zzak1.zzW();
            int v2 = Long.compare(v, 0L);
            if(v2 < 0) {
                zzak1.zzt.zzaA().zzk().zzc("Invalid time querying timed out conditional properties", zzet.zzn(s), v);
                list1 = Collections.EMPTY_LIST;
            }
            else {
                list1 = zzak1.zzt("active=0 and app_id=? and abs(? - creation_timestamp) > trigger_timeout", new String[]{s, String.valueOf(v)});
            }
            for(Object object0: list1) {
                zzac zzac0 = (zzac)object0;
                if(zzac0 != null) {
                    this.zzaA().zzj().zzd("User property timed out", zzac0.zza, this.zzn.zzj().zzf(zzac0.zzc.zzb), zzac0.zzc.zza());
                    zzau zzau2 = zzac0.zzg;
                    if(zzau2 != null) {
                        this.zzY(new zzau(zzau2, v), zzq0);
                    }
                    zzak zzak2 = this.zze;
                    zzlh.zzal(zzak2);
                    zzak2.zza(s, zzac0.zzc.zzb);
                }
            }
            zzak zzak3 = this.zze;
            zzlh.zzal(zzak3);
            Preconditions.checkNotEmpty(s);
            zzak3.zzg();
            zzak3.zzW();
            if(v2 < 0) {
                zzak3.zzt.zzaA().zzk().zzc("Invalid time querying expired conditional properties", zzet.zzn(s), v);
                list2 = Collections.EMPTY_LIST;
            }
            else {
                list2 = zzak3.zzt("active<>0 and app_id=? and abs(? - triggered_timestamp) > time_to_live", new String[]{s, String.valueOf(v)});
            }
            ArrayList arrayList0 = new ArrayList(list2.size());
            for(Object object1: list2) {
                zzac zzac1 = (zzac)object1;
                if(zzac1 != null) {
                    this.zzaA().zzj().zzd("User property expired", zzac1.zza, this.zzn.zzj().zzf(zzac1.zzc.zzb), zzac1.zzc.zza());
                    zzak zzak4 = this.zze;
                    zzlh.zzal(zzak4);
                    zzak4.zzA(s, zzac1.zzc.zzb);
                    zzau zzau3 = zzac1.zzk;
                    if(zzau3 != null) {
                        arrayList0.add(zzau3);
                    }
                    zzak zzak5 = this.zze;
                    zzlh.zzal(zzak5);
                    zzak5.zza(s, zzac1.zzc.zzb);
                }
            }
            for(Object object2: arrayList0) {
                this.zzY(new zzau(((zzau)object2), v), zzq0);
            }
            zzak zzak6 = this.zze;
            zzlh.zzal(zzak6);
            String s1 = zzau1.zza;
            Preconditions.checkNotEmpty(s);
            Preconditions.checkNotEmpty(s1);
            zzak6.zzg();
            zzak6.zzW();
            if(v2 < 0) {
                zzak6.zzt.zzaA().zzk().zzd("Invalid time querying triggered conditional properties", zzet.zzn(s), zzak6.zzt.zzj().zzd(s1), v);
                list3 = Collections.EMPTY_LIST;
            }
            else {
                list3 = zzak6.zzt("active=0 and app_id=? and trigger_event_name=? and abs(? - creation_timestamp) <= trigger_timeout", new String[]{s, s1, String.valueOf(v)});
            }
            ArrayList arrayList1 = new ArrayList(list3.size());
            for(Object object3: list3) {
                zzac zzac2 = (zzac)object3;
                if(zzac2 != null) {
                    zzlk zzlk0 = zzac2.zzc;
                    String s2 = (String)Preconditions.checkNotNull(zzac2.zza);
                    String s3 = zzac2.zzb;
                    Object object4 = Preconditions.checkNotNull(zzlk0.zza());
                    zzlm zzlm0 = new zzlm(s2, s3, zzlk0.zzb, v, object4);
                    zzak zzak7 = this.zze;
                    zzlh.zzal(zzak7);
                    if(zzak7.zzL(zzlm0)) {
                        this.zzaA().zzj().zzd("User property triggered", zzac2.zza, this.zzn.zzj().zzf(zzlm0.zzc), zzlm0.zze);
                    }
                    else {
                        this.zzaA().zzd().zzd("Too many active user properties, ignoring", zzet.zzn(zzac2.zza), this.zzn.zzj().zzf(zzlm0.zzc), zzlm0.zze);
                    }
                    zzau zzau4 = zzac2.zzi;
                    if(zzau4 != null) {
                        arrayList1.add(zzau4);
                    }
                    zzac2.zzc = new zzlk(zzlm0);
                    zzac2.zze = true;
                    zzak zzak8 = this.zze;
                    zzlh.zzal(zzak8);
                    zzak8.zzK(zzac2);
                }
            }
            this.zzY(zzau1, zzq0);
            for(Object object5: arrayList1) {
                this.zzY(new zzau(((zzau)object5), v), zzq0);
            }
            zzak zzak9 = this.zze;
            zzlh.zzal(zzak9);
            zzak9.zzC();
        }
        finally {
            zzak zzak10 = this.zze;
            zzlh.zzal(zzak10);
            zzak10.zzx();
        }
    }

    final void zzF(zzau zzau0, String s) {
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        zzh zzh0 = zzak0.zzj(s);
        if(zzh0 != null && !TextUtils.isEmpty(zzh0.zzy())) {
            Boolean boolean0 = this.zzad(zzh0);
            if(boolean0 != null) {
                if(!boolean0.booleanValue()) {
                    this.zzaA().zzd().zzb("App version does not match; dropping event. appId", zzet.zzn(s));
                    return;
                }
            }
            else if(!"_ui".equals(zzau0.zza)) {
                this.zzaA().zzk().zzb("Could not find package. appId", zzet.zzn(s));
            }
            String s1 = zzh0.zzA();
            String s2 = zzh0.zzy();
            long v = zzh0.zzb();
            String s3 = zzh0.zzx();
            long v1 = zzh0.zzm();
            long v2 = zzh0.zzj();
            boolean z = zzh0.zzan();
            String s4 = zzh0.zzz();
            zzh0.zza();
            this.zzG(zzau0, new zzq(s, s1, s2, v, s3, v1, v2, null, z, false, s4, 0L, 0L, 0, zzh0.zzam(), false, zzh0.zzt(), zzh0.zzs(), zzh0.zzk(), zzh0.zzE(), null, this.zzq(s).zzi(), "", null, zzh0.zzap(), zzh0.zzr()));
            return;
        }
        this.zzaA().zzc().zzb("No app data available; dropping event", s);
    }

    final void zzG(zzau zzau0, zzq zzq0) {
        Preconditions.checkNotEmpty(zzq0.zza);
        zzeu zzeu0 = zzeu.zzb(zzau0);
        zzlp zzlp0 = this.zzv();
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        Bundle bundle0 = zzak0.zzi(zzq0.zza);
        zzlp0.zzL(zzeu0.zzd, bundle0);
        this.zzv().zzN(zzeu0, this.zzg().zzd(zzq0.zza));
        zzau zzau1 = zzeu0.zza();
        if("_cmp".equals(zzau1.zza) && "referrer API v2".equals(zzau1.zzb.zzg("_cis"))) {
            String s = zzau1.zzb.zzg("gclid");
            if(!TextUtils.isEmpty(s)) {
                this.zzW(new zzlk("_lgclid", zzau1.zzd, s, "auto"), zzq0);
            }
        }
        this.zzE(zzau1, zzq0);
    }

    final void zzH() {
        ++this.zzs;
    }

    final void zzI(String s, int v, Throwable throwable0, byte[] arr_b, Map map0) {
        boolean z;
        this.zzaB().zzg();
        this.zzB();
        Preconditions.checkNotEmpty(s);
        try {
            if(arr_b == null) {
                arr_b = new byte[0];
            }
            zzer zzer0 = this.zzaA().zzj();
            Integer integer0 = (int)arr_b.length;
            zzer0.zzb("onConfigFetched. Response size", integer0);
            zzak zzak0 = this.zze;
            zzlh.zzal(zzak0);
            zzak0.zzw();
            try {
                zzak zzak1 = this.zze;
                zzlh.zzal(zzak1);
                zzh zzh0 = zzak1.zzj(s);
                switch(v) {
                    case 200: 
                    case 204: {
                        z = throwable0 == null;
                        break;
                    }
                    case 304: {
                        v = 304;
                        z = throwable0 == null;
                        break;
                    }
                    default: {
                        z = false;
                    }
                }
                if(zzh0 == null) {
                    this.zzaA().zzk().zzb("App does not exist in onConfigFetched. appId", zzet.zzn(s));
                }
                else if(z || v == 404) {
                    List list0 = map0 == null ? null : ((List)map0.get("Last-Modified"));
                    String s1 = list0 == null || list0.isEmpty() ? null : ((String)list0.get(0));
                    List list1 = map0 == null ? null : ((List)map0.get("ETag"));
                    String s2 = list1 == null || list1.isEmpty() ? null : ((String)list1.get(0));
                    if(v == 304 || v == 404) {
                        zzlh.zzal(this.zzc);
                        if(this.zzc.zze(s) == null) {
                            zzlh.zzal(this.zzc);
                            this.zzc.zzt(s, null, null, null);
                        }
                    }
                    else {
                        zzlh.zzal(this.zzc);
                        this.zzc.zzt(s, arr_b, s1, s2);
                    }
                    zzh0.zzN(this.zzax().currentTimeMillis());
                    zzak zzak3 = this.zze;
                    zzlh.zzal(zzak3);
                    zzak3.zzD(zzh0);
                    if(v == 404) {
                        this.zzaA().zzl().zzb("Config not found. Using empty config. appId", s);
                    }
                    else {
                        this.zzaA().zzj().zzc("Successfully fetched config. Got network response. code, size", v, integer0);
                    }
                    zzlh.zzal(this.zzd);
                    if(!this.zzd.zza() || !this.zzai()) {
                        this.zzag();
                    }
                    else {
                        this.zzX();
                    }
                }
                else {
                    zzh0.zzW(this.zzax().currentTimeMillis());
                    zzak zzak2 = this.zze;
                    zzlh.zzal(zzak2);
                    zzak2.zzD(zzh0);
                    this.zzaA().zzj().zzc("Fetching config failed. code, error", v, throwable0);
                    zzlh.zzal(this.zzc);
                    this.zzc.zzl(s);
                    this.zzk.zzd.zzb(this.zzax().currentTimeMillis());
                    if(v == 429 || v == 503) {
                        this.zzk.zzb.zzb(this.zzax().currentTimeMillis());
                    }
                    this.zzag();
                }
                zzak zzak4 = this.zze;
                zzlh.zzal(zzak4);
                zzak4.zzC();
            }
            finally {
                zzak zzak5 = this.zze;
                zzlh.zzal(zzak5);
                zzak5.zzx();
            }
        }
        finally {
            this.zzt = false;
            this.zzae();
        }
    }

    final void zzJ(boolean z) {
        this.zzag();
    }

    final void zzK(int v, Throwable throwable0, byte[] arr_b, String s) {
        this.zzaB().zzg();
        this.zzB();
        try {
            if(arr_b == null) {
                arr_b = new byte[0];
            }
            List list0 = (List)Preconditions.checkNotNull(this.zzy);
            this.zzy = null;
            if(v == 200) {
            label_10:
                if(throwable0 == null) {
                    try {
                        this.zzk.zzc.zzb(this.zzax().currentTimeMillis());
                        this.zzk.zzd.zzb(0L);
                        this.zzag();
                        this.zzaA().zzj().zzc("Successful upload. Got network response. code, size", v, ((int)arr_b.length));
                        zzak zzak0 = this.zze;
                        zzlh.zzal(zzak0);
                        zzak0.zzw();
                        try {
                            Iterator iterator0 = list0.iterator();
                            while(true) {
                                if(!iterator0.hasNext()) {
                                    zzak zzak1 = this.zze;
                                    zzlh.zzal(zzak1);
                                    zzak1.zzC();
                                    this.zzz = null;
                                    zzlh.zzal(this.zzd);
                                    if(!this.zzd.zza() || !this.zzai()) {
                                        this.zzA = -1L;
                                        this.zzag();
                                    }
                                    else {
                                        this.zzX();
                                    }
                                    this.zza = 0L;
                                    return;
                                }
                                Object object0 = iterator0.next();
                                Long long0 = (Long)object0;
                                try {
                                    zzak zzak3 = this.zze;
                                    zzlh.zzal(zzak3);
                                    zzak3.zzg();
                                    zzak3.zzW();
                                    SQLiteDatabase sQLiteDatabase0 = zzak3.zzh();
                                    String[] arr_s = {String.valueOf(((long)long0))};
                                    try {
                                        if(sQLiteDatabase0.delete("queue", "rowid=?", arr_s) != 1) {
                                            throw new SQLiteException("Deleted fewer rows from queue than expected");
                                        }
                                        continue;
                                    }
                                    catch(SQLiteException sQLiteException2) {
                                        zzak3.zzt.zzaA().zzd().zzb("Failed to delete a bundle in a queue table", sQLiteException2);
                                        throw sQLiteException2;
                                    }
                                }
                                catch(SQLiteException sQLiteException1) {
                                }
                                if(this.zzz == null || !this.zzz.contains(long0)) {
                                    break;
                                }
                            }
                        }
                        finally {
                            zzak zzak2 = this.zze;
                            zzlh.zzal(zzak2);
                            zzak2.zzx();
                        }
                        throw sQLiteException1;
                    }
                    catch(SQLiteException sQLiteException0) {
                        this.zzaA().zzd().zzb("Database error while trying to delete uploaded bundles", sQLiteException0);
                        this.zza = this.zzax().elapsedRealtime();
                        this.zzaA().zzj().zzb("Disable upload, time", this.zza);
                    }
                }
                else {
                label_62:
                    this.zzaA().zzj().zzc("Network upload failed. Will retry later. code, error", v, throwable0);
                    this.zzk.zzd.zzb(this.zzax().currentTimeMillis());
                    if(v == 429 || v == 503) {
                        this.zzk.zzb.zzb(this.zzax().currentTimeMillis());
                    }
                    zzak zzak4 = this.zze;
                    zzlh.zzal(zzak4);
                    zzak4.zzy(list0);
                    this.zzag();
                }
            }
            else if(v == 204) {
                v = 204;
                goto label_10;
            }
            else {
                goto label_62;
            }
        }
        finally {
            this.zzu = false;
            this.zzae();
        }
    }

    final void zzL(zzq zzq0) {
        int v8;
        PackageInfo packageInfo0;
        boolean z;
        zzaq zzaq0;
        int v4;
        this.zzaB().zzg();
        this.zzB();
        Preconditions.checkNotNull(zzq0);
        Preconditions.checkNotEmpty(zzq0.zza);
        if(zzlh.zzak(zzq0)) {
            zzak zzak0 = this.zze;
            zzlh.zzal(zzak0);
            zzh zzh0 = zzak0.zzj(zzq0.zza);
            if(zzh0 != null && TextUtils.isEmpty(zzh0.zzA()) && !TextUtils.isEmpty(zzq0.zzb)) {
                zzh0.zzN(0L);
                zzak zzak1 = this.zze;
                zzlh.zzal(zzak1);
                zzak1.zzD(zzh0);
                zzlh.zzal(this.zzc);
                this.zzc.zzm(zzq0.zza);
            }
            if(!zzq0.zzh) {
                this.zzd(zzq0);
                return;
            }
            long v = zzq0.zzm == 0L ? this.zzax().currentTimeMillis() : zzq0.zzm;
            this.zzn.zzg().zzd();
            int v1 = zzq0.zzn;
            if(v1 != 0 && v1 != 1) {
                this.zzaA().zzk().zzc("Incorrect app type, assuming installed app. appId, appType", zzet.zzn(zzq0.zza), v1);
                v1 = 0;
            }
            zzak zzak2 = this.zze;
            zzlh.zzal(zzak2);
            zzak2.zzw();
            try {
                zzak zzak3 = this.zze;
                zzlh.zzal(zzak3);
                zzlm zzlm0 = zzak3.zzp(zzq0.zza, "_npa");
                if(zzlm0 == null || "auto".equals(zzlm0.zzb)) {
                    if(zzq0.zzr != null) {
                        zzlk zzlk0 = new zzlk("_npa", v, ((long)(zzq0.zzr.booleanValue() ? 1L : 0L)), "auto");
                        if(zzlm0 == null || !zzlm0.zze.equals(zzlk0.zzd)) {
                            this.zzW(zzlk0, zzq0);
                        }
                    }
                    else if(zzlm0 != null) {
                        this.zzP("_npa", zzq0);
                    }
                }
                zzak zzak4 = this.zze;
                zzlh.zzal(zzak4);
                zzh zzh1 = zzak4.zzj(((String)Preconditions.checkNotNull(zzq0.zza)));
                if(zzh1 == null) {
                    v4 = v1;
                }
                else {
                    zzlp zzlp0 = this.zzv();
                    String s = zzh1.zzA();
                    String s1 = zzh1.zzt();
                    if(zzlp0.zzao(zzq0.zzb, s, zzq0.zzq, s1)) {
                        this.zzaA().zzk().zzb("New GMP App Id passed in. Removing cached database data. appId", zzet.zzn(zzh1.zzv()));
                        zzak zzak5 = this.zze;
                        zzlh.zzal(zzak5);
                        String s2 = zzh1.zzv();
                        zzak5.zzW();
                        zzak5.zzg();
                        Preconditions.checkNotEmpty(s2);
                        try {
                            SQLiteDatabase sQLiteDatabase0 = zzak5.zzh();
                            String[] arr_s = {s2};
                            int v3 = sQLiteDatabase0.delete("events", "app_id=?", arr_s) + sQLiteDatabase0.delete("user_attributes", "app_id=?", arr_s) + sQLiteDatabase0.delete("conditional_properties", "app_id=?", arr_s) + sQLiteDatabase0.delete("apps", "app_id=?", arr_s) + sQLiteDatabase0.delete("raw_events", "app_id=?", arr_s) + sQLiteDatabase0.delete("raw_events_metadata", "app_id=?", arr_s) + sQLiteDatabase0.delete("event_filters", "app_id=?", arr_s) + sQLiteDatabase0.delete("property_filters", "app_id=?", arr_s) + sQLiteDatabase0.delete("audience_filter_values", "app_id=?", arr_s) + sQLiteDatabase0.delete("consent_settings", "app_id=?", arr_s);
                            zzpk.zzc();
                            v4 = v1;
                            if(zzak5.zzt.zzf().zzs(null, zzeg.zzat)) {
                                v3 += sQLiteDatabase0.delete("default_event_params", "app_id=?", arr_s);
                            }
                            if(v3 > 0) {
                                zzak5.zzt.zzaA().zzj().zzc("Deleted application data. app, records", s2, v3);
                            }
                        }
                        catch(SQLiteException sQLiteException0) {
                            zzak5.zzt.zzaA().zzd().zzc("Error deleting application data. appId, error", zzet.zzn(s2), sQLiteException0);
                        }
                        zzh1 = null;
                    }
                    else {
                        v4 = v1;
                    }
                }
                if(zzh1 != null) {
                    int v5 = zzh1.zzb() == 0xFFFFFFFF80000000L || zzh1.zzb() == zzq0.zzj ? 0 : 1;
                    String s3 = zzh1.zzy();
                    if((v5 | (zzh1.zzb() != 0xFFFFFFFF80000000L || s3 == null || s3.equals(zzq0.zzc) ? 0 : 1)) != 0) {
                        Bundle bundle0 = new Bundle();
                        bundle0.putString("_pv", s3);
                        this.zzE(new zzau("_au", new zzas(bundle0), "auto", v), zzq0);
                    }
                }
                this.zzd(zzq0);
                if(v4 == 0) {
                    zzak zzak6 = this.zze;
                    zzlh.zzal(zzak6);
                    zzaq0 = zzak6.zzn(zzq0.zza, "_f");
                    z = false;
                }
                else {
                    zzak zzak7 = this.zze;
                    zzlh.zzal(zzak7);
                    zzaq0 = zzak7.zzn(zzq0.zza, "_v");
                    z = true;
                }
                if(zzaq0 == null) {
                    long v6 = (v / 3600000L + 1L) * 3600000L;
                    if(z) {
                        this.zzW(new zzlk("_fvt", v, v6, "auto"), zzq0);
                        this.zzaB().zzg();
                        this.zzB();
                        Bundle bundle2 = new Bundle();
                        bundle2.putLong("_c", 1L);
                        bundle2.putLong("_r", 1L);
                        bundle2.putLong("_et", 1L);
                        if(zzq0.zzp) {
                            bundle2.putLong("_dac", 1L);
                        }
                        this.zzG(new zzau("_v", new zzas(bundle2), "auto", v), zzq0);
                    }
                    else {
                        this.zzW(new zzlk("_fot", v, v6, "auto"), zzq0);
                        this.zzaB().zzg();
                        zzfl zzfl0 = (zzfl)Preconditions.checkNotNull(this.zzm);
                        String s4 = zzq0.zza;
                        if(s4 == null || s4.isEmpty()) {
                            zzfl0.zza.zzaA().zzm().zza("Install Referrer Reporter was called with invalid app package name");
                        }
                        else {
                            zzfl0.zza.zzaB().zzg();
                            if(zzfl0.zza()) {
                                zzfk zzfk0 = new zzfk(zzfl0, s4);
                                zzfl0.zza.zzaB().zzg();
                                Intent intent0 = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
                                intent0.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
                                PackageManager packageManager0 = zzfl0.zza.zzaw().getPackageManager();
                                if(packageManager0 == null) {
                                    zzfl0.zza.zzaA().zzm().zza("Failed to obtain Package Manager to verify binding conditions for Install Referrer");
                                }
                                else {
                                    List list0 = packageManager0.queryIntentServices(intent0, 0);
                                    if(list0 == null || list0.isEmpty()) {
                                        zzfl0.zza.zzaA().zzi().zza("Play Service for fetching Install Referrer is unavailable on device");
                                    }
                                    else {
                                        ResolveInfo resolveInfo0 = (ResolveInfo)list0.get(0);
                                        if(resolveInfo0.serviceInfo != null) {
                                            if(resolveInfo0.serviceInfo.name == null || !"com.android.vending".equals(resolveInfo0.serviceInfo.packageName) || !zzfl0.zza()) {
                                                zzfl0.zza.zzaA().zzk().zza("Play Store version 8.3.73 or higher required for Install Referrer");
                                            }
                                            else {
                                                Intent intent1 = new Intent(intent0);
                                                try {
                                                    boolean z1 = ConnectionTracker.getInstance().bindService(zzfl0.zza.zzaw(), intent1, zzfk0, 1);
                                                    zzfl0.zza.zzaA().zzj().zzb("Install Referrer Service is", (z1 ? "available" : "not available"));
                                                }
                                                catch(RuntimeException runtimeException0) {
                                                    zzfl0.zza.zzaA().zzd().zzb("Exception occurred while binding to Install Referrer Service", runtimeException0.getMessage());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            else {
                                zzfl0.zza.zzaA().zzi().zza("Install Referrer Reporter is not available");
                            }
                        }
                        this.zzaB().zzg();
                        this.zzB();
                        Bundle bundle1 = new Bundle();
                        bundle1.putLong("_c", 1L);
                        bundle1.putLong("_r", 1L);
                        bundle1.putLong("_uwa", 0L);
                        bundle1.putLong("_pfo", 0L);
                        bundle1.putLong("_sys", 0L);
                        bundle1.putLong("_sysu", 0L);
                        bundle1.putLong("_et", 1L);
                        if(zzq0.zzp) {
                            bundle1.putLong("_dac", 1L);
                        }
                        String s5 = (String)Preconditions.checkNotNull(zzq0.zza);
                        zzak zzak8 = this.zze;
                        zzlh.zzal(zzak8);
                        Preconditions.checkNotEmpty(s5);
                        zzak8.zzg();
                        zzak8.zzW();
                        long v7 = zzak8.zzc(s5, "first_open_count");
                        if(this.zzn.zzaw().getPackageManager() == null) {
                            this.zzaA().zzd().zzb("PackageManager is null, first open report might be inaccurate. appId", zzet.zzn(s5));
                        }
                        else {
                            try {
                                packageInfo0 = Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(s5, 0);
                            }
                            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                                this.zzaA().zzd().zzc("Package info is null, first open report might be inaccurate. appId", zzet.zzn(s5), packageManager$NameNotFoundException0);
                                packageInfo0 = null;
                            }
                            ApplicationInfo applicationInfo0 = null;
                            if(packageInfo0 != null && packageInfo0.firstInstallTime != 0L) {
                                if(packageInfo0.firstInstallTime == packageInfo0.lastUpdateTime) {
                                    v8 = 1;
                                }
                                else if(!this.zzg().zzs(null, zzeg.zzad)) {
                                    bundle1.putLong("_uwa", 1L);
                                    v8 = 0;
                                }
                                else if(v7 == 0L) {
                                    bundle1.putLong("_uwa", 1L);
                                    v8 = 0;
                                    v7 = 0L;
                                }
                                else {
                                    v8 = 0;
                                }
                                this.zzW(new zzlk("_fi", v, ((long)(1 == v8 ? 1L : 0L)), "auto"), zzq0);
                            }
                            try {
                                applicationInfo0 = Wrappers.packageManager(this.zzn.zzaw()).getApplicationInfo(s5, 0);
                            }
                            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException1) {
                                this.zzaA().zzd().zzc("Application info is null, first open report might be inaccurate. appId", zzet.zzn(s5), packageManager$NameNotFoundException1);
                            }
                            if(applicationInfo0 != null) {
                                if((applicationInfo0.flags & 1) != 0) {
                                    bundle1.putLong("_sys", 1L);
                                }
                                if((applicationInfo0.flags & 0x80) != 0) {
                                    bundle1.putLong("_sysu", 1L);
                                }
                            }
                        }
                        if(v7 >= 0L) {
                            bundle1.putLong("_pfo", v7);
                        }
                        this.zzG(new zzau("_f", new zzas(bundle1), "auto", v), zzq0);
                    }
                }
                else if(zzq0.zzi) {
                    this.zzG(new zzau("_cd", new zzas(new Bundle()), "auto", v), zzq0);
                }
                zzak zzak9 = this.zze;
                zzlh.zzal(zzak9);
                zzak9.zzC();
            }
            finally {
                zzak zzak10 = this.zze;
                zzlh.zzal(zzak10);
                zzak10.zzx();
            }
        }
    }

    final void zzM() {
        ++this.zzr;
    }

    final void zzN(zzac zzac0) {
        zzq zzq0 = this.zzac(((String)Preconditions.checkNotNull(zzac0.zza)));
        if(zzq0 != null) {
            this.zzO(zzac0, zzq0);
        }
    }

    final void zzO(zzac zzac0, zzq zzq0) {
        Preconditions.checkNotNull(zzac0);
        Preconditions.checkNotEmpty(zzac0.zza);
        Preconditions.checkNotNull(zzac0.zzc);
        Preconditions.checkNotEmpty(zzac0.zzc.zzb);
        this.zzaB().zzg();
        this.zzB();
        if(!zzlh.zzak(zzq0)) {
            return;
        }
        if(zzq0.zzh) {
            zzak zzak0 = this.zze;
            zzlh.zzal(zzak0);
            zzak0.zzw();
            try {
                this.zzd(zzq0);
                Object object0 = Preconditions.checkNotNull(zzac0.zza);
                zzak zzak1 = this.zze;
                zzlh.zzal(zzak1);
                zzac zzac1 = zzak1.zzk(((String)object0), zzac0.zzc.zzb);
                if(zzac1 == null) {
                    this.zzaA().zzk().zzc("Conditional user property doesn\'t exist", zzet.zzn(zzac0.zza), this.zzn.zzj().zzf(zzac0.zzc.zzb));
                }
                else {
                    this.zzaA().zzc().zzc("Removing conditional user property", zzac0.zza, this.zzn.zzj().zzf(zzac0.zzc.zzb));
                    zzak zzak2 = this.zze;
                    zzlh.zzal(zzak2);
                    zzak2.zza(((String)object0), zzac0.zzc.zzb);
                    if(zzac1.zze) {
                        zzak zzak3 = this.zze;
                        zzlh.zzal(zzak3);
                        zzak3.zzA(((String)object0), zzac0.zzc.zzb);
                    }
                    zzau zzau0 = zzac0.zzk;
                    if(zzau0 != null) {
                        Bundle bundle0 = zzau0.zzb == null ? null : zzau0.zzb.zzc();
                        this.zzY(((zzau)Preconditions.checkNotNull(this.zzv().zzz(((String)object0), ((zzau)Preconditions.checkNotNull(zzac0.zzk)).zza, bundle0, zzac1.zzb, zzac0.zzk.zzd, true, true))), zzq0);
                    }
                }
                zzak zzak4 = this.zze;
                zzlh.zzal(zzak4);
                zzak4.zzC();
            }
            finally {
                zzak zzak5 = this.zze;
                zzlh.zzal(zzak5);
                zzak5.zzx();
            }
            return;
        }
        this.zzd(zzq0);
    }

    final void zzP(String s, zzq zzq0) {
        this.zzaB().zzg();
        this.zzB();
        if(!zzlh.zzak(zzq0)) {
            return;
        }
        if(!zzq0.zzh) {
            this.zzd(zzq0);
            return;
        }
        if("_npa".equals(s) && zzq0.zzr != null) {
            this.zzaA().zzc().zza("Falling back to manifest metadata value for ad personalization");
            this.zzW(new zzlk("_npa", this.zzax().currentTimeMillis(), ((long)(zzq0.zzr.booleanValue() ? 1L : 0L)), "auto"), zzq0);
            return;
        }
        this.zzaA().zzc().zzb("Removing user property", this.zzn.zzj().zzf(s));
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        zzak0.zzw();
        try {
            this.zzd(zzq0);
            if("_id".equals(s)) {
                zzak zzak1 = this.zze;
                zzlh.zzal(zzak1);
                zzak1.zzA(((String)Preconditions.checkNotNull(zzq0.zza)), "_lair");
            }
            zzak zzak2 = this.zze;
            zzlh.zzal(zzak2);
            zzak2.zzA(((String)Preconditions.checkNotNull(zzq0.zza)), s);
            zzak zzak3 = this.zze;
            zzlh.zzal(zzak3);
            zzak3.zzC();
            this.zzaA().zzc().zzb("User property removed", this.zzn.zzj().zzf(s));
        }
        finally {
            zzak zzak4 = this.zze;
            zzlh.zzal(zzak4);
            zzak4.zzx();
        }
    }

    final void zzQ(zzq zzq0) {
        if(this.zzy != null) {
            ArrayList arrayList0 = new ArrayList();
            this.zzz = arrayList0;
            arrayList0.addAll(this.zzy);
        }
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        String s = (String)Preconditions.checkNotNull(zzq0.zza);
        Preconditions.checkNotEmpty(s);
        zzak0.zzg();
        zzak0.zzW();
        try {
            SQLiteDatabase sQLiteDatabase0 = zzak0.zzh();
            String[] arr_s = {s};
            int v = sQLiteDatabase0.delete("apps", "app_id=?", arr_s) + sQLiteDatabase0.delete("events", "app_id=?", arr_s) + sQLiteDatabase0.delete("user_attributes", "app_id=?", arr_s) + sQLiteDatabase0.delete("conditional_properties", "app_id=?", arr_s) + sQLiteDatabase0.delete("raw_events", "app_id=?", arr_s) + sQLiteDatabase0.delete("raw_events_metadata", "app_id=?", arr_s) + sQLiteDatabase0.delete("queue", "app_id=?", arr_s) + sQLiteDatabase0.delete("audience_filter_values", "app_id=?", arr_s) + sQLiteDatabase0.delete("main_event_params", "app_id=?", arr_s) + sQLiteDatabase0.delete("default_event_params", "app_id=?", arr_s);
            if(v > 0) {
                zzak0.zzt.zzaA().zzj().zzc("Reset analytics data. app, records", s, v);
            }
        }
        catch(SQLiteException sQLiteException0) {
            zzak0.zzt.zzaA().zzd().zzc("Error resetting analytics data. appId, error", zzet.zzn(s), sQLiteException0);
        }
        if(zzq0.zzh) {
            this.zzL(zzq0);
        }
    }

    public final void zzR(String s, zzir zzir0) {
        this.zzaB().zzg();
        if(this.zzE != null && !this.zzE.equals(s) && zzir0 == null) {
            return;
        }
        this.zzE = s;
        this.zzD = zzir0;
    }

    protected final void zzS() {
        this.zzaB().zzg();
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        zzak0.zzz();
        if(this.zzk.zzc.zza() == 0L) {
            this.zzk.zzc.zzb(this.zzax().currentTimeMillis());
        }
        this.zzag();
    }

    final void zzT(zzac zzac0) {
        zzq zzq0 = this.zzac(((String)Preconditions.checkNotNull(zzac0.zza)));
        if(zzq0 != null) {
            this.zzU(zzac0, zzq0);
        }
    }

    final void zzU(zzac zzac0, zzq zzq0) {
        Preconditions.checkNotNull(zzac0);
        Preconditions.checkNotEmpty(zzac0.zza);
        Preconditions.checkNotNull(zzac0.zzb);
        Preconditions.checkNotNull(zzac0.zzc);
        Preconditions.checkNotEmpty(zzac0.zzc.zzb);
        this.zzaB().zzg();
        this.zzB();
        if(!zzlh.zzak(zzq0)) {
            return;
        }
        if(!zzq0.zzh) {
            this.zzd(zzq0);
            return;
        }
        zzac zzac1 = new zzac(zzac0);
        boolean z = false;
        zzac1.zze = false;
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        zzak0.zzw();
        try {
            zzak zzak1 = this.zze;
            zzlh.zzal(zzak1);
            zzac zzac2 = zzak1.zzk(((String)Preconditions.checkNotNull(zzac1.zza)), zzac1.zzc.zzb);
            if(zzac2 != null && !zzac2.zzb.equals(zzac1.zzb)) {
                this.zzaA().zzk().zzd("Updating a conditional user property with different origin. name, origin, origin (from DB)", this.zzn.zzj().zzf(zzac1.zzc.zzb), zzac1.zzb, zzac2.zzb);
            }
            if(zzac2 != null && zzac2.zze) {
                zzac1.zzb = zzac2.zzb;
                zzac1.zzd = zzac2.zzd;
                zzac1.zzh = zzac2.zzh;
                zzac1.zzf = zzac2.zzf;
                zzac1.zzi = zzac2.zzi;
                zzac1.zze = true;
                zzac1.zzc = new zzlk(zzac1.zzc.zzb, zzac2.zzc.zzc, zzac1.zzc.zza(), zzac2.zzc.zzf);
            }
            else if(TextUtils.isEmpty(zzac1.zzf)) {
                zzac1.zzc = new zzlk(zzac1.zzc.zzb, zzac1.zzd, zzac1.zzc.zza(), zzac1.zzc.zzf);
                zzac1.zze = true;
                z = true;
            }
            if(zzac1.zze) {
                zzlk zzlk0 = zzac1.zzc;
                String s = (String)Preconditions.checkNotNull(zzac1.zza);
                String s1 = zzac1.zzb;
                Object object0 = Preconditions.checkNotNull(zzlk0.zza());
                zzlm zzlm0 = new zzlm(s, s1, zzlk0.zzb, zzlk0.zzc, object0);
                zzak zzak2 = this.zze;
                zzlh.zzal(zzak2);
                if(zzak2.zzL(zzlm0)) {
                    this.zzaA().zzc().zzd("User property updated immediately", zzac1.zza, this.zzn.zzj().zzf(zzlm0.zzc), zzlm0.zze);
                }
                else {
                    this.zzaA().zzd().zzd("(2)Too many active user properties, ignoring", zzet.zzn(zzac1.zza), this.zzn.zzj().zzf(zzlm0.zzc), zzlm0.zze);
                }
                if(z && zzac1.zzi != null) {
                    this.zzY(new zzau(zzac1.zzi, zzac1.zzd), zzq0);
                }
            }
            zzak zzak3 = this.zze;
            zzlh.zzal(zzak3);
            if(zzak3.zzK(zzac1)) {
                this.zzaA().zzc().zzd("Conditional property added", zzac1.zza, this.zzn.zzj().zzf(zzac1.zzc.zzb), zzac1.zzc.zza());
            }
            else {
                this.zzaA().zzd().zzd("Too many conditional properties, ignoring", zzet.zzn(zzac1.zza), this.zzn.zzj().zzf(zzac1.zzc.zzb), zzac1.zzc.zza());
            }
            zzak zzak4 = this.zze;
            zzlh.zzal(zzak4);
            zzak4.zzC();
        }
        finally {
            zzak zzak5 = this.zze;
            zzlh.zzal(zzak5);
            zzak5.zzx();
        }
    }

    final void zzV(String s, zzhb zzhb0) {
        this.zzaB().zzg();
        this.zzB();
        this.zzB.put(s, zzhb0);
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        Preconditions.checkNotNull(s);
        Preconditions.checkNotNull(zzhb0);
        zzak0.zzg();
        zzak0.zzW();
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("app_id", s);
        contentValues0.put("consent_state", zzhb0.zzi());
        try {
            if(zzak0.zzh().insertWithOnConflict("consent_settings", null, contentValues0, 5) == -1L) {
                zzak0.zzt.zzaA().zzd().zzb("Failed to insert/update consent setting (got -1). appId", zzet.zzn(s));
            }
        }
        catch(SQLiteException sQLiteException0) {
            zzak0.zzt.zzaA().zzd().zzc("Error storing consent setting. appId, error", zzet.zzn(s), sQLiteException0);
        }
    }

    final void zzW(zzlk zzlk0, zzq zzq0) {
        long v3;
        this.zzaB().zzg();
        this.zzB();
        if(zzlh.zzak(zzq0)) {
            if(!zzq0.zzh) {
                this.zzd(zzq0);
                return;
            }
            int v = this.zzv().zzl(zzlk0.zzb);
            if(v != 0) {
                zzlp zzlp0 = this.zzv();
                this.zzg();
                this.zzv().zzO(this.zzF, zzq0.zza, v, "_ev", zzlp0.zzD(zzlk0.zzb, 24, true), (zzlk0.zzb == null ? 0 : zzlk0.zzb.length()));
                return;
            }
            zzlp zzlp1 = this.zzv();
            Object object0 = zzlk0.zza();
            int v1 = zzlp1.zzd(zzlk0.zzb, object0);
            if(v1 != 0) {
                zzlp zzlp2 = this.zzv();
                this.zzg();
                Object object1 = zzlk0.zza();
                this.zzv().zzO(this.zzF, zzq0.zza, v1, "_ev", zzlp2.zzD(zzlk0.zzb, 24, true), (object1 == null || !(object1 instanceof String) && !(object1 instanceof CharSequence) ? 0 : object1.toString().length()));
                return;
            }
            zzlp zzlp3 = this.zzv();
            Object object2 = zzlk0.zza();
            Object object3 = zzlp3.zzB(zzlk0.zzb, object2);
            if(object3 != null) {
                if("_sid".equals(zzlk0.zzb)) {
                    long v2 = zzlk0.zzc;
                    String s = zzlk0.zzf;
                    String s1 = (String)Preconditions.checkNotNull(zzq0.zza);
                    zzak zzak0 = this.zze;
                    zzlh.zzal(zzak0);
                    zzlm zzlm0 = zzak0.zzp(s1, "_sno");
                    if(zzlm0 == null) {
                    label_37:
                        if(zzlm0 != null) {
                            this.zzaA().zzk().zzb("Retrieved last session number from database does not contain a valid (long) value", zzlm0.zze);
                        }
                        zzak zzak1 = this.zze;
                        zzlh.zzal(zzak1);
                        zzaq zzaq0 = zzak1.zzn(s1, "_s");
                        if(zzaq0 == null) {
                            v3 = 0L;
                        }
                        else {
                            v3 = zzaq0.zzc;
                            this.zzaA().zzj().zzb("Backfill the session number. Last used session number", v3);
                        }
                    }
                    else {
                        Object object4 = zzlm0.zze;
                        if(object4 instanceof Long) {
                            v3 = (long)(((Long)object4));
                            goto label_47;
                        }
                        goto label_37;
                    }
                label_47:
                    this.zzW(new zzlk("_sno", v2, ((long)(v3 + 1L)), s), zzq0);
                }
                zzlm zzlm1 = new zzlm(((String)Preconditions.checkNotNull(zzq0.zza)), ((String)Preconditions.checkNotNull(zzlk0.zzf)), zzlk0.zzb, zzlk0.zzc, object3);
                this.zzaA().zzj().zzc("Setting user property", this.zzn.zzj().zzf(zzlm1.zzc), object3);
                zzak zzak2 = this.zze;
                zzlh.zzal(zzak2);
                zzak2.zzw();
                try {
                    if("_id".equals(zzlm1.zzc)) {
                        zzak zzak3 = this.zze;
                        zzlh.zzal(zzak3);
                        zzlm zzlm2 = zzak3.zzp(zzq0.zza, "_id");
                        if(zzlm2 != null && !zzlm1.zze.equals(zzlm2.zze)) {
                            zzak zzak4 = this.zze;
                            zzlh.zzal(zzak4);
                            zzak4.zzA(zzq0.zza, "_lair");
                        }
                    }
                    this.zzd(zzq0);
                    zzak zzak5 = this.zze;
                    zzlh.zzal(zzak5);
                    boolean z = zzak5.zzL(zzlm1);
                    if(this.zzg().zzs(null, zzeg.zzaH) && "_sid".equals(zzlk0.zzb)) {
                        zzlh.zzal(this.zzi);
                        long v5 = this.zzi.zzd(zzq0.zzx);
                        zzak zzak6 = this.zze;
                        zzlh.zzal(zzak6);
                        zzh zzh0 = zzak6.zzj(zzq0.zza);
                        if(zzh0 != null) {
                            zzh0.zzaj(v5);
                            if(zzh0.zzao()) {
                                zzak zzak7 = this.zze;
                                zzlh.zzal(zzak7);
                                zzak7.zzD(zzh0);
                            }
                        }
                    }
                    zzak zzak8 = this.zze;
                    zzlh.zzal(zzak8);
                    zzak8.zzC();
                    if(!z) {
                        this.zzaA().zzd().zzc("Too many unique user properties are set. Ignoring user property", this.zzn.zzj().zzf(zzlm1.zzc), zzlm1.zze);
                        this.zzv().zzO(this.zzF, zzq0.zza, 9, null, null, 0);
                    }
                }
                finally {
                    zzak zzak9 = this.zze;
                    zzlh.zzal(zzak9);
                    zzak9.zzx();
                }
            }
        }
    }

    final void zzX() {
        String s1;
        List list0;
        zzgc zzgc0;
        byte[] arr_b2;
        int v11;
        byte[] arr_b1;
        ByteArrayOutputStream byteArrayOutputStream0;
        zzlj zzlj0;
        byte[] arr_b;
        long v10;
        int v9;
        ArrayList arrayList0;
        Cursor cursor4;
        Cursor cursor3;
        Cursor cursor2;
        Cursor cursor1;
        String s2;
        Cursor cursor6;
        this.zzaB().zzg();
        this.zzB();
        this.zzv = true;
        try {
            Boolean boolean0 = this.zzn.zzt().zzj();
            if(boolean0 == null) {
                this.zzaA().zzk().zza("Upload data called on the client side before use of service was decided");
            }
            else if(boolean0.booleanValue()) {
                this.zzaA().zzd().zza("Upload called in the client side when service should be used");
            }
            else if(this.zza > 0L) {
                this.zzag();
            }
            else {
                this.zzaB().zzg();
                if(this.zzy == null) {
                    zzlh.zzal(this.zzd);
                    if(this.zzd.zza()) {
                        long v1 = this.zzax().currentTimeMillis();
                        Cursor cursor0 = null;
                        int v2 = this.zzg().zze(null, zzeg.zzR);
                        this.zzg();
                        long v3 = zzag.zzz();
                        for(int v4 = 0; v4 < v2 && this.zzah(null, v1 - v3); ++v4) {
                        }
                        long v5 = this.zzk.zzc.zza();
                        if(v5 != 0L) {
                            this.zzaA().zzc().zzb("Uploading events. Elapsed time since last upload attempt (ms)", Math.abs(v1 - v5));
                        }
                        zzak zzak0 = this.zze;
                        zzlh.zzal(zzak0);
                        String s = zzak0.zzr();
                        long v6 = -1L;
                        if(TextUtils.isEmpty(s)) {
                            this.zzA = -1L;
                            zzak zzak3 = this.zze;
                            zzlh.zzal(zzak3);
                            this.zzg();
                            long v14 = zzag.zzz();
                            zzak3.zzg();
                            zzak3.zzW();
                            try {
                                cursor6 = null;
                                cursor6 = zzak3.zzh().rawQuery("select app_id from apps where app_id in (select distinct app_id from raw_events) and config_fetched_time < ? order by failed_config_fetch_time limit 1;", new String[]{String.valueOf(v1 - v14)});
                                goto label_247;
                            }
                            catch(SQLiteException sQLiteException2) {
                                goto label_253;
                            }
                            catch(Throwable throwable2) {
                            }
                            Cursor cursor7 = null;
                            goto label_260;
                            try {
                                try {
                                label_247:
                                    if(cursor6.moveToFirst()) {
                                        s2 = cursor6.getString(0);
                                        goto label_264;
                                    }
                                    else {
                                        zzak3.zzt.zzaA().zzj().zza("No expired configs for apps with pending events");
                                        goto label_255;
                                    }
                                }
                                catch(SQLiteException sQLiteException2) {
                                label_253:
                                    zzak3.zzt.zzaA().zzd().zzb("Error selecting expired configs", sQLiteException2);
                                    if(cursor6 != null) {
                                        goto label_255;
                                    }
                                    s2 = null;
                                }
                                goto label_265;
                            }
                            catch(Throwable throwable2) {
                                goto label_259;
                            }
                        label_255:
                            cursor6.close();
                            s2 = null;
                            goto label_265;
                        label_259:
                            cursor7 = cursor6;
                        label_260:
                            if(cursor7 != null) {
                                cursor7.close();
                            }
                            throw throwable2;
                        label_264:
                            cursor6.close();
                        label_265:
                            if(!TextUtils.isEmpty(s2)) {
                                zzak zzak4 = this.zze;
                                zzlh.zzal(zzak4);
                                zzh zzh0 = zzak4.zzj(s2);
                                if(zzh0 != null) {
                                    this.zzD(zzh0);
                                }
                            }
                        }
                        else {
                            if(this.zzA == -1L) {
                                zzak zzak1 = this.zze;
                                zzlh.zzal(zzak1);
                                try {
                                    cursor1 = null;
                                    cursor1 = zzak1.zzh().rawQuery("select rowid from raw_events order by rowid desc limit 1;", null);
                                    goto label_48;
                                }
                                catch(SQLiteException sQLiteException0) {
                                    goto label_52;
                                }
                                catch(Throwable throwable0) {
                                }
                                goto label_58;
                                try {
                                    try {
                                    label_48:
                                        if(cursor1.moveToFirst()) {
                                            v6 = cursor1.getLong(0);
                                        }
                                        goto label_62;
                                    }
                                    catch(SQLiteException sQLiteException0) {
                                    }
                                label_52:
                                    zzak1.zzt.zzaA().zzd().zzb("Error querying raw events", sQLiteException0);
                                    if(cursor1 != null) {
                                        goto label_54;
                                    }
                                    goto label_63;
                                }
                                catch(Throwable throwable0) {
                                    goto label_57;
                                }
                            label_54:
                                cursor1.close();
                                goto label_63;
                            label_57:
                                cursor0 = cursor1;
                            label_58:
                                if(cursor0 != null) {
                                    cursor0.close();
                                }
                                throw throwable0;
                            label_62:
                                cursor1.close();
                            label_63:
                                this.zzA = v6;
                            }
                            int v7 = this.zzg().zze(s, zzeg.zzg);
                            int v8 = Math.max(0, this.zzg().zze(s, zzeg.zzh));
                            zzak zzak2 = this.zze;
                            zzlh.zzal(zzak2);
                            zzak2.zzg();
                            zzak2.zzW();
                            Preconditions.checkArgument(v7 > 0);
                            Preconditions.checkArgument(v8 > 0);
                            Preconditions.checkNotEmpty(s);
                            try {
                                cursor2 = zzak2.zzh().query("queue", new String[]{"rowid", "data", "retry_count"}, "app_id=?", new String[]{s}, null, null, "rowid", String.valueOf(v7));
                            }
                            catch(SQLiteException sQLiteException1) {
                                cursor3 = null;
                                goto label_148;
                            }
                            catch(Throwable throwable1) {
                                cursor4 = null;
                                goto label_160;
                            }
                            try {
                                if(cursor2.moveToFirst()) {
                                    arrayList0 = new ArrayList();
                                    v9 = 0;
                                    while(true) {
                                    label_84:
                                        v10 = cursor2.getLong(0);
                                        try {
                                            arr_b = cursor2.getBlob(1);
                                            zzlj0 = zzak2.zzf.zzi;
                                            zzlh.zzal(zzlj0);
                                            goto label_92;
                                        }
                                        catch(IOException iOException0) {
                                        }
                                        goto label_90;
                                    }
                                }
                                else {
                                    list0 = Collections.EMPTY_LIST;
                                    goto label_164;
                                }
                                goto label_165;
                            }
                            catch(SQLiteException sQLiteException1) {
                                goto label_146;
                            }
                            catch(Throwable throwable1) {
                                goto label_158;
                            }
                        label_90:
                            Cursor cursor5 = cursor2;
                            goto label_131;
                            try {
                            label_92:
                                ByteArrayInputStream byteArrayInputStream0 = new ByteArrayInputStream(arr_b);
                                GZIPInputStream gZIPInputStream0 = new GZIPInputStream(byteArrayInputStream0);
                                byteArrayOutputStream0 = new ByteArrayOutputStream();
                                arr_b1 = new byte[0x400];
                                ByteArrayInputStream byteArrayInputStream1 = byteArrayInputStream0;
                                while(true) {
                                label_97:
                                    v11 = gZIPInputStream0.read(arr_b1);
                                    if(v11 <= 0) {
                                        gZIPInputStream0.close();
                                        byteArrayInputStream1.close();
                                        arr_b2 = byteArrayOutputStream0.toByteArray();
                                        goto label_106;
                                    }
                                    goto label_123;
                                }
                            }
                            catch(IOException iOException1) {
                                cursor5 = cursor2;
                                goto label_128;
                            label_106:
                                if(!arrayList0.isEmpty() && arr_b2.length + v9 > v8) {
                                    cursor5 = cursor2;
                                    goto label_135;
                                }
                                try {
                                    try {
                                        zzgc0 = (zzgc)zzlj.zzm(com.google.android.gms.internal.measurement.zzgd.zzu(), arr_b2);
                                    }
                                    catch(IOException iOException2) {
                                        zzak2.zzt.zzaA().zzd().zzc("Failed to merge queued bundle. appId", zzet.zzn(s), iOException2);
                                        cursor5 = cursor2;
                                        goto label_132;
                                    }
                                    if(!cursor2.isNull(2)) {
                                        zzgc0.zzaf(cursor2.getInt(2));
                                    }
                                    v9 += arr_b2.length;
                                    arrayList0.add(Pair.create(((com.google.android.gms.internal.measurement.zzgd)zzgc0.zzaD()), v10));
                                    cursor5 = cursor2;
                                    goto label_132;
                                }
                                catch(SQLiteException sQLiteException1) {
                                    cursor5 = cursor2;
                                    goto label_147;
                                }
                                catch(Throwable throwable1) {
                                    goto label_158;
                                }
                            }
                            catch(SQLiteException sQLiteException1) {
                                cursor5 = cursor2;
                                goto label_147;
                                try {
                                label_123:
                                    cursor5 = cursor2;
                                    byteArrayOutputStream0.write(arr_b1, 0, v11);
                                    cursor2 = cursor5;
                                    goto label_97;
                                }
                                catch(IOException iOException1) {
                                    try {
                                    label_128:
                                        zzlj0.zzt.zzaA().zzd().zzb("Failed to ungzip content", iOException1);
                                        throw iOException1;
                                    }
                                    catch(IOException iOException0) {
                                    }
                                    catch(SQLiteException sQLiteException1) {
                                        goto label_147;
                                    }
                                    catch(Throwable throwable1) {
                                        goto label_159;
                                    }
                                    try {
                                    label_131:
                                        zzak2.zzt.zzaA().zzd().zzc("Failed to unzip queued bundle. appId", zzet.zzn(s), iOException0);
                                    label_132:
                                        if(!cursor5.moveToNext() || v9 > v8) {
                                            goto label_135;
                                        }
                                        else {
                                            cursor2 = cursor5;
                                            goto label_84;
                                        }
                                        list0 = arrayList0;
                                        goto label_165;
                                    }
                                    catch(SQLiteException sQLiteException1) {
                                        goto label_147;
                                    }
                                    catch(Throwable throwable1) {
                                        goto label_159;
                                    }
                                    try {
                                        cursor2 = cursor5;
                                        goto label_84;
                                    }
                                    catch(SQLiteException sQLiteException1) {
                                        goto label_146;
                                    }
                                    catch(Throwable throwable1) {
                                        goto label_158;
                                    }
                                label_135:
                                    if(cursor5 != null) {
                                        cursor5.close();
                                    }
                                    list0 = arrayList0;
                                    goto label_165;
                                }
                                catch(SQLiteException sQLiteException1) {
                                    goto label_147;
                                }
                                catch(Throwable throwable1) {
                                    goto label_159;
                                }
                                try {
                                    list0 = Collections.EMPTY_LIST;
                                    goto label_164;
                                }
                                catch(SQLiteException sQLiteException1) {
                                }
                                catch(Throwable throwable1) {
                                    goto label_158;
                                }
                            label_146:
                                cursor5 = cursor2;
                            label_147:
                                cursor3 = cursor5;
                                try {
                                label_148:
                                    zzak2.zzt.zzaA().zzd().zzc("Error querying bundles. appId", zzet.zzn(s), sQLiteException1);
                                    list0 = Collections.EMPTY_LIST;
                                }
                                catch(Throwable throwable1) {
                                    cursor4 = cursor3;
                                    goto label_160;
                                }
                                if(cursor3 != null) {
                                    cursor3.close();
                                }
                                goto label_165;
                            }
                            catch(Throwable throwable1) {
                            label_158:
                                cursor5 = cursor2;
                            }
                        label_159:
                            cursor4 = cursor5;
                        label_160:
                            if(cursor4 != null) {
                                cursor4.close();
                            }
                            throw throwable1;
                        label_164:
                            cursor2.close();
                        label_165:
                            if(!list0.isEmpty()) {
                                if(this.zzq(s).zzj(zzha.zza)) {
                                    for(Object object0: list0) {
                                        com.google.android.gms.internal.measurement.zzgd zzgd0 = (com.google.android.gms.internal.measurement.zzgd)((Pair)object0).first;
                                    }
                                }
                                zzga zzga0 = zzgb.zza();
                                int v12 = list0.size();
                                ArrayList arrayList1 = new ArrayList(list0.size());
                                boolean z = this.zzg().zzt(s) && this.zzq(s).zzj(zzha.zza);
                                boolean z1 = this.zzq(s).zzj(zzha.zza);
                                boolean z2 = this.zzq(s).zzj(zzha.zzb);
                                zzqu.zzc();
                                boolean z3 = this.zzg().zzs(s, zzeg.zzao);
                                for(int v13 = 0; v13 < v12; ++v13) {
                                    zzgc zzgc1 = (zzgc)((com.google.android.gms.internal.measurement.zzgd)((Pair)list0.get(v13)).first).zzbB();
                                    arrayList1.add(((Long)((Pair)list0.get(v13)).second));
                                    this.zzg().zzh();
                                    zzgc1.zzam(79000L);
                                    zzgc1.zzal(v1);
                                    zzgc1.zzag(false);
                                    if(!z) {
                                        zzgc1.zzq();
                                    }
                                    if(!z1) {
                                        zzgc1.zzx();
                                        zzgc1.zzt();
                                    }
                                    if(!z2) {
                                        zzgc1.zzn();
                                    }
                                    this.zzC(s, zzgc1);
                                    if(!z3) {
                                        zzgc1.zzy();
                                    }
                                    if(this.zzg().zzs(s, zzeg.zzV)) {
                                        byte[] arr_b3 = ((com.google.android.gms.internal.measurement.zzgd)zzgc1.zzaD()).zzbx();
                                        zzlh.zzal(this.zzi);
                                        zzgc1.zzJ(this.zzi.zzf(arr_b3));
                                    }
                                    zzga0.zza(zzgc1);
                                }
                                if(Log.isLoggable(this.zzaA().zzr(), 2)) {
                                    zzlh.zzal(this.zzi);
                                    zzgb zzgb0 = (zzgb)zzga0.zzaD();
                                    s1 = this.zzi.zzo(zzgb0);
                                }
                                else {
                                    s1 = null;
                                }
                                zzlh.zzal(this.zzi);
                                byte[] arr_b4 = ((zzgb)zzga0.zzaD()).zzbx();
                                zzkv zzkv0 = this.zzl.zza(s);
                                try {
                                    Preconditions.checkArgument(!arrayList1.isEmpty());
                                    if(this.zzy == null) {
                                        this.zzy = new ArrayList(arrayList1);
                                    }
                                    else {
                                        this.zzaA().zzd().zza("Set uploading progress before finishing the previous upload");
                                    }
                                    this.zzk.zzd.zzb(v1);
                                    this.zzaA().zzj().zzd("Uploading data. app, uncompressed size, data", (v12 <= 0 ? "?" : ""), ((int)arr_b4.length), s1);
                                    this.zzu = true;
                                    zzlh.zzal(this.zzd);
                                    URL uRL0 = new URL(zzkv0.zza());
                                    zzky zzky0 = new zzky(this, s);
                                    this.zzd.zzg();
                                    this.zzd.zzW();
                                    Preconditions.checkNotNull(uRL0);
                                    Preconditions.checkNotNull(arr_b4);
                                    Preconditions.checkNotNull(zzky0);
                                    this.zzd.zzt.zzaB().zzo(new zzey(this.zzd, s, uRL0, arr_b4, zzkv0.zzb(), zzky0));
                                }
                                catch(MalformedURLException unused_ex) {
                                    this.zzaA().zzd().zzc("Failed to parse upload URL. Not uploading. appId", zzet.zzn(s), zzkv0.zza());
                                }
                            }
                        }
                    }
                    else {
                        this.zzaA().zzj().zza("Network not connected, ignoring upload request");
                        this.zzag();
                    }
                }
                else {
                    this.zzaA().zzj().zza("Uploading requested multiple times");
                }
            }
        }
        finally {
            this.zzv = false;
            this.zzae();
        }
    }

    final void zzY(zzau zzau0, zzq zzq0) {
        int v22;
        long v21;
        Iterable iterable0;
        zzap zzap1;
        zzaq zzaq1;
        long v15;
        long v9;
        zzlm zzlm1;
        long v5;
        Preconditions.checkNotNull(zzq0);
        Preconditions.checkNotEmpty(zzq0.zza);
        long v = System.nanoTime();
        this.zzaB().zzg();
        this.zzB();
        String s = zzq0.zza;
        zzlh.zzal(this.zzi);
        if(zzlj.zzB(zzau0, zzq0)) {
            if(!zzq0.zzh) {
                this.zzd(zzq0);
                return;
            }
            zzlh.zzal(this.zzc);
            if(!this.zzc.zzr(s, zzau0.zza)) {
                goto label_30;
            }
            this.zzaA().zzk().zzc("Dropping blocked event. appId", zzet.zzn(s), this.zzn.zzj().zzd(zzau0.zza));
            zzlh.zzal(this.zzc);
            if(this.zzc.zzp(s)) {
            label_19:
                zzak zzak0 = this.zze;
                zzlh.zzal(zzak0);
                zzh zzh0 = zzak0.zzj(s);
                if(zzh0 != null) {
                    long v1 = Math.max(zzh0.zzl(), zzh0.zzc());
                    long v2 = Math.abs(this.zzax().currentTimeMillis() - v1);
                    this.zzg();
                    if(v2 > ((long)(((Long)zzeg.zzz.zza(null))))) {
                        this.zzaA().zzc().zza("Fetching config for blocked app");
                        this.zzD(zzh0);
                        return;
                    label_30:
                        zzeu zzeu0 = zzeu.zzb(zzau0);
                        this.zzv().zzN(zzeu0, this.zzg().zzd(s));
                        zzpq.zzc();
                        int v3 = this.zzg().zzs(null, zzeg.zzaA) ? this.zzg().zzf(s, zzeg.zzQ, 10, 35) : 0;
                        for(Object object0: new TreeSet(zzeu0.zzd.keySet())) {
                            String s1 = (String)object0;
                            if("items".equals(s1)) {
                                zzlp zzlp0 = this.zzv();
                                Parcelable[] arr_parcelable = zzeu0.zzd.getParcelableArray(s1);
                                zzpq.zzc();
                                zzlp0.zzM(arr_parcelable, v3, this.zzg().zzs(null, zzeg.zzaA));
                            }
                        }
                        zzau zzau1 = zzeu0.zza();
                        if(Log.isLoggable(this.zzaA().zzr(), 2)) {
                            this.zzaA().zzj().zzb("Logging event", this.zzn.zzj().zzc(zzau1));
                        }
                        zzpn.zzc();
                        this.zzg().zzs(null, zzeg.zzax);
                        zzak zzak1 = this.zze;
                        zzlh.zzal(zzak1);
                        zzak1.zzw();
                        try {
                            this.zzd(zzq0);
                            boolean z = "ecommerce_purchase".equals(zzau1.zza) || ("purchase".equals(zzau1.zza) || "refund".equals(zzau1.zza));
                            if("_iap".equals(zzau1.zza)) {
                            label_58:
                                String s2 = zzau1.zzb.zzg("currency");
                                if(z) {
                                    double f = ((double)zzau1.zzb.zzd("value")) * 1000000.0;
                                    if(f == 0.0) {
                                        f = ((double)(((long)zzau1.zzb.zze("value")))) * 1000000.0;
                                    }
                                    if(f > 9223372036854776000.0 || f < -9223372036854776000.0) {
                                        goto label_68;
                                    }
                                    v5 = Math.round(f);
                                    if("refund".equals(zzau1.zza)) {
                                        v5 = -v5;
                                        goto label_75;
                                    label_68:
                                        this.zzaA().zzk().zzc("Data lost. Currency value is too big. appId", zzet.zzn(s), f);
                                        zzak zzak2 = this.zze;
                                        zzlh.zzal(zzak2);
                                        zzak2.zzC();
                                        return;
                                    }
                                }
                                else {
                                    v5 = (long)zzau1.zzb.zze("value");
                                }
                            label_75:
                                if(!TextUtils.isEmpty(s2)) {
                                    String s3 = s2.toUpperCase(Locale.US);
                                    if(s3.matches("[A-Z]{3}")) {
                                        zzak zzak3 = this.zze;
                                        zzlh.zzal(zzak3);
                                        zzlm zzlm0 = zzak3.zzp(s, "_ltv_" + s3);
                                        if(zzlm0 == null) {
                                        label_87:
                                            zzak zzak4 = this.zze;
                                            zzlh.zzal(zzak4);
                                            int v7 = this.zzg().zze(s, zzeg.zzE);
                                            Preconditions.checkNotEmpty(s);
                                            zzak4.zzg();
                                            zzak4.zzW();
                                            try {
                                                zzak4.zzh().execSQL("delete from user_attributes where app_id=? and name in (select name from user_attributes where app_id=? and name like \'_ltv_%\' order by set_timestamp desc limit ?,10);", new String[]{s, s, String.valueOf(v7 - 1)});
                                            }
                                            catch(SQLiteException sQLiteException0) {
                                                zzak4.zzt.zzaA().zzd().zzc("Error pruning currencies. appId", zzet.zzn(s), sQLiteException0);
                                            }
                                            long v8 = this.zzax().currentTimeMillis();
                                            zzlm1 = new zzlm(s, zzau1.zzc, "_ltv_" + s3, v8, v5);
                                        }
                                        else {
                                            Object object1 = zzlm0.zze;
                                            if(object1 instanceof Long) {
                                                long v6 = this.zzax().currentTimeMillis();
                                                zzlm1 = new zzlm(s, zzau1.zzc, "_ltv_" + s3, v6, ((long)(((long)(((Long)object1))) + v5)));
                                                goto label_99;
                                            }
                                            goto label_87;
                                        }
                                    label_99:
                                        zzak zzak5 = this.zze;
                                        zzlh.zzal(zzak5);
                                        if(!zzak5.zzL(zzlm1)) {
                                            this.zzaA().zzd().zzd("Too many unique user properties are set. Ignoring user property. appId", zzet.zzn(s), this.zzn.zzj().zzf(zzlm1.zzc), zzlm1.zze);
                                            this.zzv().zzO(this.zzF, s, 9, null, null, 0);
                                        }
                                    }
                                }
                            }
                            else if(z) {
                                z = true;
                                goto label_58;
                            }
                            boolean z1 = zzlp.zzak(zzau1.zza);
                            boolean z2 = "_err".equals(zzau1.zza);
                            this.zzv();
                            zzas zzas0 = zzau1.zzb;
                            if(zzas0 == null) {
                                v9 = 0L;
                            }
                            else {
                                v9 = 0L;
                                zzar zzar0 = new zzar(zzas0);
                                while(zzar0.hasNext()) {
                                    Object object2 = zzas0.zzf(zzar0.zza());
                                    if(object2 instanceof Parcelable[]) {
                                        v9 += (long)((Parcelable[])object2).length;
                                    }
                                }
                            }
                            zzak zzak6 = this.zze;
                            zzlh.zzal(zzak6);
                            zzai zzai0 = zzak6.zzm(this.zza(), s, v9 + 1L, true, z1, false, z2, false);
                            long v10 = zzai0.zzb;
                            this.zzg();
                            long v11 = v10 - ((long)(((int)(((Integer)zzeg.zzk.zza(null))))));
                            if(v11 > 0L) {
                                if(v11 % 1000L == 1L) {
                                    this.zzaA().zzd().zzc("Data loss. Too many events logged. appId, count", zzet.zzn(s), zzai0.zzb);
                                }
                                zzak zzak7 = this.zze;
                                zzlh.zzal(zzak7);
                                zzak7.zzC();
                                return;
                            }
                            if(z1) {
                                long v12 = zzai0.zza;
                                this.zzg();
                                long v13 = v12 - ((long)(((int)(((Integer)zzeg.zzm.zza(null))))));
                                if(v13 > 0L) {
                                    if(v13 % 1000L == 1L) {
                                        this.zzaA().zzd().zzc("Data loss. Too many public events logged. appId, count", zzet.zzn(s), zzai0.zza);
                                    }
                                    this.zzv().zzO(this.zzF, s, 16, "_ev", zzau1.zza, 0);
                                    zzak zzak8 = this.zze;
                                    zzlh.zzal(zzak8);
                                    zzak8.zzC();
                                    return;
                                }
                            }
                            if(z2) {
                                long v14 = zzai0.zzd - ((long)Math.max(0, Math.min(1000000, this.zzg().zze(zzq0.zza, zzeg.zzl))));
                                if(v14 > 0L) {
                                    if(v14 == 1L) {
                                        this.zzaA().zzd().zzc("Too many error events logged. appId, count", zzet.zzn(s), zzai0.zzd);
                                    }
                                    zzak zzak9 = this.zze;
                                    zzlh.zzal(zzak9);
                                    zzak9.zzC();
                                    return;
                                }
                            }
                            Bundle bundle0 = zzau1.zzb.zzc();
                            this.zzv().zzP(bundle0, "_o", zzau1.zzc);
                            if(this.zzv().zzaf(s)) {
                                this.zzv().zzP(bundle0, "_dbg", 1L);
                                this.zzv().zzP(bundle0, "_r", 1L);
                            }
                            if("_s".equals(zzau1.zza)) {
                                zzak zzak10 = this.zze;
                                zzlh.zzal(zzak10);
                                zzlm zzlm2 = zzak10.zzp(zzq0.zza, "_sno");
                                if(zzlm2 != null && zzlm2.zze instanceof Long) {
                                    this.zzv().zzP(bundle0, "_sno", zzlm2.zze);
                                }
                            }
                            zzak zzak11 = this.zze;
                            zzlh.zzal(zzak11);
                            Preconditions.checkNotEmpty(s);
                            zzak11.zzg();
                            zzak11.zzW();
                            try {
                                v15 = (long)zzak11.zzh().delete("raw_events", "rowid in (select rowid from raw_events where app_id=? order by rowid desc limit -1 offset ?)", new String[]{s, String.valueOf(Math.max(0, Math.min(1000000, zzak11.zzt.zzf().zze(s, zzeg.zzp))))});
                            }
                            catch(SQLiteException sQLiteException1) {
                                zzak11.zzt.zzaA().zzd().zzc("Error deleting over the limit events. appId", zzet.zzn(s), sQLiteException1);
                                v15 = 0L;
                            }
                            if(v15 > 0L) {
                                this.zzaA().zzk().zzc("Data lost. Too many events stored on disk, deleted. appId", zzet.zzn(s), v15);
                            }
                            zzap zzap0 = new zzap(this.zzn, zzau1.zzc, s, zzau1.zza, zzau1.zzd, 0L, bundle0);
                            zzak zzak12 = this.zze;
                            zzlh.zzal(zzak12);
                            zzaq zzaq0 = zzak12.zzn(s, zzap0.zzb);
                            if(zzaq0 == null) {
                                zzak zzak13 = this.zze;
                                zzlh.zzal(zzak13);
                                if(zzak13.zzf(s) >= ((long)this.zzg().zzb(s)) && z1) {
                                    this.zzaA().zzd().zzd("Too many event names used, ignoring event. appId, name, supported count", zzet.zzn(s), this.zzn.zzj().zzd(zzap0.zzb), this.zzg().zzb(s));
                                    this.zzv().zzO(this.zzF, s, 8, null, null, 0);
                                    return;
                                }
                                zzaq1 = new zzaq(s, zzap0.zzb, 0L, 0L, 0L, zzap0.zzd, 0L, null, null, null, null);
                                zzap1 = zzap0;
                            }
                            else {
                                zzap zzap2 = zzap0.zza(this.zzn, zzaq0.zzf);
                                zzap1 = zzap2;
                                zzaq1 = zzaq0.zzc(zzap2.zzd);
                            }
                            zzak zzak14 = this.zze;
                            zzlh.zzal(zzak14);
                            zzak14.zzE(zzaq1);
                            this.zzaB().zzg();
                            this.zzB();
                            Preconditions.checkNotNull(zzap1);
                            Preconditions.checkNotNull(zzq0);
                            Preconditions.checkNotEmpty(zzap1.zza);
                            Preconditions.checkArgument(zzap1.zza.equals(zzq0.zza));
                            zzgc zzgc0 = com.google.android.gms.internal.measurement.zzgd.zzu();
                            zzgc0.zzad(1);
                            zzgc0.zzZ("android");
                            if(!TextUtils.isEmpty(zzq0.zza)) {
                                zzgc0.zzD(zzq0.zza);
                            }
                            if(!TextUtils.isEmpty(zzq0.zzd)) {
                                zzgc0.zzF(zzq0.zzd);
                            }
                            if(!TextUtils.isEmpty(zzq0.zzc)) {
                                zzgc0.zzG(zzq0.zzc);
                            }
                            zzqu.zzc();
                            if(!TextUtils.isEmpty(zzq0.zzx) && (this.zzg().zzs(null, zzeg.zzam) || this.zzg().zzs(zzq0.zza, zzeg.zzao))) {
                                zzgc0.zzah(zzq0.zzx);
                            }
                            long v16 = zzq0.zzj;
                            if(v16 != 0xFFFFFFFF80000000L) {
                                zzgc0.zzH(((int)v16));
                            }
                            zzgc0.zzV(zzq0.zze);
                            if(!TextUtils.isEmpty(zzq0.zzb)) {
                                zzgc0.zzU(zzq0.zzb);
                            }
                            zzgc0.zzL(this.zzq(((String)Preconditions.checkNotNull(zzq0.zza))).zzd(zzhb.zzc(zzq0.zzv, 100)).zzi());
                            if(!TextUtils.isEmpty(zzq0.zzq)) {
                                zzgc0.zzC(zzq0.zzq);
                            }
                            long v17 = zzq0.zzf;
                            if(v17 != 0L) {
                                zzgc0.zzM(v17);
                            }
                            zzgc0.zzP(zzq0.zzs);
                            zzlj zzlj0 = this.zzi;
                            zzlh.zzal(zzlj0);
                            zzhf zzhf0 = zzhf.zza(zzlj0.zzf.zzn.zzaw().getContentResolver(), zzhq.zza("com.google.android.gms.measurement"), () -> zzib.zzc());
                            Map map0 = zzhf0 == null ? Collections.EMPTY_MAP : zzhf0.zzc();
                            if(map0 == null || map0.isEmpty()) {
                                iterable0 = null;
                            }
                            else {
                                ArrayList arrayList0 = new ArrayList();
                                int v18 = (int)(((Integer)zzeg.zzP.zza(null)));
                                for(Object object3: map0.entrySet()) {
                                    Map.Entry map$Entry0 = (Map.Entry)object3;
                                    if(((String)map$Entry0.getKey()).startsWith("measurement.id.")) {
                                        try {
                                            int v19 = Integer.parseInt(((String)map$Entry0.getValue()));
                                            if(v19 == 0) {
                                                continue;
                                            }
                                            arrayList0.add(v19);
                                            if(arrayList0.size() < v18) {
                                                continue;
                                            }
                                            zzlj0.zzt.zzaA().zzk().zzb("Too many experiment IDs. Number of IDs", arrayList0.size());
                                            break;
                                        }
                                        catch(NumberFormatException numberFormatException0) {
                                            zzlj0.zzt.zzaA().zzk().zzb("Experiment ID NumberFormatException", numberFormatException0);
                                        }
                                    }
                                }
                                iterable0 = arrayList0.isEmpty() ? null : arrayList0;
                            }
                            if(iterable0 != null) {
                                zzgc0.zzh(iterable0);
                            }
                            zzhb zzhb0 = this.zzq(((String)Preconditions.checkNotNull(zzq0.zza))).zzd(zzhb.zzc(zzq0.zzv, 100));
                            if(zzhb0.zzj(zzha.zza) && zzq0.zzo) {
                                Pair pair0 = this.zzk.zzd(zzq0.zza, zzhb0);
                                if(!TextUtils.isEmpty(((CharSequence)pair0.first)) && zzq0.zzo) {
                                    zzgc0.zzae(((String)pair0.first));
                                    if(pair0.second != null) {
                                        zzgc0.zzX(((Boolean)pair0.second).booleanValue());
                                    }
                                }
                            }
                            this.zzn.zzg().zzv();
                            zzgc0.zzN(Build.MODEL);
                            this.zzn.zzg().zzv();
                            zzgc0.zzY(Build.VERSION.RELEASE);
                            zzgc0.zzak(((int)this.zzn.zzg().zzb()));
                            zzgc0.zzao(this.zzn.zzg().zzc());
                            zzpz.zzc();
                            if(this.zzg().zzs(null, zzeg.zzaE)) {
                                zzgc0.zzaj(zzq0.zzz);
                            }
                            if(this.zzn.zzJ()) {
                                new String("");
                                if(!TextUtils.isEmpty(null)) {
                                    zzgc0.zzO(null);
                                }
                            }
                            zzak zzak15 = this.zze;
                            zzlh.zzal(zzak15);
                            zzh zzh1 = zzak15.zzj(zzq0.zza);
                            if(zzh1 == null) {
                                zzh1 = new zzh(this.zzn, zzq0.zza);
                                zzh1.zzJ(this.zzw(zzhb0));
                                zzh1.zzX(zzq0.zzk);
                                zzh1.zzY(zzq0.zzb);
                                if(zzhb0.zzj(zzha.zza)) {
                                    zzh1.zzag(this.zzk.zzf(zzq0.zza, zzq0.zzo));
                                }
                                zzh1.zzac(0L);
                                zzh1.zzad(0L);
                                zzh1.zzab(0L);
                                zzh1.zzL(zzq0.zzc);
                                zzh1.zzM(zzq0.zzj);
                                zzh1.zzK(zzq0.zzd);
                                zzh1.zzZ(zzq0.zze);
                                zzh1.zzU(zzq0.zzf);
                                zzh1.zzae(zzq0.zzh);
                                zzh1.zzV(zzq0.zzs);
                                zzak zzak16 = this.zze;
                                zzlh.zzal(zzak16);
                                zzak16.zzD(zzh1);
                            }
                            if(zzhb0.zzj(zzha.zzb) && !TextUtils.isEmpty(zzh1.zzw())) {
                                zzgc0.zzE(((String)Preconditions.checkNotNull(zzh1.zzw())));
                            }
                            if(!TextUtils.isEmpty(zzh1.zzz())) {
                                zzgc0.zzT(((String)Preconditions.checkNotNull(zzh1.zzz())));
                            }
                            zzak zzak17 = this.zze;
                            zzlh.zzal(zzak17);
                            List list0 = zzak17.zzu(zzq0.zza);
                            for(int v20 = 0; v20 < list0.size(); ++v20) {
                                zzgl zzgl0 = zzgm.zzd();
                                zzgl0.zzf(((zzlm)list0.get(v20)).zzc);
                                zzgl0.zzg(((zzlm)list0.get(v20)).zzd);
                                zzlh.zzal(this.zzi);
                                Object object4 = ((zzlm)list0.get(v20)).zze;
                                this.zzi.zzv(zzgl0, object4);
                                zzgc0.zzl(zzgl0);
                                if(this.zzg().zzs(null, zzeg.zzaH) && "_sid".equals(((zzlm)list0.get(v20)).zzc) && zzh1.zzq() != 0L) {
                                    zzlh.zzal(this.zzi);
                                    if(this.zzi.zzd(zzq0.zzx) != zzh1.zzq()) {
                                        zzgc0.zzy();
                                    }
                                }
                            }
                            try {
                                zzak zzak18 = this.zze;
                                zzlh.zzal(zzak18);
                                zzlb zzlb0 = zzgc0.zzaD();
                                zzak18.zzg();
                                zzak18.zzW();
                                Preconditions.checkNotNull(((com.google.android.gms.internal.measurement.zzgd)zzlb0));
                                Preconditions.checkNotEmpty("");
                                byte[] arr_b = ((com.google.android.gms.internal.measurement.zzgd)zzlb0).zzbx();
                                zzlh.zzal(zzak18.zzf.zzi);
                                v21 = zzak18.zzf.zzi.zzf(arr_b);
                                ContentValues contentValues0 = new ContentValues();
                                contentValues0.put("app_id", "");
                                contentValues0.put("metadata_fingerprint", v21);
                                contentValues0.put("metadata", arr_b);
                                try {
                                    zzak18.zzh().insertWithOnConflict("raw_events_metadata", null, contentValues0, 4);
                                }
                                catch(SQLiteException sQLiteException2) {
                                    zzak18.zzt.zzaA().zzd().zzc("Error storing raw event metadata. appId", zzet.zzn(""), sQLiteException2);
                                    throw sQLiteException2;
                                }
                            }
                            catch(IOException iOException0) {
                                this.zzaA().zzd().zzc("Data loss. Failed to insert raw event metadata. appId", zzet.zzn(""), iOException0);
                                goto label_379;
                            }
                            zzak zzak19 = this.zze;
                            zzlh.zzal(zzak19);
                            zzar zzar1 = new zzar(zzap1.zzf);
                            while(zzar1.hasNext()) {
                                if(!"_r".equals(zzar1.zza())) {
                                    continue;
                                }
                                v22 = 1;
                                goto label_359;
                            }
                            zzlh.zzal(this.zzc);
                            boolean z3 = this.zzc.zzq(zzap1.zza, zzap1.zzb);
                            zzak zzak20 = this.zze;
                            zzlh.zzal(zzak20);
                            v22 = z3 && zzak20.zzl(this.zza(), zzap1.zza, false, false, false, false, false).zze < ((long)this.zzg().zze(zzap1.zza, zzeg.zzo)) ? 1 : 0;
                        label_359:
                            zzak19.zzg();
                            zzak19.zzW();
                            Preconditions.checkNotNull(zzap1);
                            Preconditions.checkNotEmpty(zzap1.zza);
                            zzlh.zzal(zzak19.zzf.zzi);
                            byte[] arr_b1 = zzak19.zzf.zzi.zzl(zzap1).zzbx();
                            ContentValues contentValues1 = new ContentValues();
                            contentValues1.put("app_id", zzap1.zza);
                            contentValues1.put("name", zzap1.zzb);
                            contentValues1.put("timestamp", zzap1.zzd);
                            contentValues1.put("metadata_fingerprint", v21);
                            contentValues1.put("data", arr_b1);
                            contentValues1.put("realtime", v22);
                            try {
                                if(zzak19.zzh().insert("raw_events", null, contentValues1) == -1L) {
                                    zzak19.zzt.zzaA().zzd().zzb("Failed to insert raw event (got -1). appId", zzet.zzn(zzap1.zza));
                                }
                                else {
                                    this.zza = 0L;
                                }
                            }
                            catch(SQLiteException sQLiteException3) {
                                zzak19.zzt.zzaA().zzd().zzc("Error storing raw event. appId", zzet.zzn(zzap1.zza), sQLiteException3);
                            }
                        label_379:
                            zzak zzak21 = this.zze;
                            zzlh.zzal(zzak21);
                            zzak21.zzC();
                        }
                        finally {
                            zzak zzak22 = this.zze;
                            zzlh.zzal(zzak22);
                            zzak22.zzx();
                        }
                        this.zzag();
                        this.zzaA().zzj().zzb("Background event processing time, ms", ((long)((System.nanoTime() - v + 500000L) / 1000000L)));
                    }
                }
            }
            else {
                zzlh.zzal(this.zzc);
                if(this.zzc.zzs(s)) {
                    goto label_19;
                }
                else if(!"_err".equals(zzau0.zza)) {
                    this.zzv().zzO(this.zzF, s, 11, "_ev", zzau0.zza, 0);
                }
            }
        }
    }

    final boolean zzZ() {
        this.zzaB().zzg();
        if(this.zzw != null && this.zzw.isValid()) {
            this.zzaA().zzj().zza("Storage concurrent access okay");
            return true;
        }
        File file0 = new File(this.zzn.zzaw().getFilesDir(), "google_app_measurement.db");
        try {
            FileChannel fileChannel0 = new RandomAccessFile(file0, "rw").getChannel();
            this.zzx = fileChannel0;
            FileLock fileLock0 = fileChannel0.tryLock();
            this.zzw = fileLock0;
            if(fileLock0 != null) {
                this.zzaA().zzj().zza("Storage concurrent access okay");
                return true;
            }
            this.zzaA().zzd().zza("Storage concurrent data access panic");
        }
        catch(FileNotFoundException fileNotFoundException0) {
            this.zzaA().zzd().zzb("Failed to acquire storage lock", fileNotFoundException0);
        }
        catch(IOException iOException0) {
            this.zzaA().zzd().zzb("Failed to access storage lock file", iOException0);
        }
        catch(OverlappingFileLockException overlappingFileLockException0) {
            this.zzaA().zzk().zzb("Storage lock already acquired", overlappingFileLockException0);
        }
        return false;
    }

    final long zza() {
        long v = this.zzax().currentTimeMillis();
        zzkb zzkb0 = this.zzk;
        zzkb0.zzW();
        zzkb0.zzg();
        long v1 = zzkb0.zze.zza();
        if(v1 == 0L) {
            v1 = ((long)zzkb0.zzt.zzv().zzG().nextInt(86400000)) + 1L;
            zzkb0.zze.zzb(v1);
        }
        return (v + v1) / 1000L / 60L / 60L / 24L;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    public final zzet zzaA() {
        return ((zzgd)Preconditions.checkNotNull(this.zzn)).zzaA();
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    public final com.google.android.gms.measurement.internal.zzga zzaB() {
        return ((zzgd)Preconditions.checkNotNull(this.zzn)).zzaB();
    }

    static final void zzaa(zzfs zzfs0, int v, String s) {
        List list0 = zzfs0.zzp();
        for(int v1 = 0; v1 < list0.size(); ++v1) {
        }
        zzfw zzfw0 = zzfx.zze();
        zzfw0.zzj("_err");
        ((long)v).getClass();
        zzfw0.zzi(((long)v));
        zzfx zzfx0 = (zzfx)zzfw0.zzaD();
        zzfw zzfw1 = zzfx.zze();
        zzfw1.zzj("_ev");
        zzfw1.zzk(s);
        zzfx zzfx1 = (zzfx)zzfw1.zzaD();
        zzfs0.zzf(zzfx0);
        zzfs0.zzf(zzfx1);
    }

    static final void zzab(zzfs zzfs0, String s) {
        List list0 = zzfs0.zzp();
        for(int v = 0; v < list0.size(); ++v) {
            if(s.equals("")) {
                zzfs0.zzh(v);
                return;
            }
        }
    }

    private final zzq zzac(String s) {
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        zzh zzh0 = zzak0.zzj(s);
        if(zzh0 != null && !TextUtils.isEmpty(zzh0.zzy())) {
            Boolean boolean0 = this.zzad(zzh0);
            if(boolean0 != null && !boolean0.booleanValue()) {
                this.zzaA().zzd().zzb("App version does not match; dropping. appId", zzet.zzn(s));
                return null;
            }
            String s1 = zzh0.zzA();
            String s2 = zzh0.zzy();
            long v = zzh0.zzb();
            String s3 = zzh0.zzx();
            long v1 = zzh0.zzm();
            long v2 = zzh0.zzj();
            boolean z = zzh0.zzan();
            String s4 = zzh0.zzz();
            zzh0.zza();
            return new zzq(s, s1, s2, v, s3, v1, v2, null, z, false, s4, 0L, 0L, 0, zzh0.zzam(), false, zzh0.zzt(), zzh0.zzs(), zzh0.zzk(), zzh0.zzE(), null, this.zzq(s).zzi(), "", null, zzh0.zzap(), zzh0.zzr());
        }
        this.zzaA().zzc().zzb("No app data available; dropping", s);
        return null;
    }

    private final Boolean zzad(zzh zzh0) {
        try {
            if(zzh0.zzb() != 0xFFFFFFFF80000000L) {
                int v = Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(zzh0.zzv(), 0).versionCode;
                return zzh0.zzb() == ((long)v);
            }
            String s = Wrappers.packageManager(this.zzn.zzaw()).getPackageInfo(zzh0.zzv(), 0).versionName;
            String s1 = zzh0.zzy();
            return s1 == null || !s1.equals(s) ? false : true;
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
    }

    private final void zzae() {
        this.zzaB().zzg();
        if(!this.zzt && !this.zzu && !this.zzv) {
            this.zzaA().zzj().zza("Stopping uploading service(s)");
            List list0 = this.zzq;
            if(list0 == null) {
                return;
            }
            for(Object object0: list0) {
                ((Runnable)object0).run();
            }
            ((List)Preconditions.checkNotNull(this.zzq)).clear();
            return;
        }
        this.zzaA().zzj().zzd("Not stopping services. fetch, network, upload", Boolean.valueOf(this.zzt), Boolean.valueOf(this.zzu), Boolean.valueOf(this.zzv));
    }

    private final void zzaf(zzgc zzgc0, long v, boolean z) {
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        String s = z ? "_se" : "_lte";
        zzlm zzlm0 = zzak0.zzp("", s);
        zzlm zzlm1 = zzlm0 == null || zzlm0.zze == null ? new zzlm("", "auto", s, this.zzax().currentTimeMillis(), v) : new zzlm("", "auto", s, this.zzax().currentTimeMillis(), ((long)(((long)(((Long)zzlm0.zze))) + v)));
        zzgl zzgl0 = zzgm.zzd();
        zzgl0.zzf(s);
        zzgl0.zzg(this.zzax().currentTimeMillis());
        zzgl0.zze(((long)(((Long)zzlm1.zze))));
        zzgm zzgm0 = (zzgm)zzgl0.zzaD();
        int v1 = zzlj.zza(zzgc0, s);
        if(v1 >= 0) {
            zzgc0.zzan(v1, zzgm0);
        }
        else {
            zzgc0.zzm(zzgm0);
        }
        if(v > 0L) {
            zzak zzak1 = this.zze;
            zzlh.zzal(zzak1);
            zzak1.zzL(zzlm1);
            this.zzaA().zzj().zzc("Updated engagement user property. scope, value", (z ? "session-scoped" : "lifetime"), zzlm1.zze);
        }
    }

    private final void zzag() {
        long v8;
        long v3;
        this.zzaB().zzg();
        this.zzB();
        if(this.zza > 0L) {
            long v = Math.abs(this.zzax().elapsedRealtime() - this.zza);
            if(3600000L - v > 0L) {
                this.zzaA().zzj().zzb("Upload has been suspended. Will update scheduling later in approximately ms", ((long)(3600000L - v)));
                this.zzl().zzc();
                zzks zzks0 = this.zzg;
                zzlh.zzal(zzks0);
                zzks0.zza();
                return;
            }
            this.zza = 0L;
        }
        if(this.zzn.zzM() && this.zzai()) {
            long v1 = this.zzax().currentTimeMillis();
            this.zzg();
            long v2 = Math.max(0L, ((long)(((Long)zzeg.zzA.zza(null)))));
            zzak zzak0 = this.zze;
            zzlh.zzal(zzak0);
            boolean z = true;
            if(!zzak0.zzH()) {
                zzak zzak1 = this.zze;
                zzlh.zzal(zzak1);
                if(!zzak1.zzG()) {
                    z = false;
                }
            }
            if(z) {
                String s = this.zzg().zzl();
                if(TextUtils.isEmpty(s) || ".none.".equals(s)) {
                    this.zzg();
                    v3 = Math.max(0L, ((long)(((Long)zzeg.zzu.zza(null)))));
                }
                else {
                    this.zzg();
                    v3 = Math.max(0L, ((long)(((Long)zzeg.zzv.zza(null)))));
                }
            }
            else {
                this.zzg();
                v3 = Math.max(0L, ((long)(((Long)zzeg.zzt.zza(null)))));
            }
            long v4 = this.zzk.zzc.zza();
            long v5 = this.zzk.zzd.zza();
            zzak zzak2 = this.zze;
            zzlh.zzal(zzak2);
            long v6 = zzak2.zzd();
            zzak zzak3 = this.zze;
            zzlh.zzal(zzak3);
            long v7 = Math.max(v6, zzak3.zze());
            if(v7 == 0L) {
                v8 = 0L;
            }
            else {
                long v9 = v1 - Math.abs(v7 - v1);
                long v10 = v1 - Math.abs(v5 - v1);
                v8 = v2 + v9;
                long v11 = Math.max(v1 - Math.abs(v4 - v1), v10);
                if(z && v11 > 0L) {
                    v8 = Math.min(v9, v11) + v3;
                }
                zzlh.zzal(this.zzi);
                if(!this.zzi.zzx(v11, v3)) {
                    v8 = v11 + v3;
                }
                if(v10 != 0L && v10 >= v9) {
                    for(int v12 = 0; true; ++v12) {
                        this.zzg();
                        if(v12 >= Math.min(20, Math.max(0, ((int)(((Integer)zzeg.zzC.zza(null))))))) {
                            v8 = 0L;
                            break;
                        }
                        this.zzg();
                        v8 += Math.max(0L, ((long)(((Long)zzeg.zzB.zza(null))))) * (1L << v12);
                        if(v8 > v10) {
                            break;
                        }
                    }
                }
            }
            if(v8 != 0L) {
                zzlh.zzal(this.zzd);
                if(this.zzd.zza()) {
                    long v13 = this.zzk.zzb.zza();
                    this.zzg();
                    long v14 = Math.max(0L, ((long)(((Long)zzeg.zzr.zza(null)))));
                    zzlh.zzal(this.zzi);
                    if(!this.zzi.zzx(v13, v14)) {
                        v8 = Math.max(v8, v13 + v14);
                    }
                    this.zzl().zzc();
                    long v15 = v8 - this.zzax().currentTimeMillis();
                    if(v15 <= 0L) {
                        this.zzg();
                        v15 = Math.max(0L, ((long)(((Long)zzeg.zzw.zza(null)))));
                        this.zzk.zzc.zzb(this.zzax().currentTimeMillis());
                    }
                    this.zzaA().zzj().zzb("Upload scheduled in approximately ms", v15);
                    zzks zzks1 = this.zzg;
                    zzlh.zzal(zzks1);
                    zzks1.zzd(v15);
                    return;
                }
                this.zzaA().zzj().zza("No network");
                this.zzl().zzb();
                zzks zzks2 = this.zzg;
                zzlh.zzal(zzks2);
                zzks2.zza();
                return;
            }
            this.zzaA().zzj().zza("Next upload time is 0");
            this.zzl().zzc();
            zzks zzks3 = this.zzg;
            zzlh.zzal(zzks3);
            zzks3.zza();
            return;
        }
        this.zzaA().zzj().zza("Nothing to upload or uploading impossible");
        this.zzl().zzc();
        zzks zzks4 = this.zzg;
        zzlh.zzal(zzks4);
        zzks4.zza();
    }

    // This method was un-flattened
    private final boolean zzah(String s, long v) {
        byte[] arr_b1;
        int v22;
        long v21;
        long v20;
        int v19;
        SecureRandom secureRandom1;
        long v16;
        zzgc zzgc2;
        zzfs zzfs2;
        zzgc zzgc1;
        int v6;
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        zzak0.zzw();
        try {
            zzle zzle0 = new zzle(this, null);
            zzak zzak1 = this.zze;
            zzlh.zzal(zzak1);
            zzak1.zzU(null, v, this.zzA, zzle0);
            if(zzle0.zzc != null && !zzle0.zzc.isEmpty()) {
                zzgc zzgc0 = (zzgc)zzle0.zza.zzbB();
                zzgc0.zzr();
                zzfs zzfs0 = null;
                int v2 = 0;
                int v4 = 0;
                for(int v3 = 0; true; v3 = v5) {
                    int v5 = v3;
                    v6 = v4;
                    if(v2 >= zzle0.zzc.size()) {
                        break;
                    }
                    zzfs zzfs1 = (zzfs)((zzft)zzle0.zzc.get(v2)).zzbB();
                    zzlh.zzal(this.zzc);
                    if(this.zzc.zzr("", "")) {
                        this.zzaA().zzk().zzc("Dropping blocked raw event. appId", zzet.zzn(""), this.zzn.zzj().zzd(""));
                        zzlh.zzal(this.zzc);
                        if(!this.zzc.zzp("")) {
                            zzlh.zzal(this.zzc);
                            if(!this.zzc.zzs("")) {
                                this.zzv().zzO(this.zzF, "", 11, "_ev", "", 0);
                            }
                        }
                        zzgc1 = zzgc0;
                    }
                    else {
                        zzlh.zzal(this.zzc);
                        if(this.zzc.zzq("", "")) {
                            zzfs2 = zzfs0;
                            for(int v7 = 0; v7 < zzfs1.zza(); ++v7) {
                            }
                            zzgc2 = zzgc0;
                            this.zzaA().zzj().zzb("Marking event as conversion", this.zzn.zzj().zzd(""));
                            zzfw zzfw0 = zzfx.zze();
                            zzfw0.zzj("_c");
                            zzfw0.zzi(1L);
                            zzfs1.zze(zzfw0);
                            this.zzaA().zzj().zzb("Marking event as real-time", this.zzn.zzj().zzd(""));
                            zzfw zzfw1 = zzfx.zze();
                            zzfw1.zzj("_r");
                            zzfw1.zzi(1L);
                            zzfs1.zze(zzfw1);
                            zzak zzak2 = this.zze;
                            zzlh.zzal(zzak2);
                            if(zzak2.zzl(this.zza(), "", false, false, false, false, true).zze > ((long)this.zzg().zze("", zzeg.zzo))) {
                                zzlh.zzab(zzfs1, "_r");
                            }
                            else {
                                v6 = 1;
                            }
                            if(zzlp.zzak("")) {
                                zzak zzak3 = this.zze;
                                zzlh.zzal(zzak3);
                                if(zzak3.zzl(this.zza(), "", false, false, true, false, false).zzc > ((long)this.zzg().zze("", zzeg.zzn))) {
                                    this.zzaA().zzk().zzb("Too many conversions. Not logging as conversion. appId", zzet.zzn(""));
                                    for(int v8 = 0; v8 < zzfs1.zza(); ++v8) {
                                        zzfs1.zzn(v8);
                                    }
                                    this.zzaA().zzd().zzb("Did not find conversion parameter. appId", zzet.zzn(""));
                                }
                            }
                            ArrayList arrayList0 = new ArrayList(zzfs1.zzp());
                            for(int v9 = 0; v9 < arrayList0.size(); ++v9) {
                            }
                        }
                        else {
                            zzlh.zzal(this.zzi);
                            Preconditions.checkNotEmpty("");
                            zzfs2 = zzfs0;
                            zzgc2 = zzgc0;
                        }
                        zzgc1 = zzgc2;
                        zzfs0 = zzfs2;
                        zzle0.zzc.set(v2, ((zzft)zzfs1.zzaD()));
                        zzgc1.zzk(zzfs1);
                        ++v5;
                    }
                    v4 = v6;
                    ++v2;
                    zzgc0 = zzgc1;
                }
                long v10 = 0L;
                for(int v11 = 0; v11 < v5; ++v11) {
                    zzft zzft0 = zzgc0.zze(v11);
                    zzlh.zzal(this.zzi);
                    zzfx zzfx0 = zzlj.zzC(zzft0, "_et");
                    if(zzfx0 != null) {
                        Long long0 = zzfx0.zzw() ? zzfx0.zzd() : null;
                        if(long0 != null && ((long)long0) > 0L) {
                            v10 += (long)long0;
                        }
                    }
                }
                this.zzaf(zzgc0, v10, false);
                Iterator iterator0 = zzgc0.zzat().iterator();
                while(iterator0.hasNext()) {
                    iterator0.next();
                }
                if(zzlj.zza(zzgc0, "_sid") >= 0) {
                    this.zzaf(zzgc0, v10, true);
                }
                else {
                    int v12 = zzlj.zza(zzgc0, "_se");
                    if(v12 >= 0) {
                        zzgc0.zzB(v12);
                        this.zzaA().zzd().zzb("Session engagement user property is in the bundle without session ID. appId", zzet.zzn(""));
                    }
                }
                zzlj zzlj0 = this.zzi;
                zzlh.zzal(zzlj0);
                zzlj0.zzt.zzaA().zzj().zza("Checking account type status for ad personalization signals");
                zzlh.zzal(zzlj0.zzf.zzc);
                if(zzlj0.zzf.zzc.zzn("")) {
                    zzak zzak4 = zzlj0.zzf.zze;
                    zzlh.zzal(zzak4);
                    zzh zzh0 = zzak4.zzj("");
                    if(zzh0 != null && zzh0.zzam() && zzlj0.zzt.zzg().zze()) {
                        zzlj0.zzt.zzaA().zzc().zza("Turning off ad personalization due to account type");
                        zzgl zzgl0 = zzgm.zzd();
                        zzgl0.zzf("_npa");
                        zzgl0.zzg(zzlj0.zzt.zzg().zza());
                        zzgl0.zze(1L);
                        zzgm zzgm0 = (zzgm)zzgl0.zzaD();
                        for(int v13 = 0; v13 < zzgc0.zzb(); ++v13) {
                        }
                        zzgc0.zzm(zzgm0);
                    }
                }
                zzgc0.zzai(0x7FFFFFFFFFFFFFFFL);
                zzgc0.zzQ(0x8000000000000000L);
                for(int v14 = 0; v14 < zzgc0.zza(); ++v14) {
                    zzft zzft1 = zzgc0.zze(v14);
                    if(zzft1.zzd() < zzgc0.zzd()) {
                        zzgc0.zzai(zzft1.zzd());
                    }
                    if(zzft1.zzd() > zzgc0.zzc()) {
                        zzgc0.zzQ(zzft1.zzd());
                    }
                }
                zzgc0.zzz();
                zzgc0.zzo();
                zzaa zzaa0 = this.zzh;
                zzlh.zzal(zzaa0);
                zzgc0.zzf(zzaa0.zza("", zzgc0.zzat(), zzgc0.zzau(), zzgc0.zzd(), zzgc0.zzc()));
                if(this.zzg().zzw("")) {
                    HashMap hashMap0 = new HashMap();
                    ArrayList arrayList1 = new ArrayList();
                    SecureRandom secureRandom0 = this.zzv().zzG();
                    int v15 = 0;
                    while(v15 < zzgc0.zza()) {
                        zzfs zzfs3 = (zzfs)zzgc0.zze(v15).zzbB();
                        zzfu zzfu0 = this.zzc;
                        zzlh.zzal(zzfu0);
                        String s1 = zzfu0.zza("", "measurement.account.time_zone_offset_minutes");
                        if(TextUtils.isEmpty(s1)) {
                            v16 = 0L;
                        }
                        else {
                            try {
                                v16 = Long.parseLong(s1);
                            }
                            catch(NumberFormatException numberFormatException0) {
                                zzfu0.zzt.zzaA().zzk().zzc("Unable to parse timezone offset. appId", zzet.zzn(""), numberFormatException0);
                                v16 = 0L;
                            }
                        }
                        long v17 = this.zzv().zzr(zzfs3.zzc(), v16);
                        zzft zzft2 = (zzft)zzfs3.zzaD();
                        if(!TextUtils.isEmpty("_dbg")) {
                            for(Object object0: zzft2.zzi()) {
                                zzfx zzfx1 = (zzfx)object0;
                            }
                        }
                        zzlh.zzal(this.zzc);
                        int v18 = this.zzc.zzc("", "");
                        if(v18 <= 0) {
                            this.zzaA().zzk().zzc("Sample rate must be positive. event, rate", "", v18);
                            arrayList1.add(((zzft)zzfs3.zzaD()));
                            zzgc0.zzS(v15, zzfs3);
                            secureRandom1 = secureRandom0;
                            v19 = v15;
                        }
                        else {
                            zzaq zzaq0 = (zzaq)hashMap0.get("");
                            if(zzaq0 == null) {
                                zzak zzak5 = this.zze;
                                zzlh.zzal(zzak5);
                                zzaq zzaq1 = zzak5.zzn("", "");
                                if(zzaq1 == null) {
                                    this.zzaA().zzk().zzc("Event being bundled has no eventAggregate. appId, eventName", "", "");
                                    zzaq0 = new zzaq("", "", 1L, 1L, 1L, zzfs3.zzc(), 0L, null, null, null, null);
                                }
                                else {
                                    zzaq0 = zzaq1;
                                }
                            }
                            zzlh.zzal(this.zzi);
                            Long long1 = (Long)zzlj.zzD(((zzft)zzfs3.zzaD()), "_eid");
                            boolean z = long1 != null;
                            Boolean boolean0 = Boolean.valueOf(z);
                            if(v18 == 1) {
                                arrayList1.add(((zzft)zzfs3.zzaD()));
                                boolean0.getClass();
                                if(z && (zzaq0.zzi != null || zzaq0.zzj != null || zzaq0.zzk != null)) {
                                    hashMap0.put("", zzaq0.zza(null, null, null));
                                }
                                zzgc0.zzS(v15, zzfs3);
                                secureRandom1 = secureRandom0;
                                v19 = v15;
                            }
                            else {
                                if(secureRandom0.nextInt(v18) == 0) {
                                    zzlh.zzal(this.zzi);
                                    Long long2 = (long)v18;
                                    zzlj.zzA(zzfs3, "_sr", long2);
                                    arrayList1.add(((zzft)zzfs3.zzaD()));
                                    boolean0.getClass();
                                    if(z) {
                                        zzaq0 = zzaq0.zza(null, long2, null);
                                    }
                                    hashMap0.put("", zzaq0.zzb(zzfs3.zzc(), v17));
                                    secureRandom1 = secureRandom0;
                                    v19 = v15;
                                }
                                else {
                                    Long long3 = zzaq0.zzh;
                                    if(long3 == null) {
                                        secureRandom1 = secureRandom0;
                                        v22 = v15;
                                        v21 = v17;
                                        v20 = this.zzv().zzr(zzfs3.zzb(), v16);
                                    }
                                    else {
                                        v20 = (long)long3;
                                        v21 = v17;
                                        secureRandom1 = secureRandom0;
                                        v22 = v15;
                                    }
                                    if(v20 == v21) {
                                        boolean0.getClass();
                                        if(z) {
                                            hashMap0.put("", zzaq0.zza(long1, null, null));
                                        }
                                    }
                                    else {
                                        zzlh.zzal(this.zzi);
                                        zzlj.zzA(zzfs3, "_efs", 1L);
                                        zzlh.zzal(this.zzi);
                                        Long long4 = (long)v18;
                                        zzlj.zzA(zzfs3, "_sr", long4);
                                        arrayList1.add(((zzft)zzfs3.zzaD()));
                                        boolean0.getClass();
                                        if(z) {
                                            zzaq0 = zzaq0.zza(null, long4, Boolean.TRUE);
                                        }
                                        hashMap0.put("", zzaq0.zzb(zzfs3.zzc(), v21));
                                    }
                                    v19 = v22;
                                }
                                zzgc0.zzS(v19, zzfs3);
                            }
                        }
                        v15 = v19 + 1;
                        secureRandom0 = secureRandom1;
                    }
                    if(arrayList1.size() < zzgc0.zza()) {
                        zzgc0.zzr();
                        zzgc0.zzg(arrayList1);
                    }
                    for(Object object1: hashMap0.entrySet()) {
                        zzak zzak6 = this.zze;
                        zzlh.zzal(zzak6);
                        zzak6.zzE(((zzaq)((Map.Entry)object1).getValue()));
                    }
                }
                zzak zzak7 = this.zze;
                zzlh.zzal(zzak7);
                zzh zzh1 = zzak7.zzj("");
                if(zzh1 == null) {
                    this.zzaA().zzd().zzb("Bundling raw events w/o app info. appId", zzet.zzn(""));
                }
                else if(zzgc0.zza() > 0) {
                    long v23 = zzh1.zzn();
                    if(v23 == 0L) {
                        zzgc0.zzv();
                    }
                    else {
                        zzgc0.zzab(v23);
                    }
                    long v24 = zzh1.zzp();
                    if(v24 != 0L) {
                        v23 = v24;
                    }
                    if(v23 == 0L) {
                        zzgc0.zzw();
                    }
                    else {
                        zzgc0.zzac(v23);
                    }
                    zzh1.zzG();
                    zzgc0.zzI(((int)zzh1.zzo()));
                    zzh1.zzad(zzgc0.zzd());
                    zzh1.zzab(zzgc0.zzc());
                    String s2 = zzh1.zzu();
                    if(s2 == null) {
                        zzgc0.zzs();
                    }
                    else {
                        zzgc0.zzW(s2);
                    }
                    zzak zzak8 = this.zze;
                    zzlh.zzal(zzak8);
                    zzak8.zzD(zzh1);
                }
                if(zzgc0.zza() > 0) {
                    zzlh.zzal(this.zzc);
                    zzff zzff0 = this.zzc.zze("");
                    if(zzff0 == null || !zzff0.zzu()) {
                        zzgc0.zzK(-1L);
                    }
                    else {
                        zzgc0.zzK(zzff0.zzc());
                    }
                    zzak zzak9 = this.zze;
                    zzlh.zzal(zzak9);
                    com.google.android.gms.internal.measurement.zzgd zzgd0 = (com.google.android.gms.internal.measurement.zzgd)zzgc0.zzaD();
                    zzak9.zzg();
                    zzak9.zzW();
                    Preconditions.checkNotNull(zzgd0);
                    Preconditions.checkNotEmpty("");
                    Preconditions.checkState(zzgd0.zzbg());
                    zzak9.zzz();
                    long v25 = zzak9.zzt.zzax().currentTimeMillis();
                    if(zzgd0.zzk() < v25 - zzag.zzA() || zzgd0.zzk() > zzag.zzA() + v25) {
                        zzak9.zzt.zzaA().zzk().zzd("Storing bundle outside of the max uploading time span. appId, now, timestamp", zzet.zzn(""), v25, zzgd0.zzk());
                    }
                    byte[] arr_b = zzgd0.zzbx();
                    try {
                        zzlh.zzal(zzak9.zzf.zzi);
                        arr_b1 = zzak9.zzf.zzi.zzz(arr_b);
                    }
                    catch(IOException iOException0) {
                        zzak9.zzt.zzaA().zzd().zzc("Data loss. Failed to serialize bundle. appId", zzet.zzn(""), iOException0);
                        goto label_325;
                    }
                    zzak9.zzt.zzaA().zzj().zzb("Saving bundle, size", ((int)arr_b1.length));
                    ContentValues contentValues0 = new ContentValues();
                    contentValues0.put("app_id", "");
                    contentValues0.put("bundle_end_timestamp", zzgd0.zzk());
                    contentValues0.put("data", arr_b1);
                    contentValues0.put("has_realtime", v6);
                    if(zzgd0.zzbm()) {
                        contentValues0.put("retry_count", zzgd0.zze());
                    }
                    try {
                        if(zzak9.zzh().insert("queue", null, contentValues0) == -1L) {
                            zzak9.zzt.zzaA().zzd().zzb("Failed to insert bundle (got -1). appId", zzet.zzn(""));
                        }
                    }
                    catch(SQLiteException sQLiteException0) {
                        zzak9.zzt.zzaA().zzd().zzc("Error storing bundle. appId", zzet.zzn(""), sQLiteException0);
                    }
                }
            label_325:
                zzak zzak10 = this.zze;
                zzlh.zzal(zzak10);
                List list0 = zzle0.zzb;
                Preconditions.checkNotNull(list0);
                zzak10.zzg();
                zzak10.zzW();
                StringBuilder stringBuilder0 = new StringBuilder("rowid in (");
                for(int v26 = 0; v26 < list0.size(); ++v26) {
                    if(v26 != 0) {
                        stringBuilder0.append(",");
                    }
                    stringBuilder0.append(((long)(((Long)list0.get(v26)))));
                }
                stringBuilder0.append(")");
                int v27 = zzak10.zzh().delete("raw_events", stringBuilder0.toString(), null);
                if(v27 != list0.size()) {
                    zzak10.zzt.zzaA().zzd().zzc("Deleted fewer rows from raw events table than expected", v27, list0.size());
                }
                zzak zzak11 = this.zze;
                zzlh.zzal(zzak11);
                SQLiteDatabase sQLiteDatabase0 = zzak11.zzh();
                try {
                    sQLiteDatabase0.execSQL("delete from raw_events_metadata where app_id=? and metadata_fingerprint not in (select distinct metadata_fingerprint from raw_events where app_id=?)", new String[]{"", ""});
                }
                catch(SQLiteException sQLiteException1) {
                    zzak11.zzt.zzaA().zzd().zzc("Failed to remove unused event metadata. appId", zzet.zzn(""), sQLiteException1);
                }
                zzak zzak12 = this.zze;
                zzlh.zzal(zzak12);
                zzak12.zzC();
                return true;
            }
            zzak zzak13 = this.zze;
            zzlh.zzal(zzak13);
            zzak13.zzC();
            return false;
        }
        finally {
            zzak zzak14 = this.zze;
            zzlh.zzal(zzak14);
            zzak14.zzx();
        }
    }

    private final boolean zzai() {
        this.zzaB().zzg();
        this.zzB();
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        if(!zzak0.zzF()) {
            zzak zzak1 = this.zze;
            zzlh.zzal(zzak1);
            return !TextUtils.isEmpty(zzak1.zzr());
        }
        return true;
    }

    private final boolean zzaj(zzfs zzfs0, zzfs zzfs1) {
        Preconditions.checkArgument(false);
        zzlh.zzal(this.zzi);
        String s = null;
        String s1 = zzlj.zzC(((zzft)zzfs0.zzaD()), "_sc") == null ? null : "";
        zzlh.zzal(this.zzi);
        if(zzlj.zzC(((zzft)zzfs1.zzaD()), "_pc") != null) {
            s = "";
        }
        if(s != null && s.equals(s1)) {
            Preconditions.checkArgument(false);
            zzlh.zzal(this.zzi);
            zzfx zzfx0 = zzlj.zzC(((zzft)zzfs0.zzaD()), "_et");
            if(zzfx0 != null && zzfx0.zzw() && zzfx0.zzd() > 0L) {
                long v = zzfx0.zzd();
                zzlh.zzal(this.zzi);
                zzfx zzfx1 = zzlj.zzC(((zzft)zzfs1.zzaD()), "_et");
                if(zzfx1 != null && zzfx1.zzd() > 0L) {
                    v += zzfx1.zzd();
                }
                zzlh.zzal(this.zzi);
                zzlj.zzA(zzfs1, "_et", v);
                zzlh.zzal(this.zzi);
                zzlj.zzA(zzfs0, "_fr", 1L);
            }
            return true;
        }
        return false;
    }

    // 去混淆评级： 低(20)
    private static final boolean zzak(zzq zzq0) {
        return !TextUtils.isEmpty(zzq0.zzb) || !TextUtils.isEmpty(zzq0.zzq);
    }

    private static final zzku zzal(zzku zzku0) {
        if(zzku0 == null) {
            throw new IllegalStateException("Upload Component not created");
        }
        if(!zzku0.zzY()) {
            throw new IllegalStateException("Component not initialized: " + zzku0.getClass());
        }
        return zzku0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    public final Context zzaw() {
        return this.zzn.zzaw();
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    public final Clock zzax() {
        return ((zzgd)Preconditions.checkNotNull(this.zzn)).zzax();
    }

    @Override  // com.google.android.gms.measurement.internal.zzgy
    public final zzab zzay() {
        throw null;
    }

    final zzh zzd(zzq zzq0) {
        this.zzaB().zzg();
        this.zzB();
        Preconditions.checkNotNull(zzq0);
        Preconditions.checkNotEmpty(zzq0.zza);
        if(!zzq0.zzw.isEmpty()) {
            zzlg zzlg0 = new zzlg(this, zzq0.zzw, null);
            this.zzC.put(zzq0.zza, zzlg0);
        }
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        zzh zzh0 = zzak0.zzj(zzq0.zza);
        zzhb zzhb0 = this.zzq(zzq0.zza).zzd(zzhb.zzc(zzq0.zzv, 100));
        String s = zzhb0.zzj(zzha.zza) ? this.zzk.zzf(zzq0.zza, zzq0.zzo) : "";
        if(zzh0 == null) {
            zzh0 = new zzh(this.zzn, zzq0.zza);
            if(zzhb0.zzj(zzha.zzb)) {
                zzh0.zzJ(this.zzw(zzhb0));
            }
            if(zzhb0.zzj(zzha.zza)) {
                zzh0.zzag(s);
            }
        }
        else if(zzhb0.zzj(zzha.zza) && s != null && !s.equals(zzh0.zzC())) {
            zzh0.zzag(s);
            if(zzq0.zzo && !"00000000-0000-0000-0000-000000000000".equals(this.zzk.zzd(zzq0.zza, zzhb0).first)) {
                zzh0.zzJ(this.zzw(zzhb0));
                zzak zzak1 = this.zze;
                zzlh.zzal(zzak1);
                if(zzak1.zzp(zzq0.zza, "_id") != null) {
                    zzak zzak2 = this.zze;
                    zzlh.zzal(zzak2);
                    if(zzak2.zzp(zzq0.zza, "_lair") == null) {
                        long v = this.zzax().currentTimeMillis();
                        zzlm zzlm0 = new zzlm(zzq0.zza, "auto", "_lair", v, 1L);
                        zzak zzak3 = this.zze;
                        zzlh.zzal(zzak3);
                        zzak3.zzL(zzlm0);
                    }
                }
            }
        }
        else if(TextUtils.isEmpty(zzh0.zzw()) && zzhb0.zzj(zzha.zzb)) {
            zzh0.zzJ(this.zzw(zzhb0));
        }
        zzh0.zzY(zzq0.zzb);
        zzh0.zzH(zzq0.zzq);
        if(!TextUtils.isEmpty(zzq0.zzk)) {
            zzh0.zzX(zzq0.zzk);
        }
        long v1 = zzq0.zze;
        if(v1 != 0L) {
            zzh0.zzZ(v1);
        }
        if(!TextUtils.isEmpty(zzq0.zzc)) {
            zzh0.zzL(zzq0.zzc);
        }
        zzh0.zzM(zzq0.zzj);
        String s1 = zzq0.zzd;
        if(s1 != null) {
            zzh0.zzK(s1);
        }
        zzh0.zzU(zzq0.zzf);
        zzh0.zzae(zzq0.zzh);
        if(!TextUtils.isEmpty(zzq0.zzg)) {
            zzh0.zzaa(zzq0.zzg);
        }
        zzh0.zzI(zzq0.zzo);
        zzh0.zzaf(zzq0.zzr);
        zzh0.zzV(zzq0.zzs);
        zzqu.zzc();
        if(this.zzg().zzs(null, zzeg.zzam) || this.zzg().zzs(zzq0.zza, zzeg.zzao)) {
            zzh0.zzai(zzq0.zzx);
        }
        zzop.zzc();
        if(this.zzg().zzs(null, zzeg.zzal)) {
            zzh0.zzah(zzq0.zzt);
        }
        else {
            zzop.zzc();
            if(this.zzg().zzs(null, zzeg.zzak)) {
                zzh0.zzah(null);
            }
        }
        zzrd.zzc();
        if(this.zzg().zzs(null, zzeg.zzaq)) {
            zzh0.zzak(zzq0.zzy);
        }
        zzpz.zzc();
        if(this.zzg().zzs(null, zzeg.zzaE)) {
            zzh0.zzal(zzq0.zzz);
        }
        if(zzh0.zzao()) {
            zzak zzak4 = this.zze;
            zzlh.zzal(zzak4);
            zzak4.zzD(zzh0);
        }
        return zzh0;
    }

    public final zzaa zzf() {
        zzaa zzaa0 = this.zzh;
        zzlh.zzal(zzaa0);
        return zzaa0;
    }

    public final zzag zzg() {
        return ((zzgd)Preconditions.checkNotNull(this.zzn)).zzf();
    }

    public final zzak zzh() {
        zzak zzak0 = this.zze;
        zzlh.zzal(zzak0);
        return zzak0;
    }

    public final zzeo zzi() {
        return this.zzn.zzj();
    }

    public final zzez zzj() {
        zzlh.zzal(this.zzd);
        return this.zzd;
    }

    public final zzfb zzl() {
        zzfb zzfb0 = this.zzf;
        if(zzfb0 == null) {
            throw new IllegalStateException("Network broadcast receiver not created");
        }
        return zzfb0;
    }

    public final zzfu zzm() {
        zzlh.zzal(this.zzc);
        return this.zzc;
    }

    static zzgd zzo(zzlh zzlh0) {
        return zzlh0.zzn;
    }

    final zzgd zzp() {
        return this.zzn;
    }

    final zzhb zzq(String s) {
        String s1;
        this.zzaB().zzg();
        this.zzB();
        zzhb zzhb0 = (zzhb)this.zzB.get(s);
        if(zzhb0 == null) {
            zzak zzak0 = this.zze;
            zzlh.zzal(zzak0);
            Preconditions.checkNotNull(s);
            zzak0.zzg();
            zzak0.zzW();
            SQLiteDatabase sQLiteDatabase0 = zzak0.zzh();
            Cursor cursor0 = null;
            try {
                try {
                    cursor0 = sQLiteDatabase0.rawQuery("select consent_state from consent_settings where app_id=? limit 1;", new String[]{s});
                    if(cursor0.moveToFirst()) {
                        s1 = cursor0.getString(0);
                        goto label_21;
                    }
                    else {
                        goto label_23;
                    }
                    goto label_25;
                }
                catch(SQLiteException sQLiteException0) {
                    zzak0.zzt.zzaA().zzd().zzc("Database error", "select consent_state from consent_settings where app_id=? limit 1;", sQLiteException0);
                    throw sQLiteException0;
                }
            }
            catch(Throwable throwable0) {
                TWR.safeClose$NT(cursor0, throwable0);
                throw throwable0;
            }
        label_21:
            cursor0.close();
            goto label_25;
        label_23:
            cursor0.close();
            s1 = "G1";
        label_25:
            zzhb zzhb1 = zzhb.zzc(s1, 100);
            this.zzV(s, zzhb1);
            return zzhb1;
        }
        return zzhb0;
    }

    public final zzip zzr() {
        zzip zzip0 = this.zzj;
        zzlh.zzal(zzip0);
        return zzip0;
    }

    public final zzkb zzs() {
        return this.zzk;
    }

    public static zzlh zzt(Context context0) {
        Preconditions.checkNotNull(context0);
        Preconditions.checkNotNull(context0.getApplicationContext());
        if(zzlh.zzb == null) {
            synchronized(zzlh.class) {
                if(zzlh.zzb == null) {
                    zzlh.zzb = new zzlh(((zzli)Preconditions.checkNotNull(new zzli(context0))), null);
                }
                return zzlh.zzb;
            }
        }
        return zzlh.zzb;
    }

    public final zzlj zzu() {
        zzlh.zzal(this.zzi);
        return this.zzi;
    }

    public final zzlp zzv() {
        return ((zzgd)Preconditions.checkNotNull(this.zzn)).zzv();
    }

    final String zzw(zzhb zzhb0) {
        if(zzhb0.zzj(zzha.zzb)) {
            byte[] arr_b = new byte[16];
            this.zzv().zzG().nextBytes(arr_b);
            return String.format(Locale.US, "%032x", new BigInteger(1, arr_b));
        }
        return null;
    }

    final String zzx(zzq zzq0) {
        Future future0 = this.zzaB().zzh(new zzla(this, zzq0));
        try {
            return (String)future0.get(30000L, TimeUnit.MILLISECONDS);
        }
        catch(TimeoutException | InterruptedException | ExecutionException timeoutException0) {
            this.zzaA().zzd().zzc("Failed to get app instance id. appId", zzet.zzn(zzq0.zza), timeoutException0);
            return null;
        }
    }

    static void zzy(zzlh zzlh0, zzli zzli0) {
        zzlh0.zzaB().zzg();
        zzlh0.zzm = new zzfl(zzlh0);
        zzak zzak0 = new zzak(zzlh0);
        zzak0.zzX();
        zzlh0.zze = zzak0;
        zzlh0.zzg().zzq(((zzaf)Preconditions.checkNotNull(zzlh0.zzc)));
        zzkb zzkb0 = new zzkb(zzlh0);
        zzkb0.zzX();
        zzlh0.zzk = zzkb0;
        zzaa zzaa0 = new zzaa(zzlh0);
        zzaa0.zzX();
        zzlh0.zzh = zzaa0;
        zzip zzip0 = new zzip(zzlh0);
        zzip0.zzX();
        zzlh0.zzj = zzip0;
        zzks zzks0 = new zzks(zzlh0);
        zzks0.zzX();
        zzlh0.zzg = zzks0;
        zzlh0.zzf = new zzfb(zzlh0);
        if(zzlh0.zzr != zzlh0.zzs) {
            zzlh0.zzaA().zzd().zzc("Not all upload components initialized", zzlh0.zzr, zzlh0.zzs);
        }
        zzlh0.zzo = true;
    }

    final void zzz(Runnable runnable0) {
        this.zzaB().zzg();
        if(this.zzq == null) {
            this.zzq = new ArrayList();
        }
        this.zzq.add(runnable0);
    }
}

