package kotlinx.coroutines.internal;

import androidx.concurrent.futures.AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 ,*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002,-B\u0015\u0012\u0006\u0010\u0003\u001A\u00020\u0004\u0012\u0006\u0010\u0005\u001A\u00020\u0006\u00A2\u0006\u0002\u0010\u0007J\u0013\u0010\u0015\u001A\u00020\u00042\u0006\u0010\u0016\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\u0017J \u0010\u0018\u001A\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\n2\u0006\u0010\u0019\u001A\u00020\u001AH\u0002J \u0010\u001B\u001A\u0012\u0012\u0004\u0012\u00028\u00000\u0000j\b\u0012\u0004\u0012\u00028\u0000`\n2\u0006\u0010\u0019\u001A\u00020\u001AH\u0002J\u0006\u0010\u001C\u001A\u00020\u0006J1\u0010\u001D\u001A\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n2\u0006\u0010\u001E\u001A\u00020\u00042\u0006\u0010\u0016\u001A\u00028\u0000H\u0002\u00A2\u0006\u0002\u0010\u001FJ\u0006\u0010 \u001A\u00020\u0006J&\u0010!\u001A\b\u0012\u0004\u0012\u0002H#0\"\"\u0004\b\u0001\u0010#2\u0012\u0010$\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H#0%J\b\u0010&\u001A\u00020\u001AH\u0002J\f\u0010\'\u001A\b\u0012\u0004\u0012\u00028\u00000\u0000J\b\u0010(\u001A\u0004\u0018\u00010\u0002J,\u0010)\u001A\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n2\u0006\u0010*\u001A\u00020\u00042\u0006\u0010+\u001A\u00020\u0004H\u0002R#\u0010\b\u001A\u001C\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0000j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\n0\tX\u0082\u0004R\t\u0010\u000B\u001A\u00020\fX\u0082\u0004R\u0011\u0010\r\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000EX\u0082\u0004R\u000E\u0010\u0003\u001A\u00020\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u000F\u001A\u00020\u00068F\u00A2\u0006\u0006\u001A\u0004\b\u000F\u0010\u0010R\u000E\u0010\u0011\u001A\u00020\u0004X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0006X\u0082\u0004\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001A\u00020\u00048F\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\u0014\u00A8\u0006."}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "E", "", "capacity", "", "singleConsumer", "", "(IZ)V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/internal/Core;", "_state", "Lkotlinx/atomicfu/AtomicLong;", "array", "Lkotlinx/atomicfu/AtomicArray;", "isEmpty", "()Z", "mask", "size", "getSize", "()I", "addLast", "element", "(Ljava/lang/Object;)I", "allocateNextCopy", "state", "", "allocateOrGetNextCopy", "close", "fillPlaceholder", "index", "(ILjava/lang/Object;)Lkotlinx/coroutines/internal/LockFreeTaskQueueCore;", "isClosed", "map", "", "R", "transform", "Lkotlin/Function1;", "markFrozen", "next", "removeFirstOrNull", "removeSlowPath", "oldHead", "newHead", "Companion", "Placeholder", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class LockFreeTaskQueueCore {
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\u0016\u001A\u00020\u0004*\u00020\tJ\u0012\u0010\u0017\u001A\u00020\t*\u00020\t2\u0006\u0010\u0018\u001A\u00020\u0004J\u0012\u0010\u0019\u001A\u00020\t*\u00020\t2\u0006\u0010\u001A\u001A\u00020\u0004JP\u0010\u001B\u001A\u0002H\u001C\"\u0004\b\u0001\u0010\u001C*\u00020\t26\u0010\u001D\u001A2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001F\u0012\b\b \u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u001F\u0012\b\b \u0012\u0004\b\b(\"\u0012\u0004\u0012\u0002H\u001C0\u001EH\u0086\b¢\u0006\u0002\u0010#J\u0015\u0010$\u001A\u00020\t*\u00020\t2\u0006\u0010%\u001A\u00020\tH\u0086\u0004R\u000E\u0010\u0003\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0005\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0007\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\b\u001A\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\n\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\f\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\r\u001A\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u000F\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0010\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0011\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001A\u00020\u00138\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0014\u001A\u00020\tX\u0086T¢\u0006\u0002\n\u0000R\u000E\u0010\u0015\u001A\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Companion;", "", "()V", "ADD_CLOSED", "", "ADD_FROZEN", "ADD_SUCCESS", "CAPACITY_BITS", "CLOSED_MASK", "", "CLOSED_SHIFT", "FROZEN_MASK", "FROZEN_SHIFT", "HEAD_MASK", "HEAD_SHIFT", "INITIAL_CAPACITY", "MAX_CAPACITY_MASK", "MIN_ADD_SPIN_CAPACITY", "REMOVE_FROZEN", "Lkotlinx/coroutines/internal/Symbol;", "TAIL_MASK", "TAIL_SHIFT", "addFailReason", "updateHead", "newHead", "updateTail", "newTail", "withState", "T", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "head", "tail", "(JLkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "wo", "other", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final int addFailReason(long v) {
            return (v & 0x2000000000000000L) == 0L ? 1 : 2;
        }

        public final long updateHead(long v, int v1) {
            return v & 0xFFFFFFFFC0000000L | ((long)v1);
        }

        public final long updateTail(long v, int v1) {
            return this.wo(v, 0xFFFFFFFC0000000L) | ((long)v1) << 30;
        }

        public final Object withState(long v, Function2 function20) {
            return function20.invoke(((int)(0x3FFFFFFFL & v)), ((int)((v & 0xFFFFFFFC0000000L) >> 30)));
        }

        public final long wo(long v, long v1) [...] // Inlined contents
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0010\u0010\u0002\u001A\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeTaskQueueCore$Placeholder;", "", "index", "", "(I)V", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public static final class Placeholder {
        public final int index;

        public Placeholder(int v) {
            this.index = v;
        }
    }

    public static final int ADD_CLOSED = 2;
    public static final int ADD_FROZEN = 1;
    public static final int ADD_SUCCESS = 0;
    public static final int CAPACITY_BITS = 30;
    public static final long CLOSED_MASK = 0x2000000000000000L;
    public static final int CLOSED_SHIFT = 61;
    public static final Companion Companion = null;
    public static final long FROZEN_MASK = 0x1000000000000000L;
    public static final int FROZEN_SHIFT = 60;
    public static final long HEAD_MASK = 0x3FFFFFFFL;
    public static final int HEAD_SHIFT = 0;
    public static final int INITIAL_CAPACITY = 8;
    public static final int MAX_CAPACITY_MASK = 0x3FFFFFFF;
    public static final int MIN_ADD_SPIN_CAPACITY = 0x400;
    public static final Symbol REMOVE_FROZEN = null;
    public static final long TAIL_MASK = 0xFFFFFFFC0000000L;
    public static final int TAIL_SHIFT = 30;
    @Volatile
    private volatile Object _next;
    private static final AtomicReferenceFieldUpdater _next$FU;
    @Volatile
    private volatile long _state;
    private static final AtomicLongFieldUpdater _state$FU;
    private final AtomicReferenceArray array;
    private final int capacity;
    private final int mask;
    private final boolean singleConsumer;

    static {
        LockFreeTaskQueueCore.Companion = new Companion(null);
        LockFreeTaskQueueCore._next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, Object.class, "_next");
        LockFreeTaskQueueCore._state$FU = AtomicLongFieldUpdater.newUpdater(LockFreeTaskQueueCore.class, "_state");
        LockFreeTaskQueueCore.REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    }

    public LockFreeTaskQueueCore(int v, boolean z) {
        this.capacity = v;
        this.singleConsumer = z;
        this.mask = v - 1;
        this.array = new AtomicReferenceArray(v);
        if(v - 1 > 0x3FFFFFFF || (v & v - 1) != 0) {
            throw new IllegalStateException("Check failed.");
        }
    }

    public final int addLast(Object object0) {
        int v2;
        int v1;
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = LockFreeTaskQueueCore._state$FU;
        do {
            while(true) {
                long v = atomicLongFieldUpdater0.get(this);
                if((0x3000000000000000L & v) != 0L) {
                    return LockFreeTaskQueueCore.Companion.addFailReason(v);
                }
                Companion lockFreeTaskQueueCore$Companion0 = LockFreeTaskQueueCore.Companion;
                v1 = (int)((0xFFFFFFFC0000000L & v) >> 30);
                v2 = this.mask;
                if((v1 + 2 & v2) == (((int)(0x3FFFFFFFL & v)) & v2)) {
                    return 1;
                }
                if(this.singleConsumer || this.array.get(v1 & v2) == null) {
                    break;
                }
                if(this.capacity < 0x400 || (v1 - ((int)(0x3FFFFFFFL & v)) & 0x3FFFFFFF) > this.capacity >> 1) {
                    return 1;
                }
            }
        }
        while(!LockFreeTaskQueueCore._state$FU.compareAndSet(this, v, lockFreeTaskQueueCore$Companion0.updateTail(v, v1 + 1 & 0x3FFFFFFF)));
        this.array.set(v1 & v2, object0);
        LockFreeTaskQueueCore lockFreeTaskQueueCore0 = this;
        while((LockFreeTaskQueueCore._state$FU.get(lockFreeTaskQueueCore0) & 0x1000000000000000L) != 0L) {
            lockFreeTaskQueueCore0 = lockFreeTaskQueueCore0.next().fillPlaceholder(v1, object0);
            if(lockFreeTaskQueueCore0 == null) {
                break;
            }
        }
        return 0;
    }

    private final LockFreeTaskQueueCore allocateNextCopy(long v) {
        LockFreeTaskQueueCore lockFreeTaskQueueCore0 = new LockFreeTaskQueueCore(this.capacity * 2, this.singleConsumer);
        for(int v1 = (int)(0x3FFFFFFFL & v); true; ++v1) {
            int v2 = this.mask;
            if((v1 & v2) == (((int)((0xFFFFFFFC0000000L & v) >> 30)) & v2)) {
                break;
            }
            Placeholder lockFreeTaskQueueCore$Placeholder0 = this.array.get(v2 & v1);
            if(lockFreeTaskQueueCore$Placeholder0 == null) {
                lockFreeTaskQueueCore$Placeholder0 = new Placeholder(v1);
            }
            lockFreeTaskQueueCore0.array.set(lockFreeTaskQueueCore0.mask & v1, lockFreeTaskQueueCore$Placeholder0);
        }
        LockFreeTaskQueueCore._state$FU.set(lockFreeTaskQueueCore0, v & 0xEFFFFFFFFFFFFFFFL);
        return lockFreeTaskQueueCore0;
    }

    private final LockFreeTaskQueueCore allocateOrGetNextCopy(long v) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0 = LockFreeTaskQueueCore._next$FU;
        LockFreeTaskQueueCore lockFreeTaskQueueCore0;
        while((lockFreeTaskQueueCore0 = (LockFreeTaskQueueCore)atomicReferenceFieldUpdater0.get(this)) == null) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore1 = this.allocateNextCopy(v);
            AbstractResolvableFuture.SafeAtomicHelper..ExternalSyntheticBackportWithForwarding0.m(LockFreeTaskQueueCore._next$FU, this, null, lockFreeTaskQueueCore1);
        }
        return lockFreeTaskQueueCore0;
    }

    public final boolean close() {
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = LockFreeTaskQueueCore._state$FU;
        do {
            long v = atomicLongFieldUpdater0.get(this);
            if((v & 0x2000000000000000L) != 0L) {
                return true;
            }
            if((0x1000000000000000L & v) != 0L) {
                return false;
            }
        }
        while(!atomicLongFieldUpdater0.compareAndSet(this, v, 0x2000000000000000L | v));
        return true;
    }

    private final LockFreeTaskQueueCore fillPlaceholder(int v, Object object0) {
        Object object1 = this.array.get(this.mask & v);
        if(object1 instanceof Placeholder && ((Placeholder)object1).index == v) {
            this.array.set(v & this.mask, object0);
            return this;
        }
        return null;
    }

    public final int getSize() {
        long v = LockFreeTaskQueueCore._state$FU.get(this);
        return 0x3FFFFFFF & ((int)((v & 0xFFFFFFFC0000000L) >> 30)) - ((int)(0x3FFFFFFFL & v));
    }

    public final boolean isClosed() {
        return (LockFreeTaskQueueCore._state$FU.get(this) & 0x2000000000000000L) != 0L;
    }

    public final boolean isEmpty() {
        long v = LockFreeTaskQueueCore._state$FU.get(this);
        return ((int)(0x3FFFFFFFL & v)) == ((int)((v & 0xFFFFFFFC0000000L) >> 30));
    }

    private final void loop$atomicfu(AtomicLongFieldUpdater atomicLongFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicLongFieldUpdater0.get(object0));
        }
    }

    private final void loop$atomicfu(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater0, Function1 function10, Object object0) {
        while(true) {
            function10.invoke(atomicReferenceFieldUpdater0.get(object0));
        }
    }

    public final List map(Function1 function10) {
        ArrayList arrayList0 = new ArrayList(this.capacity);
        long v = LockFreeTaskQueueCore._state$FU.get(this);
        for(int v1 = (int)(0x3FFFFFFFL & v); true; ++v1) {
            int v2 = this.mask;
            if((v1 & v2) == (((int)((v & 0xFFFFFFFC0000000L) >> 30)) & v2)) {
                break;
            }
            Object object0 = this.array.get(v2 & v1);
            if(object0 != null && !(object0 instanceof Placeholder)) {
                arrayList0.add(function10.invoke(object0));
            }
        }
        return arrayList0;
    }

    private final long markFrozen() {
        long v;
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = LockFreeTaskQueueCore._state$FU;
        do {
            v = atomicLongFieldUpdater0.get(this);
            if((v & 0x1000000000000000L) != 0L) {
                return v;
            }
        }
        while(!atomicLongFieldUpdater0.compareAndSet(this, v, 0x1000000000000000L | v));
        return 0x1000000000000000L | v;
    }

    public final LockFreeTaskQueueCore next() {
        return this.allocateOrGetNextCopy(this.markFrozen());
    }

    public final Object removeFirstOrNull() {
        int v2;
        Object object0;
        long v;
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = LockFreeTaskQueueCore._state$FU;
        do {
            while(true) {
                v = atomicLongFieldUpdater0.get(this);
                if((0x1000000000000000L & v) != 0L) {
                    return LockFreeTaskQueueCore.REMOVE_FROZEN;
                }
                Companion lockFreeTaskQueueCore$Companion0 = LockFreeTaskQueueCore.Companion;
                int v1 = this.mask;
                if((((int)((0xFFFFFFFC0000000L & v) >> 30)) & v1) == (((int)(0x3FFFFFFFL & v)) & v1)) {
                    return null;
                }
                object0 = this.array.get(v1 & ((int)(0x3FFFFFFFL & v)));
                if(object0 != null) {
                    break;
                }
                if(this.singleConsumer) {
                    return null;
                }
            }
            if(object0 instanceof Placeholder) {
                return null;
            }
            v2 = ((int)(0x3FFFFFFFL & v)) + 1 & 0x3FFFFFFF;
            if(LockFreeTaskQueueCore._state$FU.compareAndSet(this, v, lockFreeTaskQueueCore$Companion0.updateHead(v, v2))) {
                this.array.set(this.mask & ((int)(0x3FFFFFFFL & v)), null);
                return object0;
            }
        }
        while(!this.singleConsumer);
        LockFreeTaskQueueCore lockFreeTaskQueueCore0 = this;
        do {
            lockFreeTaskQueueCore0 = lockFreeTaskQueueCore0.removeSlowPath(((int)(0x3FFFFFFFL & v)), v2);
        }
        while(lockFreeTaskQueueCore0 != null);
        return object0;
    }

    private final LockFreeTaskQueueCore removeSlowPath(int v, int v1) {
        int v3;
        AtomicLongFieldUpdater atomicLongFieldUpdater0 = LockFreeTaskQueueCore._state$FU;
        do {
            long v2 = atomicLongFieldUpdater0.get(this);
            Companion lockFreeTaskQueueCore$Companion0 = LockFreeTaskQueueCore.Companion;
            v3 = (int)(0x3FFFFFFFL & v2);
            if((0x1000000000000000L & v2) != 0L) {
                return this.next();
            }
        }
        while(!LockFreeTaskQueueCore._state$FU.compareAndSet(this, v2, lockFreeTaskQueueCore$Companion0.updateHead(v2, v1)));
        this.array.set(this.mask & v3, null);
        return null;
    }

    private final void update$atomicfu(AtomicLongFieldUpdater atomicLongFieldUpdater0, Function1 function10, Object object0) {
        do {
            long v = atomicLongFieldUpdater0.get(object0);
        }
        while(!atomicLongFieldUpdater0.compareAndSet(object0, v, ((Number)function10.invoke(v)).longValue()));
    }

    private final long updateAndGet$atomicfu(AtomicLongFieldUpdater atomicLongFieldUpdater0, Function1 function10, Object object0) {
        Number number0;
        do {
            long v = atomicLongFieldUpdater0.get(object0);
            number0 = (Number)function10.invoke(v);
        }
        while(!atomicLongFieldUpdater0.compareAndSet(object0, v, number0.longValue()));
        return number0.longValue();
    }
}

