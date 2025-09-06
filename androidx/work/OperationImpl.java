package androidx.work;

import androidx.lifecycle.LiveData;
import com.google.common.util.concurrent.ListenableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\u000E\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016J\u000E\u0010\n\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0016R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000B"}, d2 = {"Landroidx/work/OperationImpl;", "Landroidx/work/Operation;", "state", "Landroidx/lifecycle/LiveData;", "Landroidx/work/Operation$State;", "future", "Lcom/google/common/util/concurrent/ListenableFuture;", "Landroidx/work/Operation$State$SUCCESS;", "(Landroidx/lifecycle/LiveData;Lcom/google/common/util/concurrent/ListenableFuture;)V", "getResult", "getState", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class OperationImpl implements Operation {
    private final ListenableFuture future;
    private final LiveData state;

    public OperationImpl(LiveData liveData0, ListenableFuture listenableFuture0) {
        Intrinsics.checkNotNullParameter(liveData0, "state");
        Intrinsics.checkNotNullParameter(listenableFuture0, "future");
        super();
        this.state = liveData0;
        this.future = listenableFuture0;
    }

    @Override  // androidx.work.Operation
    public ListenableFuture getResult() {
        return this.future;
    }

    @Override  // androidx.work.Operation
    public LiveData getState() {
        return this.state;
    }
}

