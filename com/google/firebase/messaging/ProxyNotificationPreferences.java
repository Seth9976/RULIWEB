package com.google.firebase.messaging;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;

final class ProxyNotificationPreferences {
    private static final String FCM_PREFERENCES = "com.google.firebase.messaging";

    private static SharedPreferences getPreference(Context context0) {
        Context context1 = context0.getApplicationContext();
        if(context1 != null) {
            context0 = context1;
        }
        return context0.getSharedPreferences("com.google.firebase.messaging", 0);
    }

    static boolean isProxyNotificationInitialized(Context context0) {
        return ProxyNotificationPreferences.getPreference(context0).getBoolean("proxy_notification_initialized", false);
    }

    static void setProxyNotificationsInitialized(Context context0, boolean z) {
        SharedPreferences.Editor sharedPreferences$Editor0 = ProxyNotificationPreferences.getPreference(context0).edit();
        sharedPreferences$Editor0.putBoolean("proxy_notification_initialized", z);
        sharedPreferences$Editor0.apply();
    }
}

