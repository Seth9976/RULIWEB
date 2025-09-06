package kotlinx.coroutines.channels;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0005\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u0011\u0010\u0003\u001A\u00020\u0004H¦Bø\u0001\u0000¢\u0006\u0002\u0010\u0005J\u000E\u0010\u0006\u001A\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\u0007J\u0013\u0010\b\u001A\u00028\u0000H\u0097@ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0005\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/channels/ChannelIterator;", "E", "", "hasNext", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "next", "()Ljava/lang/Object;", "next0", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface ChannelIterator {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public static final class DefaultImpls {
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
        public static Object next(ChannelIterator channelIterator0, Continuation continuation0) {
            ChannelIterator.next0.1 channelIterator$next0$10;
            if(continuation0 instanceof ChannelIterator.next0.1) {
                channelIterator$next0$10 = (ChannelIterator.next0.1)continuation0;
                if((channelIterator$next0$10.label & 0x80000000) == 0) {
                    channelIterator$next0$10 = new ChannelIterator.next0.1(continuation0);
                }
                else {
                    channelIterator$next0$10.label ^= 0x80000000;
                }
            }
            else {
                channelIterator$next0$10 = new ChannelIterator.next0.1(continuation0);
            }
            Object object0 = channelIterator$next0$10.result;
            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch(channelIterator$next0$10.label) {
                case 0: {
                    ResultKt.throwOnFailure(object0);
                    channelIterator$next0$10.L$0 = channelIterator0;
                    channelIterator$next0$10.label = 1;
                    object0 = channelIterator0.hasNext(channelIterator$next0$10);
                    if(object0 == object1) {
                        return object1;
                    }
                    break;
                }
                case 1: {
                    channelIterator0 = (ChannelIterator)channelIterator$next0$10.L$0;
                    ResultKt.throwOnFailure(object0);
                    break;
                }
                default: {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                }
            }
            if(!((Boolean)object0).booleanValue()) {
                throw new ClosedReceiveChannelException("Channel was closed");
            }
            return channelIterator0.next();
        }
    }

    Object hasNext(Continuation arg1);

    Object next();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Since 1.3.0, binary compatibility with versions <= 1.2.x")
    Object next(Continuation arg1);
}

