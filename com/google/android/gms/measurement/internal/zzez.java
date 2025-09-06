package com.google.android.gms.measurement.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public final class zzez extends zzku {
    public zzez(zzlh zzlh0) {
        super(zzlh0);
    }

    public final boolean zza() {
        this.zzW();
        ConnectivityManager connectivityManager0 = (ConnectivityManager)this.zzt.zzaw().getSystemService("connectivity");
        NetworkInfo networkInfo0 = null;
        if(connectivityManager0 != null) {
            try {
                networkInfo0 = connectivityManager0.getActiveNetworkInfo();
            }
            catch(SecurityException unused_ex) {
            }
        }
        return networkInfo0 != null && networkInfo0.isConnected();
    }

    @Override  // com.google.android.gms.measurement.internal.zzku
    protected final boolean zzb() {
        return false;
    }
}

