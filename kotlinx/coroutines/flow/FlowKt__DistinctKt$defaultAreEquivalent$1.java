package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\u0010\u0000\u001A\u00020\u00012\b\u0010\u0002\u001A\u0004\u0018\u00010\u00032\b\u0010\u0004\u001A\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "old", "", "new", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
final class FlowKt__DistinctKt.defaultAreEquivalent.1 extends Lambda implements Function2 {
    public static final FlowKt__DistinctKt.defaultAreEquivalent.1 INSTANCE;

    static {
        FlowKt__DistinctKt.defaultAreEquivalent.1.INSTANCE = new FlowKt__DistinctKt.defaultAreEquivalent.1();
    }

    FlowKt__DistinctKt.defaultAreEquivalent.1() {
        super(2);
    }

    public final Boolean invoke(Object object0, Object object1) {
        return Boolean.valueOf(Intrinsics.areEqual(object0, object1));
    }

    @Override  // kotlin.jvm.functions.Function2
    public Object invoke(Object object0, Object object1) {
        return this.invoke(object0, object1);
    }
}

