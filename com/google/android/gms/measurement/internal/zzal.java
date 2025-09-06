package com.google.android.gms.measurement.internal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import java.io.File;
import java.util.Collections;
import java.util.HashSet;

public final class zzal {
    static void zza(zzet zzet0, SQLiteDatabase sQLiteDatabase0, String s, String s1, String s2, String[] arr_s) throws SQLiteException {
        Cursor cursor1;
        Cursor cursor0;
        if(zzet0 != null) {
            try {
                cursor0 = null;
                cursor1 = null;
                cursor1 = sQLiteDatabase0.query("SQLITE_MASTER", new String[]{"name"}, "name=?", new String[]{s}, null, null, null);
                goto label_8;
            }
            catch(SQLiteException sQLiteException0) {
                goto label_11;
            }
            catch(Throwable throwable0) {
            }
            Throwable throwable1 = throwable0;
            goto label_19;
            try {
                try {
                label_8:
                    boolean z = cursor1.moveToFirst();
                    goto label_22;
                }
                catch(SQLiteException sQLiteException0) {
                }
            label_11:
                zzet0.zzk().zzc("Error querying for table", s, sQLiteException0);
                if(cursor1 != null) {
                    goto label_13;
                }
                goto label_14;
            }
            catch(Throwable throwable2) {
                goto label_17;
            }
        label_13:
            cursor1.close();
        label_14:
            sQLiteDatabase0.execSQL(s1);
            goto label_25;
        label_17:
            throwable1 = throwable2;
            cursor0 = cursor1;
        label_19:
            if(cursor0 != null) {
                cursor0.close();
            }
            throw throwable1;
        label_22:
            cursor1.close();
            if(!z) {
                sQLiteDatabase0.execSQL(s1);
            }
            try {
            label_25:
                HashSet hashSet0 = new HashSet();
                Cursor cursor2 = sQLiteDatabase0.rawQuery("SELECT * FROM " + s + " LIMIT 0", null);
                try {
                    Collections.addAll(hashSet0, cursor2.getColumnNames());
                }
                finally {
                    cursor2.close();
                }
                String[] arr_s1 = s2.split(",");
                for(int v2 = 0; v2 < arr_s1.length; ++v2) {
                    String s3 = arr_s1[v2];
                    if(!hashSet0.remove(s3)) {
                        throw new SQLiteException("Table " + s + " is missing required column: " + s3);
                    }
                }
                if(arr_s != null) {
                    for(int v1 = 0; v1 < arr_s.length; v1 += 2) {
                        if(!hashSet0.remove(arr_s[v1])) {
                            sQLiteDatabase0.execSQL(arr_s[v1 + 1]);
                        }
                    }
                }
                if(!hashSet0.isEmpty()) {
                    zzet0.zzk().zzc("Table has extra columns. table, columns", s, TextUtils.join(", ", hashSet0));
                }
                return;
            }
            catch(SQLiteException sQLiteException1) {
                zzet0.zzd().zzb("Failed to verify columns on table that was just created", s);
                throw sQLiteException1;
            }
        }
        throw new IllegalArgumentException("Monitor must not be null");
    }

    static void zzb(zzet zzet0, SQLiteDatabase sQLiteDatabase0) {
        if(zzet0 == null) {
            throw new IllegalArgumentException("Monitor must not be null");
        }
        File file0 = new File(sQLiteDatabase0.getPath());
        if(!file0.setReadable(false, false)) {
            zzet0.zzk().zza("Failed to turn off database read permission");
        }
        if(!file0.setWritable(false, false)) {
            zzet0.zzk().zza("Failed to turn off database write permission");
        }
        if(!file0.setReadable(true, true)) {
            zzet0.zzk().zza("Failed to turn on database read permission for owner");
        }
        if(!file0.setWritable(true, true)) {
            zzet0.zzk().zza("Failed to turn on database write permission for owner");
        }
    }
}

