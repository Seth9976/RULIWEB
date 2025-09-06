package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0012\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u0005B#\u0012\u0006\u0010\u0006\u001A\u00020\u0007\u0012\f\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0006\u0010\t\u001A\u00020\n\u00A2\u0006\u0002\u0010\u000BJ\u0012\u0010\u0019\u001A\u00020\n2\b\u0010\u001A\u001A\u0004\u0018\u00010\u001BH\u0007J\u0016\u0010\u0019\u001A\u00020\u00032\u000E\u0010\u001A\u001A\n\u0018\u00010\u001Cj\u0004\u0018\u0001`\u001DJ\u0010\u0010\u001E\u001A\u00020\u00032\u0006\u0010\u001A\u001A\u00020\u001BH\u0016J\u0012\u0010\u001F\u001A\u00020\n2\b\u0010\u001A\u001A\u0004\u0018\u00010\u001BH\u0016J.\u0010 \u001A\u00020\u00032#\u0010!\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010\u001B\u00A2\u0006\f\b#\u0012\b\b$\u0012\u0004\b\b(\u001A\u0012\u0004\u0012\u00020\u00030\"H\u0096\u0001J\u0016\u0010%\u001A\u00020\n2\u0006\u0010&\u001A\u00028\u0000H\u0097\u0001\u00A2\u0006\u0002\u0010\'J\u0018\u0010(\u001A\u00020\u00032\u0006\u0010\u001A\u001A\u00020\u001B2\u0006\u0010)\u001A\u00020\nH\u0014J\u0015\u0010*\u001A\u00020\u00032\u0006\u0010+\u001A\u00020\u0003H\u0014\u00A2\u0006\u0002\u0010,J\u000F\u0010-\u001A\b\u0012\u0004\u0012\u00028\u00000.H\u0096\u0001J\u0019\u0010/\u001A\u00020\u00032\u0006\u0010&\u001A\u00028\u0000H\u0096A\u00F8\u0001\u0000\u00A2\u0006\u0002\u00100J\'\u00101\u001A\b\u0012\u0004\u0012\u00020\u0003022\u0006\u0010&\u001A\u00028\u0000H\u0096\u0001\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b3\u00104R\u001A\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0084\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u001A\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u000F8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001A\u00020\n8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001A\u00020\n8\u0016X\u0097\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0014\u0010\u0013R$\u0010\u0015\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000F0\u0016X\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0018\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00A1\u001E0\u0001\u00A8\u00065"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastCoroutine;", "E", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/channels/ProducerScope;", "Lkotlinx/coroutines/channels/BroadcastChannel;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "_channel", "active", "", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/BroadcastChannel;Z)V", "get_channel", "()Lkotlinx/coroutines/channels/BroadcastChannel;", "channel", "Lkotlinx/coroutines/channels/SendChannel;", "getChannel", "()Lkotlinx/coroutines/channels/SendChannel;", "isActive", "()Z", "isClosedForSend", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "offer", "element", "(Ljava/lang/Object;)Z", "onCancelled", "handled", "onCompleted", "value", "(Lkotlin/Unit;)V", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
class BroadcastCoroutine extends AbstractCoroutine implements BroadcastChannel, ProducerScope {
    private final BroadcastChannel _channel;

    public BroadcastCoroutine(CoroutineContext coroutineContext0, BroadcastChannel broadcastChannel0, boolean z) {
        super(coroutineContext0, false, z);
        this._channel = broadcastChannel0;
        this.initParentJob(((Job)coroutineContext0.get(Job.Key)));
    }

    @Override  // kotlinx.coroutines.channels.BroadcastChannel, kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public final void cancel(CancellationException cancellationException0) {
        if(cancellationException0 == null) {
            cancellationException0 = new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this);
        }
        this.cancelInternal(cancellationException0);
    }

    @Override  // kotlinx.coroutines.channels.BroadcastChannel, kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public final boolean cancel(Throwable throwable0) {
        if(throwable0 == null) {
            throwable0 = new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this);
        }
        this.cancelInternal(throwable0);
        return true;
    }

    @Override  // kotlinx.coroutines.JobSupport
    public void cancelInternal(Throwable throwable0) {
        CancellationException cancellationException0 = JobSupport.toCancellationException$default(this, throwable0, null, 1, null);
        this._channel.cancel(cancellationException0);
        this.cancelCoroutine(cancellationException0);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable throwable0) {
        boolean z = this._channel.close(throwable0);
        this.start();
        return z;
    }

    @Override  // kotlinx.coroutines.channels.ProducerScope
    public SendChannel getChannel() {
        return this;
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public SelectClause2 getOnSend() {
        return this._channel.getOnSend();
    }

    protected final BroadcastChannel get_channel() {
        return this._channel;
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1 function10) {
        this._channel.invokeOnClose(function10);
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    public boolean isActive() {
        return super.isActive();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'trySend\' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(Object object0) {
        return this._channel.offer(object0);
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    protected void onCancelled(Throwable throwable0, boolean z) {
        if(!this._channel.close(throwable0) && !z) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), throwable0);
        }
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    public void onCompleted(Object object0) {
        this.onCompleted(((Unit)object0));
    }

    protected void onCompleted(Unit unit0) {
        DefaultImpls.close$default(this._channel, null, 1, null);
    }

    @Override  // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel openSubscription() {
        return this._channel.openSubscription();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public Object send(Object object0, Continuation continuation0) {
        return this._channel.send(object0, continuation0);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public Object trySend-JP2dKIU(Object object0) {
        return this._channel.trySend-JP2dKIU(object0);
    }
}

