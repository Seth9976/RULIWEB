package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

public final class PossiblyInnerType {
    private final List arguments;
    private final ClassifierDescriptorWithTypeParameters classifierDescriptor;
    private final PossiblyInnerType outerType;

    public PossiblyInnerType(ClassifierDescriptorWithTypeParameters classifierDescriptorWithTypeParameters0, List list0, PossiblyInnerType possiblyInnerType0) {
        Intrinsics.checkNotNullParameter(classifierDescriptorWithTypeParameters0, "classifierDescriptor");
        Intrinsics.checkNotNullParameter(list0, "arguments");
        super();
        this.classifierDescriptor = classifierDescriptorWithTypeParameters0;
        this.arguments = list0;
        this.outerType = possiblyInnerType0;
    }

    public final List getArguments() {
        return this.arguments;
    }

    public final ClassifierDescriptorWithTypeParameters getClassifierDescriptor() {
        return this.classifierDescriptor;
    }

    public final PossiblyInnerType getOuterType() {
        return this.outerType;
    }
}

