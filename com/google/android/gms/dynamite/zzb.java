package com.google.android.gms.dynamite;

import android.os.Looper;
import android.util.Log;

public final class zzb {
    private static ClassLoader zza;
    private static Thread zzb;

    static {
    }

    public static ClassLoader zza() {
        zza zza0;
        SecurityException securityException1;
        Thread thread2;
        Thread thread1;
        ThreadGroup threadGroup1;
        synchronized(zzb.class) {
            if(zzb.zza == null) {
                Thread thread0 = zzb.zzb;
                ClassLoader classLoader0 = null;
                if(thread0 == null) {
                    ThreadGroup threadGroup0 = Looper.getMainLooper().getThread().getThreadGroup();
                    if(threadGroup0 == null) {
                        thread0 = null;
                    }
                    else {
                        Class class1 = Void.class;
                        synchronized(class1) {
                            try {
                                threadGroup1 = null;
                                int v2 = threadGroup0.activeGroupCount();
                                ThreadGroup[] arr_threadGroup = new ThreadGroup[v2];
                                threadGroup0.enumerate(arr_threadGroup);
                                int v3 = 0;
                                for(int v4 = 0; v4 < v2; ++v4) {
                                    ThreadGroup threadGroup2 = arr_threadGroup[v4];
                                    if("dynamiteLoader".equals(threadGroup2.getName())) {
                                        threadGroup1 = threadGroup2;
                                        break;
                                    }
                                }
                                if(threadGroup1 == null) {
                                    threadGroup1 = new ThreadGroup(threadGroup0, "dynamiteLoader");
                                }
                                thread1 = null;
                                int v5 = threadGroup1.activeCount();
                                Thread[] arr_thread = new Thread[v5];
                                threadGroup1.enumerate(arr_thread);
                                while(true) {
                                    if(v3 >= v5) {
                                        goto label_41;
                                    }
                                    thread2 = arr_thread[v3];
                                    if("GmsDynamite".equals(thread2.getName())) {
                                        break;
                                    }
                                    ++v3;
                                }
                            }
                            catch(SecurityException securityException0) {
                                securityException1 = securityException0;
                                goto label_53;
                            }
                        }
                        thread1 = thread2;
                    label_41:
                        if(thread1 == null) {
                            try {
                                zza0 = new zza(threadGroup1, "GmsDynamite");
                            }
                            catch(SecurityException securityException2) {
                                securityException1 = securityException2;
                                goto label_53;
                            }
                            try {
                                zza0.setContextClassLoader(null);
                                thread1 = zza0;
                                zza0.start();
                                thread1 = zza0;
                                goto label_54;
                            }
                            catch(SecurityException securityException1) {
                            }
                        label_53:
                            Log.w("DynamiteLoaderV2CL", "Failed to enumerate thread/threadgroup " + securityException1.getMessage());
                        }
                    label_54:
                        thread0 = thread1;
                    }
                    zzb.zzb = thread0;
                    if(thread0 != null) {
                        __monitor_enter(thread0);
                        classLoader0 = zzb.zzb.getContextClassLoader();
                        zzb.zza = classLoader0;
                        return zzb.zza;
                    }
                }
                else {
                    synchronized(thread0) {
                        try {
                            classLoader0 = zzb.zzb.getContextClassLoader();
                        }
                        catch(SecurityException securityException3) {
                            Log.w("DynamiteLoaderV2CL", "Failed to get thread context classloader " + securityException3.getMessage());
                        }
                    }
                }
                zzb.zza = classLoader0;
            }
            return zzb.zza;
        }
    }
}

