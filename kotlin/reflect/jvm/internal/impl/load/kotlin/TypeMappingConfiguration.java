package kotlin.reflect.jvm.internal.impl.load.kotlin;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public interface TypeMappingConfiguration {
    KotlinType commonSupertype(Collection arg1);

    String getPredefinedFullInternalNameForClass(ClassDescriptor arg1);

    String getPredefinedInternalNameForClass(ClassDescriptor arg1);

    Object getPredefinedTypeForClass(ClassDescriptor arg1);

    KotlinType preprocessType(KotlinType arg1);

    void processErrorType(KotlinType arg1, ClassDescriptor arg2);
}

