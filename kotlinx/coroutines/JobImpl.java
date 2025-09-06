package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.Unit;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000\b\u0011\u0018\u00002\u00020\u00012\u00020\u0002B\u000F\u0012\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001A\u00020\u0007H\u0016J\u0010\u0010\r\u001A\u00020\u00072\u0006\u0010\u000E\u001A\u00020\u000FH\u0016J\b\u0010\u0006\u001A\u00020\u0007H\u0003R\u0014\u0010\u0006\u001A\u00020\u0007X\u0090\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0014\u0010\n\u001A\u00020\u00078PX\u0090\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\t¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/JobImpl;", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/CompletableJob;", "parent", "Lkotlinx/coroutines/Job;", "(Lkotlinx/coroutines/Job;)V", "handlesException", "", "getHandlesException$kotlinx_coroutines_core", "()Z", "onCancelComplete", "getOnCancelComplete$kotlinx_coroutines_core", "complete", "completeExceptionally", "exception", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class JobImpl extends JobSupport implements CompletableJob {
    private final boolean handlesException;

    public JobImpl(Job job0) {
        super(true);
        this.initParentJob(job0);
        this.handlesException = this.handlesException();
    }

    @Override  // kotlinx.coroutines.CompletableJob
    public boolean complete() {
        return this.makeCompleting$kotlinx_coroutines_core(Unit.INSTANCE);
    }

    @Override  // kotlinx.coroutines.CompletableJob
    public boolean completeExceptionally(Throwable throwable0) {
        return this.makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(throwable0, false, 2, null));
    }

    @Override  // kotlinx.coroutines.JobSupport
    public boolean getHandlesException$kotlinx_coroutines_core() {
        return this.handlesException;
    }

    @Override  // kotlinx.coroutines.JobSupport
    public boolean getOnCancelComplete$kotlinx_coroutines_core() {
        return true;
    }

    private final boolean handlesException() {
        ChildHandle childHandle0 = this.getParentHandle$kotlinx_coroutines_core();
        ChildHandleNode childHandleNode0 = childHandle0 instanceof ChildHandleNode ? ((ChildHandleNode)childHandle0) : null;
        if(childHandleNode0 != null) {
            JobSupport jobSupport0 = childHandleNode0.getJob();
            if(jobSupport0 != null) {
                while(true) {
                    if(jobSupport0.getHandlesException$kotlinx_coroutines_core()) {
                        return true;
                    }
                    ChildHandle childHandle1 = jobSupport0.getParentHandle$kotlinx_coroutines_core();
                    ChildHandleNode childHandleNode1 = childHandle1 instanceof ChildHandleNode ? ((ChildHandleNode)childHandle1) : null;
                    if(childHandleNode1 == null) {
                        break;
                    }
                    jobSupport0 = childHandleNode1.getJob();
                    if(jobSupport0 == null) {
                        break;
                    }
                }
            }
        }
        return false;
    }
}

