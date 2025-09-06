package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

final class zzaj extends SQLiteOpenHelper {
    final zzak zza;

    zzaj(zzak zzak0, Context context0, String s) {
        this.zza = zzak0;
        super(context0, "google_app_measurement.db", null, 1);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final SQLiteDatabase getWritableDatabase() {
        if(zzak.zzo(this.zza).zzc(3600000L)) {
            try {
                return super.getWritableDatabase();
            }
            catch(SQLiteException unused_ex) {
                zzak.zzo(this.zza).zzb();
                this.zza.zzt.zzaA().zzd().zza("Opening the database failed, dropping and recreating it");
                if(!this.zza.zzt.zzaw().getDatabasePath("google_app_measurement.db").delete()) {
                    this.zza.zzt.zzaA().zzd().zzb("Failed to delete corrupted db file", "google_app_measurement.db");
                }
                try {
                    SQLiteDatabase sQLiteDatabase0 = super.getWritableDatabase();
                    zzak.zzo(this.zza).zza();
                    return sQLiteDatabase0;
                }
                catch(SQLiteException sQLiteException0) {
                    this.zza.zzt.zzaA().zzd().zzb("Failed to open freshly created database", sQLiteException0);
                    throw sQLiteException0;
                }
            }
        }
        throw new SQLiteException("Database open failed");
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase0) {
        zzal.zzb(this.zza.zzt.zzaA(), sQLiteDatabase0);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final void onOpen(SQLiteDatabase sQLiteDatabase0) {
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "events", "CREATE TABLE IF NOT EXISTS events ( app_id TEXT NOT NULL, name TEXT NOT NULL, lifetime_count INTEGER NOT NULL, current_bundle_count INTEGER NOT NULL, last_fire_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,lifetime_count,current_bundle_count,last_fire_timestamp", zzak.zzO());
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "conditional_properties", "CREATE TABLE IF NOT EXISTS conditional_properties ( app_id TEXT NOT NULL, origin TEXT NOT NULL, name TEXT NOT NULL, value BLOB NOT NULL, creation_timestamp INTEGER NOT NULL, active INTEGER NOT NULL, trigger_event_name TEXT, trigger_timeout INTEGER NOT NULL, timed_out_event BLOB,triggered_event BLOB, triggered_timestamp INTEGER NOT NULL, time_to_live INTEGER NOT NULL, expired_event BLOB, PRIMARY KEY (app_id, name)) ;", "app_id,origin,name,value,active,trigger_event_name,trigger_timeout,creation_timestamp,timed_out_event,triggered_event,triggered_timestamp,time_to_live,expired_event", null);
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "user_attributes", "CREATE TABLE IF NOT EXISTS user_attributes ( app_id TEXT NOT NULL, name TEXT NOT NULL, set_timestamp INTEGER NOT NULL, value BLOB NOT NULL, PRIMARY KEY (app_id, name)) ;", "app_id,name,set_timestamp,value", zzak.zzT());
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "apps", "CREATE TABLE IF NOT EXISTS apps ( app_id TEXT NOT NULL, app_instance_id TEXT, gmp_app_id TEXT, resettable_device_id_hash TEXT, last_bundle_index INTEGER NOT NULL, last_bundle_end_timestamp INTEGER NOT NULL, PRIMARY KEY (app_id)) ;", "app_id,app_instance_id,gmp_app_id,resettable_device_id_hash,last_bundle_index,last_bundle_end_timestamp", zzak.zzN());
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "queue", "CREATE TABLE IF NOT EXISTS queue ( app_id TEXT NOT NULL, bundle_end_timestamp INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,bundle_end_timestamp,data", zzak.zzR());
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "raw_events_metadata", "CREATE TABLE IF NOT EXISTS raw_events_metadata ( app_id TEXT NOT NULL, metadata_fingerprint INTEGER NOT NULL, metadata BLOB NOT NULL, PRIMARY KEY (app_id, metadata_fingerprint));", "app_id,metadata_fingerprint,metadata", null);
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "raw_events", "CREATE TABLE IF NOT EXISTS raw_events ( app_id TEXT NOT NULL, name TEXT NOT NULL, timestamp INTEGER NOT NULL, metadata_fingerprint INTEGER NOT NULL, data BLOB NOT NULL);", "app_id,name,timestamp,metadata_fingerprint,data", zzak.zzS());
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "event_filters", "CREATE TABLE IF NOT EXISTS event_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, event_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, event_name, audience_id, filter_id));", "app_id,audience_id,filter_id,event_name,data", zzak.zzP());
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "property_filters", "CREATE TABLE IF NOT EXISTS property_filters ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, filter_id INTEGER NOT NULL, property_name TEXT NOT NULL, data BLOB NOT NULL, PRIMARY KEY (app_id, property_name, audience_id, filter_id));", "app_id,audience_id,filter_id,property_name,data", zzak.zzQ());
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "audience_filter_values", "CREATE TABLE IF NOT EXISTS audience_filter_values ( app_id TEXT NOT NULL, audience_id INTEGER NOT NULL, current_results BLOB, PRIMARY KEY (app_id, audience_id));", "app_id,audience_id,current_results", null);
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "app2", "CREATE TABLE IF NOT EXISTS app2 ( app_id TEXT NOT NULL, first_open_count INTEGER NOT NULL, PRIMARY KEY (app_id));", "app_id,first_open_count", zzak.zzM());
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "main_event_params", "CREATE TABLE IF NOT EXISTS main_event_params ( app_id TEXT NOT NULL, event_id TEXT NOT NULL, children_to_process INTEGER NOT NULL, main_event BLOB NOT NULL, PRIMARY KEY (app_id));", "app_id,event_id,children_to_process,main_event", null);
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "default_event_params", "CREATE TABLE IF NOT EXISTS default_event_params ( app_id TEXT NOT NULL, parameters BLOB NOT NULL, PRIMARY KEY (app_id));", "app_id,parameters", null);
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "consent_settings", "CREATE TABLE IF NOT EXISTS consent_settings ( app_id TEXT NOT NULL, consent_state TEXT NOT NULL, PRIMARY KEY (app_id));", "app_id,consent_state", null);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
    }
}

