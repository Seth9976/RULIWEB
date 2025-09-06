package androidx.fragment.app;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry.SavedStateProvider;

public final class FragmentManager..ExternalSyntheticLambda4 implements SavedStateProvider {
    public final FragmentManager f$0;

    public FragmentManager..ExternalSyntheticLambda4(FragmentManager fragmentManager0) {
        this.f$0 = fragmentManager0;
    }

    @Override  // androidx.savedstate.SavedStateRegistry$SavedStateProvider
    public final Bundle saveState() {
        return this.f$0.lambda$attachController$4$androidx-fragment-app-FragmentManager();
    }
}

