package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

@Metadata(d1 = {"\u0000,\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u001AA\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u0006\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00020\b2\f\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0081@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000B\u001A+\u0010\f\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\r\u001A+\u0010\f\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000E2\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A+\u0010\u0010\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\r\u001A+\u0010\u0010\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000E2\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A+\u0010\u0011\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\r\u001A+\u0010\u0011\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000E2\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000F\u001A3\u0010\u0012\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0013\u001A3\u0010\u0012\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u000E2\u0006\u0010\u0003\u001A\u00020\u00042\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0086H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0014\u001A3\u0010\u0015\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u000E\b\u0004\u0010\t\u001A\b\u0012\u0004\u0012\u0002H\u00010\nH\u0081H\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0013\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u0016"}, d2 = {"suspendWithStateAtLeastUnchecked", "R", "Landroidx/lifecycle/Lifecycle;", "state", "Landroidx/lifecycle/Lifecycle$State;", "dispatchNeeded", "", "lifecycleDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "block", "Lkotlin/Function0;", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;ZLkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withCreated", "(Landroidx/lifecycle/Lifecycle;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withResumed", "withStarted", "withStateAtLeast", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withStateAtLeastUnchecked", "lifecycle-runtime-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class WithLifecycleStateKt {
    public static final Object suspendWithStateAtLeastUnchecked(Lifecycle lifecycle0, State lifecycle$State0, boolean z, CoroutineDispatcher coroutineDispatcher0, Function0 function00, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        androidx.lifecycle.WithLifecycleStateKt.suspendWithStateAtLeastUnchecked.2.observer.1 withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$10 = new LifecycleEventObserver() {
            @Override  // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                Object object0;
                Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
                Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
                if(lifecycle$Event0 == Event.Companion.upTo(lifecycle$State0)) {
                    lifecycle0.removeObserver(this);
                    CancellableContinuation cancellableContinuation0 = cancellableContinuationImpl0;
                    try {
                        object0 = Result.constructor-impl(function00.invoke());
                    }
                    catch(Throwable throwable0) {
                        object0 = Result.constructor-impl(ResultKt.createFailure(throwable0));
                    }
                    cancellableContinuation0.resumeWith(object0);
                    return;
                }
                if(lifecycle$Event0 == Event.ON_DESTROY) {
                    lifecycle0.removeObserver(this);
                    Object object1 = Result.constructor-impl(ResultKt.createFailure(new LifecycleDestroyedException()));
                    cancellableContinuationImpl0.resumeWith(object1);
                }
            }
        };
        if(z) {
            Runnable runnable0 = new Runnable() {
                @Override
                public final void run() {
                    lifecycle0.addObserver(withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$10);
                }
            };
            coroutineDispatcher0.dispatch(EmptyCoroutineContext.INSTANCE, runnable0);
        }
        else {
            lifecycle0.addObserver(withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$10);
        }
        cancellableContinuationImpl0.invokeOnCancellation(new Function1(coroutineDispatcher0, lifecycle0, withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$10) {
            final CoroutineDispatcher $lifecycleDispatcher;
            final androidx.lifecycle.WithLifecycleStateKt.suspendWithStateAtLeastUnchecked.2.observer.1 $observer;
            final Lifecycle $this_suspendWithStateAtLeastUnchecked;

            {
                this.$lifecycleDispatcher = coroutineDispatcher0;
                this.$this_suspendWithStateAtLeastUnchecked = lifecycle0;
                this.$observer = withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$observer$10;
                super(1);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                this.invoke(((Throwable)object0));
                return Unit.INSTANCE;
            }

            public final void invoke(Throwable throwable0) {
                if(this.$lifecycleDispatcher.isDispatchNeeded(EmptyCoroutineContext.INSTANCE)) {
                    androidx.lifecycle.WithLifecycleStateKt.suspendWithStateAtLeastUnchecked.2.2.1 withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$2$10 = new Runnable() {
                        @Override
                        public final void run() {
                            this.$observer.removeObserver(this.$observer);
                        }
                    };
                    this.$lifecycleDispatcher.dispatch(EmptyCoroutineContext.INSTANCE, withLifecycleStateKt$suspendWithStateAtLeastUnchecked$2$2$10);
                    return;
                }
                this.$this_suspendWithStateAtLeastUnchecked.removeObserver(this.$observer);
            }
        });
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0;
    }

    public static final Object withCreated(Lifecycle lifecycle0, Function0 function00, Continuation continuation0) {
        State lifecycle$State0 = State.CREATED;
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0);
    }

    public static final Object withCreated(LifecycleOwner lifecycleOwner0, Function0 function00, Continuation continuation0) {
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        State lifecycle$State0 = State.CREATED;
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0);
    }

    private static final Object withCreated$$forInline(Lifecycle lifecycle0, Function0 function00, Continuation continuation0) {
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }

    private static final Object withCreated$$forInline(LifecycleOwner lifecycleOwner0, Function0 function00, Continuation continuation0) {
        lifecycleOwner0.getLifecycle();
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }

    public static final Object withResumed(Lifecycle lifecycle0, Function0 function00, Continuation continuation0) {
        State lifecycle$State0 = State.RESUMED;
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0);
    }

    public static final Object withResumed(LifecycleOwner lifecycleOwner0, Function0 function00, Continuation continuation0) {
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        State lifecycle$State0 = State.RESUMED;
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0);
    }

    private static final Object withResumed$$forInline(Lifecycle lifecycle0, Function0 function00, Continuation continuation0) {
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }

    private static final Object withResumed$$forInline(LifecycleOwner lifecycleOwner0, Function0 function00, Continuation continuation0) {
        lifecycleOwner0.getLifecycle();
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }

    public static final Object withStarted(Lifecycle lifecycle0, Function0 function00, Continuation continuation0) {
        State lifecycle$State0 = State.STARTED;
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0);
    }

    public static final Object withStarted(LifecycleOwner lifecycleOwner0, Function0 function00, Continuation continuation0) {
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        State lifecycle$State0 = State.STARTED;
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0);
    }

    private static final Object withStarted$$forInline(Lifecycle lifecycle0, Function0 function00, Continuation continuation0) {
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }

    private static final Object withStarted$$forInline(LifecycleOwner lifecycleOwner0, Function0 function00, Continuation continuation0) {
        lifecycleOwner0.getLifecycle();
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }

    public static final Object withStateAtLeast(Lifecycle lifecycle0, State lifecycle$State0, Function0 function00, Continuation continuation0) {
        if(lifecycle$State0.compareTo(State.CREATED) < 0) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + lifecycle$State0).toString());
        }
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0);
    }

    public static final Object withStateAtLeast(LifecycleOwner lifecycleOwner0, State lifecycle$State0, Function0 function00, Continuation continuation0) {
        Lifecycle lifecycle0 = lifecycleOwner0.getLifecycle();
        if(lifecycle$State0.compareTo(State.CREATED) < 0) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + lifecycle$State0).toString());
        }
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new androidx.lifecycle.WithLifecycleStateKt.withStateAtLeastUnchecked.2(function00), continuation0);
    }

    private static final Object withStateAtLeast$$forInline(Lifecycle lifecycle0, State lifecycle$State0, Function0 function00, Continuation continuation0) {
        if(lifecycle$State0.compareTo(State.CREATED) < 0) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + lifecycle$State0).toString());
        }
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }

    private static final Object withStateAtLeast$$forInline(LifecycleOwner lifecycleOwner0, State lifecycle$State0, Function0 function00, Continuation continuation0) {
        lifecycleOwner0.getLifecycle();
        if(lifecycle$State0.compareTo(State.CREATED) < 0) {
            throw new IllegalArgumentException(("target state must be CREATED or greater, found " + lifecycle$State0).toString());
        }
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }

    public static final Object withStateAtLeastUnchecked(Lifecycle lifecycle0, State lifecycle$State0, Function0 function00, Continuation continuation0) {
        MainCoroutineDispatcher mainCoroutineDispatcher0 = Dispatchers.getMain().getImmediate();
        if(!mainCoroutineDispatcher0.isDispatchNeeded(continuation0.getContext())) {
            if(lifecycle0.getCurrentState() == State.DESTROYED) {
                throw new LifecycleDestroyedException();
            }
            return lifecycle0.getCurrentState().compareTo(lifecycle$State0) < 0 ? WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, false, mainCoroutineDispatcher0, new Function0(function00) {
                final Function0 $block;

                {
                    this.$block = function00;
                    super(0);
                }

                @Override  // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return this.$block.invoke();
                }
            }, continuation0) : function00.invoke();
        }
        return WithLifecycleStateKt.suspendWithStateAtLeastUnchecked(lifecycle0, lifecycle$State0, true, mainCoroutineDispatcher0, new Function0(function00) {
            final Function0 $block;

            {
                this.$block = function00;
                super(0);
            }

            @Override  // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.$block.invoke();
            }
        }, continuation0);
    }

    private static final Object withStateAtLeastUnchecked$$forInline(Lifecycle lifecycle0, State lifecycle$State0, Function0 function00, Continuation continuation0) {
        Dispatchers.getMain().getImmediate();
        throw new NullPointerException();
    }
}

