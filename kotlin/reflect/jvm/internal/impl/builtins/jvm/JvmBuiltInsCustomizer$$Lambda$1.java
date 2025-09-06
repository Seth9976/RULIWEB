package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;

class JvmBuiltInsCustomizer..Lambda.1 implements Neighbors {
    private final JvmBuiltInsCustomizer arg$0;

    public JvmBuiltInsCustomizer..Lambda.1(JvmBuiltInsCustomizer jvmBuiltInsCustomizer0) {
        this.arg$0 = jvmBuiltInsCustomizer0;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$Neighbors
    public Iterable getNeighbors(Object object0) {
        return JvmBuiltInsCustomizer.accessor$JvmBuiltInsCustomizer$lambda1(this.arg$0, ((ClassDescriptor)object0));
    }
}

