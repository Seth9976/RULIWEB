package androidx.core.content;

import android.content.ComponentName;
import androidx.core.util.Predicate.-CC;
import androidx.core.util.Predicate;

public final class IntentSanitizer.Builder..ExternalSyntheticLambda5 implements Predicate {
    public final ComponentName f$0;

    public IntentSanitizer.Builder..ExternalSyntheticLambda5(ComponentName componentName0) {
        this.f$0 = componentName0;
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
        return this.f$0.equals(((ComponentName)object0));
    }
}

