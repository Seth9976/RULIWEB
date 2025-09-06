package kotlinx.coroutines.flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CancellableContinuationKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.FusibleFlow;
import kotlinx.coroutines.internal.Symbol;

@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000F\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000F\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000B\n\u0002\b\u0012\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\u0012\u0004\u0012\u0002H\u00010\u00052\b\u0012\u0004\u0012\u0002H\u00010\u0006:\u0001hB\u001D\u0012\u0006\u0010\u0007\u001A\u00020\b\u0012\u0006\u0010\t\u001A\u00020\b\u0012\u0006\u0010\n\u001A\u00020\u000B\u00A2\u0006\u0002\u0010\fJ\u0019\u0010+\u001A\u00020,2\u0006\u0010-\u001A\u00020\u0003H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010.J\u0010\u0010/\u001A\u00020,2\u0006\u00100\u001A\u000201H\u0002J\b\u00102\u001A\u00020,H\u0002J\u001F\u00103\u001A\u0002042\f\u00105\u001A\b\u0012\u0004\u0012\u00028\u000006H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u00107J\u0010\u00108\u001A\u00020,2\u0006\u00109\u001A\u00020\u0012H\u0002J\b\u0010:\u001A\u00020\u0003H\u0014J\u001D\u0010;\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000E2\u0006\u0010<\u001A\u00020\bH\u0014\u00A2\u0006\u0002\u0010=J\b\u0010>\u001A\u00020,H\u0002J\u0019\u0010?\u001A\u00020,2\u0006\u0010@\u001A\u00028\u0000H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010AJ\u0019\u0010B\u001A\u00020,2\u0006\u0010@\u001A\u00028\u0000H\u0082@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010AJ\u0012\u0010C\u001A\u00020,2\b\u0010D\u001A\u0004\u0018\u00010\u000FH\u0002J1\u0010E\u001A\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020,\u0018\u00010F0\u000E2\u0014\u0010G\u001A\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020,\u0018\u00010F0\u000EH\u0002\u00A2\u0006\u0002\u0010HJ&\u0010I\u001A\b\u0012\u0004\u0012\u00028\u00000J2\u0006\u0010K\u001A\u00020L2\u0006\u0010M\u001A\u00020\b2\u0006\u0010\n\u001A\u00020\u000BH\u0016J\u0012\u0010N\u001A\u0004\u0018\u00010\u000F2\u0006\u0010O\u001A\u00020\u0012H\u0002J7\u0010P\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u000F0\u000E2\u0010\u0010Q\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u000F\u0018\u00010\u000E2\u0006\u0010R\u001A\u00020\b2\u0006\u0010S\u001A\u00020\bH\u0002\u00A2\u0006\u0002\u0010TJ\b\u0010U\u001A\u00020,H\u0016J\u0015\u0010V\u001A\u00020W2\u0006\u0010@\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010XJ\u0015\u0010Y\u001A\u00020W2\u0006\u0010@\u001A\u00028\u0000H\u0002\u00A2\u0006\u0002\u0010XJ\u0015\u0010Z\u001A\u00020W2\u0006\u0010@\u001A\u00028\u0000H\u0002\u00A2\u0006\u0002\u0010XJ\u0010\u0010[\u001A\u00020\u00122\u0006\u0010-\u001A\u00020\u0003H\u0002J\u0012\u0010\\\u001A\u0004\u0018\u00010\u000F2\u0006\u0010-\u001A\u00020\u0003H\u0002J(\u0010]\u001A\u00020,2\u0006\u0010^\u001A\u00020\u00122\u0006\u0010_\u001A\u00020\u00122\u0006\u0010`\u001A\u00020\u00122\u0006\u0010a\u001A\u00020\u0012H\u0002J%\u0010b\u001A\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020,\u0018\u00010F0\u000E2\u0006\u0010c\u001A\u00020\u0012H\u0000\u00A2\u0006\u0004\bd\u0010eJ\r\u0010f\u001A\u00020\u0012H\u0000\u00A2\u0006\u0002\bgR\u001A\u0010\r\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u000F\u0018\u00010\u000EX\u0082\u000E\u00A2\u0006\u0004\n\u0002\u0010\u0010R\u000E\u0010\t\u001A\u00020\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001A\u00020\u00128BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014R\u000E\u0010\u0015\u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001A\u00020\u00128BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u0017\u0010\u0014R\u001A\u0010\u0018\u001A\u00028\u00008DX\u0084\u0004\u00A2\u0006\f\u0012\u0004\b\u0019\u0010\u001A\u001A\u0004\b\u001B\u0010\u001CR\u000E\u0010\u001D\u001A\u00020\u0012X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u000BX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0014\u0010\u001E\u001A\u00020\u00128BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001F\u0010\u0014R\u000E\u0010 \u001A\u00020\bX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\bX\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010!\u001A\b\u0012\u0004\u0012\u00028\u00000\"8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b#\u0010$R\u000E\u0010%\u001A\u00020\u0012X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0014\u0010&\u001A\u00020\b8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b\'\u0010(R\u0014\u0010)\u001A\u00020\b8BX\u0082\u0004\u00A2\u0006\u0006\u001A\u0004\b*\u0010(\u0082\u0002\u0004\n\u0002\b\u0019\u00A8\u0006i"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl;", "T", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "Lkotlinx/coroutines/flow/SharedFlowSlot;", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlinx/coroutines/flow/CancellableFlow;", "Lkotlinx/coroutines/flow/internal/FusibleFlow;", "replay", "", "bufferCapacity", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "(IILkotlinx/coroutines/channels/BufferOverflow;)V", "buffer", "", "", "[Ljava/lang/Object;", "bufferEndIndex", "", "getBufferEndIndex", "()J", "bufferSize", "head", "getHead", "lastReplayedLocked", "getLastReplayedLocked$annotations", "()V", "getLastReplayedLocked", "()Ljava/lang/Object;", "minCollectorIndex", "queueEndIndex", "getQueueEndIndex", "queueSize", "replayCache", "", "getReplayCache", "()Ljava/util/List;", "replayIndex", "replaySize", "getReplaySize", "()I", "totalSize", "getTotalSize", "awaitValue", "", "slot", "(Lkotlinx/coroutines/flow/SharedFlowSlot;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "cancelEmitter", "emitter", "Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "cleanupTailLocked", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "correctCollectorIndexesOnDropOldest", "newHead", "createSlot", "createSlotArray", "size", "(I)[Lkotlinx/coroutines/flow/SharedFlowSlot;", "dropOldestLocked", "emit", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "emitSuspend", "enqueueLocked", "item", "findSlotsToResumeLocked", "Lkotlin/coroutines/Continuation;", "resumesIn", "([Lkotlin/coroutines/Continuation;)[Lkotlin/coroutines/Continuation;", "fuse", "Lkotlinx/coroutines/flow/Flow;", "context", "Lkotlin/coroutines/CoroutineContext;", "capacity", "getPeekedValueLockedAt", "index", "growBuffer", "curBuffer", "curSize", "newSize", "([Ljava/lang/Object;II)[Ljava/lang/Object;", "resetReplayCache", "tryEmit", "", "(Ljava/lang/Object;)Z", "tryEmitLocked", "tryEmitNoCollectorsLocked", "tryPeekLocked", "tryTakeValue", "updateBufferLocked", "newReplayIndex", "newMinCollectorIndex", "newBufferEndIndex", "newQueueEndIndex", "updateCollectorIndexLocked", "oldIndex", "updateCollectorIndexLocked$kotlinx_coroutines_core", "(J)[Lkotlin/coroutines/Continuation;", "updateNewCollectorIndexLocked", "updateNewCollectorIndexLocked$kotlinx_coroutines_core", "Emitter", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class SharedFlowImpl extends AbstractSharedFlow implements CancellableFlow, MutableSharedFlow, FusibleFlow {
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B1\u0012\n\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u0012\b\u0010\u0006\u001A\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000BJ\b\u0010\f\u001A\u00020\nH\u0016R\u0016\u0010\b\u001A\b\u0012\u0004\u0012\u00020\n0\t8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001A\u0006\u0012\u0002\b\u00030\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0004\u001A\u00020\u00058\u0006@\u0006X\u0087\u000E¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001A\u0004\u0018\u00010\u00078\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/coroutines/flow/SharedFlowImpl$Emitter;", "Lkotlinx/coroutines/DisposableHandle;", "flow", "Lkotlinx/coroutines/flow/SharedFlowImpl;", "index", "", "value", "", "cont", "Lkotlin/coroutines/Continuation;", "", "(Lkotlinx/coroutines/flow/SharedFlowImpl;JLjava/lang/Object;Lkotlin/coroutines/Continuation;)V", "dispose", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Emitter implements DisposableHandle {
        public final Continuation cont;
        public final SharedFlowImpl flow;
        public long index;
        public final Object value;

        public Emitter(SharedFlowImpl sharedFlowImpl0, long v, Object object0, Continuation continuation0) {
            this.flow = sharedFlowImpl0;
            this.index = v;
            this.value = object0;
            this.cont = continuation0;
        }

        @Override  // kotlinx.coroutines.DisposableHandle
        public void dispose() {
            this.flow.cancelEmitter(this);
        }
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[BufferOverflow.values().length];
            try {
                arr_v[BufferOverflow.SUSPEND.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[BufferOverflow.DROP_LATEST.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[BufferOverflow.DROP_OLDEST.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    private Object[] buffer;
    private final int bufferCapacity;
    private int bufferSize;
    private long minCollectorIndex;
    private final BufferOverflow onBufferOverflow;
    private int queueSize;
    private final int replay;
    private long replayIndex;

    public SharedFlowImpl(int v, int v1, BufferOverflow bufferOverflow0) {
        this.replay = v;
        this.bufferCapacity = v1;
        this.onBufferOverflow = bufferOverflow0;
    }

    public static final Object access$awaitValue(SharedFlowImpl sharedFlowImpl0, SharedFlowSlot sharedFlowSlot0, Continuation continuation0) {
        return sharedFlowImpl0.awaitValue(sharedFlowSlot0, continuation0);
    }

    public static final Object access$emitSuspend(SharedFlowImpl sharedFlowImpl0, Object object0, Continuation continuation0) {
        return sharedFlowImpl0.emitSuspend(object0, continuation0);
    }

    private final Object awaitValue(SharedFlowSlot sharedFlowSlot0, Continuation continuation0) {
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        synchronized(this) {
            if(this.tryPeekLocked(sharedFlowSlot0) < 0L) {
                sharedFlowSlot0.cont = cancellableContinuationImpl0;
            }
            else {
                cancellableContinuationImpl0.resumeWith(Unit.INSTANCE);
            }
        }
        Object object0 = cancellableContinuationImpl0.getResult();
        if(object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object0 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object0 : Unit.INSTANCE;
    }

    // 检测为 Lambda 实现
    private final void cancelEmitter(Emitter sharedFlowImpl$Emitter0) [...]

    private final void cleanupTailLocked() {
        if(this.bufferCapacity != 0 || this.queueSize > 1) {
            Object[] arr_object = this.buffer;
            Intrinsics.checkNotNull(arr_object);
            while(this.queueSize > 0 && SharedFlowKt.access$getBufferAt(arr_object, this.getHead() + ((long)this.getTotalSize()) - 1L) == SharedFlowKt.NO_VALUE) {
                --this.queueSize;
                SharedFlowKt.access$setBufferAt(arr_object, this.getHead() + ((long)this.getTotalSize()), null);
            }
        }
    }

    @Override  // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector flowCollector0, Continuation continuation0) {
        return SharedFlowImpl.collect$suspendImpl(this, flowCollector0, continuation0);
    }

    static Object collect$suspendImpl(SharedFlowImpl sharedFlowImpl0, FlowCollector flowCollector0, Continuation continuation0) {
        FlowCollector flowCollector3;
        Job job1;
        Job job0;
        Throwable throwable1;
        SharedFlowImpl sharedFlowImpl1;
        SharedFlowSlot sharedFlowSlot1;
        FlowCollector flowCollector1;
        kotlinx.coroutines.flow.SharedFlowImpl.collect.1 sharedFlowImpl$collect$10;
        if(continuation0 instanceof kotlinx.coroutines.flow.SharedFlowImpl.collect.1) {
            sharedFlowImpl$collect$10 = (kotlinx.coroutines.flow.SharedFlowImpl.collect.1)continuation0;
            if((sharedFlowImpl$collect$10.label & 0x80000000) == 0) {
                sharedFlowImpl$collect$10 = new ContinuationImpl(continuation0) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return SharedFlowImpl.collect$suspendImpl(sharedFlowImpl0, null, this);
                    }
                };
            }
            else {
                sharedFlowImpl$collect$10.label ^= 0x80000000;
            }
        }
        else {
            sharedFlowImpl$collect$10 = new ContinuationImpl(continuation0) {
                Object L$0;
                Object L$1;
                Object L$2;
                Object L$3;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return SharedFlowImpl.collect$suspendImpl(sharedFlowImpl0, null, this);
                }
            };
        }
        Object object0 = sharedFlowImpl$collect$10.result;
        Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(sharedFlowImpl$collect$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object0);
                SharedFlowSlot sharedFlowSlot0 = (SharedFlowSlot)sharedFlowImpl0.allocateSlot();
                try {
                    if(flowCollector0 instanceof SubscribedFlowCollector) {
                        sharedFlowImpl$collect$10.L$0 = sharedFlowImpl0;
                        sharedFlowImpl$collect$10.L$1 = flowCollector0;
                        sharedFlowImpl$collect$10.L$2 = sharedFlowSlot0;
                        sharedFlowImpl$collect$10.label = 1;
                        if(((SubscribedFlowCollector)flowCollector0).onSubscription(sharedFlowImpl$collect$10) != object1) {
                            flowCollector1 = flowCollector0;
                            sharedFlowSlot1 = sharedFlowSlot0;
                            job0 = (Job)sharedFlowImpl$collect$10.getContext().get(Job.Key);
                            goto label_60;
                        }
                        return object1;
                    }
                    else {
                        flowCollector1 = flowCollector0;
                        sharedFlowSlot1 = sharedFlowSlot0;
                    }
                    job0 = (Job)sharedFlowImpl$collect$10.getContext().get(Job.Key);
                    goto label_60;
                }
                catch(Throwable throwable0) {
                    sharedFlowImpl1 = sharedFlowImpl0;
                    throwable1 = throwable0;
                    sharedFlowSlot1 = sharedFlowSlot0;
                    sharedFlowImpl1.freeSlot(sharedFlowSlot1);
                    throw throwable1;
                }
            }
            case 1: {
                sharedFlowSlot1 = (SharedFlowSlot)sharedFlowImpl$collect$10.L$2;
                FlowCollector flowCollector2 = (FlowCollector)sharedFlowImpl$collect$10.L$1;
                SharedFlowImpl sharedFlowImpl2 = (SharedFlowImpl)sharedFlowImpl$collect$10.L$0;
                try {
                    ResultKt.throwOnFailure(object0);
                    flowCollector1 = flowCollector2;
                    sharedFlowImpl0 = sharedFlowImpl2;
                }
                catch(Throwable throwable1) {
                    sharedFlowImpl1 = sharedFlowImpl2;
                    sharedFlowImpl1.freeSlot(sharedFlowSlot1);
                    throw throwable1;
                }
                try {
                    job0 = (Job)sharedFlowImpl$collect$10.getContext().get(Job.Key);
                    goto label_60;
                }
                catch(Throwable throwable2) {
                    sharedFlowImpl1 = sharedFlowImpl0;
                    throwable1 = throwable2;
                    sharedFlowImpl1.freeSlot(sharedFlowSlot1);
                    throw throwable1;
                }
            }
            case 2: {
                job1 = (Job)sharedFlowImpl$collect$10.L$3;
                sharedFlowSlot1 = (SharedFlowSlot)sharedFlowImpl$collect$10.L$2;
                flowCollector3 = (FlowCollector)sharedFlowImpl$collect$10.L$1;
                sharedFlowImpl1 = (SharedFlowImpl)sharedFlowImpl$collect$10.L$0;
                break;
            }
            case 3: {
                job1 = (Job)sharedFlowImpl$collect$10.L$3;
                sharedFlowSlot1 = (SharedFlowSlot)sharedFlowImpl$collect$10.L$2;
                flowCollector3 = (FlowCollector)sharedFlowImpl$collect$10.L$1;
                sharedFlowImpl1 = (SharedFlowImpl)sharedFlowImpl$collect$10.L$0;
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        try {
            ResultKt.throwOnFailure(object0);
            flowCollector1 = flowCollector3;
            job0 = job1;
            sharedFlowImpl0 = sharedFlowImpl1;
        }
        catch(Throwable throwable1) {
            sharedFlowImpl1.freeSlot(sharedFlowSlot1);
            throw throwable1;
        }
        try {
            do {
                while(true) {
                label_60:
                    Object object2 = sharedFlowImpl0.tryTakeValue(sharedFlowSlot1);
                    if(object2 != SharedFlowKt.NO_VALUE) {
                        break;
                    }
                    sharedFlowImpl$collect$10.L$0 = sharedFlowImpl0;
                    sharedFlowImpl$collect$10.L$1 = flowCollector1;
                    sharedFlowImpl$collect$10.L$2 = sharedFlowSlot1;
                    sharedFlowImpl$collect$10.L$3 = job0;
                    sharedFlowImpl$collect$10.label = 2;
                    if(sharedFlowImpl0.awaitValue(sharedFlowSlot1, sharedFlowImpl$collect$10) == object1) {
                        return object1;
                    }
                }
                if(job0 != null) {
                    JobKt.ensureActive(job0);
                }
                sharedFlowImpl$collect$10.L$0 = sharedFlowImpl0;
                sharedFlowImpl$collect$10.L$1 = flowCollector1;
                sharedFlowImpl$collect$10.L$2 = sharedFlowSlot1;
                sharedFlowImpl$collect$10.L$3 = job0;
                sharedFlowImpl$collect$10.label = 3;
            }
            while(flowCollector1.emit(object2, sharedFlowImpl$collect$10) != object1);
            return object1;
        }
        catch(Throwable throwable2) {
            sharedFlowImpl1 = sharedFlowImpl0;
            throwable1 = throwable2;
        }
        sharedFlowImpl1.freeSlot(sharedFlowSlot1);
        throw throwable1;
    }

    private final void correctCollectorIndexesOnDropOldest(long v) {
        if(this.nCollectors != 0) {
            AbstractSharedFlowSlot[] arr_abstractSharedFlowSlot = this.slots;
            if(arr_abstractSharedFlowSlot != null) {
                for(int v1 = 0; v1 < arr_abstractSharedFlowSlot.length; ++v1) {
                    AbstractSharedFlowSlot abstractSharedFlowSlot0 = arr_abstractSharedFlowSlot[v1];
                    if(abstractSharedFlowSlot0 != null && ((SharedFlowSlot)abstractSharedFlowSlot0).index >= 0L && ((SharedFlowSlot)abstractSharedFlowSlot0).index < v) {
                        ((SharedFlowSlot)abstractSharedFlowSlot0).index = v;
                    }
                }
            }
        }
        this.minCollectorIndex = v;
    }

    protected SharedFlowSlot createSlot() {
        return new SharedFlowSlot();
    }

    @Override  // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public AbstractSharedFlowSlot createSlot() {
        return this.createSlot();
    }

    protected SharedFlowSlot[] createSlotArray(int v) {
        return new SharedFlowSlot[v];
    }

    @Override  // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public AbstractSharedFlowSlot[] createSlotArray(int v) {
        return this.createSlotArray(v);
    }

    private final void dropOldestLocked() {
        Object[] arr_object = this.buffer;
        Intrinsics.checkNotNull(arr_object);
        SharedFlowKt.setBufferAt(arr_object, this.getHead(), null);
        --this.bufferSize;
        long v = this.getHead();
        if(this.replayIndex < v + 1L) {
            this.replayIndex = v + 1L;
        }
        if(this.minCollectorIndex < v + 1L) {
            this.correctCollectorIndexesOnDropOldest(v + 1L);
        }
    }

    @Override  // kotlinx.coroutines.flow.MutableSharedFlow
    public Object emit(Object object0, Continuation continuation0) {
        return SharedFlowImpl.emit$suspendImpl(this, object0, continuation0);
    }

    static Object emit$suspendImpl(SharedFlowImpl sharedFlowImpl0, Object object0, Continuation continuation0) {
        if(sharedFlowImpl0.tryEmit(object0)) {
            return Unit.INSTANCE;
        }
        Object object1 = sharedFlowImpl0.emitSuspend(object0, continuation0);
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    private final Object emitSuspend(Object object0, Continuation continuation0) {
        Throwable throwable1;
        Emitter sharedFlowImpl$Emitter1;
        Continuation[] arr_continuation1;
        CancellableContinuationImpl cancellableContinuationImpl0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation0), 1);
        cancellableContinuationImpl0.initCancellability();
        Continuation[] arr_continuation = AbstractSharedFlowKt.EMPTY_RESUMES;
        __monitor_enter(this);
        try {
            if(this.tryEmitLocked(object0)) {
                goto label_16;
            }
            else {
                Emitter sharedFlowImpl$Emitter0 = () -> synchronized(this) {
                    if(this.index < this.getHead()) {
                        return;
                    }
                    Object[] arr_object = this.buffer;
                    Intrinsics.checkNotNull(arr_object);
                    if(SharedFlowKt.access$getBufferAt(arr_object, this.index) != this) {
                        return;
                    }
                    SharedFlowKt.access$setBufferAt(arr_object, this.index, SharedFlowKt.NO_VALUE);
                    this.cleanupTailLocked();
                };
                this.enqueueLocked(sharedFlowImpl$Emitter0);
                ++this.queueSize;
                if(this.bufferCapacity == 0) {
                    arr_continuation = this.findSlotsToResumeLocked(arr_continuation);
                }
                arr_continuation1 = arr_continuation;
                sharedFlowImpl$Emitter1 = sharedFlowImpl$Emitter0;
            }
            goto label_24;
        }
        catch(Throwable throwable0) {
            throwable1 = throwable0;
            __monitor_exit(this);
            throw throwable1;
        }
        try {
        label_16:
            cancellableContinuationImpl0.resumeWith(Unit.INSTANCE);
            arr_continuation1 = this.findSlotsToResumeLocked(arr_continuation);
            sharedFlowImpl$Emitter1 = null;
        }
        catch(Throwable throwable2) {
            throwable1 = throwable2;
            __monitor_exit(this);
            throw throwable1;
        }
    label_24:
        __monitor_exit(this);
        if(sharedFlowImpl$Emitter1 != null) {
            CancellableContinuationKt.disposeOnCancellation(cancellableContinuationImpl0, sharedFlowImpl$Emitter1);
        }
        for(int v = 0; v < arr_continuation1.length; ++v) {
            Continuation continuation1 = arr_continuation1[v];
            if(continuation1 != null) {
                continuation1.resumeWith(Unit.INSTANCE);
            }
        }
        Object object1 = cancellableContinuationImpl0.getResult();
        if(object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation0);
        }
        return object1 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? object1 : Unit.INSTANCE;
    }

    private final void enqueueLocked(Object object0) {
        int v = this.getTotalSize();
        Object[] arr_object = this.buffer;
        if(arr_object == null) {
            arr_object = this.growBuffer(null, 0, 2);
        }
        else if(v >= arr_object.length) {
            arr_object = this.growBuffer(arr_object, v, arr_object.length * 2);
        }
        SharedFlowKt.setBufferAt(arr_object, this.getHead() + ((long)v), object0);
    }

    private final Continuation[] findSlotsToResumeLocked(Continuation[] arr_continuation) {
        int v = arr_continuation.length;
        if(this.nCollectors != 0) {
            AbstractSharedFlowSlot[] arr_abstractSharedFlowSlot = this.slots;
            if(arr_abstractSharedFlowSlot != null) {
                for(int v1 = 0; v1 < arr_abstractSharedFlowSlot.length; ++v1) {
                    AbstractSharedFlowSlot abstractSharedFlowSlot0 = arr_abstractSharedFlowSlot[v1];
                    if(abstractSharedFlowSlot0 != null) {
                        Continuation continuation0 = ((SharedFlowSlot)abstractSharedFlowSlot0).cont;
                        if(continuation0 != null && this.tryPeekLocked(((SharedFlowSlot)abstractSharedFlowSlot0)) >= 0L) {
                            if(v >= arr_continuation.length) {
                                arr_continuation = Arrays.copyOf(arr_continuation, Math.max(2, arr_continuation.length * 2));
                                Intrinsics.checkNotNullExpressionValue(arr_continuation, "copyOf(this, newSize)");
                            }
                            arr_continuation[v] = continuation0;
                            ((SharedFlowSlot)abstractSharedFlowSlot0).cont = null;
                            ++v;
                        }
                    }
                }
            }
        }
        return arr_continuation;
    }

    @Override  // kotlinx.coroutines.flow.internal.FusibleFlow
    public Flow fuse(CoroutineContext coroutineContext0, int v, BufferOverflow bufferOverflow0) {
        return SharedFlowKt.fuseSharedFlow(this, coroutineContext0, v, bufferOverflow0);
    }

    private final long getBufferEndIndex() {
        return this.getHead() + ((long)this.bufferSize);
    }

    private final long getHead() {
        return Math.min(this.minCollectorIndex, this.replayIndex);
    }

    protected final Object getLastReplayedLocked() {
        Object[] arr_object = this.buffer;
        Intrinsics.checkNotNull(arr_object);
        return SharedFlowKt.getBufferAt(arr_object, this.replayIndex + ((long)this.getReplaySize()) - 1L);
    }

    protected static void getLastReplayedLocked$annotations() {
    }

    private final Object getPeekedValueLockedAt(long v) {
        Object[] arr_object = this.buffer;
        Intrinsics.checkNotNull(arr_object);
        Object object0 = SharedFlowKt.getBufferAt(arr_object, v);
        return object0 instanceof Emitter ? ((Emitter)object0).value : object0;
    }

    private final long getQueueEndIndex() {
        return this.getHead() + ((long)this.bufferSize) + ((long)this.queueSize);
    }

    @Override  // kotlinx.coroutines.flow.SharedFlow
    public List getReplayCache() {
        synchronized(this) {
            int v1 = this.getReplaySize();
            if(v1 == 0) {
                return CollectionsKt.emptyList();
            }
            ArrayList arrayList0 = new ArrayList(v1);
            Object[] arr_object = this.buffer;
            Intrinsics.checkNotNull(arr_object);
            for(int v2 = 0; v2 < v1; ++v2) {
                arrayList0.add(SharedFlowKt.getBufferAt(arr_object, this.replayIndex + ((long)v2)));
            }
            return arrayList0;
        }
    }

    private final int getReplaySize() {
        return (int)(this.getHead() + ((long)this.bufferSize) - this.replayIndex);
    }

    private final int getTotalSize() {
        return this.bufferSize + this.queueSize;
    }

    private final Object[] growBuffer(Object[] arr_object, int v, int v1) {
        if(v1 <= 0) {
            throw new IllegalStateException("Buffer size overflow");
        }
        Object[] arr_object1 = new Object[v1];
        this.buffer = arr_object1;
        if(arr_object != null) {
            long v2 = this.getHead();
            for(int v3 = 0; v3 < v; ++v3) {
                long v4 = ((long)v3) + v2;
                SharedFlowKt.setBufferAt(arr_object1, v4, SharedFlowKt.getBufferAt(arr_object, v4));
            }
        }
        return arr_object1;
    }

    @Override  // kotlinx.coroutines.flow.MutableSharedFlow
    public void resetReplayCache() {
        synchronized(this) {
            this.updateBufferLocked(this.getBufferEndIndex(), this.minCollectorIndex, this.getBufferEndIndex(), this.getQueueEndIndex());
        }
    }

    @Override  // kotlinx.coroutines.flow.MutableSharedFlow
    public boolean tryEmit(Object object0) {
        boolean z;
        Continuation[] arr_continuation = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized(this) {
            if(this.tryEmitLocked(object0)) {
                arr_continuation = this.findSlotsToResumeLocked(arr_continuation);
                z = true;
            }
            else {
                z = false;
            }
        }
        for(int v = 0; v < arr_continuation.length; ++v) {
            Continuation continuation0 = arr_continuation[v];
            if(continuation0 != null) {
                continuation0.resumeWith(Unit.INSTANCE);
            }
        }
        return z;
    }

    private final boolean tryEmitLocked(Object object0) {
        if(this.getNCollectors() == 0) {
            return this.tryEmitNoCollectorsLocked(object0);
        }
        if(this.bufferSize >= this.bufferCapacity && this.minCollectorIndex <= this.replayIndex) {
            switch(WhenMappings.$EnumSwitchMapping$0[this.onBufferOverflow.ordinal()]) {
                case 1: {
                    return false;
                }
                case 2: {
                    return true;
                }
            }
        }
        this.enqueueLocked(object0);
        int v = this.bufferSize + 1;
        this.bufferSize = v;
        if(v > this.bufferCapacity) {
            this.dropOldestLocked();
        }
        if(this.getReplaySize() > this.replay) {
            this.updateBufferLocked(this.replayIndex + 1L, this.minCollectorIndex, this.getBufferEndIndex(), this.getQueueEndIndex());
        }
        return true;
    }

    private final boolean tryEmitNoCollectorsLocked(Object object0) {
        if(this.replay == 0) {
            return true;
        }
        this.enqueueLocked(object0);
        int v = this.bufferSize + 1;
        this.bufferSize = v;
        if(v > this.replay) {
            this.dropOldestLocked();
        }
        this.minCollectorIndex = this.getHead() + ((long)this.bufferSize);
        return true;
    }

    private final long tryPeekLocked(SharedFlowSlot sharedFlowSlot0) {
        long v = sharedFlowSlot0.index;
        if(v >= this.getBufferEndIndex()) {
            if(this.bufferCapacity > 0) {
                return -1L;
            }
            if(v > this.getHead()) {
                return -1L;
            }
            return this.queueSize == 0 ? -1L : v;
        }
        return v;
    }

    private final Object tryTakeValue(SharedFlowSlot sharedFlowSlot0) {
        Symbol symbol0;
        Continuation[] arr_continuation = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized(this) {
            long v1 = this.tryPeekLocked(sharedFlowSlot0);
            if(v1 < 0L) {
                symbol0 = SharedFlowKt.NO_VALUE;
            }
            else {
                long v2 = sharedFlowSlot0.index;
                Object object0 = this.getPeekedValueLockedAt(v1);
                sharedFlowSlot0.index = v1 + 1L;
                arr_continuation = this.updateCollectorIndexLocked$kotlinx_coroutines_core(v2);
                symbol0 = object0;
            }
        }
        for(int v3 = 0; v3 < arr_continuation.length; ++v3) {
            Continuation continuation0 = arr_continuation[v3];
            if(continuation0 != null) {
                continuation0.resumeWith(Unit.INSTANCE);
            }
        }
        return symbol0;
    }

    private final void updateBufferLocked(long v, long v1, long v2, long v3) {
        long v4 = Math.min(v1, v);
        for(long v5 = this.getHead(); v5 < v4; ++v5) {
            Object[] arr_object = this.buffer;
            Intrinsics.checkNotNull(arr_object);
            SharedFlowKt.setBufferAt(arr_object, v5, null);
        }
        this.replayIndex = v;
        this.minCollectorIndex = v1;
        this.bufferSize = (int)(v2 - v4);
        this.queueSize = (int)(v3 - v2);
    }

    public final Continuation[] updateCollectorIndexLocked$kotlinx_coroutines_core(long v) {
        long v9;
        if(v > this.minCollectorIndex) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
        }
        long v1 = this.getHead();
        long v2 = this.bufferCapacity != 0 || this.queueSize <= 0 ? ((long)this.bufferSize) + v1 : ((long)this.bufferSize) + v1 + 1L;
        if(this.nCollectors != 0) {
            AbstractSharedFlowSlot[] arr_abstractSharedFlowSlot = this.slots;
            if(arr_abstractSharedFlowSlot != null) {
                for(int v3 = 0; v3 < arr_abstractSharedFlowSlot.length; ++v3) {
                    AbstractSharedFlowSlot abstractSharedFlowSlot0 = arr_abstractSharedFlowSlot[v3];
                    if(abstractSharedFlowSlot0 != null && ((SharedFlowSlot)abstractSharedFlowSlot0).index >= 0L && ((SharedFlowSlot)abstractSharedFlowSlot0).index < v2) {
                        v2 = ((SharedFlowSlot)abstractSharedFlowSlot0).index;
                    }
                }
            }
        }
        if(v2 <= this.minCollectorIndex) {
            return AbstractSharedFlowKt.EMPTY_RESUMES;
        }
        long v4 = this.getBufferEndIndex();
        int v5 = this.getNCollectors() <= 0 ? this.queueSize : Math.min(this.queueSize, this.bufferCapacity - ((int)(v4 - v2)));
        Continuation[] arr_continuation = AbstractSharedFlowKt.EMPTY_RESUMES;
        long v6 = ((long)this.queueSize) + v4;
        if(v5 > 0) {
            arr_continuation = new Continuation[v5];
            Object[] arr_object = this.buffer;
            Intrinsics.checkNotNull(arr_object);
            long v7 = v4;
            int v8 = 0;
            while(true) {
                if(v4 >= v6) {
                    v9 = v1;
                    break;
                }
                Object object0 = SharedFlowKt.getBufferAt(arr_object, v4);
                v9 = v1;
                if(object0 != SharedFlowKt.NO_VALUE) {
                    Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlinx.coroutines.flow.SharedFlowImpl.Emitter");
                    arr_continuation[v8] = ((Emitter)object0).cont;
                    SharedFlowKt.setBufferAt(arr_object, v4, SharedFlowKt.NO_VALUE);
                    SharedFlowKt.setBufferAt(arr_object, v7, ((Emitter)object0).value);
                    ++v7;
                    if(v8 + 1 >= v5) {
                        break;
                    }
                    ++v8;
                }
                ++v4;
                v1 = v9;
            }
            v4 = v7;
        }
        else {
            v9 = v1;
        }
        if(this.getNCollectors() == 0) {
            v2 = v4;
        }
        long v10 = Math.max(this.replayIndex, v4 - ((long)Math.min(this.replay, ((int)(v4 - v9)))));
        if(this.bufferCapacity == 0 && v10 < v6) {
            Object[] arr_object1 = this.buffer;
            Intrinsics.checkNotNull(arr_object1);
            if(Intrinsics.areEqual(SharedFlowKt.getBufferAt(arr_object1, v10), SharedFlowKt.NO_VALUE)) {
                ++v4;
                ++v10;
            }
        }
        this.updateBufferLocked(v10, v2, v4, v6);
        this.cleanupTailLocked();
        return arr_continuation.length == 0 ? arr_continuation : this.findSlotsToResumeLocked(arr_continuation);
    }

    public final long updateNewCollectorIndexLocked$kotlinx_coroutines_core() {
        long v = this.replayIndex;
        if(v < this.minCollectorIndex) {
            this.minCollectorIndex = v;
        }
        return v;
    }
}

