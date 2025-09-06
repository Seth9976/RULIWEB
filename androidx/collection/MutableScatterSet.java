package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableSet;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u00011B\u000F\u0012\b\b\u0002\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u0013\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\nJ\u0014\u0010\u000B\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\rJ\u0014\u0010\u000B\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002J\u001B\u0010\u000B\u001A\u00020\b2\u000E\u0010\f\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000E\u00A2\u0006\u0002\u0010\u000FJ\u0014\u0010\u000B\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010J\u0014\u0010\u000B\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0011J\b\u0010\u0012\u001A\u00020\u0013H\u0002J\f\u0010\u0014\u001A\b\u0012\u0004\u0012\u00028\u00000\u0015J\u0006\u0010\u0016\u001A\u00020\u0013J\u0015\u0010\u0017\u001A\u00020\u00042\u0006\u0010\t\u001A\u00028\u0000H\u0002\u00A2\u0006\u0002\u0010\u0018J\u0010\u0010\u0019\u001A\u00020\u00042\u0006\u0010\u001A\u001A\u00020\u0004H\u0002J\b\u0010\u001B\u001A\u00020\u0013H\u0002J\u0010\u0010\u001C\u001A\u00020\u00132\u0006\u0010\u001D\u001A\u00020\u0004H\u0002J\u0010\u0010\u001E\u001A\u00020\u00132\u0006\u0010\u0003\u001A\u00020\u0004H\u0002J\u0016\u0010\u001F\u001A\u00020\u00132\u0006\u0010\t\u001A\u00028\u0000H\u0086\u0002\u00A2\u0006\u0002\u0010 J\u0017\u0010\u001F\u001A\u00020\u00132\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\rH\u0086\u0002J\u0017\u0010\u001F\u001A\u00020\u00132\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\u0002J\u001E\u0010\u001F\u001A\u00020\u00132\u000E\u0010\f\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000EH\u0086\u0002\u00A2\u0006\u0002\u0010!J\u0017\u0010\u001F\u001A\u00020\u00132\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0086\u0002J\u0017\u0010\u001F\u001A\u00020\u00132\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0086\u0002J\u0016\u0010\"\u001A\u00020\u00132\u0006\u0010\t\u001A\u00028\u0000H\u0086\u0002\u00A2\u0006\u0002\u0010 J\u0017\u0010\"\u001A\u00020\u00132\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\rH\u0086\u0002J\u0017\u0010\"\u001A\u00020\u00132\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0086\u0002J\u001E\u0010\"\u001A\u00020\u00132\u000E\u0010\f\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000EH\u0086\u0002\u00A2\u0006\u0002\u0010!J\u0017\u0010\"\u001A\u00020\u00132\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0086\u0002J\u0017\u0010\"\u001A\u00020\u00132\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0086\u0002J\u0013\u0010#\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\nJ\u0014\u0010$\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\rJ\u0014\u0010$\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0002J\u001B\u0010$\u001A\u00020\b2\u000E\u0010\f\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u000E\u00A2\u0006\u0002\u0010\u000FJ\u0014\u0010$\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010J\u0014\u0010$\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\u0011J\b\u0010%\u001A\u00020\u0013H\u0002J\u0010\u0010&\u001A\u00020\u00132\u0006\u0010\'\u001A\u00020\u0004H\u0001J \u0010(\u001A\u00020\u00132\u0012\u0010)\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0*H\u0086\b\u00F8\u0001\u0000J\u0010\u0010+\u001A\u00020\u00132\u0006\u0010,\u001A\u00020\u0004H\u0002J\b\u0010-\u001A\u00020\u0004H\u0007J\u0019\u0010.\u001A\u00020\u00132\u0006\u0010\'\u001A\u00020\u00042\u0006\u0010/\u001A\u000200H\u0082\bR\u000E\u0010\u0006\u001A\u00020\u0004X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u00062"}, d2 = {"Landroidx/collection/MutableScatterSet;", "E", "Landroidx/collection/ScatterSet;", "initialCapacity", "", "(I)V", "growthLimit", "add", "", "element", "(Ljava/lang/Object;)Z", "addAll", "elements", "Landroidx/collection/ObjectList;", "", "([Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "adjustStorage", "", "asMutableSet", "", "clear", "findAbsoluteInsertIndex", "(Ljava/lang/Object;)I", "findFirstAvailableSlot", "hash1", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "(Ljava/lang/Object;)V", "([Ljava/lang/Object;)V", "plusAssign", "remove", "removeAll", "removeDeletedMarkers", "removeElementAt", "index", "removeIf", "predicate", "Lkotlin/Function1;", "resizeStorage", "newCapacity", "trim", "writeMetadata", "value", "", "MutableSetWrapper", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MutableScatterSet extends ScatterSet {
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u001E\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010)\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\f0\u0001R\b\u0012\u0004\u0012\u00028\u00000\u00022\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0015\u0010\u0005\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001A\u00020\u00062\f\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u000BH\u0016J\b\u0010\f\u001A\u00020\rH\u0016J\u000F\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00000\u000FH\u0096\u0002J\u0015\u0010\u0010\u001A\u00020\u00062\u0006\u0010\u0007\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\bJ\u0016\u0010\u0011\u001A\u00020\u00062\f\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u000BH\u0016J\u0016\u0010\u0012\u001A\u00020\u00062\f\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u000BH\u0016¨\u0006\u0013"}, d2 = {"Landroidx/collection/MutableScatterSet$MutableSetWrapper;", "Landroidx/collection/ScatterSet$SetWrapper;", "Landroidx/collection/ScatterSet;", "", "(Landroidx/collection/MutableScatterSet;)V", "add", "", "element", "(Ljava/lang/Object;)Z", "addAll", "elements", "", "clear", "", "iterator", "", "remove", "removeAll", "retainAll", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class MutableSetWrapper extends SetWrapper implements Set, KMutableSet {
        @Override  // androidx.collection.ScatterSet$SetWrapper
        public boolean add(Object object0) {
            return MutableScatterSet.this.add(object0);
        }

        @Override  // androidx.collection.ScatterSet$SetWrapper
        public boolean addAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            return MutableScatterSet.this.addAll(collection0);
        }

        @Override  // androidx.collection.ScatterSet$SetWrapper
        public void clear() {
            MutableScatterSet.this.clear();
        }

        @Override  // androidx.collection.ScatterSet$SetWrapper
        public Iterator iterator() {
            return new Object() {
                private int current;
                private final Iterator iterator;

                {
                    this.current = -1;
                    this.iterator = SequencesKt.iterator(new Function2(this, null) {
                        int I$0;
                        int I$1;
                        int I$2;
                        int I$3;
                        long J$0;
                        private Object L$0;
                        Object L$1;
                        Object L$2;
                        Object L$3;
                        int label;

                        {
                            MutableScatterSet.this = mutableScatterSet0;
                            androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1.this = mutableScatterSet$MutableSetWrapper$iterator$10;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1.iterator.1 mutableScatterSet$MutableSetWrapper$iterator$1$iterator$10 = new androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1.iterator.1(MutableScatterSet.this, androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1.this, continuation0);
                            mutableScatterSet$MutableSetWrapper$iterator$1$iterator$10.L$0 = object0;
                            return mutableScatterSet$MutableSetWrapper$iterator$1$iterator$10;
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                        }

                        public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                            return ((androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1.iterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            int v1;
                            int v;
                            long[] arr_v;
                            androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1 mutableScatterSet$MutableSetWrapper$iterator$10;
                            MutableScatterSet mutableScatterSet0;
                            SequenceScope sequenceScope0;
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    sequenceScope0 = (SequenceScope)this.L$0;
                                    mutableScatterSet0 = MutableScatterSet.this;
                                    mutableScatterSet$MutableSetWrapper$iterator$10 = androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1.this;
                                    arr_v = mutableScatterSet0.metadata;
                                    v = arr_v.length - 2;
                                    if(v >= 0) {
                                        v1 = 0;
                                        goto label_49;
                                    }
                                    break;
                                }
                                case 1: {
                                    int v2 = this.I$3;
                                    int v3 = this.I$2;
                                    long v4 = this.J$0;
                                    int v5 = this.I$1;
                                    int v6 = this.I$0;
                                    long[] arr_v1 = (long[])this.L$3;
                                    MutableScatterSet mutableScatterSet1 = (MutableScatterSet)this.L$2;
                                    androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1 mutableScatterSet$MutableSetWrapper$iterator$11 = (androidx.collection.MutableScatterSet.MutableSetWrapper.iterator.1)this.L$1;
                                    SequenceScope sequenceScope1 = (SequenceScope)this.L$0;
                                    ResultKt.throwOnFailure(object0);
                                alab1:
                                    while(true) {
                                        while(true) {
                                            v4 >>= 8;
                                            ++v2;
                                        label_24:
                                            if(v2 >= v3) {
                                                break alab1;
                                            }
                                            if((0xFFL & v4) >= 0x80L) {
                                                break;
                                            }
                                            int v7 = (v5 << 3) + v2;
                                            mutableScatterSet$MutableSetWrapper$iterator$11.setCurrent(v7);
                                            this.L$0 = sequenceScope1;
                                            this.L$1 = mutableScatterSet$MutableSetWrapper$iterator$11;
                                            this.L$2 = mutableScatterSet1;
                                            this.L$3 = arr_v1;
                                            this.I$0 = v6;
                                            this.I$1 = v5;
                                            this.J$0 = v4;
                                            this.I$2 = v3;
                                            this.I$3 = v2;
                                            this.label = 1;
                                            if(sequenceScope1.yield(mutableScatterSet1.elements[v7], this) != object1) {
                                                break;
                                            }
                                            return object1;
                                        }
                                    }
                                    if(v3 == 8) {
                                        v1 = v5;
                                        v = v6;
                                        arr_v = arr_v1;
                                        mutableScatterSet0 = mutableScatterSet1;
                                        mutableScatterSet$MutableSetWrapper$iterator$10 = mutableScatterSet$MutableSetWrapper$iterator$11;
                                        sequenceScope0 = sequenceScope1;
                                        while(true) {
                                            if(v1 == v) {
                                                break;
                                            }
                                            ++v1;
                                        label_49:
                                            long v8 = arr_v[v1];
                                            if((~v8 << 7 & v8 & 0x8080808080808080L) == 0x8080808080808080L) {
                                                continue;
                                            }
                                            v6 = v;
                                            v5 = v1;
                                            sequenceScope1 = sequenceScope0;
                                            v2 = 0;
                                            mutableScatterSet1 = mutableScatterSet0;
                                            arr_v1 = arr_v;
                                            v3 = 8 - (~(v1 - v) >>> 0x1F);
                                            mutableScatterSet$MutableSetWrapper$iterator$11 = mutableScatterSet$MutableSetWrapper$iterator$10;
                                            v4 = v8;
                                            goto label_24;
                                        }
                                    }
                                    break;
                                }
                                default: {
                                    throw new IllegalStateException("call to \'resume\' before \'invoke\' with coroutine");
                                }
                            }
                            return Unit.INSTANCE;
                        }
                    });
                }

                public final int getCurrent() {
                    return this.current;
                }

                public final Iterator getIterator() {
                    return this.iterator;
                }

                @Override
                public boolean hasNext() {
                    return this.iterator.hasNext();
                }

                @Override
                public Object next() {
                    return this.iterator.next();
                }

                @Override
                public void remove() {
                    int v = this.current;
                    if(v != -1) {
                        MutableScatterSet.this.removeElementAt(v);
                        this.current = -1;
                    }
                }

                public final void setCurrent(int v) {
                    this.current = v;
                }
            };
        }

        @Override  // androidx.collection.ScatterSet$SetWrapper
        public boolean remove(Object object0) {
            return MutableScatterSet.this.remove(object0);
        }

        @Override  // androidx.collection.ScatterSet$SetWrapper
        public boolean removeAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            int v = MutableScatterSet.this.getSize();
            for(Object object0: collection0) {
                MutableScatterSet.this.minusAssign(object0);
            }
            return v != MutableScatterSet.this.getSize();
        }

        @Override  // androidx.collection.ScatterSet$SetWrapper
        public boolean retainAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            MutableScatterSet mutableScatterSet0 = MutableScatterSet.this;
            long[] arr_v = mutableScatterSet0.metadata;
            int v = arr_v.length - 2;
            if(v >= 0) {
                boolean z = false;
                for(int v1 = 0; true; ++v1) {
                    long v2 = arr_v[v1];
                    if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                        int v3 = 8 - (~(v1 - v) >>> 0x1F);
                        for(int v4 = 0; v4 < v3; ++v4) {
                            if((0xFFL & v2) < 0x80L) {
                                int v5 = (v1 << 3) + v4;
                                if(!collection0.contains(mutableScatterSet0.elements[v5])) {
                                    mutableScatterSet0.removeElementAt(v5);
                                    z = true;
                                }
                            }
                            v2 >>= 8;
                        }
                        if(v3 != 8) {
                            return z;
                        }
                    }
                    if(v1 == v) {
                        break;
                    }
                }
                return z;
            }
            return false;
        }
    }

    private int growthLimit;

    public MutableScatterSet() {
        this(0, 1, null);
    }

    public MutableScatterSet(int v) {
        super(null);
        if(v < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        this.initializeStorage(ScatterMapKt.unloadedCapacity(v));
    }

    public MutableScatterSet(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 6;
        }
        this(v);
    }

    public final boolean add(Object object0) {
        int v = this.getSize();
        this.elements[this.findAbsoluteInsertIndex(object0)] = object0;
        return this.getSize() != v;
    }

    public final boolean addAll(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        int v = this.getSize();
        this.plusAssign(objectList0);
        return v != this.getSize();
    }

    public final boolean addAll(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "elements");
        int v = this.getSize();
        this.plusAssign(scatterSet0);
        return v != this.getSize();
    }

    public final boolean addAll(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        int v = this.getSize();
        this.plusAssign(iterable0);
        return v != this.getSize();
    }

    public final boolean addAll(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        int v = this.getSize();
        this.plusAssign(sequence0);
        return v != this.getSize();
    }

    public final boolean addAll(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        int v = this.getSize();
        this.plusAssign(arr_object);
        return v != this.getSize();
    }

    private final void adjustStorage() {
        if(this._capacity > 8 && UByte..ExternalSyntheticBackport0.m(((long)this._size) * 0x20L, ((long)this._capacity) * 25L) <= 0) {
            this.removeDeletedMarkers();
            return;
        }
        this.resizeStorage(ScatterMapKt.nextCapacity(this._capacity));
    }

    public final Set asMutableSet() {
        return new MutableSetWrapper(this);
    }

    public final void clear() {
        this._size = 0;
        if(this.metadata != ScatterMapKt.EmptyGroup) {
            ArraysKt.fill$default(this.metadata, 0x8080808080808080L, 0, 0, 6, null);
            int v = this._capacity >> 3;
            this.metadata[v] |= 0xFFL << ((this._capacity & 7) << 3);
        }
        ArraysKt.fill(this.elements, null, 0, this._capacity);
        this.initializeGrowth();
    }

    private final int findAbsoluteInsertIndex(Object object0) {
        int v = object0 == null ? 0 : object0.hashCode();
        int v1 = v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = v6 ^ ((long)(v1 & 0x7F)) * 0x101010101010101L;
            for(long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L; v8 != 0L; v8 &= v8 - 1L) {
                int v9 = v3 + (Long.numberOfTrailingZeros(v8) >> 3) & v2;
                if(Intrinsics.areEqual(this.elements[v9], object0)) {
                    return v9;
                }
            }
            if((~v6 << 6 & v6 & 0x8080808080808080L) != 0L) {
                int v10 = this.findFirstAvailableSlot(v1 >>> 7);
                if(this.growthLimit == 0 && (this.metadata[v10 >> 3] >> ((v10 & 7) << 3) & 0xFFL) != 0xFEL) {
                    this.adjustStorage();
                    v10 = this.findFirstAvailableSlot(v1 >>> 7);
                }
                ++this._size;
                int v11 = (v10 & 7) << 3;
                this.growthLimit -= ((this.metadata[v10 >> 3] >> v11 & 0xFFL) == 0x80L ? 1 : 0);
                long[] arr_v = this.metadata;
                arr_v[v10 >> 3] = arr_v[v10 >> 3] & ~(0xFFL << v11) | ((long)(v1 & 0x7F)) << v11;
                int v12 = (v10 - 7 & this._capacity) + (this._capacity & 7);
                int v13 = (v12 & 7) << 3;
                arr_v[v12 >> 3] = ~(0xFFL << v13) & arr_v[v12 >> 3] | ((long)(v1 & 0x7F)) << v13;
                return v10;
            }
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    private final int findFirstAvailableSlot(int v) {
        long v6;
        int v1 = this._capacity;
        int v2 = v & v1;
        int v3 = 0;
        while(true) {
            int v4 = (v2 & 7) << 3;
            long v5 = this.metadata[(v2 >> 3) + 1] << 0x40 - v4 & -((long)v4) >> 0x3F | this.metadata[v2 >> 3] >>> v4;
            v6 = v5 & ~v5 << 7 & 0x8080808080808080L;
            if(v6 != 0L) {
                break;
            }
            v3 += 8;
            v2 = v2 + v3 & v1;
        }
        return v2 + (Long.numberOfTrailingZeros(v6) >> 3) & v1;
    }

    private final void initializeGrowth() {
        this.growthLimit = ScatterMapKt.loadedCapacity(this.getCapacity()) - this._size;
    }

    private final void initializeMetadata(int v) {
        long[] arr_v;
        if(v == 0) {
            arr_v = ScatterMapKt.EmptyGroup;
        }
        else {
            long[] arr_v1 = new long[(v + 15 & -8) >> 3];
            ArraysKt.fill$default(arr_v1, 0x8080808080808080L, 0, 0, 6, null);
            arr_v = arr_v1;
        }
        this.metadata = arr_v;
        this.metadata[v >> 3] |= 0xFFL << ((v & 7) << 3);
        this.initializeGrowth();
    }

    private final void initializeStorage(int v) {
        int v1 = v <= 0 ? 0 : Math.max(7, ScatterMapKt.normalizeCapacity(v));
        this._capacity = v1;
        this.initializeMetadata(v1);
        this.elements = new Object[v1];
    }

    public final void minusAssign(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        Object[] arr_object = objectList0.content;
        int v = objectList0._size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.minusAssign(arr_object[v1]);
        }
    }

    public final void minusAssign(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "elements");
        Object[] arr_object = scatterSet0.elements;
        long[] arr_v = scatterSet0.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            this.minusAssign(arr_object[(v1 << 3) + v4]);
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_17;
                    }
                    break;
                }
            label_17:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final void minusAssign(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        for(Object object0: iterable0) {
            this.minusAssign(object0);
        }
    }

    public final void minusAssign(Object object0) {
        int v11;
        int v = 0;
        int v1 = object0 == null ? 0 : object0.hashCode();
        int v2 = v1 * 0xCC9E2D51 ^ v1 * 0xCC9E2D51 << 16;
        int v3 = this._capacity;
        int v4 = v2 >>> 7;
        while(true) {
            int v5 = v4 & v3;
            int v6 = (v5 & 7) << 3;
            long v7 = this.metadata[(v5 >> 3) + 1] << 0x40 - v6 & -((long)v6) >> 0x3F | this.metadata[v5 >> 3] >>> v6;
            long v8 = ((long)(v2 & 0x7F)) * 0x101010101010101L ^ v7;
            long v9 = ~v8 & v8 - 0x101010101010101L & 0x8080808080808080L;
            while(v9 != 0L) {
                int v10 = (Long.numberOfTrailingZeros(v9) >> 3) + v5 & v3;
                if(Intrinsics.areEqual(this.elements[v10], object0)) {
                    v11 = v10;
                    goto label_19;
                }
                v9 &= v9 - 1L;
            }
            if((v7 & ~v7 << 6 & 0x8080808080808080L) == 0L) {
                goto label_22;
            }
            else {
                v11 = -1;
            }
        label_19:
            if(v11 >= 0) {
                this.removeElementAt(v11);
            }
            return;
        label_22:
            v += 8;
            v4 = v5 + v;
        }
    }

    public final void minusAssign(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        for(Object object0: sequence0) {
            this.minusAssign(object0);
        }
    }

    public final void minusAssign(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        for(int v = 0; v < arr_object.length; ++v) {
            this.minusAssign(arr_object[v]);
        }
    }

    public final void plusAssign(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        Object[] arr_object = objectList0.content;
        int v = objectList0._size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.plusAssign(arr_object[v1]);
        }
    }

    public final void plusAssign(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "elements");
        Object[] arr_object = scatterSet0.elements;
        long[] arr_v = scatterSet0.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            this.plusAssign(arr_object[(v1 << 3) + v4]);
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_17;
                    }
                    break;
                }
            label_17:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final void plusAssign(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        for(Object object0: iterable0) {
            this.plusAssign(object0);
        }
    }

    public final void plusAssign(Object object0) {
        this.elements[this.findAbsoluteInsertIndex(object0)] = object0;
    }

    public final void plusAssign(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        for(Object object0: sequence0) {
            this.plusAssign(object0);
        }
    }

    public final void plusAssign(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        for(int v = 0; v < arr_object.length; ++v) {
            this.plusAssign(arr_object[v]);
        }
    }

    public final boolean remove(Object object0) {
        int v10;
        int v = object0 == null ? 0 : object0.hashCode();
        int v1 = v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = ((long)(v1 & 0x7F)) * 0x101010101010101L ^ v6;
            long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L;
            while(v8 != 0L) {
                int v9 = (Long.numberOfTrailingZeros(v8) >> 3) + v3 & v2;
                if(Intrinsics.areEqual(this.elements[v9], object0)) {
                    v10 = v9;
                    goto label_18;
                }
                v8 &= v8 - 1L;
            }
            if((v6 & ~v6 << 6 & 0x8080808080808080L) == 0L) {
                goto label_21;
            }
            else {
                v10 = -1;
            }
        label_18:
            if(v10 >= 0) {
                this.removeElementAt(v10);
            }
            return v10 >= 0;
        label_21:
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    public final boolean removeAll(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "elements");
        int v = this.getSize();
        this.minusAssign(objectList0);
        return v != this.getSize();
    }

    public final boolean removeAll(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "elements");
        int v = this.getSize();
        this.minusAssign(scatterSet0);
        return v != this.getSize();
    }

    public final boolean removeAll(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "elements");
        int v = this.getSize();
        this.minusAssign(iterable0);
        return v != this.getSize();
    }

    public final boolean removeAll(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "elements");
        int v = this.getSize();
        this.minusAssign(sequence0);
        return v != this.getSize();
    }

    public final boolean removeAll(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        int v = this.getSize();
        this.minusAssign(arr_object);
        return v != this.getSize();
    }

    private final void removeDeletedMarkers() {
        long[] arr_v = this.metadata;
        int v = this._capacity;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            int v3 = (v1 & 7) << 3;
            if((arr_v[v1 >> 3] >> v3 & 0xFFL) == 0xFEL) {
                long[] arr_v1 = this.metadata;
                arr_v1[v1 >> 3] = 0x80L << v3 | arr_v1[v1 >> 3] & ~(0xFFL << v3);
                int v4 = (v1 - 7 & this._capacity) + (this._capacity & 7);
                int v5 = (v4 & 7) << 3;
                arr_v1[v4 >> 3] = ~(0xFFL << v5) & arr_v1[v4 >> 3] | 0x80L << v5;
                ++v2;
            }
        }
        this.growthLimit += v2;
    }

    public final void removeElementAt(int v) {
        --this._size;
        long[] arr_v = this.metadata;
        int v1 = (v & 7) << 3;
        arr_v[v >> 3] = arr_v[v >> 3] & ~(0xFFL << v1) | 0xFEL << v1;
        int v2 = (v - 7 & this._capacity) + (this._capacity & 7);
        int v3 = (v2 & 7) << 3;
        arr_v[v2 >> 3] = arr_v[v2 >> 3] & ~(0xFFL << v3) | 0xFEL << v3;
        this.elements[v] = null;
    }

    public final void removeIf(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.elements;
        long[] arr_v = this.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L) {
                            int v5 = (v1 << 3) + v4;
                            if(((Boolean)function10.invoke(arr_object[v5])).booleanValue()) {
                                this.removeElementAt(v5);
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_19;
                    }
                    break;
                }
            label_19:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    private final void resizeStorage(int v) {
        int v7;
        long[] arr_v = this.metadata;
        Object[] arr_object = this.elements;
        int v1 = this._capacity;
        this.initializeStorage(v);
        Object[] arr_object1 = this.elements;
        for(int v2 = 0; v2 < v1; v2 = v7 + 1) {
            if((arr_v[v2 >> 3] >> ((v2 & 7) << 3) & 0xFFL) < 0x80L) {
                Object object0 = arr_object[v2];
                int v3 = object0 == null ? 0 : object0.hashCode();
                int v4 = v3 * 0xCC9E2D51 ^ v3 * 0xCC9E2D51 << 16;
                int v5 = this.findFirstAvailableSlot(v4 >>> 7);
                long[] arr_v1 = this.metadata;
                int v6 = (v5 & 7) << 3;
                v7 = v2;
                arr_v1[v5 >> 3] = arr_v1[v5 >> 3] & ~(0xFFL << v6) | ((long)(v4 & 0x7F)) << v6;
                int v8 = (v5 - 7 & this._capacity) + (this._capacity & 7);
                int v9 = (v8 & 7) << 3;
                arr_v1[v8 >> 3] = ~(0xFFL << v9) & arr_v1[v8 >> 3] | ((long)(v4 & 0x7F)) << v9;
                arr_object1[v5] = object0;
            }
            else {
                v7 = v2;
            }
        }
    }

    public final int trim() {
        int v = this._capacity;
        int v1 = ScatterMapKt.normalizeCapacity(ScatterMapKt.unloadedCapacity(this._size));
        if(v1 < v) {
            this.resizeStorage(v1);
            return v - this._capacity;
        }
        return 0;
    }

    private final void writeMetadata(int v, long v1) {
        long[] arr_v = this.metadata;
        int v2 = (v & 7) << 3;
        arr_v[v >> 3] = arr_v[v >> 3] & ~(0xFFL << v2) | v1 << v2;
        int v3 = (v - 7 & this._capacity) + (this._capacity & 7);
        int v4 = (v3 & 7) << 3;
        arr_v[v3 >> 3] = v1 << v4 | arr_v[v3 >> 3] & ~(0xFFL << v4);
    }
}

