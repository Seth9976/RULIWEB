package kotlin.reflect.jvm.internal.impl.resolve;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;

public abstract class NonReportingOverrideStrategy extends OverridingStrategy {
    protected abstract void conflict(CallableMemberDescriptor arg1, CallableMemberDescriptor arg2);

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
    public void inheritanceConflict(CallableMemberDescriptor callableMemberDescriptor0, CallableMemberDescriptor callableMemberDescriptor1) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "first");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor1, "second");
        this.conflict(callableMemberDescriptor0, callableMemberDescriptor1);
    }

    @Override  // kotlin.reflect.jvm.internal.impl.resolve.OverridingStrategy
    public void overrideConflict(CallableMemberDescriptor callableMemberDescriptor0, CallableMemberDescriptor callableMemberDescriptor1) {
        Intrinsics.checkNotNullParameter(callableMemberDescriptor0, "fromSuper");
        Intrinsics.checkNotNullParameter(callableMemberDescriptor1, "fromCurrent");
        this.conflict(callableMemberDescriptor0, callableMemberDescriptor1);
    }
}

