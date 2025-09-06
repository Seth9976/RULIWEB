package androidx.work.impl;

import androidx.work.impl.utils.PreferenceUtils;
import kotlin.jvm.functions.Function0;

public final class WorkManagerImpl..ExternalSyntheticLambda0 implements Function0 {
    public final PreferenceUtils f$0;

    public WorkManagerImpl..ExternalSyntheticLambda0(PreferenceUtils preferenceUtils0) {
        this.f$0 = preferenceUtils0;
    }

    @Override  // kotlin.jvm.functions.Function0
    public final Object invoke() {
        return this.f$0.getLastCancelAllTimeMillis();
    }
}

