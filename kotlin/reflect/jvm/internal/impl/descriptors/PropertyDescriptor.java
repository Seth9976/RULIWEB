package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;
import java.util.List;
import kotlin.reflect.jvm.internal.impl.types.TypeSubstitutor;

public interface PropertyDescriptor extends CallableMemberDescriptor, VariableDescriptorWithAccessors {
    List getAccessors();

    FieldDescriptor getBackingField();

    FieldDescriptor getDelegateField();

    PropertyGetterDescriptor getGetter();

    PropertyDescriptor getOriginal();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor, kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    Collection getOverriddenDescriptors();

    PropertySetterDescriptor getSetter();

    PropertyDescriptor substitute(TypeSubstitutor arg1);
}

