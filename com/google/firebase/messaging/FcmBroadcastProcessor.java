package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import androidx.profileinstaller.ProfileInstallReceiver..ExternalSyntheticLambda0;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class FcmBroadcastProcessor {
    private static final String EXTRA_BINARY_DATA = "rawData";
    private static final String EXTRA_BINARY_DATA_BASE_64 = "gcm.rawData64";
    private final Context context;
    private final Executor executor;
    private static WithinAppServiceConnection fcmServiceConn;
    private static final Object lock;

    static {
        FcmBroadcastProcessor.lock = new Object();
    }

    public FcmBroadcastProcessor(Context context0) {
        this.context = context0;
        this.executor = new ProfileInstallReceiver..ExternalSyntheticLambda0();
    }

    public FcmBroadcastProcessor(Context context0, ExecutorService executorService0) {
        this.context = context0;
        this.executor = executorService0;
    }

    private static Task bindToMessagingService(Context context0, Intent intent0, boolean z) {
        if(Log.isLoggable("FirebaseMessaging", 3)) {
            Log.d("FirebaseMessaging", "Binding to service");
        }
        WithinAppServiceConnection withinAppServiceConnection0 = FcmBroadcastProcessor.getServiceConnection(context0, "com.google.firebase.MESSAGING_EVENT");
        if(z) {
            if(ServiceStarter.getInstance().hasWakeLockPermission(context0)) {
                WakeLockHolder.sendWakefulServiceIntent(context0, withinAppServiceConnection0, intent0);
                return Tasks.forResult(-1);
            }
            withinAppServiceConnection0.sendIntent(intent0);
            return Tasks.forResult(-1);
        }
        return withinAppServiceConnection0.sendIntent(intent0).continueWith(new ProfileInstallReceiver..ExternalSyntheticLambda0(), (Task task0) -> -1);
    }

    private static WithinAppServiceConnection getServiceConnection(Context context0, String s) {
        synchronized(FcmBroadcastProcessor.lock) {
            if(FcmBroadcastProcessor.fcmServiceConn == null) {
                FcmBroadcastProcessor.fcmServiceConn = new WithinAppServiceConnection(context0, s);
            }
            return FcmBroadcastProcessor.fcmServiceConn;
        }
    }

    // 检测为 Lambda 实现
    static Integer lambda$bindToMessagingService$3(Task task0) throws Exception [...]

    // 检测为 Lambda 实现
    static Integer lambda$startMessagingService$0(Context context0, Intent intent0) throws Exception [...]

    // 检测为 Lambda 实现
    static Integer lambda$startMessagingService$1(Task task0) throws Exception [...]

    // 去混淆评级： 低(40)
    // 检测为 Lambda 实现
    static Task lambda$startMessagingService$2(Context context0, Intent intent0, boolean z, Task task0) throws Exception [...]

    public Task process(Intent intent0) {
        String s = intent0.getStringExtra("gcm.rawData64");
        if(s != null) {
            intent0.putExtra("rawData", Base64.decode(s, 0));
            intent0.removeExtra("gcm.rawData64");
        }
        return this.startMessagingService(this.context, intent0);
    }

    public static void reset() {
        synchronized(FcmBroadcastProcessor.lock) {
            FcmBroadcastProcessor.fcmServiceConn = null;
        }
    }

    public static void setServiceConnection(WithinAppServiceConnection withinAppServiceConnection0) {
        synchronized(FcmBroadcastProcessor.lock) {
            FcmBroadcastProcessor.fcmServiceConn = withinAppServiceConnection0;
        }
    }

    public Task startMessagingService(Context context0, Intent intent0) {
        if((intent0.getFlags() & 0x10000000) == 0) {
            return FcmBroadcastProcessor.bindToMessagingService(context0, intent0, false);
        }
        FcmBroadcastProcessor..ExternalSyntheticLambda1 fcmBroadcastProcessor$$ExternalSyntheticLambda10 = () -> ServiceStarter.getInstance().startMessagingService(context0, intent0);
        Task task0 = Tasks.call(this.executor, fcmBroadcastProcessor$$ExternalSyntheticLambda10);
        FcmBroadcastProcessor..ExternalSyntheticLambda2 fcmBroadcastProcessor$$ExternalSyntheticLambda20 = (Task task0) -> // 去混淆评级： 低(40)
        (((int)(((Integer)task0.getResult()))) == 402 ? FcmBroadcastProcessor.bindToMessagingService(context0, intent0, true).continueWith(new ProfileInstallReceiver..ExternalSyntheticLambda0(), (Task task0) -> 403) : task0);
        return task0.continueWithTask(this.executor, fcmBroadcastProcessor$$ExternalSyntheticLambda20);
    }
}

