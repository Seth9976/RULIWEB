package com.google.android.gms.common;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzag;
import com.google.android.gms.common.util.AndroidUtilsLight;
import com.google.android.gms.common.util.Hex;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.errorprone.annotations.CheckReturnValue;
import java.security.MessageDigest;

@CheckReturnValue
final class zzn {
    static final zzl zza;
    static final zzl zzb;
    static final zzl zzc;
    static final zzl zzd;
    private static volatile zzag zze;
    private static final Object zzf;
    private static Context zzg;

    static {
        zzn.zza = new zzf(new byte[]{0x30, (byte)0x82, 5, -56, 0x30, (byte)0x82, 3, -80, (byte)0xA0, 3, 2, 1, 2, 2, 20, 16, (byte)0x8A, 101, 8, 0x73, -7, 0x2F, (byte)0x8E, 81, -19});
        zzn.zzb = new zzg(new byte[]{0x30, (byte)0x82, 6, 4, 0x30, (byte)0x82, 3, -20, (byte)0xA0, 3, 2, 1, 2, 2, 20, 3, -93, -78, -83, -41, (byte)0xE1, 0x72, -54, 107, -20});
        zzn.zzc = new zzh(new byte[]{0x30, (byte)0x82, 4, 67, 0x30, (byte)0x82, 3, 43, (byte)0xA0, 3, 2, 1, 2, 2, 9, 0, -62, (byte)0xE0, (byte)0x87, 70, 100, 74, 0x30, (byte)0x8D, 0x30});
        zzn.zzd = new zzi(new byte[]{0x30, (byte)0x82, 4, -88, 0x30, (byte)0x82, 3, (byte)0x90, (byte)0xA0, 3, 2, 1, 2, 2, 9, 0, -43, (byte)0x85, -72, 108, 0x7D, -45, 78, -11, 0x30});
        zzn.zzf = new Object();
    }

    static zzx zza(String s, zzj zzj0, boolean z, boolean z1) {
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
        try {
            return zzn.zzh(s, zzj0, z, z1);
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
    }

    static zzx zzb(String s, boolean z, boolean z1, boolean z2) {
        return zzn.zzi(s, z, false, false, true);
    }

    static zzx zzc(String s, boolean z, boolean z1, boolean z2) {
        return zzn.zzi(s, z, false, false, false);
    }

    // 检测为 Lambda 实现
    static String zzd(boolean z, String s, zzj zzj0) throws Exception [...]

    static void zze(Context context0) {
        synchronized(zzn.class) {
            if(zzn.zzg == null) {
                if(context0 != null) {
                    zzn.zzg = context0.getApplicationContext();
                    return;
                }
                return;
            }
            Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
        }
    }

    static boolean zzf() {
        boolean z;
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
        try {
            zzn.zzj();
            z = zzn.zze.zzg();
        }
        catch(LoadingException | RemoteException dynamiteModule$LoadingException0) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", dynamiteModule$LoadingException0);
            z = false;
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
        return z;
    }

    static boolean zzg() {
        boolean z;
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
        try {
            zzn.zzj();
            z = zzn.zze.zzi();
        }
        catch(LoadingException | RemoteException dynamiteModule$LoadingException0) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", dynamiteModule$LoadingException0);
            z = false;
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
        return z;
    }

    private static zzx zzh(String s, zzj zzj0, boolean z, boolean z1) {
        try {
            zzn.zzj();
        }
        catch(LoadingException dynamiteModule$LoadingException0) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", dynamiteModule$LoadingException0);
            return zzx.zzd(("module init: " + dynamiteModule$LoadingException0.getMessage()), dynamiteModule$LoadingException0);
        }
        Preconditions.checkNotNull(zzn.zzg);
        zzs zzs0 = new zzs(s, zzj0, z, z1);
        try {
            if(zzn.zze.zzh(zzs0, ObjectWrapper.wrap(zzn.zzg.getPackageManager()))) {
                return zzx.zzb();
            }
        }
        catch(RemoteException remoteException0) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", remoteException0);
            return zzx.zzd("module call", remoteException0);
        }
        return new zzv(() -> {
            String s1 = z || !zzn.zzh(s, zzj0, true, false).zza ? "not allowed" : "debug cert rejected";
            MessageDigest messageDigest0 = AndroidUtilsLight.zza("SHA-256");
            Preconditions.checkNotNull(messageDigest0);
            return String.format("%s: pkg=%s, sha256=%s, atk=%s, ver=%s", s1, s, Hex.bytesToStringLowercase(messageDigest0.digest(zzj0.zzf())), Boolean.valueOf(z), "12451000.false");
        }, null);
    }

    private static zzx zzi(String s, boolean z, boolean z1, boolean z2, boolean z3) {
        zzq zzq0;
        zzx zzx0;
        StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
        try {
            Preconditions.checkNotNull(zzn.zzg);
            try {
                zzn.zzj();
            }
            catch(LoadingException dynamiteModule$LoadingException0) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", dynamiteModule$LoadingException0);
                return zzx.zzd(("module init: " + dynamiteModule$LoadingException0.getMessage()), dynamiteModule$LoadingException0);
            }
            zzo zzo0 = new zzo(s, z, false, ((IBinder)ObjectWrapper.wrap(zzn.zzg)), false, true);
            try {
                zzq0 = z3 ? zzn.zze.zze(zzo0) : zzn.zze.zzf(zzo0);
            }
            catch(RemoteException remoteException0) {
                Log.e("GoogleCertificates", "Failed to get Google certificates from remote", remoteException0);
                return zzx.zzd("module call", remoteException0);
            }
            if(zzq0.zzb()) {
                zzx0 = zzx.zzf(zzq0.zzc());
            }
            else {
                String s1 = zzq0.zza();
                PackageManager.NameNotFoundException packageManager$NameNotFoundException0 = zzq0.zzd() == 4 ? new PackageManager.NameNotFoundException() : null;
                if(s1 == null) {
                    s1 = "error checking package certificate";
                }
                zzx0 = zzx.zzg(zzq0.zzc(), zzq0.zzd(), s1, packageManager$NameNotFoundException0);
            }
            return zzx0;
        }
        finally {
            StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
        }
    }

    private static void zzj() throws LoadingException {
        if(zzn.zze != null) {
            return;
        }
        Preconditions.checkNotNull(zzn.zzg);
        synchronized(zzn.zzf) {
            if(zzn.zze == null) {
                zzn.zze = zzaf.zzb(DynamiteModule.load(zzn.zzg, DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING, "com.google.android.gms.googlecertificates").instantiate("com.google.android.gms.common.GoogleCertificatesImpl"));
            }
        }
    }
}

