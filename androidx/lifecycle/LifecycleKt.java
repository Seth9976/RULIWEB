package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"coroutineScope", "Landroidx/lifecycle/LifecycleCoroutineScope;", "Landroidx/lifecycle/Lifecycle;", "getCoroutineScope", "(Landroidx/lifecycle/Lifecycle;)Landroidx/lifecycle/LifecycleCoroutineScope;", "lifecycle-common"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LifecycleKt {
    public static final LifecycleCoroutineScope getCoroutineScope(Lifecycle lifecycle0) {
        LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl1;
        Intrinsics.checkNotNullParameter(lifecycle0, "<this>");
        do {
            LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl0 = (LifecycleCoroutineScopeImpl)lifecycle0.getInternalScopeRef().get();
            if(lifecycleCoroutineScopeImpl0 != null) {
                return lifecycleCoroutineScopeImpl0;
            }
            lifecycleCoroutineScopeImpl1 = new LifecycleCoroutineScopeImpl(lifecycle0, SupervisorKt.SupervisorJob$default(null, 1, null).plus(Dispatchers.getMain().getImmediate()));
        }
        while(!LifecycleKt..ExternalSyntheticBackportWithForwarding0.m(lifecycle0.getInternalScopeRef(), null, lifecycleCoroutineScopeImpl1));
        lifecycleCoroutineScopeImpl1.register();
        return lifecycleCoroutineScopeImpl1;
    }
}

