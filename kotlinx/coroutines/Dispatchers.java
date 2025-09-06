package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlinx.coroutines.internal.MainDispatcherLoader;
import kotlinx.coroutines.scheduling.DefaultIoScheduler;
import kotlinx.coroutines.scheduling.DefaultScheduler;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001A\u00020\u0014H\u0007R\u001C\u0010\u0003\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001A\u0004\b\u0006\u0010\u0007R\u001C\u0010\b\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\t\u0010\u0002\u001A\u0004\b\n\u0010\u0007R\u001A\u0010\u000B\u001A\u00020\f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u0002\u001A\u0004\b\u000E\u0010\u000FR\u001C\u0010\u0010\u001A\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001A\u0004\b\u0012\u0010\u0007¨\u0006\u0015"}, d2 = {"Lkotlinx/coroutines/Dispatchers;", "", "()V", "Default", "Lkotlinx/coroutines/CoroutineDispatcher;", "getDefault$annotations", "getDefault", "()Lkotlinx/coroutines/CoroutineDispatcher;", "IO", "getIO$annotations", "getIO", "Main", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "getMain$annotations", "getMain", "()Lkotlinx/coroutines/MainCoroutineDispatcher;", "Unconfined", "getUnconfined$annotations", "getUnconfined", "shutdown", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Dispatchers {
    private static final CoroutineDispatcher Default;
    public static final Dispatchers INSTANCE;
    private static final CoroutineDispatcher IO;
    private static final CoroutineDispatcher Unconfined;

    static {
        Dispatchers.INSTANCE = new Dispatchers();
        Dispatchers.Default = DefaultScheduler.INSTANCE;
        Dispatchers.Unconfined = Unconfined.INSTANCE;
        Dispatchers.IO = DefaultIoScheduler.INSTANCE;
    }

    public static final CoroutineDispatcher getDefault() {
        return Dispatchers.Default;
    }

    @JvmStatic
    public static void getDefault$annotations() {
    }

    public static final CoroutineDispatcher getIO() {
        return Dispatchers.IO;
    }

    @JvmStatic
    public static void getIO$annotations() {
    }

    public static final MainCoroutineDispatcher getMain() {
        return MainDispatcherLoader.dispatcher;
    }

    @JvmStatic
    public static void getMain$annotations() {
    }

    public static final CoroutineDispatcher getUnconfined() {
        return Dispatchers.Unconfined;
    }

    @JvmStatic
    public static void getUnconfined$annotations() {
    }

    public final void shutdown() {
        DefaultExecutor.INSTANCE.shutdown();
        DefaultScheduler.INSTANCE.shutdown$kotlinx_coroutines_core();
    }
}

