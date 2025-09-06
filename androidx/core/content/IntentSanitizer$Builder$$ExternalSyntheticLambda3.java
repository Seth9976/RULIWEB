package androidx.core.content;

import android.net.Uri;
import androidx.core.util.Predicate.-CC;
import androidx.core.util.Predicate;

public final class IntentSanitizer.Builder..ExternalSyntheticLambda3 implements Predicate {
    public final String f$0;

    public IntentSanitizer.Builder..ExternalSyntheticLambda3(String s) {
        this.f$0 = s;
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
        return Builder.lambda$allowDataWithAuthority$8(this.f$0, ((Uri)object0));
    }
}

