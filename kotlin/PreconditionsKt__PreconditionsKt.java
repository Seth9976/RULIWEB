package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0004\u001A\u001C\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001A\u0002\u0010\u0001\u001A-\u0010\u0000\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001A\u0002\u0010\u0001\u001A/\u0010\u0007\u001A\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001A\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001A\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001A@\u0010\u0007\u001A\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001A\u0004\u0018\u0001H\b2\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001A\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u001A\u0011\u0010\u000B\u001A\u00020\f2\u0006\u0010\r\u001A\u00020\u0006H\u0087\b\u001A\u001C\u0010\u000E\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003H\u0087\b\u0082\u0002\b\n\u0006\b\u0000\u001A\u0002\u0010\u0001\u001A-\u0010\u000E\u001A\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\b\n\u0006\b\u0000\u001A\u0002\u0010\u0001\u001A/\u0010\u000F\u001A\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001A\u0004\u0018\u0001H\bH\u0087\b\u0082\u0002\n\n\b\b\u0000\u001A\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\t\u001A@\u0010\u000F\u001A\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00062\b\u0010\u0002\u001A\u0004\u0018\u0001H\b2\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0000\u001A\u0004\b\u0003\u0010\u0001¢\u0006\u0002\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0010"}, d2 = {"check", "", "value", "", "lazyMessage", "Lkotlin/Function0;", "", "checkNotNull", "T", "(Ljava/lang/Object;)Ljava/lang/Object;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "error", "", "message", "require", "requireNotNull", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/PreconditionsKt")
class PreconditionsKt__PreconditionsKt extends PreconditionsKt__AssertionsJVMKt {
    private static final void check(boolean z) {
        if(!z) {
            throw new IllegalStateException("Check failed.");
        }
    }

    private static final void check(boolean z, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "lazyMessage");
        if(!z) {
            throw new IllegalStateException(function00.invoke().toString());
        }
    }

    private static final Object checkNotNull(Object object0) {
        if(object0 == null) {
            throw new IllegalStateException("Required value was null.");
        }
        return object0;
    }

    private static final Object checkNotNull(Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "lazyMessage");
        if(object0 == null) {
            throw new IllegalStateException(function00.invoke().toString());
        }
        return object0;
    }

    private static final Void error(Object object0) {
        Intrinsics.checkNotNullParameter(object0, "message");
        throw new IllegalStateException(object0.toString());
    }

    private static final void require(boolean z) {
        if(!z) {
            throw new IllegalArgumentException("Failed requirement.");
        }
    }

    private static final void require(boolean z, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "lazyMessage");
        if(!z) {
            throw new IllegalArgumentException(function00.invoke().toString());
        }
    }

    private static final Object requireNotNull(Object object0) {
        if(object0 == null) {
            throw new IllegalArgumentException("Required value was null.");
        }
        return object0;
    }

    private static final Object requireNotNull(Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "lazyMessage");
        if(object0 == null) {
            throw new IllegalArgumentException(function00.invoke().toString());
        }
        return object0;
    }
}

