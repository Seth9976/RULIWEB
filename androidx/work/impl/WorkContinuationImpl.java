package androidx.work.impl;

import android.text.TextUtils;
import androidx.lifecycle.LiveData;
import androidx.work.ArrayCreatingInputMerger;
import androidx.work.ExistingWorkPolicy;
import androidx.work.Logger;
import androidx.work.OneTimeWorkRequest.Builder;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Operation;
import androidx.work.OperationKt;
import androidx.work.WorkContinuation;
import androidx.work.WorkRequest;
import androidx.work.impl.utils.EnqueueRunnable;
import androidx.work.impl.utils.StatusRunnable;
import androidx.work.impl.workers.CombineContinuationsWorker;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.Unit;

public class WorkContinuationImpl extends WorkContinuation {
    private static final String TAG;
    private final List mAllIds;
    private boolean mEnqueued;
    private final ExistingWorkPolicy mExistingWorkPolicy;
    private final List mIds;
    private final String mName;
    private Operation mOperation;
    private final List mParents;
    private final List mWork;
    private final WorkManagerImpl mWorkManagerImpl;

    static {
        WorkContinuationImpl.TAG = "WM-WorkContinuationImpl";
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl0, String s, ExistingWorkPolicy existingWorkPolicy0, List list0) {
        this(workManagerImpl0, s, existingWorkPolicy0, list0, null);
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl0, String s, ExistingWorkPolicy existingWorkPolicy0, List list0, List list1) {
        this.mWorkManagerImpl = workManagerImpl0;
        this.mName = s;
        this.mExistingWorkPolicy = existingWorkPolicy0;
        this.mWork = list0;
        this.mParents = list1;
        this.mIds = new ArrayList(list0.size());
        this.mAllIds = new ArrayList();
        if(list1 != null) {
            for(Object object0: list1) {
                this.mAllIds.addAll(((WorkContinuationImpl)object0).mAllIds);
            }
        }
        for(int v = 0; v < list0.size(); ++v) {
            if(existingWorkPolicy0 == ExistingWorkPolicy.REPLACE && ((WorkRequest)list0.get(v)).getWorkSpec().getNextScheduleTimeOverride() != 0x7FFFFFFFFFFFFFFFL) {
                throw new IllegalArgumentException("Next Schedule Time Override must be used with ExistingPeriodicWorkPolicyUPDATE (preferably) or KEEP");
            }
            String s1 = ((WorkRequest)list0.get(v)).getStringId();
            this.mIds.add(s1);
            this.mAllIds.add(s1);
        }
    }

    public WorkContinuationImpl(WorkManagerImpl workManagerImpl0, List list0) {
        this(workManagerImpl0, null, ExistingWorkPolicy.KEEP, list0, null);
    }

    @Override  // androidx.work.WorkContinuation
    protected WorkContinuation combineInternal(List list0) {
        OneTimeWorkRequest oneTimeWorkRequest0 = (OneTimeWorkRequest)new Builder(CombineContinuationsWorker.class).setInputMerger(ArrayCreatingInputMerger.class).build();
        ArrayList arrayList0 = new ArrayList(list0.size());
        for(Object object0: list0) {
            arrayList0.add(((WorkContinuationImpl)(((WorkContinuation)object0))));
        }
        List list1 = Collections.singletonList(oneTimeWorkRequest0);
        return new WorkContinuationImpl(this.mWorkManagerImpl, null, ExistingWorkPolicy.KEEP, list1, arrayList0);
    }

    @Override  // androidx.work.WorkContinuation
    public Operation enqueue() {
        if(!this.mEnqueued) {
            this.mOperation = OperationKt.launchOperation(this.mWorkManagerImpl.getConfiguration().getTracer(), "EnqueueRunnable_" + this.getExistingWorkPolicy().name(), this.mWorkManagerImpl.getWorkTaskExecutor().getSerialTaskExecutor(), () -> {
                EnqueueRunnable.enqueue(this);
                return Unit.INSTANCE;
            });
            return this.mOperation;
        }
        Logger.get().warning("WM-WorkContinuationImpl", "Already enqueued work ids (" + TextUtils.join(", ", this.mIds) + ")");
        return this.mOperation;
    }

    public List getAllIds() {
        return this.mAllIds;
    }

    public ExistingWorkPolicy getExistingWorkPolicy() {
        return this.mExistingWorkPolicy;
    }

    public List getIds() {
        return this.mIds;
    }

    public String getName() {
        return this.mName;
    }

    public List getParents() {
        return this.mParents;
    }

    public List getWork() {
        return this.mWork;
    }

    @Override  // androidx.work.WorkContinuation
    public ListenableFuture getWorkInfos() {
        return StatusRunnable.forStringIds(this.mWorkManagerImpl.getWorkDatabase(), this.mWorkManagerImpl.getWorkTaskExecutor(), this.mAllIds);
    }

    @Override  // androidx.work.WorkContinuation
    public LiveData getWorkInfosLiveData() {
        return this.mWorkManagerImpl.getWorkInfosById(this.mAllIds);
    }

    public WorkManagerImpl getWorkManagerImpl() {
        return this.mWorkManagerImpl;
    }

    private static boolean hasCycles(WorkContinuationImpl workContinuationImpl0, Set set0) {
        set0.addAll(workContinuationImpl0.getIds());
        Set set1 = WorkContinuationImpl.prerequisitesFor(workContinuationImpl0);
        for(Object object0: set0) {
            if(set1.contains(((String)object0))) {
                return true;
            }
            if(false) {
                break;
            }
        }
        List list0 = workContinuationImpl0.getParents();
        if(list0 != null && !list0.isEmpty()) {
            for(Object object1: list0) {
                if(WorkContinuationImpl.hasCycles(((WorkContinuationImpl)object1), set0)) {
                    return true;
                }
                if(false) {
                    break;
                }
            }
        }
        set0.removeAll(workContinuationImpl0.getIds());
        return false;
    }

    public boolean hasCycles() {
        return WorkContinuationImpl.hasCycles(this, new HashSet());
    }

    public boolean isEnqueued() {
        return this.mEnqueued;
    }

    // 检测为 Lambda 实现
    Unit lambda$enqueue$0$androidx-work-impl-WorkContinuationImpl() [...]

    public void markEnqueued() {
        this.mEnqueued = true;
    }

    public static Set prerequisitesFor(WorkContinuationImpl workContinuationImpl0) {
        Set set0 = new HashSet();
        List list0 = workContinuationImpl0.getParents();
        if(list0 != null && !list0.isEmpty()) {
            for(Object object0: list0) {
                set0.addAll(((WorkContinuationImpl)object0).getIds());
            }
        }
        return set0;
    }

    @Override  // androidx.work.WorkContinuation
    public WorkContinuation then(List list0) {
        if(list0.isEmpty()) {
            return this;
        }
        List list1 = Collections.singletonList(this);
        return new WorkContinuationImpl(this.mWorkManagerImpl, this.mName, ExistingWorkPolicy.KEEP, list0, list1);
    }
}

