package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;

class DescriptorUtilsKt..Lambda.1 implements Neighbors {
    private final boolean arg$0;

    public DescriptorUtilsKt..Lambda.1(boolean z) {
        this.arg$0 = z;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$Neighbors
    public Iterable getNeighbors(Object object0) {
        return DescriptorUtilsKt.accessor$DescriptorUtilsKt$lambda1(this.arg$0, ((CallableMemberDescriptor)object0));
    }
}

