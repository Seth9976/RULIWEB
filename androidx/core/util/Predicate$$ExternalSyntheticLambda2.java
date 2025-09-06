package androidx.core.util;

import kotlin.UByte..ExternalSyntheticBackport0;

public final class Predicate..ExternalSyntheticLambda2 implements Predicate {
    @Override  // androidx.core.util.Predicate
    public Predicate and(Predicate predicate0) {
        return Predicate.-CC.$default$and(this, predicate0);
    }

    @Override  // androidx.core.util.Predicate
    public Predicate negate() {
        return Predicate.-CC.$default$negate(this);
    }

    @Override  // androidx.core.util.Predicate
    public Predicate or(Predicate predicate0) {
        return Predicate.-CC.$default$or(this, predicate0);
    }

    @Override  // androidx.core.util.Predicate
    public final boolean test(Object object0) {
        return UByte..ExternalSyntheticBackport0.m(object0);
    }
}

