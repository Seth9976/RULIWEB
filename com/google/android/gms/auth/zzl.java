package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.BlockingServiceConnection;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesIncorrectManifestValueException;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.logging.Logger;
import com.google.android.gms.internal.auth.zzbw;
import com.google.android.gms.internal.auth.zzby;
import com.google.android.gms.internal.auth.zzdc;
import com.google.android.gms.internal.auth.zze;
import com.google.android.gms.internal.auth.zzh;
import com.google.android.gms.internal.auth.zzht;
import com.google.android.gms.internal.auth.zzhw;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeoutException;

public class zzl {
    public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
    public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
    public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
    public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
    public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
    public static final String WORK_ACCOUNT_TYPE = "com.google.work";
    public static final String[] zza;
    public static final String zzb;
    private static final ComponentName zzc;
    private static final Logger zzd;

    static {
        zzl.zza = new String[]{"com.google", "com.google.work", "cn.google"};
        zzl.zzb = "androidPackageName";
        zzl.zzc = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
        zzl.zzd = zzd.zza(new String[]{"GoogleAuthUtil"});
    }

    public static void clearToken(Context context0, String s) throws GoogleAuthException, IOException {
        zzl.zze(context0, s, 0L);
    }

    public static List getAccountChangeEvents(Context context0, int v, String s) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(s, "accountName must be provided");
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        zzl.zzk(context0, 8400000);
        AccountChangeEventsRequest accountChangeEventsRequest0 = new AccountChangeEventsRequest();
        accountChangeEventsRequest0.setAccountName(s);
        accountChangeEventsRequest0.setEventIndex(v);
        zzdc.zzd(context0);
        if(zzhw.zzd() && zzl.zzp(context0)) {
            Task task0 = zzh.zza(context0).zzb(accountChangeEventsRequest0);
            try {
                AccountChangeEventsResponse accountChangeEventsResponse0 = (AccountChangeEventsResponse)zzl.zzi(task0, "account change events retrieval");
                zzl.zzj(accountChangeEventsResponse0);
                return accountChangeEventsResponse0.getEvents();
            }
            catch(ApiException apiException0) {
                zzl.zzl(apiException0, "account change events retrieval");
            }
        }
        zzi zzi0 = new zzi(accountChangeEventsRequest0);
        return (List)zzl.zzh(context0, zzl.zzc, zzi0, 0L, null);
    }

    public static String getAccountId(Context context0, String s) throws GoogleAuthException, IOException {
        Preconditions.checkNotEmpty(s, "accountName must be provided");
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        zzl.zzk(context0, 8400000);
        return zzl.getToken(context0, s, "^^_account_id_^^", new Bundle());
    }

    public static String getToken(Context context0, Account account0, String s) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzl.getToken(context0, account0, s, new Bundle());
    }

    public static String getToken(Context context0, Account account0, String s, Bundle bundle0) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        zzl.zzo(account0);
        return zzl.zza(context0, account0, s, bundle0, 0L, null).zza();
    }

    @Deprecated
    public static String getToken(Context context0, String s, String s1) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzl.getToken(context0, new Account(s, "com.google"), s1);
    }

    @Deprecated
    public static String getToken(Context context0, String s, String s1, Bundle bundle0) throws IOException, UserRecoverableAuthException, GoogleAuthException {
        return zzl.getToken(context0, new Account(s, "com.google"), s1, bundle0);
    }

    @Deprecated
    public static void invalidateToken(Context context0, String s) {
        AccountManager.get(context0).invalidateAuthToken("com.google", s);
    }

    public static Bundle removeAccount(Context context0, Account account0) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull(context0);
        zzl.zzo(account0);
        zzl.zzk(context0, 8400000);
        zzdc.zzd(context0);
        if(zzhw.zze() && zzl.zzp(context0)) {
            Task task0 = zzh.zza(context0).zzd(account0);
            try {
                Bundle bundle0 = (Bundle)zzl.zzi(task0, "account removal");
                zzl.zzj(bundle0);
                return bundle0;
            }
            catch(ApiException apiException0) {
                zzl.zzl(apiException0, "account removal");
            }
        }
        zzf zzf0 = new zzf(account0);
        return (Bundle)zzl.zzh(context0, zzl.zzc, zzf0, 0L, null);
    }

    public static Boolean requestGoogleAccountsAccess(Context context0) throws GoogleAuthException, IOException {
        Preconditions.checkNotNull(context0);
        zzl.zzk(context0, 11400000);
        String s = context0.getApplicationInfo().packageName;
        zzdc.zzd(context0);
        if(zzhw.zze() && zzl.zzp(context0)) {
            Task task0 = zzh.zza(context0).zze(s);
            try {
                Bundle bundle0 = (Bundle)zzl.zzi(task0, "google accounts access request");
                String s1 = bundle0.getString("Error");
                Intent intent0 = (Intent)bundle0.getParcelable("userRecoveryIntent");
                PendingIntent pendingIntent0 = (PendingIntent)bundle0.getParcelable("userRecoveryPendingIntent");
                zzby zzby0 = zzby.zza(s1);
                if(zzby.zzc.equals(zzby0)) {
                    return true;
                }
                zzl.zzn(context0, "requestGoogleAccountsAccess", s1, intent0, pendingIntent0);
                throw new GoogleAuthException("Invalid state. Shouldn\'t happen");
            }
            catch(ApiException apiException0) {
                zzl.zzl(apiException0, "google accounts access request");
            }
        }
        zzj zzj0 = new zzj(s, context0);
        return (Boolean)zzl.zzh(context0, zzl.zzc, zzj0, 0L, null);
    }

    public static TokenData zza(Context context0, Account account0, String s, Bundle bundle0, long v, Executor executor0) throws IOException, GoogleAuthException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        Preconditions.checkNotEmpty(s, "Scope cannot be empty or null.");
        zzl.zzo(account0);
        zzl.zzk(context0, 8400000);
        Bundle bundle1 = bundle0 == null ? new Bundle() : new Bundle(bundle0);
        zzl.zzm(context0, bundle1);
        zzdc.zzd(context0);
        if(zzhw.zze() && zzl.zzp(context0)) {
            Task task0 = zzh.zza(context0).zzc(account0, s, bundle1);
            try {
                Bundle bundle2 = (Bundle)zzl.zzi(task0, "token retrieval");
                zzl.zzj(bundle2);
                return zzl.zzg(context0, "getTokenWithDetails", bundle2);
            }
            catch(ApiException apiException0) {
                zzl.zzl(apiException0, "token retrieval");
            }
        }
        zzg zzg0 = new zzg(account0, s, bundle1, context0);
        return (TokenData)zzl.zzh(context0, zzl.zzc, zzg0, 0L, null);
    }

    static TokenData zzb(Account account0, String s, Bundle bundle0, Context context0, IBinder iBinder0) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle1 = zze.zzb(iBinder0).zze(account0, s, bundle0);
        if(bundle1 == null) {
            throw new IOException("Service call returned null");
        }
        return zzl.zzg(context0, "getTokenWithDetails", bundle1);
    }

    static Object zzc(Object object0) {
        zzl.zzj(object0);
        return object0;
    }

    static void zzd(Context context0, String s, String s1, Intent intent0, PendingIntent pendingIntent0) {
        zzl.zzn(context0, "requestGoogleAccountsAccess", s1, intent0, pendingIntent0);
    }

    public static void zze(Context context0, String s, long v) throws GoogleAuthException, IOException {
        Preconditions.checkNotMainThread("Calling this from your main thread can lead to deadlock");
        zzl.zzk(context0, 8400000);
        Bundle bundle0 = new Bundle();
        zzl.zzm(context0, bundle0);
        zzdc.zzd(context0);
        if(zzhw.zze() && zzl.zzp(context0)) {
            com.google.android.gms.internal.auth.zzg zzg0 = zzh.zza(context0);
            zzbw zzbw0 = new zzbw();
            zzbw0.zza(s);
            Task task0 = zzg0.zza(zzbw0);
            try {
                zzl.zzi(task0, "clear token");
                return;
            }
            catch(ApiException apiException0) {
                zzl.zzl(apiException0, "clear token");
            }
        }
        com.google.android.gms.auth.zzh zzh0 = new com.google.android.gms.auth.zzh(s, bundle0);
        zzl.zzh(context0, zzl.zzc, zzh0, 0L, null);
    }

    static void zzf(Intent intent0) {
        if(intent0 != null) {
            String s = intent0.toUri(1);
            try {
                Intent.parseUri(s, 1);
                return;
            }
            catch(URISyntaxException unused_ex) {
                throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
            }
        }
        throw new IllegalArgumentException("Callback cannot be null.");
    }

    private static TokenData zzg(Context context0, String s, Bundle bundle0) throws GoogleAuthException, IOException {
        TokenData tokenData0;
        ClassLoader classLoader0 = TokenData.class.getClassLoader();
        if(classLoader0 != null) {
            bundle0.setClassLoader(classLoader0);
        }
        Bundle bundle1 = bundle0.getBundle("tokenDetails");
        if(bundle1 == null) {
            tokenData0 = null;
        }
        else {
            if(classLoader0 != null) {
                bundle1.setClassLoader(classLoader0);
            }
            tokenData0 = (TokenData)bundle1.getParcelable("TokenData");
        }
        if(tokenData0 != null) {
            return tokenData0;
        }
        zzl.zzn(context0, "getTokenWithDetails", bundle0.getString("Error"), ((Intent)bundle0.getParcelable("userRecoveryIntent")), ((PendingIntent)bundle0.getParcelable("userRecoveryPendingIntent")));
        throw new GoogleAuthException("Invalid state. Shouldn\'t happen");
    }

    private static Object zzh(Context context0, ComponentName componentName0, zzk zzk0, long v, Executor executor0) throws IOException, GoogleAuthException {
        BlockingServiceConnection blockingServiceConnection0 = new BlockingServiceConnection();
        GmsClientSupervisor gmsClientSupervisor0 = GmsClientSupervisor.getInstance(context0);
        try {
            if(!gmsClientSupervisor0.bindService(componentName0, blockingServiceConnection0, "GoogleAuthUtil", null)) {
                throw new IOException("Could not bind to service.");
            }
        }
        catch(SecurityException securityException0) {
            Log.w("GoogleAuthUtil", String.format("SecurityException while bind to auth service: %s", securityException0.getMessage()));
            throw new IOException("SecurityException while binding to Auth service.", securityException0);
        }
        try {
            return zzk0.zza(blockingServiceConnection0.getService());
        }
        catch(TimeoutException | RemoteException | InterruptedException timeoutException0) {
            Log.i("GoogleAuthUtil", "Error on service connection.", timeoutException0);
            throw new IOException("Error on service connection.", timeoutException0);
        }
        finally {
            gmsClientSupervisor0.unbindService(componentName0, blockingServiceConnection0, "GoogleAuthUtil");
        }
    }

    private static Object zzi(Task task0, String s) throws IOException, ApiException {
        try {
            return Tasks.await(task0);
        }
        catch(ExecutionException executionException0) {
            Throwable throwable0 = executionException0.getCause();
            if(throwable0 instanceof ApiException) {
                throw (ApiException)throwable0;
            }
            String s1 = String.format("Unable to get a result for %s due to ExecutionException.", s);
            zzl.zzd.w(s1, new Object[0]);
            throw new IOException(s1, executionException0);
        }
        catch(InterruptedException interruptedException0) {
            String s2 = String.format("Interrupted while waiting for the task of %s to finish.", s);
            zzl.zzd.w(s2, new Object[0]);
            throw new IOException(s2, interruptedException0);
        }
        catch(CancellationException cancellationException0) {
            String s3 = String.format("Canceled while waiting for the task of %s to finish.", s);
            zzl.zzd.w(s3, new Object[0]);
            throw new IOException(s3, cancellationException0);
        }
    }

    private static Object zzj(Object object0) throws IOException {
        if(object0 != null) {
            return object0;
        }
        zzl.zzd.w("Service call returned null.", new Object[0]);
        throw new IOException("Service unavailable.");
    }

    private static void zzk(Context context0, int v) throws GoogleAuthException {
        try {
            GooglePlayServicesUtilLight.ensurePlayServicesAvailable(context0.getApplicationContext(), v);
        }
        catch(GooglePlayServicesRepairableException googlePlayServicesRepairableException0) {
            throw new GooglePlayServicesAvailabilityException(googlePlayServicesRepairableException0.getConnectionStatusCode(), googlePlayServicesRepairableException0.getMessage(), googlePlayServicesRepairableException0.getIntent());
        }
        catch(GooglePlayServicesNotAvailableException | GooglePlayServicesIncorrectManifestValueException exception0) {
            throw new GoogleAuthException(exception0.getMessage(), exception0);
        }
    }

    private static void zzl(ApiException apiException0, String s) {
        Object[] arr_object = {s, Log.getStackTraceString(apiException0)};
        zzl.zzd.w("%s failed via GoogleAuthServiceClient, falling back to previous approach:\n%s", arr_object);
    }

    private static void zzm(Context context0, Bundle bundle0) {
        String s = context0.getApplicationInfo().packageName;
        bundle0.putString("clientPackageName", s);
        if(TextUtils.isEmpty(bundle0.getString("androidPackageName"))) {
            bundle0.putString("androidPackageName", s);
        }
        bundle0.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
    }

    private static void zzn(Context context0, String s, String s1, Intent intent0, PendingIntent pendingIntent0) throws GoogleAuthException, IOException {
        zzby zzby0 = zzby.zza(s1);
        Logger logger0 = zzl.zzd;
        logger0.w(String.format("[GoogleAuthUtil] error status:%s with method:%s", zzby0, s), new Object[0]);
        if(!zzby.zzi.equals(zzby0) && !zzby.zzs.equals(zzby0) && !zzby.zzw.equals(zzby0) && !zzby.zzx.equals(zzby0) && !zzby.zzn.equals(zzby0) && !zzby.zzz.equals(zzby0) && !zzby.zzN.equals(zzby0) && !zzby.zzF.equals(zzby0) && !zzby.zzG.equals(zzby0) && !zzby.zzH.equals(zzby0) && !zzby.zzI.equals(zzby0) && !zzby.zzJ.equals(zzby0) && !zzby.zzK.equals(zzby0) && !zzby.zzM.equals(zzby0) && !zzby.zzE.equals(zzby0) && !zzby.zzL.equals(zzby0)) {
            if(!(!zzby.zze.equals(zzby0) && !zzby.zzf.equals(zzby0) && !zzby.zzg.equals(zzby0) && !zzby.zzaf.equals(zzby0) && !zzby.zzah.equals(zzby0))) {
                throw new IOException(s1);
            }
            throw new GoogleAuthException(s1);
        }
        zzdc.zzd(context0);
        if(!zzht.zzc()) {
            throw new UserRecoverableAuthException(s1, intent0);
        }
        if(pendingIntent0 != null && intent0 != null) {
            throw UserRecoverableAuthException.zza(s1, intent0, pendingIntent0);
        }
        if(GoogleApiAvailability.getInstance().getApkVersion(context0) >= 0x7FFFFFFF && pendingIntent0 == null) {
            logger0.e(String.format("Recovery PendingIntent is missing on current Gms version: %s for method: %s. It should always be present on or above Gms version %s. This indicates a bug in Gms implementation.", 0x7FFFFFFF, s, 0x7FFFFFFF), new Object[0]);
        }
        if(intent0 == null) {
            logger0.e(String.format("no recovery Intent found with status=%s for method=%s. This shouldn\'t happen", s1, s), new Object[0]);
        }
        throw new UserRecoverableAuthException(s1, intent0);
    }

    private static void zzo(Account account0) {
        if(account0 == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        if(TextUtils.isEmpty(account0.name)) {
            throw new IllegalArgumentException("Account name cannot be empty!");
        }
        String[] arr_s = zzl.zza;
        for(int v = 0; v < 3; ++v) {
            if(arr_s[v].equals(account0.type)) {
                return;
            }
        }
        throw new IllegalArgumentException("Account type not supported");
    }

    private static boolean zzp(Context context0) {
        if(GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context0, 17895000) != 0) {
            return false;
        }
        List list0 = zzhw.zzb().zzq();
        String s = context0.getApplicationInfo().packageName;
        for(Object object0: list0) {
            if(((String)object0).equals(s)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }
}

