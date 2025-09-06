package com.google.firebase.messaging;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import androidx.activity.ComponentDialog..ExternalSyntheticApiModelOutline0;
import androidx.core.app.NotificationCompat.BigTextStyle;
import androidx.core.app.NotificationCompat.Builder;
import androidx.core.content.ContextCompat;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.io.path.LinkFollowing..ExternalSyntheticApiModelOutline0;

public final class CommonNotificationBuilder {
    public static class DisplayNotificationInfo {
        public final int id;
        public final Builder notificationBuilder;
        public final String tag;

        DisplayNotificationInfo(Builder notificationCompat$Builder0, String s, int v) {
            this.notificationBuilder = notificationCompat$Builder0;
            this.tag = s;
            this.id = v;
        }
    }

    private static final String ACTION_MESSAGING_EVENT = "com.google.firebase.MESSAGING_EVENT";
    public static final String FCM_FALLBACK_NOTIFICATION_CHANNEL = "fcm_fallback_notification_channel";
    public static final String FCM_FALLBACK_NOTIFICATION_CHANNEL_LABEL = "fcm_fallback_notification_channel_label";
    private static final String FCM_FALLBACK_NOTIFICATION_CHANNEL_NAME_NO_RESOURCE = "Misc";
    private static final int ILLEGAL_RESOURCE_ID = 0;
    public static final String METADATA_DEFAULT_CHANNEL_ID = "com.google.firebase.messaging.default_notification_channel_id";
    public static final String METADATA_DEFAULT_COLOR = "com.google.firebase.messaging.default_notification_color";
    public static final String METADATA_DEFAULT_ICON = "com.google.firebase.messaging.default_notification_icon";
    private static final AtomicInteger requestCodeProvider;

    static {
        CommonNotificationBuilder.requestCodeProvider = new AtomicInteger(((int)SystemClock.elapsedRealtime()));
    }

    private static PendingIntent createContentIntent(Context context0, NotificationParams notificationParams0, String s, PackageManager packageManager0) {
        Intent intent0 = CommonNotificationBuilder.createTargetIntent(s, notificationParams0, packageManager0);
        if(intent0 == null) {
            return null;
        }
        intent0.addFlags(0x4000000);
        intent0.putExtras(notificationParams0.paramsWithReservedKeysRemoved());
        if(CommonNotificationBuilder.shouldUploadMetrics(notificationParams0)) {
            intent0.putExtra("gcm.n.analytics_data", notificationParams0.paramsForAnalyticsIntent());
        }
        return PendingIntent.getActivity(context0, CommonNotificationBuilder.generatePendingIntentRequestCode(), intent0, 0x44000000);
    }

    private static PendingIntent createDeleteIntent(Context context0, Context context1, NotificationParams notificationParams0) {
        return CommonNotificationBuilder.shouldUploadMetrics(notificationParams0) ? CommonNotificationBuilder.createMessagingPendingIntent(context0, context1, new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS").putExtras(notificationParams0.paramsForAnalyticsIntent())) : null;
    }

    // 去混淆评级： 低(20)
    private static PendingIntent createMessagingPendingIntent(Context context0, Context context1, Intent intent0) {
        return PendingIntent.getBroadcast(context0, CommonNotificationBuilder.generatePendingIntentRequestCode(), new Intent("com.google.firebase.MESSAGING_EVENT").setComponent(new ComponentName(context1, "com.google.firebase.iid.FirebaseInstanceIdReceiver")).putExtra("wrapped_intent", intent0), 0x44000000);
    }

    public static DisplayNotificationInfo createNotificationInfo(Context context0, Context context1, NotificationParams notificationParams0, String s, Bundle bundle0) {
        Resources resources0 = context1.getResources();
        PackageManager packageManager0 = context1.getPackageManager();
        Builder notificationCompat$Builder0 = new Builder(context1, s);
        String s1 = notificationParams0.getPossiblyLocalizedString(resources0, "com.ruliweb.www.ruliapp", "gcm.n.title");
        if(!TextUtils.isEmpty(s1)) {
            notificationCompat$Builder0.setContentTitle(s1);
        }
        String s2 = notificationParams0.getPossiblyLocalizedString(resources0, "com.ruliweb.www.ruliapp", "gcm.n.body");
        if(!TextUtils.isEmpty(s2)) {
            notificationCompat$Builder0.setContentText(s2);
            notificationCompat$Builder0.setStyle(new BigTextStyle().bigText(s2));
        }
        notificationCompat$Builder0.setSmallIcon(CommonNotificationBuilder.getSmallIcon(packageManager0, resources0, "com.ruliweb.www.ruliapp", notificationParams0.getString("gcm.n.icon"), bundle0));
        Uri uri0 = CommonNotificationBuilder.getSound("com.ruliweb.www.ruliapp", notificationParams0, resources0);
        if(uri0 != null) {
            notificationCompat$Builder0.setSound(uri0);
        }
        notificationCompat$Builder0.setContentIntent(CommonNotificationBuilder.createContentIntent(context0, notificationParams0, "com.ruliweb.www.ruliapp", packageManager0));
        PendingIntent pendingIntent0 = CommonNotificationBuilder.createDeleteIntent(context0, context1, notificationParams0);
        if(pendingIntent0 != null) {
            notificationCompat$Builder0.setDeleteIntent(pendingIntent0);
        }
        Integer integer0 = CommonNotificationBuilder.getColor(context1, notificationParams0.getString("gcm.n.color"), bundle0);
        if(integer0 != null) {
            notificationCompat$Builder0.setColor(((int)integer0));
        }
        notificationCompat$Builder0.setAutoCancel(!notificationParams0.getBoolean("gcm.n.sticky"));
        notificationCompat$Builder0.setLocalOnly(notificationParams0.getBoolean("gcm.n.local_only"));
        String s3 = notificationParams0.getString("gcm.n.ticker");
        if(s3 != null) {
            notificationCompat$Builder0.setTicker(s3);
        }
        Integer integer1 = notificationParams0.getNotificationPriority();
        if(integer1 != null) {
            notificationCompat$Builder0.setPriority(((int)integer1));
        }
        Integer integer2 = notificationParams0.getVisibility();
        if(integer2 != null) {
            notificationCompat$Builder0.setVisibility(((int)integer2));
        }
        Integer integer3 = notificationParams0.getNotificationCount();
        if(integer3 != null) {
            notificationCompat$Builder0.setNumber(((int)integer3));
        }
        Long long0 = notificationParams0.getLong("gcm.n.event_time");
        if(long0 != null) {
            notificationCompat$Builder0.setShowWhen(true);
            notificationCompat$Builder0.setWhen(((long)long0));
        }
        long[] arr_v = notificationParams0.getVibrateTimings();
        if(arr_v != null) {
            notificationCompat$Builder0.setVibrate(arr_v);
        }
        int[] arr_v1 = notificationParams0.getLightSettings();
        if(arr_v1 != null) {
            notificationCompat$Builder0.setLights(arr_v1[0], arr_v1[1], arr_v1[2]);
        }
        notificationCompat$Builder0.setDefaults(CommonNotificationBuilder.getConsolidatedDefaults(notificationParams0));
        return new DisplayNotificationInfo(notificationCompat$Builder0, CommonNotificationBuilder.getTag(notificationParams0), 0);
    }

    static DisplayNotificationInfo createNotificationInfo(Context context0, NotificationParams notificationParams0) {
        Bundle bundle0 = CommonNotificationBuilder.getManifestMetadata(context0.getPackageManager(), "com.ruliweb.www.ruliapp");
        return CommonNotificationBuilder.createNotificationInfo(context0, context0, notificationParams0, CommonNotificationBuilder.getOrCreateChannel(context0, notificationParams0.getNotificationChannelId(), bundle0), bundle0);
    }

    private static Intent createTargetIntent(String s, NotificationParams notificationParams0, PackageManager packageManager0) {
        String s1 = notificationParams0.getString("gcm.n.click_action");
        if(!TextUtils.isEmpty(s1)) {
            Intent intent0 = new Intent(s1);
            intent0.setPackage(s);
            intent0.setFlags(0x10000000);
            return intent0;
        }
        Uri uri0 = notificationParams0.getLink();
        if(uri0 != null) {
            Intent intent1 = new Intent("android.intent.action.VIEW");
            intent1.setPackage(s);
            intent1.setData(uri0);
            return intent1;
        }
        Intent intent2 = packageManager0.getLaunchIntentForPackage(s);
        if(intent2 == null) {
            Log.w("FirebaseMessaging", "No activity found to launch app");
        }
        return intent2;
    }

    private static int generatePendingIntentRequestCode() {
        return CommonNotificationBuilder.requestCodeProvider.incrementAndGet();
    }

    private static Integer getColor(Context context0, String s, Bundle bundle0) {
        if(!TextUtils.isEmpty(s)) {
            try {
                return Color.parseColor(s);
            }
            catch(IllegalArgumentException unused_ex) {
                Log.w("FirebaseMessaging", "Color is invalid: " + s + ". Notification will use default color.");
            }
        }
        int v = bundle0.getInt("com.google.firebase.messaging.default_notification_color", 0);
        if(v != 0) {
            try {
                return ContextCompat.getColor(context0, v);
            }
            catch(Resources.NotFoundException unused_ex) {
                Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
            }
        }
        return null;
    }

    private static int getConsolidatedDefaults(NotificationParams notificationParams0) {
        int v = notificationParams0.getBoolean("gcm.n.default_sound");
        if(notificationParams0.getBoolean("gcm.n.default_vibrate_timings")) {
            v |= 2;
        }
        return notificationParams0.getBoolean("gcm.n.default_light_settings") ? v | 4 : v;
    }

    private static Bundle getManifestMetadata(PackageManager packageManager0, String s) {
        try {
            ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo(s, 0x80);
            if(applicationInfo0 != null && applicationInfo0.metaData != null) {
                return applicationInfo0.metaData;
            }
        }
        catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
            Log.w("FirebaseMessaging", "Couldn\'t get own application info: " + packageManager$NameNotFoundException0);
        }
        return Bundle.EMPTY;
    }

    public static String getOrCreateChannel(Context context0, String s, Bundle bundle0) {
        String s2;
        if(Build.VERSION.SDK_INT < 26) {
            return null;
        }
        try {
            context0.getPackageManager().getApplicationInfo("com.ruliweb.www.ruliapp", 0);
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
            return null;
        }
        NotificationManager notificationManager0 = (NotificationManager)context0.getSystemService(NotificationManager.class);
        if(!TextUtils.isEmpty(s)) {
            if(notificationManager0.getNotificationChannel(s) != null) {
                return s;
            }
            Log.w("FirebaseMessaging", "Notification Channel requested (" + s + ") has not been created by the app. Manifest configuration, or default, value will be used.");
        }
        String s1 = bundle0.getString("com.google.firebase.messaging.default_notification_channel_id");
        if(TextUtils.isEmpty(s1)) {
            Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
        }
        else {
            if(notificationManager0.getNotificationChannel(s1) != null) {
                return s1;
            }
            Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
        }
        if(notificationManager0.getNotificationChannel("fcm_fallback_notification_channel") == null) {
            int v = context0.getResources().getIdentifier("fcm_fallback_notification_channel_label", "string", "com.ruliweb.www.ruliapp");
            if(v == 0) {
                Log.e("FirebaseMessaging", "String resource \"fcm_fallback_notification_channel_label\" is not found. Using default string channel name.");
                s2 = "Misc";
            }
            else {
                s2 = context0.getString(v);
            }
            notificationManager0.createNotificationChannel(ComponentDialog..ExternalSyntheticApiModelOutline0.m("fcm_fallback_notification_channel", s2, 3));
        }
        return "fcm_fallback_notification_channel";
    }

    private static int getPendingIntentFlags(int v) [...] // 潜在的解密器

    private static int getSmallIcon(PackageManager packageManager0, Resources resources0, String s, String s1, Bundle bundle0) {
        if(!TextUtils.isEmpty(s1)) {
            int v = resources0.getIdentifier(s1, "drawable", s);
            if(v != 0 && CommonNotificationBuilder.isValidIcon(resources0, v)) {
                return v;
            }
            int v1 = resources0.getIdentifier(s1, "mipmap", s);
            if(v1 != 0 && CommonNotificationBuilder.isValidIcon(resources0, v1)) {
                return v1;
            }
            Log.w("FirebaseMessaging", "Icon resource " + s1 + " not found. Notification will use default icon.");
        }
        int v2 = bundle0.getInt("com.google.firebase.messaging.default_notification_icon", 0);
        if(v2 == 0 || !CommonNotificationBuilder.isValidIcon(resources0, v2)) {
            try {
                v2 = packageManager0.getApplicationInfo(s, 0).icon;
                return v2 != 0 && CommonNotificationBuilder.isValidIcon(resources0, v2) ? v2 : 0x1080093;
            }
            catch(PackageManager.NameNotFoundException packageManager$NameNotFoundException0) {
                Log.w("FirebaseMessaging", "Couldn\'t get own application info: " + packageManager$NameNotFoundException0);
            }
        }
        return v2 != 0 && CommonNotificationBuilder.isValidIcon(resources0, v2) ? v2 : 0x1080093;
    }

    private static Uri getSound(String s, NotificationParams notificationParams0, Resources resources0) {
        String s1 = notificationParams0.getSoundResourceName();
        if(TextUtils.isEmpty(s1)) {
            return null;
        }
        return "default".equals(s1) || resources0.getIdentifier(s1, "raw", s) == 0 ? RingtoneManager.getDefaultUri(2) : Uri.parse(("android.resource://" + s + "/raw/" + s1));
    }

    private static String getTag(NotificationParams notificationParams0) {
        String s = notificationParams0.getString("gcm.n.tag");
        return TextUtils.isEmpty(s) ? "FCM-Notification:" + SystemClock.uptimeMillis() : s;
    }

    private static boolean isValidIcon(Resources resources0, int v) {
        if(Build.VERSION.SDK_INT != 26) {
            return true;
        }
        try {
            if(LinkFollowing..ExternalSyntheticApiModelOutline0.m(resources0.getDrawable(v, null))) {
                Log.e("FirebaseMessaging", "Adaptive icons cannot be used in notifications. Ignoring icon id: " + v);
                return false;
            }
            return true;
        }
        catch(Resources.NotFoundException unused_ex) {
            Log.e("FirebaseMessaging", "Couldn\'t find resource " + v + ", treating it as an invalid icon");
            return false;
        }
    }

    static boolean shouldUploadMetrics(NotificationParams notificationParams0) {
        return notificationParams0.getBoolean("google.c.a.e");
    }
}

