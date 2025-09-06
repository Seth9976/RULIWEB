package com.google.firebase.messaging;

import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.util.Log;
import androidx.profileinstaller.ProfileInstallReceiver..ExternalSyntheticLambda0;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.Executor;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

final class ProxyNotificationInitializer {
    private static final String MANIFEST_METADATA_NOTIFICATION_DELEGATION_ENABLED = "firebase_messaging_notification_delegation_enabled";

    private static boolean allowedToUse(Context context0) {
        return Binder.getCallingUid() == context0.getApplicationInfo().uid;
    }

    static void initialize(Context context0) {
        if(ProxyNotificationPreferences.isProxyNotificationInitialized(context0)) {
            return;
        }
        ProxyNotificationInitializer.setEnableProxyNotification(new ProfileInstallReceiver..ExternalSyntheticLambda0(), context0, ProxyNotificationInitializer.shouldEnableProxyNotification(context0));
    }

    static boolean isProxyNotificationEnabled(Context context0) {
        if(!ProxyNotificationInitializer.allowedToUse(context0)) {
            Log.e("FirebaseMessaging", "error retrieving notification delegate for package com.ruliweb.www.ruliapp");
            return false;
        }
        if("com.google.android.gms".equals(LinkFollowing..ExternalSyntheticApiModelOutline0.m(((NotificationManager)context0.getSystemService(NotificationManager.class))))) {
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "GMS core is set for proxying");
            }
            return true;
        }
        return false;
    }

    // 检测为 Lambda 实现
    static void lambda$setEnableProxyNotification$0(Context context0, boolean z, TaskCompletionSource taskCompletionSource0) [...]

    static Task setEnableProxyNotification(Executor executor0, Context context0, boolean z) {
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
        executor0.execute(() -> try {
            if(!ProxyNotificationInitializer.allowedToUse(context0)) {
                Log.e("FirebaseMessaging", "error configuring notification delegate for package com.ruliweb.www.ruliapp");
                return;
            }
            ProxyNotificationPreferences.setProxyNotificationsInitialized(context0, true);
            NotificationManager notificationManager0 = (NotificationManager)context0.getSystemService(NotificationManager.class);
            if(z) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m(notificationManager0, "com.google.android.gms");
            }
            else if("com.google.android.gms".equals(LinkFollowing..ExternalSyntheticApiModelOutline0.m(notificationManager0))) {
                LinkFollowing..ExternalSyntheticApiModelOutline0.m(notificationManager0, null);
            }
        }
        finally {
            taskCompletionSource0.trySetResult(null);
        });
        return taskCompletionSource0.getTask();
    }

    private static boolean shouldEnableProxyNotification(Context context0) {
        try {
            PackageManager packageManager0 = context0.getApplicationContext().getPackageManager();
            if(packageManager0 != null) {
                ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo("com.ruliweb.www.ruliapp", 0x80);
                return applicationInfo0 == null || applicationInfo0.metaData == null || !applicationInfo0.metaData.containsKey("firebase_messaging_notification_delegation_enabled") ? true : applicationInfo0.metaData.getBoolean("firebase_messaging_notification_delegation_enabled");
            }
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
        }
        return true;
    }
}

