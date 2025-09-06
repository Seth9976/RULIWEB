package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ChannelSegment..ExternalSyntheticBackportWithForwarding0;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001A8\u0010\u0004\u001A\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001A\u00020\u00032!\u0010\b\u001A\u001D\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\u00050\tH\u0082\b\u001A!\u0010\r\u001A\u0002H\u000E\"\u000E\b\u0000\u0010\u000E*\b\u0012\u0004\u0012\u0002H\u000E0\u000F*\u0002H\u000EH\u0000¢\u0006\u0002\u0010\u0010\u001Av\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u00130\u0012\"\u000E\b\u0000\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u0014*\b\u0012\u0004\u0012\u0002H\u00130\u00152\u0006\u0010\u0016\u001A\u00020\u00172\u0006\u0010\u0018\u001A\u0002H\u001328\b\b\u0010\u0019\u001A2\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u0011H\u0013¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\u001B\u0012\u0004\u0012\u0002H\u00130\u001AH\u0080\bø\u0001\u0000\u001Aj\u0010\u001C\u001A\b\u0012\u0004\u0012\u0002H\u00130\u0012\"\u000E\b\u0000\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u0014*\u0002H\u00132\u0006\u0010\u0016\u001A\u00020\u001726\u0010\u0019\u001A2\u0012\u0013\u0012\u00110\u0017¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u0011H\u0013¢\u0006\f\b\n\u0012\b\b\u000B\u0012\u0004\b\b(\u001B\u0012\u0004\u0012\u0002H\u00130\u001AH\u0000ø\u0001\u0000¢\u0006\u0002\u0010\u001D\u001A+\u0010\u001E\u001A\u00020\u0005\"\u000E\b\u0000\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u00130\u0014*\b\u0012\u0004\u0012\u0002H\u00130\u00152\u0006\u0010\u001F\u001A\u0002H\u0013H\u0080\b\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000E\u0010\u0002\u001A\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"CLOSED", "Lkotlinx/coroutines/internal/Symbol;", "POINTERS_SHIFT", "", "addConditionally", "", "Lkotlinx/atomicfu/AtomicInt;", "delta", "condition", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "cur", "close", "N", "Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "(Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;)Lkotlinx/coroutines/internal/ConcurrentLinkedListNode;", "findSegmentAndMoveForward", "Lkotlinx/coroutines/internal/SegmentOrClosed;", "S", "Lkotlinx/coroutines/internal/Segment;", "Lkotlinx/atomicfu/AtomicRef;", "id", "", "startFrom", "createNewSegment", "Lkotlin/Function2;", "prev", "findSegmentInternal", "(Lkotlinx/coroutines/internal/Segment;JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "moveForward", "to", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ConcurrentLinkedListKt {
    private static final Symbol CLOSED = null;
    private static final int POINTERS_SHIFT = 16;

    static {
        ConcurrentLinkedListKt.CLOSED = new Symbol("CLOSED");
    }

    private static final boolean addConditionally$atomicfu(Object object0, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0, int v, Function1 function10) {
        do {
            int v1 = atomicIntegerFieldUpdater0.get(object0);
            if(!((Boolean)function10.invoke(v1)).booleanValue()) {
                return false;
            }
        }
        while(!atomicIntegerFieldUpdater0.compareAndSet(object0, v1, v1 + v));
        return true;
    }

    private static final boolean addConditionally$atomicfu$array(Object object0, AtomicIntegerArray atomicIntegerArray0, int v, int v1, Function1 function10) {
        do {
            int v2 = atomicIntegerArray0.get(v);
            if(!((Boolean)function10.invoke(v2)).booleanValue()) {
                return false;
            }
        }
        while(!atomicIntegerArray0.compareAndSet(v, v2, v2 + v1));
        return true;
    }

    public static final ConcurrentLinkedListNode close(ConcurrentLinkedListNode concurrentLinkedListNode0) {
        while(true) {
            Object object0 = concurrentLinkedListNode0.getNextOrClosed();
            if(object0 == ConcurrentLinkedListKt.CLOSED) {
                return concurrentLinkedListNode0;
            }
            if(((ConcurrentLinkedListNode)object0) == null) {
                if(!concurrentLinkedListNode0.markAsClosed()) {
                    continue;
                }
                return concurrentLinkedListNode0;
            }
            concurrentLinkedListNode0 = (ConcurrentLinkedListNode)object0;
        }
    }

    public static final Object findSegmentAndMoveForward$atomicfu(Object object0, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, long v, Segment segment0, Function2 function20) {
        Object object1;
        while(true) {
            object1 = ConcurrentLinkedListKt.findSegmentInternal(segment0, v, function20);
            if(SegmentOrClosed.isClosed-impl(object1)) {
                break;
            }
            Segment segment1 = SegmentOrClosed.getSegment-impl(object1);
            while(true) {
                Segment segment2 = (Segment)atomicReferenceFieldUpdater0.get(object0);
                if(segment2.id >= segment1.id) {
                    return object1;
                }
                if(!segment1.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, object0, segment2, segment1)) {
                    if(segment2.decPointers$kotlinx_coroutines_core()) {
                        segment2.remove();
                    }
                    return object1;
                }
                if(segment1.decPointers$kotlinx_coroutines_core()) {
                    segment1.remove();
                }
            }
        }
        return object1;
    }

    public static final Object findSegmentAndMoveForward$atomicfu$array(Object object0, AtomicReferenceArray atomicReferenceArray0, int v, long v1, Segment segment0, Function2 function20) {
        Object object1;
        while(true) {
            object1 = ConcurrentLinkedListKt.findSegmentInternal(segment0, v1, function20);
            if(SegmentOrClosed.isClosed-impl(object1)) {
                break;
            }
            Segment segment1 = SegmentOrClosed.getSegment-impl(object1);
            while(true) {
                Segment segment2 = (Segment)atomicReferenceArray0.get(v);
                if(segment2.id >= segment1.id) {
                    return object1;
                }
                if(!segment1.tryIncPointers$kotlinx_coroutines_core()) {
                    break;
                }
                if(ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceArray0, v, segment2, segment1)) {
                    if(segment2.decPointers$kotlinx_coroutines_core()) {
                        segment2.remove();
                    }
                    return object1;
                }
                if(segment1.decPointers$kotlinx_coroutines_core()) {
                    segment1.remove();
                }
            }
        }
        return object1;
    }

    public static final Object findSegmentInternal(Segment segment0, long v, Function2 function20) {
        while(true) {
            if(segment0.id >= v && !segment0.isRemoved()) {
                return segment0;
            }
            Object object0 = segment0.getNextOrClosed();
            if(object0 == ConcurrentLinkedListKt.CLOSED) {
                return ConcurrentLinkedListKt.CLOSED;
            }
            Segment segment1 = (Segment)(((ConcurrentLinkedListNode)object0));
            if(segment1 == null) {
                segment1 = (Segment)function20.invoke(((long)(segment0.id + 1L)), segment0);
                if(!segment0.trySetNext(segment1)) {
                    continue;
                }
                if(segment0.isRemoved()) {
                    segment0.remove();
                }
            }
            segment0 = segment1;
        }
    }

    private static final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    private static final void loop$atomicfu$array(AtomicReferenceArray atomicReferenceArray0, int v, Function1 function10) {
        while(true) {
            function10.invoke(atomicReferenceArray0.get(v));
        }
    }

    public static final boolean moveForward$atomicfu(Object object0, AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Segment segment0) {
        while(true) {
            Segment segment1 = (Segment)atomicReferenceFieldUpdater0.get(object0);
            if(segment1.id >= segment0.id) {
                return true;
            }
            if(!segment0.tryIncPointers$kotlinx_coroutines_core()) {
                return false;
            }
            if(AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceFieldUpdater0, object0, segment1, segment0)) {
                if(segment1.decPointers$kotlinx_coroutines_core()) {
                    segment1.remove();
                }
                return true;
            }
            if(segment0.decPointers$kotlinx_coroutines_core()) {
                segment0.remove();
            }
        }
    }

    public static final boolean moveForward$atomicfu$array(Object object0, AtomicReferenceArray atomicReferenceArray0, int v, Segment segment0) {
        while(true) {
            Segment segment1 = (Segment)atomicReferenceArray0.get(v);
            if(segment1.id >= segment0.id) {
                return true;
            }
            if(!segment0.tryIncPointers$kotlinx_coroutines_core()) {
                return false;
            }
            if(ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(atomicReferenceArray0, v, segment1, segment0)) {
                if(segment1.decPointers$kotlinx_coroutines_core()) {
                    segment1.remove();
                }
                return true;
            }
            if(segment0.decPointers$kotlinx_coroutines_core()) {
                segment0.remove();
            }
        }
    }
}

