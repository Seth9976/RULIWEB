package androidx.activity;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry.SavedStateProvider;

public final class ComponentActivity..ExternalSyntheticLambda3 implements SavedStateProvider {
    public final ComponentActivity f$0;

    public ComponentActivity..ExternalSyntheticLambda3(ComponentActivity componentActivity0) {
        this.f$0 = componentActivity0;
    }

    @Override  // androidx.savedstate.SavedStateRegistry$SavedStateProvider
    public final Bundle saveState() {
        return ComponentActivity._init_$lambda$4(this.f$0);
    }
}

