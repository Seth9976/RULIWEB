package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineExceptionHandlerKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001B\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\f\u0010\u0006\u001A\b\u0012\u0004\u0012\u00028\u00000\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\nH\u0014J\u0015\u0010\u0011\u001A\u00020\r2\u0006\u0010\u0012\u001A\u00020\rH\u0014¢\u0006\u0002\u0010\u0013R\u0014\u0010\t\u001A\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\u000B¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/channels/ProducerCoroutine;", "E", "Lkotlinx/coroutines/channels/ChannelCoroutine;", "Lkotlinx/coroutines/channels/ProducerScope;", "parentContext", "Lkotlin/coroutines/CoroutineContext;", "channel", "Lkotlinx/coroutines/channels/Channel;", "(Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/channels/Channel;)V", "isActive", "", "()Z", "onCancelled", "", "cause", "", "handled", "onCompleted", "value", "(Lkotlin/Unit;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class ProducerCoroutine extends ChannelCoroutine implements ProducerScope {
    public ProducerCoroutine(CoroutineContext coroutineContext0, Channel channel0) {
        super(coroutineContext0, channel0, true, true);
    }

    @Override  // kotlinx.coroutines.channels.ProducerScope
    public SendChannel getChannel() {
        return this.getChannel();
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    public boolean isActive() {
        return super.isActive();
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    protected void onCancelled(Throwable throwable0, boolean z) {
        if(!this.get_channel().close(throwable0) && !z) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.getContext(), throwable0);
        }
    }

    @Override  // kotlinx.coroutines.AbstractCoroutine
    public void onCompleted(Object object0) {
        this.onCompleted(((Unit)object0));
    }

    protected void onCompleted(Unit unit0) {
        DefaultImpls.close$default(this.get_channel(), null, 1, null);
    }
}

