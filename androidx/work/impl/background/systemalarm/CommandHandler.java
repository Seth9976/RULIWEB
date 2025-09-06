package androidx.work.impl.background.systemalarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.work.Clock;
import androidx.work.Logger;
import androidx.work.impl.ExecutionListener;
import androidx.work.impl.StartStopToken;
import androidx.work.impl.StartStopTokens;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandHandler implements ExecutionListener {
    static final String ACTION_CONSTRAINTS_CHANGED = "ACTION_CONSTRAINTS_CHANGED";
    static final String ACTION_DELAY_MET = "ACTION_DELAY_MET";
    static final String ACTION_EXECUTION_COMPLETED = "ACTION_EXECUTION_COMPLETED";
    static final String ACTION_RESCHEDULE = "ACTION_RESCHEDULE";
    static final String ACTION_SCHEDULE_WORK = "ACTION_SCHEDULE_WORK";
    static final String ACTION_STOP_WORK = "ACTION_STOP_WORK";
    private static final String KEY_NEEDS_RESCHEDULE = "KEY_NEEDS_RESCHEDULE";
    private static final String KEY_WORKSPEC_GENERATION = "KEY_WORKSPEC_GENERATION";
    private static final String KEY_WORKSPEC_ID = "KEY_WORKSPEC_ID";
    private static final String TAG = null;
    static final long WORK_PROCESSING_TIME_IN_MS = 600000L;
    private final Clock mClock;
    private final Context mContext;
    private final Object mLock;
    private final Map mPendingDelayMet;
    private final StartStopTokens mStartStopTokens;

    static {
        CommandHandler.TAG = "WM-CommandHandler";
    }

    CommandHandler(Context context0, Clock clock0, StartStopTokens startStopTokens0) {
        this.mContext = context0;
        this.mClock = clock0;
        this.mStartStopTokens = startStopTokens0;
        this.mPendingDelayMet = new HashMap();
        this.mLock = new Object();
    }

    static Intent createConstraintsChangedIntent(Context context0) {
        Intent intent0 = new Intent(context0, SystemAlarmService.class);
        intent0.setAction("ACTION_CONSTRAINTS_CHANGED");
        return intent0;
    }

    static Intent createDelayMetIntent(Context context0, WorkGenerationalId workGenerationalId0) {
        Intent intent0 = new Intent(context0, SystemAlarmService.class);
        intent0.setAction("ACTION_DELAY_MET");
        return CommandHandler.writeWorkGenerationalId(intent0, workGenerationalId0);
    }

    static Intent createExecutionCompletedIntent(Context context0, WorkGenerationalId workGenerationalId0, boolean z) {
        Intent intent0 = new Intent(context0, SystemAlarmService.class);
        intent0.setAction("ACTION_EXECUTION_COMPLETED");
        intent0.putExtra("KEY_NEEDS_RESCHEDULE", z);
        return CommandHandler.writeWorkGenerationalId(intent0, workGenerationalId0);
    }

    static Intent createRescheduleIntent(Context context0) {
        Intent intent0 = new Intent(context0, SystemAlarmService.class);
        intent0.setAction("ACTION_RESCHEDULE");
        return intent0;
    }

    static Intent createScheduleWorkIntent(Context context0, WorkGenerationalId workGenerationalId0) {
        Intent intent0 = new Intent(context0, SystemAlarmService.class);
        intent0.setAction("ACTION_SCHEDULE_WORK");
        return CommandHandler.writeWorkGenerationalId(intent0, workGenerationalId0);
    }

    static Intent createStopWorkIntent(Context context0, WorkGenerationalId workGenerationalId0) {
        Intent intent0 = new Intent(context0, SystemAlarmService.class);
        intent0.setAction("ACTION_STOP_WORK");
        return CommandHandler.writeWorkGenerationalId(intent0, workGenerationalId0);
    }

    static Intent createStopWorkIntent(Context context0, String s) {
        Intent intent0 = new Intent(context0, SystemAlarmService.class);
        intent0.setAction("ACTION_STOP_WORK");
        intent0.putExtra("KEY_WORKSPEC_ID", s);
        return intent0;
    }

    private void handleConstraintsChanged(Intent intent0, int v, SystemAlarmDispatcher systemAlarmDispatcher0) {
        Logger.get().debug("WM-CommandHandler", "Handling constraints changed " + intent0);
        new ConstraintsCommandHandler(this.mContext, this.mClock, v, systemAlarmDispatcher0).handleConstraintsChanged();
    }

    private void handleDelayMet(Intent intent0, int v, SystemAlarmDispatcher systemAlarmDispatcher0) {
        synchronized(this.mLock) {
            WorkGenerationalId workGenerationalId0 = CommandHandler.readWorkGenerationalId(intent0);
            Logger.get().debug("WM-CommandHandler", "Handing delay met for " + workGenerationalId0);
            if(this.mPendingDelayMet.containsKey(workGenerationalId0)) {
                Logger.get().debug("WM-CommandHandler", "WorkSpec " + workGenerationalId0 + " is is already being handled for ACTION_DELAY_MET");
            }
            else {
                StartStopToken startStopToken0 = this.mStartStopTokens.tokenFor(workGenerationalId0);
                DelayMetCommandHandler delayMetCommandHandler0 = new DelayMetCommandHandler(this.mContext, v, systemAlarmDispatcher0, startStopToken0);
                this.mPendingDelayMet.put(workGenerationalId0, delayMetCommandHandler0);
                delayMetCommandHandler0.handleProcessWork();
            }
        }
    }

    private void handleExecutionCompleted(Intent intent0, int v) {
        WorkGenerationalId workGenerationalId0 = CommandHandler.readWorkGenerationalId(intent0);
        boolean z = intent0.getExtras().getBoolean("KEY_NEEDS_RESCHEDULE");
        Logger.get().debug("WM-CommandHandler", "Handling onExecutionCompleted " + intent0 + ", " + v);
        this.onExecuted(workGenerationalId0, z);
    }

    private void handleReschedule(Intent intent0, int v, SystemAlarmDispatcher systemAlarmDispatcher0) {
        Logger.get().debug("WM-CommandHandler", "Handling reschedule " + intent0 + ", " + v);
        systemAlarmDispatcher0.getWorkManager().rescheduleEligibleWork();
    }

    private void handleScheduleWorkIntent(Intent intent0, int v, SystemAlarmDispatcher systemAlarmDispatcher0) {
        WorkGenerationalId workGenerationalId0 = CommandHandler.readWorkGenerationalId(intent0);
        Logger.get().debug("WM-CommandHandler", "Handling schedule work for " + workGenerationalId0);
        WorkDatabase workDatabase0 = systemAlarmDispatcher0.getWorkManager().getWorkDatabase();
        workDatabase0.beginTransaction();
        try {
            WorkSpec workSpec0 = workDatabase0.workSpecDao().getWorkSpec(workGenerationalId0.getWorkSpecId());
            if(workSpec0 == null) {
                Logger.get().warning("WM-CommandHandler", "Skipping scheduling " + workGenerationalId0 + " because it\'s no longer in the DB");
                return;
            }
            long v2 = workSpec0.calculateNextRunTime();
            if(workSpec0.hasConstraints()) {
                Logger.get().debug("WM-CommandHandler", "Opportunistically setting an alarm for " + workGenerationalId0 + "at " + v2);
                Alarms.setAlarm(this.mContext, workDatabase0, workGenerationalId0, v2);
                Intent intent1 = CommandHandler.createConstraintsChangedIntent(this.mContext);
                systemAlarmDispatcher0.getTaskExecutor().getMainThreadExecutor().execute(new AddRunnable(systemAlarmDispatcher0, intent1, v));
            }
            else {
                Logger.get().debug("WM-CommandHandler", "Setting up Alarms for " + workGenerationalId0 + "at " + v2);
                Alarms.setAlarm(this.mContext, workDatabase0, workGenerationalId0, v2);
            }
            workDatabase0.setTransactionSuccessful();
        }
        finally {
            workDatabase0.endTransaction();
        }
    }

    private void handleStopWork(Intent intent0, SystemAlarmDispatcher systemAlarmDispatcher0) {
        List list0;
        Bundle bundle0 = intent0.getExtras();
        String s = bundle0.getString("KEY_WORKSPEC_ID");
        if(bundle0.containsKey("KEY_WORKSPEC_GENERATION")) {
            int v = bundle0.getInt("KEY_WORKSPEC_GENERATION");
            list0 = new ArrayList(1);
            WorkGenerationalId workGenerationalId0 = new WorkGenerationalId(s, v);
            StartStopToken startStopToken0 = this.mStartStopTokens.remove(workGenerationalId0);
            if(startStopToken0 != null) {
                list0.add(startStopToken0);
            }
        }
        else {
            list0 = this.mStartStopTokens.remove(s);
        }
        for(Object object0: list0) {
            Logger.get().debug("WM-CommandHandler", "Handing stopWork work for " + s);
            systemAlarmDispatcher0.getWorkerLauncher().stopWork(((StartStopToken)object0));
            Alarms.cancelAlarm(this.mContext, systemAlarmDispatcher0.getWorkManager().getWorkDatabase(), ((StartStopToken)object0).getId());
            systemAlarmDispatcher0.onExecuted(((StartStopToken)object0).getId(), false);
        }
    }

    private static boolean hasKeys(Bundle bundle0, String[] arr_s) {
        if(bundle0 != null && !bundle0.isEmpty()) {
            for(int v = 0; v < arr_s.length; ++v) {
                if(bundle0.get(arr_s[v]) == null) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    boolean hasPendingCommands() {
        synchronized(this.mLock) {
        }
        return !this.mPendingDelayMet.isEmpty();
    }

    @Override  // androidx.work.impl.ExecutionListener
    public void onExecuted(WorkGenerationalId workGenerationalId0, boolean z) {
        synchronized(this.mLock) {
            DelayMetCommandHandler delayMetCommandHandler0 = (DelayMetCommandHandler)this.mPendingDelayMet.remove(workGenerationalId0);
            this.mStartStopTokens.remove(workGenerationalId0);
            if(delayMetCommandHandler0 != null) {
                delayMetCommandHandler0.onExecuted(z);
            }
        }
    }

    void onHandleIntent(Intent intent0, int v, SystemAlarmDispatcher systemAlarmDispatcher0) {
        String s = intent0.getAction();
        if("ACTION_CONSTRAINTS_CHANGED".equals(s)) {
            this.handleConstraintsChanged(intent0, v, systemAlarmDispatcher0);
            return;
        }
        if("ACTION_RESCHEDULE".equals(s)) {
            this.handleReschedule(intent0, v, systemAlarmDispatcher0);
            return;
        }
        if(!CommandHandler.hasKeys(intent0.getExtras(), new String[]{"KEY_WORKSPEC_ID"})) {
            Logger.get().error("WM-CommandHandler", "Invalid request for " + s + " , requires KEY_WORKSPEC_ID .");
            return;
        }
        if("ACTION_SCHEDULE_WORK".equals(s)) {
            this.handleScheduleWorkIntent(intent0, v, systemAlarmDispatcher0);
            return;
        }
        if("ACTION_DELAY_MET".equals(s)) {
            this.handleDelayMet(intent0, v, systemAlarmDispatcher0);
            return;
        }
        if("ACTION_STOP_WORK".equals(s)) {
            this.handleStopWork(intent0, systemAlarmDispatcher0);
            return;
        }
        if("ACTION_EXECUTION_COMPLETED".equals(s)) {
            this.handleExecutionCompleted(intent0, v);
            return;
        }
        Logger.get().warning("WM-CommandHandler", "Ignoring intent " + intent0);
    }

    static WorkGenerationalId readWorkGenerationalId(Intent intent0) {
        return new WorkGenerationalId(intent0.getStringExtra("KEY_WORKSPEC_ID"), intent0.getIntExtra("KEY_WORKSPEC_GENERATION", 0));
    }

    private static Intent writeWorkGenerationalId(Intent intent0, WorkGenerationalId workGenerationalId0) {
        intent0.putExtra("KEY_WORKSPEC_ID", workGenerationalId0.getWorkSpecId());
        intent0.putExtra("KEY_WORKSPEC_GENERATION", workGenerationalId0.getGeneration());
        return intent0;
    }
}

