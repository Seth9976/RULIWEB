package kotlin.reflect.jvm.internal.impl.descriptors;

public interface MemberDescriptor extends DeclarationDescriptorNonRoot, DeclarationDescriptorWithVisibility {
    Modality getModality();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptorWithVisibility
    DescriptorVisibility getVisibility();

    boolean isActual();

    boolean isExpect();

    boolean isExternal();
}

