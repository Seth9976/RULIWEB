package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

final class zza extends AsyncTask {
    final Context zza;
    final ProviderInstallListener zzb;

    zza(Context context0, ProviderInstallListener providerInstaller$ProviderInstallListener0) {
        this.zza = context0;
        this.zzb = providerInstaller$ProviderInstallListener0;
        super();
    }

    @Override  // android.os.AsyncTask
    protected final Object doInBackground(Object[] arr_object) {
        Void[] arr_void = (Void[])arr_object;
        try {
            ProviderInstaller.installIfNeeded(this.zza);
            return 0;
        }
        catch(GooglePlayServicesRepairableException googlePlayServicesRepairableException0) {
            return googlePlayServicesRepairableException0.getConnectionStatusCode();
        }
        catch(GooglePlayServicesNotAvailableException googlePlayServicesNotAvailableException0) {
            return googlePlayServicesNotAvailableException0.errorCode;
        }
    }

    @Override  // android.os.AsyncTask
    protected final void onPostExecute(Object object0) {
        if(((int)(((Integer)object0))) == 0) {
            this.zzb.onProviderInstalled();
            return;
        }
        Intent intent0 = ProviderInstaller.zza.getErrorResolutionIntent(this.zza, ((int)(((Integer)object0))), "pi");
        this.zzb.onProviderInstallFailed(((int)(((Integer)object0))), intent0);
    }
}

