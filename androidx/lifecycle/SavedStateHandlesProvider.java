package androidx.lifecycle;

import android.os.Bundle;
import androidx.savedstate.SavedStateRegistry.SavedStateProvider;
import androidx.savedstate.SavedStateRegistry;
import java.util.Map.Entry;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0011\u001A\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001A\u00020\u0013J\u0006\u0010\u0014\u001A\u00020\u0015J\b\u0010\u0016\u001A\u00020\nH\u0016R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u0004\u0018\u00010\nX\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001B\u0010\u000B\u001A\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000F\u0010\u0010\u001A\u0004\b\r\u0010\u000E¨\u0006\u0017"}, d2 = {"Landroidx/lifecycle/SavedStateHandlesProvider;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "viewModelStoreOwner", "Landroidx/lifecycle/ViewModelStoreOwner;", "(Landroidx/savedstate/SavedStateRegistry;Landroidx/lifecycle/ViewModelStoreOwner;)V", "restored", "", "restoredState", "Landroid/os/Bundle;", "viewModel", "Landroidx/lifecycle/SavedStateHandlesVM;", "getViewModel", "()Landroidx/lifecycle/SavedStateHandlesVM;", "viewModel$delegate", "Lkotlin/Lazy;", "consumeRestoredStateForKey", "key", "", "performRestore", "", "saveState", "lifecycle-viewmodel-savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SavedStateHandlesProvider implements SavedStateProvider {
    private boolean restored;
    private Bundle restoredState;
    private final SavedStateRegistry savedStateRegistry;
    private final Lazy viewModel$delegate;

    public SavedStateHandlesProvider(SavedStateRegistry savedStateRegistry0, ViewModelStoreOwner viewModelStoreOwner0) {
        Intrinsics.checkNotNullParameter(savedStateRegistry0, "savedStateRegistry");
        Intrinsics.checkNotNullParameter(viewModelStoreOwner0, "viewModelStoreOwner");
        super();
        this.savedStateRegistry = savedStateRegistry0;
        this.viewModel$delegate = LazyKt.lazy(new Function0() {
            final ViewModelStoreOwner $viewModelStoreOwner;

            {
                this.$viewModelStoreOwner = viewModelStoreOwner0;
                super(0);
            }

            public final SavedStateHandlesVM invoke() {
                return SavedStateHandleSupport.getSavedStateHandlesVM(this.$viewModelStoreOwner);
            }

            @Override  // kotlin.jvm.functions.Function0
            public Object invoke() {
                return this.invoke();
            }
        });
    }

    public final Bundle consumeRestoredStateForKey(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        this.performRestore();
        Bundle bundle0 = this.restoredState == null ? null : this.restoredState.getBundle(s);
        Bundle bundle1 = this.restoredState;
        if(bundle1 != null) {
            bundle1.remove(s);
        }
        if(this.restoredState != null && this.restoredState.isEmpty()) {
            this.restoredState = null;
        }
        return bundle0;
    }

    private final SavedStateHandlesVM getViewModel() {
        return (SavedStateHandlesVM)this.viewModel$delegate.getValue();
    }

    public final void performRestore() {
        if(!this.restored) {
            Bundle bundle0 = this.savedStateRegistry.consumeRestoredStateForKey("androidx.lifecycle.internal.SavedStateHandlesProvider");
            Bundle bundle1 = new Bundle();
            Bundle bundle2 = this.restoredState;
            if(bundle2 != null) {
                bundle1.putAll(bundle2);
            }
            if(bundle0 != null) {
                bundle1.putAll(bundle0);
            }
            this.restoredState = bundle1;
            this.restored = true;
            this.getViewModel();
        }
    }

    @Override  // androidx.savedstate.SavedStateRegistry$SavedStateProvider
    public Bundle saveState() {
        Bundle bundle0 = new Bundle();
        Bundle bundle1 = this.restoredState;
        if(bundle1 != null) {
            bundle0.putAll(bundle1);
        }
        for(Object object0: this.getViewModel().getHandles().entrySet()) {
            String s = (String)((Map.Entry)object0).getKey();
            Bundle bundle2 = ((SavedStateHandle)((Map.Entry)object0).getValue()).savedStateProvider().saveState();
            if(!Intrinsics.areEqual(bundle2, Bundle.EMPTY)) {
                bundle0.putBundle(s, bundle2);
            }
        }
        this.restored = false;
        return bundle0;
    }
}

