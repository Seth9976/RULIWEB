package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0003\bf\u0018\u0000 \u000E2\u00020\u0001:\u0001\u000EJ\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0012\u0010\u0006\u001A\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0012\u0010\u0006\u001A\u0004\u0018\u00010\u00072\u0006\u0010\b\u001A\u00020\tH\u0016J\u0016\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\u00070\n2\u0006\u0010\u000B\u001A\u00020\fH&J\u0010\u0010\r\u001A\u00020\u00072\u0006\u0010\u0004\u001A\u00020\u0005H&J\u0010\u0010\r\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\tH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000FÀ\u0006\u0001"}, d2 = {"Landroidx/work/impl/StartStopTokens;", "", "contains", "", "id", "Landroidx/work/impl/model/WorkGenerationalId;", "remove", "Landroidx/work/impl/StartStopToken;", "spec", "Landroidx/work/impl/model/WorkSpec;", "", "workSpecId", "", "tokenFor", "Companion", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public interface StartStopTokens {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001A\u00020\u00042\b\b\u0002\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/work/impl/StartStopTokens$Companion;", "", "()V", "create", "Landroidx/work/impl/StartStopTokens;", "synchronized", "", "work-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        static final Companion $$INSTANCE;

        static {
            Companion.$$INSTANCE = new Companion();
        }

        @JvmStatic
        public final StartStopTokens create() {
            return Companion.create$default(this, false, 1, null);
        }

        @JvmStatic
        public final StartStopTokens create(boolean z) {
            StartStopTokensImpl startStopTokensImpl0 = new StartStopTokensImpl();
            return z ? new SynchronizedStartStopTokensImpl(startStopTokensImpl0) : startStopTokensImpl0;
        }

        public static StartStopTokens create$default(Companion startStopTokens$Companion0, boolean z, int v, Object object0) {
            if((v & 1) != 0) {
                z = true;
            }
            return startStopTokens$Companion0.create(z);
        }
    }

    public static final Companion Companion;

    static {
        StartStopTokens.Companion = Companion.$$INSTANCE;
    }

    boolean contains(WorkGenerationalId arg1);

    StartStopToken remove(WorkGenerationalId arg1);

    StartStopToken remove(WorkSpec arg1);

    List remove(String arg1);

    StartStopToken tokenFor(WorkGenerationalId arg1);

    StartStopToken tokenFor(WorkSpec arg1);
}

