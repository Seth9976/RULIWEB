package com.google.firebase.messaging;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

public class FirebaseMessagingService extends EnhancedIntentService {
    public static final String ACTION_DIRECT_BOOT_REMOTE_INTENT = "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT";
    static final String ACTION_NEW_TOKEN = "com.google.firebase.messaging.NEW_TOKEN";
    static final String ACTION_REMOTE_INTENT = "com.google.android.c2dm.intent.RECEIVE";
    static final String EXTRA_TOKEN = "token";
    private static final int RECENTLY_RECEIVED_MESSAGE_IDS_MAX_SIZE = 10;
    private static final Queue recentlyReceivedMessageIds;

    static {
        FirebaseMessagingService.recentlyReceivedMessageIds = new ArrayDeque(10);
    }

    private boolean alreadyReceivedMessage(String s) {
        if(TextUtils.isEmpty(s)) {
            return false;
        }
        Queue queue0 = FirebaseMessagingService.recentlyReceivedMessageIds;
        if(queue0.contains(s)) {
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Received duplicate message: " + s);
            }
            return true;
        }
        if(queue0.size() >= 10) {
            queue0.remove();
        }
        queue0.add(s);
        return false;
    }

    private void dispatchMessage(Intent intent0) {
        Bundle bundle0 = intent0.getExtras();
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        bundle0.remove("androidx.content.wakelockid");
        if(NotificationParams.isNotification(bundle0)) {
            NotificationParams notificationParams0 = new NotificationParams(bundle0);
            ExecutorService executorService0 = FcmExecutors.newNetworkIOExecutor();
            DisplayNotification displayNotification0 = new DisplayNotification(this, notificationParams0, executorService0);
            try {
                if(displayNotification0.handleNotification()) {
                    return;
                }
            }
            finally {
                executorService0.shutdown();
            }
            if(MessagingAnalytics.shouldUploadScionMetrics(intent0)) {
                MessagingAnalytics.logNotificationForeground(intent0);
            }
        }
        this.onMessageReceived(new RemoteMessage(bundle0));
    }

    private String getMessageId(Intent intent0) {
        String s = intent0.getStringExtra("google.message_id");
        return s == null ? intent0.getStringExtra("message_id") : s;
    }

    @Override  // com.google.firebase.messaging.EnhancedIntentService
    protected Intent getStartCommandIntent(Intent intent0) {
        return ServiceStarter.getInstance().getMessagingEvent();
    }

    @Override  // com.google.firebase.messaging.EnhancedIntentService
    public void handleIntent(Intent intent0) {
        String s = intent0.getAction();
        if(!"com.google.android.c2dm.intent.RECEIVE".equals(s) && !"com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(s)) {
            if("com.google.firebase.messaging.NEW_TOKEN".equals(s)) {
                this.onNewToken(intent0.getStringExtra("token"));
                return;
            }
            Log.d("FirebaseMessaging", "Unknown intent action: " + intent0.getAction());
            return;
        }
        this.handleMessageIntent(intent0);
    }

    private void handleMessageIntent(Intent intent0) {
        if(!this.alreadyReceivedMessage(intent0.getStringExtra("google.message_id"))) {
            this.passMessageIntentToSdk(intent0);
        }
    }

    public void onDeletedMessages() {
    }

    public void onMessageReceived(RemoteMessage remoteMessage0) {
    }

    public void onMessageSent(String s) {
    }

    public void onNewToken(String s) {
    }

    public void onSendError(String s, Exception exception0) {
    }

    private void passMessageIntentToSdk(Intent intent0) {
        String s = intent0.getStringExtra("message_type");
        if(s == null) {
            s = "gcm";
        }
        s.hashCode();
        switch(s) {
            case "deleted_messages": {
                return;
            }
            case "gcm": {
                MessagingAnalytics.logNotificationReceived(intent0);
                this.dispatchMessage(intent0);
                return;
            }
            case "send_error": {
                this.onSendError(this.getMessageId(intent0), new SendException(intent0.getStringExtra("error")));
                return;
            }
            case "send_event": {
                this.onMessageSent(intent0.getStringExtra("google.message_id"));
                return;
            }
            default: {
                Log.w("FirebaseMessaging", "Received message with unknown type: " + s);
            }
        }
    }

    static void resetForTesting() {
        FirebaseMessagingService.recentlyReceivedMessageIds.clear();
    }
}

