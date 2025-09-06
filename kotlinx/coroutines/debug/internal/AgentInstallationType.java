package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001A\u0010\u0003\u001A\u00020\u0004X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/debug/internal/AgentInstallationType;", "", "()V", "isInstalledStatically", "", "isInstalledStatically$kotlinx_coroutines_core", "()Z", "setInstalledStatically$kotlinx_coroutines_core", "(Z)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class AgentInstallationType {
    public static final AgentInstallationType INSTANCE;
    private static boolean isInstalledStatically;

    static {
        AgentInstallationType.INSTANCE = new AgentInstallationType();
    }

    public final boolean isInstalledStatically$kotlinx_coroutines_core() [...] // 潜在的解密器

    public final void setInstalledStatically$kotlinx_coroutines_core(boolean z) {
        AgentInstallationType.isInstalledStatically = z;
    }
}

