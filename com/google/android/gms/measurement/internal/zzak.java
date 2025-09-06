package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.measurement.zzeh;
import com.google.android.gms.internal.measurement.zzei;
import com.google.android.gms.internal.measurement.zzej;
import com.google.android.gms.internal.measurement.zzek;
import com.google.android.gms.internal.measurement.zzel;
import com.google.android.gms.internal.measurement.zzem;
import com.google.android.gms.internal.measurement.zzes;
import com.google.android.gms.internal.measurement.zzfs;
import com.google.android.gms.internal.measurement.zzft;
import com.google.android.gms.internal.measurement.zzfx;
import com.google.android.gms.internal.measurement.zzgc;
import com.google.android.gms.internal.measurement.zzgd;
import com.google.android.gms.internal.measurement.zzop;
import com.google.android.gms.internal.measurement.zzpz;
import com.google.android.gms.internal.measurement.zzqu;
import com.google.android.gms.internal.measurement.zzrd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import jeb.synthetic.TWR;

final class zzak extends zzku {
    private static final String[] zza;
    private static final String[] zzb;
    private static final String[] zzc;
    private static final String[] zzd;
    private static final String[] zze;
    private static final String[] zzg;
    private static final String[] zzh;
    private static final String[] zzi;
    private final zzaj zzj;
    private final zzkq zzk;

    static {
        zzak.zza = new String[]{"last_bundled_timestamp", "ALTER TABLE events ADD COLUMN last_bundled_timestamp INTEGER;", "last_bundled_day", "ALTER TABLE events ADD COLUMN last_bundled_day INTEGER;", "last_sampled_complex_event_id", "ALTER TABLE events ADD COLUMN last_sampled_complex_event_id INTEGER;", "last_sampling_rate", "ALTER TABLE events ADD COLUMN last_sampling_rate INTEGER;", "last_exempt_from_sampling", "ALTER TABLE events ADD COLUMN last_exempt_from_sampling INTEGER;", "current_session_count", "ALTER TABLE events ADD COLUMN current_session_count INTEGER;"};
        zzak.zzb = new String[]{"origin", "ALTER TABLE user_attributes ADD COLUMN origin TEXT;"};
        zzak.zzc = new String[]{"app_version", "ALTER TABLE apps ADD COLUMN app_version TEXT;", "app_store", "ALTER TABLE apps ADD COLUMN app_store TEXT;", "gmp_version", "ALTER TABLE apps ADD COLUMN gmp_version INTEGER;", "dev_cert_hash", "ALTER TABLE apps ADD COLUMN dev_cert_hash INTEGER;", "measurement_enabled", "ALTER TABLE apps ADD COLUMN measurement_enabled INTEGER;", "last_bundle_start_timestamp", "ALTER TABLE apps ADD COLUMN last_bundle_start_timestamp INTEGER;", "day", "ALTER TABLE apps ADD COLUMN day INTEGER;", "daily_public_events_count", "ALTER TABLE apps ADD COLUMN daily_public_events_count INTEGER;", "daily_events_count", "ALTER TABLE apps ADD COLUMN daily_events_count INTEGER;", "daily_conversions_count", "ALTER TABLE apps ADD COLUMN daily_conversions_count INTEGER;", "remote_config", "ALTER TABLE apps ADD COLUMN remote_config BLOB;", "config_fetched_time", "ALTER TABLE apps ADD COLUMN config_fetched_time INTEGER;", "failed_config_fetch_time", "ALTER TABLE apps ADD COLUMN failed_config_fetch_time INTEGER;", "app_version_int", "ALTER TABLE apps ADD COLUMN app_version_int INTEGER;", "firebase_instance_id", "ALTER TABLE apps ADD COLUMN firebase_instance_id TEXT;", "daily_error_events_count", "ALTER TABLE apps ADD COLUMN daily_error_events_count INTEGER;", "daily_realtime_events_count", "ALTER TABLE apps ADD COLUMN daily_realtime_events_count INTEGER;", "health_monitor_sample", "ALTER TABLE apps ADD COLUMN health_monitor_sample TEXT;", "android_id", "ALTER TABLE apps ADD COLUMN android_id INTEGER;", "adid_reporting_enabled", "ALTER TABLE apps ADD COLUMN adid_reporting_enabled INTEGER;", "ssaid_reporting_enabled", "ALTER TABLE apps ADD COLUMN ssaid_reporting_enabled INTEGER;", "admob_app_id", "ALTER TABLE apps ADD COLUMN admob_app_id TEXT;", "linked_admob_app_id", "ALTER TABLE apps ADD COLUMN linked_admob_app_id TEXT;", "dynamite_version", "ALTER TABLE apps ADD COLUMN dynamite_version INTEGER;", "safelisted_events", "ALTER TABLE apps ADD COLUMN safelisted_events TEXT;", "ga_app_id", "ALTER TABLE apps ADD COLUMN ga_app_id TEXT;", "config_last_modified_time", "ALTER TABLE apps ADD COLUMN config_last_modified_time TEXT;", "e_tag", "ALTER TABLE apps ADD COLUMN e_tag TEXT;", "session_stitching_token", "ALTER TABLE apps ADD COLUMN session_stitching_token TEXT;", "sgtm_upload_enabled", "ALTER TABLE apps ADD COLUMN sgtm_upload_enabled INTEGER;", "target_os_version", "ALTER TABLE apps ADD COLUMN target_os_version INTEGER;", "session_stitching_token_hash", "ALTER TABLE apps ADD COLUMN session_stitching_token_hash INTEGER;"};
        zzak.zzd = new String[]{"realtime", "ALTER TABLE raw_events ADD COLUMN realtime INTEGER;"};
        zzak.zze = new String[]{"has_realtime", "ALTER TABLE queue ADD COLUMN has_realtime INTEGER;", "retry_count", "ALTER TABLE queue ADD COLUMN retry_count INTEGER;"};
        zzak.zzg = new String[]{"session_scoped", "ALTER TABLE event_filters ADD COLUMN session_scoped BOOLEAN;"};
        zzak.zzh = new String[]{"session_scoped", "ALTER TABLE property_filters ADD COLUMN session_scoped BOOLEAN;"};
        zzak.zzi = new String[]{"previous_install_count", "ALTER TABLE app2 ADD COLUMN previous_install_count INTEGER;"};
    }

    zzak(zzlh zzlh0) {
        super(zzlh0);
        this.zzk = new zzkq(this.zzt.zzax());
        this.zzj = new zzaj(this, this.zzt.zzaw(), "google_app_measurement.db");
    }

    public final void zzA(String s, String s1) {
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotEmpty(s1);
        this.zzg();
        this.zzW();
        try {
            this.zzh().delete("user_attributes", "app_id=? and name=?", new String[]{s, s1});
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzd("Error deleting user property. appId", zzet.zzn(s), this.zzt.zzj().zzf(s1), sQLiteException0);
        }
    }

    final void zzB(String s, List list0) {
        SQLiteDatabase sQLiteDatabase2;
        int v4;
        zzei zzei0;
        boolean z;
        Preconditions.checkNotNull(list0);
        for(int v = 0; v < list0.size(); ++v) {
            zzeh zzeh0 = (zzeh)((zzei)list0.get(v)).zzbB();
            if(zzeh0.zza() != 0) {
                for(int v1 = 0; v1 < zzeh0.zza(); ++v1) {
                    zzej zzej0 = (zzej)zzeh0.zze(v1).zzbB();
                    zzej zzej1 = (zzej)zzej0.zzaA();
                    String s1 = zzhc.zzb("");
                    if(s1 == null) {
                        z = false;
                    }
                    else {
                        zzej1.zzb(s1);
                        z = true;
                    }
                    for(int v2 = 0; v2 < zzej0.zza(); ++v2) {
                        zzem zzem0 = zzej0.zzd(v2);
                        String s2 = zziq.zzb("", zzhd.zza, zzhd.zzb);
                        if(s2 != null) {
                            zzel zzel0 = (zzel)zzem0.zzbB();
                            zzel0.zza(s2);
                            zzej1.zzc(v2, ((zzem)zzel0.zzaD()));
                            z = true;
                        }
                    }
                    if(z) {
                        zzeh0.zzc(v1, zzej1);
                        list0.set(v, ((zzei)zzeh0.zzaD()));
                    }
                }
            }
            if(zzeh0.zzb() != 0) {
                for(int v3 = 0; v3 < zzeh0.zzb(); ++v3) {
                    com.google.android.gms.internal.measurement.zzet zzet0 = zzeh0.zzf(v3);
                    String s3 = zziq.zzb("", zzhe.zza, zzhe.zzb);
                    if(s3 != null) {
                        zzes zzes0 = (zzes)zzet0.zzbB();
                        zzes0.zza(s3);
                        zzeh0.zzd(v3, zzes0);
                        list0.set(v, ((zzei)zzeh0.zzaD()));
                    }
                }
            }
        }
        this.zzW();
        this.zzg();
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotNull(list0);
        SQLiteDatabase sQLiteDatabase0 = this.zzh();
        sQLiteDatabase0.beginTransaction();
        try {
            this.zzW();
            this.zzg();
            Preconditions.checkNotEmpty(s);
            SQLiteDatabase sQLiteDatabase1 = this.zzh();
            sQLiteDatabase1.delete("property_filters", "app_id=?", new String[]{s});
            sQLiteDatabase1.delete("event_filters", "app_id=?", new String[]{s});
            Iterator iterator0 = list0.iterator();
        alab1:
            while(true) {
            label_58:
                if(!iterator0.hasNext()) {
                    goto label_115;
                }
                Object object0 = iterator0.next();
                zzei0 = (zzei)object0;
                this.zzW();
                this.zzg();
                Preconditions.checkNotEmpty(s);
                Preconditions.checkNotNull(zzei0);
                if(zzei0.zzk()) {
                    v4 = zzei0.zza();
                    for(Object object1: zzei0.zzg()) {
                        if(!((zzek)object1).zzp()) {
                            this.zzt.zzaA().zzk().zzc("Event filter with no ID. Audience definition ignored. appId, audienceId", zzet.zzn(s), v4);
                            continue alab1;
                        }
                        if(false) {
                            break;
                        }
                    }
                    for(Object object2: zzei0.zzh()) {
                        if(!((com.google.android.gms.internal.measurement.zzet)object2).zzj()) {
                            this.zzt.zzaA().zzk().zzc("Property filter with no ID. Audience definition ignored. appId, audienceId", zzet.zzn(s), v4);
                            continue alab1;
                        }
                        if(false) {
                            break;
                        }
                    }
                    Iterator iterator3 = zzei0.zzg().iterator();
                    if(iterator3.hasNext()) {
                        Object object3 = iterator3.next();
                        zzek zzek0 = (zzek)object3;
                        this.zzW();
                        this.zzg();
                        Preconditions.checkNotEmpty(s);
                        Preconditions.checkNotNull(zzek0);
                        this.zzt.zzaA().zzk().zzd("Event filter had no event name. Audience definition ignored. appId, audienceId, filterId", zzet.zzn(s), v4, String.valueOf((zzek0.zzp() ? zzek0.zzb() : null)));
                        sQLiteDatabase2 = sQLiteDatabase0;
                        goto label_102;
                    }
                    break;
                }
                goto label_110;
            }
        }
        catch(Throwable throwable0) {
            sQLiteDatabase2 = sQLiteDatabase0;
            sQLiteDatabase2.endTransaction();
            throw throwable0;
        }
        try {
            sQLiteDatabase2 = sQLiteDatabase0;
            Iterator iterator4 = zzei0.zzh().iterator();
            if(iterator4.hasNext()) {
                Object object4 = iterator4.next();
                com.google.android.gms.internal.measurement.zzet zzet1 = (com.google.android.gms.internal.measurement.zzet)object4;
                this.zzW();
                this.zzg();
                Preconditions.checkNotEmpty(s);
                Preconditions.checkNotNull(zzet1);
                this.zzt.zzaA().zzk().zzd("Property filter had no property name. Audience definition ignored. appId, audienceId, filterId", zzet.zzn(s), v4, String.valueOf((zzet1.zzj() ? zzet1.zza() : null)));
            label_102:
                this.zzW();
                this.zzg();
                Preconditions.checkNotEmpty(s);
                SQLiteDatabase sQLiteDatabase3 = this.zzh();
                sQLiteDatabase3.delete("property_filters", "app_id=? and audience_id=?", new String[]{s, String.valueOf(v4)});
                sQLiteDatabase3.delete("event_filters", "app_id=? and audience_id=?", new String[]{s, String.valueOf(v4)});
            }
            sQLiteDatabase0 = sQLiteDatabase2;
            goto label_58;
        }
        catch(Throwable throwable0) {
            sQLiteDatabase2.endTransaction();
            throw throwable0;
        }
        try {
        label_110:
            this.zzt.zzaA().zzk().zzb("Audience with no ID. appId", zzet.zzn(s));
            goto label_58;
        }
        catch(Throwable throwable0) {
            sQLiteDatabase2 = sQLiteDatabase0;
            sQLiteDatabase2.endTransaction();
            throw throwable0;
        }
        try {
        label_115:
            sQLiteDatabase2 = sQLiteDatabase0;
            ArrayList arrayList0 = new ArrayList();
            for(Object object5: list0) {
                zzei zzei1 = (zzei)object5;
                arrayList0.add((zzei1.zzk() ? zzei1.zza() : null));
            }
            Preconditions.checkNotEmpty(s);
            this.zzW();
            this.zzg();
            SQLiteDatabase sQLiteDatabase4 = this.zzh();
            try {
                long v5 = this.zzZ("select count(1) from audience_filter_values where app_id=?", new String[]{s});
            }
            catch(SQLiteException sQLiteException0) {
                this.zzt.zzaA().zzd().zzc("Database error querying filters. appId", zzet.zzn(s), sQLiteException0);
                sQLiteDatabase2.setTransactionSuccessful();
                goto label_149;
            }
            int v7 = Math.max(0, Math.min(2000, this.zzt.zzf().zze(s, zzeg.zzF)));
            if(v5 > ((long)v7)) {
                ArrayList arrayList1 = new ArrayList();
                for(int v6 = 0; true; ++v6) {
                    if(v6 >= arrayList0.size()) {
                        sQLiteDatabase4.delete("audience_filter_values", "audience_id in (select audience_id from audience_filter_values where app_id=? and audience_id not in " + ("(" + TextUtils.join(",", arrayList1) + ")") + " order by rowid desc limit -1 offset ?)", new String[]{s, Integer.toString(v7)});
                        break;
                    }
                    Integer integer0 = (Integer)arrayList0.get(v6);
                    if(integer0 == null) {
                        break;
                    }
                    arrayList1.add(Integer.toString(((int)integer0)));
                }
            }
            sQLiteDatabase2.setTransactionSuccessful();
        }
        catch(Throwable throwable0) {
            sQLiteDatabase2.endTransaction();
            throw throwable0;
        }
    label_149:
        sQLiteDatabase2.endTransaction();
    }

    public final void zzC() {
        this.zzW();
        this.zzh().setTransactionSuccessful();
    }

    public final void zzD(zzh zzh0) {
        Preconditions.checkNotNull(zzh0);
        this.zzg();
        this.zzW();
        String s = zzh0.zzv();
        Preconditions.checkNotNull(s);
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("app_id", s);
        contentValues0.put("app_instance_id", zzh0.zzw());
        contentValues0.put("gmp_app_id", zzh0.zzA());
        contentValues0.put("resettable_device_id_hash", zzh0.zzC());
        contentValues0.put("last_bundle_index", zzh0.zzo());
        contentValues0.put("last_bundle_start_timestamp", zzh0.zzp());
        contentValues0.put("last_bundle_end_timestamp", zzh0.zzn());
        contentValues0.put("app_version", zzh0.zzy());
        contentValues0.put("app_store", zzh0.zzx());
        contentValues0.put("gmp_version", zzh0.zzm());
        contentValues0.put("dev_cert_hash", zzh0.zzj());
        contentValues0.put("measurement_enabled", Boolean.valueOf(zzh0.zzan()));
        contentValues0.put("day", zzh0.zzi());
        contentValues0.put("daily_public_events_count", zzh0.zzg());
        contentValues0.put("daily_events_count", zzh0.zzf());
        contentValues0.put("daily_conversions_count", zzh0.zzd());
        contentValues0.put("config_fetched_time", zzh0.zzc());
        contentValues0.put("failed_config_fetch_time", zzh0.zzl());
        contentValues0.put("app_version_int", zzh0.zzb());
        contentValues0.put("firebase_instance_id", zzh0.zzz());
        contentValues0.put("daily_error_events_count", zzh0.zze());
        contentValues0.put("daily_realtime_events_count", zzh0.zzh());
        contentValues0.put("health_monitor_sample", zzh0.zzB());
        zzh0.zza();
        contentValues0.put("android_id", 0L);
        contentValues0.put("adid_reporting_enabled", Boolean.valueOf(zzh0.zzam()));
        contentValues0.put("admob_app_id", zzh0.zzt());
        contentValues0.put("dynamite_version", zzh0.zzk());
        contentValues0.put("session_stitching_token", zzh0.zzD());
        contentValues0.put("sgtm_upload_enabled", Boolean.valueOf(zzh0.zzap()));
        contentValues0.put("target_os_version", zzh0.zzr());
        contentValues0.put("session_stitching_token_hash", zzh0.zzq());
        List list0 = zzh0.zzE();
        if(list0 != null) {
            if(list0.isEmpty()) {
                this.zzt.zzaA().zzk().zzb("Safelisted events should not be an empty list. appId", s);
            }
            else {
                contentValues0.put("safelisted_events", TextUtils.join(",", list0));
            }
        }
        zzop.zzc();
        if(this.zzt.zzf().zzs(null, zzeg.zzak) && !contentValues0.containsKey("safelisted_events")) {
            contentValues0.put("safelisted_events", null);
        }
        try {
            SQLiteDatabase sQLiteDatabase0 = this.zzh();
            if(((long)sQLiteDatabase0.update("apps", contentValues0, "app_id = ?", new String[]{s})) == 0L && sQLiteDatabase0.insertWithOnConflict("apps", null, contentValues0, 5) == -1L) {
                this.zzt.zzaA().zzd().zzb("Failed to insert/update app (got -1). appId", zzet.zzn(s));
            }
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzc("Error storing app. appId", zzet.zzn(s), sQLiteException0);
        }
    }

    public final void zzE(zzaq zzaq0) {
        Preconditions.checkNotNull(zzaq0);
        this.zzg();
        this.zzW();
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("app_id", zzaq0.zza);
        contentValues0.put("name", zzaq0.zzb);
        contentValues0.put("lifetime_count", zzaq0.zzc);
        contentValues0.put("current_bundle_count", zzaq0.zzd);
        contentValues0.put("last_fire_timestamp", zzaq0.zzf);
        contentValues0.put("last_bundled_timestamp", zzaq0.zzg);
        contentValues0.put("last_bundled_day", zzaq0.zzh);
        contentValues0.put("last_sampled_complex_event_id", zzaq0.zzi);
        contentValues0.put("last_sampling_rate", zzaq0.zzj);
        contentValues0.put("current_session_count", zzaq0.zze);
        contentValues0.put("last_exempt_from_sampling", (zzaq0.zzk == null || !zzaq0.zzk.booleanValue() ? null : 1L));
        try {
            if(this.zzh().insertWithOnConflict("events", null, contentValues0, 5) == -1L) {
                this.zzt.zzaA().zzd().zzb("Failed to insert/update event aggregates (got -1). appId", zzet.zzn(zzaq0.zza));
            }
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzc("Error storing event aggregates. appId", zzet.zzn(zzaq0.zza), sQLiteException0);
        }
    }

    public final boolean zzF() {
        return this.zzZ("select count(1) > 0 from raw_events", null) != 0L;
    }

    public final boolean zzG() {
        return this.zzZ("select count(1) > 0 from queue where has_realtime = 1", null) != 0L;
    }

    public final boolean zzH() {
        return this.zzZ("select count(1) > 0 from raw_events where realtime = 1", null) != 0L;
    }

    protected final boolean zzI() {
        return this.zzt.zzaw().getDatabasePath("google_app_measurement.db").exists();
    }

    public final boolean zzJ(String s, Long long0, long v, zzft zzft0) {
        this.zzg();
        this.zzW();
        Preconditions.checkNotNull(zzft0);
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotNull(long0);
        byte[] arr_b = zzft0.zzbx();
        this.zzt.zzaA().zzj().zzc("Saving complex main event, appId, data size", this.zzt.zzj().zzd(s), ((int)arr_b.length));
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("app_id", s);
        contentValues0.put("event_id", long0);
        contentValues0.put("children_to_process", v);
        contentValues0.put("main_event", arr_b);
        try {
            if(this.zzh().insertWithOnConflict("main_event_params", null, contentValues0, 5) == -1L) {
                this.zzt.zzaA().zzd().zzb("Failed to insert complex main event (got -1). appId", zzet.zzn(s));
                return false;
            }
            return true;
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzc("Error storing complex main event. appId", zzet.zzn(s), sQLiteException0);
            return false;
        }
    }

    public final boolean zzK(zzac zzac0) {
        Preconditions.checkNotNull(zzac0);
        this.zzg();
        this.zzW();
        String s = zzac0.zza;
        Preconditions.checkNotNull(s);
        if(this.zzp(s, zzac0.zzc.zzb) == null && this.zzZ("SELECT COUNT(1) FROM conditional_properties WHERE app_id=?", new String[]{s}) >= 1000L) {
            return false;
        }
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("app_id", s);
        contentValues0.put("origin", zzac0.zzb);
        contentValues0.put("name", zzac0.zzc.zzb);
        zzak.zzV(contentValues0, "value", Preconditions.checkNotNull(zzac0.zzc.zza()));
        contentValues0.put("active", Boolean.valueOf(zzac0.zze));
        contentValues0.put("trigger_event_name", zzac0.zzf);
        contentValues0.put("trigger_timeout", zzac0.zzh);
        contentValues0.put("timed_out_event", this.zzt.zzv().zzap(zzac0.zzg));
        contentValues0.put("creation_timestamp", zzac0.zzd);
        contentValues0.put("triggered_event", this.zzt.zzv().zzap(zzac0.zzi));
        contentValues0.put("triggered_timestamp", zzac0.zzc.zzc);
        contentValues0.put("time_to_live", zzac0.zzj);
        contentValues0.put("expired_event", this.zzt.zzv().zzap(zzac0.zzk));
        try {
            if(this.zzh().insertWithOnConflict("conditional_properties", null, contentValues0, 5) == -1L) {
                this.zzt.zzaA().zzd().zzb("Failed to insert/update conditional user property (got -1)", zzet.zzn(s));
                return true;
            }
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzc("Error storing conditional user property", zzet.zzn(s), sQLiteException0);
        }
        return true;
    }

    public final boolean zzL(zzlm zzlm0) {
        Preconditions.checkNotNull(zzlm0);
        this.zzg();
        this.zzW();
        if(this.zzp(zzlm0.zza, zzlm0.zzc) == null) {
            if(zzlp.zzak(zzlm0.zzc)) {
                if(this.zzZ("select count(1) from user_attributes where app_id=? and name not like \'!_%\' escape \'!\'", new String[]{zzlm0.zza}) >= ((long)this.zzt.zzf().zzf(zzlm0.zza, zzeg.zzG, 25, 100))) {
                    return false;
                }
            }
            else if(!"_npa".equals(zzlm0.zzc) && this.zzZ("select count(1) from user_attributes where app_id=? and origin=? AND name like \'!_%\' escape \'!\'", new String[]{zzlm0.zza, zzlm0.zzb}) >= 25L) {
                return false;
            }
        }
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("app_id", zzlm0.zza);
        contentValues0.put("origin", zzlm0.zzb);
        contentValues0.put("name", zzlm0.zzc);
        contentValues0.put("set_timestamp", zzlm0.zzd);
        zzak.zzV(contentValues0, "value", zzlm0.zze);
        try {
            if(this.zzh().insertWithOnConflict("user_attributes", null, contentValues0, 5) == -1L) {
                this.zzt.zzaA().zzd().zzb("Failed to insert/update user property (got -1). appId", zzet.zzn(zzlm0.zza));
                return true;
            }
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzc("Error storing user property. appId", zzet.zzn(zzlm0.zza), sQLiteException0);
        }
        return true;
    }

    static String[] zzM() {
        return zzak.zzi;
    }

    static String[] zzN() {
        return zzak.zzc;
    }

    static String[] zzO() {
        return zzak.zza;
    }

    static String[] zzP() {
        return zzak.zzg;
    }

    static String[] zzQ() {
        return zzak.zzh;
    }

    static String[] zzR() {
        return zzak.zze;
    }

    static String[] zzS() {
        return zzak.zzd;
    }

    static String[] zzT() {
        return zzak.zzb;
    }

    public final void zzU(String s, long v, long v1, zzle zzle0) {
        zzfs zzfs0;
        String[] arr_s2;
        String s5;
        zzgd zzgd0;
        String s4;
        String s3;
        SQLiteDatabase sQLiteDatabase0;
        String s2;
        Preconditions.checkNotNull(zzle0);
        this.zzg();
        this.zzW();
        String s1 = null;
        try {
            s2 = "";
            sQLiteDatabase0 = this.zzh();
            if(TextUtils.isEmpty(null)) {
                int v2 = Long.compare(v1, -1L);
                String[] arr_s = v2 == 0 ? new String[]{String.valueOf(v)} : new String[]{String.valueOf(v1), String.valueOf(v)};
                if(v2 != 0) {
                    s2 = "rowid <= ? and ";
                }
                s3 = sQLiteDatabase0.rawQuery("select app_id, metadata_fingerprint from raw_events where " + s2 + "app_id in (select app_id from apps where config_fetched_time >= ?) order by rowid limit 1;", arr_s);
                goto label_12;
            }
            else {
                goto label_20;
            }
            return;
        }
        catch(SQLiteException sQLiteException0) {
            s3 = null;
            goto label_81;
        }
        catch(Throwable throwable0) {
            goto label_87;
        }
        try {
        label_12:
            if(((Cursor)s3).moveToFirst()) {
                s1 = ((Cursor)s3).getString(0);
                s4 = ((Cursor)s3).getString(1);
                ((Cursor)s3).close();
                goto label_34;
            }
        }
        catch(SQLiteException sQLiteException0) {
            goto label_81;
        }
        catch(Throwable throwable0) {
            goto label_86;
        }
        if(s3 != null) {
            ((Cursor)s3).close();
            return;
            try {
            label_20:
                int v3 = Long.compare(v1, -1L);
                String[] arr_s1 = v3 == 0 ? new String[]{0} : new String[]{0, String.valueOf(v1)};
                if(v3 != 0) {
                    s2 = " and rowid <= ?";
                }
                s3 = sQLiteDatabase0.rawQuery("select metadata_fingerprint from raw_events where app_id = ?" + s2 + " order by rowid limit 1;", arr_s1);
            }
            catch(SQLiteException sQLiteException0) {
                s3 = null;
                goto label_81;
            }
            catch(Throwable throwable0) {
                goto label_87;
            }
            try {
                if(((Cursor)s3).moveToFirst()) {
                    s4 = ((Cursor)s3).getString(0);
                    ((Cursor)s3).close();
                label_34:
                    s3 = sQLiteDatabase0.query("raw_events_metadata", new String[]{"metadata"}, "app_id = ? and metadata_fingerprint = ?", new String[]{s1, s4}, null, null, "rowid", "2");
                    if(((Cursor)s3).moveToFirst()) {
                        goto label_40;
                    }
                    else {
                        this.zzt.zzaA().zzd().zzb("Raw event metadata record is missing. appId", zzet.zzn(s1));
                        if(s3 != null) {
                            goto label_38;
                        }
                    }
                }
                else {
                    goto label_93;
                }
                return;
            }
            catch(SQLiteException sQLiteException0) {
                goto label_81;
            }
            catch(Throwable throwable0) {
                goto label_86;
            }
        label_38:
            ((Cursor)s3).close();
            return;
            try {
            label_40:
                byte[] arr_b = ((Cursor)s3).getBlob(0);
                try {
                    zzgd0 = (zzgd)((zzgc)zzlj.zzm(zzgd.zzu(), arr_b)).zzaD();
                    goto label_48;
                }
                catch(IOException iOException0) {
                }
                this.zzt.zzaA().zzd().zzc("Data loss. Failed to merge raw event metadata. appId", zzet.zzn(s1), iOException0);
                if(s3 != null) {
                    goto label_46;
                }
                return;
            }
            catch(SQLiteException sQLiteException0) {
                goto label_81;
            }
            catch(Throwable throwable0) {
                goto label_86;
            }
        label_46:
            ((Cursor)s3).close();
            return;
            try {
            label_48:
                if(((Cursor)s3).moveToNext()) {
                    this.zzt.zzaA().zzk().zzb("Get multiple raw event metadata records, expected one. appId", zzet.zzn(s1));
                }
                ((Cursor)s3).close();
                Preconditions.checkNotNull(zzgd0);
                zzle0.zza = zzgd0;
                if(v1 == -1L) {
                    s5 = "app_id = ? and metadata_fingerprint = ?";
                    arr_s2 = new String[]{s1, s4};
                }
                else {
                    s5 = "app_id = ? and metadata_fingerprint = ? and rowid <= ?";
                    arr_s2 = new String[]{s1, s4, String.valueOf(v1)};
                }
                s3 = sQLiteDatabase0.query("raw_events", new String[]{"rowid", "name", "timestamp", "data"}, s5, arr_s2, null, null, "rowid", null);
                if(((Cursor)s3).moveToFirst()) {
                    while(true) {
                        long v4 = ((Cursor)s3).getLong(0);
                        byte[] arr_b1 = ((Cursor)s3).getBlob(3);
                        try {
                            zzfs0 = (zzfs)zzlj.zzm(zzft.zze(), arr_b1);
                        }
                        catch(IOException iOException1) {
                            this.zzt.zzaA().zzd().zzc("Data loss. Failed to merge raw event. appId", zzet.zzn(s1), iOException1);
                            goto label_71;
                        }
                        zzfs0.zzi(((Cursor)s3).getString(1));
                        zzfs0.zzm(((Cursor)s3).getLong(2));
                        if(!zzle0.zza(v4, ((zzft)zzfs0.zzaD()))) {
                            goto label_75;
                        }
                    label_71:
                        if(((Cursor)s3).moveToNext()) {
                            continue;
                        }
                        goto label_72;
                    }
                }
                else {
                    goto label_78;
                }
                return;
            }
            catch(SQLiteException sQLiteException0) {
                goto label_81;
            }
            catch(Throwable throwable0) {
                goto label_86;
            }
        label_72:
            if(s3 != null) {
                ((Cursor)s3).close();
                return;
            label_75:
                if(s3 != null) {
                    ((Cursor)s3).close();
                    return;
                    try {
                    label_78:
                        this.zzt.zzaA().zzk().zzb("Raw event data disappeared while in transaction. appId", zzet.zzn(s1));
                        goto label_90;
                    }
                    catch(SQLiteException sQLiteException0) {
                        try {
                        label_81:
                            this.zzt.zzaA().zzd().zzc("Data loss. Error selecting raw event. appId", zzet.zzn(s1), sQLiteException0);
                            if(s3 != null) {
                                goto label_83;
                            }
                            return;
                        }
                        catch(Throwable throwable0) {
                            goto label_86;
                        }
                    label_83:
                        ((Cursor)s3).close();
                        return;
                    }
                    catch(Throwable throwable0) {
                    label_86:
                        s1 = s3;
                    }
                label_87:
                    if(s1 != null) {
                        ((Cursor)s1).close();
                    }
                    throw throwable0;
                label_90:
                    if(s3 != null) {
                        ((Cursor)s3).close();
                        return;
                    label_93:
                        if(s3 != null) {
                            ((Cursor)s3).close();
                        }
                    }
                }
            }
        }
    }

    static final void zzV(ContentValues contentValues0, String s, Object object0) {
        new String("value");
        Preconditions.checkNotNull(object0);
        if(object0 instanceof String) {
            contentValues0.put("value", ((String)object0));
            return;
        }
        if(object0 instanceof Long) {
            contentValues0.put("value", ((Long)object0));
            return;
        }
        if(!(object0 instanceof Double)) {
            throw new IllegalArgumentException("Invalid value type");
        }
        contentValues0.put("value", ((Double)object0));
    }

    private final long zzZ(String s, String[] arr_s) {
        long v;
        SQLiteDatabase sQLiteDatabase0 = this.zzh();
        Cursor cursor0 = null;
        try {
            try {
                cursor0 = sQLiteDatabase0.rawQuery(s, arr_s);
                if(!cursor0.moveToFirst()) {
                    throw new SQLiteException("Database returned empty set");
                }
                v = cursor0.getLong(0);
            }
            catch(SQLiteException sQLiteException0) {
                this.zzt.zzaA().zzd().zzc("Database error", s, sQLiteException0);
                throw sQLiteException0;
            }
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(cursor0, throwable0);
            throw throwable0;
        }
        cursor0.close();
        return v;
    }

    public final int zza(String s, String s1) {
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotEmpty(s1);
        this.zzg();
        this.zzW();
        try {
            return this.zzh().delete("conditional_properties", "app_id=? and name=?", new String[]{s, s1});
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzd("Error deleting conditional property", zzet.zzn(s), this.zzt.zzj().zzf(s1), sQLiteException0);
            return 0;
        }
    }

    private final long zzaa(String s, String[] arr_s, long v) {
        long v1;
        SQLiteDatabase sQLiteDatabase0 = this.zzh();
        Cursor cursor0 = null;
        try {
            try {
                cursor0 = sQLiteDatabase0.rawQuery(s, arr_s);
                if(cursor0.moveToFirst()) {
                    v1 = cursor0.getLong(0);
                    goto label_12;
                }
                goto label_14;
            }
            catch(SQLiteException sQLiteException0) {
                this.zzt.zzaA().zzd().zzc("Database error", s, sQLiteException0);
                throw sQLiteException0;
            }
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(cursor0, throwable0);
            throw throwable0;
        }
    label_12:
        cursor0.close();
        return v1;
    label_14:
        cursor0.close();
        return v;
    }

    @Override  // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }

    protected final long zzc(String s, String s1) {
        long v2;
        Preconditions.checkNotEmpty(s);
        new String("first_open_count");
        this.zzg();
        this.zzW();
        SQLiteDatabase sQLiteDatabase0 = this.zzh();
        sQLiteDatabase0.beginTransaction();
        long v = 0L;
        try {
            v2 = this.zzaa("select first_open_count from app2 where app_id=?", new String[]{s}, -1L);
            if(v2 == -1L) {
                ContentValues contentValues0 = new ContentValues();
                contentValues0.put("app_id", s);
                contentValues0.put("first_open_count", 0);
                contentValues0.put("previous_install_count", 0);
                if(sQLiteDatabase0.insertWithOnConflict("app2", null, contentValues0, 5) == -1L) {
                    this.zzt.zzaA().zzd().zzc("Failed to insert column (got -1). appId", zzet.zzn(s), "first_open_count");
                    return -1L;
                }
                goto label_20;
            }
            goto label_24;
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzd("Error inserting column. appId", zzet.zzn(s), "first_open_count", sQLiteException0);
            return v;
        }
        finally {
            sQLiteDatabase0.endTransaction();
        }
        try {
        label_20:
            v2 = 0L;
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzd("Error inserting column. appId", zzet.zzn(s), "first_open_count", sQLiteException0);
            return v;
        }
        try {
        label_24:
            ContentValues contentValues1 = new ContentValues();
            contentValues1.put("app_id", s);
            contentValues1.put("first_open_count", ((long)(v2 + 1L)));
            if(((long)sQLiteDatabase0.update("app2", contentValues1, "app_id = ?", new String[]{s})) == 0L) {
                this.zzt.zzaA().zzd().zzc("Failed to update column (got 0). appId", zzet.zzn(s), "first_open_count");
                return -1L;
            }
            sQLiteDatabase0.setTransactionSuccessful();
            return v2;
        }
        catch(SQLiteException sQLiteException0) {
            v = v2;
        }
        this.zzt.zzaA().zzd().zzd("Error inserting column. appId", zzet.zzn(s), "first_open_count", sQLiteException0);
        return v;
    }

    public final long zzd() {
        return this.zzaa("select max(bundle_end_timestamp) from queue", null, 0L);
    }

    public final long zze() {
        return this.zzaa("select max(timestamp) from raw_events", null, 0L);
    }

    public final long zzf(String s) {
        Preconditions.checkNotEmpty(s);
        return this.zzaa("select count(1) from events where app_id=? and name not like \'!_%\' escape \'!\'", new String[]{s}, 0L);
    }

    final SQLiteDatabase zzh() {
        this.zzg();
        try {
            return this.zzj.getWritableDatabase();
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzk().zzb("Error opening database", sQLiteException0);
            throw sQLiteException0;
        }
    }

    public final Bundle zzi(String s) {
        Bundle bundle0;
        zzft zzft0;
        Cursor cursor0;
        this.zzg();
        this.zzW();
        try {
            cursor0 = null;
            cursor0 = this.zzh().rawQuery("select parameters from default_event_params where app_id=?", new String[]{s});
            goto label_8;
        }
        catch(SQLiteException sQLiteException0) {
            goto label_38;
        }
        catch(Throwable throwable0) {
        }
        TWR.safeClose$NT(null, throwable0);
        throw throwable0;
        try {
        label_8:
            if(!cursor0.moveToFirst()) {
                this.zzt.zzaA().zzj().zza("Default event parameters not found");
                goto label_10;
            }
            goto label_12;
        }
        catch(SQLiteException sQLiteException0) {
            goto label_38;
        }
        catch(Throwable throwable0) {
            goto label_43;
        }
    label_10:
        cursor0.close();
        return null;
        try {
        label_12:
            byte[] arr_b = cursor0.getBlob(0);
            try {
                zzft0 = (zzft)((zzfs)zzlj.zzm(zzft.zze(), arr_b)).zzaD();
                goto label_19;
            }
            catch(IOException iOException0) {
            }
            this.zzt.zzaA().zzd().zzc("Failed to retrieve default event parameters. appId", zzet.zzn(s), iOException0);
        }
        catch(SQLiteException sQLiteException0) {
            goto label_38;
        }
        catch(Throwable throwable0) {
            goto label_43;
        }
        cursor0.close();
        return null;
        try {
            try {
            label_19:
                this.zzf.zzu();
                bundle0 = new Bundle();
                Iterator iterator0 = zzft0.zzi().iterator();
                while(true) {
                    if(!iterator0.hasNext()) {
                        goto label_45;
                    }
                    Object object0 = iterator0.next();
                    zzfx zzfx0 = (zzfx)object0;
                    if(zzfx0.zzu()) {
                        bundle0.putDouble("", zzfx0.zza());
                    }
                    else if(zzfx0.zzv()) {
                        bundle0.putFloat("", zzfx0.zzb());
                    }
                    else if(zzfx0.zzy()) {
                        bundle0.putString("", "");
                    }
                    else {
                        if(!zzfx0.zzw()) {
                            continue;
                        }
                        bundle0.putLong("", zzfx0.zzd());
                    }
                }
            }
            catch(SQLiteException sQLiteException0) {
            }
        label_38:
            this.zzt.zzaA().zzd().zzb("Error selecting default event parameters", sQLiteException0);
            if(cursor0 != null) {
                goto label_40;
            }
            return null;
        }
        catch(Throwable throwable0) {
            goto label_43;
        }
    label_40:
        cursor0.close();
        return null;
    label_43:
        TWR.safeClose$NT(cursor0, throwable0);
        throw throwable0;
    label_45:
        cursor0.close();
        return bundle0;
    }

    public final zzh zzj(String s) {
        zzh zzh0;
        Cursor cursor0;
        Preconditions.checkNotEmpty(s);
        this.zzg();
        this.zzW();
        try {
            cursor0 = null;
            cursor0 = this.zzh().query("apps", new String[]{"app_instance_id", "gmp_app_id", "resettable_device_id_hash", "last_bundle_index", "last_bundle_start_timestamp", "last_bundle_end_timestamp", "app_version", "app_store", "gmp_version", "dev_cert_hash", "measurement_enabled", "day", "daily_public_events_count", "daily_events_count", "daily_conversions_count", "config_fetched_time", "failed_config_fetch_time", "app_version_int", "firebase_instance_id", "daily_error_events_count", "daily_realtime_events_count", "health_monitor_sample", "android_id", "adid_reporting_enabled", "admob_app_id", "dynamite_version", "safelisted_events", "ga_app_id", "session_stitching_token", "sgtm_upload_enabled", "target_os_version", "session_stitching_token_hash"}, "app_id=?", new String[]{s}, null, null, null);
            goto label_9;
        }
        catch(SQLiteException sQLiteException0) {
            goto label_57;
        }
        catch(Throwable throwable0) {
        }
        TWR.safeClose$NT(null, throwable0);
        throw throwable0;
        try {
            try {
            label_9:
                if(cursor0.moveToFirst()) {
                    zzh0 = new zzh(this.zzf.zzp(), s);
                    boolean z = false;
                    zzh0.zzJ(cursor0.getString(0));
                    zzh0.zzY(cursor0.getString(1));
                    zzh0.zzag(cursor0.getString(2));
                    zzh0.zzac(cursor0.getLong(3));
                    zzh0.zzad(cursor0.getLong(4));
                    zzh0.zzab(cursor0.getLong(5));
                    zzh0.zzL(cursor0.getString(6));
                    zzh0.zzK(cursor0.getString(7));
                    zzh0.zzZ(cursor0.getLong(8));
                    zzh0.zzU(cursor0.getLong(9));
                    zzh0.zzae(cursor0.isNull(10) || cursor0.getInt(10) != 0);
                    zzh0.zzT(cursor0.getLong(11));
                    zzh0.zzR(cursor0.getLong(12));
                    zzh0.zzQ(cursor0.getLong(13));
                    zzh0.zzO(cursor0.getLong(14));
                    zzh0.zzN(cursor0.getLong(15));
                    zzh0.zzW(cursor0.getLong(16));
                    zzh0.zzM((cursor0.isNull(17) ? 0xFFFFFFFF80000000L : ((long)cursor0.getInt(17))));
                    zzh0.zzX(cursor0.getString(18));
                    zzh0.zzP(cursor0.getLong(19));
                    zzh0.zzS(cursor0.getLong(20));
                    zzh0.zzaa(cursor0.getString(21));
                    zzh0.zzI(cursor0.isNull(23) || cursor0.getInt(23) != 0);
                    zzh0.zzH(cursor0.getString(24));
                    zzh0.zzV((cursor0.isNull(25) ? 0L : cursor0.getLong(25)));
                    if(!cursor0.isNull(26)) {
                        zzh0.zzah(Arrays.asList(cursor0.getString(26).split(",", -1)));
                    }
                    zzqu.zzc();
                    if(this.zzt.zzf().zzs(s, zzeg.zzao) || this.zzt.zzf().zzs(null, zzeg.zzam)) {
                        zzh0.zzai(cursor0.getString(28));
                    }
                    zzrd.zzc();
                    if(this.zzt.zzf().zzs(null, zzeg.zzaq)) {
                        if(!cursor0.isNull(29) && cursor0.getInt(29) != 0) {
                            z = true;
                        }
                        zzh0.zzak(z);
                    }
                    zzpz.zzc();
                    if(this.zzt.zzf().zzs(null, zzeg.zzaE)) {
                        zzh0.zzal(cursor0.getLong(30));
                    }
                    if(this.zzt.zzf().zzs(null, zzeg.zzaH)) {
                        zzh0.zzaj(cursor0.getLong(0x1F));
                    }
                    zzh0.zzF();
                    if(cursor0.moveToNext()) {
                        this.zzt.zzaA().zzd().zzb("Got multiple records for app, expected one. appId", zzet.zzn(s));
                    }
                    goto label_64;
                }
                goto label_66;
            }
            catch(SQLiteException sQLiteException0) {
            }
        label_57:
            this.zzt.zzaA().zzd().zzc("Error querying app. appId", zzet.zzn(s), sQLiteException0);
            if(cursor0 != null) {
                goto label_59;
            }
            return null;
        }
        catch(Throwable throwable0) {
            goto label_62;
        }
    label_59:
        cursor0.close();
        return null;
    label_62:
        TWR.safeClose$NT(cursor0, throwable0);
        throw throwable0;
    label_64:
        cursor0.close();
        return zzh0;
    label_66:
        cursor0.close();
        return null;
    }

    public final zzac zzk(String s, String s1) {
        zzac zzac0;
        Cursor cursor0;
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotEmpty(s1);
        this.zzg();
        this.zzW();
        try {
            cursor0 = null;
            cursor0 = this.zzh().query("conditional_properties", new String[]{"origin", "value", "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, "app_id=? and name=?", new String[]{s, s1}, null, null, null);
            goto label_10;
        }
        catch(SQLiteException sQLiteException0) {
            goto label_29;
        }
        catch(Throwable throwable0) {
        }
        TWR.safeClose$NT(null, throwable0);
        throw throwable0;
        try {
            try {
            label_10:
                if(cursor0.moveToFirst()) {
                    String s2 = cursor0.getString(0);
                    if(s2 == null) {
                        s2 = "";
                    }
                    Object object0 = this.zzq(cursor0, 1);
                    boolean z = cursor0.getInt(2) != 0;
                    String s3 = cursor0.getString(3);
                    long v = cursor0.getLong(4);
                    zzau zzau0 = (zzau)this.zzf.zzu().zzi(cursor0.getBlob(5), zzau.CREATOR);
                    long v1 = cursor0.getLong(6);
                    zzau zzau1 = (zzau)this.zzf.zzu().zzi(cursor0.getBlob(7), zzau.CREATOR);
                    long v2 = cursor0.getLong(8);
                    long v3 = cursor0.getLong(9);
                    zzau zzau2 = (zzau)this.zzf.zzu().zzi(cursor0.getBlob(10), zzau.CREATOR);
                    zzac0 = new zzac(s, s2, new zzlk(s1, v2, object0, s2), v1, z, s3, zzau0, v, zzau1, v3, zzau2);
                    if(cursor0.moveToNext()) {
                        this.zzt.zzaA().zzd().zzc("Got multiple records for conditional property, expected one", zzet.zzn(s), this.zzt.zzj().zzf(s1));
                    }
                    goto label_36;
                }
                goto label_38;
            }
            catch(SQLiteException sQLiteException0) {
            }
        label_29:
            this.zzt.zzaA().zzd().zzd("Error querying conditional property", zzet.zzn(s), this.zzt.zzj().zzf(s1), sQLiteException0);
            if(cursor0 != null) {
                goto label_31;
            }
            return null;
        }
        catch(Throwable throwable0) {
            goto label_34;
        }
    label_31:
        cursor0.close();
        return null;
    label_34:
        TWR.safeClose$NT(cursor0, throwable0);
        throw throwable0;
    label_36:
        cursor0.close();
        return zzac0;
    label_38:
        cursor0.close();
        return null;
    }

    public final zzai zzl(long v, String s, boolean z, boolean z1, boolean z2, boolean z3, boolean z4) {
        return this.zzm(v, s, 1L, false, false, z2, false, z4);
    }

    public final zzai zzm(long v, String s, long v1, boolean z, boolean z1, boolean z2, boolean z3, boolean z4) {
        Preconditions.checkNotEmpty(s);
        this.zzg();
        this.zzW();
        String[] arr_s = {s};
        zzai zzai0 = new zzai();
        Cursor cursor0 = null;
        try {
            SQLiteDatabase sQLiteDatabase0 = this.zzh();
            cursor0 = sQLiteDatabase0.query("apps", new String[]{"day", "daily_events_count", "daily_public_events_count", "daily_conversions_count", "daily_error_events_count", "daily_realtime_events_count"}, "app_id=?", new String[]{s}, null, null, null);
            if(cursor0.moveToFirst()) {
                if(cursor0.getLong(0) == v) {
                    zzai0.zzb = cursor0.getLong(1);
                    zzai0.zza = cursor0.getLong(2);
                    zzai0.zzc = cursor0.getLong(3);
                    zzai0.zzd = cursor0.getLong(4);
                    zzai0.zze = cursor0.getLong(5);
                }
                if(z) {
                    zzai0.zzb += v1;
                }
                if(z1) {
                    zzai0.zza += v1;
                }
                if(z2) {
                    zzai0.zzc += v1;
                }
                if(z3) {
                    zzai0.zzd += v1;
                }
                if(z4) {
                    zzai0.zze += v1;
                }
                ContentValues contentValues0 = new ContentValues();
                contentValues0.put("day", v);
                contentValues0.put("daily_public_events_count", zzai0.zza);
                contentValues0.put("daily_events_count", zzai0.zzb);
                contentValues0.put("daily_conversions_count", zzai0.zzc);
                contentValues0.put("daily_error_events_count", zzai0.zzd);
                contentValues0.put("daily_realtime_events_count", zzai0.zze);
                sQLiteDatabase0.update("apps", contentValues0, "app_id=?", arr_s);
                goto label_33;
            }
            goto label_35;
        }
        catch(SQLiteException sQLiteException0) {
            goto label_38;
        }
        catch(Throwable throwable0) {
            goto label_43;
        }
    label_33:
        cursor0.close();
        return zzai0;
        try {
            try {
            label_35:
                this.zzt.zzaA().zzk().zzb("Not updating daily counts, app is not known. appId", zzet.zzn(s));
                goto label_45;
            }
            catch(SQLiteException sQLiteException0) {
            }
        label_38:
            this.zzt.zzaA().zzd().zzc("Error updating daily counts. appId", zzet.zzn(s), sQLiteException0);
            if(cursor0 != null) {
                goto label_40;
            }
            return zzai0;
        }
        catch(Throwable throwable0) {
            goto label_43;
        }
    label_40:
        cursor0.close();
        return zzai0;
    label_43:
        TWR.safeClose$NT(cursor0, throwable0);
        throw throwable0;
    label_45:
        cursor0.close();
        return zzai0;
    }

    public final zzaq zzn(String s, String s1) {
        zzaq zzaq0;
        Boolean boolean0;
        Cursor cursor0;
        boolean z;
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotEmpty(s1);
        this.zzg();
        this.zzW();
        ArrayList arrayList0 = new ArrayList(Arrays.asList(new String[]{"lifetime_count", "current_bundle_count", "last_fire_timestamp", "last_bundled_timestamp", "last_bundled_day", "last_sampled_complex_event_id", "last_sampling_rate", "last_exempt_from_sampling", "current_session_count"}));
        try {
            z = false;
            cursor0 = null;
            cursor0 = this.zzh().query("events", ((String[])arrayList0.toArray(new String[0])), "app_id=? and name=?", new String[]{s, s1}, null, null, null);
            goto label_12;
        }
        catch(SQLiteException sQLiteException0) {
            goto label_34;
        }
        catch(Throwable throwable0) {
        }
        TWR.safeClose$NT(null, throwable0);
        throw throwable0;
        try {
            try {
            label_12:
                if(cursor0.moveToFirst()) {
                    long v = cursor0.getLong(0);
                    long v1 = cursor0.getLong(1);
                    long v2 = cursor0.getLong(2);
                    long v3 = 0L;
                    long v4 = cursor0.isNull(3) ? 0L : cursor0.getLong(3);
                    Long long0 = cursor0.isNull(4) ? null : cursor0.getLong(4);
                    Long long1 = cursor0.isNull(5) ? null : cursor0.getLong(5);
                    Long long2 = cursor0.isNull(6) ? null : cursor0.getLong(6);
                    if(cursor0.isNull(7)) {
                        boolean0 = null;
                    }
                    else {
                        if(cursor0.getLong(7) == 1L) {
                            z = true;
                        }
                        boolean0 = Boolean.valueOf(z);
                    }
                    if(!cursor0.isNull(8)) {
                        v3 = cursor0.getLong(8);
                    }
                    zzaq0 = new zzaq(s, s1, v, v1, v3, v2, v4, long0, long1, long2, boolean0);
                    if(cursor0.moveToNext()) {
                        this.zzt.zzaA().zzd().zzb("Got multiple records for event aggregates, expected one. appId", zzet.zzn(s));
                    }
                    goto label_41;
                }
                goto label_43;
            }
            catch(SQLiteException sQLiteException0) {
            }
        label_34:
            this.zzt.zzaA().zzd().zzd("Error querying events. appId", zzet.zzn(s), this.zzt.zzj().zzd(s1), sQLiteException0);
            if(cursor0 != null) {
                goto label_36;
            }
            return null;
        }
        catch(Throwable throwable0) {
            goto label_39;
        }
    label_36:
        cursor0.close();
        return null;
    label_39:
        TWR.safeClose$NT(cursor0, throwable0);
        throw throwable0;
    label_41:
        cursor0.close();
        return zzaq0;
    label_43:
        cursor0.close();
        return null;
    }

    static zzkq zzo(zzak zzak0) {
        return zzak0.zzk;
    }

    public final zzlm zzp(String s, String s1) {
        zzlm zzlm0;
        Throwable throwable1;
        SQLiteException sQLiteException1;
        Cursor cursor1;
        Preconditions.checkNotEmpty(s);
        Preconditions.checkNotEmpty(s1);
        this.zzg();
        this.zzW();
        Cursor cursor0 = null;
        try {
            cursor1 = this.zzh().query("user_attributes", new String[]{"set_timestamp", "value", "origin"}, "app_id=? and name=?", new String[]{s, s1}, null, null, null);
        }
        catch(SQLiteException sQLiteException0) {
            sQLiteException1 = sQLiteException0;
            cursor1 = null;
            goto label_24;
        }
        catch(Throwable throwable0) {
            throwable1 = throwable0;
            goto label_31;
        }
        try {
            if(cursor1.moveToFirst()) {
                long v = cursor1.getLong(0);
                Object object0 = this.zzq(cursor1, 1);
                if(object0 != null) {
                    zzlm0 = new zzlm(s, cursor1.getString(2), s1, v, object0);
                    if(cursor1.moveToNext()) {
                        this.zzt.zzaA().zzd().zzb("Got multiple records for user property, expected one. appId", zzet.zzn(s));
                    }
                    goto label_34;
                }
                goto label_36;
            }
            goto label_38;
        }
        catch(SQLiteException sQLiteException2) {
            sQLiteException1 = sQLiteException2;
            try {
            label_24:
                this.zzt.zzaA().zzd().zzd("Error querying user property. appId", zzet.zzn(s), this.zzt.zzj().zzf(s1), sQLiteException1);
                if(cursor1 != null) {
                    goto label_26;
                }
                return null;
            }
            catch(Throwable throwable2) {
                goto label_29;
            }
        label_26:
            cursor1.close();
            return null;
        }
        catch(Throwable throwable2) {
        label_29:
            throwable1 = throwable2;
            cursor0 = cursor1;
        }
    label_31:
        if(cursor0 != null) {
            cursor0.close();
        }
        throw throwable1;
    label_34:
        cursor1.close();
        return zzlm0;
    label_36:
        cursor1.close();
        return null;
    label_38:
        cursor1.close();
        return null;
    }

    final Object zzq(Cursor cursor0, int v) {
        int v1 = cursor0.getType(v);
        switch(v1) {
            case 0: {
                this.zzt.zzaA().zzd().zza("Loaded invalid null value from database");
                return null;
            }
            case 1: {
                return cursor0.getLong(v);
            }
            case 2: {
                return cursor0.getDouble(v);
            }
            case 3: {
                return cursor0.getString(v);
            }
            case 4: {
                this.zzt.zzaA().zzd().zza("Loaded invalid blob type value, ignoring it");
                return null;
            }
            default: {
                this.zzt.zzaA().zzd().zzb("Loaded invalid unknown value type, ignoring it", v1);
                return null;
            }
        }
    }

    public final String zzr() {
        String s;
        SQLiteException sQLiteException1;
        Cursor cursor0;
        SQLiteDatabase sQLiteDatabase0 = this.zzh();
        try {
            cursor0 = sQLiteDatabase0.rawQuery("select app_id from queue order by has_realtime desc, rowid asc limit 1;", null);
        }
        catch(SQLiteException sQLiteException0) {
            sQLiteException1 = sQLiteException0;
            cursor0 = null;
            goto label_14;
        }
        catch(Throwable throwable0) {
            TWR.safeClose$NT(null, throwable0);
            throw throwable0;
        }
        try {
            if(cursor0.moveToFirst()) {
                s = cursor0.getString(0);
                goto label_21;
            }
            goto label_23;
        }
        catch(SQLiteException sQLiteException1) {
            try {
            label_14:
                this.zzt.zzaA().zzd().zzb("Database error getting next bundle app id", sQLiteException1);
                if(cursor0 != null) {
                    goto label_16;
                }
                return null;
            }
            catch(Throwable throwable1) {
                goto label_19;
            }
        label_16:
            cursor0.close();
            return null;
        }
        catch(Throwable throwable1) {
        label_19:
            TWR.safeClose$NT(cursor0, throwable1);
            throw throwable1;
        }
    label_21:
        cursor0.close();
        return s;
    label_23:
        cursor0.close();
        return null;
    }

    public final List zzs(String s, String s1, String s2) {
        Preconditions.checkNotEmpty(s);
        this.zzg();
        this.zzW();
        ArrayList arrayList0 = new ArrayList(3);
        arrayList0.add(s);
        StringBuilder stringBuilder0 = new StringBuilder("app_id=?");
        if(!TextUtils.isEmpty(s1)) {
            arrayList0.add(s1);
            stringBuilder0.append(" and origin=?");
        }
        if(!TextUtils.isEmpty(s2)) {
            arrayList0.add(s2 + "*");
            stringBuilder0.append(" and name glob ?");
        }
        return this.zzt(stringBuilder0.toString(), ((String[])arrayList0.toArray(new String[arrayList0.size()])));
    }

    public final List zzt(String s, String[] arr_s) {
        List list1;
        this.zzg();
        this.zzW();
        List list0 = new ArrayList();
        Cursor cursor0 = null;
        try {
            cursor0 = this.zzh().query("conditional_properties", new String[]{"app_id", "origin", "name", "value", "active", "trigger_event_name", "trigger_timeout", "timed_out_event", "creation_timestamp", "triggered_event", "triggered_timestamp", "time_to_live", "expired_event"}, s, arr_s, null, null, "rowid", "1001");
            if(cursor0.moveToFirst()) {
                while(true) {
                label_6:
                    if(list0.size() >= 1000) {
                        this.zzt.zzaA().zzd().zzb("Read more than the max allowed conditional properties, ignoring extra", 1000);
                        goto label_35;
                    }
                    String s1 = cursor0.getString(0);
                    String s2 = cursor0.getString(1);
                    String s3 = cursor0.getString(2);
                    Object object0 = this.zzq(cursor0, 3);
                    boolean z = cursor0.getInt(4) != 0;
                    String s4 = cursor0.getString(5);
                    long v = cursor0.getLong(6);
                    Parcelable parcelable0 = this.zzf.zzu().zzi(cursor0.getBlob(7), zzau.CREATOR);
                    long v1 = cursor0.getLong(8);
                    Parcelable parcelable1 = this.zzf.zzu().zzi(cursor0.getBlob(9), zzau.CREATOR);
                    long v2 = cursor0.getLong(10);
                    long v3 = cursor0.getLong(11);
                    Parcelable parcelable2 = this.zzf.zzu().zzi(cursor0.getBlob(12), zzau.CREATOR);
                    list0.add(new zzac(s1, s2, new zzlk(s3, v2, object0, s2), v1, z, s4, ((zzau)parcelable0), v, ((zzau)parcelable1), v3, ((zzau)parcelable2)));
                    if(!cursor0.moveToNext()) {
                        goto label_35;
                    }
                }
            }
            goto label_37;
        }
        catch(SQLiteException sQLiteException0) {
            try {
                this.zzt.zzaA().zzd().zzb("Error querying conditional user property value", sQLiteException0);
                list1 = Collections.EMPTY_LIST;
                if(cursor0 != null) {
                    goto label_29;
                }
                return list1;
            }
            catch(Throwable throwable0) {
                goto label_32;
            }
        label_29:
            cursor0.close();
            return list1;
        }
        catch(Throwable throwable0) {
        label_32:
            TWR.safeClose$NT(cursor0, throwable0);
            throw throwable0;
        }
        goto label_6;
    label_35:
        cursor0.close();
        return list0;
    label_37:
        cursor0.close();
        return list0;
    }

    public final List zzu(String s) {
        List list1;
        Preconditions.checkNotEmpty(s);
        this.zzg();
        this.zzW();
        List list0 = new ArrayList();
        Cursor cursor0 = null;
        try {
            cursor0 = this.zzh().query("user_attributes", new String[]{"name", "origin", "set_timestamp", "value"}, "app_id=?", new String[]{s}, null, null, "rowid", "1000");
            if(cursor0.moveToFirst()) {
                do {
                    String s1 = cursor0.getString(0);
                    String s2 = cursor0.getString(1);
                    if(s2 == null) {
                        s2 = "";
                    }
                    long v = cursor0.getLong(2);
                    Object object0 = this.zzq(cursor0, 3);
                    if(object0 == null) {
                        this.zzt.zzaA().zzd().zzb("Read invalid user property value, ignoring it. appId", zzet.zzn(s));
                    }
                    else {
                        list0.add(new zzlm(s, s2, s1, v, object0));
                    }
                }
                while(cursor0.moveToNext());
                goto label_28;
            }
            goto label_30;
        }
        catch(SQLiteException sQLiteException0) {
            try {
                this.zzt.zzaA().zzd().zzc("Error querying user properties. appId", zzet.zzn(s), sQLiteException0);
                list1 = Collections.EMPTY_LIST;
                if(cursor0 != null) {
                    goto label_23;
                }
                return list1;
            }
            catch(Throwable throwable0) {
                goto label_26;
            }
        label_23:
            cursor0.close();
            return list1;
        }
        catch(Throwable throwable0) {
        label_26:
            TWR.safeClose$NT(cursor0, throwable0);
            throw throwable0;
        }
    label_28:
        cursor0.close();
        return list0;
    label_30:
        cursor0.close();
        return list0;
    }

    public final List zzv(String s, String s1, String s2) {
        List list1;
        String s5;
        Object object0;
        long v;
        String s4;
        StringBuilder stringBuilder0;
        ArrayList arrayList0;
        Preconditions.checkNotEmpty(s);
        this.zzg();
        this.zzW();
        List list0 = new ArrayList();
        Cursor cursor0 = null;
        try {
            arrayList0 = new ArrayList(3);
            arrayList0.add(s);
            stringBuilder0 = new StringBuilder("app_id=?");
            if(TextUtils.isEmpty(s1)) {
                goto label_17;
            }
            else {
                goto label_13;
            }
            goto label_18;
        }
        catch(SQLiteException sQLiteException0) {
            String s3 = s1;
            goto label_46;
            try {
                try {
                label_13:
                    s3 = s1;
                    arrayList0.add(s3);
                    stringBuilder0.append(" and origin=?");
                    goto label_18;
                label_17:
                    s3 = s1;
                label_18:
                    if(!TextUtils.isEmpty(s2)) {
                        arrayList0.add(s2 + "*");
                        stringBuilder0.append(" and name glob ?");
                    }
                    Object[] arr_object = arrayList0.toArray(new String[arrayList0.size()]);
                    cursor0 = this.zzh().query("user_attributes", new String[]{"name", "set_timestamp", "value", "origin"}, stringBuilder0.toString(), ((String[])arr_object), null, null, "rowid", "1001");
                    if(cursor0.moveToFirst()) {
                        while(true) {
                        label_24:
                            if(list0.size() >= 1000) {
                                this.zzt.zzaA().zzd().zzb("Read more than the max allowed user properties, ignoring excess", 1000);
                            }
                            else {
                                s4 = cursor0.getString(0);
                                v = cursor0.getLong(1);
                                object0 = this.zzq(cursor0, 2);
                                s5 = cursor0.getString(3);
                                if(object0 == null) {
                                    goto label_32;
                                }
                                else {
                                    goto label_34;
                                }
                                goto label_35;
                            }
                            goto label_40;
                        }
                    }
                    goto label_54;
                }
                catch(SQLiteException sQLiteException0) {
                    goto label_45;
                }
                try {
                label_32:
                    this.zzt.zzaA().zzd().zzd("(2)Read invalid user property value, ignoring it", zzet.zzn(s), s5, s2);
                    goto label_35;
                label_34:
                    list0.add(new zzlm(s, s5, s4, v, object0));
                label_35:
                    if(cursor0.moveToNext()) {
                        goto label_42;
                    }
                    goto label_40;
                }
                catch(SQLiteException sQLiteException0) {
                }
            }
            catch(Throwable throwable0) {
                goto label_52;
            }
            s3 = s5;
            goto label_46;
        label_40:
            cursor0.close();
            return list0;
            try {
            label_42:
                s3 = s5;
                goto label_24;
            }
            catch(SQLiteException sQLiteException0) {
            }
            catch(Throwable throwable0) {
                goto label_52;
            }
        label_45:
            try {
            label_46:
                this.zzt.zzaA().zzd().zzd("(2)Error querying user properties", zzet.zzn(s), s3, sQLiteException0);
                list1 = Collections.EMPTY_LIST;
                if(cursor0 != null) {
                    goto label_49;
                }
                return list1;
            }
            catch(Throwable throwable0) {
                goto label_52;
            }
        label_49:
            cursor0.close();
            return list1;
        }
        catch(Throwable throwable0) {
        label_52:
            TWR.safeClose$NT(cursor0, throwable0);
            throw throwable0;
        }
    label_54:
        cursor0.close();
        return list0;
    }

    public final void zzw() {
        this.zzW();
        this.zzh().beginTransaction();
    }

    public final void zzx() {
        this.zzW();
        this.zzh().endTransaction();
    }

    final void zzy(List list0) {
        this.zzg();
        this.zzW();
        Preconditions.checkNotNull(list0);
        Preconditions.checkNotZero(list0.size());
        if(!this.zzI()) {
            return;
        }
        String s = "(" + TextUtils.join(",", list0) + ")";
        if(this.zzZ("SELECT COUNT(1) FROM queue WHERE rowid IN " + s + " AND retry_count =  2147483647 LIMIT 1", null) > 0L) {
            this.zzt.zzaA().zzk().zza("The number of upload retries exceeds the limit. Will remain unchanged.");
        }
        try {
            this.zzh().execSQL("UPDATE queue SET retry_count = IFNULL(retry_count, 0) + 1 WHERE rowid IN " + s + " AND (retry_count IS NULL OR retry_count < 2147483647)");
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzb("Error incrementing retry count. error", sQLiteException0);
        }
    }

    final void zzz() {
        this.zzg();
        this.zzW();
        if(this.zzI()) {
            long v = this.zzf.zzs().zza.zza();
            long v1 = this.zzt.zzax().elapsedRealtime();
            if(Math.abs(v1 - v) > ((long)(((Long)zzeg.zzy.zza(null))))) {
                this.zzf.zzs().zza.zzb(v1);
                this.zzg();
                this.zzW();
                if(this.zzI()) {
                    int v2 = this.zzh().delete("queue", "abs(bundle_end_timestamp - ?) > cast(? as integer)", new String[]{String.valueOf(this.zzt.zzax().currentTimeMillis()), "2419200000"});
                    if(v2 > 0) {
                        this.zzt.zzaA().zzj().zzb("Deleted stale rows. rowsDeleted", v2);
                    }
                }
            }
        }
    }
}

