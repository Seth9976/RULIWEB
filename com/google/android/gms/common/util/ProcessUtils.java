package com.google.android.gms.common.util;

import android.app.Application;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode;
import com.google.android.gms.common.internal.Preconditions;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import javax.annotation.Nullable;

public class ProcessUtils {
    @Nullable
    private static String zza;
    private static int zzb;

    static {
    }

    public static String getMyProcessName() {
        BufferedReader bufferedReader0;
        if(ProcessUtils.zza == null) {
            if(Build.VERSION.SDK_INT >= 28) {
                ProcessUtils.zza = Application.getProcessName();
                return ProcessUtils.zza;
            }
            int v = ProcessUtils.zzb;
            if(v == 0) {
                v = Process.myPid();
                ProcessUtils.zzb = v;
            }
            String s = null;
            if(v > 0) {
                try {
                    StrictMode.ThreadPolicy strictMode$ThreadPolicy0 = StrictMode.allowThreadDiskReads();
                    try {
                        bufferedReader0 = new BufferedReader(new FileReader("/proc/" + v + "/cmdline"));
                    }
                    finally {
                        StrictMode.setThreadPolicy(strictMode$ThreadPolicy0);
                    }
                }
                catch(IOException unused_ex) {
                    bufferedReader0 = null;
                    IOUtils.closeQuietly(bufferedReader0);
                    ProcessUtils.zza = s;
                    return ProcessUtils.zza;
                }
                catch(Throwable throwable0) {
                    IOUtils.closeQuietly(((Closeable)s));
                    throw throwable0;
                }
                try {
                    String s1 = bufferedReader0.readLine();
                    Preconditions.checkNotNull(s1);
                    s = s1.trim();
                }
                catch(IOException throwable0) {
                    s = bufferedReader0;
                    IOUtils.closeQuietly(((Closeable)s));
                    throw throwable0;
                }
                catch(Throwable unused_ex) {
                }
                IOUtils.closeQuietly(bufferedReader0);
            }
            ProcessUtils.zza = s;
        }
        return ProcessUtils.zza;
    }
}

