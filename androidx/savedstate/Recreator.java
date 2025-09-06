package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0000\u0018\u0000 \u000E2\u00020\u0001:\u0002\u000E\u000FB\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\nH\u0016J\u0010\u0010\u000B\u001A\u00020\u00062\u0006\u0010\f\u001A\u00020\rH\u0002R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/savedstate/Recreator;", "Landroidx/lifecycle/LifecycleEventObserver;", "owner", "Landroidx/savedstate/SavedStateRegistryOwner;", "(Landroidx/savedstate/SavedStateRegistryOwner;)V", "onStateChanged", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "reflectiveNew", "className", "", "Companion", "SavedStateProvider", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class Recreator implements LifecycleEventObserver {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/savedstate/Recreator$Companion;", "", "()V", "CLASSES_KEY", "", "COMPONENT_KEY", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000E\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u0007J\b\u0010\u000B\u001A\u00020\fH\u0016R\u0014\u0010\u0005\u001A\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/savedstate/Recreator$SavedStateProvider;", "Landroidx/savedstate/SavedStateRegistry$SavedStateProvider;", "registry", "Landroidx/savedstate/SavedStateRegistry;", "(Landroidx/savedstate/SavedStateRegistry;)V", "classes", "", "", "add", "", "className", "saveState", "Landroid/os/Bundle;", "savedstate_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class SavedStateProvider implements androidx.savedstate.SavedStateRegistry.SavedStateProvider {
        private final Set classes;

        public SavedStateProvider(SavedStateRegistry savedStateRegistry0) {
            Intrinsics.checkNotNullParameter(savedStateRegistry0, "registry");
            super();
            this.classes = new LinkedHashSet();
            savedStateRegistry0.registerSavedStateProvider("androidx.savedstate.Restarter", this);
        }

        public final void add(String s) {
            Intrinsics.checkNotNullParameter(s, "className");
            this.classes.add(s);
        }

        @Override  // androidx.savedstate.SavedStateRegistry$SavedStateProvider
        public Bundle saveState() {
            Bundle bundle0 = new Bundle();
            bundle0.putStringArrayList("classes_to_restore", new ArrayList(this.classes));
            return bundle0;
        }
    }

    public static final String CLASSES_KEY = "classes_to_restore";
    public static final String COMPONENT_KEY = "androidx.savedstate.Restarter";
    public static final Companion Companion;
    private final SavedStateRegistryOwner owner;

    static {
        Recreator.Companion = new Companion(null);
    }

    public Recreator(SavedStateRegistryOwner savedStateRegistryOwner0) {
        Intrinsics.checkNotNullParameter(savedStateRegistryOwner0, "owner");
        super();
        this.owner = savedStateRegistryOwner0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
        Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
        if(lifecycle$Event0 != Event.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        lifecycleOwner0.getLifecycle().removeObserver(this);
        Bundle bundle0 = this.owner.getSavedStateRegistry().consumeRestoredStateForKey("androidx.savedstate.Restarter");
        if(bundle0 != null) {
            ArrayList arrayList0 = bundle0.getStringArrayList("classes_to_restore");
            if(arrayList0 == null) {
                throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
            }
            for(Object object0: arrayList0) {
                this.reflectiveNew(((String)object0));
            }
        }
    }

    private final void reflectiveNew(String s) {
        AutoRecreated savedStateRegistry$AutoRecreated0;
        Constructor constructor0;
        Class class0;
        try {
            class0 = Class.forName(s, false, Recreator.class.getClassLoader()).asSubclass(AutoRecreated.class);
            Intrinsics.checkNotNullExpressionValue(class0, "{\n                Class.…class.java)\n            }");
        }
        catch(ClassNotFoundException classNotFoundException0) {
            throw new RuntimeException("Class " + s + " wasn\'t found", classNotFoundException0);
        }
        try {
            constructor0 = class0.getDeclaredConstructor(null);
        }
        catch(NoSuchMethodException noSuchMethodException0) {
            throw new IllegalStateException("Class " + class0.getSimpleName() + " must have default constructor in order to be automatically recreated", noSuchMethodException0);
        }
        constructor0.setAccessible(true);
        try {
            Object object0 = constructor0.newInstance(null);
            Intrinsics.checkNotNullExpressionValue(object0, "{\n                constr…wInstance()\n            }");
            savedStateRegistry$AutoRecreated0 = (AutoRecreated)object0;
        }
        catch(Exception exception0) {
            throw new RuntimeException("Failed to instantiate " + s, exception0);
        }
        savedStateRegistry$AutoRecreated0.onRecreated(this.owner);
    }
}

