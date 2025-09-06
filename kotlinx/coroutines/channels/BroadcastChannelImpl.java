package kotlinx.coroutines.channels;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.selects.SelectImplementation;
import kotlinx.coroutines.selects.SelectInstance;
import kotlinx.coroutines.selects.TrySelectDetailedResult;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u000245B\r\u0012\u0006\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\u0017\u0010\u001F\u001A\u00020\n2\b\u0010 \u001A\u0004\u0018\u00010!H\u0010\u00A2\u0006\u0002\b\"J\u0012\u0010#\u001A\u00020\n2\b\u0010 \u001A\u0004\u0018\u00010!H\u0016J\u000E\u0010$\u001A\b\u0012\u0004\u0012\u00028\u00000%H\u0016J\u001E\u0010&\u001A\u00020\'2\n\u0010(\u001A\u0006\u0012\u0002\b\u00030\u00132\b\u0010)\u001A\u0004\u0018\u00010\rH\u0014J\u0016\u0010*\u001A\u00020\'2\f\u0010+\u001A\b\u0012\u0004\u0012\u00028\u00000%H\u0002J\u0019\u0010,\u001A\u00020\'2\u0006\u0010)\u001A\u00028\u0000H\u0096@\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010-J\b\u0010.\u001A\u00020/H\u0016J&\u00100\u001A\b\u0012\u0004\u0012\u00020\'012\u0006\u0010)\u001A\u00028\u0000H\u0016\u00F8\u0001\u0001\u00F8\u0001\u0002\u00F8\u0001\u0000\u00A2\u0006\u0004\b2\u00103R\u0011\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\b\n\u0000\u001A\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001A\u00020\n8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\u000BR\u0010\u0010\f\u001A\u0004\u0018\u00010\rX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u000E\u001A\u00060\u000Fj\u0002`\u0010X\u0082\u0004\u00A2\u0006\u0002\n\u0000R6\u0010\u0011\u001A*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0012j\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0013\u0012\u0006\u0012\u0004\u0018\u00010\r`\u0014X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u001A\u0010\u0015\u001A\u000E\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0016X\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001A\u00028\u00008F\u00A2\u0006\f\u0012\u0004\b\u0018\u0010\u0019\u001A\u0004\b\u001A\u0010\u001BR\u0019\u0010\u001C\u001A\u0004\u0018\u00018\u00008F\u00A2\u0006\f\u0012\u0004\b\u001D\u0010\u0019\u001A\u0004\b\u001E\u0010\u001B\u0082\u0002\u000F\n\u0002\b\u0019\n\u0002\b!\n\u0005\b\u00A1\u001E0\u0001\u00A8\u00066"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl;", "E", "Lkotlinx/coroutines/channels/BufferedChannel;", "Lkotlinx/coroutines/channels/BroadcastChannel;", "capacity", "", "(I)V", "getCapacity", "()I", "isClosedForSend", "", "()Z", "lastConflatedElement", "", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "Lkotlinx/coroutines/internal/ReentrantLock;", "onSendInternalResult", "Ljava/util/HashMap;", "Lkotlinx/coroutines/selects/SelectInstance;", "Lkotlin/collections/HashMap;", "subscribers", "", "value", "getValue$annotations", "()V", "getValue", "()Ljava/lang/Object;", "valueOrNull", "getValueOrNull$annotations", "getValueOrNull", "cancelImpl", "cause", "", "cancelImpl$kotlinx_coroutines_core", "close", "openSubscription", "Lkotlinx/coroutines/channels/ReceiveChannel;", "registerSelectForSend", "", "select", "element", "removeSubscriber", "s", "send", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toString", "", "trySend", "Lkotlinx/coroutines/channels/ChannelResult;", "trySend-JP2dKIU", "(Ljava/lang/Object;)Ljava/lang/Object;", "SubscriberBuffered", "SubscriberConflated", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class BroadcastChannelImpl extends BufferedChannel implements BroadcastChannel {
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl$SubscriberBuffered;", "Lkotlinx/coroutines/channels/BufferedChannel;", "(Lkotlinx/coroutines/channels/BroadcastChannelImpl;)V", "cancelImpl", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class SubscriberBuffered extends BufferedChannel {
        public SubscriberBuffered() {
            super(broadcastChannelImpl0.getCapacity(), null, 2, null);
        }

        public boolean cancelImpl(Throwable throwable0) {
            Lock lock0 = BroadcastChannelImpl.this.lock;
            lock0.lock();
            try {
                BroadcastChannelImpl.this.removeSubscriber(this);
                return super.cancelImpl$kotlinx_coroutines_core(throwable0);
            }
            finally {
                lock0.unlock();
            }
        }

        @Override  // kotlinx.coroutines.channels.BufferedChannel
        public boolean cancelImpl$kotlinx_coroutines_core(Throwable throwable0) {
            return this.cancelImpl(throwable0);
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001A\u00020\u00042\b\u0010\u0005\u001A\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lkotlinx/coroutines/channels/BroadcastChannelImpl$SubscriberConflated;", "Lkotlinx/coroutines/channels/ConflatedBufferedChannel;", "(Lkotlinx/coroutines/channels/BroadcastChannelImpl;)V", "cancelImpl", "", "cause", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class SubscriberConflated extends ConflatedBufferedChannel {
        public SubscriberConflated() {
            super(1, BufferOverflow.DROP_OLDEST, null, 4, null);
        }

        public boolean cancelImpl(Throwable throwable0) {
            BroadcastChannelImpl.this.removeSubscriber(this);
            return super.cancelImpl$kotlinx_coroutines_core(throwable0);
        }

        @Override  // kotlinx.coroutines.channels.BufferedChannel
        public boolean cancelImpl$kotlinx_coroutines_core(Throwable throwable0) {
            return this.cancelImpl(throwable0);
        }
    }

    private final int capacity;
    private Object lastConflatedElement;
    private final ReentrantLock lock;
    private final HashMap onSendInternalResult;
    private List subscribers;

    public BroadcastChannelImpl(int v) {
        super(0, null);
        this.capacity = v;
        if(v < 1 && v != -1) {
            throw new IllegalArgumentException(("BroadcastChannel capacity must be positive or Channel.CONFLATED, but " + v + " was specified").toString());
        }
        this.lock = new ReentrantLock();
        this.subscribers = CollectionsKt.emptyList();
        this.lastConflatedElement = BroadcastChannelKt.access$getNO_ELEMENT$p();
        this.onSendInternalResult = new HashMap();
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    public boolean cancelImpl$kotlinx_coroutines_core(Throwable throwable0) {
        Lock lock0 = this.lock;
        lock0.lock();
        try {
            for(Object object0: this.subscribers) {
                ((BufferedChannel)object0).cancelImpl$kotlinx_coroutines_core(throwable0);
            }
            this.lastConflatedElement = BroadcastChannelKt.access$getNO_ELEMENT$p();
            return super.cancelImpl$kotlinx_coroutines_core(throwable0);
        }
        finally {
            lock0.unlock();
        }
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public boolean close(Throwable throwable0) {
        Lock lock0 = this.lock;
        lock0.lock();
        try {
            for(Object object0: this.subscribers) {
                ((BufferedChannel)object0).close(throwable0);
            }
            Iterable iterable0 = this.subscribers;
            Collection collection0 = new ArrayList();
            for(Object object1: iterable0) {
                if(((BufferedChannel)object1).hasElements$kotlinx_coroutines_core()) {
                    collection0.add(object1);
                }
            }
            this.subscribers = (List)collection0;
            return super.close(throwable0);
        }
        finally {
            lock0.unlock();
        }
    }

    public final int getCapacity() {
        return this.capacity;
    }

    public final Object getValue() {
        Lock lock0 = this.lock;
        lock0.lock();
        try {
            if(this.isClosedForSend()) {
                Throwable throwable0 = this.getCloseCause();
                if(throwable0 == null) {
                    throwable0 = new IllegalStateException("This broadcast channel is closed");
                }
                throw throwable0;
            }
            if(this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                return this.lastConflatedElement;
            }
        }
        finally {
            lock0.unlock();
        }
        throw new IllegalStateException("No value");
    }

    public static void getValue$annotations() {
    }

    public final Object getValueOrNull() {
        Object object0 = null;
        Lock lock0 = this.lock;
        lock0.lock();
        try {
            if(!this.isClosedForReceive() && this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                object0 = this.lastConflatedElement;
            }
            return object0;
        }
        finally {
            lock0.unlock();
        }
    }

    public static void getValueOrNull$annotations() {
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public boolean isClosedForSend() {
        this.lock.lock();
        try {
            return super.isClosedForSend();
        }
        finally {
            this.lock.unlock();
        }
    }

    @Override  // kotlinx.coroutines.channels.BroadcastChannel
    public ReceiveChannel openSubscription() {
        Lock lock0 = this.lock;
        lock0.lock();
        try {
            SubscriberConflated broadcastChannelImpl$SubscriberConflated0 = this.capacity == -1 ? new SubscriberConflated(this) : new SubscriberBuffered(this);
            if(this.isClosedForSend() && this.lastConflatedElement == BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                broadcastChannelImpl$SubscriberConflated0.close(this.getCloseCause());
                return broadcastChannelImpl$SubscriberConflated0;
            }
            if(this.lastConflatedElement != BroadcastChannelKt.access$getNO_ELEMENT$p()) {
                broadcastChannelImpl$SubscriberConflated0.trySend-JP2dKIU(this.getValue());
            }
            this.subscribers = CollectionsKt.plus(this.subscribers, broadcastChannelImpl$SubscriberConflated0);
            return broadcastChannelImpl$SubscriberConflated0;
        }
        finally {
            lock0.unlock();
        }
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    protected void registerSelectForSend(SelectInstance selectInstance0, Object object0) {
        Lock lock0 = this.lock;
        lock0.lock();
        try {
            Object object1 = this.onSendInternalResult.remove(selectInstance0);
            if(object1 != null) {
                selectInstance0.selectInRegistrationPhase(object1);
                return;
            }
        }
        finally {
            lock0.unlock();
        }
        CoroutineScope coroutineScope0 = CoroutineScopeKt.CoroutineScope(selectInstance0.getContext());
        kotlinx.coroutines.channels.BroadcastChannelImpl.registerSelectForSend.2 broadcastChannelImpl$registerSelectForSend$20 = new Function2(object0, selectInstance0, null) {
            final Object $element;
            final SelectInstance $select;
            int label;

            {
                BroadcastChannelImpl.this = broadcastChannelImpl0;
                this.$element = object0;
                this.$select = selectInstance0;
                super(2, continuation0);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation create(Object object0, Continuation continuation0) {
                return new kotlinx.coroutines.channels.BroadcastChannelImpl.registerSelectForSend.2(BroadcastChannelImpl.this, this.$element, this.$select, continuation0);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(((CoroutineScope)object0), ((Continuation)object1));
            }

            public final Object invoke(CoroutineScope coroutineScope0, Continuation continuation0) {
                return ((kotlinx.coroutines.channels.BroadcastChannelImpl.registerSelectForSend.2)this.create(coroutineScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
            }

            @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object object0) {
                Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                boolean z = true;
                switch(this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(object0);
                        try {
                            this.label = 1;
                            if(BroadcastChannelImpl.this.send(this.$element, this) == object1) {
                                return object1;
                            label_8:
                                ResultKt.throwOnFailure(object0);
                            }
                            break;
                        }
                        catch(Throwable throwable0) {
                            goto label_11;
                        }
                    }
                    case 1: {
                        goto label_8;
                    label_11:
                        if(!BroadcastChannelImpl.this.isClosedForSend() || !(throwable0 instanceof ClosedSendChannelException) && BroadcastChannelImpl.this.getSendException() != throwable0) {
                            throw throwable0;
                        }
                        z = false;
                        break;
                    }
                    default: {
                        throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                    }
                }
                BroadcastChannelImpl broadcastChannelImpl0 = BroadcastChannelImpl.this;
                SelectInstance selectInstance0 = this.$select;
                Lock lock0 = BroadcastChannelImpl.this.lock;
                lock0.lock();
                try {
                    Map map0 = broadcastChannelImpl0.onSendInternalResult;
                    Unit unit0 = z ? Unit.INSTANCE : BufferedChannelKt.getCHANNEL_CLOSED();
                    map0.put(selectInstance0, unit0);
                    Intrinsics.checkNotNull(selectInstance0, "null cannot be cast to non-null type kotlinx.coroutines.selects.SelectImplementation<*>");
                    SelectImplementation selectImplementation0 = (SelectImplementation)selectInstance0;
                    if(((SelectImplementation)selectInstance0).trySelectDetailed(broadcastChannelImpl0, Unit.INSTANCE) != TrySelectDetailedResult.REREGISTER) {
                        broadcastChannelImpl0.onSendInternalResult.remove(selectInstance0);
                    }
                    return Unit.INSTANCE;
                }
                finally {
                    lock0.unlock();
                }
            }
        };
        BuildersKt__Builders_commonKt.launch$default(coroutineScope0, null, CoroutineStart.UNDISPATCHED, broadcastChannelImpl$registerSelectForSend$20, 1, null);
    }

    private final void removeSubscriber(ReceiveChannel receiveChannel0) {
        Lock lock0 = this.lock;
        lock0.lock();
        try {
            Iterable iterable0 = this.subscribers;
            Collection collection0 = new ArrayList();
            for(Object object0: iterable0) {
                if(((BufferedChannel)object0) != receiveChannel0) {
                    collection0.add(object0);
                }
            }
            this.subscribers = (List)collection0;
        }
        finally {
            lock0.unlock();
        }
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public Object send(Object object0, Continuation continuation0) {
        Object object4;
        Iterator iterator0;
        BroadcastChannelImpl broadcastChannelImpl0;
        Object object3;
        kotlinx.coroutines.channels.BroadcastChannelImpl.send.1 broadcastChannelImpl$send$10;
        if(continuation0 instanceof kotlinx.coroutines.channels.BroadcastChannelImpl.send.1) {
            broadcastChannelImpl$send$10 = (kotlinx.coroutines.channels.BroadcastChannelImpl.send.1)continuation0;
            if((broadcastChannelImpl$send$10.label & 0x80000000) == 0) {
                broadcastChannelImpl$send$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    int label;
                    Object result;

                    @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object object0) {
                        this.result = object0;
                        this.label |= 0x80000000;
                        return continuation0.send(null, this);
                    }
                };
            }
            else {
                broadcastChannelImpl$send$10.label ^= 0x80000000;
            }
        }
        else {
            broadcastChannelImpl$send$10 = new ContinuationImpl(/*ERROR_MISSING_ARG_1/*) {
                Object L$0;
                Object L$1;
                Object L$2;
                int label;
                Object result;

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    this.result = object0;
                    this.label |= 0x80000000;
                    return continuation0.send(null, this);
                }
            };
        }
        Object object1 = broadcastChannelImpl$send$10.result;
        Object object2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(broadcastChannelImpl$send$10.label) {
            case 0: {
                ResultKt.throwOnFailure(object1);
                Lock lock0 = this.lock;
                lock0.lock();
                try {
                    if(!this.isClosedForSend()) {
                        if(this.capacity == -1) {
                            this.lastConflatedElement = object0;
                        }
                        List list0 = this.subscribers;
                        object3 = object0;
                        broadcastChannelImpl0 = this;
                        iterator0 = list0.iterator();
                        goto label_36;
                    }
                }
                finally {
                    lock0.unlock();
                }
                throw this.getSendException();
            }
            case 1: {
                iterator0 = (Iterator)broadcastChannelImpl$send$10.L$2;
                object4 = broadcastChannelImpl$send$10.L$1;
                broadcastChannelImpl0 = (BroadcastChannelImpl)broadcastChannelImpl$send$10.L$0;
                ResultKt.throwOnFailure(object1);
                break;
            }
            default: {
                throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
            }
        }
        while(true) {
            if(!((Boolean)object1).booleanValue() && broadcastChannelImpl0.isClosedForSend()) {
                throw broadcastChannelImpl0.getSendException();
            }
            object3 = object4;
        label_36:
            if(!iterator0.hasNext()) {
                break;
            }
            Object object5 = iterator0.next();
            broadcastChannelImpl$send$10.L$0 = broadcastChannelImpl0;
            broadcastChannelImpl$send$10.L$1 = object3;
            broadcastChannelImpl$send$10.L$2 = iterator0;
            broadcastChannelImpl$send$10.label = 1;
            Object object6 = ((BufferedChannel)object5).sendBroadcast$kotlinx_coroutines_core(object3, broadcastChannelImpl$send$10);
            if(object6 == object2) {
                return object2;
            }
            object4 = object3;
            object1 = object6;
        }
        return Unit.INSTANCE;
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel
    public String toString() {
        return (this.lastConflatedElement == BroadcastChannelKt.NO_ELEMENT ? "" : "CONFLATED_ELEMENT=" + this.lastConflatedElement + "; ") + "BROADCAST=<" + super.toString() + ">; SUBSCRIBERS=" + CollectionsKt.joinToString$default(this.subscribers, ";", "<", ">", 0, null, null, 56, null);
    }

    @Override  // kotlinx.coroutines.channels.BufferedChannel, kotlinx.coroutines.channels.SendChannel
    public Object trySend-JP2dKIU(Object object0) {
        Lock lock0 = this.lock;
        lock0.lock();
        try {
            if(this.isClosedForSend()) {
                return super.trySend-JP2dKIU(object0);
            }
            Iterable iterable0 = this.subscribers;
            if(!(iterable0 instanceof Collection) || !((Collection)iterable0).isEmpty()) {
                for(Object object2: iterable0) {
                    if(((BufferedChannel)object2).shouldSendSuspend$kotlinx_coroutines_core()) {
                        return ChannelResult.Companion.failure-PtdJZtk();
                    }
                    if(false) {
                        break;
                    }
                }
            }
            if(this.capacity == -1) {
                this.lastConflatedElement = object0;
            }
            for(Object object4: this.subscribers) {
                ((BufferedChannel)object4).trySend-JP2dKIU(object0);
            }
        }
        finally {
            lock0.unlock();
        }
        return ChannelResult.Companion.success-JP2dKIU(Unit.INSTANCE);
    }
}

