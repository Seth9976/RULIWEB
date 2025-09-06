package kotlin.reflect.jvm.internal.impl.resolve.descriptorUtil;

import kotlin.reflect.jvm.internal.impl.descriptors.ValueParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;

class DescriptorUtilsKt..Lambda.0 implements Neighbors {
    public static final DescriptorUtilsKt..Lambda.0 INSTANCE;

    static {
        DescriptorUtilsKt..Lambda.0.INSTANCE = new DescriptorUtilsKt..Lambda.0();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$Neighbors
    public Iterable getNeighbors(Object object0) {
        return DescriptorUtilsKt.accessor$DescriptorUtilsKt$lambda0(((ValueParameterDescriptor)object0));
    }
}

