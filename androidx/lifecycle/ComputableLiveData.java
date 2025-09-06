package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\b\'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0019\u001A\u00028\u0000H%¢\u0006\u0002\u0010\u001AJ\b\u0010\u001B\u001A\u00020\u001CH\u0016R\u0016\u0010\u0006\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001A\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\u0003\u001A\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u0014\u0010\u000E\u001A\u00020\tX\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u000BR\u0016\u0010\u0010\u001A\u00020\u00118\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0013R\u001C\u0010\u0014\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0015\u0010\u0016R\u0016\u0010\u0017\u001A\u00020\u00118\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0013¨\u0006\u001D"}, d2 = {"Landroidx/lifecycle/ComputableLiveData;", "T", "", "executor", "Ljava/util/concurrent/Executor;", "(Ljava/util/concurrent/Executor;)V", "_liveData", "Landroidx/lifecycle/LiveData;", "computing", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getComputing$lifecycle_livedata_release", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "getExecutor$lifecycle_livedata_release", "()Ljava/util/concurrent/Executor;", "invalid", "getInvalid$lifecycle_livedata_release", "invalidationRunnable", "Ljava/lang/Runnable;", "getInvalidationRunnable$lifecycle_livedata_release$annotations", "()V", "liveData", "getLiveData", "()Landroidx/lifecycle/LiveData;", "refreshRunnable", "getRefreshRunnable$lifecycle_livedata_release$annotations", "compute", "()Ljava/lang/Object;", "invalidate", "", "lifecycle-livedata_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ComputableLiveData {
    private final LiveData _liveData;
    private final AtomicBoolean computing;
    private final Executor executor;
    private final AtomicBoolean invalid;
    public final Runnable invalidationRunnable;
    private final LiveData liveData;
    public final Runnable refreshRunnable;

    public ComputableLiveData() {
        this(null, 1, null);
    }

    public ComputableLiveData(Executor executor0) {
        Intrinsics.checkNotNullParameter(executor0, "executor");
        super();
        this.executor = executor0;
        LiveData liveData0 = new LiveData() {
            @Override  // androidx.lifecycle.LiveData
            protected void onActive() {
                ComputableLiveData.this.getExecutor$lifecycle_livedata_release().execute(ComputableLiveData.this.refreshRunnable);
            }
        };
        this._liveData = liveData0;
        this.liveData = liveData0;
        this.invalid = new AtomicBoolean(true);
        this.computing = new AtomicBoolean(false);
        this.refreshRunnable = () -> {
            Intrinsics.checkNotNullParameter(this, "this$0");
            do {
                int v = 0;
                if(this.computing.compareAndSet(false, true)) {
                    Object object0 = null;
                    int v1 = 0;
                    try {
                        while(this.invalid.compareAndSet(true, false)) {
                            object0 = this.compute();
                            v1 = 1;
                        }
                        if(v1 != 0) {
                            this.getLiveData().postValue(object0);
                        }
                    }
                    finally {
                        this.computing.set(false);
                    }
                    v = v1;
                }
            }
            while(v != 0 && this.invalid.get());
        };
        this.invalidationRunnable = () -> {
            Intrinsics.checkNotNullParameter(this, "this$0");
            boolean z = this.getLiveData().hasActiveObservers();
            if(this.invalid.compareAndSet(false, true) && z) {
                this.executor.execute(this.refreshRunnable);
            }
        };
    }

    public ComputableLiveData(Executor executor0, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            executor0 = ArchTaskExecutor.getIOThreadExecutor();
            Intrinsics.checkNotNullExpressionValue(executor0, "getIOThreadExecutor()");
        }
        this(executor0);
    }

    protected abstract Object compute();

    public final AtomicBoolean getComputing$lifecycle_livedata_release() {
        return this.computing;
    }

    public final Executor getExecutor$lifecycle_livedata_release() {
        return this.executor;
    }

    public final AtomicBoolean getInvalid$lifecycle_livedata_release() {
        return this.invalid;
    }

    public static void getInvalidationRunnable$lifecycle_livedata_release$annotations() {
    }

    public LiveData getLiveData() {
        return this.liveData;
    }

    public static void getRefreshRunnable$lifecycle_livedata_release$annotations() {
    }

    public void invalidate() {
        ArchTaskExecutor.getInstance().executeOnMainThread(this.invalidationRunnable);
    }

    // 检测为 Lambda 实现
    private static final void invalidationRunnable$lambda$1(ComputableLiveData computableLiveData0) [...]

    // 检测为 Lambda 实现
    private static final void refreshRunnable$lambda$0(ComputableLiveData computableLiveData0) [...]
}

