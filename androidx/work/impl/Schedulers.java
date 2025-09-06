package androidx.work.impl;

import android.content.Context;
import android.os.Build.VERSION;
import androidx.work.Clock;
import androidx.work.Configuration;
import androidx.work.Logger;
import androidx.work.impl.background.systemalarm.SystemAlarmScheduler;
import androidx.work.impl.background.systemalarm.SystemAlarmService;
import androidx.work.impl.background.systemjob.SystemJobScheduler;
import androidx.work.impl.background.systemjob.SystemJobService;
import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import androidx.work.impl.utils.PackageManagerHelper;
import java.util.List;
import java.util.concurrent.Executor;

public class Schedulers {
    public static final String GCM_SCHEDULER = "androidx.work.impl.background.gcm.GcmScheduler";
    private static final String TAG;

    static {
        Schedulers.TAG = "WM-Schedulers";
    }

    static Scheduler createBestAvailableBackgroundScheduler(Context context0, WorkDatabase workDatabase0, Configuration configuration0) {
        if(Build.VERSION.SDK_INT >= 23) {
            Scheduler scheduler0 = new SystemJobScheduler(context0, workDatabase0, configuration0);
            PackageManagerHelper.setComponentEnabled(context0, SystemJobService.class, true);
            Logger.get().debug("WM-Schedulers", "Created SystemJobScheduler and enabled SystemJobService");
            return scheduler0;
        }
        Scheduler scheduler1 = Schedulers.tryCreateGcmBasedScheduler(context0, configuration0.getClock());
        if(scheduler1 == null) {
            scheduler1 = new SystemAlarmScheduler(context0);
            PackageManagerHelper.setComponentEnabled(context0, SystemAlarmService.class, true);
            Logger.get().debug("WM-Schedulers", "Created SystemAlarmScheduler");
        }
        return scheduler1;
    }

    // 检测为 Lambda 实现
    static void lambda$registerRescheduling$0(List list0, WorkGenerationalId workGenerationalId0, Configuration configuration0, WorkDatabase workDatabase0) [...]

    // 检测为 Lambda 实现
    static void lambda$registerRescheduling$1(Executor executor0, List list0, Configuration configuration0, WorkDatabase workDatabase0, WorkGenerationalId workGenerationalId0, boolean z) [...]

    private static void markScheduled(WorkSpecDao workSpecDao0, Clock clock0, List list0) {
        if(list0.size() > 0) {
            long v = clock0.currentTimeMillis();
            for(Object object0: list0) {
                workSpecDao0.markWorkSpecScheduled(((WorkSpec)object0).id, v);
            }
        }
    }

    public static void registerRescheduling(List list0, Processor processor0, Executor executor0, WorkDatabase workDatabase0, Configuration configuration0) {
        processor0.addExecutionListener((WorkGenerationalId workGenerationalId0, boolean z) -> executor0.execute(() -> {
            for(Object object0: list0) {
                ((Scheduler)object0).cancel(workGenerationalId0.getWorkSpecId());
            }
            Schedulers.schedule(configuration0, workDatabase0, list0);
        }));
    }

    public static void schedule(Configuration configuration0, WorkDatabase workDatabase0, List list0) {
        List list3;
        List list2;
        List list1;
        if(list0 != null && list0.size() != 0) {
            WorkSpecDao workSpecDao0 = workDatabase0.workSpecDao();
            workDatabase0.beginTransaction();
            try {
                if(Build.VERSION.SDK_INT >= 24) {
                    list1 = workSpecDao0.getEligibleWorkForSchedulingWithContentUris();
                    Schedulers.markScheduled(workSpecDao0, configuration0.getClock(), list1);
                }
                else {
                    list1 = null;
                }
                list2 = workSpecDao0.getEligibleWorkForScheduling(configuration0.getMaxSchedulerLimit());
                Schedulers.markScheduled(workSpecDao0, configuration0.getClock(), list2);
                if(list1 != null) {
                    list2.addAll(list1);
                }
                list3 = workSpecDao0.getAllEligibleWorkSpecsForScheduling(200);
                workDatabase0.setTransactionSuccessful();
            }
            finally {
                workDatabase0.endTransaction();
            }
            if(list2.size() > 0) {
                WorkSpec[] arr_workSpec = (WorkSpec[])list2.toArray(new WorkSpec[list2.size()]);
                for(Object object0: list0) {
                    Scheduler scheduler0 = (Scheduler)object0;
                    if(scheduler0.hasLimitedSchedulingSlots()) {
                        scheduler0.schedule(arr_workSpec);
                    }
                }
            }
            if(list3.size() > 0) {
                WorkSpec[] arr_workSpec1 = (WorkSpec[])list3.toArray(new WorkSpec[list3.size()]);
                for(Object object1: list0) {
                    Scheduler scheduler1 = (Scheduler)object1;
                    if(!scheduler1.hasLimitedSchedulingSlots()) {
                        scheduler1.schedule(arr_workSpec1);
                    }
                }
            }
        }
    }

    private static Scheduler tryCreateGcmBasedScheduler(Context context0, Clock clock0) {
        try {
            Scheduler scheduler0 = (Scheduler)Class.forName("androidx.work.impl.background.gcm.GcmScheduler").getConstructor(Context.class, Clock.class).newInstance(context0, clock0);
            Logger.get().debug("WM-Schedulers", "Created androidx.work.impl.background.gcm.GcmScheduler");
            return scheduler0;
        }
        catch(Throwable throwable0) {
            Logger.get().debug("WM-Schedulers", "Unable to create GCM Scheduler", throwable0);
            return null;
        }
    }
}

