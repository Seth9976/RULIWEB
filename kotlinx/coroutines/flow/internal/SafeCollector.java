package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000B\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u00032\u00020\u0004B\u001B\u0012\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0006\u001A\u00020\u0007¢\u0006\u0002\u0010\bJ\'\u0010\u0015\u001A\u00020\u00102\u0006\u0010\u0016\u001A\u00020\u00072\b\u0010\u0017\u001A\u0004\u0018\u00010\u00072\u0006\u0010\u0018\u001A\u00028\u0000H\u0002¢\u0006\u0002\u0010\u0019J\u0019\u0010\u001A\u001A\u00020\u00102\u0006\u0010\u0018\u001A\u00028\u0000H\u0096@ø\u0001\u0000¢\u0006\u0002\u0010\u001BJ%\u0010\u001A\u001A\u0004\u0018\u00010\u001C2\f\u0010\u001D\u001A\b\u0012\u0004\u0012\u00020\u00100\u000F2\u0006\u0010\u0018\u001A\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001EJ\u001A\u0010\u001F\u001A\u00020\u00102\u0006\u0010 \u001A\u00020!2\b\u0010\u0018\u001A\u0004\u0018\u00010\u001CH\u0002J\n\u0010\"\u001A\u0004\u0018\u00010#H\u0016J \u0010$\u001A\u00020\u001C2\u000E\u0010%\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u001C0&H\u0016ø\u0001\u0000¢\u0006\u0002\u0010\'J\b\u0010(\u001A\u00020\u0010H\u0016R\u0016\u0010\t\u001A\u0004\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0010\u0010\u0006\u001A\u00020\u00078\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001A\u00020\r8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001A\b\u0012\u0004\u0012\u00028\u00000\u00028\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000E\u001A\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000FX\u0082\u000E¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001A\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lkotlinx/coroutines/flow/internal/SafeCollector;", "T", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "collector", "collectContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/CoroutineContext;)V", "callerFrame", "getCallerFrame", "()Lkotlin/coroutines/jvm/internal/CoroutineStackFrame;", "collectContextSize", "", "completion", "Lkotlin/coroutines/Continuation;", "", "context", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "lastEmissionContext", "checkContext", "currentContext", "previousContext", "value", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Ljava/lang/Object;)V", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "uCont", "(Lkotlin/coroutines/Continuation;Ljava/lang/Object;)Ljava/lang/Object;", "exceptionTransparencyViolated", "exception", "Lkotlinx/coroutines/flow/internal/DownstreamExceptionContext;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "invokeSuspend", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "releaseIntercepted", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SafeCollector extends ContinuationImpl implements CoroutineStackFrame, FlowCollector {
    public final CoroutineContext collectContext;
    public final int collectContextSize;
    public final FlowCollector collector;
    private Continuation completion;
    private CoroutineContext lastEmissionContext;

    public SafeCollector(FlowCollector flowCollector0, CoroutineContext coroutineContext0) {
        super(NoOpContinuation.INSTANCE, EmptyCoroutineContext.INSTANCE);
        this.collector = flowCollector0;
        this.collectContext = coroutineContext0;
        this.collectContextSize = ((Number)coroutineContext0.fold(0, kotlinx.coroutines.flow.internal.SafeCollector.collectContextSize.1.INSTANCE)).intValue();

        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u00020\u00012\u0006\u0010\u0004\u001A\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "T", "count", "<anonymous parameter 1>", "Lkotlin/coroutines/CoroutineContext$Element;", "invoke", "(ILkotlin/coroutines/CoroutineContext$Element;)Ljava/lang/Integer;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.flow.internal.SafeCollector.collectContextSize.1 extends Lambda implements Function2 {
            public static final kotlinx.coroutines.flow.internal.SafeCollector.collectContextSize.1 INSTANCE;

            static {
                kotlinx.coroutines.flow.internal.SafeCollector.collectContextSize.1.INSTANCE = new kotlinx.coroutines.flow.internal.SafeCollector.collectContextSize.1();
            }

            kotlinx.coroutines.flow.internal.SafeCollector.collectContextSize.1() {
                super(2);
            }

            public final Integer invoke(int v, Element coroutineContext$Element0) {
                return (int)(v + 1);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((Number)object0).intValue(), ((Element)object1));
            }
        }

    }

    private final void checkContext(CoroutineContext coroutineContext0, CoroutineContext coroutineContext1, Object object0) {
        if(coroutineContext1 instanceof DownstreamExceptionContext) {
            this.exceptionTransparencyViolated(((DownstreamExceptionContext)coroutineContext1), object0);
        }
        SafeCollector_commonKt.checkContext(this, coroutineContext0);
    }

    private final Object emit(Continuation continuation0, Object object0) {
        CoroutineContext coroutineContext0 = continuation0.getContext();
        JobKt.ensureActive(coroutineContext0);
        CoroutineContext coroutineContext1 = this.lastEmissionContext;
        if(coroutineContext1 != coroutineContext0) {
            this.checkContext(coroutineContext0, coroutineContext1, object0);
            this.lastEmissionContext = coroutineContext0;
        }
        this.completion = continuation0;
        Intrinsics.checkNotNull(this.collector, "null cannot be cast to non-null type kotlinx.coroutines.flow.FlowCollector<kotlin.Any?>");
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Unit>");
        Object object1 = SafeCollectorKt.emitFun.invoke(this.collector, object0, this);
        if(!Intrinsics.areEqual(object1, IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
            this.completion = null;
        }
        return object1;
    }

    @Override  // kotlinx.coroutines.flow.FlowCollector
    public Object emit(Object object0, Continuation continuation0) {
        Object object1;
        try {
            object1 = this.emit(continuation0, object0);
        }
        catch(Throwable throwable0) {
            this.lastEmissionContext = new DownstreamExceptionContext(throwable0, continuation0.getContext());
            throw throwable0;
        }
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    private final void exceptionTransparencyViolated(DownstreamExceptionContext downstreamExceptionContext0, Object object0) {
        throw new IllegalStateException(StringsKt.trimIndent(("\n            Flow exception transparency is violated:\n                Previous \'emit\' call has thrown exception " + downstreamExceptionContext0.e + ", but then emission attempt of value \'" + object0 + "\' has been detected.\n                Emissions from \'catch\' blocks are prohibited in order to avoid unspecified behaviour, \'Flow.catch\' operator can be used instead.\n                For a more detailed explanation, please refer to Flow documentation.\n            ")).toString());
    }

    // 去混淆评级： 低(20)
    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public CoroutineStackFrame getCallerFrame() {
        return this.completion instanceof CoroutineStackFrame ? ((CoroutineStackFrame)this.completion) : null;
    }

    @Override  // kotlin.coroutines.jvm.internal.ContinuationImpl
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext0 = this.lastEmissionContext;
        return coroutineContext0 == null ? EmptyCoroutineContext.INSTANCE : coroutineContext0;
    }

    @Override  // kotlin.coroutines.jvm.internal.CoroutineStackFrame, kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object object0) {
        Throwable throwable0 = Result.exceptionOrNull-impl(object0);
        if(throwable0 != null) {
            this.lastEmissionContext = new DownstreamExceptionContext(throwable0, this.getContext());
        }
        Continuation continuation0 = this.completion;
        if(continuation0 != null) {
            continuation0.resumeWith(object0);
        }
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }

    @Override  // kotlin.coroutines.jvm.internal.ContinuationImpl
    public void releaseIntercepted() {
        super.releaseIntercepted();
    }
}

