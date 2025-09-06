package androidx.work;

import android.content.Context;
import android.net.Network;
import androidx.concurrent.futures.CallbackToFutureAdapter.Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import androidx.work.impl.utils.taskexecutor.TaskExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class ListenableWorker {
    public static abstract class Result {
        public static final class Failure extends Result {
            private final Data mOutputData;

            public Failure() {
                this(Data.EMPTY);
            }

            public Failure(Data data0) {
                this.mOutputData = data0;
            }

            @Override
            public boolean equals(Object object0) {
                if(this == object0) {
                    return true;
                }
                return object0 == null || this.getClass() != object0.getClass() ? false : this.mOutputData.equals(((Failure)object0).mOutputData);
            }

            @Override  // androidx.work.ListenableWorker$Result
            public Data getOutputData() {
                return this.mOutputData;
            }

            @Override
            public int hashCode() {
                return this.mOutputData.hashCode() + 846803280;
            }

            @Override
            public String toString() {
                return "Failure {mOutputData=" + this.mOutputData + '}';
            }
        }

        public static final class Retry extends Result {
            @Override
            public boolean equals(Object object0) {
                return this == object0 ? true : object0 != null && this.getClass() == object0.getClass();
            }

            @Override  // androidx.work.ListenableWorker$Result
            public Data getOutputData() {
                return Data.EMPTY;
            }

            @Override
            public int hashCode() {
                return 0x18BE74E;
            }

            @Override
            public String toString() {
                return "Retry";
            }
        }

        public static final class Success extends Result {
            private final Data mOutputData;

            public Success() {
                this(Data.EMPTY);
            }

            public Success(Data data0) {
                this.mOutputData = data0;
            }

            @Override
            public boolean equals(Object object0) {
                if(this == object0) {
                    return true;
                }
                return object0 == null || this.getClass() != object0.getClass() ? false : this.mOutputData.equals(((Success)object0).mOutputData);
            }

            @Override  // androidx.work.ListenableWorker$Result
            public Data getOutputData() {
                return this.mOutputData;
            }

            @Override
            public int hashCode() {
                return this.mOutputData.hashCode() - 0x6FDE0E09;
            }

            @Override
            public String toString() {
                return "Success {mOutputData=" + this.mOutputData + '}';
            }
        }

        public static Result failure() {
            return new Failure();
        }

        public static Result failure(Data data0) {
            return new Failure(data0);
        }

        public abstract Data getOutputData();

        public static Result retry() {
            return new Retry();
        }

        public static Result success() {
            return new Success();
        }

        public static Result success(Data data0) {
            return new Success(data0);
        }
    }

    private Context mAppContext;
    private final AtomicInteger mStopReason;
    private boolean mUsed;
    private WorkerParameters mWorkerParams;

    public ListenableWorker(Context context0, WorkerParameters workerParameters0) {
        this.mStopReason = new AtomicInteger(0xFFFFFF00);
        if(context0 == null) {
            throw new IllegalArgumentException("Application Context is null");
        }
        if(workerParameters0 == null) {
            throw new IllegalArgumentException("WorkerParameters is null");
        }
        this.mAppContext = context0;
        this.mWorkerParams = workerParameters0;
    }

    public final Context getApplicationContext() {
        return this.mAppContext;
    }

    public Executor getBackgroundExecutor() {
        return this.mWorkerParams.getBackgroundExecutor();
    }

    public ListenableFuture getForegroundInfoAsync() {
        return CallbackToFutureAdapter.getFuture(new ListenableWorker..ExternalSyntheticLambda0());
    }

    public final UUID getId() {
        return this.mWorkerParams.getId();
    }

    public final Data getInputData() {
        return this.mWorkerParams.getInputData();
    }

    public final Network getNetwork() {
        return this.mWorkerParams.getNetwork();
    }

    public final int getRunAttemptCount() {
        return this.mWorkerParams.getRunAttemptCount();
    }

    public final int getStopReason() {
        return this.mStopReason.get();
    }

    public final Set getTags() {
        return this.mWorkerParams.getTags();
    }

    public TaskExecutor getTaskExecutor() {
        return this.mWorkerParams.getTaskExecutor();
    }

    public final List getTriggeredContentAuthorities() {
        return this.mWorkerParams.getTriggeredContentAuthorities();
    }

    public final List getTriggeredContentUris() {
        return this.mWorkerParams.getTriggeredContentUris();
    }

    public WorkerFactory getWorkerFactory() {
        return this.mWorkerParams.getWorkerFactory();
    }

    public final boolean isStopped() {
        return this.mStopReason.get() != 0xFFFFFF00;
    }

    public final boolean isUsed() {
        return this.mUsed;
    }

    static Object lambda$getForegroundInfoAsync$0(Completer callbackToFutureAdapter$Completer0) throws Exception {
        callbackToFutureAdapter$Completer0.setException(new IllegalStateException("Expedited WorkRequests require a ListenableWorker to provide an implementation for`getForegroundInfoAsync()`"));
        return "default failing getForegroundInfoAsync";
    }

    public void onStopped() {
    }

    public final ListenableFuture setForegroundAsync(ForegroundInfo foregroundInfo0) {
        return this.mWorkerParams.getForegroundUpdater().setForegroundAsync(this.getApplicationContext(), this.getId(), foregroundInfo0);
    }

    public ListenableFuture setProgressAsync(Data data0) {
        return this.mWorkerParams.getProgressUpdater().updateProgress(this.getApplicationContext(), this.getId(), data0);
    }

    public final void setUsed() {
        this.mUsed = true;
    }

    public abstract ListenableFuture startWork();

    public final void stop(int v) {
        if(this.mStopReason.compareAndSet(0xFFFFFF00, v)) {
            this.onStopped();
        }
    }
}

