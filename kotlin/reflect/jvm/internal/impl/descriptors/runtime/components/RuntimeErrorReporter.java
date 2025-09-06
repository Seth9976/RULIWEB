package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter;

public final class RuntimeErrorReporter implements ErrorReporter {
    public static final RuntimeErrorReporter INSTANCE;

    static {
        RuntimeErrorReporter.INSTANCE = new RuntimeErrorReporter();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
    public void reportCannotInferVisibility(CallableMemberDescriptor callableMemberDescriptor0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "descriptor");
        throw new IllegalStateException("Cannot infer visibility for " + callableMemberDescriptor0);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.serialization.deserialization.ErrorReporter
    public void reportIncompleteHierarchy(ClassDescriptor classDescriptor0, List list0) {
        Intrinsics.checkNotNullParameter(classDescriptor0, "descriptor");
        Intrinsics.checkNotNullParameter(list0, "unresolvedSuperClasses");
        throw new IllegalStateException("Incomplete hierarchy for class " + classDescriptor0.getName() + ", unresolved classes " + list0);
    }
}

