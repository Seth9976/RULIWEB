package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

public interface ExternalOverridabilityCondition {
    public static enum Contract {
        CONFLICTS_ONLY,
        SUCCESS_ONLY,
        BOTH;

    }

    public static enum Result {
        OVERRIDABLE,
        CONFLICT,
        INCOMPATIBLE,
        UNKNOWN;

    }

    Contract getContract();

    Result isOverridable(CallableDescriptor arg1, CallableDescriptor arg2, ClassDescriptor arg3);
}

