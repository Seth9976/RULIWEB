package androidx.core.content;

import android.content.UriMatcher;
import android.net.Uri;
import androidx.core.util.Predicate.-CC;
import androidx.core.util.Predicate;

public final class UriMatcherCompat..ExternalSyntheticLambda0 implements Predicate {
    public final UriMatcher f$0;

    public UriMatcherCompat..ExternalSyntheticLambda0(UriMatcher uriMatcher0) {
        this.f$0 = uriMatcher0;
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
        return UriMatcherCompat.lambda$asPredicate$0(this.f$0, ((Uri)object0));
    }
}

