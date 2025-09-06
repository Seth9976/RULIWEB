package kotlin;

import android.content.ContentProviderClient;
import android.content.res.TypedArray;
import android.drm.DrmManagerClient;
import android.media.MediaDrm;
import android.media.MediaMetadataRetriever;
import android.os.Build.VERSION;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public final class UByte..ExternalSyntheticBackport0 {
    public static int m(double f) {
        long v = Double.doubleToLongBits(f);
        return (int)(v ^ v >>> 0x20);
    }

    public static int m(int v, int v1) [...] // Inlined contents

    public static int m(long v) [...] // Inlined contents

    public static int m(long v, long v1) {
        return Long.compare(v ^ 0x8000000000000000L, v1 ^ 0x8000000000000000L);
    }

    // 去混淆评级： 低(20)
    public static int m(boolean z) {
        return z ? 0x4CF : 0x4D5;
    }

    public static long m(long v, long v1) {
        if(v1 < 0L) {
            return (v ^ 0x8000000000000000L) >= (0x8000000000000000L ^ v1) ? v - v1 : v;
        }
        if(v >= 0L) {
            return v % v1;
        }
        long v2 = v - ((v >>> 1) / v1 << 1) * v1;
        if((v2 ^ 0x8000000000000000L) < (0x8000000000000000L ^ v1)) {
            v1 = 0L;
        }
        return v2 - v1;
    }

    public static List m(Object object0) {
        return UByte..ExternalSyntheticBackport0.m(new Object[]{object0});
    }

    public static List m(Object object0, Object object1) {
        return UByte..ExternalSyntheticBackport0.m(new Object[]{object0, object1});
    }

    public static List m(Object[] arr_object) {
        ArrayList arrayList0 = new ArrayList(arr_object.length);
        for(int v = 0; v < arr_object.length; ++v) {
            arrayList0.add(Objects.requireNonNull(arr_object[v]));
        }
        return Collections.unmodifiableList(arrayList0);
    }

    public static void m(Object object0) {
        if(object0 instanceof AutoCloseable) {
            ((AutoCloseable)object0).close();
            return;
        }
        if(object0 instanceof ExecutorService) {
            UByte..ExternalSyntheticBackport0.m(((ExecutorService)object0));
            return;
        }
        if(object0 instanceof TypedArray) {
            ((TypedArray)object0).recycle();
            return;
        }
        if(object0 instanceof MediaMetadataRetriever) {
            ((MediaMetadataRetriever)object0).release();
            return;
        }
        if(object0 instanceof MediaDrm) {
            ((MediaDrm)object0).release();
            return;
        }
        if(object0 instanceof DrmManagerClient) {
            ((DrmManagerClient)object0).release();
            return;
        }
        if(object0 instanceof ContentProviderClient) {
            ((ContentProviderClient)object0).release();
            return;
        }
        UByte..ExternalSyntheticBackport0.m$1(object0);
    }

    public static void m(ExecutorService executorService0) {
        if(Build.VERSION.SDK_INT <= 23 || executorService0 != ForkJoinPool.commonPool()) {
            boolean z = executorService0.isTerminated();
            if(!z) {
                executorService0.shutdown();
                boolean z1 = false;
                while(!z) {
                    try {
                        z = executorService0.awaitTermination(1L, TimeUnit.DAYS);
                    }
                    catch(InterruptedException unused_ex) {
                        if(z1) {
                            continue;
                        }
                        executorService0.shutdownNow();
                        z1 = true;
                    }
                }
                if(z1) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static boolean m(Object object0) {
        return object0 == null;
    }

    public static int m$1(int v, int v1) [...] // Inlined contents

    public static long m$1(long v, long v1) {
        if(v1 < 0L) {
            return (v ^ 0x8000000000000000L) >= (v1 ^ 0x8000000000000000L) ? 1L : 0L;
        }
        if(v >= 0L) {
            return v / v1;
        }
        int v2 = 1;
        long v3 = (v >>> 1) / v1 << 1;
        if((v - v3 * v1 ^ 0x8000000000000000L) < (v1 ^ 0x8000000000000000L)) {
            v2 = 0;
        }
        return v3 + ((long)v2);
    }

    public static void m$1(Object object0) {
        throw new IllegalArgumentException();
    }

    public static int m$2(int v, int v1) {
        return Integer.compare(v ^ 0x80000000, v1 ^ 0x80000000);
    }
}

