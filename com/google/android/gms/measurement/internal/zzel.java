package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

final class zzel extends SQLiteOpenHelper {
    final zzem zza;

    zzel(zzem zzem0, Context context0, String s) {
        this.zza = zzem0;
        super(context0, "google_app_measurement_local.db", null, 1);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        try {
            return super.getWritableDatabase();
        }
        catch(SQLiteDatabaseLockedException sQLiteDatabaseLockedException0) {
            throw sQLiteDatabaseLockedException0;
        }
        catch(SQLiteException unused_ex) {
            this.zza.zzt.zzaA().zzd().zza("Opening the local database failed, dropping and recreating it");
            if(!this.zza.zzt.zzaw().getDatabasePath("google_app_measurement_local.db").delete()) {
                this.zza.zzt.zzaA().zzd().zzb("Failed to delete corrupted local db file", "google_app_measurement_local.db");
            }
            try {
                return super.getWritableDatabase();
            }
            catch(SQLiteException sQLiteException0) {
                this.zza.zzt.zzaA().zzd().zzb("Failed to open local database. Events will bypass local storage", sQLiteException0);
                return null;
            }
        }
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
        zzal.zza(this.zza.zzt.zzaA(), sQLiteDatabase0, "messages", "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)", "type,entry", null);
    }

    @Override  // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase0, int v, int v1) {
    }
}

