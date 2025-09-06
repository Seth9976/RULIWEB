package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
@DebugMetadata(c = "kotlinx.coroutines.channels.ReceiveChannel$DefaultImpls", f = "Channel.kt", i = {}, l = {372}, m = "receiveOrNull", n = {}, s = {})
final class ReceiveChannel.receiveOrNull.1 extends ContinuationImpl {
    int label;
    Object result;

    ReceiveChannel.receiveOrNull.1(Continuation continuation0) {
        super(continuation0);
    }

    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object object0) {
        this.result = object0;
        this.label |= 0x80000000;
        return DefaultImpls.receiveOrNull(null, this);
    }
}

