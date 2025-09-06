package kotlin.reflect.full;

import kotlin.reflect.KClass;
import kotlin.reflect.KProperty1;
import kotlin.reflect.jvm.internal.impl.utils.DFS.Neighbors;

class KClasses..Lambda.1 implements Neighbors {
    private final KProperty1 arg$0;

    public KClasses..Lambda.1(KProperty1 kProperty10) {
        this.arg$0 = kProperty10;
    }

    @Override  // kotlin.reflect.jvm.internal.impl.utils.DFS$Neighbors
    public Iterable getNeighbors(Object object0) {
        return KClasses.accessor$KClasses$lambda1(this.arg$0, ((KClass)object0));
    }
}

