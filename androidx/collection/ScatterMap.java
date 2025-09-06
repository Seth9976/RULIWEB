package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\r\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003:\u0001KB\u0007\b\u0004\u00A2\u0006\u0002\u0010\u0004J&\u0010\u0016\u001A\u00020\u00172\u0018\u0010\u0018\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000J\u0006\u0010\u001A\u001A\u00020\u0017J&\u0010\u001A\u001A\u00020\u00172\u0018\u0010\u0018\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000J\r\u0010\u001B\u001A\u00020\u001CH\u0000\u00A2\u0006\u0002\b\u001DJ\u0012\u0010\u001E\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u001FJ\u0016\u0010 \u001A\u00020\u00172\u0006\u0010!\u001A\u00028\u0000H\u0086\u0002\u00A2\u0006\u0002\u0010\"J\u0013\u0010#\u001A\u00020\u00172\u0006\u0010!\u001A\u00028\u0000\u00A2\u0006\u0002\u0010\"J\u0013\u0010$\u001A\u00020\u00172\u0006\u0010%\u001A\u00028\u0001\u00A2\u0006\u0002\u0010\"J\u0006\u0010&\u001A\u00020\u0006J&\u0010&\u001A\u00020\u00062\u0018\u0010\u0018\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u00170\u0019H\u0086\b\u00F8\u0001\u0000J\u0013\u0010\'\u001A\u00020\u00172\b\u0010(\u001A\u0004\u0018\u00010\u0003H\u0096\u0002J\u0018\u0010)\u001A\u00020\u00062\u0006\u0010!\u001A\u00028\u0000H\u0080\b\u00A2\u0006\u0004\b*\u0010+JD\u0010,\u001A\u00020-26\u0010.\u001A2\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(!\u0012\u0013\u0012\u00118\u0001\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020-0\u0019H\u0086\b\u00F8\u0001\u0000J/\u00101\u001A\u00020-2!\u0010.\u001A\u001D\u0012\u0013\u0012\u00110\u0006\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(3\u0012\u0004\u0012\u00020-02H\u0081\b\u00F8\u0001\u0000J/\u00104\u001A\u00020-2!\u0010.\u001A\u001D\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(!\u0012\u0004\u0012\u00020-02H\u0086\b\u00F8\u0001\u0000J/\u00105\u001A\u00020-2!\u0010.\u001A\u001D\u0012\u0013\u0012\u00118\u0001\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020-02H\u0086\b\u00F8\u0001\u0000J\u0018\u00106\u001A\u0004\u0018\u00018\u00012\u0006\u0010!\u001A\u00028\u0000H\u0086\u0002\u00A2\u0006\u0002\u00107J\u001B\u00108\u001A\u00028\u00012\u0006\u0010!\u001A\u00028\u00002\u0006\u00109\u001A\u00028\u0001\u00A2\u0006\u0002\u0010:J\'\u0010;\u001A\u00028\u00012\u0006\u0010!\u001A\u00028\u00002\f\u00109\u001A\b\u0012\u0004\u0012\u00028\u00010<H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010=J\b\u0010>\u001A\u00020\u0006H\u0016J\u0006\u0010?\u001A\u00020\u0017J\u0006\u0010@\u001A\u00020\u0017Jv\u0010A\u001A\u00020\u001C2\b\b\u0002\u0010B\u001A\u00020C2\b\b\u0002\u0010D\u001A\u00020C2\b\b\u0002\u0010E\u001A\u00020C2\b\b\u0002\u0010F\u001A\u00020\u00062\b\b\u0002\u0010G\u001A\u00020C2:\b\u0002\u0010H\u001A4\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(!\u0012\u0013\u0012\u00118\u0001\u00A2\u0006\f\b/\u0012\b\b0\u0012\u0004\b\b(%\u0012\u0004\u0012\u00020C\u0018\u00010\u0019H\u0007J\u0006\u0010I\u001A\u00020\u0017J\b\u0010J\u001A\u00020\u001CH\u0016R\u0012\u0010\u0005\u001A\u00020\u00068\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001A\u00020\u00068\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u0011\u0010\b\u001A\u00020\u00068F\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\nR\"\u0010\u000B\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\f8\u0000@\u0000X\u0081\u000E\u00A2\u0006\n\n\u0002\u0010\u000E\u0012\u0004\b\r\u0010\u0004R\u0018\u0010\u000F\u001A\u00020\u00108\u0000@\u0000X\u0081\u000E\u00A2\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0004R\u0011\u0010\u0012\u001A\u00020\u00068F\u00A2\u0006\u0006\u001A\u0004\b\u0013\u0010\nR\"\u0010\u0014\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\f8\u0000@\u0000X\u0081\u000E\u00A2\u0006\n\n\u0002\u0010\u000E\u0012\u0004\b\u0015\u0010\u0004\u0082\u0001\u0001L\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006M"}, d2 = {"Landroidx/collection/ScatterMap;", "K", "V", "", "()V", "_capacity", "", "_size", "capacity", "getCapacity", "()I", "keys", "", "getKeys$annotations", "[Ljava/lang/Object;", "metadata", "", "getMetadata$annotations", "size", "getSize", "values", "getValues$annotations", "all", "", "predicate", "Lkotlin/Function2;", "any", "asDebugString", "", "asDebugString$collection", "asMap", "", "contains", "key", "(Ljava/lang/Object;)Z", "containsKey", "containsValue", "value", "count", "equals", "other", "findKeyIndex", "findKeyIndex$collection", "(Ljava/lang/Object;)I", "forEach", "", "block", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function1;", "index", "forEachKey", "forEachValue", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "hashCode", "isEmpty", "isNotEmpty", "joinToString", "separator", "", "prefix", "postfix", "limit", "truncated", "transform", "none", "toString", "MapWrapper", "Landroidx/collection/MutableScatterMap;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public abstract class ScatterMap {
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001E\n\u0002\b\u0003\n\u0002\u0010\u000B\n\u0002\b\b\b\u0090\u0004\u0018\u00002\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0012\u001A\u00020\u00132\u0006\u0010\u0014\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u0015\u0010\u0016\u001A\u00020\u00132\u0006\u0010\u0017\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0015J\u0018\u0010\u0018\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0014\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u0019J\b\u0010\u001A\u001A\u00020\u0013H\u0016R&\u0010\u0003\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0006\u0010\u0007R\u001A\u0010\b\u001A\b\u0012\u0004\u0012\u00028\u00000\u00048VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001A\u00020\u000B8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\f\u0010\rR\u001A\u0010\u000E\u001A\b\u0012\u0004\u0012\u00028\u00010\u000F8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0010\u0010\u0011¨\u0006\u001B"}, d2 = {"Landroidx/collection/ScatterMap$MapWrapper;", "", "(Landroidx/collection/ScatterMap;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "size", "", "getSize", "()I", "values", "", "getValues", "()Ljava/util/Collection;", "containsKey", "", "key", "(Ljava/lang/Object;)Z", "containsValue", "value", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "isEmpty", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    public class MapWrapper implements Map, KMappedMarker {
        @Override
        public void clear() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public Object compute(Object object0, BiFunction biFunction0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public Object computeIfAbsent(Object object0, Function function0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public Object computeIfPresent(Object object0, BiFunction biFunction0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public boolean containsKey(Object object0) {
            return ScatterMap.this.containsKey(object0);
        }

        @Override
        public boolean containsValue(Object object0) {
            return ScatterMap.this.containsValue(object0);
        }

        @Override
        public final Set entrySet() {
            return this.getEntries();
        }

        @Override
        public Object get(Object object0) {
            return ScatterMap.this.get(object0);
        }

        public Set getEntries() {
            return new Object() {
                @Override
                public boolean add(Object object0) {
                    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
                }

                public boolean add(Map.Entry map$Entry0) {
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
                public final boolean contains(Object object0) {
                    return object0 instanceof Map.Entry ? this.contains(((Map.Entry)object0)) : false;
                }

                public boolean contains(Map.Entry map$Entry0) {
                    Intrinsics.checkNotNullParameter(map$Entry0, "element");
                    Object object0 = map$Entry0.getKey();
                    return Intrinsics.areEqual(ScatterMap.this.get(object0), map$Entry0.getValue());
                }

                @Override
                public boolean containsAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    ScatterMap scatterMap0 = ScatterMap.this;
                    if(collection0.isEmpty()) {
                        return true;
                    }
                    for(Object object0: collection0) {
                        if(!Intrinsics.areEqual(scatterMap0.get(((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue())) {
                            return false;
                        }
                        if(false) {
                            break;
                        }
                    }
                    return true;
                }

                public int getSize() {
                    return ScatterMap.this._size;
                }

                @Override
                public boolean isEmpty() {
                    return ScatterMap.this.isEmpty();
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
                            ScatterMap.this = scatterMap0;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            androidx.collection.ScatterMap.MapWrapper.entries.1.iterator.1 scatterMap$MapWrapper$entries$1$iterator$10 = new androidx.collection.ScatterMap.MapWrapper.entries.1.iterator.1(ScatterMap.this, continuation0);
                            scatterMap$MapWrapper$entries$1$iterator$10.L$0 = object0;
                            return scatterMap$MapWrapper$entries$1$iterator$10;
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                        }

                        public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                            return ((androidx.collection.ScatterMap.MapWrapper.entries.1.iterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Object invokeSuspend(Object object0) {
                            int v1;
                            int v;
                            long[] arr_v;
                            ScatterMap scatterMap0;
                            SequenceScope sequenceScope0;
                            Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                            switch(this.label) {
                                case 0: {
                                    ResultKt.throwOnFailure(object0);
                                    sequenceScope0 = (SequenceScope)this.L$0;
                                    scatterMap0 = ScatterMap.this;
                                    arr_v = scatterMap0.metadata;
                                    v = arr_v.length - 2;
                                    if(v >= 0) {
                                        v1 = 0;
                                        goto label_44;
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
                                    ScatterMap scatterMap1 = (ScatterMap)this.L$1;
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
                                            int v6 = (v1 << 3) + v2;
                                            MapEntry mapEntry0 = new MapEntry(scatterMap1.keys[v6], scatterMap1.values[v6]);
                                            this.L$0 = sequenceScope1;
                                            this.L$1 = scatterMap1;
                                            this.L$2 = arr_v1;
                                            this.I$0 = v5;
                                            this.I$1 = v1;
                                            this.J$0 = v4;
                                            this.I$2 = v3;
                                            this.I$3 = v2;
                                            this.label = 1;
                                            if(sequenceScope1.yield(mapEntry0, this) != object1) {
                                                break;
                                            }
                                            return object1;
                                        }
                                    }
                                    if(v3 == 8) {
                                        v = v5;
                                        arr_v = arr_v1;
                                        scatterMap0 = scatterMap1;
                                        sequenceScope0 = sequenceScope1;
                                        while(true) {
                                            if(v1 == v) {
                                                break;
                                            }
                                            ++v1;
                                        label_44:
                                            long v7 = arr_v[v1];
                                            if((~v7 << 7 & v7 & 0x8080808080808080L) == 0x8080808080808080L) {
                                                continue;
                                            }
                                            scatterMap1 = scatterMap0;
                                            v3 = 8 - (~(v1 - v) >>> 0x1F);
                                            sequenceScope1 = sequenceScope0;
                                            v2 = 0;
                                            arr_v1 = arr_v;
                                            v5 = v;
                                            v4 = v7;
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
            };
        }

        public Set getKeys() {
            return new Object() {
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
                    return ScatterMap.this.containsKey(object0);
                }

                @Override
                public boolean containsAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    ScatterMap scatterMap0 = ScatterMap.this;
                    if(collection0.isEmpty()) {
                        return true;
                    }
                    for(Object object0: collection0) {
                        if(!scatterMap0.containsKey(object0)) {
                            return false;
                        }
                        if(false) {
                            break;
                        }
                    }
                    return true;
                }

                public int getSize() {
                    return ScatterMap.this._size;
                }

                @Override
                public boolean isEmpty() {
                    return ScatterMap.this.isEmpty();
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
                            ScatterMap.this = scatterMap0;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            androidx.collection.ScatterMap.MapWrapper.keys.1.iterator.1 scatterMap$MapWrapper$keys$1$iterator$10 = new androidx.collection.ScatterMap.MapWrapper.keys.1.iterator.1(ScatterMap.this, continuation0);
                            scatterMap$MapWrapper$keys$1$iterator$10.L$0 = object0;
                            return scatterMap$MapWrapper$keys$1$iterator$10;
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                        }

                        public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                            return ((androidx.collection.ScatterMap.MapWrapper.keys.1.iterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
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
                                    arr_object = ScatterMap.this.keys;
                                    arr_v = ScatterMap.this.metadata;
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
            };
        }

        public int getSize() {
            return ScatterMap.this._size;
        }

        public Collection getValues() {
            return new Object() {
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
                    return ScatterMap.this.containsValue(object0);
                }

                @Override
                public boolean containsAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    ScatterMap scatterMap0 = ScatterMap.this;
                    if(collection0.isEmpty()) {
                        return true;
                    }
                    for(Object object0: collection0) {
                        if(!scatterMap0.containsValue(object0)) {
                            return false;
                        }
                        if(false) {
                            break;
                        }
                    }
                    return true;
                }

                public int getSize() {
                    return ScatterMap.this._size;
                }

                @Override
                public boolean isEmpty() {
                    return ScatterMap.this.isEmpty();
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
                            ScatterMap.this = scatterMap0;
                            super(2, continuation0);
                        }

                        @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                        public final Continuation create(Object object0, Continuation continuation0) {
                            androidx.collection.ScatterMap.MapWrapper.values.1.iterator.1 scatterMap$MapWrapper$values$1$iterator$10 = new androidx.collection.ScatterMap.MapWrapper.values.1.iterator.1(ScatterMap.this, continuation0);
                            scatterMap$MapWrapper$values$1$iterator$10.L$0 = object0;
                            return scatterMap$MapWrapper$values$1$iterator$10;
                        }

                        @Override  // kotlin.jvm.functions.Function2
                        public Object invoke(Object object0, Object object1) {
                            return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                        }

                        public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                            return ((androidx.collection.ScatterMap.MapWrapper.values.1.iterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
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
                                    arr_object = ScatterMap.this.values;
                                    arr_v = ScatterMap.this.metadata;
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
                public boolean removeIf(Predicate predicate0) {
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
            };
        }

        @Override
        public boolean isEmpty() {
            return ScatterMap.this.isEmpty();
        }

        @Override
        public final Set keySet() {
            return this.getKeys();
        }

        @Override
        public Object merge(Object object0, Object object1, BiFunction biFunction0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public Object put(Object object0, Object object1) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public void putAll(Map map0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public Object putIfAbsent(Object object0, Object object1) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public Object remove(Object object0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public boolean remove(Object object0, Object object1) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public Object replace(Object object0, Object object1) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public boolean replace(Object object0, Object object1, Object object2) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public void replaceAll(BiFunction biFunction0) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        @Override
        public final int size() {
            return this.getSize();
        }

        @Override
        public final Collection values() {
            return this.getValues();
        }
    }

    public int _capacity;
    public int _size;
    public Object[] keys;
    public long[] metadata;
    public Object[] values;

    private ScatterMap() {
        this.metadata = ScatterMapKt.EmptyGroup;
        this.keys = ContainerHelpersKt.EMPTY_OBJECTS;
        this.values = ContainerHelpersKt.EMPTY_OBJECTS;
    }

    public ScatterMap(DefaultConstructorMarker defaultConstructorMarker0) {
    }

    public final boolean all(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "predicate");
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
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
                            if(!((Boolean)function20.invoke(arr_object[v5], arr_object1[v5])).booleanValue()) {
                                return false;
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_20;
                    }
                    break;
                }
            label_20:
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

    public final boolean any(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "predicate");
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
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
                            if(((Boolean)function20.invoke(arr_object[v5], arr_object1[v5])).booleanValue()) {
                                return true;
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_20;
                    }
                    break;
                }
            label_20:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return false;
    }

    public final String asDebugString$collection() {
        StringBuilder stringBuilder0 = new StringBuilder("{metadata=[");
        int v = this.getCapacity();
        for(int v2 = 0; v2 < v; ++v2) {
            long v3 = this.metadata[v2 >> 3] >> ((v2 & 7) << 3) & 0xFFL;
            if(v3 == 0x80L) {
                stringBuilder0.append("Empty");
            }
            else if(v3 == 0xFEL) {
                stringBuilder0.append("Deleted");
            }
            else {
                stringBuilder0.append(v3);
            }
            stringBuilder0.append(", ");
        }
        stringBuilder0.append("], keys=[");
        for(int v4 = 0; v4 < this.keys.length; ++v4) {
            stringBuilder0.append(this.keys[v4]);
            stringBuilder0.append(", ");
        }
        stringBuilder0.append("], values=[");
        for(int v1 = 0; v1 < this.values.length; ++v1) {
            stringBuilder0.append(this.values[v1]);
            stringBuilder0.append(", ");
        }
        stringBuilder0.append("]}");
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public final Map asMap() {
        return new MapWrapper(this);
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
                if(Intrinsics.areEqual(this.keys[v9], object0)) {
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

    public final boolean containsKey(Object object0) {
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
                if(Intrinsics.areEqual(this.keys[v9], object0)) {
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

    public final boolean containsValue(Object object0) {
        Object[] arr_object = this.values;
        long[] arr_v = this.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            while(true) {
                long v2 = arr_v[v1];
                if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v3 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v4 = 0; v4 < v3; ++v4) {
                        if((0xFFL & v2) < 0x80L && Intrinsics.areEqual(object0, arr_object[(v1 << 3) + v4])) {
                            return true;
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
        return false;
    }

    public final int count() {
        return this.getSize();
    }

    public final int count(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "predicate");
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
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
                            int v6 = (v1 << 3) + v5;
                            if(((Boolean)function20.invoke(arr_object[v6], arr_object1[v6])).booleanValue()) {
                                ++v2;
                            }
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
        if(!(object0 instanceof ScatterMap)) {
            return false;
        }
        if(((ScatterMap)object0).getSize() != this.getSize()) {
            return false;
        }
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
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
                            Object object1 = arr_object[v5];
                            Object object2 = arr_object1[v5];
                            if(object2 == null) {
                                if(((ScatterMap)object0).get(object1) != null || !((ScatterMap)object0).containsKey(object1)) {
                                    return false;
                                }
                            }
                            else if(!Intrinsics.areEqual(object2, ((ScatterMap)object0).get(object1))) {
                                return false;
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_30;
                    }
                    break;
                }
            label_30:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        return true;
    }

    public final int findKeyIndex$collection(Object object0) {
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
                if(Intrinsics.areEqual(this.keys[v10], object0)) {
                    return v10;
                }
            }
            if((v7 & ~v7 << 6 & 0x8080808080808080L) != 0L) {
                return -1;
            }
            v += 8;
        }
    }

    public final void forEach(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "block");
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
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
                            function20.invoke(arr_object[v5], arr_object1[v5]);
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

    public final void forEachIndexed(Function1 function10) {
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

    public final void forEachKey(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        Object[] arr_object = this.keys;
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

    public final void forEachValue(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "block");
        Object[] arr_object = this.values;
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

    public final Object get(Object object0) {
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
                if(Intrinsics.areEqual(this.keys[v10], object0)) {
                    return v10 < 0 ? null : this.values[v10];
                }
            }
            if((v7 & ~v7 << 6 & 0x8080808080808080L) != 0L) {
                throw new ArrayIndexOutOfBoundsException(-1);
            }
            v += 8;
        }
    }

    public final int getCapacity() {
        return this._capacity;
    }

    public static void getKeys$annotations() {
    }

    public static void getMetadata$annotations() {
    }

    public final Object getOrDefault(Object object0, Object object1) {
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
                if(Intrinsics.areEqual(this.keys[v10], object0)) {
                    return v10 < 0 ? object1 : this.values[v10];
                }
            }
            if((v7 & ~v7 << 6 & 0x8080808080808080L) != 0L) {
                throw new ArrayIndexOutOfBoundsException(-1);
            }
            v += 8;
        }
    }

    public final Object getOrElse(Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        Object object1 = this.get(object0);
        return object1 == null ? function00.invoke() : object1;
    }

    public final int getSize() {
        return this._size;
    }

    public static void getValues$annotations() {
    }

    @Override
    public int hashCode() {
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
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
                            int v6 = (v1 << 3) + v5;
                            Object object0 = arr_object[v6];
                            Object object1 = arr_object1[v6];
                            v2 += (object1 == null ? 0 : object1.hashCode()) ^ (object0 == null ? 0 : object0.hashCode());
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
        return ScatterMap.joinToString$default(this, null, null, null, 0, null, null, 0x3F, null);
    }

    public final String joinToString(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        return ScatterMap.joinToString$default(this, charSequence0, null, null, 0, null, null, 62, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        return ScatterMap.joinToString$default(this, charSequence0, charSequence1, null, 0, null, null, 60, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return ScatterMap.joinToString$default(this, charSequence0, charSequence1, charSequence2, 0, null, null, 56, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        return ScatterMap.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, null, null, 0x30, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3) {
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        return ScatterMap.joinToString$default(this, charSequence0, charSequence1, charSequence2, v, charSequence3, null, 0x20, null);
    }

    public final String joinToString(CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function2 function20) {
        Object[] arr_object5;
        Object[] arr_object4;
        Object[] arr_object3;
        Object[] arr_object2;
        Intrinsics.checkNotNullParameter(charSequence0, "separator");
        Intrinsics.checkNotNullParameter(charSequence1, "prefix");
        Intrinsics.checkNotNullParameter(charSequence2, "postfix");
        Intrinsics.checkNotNullParameter(charSequence3, "truncated");
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(charSequence1);
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
        long[] arr_v = this.metadata;
        int v1 = arr_v.length - 2;
        if(v1 >= 0) {
            int v2 = 0;
            int v3 = 0;
            while(true) {
                long v4 = arr_v[v2];
                int v5 = v2;
                if((~v4 << 7 & v4 & 0x8080808080808080L) == 0x8080808080808080L) {
                    arr_object4 = arr_object;
                    arr_object5 = arr_object1;
                    goto label_52;
                }
                int v6 = 8 - (~(v5 - v1) >>> 0x1F);
                int v7 = 0;
                while(v7 < v6) {
                    if((v4 & 0xFFL) < 0x80L) {
                        int v8 = (v5 << 3) + v7;
                        Object object0 = arr_object[v8];
                        arr_object2 = arr_object;
                        Object object1 = arr_object1[v8];
                        arr_object3 = arr_object1;
                        if(v3 == v) {
                            stringBuilder0.append(charSequence3);
                            goto label_60;
                        }
                        if(v3 != 0) {
                            stringBuilder0.append(charSequence0);
                        }
                        if(function20 == null) {
                            stringBuilder0.append(object0);
                            stringBuilder0.append('=');
                            stringBuilder0.append(object1);
                        }
                        else {
                            stringBuilder0.append(((CharSequence)function20.invoke(object0, object1)));
                        }
                        ++v3;
                    }
                    else {
                        arr_object2 = arr_object;
                        arr_object3 = arr_object1;
                    }
                    v4 >>= 8;
                    ++v7;
                    arr_object1 = arr_object3;
                    arr_object = arr_object2;
                }
                arr_object4 = arr_object;
                arr_object5 = arr_object1;
                if(v6 == 8) {
                label_52:
                    if(v5 != v1) {
                        v2 = v5 + 1;
                        arr_object1 = arr_object5;
                        arr_object = arr_object4;
                        continue;
                    }
                    break;
                }
                else {
                    stringBuilder0.append(charSequence2);
                }
                goto label_60;
            }
        }
        stringBuilder0.append(charSequence2);
    label_60:
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder().apply(builderAction).toString()");
        return s;
    }

    public static String joinToString$default(ScatterMap scatterMap0, CharSequence charSequence0, CharSequence charSequence1, CharSequence charSequence2, int v, CharSequence charSequence3, Function2 function20, int v1, Object object0) {
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
            function20 = null;
        }
        return scatterMap0.joinToString(charSequence0, charSequence1, charSequence2, v, charSequence3, function20);
    }

    public final boolean none() {
        return this._size == 0;
    }

    @Override
    public String toString() {
        if(this.isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder("{");
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
        long[] arr_v = this.metadata;
        int v = arr_v.length - 2;
        if(v >= 0) {
            int v1 = 0;
            int v2 = 0;
            while(true) {
                long v3 = arr_v[v1];
                if((~v3 << 7 & v3 & 0x8080808080808080L) != 0x8080808080808080L) {
                    int v4 = 8 - (~(v1 - v) >>> 0x1F);
                    for(int v5 = 0; v5 < v4; ++v5) {
                        if((0xFFL & v3) < 0x80L) {
                            int v6 = (v1 << 3) + v5;
                            String s = arr_object[v6];
                            String s1 = arr_object1[v6];
                            if(s == this) {
                                s = "(this)";
                            }
                            stringBuilder0.append(s);
                            stringBuilder0.append("=");
                            if(s1 == this) {
                                s1 = "(this)";
                            }
                            stringBuilder0.append(s1);
                            ++v2;
                            if(v2 < this._size) {
                                stringBuilder0.append(", ");
                            }
                        }
                        v3 >>= 8;
                    }
                    if(v4 == 8) {
                        goto label_33;
                    }
                    break;
                }
            label_33:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
        stringBuilder0.append('}');
        String s2 = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s2, "s.append(\'}\').toString()");
        return s2;
    }
}

