package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.SharedPreferences;
import androidx.collection.ArrayMap;
import java.util.Iterator;
import java.util.Map;

public final class zzic implements zzhk {
    private static final Map zza;
    private final SharedPreferences zzb;
    private final SharedPreferences.OnSharedPreferenceChangeListener zzc;

    static {
        zzic.zza = new ArrayMap();
    }

    // 去混淆评级： 低(30)
    static zzic zza(Context context0, String s, Runnable runnable0) {
        throw null;
    }

    @Override  // com.google.android.gms.internal.measurement.zzhk
    public final Object zzb(String s) {
        throw null;
    }

    static void zzc() {
        synchronized(zzic.class) {
            Map map0 = zzic.zza;
            Iterator iterator0 = map0.values().iterator();
            if(!iterator0.hasNext()) {
                map0.clear();
                return;
            }
            Object object0 = iterator0.next();
            zzic zzic0 = (zzic)object0;
        }
        throw null;
    }
}

