package androidx.work.impl.foreground;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import androidx.work.ForegroundInfo;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.constraints.ConstraintsState.ConstraintsNotMet;
import androidx.work.impl.constraints.ConstraintsState;
import androidx.work.impl.constraints.OnConstraintsStateChangedListener;
import androidx.work.impl.constraints.WorkConstraintsTracker;
import androidx.work.impl.constraints.WorkConstraintsTrackerKt;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import java.util.UUID;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Job;

public class SystemForegroundDispatcher implements ExecutionListener, OnConstraintsStateChangedListener {
    interface Callback {
        void cancelNotification(int arg1);

        void notify(int arg1, Notification arg2);

        void startForeground(int arg1, int arg2, Notification arg3);

        void stop();
    }

    private static final String ACTION_CANCEL_WORK = "ACTION_CANCEL_WORK";
    private static final String ACTION_NOTIFY = "ACTION_NOTIFY";
    private static final String ACTION_START_FOREGROUND = "ACTION_START_FOREGROUND";
    private static final String ACTION_STOP_FOREGROUND = "ACTION_STOP_FOREGROUND";
    private static final String KEY_FOREGROUND_SERVICE_TYPE = "KEY_FOREGROUND_SERVICE_TYPE";
    private static final String KEY_GENERATION = "KEY_GENERATION";
    private static final String KEY_NOTIFICATION = "KEY_NOTIFICATION";
    private static final String KEY_NOTIFICATION_ID = "KEY_NOTIFICATION_ID";
    private static final String KEY_WORKSPEC_ID = "KEY_WORKSPEC_ID";
    static final String TAG;
    private Callback mCallback;
    final WorkConstraintsTracker mConstraintsTracker;
    private Context mContext;
    WorkGenerationalId mCurrentForegroundId;
    final Map mForegroundInfoById;
    final Object mLock;
    private final TaskExecutor mTaskExecutor;
    final Map mTrackedWorkSpecs;
    private WorkManagerImpl mWorkManagerImpl;
    final Map mWorkSpecById;

    static {
        SystemForegroundDispatcher.TAG = "WM-SystemFgDispatcher";
    }

    SystemForegroundDispatcher(Context context0) {
        this.mContext = context0;
        this.mLock = new Object();
        WorkManagerImpl workManagerImpl0 = WorkManagerImpl.getInstance(this.mContext);
        this.mWorkManagerImpl = workManagerImpl0;
        this.mTaskExecutor = workManagerImpl0.getWorkTaskExecutor();
        this.mCurrentForegroundId = null;
        this.mForegroundInfoById = new LinkedHashMap();
        this.mTrackedWorkSpecs = new HashMap();
        this.mWorkSpecById = new HashMap();
        this.mConstraintsTracker = new WorkConstraintsTracker(this.mWorkManagerImpl.getTrackers());
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    SystemForegroundDispatcher(Context context0, WorkManagerImpl workManagerImpl0, WorkConstraintsTracker workConstraintsTracker0) {
        this.mContext = context0;
        this.mLock = new Object();
        this.mWorkManagerImpl = workManagerImpl0;
        this.mTaskExecutor = workManagerImpl0.getWorkTaskExecutor();
        this.mCurrentForegroundId = null;
        this.mForegroundInfoById = new LinkedHashMap();
        this.mTrackedWorkSpecs = new HashMap();
        this.mWorkSpecById = new HashMap();
        this.mConstraintsTracker = workConstraintsTracker0;
        this.mWorkManagerImpl.getProcessor().addExecutionListener(this);
    }

    public static Intent createCancelWorkIntent(Context context0, String s) {
        Intent intent0 = new Intent(context0, SystemForegroundService.class);
        intent0.setAction("ACTION_CANCEL_WORK");
        intent0.setData(Uri.parse(("workspec://" + s)));
        intent0.putExtra("KEY_WORKSPEC_ID", s);
        return intent0;
    }

    public static Intent createNotifyIntent(Context context0, WorkGenerationalId workGenerationalId0, ForegroundInfo foregroundInfo0) {
        Intent intent0 = new Intent(context0, SystemForegroundService.class);
        intent0.setAction("ACTION_NOTIFY");
        intent0.putExtra("KEY_NOTIFICATION_ID", foregroundInfo0.getNotificationId());
        intent0.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo0.getForegroundServiceType());
        intent0.putExtra("KEY_NOTIFICATION", foregroundInfo0.getNotification());
        intent0.putExtra("KEY_WORKSPEC_ID", workGenerationalId0.getWorkSpecId());
        intent0.putExtra("KEY_GENERATION", workGenerationalId0.getGeneration());
        return intent0;
    }

    public static Intent createStartForegroundIntent(Context context0, WorkGenerationalId workGenerationalId0, ForegroundInfo foregroundInfo0) {
        Intent intent0 = new Intent(context0, SystemForegroundService.class);
        intent0.setAction("ACTION_START_FOREGROUND");
        intent0.putExtra("KEY_WORKSPEC_ID", workGenerationalId0.getWorkSpecId());
        intent0.putExtra("KEY_GENERATION", workGenerationalId0.getGeneration());
        intent0.putExtra("KEY_NOTIFICATION_ID", foregroundInfo0.getNotificationId());
        intent0.putExtra("KEY_FOREGROUND_SERVICE_TYPE", foregroundInfo0.getForegroundServiceType());
        intent0.putExtra("KEY_NOTIFICATION", foregroundInfo0.getNotification());
        return intent0;
    }

    public static Intent createStopForegroundIntent(Context context0) {
        Intent intent0 = new Intent(context0, SystemForegroundService.class);
        intent0.setAction("ACTION_STOP_FOREGROUND");
        return intent0;
    }

    private void handleCancelWork(Intent intent0) {
        Logger.get().info("WM-SystemFgDispatcher", "Stopping foreground work for " + intent0);
        String s = intent0.getStringExtra("KEY_WORKSPEC_ID");
        if(s != null && !TextUtils.isEmpty(s)) {
            this.mWorkManagerImpl.cancelWorkById(UUID.fromString(s));
        }
    }

    private void handleNotify(Intent intent0) {
        if(this.mCallback == null) {
            throw new IllegalStateException("handleNotify was called on the destroyed dispatcher");
        }
        int v = 0;
        int v1 = intent0.getIntExtra("KEY_NOTIFICATION_ID", 0);
        int v2 = intent0.getIntExtra("KEY_FOREGROUND_SERVICE_TYPE", 0);
        String s = intent0.getStringExtra("KEY_WORKSPEC_ID");
        WorkGenerationalId workGenerationalId0 = new WorkGenerationalId(s, intent0.getIntExtra("KEY_GENERATION", 0));
        Notification notification0 = (Notification)intent0.getParcelableExtra("KEY_NOTIFICATION");
        Logger.get().debug("WM-SystemFgDispatcher", "Notifying with (id:" + v1 + ", workSpecId: " + s + ", notificationType :" + v2 + ")");
        if(notification0 == null) {
            throw new IllegalArgumentException("Notification passed in the intent was null.");
        }
        ForegroundInfo foregroundInfo0 = new ForegroundInfo(v1, notification0, v2);
        this.mForegroundInfoById.put(workGenerationalId0, foregroundInfo0);
        ForegroundInfo foregroundInfo1 = (ForegroundInfo)this.mForegroundInfoById.get(this.mCurrentForegroundId);
        if(foregroundInfo1 == null) {
            this.mCurrentForegroundId = workGenerationalId0;
        }
        else {
            this.mCallback.notify(v1, notification0);
            if(Build.VERSION.SDK_INT >= 29) {
                for(Object object0: this.mForegroundInfoById.entrySet()) {
                    v |= ((ForegroundInfo)((Map.Entry)object0).getValue()).getForegroundServiceType();
                }
                foregroundInfo0 = new ForegroundInfo(foregroundInfo1.getNotificationId(), foregroundInfo1.getNotification(), v);
            }
            else {
                foregroundInfo0 = foregroundInfo1;
            }
        }
        this.mCallback.startForeground(foregroundInfo0.getNotificationId(), foregroundInfo0.getForegroundServiceType(), foregroundInfo0.getNotification());
    }

    private void handleStartForeground(Intent intent0) {
        Logger.get().info("WM-SystemFgDispatcher", "Started foreground service " + intent0);
        androidx.work.impl.foreground.SystemForegroundDispatcher.1 systemForegroundDispatcher$10 = new Runnable() {
            @Override
            public void run() {
                WorkSpec workSpec0 = SystemForegroundDispatcher.this.mWorkManagerImpl.getProcessor().getRunningWorkSpec(intent0.getStringExtra("KEY_WORKSPEC_ID"));
                if(workSpec0 != null && workSpec0.hasConstraints()) {
                    synchronized(SystemForegroundDispatcher.this.mLock) {
                        WorkGenerationalId workGenerationalId0 = WorkSpecKt.generationalId(workSpec0);
                        SystemForegroundDispatcher.this.mWorkSpecById.put(workGenerationalId0, workSpec0);
                        CoroutineDispatcher coroutineDispatcher0 = SystemForegroundDispatcher.this.mTaskExecutor.getTaskCoroutineDispatcher();
                        Job job0 = WorkConstraintsTrackerKt.listen(SystemForegroundDispatcher.this.mConstraintsTracker, workSpec0, coroutineDispatcher0, SystemForegroundDispatcher.this);
                        WorkGenerationalId workGenerationalId1 = WorkSpecKt.generationalId(workSpec0);
                        SystemForegroundDispatcher.this.mTrackedWorkSpecs.put(workGenerationalId1, job0);
                    }
                }
            }
        };
        this.mTaskExecutor.executeOnTaskThread(systemForegroundDispatcher$10);
    }

    void handleStop(Intent intent0) {
        Logger.get().info("WM-SystemFgDispatcher", "Stopping foreground service");
        Callback systemForegroundDispatcher$Callback0 = this.mCallback;
        if(systemForegroundDispatcher$Callback0 != null) {
            systemForegroundDispatcher$Callback0.stop();
        }
    }

    @Override  // androidx.work.impl.constraints.OnConstraintsStateChangedListener
    public void onConstraintsStateChanged(WorkSpec workSpec0, ConstraintsState constraintsState0) {
        if(constraintsState0 instanceof ConstraintsNotMet) {
            Logger.get().debug("WM-SystemFgDispatcher", "Constraints unmet for WorkSpec " + workSpec0.id);
            this.mWorkManagerImpl.stopForegroundWork(WorkSpecKt.generationalId(workSpec0), ((ConstraintsNotMet)constraintsState0).getReason());
        }
    }

    void onDestroy() {
        this.mCallback = null;
        synchronized(this.mLock) {
            for(Object object1: this.mTrackedWorkSpecs.values()) {
                ((Job)object1).cancel(null);
            }
        }
        this.mWorkManagerImpl.getProcessor().removeExecutionListener(this);
    }

    @Override  // androidx.work.impl.ExecutionListener
    public void onExecuted(WorkGenerationalId workGenerationalId0, boolean z) {
        synchronized(this.mLock) {
            Job job0 = ((WorkSpec)this.mWorkSpecById.remove(workGenerationalId0)) == null ? null : ((Job)this.mTrackedWorkSpecs.remove(workGenerationalId0));
            if(job0 != null) {
                job0.cancel(null);
            }
        }
        ForegroundInfo foregroundInfo0 = (ForegroundInfo)this.mForegroundInfoById.remove(workGenerationalId0);
        if(workGenerationalId0.equals(this.mCurrentForegroundId)) {
            if(this.mForegroundInfoById.size() > 0) {
                Iterator iterator0 = this.mForegroundInfoById.entrySet().iterator();
                Object object1 = iterator0.next();
                Map.Entry map$Entry0;
                for(map$Entry0 = (Map.Entry)object1; iterator0.hasNext(); map$Entry0 = (Map.Entry)object2) {
                    Object object2 = iterator0.next();
                }
                this.mCurrentForegroundId = (WorkGenerationalId)map$Entry0.getKey();
                if(this.mCallback != null) {
                    ForegroundInfo foregroundInfo1 = (ForegroundInfo)map$Entry0.getValue();
                    this.mCallback.startForeground(foregroundInfo1.getNotificationId(), foregroundInfo1.getForegroundServiceType(), foregroundInfo1.getNotification());
                    this.mCallback.cancelNotification(foregroundInfo1.getNotificationId());
                }
            }
            else {
                this.mCurrentForegroundId = null;
            }
        }
        Callback systemForegroundDispatcher$Callback0 = this.mCallback;
        if(foregroundInfo0 != null && systemForegroundDispatcher$Callback0 != null) {
            Logger.get().debug("WM-SystemFgDispatcher", "Removing Notification (id: " + foregroundInfo0.getNotificationId() + ", workSpecId: " + workGenerationalId0 + ", notificationType: " + foregroundInfo0.getForegroundServiceType());
            systemForegroundDispatcher$Callback0.cancelNotification(foregroundInfo0.getNotificationId());
        }
    }

    void onStartCommand(Intent intent0) {
        String s = intent0.getAction();
        if("ACTION_START_FOREGROUND".equals(s)) {
            this.handleStartForeground(intent0);
            this.handleNotify(intent0);
            return;
        }
        if("ACTION_NOTIFY".equals(s)) {
            this.handleNotify(intent0);
            return;
        }
        if("ACTION_CANCEL_WORK".equals(s)) {
            this.handleCancelWork(intent0);
            return;
        }
        if("ACTION_STOP_FOREGROUND".equals(s)) {
            this.handleStop(intent0);
        }
    }

    void onTimeout(int v, int v1) {
        Logger.get().info("WM-SystemFgDispatcher", "Foreground service timed out, FGS type: " + v1);
        for(Object object0: this.mForegroundInfoById.entrySet()) {
            Map.Entry map$Entry0 = (Map.Entry)object0;
            if(((ForegroundInfo)map$Entry0.getValue()).getForegroundServiceType() == v1) {
                WorkGenerationalId workGenerationalId0 = (WorkGenerationalId)map$Entry0.getKey();
                this.mWorkManagerImpl.stopForegroundWork(workGenerationalId0, 0xFFFFFF80);
            }
        }
        Callback systemForegroundDispatcher$Callback0 = this.mCallback;
        if(systemForegroundDispatcher$Callback0 != null) {
            systemForegroundDispatcher$Callback0.stop();
        }
    }

    void setCallback(Callback systemForegroundDispatcher$Callback0) {
        if(this.mCallback != null) {
            Logger.get().error("WM-SystemFgDispatcher", "A callback already exists.");
            return;
        }
        this.mCallback = systemForegroundDispatcher$Callback0;
    }
}

