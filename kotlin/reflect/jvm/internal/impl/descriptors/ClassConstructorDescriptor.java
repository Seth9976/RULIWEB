package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public interface ClassConstructorDescriptor extends ConstructorDescriptor {
    ClassConstructorDescriptor getOriginal();

    ClassConstructorDescriptor substitute(TypeSubstitutor arg1);
}

