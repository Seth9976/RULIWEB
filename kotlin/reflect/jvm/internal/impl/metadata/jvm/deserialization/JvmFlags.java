package kotlin.reflect.jvm.internal.impl.metadata.jvm.deserialization;

import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.BooleanFlagField;
import kotlin.reflect.jvm.internal.impl.metadata.deserialization.Flags.FlagField;

public final class JvmFlags {
    public static final JvmFlags INSTANCE;
    private static final BooleanFlagField IS_COMPILED_IN_COMPATIBILITY_MODE;
    private static final BooleanFlagField IS_COMPILED_IN_JVM_DEFAULT_MODE;
    private static final BooleanFlagField IS_MOVED_FROM_INTERFACE_COMPANION;

    static {
        JvmFlags.INSTANCE = new JvmFlags();
        JvmFlags.IS_MOVED_FROM_INTERFACE_COMPANION = FlagField.booleanFirst();
        BooleanFlagField flags$BooleanFlagField0 = FlagField.booleanFirst();
        JvmFlags.IS_COMPILED_IN_JVM_DEFAULT_MODE = flags$BooleanFlagField0;
        JvmFlags.IS_COMPILED_IN_COMPATIBILITY_MODE = FlagField.booleanAfter(flags$BooleanFlagField0);
    }

    public final BooleanFlagField getIS_MOVED_FROM_INTERFACE_COMPANION() {
        return JvmFlags.IS_MOVED_FROM_INTERFACE_COMPANION;
    }
}

