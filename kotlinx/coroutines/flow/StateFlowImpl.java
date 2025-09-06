package kotlinx.coroutines.flow;

import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.Volatile;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\b\u0012\u0004\u0012\u0002H\u00010\u0006B\r\u0012\u0006\u0010\u0007\u001A\u00020\b\u00A2\u0006\u0002\u0010\tJ\u001F\u0010\u0018\u001A\u00020\u00192\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u00028\u00000\u001BH\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001CJ\u001D\u0010\u001D\u001A\u00020\u001E2\u0006\u0010\u001F\u001A\u00028\u00002\u0006\u0010 \u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010!J\b\u0010\"\u001A\u00020\u0003H\u0014J\u001D\u0010#\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030$2\u0006\u0010%\u001A\u00020\u0011H\u0014\u00A2\u0006\u0002\u0010&J\u0019\u0010\'\u001A\u00020(2\u0006\u0010\u0012\u001A\u00028\u0000H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010)J&\u0010*\u001A\b\u0012\u0004\u0012\u00028\u00000+2\u0006\u0010,\u001A\u00020-2\u0006\u0010.\u001A\u00020\u00112\u0006\u0010/\u001A\u000200H\u0016J\b\u00101\u001A\u00020(H\u0016J\u0015\u00102\u001A\u00020\u001E2\u0006\u0010\u0012\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u00103J\u001A\u00104\u001A\u00020\u001E2\b\u00105\u001A\u0004\u0018\u00010\b2\u0006\u00106\u001A\u00020\bH\u0002R\u000F\u0010\n\u001A\b\u0012\u0004\u0012\u00020\b0\u000BX\u0082\u0004R\u001A\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\r8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\u000E\u0010\u0010\u001A\u00020\u0011X\u0082\u000E\u00A2\u0006\u0002\n\u0000R*\u0010\u0012\u001A\u00028\u00002\u0006\u0010\u0012\u001A\u00028\u00008V@VX\u0096\u000E\u00A2\u0006\u0012\u0012\u0004\b\u0013\u0010\u0014\u001A\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u00067"}, d2 = {"Lkotlinx/coroutines/flow/StateFlowImpl;", "T", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/StateFlowSlot;", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "initialState", "", "(Ljava/lang/Object;)V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "sequence", "", "value", "getValue$annotations", "()V", "getValue", "()Ljava/lang/Object;", "setValue", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "compareAndSet", "", "expect", "update", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "createSlot", "createSlotArray", "", "size", "(I)[Lkotlinx/coroutines/flow/StateFlowSlot;", "emit", "", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fuse", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "resetReplayCache", "tryEmit", "(Ljava/lang/Object;)Z", "updateState", "expectedState", "newState", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
final class StateFlowImpl extends AbstractSharedFlow implements CancellableFlow, MutableStateFlow, FusibleFlow {
    @Volatile
    private volatile Object _state;
    private static final AtomicReferenceFieldUpdater _state$FU;
    private int sequence;

    static {
        StateFlowImpl._state$FU = AtomicReferenceFieldUpdater.newUpdater(StateFlowImpl.class, Object.class, "_state");
    }

    public StateFlowImpl(Object object0) {
        this._state = object0;
    }

    @Override  // kotlinx.coroutines.flow.Flow, kotlinx.coroutines.flow.SharedFlow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        Object object2;
        Job job0;
        FlowCollector flowCollector1;
        StateFlowSlot stateFlowSlot1;
        StateFlowImpl stateFlowImpl0;
        kotlinx.coroutines.flow.StateFlowImpl.collect.1 stateFlowImpl$collect$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.StateFlowImpl.collect.1) {
            stateFlowImpl$collect$10 = (kotlinx.coroutines.flow.StateFlowImpl.collect.1)continuation0;
            if((stateFlowImpl$collect$10.label & 0x80000000) == 0) {
                stateFlowImpl$collect$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    Object L$4;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.collect(null, this);
                    }
                };
            }
            else {
                stateFlowImpl$collect$10.label ^= 0x80000000;
            }
        }
        else {
            stateFlowImpl$collect$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                Object L$4;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.collect(null, this);
                }
            };
        }
        Object object0 = stateFlowImpl$collect$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(stateFlowImpl$collect$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                StateFlowSlot stateFlowSlot0 = (StateFlowSlot)this.allocateSlot();
                try {
                    if(flowCollector0 instanceof SubscribedFlowCollector) {
                        stateFlowImpl$collect$10.L$0 = this;
                        stateFlowImpl$collect$10.L$1 = flowCollector0;
                        stateFlowImpl$collect$10.L$2 = stateFlowSlot0;
                        stateFlowImpl$collect$10.label = 1;
                        if(((SubscribedFlowCollector)flowCollector0).onSubscription(stateFlowImpl$collect$10) != object1) {
                            stateFlowImpl0 = this;
                            stateFlowSlot1 = stateFlowSlot0;
                            flowCollector1 = flowCollector0;
                            job0 = (Job)stateFlowImpl$collect$10.getContext().get(Job.Key);
                            object2 = null;
                            goto label_51;
                        }
                    }
                    else {
                        stateFlowImpl0 = this;
                        stateFlowSlot1 = stateFlowSlot0;
                        flowCollector1 = flowCollector0;
                        job0 = (Job)stateFlowImpl$collect$10.getContext().get(Job.Key);
                        object2 = null;
                        goto label_51;
                    }
                    return object1;
                }
                catch(Throwable throwable0) {
                    stateFlowImpl0 = this;
                    stateFlowSlot1 = stateFlowSlot0;
                    break;
                }
            }
            case 1: {
                stateFlowSlot1 = (StateFlowSlot)stateFlowImpl$collect$10.L$2;
                flowCollector0 = (FlowCollector)stateFlowImpl$collect$10.L$1;
                stateFlowImpl0 = (StateFlowImpl)stateFlowImpl$collect$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    flowCollector1 = flowCollector0;
                    job0 = (Job)stateFlowImpl$collect$10.getContext().get(Job.Key);
                    object2 = null;
                    goto label_51;
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            case 2: {
                object2 = stateFlowImpl$collect$10.L$4;
                job0 = (Job)stateFlowImpl$collect$10.L$3;
                stateFlowSlot1 = (StateFlowSlot)stateFlowImpl$collect$10.L$2;
                flowCollector1 = (FlowCollector)stateFlowImpl$collect$10.L$1;
                stateFlowImpl0 = (StateFlowImpl)stateFlowImpl$collect$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    goto label_63;
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            case 3: {
                object2 = stateFlowImpl$collect$10.L$4;
                job0 = (Job)stateFlowImpl$collect$10.L$3;
                stateFlowSlot1 = (StateFlowSlot)stateFlowImpl$collect$10.L$2;
                flowCollector1 = (FlowCollector)stateFlowImpl$collect$10.L$1;
                stateFlowImpl0 = (StateFlowImpl)stateFlowImpl$collect$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                alab1:
                    while(true) {
                        while(true) {
                        label_51:
                            Object object3 = StateFlowImpl._state$FU.get(stateFlowImpl0);
                            if(job0 != null) {
                                JobKt.ensureActive(job0);
                            }
                            if(object2 == null || !Intrinsics.areEqual(object2, object3)) {
                                stateFlowImpl$collect$10.L$0 = stateFlowImpl0;
                                stateFlowImpl$collect$10.L$1 = flowCollector1;
                                stateFlowImpl$collect$10.L$2 = stateFlowSlot1;
                                stateFlowImpl$collect$10.L$3 = job0;
                                stateFlowImpl$collect$10.L$4 = object3;
                                stateFlowImpl$collect$10.label = 2;
                                if(flowCollector1.emit((object3 == NullSurrogateKt.NULL ? null : object3), stateFlowImpl$collect$10) == object1) {
                                    break alab1;
                                }
                                object2 = object3;
                            }
                        label_63:
                            if(stateFlowSlot1.takePending()) {
                                break;
                            }
                            stateFlowImpl$collect$10.L$0 = stateFlowImpl0;
                            stateFlowImpl$collect$10.L$1 = flowCollector1;
                            stateFlowImpl$collect$10.L$2 = stateFlowSlot1;
                            stateFlowImpl$collect$10.L$3 = job0;
                            stateFlowImpl$collect$10.L$4 = object2;
                            stateFlowImpl$collect$10.label = 3;
                            if(stateFlowSlot1.awaitPending(stateFlowImpl$collect$10) != object1) {
                                break;
                            }
                            break alab1;
                        }
                    }
                    return object1;
                }
                catch(Throwable throwable0) {
                    break;
                }
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        stateFlowImpl0.freeSlot(stateFlowSlot1);
        throw throwable0;
    }

    @Override  // kotlinx.coroutines.flow.MutableStateFlow
    public boolean compareAndSet(Object object0, Object object1) {
        if(object0 == null) {
            object0 = NullSurrogateKt.NULL;
        }
        if(object1 == null) {
            object1 = NullSurrogateKt.NULL;
        }
        return this.updateState(object0, object1);
    }

    protected StateFlowSlot createSlot() {
        return new StateFlowSlot();
    }

    @Override  // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public AbstractSharedFlowSlot createSlot() {
        return this.createSlot();
    }

    protected StateFlowSlot[] createSlotArray(int v) {
        return new StateFlowSlot[v];
    }

    @Override  // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public AbstractSharedFlowSlot[] createSlotArray(int v) {
        return this.createSlotArray(v);
    }

    @Override  // kotlinx.coroutines.flow.MutableSharedFlow
    public Object emit(Object object0, Continuation continuation0) {
        this.setValue(object0);
        return Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow fuse(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return StateFlowKt.fuseStateFlow(this, coroutineContext0, v, bufferOverflow0);
    }

    @Override  // kotlinx.coroutines.flow.SharedFlow
    public List getReplayCache() {
        return CollectionsKt.listOf(this.getValue());
    }

    @Override  // kotlinx.coroutines.flow.MutableStateFlow
    public Object getValue() {
        Object object0 = StateFlowImpl._state$FU.get(this);
        return object0 == NullSurrogateKt.NULL ? null : object0;
    }

    public static void getValue$annotations() {
    }

    @Override  // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        throw new UnsupportedOperationException("MutableStateFlow.resetReplayCache is not supported");
    }

    @Override  // kotlinx.coroutines.flow.MutableStateFlow
    public void setValue(Object object0) {
        if(object0 == null) {
            object0 = NullSurrogateKt.NULL;
        }
        this.updateState(null, object0);
    }

    @Override  // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(Object object0) {
        this.setValue(object0);
        return true;
    }

    private final boolean updateState(Object object0, Object object1) {
        AbstractSharedFlowSlot[] arr_abstractSharedFlowSlot1;
        int v4;
        AbstractSharedFlowSlot[] arr_abstractSharedFlowSlot;
        int v1;
        int v;
        synchronized(this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = StateFlowImpl._state$FU;
            Object object2 = atomicReferenceFieldUpdater0.get(this);
            if(object0 == null || Intrinsics.areEqual(object2, object0)) {
                if(!Intrinsics.areEqual(object2, object1)) {
                    atomicReferenceFieldUpdater0.set(this, object1);
                    v = this.sequence;
                    if((v & 1) == 0) {
                        v1 = v + 1;
                        this.sequence = v1;
                        arr_abstractSharedFlowSlot = this.getSlots();
                        goto label_11;
                    }
                    this.sequence = v + 2;
                    return true;
                }
                return true;
            }
            return false;
        }
    label_11:
        while(true) {
            StateFlowSlot[] arr_stateFlowSlot = (StateFlowSlot[])arr_abstractSharedFlowSlot;
            if(arr_stateFlowSlot != null) {
                int v2 = arr_stateFlowSlot.length;
                for(int v3 = 0; v3 < v2; ++v3) {
                    StateFlowSlot stateFlowSlot0 = arr_stateFlowSlot[v3];
                    if(stateFlowSlot0 != null) {
                        stateFlowSlot0.makePending();
                    }
                }
            }
            synchronized(this) {
                v4 = this.sequence;
                if(v4 == v1) {
                    this.sequence = v1 + 1;
                    return true;
                }
                goto label_28;
            }
            return true;
        label_28:
            arr_abstractSharedFlowSlot1 = this.getSlots();
            arr_abstractSharedFlowSlot = arr_abstractSharedFlowSlot1;
            v1 = v4;
        }
        this.sequence = v + 2;
        return true;
    }
}

