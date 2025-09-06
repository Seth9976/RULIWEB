package com.ruliweb.www.ruliapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import androidx.core.app.NotificationCompat.Builder;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage.Notification;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001A\u00020\bH\u0002J\u0010\u0010\t\u001A\u00020\b2\u0006\u0010\n\u001A\u00020\u000BH\u0016J\u0010\u0010\f\u001A\u00020\b2\u0006\u0010\r\u001A\u00020\u0004H\u0016J\b\u0010\u000E\u001A\u00020\bH\u0002J\u0010\u0010\u000F\u001A\u00020\b2\u0006\u0010\u0010\u001A\u00020\u000BH\u0002J\u0012\u0010\u0011\u001A\u00020\b2\b\u0010\r\u001A\u0004\u0018\u00010\u0004H\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/ruliweb/www/ruliapp/RuliMessagingService;", "Lcom/google/firebase/messaging/FirebaseMessagingService;", "()V", "TAG", "", "notiId", "", "handleNow", "", "onMessageReceived", "remoteMessage", "Lcom/google/firebase/messaging/RemoteMessage;", "onNewToken", "token", "scheduleJob", "sendNotification", "messageBody", "sendRegistrationToServer", "app_release"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class RuliMessagingService extends FirebaseMessagingService {
    private final String TAG;
    private int notiId;

    public RuliMessagingService() {
        this.TAG = "FMS";
        this.notiId = 99896;
    }

    private final void handleNow() {
    }

    @Override  // com.google.firebase.messaging.FirebaseMessagingService
    public void onMessageReceived(RemoteMessage remoteMessage0) {
        Intrinsics.checkNotNullParameter(remoteMessage0, "remoteMessage");
        Map map0 = remoteMessage0.getData();
        Intrinsics.checkNotNullExpressionValue(map0, "getData(...)");
        if(!map0.isEmpty()) {
            this.sendNotification(remoteMessage0);
        }
    }

    @Override  // com.google.firebase.messaging.FirebaseMessagingService
    public void onNewToken(String s) {
        Intrinsics.checkNotNullParameter(s, "token");
    }

    private final void scheduleJob() {
    }

    private final void sendNotification(RemoteMessage remoteMessage0) {
        Intent intent0 = new Intent(this, MainActivity.class);
        intent0.addFlags(0x10008000);
        Object object0 = remoteMessage0.getData().get("link");
        Intrinsics.checkNotNull(object0);
        intent0.putExtra("linkTo", ((String)object0));
        PendingIntent pendingIntent0 = PendingIntent.getActivity(this, 0, intent0, 0x4000000);
        Intrinsics.checkNotNullExpressionValue("ruli_noti_channel", "getString(...)");
        Uri uri0 = RingtoneManager.getDefaultUri(2);
        Builder notificationCompat$Builder0 = new Builder(this, "ruli_noti_channel").setSmallIcon(0x7F080116);  // drawable:ruliweb_icon_144_144
        Notification remoteMessage$Notification0 = remoteMessage0.getNotification();
        Intrinsics.checkNotNull(remoteMessage$Notification0);
        Builder notificationCompat$Builder1 = notificationCompat$Builder0.setContentTitle(remoteMessage$Notification0.getTitle());
        Notification remoteMessage$Notification1 = remoteMessage0.getNotification();
        Intrinsics.checkNotNull(remoteMessage$Notification1);
        Builder notificationCompat$Builder2 = notificationCompat$Builder1.setContentText(remoteMessage$Notification1.getBody()).setAutoCancel(true).setSound(uri0).setContentIntent(pendingIntent0).setPriority(2).setFullScreenIntent(pendingIntent0, true);
        notificationCompat$Builder2.setPriority(1);
        if(Build.VERSION.SDK_INT >= 0x1F) {
            notificationCompat$Builder2.setForegroundServiceBehavior(1);
        }
        Object object1 = this.getSystemService("notification");
        Intrinsics.checkNotNull(object1, "null cannot be cast to non-null type android.app.NotificationManager");
        if(Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager)object1).createNotificationChannel(MainActivity..ExternalSyntheticApiModelOutline0.m("ruli_noti_channel", "RuliappNotiChannel", 4));
        }
        ((NotificationManager)object1).notify(this.notiId, notificationCompat$Builder2.build());
    }

    private final void sendRegistrationToServer(String s) {
    }
}

