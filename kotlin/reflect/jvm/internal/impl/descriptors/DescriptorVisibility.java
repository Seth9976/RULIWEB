package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.resolve.scopes.receivers.ReceiverValue;

public abstract class DescriptorVisibility {
    public final Integer compareTo(DescriptorVisibility descriptorVisibility0) {
        Intrinsics.checkNotNullParameter(descriptorVisibility0, "visibility");
        return this.getDelegate().compareTo(descriptorVisibility0.getDelegate());
    }

    public abstract Visibility getDelegate();

    public abstract String getInternalDisplayName();

    public final boolean isPublicAPI() {
        return this.getDelegate().isPublicAPI();
    }

    public abstract boolean isVisible(ReceiverValue arg1, DeclarationDescriptorWithVisibility arg2, DeclarationDescriptor arg3, boolean arg4);

    public abstract DescriptorVisibility normalize();

    @Override
    public final String toString() {
        return this.getDelegate().toString();
    }
}

