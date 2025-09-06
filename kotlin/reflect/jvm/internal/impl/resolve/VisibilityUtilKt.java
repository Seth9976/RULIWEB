package kotlin.reflect.jvm.internal.impl.resolve;

import java.util.Collection;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.DescriptorVisibilities;

public final class VisibilityUtilKt {
    public static final CallableMemberDescriptor findMemberWithMaxVisibility(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "descriptors");
        collection0.isEmpty();
        CallableMemberDescriptor callableMemberDescriptor0 = null;
        for(Object object0: collection0) {
            CallableMemberDescriptor callableMemberDescriptor1 = (CallableMemberDescriptor)object0;
            if(callableMemberDescriptor0 == null) {
                callableMemberDescriptor0 = callableMemberDescriptor1;
            }
            else {
                Integer integer0 = DescriptorVisibilities.compare(callableMemberDescriptor0.getVisibility(), callableMemberDescriptor1.getVisibility());
                if(integer0 != null && ((int)integer0) < 0) {
                    callableMemberDescriptor0 = callableMemberDescriptor1;
                }
            }
        }
        Intrinsics.checkNotNull(callableMemberDescriptor0);
        return callableMemberDescriptor0;
    }
}

