package kotlinx.coroutines;

import java.util.concurrent.atomic.AtomicLong;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0017\u0010\u0014\u001A\u00020\u00152\f\u0010\u0016\u001A\b\u0012\u0004\u0012\u00020\u00010\u0017H\u0081\b\u001A\b\u0010\u0018\u001A\u00020\u0015H\u0000\"\u0014\u0010\u0000\u001A\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0004\u001A\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0006\u0010\u0007\"\u0014\u0010\b\u001A\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\u0003\"\u000E\u0010\n\u001A\u00020\u000BX\u0086T¢\u0006\u0002\n\u0000\"\u000E\u0010\f\u001A\u00020\u000BX\u0086T¢\u0006\u0002\n\u0000\"\u000E\u0010\r\u001A\u00020\u000BX\u0086T¢\u0006\u0002\n\u0000\"\u000E\u0010\u000E\u001A\u00020\u000BX\u0086T¢\u0006\u0002\n\u0000\"\u001C\u0010\u000F\u001A\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000E\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001A\u0004\b\u0012\u0010\u0003\"\u000E\u0010\u0013\u001A\u00020\u000BX\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"ASSERTIONS_ENABLED", "", "getASSERTIONS_ENABLED", "()Z", "COROUTINE_ID", "Ljava/util/concurrent/atomic/AtomicLong;", "getCOROUTINE_ID", "()Ljava/util/concurrent/atomic/AtomicLong;", "DEBUG", "getDEBUG", "DEBUG_PROPERTY_NAME", "", "DEBUG_PROPERTY_VALUE_AUTO", "DEBUG_PROPERTY_VALUE_OFF", "DEBUG_PROPERTY_VALUE_ON", "RECOVER_STACK_TRACES", "getRECOVER_STACK_TRACES$annotations", "()V", "getRECOVER_STACK_TRACES", "STACKTRACE_RECOVERY_PROPERTY_NAME", "assert", "", "value", "Lkotlin/Function0;", "resetCoroutineId", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class DebugKt {
    private static final boolean ASSERTIONS_ENABLED = false;
    private static final AtomicLong COROUTINE_ID = null;
    private static final boolean DEBUG = false;
    public static final String DEBUG_PROPERTY_NAME = "kotlinx.coroutines.debug";
    public static final String DEBUG_PROPERTY_VALUE_AUTO = "auto";
    public static final String DEBUG_PROPERTY_VALUE_OFF = "off";
    public static final String DEBUG_PROPERTY_VALUE_ON = "on";
    private static final boolean RECOVER_STACK_TRACES = false;
    public static final String STACKTRACE_RECOVERY_PROPERTY_NAME = "kotlinx.coroutines.stacktrace.recovery";

    static {
        boolean z1;
        boolean z = false;
        DebugKt.ASSERTIONS_ENABLED = false;
        String s = SystemPropsKt.systemProp("kotlinx.coroutines.debug");
        if(s == null) {
            z1 = false;
        }
        else {
            switch(s) {
                case "": 
                case "on": {
                    z1 = true;
                    break;
                }
                case "auto": {
                    z1 = false;
                    break;
                }
                case "off": {
                    z1 = false;
                    break;
                }
                default: {
                    throw new IllegalStateException(("System property \'kotlinx.coroutines.debug\' has unrecognized value \'" + s + '\'').toString());
                }
            }
        }
        DebugKt.DEBUG = z1;
        if(z1 && SystemPropsKt.systemProp("kotlinx.coroutines.stacktrace.recovery", true)) {
            z = true;
        }
        DebugKt.RECOVER_STACK_TRACES = z;
        DebugKt.COROUTINE_ID = new AtomicLong(0L);
    }

    // 去混淆评级： 低(40)
    private static final void assert(Function0 function00) {
    }

    public static final boolean getASSERTIONS_ENABLED() [...] // 潜在的解密器

    public static final AtomicLong getCOROUTINE_ID() {
        return DebugKt.COROUTINE_ID;
    }

    public static final boolean getDEBUG() [...] // 潜在的解密器

    public static final boolean getRECOVER_STACK_TRACES() [...] // 潜在的解密器

    public static void getRECOVER_STACK_TRACES$annotations() {
    }

    public static final void resetCoroutineId() {
        DebugKt.COROUTINE_ID.set(0L);
    }
}

