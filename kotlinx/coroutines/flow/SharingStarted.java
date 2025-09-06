package kotlinx.coroutines.flow;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\bæ\u0080\u0001\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\u001C\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006H&¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/flow/SharingStarted;", "", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "Companion", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface SharingStarted {
    @Metadata(d1 = {"\u0000\u001C\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001A\u0010\t\u001A\u00020\u00042\b\b\u0002\u0010\n\u001A\u00020\u000B2\b\b\u0002\u0010\f\u001A\u00020\u000BR\u0011\u0010\u0003\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001A\u00020\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\u0006¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/flow/SharingStarted$Companion;", "", "()V", "Eagerly", "Lkotlinx/coroutines/flow/SharingStarted;", "getEagerly", "()Lkotlinx/coroutines/flow/SharingStarted;", "Lazily", "getLazily", "WhileSubscribed", "stopTimeoutMillis", "", "replayExpirationMillis", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE;
        private static final SharingStarted Eagerly;
        private static final SharingStarted Lazily;

        static {
            Companion.$$INSTANCE = new Companion();
            Companion.Eagerly = new StartedEagerly();
            Companion.Lazily = new StartedLazily();
        }

        public final SharingStarted WhileSubscribed(long v, long v1) {
            return new StartedWhileSubscribed(v, v1);
        }

        public static SharingStarted WhileSubscribed$default(Companion sharingStarted$Companion0, long v, long v1, int v2, Object object0) {
            if((v2 & 1) != 0) {
                v = 0L;
            }
            if((v2 & 2) != 0) {
                v1 = 0x7FFFFFFFFFFFFFFFL;
            }
            return sharingStarted$Companion0.WhileSubscribed(v, v1);
        }

        public final SharingStarted getEagerly() {
            return Companion.Eagerly;
        }

        public final SharingStarted getLazily() {
            return Companion.Lazily;
        }
    }

    public static final Companion Companion;

    static {
        SharingStarted.Companion = Companion.$$INSTANCE;
    }

    Flow command(StateFlow arg1);
}

