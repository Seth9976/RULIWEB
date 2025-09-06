package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public interface Substitutable {
    DeclarationDescriptorNonRoot substitute(TypeSubstitutor arg1);
}

