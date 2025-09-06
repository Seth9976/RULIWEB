package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.ChannelFlow;

@Metadata(d1 = {"\u0000j\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001A\u001C\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001A\u001C\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001A+\u0010\u0007\u001A\b\u0012\u0004\u0012\u0002H\u00020\b\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010\n\u001A\u00020\u000BH\u0002\u00A2\u0006\u0002\b\f\u001AM\u0010\r\u001A\u00020\u000E\"\u0004\b\u0000\u0010\u0002*\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00020\t2\f\u0010\u0013\u001A\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u0002H\u0002H\u0002\u00A2\u0006\u0004\b\u0017\u0010\u0018\u001AA\u0010\u0019\u001A\u00020\u001A\"\u0004\b\u0000\u0010\u0002*\u00020\u000F2\u0006\u0010\u0010\u001A\u00020\u00112\f\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00020\t2\u0012\u0010\u001B\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u001CH\u0002\u00A2\u0006\u0002\b\u001D\u001AS\u0010\u001E\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012-\u0010\u001F\u001A)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020!\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001A0\"\u0012\u0006\u0012\u0004\u0018\u00010#0 \u00A2\u0006\u0002\b$\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010%\u001A6\u0010&\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010\'\u001A\u00020\u000F2\u0006\u0010\u0014\u001A\u00020\u00152\b\b\u0002\u0010\n\u001A\u00020\u000B\u001A/\u0010(\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010\'\u001A\u00020\u000FH\u0086@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010)\u001A9\u0010(\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\t2\u0006\u0010\'\u001A\u00020\u000F2\u0006\u0010\u0014\u001A\u00020\u00152\u0006\u0010\u0016\u001A\u0002H\u0002\u00A2\u0006\u0002\u0010*\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006+"}, d2 = {"asSharedFlow", "Lkotlinx/coroutines/flow/SharedFlow;", "T", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "asStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "configureSharing", "Lkotlinx/coroutines/flow/SharingConfig;", "Lkotlinx/coroutines/flow/Flow;", "replay", "", "configureSharing$FlowKt__ShareKt", "launchSharing", "Lkotlinx/coroutines/Job;", "Lkotlinx/coroutines/CoroutineScope;", "context", "Lkotlin/coroutines/CoroutineContext;", "upstream", "shared", "started", "Lkotlinx/coroutines/flow/SharingStarted;", "initialValue", "launchSharing$FlowKt__ShareKt", "(Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/CoroutineContext;Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/flow/MutableSharedFlow;Lkotlinx/coroutines/flow/SharingStarted;Ljava/lang/Object;)Lkotlinx/coroutines/Job;", "launchSharingDeferred", "", "result", "Lkotlinx/coroutines/CompletableDeferred;", "launchSharingDeferred$FlowKt__ShareKt", "onSubscription", "action", "Lkotlin/Function2;", "Lkotlinx/coroutines/flow/FlowCollector;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/coroutines/flow/SharedFlow;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/flow/SharedFlow;", "shareIn", "scope", "stateIn", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;Lkotlinx/coroutines/flow/SharingStarted;Ljava/lang/Object;)Lkotlinx/coroutines/flow/StateFlow;", "kotlinx-coroutines-core"}, k = 5, mv = {1, 8, 0}, xi = 0x30, xs = "kotlinx/coroutines/flow/FlowKt")
final class FlowKt__ShareKt {
    public static final SharedFlow asSharedFlow(MutableSharedFlow mutableSharedFlow0) {
        return new ReadonlySharedFlow(mutableSharedFlow0, null);
    }

    public static final StateFlow asStateFlow(MutableStateFlow mutableStateFlow0) {
        return new ReadonlyStateFlow(mutableStateFlow0, null);
    }

    private static final SharingConfig configureSharing$FlowKt__ShareKt(Flow flow0, int v) {
        int v1 = RangesKt.coerceAtLeast(v, 0x40) - v;
        if(flow0 instanceof ChannelFlow) {
            Flow flow1 = ((ChannelFlow)flow0).dropChannelOperators();
            if(flow1 != null) {
                int v2 = ((ChannelFlow)flow0).capacity;
                if(v2 != -3 && (v2 != -2 && v2 != 0)) {
                    return new SharingConfig(flow1, ((ChannelFlow)flow0).capacity, ((ChannelFlow)flow0).onBufferOverflow, ((ChannelFlow)flow0).context);
                }
                if(((ChannelFlow)flow0).onBufferOverflow == BufferOverflow.SUSPEND) {
                    return ((ChannelFlow)flow0).capacity == 0 ? new SharingConfig(flow1, 0, ((ChannelFlow)flow0).onBufferOverflow, ((ChannelFlow)flow0).context) : new SharingConfig(flow1, v1, ((ChannelFlow)flow0).onBufferOverflow, ((ChannelFlow)flow0).context);
                }
                return v == 0 ? new SharingConfig(flow1, 1, ((ChannelFlow)flow0).onBufferOverflow, ((ChannelFlow)flow0).context) : new SharingConfig(flow1, 0, ((ChannelFlow)flow0).onBufferOverflow, ((ChannelFlow)flow0).context);
            }
        }
        return new SharingConfig(flow0, v1, BufferOverflow.SUSPEND, EmptyCoroutineContext.INSTANCE);
    }

    // 去混淆评级： 低(20)
    private static final Job launchSharing$FlowKt__ShareKt(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, Flow flow0, MutableSharedFlow mutableSharedFlow0, SharingStarted sharingStarted0, Object object0) {
        return Intrinsics.areEqual(sharingStarted0, SharingStarted.Companion.getEagerly()) ? BuildersKt.launch(coroutineScope0, coroutineContext0, CoroutineStart.DEFAULT, new Function2(sharingStarted0, flow0, mutableSharedFlow0, object0, null) {
            final Object $initialValue;
            final MutableSharedFlow $shared;
            final SharingStarted $started;
            final Flow $upstream;
            int label;

            {
                this.$started = sharingStarted0;
                this.$upstream = flow0;
                this.$shared = mutableSharedFlow0;
                this.$initialValue = object0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1(this.$started, this.$upstream, this.$shared, this.$initialValue, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        if(this.$started == SharingStarted.Companion.getEagerly()) {
                            this.label = 1;
                            if(this.$upstream.collect(this.$shared, this) == object1) {
                                return object1;
                            }
                        }
                        else if(this.$started == SharingStarted.Companion.getLazily()) {
                            Flow flow0 = this.$shared.getSubscriptionCount();
                            Function2 function20 = new Function2() {
                                int I$0;
                                int label;

                                {
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.1 flowKt__ShareKt$launchSharing$1$10 = new kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.1(continuation0);
                                    flowKt__ShareKt$launchSharing$1$10.I$0 = ((Number)object0).intValue();
                                    return flowKt__ShareKt$launchSharing$1$10;
                                }

                                public final Object invoke(int v, Continuation continuation0) {
                                    return ((kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.1)this.create(v, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((Number)object0).intValue(), ((Continuation)object1));
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    if(this.label != 0) {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(object0);
                                    return this.I$0 <= 0 ? Boxing.boxBoolean(false) : Boxing.boxBoolean(true);
                                }
                            };
                            this.label = 2;
                            if(FlowKt.first(flow0, function20, this) == object1) {
                                return object1;
                            }
                        label_17:
                            this.label = 3;
                            if(this.$upstream.collect(this.$shared, this) == object1) {
                                return object1;
                            }
                        }
                        else {
                            StateFlow stateFlow0 = this.$shared.getSubscriptionCount();
                            Flow flow1 = FlowKt.distinctUntilChanged(this.$started.command(stateFlow0));
                            kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.2 flowKt__ShareKt$launchSharing$1$20 = new Function2(this.$shared, this.$initialValue, null) {
                                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
                                public final class WhenMappings {
                                    public static final int[] $EnumSwitchMapping$0;

                                    static {
                                        int[] arr_v = new int[SharingCommand.values().length];
                                        try {
                                            arr_v[SharingCommand.START.ordinal()] = 1;
                                        }
                                        catch(NoSuchFieldError unused_ex) {
                                        }
                                        try {
                                            arr_v[SharingCommand.STOP.ordinal()] = 2;
                                        }
                                        catch(NoSuchFieldError unused_ex) {
                                        }
                                        try {
                                            arr_v[SharingCommand.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;
                                        }
                                        catch(NoSuchFieldError unused_ex) {
                                        }
                                        WhenMappings.$EnumSwitchMapping$0 = arr_v;
                                    }
                                }

                                final Object $initialValue;
                                final MutableSharedFlow $shared;
                                final Flow $upstream;
                                Object L$0;
                                int label;

                                {
                                    this.$upstream = flow0;
                                    this.$shared = mutableSharedFlow0;
                                    this.$initialValue = object0;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.2 flowKt__ShareKt$launchSharing$1$20 = new kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.2(this.$upstream, this.$shared, this.$initialValue, continuation0);
                                    flowKt__ShareKt$launchSharing$1$20.L$0 = object0;
                                    return flowKt__ShareKt$launchSharing$1$20;
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((SharingCommand)object0), ((Continuation)object1));
                                }

                                public final Object invoke(SharingCommand sharingCommand0, Continuation continuation0) {
                                    return ((kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.2)this.create(sharingCommand0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            switch(WhenMappings.$EnumSwitchMapping$0[((SharingCommand)this.L$0).ordinal()]) {
                                                case 1: {
                                                    this.label = 1;
                                                    return this.$upstream.collect(this.$shared, this) == object1 ? object1 : Unit.INSTANCE;
                                                }
                                                case 3: {
                                                    if(this.$initialValue == SharedFlowKt.NO_VALUE) {
                                                        this.$shared.resetReplayCache();
                                                        return Unit.INSTANCE;
                                                    }
                                                    this.$shared.tryEmit(this.$initialValue);
                                                    return Unit.INSTANCE;
                                                }
                                                default: {
                                                    return Unit.INSTANCE;
                                                }
                                            }
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
                            this.label = 4;
                            if(FlowKt.collectLatest(flow1, flowKt__ShareKt$launchSharing$1$20, this) == object1) {
                                return object1;
                            }
                        }
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        goto label_17;
                    }
                    case 1: 
                    case 3: 
                    case 4: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                return Unit.INSTANCE;
            }
        }) : BuildersKt.launch(coroutineScope0, coroutineContext0, CoroutineStart.UNDISPATCHED, new Function2(sharingStarted0, flow0, mutableSharedFlow0, object0, null) {
            final Object $initialValue;
            final MutableSharedFlow $shared;
            final SharingStarted $started;
            final Flow $upstream;
            int label;

            {
                this.$started = sharingStarted0;
                this.$upstream = flow0;
                this.$shared = mutableSharedFlow0;
                this.$initialValue = object0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1(this.$started, this.$upstream, this.$shared, this.$initialValue, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        if(this.$started == SharingStarted.Companion.getEagerly()) {
                            this.label = 1;
                            if(this.$upstream.collect(this.$shared, this) == object1) {
                                return object1;
                            }
                        }
                        else if(this.$started == SharingStarted.Companion.getLazily()) {
                            Flow flow0 = this.$shared.getSubscriptionCount();
                            Function2 function20 = new Function2() {
                                int I$0;
                                int label;

                                {
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.1 flowKt__ShareKt$launchSharing$1$10 = new kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.1(continuation0);
                                    flowKt__ShareKt$launchSharing$1$10.I$0 = ((Number)object0).intValue();
                                    return flowKt__ShareKt$launchSharing$1$10;
                                }

                                public final Object invoke(int v, Continuation continuation0) {
                                    return ((kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.1)this.create(v, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((Number)object0).intValue(), ((Continuation)object1));
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    if(this.label != 0) {
                                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                    }
                                    ResultKt.throwOnFailure(object0);
                                    return this.I$0 <= 0 ? Boxing.boxBoolean(false) : Boxing.boxBoolean(true);
                                }
                            };
                            this.label = 2;
                            if(FlowKt.first(flow0, function20, this) == object1) {
                                return object1;
                            }
                        label_17:
                            this.label = 3;
                            if(this.$upstream.collect(this.$shared, this) == object1) {
                                return object1;
                            }
                        }
                        else {
                            StateFlow stateFlow0 = this.$shared.getSubscriptionCount();
                            Flow flow1 = FlowKt.distinctUntilChanged(this.$started.command(stateFlow0));
                            kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.2 flowKt__ShareKt$launchSharing$1$20 = new Function2(this.$shared, this.$initialValue, null) {
                                @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
                                public final class WhenMappings {
                                    public static final int[] $EnumSwitchMapping$0;

                                    static {
                                        int[] arr_v = new int[SharingCommand.values().length];
                                        try {
                                            arr_v[SharingCommand.START.ordinal()] = 1;
                                        }
                                        catch(NoSuchFieldError unused_ex) {
                                        }
                                        try {
                                            arr_v[SharingCommand.STOP.ordinal()] = 2;
                                        }
                                        catch(NoSuchFieldError unused_ex) {
                                        }
                                        try {
                                            arr_v[SharingCommand.STOP_AND_RESET_REPLAY_CACHE.ordinal()] = 3;
                                        }
                                        catch(NoSuchFieldError unused_ex) {
                                        }
                                        WhenMappings.$EnumSwitchMapping$0 = arr_v;
                                    }
                                }

                                final Object $initialValue;
                                final MutableSharedFlow $shared;
                                final Flow $upstream;
                                Object L$0;
                                int label;

                                {
                                    this.$upstream = flow0;
                                    this.$shared = mutableSharedFlow0;
                                    this.$initialValue = object0;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.2 flowKt__ShareKt$launchSharing$1$20 = new kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.2(this.$upstream, this.$shared, this.$initialValue, continuation0);
                                    flowKt__ShareKt$launchSharing$1$20.L$0 = object0;
                                    return flowKt__ShareKt$launchSharing$1$20;
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((SharingCommand)object0), ((Continuation)object1));
                                }

                                public final Object invoke(SharingCommand sharingCommand0, Continuation continuation0) {
                                    return ((kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1.2)this.create(sharingCommand0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            switch(WhenMappings.$EnumSwitchMapping$0[((SharingCommand)this.L$0).ordinal()]) {
                                                case 1: {
                                                    this.label = 1;
                                                    return this.$upstream.collect(this.$shared, this) == object1 ? object1 : Unit.INSTANCE;
                                                }
                                                case 3: {
                                                    if(this.$initialValue == SharedFlowKt.NO_VALUE) {
                                                        this.$shared.resetReplayCache();
                                                        return Unit.INSTANCE;
                                                    }
                                                    this.$shared.tryEmit(this.$initialValue);
                                                    return Unit.INSTANCE;
                                                }
                                                default: {
                                                    return Unit.INSTANCE;
                                                }
                                            }
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
                            this.label = 4;
                            if(FlowKt.collectLatest(flow1, flowKt__ShareKt$launchSharing$1$20, this) == object1) {
                                return object1;
                            }
                        }
                        break;
                    }
                    case 2: {
                        ResultKt.throwOnFailure(object0);
                        goto label_17;
                    }
                    case 1: 
                    case 3: 
                    case 4: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                return Unit.INSTANCE;
            }
        });
    }

    private static final void launchSharingDeferred$FlowKt__ShareKt(CoroutineScope coroutineScope0, CoroutineContext coroutineContext0, Flow flow0, CompletableDeferred completableDeferred0) {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope0, coroutineContext0, null, new Function2(flow0, completableDeferred0, null) {
            final CompletableDeferred $result;
            final Flow $upstream;
            private Object L$0;
            int label;

            {
                this.$upstream = flow0;
                this.$result = completableDeferred0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharingDeferred.1 flowKt__ShareKt$launchSharingDeferred$10 = new kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharingDeferred.1(this.$upstream, this.$result, continuation0);
                flowKt__ShareKt$launchSharingDeferred$10.L$0 = object0;
                return flowKt__ShareKt$launchSharingDeferred$10;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharingDeferred.1)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        CoroutineScope coroutineScope0 = (CoroutineScope)this.L$0;
                        try {
                            kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharingDeferred.1.1 flowKt__ShareKt$launchSharingDeferred$1$10 = new FlowCollector() {
                                @Override  // kotlinx.coroutines.flow.FlowCollector
                                public final Object emit(Object object0, Continuation continuation0) {
                                    Unit unit0;
                                    MutableStateFlow mutableStateFlow0 = (MutableStateFlow)coroutineScope0.element;
                                    if(mutableStateFlow0 == null) {
                                        unit0 = null;
                                    }
                                    else {
                                        mutableStateFlow0.setValue(object0);
                                        unit0 = Unit.INSTANCE;
                                    }
                                    if(unit0 == null) {
                                        MutableStateFlow mutableStateFlow1 = StateFlowKt.MutableStateFlow(object0);
                                        ReadonlyStateFlow readonlyStateFlow0 = new ReadonlyStateFlow(mutableStateFlow1, JobKt.getJob(this.$result.getCoroutineContext()));
                                        this.$result.complete(readonlyStateFlow0);
                                        coroutineScope0.element = mutableStateFlow1;
                                    }
                                    return Unit.INSTANCE;
                                }
                            };
                            this.label = 1;
                            return this.$upstream.collect(flowKt__ShareKt$launchSharingDeferred$1$10, this) == object1 ? object1 : Unit.INSTANCE;
                        label_10:
                            ResultKt.throwOnFailure(object0);
                            return Unit.INSTANCE;
                        }
                        catch(Throwable throwable0) {
                            this.$result.completeExceptionally(throwable0);
                            throw throwable0;
                        }
                    }
                    case 1: {
                        goto label_10;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
            }
        }, 2, null);
    }

    public static final SharedFlow onSubscription(SharedFlow sharedFlow0, Function2 function20) {
        return new SubscribedSharedFlow(sharedFlow0, function20);
    }

    public static final SharedFlow shareIn(Flow flow0, CoroutineScope coroutineScope0, SharingStarted sharingStarted0, int v) {
        SharingConfig sharingConfig0 = FlowKt__ShareKt.configureSharing$FlowKt__ShareKt(flow0, v);
        MutableSharedFlow mutableSharedFlow0 = SharedFlowKt.MutableSharedFlow(v, sharingConfig0.extraBufferCapacity, sharingConfig0.onBufferOverflow);
        return new ReadonlySharedFlow(mutableSharedFlow0, FlowKt__ShareKt.launchSharing$FlowKt__ShareKt(coroutineScope0, sharingConfig0.context, sharingConfig0.upstream, mutableSharedFlow0, sharingStarted0, SharedFlowKt.NO_VALUE));
    }

    public static SharedFlow shareIn$default(Flow flow0, CoroutineScope coroutineScope0, SharingStarted sharingStarted0, int v, int v1, Object object0) {
        if((v1 & 4) != 0) {
            v = 0;
        }
        return FlowKt.shareIn(flow0, coroutineScope0, sharingStarted0, v);
    }

    public static final Object stateIn(Flow flow0, CoroutineScope coroutineScope0, Continuation continuation0) {
        SharingConfig sharingConfig0 = FlowKt__ShareKt.configureSharing$FlowKt__ShareKt(flow0, 1);
        CompletableDeferred completableDeferred0 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        FlowKt__ShareKt.launchSharingDeferred$FlowKt__ShareKt(coroutineScope0, sharingConfig0.context, sharingConfig0.upstream, completableDeferred0);
        return completableDeferred0.await(continuation0);
    }

    public static final StateFlow stateIn(Flow flow0, CoroutineScope coroutineScope0, SharingStarted sharingStarted0, Object object0) {
        SharingConfig sharingConfig0 = FlowKt__ShareKt.configureSharing$FlowKt__ShareKt(flow0, 1);
        MutableStateFlow mutableStateFlow0 = StateFlowKt.MutableStateFlow(object0);
        return new ReadonlyStateFlow(mutableStateFlow0, FlowKt__ShareKt.launchSharing$FlowKt__ShareKt(coroutineScope0, sharingConfig0.context, sharingConfig0.upstream, mutableStateFlow0, sharingStarted0, object0));
    }
}

