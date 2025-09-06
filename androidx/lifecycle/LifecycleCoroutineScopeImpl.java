package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.JobKt__JobKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\f\u001A\u00020\r2\u0006\u0010\u000E\u001A\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u0011H\u0016J\u0006\u0010\u0012\u001A\u00020\rR\u0014\u0010\u0005\u001A\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001A\u0004\b\b\u0010\tR\u0014\u0010\u0003\u001A\u00020\u0004X\u0090\u0004¢\u0006\b\n\u0000\u001A\u0004\b\n\u0010\u000B¨\u0006\u0013"}, d2 = {"Landroidx/lifecycle/LifecycleCoroutineScopeImpl;", "Landroidx/lifecycle/LifecycleCoroutineScope;", "Landroidx/lifecycle/LifecycleEventObserver;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/lifecycle/Lifecycle;Lkotlin/coroutines/CoroutineContext;)V", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getLifecycle$lifecycle_common", "()Landroidx/lifecycle/Lifecycle;", "onStateChanged", "", "source", "Landroidx/lifecycle/LifecycleOwner;", "event", "Landroidx/lifecycle/Lifecycle$Event;", "register", "lifecycle-common"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class LifecycleCoroutineScopeImpl extends LifecycleCoroutineScope implements LifecycleEventObserver {
    private final CoroutineContext coroutineContext;
    private final Lifecycle lifecycle;

    public LifecycleCoroutineScopeImpl(Lifecycle lifecycle0, CoroutineContext coroutineContext0) {
        Intrinsics.checkNotNullParameter(lifecycle0, "lifecycle");
        Intrinsics.checkNotNullParameter(coroutineContext0, "coroutineContext");
        super();
        this.lifecycle = lifecycle0;
        this.coroutineContext = coroutineContext0;
        if(this.getLifecycle$lifecycle_common().getCurrentState() == State.DESTROYED) {
            JobKt__JobKt.cancel$default(this.getCoroutineContext(), null, 1, null);
        }
    }

    @Override  // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override  // androidx.lifecycle.LifecycleCoroutineScope
    public Lifecycle getLifecycle$lifecycle_common() {
        return this.lifecycle;
    }

    @Override  // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
        Intrinsics.checkNotNullParameter(lifecycleOwner0, "source");
        Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
        if(this.getLifecycle$lifecycle_common().getCurrentState().compareTo(State.DESTROYED) <= 0) {
            this.getLifecycle$lifecycle_common().removeObserver(this);
            JobKt__JobKt.cancel$default(this.getCoroutineContext(), null, 1, null);
        }
    }

    public final void register() {
        BuildersKt.launch$default(this, Dispatchers.getMain().getImmediate(), null, new Function2(null) {
            private Object L$0;
            int label;

            {
                LifecycleCoroutineScopeImpl.this = lifecycleCoroutineScopeImpl0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.lifecycle.LifecycleCoroutineScopeImpl.register.1 lifecycleCoroutineScopeImpl$register$10 = new androidx.lifecycle.LifecycleCoroutineScopeImpl.register.1(LifecycleCoroutineScopeImpl.this, continuation0);
                lifecycleCoroutineScopeImpl$register$10.L$0 = object0;
                return lifecycleCoroutineScopeImpl$register$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.lifecycle.LifecycleCoroutineScopeImpl.register.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                if(this.label != 0) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                }
                ResultKt.throwOnFailure(object0);
                CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                if(LifecycleCoroutineScopeImpl.this.getLifecycle$lifecycle_common().getCurrentState().compareTo(State.INITIALIZED) >= 0) {
                    LifecycleCoroutineScopeImpl.this.getLifecycle$lifecycle_common().addObserver(LifecycleCoroutineScopeImpl.this);
                    return Unit.INSTANCE;
                }
                JobKt__JobKt.cancel$default(coroutineScope0.getCoroutineContext(), null, 1, null);
                return Unit.INSTANCE;
            }
        }, 2, null);
    }
}

