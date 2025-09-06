package com.google.firebase.messaging;

import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Process;
import android.util.Log;
import androidx.core.app.NotificationCompat.BigPictureStyle;
import androidx.core.app.NotificationCompat.Builder;
import com.google.android.gms.tasks.Tasks;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class DisplayNotification {
    private static final int IMAGE_DOWNLOAD_TIMEOUT_SECONDS = 5;
    private final Context context;
    private final ExecutorService networkIoExecutor;
    private final NotificationParams params;

    public DisplayNotification(Context context0, NotificationParams notificationParams0, ExecutorService executorService0) {
        this.networkIoExecutor = executorService0;
        this.context = context0;
        this.params = notificationParams0;
    }

    boolean handleNotification() {
        if(this.params.getBoolean("gcm.n.noui")) {
            return true;
        }
        if(this.isAppForeground()) {
            return false;
        }
        ImageDownload imageDownload0 = this.startImageDownloadInBackground();
        DisplayNotificationInfo commonNotificationBuilder$DisplayNotificationInfo0 = CommonNotificationBuilder.createNotificationInfo(this.context, this.params);
        this.waitForAndApplyImageDownload(commonNotificationBuilder$DisplayNotificationInfo0.notificationBuilder, imageDownload0);
        this.showNotification(commonNotificationBuilder$DisplayNotificationInfo0);
        return true;
    }

    private boolean isAppForeground() {
        if(((KeyguardManager)this.context.getSystemService("keyguard")).inKeyguardRestrictedInputMode()) {
            return false;
        }
        int v = Process.myPid();
        List list0 = ((ActivityManager)this.context.getSystemService("activity")).getRunningAppProcesses();
        if(list0 != null) {
            for(Object object0: list0) {
                ActivityManager.RunningAppProcessInfo activityManager$RunningAppProcessInfo0 = (ActivityManager.RunningAppProcessInfo)object0;
                if(activityManager$RunningAppProcessInfo0.pid == v) {
                    return activityManager$RunningAppProcessInfo0.importance == 100;
                }
                if(false) {
                    break;
                }
            }
        }
        return false;
    }

    private void showNotification(DisplayNotificationInfo commonNotificationBuilder$DisplayNotificationInfo0) {
        if(Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Showing notification");
        }
        NotificationManager notificationManager0 = (NotificationManager)this.context.getSystemService("notification");
        Notification notification0 = commonNotificationBuilder$DisplayNotificationInfo0.notificationBuilder.build();
        notificationManager0.notify(commonNotificationBuilder$DisplayNotificationInfo0.tag, commonNotificationBuilder$DisplayNotificationInfo0.id, notification0);
    }

    private ImageDownload startImageDownloadInBackground() {
        ImageDownload imageDownload0 = ImageDownload.create(this.params.getString("gcm.n.image"));
        if(imageDownload0 != null) {
            imageDownload0.start(this.networkIoExecutor);
        }
        return imageDownload0;
    }

    private void waitForAndApplyImageDownload(Builder notificationCompat$Builder0, ImageDownload imageDownload0) {
        if(imageDownload0 != null) {
            try {
                Bitmap bitmap0 = (Bitmap)Tasks.await(imageDownload0.getTask(), 5L, TimeUnit.SECONDS);
                notificationCompat$Builder0.setLargeIcon(bitmap0);
                notificationCompat$Builder0.setStyle(new BigPictureStyle().bigPicture(bitmap0).bigLargeIcon(null));
            }
            catch(ExecutionException executionException0) {
                Log.w("FirebaseMessaging", "Failed to download image: " + executionException0.getCause());
            }
            catch(InterruptedException unused_ex) {
                Log.w("FirebaseMessaging", "Interrupted while downloading image, showing notification without it");
                imageDownload0.close();
                Thread.currentThread().interrupt();
            }
            catch(TimeoutException unused_ex) {
                Log.w("FirebaseMessaging", "Failed to download image in time, showing notification without it");
                imageDownload0.close();
            }
        }
    }
}

