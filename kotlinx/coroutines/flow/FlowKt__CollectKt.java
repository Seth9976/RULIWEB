package kotlinx.coroutines.flow;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.internal.NopCollector;

@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A\u0019\u0010\u0000\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0003\u001AV\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u000223\b\u0004\u0010\u0005\u001A-\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\u0006H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\f\u001Ak\u0010\r\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00022H\b\u0004\u0010\u0005\u001AB\b\u0001\u0012\u0013\u0012\u00110\u000F¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\u000EH\u0086Hø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001AT\u0010\u0012\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u000221\u0010\u0005\u001A-\b\u0001\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u000B0\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001A/\u0010\u0013\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00142\f\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0002H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001A\u001E\u0010\u0017\u001A\u00020\u0018\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00022\u0006\u0010\u0019\u001A\u00020\u001A\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u001B"}, d2 = {"collect", "", "Lkotlinx/coroutines/flow/Flow;", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "T", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectIndexed", "Lkotlin/Function3;", "", "index", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectLatest", "emitAll", "Lkotlinx/coroutines/flow/FlowCollector;", "flow", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "launchIn", "Lkotlinx/coroutines/Job;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__CollectKt {
    public static final Object collect(Flow flow0, Continuation continuation0) {
        Object object0 = flow0.collect(NopCollector.INSTANCE, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Backwards compatibility with JS and K/N")
    public static final Object collect(Flow flow0, Function2 function20, Continuation continuation0) {
        Object object0 = flow0.collect(new FlowCollector() {
            @Override  // kotlinx.coroutines.flow.FlowCollector
            public Object emit(Object object0, Continuation continuation0) {
                Object object1 = function20.invoke(object0, continuation0);
                return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__CollectKt.collect.3.emit.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__CollectKt.collect.3.emit.1(kotlinx.coroutines.flow.FlowKt__CollectKt.collect.3 flowKt__CollectKt$collect$30, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__CollectKt.collect.3.this.emit(null, this);
                    }
                }

            }

            public Object emit$$forInline(Object object0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__CollectKt.collect.3.emit.1(this, continuation0);
                function20.invoke(object0, continuation0);
                return Unit.INSTANCE;
            }
        }, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Backwards compatibility with JS and K/N")
    private static final Object collect$$forInline(Flow flow0, Function2 function20, Continuation continuation0) {
        flow0.collect(new kotlinx.coroutines.flow.FlowKt__CollectKt.collect.3(function20), continuation0);
        return Unit.INSTANCE;
    }

    public static final Object collectIndexed(Flow flow0, Function3 function30, Continuation continuation0) {
        Object object0 = flow0.collect(new FlowCollector() {
            private int index;

            @Override  // kotlinx.coroutines.flow.FlowCollector
            public Object emit(Object object0, Continuation continuation0) {
                Function3 function30 = function30;
                int v = this.index;
                this.index = v + 1;
                if(v < 0) {
                    throw new ArithmeticException("Index overflow has happened");
                }
                Object object1 = function30.invoke(Boxing.boxInt(v), object0, continuation0);
                return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.FlowKt__CollectKt.collectIndexed.2.emit.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.FlowKt__CollectKt.collectIndexed.2.emit.1(kotlinx.coroutines.flow.FlowKt__CollectKt.collectIndexed.2 flowKt__CollectKt$collectIndexed$20, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.FlowKt__CollectKt.collectIndexed.2.this.emit(null, this);
                    }
                }

            }

            public Object emit$$forInline(Object object0, Continuation continuation0) {
                new kotlinx.coroutines.flow.FlowKt__CollectKt.collectIndexed.2.emit.1(this, continuation0);
                Function3 function30 = function30;
                int v = this.index;
                this.index = v + 1;
                if(v < 0) {
                    throw new ArithmeticException("Index overflow has happened");
                }
                function30.invoke(v, object0, continuation0);
                return Unit.INSTANCE;
            }
        }, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    private static final Object collectIndexed$$forInline(Flow flow0, Function3 function30, Continuation continuation0) {
        flow0.collect(new kotlinx.coroutines.flow.FlowKt__CollectKt.collectIndexed.2(function30), continuation0);
        return Unit.INSTANCE;
    }

    public static final Object collectLatest(Flow flow0, Function2 function20, Continuation continuation0) {
        Object object0 = FlowKt.collect(FlowKt.buffer$default(FlowKt.mapLatest(flow0, function20), 0, null, 2, null), continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    public static final Object emitAll(FlowCollector flowCollector0, Flow flow0, Continuation continuation0) {
        FlowKt.ensureActive(flowCollector0);
        Object object0 = flow0.collect(flowCollector0, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    public static final Job launchIn(Flow flow0, CoroutineScope coroutineScope0) {
        return BuildersKt__Builders_commonKt.launch$default(coroutineScope0, null, null, new Function2(flow0, null) {
            final Flow $this_launchIn;
            int label;

            {
                this.$this_launchIn = flow0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new kotlinx.coroutines.flow.FlowKt__CollectKt.launchIn.1(this.$this_launchIn, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__CollectKt.launchIn.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        this.label = 1;
                        return FlowKt.collect(this.$this_launchIn, this) == object1 ? object1 : Unit.INSTANCE;
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
        }, 3, null);
    }
}

