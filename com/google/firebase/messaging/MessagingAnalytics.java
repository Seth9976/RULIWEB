package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.datatransport.Encoding;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.installations.FirebaseInstallations;
import com.google.firebase.messaging.reporting.MessagingClientEvent.Builder;
import com.google.firebase.messaging.reporting.MessagingClientEvent.Event;
import com.google.firebase.messaging.reporting.MessagingClientEvent.MessageType;
import com.google.firebase.messaging.reporting.MessagingClientEvent.SDKPlatform;
import com.google.firebase.messaging.reporting.MessagingClientEvent;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import java.util.concurrent.ExecutionException;

public class MessagingAnalytics {
    private static final String DELIVERY_METRICS_EXPORT_TO_BIG_QUERY_PREF = "export_to_big_query";
    private static final String FCM_PREFERENCES = "com.google.firebase.messaging";
    private static final String MANIFEST_DELIVERY_METRICS_EXPORT_TO_BIG_QUERY_ENABLED = "delivery_metrics_exported_to_big_query_enabled";
    private static final String REENGAGEMENT_MEDIUM = "notification";
    private static final String REENGAGEMENT_SOURCE = "Firebase";

    static boolean deliveryMetricsExportToBigQueryEnabled() {
        try {
            FirebaseApp.getInstance();
        }
        catch(IllegalStateException unused_ex) {
            Log.i("FirebaseMessaging", "FirebaseApp has not being initialized. Device might be in direct boot mode. Skip exporting delivery metrics to Big Query");
            return false;
        }
        Context context0 = FirebaseApp.getInstance().getApplicationContext();
        SharedPreferences sharedPreferences0 = context0.getSharedPreferences("com.google.firebase.messaging", 0);
        if(sharedPreferences0.contains("export_to_big_query")) {
            return sharedPreferences0.getBoolean("export_to_big_query", false);
        }
        try {
            PackageManager packageManager0 = context0.getPackageManager();
            if(packageManager0 != null) {
                ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo("com.ruliweb.www.ruliapp", 0x80);
                return applicationInfo0 == null || applicationInfo0.metaData == null || !applicationInfo0.metaData.containsKey("delivery_metrics_exported_to_big_query_enabled") ? false : applicationInfo0.metaData.getBoolean("delivery_metrics_exported_to_big_query_enabled", false);
            }
        }
        catch(PackageManager.NameNotFoundException unused_ex) {
        }
        return false;
    }

    static MessagingClientEvent eventToProto(Event messagingClientEvent$Event0, Intent intent0) {
        if(intent0 == null) {
            return null;
        }
        Bundle bundle0 = intent0.getExtras();
        if(bundle0 == null) {
            bundle0 = Bundle.EMPTY;
        }
        Builder messagingClientEvent$Builder0 = MessagingClientEvent.newBuilder().setTtl(MessagingAnalytics.getTtl(bundle0)).setEvent(messagingClientEvent$Event0).setInstanceId(MessagingAnalytics.getInstanceId(bundle0)).setPackageName(MessagingAnalytics.getPackageName()).setSdkPlatform(SDKPlatform.ANDROID).setMessageType(MessagingAnalytics.getMessageTypeForFirelog(bundle0));
        String s = MessagingAnalytics.getMessageId(bundle0);
        if(s != null) {
            messagingClientEvent$Builder0.setMessageId(s);
        }
        String s1 = MessagingAnalytics.getTopic(bundle0);
        if(s1 != null) {
            messagingClientEvent$Builder0.setTopic(s1);
        }
        String s2 = MessagingAnalytics.getCollapseKey(bundle0);
        if(s2 != null) {
            messagingClientEvent$Builder0.setCollapseKey(s2);
        }
        String s3 = MessagingAnalytics.getMessageLabel(bundle0);
        if(s3 != null) {
            messagingClientEvent$Builder0.setAnalyticsLabel(s3);
        }
        String s4 = MessagingAnalytics.getComposerLabel(bundle0);
        if(s4 != null) {
            messagingClientEvent$Builder0.setComposerLabel(s4);
        }
        long v = MessagingAnalytics.getProjectNumber(bundle0);
        if(v > 0L) {
            messagingClientEvent$Builder0.setProjectNumber(v);
        }
        return messagingClientEvent$Builder0.build();
    }

    static String getCollapseKey(Bundle bundle0) {
        return bundle0.getString("collapse_key");
    }

    static String getComposerId(Bundle bundle0) {
        return bundle0.getString("google.c.a.c_id");
    }

    static String getComposerLabel(Bundle bundle0) {
        return bundle0.getString("google.c.a.c_l");
    }

    static String getInstanceId(Bundle bundle0) {
        String s = bundle0.getString("google.to");
        if(!TextUtils.isEmpty(s)) {
            return s;
        }
        try {
            return (String)Tasks.await(FirebaseInstallations.getInstance(FirebaseApp.getInstance()).getId());
        }
        catch(ExecutionException | InterruptedException exception0) {
            throw new RuntimeException(exception0);
        }
    }

    static String getMessageChannel(Bundle bundle0) {
        return bundle0.getString("google.c.a.m_c");
    }

    static String getMessageId(Bundle bundle0) {
        String s = bundle0.getString("google.message_id");
        return s == null ? bundle0.getString("message_id") : s;
    }

    static String getMessageLabel(Bundle bundle0) {
        return bundle0.getString("google.c.a.m_l");
    }

    private static int getMessagePriority(String s) {
        if("high".equals(s)) {
            return 1;
        }
        return "normal".equals(s) ? 2 : 0;
    }

    static int getMessagePriorityForFirelog(Bundle bundle0) {
        int v = MessagingAnalytics.getPriority(bundle0);
        if(v == 2) {
            return 5;
        }
        return v == 1 ? 10 : 0;
    }

    static String getMessageTime(Bundle bundle0) {
        return bundle0.getString("google.c.a.ts");
    }

    static MessageType getMessageTypeForFirelog(Bundle bundle0) {
        return bundle0 == null || !NotificationParams.isNotification(bundle0) ? MessageType.DATA_MESSAGE : MessageType.DISPLAY_NOTIFICATION;
    }

    static String getMessageTypeForScion(Bundle bundle0) {
        return bundle0 == null || !NotificationParams.isNotification(bundle0) ? "data" : "display";
    }

    static String getPackageName() {
        return "com.ruliweb.www.ruliapp";
    }

    static int getPriority(Bundle bundle0) {
        String s = bundle0.getString("google.delivered_priority");
        if(s == null) {
            if("1".equals(bundle0.getString("google.priority_reduced"))) {
                return 2;
            }
            s = bundle0.getString("google.priority");
        }
        return MessagingAnalytics.getMessagePriority(s);
    }

    static long getProjectNumber(Bundle bundle0) {
        if(bundle0.containsKey("google.c.sender.id")) {
            try {
                return Long.parseLong(bundle0.getString("google.c.sender.id"));
            }
            catch(NumberFormatException numberFormatException0) {
                Log.w("FirebaseMessaging", "error parsing project number", numberFormatException0);
            }
        }
        FirebaseApp firebaseApp0 = FirebaseApp.getInstance();
        String s = firebaseApp0.getOptions().getGcmSenderId();
        if(s != null) {
            try {
                return Long.parseLong(s);
            }
            catch(NumberFormatException numberFormatException1) {
                Log.w("FirebaseMessaging", "error parsing sender ID", numberFormatException1);
            }
        }
        String s1 = firebaseApp0.getOptions().getApplicationId();
        if(!s1.startsWith("1:")) {
            try {
                return Long.parseLong(s1);
            }
            catch(NumberFormatException numberFormatException2) {
                Log.w("FirebaseMessaging", "error parsing app ID", numberFormatException2);
                return 0L;
            }
        }
        String[] arr_s = s1.split(":");
        if(arr_s.length < 2) {
            return 0L;
        }
        String s2 = arr_s[1];
        if(s2.isEmpty()) {
            return 0L;
        }
        try {
            return Long.parseLong(s2);
        }
        catch(NumberFormatException numberFormatException3) {
            Log.w("FirebaseMessaging", "error parsing app ID", numberFormatException3);
            return 0L;
        }
    }

    static String getTopic(Bundle bundle0) {
        String s = bundle0.getString("from");
        return s == null || !s.startsWith("/topics/") ? null : s;
    }

    static int getTtl(Bundle bundle0) {
        Object object0 = bundle0.get("google.ttl");
        if(object0 instanceof Integer) {
            return (int)(((Integer)object0));
        }
        if(object0 instanceof String) {
            try {
                return Integer.parseInt(((String)object0));
            }
            catch(NumberFormatException unused_ex) {
                Log.w("FirebaseMessaging", "Invalid TTL: " + object0);
            }
        }
        return 0;
    }

    // 去混淆评级： 低(20)
    static String getUseDeviceTime(Bundle bundle0) {
        return bundle0.containsKey("google.c.a.udt") ? bundle0.getString("google.c.a.udt") : null;
    }

    private static boolean isDirectBootMessage(Intent intent0) {
        return "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(intent0.getAction());
    }

    public static void logNotificationDismiss(Intent intent0) {
        MessagingAnalytics.logToScion("_nd", intent0.getExtras());
    }

    public static void logNotificationForeground(Intent intent0) {
        MessagingAnalytics.logToScion("_nf", intent0.getExtras());
    }

    public static void logNotificationOpen(Bundle bundle0) {
        MessagingAnalytics.setUserPropertyIfRequired(bundle0);
        MessagingAnalytics.logToScion("_no", bundle0);
    }

    public static void logNotificationReceived(Intent intent0) {
        if(MessagingAnalytics.shouldUploadScionMetrics(intent0)) {
            MessagingAnalytics.logToScion("_nr", intent0.getExtras());
        }
        if(MessagingAnalytics.shouldUploadFirelogAnalytics(intent0)) {
            MessagingAnalytics.logToFirelog(Event.MESSAGE_DELIVERED, intent0, FirebaseMessaging.getTransportFactory());
        }
    }

    private static void logToFirelog(Event messagingClientEvent$Event0, Intent intent0, TransportFactory transportFactory0) {
        if(transportFactory0 == null) {
            Log.e("FirebaseMessaging", "TransportFactory is null. Skip exporting message delivery metrics to Big Query");
            return;
        }
        MessagingClientEvent messagingClientEvent0 = MessagingAnalytics.eventToProto(messagingClientEvent$Event0, intent0);
        if(messagingClientEvent0 != null) {
            try {
                Encoding encoding0 = Encoding.of("proto");
                MessagingAnalytics..ExternalSyntheticLambda0 messagingAnalytics$$ExternalSyntheticLambda00 = new MessagingAnalytics..ExternalSyntheticLambda0();
                transportFactory0.getTransport("FCM_CLIENT_EVENT_LOGGING", MessagingClientEventExtension.class, encoding0, messagingAnalytics$$ExternalSyntheticLambda00).send(com.google.android.datatransport.Event.ofData(MessagingClientEventExtension.newBuilder().setMessagingClientEvent(messagingClientEvent0).build()));
            }
            catch(RuntimeException runtimeException0) {
                Log.w("FirebaseMessaging", "Failed to send big query analytics payload.", runtimeException0);
            }
        }
    }

    static void logToScion(String s, Bundle bundle0) {
        try {
            FirebaseApp.getInstance();
        }
        catch(IllegalStateException unused_ex) {
            Log.e("FirebaseMessaging", "Default FirebaseApp has not been initialized. Skip logging event to GA.");
            return;
        }
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        Bundle bundle1 = new Bundle();
        String s1 = MessagingAnalytics.getComposerId(bundle0);
        if(s1 != null) {
            bundle1.putString("_nmid", s1);
        }
        String s2 = MessagingAnalytics.getComposerLabel(bundle0);
        if(s2 != null) {
            bundle1.putString("_nmn", s2);
        }
        String s3 = MessagingAnalytics.getMessageLabel(bundle0);
        if(!TextUtils.isEmpty(s3)) {
            bundle1.putString("label", s3);
        }
        String s4 = MessagingAnalytics.getMessageChannel(bundle0);
        if(!TextUtils.isEmpty(s4)) {
            bundle1.putString("message_channel", s4);
        }
        String s5 = MessagingAnalytics.getTopic(bundle0);
        if(s5 != null) {
            bundle1.putString("_nt", s5);
        }
        String s6 = MessagingAnalytics.getMessageTime(bundle0);
        if(s6 != null) {
            try {
                bundle1.putInt("_nmt", Integer.parseInt(s6));
            }
            catch(NumberFormatException numberFormatException0) {
                Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", numberFormatException0);
            }
        }
        String s7 = MessagingAnalytics.getUseDeviceTime(bundle0);
        if(s7 != null) {
            try {
                bundle1.putInt("_ndt", Integer.parseInt(s7));
            }
            catch(NumberFormatException numberFormatException1) {
                Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", numberFormatException1);
            }
        }
        String s8 = MessagingAnalytics.getMessageTypeForScion(bundle0);
        if("_nr".equals(s) || "_nf".equals(s)) {
            bundle1.putString("_nmc", s8);
        }
        if(Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Logging to scion event=" + s + " scionPayload=" + bundle1);
        }
        AnalyticsConnector analyticsConnector0 = (AnalyticsConnector)FirebaseApp.getInstance().get(AnalyticsConnector.class);
        if(analyticsConnector0 != null) {
            analyticsConnector0.logEvent("fcm", s, bundle1);
            return;
        }
        Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
    }

    static void setDeliveryMetricsExportToBigQuery(boolean z) {
        FirebaseApp.getInstance().getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit().putBoolean("export_to_big_query", z).apply();
    }

    private static void setUserPropertyIfRequired(Bundle bundle0) {
        if(bundle0 != null) {
            if("1".equals(bundle0.getString("google.c.a.tc"))) {
                AnalyticsConnector analyticsConnector0 = (AnalyticsConnector)FirebaseApp.getInstance().get(AnalyticsConnector.class);
                if(Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
                }
                if(analyticsConnector0 != null) {
                    String s = bundle0.getString("google.c.a.c_id");
                    analyticsConnector0.setUserProperty("fcm", "_ln", s);
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("source", "Firebase");
                    bundle1.putString("medium", "notification");
                    bundle1.putString("campaign", s);
                    analyticsConnector0.logEvent("fcm", "_cmp", bundle1);
                    return;
                }
                Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
                return;
            }
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
            }
        }
    }

    public static boolean shouldUploadFirelogAnalytics(Intent intent0) {
        return intent0 == null || MessagingAnalytics.isDirectBootMessage(intent0) ? false : MessagingAnalytics.deliveryMetricsExportToBigQueryEnabled();
    }

    public static boolean shouldUploadScionMetrics(Intent intent0) {
        return intent0 == null || MessagingAnalytics.isDirectBootMessage(intent0) ? false : MessagingAnalytics.shouldUploadScionMetrics(intent0.getExtras());
    }

    public static boolean shouldUploadScionMetrics(Bundle bundle0) {
        return bundle0 == null ? false : "1".equals(bundle0.getString("google.c.a.e"));
    }
}

