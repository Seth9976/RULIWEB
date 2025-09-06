package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public interface ConstructorDescriptor extends FunctionDescriptor {
    ClassDescriptor getConstructedClass();

    ClassifierDescriptorWithTypeParameters getContainingDeclaration();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    KotlinType getReturnType();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    List getTypeParameters();

    boolean isPrimary();

    ConstructorDescriptor substitute(TypeSubstitutor arg1);
}

