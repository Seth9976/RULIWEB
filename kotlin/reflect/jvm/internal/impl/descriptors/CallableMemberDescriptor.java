package kotlin.reflect.jvm.internal.impl.descriptors;

import java.util.Collection;

public interface CallableMemberDescriptor extends CallableDescriptor, MemberDescriptor {
    public static enum Kind {
        DECLARATION,
        FAKE_OVERRIDE,
        DELEGATION,
        SYNTHESIZED;

        public boolean isReal() {
            return this != Kind.FAKE_OVERRIDE;
        }
    }

    CallableMemberDescriptor copy(DeclarationDescriptor arg1, Modality arg2, DescriptorVisibility arg3, Kind arg4, boolean arg5);

    Kind getKind();

    CallableMemberDescriptor getOriginal();

    @Override  // kotlin.reflect.jvm.internal.impl.descriptors.CallableDescriptor
    Collection getOverriddenDescriptors();

    void setOverriddenDescriptors(Collection arg1);
}

