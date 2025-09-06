package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001<B\u0007\b\u0004\u00A2\u0006\u0002\u0010\u0003J:\u0010\u0013\u001A\u00020\u00142!\u0010\u0015\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0016H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0006\u0010\u001A\u001A\u00020\u0014J:\u0010\u001A\u001A\u00020\u00142!\u0010\u0015\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0016H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\f\u0010\u001B\u001A\b\u0012\u0004\u0012\u00028\u00000\u001CJ\u0016\u0010\u001D\u001A\u00020\u00142\u0006\u0010\u0019\u001A\u00028\u0000H\u0086\u0002\u00A2\u0006\u0002\u0010\u001EJ\b\u0010\u001F\u001A\u00020\u0005H\u0007J:\u0010\u001F\u001A\u00020\u00052!\u0010\u0015\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0016H\u0087\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\u0013\u0010 \u001A\u00020\u00142\b\u0010!\u001A\u0004\u0018\u00010\u0002H\u0096\u0002J\u0018\u0010\"\u001A\u00020\u00052\u0006\u0010\u0019\u001A\u00028\u0000H\u0080\b\u00A2\u0006\u0004\b#\u0010$J\u000E\u0010%\u001A\u00028\u0000H\u0086\b\u00A2\u0006\u0002\u0010&J?\u0010%\u001A\u00028\u00002!\u0010\u0015\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0016H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u00A2\u0006\u0002\u0010\'JA\u0010(\u001A\u0004\u0018\u00018\u00002!\u0010\u0015\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\u00140\u0016H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001\u00A2\u0006\u0002\u0010\'J:\u0010)\u001A\u00020*2!\u0010+\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020*0\u0016H\u0086\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J:\u0010,\u001A\u00020*2!\u0010+\u001A\u001D\u0012\u0013\u0012\u00110\u0005\u00A2\u0006\f\b\u0017\u0012\b\b\u0018\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020*0\u0016H\u0081\b\u00F8\u0001\u0000\u0082\u0002\b\n\u0006\b\u0001\u0012\u0002\u0010\u0001J\b\u0010.\u001A\u00020\u0005H\u0016J\u0006\u0010/\u001A\u00020\u0014J\u0006\u00100\u001A\u00020\u0014JR\u00101\u001A\u0002022\b\b\u0002\u00103\u001A\u0002042\b\b\u0002\u00105\u001A\u0002042\b\b\u0002\u00106\u001A\u0002042\b\b\u0002\u00107\u001A\u00020\u00052\b\b\u0002\u00108\u001A\u0002042\u0016\b\u0002\u00109\u001A\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u000204\u0018\u00010\u0016H\u0007J\u0006\u0010:\u001A\u00020\u0014J\b\u0010;\u001A\u000202H\u0016R\u0012\u0010\u0004\u001A\u00020\u00058\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001A\u00020\u00058\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001A\u00020\u00058G\u00A2\u0006\u0006\u001A\u0004\b\b\u0010\tR\"\u0010\n\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000B8\u0000@\u0000X\u0081\u000E\u00A2\u0006\n\n\u0002\u0010\r\u0012\u0004\b\f\u0010\u0003R\u0018\u0010\u000E\u001A\u00020\u000F8\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0003R\u0011\u0010\u0011\u001A\u00020\u00058G\u00A2\u0006\u0006\u001A\u0004\b\u0012\u0010\t\u0082\u0001\u0001=\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006>"}, d2 = {"Landroidx/collection/ScatterSet;", "E", "", "()V", "_capacity", "", "_size", "capacity", "getCapacity", "()I", "elements", "", "getElements$annotations", "[Ljava/lang/Object;", "metadata", "", "getMetadata$annotations", "size", "getSize", "all", "", "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "element", "any", "asSet", "", "contains", "(Ljava/lang/Object;)Z", "count", "equals", "other", "findElementIndex", "findElementIndex$collection", "(Ljava/lang/Object;)I", "first", "()Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstOrNull", "forEach", "", "block", "forEachIndex", "index", "hashCode", "isEmpty", "isNotEmpty", "joinToString", "", "separator", "", "prefix", "postfix", "limit", "truncated", "transform", "none", "toString", "SetWrapper", "Landroidx/collection/MutableScatterSet;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ScatterSet {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\u0004\n\u0002\u0010\u001E\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0090\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\nJ\u0016\u0010\u000B\u001A\u00020\b2\f\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00000\rH\u0016J\b\u0010\u000E\u001A\u00020\bH\u0016J\u000F\u0010\u000F\u001A\b\u0012\u0004\u0012\u00028\u00000\u0010H\u0096\u0002R\u0014\u0010\u0003\u001A\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Landroidx/collection/ScatterSet$SetWrapper;", "", "(Landroidx/collection/ScatterSet;)V", "size", "", "getSize", "()I", "contains", "", "element", "(Ljava/lang/Object;)Z", "containsAll", "elements", "", "isEmpty", "iterator", "", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public class SetWrapper implements Set, KMappedMarker {
        @Override
        public boolean add(Object object0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public boolean addAll(Collection collection0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public boolean contains(Object object0) {
            return ScatterSet.this.contains(object0);
        }

        @Override
        public boolean containsAll(Collection collection0) {
            Intrinsics.checkNotNullParameter(collection0, "elements");
            ScatterSet scatterSet0 = ScatterSet.this;
            for(Object object0: collection0) {
                if(!scatterSet0.contains(object0)) {
                    return false;
                }
                if(false) {
                    break;
                }
            }
            return true;
        }

        public int getSize() {
            return ScatterSet.this._size;
        }

        @Override
        public boolean isEmpty() {
            return ScatterSet.this.isEmpty();
        }

        @Override
        public Iterator iterator() {
            return SequencesKt.iterator(new Function2(null) {
                int I$0;
                int I$1;
                int I$2;
                int I$3;
                long J$0;
                private Object L$0;
                Object L$1;
                Object L$2;
                int label;

                {
                    ScatterSet.this = scatterSet0;
                    super(2, continuation0);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation create(Object object0, Continuation continuation0) {
                    androidx.collection.ScatterSet.SetWrapper.iterator.1 scatterSet$SetWrapper$iterator$10 = new androidx.collection.ScatterSet.SetWrapper.iterator.1(ScatterSet.this, continuation0);
                    scatterSet$SetWrapper$iterator$10.L$0 = object0;
                    return scatterSet$SetWrapper$iterator$10;
                }

                @Override  // kotlin.jvm.functions.Function2
                public Object invoke(Object object0, Object object1) {
                    return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                }

                public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                    return ((androidx.collection.ScatterSet.SetWrapper.iterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                }

                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object object0) {
                    int v1;
                    int v;
                    long[] arr_v;
                    Object[] arr_object;
                    SequenceScope sequenceScope0;
                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    switch(this.label) {
                        case 0: {
                            ResultKt.throwOnFailure(object0);
                            sequenceScope0 = (SequenceScope)this.L$0;
                            arr_object = ScatterSet.this.elements;
                            arr_v = ScatterSet.this.metadata;
                            v = arr_v.length - 2;
                            if(v >= 0) {
                                v1 = 0;
                                goto label_42;
                            }
                            break;
                        }
                        case 1: {
                            int v2 = this.I$3;
                            int v3 = this.I$2;
                            long v4 = this.J$0;
                            v1 = this.I$1;
                            int v5 = this.I$0;
                            long[] arr_v1 = (long[])this.L$2;
                            Object[] arr_object1 = (Object[])this.L$1;
                            SequenceScope sequenceScope1 = (SequenceScope)this.L$0;
                            ResultKt.throwOnFailure(object0);
                        alab1:
                            while(true) {
                                while(true) {
                                    v4 >>= 8;
                                    ++v2;
                                label_22:
                                    if(v2 >= v3) {
                                        break alab1;
                                    }
                                    if((0xFFL & v4) >= 0x80L) {
                                        break;
                                    }
                                    this.L$0 = sequenceScope1;
                                    this.L$1 = arr_object1;
                                    this.L$2 = arr_v1;
                                    this.I$0 = v5;
                                    this.I$1 = v1;
                                    this.J$0 = v4;
                                    this.I$2 = v3;
                                    this.I$3 = v2;
                                    this.label = 1;
                                    if(sequenceScope1.yield(arr_object1[(v1 << 3) + v2], this) != object1) {
                                        break;
                                    }
                                    return object1;
                                }
                            }
                            if(v3 == 8) {
                                v = v5;
                                arr_v = arr_v1;
                                arr_object = arr_object1;
                                sequenceScope0 = sequenceScope1;
                                while(true) {
                                    if(v1 == v) {
                                        break;
                                    }
                                    ++v1;
                                label_42:
                                    long v6 = arr_v[v1];
                                    if((~v6 << 7 & v6 & 0x8080808080808080L) == 0x8080808080808080L) {
                                        continue;
                                    }
                                    sequenceScope1 = sequenceScope0;
                                    v2 = 0;
                                    arr_v1 = arr_v;
                                    v5 = v;
                                    v3 = 8 - (~(v1 - v) >>> 0x1F);
                                    arr_object1 = arr_object;
                                    v4 = v6;
                                    goto label_22;
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

        @Override
        public boolean remove(Object object0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public boolean removeAll(Collection collection0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public boolean retainAll(Collection collection0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public final int size() {
            return this.getSize();
        }

        @Override
        public Object[] toArray() {
            return CollectionToArray.toArray(this);
        }

        @Override
        public Object[] toArray(Object[] arr_object) {
            Intrinsics.checkNotNullParameter(arr_object, "array");
            return CollectionToArray.toArray(this, arr_object);
        }
    }

    public int _capacity;
    public int _size;
    public Object[] elements;
    public long[] metadata;

    private ScatterSet() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.elements = ContainerHelpersKt.EMPTY_OBJECTS;
    }

    public ScatterSet(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public final boolean all(Function1 function10) {
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
                        if((0xFFL & v2) < 0x80L && !((Boolean)function10.invoke(arr_object[(v1 << 3) + v4])).booleanValue()) {
                            return false;
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
        return true;
    }

    public final boolean any() {
        return this._size != 0;
    }

    public final boolean any(Function1 function10) {
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
                        if((0xFFL & v2) < 0x80L && ((Boolean)function10.invoke(arr_object[(v1 << 3) + v4])).booleanValue()) {
                            return true;
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
        return false;
    }

    public final Set asSet() {
        return new SetWrapper(this);
    }

    public final boolean contains(Object object0) {
        int v = object0 == null ? 0 : object0.hashCode();
        int v1 = v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
        int v2 = this._capacity;
        int v3 = v1 >>> 7 & v2;
        int v4 = 0;
        while(true) {
            int v5 = (v3 & 7) << 3;
            long v6 = this.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | this.metadata[v3 >> 3] >>> v5;
            long v7 = ((long)(v1 & 0x7F)) * 0x101010101010101L ^ v6;
            for(long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L; v8 != 0L; v8 &= v8 - 1L) {
                int v9 = (Long.numberOfTrailingZeros(v8) >> 3) + v3 & v2;
                if(Intrinsics.areEqual(this.elements[v9], object0)) {
                    return v9 >= 0;
                }
            }
            if((v6 & ~v6 << 6 & 0x8080808080808080L) != 0L) {
                return false;
            }
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    public final int count() {
        return this.getSize();
    }

    public final int count(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "predicate");
        Object[] arr_object = this.elements;
        long[] arr_v = this.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                long v3 = arr_v[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v5 = 0; v5 < v4; ++v5) {
                        if((0xFFL & v3) < 0x80L && ((Boolean)function10.invoke(arr_object[(v1 << 3) + v5])).booleanValue()) {
                            ++v2;
                        }
                        v3 >>= 8;
                    }
                    if(v4 != 8) {
                        return v2;
                    }
                }
                if(v1 == v) {
                    break;
                }
            }
            return v2;
        }
        return 0;
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof ScatterSet)) {
            return false;
        }
        if(((ScatterSet)object0).getSize() != this.getSize()) {
            return false;
        }
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
                        if((0xFFL & v2) < 0x80L && !((ScatterSet)object0).contains(arr_object[(v1 << 3) + v4])) {
                            return false;
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_22;
                    }
                    break;
                }
            label_22:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return true;
    }

    public final int findElementIndex$collection(Object object0) {
        int v = 0;
        int v1 = object0 == null ? 0 : object0.hashCode();
        int v2 = v1 * 0xCC9E2D51 ^ v1 * 0xCC9E2D51 << 16;
        int v3 = this._capacity;
        for(int v4 = v2 >>> 7; true; v4 = v5 + v) {
            int v5 = v4 & v3;
            int v6 = (v5 & 7) << 3;
            long v7 = this.metadata[(v5 >> 3) + 1] << 0x40 - v6 & -((long)v6) >> 0x3F | this.metadata[v5 >> 3] >>> v6;
            long v8 = ((long)(v2 & 0x7F)) * 0x101010101010101L ^ v7;
            for(long v9 = ~v8 & v8 - 0x101010101010101L & 0x8080808080808080L; v9 != 0L; v9 &= v9 - 1L) {
                int v10 = (Long.numberOfTrailingZeros(v9) >> 3) + v5 & v3;
                if(Intrinsics.areEqual(this.elements[v10], object0)) {
                    return v10;
                }
            }
            if((v7 & ~v7 << 6 & 0x8080808080808080L) != 0L) {
                return -1;
            }
            v += 8;
        }
    }

    public final Object first() {
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
                            return arr_object[(v1 << 3) + v4];
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_16;
                    }
                    break;
                }
            label_16:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        throw new NoSuchElementException("The ScatterSet is empty");
    }

    public final Object first(Function1 function10) {
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
                            Object object0 = arr_object[(v1 << 3) + v4];
                            if(((Boolean)function10.invoke(object0)).booleanValue()) {
                                return object0;
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
        throw new NoSuchElementException("Could not find a match");
    }

    public final Object firstOrNull(Function1 function10) {
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
                            Object object0 = arr_object[(v1 << 3) + v4];
                            if(((Boolean)function10.invoke(object0)).booleanValue()) {
                                return object0;
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
        return null;
    }

    public final void forEach(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
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
                            function10.invoke(arr_object[(v1 << 3) + v4]);
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

    public final void forEachIndex(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
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
                            function10.invoke(((int)((v1 << 3) + v4)));
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_16;
                    }
                    break;
                }
            label_16:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public static void getElements$annotations() {
    }

    public static void getMetadata$annotations() {
    }

    public final int getSize() {
        return this._size;
    }

    @Override
    public int hashCode() {
        Object[] arr_object = this.elements;
        long[] arr_v = this.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v2 = 0;
            for(int v1 = 0; true; ++v1) {
                long v3 = arr_v[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v5 = 0; v5 < v4; ++v5) {
                        if((0xFFL & v3) < 0x80L) {
                            Object object0 = arr_object[(v1 << 3) + v5];
                            v2 += (object0 == null ? 0 : object0.hashCode());
                        }
                        v3 >>= 8;
                    }
                    if(v4 != 8) {
                        return v2;
                    }
                }
                if(v1 == v) {
                    break;
                }
            }
            return v2;
        }
        return 0;
    }

    public final boolean isEmpty() {
        return this._size == 0;
    }

    public final boolean isNotEmpty() {
        return this._size != 0;
    }

    public final String joinToString() {
        return ScatterSet.joinToString$default(this, null, null, null, 0, null, null, 0x3F, null);
    }

    public final String joinToString(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        return ScatterSet.joinToString$default(this, charSequence0, null, null, 0, null, null, 62, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        return ScatterSet.joinToString$default(this, charSequence0, charSequence1, null, 0, null, null, 60, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return ScatterSet.joinToString$default(this, charSequence0, charSequence1, charSequence2, 0, null, null, 56, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return ScatterSet.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, null, null, 0x30, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        return ScatterSet.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, charSequence3, null, 0x20, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        Object[] arr_object = this.elements;
        long[] arr_v = this.metadata;
        int v1 = arr_v.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            int v3 = 0;
            while(true) {
                long v4 = arr_v[v2];
                if((~v4 << 7 & v4 & 0x8080808080808080L) == 0x8080808080808080L) {
                    goto label_35;
                }
                int v5 = 8 - (~(v2 - v1) >>> 0x1F);
                int v6 = 0;
                while(v6 < v5) {
                    if((v4 & 0xFFL) < 0x80L) {
                        Object object0 = arr_object[(v2 << 3) + v6];
                        if(v3 == v) {
                            stringBuilder0.append(charSequence3);
                            goto label_41;
                        }
                        if(v3 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        if(function10 == null) {
                            stringBuilder0.append(object0);
                        }
                        else {
                            stringBuilder0.append(((CharSequence)function10.invoke(object0)));
                        }
                        ++v3;
                    }
                    v4 >>= 8;
                    ++v6;
                }
                if(v5 == 8) {
                label_35:
                    if(v2 != v1) {
                        ++v2;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_41;
            }
        }
        stringBuilder0.append(charSequence2);
    label_41:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static String joinToString$default(ScatterSet scatterSet0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function1 function10, int v1, Object object0) {
        if(object0 != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: joinToString");
        }
        if((v1 & 1) != 0) {
            charSequence0 = ", ";
        }
        if((v1 & 2) != 0) {
            charSequence1 = "";
        }
        if((v1 & 4) != 0) {
            charSequence2 = "";
        }
        if((v1 & 8) != 0) {
            v = -1;
        }
        if((v1 & 16) != 0) {
            charSequence3 = "...";
        }
        if((v1 & 0x20) != 0) {
            function10 = null;
        }
        return scatterSet0.joinToString(charSequence0, charSequence1, charSequence2, v, charSequence3, function10);
    }

    public final boolean none() {
        return this._size == 0;
    }

    @Override
    public String toString() {
        return ScatterSet.joinToString$default(this, null, "[", "]", 0, null, new Function1() {
            {
                ScatterSet.this = scatterSet0;
                super(1);
            }

            public final CharSequence invoke(Object object0) {
                return object0 == ScatterSet.this ? "(this)" : String.valueOf(object0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        }, 25, null);
    }
}

