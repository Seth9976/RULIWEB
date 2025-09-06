package androidx.lifecycle;

import kotlin.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionAdapter;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
final class Transformations.sam.androidx_lifecycle_Observer.0 implements Observer, FunctionAdapter {
    private final Function1 function;

    Transformations.sam.androidx_lifecycle_Observer.0(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "function");
        super();
        this.function = function10;
    }

    // 去混淆评级： 低(20)
    @Override
    public final boolean equals(Object object0) {
        return !(object0 instanceof Observer) || !(object0 instanceof FunctionAdapter) ? false : Intrinsics.areEqual(this.getFunctionDelegate(), ((FunctionAdapter)object0).getFunctionDelegate());
    }

    @Override  // kotlin.jvm.internal.FunctionAdapter
    public final Function getFunctionDelegate() {
        return this.function;
    }

    @Override
    public final int hashCode() {
        return this.getFunctionDelegate().hashCode();
    }

    @Override  // androidx.lifecycle.Observer
    public final void onChanged(Object object0) {
        this.function.invoke(object0);
    }
}

