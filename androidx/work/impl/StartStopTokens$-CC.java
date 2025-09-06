package androidx.work.impl;

import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

public final class StartStopTokens.-CC {
    public static StartStopToken $default$remove(StartStopTokens _this, WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "spec");
        return _this.remove(WorkSpecKt.generationalId(workSpec0));
    }

    public static StartStopToken $default$tokenFor(StartStopTokens _this, WorkSpec workSpec0) {
        Intrinsics.checkNotNullParameter(workSpec0, "spec");
        return _this.tokenFor(WorkSpecKt.generationalId(workSpec0));
    }

    static {
    }

    @JvmStatic
    public static StartStopTokens create() {
        return StartStopTokens.Companion.create();
    }

    @JvmStatic
    public static StartStopTokens create(boolean z) {
        return StartStopTokens.Companion.create(z);
    }
}

