package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Process;
import android.os.WorkSource;
import android.util.Log;
import androidx.core.content.ContextCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.wrappers.Wrappers;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class WorkSourceUtil {
    private static final int zza;
    private static final Method zzb;
    private static final Method zzc;
    private static final Method zzd;
    private static final Method zze;
    private static final Method zzf;
    private static final Method zzg;
    private static final Method zzh;
    private static final Method zzi;
    private static Boolean zzj;

    static {
        Method method7;
        Method method6;
        Method method5;
        Method method4;
        Method method3;
        Method method2;
        Method method1;
        Method method0;
        WorkSourceUtil.zza = Process.myUid();
        try {
            method0 = null;
            method0 = WorkSource.class.getMethod("add", Integer.TYPE);
        }
        catch(Exception unused_ex) {
        }
        try {
            WorkSourceUtil.zzb = method0;
            method1 = null;
            method1 = WorkSource.class.getMethod("add", Integer.TYPE, String.class);
        }
        catch(Exception unused_ex) {
        }
        try {
            WorkSourceUtil.zzc = method1;
            method2 = null;
            method2 = WorkSource.class.getMethod("size", null);
        }
        catch(Exception unused_ex) {
        }
        try {
            WorkSourceUtil.zzd = method2;
            method3 = null;
            method3 = WorkSource.class.getMethod("get", Integer.TYPE);
        }
        catch(Exception unused_ex) {
        }
        try {
            WorkSourceUtil.zze = method3;
            method4 = null;
            method4 = WorkSource.class.getMethod("getName", Integer.TYPE);
        }
        catch(Exception unused_ex) {
        }
        try {
            WorkSourceUtil.zzf = method4;
            method5 = WorkSource.class.getMethod("createWorkChain", null);
        }
        catch(Exception exception0) {
            Log.w("WorkSourceUtil", "Missing WorkChain API createWorkChain", exception0);
            method5 = null;
        }
        try {
            WorkSourceUtil.zzg = method5;
            method6 = Class.forName("android.os.WorkSource$WorkChain").getMethod("addNode", Integer.TYPE, String.class);
        }
        catch(Exception exception1) {
            Log.w("WorkSourceUtil", "Missing WorkChain class", exception1);
            method6 = null;
        }
        try {
            WorkSourceUtil.zzh = method6;
            method7 = null;
            method7 = WorkSource.class.getMethod("isEmpty", null);
            method7.setAccessible(true);
        }
        catch(Exception unused_ex) {
        }
        WorkSourceUtil.zzi = method7;
        WorkSourceUtil.zzj = null;
    }

    public static void add(WorkSource workSource0, int v, String s) {
        Method method0 = WorkSourceUtil.zzc;
        if(method0 != null) {
            if(s == null) {
                s = "";
            }
            try {
                method0.invoke(workSource0, v, s);
            }
            catch(Exception exception0) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception0);
            }
            return;
        }
        Method method1 = WorkSourceUtil.zzb;
        if(method1 != null) {
            try {
                method1.invoke(workSource0, v);
            }
            catch(Exception exception1) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception1);
            }
        }
    }

    public static WorkSource fromPackage(Context context0, String s) {
        ApplicationInfo applicationInfo0;
        if(context0 != null && context0.getPackageManager() != null && s != null) {
            try {
                applicationInfo0 = Wrappers.packageManager(context0).getApplicationInfo(s, 0);
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                Log.e("WorkSourceUtil", "Could not find package: " + s);
                return null;
            }
            if(applicationInfo0 == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: " + s);
                return null;
            }
            int v = applicationInfo0.uid;
            WorkSource workSource0 = new WorkSource();
            WorkSourceUtil.add(workSource0, v, s);
            return workSource0;
        }
        return null;
    }

    public static WorkSource fromPackageAndModuleExperimentalPi(Context context0, String s, String s1) {
        ApplicationInfo applicationInfo0;
        int v;
        if(context0 != null && context0.getPackageManager() != null && s1 != null && s != null) {
            try {
                v = -1;
                applicationInfo0 = Wrappers.packageManager(context0).getApplicationInfo(s, 0);
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
                Log.e("WorkSourceUtil", "Could not find package: " + s);
                goto label_10;
            }
            if(applicationInfo0 == null) {
                Log.e("WorkSourceUtil", "Could not get applicationInfo from package: " + s);
            }
            else {
                v = applicationInfo0.uid;
            }
        label_10:
            if(v < 0) {
                return null;
            }
            WorkSource workSource0 = new WorkSource();
            Method method0 = WorkSourceUtil.zzg;
            if(method0 != null) {
                Method method1 = WorkSourceUtil.zzh;
                if(method1 != null) {
                    try {
                        Object object0 = method0.invoke(workSource0, null);
                        int v1 = WorkSourceUtil.zza;
                        if(v != v1) {
                            method1.invoke(object0, v, s);
                        }
                        method1.invoke(object0, v1, s1);
                    }
                    catch(Exception exception0) {
                        Log.w("WorkSourceUtil", "Unable to assign chained blame through WorkSource", exception0);
                    }
                    return workSource0;
                }
            }
            WorkSourceUtil.add(workSource0, v, s);
            return workSource0;
        }
        Log.w("WorkSourceUtil", "Unexpected null arguments");
        return null;
    }

    public static int get(WorkSource workSource0, int v) {
        Method method0 = WorkSourceUtil.zze;
        if(method0 != null) {
            try {
                Object object0 = method0.invoke(workSource0, v);
                Preconditions.checkNotNull(object0);
                return (int)(((Integer)object0));
            }
            catch(Exception exception0) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception0);
            }
        }
        return 0;
    }

    public static String getName(WorkSource workSource0, int v) {
        Method method0 = WorkSourceUtil.zzf;
        if(method0 != null) {
            try {
                return (String)method0.invoke(workSource0, v);
            }
            catch(Exception exception0) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception0);
            }
        }
        return null;
    }

    public static List getNames(WorkSource workSource0) {
        List list0 = new ArrayList();
        int v1 = workSource0 == null ? 0 : WorkSourceUtil.size(workSource0);
        if(v1 != 0) {
            for(int v = 0; v < v1; ++v) {
                String s = WorkSourceUtil.getName(workSource0, v);
                if(!Strings.isEmptyOrWhitespace(s)) {
                    Preconditions.checkNotNull(s);
                    list0.add(s);
                }
            }
        }
        return list0;
    }

    public static boolean hasWorkSourcePermission(Context context0) {
        synchronized(WorkSourceUtil.class) {
            boolean z = false;
            Boolean boolean0 = WorkSourceUtil.zzj;
            if(boolean0 != null) {
                return boolean0.booleanValue();
            }
            if(context0 == null) {
                return false;
            }
            if(ContextCompat.checkSelfPermission(context0, "android.permission.UPDATE_DEVICE_STATS") == 0) {
                z = true;
            }
            Boolean boolean1 = Boolean.valueOf(z);
            WorkSourceUtil.zzj = boolean1;
            boolean1.getClass();
            return z;
        }
    }

    public static boolean isEmpty(WorkSource workSource0) {
        Method method0 = WorkSourceUtil.zzi;
        if(method0 != null) {
            try {
                Object object0 = method0.invoke(workSource0, null);
                Preconditions.checkNotNull(object0);
                return ((Boolean)object0).booleanValue();
            }
            catch(Exception exception0) {
                Log.e("WorkSourceUtil", "Unable to check WorkSource emptiness", exception0);
            }
        }
        return WorkSourceUtil.size(workSource0) == 0;
    }

    public static int size(WorkSource workSource0) {
        Method method0 = WorkSourceUtil.zzd;
        if(method0 != null) {
            try {
                Object object0 = method0.invoke(workSource0, null);
                Preconditions.checkNotNull(object0);
                return (int)(((Integer)object0));
            }
            catch(Exception exception0) {
                Log.wtf("WorkSourceUtil", "Unable to assign blame through WorkSource", exception0);
            }
        }
        return 0;
    }
}

