package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import java.lang.reflect.Method;

public class ProviderInstaller {
    public interface ProviderInstallListener {
        void onProviderInstallFailed(int arg1, Intent arg2);

        void onProviderInstalled();
    }

    public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
    private static final GoogleApiAvailabilityLight zza;
    private static final Object zzb;
    private static Method zzc;
    private static Method zzd;

    static {
        ProviderInstaller.zza = GoogleApiAvailabilityLight.getInstance();
        ProviderInstaller.zzb = new Object();
        ProviderInstaller.zzc = null;
        ProviderInstaller.zzd = null;
    }

    public static void installIfNeeded(Context context0) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Context context1;
        Preconditions.checkNotNull(context0, "Context must not be null");
        ProviderInstaller.zza.verifyGooglePlayServicesIsAvailable(context0, 11925000);
        synchronized(ProviderInstaller.zzb) {
            long v1 = SystemClock.elapsedRealtime();
            try {
                context1 = DynamiteModule.load(context0, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.providerinstaller.dynamite").getModuleContext();
            }
            catch(LoadingException dynamiteModule$LoadingException0) {
                Log.w("ProviderInstaller", "Failed to load providerinstaller module: " + dynamiteModule$LoadingException0.getMessage());
                context1 = null;
            }
            if(context1 != null) {
                ProviderInstaller.zzc(context1, context0, "com.google.android.gms.providerinstaller.ProviderInstallerImpl");
                return;
            }
            long v2 = SystemClock.elapsedRealtime();
            Context context2 = GooglePlayServicesUtilLight.getRemoteContext(context0);
            if(context2 != null) {
                try {
                    if(ProviderInstaller.zzd == null) {
                        ProviderInstaller.zzd = ProviderInstaller.zzb(context2, "com.google.android.gms.common.security.ProviderInstallerImpl", "reportRequestStats", new Class[]{Context.class, Long.TYPE, Long.TYPE});
                    }
                    ProviderInstaller.zzd.invoke(null, context0, v1, v2);
                }
                catch(Exception exception0) {
                    Log.w("ProviderInstaller", "Failed to report request stats: " + exception0.getMessage());
                }
            }
            if(context2 != null) {
                ProviderInstaller.zzc(context2, context0, "com.google.android.gms.common.security.ProviderInstallerImpl");
                return;
            }
            Log.e("ProviderInstaller", "Failed to get remote context");
        }
        throw new GooglePlayServicesNotAvailableException(8);
    }

    public static void installIfNeededAsync(Context context0, ProviderInstallListener providerInstaller$ProviderInstallListener0) {
        Preconditions.checkNotNull(context0, "Context must not be null");
        Preconditions.checkNotNull(providerInstaller$ProviderInstallListener0, "Listener must not be null");
        Preconditions.checkMainThread("Must be called on the UI thread");
        new zza(context0, providerInstaller$ProviderInstallListener0).execute(new Void[0]);
    }

    private static Method zzb(Context context0, String s, String s1, Class[] arr_class) throws ClassNotFoundException, NoSuchMethodException {
        return context0.getClassLoader().loadClass(s).getMethod(s1, arr_class);
    }

    private static void zzc(Context context0, Context context1, String s) throws GooglePlayServicesNotAvailableException {
        try {
            if(ProviderInstaller.zzc == null) {
                ProviderInstaller.zzc = ProviderInstaller.zzb(context0, s, "insertProvider", new Class[]{Context.class});
            }
            ProviderInstaller.zzc.invoke(null, context0);
        }
        catch(Exception exception0) {
            Throwable throwable0 = exception0.getCause();
            if(Log.isLoggable("ProviderInstaller", 6)) {
                Log.e("ProviderInstaller", "Failed to install provider: " + (throwable0 == null ? exception0.getMessage() : throwable0.getMessage()));
            }
            throw new GooglePlayServicesNotAvailableException(8);
        }
    }
}

