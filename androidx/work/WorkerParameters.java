package androidx.work;

import android.net.Network;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import kotlin.coroutines.CoroutineContext;

public final class WorkerParameters {
    public static class RuntimeExtras {
        public Network network;
        public List triggeredContentAuthorities;
        public List triggeredContentUris;

        public RuntimeExtras() {
            this.triggeredContentAuthorities = Collections.EMPTY_LIST;
            this.triggeredContentUris = Collections.EMPTY_LIST;
        }
    }

    private Executor mBackgroundExecutor;
    private ForegroundUpdater mForegroundUpdater;
    private int mGeneration;
    private UUID mId;
    private Data mInputData;
    private ProgressUpdater mProgressUpdater;
    private int mRunAttemptCount;
    private RuntimeExtras mRuntimeExtras;
    private Set mTags;
    private TaskExecutor mWorkTaskExecutor;
    private CoroutineContext mWorkerContext;
    private WorkerFactory mWorkerFactory;

    public WorkerParameters(UUID uUID0, Data data0, Collection collection0, RuntimeExtras workerParameters$RuntimeExtras0, int v, int v1, Executor executor0, CoroutineContext coroutineContext0, TaskExecutor taskExecutor0, WorkerFactory workerFactory0, ProgressUpdater progressUpdater0, ForegroundUpdater foregroundUpdater0) {
        this.mId = uUID0;
        this.mInputData = data0;
        this.mTags = new HashSet(collection0);
        this.mRuntimeExtras = workerParameters$RuntimeExtras0;
        this.mRunAttemptCount = v;
        this.mGeneration = v1;
        this.mBackgroundExecutor = executor0;
        this.mWorkerContext = coroutineContext0;
        this.mWorkTaskExecutor = taskExecutor0;
        this.mWorkerFactory = workerFactory0;
        this.mProgressUpdater = progressUpdater0;
        this.mForegroundUpdater = foregroundUpdater0;
    }

    public Executor getBackgroundExecutor() {
        return this.mBackgroundExecutor;
    }

    public ForegroundUpdater getForegroundUpdater() {
        return this.mForegroundUpdater;
    }

    public int getGeneration() {
        return this.mGeneration;
    }

    public UUID getId() {
        return this.mId;
    }

    public Data getInputData() {
        return this.mInputData;
    }

    public Network getNetwork() {
        return this.mRuntimeExtras.network;
    }

    public ProgressUpdater getProgressUpdater() {
        return this.mProgressUpdater;
    }

    public int getRunAttemptCount() {
        return this.mRunAttemptCount;
    }

    public RuntimeExtras getRuntimeExtras() {
        return this.mRuntimeExtras;
    }

    public Set getTags() {
        return this.mTags;
    }

    public TaskExecutor getTaskExecutor() {
        return this.mWorkTaskExecutor;
    }

    public List getTriggeredContentAuthorities() {
        return this.mRuntimeExtras.triggeredContentAuthorities;
    }

    public List getTriggeredContentUris() {
        return this.mRuntimeExtras.triggeredContentUris;
    }

    public CoroutineContext getWorkerContext() {
        return this.mWorkerContext;
    }

    public WorkerFactory getWorkerFactory() {
        return this.mWorkerFactory;
    }
}

