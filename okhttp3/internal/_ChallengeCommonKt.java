package okhttp3.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Challenge;

@Metadata(d1 = {"\u0000\u001E\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\u0014\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004\u001A\n\u0010\u0005\u001A\u00020\u0006*\u00020\u0002\u001A\n\u0010\u0007\u001A\u00020\b*\u00020\u0002¨\u0006\t"}, d2 = {"commonEquals", "", "Lokhttp3/Challenge;", "other", "", "commonHashCode", "", "commonToString", "", "okhttp"}, k = 2, mv = {1, 7, 1}, xi = 0x30)
public final class _ChallengeCommonKt {
    public static final boolean commonEquals(Challenge challenge0, Object object0) {
        Intrinsics.checkNotNullParameter(challenge0, "<this>");
        return object0 instanceof Challenge && Intrinsics.areEqual(((Challenge)object0).scheme(), challenge0.scheme()) && Intrinsics.areEqual(((Challenge)object0).authParams(), challenge0.authParams());
    }

    public static final int commonHashCode(Challenge challenge0) {
        Intrinsics.checkNotNullParameter(challenge0, "<this>");
        return (challenge0.scheme().hashCode() + 899) * 0x1F + challenge0.authParams().hashCode();
    }

    public static final String commonToString(Challenge challenge0) {
        Intrinsics.checkNotNullParameter(challenge0, "<this>");
        return challenge0.scheme() + " authParams=" + challenge0.authParams();
    }
}

