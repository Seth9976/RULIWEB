package com.google.android.gms.ads.identifier;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.internal.ads_identifier.zze;
import com.google.android.gms.internal.ads_identifier.zzf;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.annotation.ParametersAreNonnullByDefault;
import jeb.synthetic.FIN;

@ParametersAreNonnullByDefault
public class AdvertisingIdClient {
    public static final class Info {
        private final String zza;
        private final boolean zzb;

        @Deprecated
        public Info(String s, boolean z) {
            this.zza = s;
            this.zzb = z;
        }

        public String getId() {
            return this.zza;
        }

        public boolean isLimitAdTrackingEnabled() {
            return this.zzb;
        }

        @Override
        public String toString() {
            return "{" + this.zza + "}" + this.zzb;
        }
    }

    BlockingServiceConnection zza;
    zzf zzb;
    boolean zzc;
    final Object zzd;
    zzb zze;
    final long zzf;
    private final Context zzg;

    public AdvertisingIdClient(Context context0) {
        this(context0, 30000L, false, false);
    }

    public AdvertisingIdClient(Context context0, long v, boolean z, boolean z1) {
        this.zzd = new Object();
        Preconditions.checkNotNull(context0);
        if(z) {
            Context context1 = context0.getApplicationContext();
            if(context1 != null) {
                context0 = context1;
            }
        }
        this.zzg = context0;
        this.zzc = false;
        this.zzf = v;
    }

    @Override
    protected final void finalize() throws Throwable {
        this.zza();
        super.finalize();
    }

    public static Info getAdvertisingIdInfo(Context context0) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        Info advertisingIdClient$Info0;
        AdvertisingIdClient advertisingIdClient0 = new AdvertisingIdClient(context0, -1L, true, false);
        try {
            long v = SystemClock.elapsedRealtime();
            advertisingIdClient0.zzb(false);
            advertisingIdClient$Info0 = advertisingIdClient0.zzd(-1);
            advertisingIdClient0.zzc(advertisingIdClient$Info0, true, 0.0f, SystemClock.elapsedRealtime() - v, "", null);
        }
        catch(Throwable throwable0) {
            try {
                advertisingIdClient0.zzc(null, true, 0.0f, -1L, "", throwable0);
                throw throwable0;
            }
            catch(Throwable throwable1) {
                advertisingIdClient0.zza();
                throw throwable1;
            }
        }
        advertisingIdClient0.zza();
        return advertisingIdClient$Info0;
    }

    public Info getInfo() throws IOException {
        return this.zzd(-1);
    }

    public static boolean getIsAdIdFakeForDebugLogging(Context context0) throws IOException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        boolean z;
        AdvertisingIdClient advertisingIdClient0 = new AdvertisingIdClient(context0, -1L, false, false);
        try {
            advertisingIdClient0.zzb(false);
            Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
            synchronized(advertisingIdClient0) {
                if(!advertisingIdClient0.zzc) {
                    Object object0 = advertisingIdClient0.zzd;
                    synchronized(object0) {
                        if(advertisingIdClient0.zze == null || !advertisingIdClient0.zze.zzb) {
                            throw new IOException("AdvertisingIdClient is not connected.");
                        }
                    }
                    try {
                        advertisingIdClient0.zzb(false);
                    }
                    catch(Exception exception0) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.", exception0);
                    }
                    if(!advertisingIdClient0.zzc) {
                        throw new IOException("AdvertisingIdClient cannot reconnect.");
                    }
                }
                Preconditions.checkNotNull(advertisingIdClient0.zza);
                Preconditions.checkNotNull(advertisingIdClient0.zzb);
                try {
                    z = advertisingIdClient0.zzb.zzd();
                }
                catch(RemoteException remoteException0) {
                    Log.i("AdvertisingIdClient", "GMS remote exception ", remoteException0);
                    throw new IOException("Remote exception");
                }
            }
            advertisingIdClient0.zze();
            return z;
        }
        finally {
            advertisingIdClient0.zza();
        }
    }

    public static void setShouldSkipGmsCoreVersionCheck(boolean z) {
    }

    public void start() throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        this.zzb(true);
    }

    public final void zza() {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized(this) {
            if(this.zzg != null && this.zza != null) {
                if(this.zzc) {
                    try {
                        ConnectionTracker.getInstance().unbindService(this.zzg, this.zza);
                    }
                    catch(Throwable throwable0) {
                        Log.i("AdvertisingIdClient", "AdvertisingIdClient unbindService failed.", throwable0);
                    }
                }
                this.zzc = false;
                this.zzb = null;
                this.zza = null;
            }
        }
    }

    protected final void zzb(boolean z) throws IOException, IllegalStateException, GooglePlayServicesNotAvailableException, GooglePlayServicesRepairableException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        __monitor_enter(this);
        int v = FIN.finallyOpen$NT();
        if(this.zzc) {
            this.zza();
        }
        try {
            Context context0 = this.zzg;
            context0.getPackageManager().getPackageInfo("com.android.vending", 0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            FIN.finallyExec$NT(v);
            throw new GooglePlayServicesNotAvailableException(9);
        }
        switch(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context0, 12451000)) {
            case 0: 
            case 2: {
                BlockingServiceConnection blockingServiceConnection0 = new BlockingServiceConnection();
                Intent intent0 = new Intent("com.google.android.gms.ads.identifier.service.START");
                intent0.setPackage("com.google.android.gms");
                try {
                    if(ConnectionTracker.getInstance().bindService(context0, intent0, blockingServiceConnection0, 1)) {
                        goto label_21;
                    }
                    goto label_36;
                }
                catch(Throwable throwable0) {
                    FIN.finallyExec$NT(v);
                    throw new IOException(throwable0);
                }
                try {
                label_21:
                    this.zza = blockingServiceConnection0;
                    this.zzb = zze.zza(blockingServiceConnection0.getServiceWithTimeout(10000L, TimeUnit.MILLISECONDS));
                    this.zzc = true;
                }
                catch(InterruptedException unused_ex) {
                    FIN.finallyExec$NT(v);
                    throw new IOException("Interrupted exception");
                }
                catch(Throwable throwable1) {
                    FIN.finallyExec$NT(v);
                    throw new IOException(throwable1);
                }
                if(z) {
                    this.zze();
                }
                FIN.finallyCodeBegin$NT(v);
                __monitor_exit(this);
                FIN.finallyCodeEnd$NT(v);
                return;
            label_36:
                FIN.finallyExec$NT(v);
                throw new IOException("Connection failure");
            }
            default: {
                FIN.finallyExec$NT(v);
                throw new IOException("Google Play services not available");
            }
        }
    }

    final boolean zzc(Info advertisingIdClient$Info0, boolean z, float f, long v, String s, Throwable throwable0) {
        if(Math.random() <= 0.0) {
            HashMap hashMap0 = new HashMap();
            String s1 = "1";
            hashMap0.put("app_context", "1");
            if(advertisingIdClient$Info0 != null) {
                if(!advertisingIdClient$Info0.isLimitAdTrackingEnabled()) {
                    s1 = "0";
                }
                hashMap0.put("limit_ad_tracking", s1);
                String s2 = advertisingIdClient$Info0.getId();
                if(s2 != null) {
                    hashMap0.put("ad_id_size", Integer.toString(s2.length()));
                }
            }
            if(throwable0 != null) {
                hashMap0.put("error", throwable0.getClass().getName());
            }
            hashMap0.put("tag", "AdvertisingIdClient");
            hashMap0.put("time_spent", Long.toString(v));
            new zza(this, hashMap0).start();
            return true;
        }
        return false;
    }

    private final Info zzd(int v) throws IOException {
        Info advertisingIdClient$Info0;
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        synchronized(this) {
            if(!this.zzc) {
                Object object0 = this.zzd;
                synchronized(object0) {
                    if(this.zze == null || !this.zze.zzb) {
                        throw new IOException("AdvertisingIdClient is not connected.");
                    }
                }
                try {
                    this.zzb(false);
                }
                catch(Exception exception0) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.", exception0);
                }
                if(!this.zzc) {
                    throw new IOException("AdvertisingIdClient cannot reconnect.");
                }
            }
            Preconditions.checkNotNull(this.zza);
            Preconditions.checkNotNull(this.zzb);
            try {
                advertisingIdClient$Info0 = new Info(this.zzb.zzc(), this.zzb.zze(true));
            }
            catch(RemoteException remoteException0) {
                Log.i("AdvertisingIdClient", "GMS remote exception ", remoteException0);
                throw new IOException("Remote exception");
            }
        }
        this.zze();
        return advertisingIdClient$Info0;
    }

    private final void zze() {
        synchronized(this.zzd) {
            zzb zzb0 = this.zze;
            if(zzb0 != null) {
                zzb0.zza.countDown();
                try {
                    this.zze.join();
                }
                catch(InterruptedException unused_ex) {
                }
            }
            long v1 = this.zzf;
            if(v1 > 0L) {
                this.zze = new zzb(this, v1);
            }
        }
    }
}

