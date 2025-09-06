package androidx.fragment.app;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
final class FragmentKt.sam.androidx_fragment_app_FragmentResultListener.0 implements FragmentResultListener {
    private final Function2 function;

    FragmentKt.sam.androidx_fragment_app_FragmentResultListener.0(Function2 function20) {
        this.function = function20;
    }

    @Override  // androidx.fragment.app.FragmentResultListener
    public final void onFragmentResult(String s, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(s, "p0");
        Intrinsics.checkNotNullParameter(bundle0, "p1");
        Intrinsics.checkNotNullExpressionValue(this.function.invoke(s, bundle0), "invoke(...)");
    }
}

