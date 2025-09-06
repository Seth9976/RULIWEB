package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ThreadContextKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001B\u0012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u000E\u001A\u00020\f2\u0006\u0010\u000F\u001A\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0004\u001A\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\t\u001A\u001E\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000B\u0012\u0006\u0012\u0004\u0018\u00010\b0\nX\u0082\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lkotlinx/coroutines/flow/internal/UndispatchedContextCollector;", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "downstream", "emitContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;)V", "countOrElement", "", "emitRef", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/jvm/functions/Function2;", "emit", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class UndispatchedContextCollector implements FlowCollector {
    private final Object countOrElement;
    private final CoroutineContext emitContext;
    private final Function2 emitRef;

    public UndispatchedContextCollector(FlowCollector flowCollector0, CoroutineContext coroutineContext0) {
        this.emitContext = coroutineContext0;
        this.countOrElement = ThreadContextKt.threadContextElements(coroutineContext0);
        this.emitRef = new Function2(null) {
            final FlowCollector $downstream;
            Object L$0;
            int label;

            {
                this.$downstream = flowCollector0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.internal.UndispatchedContextCollector.emitRef.1 undispatchedContextCollector$emitRef$10 = new kotlinx.coroutines.flow.internal.UndispatchedContextCollector.emitRef.1(this.$downstream, continuation0);
                undispatchedContextCollector$emitRef$10.L$0 = object0;
                return undispatchedContextCollector$emitRef$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, ((Continuation)object1));
            }

            public final Object invoke(Object object0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.internal.UndispatchedContextCollector.emitRef.1)this.create(object0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return this.$downstream.emit(this.L$0, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        };
    }

    @Override  // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object object0, Continuation continuation0) {
        Object object1 = ChannelFlowKt.withContextUndispatched(this.emitContext, object0, this.countOrElement, this.emitRef, continuation0);
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }
}

