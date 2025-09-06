package com.google.firebase.messaging;

import android.app.Application.ActivityLifecycleCallbacks;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.datatransport.TransportFactory;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.concurrent.NamedThreadFactory;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Subscriber;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FirebaseMessaging {
    class AutoInit {
        private static final String AUTO_INIT_PREF = "auto_init";
        private static final String FCM_PREFERENCES = "com.google.firebase.messaging";
        private static final String MANIFEST_METADATA_AUTO_INIT_ENABLED = "firebase_messaging_auto_init_enabled";
        private Boolean autoInitEnabled;
        private EventHandler dataCollectionDefaultChangeEventHandler;
        private boolean initialized;
        private final Subscriber subscriber;

        AutoInit(Subscriber subscriber0) {
            this.subscriber = subscriber0;
        }

        void initialize() {
            synchronized(this) {
                if(this.initialized) {
                    return;
                }
                Boolean boolean0 = this.readEnabled();
                this.autoInitEnabled = boolean0;
                if(boolean0 == null) {
                    FirebaseMessaging.AutoInit..ExternalSyntheticLambda0 firebaseMessaging$AutoInit$$ExternalSyntheticLambda00 = (Event event0) -> if(this.isEnabled()) {
                        FirebaseMessaging.this.startSyncIfNecessary();
                    };
                    this.dataCollectionDefaultChangeEventHandler = firebaseMessaging$AutoInit$$ExternalSyntheticLambda00;
                    this.subscriber.subscribe(DataCollectionDefaultChange.class, firebaseMessaging$AutoInit$$ExternalSyntheticLambda00);
                }
                this.initialized = true;
            }
        }

        boolean isEnabled() {
            synchronized(this) {
                this.initialize();
                Boolean boolean0 = this.autoInitEnabled;
                return boolean0 == null ? FirebaseMessaging.this.firebaseApp.isDataCollectionDefaultEnabled() : boolean0.booleanValue();
            }
        }

        // 检测为 Lambda 实现
        void lambda$initialize$0$com-google-firebase-messaging-FirebaseMessaging$AutoInit(Event event0) [...]

        private Boolean readEnabled() {
            Context context0 = FirebaseMessaging.this.firebaseApp.getApplicationContext();
            SharedPreferences sharedPreferences0 = context0.getSharedPreferences("com.google.firebase.messaging", 0);
            if(sharedPreferences0.contains("auto_init")) {
                return Boolean.valueOf(sharedPreferences0.getBoolean("auto_init", false));
            }
            try {
                PackageManager packageManager0 = context0.getPackageManager();
                if(packageManager0 != null) {
                    ApplicationInfo applicationInfo0 = packageManager0.getApplicationInfo("com.ruliweb.www.ruliapp", 0x80);
                    return applicationInfo0 == null || applicationInfo0.metaData == null || !applicationInfo0.metaData.containsKey("firebase_messaging_auto_init_enabled") ? null : Boolean.valueOf(applicationInfo0.metaData.getBoolean("firebase_messaging_auto_init_enabled"));
                }
            }
            catch(PackageManager.NameNotFoundException unused_ex) {
            }
            return null;
        }

        void setEnabled(boolean z) {
            synchronized(this) {
                this.initialize();
                EventHandler eventHandler0 = this.dataCollectionDefaultChangeEventHandler;
                if(eventHandler0 != null) {
                    this.subscriber.unsubscribe(DataCollectionDefaultChange.class, eventHandler0);
                    this.dataCollectionDefaultChangeEventHandler = null;
                }
                SharedPreferences.Editor sharedPreferences$Editor0 = FirebaseMessaging.this.firebaseApp.getApplicationContext().getSharedPreferences("com.google.firebase.messaging", 0).edit();
                sharedPreferences$Editor0.putBoolean("auto_init", z);
                sharedPreferences$Editor0.apply();
                if(z) {
                    FirebaseMessaging.this.startSyncIfNecessary();
                }
                this.autoInitEnabled = Boolean.valueOf(z);
            }
        }
    }

    private static final String EXTRA_DUMMY_P_INTENT = "app";
    static final String GMS_PACKAGE = "com.google.android.gms";
    @Deprecated
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final long MAX_DELAY_SEC = 0L;
    private static final long MIN_DELAY_SEC = 30L;
    private static final String SEND_INTENT_ACTION = "com.google.android.gcm.intent.SEND";
    private static final String SUBTYPE_DEFAULT = "";
    static final String TAG = "FirebaseMessaging";
    private final AutoInit autoInit;
    private final Context context;
    private final Executor fileExecutor;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi fis;
    private final GmsRpc gmsRpc;
    private final FirebaseInstanceIdInternal iid;
    private final Executor initExecutor;
    private final Application.ActivityLifecycleCallbacks lifecycleCallbacks;
    private final Metadata metadata;
    private final RequestDeduplicator requestDeduplicator;
    private static Store store;
    static ScheduledExecutorService syncExecutor;
    private boolean syncScheduledOrRunning;
    private final Executor taskExecutor;
    private final Task topicsSubscriberTask;
    static TransportFactory transportFactory;

    static {
        FirebaseMessaging.MAX_DELAY_SEC = TimeUnit.HOURS.toSeconds(8L);
    }

    FirebaseMessaging(FirebaseApp firebaseApp0, FirebaseInstanceIdInternal firebaseInstanceIdInternal0, Provider provider0, Provider provider1, FirebaseInstallationsApi firebaseInstallationsApi0, TransportFactory transportFactory0, Subscriber subscriber0) {
        this(firebaseApp0, firebaseInstanceIdInternal0, provider0, provider1, firebaseInstallationsApi0, transportFactory0, subscriber0, new Metadata(firebaseApp0.getApplicationContext()));
    }

    FirebaseMessaging(FirebaseApp firebaseApp0, FirebaseInstanceIdInternal firebaseInstanceIdInternal0, Provider provider0, Provider provider1, FirebaseInstallationsApi firebaseInstallationsApi0, TransportFactory transportFactory0, Subscriber subscriber0, Metadata metadata0) {
        this(firebaseApp0, firebaseInstanceIdInternal0, firebaseInstallationsApi0, transportFactory0, subscriber0, metadata0, new GmsRpc(firebaseApp0, metadata0, provider0, provider1, firebaseInstallationsApi0), FcmExecutors.newTaskExecutor(), FcmExecutors.newInitExecutor(), FcmExecutors.newFileIOExecutor());
    }

    FirebaseMessaging(FirebaseApp firebaseApp0, FirebaseInstanceIdInternal firebaseInstanceIdInternal0, FirebaseInstallationsApi firebaseInstallationsApi0, TransportFactory transportFactory0, Subscriber subscriber0, Metadata metadata0, GmsRpc gmsRpc0, Executor executor0, Executor executor1, Executor executor2) {
        this.syncScheduledOrRunning = false;
        FirebaseMessaging.transportFactory = transportFactory0;
        this.firebaseApp = firebaseApp0;
        this.iid = firebaseInstanceIdInternal0;
        this.fis = firebaseInstallationsApi0;
        this.autoInit = new AutoInit(this, subscriber0);
        Context context0 = firebaseApp0.getApplicationContext();
        this.context = context0;
        FcmLifecycleCallbacks fcmLifecycleCallbacks0 = new FcmLifecycleCallbacks();
        this.lifecycleCallbacks = fcmLifecycleCallbacks0;
        this.metadata = metadata0;
        this.taskExecutor = executor0;
        this.gmsRpc = gmsRpc0;
        this.requestDeduplicator = new RequestDeduplicator(executor0);
        this.initExecutor = executor1;
        this.fileExecutor = executor2;
        Context context1 = firebaseApp0.getApplicationContext();
        if(context1 instanceof Application) {
            ((Application)context1).registerActivityLifecycleCallbacks(fcmLifecycleCallbacks0);
        }
        else {
            Log.w("FirebaseMessaging", "Context " + context1 + " was not an application, can\'t register for lifecycle callbacks. Some notification events may be dropped as a result.");
        }
        if(firebaseInstanceIdInternal0 != null) {
            firebaseInstanceIdInternal0.addNewTokenListener((String s) -> this.invokeOnTokenRefresh(s));
        }
        executor1.execute(() -> if(this.isAutoInitEnabled()) {
            this.startSyncIfNecessary();
        });
        Task task0 = TopicsSubscriber.createInstance(this, metadata0, gmsRpc0, context0, FcmExecutors.newTopicsSyncExecutor());
        this.topicsSubscriberTask = task0;
        task0.addOnSuccessListener(executor1, (TopicsSubscriber topicsSubscriber0) -> if(this.isAutoInitEnabled()) {
            topicsSubscriber0.startTopicsSyncIfNecessary();
        });
        executor1.execute(() -> ProxyNotificationInitializer.initialize(this.context));
    }

    String blockingGetToken() throws IOException {
        FirebaseInstanceIdInternal firebaseInstanceIdInternal0 = this.iid;
        if(firebaseInstanceIdInternal0 != null) {
            try {
                return (String)Tasks.await(firebaseInstanceIdInternal0.getTokenTask());
            }
            catch(ExecutionException | InterruptedException executionException0) {
                throw new IOException(executionException0);
            }
        }
        Token store$Token0 = this.getTokenWithoutTriggeringSync();
        if(!this.tokenNeedsRefresh(store$Token0)) {
            return store$Token0.token;
        }
        String s = Metadata.getDefaultSenderId(this.firebaseApp);
        FirebaseMessaging..ExternalSyntheticLambda8 firebaseMessaging$$ExternalSyntheticLambda80 = () -> {
            Task task0 = this.gmsRpc.getToken();
            FirebaseMessaging..ExternalSyntheticLambda1 firebaseMessaging$$ExternalSyntheticLambda10 = (String s1) -> {
                FirebaseMessaging.getStore(this.context).saveToken(this.getSubtype(), s, s1, this.metadata.getAppVersionCode());
                if(store$Token0 == null || !s1.equals(store$Token0.token)) {
                    this.invokeOnTokenRefresh(s1);
                }
                return Tasks.forResult(s1);
            };
            return task0.onSuccessTask(this.fileExecutor, firebaseMessaging$$ExternalSyntheticLambda10);
        };
        Task task0 = this.requestDeduplicator.getOrStartGetTokenRequest(s, firebaseMessaging$$ExternalSyntheticLambda80);
        try {
            return (String)Tasks.await(task0);
        }
        catch(ExecutionException | InterruptedException executionException1) {
            throw new IOException(executionException1);
        }
    }

    static void clearStoreForTest() {
        synchronized(FirebaseMessaging.class) {
            FirebaseMessaging.store = null;
        }
    }

    static void clearTransportFactoryForTest() {
        FirebaseMessaging.transportFactory = null;
    }

    public Task deleteToken() {
        if(this.iid != null) {
            TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
            FirebaseMessaging..ExternalSyntheticLambda9 firebaseMessaging$$ExternalSyntheticLambda90 = () -> try {
                String s = Metadata.getDefaultSenderId(this.firebaseApp);
                this.iid.deleteToken(s, "FCM");
                taskCompletionSource0.setResult(null);
            }
            catch(Exception exception0) {
                taskCompletionSource0.setException(exception0);
            };
            this.initExecutor.execute(firebaseMessaging$$ExternalSyntheticLambda90);
            return taskCompletionSource0.getTask();
        }
        if(this.getTokenWithoutTriggeringSync() == null) {
            return Tasks.forResult(null);
        }
        TaskCompletionSource taskCompletionSource1 = new TaskCompletionSource();
        FcmExecutors.newNetworkIOExecutor().execute(() -> try {
            Tasks.await(this.gmsRpc.deleteToken());
            FirebaseMessaging.getStore(this.context).deleteToken(this.getSubtype(), Metadata.getDefaultSenderId(this.firebaseApp));
            taskCompletionSource1.setResult(null);
        }
        catch(Exception exception0) {
            taskCompletionSource1.setException(exception0);
        });
        return taskCompletionSource1.getTask();
    }

    public boolean deliveryMetricsExportToBigQueryEnabled() {
        return MessagingAnalytics.deliveryMetricsExportToBigQueryEnabled();
    }

    void enqueueTaskWithDelaySeconds(Runnable runnable0, long v) {
        synchronized(FirebaseMessaging.class) {
            if(FirebaseMessaging.syncExecutor == null) {
                FirebaseMessaging.syncExecutor = new ScheduledThreadPoolExecutor(1, new NamedThreadFactory("TAG"));
            }
            FirebaseMessaging.syncExecutor.schedule(runnable0, v, TimeUnit.SECONDS);
        }
    }

    Context getApplicationContext() {
        return this.context;
    }

    public static FirebaseMessaging getInstance() {
        synchronized(FirebaseMessaging.class) {
            return FirebaseMessaging.getInstance(FirebaseApp.getInstance());
        }
    }

    static FirebaseMessaging getInstance(FirebaseApp firebaseApp0) {
        synchronized(FirebaseMessaging.class) {
            FirebaseMessaging firebaseMessaging0 = (FirebaseMessaging)firebaseApp0.get(FirebaseMessaging.class);
            Preconditions.checkNotNull(firebaseMessaging0, "Firebase Messaging component is not present");
            return firebaseMessaging0;
        }
    }

    private static Store getStore(Context context0) {
        synchronized(FirebaseMessaging.class) {
            if(FirebaseMessaging.store == null) {
                FirebaseMessaging.store = new Store(context0);
            }
            return FirebaseMessaging.store;
        }
    }

    // 去混淆评级： 低(20)
    private String getSubtype() {
        return "[DEFAULT]".equals(this.firebaseApp.getName()) ? "" : this.firebaseApp.getPersistenceKey();
    }

    public Task getToken() {
        FirebaseInstanceIdInternal firebaseInstanceIdInternal0 = this.iid;
        if(firebaseInstanceIdInternal0 != null) {
            return firebaseInstanceIdInternal0.getTokenTask();
        }
        TaskCompletionSource taskCompletionSource0 = new TaskCompletionSource();
        FirebaseMessaging..ExternalSyntheticLambda6 firebaseMessaging$$ExternalSyntheticLambda60 = () -> try {
            taskCompletionSource0.setResult(this.blockingGetToken());
        }
        catch(Exception exception0) {
            taskCompletionSource0.setException(exception0);
        };
        this.initExecutor.execute(firebaseMessaging$$ExternalSyntheticLambda60);
        return taskCompletionSource0.getTask();
    }

    Token getTokenWithoutTriggeringSync() {
        return FirebaseMessaging.getStore(this.context).getToken(this.getSubtype(), Metadata.getDefaultSenderId(this.firebaseApp));
    }

    Task getTopicsSubscriberTask() {
        return this.topicsSubscriberTask;
    }

    public static TransportFactory getTransportFactory() {
        return FirebaseMessaging.transportFactory;
    }

    private void invokeOnTokenRefresh(String s) {
        if("[DEFAULT]".equals(this.firebaseApp.getName())) {
            if(Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Invoking onNewToken for app: " + this.firebaseApp.getName());
            }
            Intent intent0 = new Intent("com.google.firebase.messaging.NEW_TOKEN");
            intent0.putExtra("token", s);
            new FcmBroadcastProcessor(this.context).process(intent0);
        }
    }

    public boolean isAutoInitEnabled() {
        return this.autoInit.isEnabled();
    }

    boolean isGmsCorePresent() {
        return this.metadata.isGmscorePresent();
    }

    public boolean isNotificationDelegationEnabled() {
        return ProxyNotificationInitializer.isProxyNotificationEnabled(this.context);
    }

    // 检测为 Lambda 实现
    Task lambda$blockingGetToken$10$com-google-firebase-messaging-FirebaseMessaging(String s, Token store$Token0) [...]

    // 检测为 Lambda 实现
    Task lambda$blockingGetToken$9$com-google-firebase-messaging-FirebaseMessaging(String s, Token store$Token0, String s1) throws Exception [...]

    // 检测为 Lambda 实现
    void lambda$deleteToken$5$com-google-firebase-messaging-FirebaseMessaging(TaskCompletionSource taskCompletionSource0) [...]

    // 检测为 Lambda 实现
    void lambda$deleteToken$6$com-google-firebase-messaging-FirebaseMessaging(TaskCompletionSource taskCompletionSource0) [...]

    // 检测为 Lambda 实现
    void lambda$getToken$4$com-google-firebase-messaging-FirebaseMessaging(TaskCompletionSource taskCompletionSource0) [...]

    // 检测为 Lambda 实现
    void lambda$new$0$com-google-firebase-messaging-FirebaseMessaging(String s) [...]

    // 检测为 Lambda 实现
    void lambda$new$1$com-google-firebase-messaging-FirebaseMessaging() [...]

    // 检测为 Lambda 实现
    void lambda$new$2$com-google-firebase-messaging-FirebaseMessaging(TopicsSubscriber topicsSubscriber0) [...]

    // 检测为 Lambda 实现
    void lambda$new$3$com-google-firebase-messaging-FirebaseMessaging() [...]

    // 检测为 Lambda 实现
    static Task lambda$subscribeToTopic$7(String s, TopicsSubscriber topicsSubscriber0) throws Exception [...]

    // 检测为 Lambda 实现
    static Task lambda$unsubscribeFromTopic$8(String s, TopicsSubscriber topicsSubscriber0) throws Exception [...]

    @Deprecated
    public void send(RemoteMessage remoteMessage0) {
        if(TextUtils.isEmpty(remoteMessage0.getTo())) {
            throw new IllegalArgumentException("Missing \'to\'");
        }
        Intent intent0 = new Intent("com.google.android.gcm.intent.SEND");
        Intent intent1 = new Intent();
        intent1.setPackage("com.google.example.invalidpackage");
        intent0.putExtra("app", PendingIntent.getBroadcast(this.context, 0, intent1, (Build.VERSION.SDK_INT < 23 ? 0 : 0x4000000)));
        intent0.setPackage("com.google.android.gms");
        remoteMessage0.populateSendMessageIntent(intent0);
        this.context.sendOrderedBroadcast(intent0, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }

    public void setAutoInitEnabled(boolean z) {
        this.autoInit.setEnabled(z);
    }

    public void setDeliveryMetricsExportToBigQuery(boolean z) {
        MessagingAnalytics.setDeliveryMetricsExportToBigQuery(z);
    }

    public Task setNotificationDelegationEnabled(boolean z) {
        return ProxyNotificationInitializer.setEnableProxyNotification(this.initExecutor, this.context, z);
    }

    void setSyncScheduledOrRunning(boolean z) {
        synchronized(this) {
            this.syncScheduledOrRunning = z;
        }
    }

    private void startSync() {
        synchronized(this) {
            if(!this.syncScheduledOrRunning) {
                this.syncWithDelaySecondsInternal(0L);
            }
        }
    }

    private void startSyncIfNecessary() {
        FirebaseInstanceIdInternal firebaseInstanceIdInternal0 = this.iid;
        if(firebaseInstanceIdInternal0 != null) {
            firebaseInstanceIdInternal0.getToken();
            return;
        }
        if(this.tokenNeedsRefresh(this.getTokenWithoutTriggeringSync())) {
            this.startSync();
        }
    }

    public Task subscribeToTopic(String s) {
        FirebaseMessaging..ExternalSyntheticLambda0 firebaseMessaging$$ExternalSyntheticLambda00 = (TopicsSubscriber topicsSubscriber0) -> topicsSubscriber0.subscribeToTopic(s);
        return this.topicsSubscriberTask.onSuccessTask(firebaseMessaging$$ExternalSyntheticLambda00);
    }

    void syncWithDelaySecondsInternal(long v) {
        synchronized(this) {
            this.enqueueTaskWithDelaySeconds(new SyncTask(this, Math.min(Math.max(30L, 2L * v), FirebaseMessaging.MAX_DELAY_SEC)), v);
            this.syncScheduledOrRunning = true;
        }
    }

    boolean tokenNeedsRefresh(Token store$Token0) {
        return store$Token0 == null || store$Token0.needsRefresh(this.metadata.getAppVersionCode());
    }

    public Task unsubscribeFromTopic(String s) {
        FirebaseMessaging..ExternalSyntheticLambda7 firebaseMessaging$$ExternalSyntheticLambda70 = (TopicsSubscriber topicsSubscriber0) -> topicsSubscriber0.unsubscribeFromTopic(s);
        return this.topicsSubscriberTask.onSuccessTask(firebaseMessaging$$ExternalSyntheticLambda70);
    }
}

