package androidx.fragment.app;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry.SavedStateProvider;

public final class FragmentActivity..ExternalSyntheticLambda0 implements SavedStateProvider {
    public final FragmentActivity f$0;

    public FragmentActivity..ExternalSyntheticLambda0(FragmentActivity fragmentActivity0) {
        this.f$0 = fragmentActivity0;
    }

    @Override  // androidx.savedstate.SavedStateRegistry$SavedStateProvider
    public final Bundle saveState() {
        return this.f$0.lambda$init$0$androidx-fragment-app-FragmentActivity();
    }
}

