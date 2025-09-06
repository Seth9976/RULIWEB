package androidx.core.app;

import android.app.PendingIntent.CanceledException;
import android.app.PendingIntent.OnFinished;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import java.io.Closeable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.concurrent.CountDownLatch;

public final class PendingIntentCompat {
    static class Api23Impl {
        public static void send(PendingIntent pendingIntent0, Context context0, int v, Intent intent0, PendingIntent.OnFinished pendingIntent$OnFinished0, Handler handler0, String s, Bundle bundle0) throws PendingIntent.CanceledException {
            pendingIntent0.send(context0, v, intent0, pendingIntent$OnFinished0, handler0, s, bundle0);
        }
    }

    static class Api26Impl {
        public static PendingIntent getForegroundService(Context context0, int v, Intent intent0, int v1) {
            return PendingIntent.getForegroundService(context0, v, intent0, v1);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    static class GatedCallback implements Closeable, AutoCloseable {
        private PendingIntent.OnFinished mCallback;
        private final CountDownLatch mComplete;
        private boolean mSuccess;

        GatedCallback(PendingIntent.OnFinished pendingIntent$OnFinished0) {
            this.mComplete = new CountDownLatch(1);
            this.mCallback = pendingIntent$OnFinished0;
            this.mSuccess = false;
        }

        @Override
        public void close() {
            if(!this.mSuccess) {
                this.mCallback = null;
            }
            this.mComplete.countDown();
        }

        public void complete() {
            this.mSuccess = true;
        }

        public PendingIntent.OnFinished getCallback() {
            return this.mCallback == null ? null : (PendingIntent pendingIntent0, Intent intent0, int v, String s, Bundle bundle0) -> {
                boolean z = false;
                while(true) {
                    try {
                        this.mComplete.await();
                        break;
                    }
                    catch(InterruptedException unused_ex) {
                        z = true;
                    }
                    catch(Throwable throwable0) {
                        if(z) {
                            Thread.currentThread().interrupt();
                        }
                        throw throwable0;
                    }
                }
                if(z) {
                    Thread.currentThread().interrupt();
                }
                PendingIntent.OnFinished pendingIntent$OnFinished0 = this.mCallback;
                if(pendingIntent$OnFinished0 != null) {
                    pendingIntent$OnFinished0.onSendFinished(pendingIntent0, intent0, v, s, bundle0);
                    this.mCallback = null;
                }
            };
        }

        // 检测为 Lambda 实现
        private void onSendFinished(PendingIntent pendingIntent0, Intent intent0, int v, String s, Bundle bundle0) [...]
    }

    static int addMutabilityFlags(boolean z, int v) {
        if(z) {
            return Build.VERSION.SDK_INT < 0x1F ? v : 0x2000000 | v;
        }
        return Build.VERSION.SDK_INT < 23 ? v : 0x4000000 | v;
    }

    public static PendingIntent getActivities(Context context0, int v, Intent[] arr_intent, int v1, Bundle bundle0, boolean z) {
        return PendingIntent.getActivities(context0, v, arr_intent, PendingIntentCompat.addMutabilityFlags(z, v1), bundle0);
    }

    public static PendingIntent getActivities(Context context0, int v, Intent[] arr_intent, int v1, boolean z) {
        return PendingIntent.getActivities(context0, v, arr_intent, PendingIntentCompat.addMutabilityFlags(z, v1));
    }

    public static PendingIntent getActivity(Context context0, int v, Intent intent0, int v1, Bundle bundle0, boolean z) {
        return PendingIntent.getActivity(context0, v, intent0, PendingIntentCompat.addMutabilityFlags(z, v1), bundle0);
    }

    public static PendingIntent getActivity(Context context0, int v, Intent intent0, int v1, boolean z) {
        return PendingIntent.getActivity(context0, v, intent0, PendingIntentCompat.addMutabilityFlags(z, v1));
    }

    public static PendingIntent getBroadcast(Context context0, int v, Intent intent0, int v1, boolean z) {
        return PendingIntent.getBroadcast(context0, v, intent0, PendingIntentCompat.addMutabilityFlags(z, v1));
    }

    public static PendingIntent getForegroundService(Context context0, int v, Intent intent0, int v1, boolean z) {
        return Api26Impl.getForegroundService(context0, v, intent0, PendingIntentCompat.addMutabilityFlags(z, v1));
    }

    public static PendingIntent getService(Context context0, int v, Intent intent0, int v1, boolean z) {
        return PendingIntent.getService(context0, v, intent0, PendingIntentCompat.addMutabilityFlags(z, v1));
    }

    public static void send(PendingIntent pendingIntent0, int v, PendingIntent.OnFinished pendingIntent$OnFinished0, Handler handler0) throws PendingIntent.CanceledException {
        try(GatedCallback pendingIntentCompat$GatedCallback0 = new GatedCallback(pendingIntent$OnFinished0)) {
            pendingIntent0.send(v, pendingIntentCompat$GatedCallback0.getCallback(), handler0);
            pendingIntentCompat$GatedCallback0.complete();
        }
    }

    public static void send(PendingIntent pendingIntent0, Context context0, int v, Intent intent0, PendingIntent.OnFinished pendingIntent$OnFinished0, Handler handler0) throws PendingIntent.CanceledException {
        PendingIntentCompat.send(pendingIntent0, context0, v, intent0, pendingIntent$OnFinished0, handler0, null, null);
    }

    public static void send(PendingIntent pendingIntent0, Context context0, int v, Intent intent0, PendingIntent.OnFinished pendingIntent$OnFinished0, Handler handler0, String s, Bundle bundle0) throws PendingIntent.CanceledException {
        try(GatedCallback pendingIntentCompat$GatedCallback0 = new GatedCallback(pendingIntent$OnFinished0)) {
            if(Build.VERSION.SDK_INT >= 23) {
                Api23Impl.send(pendingIntent0, context0, v, intent0, pendingIntent$OnFinished0, handler0, s, bundle0);
            }
            else {
                pendingIntent0.send(context0, v, intent0, pendingIntentCompat$GatedCallback0.getCallback(), handler0, s);
            }
            pendingIntentCompat$GatedCallback0.complete();
        }
    }
}

