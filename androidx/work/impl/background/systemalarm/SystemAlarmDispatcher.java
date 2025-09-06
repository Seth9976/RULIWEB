package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.PowerManager.WakeLock;
import android.text.TextUtils;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.Processor;
import androidx.work.impl.StartStopTokens.-CC;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkLauncher;
import androidx.work.impl.WorkLauncherImpl;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.utils.WakeLocks;
import androidx.work.impl.utils.WorkTimer;
import androidx.work.impl.utils.taskexecutor.SerialExecutor;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.ArrayList;
import java.util.List;

public class SystemAlarmDispatcher implements ExecutionListener {
    static class AddRunnable implements Runnable {
        private final SystemAlarmDispatcher mDispatcher;
        private final Intent mIntent;
        private final int mStartId;

        AddRunnable(SystemAlarmDispatcher systemAlarmDispatcher0, Intent intent0, int v) {
            this.mDispatcher = systemAlarmDispatcher0;
            this.mIntent = intent0;
            this.mStartId = v;
        }

        @Override
        public void run() {
            this.mDispatcher.add(this.mIntent, this.mStartId);
        }
    }

    interface CommandsCompletedListener {
        void onAllCommandsCompleted();
    }

    static class DequeueAndCheckForCompletion implements Runnable {
        private final SystemAlarmDispatcher mDispatcher;

        DequeueAndCheckForCompletion(SystemAlarmDispatcher systemAlarmDispatcher0) {
            this.mDispatcher = systemAlarmDispatcher0;
        }

        @Override
        public void run() {
            this.mDispatcher.dequeueAndCheckForCompletion();
        }
    }

    private static final int DEFAULT_START_ID = 0;
    private static final String KEY_START_ID = "KEY_START_ID";
    private static final String PROCESS_COMMAND_TAG = "ProcessCommand";
    static final String TAG;
    final CommandHandler mCommandHandler;
    private CommandsCompletedListener mCompletedListener;
    final Context mContext;
    Intent mCurrentIntent;
    final List mIntents;
    private final Processor mProcessor;
    private StartStopTokens mStartStopTokens;
    final TaskExecutor mTaskExecutor;
    private final WorkLauncher mWorkLauncher;
    private final WorkManagerImpl mWorkManager;
    private final WorkTimer mWorkTimer;

    static {
        SystemAlarmDispatcher.TAG = "WM-SystemAlarmDispatche";
    }

    SystemAlarmDispatcher(Context context0) {
        this(context0, null, null, null);
    }

    SystemAlarmDispatcher(Context context0, Processor processor0, WorkManagerImpl workManagerImpl0, WorkLauncher workLauncher0) {
        Context context1 = context0.getApplicationContext();
        this.mContext = context1;
        this.mStartStopTokens = StartStopTokens.-CC.create();
        if(workManagerImpl0 == null) {
            workManagerImpl0 = WorkManagerImpl.getInstance(context0);
        }
        this.mWorkManager = workManagerImpl0;
        this.mCommandHandler = new CommandHandler(context1, workManagerImpl0.getConfiguration().getClock(), this.mStartStopTokens);
        this.mWorkTimer = new WorkTimer(workManagerImpl0.getConfiguration().getRunnableScheduler());
        if(processor0 == null) {
            processor0 = workManagerImpl0.getProcessor();
        }
        this.mProcessor = processor0;
        TaskExecutor taskExecutor0 = workManagerImpl0.getWorkTaskExecutor();
        this.mTaskExecutor = taskExecutor0;
        if(workLauncher0 == null) {
            workLauncher0 = new WorkLauncherImpl(processor0, taskExecutor0);
        }
        this.mWorkLauncher = workLauncher0;
        processor0.addExecutionListener(this);
        this.mIntents = new ArrayList();
        this.mCurrentIntent = null;
    }

    // 检测为 Lambda 实现
    public boolean add(Intent intent0, int v) [...]

    private void assertMainThread() {
        if(Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("Needs to be invoked on the main thread.");
        }
    }

    void dequeueAndCheckForCompletion() {
        Logger.get().debug("WM-SystemAlarmDispatche", "Checking if commands are complete.");
        this.assertMainThread();
        synchronized(this.mIntents) {
            if(this.mCurrentIntent != null) {
                Logger.get().debug("WM-SystemAlarmDispatche", "Removing command " + this.mCurrentIntent);
                if(!((Intent)this.mIntents.remove(0)).equals(this.mCurrentIntent)) {
                    throw new IllegalStateException("Dequeue-d command is not the first.");
                }
                this.mCurrentIntent = null;
            }
            SerialExecutor serialExecutor0 = this.mTaskExecutor.getSerialTaskExecutor();
            if(!this.mCommandHandler.hasPendingCommands() && this.mIntents.isEmpty() && !serialExecutor0.hasPendingTasks()) {
                Logger.get().debug("WM-SystemAlarmDispatche", "No more commands & intents.");
                CommandsCompletedListener systemAlarmDispatcher$CommandsCompletedListener0 = this.mCompletedListener;
                if(systemAlarmDispatcher$CommandsCompletedListener0 != null) {
                    systemAlarmDispatcher$CommandsCompletedListener0.onAllCommandsCompleted();
                }
            }
            else if(!this.mIntents.isEmpty()) {
                this.processCommand();
            }
        }
    }

    Processor getProcessor() {
        return this.mProcessor;
    }

    TaskExecutor getTaskExecutor() {
        return this.mTaskExecutor;
    }

    WorkManagerImpl getWorkManager() {
        return this.mWorkManager;
    }

    WorkTimer getWorkTimer() {
        return this.mWorkTimer;
    }

    WorkLauncher getWorkerLauncher() {
        return this.mWorkLauncher;
    }

    private boolean hasIntentWithAction(String s) {
        this.assertMainThread();
        synchronized(this.mIntents) {
            for(Object object0: this.mIntents) {
                if(s.equals(((Intent)object0).getAction())) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
            return false;
        }
    }

    void onDestroy() {
        Logger.get().debug("WM-SystemAlarmDispatche", "Destroying SystemAlarmDispatcher");
        this.mProcessor.removeExecutionListener(this);
        this.mCompletedListener = null;
    }

    @Override  // androidx.work.impl.ExecutionListener
    public void onExecuted(WorkGenerationalId workGenerationalId0, boolean z) {
        this.mTaskExecutor.getMainThreadExecutor().execute(() -> {
            Logger.get().debug("WM-SystemAlarmDispatche", "Adding command " + CommandHandler.createExecutionCompletedIntent(this.mContext, workGenerationalId0, z) + " (" + 0 + ")");
            this.assertMainThread();
            String s = CommandHandler.createExecutionCompletedIntent(this.mContext, workGenerationalId0, z).getAction();
            if(TextUtils.isEmpty(s)) {
                Logger.get().warning("WM-SystemAlarmDispatche", "Unknown command. Ignoring");
                return false;
            }
            if("ACTION_CONSTRAINTS_CHANGED".equals(s) && this.hasIntentWithAction("ACTION_CONSTRAINTS_CHANGED")) {
                return false;
            }
            CommandHandler.createExecutionCompletedIntent(this.mContext, workGenerationalId0, z).putExtra("KEY_START_ID", 0);
            synchronized(this.mIntents) {
                this.mIntents.add(CommandHandler.createExecutionCompletedIntent(this.mContext, workGenerationalId0, z));
                if(this.mIntents.isEmpty()) {
                    this.processCommand();
                }
                return true;
            }
        });
    }

    private void processCommand() {
        this.assertMainThread();
        PowerManager.WakeLock powerManager$WakeLock0 = WakeLocks.newWakeLock(this.mContext, "ProcessCommand");
        try {
            powerManager$WakeLock0.acquire();
            androidx.work.impl.background.systemalarm.SystemAlarmDispatcher.1 systemAlarmDispatcher$10 = new Runnable() {
                @Override
                public void run() {
                    synchronized(SystemAlarmDispatcher.this.mIntents) {
                        SystemAlarmDispatcher.this.mCurrentIntent = (Intent)SystemAlarmDispatcher.this.mIntents.get(0);
                    }
                    if(SystemAlarmDispatcher.this.mCurrentIntent != null) {
                        String s = SystemAlarmDispatcher.this.mCurrentIntent.getAction();
                        int v1 = SystemAlarmDispatcher.this.mCurrentIntent.getIntExtra("KEY_START_ID", 0);
                        Logger.get().debug("WM-SystemAlarmDispatche", "Processing command " + SystemAlarmDispatcher.this.mCurrentIntent + ", " + v1);
                        PowerManager.WakeLock powerManager$WakeLock0 = WakeLocks.newWakeLock(SystemAlarmDispatcher.this.mContext, s + " (" + v1 + ")");
                        try {
                            Logger.get().debug("WM-SystemAlarmDispatche", "Acquiring operation wake lock (" + s + ") " + powerManager$WakeLock0);
                            powerManager$WakeLock0.acquire();
                            SystemAlarmDispatcher.this.mCommandHandler.onHandleIntent(SystemAlarmDispatcher.this.mCurrentIntent, v1, SystemAlarmDispatcher.this);
                        }
                        catch(Throwable throwable0) {
                            Logger.get().error("WM-SystemAlarmDispatche", "Unexpected error in onHandleIntent", throwable0);
                        }
                        finally {
                            Logger.get().debug("WM-SystemAlarmDispatche", "Releasing operation wake lock (" + s + ") " + powerManager$WakeLock0);
                            powerManager$WakeLock0.release();
                            SystemAlarmDispatcher.this.mTaskExecutor.getMainThreadExecutor().execute(new DequeueAndCheckForCompletion(SystemAlarmDispatcher.this));
                        }
                    }
                }
            };
            this.mWorkManager.getWorkTaskExecutor().executeOnTaskThread(systemAlarmDispatcher$10);
        }
        finally {
            powerManager$WakeLock0.release();
        }
    }

    void setCompletedListener(CommandsCompletedListener systemAlarmDispatcher$CommandsCompletedListener0) {
        if(this.mCompletedListener != null) {
            Logger.get().error("WM-SystemAlarmDispatche", "A completion listener for SystemAlarmDispatcher already exists.");
            return;
        }
        this.mCompletedListener = systemAlarmDispatcher$CommandsCompletedListener0;
    }
}

