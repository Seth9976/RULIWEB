package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.StateFlow;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b \u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00060\u0003j\u0002`\u0004B\u0005¢\u0006\u0002\u0010\u0005J\r\u0010\u0017\u001A\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0018J\r\u0010\u0019\u001A\u00028\u0000H$¢\u0006\u0002\u0010\u0018J\u001D\u0010\u001A\u001A\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000E2\u0006\u0010\u001B\u001A\u00020\tH$¢\u0006\u0002\u0010\u001CJ\u001D\u0010\u001D\u001A\u00020\u001E2\u0012\u0010\u001F\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001E0 H\u0084\bJ\u0015\u0010!\u001A\u00020\u001E2\u0006\u0010\"\u001A\u00028\u0000H\u0004¢\u0006\u0002\u0010#R\u0010\u0010\u0006\u001A\u0004\u0018\u00010\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u001E\u0010\n\u001A\u00020\t2\u0006\u0010\b\u001A\u00020\t@BX\u0084\u000E¢\u0006\b\n\u0000\u001A\u0004\b\u000B\u0010\fR\u000E\u0010\r\u001A\u00020\tX\u0082\u000E¢\u0006\u0002\n\u0000R4\u0010\u000F\u001A\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000E2\u0010\u0010\b\u001A\f\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0018\u00010\u000E@BX\u0084\u000E¢\u0006\n\n\u0002\u0010\u0012\u001A\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0013\u001A\b\u0012\u0004\u0012\u00020\t0\u00148F¢\u0006\u0006\u001A\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"Lkotlinx/coroutines/flow/internal/AbstractSharedFlow;", "S", "Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "", "Lkotlinx/coroutines/internal/SynchronizedObject;", "()V", "_subscriptionCount", "Lkotlinx/coroutines/flow/internal/SubscriptionCountStateFlow;", "<set-?>", "", "nCollectors", "getNCollectors", "()I", "nextIndex", "", "slots", "getSlots", "()[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "subscriptionCount", "Lkotlinx/coroutines/flow/StateFlow;", "getSubscriptionCount", "()Lkotlinx/coroutines/flow/StateFlow;", "allocateSlot", "()Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "createSlot", "createSlotArray", "size", "(I)[Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;", "forEachSlotLocked", "", "block", "Lkotlin/Function1;", "freeSlot", "slot", "(Lkotlinx/coroutines/flow/internal/AbstractSharedFlowSlot;)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class AbstractSharedFlow {
    private SubscriptionCountStateFlow _subscriptionCount;
    private int nCollectors;
    private int nextIndex;
    private AbstractSharedFlowSlot[] slots;

    protected final AbstractSharedFlowSlot allocateSlot() {
        SubscriptionCountStateFlow subscriptionCountStateFlow0;
        AbstractSharedFlowSlot abstractSharedFlowSlot0;
        synchronized(this) {
            AbstractSharedFlowSlot[] arr_abstractSharedFlowSlot = this.slots;
            if(arr_abstractSharedFlowSlot == null) {
                arr_abstractSharedFlowSlot = this.createSlotArray(2);
                this.slots = arr_abstractSharedFlowSlot;
            }
            else if(this.nCollectors >= arr_abstractSharedFlowSlot.length) {
                Object[] arr_object = Arrays.copyOf(arr_abstractSharedFlowSlot, arr_abstractSharedFlowSlot.length * 2);
                Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
                this.slots = (AbstractSharedFlowSlot[])arr_object;
                arr_abstractSharedFlowSlot = (AbstractSharedFlowSlot[])arr_object;
            }
            int v1 = this.nextIndex;
            do {
                abstractSharedFlowSlot0 = arr_abstractSharedFlowSlot[v1];
                if(abstractSharedFlowSlot0 == null) {
                    abstractSharedFlowSlot0 = this.createSlot();
                    arr_abstractSharedFlowSlot[v1] = abstractSharedFlowSlot0;
                }
                ++v1;
                v1 = v1 < arr_abstractSharedFlowSlot.length ? v1 + 1 : 0;
                Intrinsics.checkNotNull(abstractSharedFlowSlot0, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
            }
            while(!abstractSharedFlowSlot0.allocateLocked(this));
            this.nextIndex = v1;
            ++this.nCollectors;
            subscriptionCountStateFlow0 = this._subscriptionCount;
        }
        if(subscriptionCountStateFlow0 != null) {
            subscriptionCountStateFlow0.increment(1);
        }
        return abstractSharedFlowSlot0;
    }

    protected abstract AbstractSharedFlowSlot createSlot();

    protected abstract AbstractSharedFlowSlot[] createSlotArray(int arg1);

    protected final void forEachSlotLocked(Function1 function10) {
        if(this.nCollectors != 0) {
            AbstractSharedFlowSlot[] arr_abstractSharedFlowSlot = this.slots;
            if(arr_abstractSharedFlowSlot != null) {
                for(int v = 0; v < arr_abstractSharedFlowSlot.length; ++v) {
                    AbstractSharedFlowSlot abstractSharedFlowSlot0 = arr_abstractSharedFlowSlot[v];
                    if(abstractSharedFlowSlot0 != null) {
                        function10.invoke(abstractSharedFlowSlot0);
                    }
                }
            }
        }
    }

    protected final void freeSlot(AbstractSharedFlowSlot abstractSharedFlowSlot0) {
        Continuation[] arr_continuation;
        SubscriptionCountStateFlow subscriptionCountStateFlow0;
        synchronized(this) {
            int v1 = this.nCollectors - 1;
            this.nCollectors = v1;
            subscriptionCountStateFlow0 = this._subscriptionCount;
            if(v1 == 0) {
                this.nextIndex = 0;
            }
            Intrinsics.checkNotNull(abstractSharedFlowSlot0, "null cannot be cast to non-null type kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot<kotlin.Any>");
            arr_continuation = abstractSharedFlowSlot0.freeLocked(this);
        }
        for(int v2 = 0; v2 < arr_continuation.length; ++v2) {
            Continuation continuation0 = arr_continuation[v2];
            if(continuation0 != null) {
                continuation0.resumeWith(Unit.INSTANCE);
            }
        }
        if(subscriptionCountStateFlow0 != null) {
            subscriptionCountStateFlow0.increment(-1);
        }
    }

    protected final int getNCollectors() {
        return this.nCollectors;
    }

    protected final AbstractSharedFlowSlot[] getSlots() {
        return this.slots;
    }

    public final StateFlow getSubscriptionCount() {
        synchronized(this) {
            SubscriptionCountStateFlow subscriptionCountStateFlow0 = this._subscriptionCount;
            if(subscriptionCountStateFlow0 == null) {
                subscriptionCountStateFlow0 = new SubscriptionCountStateFlow(this.nCollectors);
                this._subscriptionCount = subscriptionCountStateFlow0;
            }
            return subscriptionCountStateFlow0;
        }
    }
}

