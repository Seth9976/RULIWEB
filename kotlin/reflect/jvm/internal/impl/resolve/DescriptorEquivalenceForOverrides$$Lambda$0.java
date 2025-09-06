package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor;
import kotlin.reflect.jvm.internal.impl.types.TypeConstructor;
import kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker.TypeConstructorEquality;

class DescriptorEquivalenceForOverrides..Lambda.0 implements TypeConstructorEquality {
    private final boolean arg$0;
    private final CallableDescriptor arg$1;
    private final CallableDescriptor arg$2;

    public DescriptorEquivalenceForOverrides..Lambda.0(boolean z, CallableDescriptor callableDescriptor0, CallableDescriptor callableDescriptor1) {
        this.arg$0 = z;
        this.arg$1 = callableDescriptor0;
        this.arg$2 = callableDescriptor1;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.types.checker.KotlinTypeChecker$TypeConstructorEquality
    public boolean equals(TypeConstructor typeConstructor0, TypeConstructor typeConstructor1) {
        return DescriptorEquivalenceForOverrides.accessor$DescriptorEquivalenceForOverrides$lambda0(this.arg$0, this.arg$1, this.arg$2, typeConstructor0, typeConstructor1);
    }
}

