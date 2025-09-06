package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.MainDispatchersKt;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\u0002\u001A\b\u0010\b\u001A\u00020\u0001H\u0002\"\u001C\u0010\u0000\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\"\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"DefaultDelay", "Lkotlinx/coroutines/Delay;", "getDefaultDelay$annotations", "()V", "getDefaultDelay", "()Lkotlinx/coroutines/Delay;", "defaultMainDelayOptIn", "", "initializeDefaultDelay", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DefaultExecutorKt {
    private static final Delay DefaultDelay;
    private static final boolean defaultMainDelayOptIn;

    static {
        DefaultExecutorKt.defaultMainDelayOptIn = SystemPropsKt.systemProp("kotlinx.coroutines.main.delay", false);
        DefaultExecutorKt.DefaultDelay = DefaultExecutorKt.initializeDefaultDelay();
    }

    public static final Delay getDefaultDelay() {
        return DefaultExecutorKt.DefaultDelay;
    }

    public static void getDefaultDelay$annotations() {
    }

    private static final Delay initializeDefaultDelay() {
        if(!DefaultExecutorKt.defaultMainDelayOptIn) {
            return DefaultExecutor.INSTANCE;
        }
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain();
        return !MainDispatchersKt.isMissing(mainCoroutineDispatcher0) && mainCoroutineDispatcher0 instanceof Delay ? ((Delay)mainCoroutineDispatcher0) : DefaultExecutor.INSTANCE;
    }
}

