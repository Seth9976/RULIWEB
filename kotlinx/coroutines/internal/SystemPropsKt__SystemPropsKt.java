package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000E\n\u0002\b\u0002\u001A\u0012\u0010\u0004\u001A\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001A\u00020\u0005H\u0000\"\u0014\u0010\u0000\u001A\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0002\u0010\u0003¨\u0006\u0007"}, d2 = {"AVAILABLE_PROCESSORS", "", "getAVAILABLE_PROCESSORS", "()I", "systemProp", "", "propertyName", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/internal/SystemPropsKt")
final class SystemPropsKt__SystemPropsKt {
    private static final int AVAILABLE_PROCESSORS;

    static {
        SystemPropsKt__SystemPropsKt.AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    }

    public static final int getAVAILABLE_PROCESSORS() [...] // 潜在的解密器

    public static final String systemProp(String s) {
        try {
            return System.getProperty(s);
        }
        catch(SecurityException unused_ex) {
            return null;
        }
    }
}

