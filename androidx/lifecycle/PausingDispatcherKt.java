package androidx.lifecycle;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u00000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u001AD\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\'\u0010\u0003\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u00A2\u0006\u0002\b\bH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\t\u001AD\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\n2\'\u0010\u0003\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u00A2\u0006\u0002\b\bH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000B\u001AD\u0010\f\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\'\u0010\u0003\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u00A2\u0006\u0002\b\bH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\t\u001AD\u0010\f\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\n2\'\u0010\u0003\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u00A2\u0006\u0002\b\bH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000B\u001AD\u0010\r\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\'\u0010\u0003\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u00A2\u0006\u0002\b\bH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\t\u001AD\u0010\r\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\n2\'\u0010\u0003\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u00A2\u0006\u0002\b\bH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u000B\u001AL\u0010\u000E\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u000F\u001A\u00020\u00102\'\u0010\u0003\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004\u00A2\u0006\u0002\b\bH\u0087@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006\u0012"}, d2 = {"whenCreated", "T", "Landroidx/lifecycle/Lifecycle;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/lifecycle/Lifecycle;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "whenResumed", "whenStarted", "whenStateAtLeast", "minState", "Landroidx/lifecycle/Lifecycle$State;", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lifecycle-common"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class PausingDispatcherKt {
    @Deprecated(message = "whenCreated has been deprecated because it runs the block on a pausing dispatcher that suspends, rather than cancels work when the lifecycle state goes below the given state. Use withCreated for non-suspending work that needs to run only once when the Lifecycle changes.")
    public static final Object whenCreated(Lifecycle lifecycle0, Function2 function20, Continuation continuation0) {
        return PausingDispatcherKt.whenStateAtLeast(lifecycle0, State.CREATED, function20, continuation0);
    }

    @Deprecated(message = "whenCreated has been deprecated because it runs the block on a pausing dispatcher that suspends, rather than cancels work when the lifecycle state goes below the given state. Use withCreated for non-suspending work that needs to run only once when the Lifecycle changes.")
    public static final Object whenCreated(LifecycleOwner lifecycleOwner0, Function2 function20, Continuation continuation0) {
        return PausingDispatcherKt.whenCreated(lifecycleOwner0.getLifecycle(), function20, continuation0);
    }

    @Deprecated(message = "whenResumed has been deprecated because it runs the block on a pausing dispatcher that suspends, rather than cancels work when the lifecycle state goes below the given state. Use withResumed for non-suspending work that needs to run only once when the Lifecycle changes.")
    public static final Object whenResumed(Lifecycle lifecycle0, Function2 function20, Continuation continuation0) {
        return PausingDispatcherKt.whenStateAtLeast(lifecycle0, State.RESUMED, function20, continuation0);
    }

    @Deprecated(message = "whenResumed has been deprecated because it runs the block on a pausing dispatcher that suspends, rather than cancels work when the lifecycle state goes below the given state. Use withResumed for non-suspending work that needs to run only once when the Lifecycle changes.")
    public static final Object whenResumed(LifecycleOwner lifecycleOwner0, Function2 function20, Continuation continuation0) {
        return PausingDispatcherKt.whenResumed(lifecycleOwner0.getLifecycle(), function20, continuation0);
    }

    @Deprecated(message = "whenStarted has been deprecated because it runs the block on a pausing dispatcher that suspends, rather than cancels work when the lifecycle state goes below the given state. Use withStarted for non-suspending work that needs to run only once when the Lifecycle changes.")
    public static final Object whenStarted(Lifecycle lifecycle0, Function2 function20, Continuation continuation0) {
        return PausingDispatcherKt.whenStateAtLeast(lifecycle0, State.STARTED, function20, continuation0);
    }

    @Deprecated(message = "whenStarted has been deprecated because it runs the block on a pausing dispatcher that suspends, rather than cancels work when the lifecycle state goes below the given state. Use withStarted for non-suspending work that needs to run only once when the Lifecycle changes.")
    public static final Object whenStarted(LifecycleOwner lifecycleOwner0, Function2 function20, Continuation continuation0) {
        return PausingDispatcherKt.whenStarted(lifecycleOwner0.getLifecycle(), function20, continuation0);
    }

    @Deprecated(message = "whenStateAtLeast has been deprecated because it runs the block on a pausing dispatcher that suspends, rather than cancels work when the lifecycle state goes below the given state. Use withStateAtLeast for non-suspending work that needs to run only once when the Lifecycle changes.")
    public static final Object whenStateAtLeast(Lifecycle lifecycle0, State lifecycle$State0, Function2 function20, Continuation continuation0) {
        return BuildersKt.withContext(Dispatchers.getMain().getImmediate(), new Function2(lifecycle0, lifecycle$State0, function20, null) {
            final Function2 $block;
            final State $minState;
            final Lifecycle $this_whenStateAtLeast;
            private Object L$0;
            int label;

            {
                this.$this_whenStateAtLeast = lifecycle0;
                this.$minState = lifecycle$State0;
                this.$block = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.lifecycle.PausingDispatcherKt.whenStateAtLeast.2 pausingDispatcherKt$whenStateAtLeast$20 = new androidx.lifecycle.PausingDispatcherKt.whenStateAtLeast.2(this.$this_whenStateAtLeast, this.$minState, this.$block, continuation0);
                pausingDispatcherKt$whenStateAtLeast$20.L$0 = object0;
                return pausingDispatcherKt$whenStateAtLeast$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.lifecycle.PausingDispatcherKt.whenStateAtLeast.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                LifecycleController lifecycleController1;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        Job job0 = (Job)((CoroutineScope)this.L$0).getCoroutineContext().get(Job.Key);
                        if(job0 == null) {
                            throw new IllegalStateException("when[State] methods should have a parent job");
                        }
                        PausingDispatcher pausingDispatcher0 = new PausingDispatcher();
                        LifecycleController lifecycleController0 = new LifecycleController(this.$this_whenStateAtLeast, this.$minState, pausingDispatcher0.dispatchQueue, job0);
                        try {
                            this.L$0 = lifecycleController0;
                            this.label = 1;
                            object0 = BuildersKt.withContext(pausingDispatcher0, this.$block, this);
                        }
                        catch(Throwable throwable0) {
                            lifecycleController1 = lifecycleController0;
                            lifecycleController1.finish();
                            throw throwable0;
                        }
                        if(object0 == object1) {
                            return object1;
                        }
                        lifecycleController1 = lifecycleController0;
                        break;
                    }
                    case 1: {
                        lifecycleController1 = (LifecycleController)this.L$0;
                        try {
                            ResultKt.throwOnFailure(object0);
                            break;
                        }
                        catch(Throwable throwable0) {
                        }
                        lifecycleController1.finish();
                        throw throwable0;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                lifecycleController1.finish();
                return object0;
            }
        }, continuation0);
    }
}

