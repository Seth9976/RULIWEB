package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.time.Duration;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001A+\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0006\u0010\u0007\u0082\u0002\u000B\n\u0005\b¡\u001E0\u0001\n\u0002\b\u0019¨\u0006\b"}, d2 = {"WhileSubscribed", "Lkotlinx/coroutines/flow/SharingStarted;", "Lkotlinx/coroutines/flow/SharingStarted$Companion;", "stopTimeout", "Lkotlin/time/Duration;", "replayExpiration", "WhileSubscribed-5qebJ5I", "(Lkotlinx/coroutines/flow/SharingStarted$Companion;JJ)Lkotlinx/coroutines/flow/SharingStarted;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SharingStartedKt {
    public static final SharingStarted WhileSubscribed-5qebJ5I(Companion sharingStarted$Companion0, long v, long v1) {
        return new StartedWhileSubscribed(Duration.getInWholeMilliseconds-impl(v), Duration.getInWholeMilliseconds-impl(v1));
    }

    public static SharingStarted WhileSubscribed-5qebJ5I$default(Companion sharingStarted$Companion0, long v, long v1, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0L;
        }
        if((v2 & 2) != 0) {
            v1 = 0x7FFFFFFFFFFFFFFFL;
        }
        return SharingStartedKt.WhileSubscribed-5qebJ5I(sharingStarted$Companion0, v, v1);
    }
}

