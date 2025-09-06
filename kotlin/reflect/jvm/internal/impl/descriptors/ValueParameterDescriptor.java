package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import kotlin.reflect.jvm.internal.impl.name.Name;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public interface ValueParameterDescriptor extends ParameterDescriptor, VariableDescriptor {
    public static final class DefaultImpls {
        public static boolean isLateInit(ValueParameterDescriptor valueParameterDescriptor0) [...] // Inlined contents
    }

    ValueParameterDescriptor copy(CallableDescriptor arg1, Name arg2, int arg3);

    boolean declaresDefaultValue();

    CallableDescriptor getContainingDeclaration();

    int getIndex();

    ValueParameterDescriptor getOriginal();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    Collection getOverriddenDescriptors();

    KotlinType getVarargElementType();

    boolean isCrossinline();

    boolean isNoinline();
}

