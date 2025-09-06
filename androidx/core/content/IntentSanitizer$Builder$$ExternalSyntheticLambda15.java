package androidx.core.content;

import android.content.ComponentName;
import androidx.core.util.Predicate.-CC;
import androidx.core.util.Predicate;

public final class IntentSanitizer.Builder..ExternalSyntheticLambda15 implements Predicate {
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
        return Builder.lambda$new$5(((ComponentName)object0));
    }
}

