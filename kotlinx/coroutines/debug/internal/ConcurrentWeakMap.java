package kotlinx.coroutines.debug.internal;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.AbstractMutableMap;
import kotlin.collections.AbstractMutableSet;
import kotlin.jvm.Volatile;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.ChannelSegment..ExternalSyntheticBackportWithForwarding0;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\'\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000F\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0004:\u0003()*B\u000F\u0012\b\b\u0002\u0010\u0005\u001A\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\u0019\u001A\u00020\u001A2\n\u0010\u001B\u001A\u0006\u0012\u0002\b\u00030\u001CH\u0002J\b\u0010\u001D\u001A\u00020\u001AH\u0016J\b\u0010\u001E\u001A\u00020\u001AH\u0002J\u0018\u0010\u001F\u001A\u0004\u0018\u00018\u00012\u0006\u0010 \u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010!J\u001F\u0010\"\u001A\u0004\u0018\u00018\u00012\u0006\u0010 \u001A\u00028\u00002\u0006\u0010#\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010$J!\u0010%\u001A\u0004\u0018\u00018\u00012\u0006\u0010 \u001A\u00028\u00002\b\u0010#\u001A\u0004\u0018\u00018\u0001H\u0002¢\u0006\u0002\u0010$J\u0017\u0010&\u001A\u0004\u0018\u00018\u00012\u0006\u0010 \u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010!J\u0006\u0010\'\u001A\u00020\u001AR\t\u0010\b\u001A\u00020\tX\u0082\u0004R\u001F\u0010\n\u001A\u0018\u0012\u0014\u0012\u00120\fR\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00000\u000BX\u0082\u0004R&\u0010\r\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000F0\u000E8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011R\u001A\u0010\u0012\u001A\b\u0012\u0004\u0012\u00028\u00000\u000E8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001A\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0005\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0018X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "K", "", "V", "Lkotlin/collections/AbstractMutableMap;", "weakRefQueue", "", "(Z)V", "_size", "Lkotlinx/atomicfu/AtomicInt;", "core", "Lkotlinx/atomicfu/AtomicRef;", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "", "getSize", "()I", "Ljava/lang/ref/ReferenceQueue;", "cleanWeakRef", "", "w", "Lkotlinx/coroutines/debug/internal/HashedWeakRef;", "clear", "decrementSize", "get", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "put", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putSynchronized", "remove", "runWeakRefQueueCleaningLoopUntilInterrupted", "Core", "Entry", "KeyValueSet", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ConcurrentWeakMap extends AbstractMutableMap {
    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010)\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001:\u0001!B\r\u0012\u0006\u0010\u0002\u001A\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\r\u001A\u00020\u000E2\n\u0010\u000F\u001A\u0006\u0012\u0002\b\u00030\u0007J\u0015\u0010\u0010\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0011\u001A\u00028\u0000¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0013\u001A\u00020\u00032\u0006\u0010\u0014\u001A\u00020\u0003H\u0002J,\u0010\u0015\u001A\b\u0012\u0004\u0012\u0002H\u00170\u0016\"\u0004\b\u0002\u0010\u00172\u0018\u0010\u0018\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\u00170\u0019J1\u0010\u001A\u001A\u0004\u0018\u00010\u00012\u0006\u0010\u0011\u001A\u00028\u00002\b\u0010\u001B\u001A\u0004\u0018\u00018\u00012\u0010\b\u0002\u0010\u001C\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0007¢\u0006\u0002\u0010\u001DJ\u0016\u0010\u001E\u001A\u00120\u0000R\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001FJ\u0010\u0010 \u001A\u00020\u000E2\u0006\u0010\u0013\u001A\u00020\u0003H\u0002R\u000E\u0010\u0002\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001A\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00070\u0006X\u0082\u0004R\t\u0010\b\u001A\u00020\tX\u0082\u0004R\u000E\u0010\n\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u000B\u001A\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0006X\u0082\u0004¨\u0006\""}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;", "", "allocated", "", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;I)V", "keys", "Lkotlinx/atomicfu/AtomicArray;", "Lkotlinx/coroutines/debug/internal/HashedWeakRef;", "load", "Lkotlinx/atomicfu/AtomicInt;", "shift", "threshold", "values", "cleanWeakRef", "", "weakRef", "getImpl", "key", "(Ljava/lang/Object;)Ljava/lang/Object;", "index", "hash", "keyValueIterator", "", "E", "factory", "Lkotlin/Function2;", "putImpl", "value", "weakKey0", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/coroutines/debug/internal/HashedWeakRef;)Ljava/lang/Object;", "rehash", "Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;", "removeCleanedAt", "KeyValueIterator", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class Core {
        @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010)\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\b\u0082\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001F\u0012\u0018\u0010\u0003\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000B\u001A\u00020\fH\u0002J\t\u0010\r\u001A\u00020\u000EH\u0096\u0002J\u000E\u0010\u000F\u001A\u00028\u0002H\u0096\u0002¢\u0006\u0002\u0010\u0010J\b\u0010\u0011\u001A\u00020\u0012H\u0016R \u0010\u0003\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000E\u0010\u0006\u001A\u00020\u0007X\u0082\u000E¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\tR\u0010\u0010\n\u001A\u00028\u0001X\u0082.¢\u0006\u0004\n\u0002\u0010\t¨\u0006\u0013"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core$KeyValueIterator;", "E", "", "factory", "Lkotlin/Function2;", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Core;Lkotlin/jvm/functions/Function2;)V", "index", "", "key", "Ljava/lang/Object;", "value", "findNext", "", "hasNext", "", "next", "()Ljava/lang/Object;", "remove", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
        final class KeyValueIterator implements Iterator, KMutableIterator {
            private final Function2 factory;
            private int index;
            private Object key;
            private Object value;

            public KeyValueIterator(Function2 function20) {
                this.factory = function20;
                this.index = -1;
                this.findNext();
            }

            private final void findNext() {
                Object object1;
                Object object0;
            alab1:
                while(true) {
                    do {
                        do {
                            int v = this.index + 1;
                            this.index = v;
                            if(v >= Core.this.allocated) {
                                break alab1;
                            }
                            HashedWeakRef hashedWeakRef0 = (HashedWeakRef)Core.this.keys.get(this.index);
                            if(hashedWeakRef0 == null) {
                                continue alab1;
                            }
                            object0 = hashedWeakRef0.get();
                        }
                        while(object0 == null);
                        this.key = object0;
                        object1 = Core.this.values.get(this.index);
                        if(object1 instanceof Marked) {
                            object1 = ((Marked)object1).ref;
                        }
                    }
                    while(object1 == null);
                    this.value = object1;
                    return;
                }
            }

            @Override
            public boolean hasNext() {
                return this.index < Core.this.allocated;
            }

            @Override
            public Object next() {
                if(this.index >= Core.this.allocated) {
                    throw new NoSuchElementException();
                }
                Function2 function20 = this.factory;
                Unit unit0 = this.key;
                if(unit0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("key");
                    unit0 = Unit.INSTANCE;
                }
                Unit unit1 = this.value;
                if(unit1 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("value");
                    unit1 = Unit.INSTANCE;
                }
                Object object0 = function20.invoke(unit0, unit1);
                this.findNext();
                return object0;
            }

            public Void remove() {
                ConcurrentWeakMapKt.noImpl();
                throw new KotlinNothingValueException();
            }

            @Override
            public void remove() {
                this.remove();
            }
        }

        private final int allocated;
        private final AtomicReferenceArray keys;
        @Volatile
        private volatile int load;
        private static final AtomicIntegerFieldUpdater load$FU;
        private final int shift;
        private final int threshold;
        private final AtomicReferenceArray values;

        static {
            Core.load$FU = AtomicIntegerFieldUpdater.newUpdater(Core.class, "load");
        }

        public Core(int v) {
            this.allocated = v;
            this.shift = Integer.numberOfLeadingZeros(v) + 1;
            this.threshold = v * 2 / 3;
            this.keys = new AtomicReferenceArray(v);
            this.values = new AtomicReferenceArray(v);
        }

        public final void cleanWeakRef(HashedWeakRef hashedWeakRef0) {
            for(int v = this.index(hashedWeakRef0.hash); true; --v) {
                HashedWeakRef hashedWeakRef1 = (HashedWeakRef)this.keys.get(v);
                if(hashedWeakRef1 == null) {
                    return;
                }
                if(hashedWeakRef1 == hashedWeakRef0) {
                    this.removeCleanedAt(v);
                    return;
                }
                if(v == 0) {
                    v = this.allocated;
                }
            }
        }

        public final Object getImpl(Object object0) {
            for(int v = this.index(object0.hashCode()); true; --v) {
                HashedWeakRef hashedWeakRef0 = (HashedWeakRef)this.keys.get(v);
                if(hashedWeakRef0 == null) {
                    return null;
                }
                Object object1 = hashedWeakRef0.get();
                if(Intrinsics.areEqual(object0, object1)) {
                    Object object2 = this.values.get(v);
                    return object2 instanceof Marked ? ((Marked)object2).ref : object2;
                }
                if(object1 == null) {
                    this.removeCleanedAt(v);
                }
                if(v == 0) {
                    v = this.allocated;
                }
            }
        }

        private final int index(int v) {
            return v * -1640531527 >>> this.shift;
        }

        public final Iterator keyValueIterator(Function2 function20) {
            return new KeyValueIterator(this, function20);
        }

        public final Object putImpl(Object object0, Object object1, HashedWeakRef hashedWeakRef0) {
            Object object3;
            int v = this.index(object0.hashCode());
            boolean z = false;
            while(true) {
                HashedWeakRef hashedWeakRef1 = (HashedWeakRef)this.keys.get(v);
                if(hashedWeakRef1 == null) {
                    if(object1 == null) {
                        return null;
                    }
                    if(!z) {
                        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0 = Core.load$FU;
                        do {
                            int v1 = atomicIntegerFieldUpdater0.get(this);
                            if(v1 >= this.threshold) {
                                return ConcurrentWeakMapKt.REHASH;
                            }
                        }
                        while(!atomicIntegerFieldUpdater0.compareAndSet(this, v1, v1 + 1));
                        z = true;
                    }
                    if(hashedWeakRef0 == null) {
                        hashedWeakRef0 = new HashedWeakRef(object0, ConcurrentWeakMap.this.weakRefQueue);
                    }
                    if(!ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(this.keys, v, null, hashedWeakRef0)) {
                        continue;
                    }
                }
                else {
                    Object object2 = hashedWeakRef1.get();
                    if(!Intrinsics.areEqual(object0, object2)) {
                        goto label_26;
                    }
                    else if(z) {
                        Core.load$FU.decrementAndGet(this);
                    }
                }
                do {
                    object3 = this.values.get(v);
                    if(object3 instanceof Marked) {
                        return ConcurrentWeakMapKt.REHASH;
                    }
                }
                while(!ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(this.values, v, object3, object1));
                return object3;
            label_26:
                if(object2 == null) {
                    this.removeCleanedAt(v);
                }
                if(v == 0) {
                    v = this.allocated;
                }
                --v;
            }
        }

        public static Object putImpl$default(Core concurrentWeakMap$Core0, Object object0, Object object1, HashedWeakRef hashedWeakRef0, int v, Object object2) {
            if((v & 4) != 0) {
                hashedWeakRef0 = null;
            }
            return concurrentWeakMap$Core0.putImpl(object0, object1, hashedWeakRef0);
        }

        public final Core rehash() {
            Core concurrentWeakMap$Core0;
        alab1:
            while(true) {
                int v = Integer.highestOneBit(RangesKt.coerceAtLeast(ConcurrentWeakMap.this.size(), 4));
                concurrentWeakMap$Core0 = new Core(ConcurrentWeakMap.this, v * 4);
                int v1 = this.allocated;
                for(int v2 = 0; true; ++v2) {
                    if(v2 >= v1) {
                        break alab1;
                    }
                    HashedWeakRef hashedWeakRef0 = (HashedWeakRef)this.keys.get(v2);
                    Object object0 = hashedWeakRef0 == null ? null : hashedWeakRef0.get();
                    if(hashedWeakRef0 != null && object0 == null) {
                        this.removeCleanedAt(v2);
                    }
                    do {
                        Object object1 = this.values.get(v2);
                        if(object1 instanceof Marked) {
                            object1 = ((Marked)object1).ref;
                            break;
                        }
                        Marked marked0 = ConcurrentWeakMapKt.mark(object1);
                    }
                    while(!ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(this.values, v2, object1, marked0));
                    if(object0 != null && object1 != null && concurrentWeakMap$Core0.putImpl(object0, object1, hashedWeakRef0) == ConcurrentWeakMapKt.REHASH) {
                        break;
                    }
                }
            }
            return concurrentWeakMap$Core0;
        }

        private final void removeCleanedAt(int v) {
            do {
                Object object0 = this.values.get(v);
                if(object0 == null || object0 instanceof Marked) {
                    return;
                }
            }
            while(!ChannelSegment..ExternalSyntheticBackportWithForwarding0.m(this.values, v, object0, null));
            ConcurrentWeakMap.this.decrementSize();
        }

        private final void update$atomicfu(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater0, Function1 function10, Object object0) {
            do {
                int v = atomicIntegerFieldUpdater0.get(object0);
            }
            while(!atomicIntegerFieldUpdater0.compareAndSet(object0, v, ((Number)function10.invoke(v)).intValue()));
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\'\n\u0002\b\u000B\b\u0002\u0018\u0000*\u0004\b\u0002\u0010\u0001*\u0004\b\u0003\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001A\u00028\u0002\u0012\u0006\u0010\u0005\u001A\u00028\u0003¢\u0006\u0002\u0010\u0006J\u0015\u0010\u000B\u001A\u00028\u00032\u0006\u0010\f\u001A\u00028\u0003H\u0016¢\u0006\u0002\u0010\rR\u0016\u0010\u0004\u001A\u00028\u0002X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\u0007\u0010\bR\u0016\u0010\u0005\u001A\u00028\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\t\u001A\u0004\b\n\u0010\b¨\u0006\u000E"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$Entry;", "K", "V", "", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getKey", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getValue", "setValue", "newValue", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    static final class Entry implements Map.Entry, kotlin.jvm.internal.markers.KMutableMap.Entry {
        private final Object key;
        private final Object value;

        public Entry(Object object0, Object object1) {
            this.key = object0;
            this.value = object1;
        }

        @Override
        public Object getKey() {
            return this.key;
        }

        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public Object setValue(Object object0) {
            ConcurrentWeakMapKt.noImpl();
            throw new KotlinNothingValueException();
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010)\n\u0000\b\u0082\u0004\u0018\u0000*\u0004\b\u0002\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u001F\u0012\u0018\u0010\u0003\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\n\u001A\u00020\u000B2\u0006\u0010\f\u001A\u00028\u0002H\u0016¢\u0006\u0002\u0010\rJ\u000F\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00020\u000FH\u0096\u0002R \u0010\u0003\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001A\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap$KeyValueSet;", "E", "Lkotlin/collections/AbstractMutableSet;", "factory", "Lkotlin/Function2;", "(Lkotlinx/coroutines/debug/internal/ConcurrentWeakMap;Lkotlin/jvm/functions/Function2;)V", "size", "", "getSize", "()I", "add", "", "element", "(Ljava/lang/Object;)Z", "iterator", "", "kotlinx-coroutines-core"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class KeyValueSet extends AbstractMutableSet {
        private final Function2 factory;

        public KeyValueSet(Function2 function20) {
            this.factory = function20;
        }

        @Override  // kotlin.collections.AbstractMutableSet
        public boolean add(Object object0) {
            ConcurrentWeakMapKt.noImpl();
            throw new KotlinNothingValueException();
        }

        @Override  // kotlin.collections.AbstractMutableSet
        public int getSize() {
            return ConcurrentWeakMap.this.size();
        }

        @Override
        public Iterator iterator() {
            return ((Core)ConcurrentWeakMap.core$FU.get(ConcurrentWeakMap.this)).keyValueIterator(this.factory);
        }
    }

    @Volatile
    private volatile int _size;
    private static final AtomicIntegerFieldUpdater _size$FU;
    @Volatile
    private volatile Object core;
    private static final AtomicReferenceFieldUpdater core$FU;
    private final ReferenceQueue weakRefQueue;

    static {
        ConcurrentWeakMap._size$FU = AtomicIntegerFieldUpdater.newUpdater(ConcurrentWeakMap.class, "_size");
        ConcurrentWeakMap.core$FU = AtomicReferenceFieldUpdater.newUpdater(ConcurrentWeakMap.class, Object.class, "core");
    }

    public ConcurrentWeakMap() {
        this(false, 1, null);
    }

    public ConcurrentWeakMap(boolean z) {
        this.core = new Core(this, 16);
        this.weakRefQueue = z ? new ReferenceQueue() : null;
    }

    public ConcurrentWeakMap(boolean z, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            z = false;
        }
        this(z);
    }

    private final void cleanWeakRef(HashedWeakRef hashedWeakRef0) {
        ((Core)ConcurrentWeakMap.core$FU.get(this)).cleanWeakRef(hashedWeakRef0);
    }

    @Override
    public void clear() {
        for(Object object0: this.keySet()) {
            this.remove(object0);
        }
    }

    private final void decrementSize() {
        ConcurrentWeakMap._size$FU.decrementAndGet(this);
    }

    @Override
    public Object get(Object object0) {
        return object0 == null ? null : ((Core)ConcurrentWeakMap.core$FU.get(this)).getImpl(object0);
    }

    @Override  // kotlin.collections.AbstractMutableMap
    public Set getEntries() {
        return new KeyValueSet(this, kotlinx.coroutines.debug.internal.ConcurrentWeakMap.entries.1.INSTANCE);

        @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\'\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001A\u0002H\u00022\u0006\u0010\u0006\u001A\u0002H\u0003H\n¢\u0006\u0004\b\u0007\u0010\b"}, d2 = {"<anonymous>", "", "K", "V", "", "k", "v", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/util/Map$Entry;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.debug.internal.ConcurrentWeakMap.entries.1 extends Lambda implements Function2 {
            public static final kotlinx.coroutines.debug.internal.ConcurrentWeakMap.entries.1 INSTANCE;

            static {
                kotlinx.coroutines.debug.internal.ConcurrentWeakMap.entries.1.INSTANCE = new kotlinx.coroutines.debug.internal.ConcurrentWeakMap.entries.1();
            }

            kotlinx.coroutines.debug.internal.ConcurrentWeakMap.entries.1() {
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public Object invoke(Object object0, Object object1) {
                return this.invoke(object0, object1);
            }

            public final Map.Entry invoke(Object object0, Object object1) {
                return new Entry(object0, object1);
            }
        }

    }

    @Override  // kotlin.collections.AbstractMutableMap
    public Set getKeys() {
        return new KeyValueSet(this, kotlinx.coroutines.debug.internal.ConcurrentWeakMap.keys.1.INSTANCE);

        @Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0010\u0000\u001A\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u00020\u00022\u0006\u0010\u0004\u001A\u0002H\u00012\u0006\u0010\u0005\u001A\u0002H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "K", "", "V", "k", "<anonymous parameter 1>", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 0x30)
        final class kotlinx.coroutines.debug.internal.ConcurrentWeakMap.keys.1 extends Lambda implements Function2 {
            public static final kotlinx.coroutines.debug.internal.ConcurrentWeakMap.keys.1 INSTANCE;

            static {
                kotlinx.coroutines.debug.internal.ConcurrentWeakMap.keys.1.INSTANCE = new kotlinx.coroutines.debug.internal.ConcurrentWeakMap.keys.1();
            }

            kotlinx.coroutines.debug.internal.ConcurrentWeakMap.keys.1() {
                super(2);
            }

            @Override  // kotlin.jvm.functions.Function2
            public final Object invoke(Object object0, Object object1) {
                return object0;
            }
        }

    }

    @Override  // kotlin.collections.AbstractMutableMap
    public int getSize() {
        return ConcurrentWeakMap._size$FU.get(this);
    }

    @Override  // kotlin.collections.AbstractMutableMap
    public Object put(Object object0, Object object1) {
        Object object2 = Core.putImpl$default(((Core)ConcurrentWeakMap.core$FU.get(this)), object0, object1, null, 4, null);
        if(object2 == ConcurrentWeakMapKt.REHASH) {
            object2 = this.putSynchronized(object0, object1);
        }
        if(object2 == null) {
            ConcurrentWeakMap._size$FU.incrementAndGet(this);
        }
        return object2;
    }

    private final Object putSynchronized(Object object0, Object object1) {
        synchronized(this) {
            Core concurrentWeakMap$Core0 = (Core)ConcurrentWeakMap.core$FU.get(this);
            Object object2;
            while((object2 = Core.putImpl$default(concurrentWeakMap$Core0, object0, object1, null, 4, null)) == ConcurrentWeakMapKt.REHASH) {
                concurrentWeakMap$Core0 = concurrentWeakMap$Core0.rehash();
                ConcurrentWeakMap.core$FU.set(this, concurrentWeakMap$Core0);
            }
            return object2;
        }
    }

    @Override
    public Object remove(Object object0) {
        if(object0 == null) {
            return null;
        }
        Object object1 = Core.putImpl$default(((Core)ConcurrentWeakMap.core$FU.get(this)), object0, null, null, 4, null);
        if(object1 == ConcurrentWeakMapKt.REHASH) {
            object1 = this.putSynchronized(object0, null);
        }
        if(object1 != null) {
            ConcurrentWeakMap._size$FU.decrementAndGet(this);
        }
        return object1;
    }

    public final void runWeakRefQueueCleaningLoopUntilInterrupted() {
        if(this.weakRefQueue != null) {
            try {
                while(true) {
                    Reference reference0 = this.weakRefQueue.remove();
                    Intrinsics.checkNotNull(reference0, "null cannot be cast to non-null type kotlinx.coroutines.debug.internal.HashedWeakRef<*>");
                    this.cleanWeakRef(((HashedWeakRef)reference0));
                }
            }
            catch(InterruptedException unused_ex) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        throw new IllegalStateException("Must be created with weakRefQueue = true");
    }
}

