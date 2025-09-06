package kotlin.reflect.jvm.internal.impl.resolve.deprecation;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor.UserDataKey;

public final class DescriptorBasedDeprecationInfoKt {
    private static final UserDataKey DEPRECATED_FUNCTION_KEY;

    static {
        DescriptorBasedDeprecationInfoKt.DEPRECATED_FUNCTION_KEY = new DescriptorBasedDeprecationInfoKt.DEPRECATED_FUNCTION_KEY.1();
    }

    public static final UserDataKey getDEPRECATED_FUNCTION_KEY() {
        return DescriptorBasedDeprecationInfoKt.DEPRECATED_FUNCTION_KEY;
    }
}

