package kotlin.reflect.full;

import kotlin.reflect.KType;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;

class KClasses..Lambda.0 implements Neighbors {
    public static final KClasses..Lambda.0 INSTANCE;

    static {
        KClasses..Lambda.0.INSTANCE = new KClasses..Lambda.0();
    }

    @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$Neighbors
    public Iterable getNeighbors(Object object0) {
        return KClasses.accessor$KClasses$lambda0(((KType)object0));
    }
}

