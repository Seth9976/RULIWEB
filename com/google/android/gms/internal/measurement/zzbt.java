package com.google.android.gms.internal.measurement;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.UserHandle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class zzbt {
    private static final Method zza;
    private static final Method zzb;

    static {
        Method method1;
        Method method0 = null;
        if(Build.VERSION.SDK_INT >= 24) {
            try {
                method1 = JobScheduler.class.getDeclaredMethod("scheduleAsPackage", JobInfo.class, String.class, Integer.TYPE, String.class);
            }
            catch(NoSuchMethodException unused_ex) {
                if(Log.isLoggable("JobSchedulerCompat", 6)) {
                    Log.e("JobSchedulerCompat", "No scheduleAsPackage method available, falling back to schedule");
                }
                method1 = null;
            }
        }
        else {
            method1 = null;
        }
        zzbt.zza = method1;
        if(Build.VERSION.SDK_INT >= 24) {
            try {
                method0 = UserHandle.class.getDeclaredMethod("myUserId", null);
            }
            catch(NoSuchMethodException unused_ex) {
                if(Log.isLoggable("JobSchedulerCompat", 6)) {
                    Log.e("JobSchedulerCompat", "No myUserId method available");
                }
            }
        }
        zzbt.zzb = method0;
    }

    public static int zza(Context context0, JobInfo jobInfo0, String s, String s1) {
        int v;
        JobScheduler jobScheduler0 = (JobScheduler)context0.getSystemService("jobscheduler");
        jobScheduler0.getClass();
        if(zzbt.zza != null && context0.checkSelfPermission("android.permission.UPDATE_DEVICE_STATS") == 0) {
            Method method0 = zzbt.zzb;
            if(method0 == null) {
                v = 0;
            }
            else {
                try {
                    Integer integer0 = (Integer)method0.invoke(UserHandle.class, null);
                    v = integer0 == null ? 0 : ((int)integer0);
                }
                catch(IllegalAccessException | InvocationTargetException illegalAccessException0) {
                    if(Log.isLoggable("JobSchedulerCompat", 6)) {
                        Log.e("JobSchedulerCompat", "myUserId invocation illegal", illegalAccessException0);
                    }
                    v = 0;
                }
            }
            Method method1 = zzbt.zza;
            if(method1 != null) {
                try {
                    Integer integer1 = (Integer)method1.invoke(jobScheduler0, jobInfo0, "com.google.android.gms", v, "UploadAlarm");
                    return integer1 == null ? 0 : ((int)integer1);
                }
                catch(IllegalAccessException | InvocationTargetException illegalAccessException1) {
                    Log.e("UploadAlarm", "error calling scheduleAsPackage", illegalAccessException1);
                }
            }
            return jobScheduler0.schedule(jobInfo0);
        }
        return jobScheduler0.schedule(jobInfo0);
    }
}

