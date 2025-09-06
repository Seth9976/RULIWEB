package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry.AutoRecreated;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0001\u0013B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0007J,\u0010\r\u001A\u00020\u000E2\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\f2\b\u0010\u000F\u001A\u0004\u0018\u00010\u00042\b\u0010\u0010\u001A\u0004\u0018\u00010\u0011H\u0007J\u0018\u0010\u0012\u001A\u00020\u00062\u0006\u0010\t\u001A\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/lifecycle/LegacySavedStateHandleController;", "", "()V", "TAG_SAVED_STATE_HANDLE_CONTROLLER", "", "attachHandleIfNeeded", "", "viewModel", "Landroidx/lifecycle/ViewModel;", "registry", "Landroidx/savedstate/SavedStateRegistry;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "create", "Landroidx/lifecycle/SavedStateHandleController;", "key", "defaultArgs", "Landroid/os/Bundle;", "tryToAddRecreator", "OnRecreation", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class LegacySavedStateHandleController {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Landroidx/lifecycle/LegacySavedStateHandleController$OnRecreation;", "Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", "()V", "onRecreated", "", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class OnRecreation implements AutoRecreated {
        @Override  // androidx.savedstate.SavedStateRegistry$AutoRecreated
        public void onRecreated(SavedStateRegistryOwner savedStateRegistryOwner0) {
            Intrinsics.checkNotNullParameter(savedStateRegistryOwner0, "owner");
            if(!(savedStateRegistryOwner0 instanceof ViewModelStoreOwner)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
            }
            ViewModelStore viewModelStore0 = ((ViewModelStoreOwner)savedStateRegistryOwner0).getViewModelStore();
            SavedStateRegistry savedStateRegistry0 = savedStateRegistryOwner0.getSavedStateRegistry();
            for(Object object0: viewModelStore0.keys()) {
                ViewModel viewModel0 = viewModelStore0.get(((String)object0));
                Intrinsics.checkNotNull(viewModel0);
                LegacySavedStateHandleController.attachHandleIfNeeded(viewModel0, savedStateRegistry0, savedStateRegistryOwner0.getLifecycle());
            }
            if(!viewModelStore0.keys().isEmpty()) {
                savedStateRegistry0.runOnNextRecreation(OnRecreation.class);
            }
        }
    }

    public static final LegacySavedStateHandleController INSTANCE = null;
    public static final String TAG_SAVED_STATE_HANDLE_CONTROLLER = "androidx.lifecycle.savedstate.vm.tag";

    static {
        LegacySavedStateHandleController.INSTANCE = new LegacySavedStateHandleController();
    }

    @JvmStatic
    public static final void attachHandleIfNeeded(ViewModel viewModel0, SavedStateRegistry savedStateRegistry0, Lifecycle lifecycle0) {
        Intrinsics.checkNotNullParameter(viewModel0, "viewModel");
        Intrinsics.checkNotNullParameter(savedStateRegistry0, "registry");
        Intrinsics.checkNotNullParameter(lifecycle0, "lifecycle");
        SavedStateHandleController savedStateHandleController0 = (SavedStateHandleController)viewModel0.getTag("androidx.lifecycle.savedstate.vm.tag");
        if(savedStateHandleController0 != null && !savedStateHandleController0.isAttached()) {
            savedStateHandleController0.attachToLifecycle(savedStateRegistry0, lifecycle0);
            LegacySavedStateHandleController.INSTANCE.tryToAddRecreator(savedStateRegistry0, lifecycle0);
        }
    }

    @JvmStatic
    public static final SavedStateHandleController create(SavedStateRegistry savedStateRegistry0, Lifecycle lifecycle0, String s, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(savedStateRegistry0, "registry");
        Intrinsics.checkNotNullParameter(lifecycle0, "lifecycle");
        Intrinsics.checkNotNull(s);
        Bundle bundle1 = savedStateRegistry0.consumeRestoredStateForKey(s);
        SavedStateHandleController savedStateHandleController0 = new SavedStateHandleController(s, SavedStateHandle.Companion.createHandle(bundle1, bundle0));
        savedStateHandleController0.attachToLifecycle(savedStateRegistry0, lifecycle0);
        LegacySavedStateHandleController.INSTANCE.tryToAddRecreator(savedStateRegistry0, lifecycle0);
        return savedStateHandleController0;
    }

    private final void tryToAddRecreator(SavedStateRegistry savedStateRegistry0, Lifecycle lifecycle0) {
        State lifecycle$State0 = lifecycle0.getCurrentState();
        if(lifecycle$State0 != State.INITIALIZED && !lifecycle$State0.isAtLeast(State.STARTED)) {
            lifecycle0.addObserver(new LifecycleEventObserver() {
                @Override  // androidx.lifecycle.LifecycleEventObserver
                public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                    Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
                    Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
                    if(lifecycle$Event0 == Event.ON_START) {
                        savedStateRegistry0.removeObserver(this);
                        this.$registry.runOnNextRecreation(OnRecreation.class);
                    }
                }
            });
            return;
        }
        savedStateRegistry0.runOnNextRecreation(OnRecreation.class);
    }
}

