package androidx.core.util;

public final class Predicate..ExternalSyntheticLambda4 implements Predicate {
    public final Predicate f$0;
    public final Predicate f$1;

    public Predicate..ExternalSyntheticLambda4(Predicate predicate0, Predicate predicate1) {
        this.f$0 = predicate0;
        this.f$1 = predicate1;
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
        return Predicate.-CC.$private$lambda$and$0(this.f$0, this.f$1, object0);
    }
}

