package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.List;

public interface ClassifierDescriptorWithTypeParameters extends ClassifierDescriptor, DeclarationDescriptorWithVisibility, MemberDescriptor, Substitutable {
    List getDeclaredTypeParameters();

    boolean isInner();
}

