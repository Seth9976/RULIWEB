package androidx.lifecycle;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job.DefaultImpls;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001AF\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\'\u0010\u0005\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000B\u001AF\u0010\u0000\u001A\u00020\u0001*\u00020\f2\u0006\u0010\u0003\u001A\u00020\u00042\'\u0010\u0005\u001A#\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006¢\u0006\u0002\b\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u000E"}, d2 = {"repeatOnLifecycle", "", "Landroidx/lifecycle/Lifecycle;", "state", "Landroidx/lifecycle/Lifecycle$State;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Landroidx/lifecycle/Lifecycle;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/lifecycle/LifecycleOwner;", "(Landroidx/lifecycle/LifecycleOwner;Landroidx/lifecycle/Lifecycle$State;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "lifecycle-runtime-ktx_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class RepeatOnLifecycleKt {
    public static final Object repeatOnLifecycle(Lifecycle lifecycle0, State lifecycle$State0, Function2 function20, Continuation continuation0) {
        if(lifecycle$State0 == State.INITIALIZED) {
            throw new IllegalArgumentException("repeatOnLifecycle cannot start work with the INITIALIZED lifecycle state.");
        }
        if(lifecycle0.getCurrentState() == State.DESTROYED) {
            return Unit.INSTANCE;
        }
        Object object0 = CoroutineScopeKt.coroutineScope(new Function2(lifecycle0, lifecycle$State0, function20, null) {
            final Function2 $block;
            final State $state;
            final Lifecycle $this_repeatOnLifecycle;
            private Object L$0;
            int label;

            {
                this.$this_repeatOnLifecycle = lifecycle0;
                this.$state = lifecycle$State0;
                this.$block = function20;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3 repeatOnLifecycleKt$repeatOnLifecycle$30 = new androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3(this.$this_repeatOnLifecycle, this.$state, this.$block, continuation0);
                repeatOnLifecycleKt$repeatOnLifecycle$30.L$0 = object0;
                return repeatOnLifecycleKt$repeatOnLifecycle$30;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        CoroutineContext coroutineContext0 = Dispatchers.getMain().getImmediate();
                        androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1 repeatOnLifecycleKt$repeatOnLifecycle$3$10 = new Function2(this.$state, coroutineScope0, this.$block, null) {
                            final CoroutineScope $$this$coroutineScope;
                            final Function2 $block;
                            final State $state;
                            final Lifecycle $this_repeatOnLifecycle;
                            Object L$0;
                            Object L$1;
                            Object L$2;
                            Object L$3;
                            Object L$4;
                            Object L$5;
                            int label;

                            {
                                this.$this_repeatOnLifecycle = lifecycle0;
                                this.$state = lifecycle$State0;
                                this.$$this$coroutineScope = coroutineScope0;
                                this.$block = function20;
                                super(2, continuation0);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Continuation create(Object object0, Continuation continuation0) {
                                return new androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1(this.$this_repeatOnLifecycle, this.$state, this.$$this$coroutineScope, this.$block, continuation0);
                            }

                            @Override  // kotlin.jvm.functions.Function2
                            public Object invoke(Object object0, Object object1) {
                                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                            }

                            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                return ((androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                            }

                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                            public final Object invokeSuspend(Object object0) {
                                ObjectRef ref$ObjectRef2;
                                Throwable throwable1;
                                ObjectRef ref$ObjectRef1;
                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                switch(this.label) {
                                    case 0: {
                                        ResultKt.throwOnFailure(object0);
                                        if(this.$this_repeatOnLifecycle.getCurrentState() == State.DESTROYED) {
                                            return Unit.INSTANCE;
                                        }
                                        ObjectRef ref$ObjectRef0 = new ObjectRef();
                                        ref$ObjectRef1 = new ObjectRef();
                                        try {
                                            this.L$0 = ref$ObjectRef0;
                                            this.L$1 = ref$ObjectRef1;
                                            this.L$2 = this.$state;
                                            this.L$3 = this.$this_repeatOnLifecycle;
                                            this.L$4 = this.$$this$coroutineScope;
                                            this.L$5 = this.$block;
                                            this.label = 1;
                                            CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(this), 1);
                                            cancellableContinuationImpl0.initCancellability();
                                            Event lifecycle$Event0 = Event.Companion.upTo(this.$state);
                                            Event lifecycle$Event1 = Event.Companion.downFrom(this.$state);
                                            Mutex mutex0 = MutexKt.Mutex$default(false, 1, null);
                                            ref$ObjectRef1.element = new LifecycleEventObserver() {
                                                @Override  // androidx.lifecycle.LifecycleEventObserver
                                                public final void onStateChanged(LifecycleOwner lifecycleOwner0, Event lifecycle$Event0) {
                                                    Intrinsics.checkNotNullParameter(lifecycleOwner0, "<anonymous parameter 0>");
                                                    Intrinsics.checkNotNullParameter(lifecycle$Event0, "event");
                                                    if(lifecycle$Event0 == ref$ObjectRef0) {
                                                        androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1.1.1.1 repeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$10 = new Function2(this.$block, null) {
                                                            final Function2 $block;
                                                            final Mutex $mutex;
                                                            Object L$0;
                                                            Object L$1;
                                                            int label;

                                                            {
                                                                this.$mutex = mutex0;
                                                                this.$block = function20;
                                                                super(2, continuation0);
                                                            }

                                                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Continuation create(Object object0, Continuation continuation0) {
                                                                return new androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1.1.1.1(this.$mutex, this.$block, continuation0);
                                                            }

                                                            @Override  // kotlin.jvm.functions.Function2
                                                            public Object invoke(Object object0, Object object1) {
                                                                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                                            }

                                                            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                                                return ((androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1.1.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                                            }

                                                            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                            public final Object invokeSuspend(Object object0) {
                                                                Throwable throwable1;
                                                                Mutex mutex2;
                                                                Function2 function20;
                                                                Mutex mutex0;
                                                                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                switch(this.label) {
                                                                    case 0: {
                                                                        ResultKt.throwOnFailure(object0);
                                                                        mutex0 = this.$mutex;
                                                                        function20 = this.$block;
                                                                        this.L$0 = mutex0;
                                                                        this.L$1 = function20;
                                                                        this.label = 1;
                                                                        if(mutex0.lock(null, this) == object1) {
                                                                            return object1;
                                                                        }
                                                                        goto label_15;
                                                                    }
                                                                    case 1: {
                                                                        function20 = (Function2)this.L$1;
                                                                        Mutex mutex1 = (Mutex)this.L$0;
                                                                        ResultKt.throwOnFailure(object0);
                                                                        mutex0 = mutex1;
                                                                        try {
                                                                        label_15:
                                                                            Function2 function21 = new Function2(null) {
                                                                                final Function2 $block;
                                                                                private Object L$0;
                                                                                int label;

                                                                                {
                                                                                    this.$block = function20;
                                                                                    super(2, continuation0);
                                                                                }

                                                                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                                public final Continuation create(Object object0, Continuation continuation0) {
                                                                                    androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1.1.1.1.1.1 repeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$10 = new androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1.1.1.1.1.1(this.$block, continuation0);
                                                                                    repeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$10.L$0 = object0;
                                                                                    return repeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$1$1$10;
                                                                                }

                                                                                @Override  // kotlin.jvm.functions.Function2
                                                                                public Object invoke(Object object0, Object object1) {
                                                                                    return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
                                                                                }

                                                                                public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                                                                                    return ((androidx.lifecycle.RepeatOnLifecycleKt.repeatOnLifecycle.3.1.1.1.1.1.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                                                                }

                                                                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                                                                public final Object invokeSuspend(Object object0) {
                                                                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                                                                    switch(this.label) {
                                                                                        case 0: {
                                                                                            ResultKt.throwOnFailure(object0);
                                                                                            this.label = 1;
                                                                                            return this.$block.invoke(((CoroutineScope)this.L$0), this) == object1 ? object1 : Unit.INSTANCE;
                                                                                        }
                                                                                        case 1: {
                                                                                            ResultKt.throwOnFailure(object0);
                                                                                            return Unit.INSTANCE;
                                                                                        }
                                                                                        default: {
                                                                                            throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                                                                        }
                                                                                    }
                                                                                }
                                                                            };
                                                                            this.L$0 = mutex0;
                                                                            this.L$1 = null;
                                                                            this.label = 2;
                                                                            if(CoroutineScopeKt.coroutineScope(function21, this) == object1) {
                                                                                return object1;
                                                                            }
                                                                        }
                                                                        catch(Throwable throwable0) {
                                                                            mutex2 = mutex0;
                                                                            throwable1 = throwable0;
                                                                            mutex2.unlock(null);
                                                                            throw throwable1;
                                                                        }
                                                                        mutex2 = mutex0;
                                                                        break;
                                                                    }
                                                                    case 2: {
                                                                        mutex2 = (Mutex)this.L$0;
                                                                        try {
                                                                            ResultKt.throwOnFailure(object0);
                                                                            break;
                                                                        }
                                                                        catch(Throwable throwable1) {
                                                                        }
                                                                        mutex2.unlock(null);
                                                                        throw throwable1;
                                                                    }
                                                                    default: {
                                                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                                                    }
                                                                }
                                                                mutex2.unlock(null);
                                                                return Unit.INSTANCE;
                                                            }
                                                        };
                                                        this.$$this$coroutineScope.element = BuildersKt.launch$default(lifecycle$Event1, null, null, repeatOnLifecycleKt$repeatOnLifecycle$3$1$1$1$10, 3, null);
                                                        return;
                                                    }
                                                    if(lifecycle$Event0 == cancellableContinuationImpl0) {
                                                        Job job0 = (Job)this.$$this$coroutineScope.element;
                                                        if(job0 != null) {
                                                            DefaultImpls.cancel$default(job0, null, 1, null);
                                                        }
                                                        this.$$this$coroutineScope.element = null;
                                                    }
                                                    if(lifecycle$Event0 == Event.ON_DESTROY) {
                                                        mutex0.resumeWith(Unit.INSTANCE);
                                                    }
                                                }
                                            };
                                            Object object2 = ref$ObjectRef1.element;
                                            Intrinsics.checkNotNull(object2, "null cannot be cast to non-null type androidx.lifecycle.LifecycleEventObserver");
                                            this.$this_repeatOnLifecycle.addObserver(((LifecycleEventObserver)object2));
                                            Object object3 = cancellableContinuationImpl0.getResult();
                                            if(object3 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                                                DebugProbesKt.probeCoroutineSuspended(this);
                                            }
                                        }
                                        catch(Throwable throwable0) {
                                            throwable1 = throwable0;
                                            ref$ObjectRef2 = ref$ObjectRef0;
                                            goto label_46;
                                        }
                                        if(object3 == object1) {
                                            return object1;
                                        }
                                        ref$ObjectRef2 = ref$ObjectRef0;
                                        break;
                                    }
                                    case 1: {
                                        Function2 function20 = (Function2)this.L$5;
                                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$4;
                                        Lifecycle lifecycle0 = (Lifecycle)this.L$3;
                                        State lifecycle$State0 = (State)this.L$2;
                                        ref$ObjectRef1 = (ObjectRef)this.L$1;
                                        ref$ObjectRef2 = (ObjectRef)this.L$0;
                                        try {
                                            ResultKt.throwOnFailure(object0);
                                            break;
                                        }
                                        catch(Throwable throwable2) {
                                            throwable1 = throwable2;
                                        }
                                    label_46:
                                        Job job0 = (Job)ref$ObjectRef2.element;
                                        if(job0 != null) {
                                            DefaultImpls.cancel$default(job0, null, 1, null);
                                        }
                                        LifecycleEventObserver lifecycleEventObserver0 = (LifecycleEventObserver)ref$ObjectRef1.element;
                                        if(lifecycleEventObserver0 != null) {
                                            this.$this_repeatOnLifecycle.removeObserver(lifecycleEventObserver0);
                                        }
                                        throw throwable1;
                                    }
                                    default: {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                }
                                Job job1 = (Job)ref$ObjectRef2.element;
                                if(job1 != null) {
                                    DefaultImpls.cancel$default(job1, null, 1, null);
                                }
                                LifecycleEventObserver lifecycleEventObserver1 = (LifecycleEventObserver)ref$ObjectRef1.element;
                                if(lifecycleEventObserver1 != null) {
                                    this.$this_repeatOnLifecycle.removeObserver(lifecycleEventObserver1);
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        this.label = 1;
                        return BuildersKt.withContext(coroutineContext0, repeatOnLifecycleKt$repeatOnLifecycle$3$10, this) == object1 ? object1 : Unit.INSTANCE;
                    }
                    case 1: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        }, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    public static final Object repeatOnLifecycle(LifecycleOwner lifecycleOwner0, State lifecycle$State0, Function2 function20, Continuation continuation0) {
        Object object0 = RepeatOnLifecycleKt.repeatOnLifecycle(lifecycleOwner0.getLifecycle(), lifecycle$State0, function20, continuation0);
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }
}

