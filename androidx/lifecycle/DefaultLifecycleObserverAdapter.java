package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\b\u0010\u0004\u001A\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0016R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001A\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Landroidx/lifecycle/DefaultLifecycleObserverAdapter;", "Landroidx/lifecycle/LifecycleEventObserver;", "defaultLifecycleObserver", "Landroidx/lifecycle/DefaultLifecycleObserver;", "lifecycleEventObserver", "(Landroidx/lifecycle/DefaultLifecycleObserver;Landroidx/lifecycle/LifecycleEventObserver;)V", "onStateChanged", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class DefaultLifecycleObserverAdapter implements LifecycleEventObserver {
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[Event.values().length];
            try {
                arr_v[Event.ON_CREATE.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Event.ON_START.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Event.ON_RESUME.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Event.ON_PAUSE.ordinal()] = 4;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Event.ON_STOP.ordinal()] = 5;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Event.ON_DESTROY.ordinal()] = 6;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[Event.ON_ANY.ordinal()] = 7;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private final DefaultLifecycleObserver defaultLifecycleObserver;
    private final LifecycleEventObserver lifecycleEventObserver;

    public DefaultLifecycleObserverAdapter(DefaultLifecycleObserver defaultLifecycleObserver0, LifecycleEventObserver lifecycleEventObserver0) {
        Intrinsics.checkNotNullParameter(defaultLifecycleObserver0, "defaultLifecycleObserver");
        super();
        this.defaultLifecycleObserver = defaultLifecycleObserver0;
        this.lifecycleEventObserver = lifecycleEventObserver0;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
        Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
        switch(lifecycle$Event0) {
            case 1: {
                this.defaultLifecycleObserver.onCreate(lifecycleOwner0);
                break;
            }
            case 2: {
                this.defaultLifecycleObserver.onStart(lifecycleOwner0);
                break;
            }
            case 3: {
                this.defaultLifecycleObserver.onResume(lifecycleOwner0);
                break;
            }
            case 4: {
                this.defaultLifecycleObserver.onPause(lifecycleOwner0);
                break;
            }
            case 5: {
                this.defaultLifecycleObserver.onStop(lifecycleOwner0);
                break;
            }
            case 6: {
                this.defaultLifecycleObserver.onDestroy(lifecycleOwner0);
                break;
            }
            case 7: {
                throw new IllegalArgumentException("ON_ANY must not been send by anybody");
            }
        }
        LifecycleEventObserver lifecycleEventObserver0 = this.lifecycleEventObserver;
        if(lifecycleEventObserver0 != null) {
            lifecycleEventObserver0.onStateChanged(lifecycleOwner0, lifecycle$Event0);
        }
    }
}

