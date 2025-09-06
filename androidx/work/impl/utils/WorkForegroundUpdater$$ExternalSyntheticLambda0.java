package androidx.work.impl.utils;

import android.content.Context;
import androidx.work.ForegroundInfo;
import java.util.UUID;
import kotlin.jvm.functions.Function0;

public final class WorkForegroundUpdater..ExternalSyntheticLambda0 implements Function0 {
    public final WorkForegroundUpdater f$0;
    public final UUID f$1;
    public final ForegroundInfo f$2;
    public final Context f$3;

    public WorkForegroundUpdater..ExternalSyntheticLambda0(WorkForegroundUpdater workForegroundUpdater0, UUID uUID0, ForegroundInfo foregroundInfo0, Context context0) {
        this.f$0 = workForegroundUpdater0;
        this.f$1 = uUID0;
        this.f$2 = foregroundInfo0;
        this.f$3 = context0;
    }

    @Override  // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.f$0.lambda$setForegroundAsync$0$androidx-work-impl-utils-WorkForegroundUpdater(this.f$1, this.f$2, this.f$3);
    }
}

