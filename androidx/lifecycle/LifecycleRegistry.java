package androidx.lifecycle;

import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.FastSafeIterableMap;
import androidx.arch.core.internal.SafeIterableMap.IteratorWithAdditions;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000E\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\b\u0016\u0018\u0000 42\u00020\u0001:\u000245B\u000F\b\u0016\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u00A2\u0006\u0002\u0010\u0004B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\u0002\u0010\u0007J\u0010\u0010!\u001A\u00020\"2\u0006\u0010#\u001A\u00020\u001CH\u0016J\u0010\u0010$\u001A\u00020\"2\u0006\u0010\u0014\u001A\u00020\u0003H\u0002J\u0010\u0010%\u001A\u00020\u000B2\u0006\u0010#\u001A\u00020\u001CH\u0002J\u0010\u0010&\u001A\u00020\"2\u0006\u0010\'\u001A\u00020(H\u0003J\u0010\u0010)\u001A\u00020\"2\u0006\u0010\u0014\u001A\u00020\u0003H\u0002J\u0010\u0010*\u001A\u00020\"2\u0006\u0010+\u001A\u00020,H\u0016J\u0010\u0010-\u001A\u00020\"2\u0006\u0010\n\u001A\u00020\u000BH\u0017J\u0010\u0010.\u001A\u00020\"2\u0006\u0010/\u001A\u00020\u000BH\u0002J\b\u00100\u001A\u00020\"H\u0002J\u0010\u00101\u001A\u00020\"2\u0006\u0010\n\u001A\u00020\u000BH\u0002J\u0010\u00102\u001A\u00020\"2\u0006\u0010#\u001A\u00020\u001CH\u0016J\b\u00103\u001A\u00020\"H\u0002R\u000E\u0010\b\u001A\u00020\tX\u0082\u000E\u00A2\u0006\u0002\n\u0000R$\u0010\f\u001A\u00020\u000B2\u0006\u0010\n\u001A\u00020\u000B8V@VX\u0096\u000E\u00A2\u0006\f\u001A\u0004\b\r\u0010\u000E\"\u0004\b\u000F\u0010\u0010R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001A\u00020\u00068BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001A\b\u0012\u0004\u0012\u00020\u00030\u0015X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0016\u001A\u00020\u0006X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001A\u00020\t8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0018\u0010\u0019R\u001A\u0010\u001A\u001A\u000E\u0012\u0004\u0012\u00020\u001C\u0012\u0004\u0012\u00020\u001D0\u001BX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u001E\u0010\u001E\u001A\u0012\u0012\u0004\u0012\u00020\u000B0\u001Fj\b\u0012\u0004\u0012\u00020\u000B` X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u00066"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry;", "Landroidx/lifecycle/Lifecycle;", "provider", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;)V", "enforceMainThread", "", "(Landroidx/lifecycle/LifecycleOwner;Z)V", "addingObserverCounter", "", "state", "Landroidx/lifecycle/Lifecycle$State;", "currentState", "getCurrentState", "()Landroidx/lifecycle/Lifecycle$State;", "setCurrentState", "(Landroidx/lifecycle/Lifecycle$State;)V", "handlingEvent", "isSynced", "()Z", "lifecycleOwner", "Ljava/lang/ref/WeakReference;", "newEventOccurred", "observerCount", "getObserverCount", "()I", "observerMap", "Landroidx/arch/core/internal/FastSafeIterableMap;", "Landroidx/lifecycle/LifecycleObserver;", "Landroidx/lifecycle/LifecycleRegistry$ObserverWithState;", "parentStates", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "addObserver", "", "observer", "backwardPass", "calculateTargetState", "enforceMainThreadIfNeeded", "methodName", "", "forwardPass", "handleLifecycleEvent", "event", "Landroidx/lifecycle/Lifecycle$Event;", "markState", "moveToState", "next", "popParentState", "pushParentState", "removeObserver", "sync", "Companion", "ObserverWithState", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class LifecycleRegistry extends Lifecycle {
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u001F\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00020\b2\b\u0010\n\u001A\u0004\u0018\u00010\bH\u0001¢\u0006\u0002\b\u000B¨\u0006\f"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry$Companion;", "", "()V", "createUnsafe", "Landroidx/lifecycle/LifecycleRegistry;", "owner", "Landroidx/lifecycle/LifecycleOwner;", "min", "Landroidx/lifecycle/Lifecycle$State;", "state1", "state2", "min$lifecycle_runtime_release", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        @JvmStatic
        public final LifecycleRegistry createUnsafe(LifecycleOwner lifecycleOwner0) {
            Intrinsics.checkNotNullParameter(lifecycleOwner0, "owner");
            return new LifecycleRegistry(lifecycleOwner0, false, null);
        }

        @JvmStatic
        public final State min$lifecycle_runtime_release(State lifecycle$State0, State lifecycle$State1) {
            Intrinsics.checkNotNullParameter(lifecycle$State0, "state1");
            return lifecycle$State1 == null || lifecycle$State1.compareTo(lifecycle$State0) >= 0 ? lifecycle$State0 : lifecycle$State1;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001A\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0012\u001A\u00020\u00132\b\u0010\u0014\u001A\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001A\u00020\u0017R\u001A\u0010\u0007\u001A\u00020\bX\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\t\u0010\n\"\u0004\b\u000B\u0010\fR\u001A\u0010\r\u001A\u00020\u0005X\u0086\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\u000E\u0010\u000F\"\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Landroidx/lifecycle/LifecycleRegistry$ObserverWithState;", "", "observer", "Landroidx/lifecycle/LifecycleObserver;", "initialState", "Landroidx/lifecycle/Lifecycle$State;", "(Landroidx/lifecycle/LifecycleObserver;Landroidx/lifecycle/Lifecycle$State;)V", "lifecycleObserver", "Landroidx/lifecycle/LifecycleEventObserver;", "getLifecycleObserver", "()Landroidx/lifecycle/LifecycleEventObserver;", "setLifecycleObserver", "(Landroidx/lifecycle/LifecycleEventObserver;)V", "state", "getState", "()Landroidx/lifecycle/Lifecycle$State;", "setState", "(Landroidx/lifecycle/Lifecycle$State;)V", "dispatchEvent", "", "owner", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "lifecycle-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class ObserverWithState {
        private LifecycleEventObserver lifecycleObserver;
        private State state;

        public ObserverWithState(LifecycleObserver lifecycleObserver0, State lifecycle$State0) {
            Intrinsics.checkNotNullParameter(lifecycle$State0, "initialState");
            super();
            Intrinsics.checkNotNull(lifecycleObserver0);
            this.lifecycleObserver = Lifecycling.lifecycleEventObserver(lifecycleObserver0);
            this.state = lifecycle$State0;
        }

        public final void dispatchEvent(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
            Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
            State lifecycle$State0 = lifecycle$Event0.getTargetState();
            this.state = LifecycleRegistry.Companion.min$lifecycle_runtime_release(this.state, lifecycle$State0);
            LifecycleEventObserver lifecycleEventObserver0 = this.lifecycleObserver;
            Intrinsics.checkNotNull(lifecycleOwner0);
            lifecycleEventObserver0.onStateChanged(lifecycleOwner0, lifecycle$Event0);
            this.state = lifecycle$State0;
        }

        public final LifecycleEventObserver getLifecycleObserver() {
            return this.lifecycleObserver;
        }

        public final State getState() {
            return this.state;
        }

        public final void setLifecycleObserver(LifecycleEventObserver lifecycleEventObserver0) {
            Intrinsics.checkNotNullParameter(lifecycleEventObserver0, "<set-?>");
            this.lifecycleObserver = lifecycleEventObserver0;
        }

        public final void setState(State lifecycle$State0) {
            Intrinsics.checkNotNullParameter(lifecycle$State0, "<set-?>");
            this.state = lifecycle$State0;
        }
    }

    public static final Companion Companion;
    private int addingObserverCounter;
    private final boolean enforceMainThread;
    private boolean handlingEvent;
    private final WeakReference lifecycleOwner;
    private boolean newEventOccurred;
    private FastSafeIterableMap observerMap;
    private ArrayList parentStates;
    private State state;

    static {
        LifecycleRegistry.Companion = new Companion(null);
    }

    public LifecycleRegistry(LifecycleOwner lifecycleOwner0) {
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "provider");
        this(lifecycleOwner0, true);
    }

    private LifecycleRegistry(LifecycleOwner lifecycleOwner0, boolean z) {
        this.enforceMainThread = z;
        this.observerMap = new FastSafeIterableMap();
        this.state = State.INITIALIZED;
        this.parentStates = new ArrayList();
        this.lifecycleOwner = new WeakReference(lifecycleOwner0);
    }

    public LifecycleRegistry(LifecycleOwner lifecycleOwner0, boolean z, DefaultConstructorMarker defaultConstructorMarker0) {
        this(lifecycleOwner0, z);
    }

    @Override  // androidx.lifecycle.Lifecycle
    public void addObserver(LifecycleObserver lifecycleObserver0) {
        Intrinsics.checkNotNullParameter(lifecycleObserver0, "observer");
        this.enforceMainThreadIfNeeded("addObserver");
        ObserverWithState lifecycleRegistry$ObserverWithState0 = new ObserverWithState(lifecycleObserver0, (this.state == State.DESTROYED ? State.DESTROYED : State.INITIALIZED));
        if(((ObserverWithState)this.observerMap.putIfAbsent(lifecycleObserver0, lifecycleRegistry$ObserverWithState0)) == null) {
            LifecycleOwner lifecycleOwner0 = (LifecycleOwner)this.lifecycleOwner.get();
            if(lifecycleOwner0 != null) {
                boolean z = this.addingObserverCounter != 0 || this.handlingEvent;
                State lifecycle$State0 = this.calculateTargetState(lifecycleObserver0);
                ++this.addingObserverCounter;
                while(lifecycleRegistry$ObserverWithState0.getState().compareTo(lifecycle$State0) < 0 && this.observerMap.contains(lifecycleObserver0)) {
                    this.pushParentState(lifecycleRegistry$ObserverWithState0.getState());
                    Event lifecycle$Event0 = Event.Companion.upFrom(lifecycleRegistry$ObserverWithState0.getState());
                    if(lifecycle$Event0 == null) {
                        throw new IllegalStateException("no event up from " + lifecycleRegistry$ObserverWithState0.getState());
                    }
                    lifecycleRegistry$ObserverWithState0.dispatchEvent(lifecycleOwner0, lifecycle$Event0);
                    this.popParentState();
                    lifecycle$State0 = this.calculateTargetState(lifecycleObserver0);
                }
                if(!z) {
                    this.sync();
                }
                --this.addingObserverCounter;
            }
        }
    }

    private final void backwardPass(LifecycleOwner lifecycleOwner0) {
        Iterator iterator0 = this.observerMap.descendingIterator();
        Intrinsics.checkNotNullExpressionValue(iterator0, "observerMap.descendingIterator()");
        while(iterator0.hasNext() && !this.newEventOccurred) {
            Object object0 = iterator0.next();
            Intrinsics.checkNotNullExpressionValue(((Map.Entry)object0), "next()");
            LifecycleObserver lifecycleObserver0 = (LifecycleObserver)((Map.Entry)object0).getKey();
            ObserverWithState lifecycleRegistry$ObserverWithState0 = (ObserverWithState)((Map.Entry)object0).getValue();
            while(lifecycleRegistry$ObserverWithState0.getState().compareTo(this.state) > 0 && !this.newEventOccurred && this.observerMap.contains(lifecycleObserver0)) {
                Event lifecycle$Event0 = Event.Companion.downFrom(lifecycleRegistry$ObserverWithState0.getState());
                if(lifecycle$Event0 == null) {
                    throw new IllegalStateException("no event down from " + lifecycleRegistry$ObserverWithState0.getState());
                }
                this.pushParentState(lifecycle$Event0.getTargetState());
                lifecycleRegistry$ObserverWithState0.dispatchEvent(lifecycleOwner0, lifecycle$Event0);
                this.popParentState();
            }
        }
    }

    private final State calculateTargetState(LifecycleObserver lifecycleObserver0) {
        State lifecycle$State1;
        Map.Entry map$Entry0 = this.observerMap.ceil(lifecycleObserver0);
        State lifecycle$State0 = null;
        if(map$Entry0 == null) {
            lifecycle$State1 = null;
        }
        else {
            ObserverWithState lifecycleRegistry$ObserverWithState0 = (ObserverWithState)map$Entry0.getValue();
            lifecycle$State1 = lifecycleRegistry$ObserverWithState0 == null ? null : lifecycleRegistry$ObserverWithState0.getState();
        }
        if(!this.parentStates.isEmpty()) {
            lifecycle$State0 = (State)this.parentStates.get(this.parentStates.size() - 1);
        }
        State lifecycle$State2 = LifecycleRegistry.Companion.min$lifecycle_runtime_release(this.state, lifecycle$State1);
        return LifecycleRegistry.Companion.min$lifecycle_runtime_release(lifecycle$State2, lifecycle$State0);
    }

    @JvmStatic
    public static final LifecycleRegistry createUnsafe(LifecycleOwner lifecycleOwner0) {
        return LifecycleRegistry.Companion.createUnsafe(lifecycleOwner0);
    }

    private final void enforceMainThreadIfNeeded(String s) {
        if(this.enforceMainThread && !ArchTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException(("Method " + s + " must be called on the main thread").toString());
        }
    }

    private final void forwardPass(LifecycleOwner lifecycleOwner0) {
        IteratorWithAdditions safeIterableMap$IteratorWithAdditions0 = this.observerMap.iteratorWithAdditions();
        Intrinsics.checkNotNullExpressionValue(safeIterableMap$IteratorWithAdditions0, "observerMap.iteratorWithAdditions()");
        while(safeIterableMap$IteratorWithAdditions0.hasNext() && !this.newEventOccurred) {
            Object object0 = safeIterableMap$IteratorWithAdditions0.next();
            LifecycleObserver lifecycleObserver0 = (LifecycleObserver)((Map.Entry)object0).getKey();
            ObserverWithState lifecycleRegistry$ObserverWithState0 = (ObserverWithState)((Map.Entry)object0).getValue();
            while(lifecycleRegistry$ObserverWithState0.getState().compareTo(this.state) < 0 && !this.newEventOccurred && this.observerMap.contains(lifecycleObserver0)) {
                this.pushParentState(lifecycleRegistry$ObserverWithState0.getState());
                Event lifecycle$Event0 = Event.Companion.upFrom(lifecycleRegistry$ObserverWithState0.getState());
                if(lifecycle$Event0 == null) {
                    throw new IllegalStateException("no event up from " + lifecycleRegistry$ObserverWithState0.getState());
                }
                lifecycleRegistry$ObserverWithState0.dispatchEvent(lifecycleOwner0, lifecycle$Event0);
                this.popParentState();
            }
        }
    }

    @Override  // androidx.lifecycle.Lifecycle
    public State getCurrentState() {
        return this.state;
    }

    public int getObserverCount() {
        this.enforceMainThreadIfNeeded("getObserverCount");
        return this.observerMap.size();
    }

    public void handleLifecycleEvent(Event lifecycle$Event0) {
        Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
        this.enforceMainThreadIfNeeded("handleLifecycleEvent");
        this.moveToState(lifecycle$Event0.getTargetState());
    }

    private final boolean isSynced() {
        if(this.observerMap.size() == 0) {
            return true;
        }
        Map.Entry map$Entry0 = this.observerMap.eldest();
        Intrinsics.checkNotNull(map$Entry0);
        State lifecycle$State0 = ((ObserverWithState)map$Entry0.getValue()).getState();
        Map.Entry map$Entry1 = this.observerMap.newest();
        Intrinsics.checkNotNull(map$Entry1);
        State lifecycle$State1 = ((ObserverWithState)map$Entry1.getValue()).getState();
        return lifecycle$State0 == lifecycle$State1 && this.state == lifecycle$State1;
    }

    @Deprecated(message = "Override [currentState].")
    public void markState(State lifecycle$State0) {
        Intrinsics.checkNotNullParameter(lifecycle$State0, "state");
        this.enforceMainThreadIfNeeded("markState");
        this.setCurrentState(lifecycle$State0);
    }

    @JvmStatic
    public static final State min$lifecycle_runtime_release(State lifecycle$State0, State lifecycle$State1) {
        return LifecycleRegistry.Companion.min$lifecycle_runtime_release(lifecycle$State0, lifecycle$State1);
    }

    private final void moveToState(State lifecycle$State0) {
        State lifecycle$State1 = this.state;
        if(lifecycle$State1 != lifecycle$State0) {
            if(lifecycle$State1 == State.INITIALIZED && lifecycle$State0 == State.DESTROYED) {
                throw new IllegalStateException(("no event down from " + this.state + " in component " + this.lifecycleOwner.get()).toString());
            }
            this.state = lifecycle$State0;
            if(this.handlingEvent || this.addingObserverCounter != 0) {
                this.newEventOccurred = true;
                return;
            }
            this.handlingEvent = true;
            this.sync();
            this.handlingEvent = false;
            if(this.state == State.DESTROYED) {
                this.observerMap = new FastSafeIterableMap();
            }
        }
    }

    private final void popParentState() {
        this.parentStates.remove(this.parentStates.size() - 1);
    }

    private final void pushParentState(State lifecycle$State0) {
        this.parentStates.add(lifecycle$State0);
    }

    @Override  // androidx.lifecycle.Lifecycle
    public void removeObserver(LifecycleObserver lifecycleObserver0) {
        Intrinsics.checkNotNullParameter(lifecycleObserver0, "observer");
        this.enforceMainThreadIfNeeded("removeObserver");
        this.observerMap.remove(lifecycleObserver0);
    }

    public void setCurrentState(State lifecycle$State0) {
        Intrinsics.checkNotNullParameter(lifecycle$State0, "state");
        this.enforceMainThreadIfNeeded("setCurrentState");
        this.moveToState(lifecycle$State0);
    }

    private final void sync() {
        LifecycleOwner lifecycleOwner0 = (LifecycleOwner)this.lifecycleOwner.get();
        if(lifecycleOwner0 == null) {
            throw new IllegalStateException("LifecycleOwner of this LifecycleRegistry is already garbage collected. It is too late to change lifecycle state.");
        }
        while(!this.isSynced()) {
            this.newEventOccurred = false;
            State lifecycle$State0 = this.state;
            Map.Entry map$Entry0 = this.observerMap.eldest();
            Intrinsics.checkNotNull(map$Entry0);
            if(lifecycle$State0.compareTo(((ObserverWithState)map$Entry0.getValue()).getState()) < 0) {
                this.backwardPass(lifecycleOwner0);
            }
            Map.Entry map$Entry1 = this.observerMap.newest();
            if(!this.newEventOccurred && map$Entry1 != null && this.state.compareTo(((ObserverWithState)map$Entry1.getValue()).getState()) > 0) {
                this.forwardPass(lifecycleOwner0);
            }
        }
        this.newEventOccurred = false;
    }
}

