package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.types.KotlinType;

public interface CallableDescriptor extends DeclarationDescriptorNonRoot, DeclarationDescriptorWithVisibility, Substitutable {
    public interface UserDataKey {
    }

    List getContextReceiverParameters();

    ReceiverParameterDescriptor getDispatchReceiverParameter();

    ReceiverParameterDescriptor getExtensionReceiverParameter();

    CallableDescriptor getOriginal();

    Collection getOverriddenDescriptors();

    KotlinType getReturnType();

    List getTypeParameters();

    Object getUserData(UserDataKey arg1);

    List getValueParameters();

    boolean hasSynthesizedParameterNames();
}

