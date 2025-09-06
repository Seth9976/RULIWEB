package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.ExceptionsKt;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\u0006\u0010\b\u001A\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000B\u001A\u00020\t2\u0006\u0010\f\u001A\u00020\rH\u0014J\u0012\u0010\u000E\u001A\u00020\u000F2\b\u0010\u0010\u001A\u0004\u0018\u00010\rH\u0014¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/channels/ActorCoroutine;", "E", "Lkotlinx/coroutines/channels/ChannelCoroutine;", "Lkotlinx/coroutines/channels/ActorScope;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "channel", "Lkotlinx/coroutines/channels/Channel;", "active", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;Z)V", "handleJobException", "exception", "", "onCancelling", "", "cause", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
class ActorCoroutine extends ChannelCoroutine implements ActorScope {
    public ActorCoroutine(CoroutineContext coroutineContext0, Channel channel0, boolean z) {
        super(coroutineContext0, channel0, false, z);
        this.initParentJob(((Job)coroutineContext0.get(Job.Key)));
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected boolean handleJobException(Throwable throwable0) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), throwable0);
        return true;
    }

    @Override  // kotlinx.coroutines.JobSupport
    protected void onCancelling(Throwable throwable0) {
        Channel channel0 = this.get_channel();
        CancellationException cancellationException0 = null;
        if(throwable0 != null) {
            if(throwable0 instanceof CancellationException) {
                cancellationException0 = (CancellationException)throwable0;
            }
            if(cancellationException0 == null) {
                cancellationException0 = ExceptionsKt.CancellationException((DebugStringsKt.getClassSimpleName(this) + " was cancelled"), throwable0);
            }
        }
        channel0.cancel(cancellationException0);
    }
}

