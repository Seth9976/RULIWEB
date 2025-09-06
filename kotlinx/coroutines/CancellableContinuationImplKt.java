package kotlinx.coroutines;

import kotlin.Metadata;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u001A\u0019\u0010\u000E\u001A\u00020\u00012\u0006\u0010\t\u001A\u00020\u00012\u0006\u0010\f\u001A\u00020\u0001H\u0082\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0003\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\u0004\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001A\u00020\u00068\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0007\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000E\u0010\b\u001A\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0019\u0010\t\u001A\u00020\u0001*\u00020\u00018Â\u0002X\u0082\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000B\"\u0019\u0010\f\u001A\u00020\u0001*\u00020\u00018Â\u0002X\u0082\u0004¢\u0006\u0006\u001A\u0004\b\r\u0010\u000B¨\u0006\u000F"}, d2 = {"DECISION_SHIFT", "", "INDEX_MASK", "NO_INDEX", "RESUMED", "RESUME_TOKEN", "Lkotlinx/coroutines/internal/Symbol;", "SUSPENDED", "UNDECIDED", "decision", "getDecision", "(I)I", "index", "getIndex", "decisionAndIndex", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class CancellableContinuationImplKt {
    private static final int DECISION_SHIFT = 29;
    private static final int INDEX_MASK = 0x1FFFFFFF;
    private static final int NO_INDEX = 0x1FFFFFFF;
    private static final int RESUMED = 2;
    public static final Symbol RESUME_TOKEN = null;
    private static final int SUSPENDED = 1;
    private static final int UNDECIDED;

    static {
        CancellableContinuationImplKt.RESUME_TOKEN = new Symbol("RESUME_TOKEN");
    }

    private static final int decisionAndIndex(int v, int v1) {
        return (v << 29) + v1;
    }

    private static final int getDecision(int v) {
        return v >> 29;
    }

    private static final int getIndex(int v) {
        return v & 0x1FFFFFFF;
    }
}

