package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001A\u00020\u000EH\u0007J\u0011\u0010\u000F\u001A\u00020\u000E2\u0006\u0010\b\u001A\u00020\tH\u0082\bR\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/lifecycle/LifecycleController;", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "minState", "Landroidx/lifecycle/Lifecycle$State;", "dispatchQueue", "Landroidx/lifecycle/DispatchQueue;", "parentJob", "Lkotlinx/coroutines/Job;", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;Landroidx/lifecycle/DispatchQueue;Lkotlinx/coroutines/Job;)V", "observer", "Landroidx/lifecycle/LifecycleEventObserver;", "finish", "", "handleDestroy", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class LifecycleController {
    private final DispatchQueue dispatchQueue;
    private final Lifecycle lifecycle;
    private final State minState;
    private final LifecycleEventObserver observer;

    public LifecycleController(Lifecycle lifecycle0, State lifecycle$State0, DispatchQueue dispatchQueue0, Job job0) {
        Intrinsics.checkNotNullParameter(lifecycle0, "lifecycle");
        Intrinsics.checkNotNullParameter(lifecycle$State0, "minState");
        Intrinsics.checkNotNullParameter(dispatchQueue0, "dispatchQueue");
        Intrinsics.checkNotNullParameter(job0, "parentJob");
        super();
        this.lifecycle = lifecycle0;
        this.minState = lifecycle$State0;
        this.dispatchQueue = dispatchQueue0;
        LifecycleController..ExternalSyntheticLambda0 lifecycleController$$ExternalSyntheticLambda00 = (LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) -> {
            Intrinsics.checkNotNullParameter(this, "this$0");
            Intrinsics.checkNotNullParameter(job0, "$parentJob");
            Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "<anonymous parameter 1>");
            if(lifecycleOwner0.getLifecycle().getCurrentState() == State.DESTROYED) {
                DefaultImpls.cancel$default(job0, null, 1, null);
                this.finish();
                return;
            }
            if(lifecycleOwner0.getLifecycle().getCurrentState().compareTo(this.minState) < 0) {
                this.dispatchQueue.pause();
                return;
            }
            this.dispatchQueue.resume();
        };
        this.observer = lifecycleController$$ExternalSyntheticLambda00;
        if(lifecycle0.getCurrentState() == State.DESTROYED) {
            DefaultImpls.cancel$default(job0, null, 1, null);
            this.finish();
            return;
        }
        lifecycle0.addObserver(lifecycleController$$ExternalSyntheticLambda00);
    }

    public final void finish() {
        this.lifecycle.removeObserver(this.observer);
        this.dispatchQueue.finish();
    }

    private final void handleDestroy(Job job0) {
        DefaultImpls.cancel$default(job0, null, 1, null);
        this.finish();
    }

    // 检测为 Lambda 实现
    private static final void observer$lambda$0(LifecycleController lifecycleController0, Job job0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) [...]
}

