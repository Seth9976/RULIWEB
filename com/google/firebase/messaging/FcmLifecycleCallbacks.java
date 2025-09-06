package com.google.firebase.messaging;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

class FcmLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {
    private final Set seenIntents;

    FcmLifecycleCallbacks() {
        this.seenIntents = Collections.newSetFromMap(new WeakHashMap());
    }

    // 检测为 Lambda 实现
    void lambda$onActivityCreated$0$com-google-firebase-messaging-FcmLifecycleCallbacks(Intent intent0) [...]

    private void logNotificationOpen(Intent intent0) {
        Bundle bundle0 = null;
        try {
            Bundle bundle1 = intent0.getExtras();
            if(bundle1 != null) {
                bundle0 = bundle1.getBundle("gcm.n.analytics_data");
            }
        }
        catch(RuntimeException runtimeException0) {
            Log.w("FirebaseMessaging", "Failed trying to get analytics data from Intent extras.", runtimeException0);
        }
        if(MessagingAnalytics.shouldUploadScionMetrics(bundle0)) {
            MessagingAnalytics.logNotificationOpen(bundle0);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity0, Bundle bundle0) {
        Intent intent0 = activity0.getIntent();
        if(intent0 != null && this.seenIntents.add(intent0)) {
            if(Build.VERSION.SDK_INT <= 25) {
                new Handler(Looper.getMainLooper()).post(() -> this.logNotificationOpen(intent0));
                return;
            }
            this.logNotificationOpen(intent0);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity0) {
        if(activity0.isFinishing()) {
            Intent intent0 = activity0.getIntent();
            this.seenIntents.remove(intent0);
        }
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity0, Bundle bundle0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity0) {
    }

    @Override  // android.app.Application$ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity0) {
    }
}

