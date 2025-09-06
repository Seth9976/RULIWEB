package com.google.firebase;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.core.os.UserManagerCompat;
import androidx.lifecycle.LifecycleKt..ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentDiscovery;
import com.google.firebase.components.ComponentDiscoveryService;
import com.google.firebase.components.ComponentRuntime.Builder;
import com.google.firebase.components.ComponentRuntime;
import com.google.firebase.components.Lazy;
import com.google.firebase.concurrent.ExecutorsRegistrar;
import com.google.firebase.concurrent.UiExecutor;
import com.google.firebase.events.Publisher;
import com.google.firebase.heartbeatinfo.DefaultHeartBeatController;
import com.google.firebase.inject.Provider;
import com.google.firebase.internal.DataCollectionConfigStorage;
import com.google.firebase.provider.FirebaseInitProvider;
import com.google.firebase.tracing.ComponentMonitor;
import com.google.firebase.tracing.FirebaseTrace;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseApp {
    public interface BackgroundStateChangeListener {
        void onBackgroundStateChanged(boolean arg1);
    }

    static class GlobalBackgroundStateListener implements com.google.android.gms.common.api.internal.BackgroundDetector.BackgroundStateChangeListener {
        private static AtomicReference INSTANCE;

        static {
            GlobalBackgroundStateListener.INSTANCE = new AtomicReference();
        }

        static void access$000(Context context0) {
            GlobalBackgroundStateListener.ensureBackgroundStateListenerRegistered(context0);
        }

        private static void ensureBackgroundStateListenerRegistered(Context context0) {
            if(context0.getApplicationContext() instanceof Application) {
                Application application0 = (Application)context0.getApplicationContext();
                if(GlobalBackgroundStateListener.INSTANCE.get() == null) {
                    GlobalBackgroundStateListener firebaseApp$GlobalBackgroundStateListener0 = new GlobalBackgroundStateListener();
                    if(LifecycleKt..ExternalSyntheticBackportWithForwarding0.m(GlobalBackgroundStateListener.INSTANCE, null, firebaseApp$GlobalBackgroundStateListener0)) {
                        BackgroundDetector.initialize(application0);
                        BackgroundDetector.getInstance().addListener(firebaseApp$GlobalBackgroundStateListener0);
                    }
                }
            }
        }

        @Override  // com.google.android.gms.common.api.internal.BackgroundDetector$BackgroundStateChangeListener
        public void onBackgroundStateChanged(boolean z) {
            synchronized(FirebaseApp.LOCK) {
                for(Object object1: new ArrayList(FirebaseApp.INSTANCES.values())) {
                    FirebaseApp firebaseApp0 = (FirebaseApp)object1;
                    if(firebaseApp0.automaticResourceManagementEnabled.get()) {
                        firebaseApp0.notifyBackgroundStateChangeListeners(z);
                    }
                }
            }
        }
    }

    static class UserUnlockReceiver extends BroadcastReceiver {
        private static AtomicReference INSTANCE;
        private final Context applicationContext;

        static {
            UserUnlockReceiver.INSTANCE = new AtomicReference();
        }

        public UserUnlockReceiver(Context context0) {
            this.applicationContext = context0;
        }

        static void access$100(Context context0) {
            UserUnlockReceiver.ensureReceiverRegistered(context0);
        }

        private static void ensureReceiverRegistered(Context context0) {
            if(UserUnlockReceiver.INSTANCE.get() == null) {
                UserUnlockReceiver firebaseApp$UserUnlockReceiver0 = new UserUnlockReceiver(context0);
                if(LifecycleKt..ExternalSyntheticBackportWithForwarding0.m(UserUnlockReceiver.INSTANCE, null, firebaseApp$UserUnlockReceiver0)) {
                    context0.registerReceiver(firebaseApp$UserUnlockReceiver0, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        @Override  // android.content.BroadcastReceiver
        public void onReceive(Context context0, Intent intent0) {
            synchronized(FirebaseApp.LOCK) {
                for(Object object1: FirebaseApp.INSTANCES.values()) {
                    ((FirebaseApp)object1).initializeAllApis();
                }
            }
            this.unregister();
        }

        public void unregister() {
            this.applicationContext.unregisterReceiver(this);
        }
    }

    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    static final Map INSTANCES = null;
    private static final Object LOCK = null;
    private static final String LOG_TAG = "FirebaseApp";
    private final Context applicationContext;
    private final AtomicBoolean automaticResourceManagementEnabled;
    private final List backgroundStateChangeListeners;
    private final ComponentRuntime componentRuntime;
    private final Lazy dataCollectionConfigStorage;
    private final Provider defaultHeartBeatController;
    private final AtomicBoolean deleted;
    private final List lifecycleListeners;
    private final String name;
    private final FirebaseOptions options;

    static {
        FirebaseApp.LOCK = new Object();
        FirebaseApp.INSTANCES = new ArrayMap();
    }

    protected FirebaseApp(Context context0, String s, FirebaseOptions firebaseOptions0) {
        this.automaticResourceManagementEnabled = new AtomicBoolean(false);
        this.deleted = new AtomicBoolean();
        this.backgroundStateChangeListeners = new CopyOnWriteArrayList();
        this.lifecycleListeners = new CopyOnWriteArrayList();
        this.applicationContext = (Context)Preconditions.checkNotNull(context0);
        this.name = Preconditions.checkNotEmpty(s);
        this.options = (FirebaseOptions)Preconditions.checkNotNull(firebaseOptions0);
        StartupTime startupTime0 = FirebaseInitProvider.getStartupTime();
        FirebaseTrace.pushTrace("Firebase");
        FirebaseTrace.pushTrace("ComponentDiscovery");
        List list0 = ComponentDiscovery.forContext(context0, ComponentDiscoveryService.class).discoverLazy();
        FirebaseTrace.popTrace();
        FirebaseTrace.pushTrace("Runtime");
        Builder componentRuntime$Builder0 = ComponentRuntime.builder(UiExecutor.INSTANCE).addLazyComponentRegistrars(list0).addComponentRegistrar(new FirebaseCommonRegistrar()).addComponentRegistrar(new ExecutorsRegistrar()).addComponent(Component.of(context0, Context.class, new Class[0])).addComponent(Component.of(this, FirebaseApp.class, new Class[0])).addComponent(Component.of(firebaseOptions0, FirebaseOptions.class, new Class[0])).setProcessor(new ComponentMonitor());
        if(UserManagerCompat.isUserUnlocked(context0) && FirebaseInitProvider.isCurrentlyInitializing()) {
            componentRuntime$Builder0.addComponent(Component.of(startupTime0, StartupTime.class, new Class[0]));
        }
        ComponentRuntime componentRuntime0 = componentRuntime$Builder0.build();
        this.componentRuntime = componentRuntime0;
        FirebaseTrace.popTrace();
        this.dataCollectionConfigStorage = new Lazy(() -> new DataCollectionConfigStorage(context0, this.getPersistenceKey(), ((Publisher)this.componentRuntime.get(Publisher.class))));
        this.defaultHeartBeatController = componentRuntime0.getProvider(DefaultHeartBeatController.class);
        this.addBackgroundStateChangeListener((boolean z) -> if(!z) {
            ((DefaultHeartBeatController)this.defaultHeartBeatController.get()).registerHeartBeat();
        });
        FirebaseTrace.popTrace();
    }

    public void addBackgroundStateChangeListener(BackgroundStateChangeListener firebaseApp$BackgroundStateChangeListener0) {
        this.checkNotDeleted();
        if(this.automaticResourceManagementEnabled.get() && BackgroundDetector.getInstance().isInBackground()) {
            firebaseApp$BackgroundStateChangeListener0.onBackgroundStateChanged(true);
        }
        this.backgroundStateChangeListeners.add(firebaseApp$BackgroundStateChangeListener0);
    }

    public void addLifecycleEventListener(FirebaseAppLifecycleListener firebaseAppLifecycleListener0) {
        this.checkNotDeleted();
        Preconditions.checkNotNull(firebaseAppLifecycleListener0);
        this.lifecycleListeners.add(firebaseAppLifecycleListener0);
    }

    private void checkNotDeleted() {
        Preconditions.checkState(!this.deleted.get(), "FirebaseApp was deleted");
    }

    public static void clearInstancesForTest() {
        synchronized(FirebaseApp.LOCK) {
            FirebaseApp.INSTANCES.clear();
        }
    }

    public void delete() {
        if(!this.deleted.compareAndSet(false, true)) {
            return;
        }
        synchronized(FirebaseApp.LOCK) {
            FirebaseApp.INSTANCES.remove(this.name);
        }
        this.notifyOnAppDeleted();
    }

    @Override
    public boolean equals(Object object0) {
        if(!(object0 instanceof FirebaseApp)) {
            return false;
        }
        String s = ((FirebaseApp)object0).getName();
        return this.name.equals(s);
    }

    public Object get(Class class0) {
        this.checkNotDeleted();
        return this.componentRuntime.get(class0);
    }

    private static List getAllAppNames() {
        List list0 = new ArrayList();
        synchronized(FirebaseApp.LOCK) {
            for(Object object1: FirebaseApp.INSTANCES.values()) {
                list0.add(((FirebaseApp)object1).getName());
            }
        }
        Collections.sort(list0);
        return list0;
    }

    public Context getApplicationContext() {
        this.checkNotDeleted();
        return this.applicationContext;
    }

    public static List getApps(Context context0) {
        synchronized(FirebaseApp.LOCK) {
        }
        return new ArrayList(FirebaseApp.INSTANCES.values());
    }

    public static FirebaseApp getInstance() {
        synchronized(FirebaseApp.LOCK) {
            FirebaseApp firebaseApp0 = (FirebaseApp)FirebaseApp.INSTANCES.get("[DEFAULT]");
            if(firebaseApp0 != null) {
                ((DefaultHeartBeatController)firebaseApp0.defaultHeartBeatController.get()).registerHeartBeat();
                return firebaseApp0;
            }
        }
        throw new IllegalStateException("Default FirebaseApp is not initialized in this process " + ProcessUtils.getMyProcessName() + ". Make sure to call FirebaseApp.initializeApp(Context) first.");
    }

    public static FirebaseApp getInstance(String s) {
        synchronized(FirebaseApp.LOCK) {
            FirebaseApp firebaseApp0 = (FirebaseApp)FirebaseApp.INSTANCES.get(FirebaseApp.normalize(s));
            if(firebaseApp0 != null) {
                ((DefaultHeartBeatController)firebaseApp0.defaultHeartBeatController.get()).registerHeartBeat();
                return firebaseApp0;
            }
            List list0 = FirebaseApp.getAllAppNames();
            if(!list0.isEmpty()) {
                String s1 = "Available app names: " + TextUtils.join(", ", list0);
            }
        }
        throw new IllegalStateException(String.format("FirebaseApp with name %s doesn\'t exist. %s", s, ""));
    }

    public String getName() {
        this.checkNotDeleted();
        return this.name;
    }

    public FirebaseOptions getOptions() {
        this.checkNotDeleted();
        return this.options;
    }

    public static String getPersistenceKey(String s, FirebaseOptions firebaseOptions0) {
        return Base64Utils.encodeUrlSafeNoPadding(s.getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(firebaseOptions0.getApplicationId().getBytes(Charset.defaultCharset()));
    }

    public String getPersistenceKey() {
        return Base64Utils.encodeUrlSafeNoPadding(this.getName().getBytes(Charset.defaultCharset())) + "+" + Base64Utils.encodeUrlSafeNoPadding(this.getOptions().getApplicationId().getBytes(Charset.defaultCharset()));
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    private void initializeAllApis() {
        if(!UserManagerCompat.isUserUnlocked(this.applicationContext)) {
            Log.i("FirebaseApp", "Device in Direct Boot Mode: postponing initialization of Firebase APIs for app " + this.getName());
            UserUnlockReceiver.access$100(this.applicationContext);
            return;
        }
        Log.i("FirebaseApp", "Device unlocked: initializing all Firebase APIs for app " + this.getName());
        boolean z = this.isDefaultApp();
        this.componentRuntime.initializeEagerComponents(z);
        ((DefaultHeartBeatController)this.defaultHeartBeatController.get()).registerHeartBeat();
    }

    void initializeAllComponents() {
        this.componentRuntime.initializeAllComponentsForTests();
    }

    public static FirebaseApp initializeApp(Context context0) {
        FirebaseOptions firebaseOptions0;
        synchronized(FirebaseApp.LOCK) {
            if(FirebaseApp.INSTANCES.containsKey("[DEFAULT]")) {
                return FirebaseApp.getInstance();
            }
            firebaseOptions0 = FirebaseOptions.fromResource(context0);
            if(firebaseOptions0 == null) {
                Log.w("FirebaseApp", "Default FirebaseApp failed to initialize because no default options were found. This usually means that com.google.gms:google-services was not applied to your gradle project.");
                return null;
            }
        }
        return FirebaseApp.initializeApp(context0, firebaseOptions0);
    }

    public static FirebaseApp initializeApp(Context context0, FirebaseOptions firebaseOptions0) {
        return FirebaseApp.initializeApp(context0, firebaseOptions0, "[DEFAULT]");
    }

    public static FirebaseApp initializeApp(Context context0, FirebaseOptions firebaseOptions0, String s) {
        FirebaseApp firebaseApp0;
        GlobalBackgroundStateListener.access$000(context0);
        String s1 = FirebaseApp.normalize(s);
        if(context0.getApplicationContext() != null) {
            context0 = context0.getApplicationContext();
        }
        synchronized(FirebaseApp.LOCK) {
            Preconditions.checkState(!FirebaseApp.INSTANCES.containsKey(s1), "FirebaseApp name " + s1 + " already exists!");
            Preconditions.checkNotNull(context0, "Application context cannot be null.");
            firebaseApp0 = new FirebaseApp(context0, s1, firebaseOptions0);
            FirebaseApp.INSTANCES.put(s1, firebaseApp0);
        }
        firebaseApp0.initializeAllApis();
        return firebaseApp0;
    }

    public boolean isDataCollectionDefaultEnabled() {
        this.checkNotDeleted();
        return ((DataCollectionConfigStorage)this.dataCollectionConfigStorage.get()).isEnabled();
    }

    public boolean isDefaultApp() {
        return "[DEFAULT]".equals(this.getName());
    }

    // 检测为 Lambda 实现
    DataCollectionConfigStorage lambda$new$0$com-google-firebase-FirebaseApp(Context context0) [...]

    // 检测为 Lambda 实现
    void lambda$new$1$com-google-firebase-FirebaseApp(boolean z) [...]

    private static String normalize(String s) {
        return s.trim();
    }

    private void notifyBackgroundStateChangeListeners(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for(Object object0: this.backgroundStateChangeListeners) {
            ((BackgroundStateChangeListener)object0).onBackgroundStateChanged(z);
        }
    }

    private void notifyOnAppDeleted() {
        for(Object object0: this.lifecycleListeners) {
            ((FirebaseAppLifecycleListener)object0).onDeleted(this.name, this.options);
        }
    }

    public void removeBackgroundStateChangeListener(BackgroundStateChangeListener firebaseApp$BackgroundStateChangeListener0) {
        this.checkNotDeleted();
        this.backgroundStateChangeListeners.remove(firebaseApp$BackgroundStateChangeListener0);
    }

    public void removeLifecycleEventListener(FirebaseAppLifecycleListener firebaseAppLifecycleListener0) {
        this.checkNotDeleted();
        Preconditions.checkNotNull(firebaseAppLifecycleListener0);
        this.lifecycleListeners.remove(firebaseAppLifecycleListener0);
    }

    public void setAutomaticResourceManagementEnabled(boolean z) {
        this.checkNotDeleted();
        if(this.automaticResourceManagementEnabled.compareAndSet(!z, z)) {
            boolean z1 = BackgroundDetector.getInstance().isInBackground();
            if(z && z1) {
                this.notifyBackgroundStateChangeListeners(true);
                return;
            }
            if(!z && z1) {
                this.notifyBackgroundStateChangeListeners(false);
            }
        }
    }

    public void setDataCollectionDefaultEnabled(Boolean boolean0) {
        this.checkNotDeleted();
        ((DataCollectionConfigStorage)this.dataCollectionConfigStorage.get()).setEnabled(boolean0);
    }

    @Deprecated
    public void setDataCollectionDefaultEnabled(boolean z) {
        this.setDataCollectionDefaultEnabled(Boolean.valueOf(z));
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.name).add("options", this.options).toString();
    }
}

