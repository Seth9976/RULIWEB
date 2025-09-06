package kotlin.reflect.jvm.internal.impl.descriptors.deserialization;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.SimpleFunctionDescriptor;

public interface PlatformDependentDeclarationFilter {
    public static final class All implements PlatformDependentDeclarationFilter {
        public static final All INSTANCE;

        static {
            All.INSTANCE = new All();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter
        public boolean isFunctionAvailable(ClassDescriptor classDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "functionDescriptor");
            return true;
        }
    }

    public static final class NoPlatformDependent implements PlatformDependentDeclarationFilter {
        public static final NoPlatformDependent INSTANCE;

        static {
            NoPlatformDependent.INSTANCE = new NoPlatformDependent();
        }

        @Override  // kotlin.reflect.jvm.internal.impl.descriptors.deserialization.PlatformDependentDeclarationFilter
        public boolean isFunctionAvailable(ClassDescriptor classDescriptor0, SimpleFunctionDescriptor simpleFunctionDescriptor0) {
            Intrinsics.checkNotNullParameter(classDescriptor0, "classDescriptor");
            Intrinsics.checkNotNullParameter(simpleFunctionDescriptor0, "functionDescriptor");
            return !simpleFunctionDescriptor0.getAnnotations().hasAnnotation(PlatformDependentDeclarationFilterKt.getPLATFORM_DEPENDENT_ANNOTATION_FQ_NAME());
        }
    }

    boolean isFunctionAvailable(ClassDescriptor arg1, SimpleFunctionDescriptor arg2);
}

