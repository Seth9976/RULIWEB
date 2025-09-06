package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001A\u001C\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u0007\u001AT\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000726\u0010\t\u001A2\u0012\u0013\u0012\u0011H\b¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\f\u0012\u0013\u0012\u0011H\b¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\u0001\u001A6\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u000F*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0012\u0010\u0010\u001A\u000E\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u000F0\u0005\u001Au\u0010\u000E\u001A\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00072\u0014\u0010\u0010\u001A\u0010\u0012\u0004\u0012\u0002H\b\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00052:\u0010\t\u001A6\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\f\u0012\u0015\u0012\u0013\u0018\u00010\u0002¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00030\u0001H\u0002¢\u0006\u0002\b\u0011\"$\u0010\u0000\u001A\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00030\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001E\u0010\u0004\u001A\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"defaultAreEquivalent", "Lkotlin/Function2;", "", "", "defaultKeySelector", "Lkotlin/Function1;", "distinctUntilChanged", "Lkotlinx/coroutines/flow/Flow;", "T", "areEquivalent", "Lkotlin/ParameterName;", "name", "old", "new", "distinctUntilChangedBy", "K", "keySelector", "distinctUntilChangedBy$FlowKt__DistinctKt", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__DistinctKt {
    private static final Function2 defaultAreEquivalent;
    private static final Function1 defaultKeySelector;

    static {
        FlowKt__DistinctKt.defaultKeySelector = FlowKt__DistinctKt.defaultKeySelector.1.INSTANCE;
        FlowKt__DistinctKt.defaultAreEquivalent = FlowKt__DistinctKt.defaultAreEquivalent.1.INSTANCE;
    }

    // 去混淆评级： 低(20)
    public static final Flow distinctUntilChanged(Flow flow0) {
        return flow0 instanceof StateFlow ? flow0 : FlowKt__DistinctKt.distinctUntilChangedBy$FlowKt__DistinctKt(flow0, FlowKt__DistinctKt.defaultKeySelector, FlowKt__DistinctKt.defaultAreEquivalent);
    }

    public static final Flow distinctUntilChanged(Flow flow0, Function2 function20) {
        Intrinsics.checkNotNull(function20, "null cannot be cast to non-null type kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Boolean>");
        Function2 function21 = (Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(function20, 2);
        return FlowKt__DistinctKt.distinctUntilChangedBy$FlowKt__DistinctKt(flow0, FlowKt__DistinctKt.defaultKeySelector, function21);
    }

    public static final Flow distinctUntilChangedBy(Flow flow0, Function1 function10) {
        return FlowKt__DistinctKt.distinctUntilChangedBy$FlowKt__DistinctKt(flow0, function10, FlowKt__DistinctKt.defaultAreEquivalent);
    }

    private static final Flow distinctUntilChangedBy$FlowKt__DistinctKt(Flow flow0, Function1 function10, Function2 function20) {
        return flow0 instanceof DistinctFlowImpl && ((DistinctFlowImpl)flow0).keySelector == function10 && ((DistinctFlowImpl)flow0).areEquivalent == function20 ? flow0 : new DistinctFlowImpl(flow0, function10, function20);
    }
}

