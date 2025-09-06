package kotlinx.coroutines.stream;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.stream.Stream;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.Volatile;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import retrofit2.Platform..ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J\u001F\u0010\b\u001A\u00020\t2\f\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u000BH\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\fR\t\u0010\u0006\u001A\u00020\u0007X\u0082\u0004R\u0014\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/stream/StreamFlow;", "T", "Lkotlinx/coroutines/flow/Flow;", "stream", "Ljava/util/stream/Stream;", "(Ljava/util/stream/Stream;)V", "consumed", "Lkotlinx/atomicfu/AtomicBoolean;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class StreamFlow implements Flow {
    @Volatile
    private volatile int consumed;
    private static final AtomicIntegerFieldUpdater consumed$FU;
    private final Stream stream;

    static {
        StreamFlow.consumed$FU = AtomicIntegerFieldUpdater.newUpdater(StreamFlow.class, "consumed");
    }

    public StreamFlow(Stream stream0) {
        this.stream = stream0;
        this.consumed = 0;
    }

    @Override  // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        Iterator iterator0;
        FlowCollector flowCollector1;
        StreamFlow streamFlow0;
        kotlinx.coroutines.stream.StreamFlow.collect.1 streamFlow$collect$10;
        if(continuation0 instanceof kotlinx.coroutines.stream.StreamFlow.collect.1) {
            streamFlow$collect$10 = (kotlinx.coroutines.stream.StreamFlow.collect.1)continuation0;
            if((streamFlow$collect$10.label & 0x80000000) == 0) {
                streamFlow$collect$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.collect(null, this);
                    }
                };
            }
            else {
                streamFlow$collect$10.label ^= 0x80000000;
            }
        }
        else {
            streamFlow$collect$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.collect(null, this);
                }
            };
        }
        Object object0 = streamFlow$collect$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(streamFlow$collect$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                if(StreamFlow.consumed$FU.compareAndSet(this, 0, 1)) {
                    try {
                        streamFlow0 = this;
                        flowCollector1 = flowCollector0;
                        iterator0 = this.stream.iterator();
                        streamFlow0 = this;
                        goto label_25;
                    }
                    catch(Throwable throwable0) {
                        break;
                    }
                }
                throw new IllegalStateException("Stream.consumeAsFlow can be collected only once");
            }
            case 1: {
                iterator0 = (Iterator)streamFlow$collect$10.L$2;
                FlowCollector flowCollector2 = (FlowCollector)streamFlow$collect$10.L$1;
                streamFlow0 = (StreamFlow)streamFlow$collect$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    flowCollector1 = flowCollector2;
                    while(true) {
                    label_25:
                        if(!iterator0.hasNext()) {
                            goto label_38;
                        }
                        Object object2 = iterator0.next();
                        streamFlow$collect$10.L$0 = streamFlow0;
                        streamFlow$collect$10.L$1 = flowCollector1;
                        streamFlow$collect$10.L$2 = iterator0;
                        streamFlow$collect$10.label = 1;
                        if(flowCollector1.emit(object2, streamFlow$collect$10) == object1) {
                            return object1;
                        }
                    }
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        Platform..ExternalSyntheticApiModelOutline0.m(streamFlow0.stream);
        throw throwable0;
    label_38:
        Platform..ExternalSyntheticApiModelOutline0.m(streamFlow0.stream);
        return Unit.INSTANCE;
    }
}

