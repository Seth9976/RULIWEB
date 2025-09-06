package androidx.work.impl.utils;

import androidx.work.Data;
import java.util.UUID;
import kotlin.jvm.functions.Function0;

public final class WorkProgressUpdater..ExternalSyntheticLambda0 implements Function0 {
    public final WorkProgressUpdater f$0;
    public final UUID f$1;
    public final Data f$2;

    public WorkProgressUpdater..ExternalSyntheticLambda0(WorkProgressUpdater workProgressUpdater0, UUID uUID0, Data data0) {
        this.f$0 = workProgressUpdater0;
        this.f$1 = uUID0;
        this.f$2 = data0;
    }

    @Override  // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.f$0.lambda$updateProgress$0$androidx-work-impl-utils-WorkProgressUpdater(this.f$1, this.f$2);
    }
}

