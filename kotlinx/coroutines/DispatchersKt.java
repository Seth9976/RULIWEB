package kotlinx.coroutines;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000\"\u001E\u0010\u0002\u001A\u00020\u0003*\u00020\u00048FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001A\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"IO_PARALLELISM_PROPERTY_NAME", "", "IO", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/Dispatchers;", "getIO$annotations", "(Lkotlinx/coroutines/Dispatchers;)V", "getIO", "(Lkotlinx/coroutines/Dispatchers;)Lkotlinx/coroutines/CoroutineDispatcher;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DispatchersKt {
    public static final String IO_PARALLELISM_PROPERTY_NAME = "kotlinx.coroutines.io.parallelism";

    public static final CoroutineDispatcher getIO(Dispatchers dispatchers0) {
        return Dispatchers.getIO();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Should not be used directly")
    public static void getIO$annotations(Dispatchers dispatchers0) {
    }
}

