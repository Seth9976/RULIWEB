package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzc;
import com.google.android.gms.internal.measurement.zzd;
import com.google.android.gms.internal.measurement.zzfc;
import com.google.android.gms.internal.measurement.zzfe;
import com.google.android.gms.internal.measurement.zzff;
import com.google.android.gms.internal.measurement.zzgt;
import com.google.android.gms.internal.measurement.zzll;
import com.google.android.gms.internal.measurement.zzr;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzfu extends zzku implements zzaf {
    final Map zza;
    final Map zzb;
    final Map zzc;
    final LruCache zzd;
    final zzr zze;
    private final Map zzg;
    private final Map zzh;
    private final Map zzi;
    private final Map zzj;
    private final Map zzk;
    private final Map zzl;

    zzfu(zzlh zzlh0) {
        super(zzlh0);
        this.zzg = new ArrayMap();
        this.zza = new ArrayMap();
        this.zzb = new ArrayMap();
        this.zzc = new ArrayMap();
        this.zzh = new ArrayMap();
        this.zzj = new ArrayMap();
        this.zzk = new ArrayMap();
        this.zzl = new ArrayMap();
        this.zzi = new ArrayMap();
        this.zzd = new zzfr(this, 20);
        this.zze = new zzfs(this);
    }

    private final zzff zzA(String s, byte[] arr_b) {
        if(arr_b == null) {
            return zzff.zzg();
        }
        try {
            zzff zzff0 = (zzff)((zzfe)zzlj.zzm(zzff.zze(), arr_b)).zzaD();
            zzer zzer0 = this.zzt.zzaA().zzj();
            String s1 = null;
            Long long0 = zzff0.zzu() ? zzff0.zzc() : null;
            if(zzff0.zzt()) {
                s1 = "";
            }
            zzer0.zzc("Parsed config. version, gmp_app_id", long0, s1);
            return zzff0;
        }
        catch(zzll zzll0) {
            this.zzt.zzaA().zzk().zzc("Unable to merge remote config. appId", zzet.zzn(s), zzll0);
            return zzff.zzg();
        }
        catch(RuntimeException runtimeException0) {
            this.zzt.zzaA().zzk().zzc("Unable to merge remote config. appId", zzet.zzn(s), runtimeException0);
            return zzff.zzg();
        }
    }

    private final void zzB(String s, zzfe zzfe0) {
        HashSet hashSet0 = new HashSet();
        ArrayMap arrayMap0 = new ArrayMap();
        ArrayMap arrayMap1 = new ArrayMap();
        ArrayMap arrayMap2 = new ArrayMap();
        Iterator iterator0 = zzfe0.zzg().iterator();
        while(iterator0.hasNext()) {
            iterator0.next();
            hashSet0.add("");
        }
        for(int v = 0; v < zzfe0.zza(); ++v) {
            zzfc zzfc0 = (zzfc)zzfe0.zzb(v).zzbB();
            this.zzt.zzaA().zzk().zza("EventConfig contained null event name");
        }
        this.zza.put(s, hashSet0);
        this.zzb.put(s, arrayMap0);
        this.zzc.put(s, arrayMap1);
        this.zzi.put(s, arrayMap2);
    }

    private final void zzC(String s) {
        zzah zzah0;
        String s2;
        String s1;
        byte[] arr_b;
        Cursor cursor1;
        this.zzW();
        this.zzg();
        Preconditions.checkNotEmpty(s);
        if(this.zzh.get(s) == null) {
            zzak zzak0 = this.zzf.zzh();
            Preconditions.checkNotEmpty(s);
            zzak0.zzg();
            zzak0.zzW();
            Cursor cursor0 = null;
            try {
                cursor1 = null;
                cursor1 = zzak0.zzh().query("apps", new String[]{"remote_config", "config_last_modified_time", "e_tag"}, "app_id=?", new String[]{s}, null, null, null);
                goto label_15;
            }
            catch(SQLiteException sQLiteException0) {
                goto label_25;
            }
            catch(Throwable throwable0) {
            }
            Throwable throwable1 = throwable0;
            goto label_33;
            try {
            label_15:
                if(cursor1.moveToFirst()) {
                    arr_b = cursor1.getBlob(0);
                    s1 = cursor1.getString(1);
                    s2 = cursor1.getString(2);
                    if(cursor1.moveToNext()) {
                        zzak0.zzt.zzaA().zzd().zzb("Got multiple records for app config, expected one. appId", zzet.zzn(s));
                    }
                    goto label_21;
                }
                else {
                    goto label_41;
                }
                goto label_43;
            }
            catch(SQLiteException sQLiteException0) {
                goto label_25;
            }
            catch(Throwable throwable2) {
                goto label_31;
            }
        label_21:
            if(arr_b == null) {
                cursor1.close();
                zzah0 = null;
                goto label_43;
            label_41:
                cursor1.close();
                zzah0 = null;
            }
            else {
                try {
                    try {
                        zzah0 = new zzah(arr_b, s1, s2);
                        goto label_36;
                    }
                    catch(SQLiteException sQLiteException0) {
                    }
                label_25:
                    zzak0.zzt.zzaA().zzd().zzc("Error querying remote config. appId", zzet.zzn(s), sQLiteException0);
                    if(cursor1 != null) {
                        goto label_27;
                    }
                    zzah0 = null;
                    goto label_43;
                }
                catch(Throwable throwable2) {
                    goto label_31;
                }
            label_27:
                cursor1.close();
                zzah0 = null;
                goto label_43;
            label_31:
                throwable1 = throwable2;
                cursor0 = cursor1;
            label_33:
                if(cursor0 != null) {
                    cursor0.close();
                }
                throw throwable1;
            label_36:
                cursor1.close();
            }
        label_43:
            if(zzah0 == null) {
                this.zzg.put(s, null);
                this.zzb.put(s, null);
                this.zza.put(s, null);
                this.zzc.put(s, null);
                this.zzh.put(s, null);
                this.zzj.put(s, null);
                this.zzk.put(s, null);
                this.zzl.put(s, null);
                this.zzi.put(s, null);
                return;
            }
            zzfe zzfe0 = (zzfe)this.zzA(s, zzah0.zza).zzbB();
            this.zzB(s, zzfe0);
            Map map0 = zzfu.zzE(((zzff)zzfe0.zzaD()));
            this.zzg.put(s, map0);
            zzff zzff0 = (zzff)zzfe0.zzaD();
            this.zzh.put(s, zzff0);
            this.zzD(s, ((zzff)zzfe0.zzaD()));
            this.zzj.put(s, "");
            this.zzk.put(s, zzah0.zzb);
            this.zzl.put(s, zzah0.zzc);
        }
    }

    private final void zzD(String s, zzff zzff0) {
        if(zzff0.zza() != 0) {
            this.zzt.zzaA().zzj().zzb("EES programs found", zzff0.zza());
            zzgt zzgt0 = (zzgt)zzff0.zzo().get(0);
            try {
                zzc zzc0 = new zzc();
                zzc0.zzd("internal.remoteConfig", new zzfo(this, s));
                zzc0.zzd("internal.appMetadata", new zzfp(this, s));
                zzc0.zzd("internal.logger", new zzfq(this));
                zzc0.zzc(zzgt0);
                this.zzd.put(s, zzc0);
                this.zzt.zzaA().zzj().zzc("EES program loaded for appId, activities", s, zzgt0.zza().zza());
                Iterator iterator0 = zzgt0.zza().zzd().iterator();
                while(iterator0.hasNext()) {
                    iterator0.next();
                    this.zzt.zzaA().zzj().zzb("EES program activity", "");
                }
            }
            catch(zzd unused_ex) {
                this.zzt.zzaA().zzd().zzb("Failed to load EES program. appId", s);
            }
            return;
        }
        this.zzd.remove(s);
    }

    private static final Map zzE(zzff zzff0) {
        Map map0 = new ArrayMap();
        if(zzff0 != null) {
            Iterator iterator0 = zzff0.zzp().iterator();
            while(iterator0.hasNext()) {
                iterator0.next();
                map0.put("", "");
            }
        }
        return map0;
    }

    @Override  // com.google.android.gms.measurement.internal.zzaf
    public final String zza(String s, String s1) {
        this.zzg();
        this.zzC(s);
        Map map0 = (Map)this.zzg.get(s);
        return map0 == null ? null : ((String)map0.get(s1));
    }

    @Override  // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }

    final int zzc(String s, String s1) {
        this.zzg();
        this.zzC(s);
        Map map0 = (Map)this.zzi.get(s);
        if(map0 != null) {
            Integer integer0 = (Integer)map0.get(s1);
            return integer0 == null ? 1 : ((int)integer0);
        }
        return 1;
    }

    static zzc zzd(zzfu zzfu0, String s) {
        zzfu0.zzW();
        Preconditions.checkNotEmpty(s);
        if(!zzfu0.zzo(s)) {
            return null;
        }
        if(zzfu0.zzh.containsKey(s) && zzfu0.zzh.get(s) != null) {
            zzfu0.zzD(s, ((zzff)zzfu0.zzh.get(s)));
            return (zzc)zzfu0.zzd.snapshot().get(s);
        }
        zzfu0.zzC(s);
        return (zzc)zzfu0.zzd.snapshot().get(s);
    }

    protected final zzff zze(String s) {
        this.zzW();
        this.zzg();
        Preconditions.checkNotEmpty(s);
        this.zzC(s);
        return (zzff)this.zzh.get(s);
    }

    protected final String zzf(String s) {
        this.zzg();
        return (String)this.zzl.get(s);
    }

    protected final String zzh(String s) {
        this.zzg();
        return (String)this.zzk.get(s);
    }

    final String zzi(String s) {
        this.zzg();
        this.zzC(s);
        return (String)this.zzj.get(s);
    }

    static Map zzj(zzfu zzfu0) {
        return zzfu0.zzg;
    }

    final Set zzk(String s) {
        this.zzg();
        this.zzC(s);
        return (Set)this.zza.get(s);
    }

    protected final void zzl(String s) {
        this.zzg();
        this.zzk.put(s, null);
    }

    final void zzm(String s) {
        this.zzg();
        this.zzh.remove(s);
    }

    final boolean zzn(String s) {
        this.zzg();
        zzff zzff0 = this.zze(s);
        return zzff0 == null ? false : zzff0.zzs();
    }

    public final boolean zzo(String s) {
        if(TextUtils.isEmpty(s)) {
            return false;
        }
        zzff zzff0 = (zzff)this.zzh.get(s);
        return zzff0 == null ? false : zzff0.zza() != 0;
    }

    final boolean zzp(String s) {
        return "1".equals(this.zza(s, "measurement.upload.blacklist_internal"));
    }

    final boolean zzq(String s, String s1) {
        this.zzg();
        this.zzC(s);
        if("ecommerce_purchase".equals(s1)) {
            return true;
        }
        if(!"purchase".equals(s1) && !"refund".equals(s1)) {
            Map map0 = (Map)this.zzc.get(s);
            if(map0 != null) {
                Boolean boolean0 = (Boolean)map0.get(s1);
                return boolean0 == null ? false : boolean0.booleanValue();
            }
            return false;
        }
        return true;
    }

    final boolean zzr(String s, String s1) {
        this.zzg();
        this.zzC(s);
        if(this.zzp(s) && zzlp.zzaj(s1)) {
            return true;
        }
        if(this.zzs(s) && zzlp.zzak(s1)) {
            return true;
        }
        Map map0 = (Map)this.zzb.get(s);
        if(map0 != null) {
            Boolean boolean0 = (Boolean)map0.get(s1);
            return boolean0 == null ? false : boolean0.booleanValue();
        }
        return false;
    }

    final boolean zzs(String s) {
        return "1".equals(this.zza(s, "measurement.upload.blacklist_public"));
    }

    protected final boolean zzt(String s, byte[] arr_b, String s1, String s2) {
        this.zzW();
        this.zzg();
        Preconditions.checkNotEmpty(s);
        zzfe zzfe0 = (zzfe)this.zzA(s, arr_b).zzbB();
        this.zzB(s, zzfe0);
        this.zzD(s, ((zzff)zzfe0.zzaD()));
        zzff zzff0 = (zzff)zzfe0.zzaD();
        this.zzh.put(s, zzff0);
        this.zzj.put(s, "");
        this.zzk.put(s, s1);
        this.zzl.put(s, s2);
        Map map0 = zzfu.zzE(((zzff)zzfe0.zzaD()));
        this.zzg.put(s, map0);
        this.zzf.zzh().zzB(s, new ArrayList(zzfe0.zzf()));
        try {
            zzfe0.zzc();
            arr_b = ((zzff)zzfe0.zzaD()).zzbx();
        }
        catch(RuntimeException runtimeException0) {
            this.zzt.zzaA().zzk().zzc("Unable to serialize reduced-size config. Storing full config instead. appId", zzet.zzn(s), runtimeException0);
        }
        zzak zzak0 = this.zzf.zzh();
        Preconditions.checkNotEmpty(s);
        zzak0.zzg();
        zzak0.zzW();
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("remote_config", arr_b);
        contentValues0.put("config_last_modified_time", s1);
        contentValues0.put("e_tag", s2);
        try {
            if(((long)zzak0.zzh().update("apps", contentValues0, "app_id = ?", new String[]{s})) == 0L) {
                zzak0.zzt.zzaA().zzd().zzb("Failed to update remote config (got 0). appId", zzet.zzn(s));
            }
        }
        catch(SQLiteException sQLiteException0) {
            zzak0.zzt.zzaA().zzd().zzc("Error storing remote config. appId", zzet.zzn(s), sQLiteException0);
        }
        zzff zzff1 = (zzff)zzfe0.zzaD();
        this.zzh.put(s, zzff1);
        return true;
    }

    final boolean zzu(String s) {
        this.zzg();
        this.zzC(s);
        return this.zza.get(s) != null && ((Set)this.zza.get(s)).contains("app_instance_id");
    }

    final boolean zzv(String s) {
        this.zzg();
        this.zzC(s);
        return this.zza.get(s) != null && (((Set)this.zza.get(s)).contains("device_model") || ((Set)this.zza.get(s)).contains("device_info"));
    }

    final boolean zzw(String s) {
        this.zzg();
        this.zzC(s);
        return this.zza.get(s) != null && ((Set)this.zza.get(s)).contains("enhanced_user_id");
    }

    final boolean zzx(String s) {
        this.zzg();
        this.zzC(s);
        return this.zza.get(s) != null && ((Set)this.zza.get(s)).contains("google_signals");
    }

    final boolean zzy(String s) {
        this.zzg();
        this.zzC(s);
        return this.zza.get(s) != null && (((Set)this.zza.get(s)).contains("os_version") || ((Set)this.zza.get(s)).contains("device_info"));
    }

    final boolean zzz(String s) {
        this.zzg();
        this.zzC(s);
        return this.zza.get(s) != null && ((Set)this.zza.get(s)).contains("user_id");
    }
}

