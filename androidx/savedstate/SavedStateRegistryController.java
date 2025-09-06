package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle.State;
import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u000F\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000B\u001A\u00020\fH\u0007J\u0012\u0010\r\u001A\u00020\f2\b\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0007J\u0010\u0010\u0010\u001A\u00020\f2\u0006\u0010\u0011\u001A\u00020\u000FH\u0007R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001A\u00020\b¢\u0006\b\n\u0000\u001A\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Landroidx/savedstate/SavedStateRegistryController;", "", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "(Landroidx/savedstate/SavedStateRegistryOwner;)V", "attached", "", "savedStateRegistry", "Landroidx/savedstate/SavedStateRegistry;", "getSavedStateRegistry", "()Landroidx/savedstate/SavedStateRegistry;", "performAttach", "", "performRestore", "savedState", "Landroid/os/Bundle;", "performSave", "outBundle", "Companion", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SavedStateRegistryController {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\u0007"}, d2 = {"Landroidx/savedstate/SavedStateRegistryController$Companion;", "", "()V", "create", "Landroidx/savedstate/SavedStateRegistryController;", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final SavedStateRegistryController create(SavedStateRegistryOwner savedStateRegistryOwner0) {
            Intrinsics.checkNotNullParameter(savedStateRegistryOwner0, "owner");
            return new SavedStateRegistryController(savedStateRegistryOwner0, null);
        }
    }

    public static final Companion Companion;
    private boolean attached;
    private final SavedStateRegistryOwner owner;
    private final SavedStateRegistry savedStateRegistry;

    static {
        SavedStateRegistryController.Companion = new Companion(null);
    }

    private SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner0) {
        this.owner = savedStateRegistryOwner0;
        this.savedStateRegistry = new SavedStateRegistry();
    }

    public SavedStateRegistryController(SavedStateRegistryOwner savedStateRegistryOwner0, DefaultConstructorMarker defaultConstructorMarker0) {
        this(savedStateRegistryOwner0);
    }

    @JvmStatic
    public static final SavedStateRegistryController create(SavedStateRegistryOwner savedStateRegistryOwner0) {
        return SavedStateRegistryController.Companion.create(savedStateRegistryOwner0);
    }

    public final SavedStateRegistry getSavedStateRegistry() {
        return this.savedStateRegistry;
    }

    public final void performAttach() {
        Lifecycle lifecycle0 = this.owner.getLifecycle();
        if(lifecycle0.getCurrentState() != State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner\'s initialization stage");
        }
        lifecycle0.addObserver(new Recreator(this.owner));
        this.savedStateRegistry.performAttach$savedstate_release(lifecycle0);
        this.attached = true;
    }

    public final void performRestore(Bundle bundle0) {
        if(!this.attached) {
            this.performAttach();
        }
        Lifecycle lifecycle0 = this.owner.getLifecycle();
        if(lifecycle0.getCurrentState().isAtLeast(State.STARTED)) {
            throw new IllegalStateException(("performRestore cannot be called when owner is " + lifecycle0.getCurrentState()).toString());
        }
        this.savedStateRegistry.performRestore$savedstate_release(bundle0);
    }

    public final void performSave(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "outBundle");
        this.savedStateRegistry.performSave(bundle0);
    }
}

