package kotlin.reflect.jvm.internal.impl.serialization.deserialization;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;

public interface ErrorReporter {
    public static final ErrorReporter DO_NOTHING;

    static {
        ErrorReporter.DO_NOTHING = new ErrorReporter() {
            private static void $$$reportNull$$$0(int v) {
                Object[] arr_object = new Object[3];
                arr_object[0] = v == 1 ? "unresolvedSuperClasses" : "descriptor";
                arr_object[1] = "kotlin/reflect/jvm/internal/impl/serialization/deserialization/ErrorReporter$1";
                arr_object[2] = v == 2 ? "reportCannotInferVisibility" : "reportIncompleteHierarchy";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter \'%s\' of %s.%s must not be null", arr_object));
            }

            @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
            public void reportCannotInferVisibility(CallableMemberDescriptor callableMemberDescriptor0) {
                if(callableMemberDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter.1.$$$reportNull$$$0(2);
                }
            }

            @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
            public void reportIncompleteHierarchy(ClassDescriptor classDescriptor0, List list0) {
                if(classDescriptor0 == null) {
                    kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter.1.$$$reportNull$$$0(0);
                }
                if(list0 == null) {
                    kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter.1.$$$reportNull$$$0(1);
                }
            }
        };
    }

    void reportCannotInferVisibility(CallableMemberDescriptor arg1);

    void reportIncompleteHierarchy(ClassDescriptor arg1, List arg2);
}

