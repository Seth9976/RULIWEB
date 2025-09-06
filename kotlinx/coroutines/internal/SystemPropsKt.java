package kotlinx.coroutines.internal;

import kotlin.Metadata;

@Metadata(d1 = {"kotlinx/coroutines/internal/SystemPropsKt__SystemPropsKt", "kotlinx/coroutines/internal/SystemPropsKt__SystemProps_commonKt"}, k = 4, mv = {1, 8, 0}, xi = 0x30)
public final class SystemPropsKt {
    // 去混淆评级： 低(20)
    public static final int getAVAILABLE_PROCESSORS() [...] // 潜在的解密器

    public static final int systemProp(String s, int v, int v1, int v2) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(s, v, v1, v2);
    }

    public static final long systemProp(String s, long v, long v1, long v2) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(s, v, v1, v2);
    }

    public static final String systemProp(String s) {
        return SystemPropsKt__SystemPropsKt.systemProp(s);
    }

    public static final String systemProp(String s, String s1) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(s, s1);
    }

    public static final boolean systemProp(String s, boolean z) {
        return SystemPropsKt__SystemProps_commonKt.systemProp(s, z);
    }
}

