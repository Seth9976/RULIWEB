package androidx.core.util;

public final class Predicate..ExternalSyntheticLambda3 implements Predicate {
    public final Object f$0;

    public Predicate..ExternalSyntheticLambda3(Object object0) {
        this.f$0 = object0;
    }

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
        return this.f$0.equals(object0);
    }
}

