package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\'J\u0010\u0010\u0011\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u00020\u0010H\'R\u0014\u0010\u0003\u001A\u00020\u00048gX¦\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006R$\u0010\u0007\u001A\b\u0012\u0004\u0012\u00020\u00010\b8\u0006@\u0006X\u0087\u000E¢\u0006\u000E\n\u0000\u001A\u0004\b\t\u0010\n\"\u0004\b\u000B\u0010\f¨\u0006\u0014"}, d2 = {"Landroidx/lifecycle/Lifecycle;", "", "()V", "currentState", "Landroidx/lifecycle/Lifecycle$State;", "getCurrentState", "()Landroidx/lifecycle/Lifecycle$State;", "internalScopeRef", "Ljava/util/concurrent/atomic/AtomicReference;", "getInternalScopeRef", "()Ljava/util/concurrent/atomic/AtomicReference;", "setInternalScopeRef", "(Ljava/util/concurrent/atomic/AtomicReference;)V", "addObserver", "", "observer", "Landroidx/lifecycle/LifecycleObserver;", "removeObserver", "Event", "State", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class Lifecycle {
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000B\b\u0086\u0001\u0018\u0000 \u000E2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000EB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001A\u00020\u00048F¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000Bj\u0002\b\fj\u0002\b\r¨\u0006\u000F"}, d2 = {"Landroidx/lifecycle/Lifecycle$Event;", "", "(Ljava/lang/String;I)V", "targetState", "Landroidx/lifecycle/Lifecycle$State;", "getTargetState", "()Landroidx/lifecycle/Lifecycle$State;", "ON_CREATE", "ON_START", "ON_RESUME", "ON_PAUSE", "ON_STOP", "ON_DESTROY", "ON_ANY", "Companion", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum Event {
        @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0012\u0010\u0007\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0012\u0010\b\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007J\u0012\u0010\t\u001A\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001A\u00020\u0006H\u0007¨\u0006\n"}, d2 = {"Landroidx/lifecycle/Lifecycle$Event$Companion;", "", "()V", "downFrom", "Landroidx/lifecycle/Lifecycle$Event;", "state", "Landroidx/lifecycle/Lifecycle$State;", "downTo", "upFrom", "upTo", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        public static final class Companion {
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
            public final class WhenMappings {
                public static final int[] $EnumSwitchMapping$0;

                static {
                    int[] arr_v = new int[State.values().length];
                    try {
                        arr_v[State.CREATED.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    try {
                        arr_v[State.STARTED.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    try {
                        arr_v[State.RESUMED.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    try {
                        arr_v[State.DESTROYED.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    try {
                        arr_v[State.INITIALIZED.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError unused_ex) {
                    }
                    WhenMappings.$EnumSwitchMapping$0 = arr_v;
                }
            }

            private Companion() {
            }

            public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
            }

            @JvmStatic
            public final Event downFrom(State lifecycle$State0) {
                Intrinsics.checkNotNullParameter(lifecycle$State0, "state");
                switch(lifecycle$State0) {
                    case 1: {
                        return Event.ON_DESTROY;
                    }
                    case 2: {
                        return Event.ON_STOP;
                    }
                    case 3: {
                        return Event.ON_PAUSE;
                    }
                    default: {
                        return null;
                    }
                }
            }

            @JvmStatic
            public final Event downTo(State lifecycle$State0) {
                Intrinsics.checkNotNullParameter(lifecycle$State0, "state");
                switch(lifecycle$State0) {
                    case 1: {
                        return Event.ON_STOP;
                    }
                    case 2: {
                        return Event.ON_PAUSE;
                    }
                    case 4: {
                        return Event.ON_DESTROY;
                    }
                    default: {
                        return null;
                    }
                }
            }

            @JvmStatic
            public final Event upFrom(State lifecycle$State0) {
                Intrinsics.checkNotNullParameter(lifecycle$State0, "state");
                switch(lifecycle$State0) {
                    case 1: {
                        return Event.ON_START;
                    }
                    case 2: {
                        return Event.ON_RESUME;
                    }
                    case 5: {
                        return Event.ON_CREATE;
                    }
                    default: {
                        return null;
                    }
                }
            }

            @JvmStatic
            public final Event upTo(State lifecycle$State0) {
                Intrinsics.checkNotNullParameter(lifecycle$State0, "state");
                switch(lifecycle$State0) {
                    case 1: {
                        return Event.ON_CREATE;
                    }
                    case 2: {
                        return Event.ON_START;
                    }
                    case 3: {
                        return Event.ON_RESUME;
                    }
                    default: {
                        return null;
                    }
                }
            }
        }

        @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
        public final class androidx.lifecycle.Lifecycle.Event.WhenMappings {
            public static final int[] $EnumSwitchMapping$0;

            static {
                int[] arr_v = new int[Event.values().length];
                try {
                    arr_v[Event.ON_CREATE.ordinal()] = 1;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[Event.ON_STOP.ordinal()] = 2;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[Event.ON_START.ordinal()] = 3;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[Event.ON_PAUSE.ordinal()] = 4;
                }
                catch(NoSuchFieldError unused_ex) {
                }
                try {
                    arr_v[Event.ON_RESUME.ordinal()] = 5;
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
                androidx.lifecycle.Lifecycle.Event.WhenMappings.$EnumSwitchMapping$0 = arr_v;
            }
        }

        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY;

        public static final Companion Companion;

        private static final Event[] $values() [...] // Inlined contents

        static {
            Event.Companion = new Companion(null);
        }

        @JvmStatic
        public static final Event downFrom(State lifecycle$State0) {
            return Event.Companion.downFrom(lifecycle$State0);
        }

        @JvmStatic
        public static final Event downTo(State lifecycle$State0) {
            return Event.Companion.downTo(lifecycle$State0);
        }

        public final State getTargetState() {
            switch(this) {
                case 1: 
                case 2: {
                    return State.CREATED;
                }
                case 3: 
                case 4: {
                    return State.STARTED;
                }
                case 5: {
                    return State.RESUMED;
                }
                case 6: {
                    return State.DESTROYED;
                }
                default: {
                    throw new IllegalArgumentException(this + " has no target state");
                }
            }
        }

        @JvmStatic
        public static final Event upFrom(State lifecycle$State0) {
            return Event.Companion.upFrom(lifecycle$State0);
        }

        @JvmStatic
        public static final Event upTo(State lifecycle$State0) {
            return Event.Companion.upTo(lifecycle$State0);
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000E\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u0000j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000B"}, d2 = {"Landroidx/lifecycle/Lifecycle$State;", "", "(Ljava/lang/String;I)V", "isAtLeast", "", "state", "DESTROYED", "INITIALIZED", "CREATED", "STARTED", "RESUMED", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        private static final State[] $values() [...] // Inlined contents

        public final boolean isAtLeast(State lifecycle$State0) {
            Intrinsics.checkNotNullParameter(lifecycle$State0, "state");
            return this.compareTo(lifecycle$State0) >= 0;
        }
    }

    private AtomicReference internalScopeRef;

    public Lifecycle() {
        this.internalScopeRef = new AtomicReference();
    }

    public abstract void addObserver(LifecycleObserver arg1);

    public abstract State getCurrentState();

    public final AtomicReference getInternalScopeRef() {
        return this.internalScopeRef;
    }

    public abstract void removeObserver(LifecycleObserver arg1);

    public final void setInternalScopeRef(AtomicReference atomicReference0) {
        Intrinsics.checkNotNullParameter(atomicReference0, "<set-?>");
        this.internalScopeRef = atomicReference0;
    }
}

