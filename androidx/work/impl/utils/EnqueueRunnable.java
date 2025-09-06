package androidx.work.impl.utils;

import android.text.TextUtils;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.WorkInfo.State;
import androidx.work.WorkRequest;
import androidx.work.impl.Schedulers;
import androidx.work.impl.WorkContinuationImpl;
import androidx.work.impl.WorkDatabase;
import androidx.work.impl.WorkManagerImpl;
import androidx.work.impl.model.Dependency;
import androidx.work.impl.model.DependencyDao;
import androidx.work.impl.model.WorkName;
import androidx.work.impl.model.WorkSpec.IdAndState;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecDao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnqueueRunnable {
    private static final String TAG;

    static {
        EnqueueRunnable.TAG = "WM-EnqueueRunnable";
    }

    public static boolean addToDatabase(WorkContinuationImpl workContinuationImpl0) {
        WorkManagerImpl workManagerImpl0 = workContinuationImpl0.getWorkManagerImpl();
        WorkDatabase workDatabase0 = workManagerImpl0.getWorkDatabase();
        workDatabase0.beginTransaction();
        try {
            EnqueueUtilsKt.checkContentUriTriggerWorkerLimits(workDatabase0, workManagerImpl0.getConfiguration(), workContinuationImpl0);
            boolean z = EnqueueRunnable.processContinuation(workContinuationImpl0);
            workDatabase0.setTransactionSuccessful();
            return z;
        }
        finally {
            workDatabase0.endTransaction();
        }
    }

    public static void enqueue(WorkContinuationImpl workContinuationImpl0) {
        if(workContinuationImpl0.hasCycles()) {
            throw new IllegalStateException("WorkContinuation has cycles (" + workContinuationImpl0 + ")");
        }
        if(EnqueueRunnable.addToDatabase(workContinuationImpl0)) {
            EnqueueRunnable.scheduleWorkInBackground(workContinuationImpl0);
        }
    }

    private static boolean enqueueContinuation(WorkContinuationImpl workContinuationImpl0) {
        boolean z = EnqueueRunnable.enqueueWorkWithPrerequisites(workContinuationImpl0.getWorkManagerImpl(), workContinuationImpl0.getWork(), ((String[])WorkContinuationImpl.prerequisitesFor(workContinuationImpl0).toArray(new String[0])), workContinuationImpl0.getName(), workContinuationImpl0.getExistingWorkPolicy());
        workContinuationImpl0.markEnqueued();
        return z;
    }

    private static boolean enqueueWorkWithPrerequisites(WorkManagerImpl workManagerImpl0, List list0, String[] arr_s, String s, ExistingWorkPolicy existingWorkPolicy0) {
        WorkDatabase workDatabase2;
        boolean z4;
        WorkDatabase workDatabase1;
        boolean z2;
        boolean z1;
        int v3;
        long v = workManagerImpl0.getConfiguration().getClock().currentTimeMillis();
        WorkDatabase workDatabase0 = workManagerImpl0.getWorkDatabase();
        boolean z = arr_s != null && arr_s.length > 0;
        if(z) {
            int v1 = arr_s.length;
            v3 = 1;
            z1 = false;
            z2 = false;
            for(int v2 = 0; v2 < v1; ++v2) {
                String s1 = arr_s[v2];
                WorkSpec workSpec0 = workDatabase0.workSpecDao().getWorkSpec(s1);
                if(workSpec0 == null) {
                    Logger.get().error("WM-EnqueueRunnable", "Prerequisite " + s1 + " doesn\'t exist; not enqueuing");
                    return false;
                }
                State workInfo$State0 = workSpec0.state;
                v3 &= (workInfo$State0 == State.SUCCEEDED ? 1 : 0);
                if(workInfo$State0 == State.FAILED) {
                    z2 = true;
                }
                else if(workInfo$State0 == State.CANCELLED) {
                    z1 = true;
                }
            }
        }
        else {
            v3 = 1;
            z1 = false;
            z2 = false;
        }
        boolean z3 = TextUtils.isEmpty(s);
        if(z3 || z) {
            workDatabase1 = workDatabase0;
            z4 = false;
        }
        else {
            List list1 = workDatabase0.workSpecDao().getWorkSpecIdAndStatesForName(s);
            if(list1.isEmpty()) {
                workDatabase1 = workDatabase0;
                z4 = false;
            }
            else if(existingWorkPolicy0 != ExistingWorkPolicy.APPEND && existingWorkPolicy0 != ExistingWorkPolicy.APPEND_OR_REPLACE) {
                if(existingWorkPolicy0 == ExistingWorkPolicy.KEEP) {
                    for(Object object0: list1) {
                        if(((IdAndState)object0).state == State.ENQUEUED || ((IdAndState)object0).state == State.RUNNING) {
                            return false;
                        }
                        if(false) {
                            break;
                        }
                    }
                }
                CancelWorkRunnable.forNameInline(s, workManagerImpl0);
                WorkSpecDao workSpecDao0 = workDatabase0.workSpecDao();
                for(Object object1: list1) {
                    workSpecDao0.delete(((IdAndState)object1).id);
                }
                workDatabase1 = workDatabase0;
                z4 = true;
            }
            else {
                DependencyDao dependencyDao0 = workDatabase0.dependencyDao();
                List list2 = new ArrayList();
                for(Object object2: list1) {
                    IdAndState workSpec$IdAndState0 = (IdAndState)object2;
                    if(dependencyDao0.hasDependents(workSpec$IdAndState0.id)) {
                        workDatabase2 = workDatabase0;
                    }
                    else {
                        workDatabase2 = workDatabase0;
                        int v4 = workSpec$IdAndState0.state == State.SUCCEEDED ? 1 : 0;
                        if(workSpec$IdAndState0.state == State.FAILED) {
                            z2 = true;
                        }
                        else if(workSpec$IdAndState0.state == State.CANCELLED) {
                            z1 = true;
                        }
                        list2.add(workSpec$IdAndState0.id);
                        v3 &= v4;
                    }
                    workDatabase0 = workDatabase2;
                }
                workDatabase1 = workDatabase0;
                if(existingWorkPolicy0 == ExistingWorkPolicy.APPEND_OR_REPLACE && (z1 || z2)) {
                    WorkSpecDao workSpecDao1 = workDatabase1.workSpecDao();
                    for(Object object3: workSpecDao1.getWorkSpecIdAndStatesForName(s)) {
                        workSpecDao1.delete(((IdAndState)object3).id);
                    }
                    list2 = Collections.EMPTY_LIST;
                    z1 = false;
                    z2 = false;
                }
                arr_s = (String[])list2.toArray(arr_s);
                z = arr_s.length > 0;
                z4 = false;
            }
        }
        for(Object object4: list0) {
            WorkRequest workRequest0 = (WorkRequest)object4;
            WorkSpec workSpec1 = workRequest0.getWorkSpec();
            if(!z || v3 != 0) {
                workSpec1.lastEnqueueTime = v;
            }
            else if(z2) {
                workSpec1.state = State.FAILED;
            }
            else if(z1) {
                workSpec1.state = State.CANCELLED;
            }
            else {
                workSpec1.state = State.BLOCKED;
            }
            if(workSpec1.state == State.ENQUEUED) {
                z4 = true;
            }
            workDatabase1.workSpecDao().insertWorkSpec(EnqueueUtilsKt.wrapWorkSpecIfNeeded(workManagerImpl0.getSchedulers(), workSpec1));
            if(z) {
                for(int v5 = 0; v5 < arr_s.length; ++v5) {
                    String s2 = arr_s[v5];
                    Dependency dependency0 = new Dependency(workRequest0.getStringId(), s2);
                    workDatabase1.dependencyDao().insertDependency(dependency0);
                }
            }
            workDatabase1.workTagDao().insertTags(workRequest0.getStringId(), workRequest0.getTags());
            if(!z3) {
                workDatabase1.workNameDao().insert(new WorkName(s, workRequest0.getStringId()));
            }
        }
        return z4;
    }

    private static boolean processContinuation(WorkContinuationImpl workContinuationImpl0) {
        List list0 = workContinuationImpl0.getParents();
        boolean z = false;
        if(list0 != null) {
            for(Object object0: list0) {
                WorkContinuationImpl workContinuationImpl1 = (WorkContinuationImpl)object0;
                if(workContinuationImpl1.isEnqueued()) {
                    Logger.get().warning("WM-EnqueueRunnable", "Already enqueued work ids (" + TextUtils.join(", ", workContinuationImpl1.getIds()) + ")");
                }
                else {
                    z |= EnqueueRunnable.processContinuation(workContinuationImpl1);
                }
            }
        }
        return EnqueueRunnable.enqueueContinuation(workContinuationImpl0) | z;
    }

    public static void scheduleWorkInBackground(WorkContinuationImpl workContinuationImpl0) {
        WorkManagerImpl workManagerImpl0 = workContinuationImpl0.getWorkManagerImpl();
        Schedulers.schedule(workManagerImpl0.getConfiguration(), workManagerImpl0.getWorkDatabase(), workManagerImpl0.getSchedulers());
    }
}

