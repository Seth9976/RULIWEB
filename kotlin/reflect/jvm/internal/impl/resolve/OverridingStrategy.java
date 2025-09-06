package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;

public abstract class OverridingStrategy {
    public abstract void addFakeOverride(CallableMemberDescriptor arg1);

    public abstract void inheritanceConflict(CallableMemberDescriptor arg1, CallableMemberDescriptor arg2);

    public abstract void overrideConflict(CallableMemberDescriptor arg1, CallableMemberDescriptor arg2);

    public void setOverriddenDescriptors(CallableMemberDescriptor callableMemberDescriptor0, Collection collection0) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "member");
        Intrinsics.checkNotNullParameter(collection0, "overridden");
        callableMemberDescriptor0.setOverriddenDescriptors(collection0);
    }
}

