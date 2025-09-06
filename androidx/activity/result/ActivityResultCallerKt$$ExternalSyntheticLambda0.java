package androidx.activity.result;

import kotlin.jvm.functions.Function1;

public final class ActivityResultCallerKt..ExternalSyntheticLambda0 implements ActivityResultCallback {
    public final Function1 f$0;

    public ActivityResultCallerKt..ExternalSyntheticLambda0(Function1 function10) {
        this.f$0 = function10;
    }

    @Override  // androidx.activity.result.ActivityResultCallback
    public final void onActivityResult(Object object0) {
        ActivityResultCallerKt.registerForActivityResult$lambda$0(this.f$0, object0);
    }
}

