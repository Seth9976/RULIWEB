package kotlinx.coroutines.debug;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.AgentInstallationType;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import sun.misc.Signal;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001A\u00020\u0006H\u0002J\u001A\u0010\u0007\u001A\u00020\u00062\b\u0010\b\u001A\u0004\u0018\u00010\t2\u0006\u0010\n\u001A\u00020\u000BH\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/debug/AgentPremain;", "", "()V", "enableCreationStackTraces", "", "installSignalHandler", "", "premain", "args", "", "instrumentation", "Ljava/lang/instrument/Instrumentation;", "DebugProbesTransformer", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class AgentPremain {
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J<\u0010\u0003\u001A\u0004\u0018\u00010\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001A\u00020\b2\f\u0010\t\u001A\b\u0012\u0002\b\u0003\u0018\u00010\n2\u0006\u0010\u000B\u001A\u00020\f2\b\u0010\r\u001A\u0004\u0018\u00010\u0004H\u0016¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/debug/AgentPremain$DebugProbesTransformer;", "Ljava/lang/instrument/ClassFileTransformer;", "()V", "transform", "", "loader", "Ljava/lang/ClassLoader;", "className", "", "classBeingRedefined", "Ljava/lang/Class;", "protectionDomain", "Ljava/security/ProtectionDomain;", "classfileBuffer", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class DebugProbesTransformer implements ClassFileTransformer {
        public static final DebugProbesTransformer INSTANCE;

        static {
            DebugProbesTransformer.INSTANCE = new DebugProbesTransformer();
        }

        public byte[] transform(ClassLoader classLoader0, String s, Class class0, ProtectionDomain protectionDomain0, byte[] arr_b) {
            if(classLoader0 != null && Intrinsics.areEqual(s, "kotlin/coroutines/jvm/internal/DebugProbesKt")) {
                AgentInstallationType.INSTANCE.setInstalledStatically$kotlinx_coroutines_core(true);
                return ByteStreamsKt.readBytes(classLoader0.getResourceAsStream("DebugProbesKt.bin"));
            }
            return null;
        }
    }

    public static final AgentPremain INSTANCE;
    private static final boolean enableCreationStackTraces;

    public static void $r8$lambda$qtwhjy3HAlNEjQH7oAL6W8vp2zY(Signal signal0) {
        AgentPremain.installSignalHandler$lambda$1(signal0);
    }

    static {
        Boolean boolean1;
        AgentPremain.INSTANCE = new AgentPremain();
        Boolean boolean0 = null;
        try {
            String s = System.getProperty("kotlinx.coroutines.debug.enable.creation.stack.trace");
            boolean1 = s == null ? null : Boolean.valueOf(Boolean.parseBoolean(s));
        }
        catch(Throwable throwable0) {
            boolean1 = Result.constructor-impl(ResultKt.createFailure(throwable0));
        }
        if(!Result.isFailure-impl(boolean1)) {
            boolean0 = boolean1;
        }
        AgentPremain.enableCreationStackTraces = boolean0 == null ? false : boolean0.booleanValue();
    }

    private final void installSignalHandler() {
        try {
            Signal.handle(new Signal("TRAP"), new AgentPremain..ExternalSyntheticLambda0());
        }
        catch(Throwable unused_ex) {
        }
    }

    private static final void installSignalHandler$lambda$1(Signal signal0) {
        if(DebugProbesImpl.INSTANCE.isInstalled$kotlinx_coroutines_debug()) {
            DebugProbesImpl.INSTANCE.dumpCoroutines(System.out);
            return;
        }
        System.out.println("Cannot perform coroutines dump, debug probes are disabled");
    }

    @JvmStatic
    public static final void premain(String s, Instrumentation instrumentation0) {
        AgentInstallationType.INSTANCE.setInstalledStatically$kotlinx_coroutines_core(true);
        instrumentation0.addTransformer(DebugProbesTransformer.INSTANCE);
        DebugProbesImpl.INSTANCE.setEnableCreationStackTraces$kotlinx_coroutines_core(AgentPremain.enableCreationStackTraces);
        DebugProbesImpl.INSTANCE.install$kotlinx_coroutines_core();
        AgentPremain.INSTANCE.installSignalHandler();
    }
}

