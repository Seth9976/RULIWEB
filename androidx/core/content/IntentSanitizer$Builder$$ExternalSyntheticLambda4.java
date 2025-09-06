package androidx.core.content;

import androidx.core.util.Predicate.-CC;
import androidx.core.util.Predicate;

public final class IntentSanitizer.Builder..ExternalSyntheticLambda4 implements Predicate {
    public final Class f$0;
    public final Predicate f$1;

    public IntentSanitizer.Builder..ExternalSyntheticLambda4(Class class0, Predicate predicate0) {
        this.f$0 = class0;
        this.f$1 = predicate0;
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
        return Builder.lambda$allowExtra$13(this.f$0, this.f$1, object0);
    }
}

