package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.reflect.jvm.internal.impl.descriptors.CallableMemberDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;

class JvmBuiltInsCustomizer..Lambda.0 implements Neighbors {
    public static final JvmBuiltInsCustomizer..Lambda.0 INSTANCE;

    static {
        JvmBuiltInsCustomizer..Lambda.0.INSTANCE = new JvmBuiltInsCustomizer..Lambda.0();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$Neighbors
    public Iterable getNeighbors(Object object0) {
        return JvmBuiltInsCustomizer.accessor$JvmBuiltInsCustomizer$lambda0(((CallableMemberDescriptor)object0));
    }
}

