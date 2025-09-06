package com.google.android.gms.internal.auth;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import android.util.Log;
import androidx.collection.ArrayMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jeb.synthetic.TWR;

public final class zzcg implements zzcl {
    public static final String[] zza;
    private static final Map zzb;
    private final ContentResolver zzc;
    private final Uri zzd;
    private final Runnable zze;
    private final ContentObserver zzf;
    private final Object zzg;
    private volatile Map zzh;
    private final List zzi;

    static {
        zzcg.zzb = new ArrayMap();
        zzcg.zza = new String[]{"key", "value"};
    }

    private zzcg(ContentResolver contentResolver0, Uri uri0, Runnable runnable0) {
        zzcf zzcf0 = new zzcf(this, null);
        this.zzf = zzcf0;
        this.zzg = new Object();
        this.zzi = new ArrayList();
        contentResolver0.getClass();
        uri0.getClass();
        this.zzc = contentResolver0;
        this.zzd = uri0;
        this.zze = runnable0;
        contentResolver0.registerContentObserver(uri0, false, zzcf0);
    }

    public static zzcg zza(ContentResolver contentResolver0, Uri uri0, Runnable runnable0) {
        synchronized(zzcg.class) {
            Map map0 = zzcg.zzb;
            zzcg zzcg0 = (zzcg)map0.get(uri0);
            if(zzcg0 == null) {
                try {
                    zzcg zzcg1 = new zzcg(contentResolver0, uri0, runnable0);
                    try {
                        map0.put(uri0, zzcg1);
                    }
                    catch(SecurityException unused_ex) {
                    }
                    zzcg0 = zzcg1;
                }
                catch(SecurityException unused_ex) {
                }
            }
            return zzcg0;
        }
    }

    @Override  // com.google.android.gms.internal.auth.zzcl
    public final Object zzb(String s) {
        Map map1;
        Map map0 = this.zzh;
        if(map0 == null) {
            synchronized(this.zzg) {
                map0 = this.zzh;
                if(map0 == null) {
                    StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
                    try {
                        try {
                            map1 = (Map)zzcj.zza(() -> {
                                Map map1;
                                Map map0;
                                Cursor cursor0 = this.zzc.query(this.zzd, zzcg.zza, null, null, null);
                                if(cursor0 == null) {
                                    return Collections.EMPTY_MAP;
                                }
                                try {
                                    int v = cursor0.getCount();
                                    if(v != 0) {
                                        map0 = v <= 0x100 ? new ArrayMap(v) : new HashMap(v, 1.0f);
                                        while(true) {
                                            if(!cursor0.moveToNext()) {
                                                goto label_12;
                                            }
                                            map0.put(cursor0.getString(0), cursor0.getString(1));
                                        }
                                    }
                                    goto label_14;
                                }
                                catch(Throwable throwable0) {
                                    goto label_17;
                                }
                            label_12:
                                cursor0.close();
                                return map0;
                                try {
                                label_14:
                                    map1 = Collections.EMPTY_MAP;
                                }
                                catch(Throwable throwable0) {
                                label_17:
                                    TWR.safeClose$NT(cursor0, throwable0);
                                    throw throwable0;
                                }
                                cursor0.close();
                                return map1;
                            });
                            goto label_18;
                        }
                        catch(SecurityException | SQLiteException | IllegalStateException unused_ex) {
                        }
                        Log.e("ConfigurationContentLdr", "PhenotypeFlag unable to load ContentProvider, using default values");
                    }
                    catch(Throwable throwable0) {
                        StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
                        throw throwable0;
                    }
                    StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
                    map1 = null;
                    goto label_19;
                label_18:
                    StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
                label_19:
                    this.zzh = map1;
                    map0 = map1;
                }
            }
        }
        if(map0 == null) {
            map0 = Collections.EMPTY_MAP;
        }
        return (String)map0.get(s);
    }

    // 检测为 Lambda 实现
    final Map zzc() [...]

    static void zzd() {
        synchronized(zzcg.class) {
            for(Object object0: zzcg.zzb.values()) {
                ((zzcg)object0).zzc.unregisterContentObserver(((zzcg)object0).zzf);
            }
            zzcg.zzb.clear();
        }
    }

    public final void zze() {
        synchronized(this.zzg) {
            this.zzh = null;
            zzdc.zzc();
        }
        synchronized(this) {
            for(Object object0: this.zzi) {
                ((zzch)object0).zza();
            }
        }
    }
}

