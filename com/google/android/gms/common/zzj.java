package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzz;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Arrays;

abstract class zzj extends zzz {
    private final int zza;

    protected zzj(byte[] arr_b) {
        Preconditions.checkArgument(arr_b.length == 25);
        this.zza = Arrays.hashCode(arr_b);
    }

    @Override
    public final boolean equals(Object object0) {
        if(object0 != null && object0 instanceof zzaa) {
            try {
                if(((zzaa)object0).zzc() != this.zza) {
                    return false;
                }
                IObjectWrapper iObjectWrapper0 = ((zzaa)object0).zzd();
                if(iObjectWrapper0 == null) {
                    return false;
                }
                byte[] arr_b = (byte[])ObjectWrapper.unwrap(iObjectWrapper0);
                return Arrays.equals(this.zzf(), arr_b);
            }
            catch(RemoteException remoteException0) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", remoteException0);
            }
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return this.zza;
    }

    @Override  // com.google.android.gms.common.internal.zzaa
    public final int zzc() {
        return this.zza;
    }

    @Override  // com.google.android.gms.common.internal.zzaa
    public final IObjectWrapper zzd() {
        return ObjectWrapper.wrap(this.zzf());
    }

    protected static byte[] zze(String s) [...] // 潜在的解密器

    abstract byte[] zzf();
}

