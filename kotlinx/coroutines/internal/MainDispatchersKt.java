package kotlinx.coroutines.internal;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001A \u0010\u0006\u001A\u00020\u00072\n\b\u0002\u0010\b\u001A\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001A\u0004\u0018\u00010\u0001H\u0002\u001A\b\u0010\u000B\u001A\u00020\fH\u0000\u001A\f\u0010\r\u001A\u00020\u0003*\u00020\u000EH\u0007\u001A\u001A\u0010\u000F\u001A\u00020\u000E*\u00020\u00102\f\u0010\u0011\u001A\b\u0012\u0004\u0012\u00020\u00100\u0012H\u0007\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001A\u00020\u0003X\u0082D¢\u0006\b\n\u0000\u0012\u0004\b\u0004\u0010\u0005¨\u0006\u0013"}, d2 = {"FAST_SERVICE_LOADER_PROPERTY_NAME", "", "SUPPORT_MISSING", "", "getSUPPORT_MISSING$annotations", "()V", "createMissingDispatcher", "Lkotlinx/coroutines/internal/MissingMainCoroutineDispatcher;", "cause", "", "errorHint", "throwMissingMainDispatcherException", "", "isMissing", "Lkotlinx/coroutines/MainCoroutineDispatcher;", "tryCreateDispatcher", "Lkotlinx/coroutines/internal/MainDispatcherFactory;", "factories", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class MainDispatchersKt {
    private static final String FAST_SERVICE_LOADER_PROPERTY_NAME = "kotlinx.coroutines.fast.service.loader";
    private static final boolean SUPPORT_MISSING = true;

    static {
    }

    private static final MissingMainCoroutineDispatcher createMissingDispatcher(Throwable throwable0, String s) {
        if(MainDispatchersKt.SUPPORT_MISSING) {
            return new MissingMainCoroutineDispatcher(throwable0, s);
        }
        if(throwable0 != null) {
            throw throwable0;
        }
        MainDispatchersKt.throwMissingMainDispatcherException();
        throw new KotlinNothingValueException();
    }

    static MissingMainCoroutineDispatcher createMissingDispatcher$default(Throwable throwable0, String s, int v, Object object0) {
        if((v & 1) != 0) {
            throwable0 = null;
        }
        if((v & 2) != 0) {
            s = null;
        }
        return MainDispatchersKt.createMissingDispatcher(throwable0, s);
    }

    private static void getSUPPORT_MISSING$annotations() {
    }

    public static final boolean isMissing(MainCoroutineDispatcher mainCoroutineDispatcher0) {
        return mainCoroutineDispatcher0.getImmediate() instanceof MissingMainCoroutineDispatcher;
    }

    public static final Void throwMissingMainDispatcherException() {
        throw new IllegalStateException("Module with the Main dispatcher is missing. Add dependency providing the Main dispatcher, e.g. \'kotlinx-coroutines-android\' and ensure it has the same version as \'kotlinx-coroutines-core\'");
    }

    public static final MainCoroutineDispatcher tryCreateDispatcher(MainDispatcherFactory mainDispatcherFactory0, List list0) {
        try {
            return mainDispatcherFactory0.createDispatcher(list0);
        }
        catch(Throwable throwable0) {
            return MainDispatchersKt.createMissingDispatcher(throwable0, mainDispatcherFactory0.hintOnError());
        }
    }
}

