package androidx.core.app;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler.Callback;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings.Secure;
import android.service.notification.StatusBarNotification;
import android.support.v4.app.INotificationSideChannel.Stub;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;

public final class NotificationManagerCompat {
    static class Api23Impl {
        static List getActiveNotifications(NotificationManager notificationManager0) {
            StatusBarNotification[] arr_statusBarNotification = notificationManager0.getActiveNotifications();
            return arr_statusBarNotification == null ? new ArrayList() : Arrays.asList(arr_statusBarNotification);
        }

        static int getCurrentInterruptionFilter(NotificationManager notificationManager0) {
            return notificationManager0.getCurrentInterruptionFilter();
        }
    }

    static class Api24Impl {
        static boolean areNotificationsEnabled(NotificationManager notificationManager0) {
            return notificationManager0.areNotificationsEnabled();
        }

        static int getImportance(NotificationManager notificationManager0) {
            return notificationManager0.getImportance();
        }
    }

    static class Api26Impl {
        static void createNotificationChannel(NotificationManager notificationManager0, NotificationChannel notificationChannel0) {
            notificationManager0.createNotificationChannel(notificationChannel0);
        }

        static void createNotificationChannelGroup(NotificationManager notificationManager0, NotificationChannelGroup notificationChannelGroup0) {
            notificationManager0.createNotificationChannelGroup(notificationChannelGroup0);
        }

        static void createNotificationChannelGroups(NotificationManager notificationManager0, List list0) {
            notificationManager0.createNotificationChannelGroups(list0);
        }

        static void createNotificationChannels(NotificationManager notificationManager0, List list0) {
            notificationManager0.createNotificationChannels(list0);
        }

        static void deleteNotificationChannel(NotificationManager notificationManager0, String s) {
            notificationManager0.deleteNotificationChannel(s);
        }

        static void deleteNotificationChannelGroup(NotificationManager notificationManager0, String s) {
            notificationManager0.deleteNotificationChannelGroup(s);
        }

        static String getId(NotificationChannel notificationChannel0) {
            return notificationChannel0.getId();
        }

        static String getId(NotificationChannelGroup notificationChannelGroup0) {
            return notificationChannelGroup0.getId();
        }

        static NotificationChannel getNotificationChannel(NotificationManager notificationManager0, String s) {
            return notificationManager0.getNotificationChannel(s);
        }

        static List getNotificationChannelGroups(NotificationManager notificationManager0) {
            return notificationManager0.getNotificationChannelGroups();
        }

        static List getNotificationChannels(NotificationManager notificationManager0) {
            return notificationManager0.getNotificationChannels();
        }
    }

    static class Api28Impl {
        static NotificationChannelGroup getNotificationChannelGroup(NotificationManager notificationManager0, String s) {
            return notificationManager0.getNotificationChannelGroup(s);
        }
    }

    static class Api30Impl {
        static NotificationChannel getNotificationChannel(NotificationManager notificationManager0, String s, String s1) {
            return notificationManager0.getNotificationChannel(s, s1);
        }

        static String getParentChannelId(NotificationChannel notificationChannel0) {
            return notificationChannel0.getParentChannelId();
        }
    }

    static class Api34Impl {
        static boolean canUseFullScreenIntent(NotificationManager notificationManager0) {
            return notificationManager0.canUseFullScreenIntent();
        }
    }

    static class CancelTask implements Task {
        final boolean all;
        final int id;
        final String packageName;
        final String tag;

        CancelTask(String s) {
            this.packageName = s;
            this.id = 0;
            this.tag = null;
            this.all = true;
        }

        CancelTask(String s, int v, String s1) {
            this.packageName = s;
            this.id = v;
            this.tag = s1;
            this.all = false;
        }

        @Override  // androidx.core.app.NotificationManagerCompat$Task
        public void send(INotificationSideChannel iNotificationSideChannel0) throws RemoteException {
            if(this.all) {
                iNotificationSideChannel0.cancelAll(this.packageName);
                return;
            }
            iNotificationSideChannel0.cancel(this.packageName, this.id, this.tag);
        }

        @Override
        public String toString() {
            return "CancelTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + ", all:" + this.all + "]";
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface InterruptionFilter {
    }

    public static class NotificationWithIdAndTag {
        final int mId;
        Notification mNotification;
        final String mTag;

        public NotificationWithIdAndTag(int v, Notification notification0) {
            this(null, v, notification0);
        }

        public NotificationWithIdAndTag(String s, int v, Notification notification0) {
            this.mTag = s;
            this.mId = v;
            this.mNotification = notification0;
        }
    }

    static class NotifyTask implements Task {
        final int id;
        final Notification notif;
        final String packageName;
        final String tag;

        NotifyTask(String s, int v, String s1, Notification notification0) {
            this.packageName = s;
            this.id = v;
            this.tag = s1;
            this.notif = notification0;
        }

        @Override  // androidx.core.app.NotificationManagerCompat$Task
        public void send(INotificationSideChannel iNotificationSideChannel0) throws RemoteException {
            iNotificationSideChannel0.notify(this.packageName, this.id, this.tag, this.notif);
        }

        @Override
        public String toString() {
            return "NotifyTask[packageName:" + this.packageName + ", id:" + this.id + ", tag:" + this.tag + "]";
        }
    }

    static class ServiceConnectedEvent {
        final ComponentName componentName;
        final IBinder iBinder;

        ServiceConnectedEvent(ComponentName componentName0, IBinder iBinder0) {
            this.componentName = componentName0;
            this.iBinder = iBinder0;
        }
    }

    static class SideChannelManager implements ServiceConnection, Handler.Callback {
        static class ListenerRecord {
            boolean bound;
            final ComponentName componentName;
            int retryCount;
            INotificationSideChannel service;
            ArrayDeque taskQueue;

            ListenerRecord(ComponentName componentName0) {
                this.bound = false;
                this.taskQueue = new ArrayDeque();
                this.retryCount = 0;
                this.componentName = componentName0;
            }
        }

        private static final int MSG_QUEUE_TASK = 0;
        private static final int MSG_RETRY_LISTENER_QUEUE = 3;
        private static final int MSG_SERVICE_CONNECTED = 1;
        private static final int MSG_SERVICE_DISCONNECTED = 2;
        private Set mCachedEnabledPackages;
        private final Context mContext;
        private final Handler mHandler;
        private final HandlerThread mHandlerThread;
        private final Map mRecordMap;

        SideChannelManager(Context context0) {
            this.mRecordMap = new HashMap();
            this.mCachedEnabledPackages = new HashSet();
            this.mContext = context0;
            HandlerThread handlerThread0 = new HandlerThread("NotificationManagerCompat");
            this.mHandlerThread = handlerThread0;
            handlerThread0.start();
            this.mHandler = new Handler(handlerThread0.getLooper(), this);
        }

        private boolean ensureServiceBound(ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0) {
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.bound) {
                return true;
            }
            Intent intent0 = new Intent("android.support.BIND_NOTIFICATION_SIDE_CHANNEL").setComponent(notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName);
            notificationManagerCompat$SideChannelManager$ListenerRecord0.bound = this.mContext.bindService(intent0, this, 33);
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.bound) {
                notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount = 0;
                return notificationManagerCompat$SideChannelManager$ListenerRecord0.bound;
            }
            Log.w("NotifManCompat", "Unable to bind to listener " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName);
            this.mContext.unbindService(this);
            return notificationManagerCompat$SideChannelManager$ListenerRecord0.bound;
        }

        private void ensureServiceUnbound(ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0) {
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.bound) {
                this.mContext.unbindService(this);
                notificationManagerCompat$SideChannelManager$ListenerRecord0.bound = false;
            }
            notificationManagerCompat$SideChannelManager$ListenerRecord0.service = null;
        }

        @Override  // android.os.Handler$Callback
        public boolean handleMessage(Message message0) {
            switch(message0.what) {
                case 0: {
                    this.handleQueueTask(((Task)message0.obj));
                    return true;
                }
                case 1: {
                    this.handleServiceConnected(((ServiceConnectedEvent)message0.obj).componentName, ((ServiceConnectedEvent)message0.obj).iBinder);
                    return true;
                }
                case 2: {
                    this.handleServiceDisconnected(((ComponentName)message0.obj));
                    return true;
                }
                case 3: {
                    this.handleRetryListenerQueue(((ComponentName)message0.obj));
                    return true;
                }
                default: {
                    return false;
                }
            }
        }

        private void handleQueueTask(Task notificationManagerCompat$Task0) {
            this.updateListenerMap();
            for(Object object0: this.mRecordMap.values()) {
                ((ListenerRecord)object0).taskQueue.add(notificationManagerCompat$Task0);
                this.processListenerQueue(((ListenerRecord)object0));
            }
        }

        private void handleRetryListenerQueue(ComponentName componentName0) {
            ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0 = (ListenerRecord)this.mRecordMap.get(componentName0);
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0 != null) {
                this.processListenerQueue(notificationManagerCompat$SideChannelManager$ListenerRecord0);
            }
        }

        private void handleServiceConnected(ComponentName componentName0, IBinder iBinder0) {
            ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0 = (ListenerRecord)this.mRecordMap.get(componentName0);
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0 != null) {
                notificationManagerCompat$SideChannelManager$ListenerRecord0.service = Stub.asInterface(iBinder0);
                notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount = 0;
                this.processListenerQueue(notificationManagerCompat$SideChannelManager$ListenerRecord0);
            }
        }

        private void handleServiceDisconnected(ComponentName componentName0) {
            ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0 = (ListenerRecord)this.mRecordMap.get(componentName0);
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0 != null) {
                this.ensureServiceUnbound(notificationManagerCompat$SideChannelManager$ListenerRecord0);
            }
        }

        @Override  // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName0, IBinder iBinder0) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Connected to service " + componentName0);
            }
            ServiceConnectedEvent notificationManagerCompat$ServiceConnectedEvent0 = new ServiceConnectedEvent(componentName0, iBinder0);
            this.mHandler.obtainMessage(1, notificationManagerCompat$ServiceConnectedEvent0).sendToTarget();
        }

        @Override  // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName0) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Disconnected from service " + componentName0);
            }
            this.mHandler.obtainMessage(2, componentName0).sendToTarget();
        }

        private void processListenerQueue(ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0) {
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Processing component " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName + ", " + notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.size() + " queued tasks");
            }
            if(!notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.isEmpty()) {
                if(!this.ensureServiceBound(notificationManagerCompat$SideChannelManager$ListenerRecord0) || notificationManagerCompat$SideChannelManager$ListenerRecord0.service == null) {
                    this.scheduleListenerRetry(notificationManagerCompat$SideChannelManager$ListenerRecord0);
                    return;
                }
                Task notificationManagerCompat$Task0;
                while((notificationManagerCompat$Task0 = (Task)notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.peek()) != null) {
                    try {
                        if(Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Sending task " + notificationManagerCompat$Task0);
                        }
                        notificationManagerCompat$Task0.send(notificationManagerCompat$SideChannelManager$ListenerRecord0.service);
                        notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.remove();
                    }
                    catch(DeadObjectException unused_ex) {
                        if(!Log.isLoggable("NotifManCompat", 3)) {
                            break;
                        }
                        Log.d("NotifManCompat", "Remote service has died: " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName);
                        break;
                    }
                    catch(RemoteException remoteException0) {
                        Log.w("NotifManCompat", "RemoteException communicating with " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName, remoteException0);
                        break;
                    }
                }
                if(!notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.isEmpty()) {
                    this.scheduleListenerRetry(notificationManagerCompat$SideChannelManager$ListenerRecord0);
                }
            }
        }

        public void queueTask(Task notificationManagerCompat$Task0) {
            this.mHandler.obtainMessage(0, notificationManagerCompat$Task0).sendToTarget();
        }

        private void scheduleListenerRetry(ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0) {
            if(this.mHandler.hasMessages(3, notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName)) {
                return;
            }
            ++notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount;
            if(notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount > 6) {
                Log.w("NotifManCompat", "Giving up on delivering " + notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.size() + " tasks to " + notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName + " after " + notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount + " retries");
                notificationManagerCompat$SideChannelManager$ListenerRecord0.taskQueue.clear();
                return;
            }
            int v = (1 << notificationManagerCompat$SideChannelManager$ListenerRecord0.retryCount - 1) * 1000;
            if(Log.isLoggable("NotifManCompat", 3)) {
                Log.d("NotifManCompat", "Scheduling retry for " + v + " ms");
            }
            Message message0 = this.mHandler.obtainMessage(3, notificationManagerCompat$SideChannelManager$ListenerRecord0.componentName);
            this.mHandler.sendMessageDelayed(message0, ((long)v));
        }

        private void updateListenerMap() {
            Set set0 = NotificationManagerCompat.getEnabledListenerPackages(this.mContext);
            if(!set0.equals(this.mCachedEnabledPackages)) {
                this.mCachedEnabledPackages = set0;
                List list0 = this.mContext.getPackageManager().queryIntentServices(new Intent().setAction("android.support.BIND_NOTIFICATION_SIDE_CHANNEL"), 0);
                HashSet hashSet0 = new HashSet();
                for(Object object0: list0) {
                    ResolveInfo resolveInfo0 = (ResolveInfo)object0;
                    if(set0.contains(resolveInfo0.serviceInfo.packageName)) {
                        ComponentName componentName0 = new ComponentName(resolveInfo0.serviceInfo.packageName, resolveInfo0.serviceInfo.name);
                        if(resolveInfo0.serviceInfo.permission == null) {
                            hashSet0.add(componentName0);
                        }
                        else {
                            Log.w("NotifManCompat", "Permission present on component " + componentName0 + ", not adding listener record.");
                        }
                    }
                }
                for(Object object1: hashSet0) {
                    ComponentName componentName1 = (ComponentName)object1;
                    if(!this.mRecordMap.containsKey(componentName1)) {
                        if(Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Adding listener record for " + componentName1);
                        }
                        ListenerRecord notificationManagerCompat$SideChannelManager$ListenerRecord0 = new ListenerRecord(componentName1);
                        this.mRecordMap.put(componentName1, notificationManagerCompat$SideChannelManager$ListenerRecord0);
                    }
                }
                Iterator iterator2 = this.mRecordMap.entrySet().iterator();
                while(iterator2.hasNext()) {
                    Object object2 = iterator2.next();
                    Map.Entry map$Entry0 = (Map.Entry)object2;
                    if(!hashSet0.contains(map$Entry0.getKey())) {
                        if(Log.isLoggable("NotifManCompat", 3)) {
                            Log.d("NotifManCompat", "Removing listener record for " + map$Entry0.getKey());
                        }
                        this.ensureServiceUnbound(((ListenerRecord)map$Entry0.getValue()));
                        iterator2.remove();
                    }
                }
            }
        }
    }

    interface Task {
        void send(INotificationSideChannel arg1) throws RemoteException;
    }

    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    public static final int INTERRUPTION_FILTER_ALARMS = 4;
    public static final int INTERRUPTION_FILTER_ALL = 1;
    public static final int INTERRUPTION_FILTER_NONE = 3;
    public static final int INTERRUPTION_FILTER_PRIORITY = 2;
    public static final int INTERRUPTION_FILTER_UNKNOWN = 0;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    private static Set sEnabledNotificationListenerPackages;
    private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock;
    private static final Object sLock;
    private static SideChannelManager sSideChannelManager;

    static {
        NotificationManagerCompat.sEnabledNotificationListenersLock = new Object();
        NotificationManagerCompat.sEnabledNotificationListenerPackages = new HashSet();
        NotificationManagerCompat.sLock = new Object();
    }

    NotificationManagerCompat(NotificationManager notificationManager0, Context context0) {
        this.mContext = context0;
        this.mNotificationManager = notificationManager0;
    }

    private NotificationManagerCompat(Context context0) {
        this.mContext = context0;
        this.mNotificationManager = (NotificationManager)context0.getSystemService("notification");
    }

    public boolean areNotificationsEnabled() {
        if(Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.areNotificationsEnabled(this.mNotificationManager);
        }
        AppOpsManager appOpsManager0 = (AppOpsManager)this.mContext.getSystemService("appops");
        int v = this.mContext.getApplicationInfo().uid;
        try {
            Method method0 = AppOpsManager.class.getMethod("checkOpNoThrow", Integer.TYPE, Integer.TYPE, String.class);
            Integer integer0 = (Integer)AppOpsManager.class.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class);
            integer0.intValue();
            return ((int)(((Integer)method0.invoke(appOpsManager0, integer0, v, "com.ruliweb.www.ruliapp")))) == 0;
        }
        catch(ClassNotFoundException | NoSuchMethodException | NoSuchFieldException | InvocationTargetException | IllegalAccessException | RuntimeException unused_ex) {
            return true;
        }
    }

    public boolean canUseFullScreenIntent() {
        if(Build.VERSION.SDK_INT < 29) {
            return true;
        }
        return Build.VERSION.SDK_INT >= 34 ? Api34Impl.canUseFullScreenIntent(this.mNotificationManager) : this.mContext.checkSelfPermission("android.permission.USE_FULL_SCREEN_INTENT") == 0;
    }

    public void cancel(int v) {
        this.cancel(null, v);
    }

    public void cancel(String s, int v) {
        this.mNotificationManager.cancel(s, v);
    }

    public void cancelAll() {
        this.mNotificationManager.cancelAll();
    }

    public void createNotificationChannel(NotificationChannel notificationChannel0) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.createNotificationChannel(this.mNotificationManager, notificationChannel0);
        }
    }

    public void createNotificationChannel(NotificationChannelCompat notificationChannelCompat0) {
        this.createNotificationChannel(notificationChannelCompat0.getNotificationChannel());
    }

    public void createNotificationChannelGroup(NotificationChannelGroup notificationChannelGroup0) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.createNotificationChannelGroup(this.mNotificationManager, notificationChannelGroup0);
        }
    }

    public void createNotificationChannelGroup(NotificationChannelGroupCompat notificationChannelGroupCompat0) {
        this.createNotificationChannelGroup(notificationChannelGroupCompat0.getNotificationChannelGroup());
    }

    public void createNotificationChannelGroups(List list0) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.createNotificationChannelGroups(this.mNotificationManager, list0);
        }
    }

    public void createNotificationChannelGroupsCompat(List list0) {
        if(Build.VERSION.SDK_INT >= 26 && !list0.isEmpty()) {
            ArrayList arrayList0 = new ArrayList(list0.size());
            for(Object object0: list0) {
                arrayList0.add(((NotificationChannelGroupCompat)object0).getNotificationChannelGroup());
            }
            Api26Impl.createNotificationChannelGroups(this.mNotificationManager, arrayList0);
        }
    }

    public void createNotificationChannels(List list0) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.createNotificationChannels(this.mNotificationManager, list0);
        }
    }

    public void createNotificationChannelsCompat(List list0) {
        if(Build.VERSION.SDK_INT >= 26 && !list0.isEmpty()) {
            ArrayList arrayList0 = new ArrayList(list0.size());
            for(Object object0: list0) {
                arrayList0.add(((NotificationChannelCompat)object0).getNotificationChannel());
            }
            Api26Impl.createNotificationChannels(this.mNotificationManager, arrayList0);
        }
    }

    public void deleteNotificationChannel(String s) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.deleteNotificationChannel(this.mNotificationManager, s);
        }
    }

    public void deleteNotificationChannelGroup(String s) {
        if(Build.VERSION.SDK_INT >= 26) {
            Api26Impl.deleteNotificationChannelGroup(this.mNotificationManager, s);
        }
    }

    public void deleteUnlistedNotificationChannels(Collection collection0) {
        if(Build.VERSION.SDK_INT >= 26) {
            for(Object object0: Api26Impl.getNotificationChannels(this.mNotificationManager)) {
                NotificationChannel notificationChannel0 = (NotificationChannel)object0;
                if(!collection0.contains(Api26Impl.getId(notificationChannel0)) && (Build.VERSION.SDK_INT < 30 || !collection0.contains(Api30Impl.getParentChannelId(notificationChannel0)))) {
                    String s = Api26Impl.getId(notificationChannel0);
                    Api26Impl.deleteNotificationChannel(this.mNotificationManager, s);
                }
            }
        }
    }

    public static NotificationManagerCompat from(Context context0) {
        return new NotificationManagerCompat(context0);
    }

    public List getActiveNotifications() {
        return Build.VERSION.SDK_INT >= 23 ? Api23Impl.getActiveNotifications(this.mNotificationManager) : new ArrayList();
    }

    public int getCurrentInterruptionFilter() {
        return Build.VERSION.SDK_INT >= 23 ? Api23Impl.getCurrentInterruptionFilter(this.mNotificationManager) : 0;
    }

    public static Set getEnabledListenerPackages(Context context0) {
        String s = Settings.Secure.getString(context0.getContentResolver(), "enabled_notification_listeners");
        synchronized(NotificationManagerCompat.sEnabledNotificationListenersLock) {
            if(s != null && !s.equals(NotificationManagerCompat.sEnabledNotificationListeners)) {
                String[] arr_s = s.split(":", -1);
                HashSet hashSet0 = new HashSet(arr_s.length);
                for(int v1 = 0; v1 < arr_s.length; ++v1) {
                    ComponentName componentName0 = ComponentName.unflattenFromString(arr_s[v1]);
                    if(componentName0 != null) {
                        hashSet0.add(componentName0.getPackageName());
                    }
                }
                NotificationManagerCompat.sEnabledNotificationListenerPackages = hashSet0;
                NotificationManagerCompat.sEnabledNotificationListeners = s;
            }
            return NotificationManagerCompat.sEnabledNotificationListenerPackages;
        }
    }

    public int getImportance() {
        return Build.VERSION.SDK_INT < 24 ? -1000 : Api24Impl.getImportance(this.mNotificationManager);
    }

    public NotificationChannel getNotificationChannel(String s) {
        return Build.VERSION.SDK_INT < 26 ? null : Api26Impl.getNotificationChannel(this.mNotificationManager, s);
    }

    public NotificationChannel getNotificationChannel(String s, String s1) {
        return Build.VERSION.SDK_INT < 30 ? this.getNotificationChannel(s) : Api30Impl.getNotificationChannel(this.mNotificationManager, s, s1);
    }

    public NotificationChannelCompat getNotificationChannelCompat(String s) {
        if(Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel0 = this.getNotificationChannel(s);
            return notificationChannel0 == null ? null : new NotificationChannelCompat(notificationChannel0);
        }
        return null;
    }

    public NotificationChannelCompat getNotificationChannelCompat(String s, String s1) {
        if(Build.VERSION.SDK_INT >= 26) {
            NotificationChannel notificationChannel0 = this.getNotificationChannel(s, s1);
            return notificationChannel0 == null ? null : new NotificationChannelCompat(notificationChannel0);
        }
        return null;
    }

    public NotificationChannelGroup getNotificationChannelGroup(String s) {
        if(Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.getNotificationChannelGroup(this.mNotificationManager, s);
        }
        if(Build.VERSION.SDK_INT >= 26) {
            for(Object object0: this.getNotificationChannelGroups()) {
                NotificationChannelGroup notificationChannelGroup0 = (NotificationChannelGroup)object0;
                if(Api26Impl.getId(notificationChannelGroup0).equals(s)) {
                    return notificationChannelGroup0;
                }
                if(false) {
                    break;
                }
            }
        }
        return null;
    }

    public NotificationChannelGroupCompat getNotificationChannelGroupCompat(String s) {
        if(Build.VERSION.SDK_INT >= 28) {
            NotificationChannelGroup notificationChannelGroup0 = this.getNotificationChannelGroup(s);
            return notificationChannelGroup0 == null ? null : new NotificationChannelGroupCompat(notificationChannelGroup0);
        }
        if(Build.VERSION.SDK_INT >= 26) {
            NotificationChannelGroup notificationChannelGroup1 = this.getNotificationChannelGroup(s);
            return notificationChannelGroup1 == null ? null : new NotificationChannelGroupCompat(notificationChannelGroup1, this.getNotificationChannels());
        }
        return null;
    }

    public List getNotificationChannelGroups() {
        return Build.VERSION.SDK_INT < 26 ? Collections.EMPTY_LIST : Api26Impl.getNotificationChannelGroups(this.mNotificationManager);
    }

    public List getNotificationChannelGroupsCompat() {
        if(Build.VERSION.SDK_INT >= 26) {
            List list0 = this.getNotificationChannelGroups();
            if(!list0.isEmpty()) {
                List list1 = Build.VERSION.SDK_INT < 28 ? this.getNotificationChannels() : Collections.EMPTY_LIST;
                List list2 = new ArrayList(list0.size());
                for(Object object0: list0) {
                    NotificationChannelGroup notificationChannelGroup0 = (NotificationChannelGroup)object0;
                    if(Build.VERSION.SDK_INT >= 28) {
                        list2.add(new NotificationChannelGroupCompat(notificationChannelGroup0));
                    }
                    else {
                        list2.add(new NotificationChannelGroupCompat(notificationChannelGroup0, list1));
                    }
                }
                return list2;
            }
        }
        return Collections.EMPTY_LIST;
    }

    public List getNotificationChannels() {
        return Build.VERSION.SDK_INT < 26 ? Collections.EMPTY_LIST : Api26Impl.getNotificationChannels(this.mNotificationManager);
    }

    public List getNotificationChannelsCompat() {
        if(Build.VERSION.SDK_INT >= 26) {
            List list0 = this.getNotificationChannels();
            if(!list0.isEmpty()) {
                List list1 = new ArrayList(list0.size());
                for(Object object0: list0) {
                    list1.add(new NotificationChannelCompat(((NotificationChannel)object0)));
                }
                return list1;
            }
        }
        return Collections.EMPTY_LIST;
    }

    public void notify(int v, Notification notification0) {
        this.notify(null, v, notification0);
    }

    public void notify(String s, int v, Notification notification0) {
        if(NotificationManagerCompat.useSideChannelForNotification(notification0)) {
            this.pushSideChannelQueue(new NotifyTask("com.ruliweb.www.ruliapp", v, s, notification0));
            this.mNotificationManager.cancel(s, v);
            return;
        }
        this.mNotificationManager.notify(s, v, notification0);
    }

    public void notify(List list0) {
        int v = list0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            NotificationWithIdAndTag notificationManagerCompat$NotificationWithIdAndTag0 = (NotificationWithIdAndTag)list0.get(v1);
            this.notify(notificationManagerCompat$NotificationWithIdAndTag0.mTag, notificationManagerCompat$NotificationWithIdAndTag0.mId, notificationManagerCompat$NotificationWithIdAndTag0.mNotification);
        }
    }

    private void pushSideChannelQueue(Task notificationManagerCompat$Task0) {
        synchronized(NotificationManagerCompat.sLock) {
            if(NotificationManagerCompat.sSideChannelManager == null) {
                NotificationManagerCompat.sSideChannelManager = new SideChannelManager(this.mContext.getApplicationContext());
            }
            NotificationManagerCompat.sSideChannelManager.queueTask(notificationManagerCompat$Task0);
        }
    }

    private static boolean useSideChannelForNotification(Notification notification0) {
        Bundle bundle0 = NotificationCompat.getExtras(notification0);
        return bundle0 != null && bundle0.getBoolean("android.support.useSideChannel");
    }
}

