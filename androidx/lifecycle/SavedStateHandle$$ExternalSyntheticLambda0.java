package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry.SavedStateProvider;

public final class SavedStateHandle..ExternalSyntheticLambda0 implements SavedStateProvider {
    public final SavedStateHandle f$0;

    public SavedStateHandle..ExternalSyntheticLambda0(SavedStateHandle savedStateHandle0) {
        this.f$0 = savedStateHandle0;
    }

    @Override  // androidx.savedstate.SavedStateRegistry$SavedStateProvider
    public final Bundle saveState() {
        return SavedStateHandle.savedStateProvider$lambda$0(this.f$0);
    }
}

