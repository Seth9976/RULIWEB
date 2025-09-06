package kotlinx.coroutines.flow;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.DelayKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001A\u00020\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0003¢\u0006\u0002\u0010\u0005J\u001C\u0010\u0006\u001A\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001A\b\u0012\u0004\u0012\u00020\u000B0\nH\u0016J\u0013\u0010\f\u001A\u00020\r2\b\u0010\u000E\u001A\u0004\u0018\u00010\u000FH\u0096\u0002J\b\u0010\u0010\u001A\u00020\u000BH\u0017J\b\u0010\u0011\u001A\u00020\u0012H\u0016R\u000E\u0010\u0004\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/flow/StartedWhileSubscribed;", "Lkotlinx/coroutines/flow/SharingStarted;", "stopTimeout", "", "replayExpiration", "(JJ)V", "command", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/SharingCommand;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "", "equals", "", "other", "", "hashCode", "toString", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class StartedWhileSubscribed implements SharingStarted {
    private final long replayExpiration;
    private final long stopTimeout;

    public StartedWhileSubscribed(long v, long v1) {
        this.stopTimeout = v;
        this.replayExpiration = v1;
        if(v < 0L) {
            throw new IllegalArgumentException(("stopTimeout(" + v + " ms) cannot be negative").toString());
        }
        if(v1 < 0L) {
            throw new IllegalArgumentException(("replayExpiration(" + v1 + " ms) cannot be negative").toString());
        }
    }

    @Override  // kotlinx.coroutines.flow.SharingStarted
    public Flow command(StateFlow stateFlow0) {
        return FlowKt.distinctUntilChanged(FlowKt.dropWhile(FlowKt.transformLatest(stateFlow0, new Function3(null) {
            int I$0;
            private Object L$0;
            int label;

            {
                StartedWhileSubscribed.this = startedWhileSubscribed0;
                super(3, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function3
            public Object invoke(Object object0, Object object1, Object object2) {
                return this.invoke(((FlowCollector)object0), ((Number)object1).intValue(), ((Continuation)object2));
            }

            public final Object invoke(FlowCollector flowCollector0, int v, Continuation continuation0) {
                kotlinx.coroutines.flow.StartedWhileSubscribed.command.1 startedWhileSubscribed$command$10 = new kotlinx.coroutines.flow.StartedWhileSubscribed.command.1(StartedWhileSubscribed.this, continuation0);
                startedWhileSubscribed$command$10.L$0 = flowCollector0;
                startedWhileSubscribed$command$10.I$0 = v;
                return startedWhileSubscribed$command$10.invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                FlowCollector flowCollector0;
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        flowCollector0 = (FlowCollector)this.L$0;
                        if(this.I$0 > 0) {
                            this.label = 1;
                            return flowCollector0.emit(SharingCommand.START, this) != object1 ? Unit.INSTANCE : object1;
                        }
                        this.L$0 = flowCollector0;
                        this.label = 2;
                        if(DelayKt.delay(StartedWhileSubscribed.this.stopTimeout, this) != object1) {
                        label_23:
                            if(StartedWhileSubscribed.this.replayExpiration > 0L) {
                                this.L$0 = flowCollector0;
                                this.label = 3;
                                if(flowCollector0.emit(SharingCommand.STOP, this) != object1) {
                                label_27:
                                    this.L$0 = flowCollector0;
                                    this.label = 4;
                                    if(DelayKt.delay(StartedWhileSubscribed.this.replayExpiration, this) != object1) {
                                        goto label_30;
                                    }
                                }
                            }
                            else {
                            label_30:
                                this.L$0 = null;
                                this.label = 5;
                                if(flowCollector0.emit(SharingCommand.STOP_AND_RESET_REPLAY_CACHE, this) != object1) {
                                    return Unit.INSTANCE;
                                }
                            }
                        }
                        break;
                    }
                    case 2: {
                        flowCollector0 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_23;
                    }
                    case 3: {
                        flowCollector0 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_27;
                    }
                    case 4: {
                        flowCollector0 = (FlowCollector)this.L$0;
                        ResultKt.throwOnFailure(object0);
                        goto label_30;
                    }
                    case 1: 
                    case 5: {
                        ResultKt.throwOnFailure(object0);
                        return Unit.INSTANCE;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                return object1;
            }
        }), new Function2() {
            Object L$0;
            int label;

            {
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                kotlinx.coroutines.flow.StartedWhileSubscribed.command.2 startedWhileSubscribed$command$20 = new kotlinx.coroutines.flow.StartedWhileSubscribed.command.2(continuation0);
                startedWhileSubscribed$command$20.L$0 = object0;
                return startedWhileSubscribed$command$20;
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((SharingCommand)object0), ((Continuation)object1));
            }

            public final Object invoke(SharingCommand sharingCommand0, Continuation continuation0) {
                return ((kotlinx.coroutines.flow.StartedWhileSubscribed.command.2)this.create(sharingCommand0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                if(this.label != 0) {
                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                }
                ResultKt.throwOnFailure(object0);
                return ((SharingCommand)this.L$0) == SharingCommand.START ? Boxing.boxBoolean(false) : Boxing.boxBoolean(true);
            }
        }));
    }

    @Override
    public boolean equals(Object object0) {
        return object0 instanceof StartedWhileSubscribed && this.stopTimeout == ((StartedWhileSubscribed)object0).stopTimeout && this.replayExpiration == ((StartedWhileSubscribed)object0).replayExpiration;
    }

    @Override
    public int hashCode() {
        return ((int)(this.stopTimeout ^ this.stopTimeout >>> 0x20)) * 0x1F + ((int)(this.replayExpiration ^ this.replayExpiration >>> 0x20));
    }

    @Override
    public String toString() {
        List list0 = CollectionsKt.createListBuilder(2);
        if(this.stopTimeout > 0L) {
            list0.add("stopTimeout=" + this.stopTimeout + "ms");
        }
        if(this.replayExpiration < 0x7FFFFFFFFFFFFFFFL) {
            list0.add("replayExpiration=" + this.replayExpiration + "ms");
        }
        return "SharingStarted.WhileSubscribed(" + CollectionsKt.joinToString$default(CollectionsKt.build(list0), null, null, null, 0, null, null, 0x3F, null) + ')';
    }
}

