package kotlinx.coroutines.debug.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0000\n\u0000\u001A\b\u0010\b\u001A\u00020\tH\u0002\u001A\u000E\u0010\n\u001A\u00020\u0003*\u0004\u0018\u00010\u000BH\u0002\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0005\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"MAGIC", "", "MARKED_NULL", "Lkotlinx/coroutines/debug/internal/Marked;", "MARKED_TRUE", "MIN_CAPACITY", "REHASH", "Lkotlinx/coroutines/internal/Symbol;", "noImpl", "", "mark", "", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ConcurrentWeakMapKt {
    private static final int MAGIC = -1640531527;
    private static final Marked MARKED_NULL = null;
    private static final Marked MARKED_TRUE = null;
    private static final int MIN_CAPACITY = 16;
    private static final Symbol REHASH;

    static {
        ConcurrentWeakMapKt.REHASH = new Symbol("REHASH");
        ConcurrentWeakMapKt.MARKED_NULL = new Marked(null);
        ConcurrentWeakMapKt.MARKED_TRUE = new Marked(Boolean.TRUE);
    }

    private static final Marked mark(Object object0) {
        if(object0 == null) {
            return ConcurrentWeakMapKt.MARKED_NULL;
        }
        return Intrinsics.areEqual(object0, Boolean.TRUE) ? ConcurrentWeakMapKt.MARKED_TRUE : new Marked(object0);
    }

    private static final Void noImpl() {
        throw new UnsupportedOperationException("not implemented");
    }
}

