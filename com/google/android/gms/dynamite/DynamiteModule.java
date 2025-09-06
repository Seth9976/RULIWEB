package com.google.android.gms.dynamite;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.SystemClock;
import android.util.Log;
import androidx.work.impl.utils.NetworkApi23..ExternalSyntheticApiModelOutline0;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.errorprone.annotations.ResultIgnorabilityUnspecified;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class DynamiteModule {
    public static class DynamiteLoaderClassLoader {
        public static ClassLoader sClassLoader;

    }

    public static class LoadingException extends Exception {
        LoadingException(String s, zzp zzp0) {
            super(s);
        }

        LoadingException(String s, Throwable throwable0, zzp zzp0) {
            super(s, throwable0);
        }
    }

    public interface VersionPolicy {
        public interface IVersions {
            int zza(Context arg1, String arg2);

            int zzb(Context arg1, String arg2, boolean arg3) throws LoadingException;
        }

        public static class SelectionResult {
            public int localVersion;
            public int remoteVersion;
            public int selection;

            public SelectionResult() {
                this.localVersion = 0;
                this.remoteVersion = 0;
                this.selection = 0;
            }
        }

        SelectionResult selectModule(Context arg1, String arg2, IVersions arg3) throws LoadingException;
    }

    public static final int LOCAL = -1;
    public static final int NONE = 0;
    public static final int NO_SELECTION = 0;
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION = null;
    public static final VersionPolicy PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = null;
    public static final VersionPolicy PREFER_HIGHEST_OR_REMOTE_VERSION = null;
    public static final VersionPolicy PREFER_LOCAL = null;
    public static final VersionPolicy PREFER_REMOTE = null;
    public static final VersionPolicy PREFER_REMOTE_VERSION_NO_FORCE_STAGING = null;
    public static final int REMOTE = 1;
    public static final VersionPolicy zza = null;
    private static Boolean zzb = null;
    private static String zzc = null;
    private static boolean zzd = false;
    private static int zze = -1;
    private static Boolean zzf;
    private static final ThreadLocal zzg;
    private static final ThreadLocal zzh;
    private static final IVersions zzi;
    private final Context zzj;
    private static zzq zzk;
    private static zzr zzl;

    static {
        DynamiteModule.zzg = new ThreadLocal();
        DynamiteModule.zzh = new zzd();
        DynamiteModule.zzi = new zze();
        DynamiteModule.PREFER_REMOTE = new zzf();
        DynamiteModule.PREFER_LOCAL = new zzg();
        DynamiteModule.PREFER_REMOTE_VERSION_NO_FORCE_STAGING = new zzh();
        DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION = new zzi();
        DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING = new zzj();
        DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION = new zzk();
        DynamiteModule.zza = new zzl();
    }

    private DynamiteModule(Context context0) {
        Preconditions.checkNotNull(context0);
        this.zzj = context0;
    }

    public static int getLocalVersion(Context context0, String s) {
        try {
            Class class0 = context0.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.descriptors." + s + ".ModuleDescriptor");
            Field field0 = class0.getDeclaredField("MODULE_ID");
            Field field1 = class0.getDeclaredField("MODULE_VERSION");
            if(!Objects.equal(field0.get(null), s)) {
                Log.e("DynamiteModule", "Module descriptor id \'" + field0.get(null) + "\' didn\'t match expected id \'" + s + "\'");
                return 0;
            }
            return field1.getInt(null);
        }
        catch(ClassNotFoundException unused_ex) {
            Log.w("DynamiteModule", "Local module descriptor class for " + s + " not found.");
            return 0;
        }
        catch(Exception exception0) {
            Log.e("DynamiteModule", "Failed to load module descriptor class: " + exception0.getMessage());
            return 0;
        }
    }

    @ResultIgnorabilityUnspecified
    public Context getModuleContext() {
        return this.zzj;
    }

    public static int getRemoteVersion(Context context0, String s) {
        return DynamiteModule.zza(context0, s, false);
    }

    public IBinder instantiate(String s) throws LoadingException {
        try {
            return (IBinder)this.zzj.getClassLoader().loadClass(s).newInstance();
        }
        catch(ClassNotFoundException | InstantiationException | IllegalAccessException classNotFoundException0) {
            throw new LoadingException("Failed to instantiate module class: " + s, classNotFoundException0, null);
        }
    }

    @ResultIgnorabilityUnspecified
    public static DynamiteModule load(Context context0, VersionPolicy dynamiteModule$VersionPolicy0, String s) throws LoadingException {
        boolean z;
        zzr zzr0;
        SelectionResult dynamiteModule$VersionPolicy$SelectionResult0;
        DynamiteModule dynamiteModule1;
        IObjectWrapper iObjectWrapper0;
        IObjectWrapper iObjectWrapper1;
        Context context1 = context0.getApplicationContext();
        if(context1 == null) {
            throw new LoadingException("null application Context", null);
        }
        zzn zzn0 = (zzn)DynamiteModule.zzg.get();
        zzn zzn1 = new zzn(null);
        DynamiteModule.zzg.set(zzn1);
        Long long0 = (Long)DynamiteModule.zzh.get();
        long v = (long)long0;
        try {
            Long long1 = SystemClock.elapsedRealtime();
            DynamiteModule.zzh.set(long1);
            dynamiteModule$VersionPolicy$SelectionResult0 = dynamiteModule$VersionPolicy0.selectModule(context0, s, DynamiteModule.zzi);
            ThreadLocal threadLocal0 = DynamiteModule.zzg;
            Log.i("DynamiteModule", "Considering local module " + s + ":" + dynamiteModule$VersionPolicy$SelectionResult0.localVersion + " and remote module " + s + ":" + dynamiteModule$VersionPolicy$SelectionResult0.remoteVersion);
            int v2 = dynamiteModule$VersionPolicy$SelectionResult0.selection;
            if(v2 != 0) {
                if(v2 != -1) {
                label_18:
                    if(v2 != 1 || dynamiteModule$VersionPolicy$SelectionResult0.remoteVersion != 0) {
                        if(v2 == -1) {
                            return DynamiteModule.zzc(context1, s);
                        }
                        if(v2 == 1) {
                            try {
                                int v3 = dynamiteModule$VersionPolicy$SelectionResult0.remoteVersion;
                                try {
                                    Class class0 = DynamiteModule.class;
                                    synchronized(class0) {
                                        if(!DynamiteModule.zzf(context0)) {
                                            throw new LoadingException("Remote loading disabled", null);
                                        }
                                        Boolean boolean0 = DynamiteModule.zzb;
                                    }
                                    if(boolean0 == null) {
                                        throw new LoadingException("Failed to determine which loading route to use.", null);
                                    }
                                    if(boolean0.booleanValue()) {
                                        Log.i("DynamiteModule", "Selected remote version of " + s + ", version >= " + v3);
                                        synchronized(DynamiteModule.class) {
                                            zzr0 = DynamiteModule.zzl;
                                        }
                                        if(zzr0 == null) {
                                            throw new LoadingException("DynamiteLoaderV2 was not cached.", null);
                                        }
                                        zzn zzn3 = (zzn)threadLocal0.get();
                                        if(zzn3 == null || zzn3.zza == null) {
                                            throw new LoadingException("No result cursor", null);
                                        }
                                        Context context2 = context0.getApplicationContext();
                                        Cursor cursor0 = zzn3.zza;
                                        ObjectWrapper.wrap(null);
                                        Class class1 = DynamiteModule.class;
                                        synchronized(class1) {
                                            z = DynamiteModule.zze >= 2;
                                        }
                                        Boolean.valueOf(z).getClass();
                                        if(z) {
                                            Log.v("DynamiteModule", "Dynamite loader version >= 2, using loadModule2NoCrashUtils");
                                            iObjectWrapper1 = zzr0.zzf(ObjectWrapper.wrap(context2), s, v3, ObjectWrapper.wrap(cursor0));
                                        }
                                        else {
                                            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to loadModule2");
                                            iObjectWrapper1 = zzr0.zze(ObjectWrapper.wrap(context2), s, v3, ObjectWrapper.wrap(cursor0));
                                        }
                                        Context context3 = (Context)ObjectWrapper.unwrap(iObjectWrapper1);
                                        if(context3 == null) {
                                            throw new LoadingException("Failed to get module context", null);
                                        }
                                        dynamiteModule1 = new DynamiteModule(context3);
                                    }
                                    else {
                                        Log.i("DynamiteModule", "Selected remote version of " + s + ", version >= " + v3);
                                        zzq zzq0 = DynamiteModule.zzg(context0);
                                        if(zzq0 == null) {
                                            throw new LoadingException("Failed to create IDynamiteLoader.", null);
                                        }
                                        int v5 = zzq0.zze();
                                        if(v5 >= 3) {
                                            zzn zzn2 = (zzn)threadLocal0.get();
                                            if(zzn2 == null) {
                                                throw new LoadingException("No cached result cursor holder", null);
                                            }
                                            iObjectWrapper0 = zzq0.zzi(ObjectWrapper.wrap(context0), s, v3, ObjectWrapper.wrap(zzn2.zza));
                                        }
                                        else if(v5 == 2) {
                                            Log.w("DynamiteModule", "IDynamite loader version = 2");
                                            iObjectWrapper0 = zzq0.zzj(ObjectWrapper.wrap(context0), s, v3);
                                        }
                                        else {
                                            Log.w("DynamiteModule", "Dynamite loader version < 2, falling back to createModuleContext");
                                            iObjectWrapper0 = zzq0.zzh(ObjectWrapper.wrap(context0), s, v3);
                                        }
                                        Object object0 = ObjectWrapper.unwrap(iObjectWrapper0);
                                        if(object0 == null) {
                                            throw new LoadingException("Failed to load remote module.", null);
                                        }
                                        dynamiteModule1 = new DynamiteModule(((Context)object0));
                                    }
                                    return dynamiteModule1;
                                }
                                catch(RemoteException remoteException0) {
                                    throw new LoadingException("Failed to load remote module.", remoteException0, null);
                                }
                                catch(LoadingException dynamiteModule$LoadingException1) {
                                    throw dynamiteModule$LoadingException1;
                                }
                                catch(Throwable throwable0) {
                                    CrashUtils.addDynamiteErrorToDropBox(context0, throwable0);
                                    throw new LoadingException("Failed to load remote module.", throwable0, null);
                                }
                            }
                            catch(LoadingException dynamiteModule$LoadingException0) {
                                Log.w("DynamiteModule", "Failed to load remote module: " + dynamiteModule$LoadingException0.getMessage());
                                if(dynamiteModule$VersionPolicy$SelectionResult0.localVersion == 0 || dynamiteModule$VersionPolicy0.selectModule(context0, s, new zzo(dynamiteModule$VersionPolicy$SelectionResult0.localVersion, 0)).selection != -1) {
                                    throw new LoadingException("Remote load failed. No local fallback found.", dynamiteModule$LoadingException0, null);
                                }
                                return DynamiteModule.zzc(context1, s);
                            }
                        }
                        throw new LoadingException("VersionPolicy returned invalid code:" + v2, null);
                    }
                }
                else if(dynamiteModule$VersionPolicy$SelectionResult0.localVersion != 0) {
                    v2 = -1;
                    goto label_18;
                }
            }
        }
        finally {
            if(v == 0L) {
                DynamiteModule.zzh.remove();
            }
            else {
                DynamiteModule.zzh.set(long0);
            }
            Cursor cursor1 = zzn1.zza;
            if(cursor1 != null) {
                cursor1.close();
            }
            DynamiteModule.zzg.set(zzn0);
        }
        throw new LoadingException("No acceptable module " + s + " found. Local version is " + dynamiteModule$VersionPolicy$SelectionResult0.localVersion + " and remote version is " + dynamiteModule$VersionPolicy$SelectionResult0.remoteVersion + ".", null);
    }

    public static int zza(Context context0, String s, boolean z) {
        int v4;
        Cursor cursor2;
        zzq zzq0;
        int v2;
        Cursor cursor0;
        try {
            synchronized(DynamiteModule.class) {
                cursor0 = null;
                Boolean boolean0 = DynamiteModule.zzb;
                if(boolean0 == null) {
                    try {
                        Field field0 = context0.getApplicationContext().getClassLoader().loadClass("com.google.android.gms.dynamite.DynamiteModule$DynamiteLoaderClassLoader").getDeclaredField("sClassLoader");
                        Class class1 = field0.getDeclaringClass();
                        synchronized(class1) {
                            ClassLoader classLoader0 = (ClassLoader)field0.get(null);
                            if(classLoader0 == ClassLoader.getSystemClassLoader()) {
                                boolean0 = Boolean.FALSE;
                            }
                            else if(classLoader0 == null) {
                                if(!DynamiteModule.zzf(context0)) {
                                    return 0;
                                }
                                if(DynamiteModule.zzd || Boolean.TRUE.equals(null)) {
                                    field0.set(null, ClassLoader.getSystemClassLoader());
                                    boolean0 = Boolean.FALSE;
                                    goto label_52;
                                }
                                try {
                                    v2 = DynamiteModule.zzb(context0, s, z, true);
                                    if(DynamiteModule.zzc != null && !DynamiteModule.zzc.isEmpty()) {
                                        ClassLoader classLoader1 = zzb.zza();
                                        if(classLoader1 == null) {
                                            if(Build.VERSION.SDK_INT >= 29) {
                                                String s1 = DynamiteModule.zzc;
                                                Preconditions.checkNotNull(s1);
                                                classLoader1 = NetworkApi23..ExternalSyntheticApiModelOutline0.m(s1, ClassLoader.getSystemClassLoader());
                                            }
                                            else {
                                                String s2 = DynamiteModule.zzc;
                                                Preconditions.checkNotNull(s2);
                                                classLoader1 = new zzc(s2, ClassLoader.getSystemClassLoader());
                                            }
                                        }
                                        DynamiteModule.zzd(classLoader1);
                                        field0.set(null, classLoader1);
                                        DynamiteModule.zzb = Boolean.TRUE;
                                        return v2;
                                    }
                                    return v2;
                                }
                                catch(LoadingException unused_ex) {
                                    field0.set(null, ClassLoader.getSystemClassLoader());
                                    boolean0 = Boolean.FALSE;
                                    goto label_52;
                                }
                                return v2;
                            }
                            else {
                                try {
                                    DynamiteModule.zzd(classLoader0);
                                }
                                catch(LoadingException unused_ex) {
                                }
                                boolean0 = Boolean.TRUE;
                            }
                        }
                    }
                    catch(ClassNotFoundException | IllegalAccessException | NoSuchFieldException classNotFoundException0) {
                        Log.w("DynamiteModule", "Failed to load module via V2: " + classNotFoundException0.toString());
                        boolean0 = Boolean.FALSE;
                    }
                label_52:
                    DynamiteModule.zzb = boolean0;
                }
            }
            if(!boolean0.booleanValue()) {
                zzq0 = DynamiteModule.zzg(context0);
                if(zzq0 != null) {
                    goto label_64;
                }
                return 0;
            }
            return DynamiteModule.zzb(context0, s, z, false);
        }
        catch(Throwable throwable0) {
            CrashUtils.addDynamiteErrorToDropBox(context0, throwable0);
            throw throwable0;
        }
        try {
        label_64:
            int v3 = zzq0.zze();
            if(v3 < 3) {
                if(v3 == 2) {
                    Log.w("DynamiteModule", "IDynamite loader version = 2, no high precision latency measurement.");
                    return zzq0.zzg(ObjectWrapper.wrap(context0), s, z);
                }
                Log.w("DynamiteModule", "IDynamite loader version < 2, falling back to getModuleVersion2");
                return zzq0.zzf(ObjectWrapper.wrap(context0), s, z);
            }
            zzn zzn0 = (zzn)DynamiteModule.zzg.get();
            if(zzn0 != null) {
                Cursor cursor1 = zzn0.zza;
                if(cursor1 != null) {
                    return cursor1.getInt(0);
                }
            }
            cursor2 = (Cursor)ObjectWrapper.unwrap(zzq0.zzk(ObjectWrapper.wrap(context0), s, z, ((long)(((Long)DynamiteModule.zzh.get())))));
            if(cursor2 != null) {
                goto label_82;
            }
            goto label_89;
        }
        catch(RemoteException remoteException0) {
            RemoteException remoteException1 = remoteException0;
            goto label_94;
            try {
            label_82:
                if(cursor2.moveToFirst()) {
                    v4 = cursor2.getInt(0);
                    if(v4 <= 0 || !DynamiteModule.zze(cursor2)) {
                        goto label_85;
                    }
                    goto label_86;
                }
                goto label_89;
            }
            catch(RemoteException remoteException2) {
                goto label_92;
            }
            catch(Throwable throwable2) {
                goto label_103;
            }
        label_85:
            cursor0 = cursor2;
        label_86:
            if(cursor0 != null) {
                try {
                    cursor0.close();
                }
                catch(Throwable throwable0) {
                    CrashUtils.addDynamiteErrorToDropBox(context0, throwable0);
                    throw throwable0;
                }
            }
            return v4;
            try {
            label_89:
                Log.w("DynamiteModule", "Failed to retrieve remote module version.");
                goto label_108;
            }
            catch(RemoteException remoteException2) {
            }
            catch(Throwable throwable2) {
                goto label_103;
            }
        label_92:
            remoteException1 = remoteException2;
            cursor0 = cursor2;
            try {
            label_94:
                Log.w("DynamiteModule", "Failed to retrieve remote module version: " + remoteException1.getMessage());
                if(cursor0 != null) {
                    goto label_100;
                }
                return 0;
            }
            catch(Throwable throwable1) {
                goto label_98;
            }
        }
        catch(Throwable throwable1) {
            try {
            label_98:
                Throwable throwable3 = throwable1;
                goto label_105;
            label_100:
                cursor0.close();
                return 0;
            label_103:
                throwable3 = throwable2;
                cursor0 = cursor2;
            label_105:
                if(cursor0 != null) {
                    cursor0.close();
                }
                throw throwable3;
            label_108:
                if(cursor2 != null) {
                    cursor2.close();
                    return 0;
                }
                return 0;
            }
            catch(Throwable throwable0) {
            }
        }
        CrashUtils.addDynamiteErrorToDropBox(context0, throwable0);
        throw throwable0;
    }

    private static int zzb(Context context0, String s, boolean z, boolean z1) throws LoadingException {
        int v1;
        Throwable throwable1;
        Exception exception1;
        Cursor cursor1;
        boolean z2;
        Cursor cursor0 = null;
        try {
            ContentResolver contentResolver0 = context0.getContentResolver();
            long v = (long)(((Long)DynamiteModule.zzh.get()));
            String s1 = "api_force_staging";
            z2 = true;
            if(!z) {
                s1 = "api";
            }
            cursor1 = contentResolver0.query(new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").path(s1).appendPath(s).appendQueryParameter("requestStartTime", String.valueOf(v)).build(), null, null, null, null);
        }
        catch(Exception exception0) {
            exception1 = exception0;
            cursor1 = null;
            goto label_48;
        }
        catch(Throwable throwable0) {
            throwable1 = throwable0;
            goto label_54;
        }
        if(cursor1 != null) {
            try {
                if(cursor1.moveToFirst()) {
                    boolean z3 = false;
                    v1 = cursor1.getInt(0);
                    if(v1 > 0) {
                        Class class0 = DynamiteModule.class;
                        synchronized(class0) {
                            DynamiteModule.zzc = cursor1.getString(2);
                            int v3 = cursor1.getColumnIndex("loaderVersion");
                            if(v3 >= 0) {
                                DynamiteModule.zze = cursor1.getInt(v3);
                            }
                            int v4 = cursor1.getColumnIndex("disableStandaloneDynamiteLoader2");
                            if(v4 >= 0) {
                                if(cursor1.getInt(v4) == 0) {
                                    z2 = false;
                                }
                                DynamiteModule.zzd = z2;
                                z3 = z2;
                            }
                        }
                        if(DynamiteModule.zze(cursor1)) {
                            cursor1 = null;
                        }
                    }
                    if(z1 && z3) {
                        throw new LoadingException("forcing fallback to container DynamiteLoader impl", null);
                    }
                    goto label_41;
                }
                goto label_44;
            }
            catch(Exception exception2) {
                goto label_47;
            }
            catch(Throwable throwable2) {
                goto label_52;
            }
        label_41:
            if(cursor1 != null) {
                cursor1.close();
            }
            return v1;
        }
        try {
        label_44:
            Log.w("DynamiteModule", "Failed to retrieve remote module version.");
            throw new LoadingException("Failed to connect to dynamite module ContentResolver.", null);
        }
        catch(Exception exception2) {
        label_47:
            exception1 = exception2;
            try {
            label_48:
                if(!(exception1 instanceof LoadingException)) {
                    throw new LoadingException("V2 version check failed: " + exception1.getMessage(), exception1, null);
                }
                throw exception1;
            }
            catch(Throwable throwable2) {
                goto label_52;
            }
        }
        catch(Throwable throwable2) {
        label_52:
            throwable1 = throwable2;
            cursor0 = cursor1;
        }
    label_54:
        if(cursor0 != null) {
            cursor0.close();
        }
        throw throwable1;
    }

    private static DynamiteModule zzc(Context context0, String s) {
        Log.i("DynamiteModule", "Selected local version of " + s);
        return new DynamiteModule(context0);
    }

    private static void zzd(ClassLoader classLoader0) throws LoadingException {
        zzr zzr0;
        try {
            IBinder iBinder0 = (IBinder)classLoader0.loadClass("com.google.android.gms.dynamiteloader.DynamiteLoaderV2").getConstructor(null).newInstance(null);
            if(iBinder0 == null) {
                zzr0 = null;
            }
            else {
                IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoaderV2");
                zzr0 = iInterface0 instanceof zzr ? ((zzr)iInterface0) : new zzr(iBinder0);
            }
            DynamiteModule.zzl = zzr0;
        }
        catch(ClassNotFoundException | IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException classNotFoundException0) {
            throw new LoadingException("Failed to instantiate dynamite loader", classNotFoundException0, null);
        }
    }

    private static boolean zze(Cursor cursor0) {
        zzn zzn0 = (zzn)DynamiteModule.zzg.get();
        if(zzn0 != null && zzn0.zza == null) {
            zzn0.zza = cursor0;
            return true;
        }
        return false;
    }

    private static boolean zzf(Context context0) {
        boolean z = false;
        if(Boolean.TRUE.equals(null)) {
            return true;
        }
        if(Boolean.TRUE.equals(DynamiteModule.zzf)) {
            return true;
        }
        if(DynamiteModule.zzf == null) {
            ProviderInfo providerInfo0 = context0.getPackageManager().resolveContentProvider("com.google.android.gms.chimera", 0);
            if(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context0, 10000000) == 0 && providerInfo0 != null && "com.google.android.gms".equals(providerInfo0.packageName)) {
                z = true;
            }
            Boolean boolean0 = Boolean.valueOf(z);
            DynamiteModule.zzf = boolean0;
            boolean0.getClass();
            if(z && providerInfo0.applicationInfo != null && (providerInfo0.applicationInfo.flags & 0x81) == 0) {
                Log.i("DynamiteModule", "Non-system-image GmsCore APK, forcing V1");
                DynamiteModule.zzd = true;
            }
        }
        if(!z) {
            Log.e("DynamiteModule", "Invalid GmsCore APK, remote loading disabled.");
        }
        return z;
    }

    private static zzq zzg(Context context0) {
        zzq zzq1;
        synchronized(DynamiteModule.class) {
            zzq zzq0 = DynamiteModule.zzk;
            if(zzq0 != null) {
                return zzq0;
            }
            try {
                IBinder iBinder0 = (IBinder)context0.createPackageContext("com.google.android.gms", 3).getClassLoader().loadClass("com.google.android.gms.chimera.container.DynamiteLoaderImpl").newInstance();
                if(iBinder0 == null) {
                    zzq1 = null;
                }
                else {
                    IInterface iInterface0 = iBinder0.queryLocalInterface("com.google.android.gms.dynamite.IDynamiteLoader");
                    zzq1 = iInterface0 instanceof zzq ? ((zzq)iInterface0) : new zzq(iBinder0);
                }
                if(zzq1 != null) {
                    DynamiteModule.zzk = zzq1;
                    return zzq1;
                }
            }
            catch(Exception exception0) {
                Log.e("DynamiteModule", "Failed to load IDynamiteLoader from GmsCore: " + exception0.getMessage());
            }
            return null;
        }
    }
}

