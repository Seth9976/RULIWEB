package com.google.android.gms.measurement.internal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteFullException;
import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ParseException;
import java.util.ArrayList;
import java.util.List;
import jeb.synthetic.TWR;

public final class zzem extends zzf {
    private final zzel zza;
    private boolean zzb;

    zzem(zzgd zzgd0) {
        super(zzgd0);
        this.zza = new zzel(this, this.zzt.zzaw(), "google_app_measurement_local.db");
    }

    @Override  // com.google.android.gms.measurement.internal.zzf
    protected final boolean zzf() {
        return false;
    }

    final SQLiteDatabase zzh() throws SQLiteException {
        if(this.zzb) {
            return null;
        }
        SQLiteDatabase sQLiteDatabase0 = this.zza.getWritableDatabase();
        if(sQLiteDatabase0 == null) {
            this.zzb = true;
            return null;
        }
        return sQLiteDatabase0;
    }

    public final List zzi(int v) {
        zzac zzac0;
        Parcel parcel2;
        zzlk zzlk0;
        Parcel parcel1;
        zzau zzau0;
        Parcel parcel0;
        byte[] arr_b;
        String[] arr_s;
        String s;
        long v4;
        Cursor cursor2;
        long v3;
        Cursor cursor1;
        SQLiteDatabase sQLiteDatabase0;
        this.zzg();
        Cursor cursor0 = null;
        if(this.zzb) {
            return null;
        }
        List list0 = new ArrayList();
        if(this.zzl()) {
            int v1 = 5;
            int v2 = 0;
            while(true) {
                try {
                    sQLiteDatabase0 = null;
                    sQLiteDatabase0 = this.zzh();
                    goto label_19;
                }
                catch(SQLiteFullException sQLiteFullException0) {
                    cursor1 = null;
                    goto label_117;
                }
                catch(SQLiteDatabaseLockedException unused_ex) {
                    cursor1 = null;
                    goto label_124;
                }
                catch(SQLiteException sQLiteException0) {
                    cursor1 = null;
                    goto label_132;
                label_19:
                    if(sQLiteDatabase0 == null) {
                        try {
                            this.zzb = true;
                            return null;
                        label_22:
                            sQLiteDatabase0.beginTransaction();
                            goto label_23;
                        }
                        catch(SQLiteFullException sQLiteFullException0) {
                            cursor1 = null;
                            goto label_117;
                        }
                        catch(SQLiteDatabaseLockedException unused_ex) {
                            cursor1 = null;
                            goto label_124;
                        }
                        catch(SQLiteException sQLiteException0) {
                            cursor1 = null;
                            goto label_132;
                        }
                        catch(Throwable throwable0) {
                            goto label_144;
                        }
                    }
                    goto label_22;
                    try {
                    label_23:
                        v3 = -1L;
                        cursor2 = null;
                        cursor2 = sQLiteDatabase0.query("messages", new String[]{"rowid"}, "type=?", new String[]{"3"}, null, null, "rowid desc", "1");
                        if(cursor2.moveToFirst()) {
                            v4 = cursor2.getLong(0);
                            goto label_33;
                        }
                        else {
                            goto label_35;
                        }
                        goto label_37;
                    }
                    catch(Throwable throwable1) {
                        try {
                            if(cursor2 != null) {
                                cursor2.close();
                            }
                            throw throwable1;
                        label_33:
                            cursor2.close();
                            goto label_37;
                        label_35:
                            cursor2.close();
                            v4 = -1L;
                        label_37:
                            if(v4 == -1L) {
                                s = null;
                                arr_s = null;
                            }
                            else {
                                s = "rowid<?";
                                arr_s = new String[]{String.valueOf(v4)};
                            }
                            cursor1 = sQLiteDatabase0.query("messages", new String[]{"rowid", "type", "entry"}, s, arr_s, null, null, "rowid asc", "100");
                            goto label_55;
                        }
                        catch(SQLiteFullException sQLiteFullException0) {
                        }
                        catch(SQLiteDatabaseLockedException unused_ex) {
                            cursor1 = null;
                            goto label_124;
                        }
                        catch(SQLiteException sQLiteException0) {
                            cursor1 = null;
                            goto label_132;
                        }
                        catch(Throwable throwable0) {
                            goto label_144;
                        }
                    }
                    cursor1 = null;
                    goto label_117;
                }
                catch(Throwable throwable0) {
                    goto label_144;
                }
                try {
                alab1:
                    while(true) {
                    label_55:
                        if(!cursor1.moveToNext()) {
                            goto label_111;
                        }
                        v3 = cursor1.getLong(0);
                        int v5 = cursor1.getInt(1);
                        arr_b = cursor1.getBlob(2);
                        switch(v5) {
                            case 0: {
                                parcel0 = Parcel.obtain();
                                break alab1;
                            }
                            case 1: {
                                goto label_77;
                            }
                            case 2: {
                                goto label_93;
                            }
                            case 3: {
                                goto label_109;
                            }
                            default: {
                                this.zzt.zzaA().zzd().zza("Unknown record type in local database");
                            }
                        }
                    }
                }
                catch(SQLiteFullException sQLiteFullException0) {
                    goto label_117;
                }
                catch(SQLiteDatabaseLockedException unused_ex) {
                    goto label_124;
                }
                catch(SQLiteException sQLiteException0) {
                    goto label_132;
                }
                catch(Throwable throwable0) {
                    break;
                }
                try {
                    try {
                        parcel0.unmarshall(arr_b, 0, arr_b.length);
                        parcel0.setDataPosition(0);
                        zzau0 = (zzau)zzau.CREATOR.createFromParcel(parcel0);
                        goto label_73;
                    }
                    catch(ParseException unused_ex) {
                    }
                    this.zzt.zzaA().zzd().zza("Failed to load event from local database");
                }
                catch(Throwable throwable2) {
                    goto label_71;
                }
                try {
                    parcel0.recycle();
                    goto label_55;
                label_71:
                    parcel0.recycle();
                    throw throwable2;
                label_73:
                    parcel0.recycle();
                    if(zzau0 == null) {
                        goto label_55;
                    }
                    list0.add(zzau0);
                    goto label_55;
                label_77:
                    parcel1 = Parcel.obtain();
                }
                catch(SQLiteFullException sQLiteFullException0) {
                    goto label_117;
                }
                catch(SQLiteDatabaseLockedException unused_ex) {
                    goto label_124;
                }
                catch(SQLiteException sQLiteException0) {
                    goto label_132;
                }
                catch(Throwable throwable0) {
                    break;
                }
                try {
                    try {
                        parcel1.unmarshall(arr_b, 0, arr_b.length);
                        parcel1.setDataPosition(0);
                        zzlk0 = (zzlk)zzlk.CREATOR.createFromParcel(parcel1);
                        goto label_89;
                    }
                    catch(ParseException unused_ex) {
                    }
                    this.zzt.zzaA().zzd().zza("Failed to load user property from local database");
                }
                catch(Throwable throwable3) {
                    goto label_87;
                }
                try {
                    parcel1.recycle();
                    zzlk0 = null;
                    goto label_90;
                label_87:
                    parcel1.recycle();
                    throw throwable3;
                label_89:
                    parcel1.recycle();
                label_90:
                    if(zzlk0 == null) {
                        goto label_55;
                    }
                    list0.add(zzlk0);
                    goto label_55;
                label_93:
                    parcel2 = Parcel.obtain();
                }
                catch(SQLiteFullException sQLiteFullException0) {
                    goto label_117;
                }
                catch(SQLiteDatabaseLockedException unused_ex) {
                    goto label_124;
                }
                catch(SQLiteException sQLiteException0) {
                    goto label_132;
                }
                catch(Throwable throwable0) {
                    break;
                }
                try {
                    try {
                        parcel2.unmarshall(arr_b, 0, arr_b.length);
                        parcel2.setDataPosition(0);
                        zzac0 = (zzac)zzac.CREATOR.createFromParcel(parcel2);
                        goto label_105;
                    }
                    catch(ParseException unused_ex) {
                    }
                    this.zzt.zzaA().zzd().zza("Failed to load conditional user property from local database");
                }
                catch(Throwable throwable4) {
                    goto label_103;
                }
                try {
                    parcel2.recycle();
                    zzac0 = null;
                    goto label_106;
                label_103:
                    parcel2.recycle();
                    throw throwable4;
                label_105:
                    parcel2.recycle();
                label_106:
                    if(zzac0 == null) {
                        goto label_55;
                    }
                    list0.add(zzac0);
                    goto label_55;
                label_109:
                    this.zzt.zzaA().zzk().zza("Skipping app launch break");
                    goto label_55;
                label_111:
                    if(sQLiteDatabase0.delete("messages", "rowid <= ?", new String[]{Long.toString(v3)}) < list0.size()) {
                        this.zzt.zzaA().zzd().zza("Fewer entries removed from local database than expected");
                    }
                    sQLiteDatabase0.setTransactionSuccessful();
                    sQLiteDatabase0.endTransaction();
                    goto label_149;
                }
                catch(SQLiteFullException sQLiteFullException0) {
                }
                catch(SQLiteDatabaseLockedException unused_ex) {
                    goto label_124;
                }
                catch(SQLiteException sQLiteException0) {
                    goto label_132;
                }
                catch(Throwable throwable0) {
                    break;
                }
                try {
                label_117:
                    this.zzt.zzaA().zzd().zzb("Error reading entries from local database", sQLiteFullException0);
                    this.zzb = true;
                    if(cursor1 != null) {
                        goto label_120;
                    }
                    goto label_121;
                }
                catch(Throwable throwable0) {
                    break;
                }
            label_120:
                cursor1.close();
            label_121:
                if(sQLiteDatabase0 != null) {
                    sQLiteDatabase0.close();
                    goto label_140;
                    try {
                    label_124:
                        SystemClock.sleep(v1);
                        v1 += 20;
                        if(cursor1 != null) {
                            goto label_127;
                        }
                        goto label_128;
                    }
                    catch(Throwable throwable0) {
                        break;
                    }
                label_127:
                    cursor1.close();
                label_128:
                    if(sQLiteDatabase0 != null) {
                        sQLiteDatabase0.close();
                        goto label_140;
                        try {
                        label_132:
                            if(sQLiteDatabase0 != null && sQLiteDatabase0.inTransaction()) {
                                sQLiteDatabase0.endTransaction();
                            }
                            this.zzt.zzaA().zzd().zzb("Error reading entries from local database", sQLiteException0);
                            this.zzb = true;
                            if(cursor1 != null) {
                                goto label_137;
                            }
                            goto label_138;
                        }
                        catch(Throwable throwable0) {
                            break;
                        }
                    label_137:
                        cursor1.close();
                    label_138:
                        if(sQLiteDatabase0 != null) {
                            sQLiteDatabase0.close();
                        }
                    }
                }
            label_140:
                ++v2;
            }
            cursor0 = cursor1;
        label_144:
            if(cursor0 != null) {
                cursor0.close();
            }
            if(sQLiteDatabase0 != null) {
                sQLiteDatabase0.close();
            }
            throw throwable0;
        label_149:
            cursor1.close();
            sQLiteDatabase0.close();
            return list0;
        }
        return list0;
    }

    public final void zzj() {
        this.zzg();
        try {
            SQLiteDatabase sQLiteDatabase0 = this.zzh();
            if(sQLiteDatabase0 != null) {
                int v = sQLiteDatabase0.delete("messages", null, null);
                if(v > 0) {
                    this.zzt.zzaA().zzj().zzb("Reset local analytics data. records", v);
                }
            }
        }
        catch(SQLiteException sQLiteException0) {
            this.zzt.zzaA().zzd().zzb("Error resetting local analytics data. error", sQLiteException0);
        }
    }

    public final boolean zzk() {
        return this.zzq(3, new byte[0]);
    }

    final boolean zzl() {
        return this.zzt.zzaw().getDatabasePath("google_app_measurement_local.db").exists();
    }

    public final boolean zzm() {
        SQLiteDatabase sQLiteDatabase0;
        this.zzg();
        if(this.zzb) {
            return false;
        }
        if(this.zzl()) {
            int v = 5;
            int v1 = 0;
            while(true) {
                try {
                    try {
                    label_6:
                        sQLiteDatabase0 = null;
                        sQLiteDatabase0 = this.zzh();
                        if(sQLiteDatabase0 == null) {
                            this.zzb = true;
                            return false;
                        }
                        sQLiteDatabase0.beginTransaction();
                        sQLiteDatabase0.delete("messages", "type == ?", new String[]{"3"});
                        sQLiteDatabase0.setTransactionSuccessful();
                        sQLiteDatabase0.endTransaction();
                        break;
                    }
                    catch(SQLiteFullException sQLiteFullException0) {
                    }
                    catch(SQLiteDatabaseLockedException unused_ex) {
                        goto label_22;
                    }
                    catch(SQLiteException sQLiteException0) {
                        goto label_28;
                    }
                    this.zzt.zzaA().zzd().zzb("Error deleting app launch break from local database", sQLiteFullException0);
                    this.zzb = true;
                    if(sQLiteDatabase0 != null) {
                        goto label_20;
                    }
                    goto label_34;
                }
                catch(Throwable throwable0) {
                    goto label_37;
                }
            label_20:
                sQLiteDatabase0.close();
                goto label_34;
                try {
                label_22:
                    SystemClock.sleep(v);
                    v += 20;
                    if(sQLiteDatabase0 != null) {
                        goto label_25;
                    }
                    goto label_34;
                }
                catch(Throwable throwable0) {
                    goto label_37;
                }
            label_25:
                sQLiteDatabase0.close();
                goto label_34;
                try {
                label_28:
                    if(sQLiteDatabase0 != null && sQLiteDatabase0.inTransaction()) {
                        sQLiteDatabase0.endTransaction();
                    }
                    this.zzt.zzaA().zzd().zzb("Error deleting app launch break from local database", sQLiteException0);
                    this.zzb = true;
                    if(sQLiteDatabase0 != null) {
                        goto label_33;
                    }
                    goto label_34;
                }
                catch(Throwable throwable0) {
                    goto label_37;
                }
            label_33:
                sQLiteDatabase0.close();
            label_34:
                ++v1;
                goto label_6;
            label_37:
                TWR.safeClose$NT(sQLiteDatabase0, throwable0);
                throw throwable0;
            }
            sQLiteDatabase0.close();
            return true;
        }
        return false;
    }

    public final boolean zzn(zzac zzac0) {
        byte[] arr_b = this.zzt.zzv().zzap(zzac0);
        if(arr_b.length > 0x20000) {
            this.zzt.zzaA().zzh().zza("Conditional user property too long for local database. Sending directly to service");
            return false;
        }
        return this.zzq(2, arr_b);
    }

    public final boolean zzo(zzau zzau0) {
        Parcel parcel0 = Parcel.obtain();
        zzav.zza(zzau0, parcel0, 0);
        byte[] arr_b = parcel0.marshall();
        parcel0.recycle();
        if(arr_b.length > 0x20000) {
            this.zzt.zzaA().zzh().zza("Event is too long for local database. Sending event directly to service");
            return false;
        }
        return this.zzq(0, arr_b);
    }

    public final boolean zzp(zzlk zzlk0) {
        Parcel parcel0 = Parcel.obtain();
        zzll.zza(zzlk0, parcel0, 0);
        byte[] arr_b = parcel0.marshall();
        parcel0.recycle();
        if(arr_b.length > 0x20000) {
            this.zzt.zzaA().zzh().zza("User property too long for local database. Sending directly to service");
            return false;
        }
        return this.zzq(1, arr_b);
    }

    private final boolean zzq(int v, byte[] arr_b) {
        long v3;
        Cursor cursor0;
        SQLiteDatabase sQLiteDatabase1;
        SQLiteDatabase sQLiteDatabase0;
        this.zzg();
        if(this.zzb) {
            return false;
        }
        ContentValues contentValues0 = new ContentValues();
        contentValues0.put("type", v);
        contentValues0.put("entry", arr_b);
        int v1 = 5;
        int v2 = 0;
        while(true) {
            try {
                sQLiteDatabase0 = null;
                sQLiteDatabase1 = null;
                sQLiteDatabase1 = this.zzh();
                goto label_18;
            }
            catch(SQLiteFullException sQLiteFullException0) {
            }
            catch(SQLiteDatabaseLockedException unused_ex) {
                goto label_53;
            }
            catch(SQLiteException sQLiteException0) {
                cursor0 = null;
                goto label_65;
            }
            catch(Throwable throwable0) {
                goto label_81;
            }
            cursor0 = null;
            goto label_45;
        label_18:
            if(sQLiteDatabase1 == null) {
                try {
                    this.zzb = true;
                    return false;
                label_21:
                    v3 = 0L;
                    sQLiteDatabase1.beginTransaction();
                    cursor0 = sQLiteDatabase1.rawQuery("select count(1) from messages", null);
                    goto label_31;
                }
                catch(SQLiteFullException sQLiteFullException0) {
                    cursor0 = null;
                    goto label_44;
                }
                catch(SQLiteDatabaseLockedException unused_ex) {
                    goto label_53;
                }
                catch(SQLiteException sQLiteException0) {
                    cursor0 = null;
                    goto label_64;
                }
                catch(Throwable throwable0) {
                    goto label_81;
                }
            }
            goto label_21;
        label_31:
            if(cursor0 != null) {
                try {
                    if(cursor0.moveToFirst()) {
                        v3 = cursor0.getLong(0);
                    }
                label_34:
                    if(v3 >= 100000L) {
                        this.zzt.zzaA().zzd().zza("Data loss, local db full");
                        long v4 = (long)sQLiteDatabase1.delete("messages", "rowid in (select rowid from messages order by rowid asc limit ?)", new String[]{Long.toString(100001L - v3)});
                        if(v4 != 100001L - v3) {
                            this.zzt.zzaA().zzd().zzd("Different delete count than expected in local db. expected, received, difference", ((long)(100001L - v3)), v4, ((long)(100001L - v3 - v4)));
                        }
                    }
                    sQLiteDatabase1.insertOrThrow("messages", null, contentValues0);
                    sQLiteDatabase1.setTransactionSuccessful();
                    sQLiteDatabase1.endTransaction();
                    goto label_86;
                }
                catch(SQLiteFullException sQLiteFullException0) {
                    goto label_44;
                }
                catch(SQLiteDatabaseLockedException unused_ex) {
                    goto label_52;
                }
                catch(SQLiteException sQLiteException0) {
                    goto label_64;
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            goto label_34;
        label_44:
            sQLiteDatabase0 = sQLiteDatabase1;
            try {
            label_45:
                this.zzt.zzaA().zzd().zzb("Error writing entry; local database full", sQLiteFullException0);
                this.zzb = true;
                if(cursor0 != null) {
                    goto label_48;
                }
                goto label_49;
            }
            catch(Throwable throwable0) {
                sQLiteDatabase1 = sQLiteDatabase0;
                break;
            }
        label_48:
            cursor0.close();
        label_49:
            if(sQLiteDatabase0 != null) {
                sQLiteDatabase0.close();
                goto label_77;
            label_52:
                sQLiteDatabase0 = cursor0;
                try {
                label_53:
                    SystemClock.sleep(v1);
                    v1 += 20;
                }
                catch(Throwable throwable0) {
                    goto label_81;
                }
                if(sQLiteDatabase0 != null) {
                    ((Cursor)sQLiteDatabase0).close();
                }
                if(sQLiteDatabase1 != null) {
                    sQLiteDatabase1.close();
                    goto label_77;
                label_64:
                    sQLiteDatabase0 = sQLiteDatabase1;
                    try {
                    label_65:
                        if(sQLiteDatabase0 != null && sQLiteDatabase0.inTransaction()) {
                            sQLiteDatabase0.endTransaction();
                        }
                        this.zzt.zzaA().zzd().zzb("Error writing entry to local database", sQLiteException0);
                        this.zzb = true;
                    }
                    catch(Throwable throwable0) {
                        sQLiteDatabase1 = sQLiteDatabase0;
                        break;
                    }
                    if(cursor0 != null) {
                        cursor0.close();
                    }
                    if(sQLiteDatabase0 != null) {
                        sQLiteDatabase0.close();
                    }
                }
            }
        label_77:
            ++v2;
        }
        sQLiteDatabase0 = cursor0;
    label_81:
        if(sQLiteDatabase0 != null) {
            ((Cursor)sQLiteDatabase0).close();
        }
        if(sQLiteDatabase1 != null) {
            sQLiteDatabase1.close();
        }
        throw throwable0;
    label_86:
        if(cursor0 != null) {
            cursor0.close();
        }
        sQLiteDatabase1.close();
        return true;
    }
}

