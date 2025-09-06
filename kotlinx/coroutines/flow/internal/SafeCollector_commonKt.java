package kotlinx.coroutines.flow.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext.Element;
import kotlin.coroutines.CoroutineContext.Key;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.internal.ScopeCoroutine;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001AN\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022/\b\u0005\u0010\u0003\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0004¢\u0006\u0002\b\tH\u0081\bø\u0001\u0000¢\u0006\u0002\u0010\n\u001A\u0018\u0010\u000B\u001A\u00020\u0007*\u0006\u0012\u0002\b\u00030\f2\u0006\u0010\r\u001A\u00020\u000EH\u0001\u001A\u001B\u0010\u000F\u001A\u0004\u0018\u00010\u0010*\u0004\u0018\u00010\u00102\b\u0010\u0011\u001A\u0004\u0018\u00010\u0010H\u0080\u0010\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"unsafeFlow", "Lkotlinx/coroutines/flow/Flow;", "T", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/Flow;", "checkContext", "Lkotlinx/coroutines/flow/internal/SafeCollector;", "currentContext", "Lkotlin/coroutines/CoroutineContext;", "transitiveCoroutineParent", "Lkotlinx/coroutines/Job;", "collectJob", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SafeCollector_commonKt {
    public static final void checkContext(SafeCollector safeCollector0, CoroutineContext coroutineContext0) {
        if(((Number)coroutineContext0.fold(0, new Function2(safeCollector0) {
            final SafeCollector $this_checkContext;

            {
                this.$this_checkContext = safeCollector0;
                super(2);
            }

            public final Integer invoke(int v, Element coroutineContext$Element0) {
                Key coroutineContext$Key0 = coroutineContext$Element0.getKey();
                Element coroutineContext$Element1 = this.$this_checkContext.collectContext.get(coroutineContext$Key0);
                if(coroutineContext$Key0 != Job.Key) {
                    return coroutineContext$Element0 == coroutineContext$Element1 ? ((int)(v + 1)) : 0x80000000;
                }
                Intrinsics.checkNotNull(coroutineContext$Element0, "null cannot be cast to non-null type kotlinx.coroutines.Job");
                Job job0 = SafeCollector_commonKt.transitiveCoroutineParent(((Job)coroutineContext$Element0), ((Job)coroutineContext$Element1));
                if(job0 != ((Job)coroutineContext$Element1)) {
                    throw new IllegalStateException(("Flow invariant is violated:\n\t\tEmission from another coroutine is detected.\n\t\tChild of " + job0 + ", expected child of " + ((Job)coroutineContext$Element1) + ".\n\t\tFlowCollector is not thread-safe and concurrent emissions are prohibited.\n\t\tTo mitigate this restriction please use \'channelFlow\' builder instead of \'flow\'").toString());
                }
                if(((Job)coroutineContext$Element1) != null) {
                    ++v;
                }
                return v;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((Number)object0).intValue(), ((Element)object1));
            }
        })).intValue() != safeCollector0.collectContextSize) {
            throw new IllegalStateException(("Flow invariant is violated:\n\t\tFlow was collected in " + safeCollector0.collectContext + ",\n\t\tbut emission happened in " + coroutineContext0 + ".\n\t\tPlease refer to \'flow\' documentation or use \'flowOn\' instead").toString());
        }
    }

    public static final Job transitiveCoroutineParent(Job job0, Job job1) {
        while(true) {
            if(job0 == null) {
                return null;
            }
            if(job0 == job1 || !(job0 instanceof ScopeCoroutine)) {
                return job0;
            }
            job0 = job0.getParent();
        }
    }

    public static final Flow unsafeFlow(Function2 function20) {
        return new Flow() {
            @Override  // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
                Object object0 = function20.invoke(flowCollector0, continuation0);
                return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;

                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0xB0)
                public final class kotlinx.coroutines.flow.internal.SafeCollector_commonKt.unsafeFlow.1.collect.1 extends ContinuationImpl {
                    int label;
                    Object result;

                    public kotlinx.coroutines.flow.internal.SafeCollector_commonKt.unsafeFlow.1.collect.1(kotlinx.coroutines.flow.internal.SafeCollector_commonKt.unsafeFlow.1 safeCollector_commonKt$unsafeFlow$10, Continuation continuation0) {
                    }

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return kotlinx.coroutines.flow.internal.SafeCollector_commonKt.unsafeFlow.1.this.collect(null, this);
                    }
                }

            }

            public Object collect$$forInline(FlowCollector flowCollector0, Continuation continuation0) {
                new kotlinx.coroutines.flow.internal.SafeCollector_commonKt.unsafeFlow.1.collect.1(this, continuation0);
                function20.invoke(flowCollector0, continuation0);
                return Unit.INSTANCE;
            }
        };
    }
}

