package kotlinx.coroutines.channels;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.AbstractCoroutine;
import kotlinx.coroutines.JobCancellationException;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.selects.SelectClause1;
import kotlinx.coroutines.selects.SelectClause2;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u000B\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0004B+\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u0012\f\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0010\b\u001A\u00020\t\u0012\u0006\u0010\n\u001A\u00020\t\u00A2\u0006\u0002\u0010\u000BJ\b\u0010\"\u001A\u00020\u0003H\u0017J\u0012\u0010\"\u001A\u00020\t2\b\u0010#\u001A\u0004\u0018\u00010$H\u0007J\u0016\u0010\"\u001A\u00020\u00032\u000E\u0010#\u001A\n\u0018\u00010%j\u0004\u0018\u0001`&J\u0010\u0010\'\u001A\u00020\u00032\u0006\u0010#\u001A\u00020$H\u0016J\u0013\u0010(\u001A\u00020\t2\b\u0010#\u001A\u0004\u0018\u00010$H\u0096\u0001J.\u0010)\u001A\u00020\u00032#\u0010*\u001A\u001F\u0012\u0015\u0012\u0013\u0018\u00010$\u00A2\u0006\f\b,\u0012\b\b-\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u00030+H\u0096\u0001J\u000F\u0010.\u001A\b\u0012\u0004\u0012\u00028\u00000/H\u0096\u0003J\u0016\u00100\u001A\u00020\t2\u0006\u00101\u001A\u00028\u0000H\u0097\u0001\u00A2\u0006\u0002\u00102J\u0010\u00103\u001A\u0004\u0018\u00018\u0000H\u0097\u0001\u00A2\u0006\u0002\u00104J\u0011\u00105\u001A\u00028\u0000H\u0096A\u00F8\u0001\u0000\u00A2\u0006\u0002\u00106J\"\u00107\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096A\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00F8\u0001\u0000\u00A2\u0006\u0004\b8\u00106J\u0013\u00109\u001A\u0004\u0018\u00018\u0000H\u0097A\u00F8\u0001\u0000\u00A2\u0006\u0002\u00106J\u0019\u0010:\u001A\u00020\u00032\u0006\u00101\u001A\u00028\u0000H\u0096A\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010;J\u001F\u0010<\u001A\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0096\u0001\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b=\u00104J\'\u0010>\u001A\b\u0012\u0004\u0012\u00020\u00030\u00192\u0006\u00101\u001A\u00028\u0000H\u0096\u0001\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b?\u0010@R\u001A\u0010\u0007\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0084\u0004\u00A2\u0006\b\n\u0000\u001A\u0004\b\f\u0010\rR\u0017\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u00048F\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\rR\u0014\u0010\u0010\u001A\u00020\t8\u0016X\u0097\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001A\u00020\t8\u0016X\u0097\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0013\u001A\u00020\t8\u0016X\u0097\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0011R\u0018\u0010\u0014\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015X\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017R!\u0010\u0018\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00190\u0015X\u0096\u0005\u00F8\u0001\u0000\u00A2\u0006\u0006\u001A\u0004\b\u001A\u0010\u0017R\u001C\u0010\u001B\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u00158VX\u0097\u0005\u00A2\u0006\u0006\u001A\u0004\b\u001C\u0010\u0017R$\u0010\u001D\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001F0\u001EX\u0096\u0005\u00A2\u0006\u0006\u001A\u0004\b \u0010!\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00A1\u001E0\u0001\u00A8\u0006A"}, d2 = {"Lkotlinx/coroutines/channels/ChannelCoroutine;", "E", "Lkotlinx/coroutines/AbstractCoroutine;", "", "Lkotlinx/coroutines/channels/Channel;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "_channel", "initParentJob", "", "active", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;ZZ)V", "get_channel", "()Lkotlinx/coroutines/channels/Channel;", "channel", "getChannel", "isClosedForReceive", "()Z", "isClosedForSend", "isEmpty", "onReceive", "Lkotlinx/coroutines/selects/SelectClause1;", "getOnReceive", "()Lkotlinx/coroutines/selects/SelectClause1;", "onReceiveCatching", "Lkotlinx/coroutines/channels/ChannelResult;", "getOnReceiveCatching", "onReceiveOrNull", "getOnReceiveOrNull", "onSend", "Lkotlinx/coroutines/selects/SelectClause2;", "Lkotlinx/coroutines/channels/SendChannel;", "getOnSend", "()Lkotlinx/coroutines/selects/SelectClause2;", "cancel", "cause", "", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/CancellationException;", "cancelInternal", "close", "invokeOnClose", "handler", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "iterator", "Lkotlinx/coroutines/channels/ChannelIterator;", "offer", "element", "(Ljava/lang/Object;)Z", "poll", "()Ljava/lang/Object;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "receiveCatching", "receiveCatching-JP2dKIU", "receiveOrNull", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryReceive", "tryReceive-PtdJZtk", "trySend", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class ChannelCoroutine extends AbstractCoroutine implements Channel {
    private final Channel _channel;

    public ChannelCoroutine(CoroutineContext coroutineContext0, Channel channel0, boolean z, boolean z1) {
        super(coroutineContext0, z, z1);
        this._channel = channel0;
    }

    @Override  // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job, kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public void cancel() {
        this.cancelInternal(new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this));
    }

    @Override  // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job, kotlinx.coroutines.channels.ReceiveChannel
    public final void cancel(CancellationException cancellationException0) {
        if(this.isCancelled()) {
            return;
        }
        if(cancellationException0 == null) {
            cancellationException0 = new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this);
        }
        this.cancelInternal(cancellationException0);
    }

    @Override  // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job, kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.2.0, binary compatibility with versions <= 1.1.x")
    public final boolean cancel(Throwable throwable0) {
        this.cancelInternal(new JobCancellationException(JobSupport.access$cancellationExceptionMessage(this), null, this));
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
        return this._channel.close(throwable0);
    }

    public final Channel getChannel() {
        return this;
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1 getOnReceive() {
        return this._channel.getOnReceive();
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1 getOnReceiveCatching() {
        return this._channel.getOnReceiveCatching();
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public SelectClause1 getOnReceiveOrNull() {
        return this._channel.getOnReceiveOrNull();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public SelectClause2 getOnSend() {
        return this._channel.getOnSend();
    }

    protected final Channel get_channel() {
        return this._channel;
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public void invokeOnClose(Function1 function10) {
        this._channel.invokeOnClose(function10);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isClosedForReceive() {
        return this._channel.isClosedForReceive();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        return this._channel.isClosedForSend();
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public boolean isEmpty() {
        return this._channel.isEmpty();
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public ChannelIterator iterator() {
        return this._channel.iterator();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'trySend\' method", replaceWith = @ReplaceWith(expression = "trySend(element).isSuccess", imports = {}))
    public boolean offer(Object object0) {
        return this._channel.offer(object0);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of \'tryReceive\'. Please note that the provided replacement does not rethrow channel\'s close cause as \'poll\' did, for the precise replacement please refer to the \'poll\' documentation", replaceWith = @ReplaceWith(expression = "tryReceive().getOrNull()", imports = {}))
    public Object poll() {
        return this._channel.poll();
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public Object receive(Continuation continuation0) {
        return this._channel.receive(continuation0);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public Object receiveCatching-JP2dKIU(Continuation continuation0) {
        return this._channel.receiveCatching-JP2dKIU(continuation0);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in favor of \'receiveCatching\'. Please note that the provided replacement does not rethrow channel\'s close cause as \'receiveOrNull\' did, for the detailed replacement please refer to the \'receiveOrNull\' documentation", replaceWith = @ReplaceWith(expression = "receiveCatching().getOrNull()", imports = {}))
    public Object receiveOrNull(Continuation continuation0) {
        return this._channel.receiveOrNull(continuation0);
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public Object send(Object object0, Continuation continuation0) {
        return this._channel.send(object0, continuation0);
    }

    @Override  // kotlinx.coroutines.channels.ReceiveChannel
    public Object tryReceive-PtdJZtk() {
        return this._channel.tryReceive-PtdJZtk();
    }

    @Override  // kotlinx.coroutines.channels.SendChannel
    public Object trySend-JP2dKIU(Object object0) {
        return this._channel.trySend-JP2dKIU(object0);
    }
}

