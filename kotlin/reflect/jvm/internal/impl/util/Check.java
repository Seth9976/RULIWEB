package kotlin.reflect.jvm.internal.impl.util;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.FunctionDescriptor;

public interface Check {
    public static final class DefaultImpls {
        public static String invoke(Check check0, FunctionDescriptor functionDescriptor0) {
            Intrinsics.checkNotNullParameter(functionDescriptor0, "functionDescriptor");
            return check0.check(functionDescriptor0) ? null : check0.getDescription();
        }
    }

    boolean check(FunctionDescriptor arg1);

    String getDescription();

    String invoke(FunctionDescriptor arg1);
}

