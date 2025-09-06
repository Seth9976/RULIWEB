package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.savedstate.SavedStateRegistry;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u001F2\u00020\u00012\u00020\u0002:\u0001\u001FB\u0007\b\u0016¢\u0006\u0002\u0010\u0003B\u0019\b\u0016\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ%\u0010\r\u001A\u0002H\u000E\"\b\b\u0000\u0010\u000E*\u00020\u000F2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u0011H\u0016¢\u0006\u0002\u0010\u0012J-\u0010\r\u001A\u0002H\u000E\"\b\b\u0000\u0010\u000E*\u00020\u000F2\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00112\u0006\u0010\u0013\u001A\u00020\u0014H\u0016¢\u0006\u0002\u0010\u0015J-\u0010\r\u001A\u0002H\u000E\"\b\b\u0000\u0010\u000E*\u00020\u000F2\u0006\u0010\u0016\u001A\u00020\u00172\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u0011H\u0002¢\u0006\u0002\u0010\u0018J5\u0010\r\u001A\u0002H\u000E\"\b\b\u0000\u0010\u000E*\u00020\u000F2\u0006\u0010\u0016\u001A\u00020\u00172\f\u0010\u0010\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u00112\u0006\u0010\u0019\u001A\u00020\u001AH$¢\u0006\u0002\u0010\u001BJ\u0010\u0010\u001C\u001A\u00020\u001D2\u0006\u0010\u001E\u001A\u00020\u000FH\u0017R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u000B\u001A\u0004\u0018\u00010\fX\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/lifecycle/AbstractSavedStateViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$OnRequeryFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "()V", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "defaultArgs", "Landroid/os/Bundle;", "(Landroidx/savedstate/SavedStateRegistryOwner;Landroid/os/Bundle;)V", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "extras", "Landroidx/lifecycle/viewmodel/CreationExtras;", "(Ljava/lang/Class;Landroidx/lifecycle/viewmodel/CreationExtras;)Landroidx/lifecycle/ViewModel;", "key", "", "(Ljava/lang/String;Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "handle", "Landroidx/lifecycle/SavedStateHandle;", "(Ljava/lang/String;Ljava/lang/Class;Landroidx/lifecycle/SavedStateHandle;)Landroidx/lifecycle/ViewModel;", "onRequery", "", "viewModel", "Companion", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class AbstractSavedStateViewModelFactory extends OnRequeryFactory implements Factory {
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/lifecycle/AbstractSavedStateViewModelFactory$Companion;", "", "()V", "TAG_SAVED_STATE_HANDLE_CONTROLLER", "", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    public static final Companion Companion = null;
    public static final String TAG_SAVED_STATE_HANDLE_CONTROLLER = "androidx.lifecycle.savedstate.vm.tag";
    private Bundle defaultArgs;
    private Lifecycle lifecycle;
    private SavedStateRegistry savedStateRegistry;

    static {
        AbstractSavedStateViewModelFactory.Companion = new Companion(null);
    }

    public AbstractSavedStateViewModelFactory() {
    }

    public AbstractSavedStateViewModelFactory(SavedStateRegistryOwner savedStateRegistryOwner0, Bundle bundle0) {
        Intrinsics.checkNotNullParameter(savedStateRegistryOwner0, "owner");
        super();
        this.savedStateRegistry = savedStateRegistryOwner0.getSavedStateRegistry();
        this.lifecycle = savedStateRegistryOwner0.getLifecycle();
        this.defaultArgs = bundle0;
    }

    private final ViewModel create(String s, Class class0) {
        SavedStateRegistry savedStateRegistry0 = this.savedStateRegistry;
        Intrinsics.checkNotNull(savedStateRegistry0);
        Lifecycle lifecycle0 = this.lifecycle;
        Intrinsics.checkNotNull(lifecycle0);
        SavedStateHandleController savedStateHandleController0 = LegacySavedStateHandleController.create(savedStateRegistry0, lifecycle0, s, this.defaultArgs);
        ViewModel viewModel0 = this.create(s, class0, savedStateHandleController0.getHandle());
        viewModel0.setTagIfAbsent("androidx.lifecycle.savedstate.vm.tag", savedStateHandleController0);
        return viewModel0;
    }

    @Override  // androidx.lifecycle.ViewModelProvider$Factory
    public ViewModel create(Class class0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        String s = class0.getCanonicalName();
        if(s == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        if(this.lifecycle == null) {
            throw new UnsupportedOperationException("AbstractSavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        return this.create(s, class0);
    }

    @Override  // androidx.lifecycle.ViewModelProvider$Factory
    public ViewModel create(Class class0, CreationExtras creationExtras0) {
        Intrinsics.checkNotNullParameter(class0, "modelClass");
        Intrinsics.checkNotNullParameter(creationExtras0, "extras");
        String s = (String)creationExtras0.get(NewInstanceFactory.VIEW_MODEL_KEY);
        if(s == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        return this.savedStateRegistry == null ? this.create(s, class0, SavedStateHandleSupport.createSavedStateHandle(creationExtras0)) : this.create(s, class0);
    }

    protected abstract ViewModel create(String arg1, Class arg2, SavedStateHandle arg3);

    @Override  // androidx.lifecycle.ViewModelProvider$OnRequeryFactory
    public void onRequery(ViewModel viewModel0) {
        Intrinsics.checkNotNullParameter(viewModel0, "viewModel");
        SavedStateRegistry savedStateRegistry0 = this.savedStateRegistry;
        if(savedStateRegistry0 != null) {
            Intrinsics.checkNotNull(savedStateRegistry0);
            Lifecycle lifecycle0 = this.lifecycle;
            Intrinsics.checkNotNull(lifecycle0);
            LegacySavedStateHandleController.attachHandleIfNeeded(viewModel0, savedStateRegistry0, lifecycle0);
        }
    }
}

