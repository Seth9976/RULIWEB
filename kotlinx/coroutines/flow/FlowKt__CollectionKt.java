package kotlinx.coroutines.flow;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

@Metadata(d1 = {"\u0000(\n\u0002\b\u0003\n\u0002\u0010\u001F\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010#\n\u0002\b\u0002\u001A;\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0010\b\u0001\u0010\u0001*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001A\u0002H\u0001H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001A7\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u000E\b\u0002\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\tH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\n\u001A7\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u000E\b\u0002\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\rH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000E\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000F"}, d2 = {"toCollection", "C", "T", "", "Lkotlinx/coroutines/flow/Flow;", "destination", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Collection;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toList", "", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toSet", "", "", "(Lkotlinx/coroutines/flow/Flow;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__CollectionKt {
    public static final Object toCollection(Flow flow0, Collection collection0, Continuation continuation0) {
        kotlinx.coroutines.flow.FlowKt__CollectionKt.toCollection.1 flowKt__CollectionKt$toCollection$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.FlowKt__CollectionKt.toCollection.1) {
            flowKt__CollectionKt$toCollection$10 = (kotlinx.coroutines.flow.FlowKt__CollectionKt.toCollection.1)continuation0;
            if((flowKt__CollectionKt$toCollection$10.label & 0x80000000) == 0) {
                flowKt__CollectionKt$toCollection$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return FlowKt.toCollection(null, null, this);
                    }
                };
            }
            else {
                flowKt__CollectionKt$toCollection$10.label ^= 0x80000000;
            }
        }
        else {
            flowKt__CollectionKt$toCollection$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return FlowKt.toCollection(null, null, this);
                }
            };
        }
        Object object0 = flowKt__CollectionKt$toCollection$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(flowKt__CollectionKt$toCollection$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                FlowCollector flowCollector0 = new FlowCollector() {
                    @Override  // kotlinx.coroutines.flow.FlowCollector
                    public final Object emit(Object object0, Continuation continuation0) {
                        collection0.add(object0);
                        return Unit.INSTANCE;
                    }
                };
                flowKt__CollectionKt$toCollection$10.L$0 = collection0;
                flowKt__CollectionKt$toCollection$10.label = 1;
                return flow0.collect(flowCollector0, flowKt__CollectionKt$toCollection$10) == object1 ? object1 : collection0;
            }
            case 1: {
                Collection collection1 = (Collection)flowKt__CollectionKt$toCollection$10.L$0;
                ResultKt.throwOnFailure(object0);
                return collection1;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
    }

    public static final Object toList(Flow flow0, List list0, Continuation continuation0) {
        return FlowKt.toCollection(flow0, list0, continuation0);
    }

    public static Object toList$default(Flow flow0, List list0, Continuation continuation0, int v, Object object0) {
        if((v & 1) != 0) {
            list0 = new ArrayList();
        }
        return FlowKt.toList(flow0, list0, continuation0);
    }

    public static final Object toSet(Flow flow0, Set set0, Continuation continuation0) {
        return FlowKt.toCollection(flow0, set0, continuation0);
    }

    public static Object toSet$default(Flow flow0, Set set0, Continuation continuation0, int v, Object object0) {
        if((v & 1) != 0) {
            set0 = new LinkedHashSet();
        }
        return FlowKt.toSet(flow0, set0, continuation0);
    }
}

