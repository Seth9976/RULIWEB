package com.google.android.gms.cloudmessaging;

import android.app.PendingIntent.CanceledException;
import android.app.PendingIntent;
import android.content.BroadcastReceiver.PendingResult;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class CloudMessagingReceiver extends BroadcastReceiver {
    public static final class IntentActionKeys {
        public static final String NOTIFICATION_DISMISS = "com.google.firebase.messaging.NOTIFICATION_DISMISS";
        public static final String NOTIFICATION_OPEN = "com.google.firebase.messaging.NOTIFICATION_OPEN";

    }

    public static final class IntentKeys {
        public static final String PENDING_INTENT = "pending_intent";
        public static final String WRAPPED_INTENT = "wrapped_intent";

    }

    private final ExecutorService zza;

    public CloudMessagingReceiver() {
        NamedThreadFactory namedThreadFactory0 = new NamedThreadFactory("firebase-iid-executor");
        ThreadPoolExecutor threadPoolExecutor0 = new ThreadPoolExecutor(1, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), namedThreadFactory0);
        threadPoolExecutor0.allowCoreThreadTimeOut(true);
        this.zza = Executors.unconfigurableExecutorService(threadPoolExecutor0);
    }

    protected Executor getBroadcastExecutor() {
        return this.zza;
    }

    protected abstract int onMessageReceive(Context arg1, CloudMessage arg2);

    protected void onNotificationDismissed(Context context0, Bundle bundle0) {
    }

    @Override  // android.content.BroadcastReceiver
    public final void onReceive(Context context0, Intent intent0) {
        if(intent0 == null) {
            return;
        }
        this.getBroadcastExecutor().execute(new zze(this, intent0, context0, this.isOrderedBroadcast(), this.goAsync()));
    }

    public final void zza(Intent intent0, Context context0, boolean z, BroadcastReceiver.PendingResult broadcastReceiver$PendingResult0) {
        try {
            Parcelable parcelable0 = intent0.getParcelableExtra("wrapped_intent");
            Intent intent1 = parcelable0 instanceof Intent ? ((Intent)parcelable0) : null;
            int v1 = intent1 == null ? this.zzb(context0, intent0) : this.zzc(context0, intent1);
            if(z) {
                broadcastReceiver$PendingResult0.setResultCode(v1);
            }
        }
        finally {
            broadcastReceiver$PendingResult0.finish();
        }
    }

    private final int zzb(Context context0, Intent intent0) {
        Task task0;
        if(intent0.getExtras() == null) {
            return 500;
        }
        String s = intent0.getStringExtra("google.message_id");
        if(TextUtils.isEmpty(s)) {
            task0 = Tasks.forResult(null);
        }
        else {
            Bundle bundle0 = new Bundle();
            bundle0.putString("google.message_id", s);
            task0 = zzs.zzb(context0).zzc(2, bundle0);
        }
        int v = this.onMessageReceive(context0, new CloudMessage(intent0));
        try {
            Tasks.await(task0, TimeUnit.SECONDS.toMillis(1L), TimeUnit.MILLISECONDS);
        }
        catch(ExecutionException | InterruptedException | TimeoutException executionException0) {
            Log.w("CloudMessagingReceiver", "Message ack failed: " + executionException0);
        }
        return v;
    }

    private final int zzc(Context context0, Intent intent0) {
        PendingIntent pendingIntent0 = (PendingIntent)intent0.getParcelableExtra("pending_intent");
        if(pendingIntent0 != null) {
            try {
                pendingIntent0.send();
            }
            catch(PendingIntent.CanceledException unused_ex) {
                Log.e("CloudMessagingReceiver", "Notification pending intent canceled");
            }
        }
        Bundle bundle0 = intent0.getExtras();
        if(bundle0 == null) {
            bundle0 = new Bundle();
        }
        else {
            bundle0.remove("pending_intent");
        }
        if("com.google.firebase.messaging.NOTIFICATION_DISMISS".equals(intent0.getAction())) {
            this.onNotificationDismissed(context0, bundle0);
            return -1;
        }
        Log.e("CloudMessagingReceiver", "Unknown notification action");
        return 500;
    }
}

