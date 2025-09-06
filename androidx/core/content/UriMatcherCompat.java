package androidx.core.content;

import android.content.UriMatcher;
import android.net.Uri;
import androidx.core.util.Predicate;

public class UriMatcherCompat {
    public static Predicate asPredicate(UriMatcher uriMatcher0) {
        return new UriMatcherCompat..ExternalSyntheticLambda0(uriMatcher0);
    }

    static boolean lambda$asPredicate$0(UriMatcher uriMatcher0, Uri uri0) {
        return uriMatcher0.match(uri0) != -1;
    }
}

