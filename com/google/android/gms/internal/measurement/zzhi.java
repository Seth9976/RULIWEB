package com.google.android.gms.internal.measurement;

import android.os.Binder;

public final class zzhi {
    public static Object zza(zzhj zzhj0) {
        try {
            return zzhj0.zza();
        }
        catch(SecurityException unused_ex) {
            long v = Binder.clearCallingIdentity();
            try {
                return zzhj0.zza();
            }
            finally {
                Binder.restoreCallingIdentity(v);
            }
        }
    }
}

