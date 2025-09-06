package com.google.android.gms.internal.auth;

import android.os.Binder;

public final class zzcj {
    public static Object zza(zzck zzck0) {
        try {
            return zzck0.zza();
        }
        catch(SecurityException unused_ex) {
            long v = Binder.clearCallingIdentity();
            try {
                return zzck0.zza();
            }
            finally {
                Binder.restoreCallingIdentity(v);
            }
        }
    }
}

