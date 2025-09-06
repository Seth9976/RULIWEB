package androidx.savedstate;

import android.os.Bundle;
import androidx.arch.core.internal.SafeIterableMap.IteratorWithAdditions;
import androidx.arch.core.internal.SafeIterableMap;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 )2\u00020\u0001:\u0003()*B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001A\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001A\u00020\u0007H\u0007J\u0010\u0010\u0016\u001A\u0004\u0018\u00010\b2\u0006\u0010\u0015\u001A\u00020\u0007J\u0015\u0010\u0017\u001A\u00020\u00182\u0006\u0010\u0019\u001A\u00020\u001AH\u0001¢\u0006\u0002\b\u001BJ\u0017\u0010\u001C\u001A\u00020\u00182\b\u0010\u001D\u001A\u0004\u0018\u00010\u0013H\u0001¢\u0006\u0002\b\u001EJ\u0010\u0010\u001F\u001A\u00020\u00182\u0006\u0010 \u001A\u00020\u0013H\u0007J\u0018\u0010!\u001A\u00020\u00182\u0006\u0010\u0015\u001A\u00020\u00072\u0006\u0010\"\u001A\u00020\bH\u0007J\u0018\u0010#\u001A\u00020\u00182\u000E\u0010$\u001A\n\u0012\u0006\b\u0001\u0012\u00020&0%H\u0007J\u0010\u0010\'\u001A\u00020\u00182\u0006\u0010\u0015\u001A\u00020\u0007H\u0007R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u000E¢\u0006\u0002\n\u0000R\u001A\u0010\u0005\u001A\u000E\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001A\u0010\t\u001A\u00020\u0004X\u0080\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\n\u0010\u000B\"\u0004\b\f\u0010\rR \u0010\u000F\u001A\u00020\u00042\u0006\u0010\u000E\u001A\u00020\u00048G@BX\u0086\u000E¢\u0006\b\n\u0000\u001A\u0004\b\u000F\u0010\u000BR\u0010\u0010\u0010\u001A\u0004\u0018\u00010\u0011X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001A\u0004\u0018\u00010\u0013X\u0082\u000E¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Landroidx/savedstate/SavedStateRegistry;", "", "()V", "attached", "", "components", "Landroidx/arch/core/internal/SafeIterableMap;", "", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "isAllowingSavingState", "isAllowingSavingState$savedstate_release", "()Z", "setAllowingSavingState$savedstate_release", "(Z)V", "<set-?>", "isRestored", "recreatorProvider", "Landroidx/savedstate/Recreator$SavedStateProvider;", "restoredState", "Landroid/os/Bundle;", "consumeRestoredStateForKey", "key", "getSavedStateProvider", "performAttach", "", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "performAttach$savedstate_release", "performRestore", "savedState", "performRestore$savedstate_release", "performSave", "outBundle", "registerSavedStateProvider", "provider", "runOnNextRecreation", "clazz", "Ljava/lang/Class;", "Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", "unregisterSavedStateProvider", "AutoRecreated", "Companion", "SavedStateProvider", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class SavedStateRegistry {
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001A\u00020\u00032\u0006\u0010\u0004\u001A\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/savedstate/SavedStateRegistry$AutoRecreated;", "", "onRecreated", "", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public interface AutoRecreated {
        void onRecreated(SavedStateRegistryOwner arg1);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Landroidx/savedstate/SavedStateRegistry$Companion;", "", "()V", "SAVED_COMPONENTS_KEY", "", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001A\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0004À\u0006\u0001"}, d2 = {"Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "", "saveState", "Landroid/os/Bundle;", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public interface SavedStateProvider {
        Bundle saveState();
    }

    private static final Companion Companion = null;
    @Deprecated
    private static final String SAVED_COMPONENTS_KEY = "androidx.lifecycle.BundlableSavedStateRegistry.key";
    private boolean attached;
    private final SafeIterableMap components;
    private boolean isAllowingSavingState;
    private boolean isRestored;
    private androidx.savedstate.Recreator.SavedStateProvider recreatorProvider;
    private Bundle restoredState;

    // 检测为 Lambda 实现
    public static void $r8$lambda$eDF1FsaoUa1afQFv2y5LNvCkYm4(SavedStateRegistry savedStateRegistry0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) [...]

    static {
        SavedStateRegistry.Companion = new Companion(null);
    }

    public SavedStateRegistry() {
        this.components = new SafeIterableMap();
        this.isAllowingSavingState = true;
    }

    public final Bundle consumeRestoredStateForKey(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        if(!this.isRestored) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle0 = this.restoredState;
        if(bundle0 != null) {
            Bundle bundle1 = bundle0.getBundle(s);
            Bundle bundle2 = this.restoredState;
            if(bundle2 != null) {
                bundle2.remove(s);
            }
            if(this.restoredState != null && !this.restoredState.isEmpty()) {
                return bundle1;
            }
            this.restoredState = null;
            return bundle1;
        }
        return null;
    }

    public final SavedStateProvider getSavedStateProvider(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        for(Object object0: this.components) {
            Intrinsics.checkNotNullExpressionValue(((Map.Entry)object0), "components");
            String s1 = (String)((Map.Entry)object0).getKey();
            SavedStateProvider savedStateRegistry$SavedStateProvider0 = (SavedStateProvider)((Map.Entry)object0).getValue();
            if(Intrinsics.areEqual(s1, s)) {
                return savedStateRegistry$SavedStateProvider0;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    public final boolean isAllowingSavingState$savedstate_release() {
        return this.isAllowingSavingState;
    }

    public final boolean isRestored() {
        return this.isRestored;
    }

    private static final void performAttach$lambda$4(SavedStateRegistry savedStateRegistry0, LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        Intrinsics.checkNotNullParameter(savedStateRegistry0, "this$0");
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
        if(lifecycle$Event0 == Event.ON_START) {
            savedStateRegistry0.isAllowingSavingState = true;
            return;
        }
        if(lifecycle$Event0 == Event.ON_STOP) {
            savedStateRegistry0.isAllowingSavingState = false;
        }
    }

    public final void performAttach$savedstate_release(Lifecycle lifecycle0) {
        Intrinsics.checkNotNullParameter(lifecycle0, "lifecycle");
        if(this.attached) {
            throw new IllegalStateException("SavedStateRegistry was already attached.");
        }
        lifecycle0.addObserver((LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) -> SavedStateRegistry.performAttach$lambda$4(this, lifecycleOwner0, lifecycle$Event0));
        this.attached = true;
    }

    public final void performRestore$savedstate_release(Bundle bundle0) {
        if(!this.attached) {
            throw new IllegalStateException("You must call performAttach() before calling performRestore(Bundle).");
        }
        if(this.isRestored) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        this.restoredState = bundle0 == null ? null : bundle0.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        this.isRestored = true;
    }

    public final void performSave(Bundle bundle0) {
        Intrinsics.checkNotNullParameter(bundle0, "outBundle");
        Bundle bundle1 = new Bundle();
        Bundle bundle2 = this.restoredState;
        if(bundle2 != null) {
            bundle1.putAll(bundle2);
        }
        IteratorWithAdditions safeIterableMap$IteratorWithAdditions0 = this.components.iteratorWithAdditions();
        Intrinsics.checkNotNullExpressionValue(safeIterableMap$IteratorWithAdditions0, "this.components.iteratorWithAdditions()");
        while(safeIterableMap$IteratorWithAdditions0.hasNext()) {
            Object object0 = safeIterableMap$IteratorWithAdditions0.next();
            bundle1.putBundle(((String)((Map.Entry)object0).getKey()), ((SavedStateProvider)((Map.Entry)object0).getValue()).saveState());
        }
        if(!bundle1.isEmpty()) {
            bundle0.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle1);
        }
    }

    public final void registerSavedStateProvider(String s, SavedStateProvider savedStateRegistry$SavedStateProvider0) {
        Intrinsics.checkNotNullParameter(s, "key");
        Intrinsics.checkNotNullParameter(savedStateRegistry$SavedStateProvider0, "provider");
        if(((SavedStateProvider)this.components.putIfAbsent(s, savedStateRegistry$SavedStateProvider0)) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public final void runOnNextRecreation(Class class0) {
        androidx.savedstate.Recreator.SavedStateProvider recreator$SavedStateProvider1;
        Intrinsics.checkNotNullParameter(class0, "clazz");
        if(!this.isAllowingSavingState) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        androidx.savedstate.Recreator.SavedStateProvider recreator$SavedStateProvider0 = this.recreatorProvider == null ? new androidx.savedstate.Recreator.SavedStateProvider(this) : this.recreatorProvider;
        try {
            this.recreatorProvider = recreator$SavedStateProvider0;
            class0.getDeclaredConstructor(null);
            recreator$SavedStateProvider1 = this.recreatorProvider;
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new IllegalArgumentException("Class " + class0.getSimpleName() + " must have default constructor in order to be automatically recreated", noSuchMethodException0);
        }
        if(recreator$SavedStateProvider1 != null) {
            String s = class0.getName();
            Intrinsics.checkNotNullExpressionValue(s, "clazz.name");
            recreator$SavedStateProvider1.add(s);
        }
    }

    public final void setAllowingSavingState$savedstate_release(boolean z) {
        this.isAllowingSavingState = z;
    }

    public final void unregisterSavedStateProvider(String s) {
        Intrinsics.checkNotNullParameter(s, "key");
        this.components.remove(s);
    }
}

