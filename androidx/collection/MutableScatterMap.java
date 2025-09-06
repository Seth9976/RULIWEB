package androidx.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.jvm.internal.markers.KMutableMap;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequenceScope;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001C\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0005\n\u0002\u0010\u000B\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0001EB\u000F\u0012\b\b\u0002\u0010\u0004\u001A\u00020\u0005\u00A2\u0006\u0002\u0010\u0006J\b\u0010\b\u001A\u00020\tH\u0002J\u0012\u0010\n\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000BJ\u0006\u0010\f\u001A\u00020\tJS\u0010\r\u001A\u00028\u00012\u0006\u0010\u000E\u001A\u00028\u000028\u0010\u000F\u001A4\u0012\u0013\u0012\u00118\u0000\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u000E\u0012\u0015\u0012\u0013\u0018\u00018\u0001\u00A2\u0006\f\b\u0011\u0012\b\b\u0012\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00028\u00010\u0010H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001A\u00020\u00052\u0006\u0010\u0016\u001A\u00020\u0005H\u0002J\u0015\u0010\u0017\u001A\u00020\u00052\u0006\u0010\u000E\u001A\u00028\u0000H\u0001\u00A2\u0006\u0002\u0010\u0018J\'\u0010\u0019\u001A\u00028\u00012\u0006\u0010\u000E\u001A\u00028\u00002\f\u0010\u001A\u001A\b\u0012\u0004\u0012\u00028\u00010\u001BH\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001CJ\b\u0010\u001D\u001A\u00020\tH\u0002J\u0010\u0010\u001E\u001A\u00020\t2\u0006\u0010\u001F\u001A\u00020\u0005H\u0002J\u0010\u0010 \u001A\u00020\t2\u0006\u0010\u0004\u001A\u00020\u0005H\u0002J\u0016\u0010!\u001A\u00020\t2\u0006\u0010\u000E\u001A\u00028\u0000H\u0086\n\u00A2\u0006\u0002\u0010\"J\u0017\u0010!\u001A\u00020\t2\f\u0010#\u001A\b\u0012\u0004\u0012\u00028\u00000$H\u0086\nJ\u0017\u0010!\u001A\u00020\t2\f\u0010#\u001A\b\u0012\u0004\u0012\u00028\u00000%H\u0086\nJ\u001E\u0010!\u001A\u00020\t2\u000E\u0010#\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000&H\u0086\n\u00A2\u0006\u0002\u0010\'J\u0017\u0010!\u001A\u00020\t2\f\u0010#\u001A\b\u0012\u0004\u0012\u00028\u00000(H\u0086\nJ\u0017\u0010!\u001A\u00020\t2\f\u0010#\u001A\b\u0012\u0004\u0012\u00028\u00000)H\u0086\nJ\u001D\u0010*\u001A\u00020\t2\u0012\u0010+\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003H\u0086\nJ*\u0010*\u001A\u00020\t2\u001A\u0010,\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-0&H\u0086\n\u00A2\u0006\u0002\u0010.J\u001D\u0010*\u001A\u00020\t2\u0012\u0010/\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-H\u0086\nJ#\u0010*\u001A\u00020\t2\u0018\u0010,\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-0(H\u0086\nJ\u001D\u0010*\u001A\u00020\t2\u0012\u0010+\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100H\u0086\nJ#\u0010*\u001A\u00020\t2\u0018\u0010,\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-0)H\u0086\nJ\u001D\u00101\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u000E\u001A\u00028\u00002\u0006\u0010\u0013\u001A\u00028\u0001\u00A2\u0006\u0002\u00102J\u001A\u00103\u001A\u00020\t2\u0012\u0010+\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003J\'\u00103\u001A\u00020\t2\u001A\u0010,\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-0&\u00A2\u0006\u0002\u0010.J \u00103\u001A\u00020\t2\u0018\u0010,\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-0(J\u001A\u00103\u001A\u00020\t2\u0012\u0010+\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u000100J \u00103\u001A\u00020\t2\u0018\u0010,\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010-0)J\u0015\u00104\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u000E\u001A\u00028\u0000\u00A2\u0006\u0002\u00105J\u001B\u00104\u001A\u0002062\u0006\u0010\u000E\u001A\u00028\u00002\u0006\u0010\u0013\u001A\u00028\u0001\u00A2\u0006\u0002\u00107J\b\u00108\u001A\u00020\tH\u0002J&\u00109\u001A\u00020\t2\u0018\u0010:\u001A\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002060\u0010H\u0086\b\u00F8\u0001\u0000J\u0017\u0010;\u001A\u0004\u0018\u00018\u00012\u0006\u0010<\u001A\u00020\u0005H\u0001\u00A2\u0006\u0002\u0010=J\u0010\u0010>\u001A\u00020\t2\u0006\u0010?\u001A\u00020\u0005H\u0002J\u001E\u0010@\u001A\u00020\t2\u0006\u0010\u000E\u001A\u00028\u00002\u0006\u0010\u0013\u001A\u00028\u0001H\u0086\u0002\u00A2\u0006\u0002\u0010AJ\u0006\u0010B\u001A\u00020\u0005J\u0019\u0010C\u001A\u00020\t2\u0006\u0010<\u001A\u00020\u00052\u0006\u0010\u0013\u001A\u00020DH\u0082\bR\u000E\u0010\u0007\u001A\u00020\u0005X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006F"}, d2 = {"Landroidx/collection/MutableScatterMap;", "K", "V", "Landroidx/collection/ScatterMap;", "initialCapacity", "", "(I)V", "growthLimit", "adjustStorage", "", "asMutableMap", "", "clear", "compute", "key", "computeBlock", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "value", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "findFirstAvailableSlot", "hash1", "findInsertIndex", "(Ljava/lang/Object;)I", "getOrPut", "defaultValue", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "initializeGrowth", "initializeMetadata", "capacity", "initializeStorage", "minusAssign", "(Ljava/lang/Object;)V", "keys", "Landroidx/collection/ObjectList;", "Landroidx/collection/ScatterSet;", "", "([Ljava/lang/Object;)V", "", "Lkotlin/sequences/Sequence;", "plusAssign", "from", "pairs", "Lkotlin/Pair;", "([Lkotlin/Pair;)V", "pair", "", "put", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "removeDeletedMarkers", "removeIf", "predicate", "removeValueAt", "index", "(I)Ljava/lang/Object;", "resizeStorage", "newCapacity", "set", "(Ljava/lang/Object;Ljava/lang/Object;)V", "trim", "writeMetadata", "", "MutableMapWrapper", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class MutableScatterMap extends ScatterMap {
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\'\n\u0002\b\u0005\n\u0002\u0010\u001F\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010$\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00120\u0001R\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00022\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001A\u00020\u0011H\u0016J\u001F\u0010\u0012\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001A\u00028\u00002\u0006\u0010\u0014\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0015J\u001E\u0010\u0016\u001A\u00020\u00112\u0014\u0010\u0017\u001A\u0010\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0018H\u0016J\u0017\u0010\u0019\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001AR&\u0010\u0005\u001A\u0014\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00070\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\b\u0010\tR\u001A\u0010\n\u001A\b\u0012\u0004\u0012\u00028\u00000\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000B\u0010\tR\u001A\u0010\f\u001A\b\u0012\u0004\u0012\u00028\u00010\r8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000F¨\u0006\u001B"}, d2 = {"Landroidx/collection/MutableScatterMap$MutableMapWrapper;", "Landroidx/collection/ScatterMap$MapWrapper;", "Landroidx/collection/ScatterMap;", "", "(Landroidx/collection/MutableScatterMap;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "keys", "getKeys", "values", "", "getValues", "()Ljava/util/Collection;", "clear", "", "put", "key", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "putAll", "from", "", "remove", "(Ljava/lang/Object;)Ljava/lang/Object;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class MutableMapWrapper extends MapWrapper implements Map, KMutableMap {
        @Override  // androidx.collection.ScatterMap$MapWrapper
        public void clear() {
            MutableScatterMap.this.clear();
        }

        @Override  // androidx.collection.ScatterMap$MapWrapper
        public Set getEntries() {
            return new Object() {
                @Override
                public boolean add(Object object0) {
                    return this.add(((Map.Entry)object0));
                }

                public boolean add(Map.Entry map$Entry0) {
                    Intrinsics.checkNotNullParameter(map$Entry0, "element");
                    throw new UnsupportedOperationException();
                }

                @Override
                public boolean addAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    throw new UnsupportedOperationException();
                }

                @Override
                public void clear() {
                    MutableScatterMap.this.clear();
                }

                @Override
                public final boolean contains(Object object0) {
                    return TypeIntrinsics.isMutableMapEntry(object0) ? this.contains(((Map.Entry)object0)) : false;
                }

                public boolean contains(Map.Entry map$Entry0) {
                    Intrinsics.checkNotNullParameter(map$Entry0, "element");
                    Object object0 = map$Entry0.getKey();
                    return Intrinsics.areEqual(MutableScatterMap.this.get(object0), map$Entry0.getValue());
                }

                @Override
                public boolean containsAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    if(collection0.isEmpty()) {
                        return true;
                    }
                    for(Object object0: collection0) {
                        if(!Intrinsics.areEqual(mutableScatterMap0.get(((Map.Entry)object0).getKey()), ((Map.Entry)object0).getValue())) {
                            return false;
                        }
                        if(false) {
                            break;
                        }
                    }
                    return true;
                }

                public int getSize() {
                    return MutableScatterMap.this._size;
                }

                @Override
                public boolean isEmpty() {
                    return MutableScatterMap.this.isEmpty();
                }

                @Override
                public Iterator iterator() {
                    return new Object() {
                        private int current;
                        private Iterator iterator;

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
                                    MutableScatterMap.this = mutableScatterMap0;
                                    androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1.this = mutableScatterMap$MutableMapWrapper$entries$1$iterator$10;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1.1 mutableScatterMap$MutableMapWrapper$entries$1$iterator$1$10 = new androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1.1(MutableScatterMap.this, androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1.this, continuation0);
                                    mutableScatterMap$MutableMapWrapper$entries$1$iterator$1$10.L$0 = object0;
                                    return mutableScatterMap$MutableMapWrapper$entries$1$iterator$1$10;
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                                    return ((androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    int v1;
                                    int v;
                                    long[] arr_v;
                                    androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1 mutableScatterMap$MutableMapWrapper$entries$1$iterator$10;
                                    MutableScatterMap mutableScatterMap0;
                                    SequenceScope sequenceScope0;
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            sequenceScope0 = (SequenceScope)this.L$0;
                                            mutableScatterMap0 = MutableScatterMap.this;
                                            mutableScatterMap$MutableMapWrapper$entries$1$iterator$10 = androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1.this;
                                            arr_v = mutableScatterMap0.metadata;
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
                                            MutableScatterMap mutableScatterMap1 = (MutableScatterMap)this.L$2;
                                            androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1 mutableScatterMap$MutableMapWrapper$entries$1$iterator$11 = (androidx.collection.MutableScatterMap.MutableMapWrapper.entries.1.iterator.1)this.L$1;
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
                                                    mutableScatterMap$MutableMapWrapper$entries$1$iterator$11.setCurrent((v5 << 3) + v2);
                                                    MutableMapEntry mutableMapEntry0 = new MutableMapEntry(mutableScatterMap1.keys, mutableScatterMap1.values, mutableScatterMap$MutableMapWrapper$entries$1$iterator$11.getCurrent());
                                                    this.L$0 = sequenceScope1;
                                                    this.L$1 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$11;
                                                    this.L$2 = mutableScatterMap1;
                                                    this.L$3 = arr_v1;
                                                    this.I$0 = v6;
                                                    this.I$1 = v5;
                                                    this.J$0 = v4;
                                                    this.I$2 = v3;
                                                    this.I$3 = v2;
                                                    this.label = 1;
                                                    if(sequenceScope1.yield(mutableMapEntry0, this) != object1) {
                                                        break;
                                                    }
                                                    return object1;
                                                }
                                            }
                                            if(v3 == 8) {
                                                v1 = v5;
                                                v = v6;
                                                arr_v = arr_v1;
                                                mutableScatterMap0 = mutableScatterMap1;
                                                mutableScatterMap$MutableMapWrapper$entries$1$iterator$10 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$11;
                                                sequenceScope0 = sequenceScope1;
                                                while(true) {
                                                    if(v1 == v) {
                                                        break;
                                                    }
                                                    ++v1;
                                                label_49:
                                                    long v7 = arr_v[v1];
                                                    if((~v7 << 7 & v7 & 0x8080808080808080L) == 0x8080808080808080L) {
                                                        continue;
                                                    }
                                                    v6 = v;
                                                    v5 = v1;
                                                    sequenceScope1 = sequenceScope0;
                                                    v2 = 0;
                                                    mutableScatterMap1 = mutableScatterMap0;
                                                    arr_v1 = arr_v;
                                                    v3 = 8 - (~(v1 - v) >>> 0x1F);
                                                    mutableScatterMap$MutableMapWrapper$entries$1$iterator$11 = mutableScatterMap$MutableMapWrapper$entries$1$iterator$10;
                                                    v4 = v7;
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
                            return this.next();
                        }

                        public Map.Entry next() {
                            return this.iterator.next();
                        }

                        @Override
                        public void remove() {
                            int v = this.current;
                            if(v != -1) {
                                MutableScatterMap.this.removeValueAt(v);
                                this.current = -1;
                            }
                        }

                        public final void setCurrent(int v) {
                            this.current = v;
                        }

                        public final void setIterator(Iterator iterator0) {
                            Intrinsics.checkNotNullParameter(iterator0, "<set-?>");
                            this.iterator = iterator0;
                        }
                    };
                }

                @Override
                public final boolean remove(Object object0) {
                    return TypeIntrinsics.isMutableMapEntry(object0) ? this.remove(((Map.Entry)object0)) : false;
                }

                public boolean remove(Map.Entry map$Entry0) {
                    int v11;
                    Intrinsics.checkNotNullParameter(map$Entry0, "element");
                    ScatterMap scatterMap0 = MutableScatterMap.this;
                    Object object0 = map$Entry0.getKey();
                    int v = object0 == null ? 0 : object0.hashCode();
                    int v1 = v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
                    int v2 = scatterMap0._capacity;
                    int v3 = v1 >>> 7 & v2;
                    int v4 = 0;
                    while(true) {
                        int v5 = (v3 & 7) << 3;
                        int v6 = v3;
                        long v7 = -((long)v5) >> 0x3F & scatterMap0.metadata[(v3 >> 3) + 1] << 0x40 - v5 | scatterMap0.metadata[v3 >> 3] >>> v5;
                        long v8 = ((long)(v1 & 0x7F)) * 0x101010101010101L ^ v7;
                        long v9 = ~v8 & v8 - 0x101010101010101L & 0x8080808080808080L;
                        while(v9 != 0L) {
                            int v10 = v6 + (Long.numberOfTrailingZeros(v9) >> 3) & v2;
                            if(Intrinsics.areEqual(scatterMap0.keys[v10], object0)) {
                                v11 = v10;
                                goto label_22;
                            }
                            v9 &= v9 - 1L;
                        }
                        if((v7 & ~v7 << 6 & 0x8080808080808080L) == 0L) {
                            goto label_26;
                        }
                        else {
                            v11 = -1;
                        }
                    label_22:
                        if(v11 >= 0 && Intrinsics.areEqual(MutableScatterMap.this.values[v11], map$Entry0.getValue())) {
                            MutableScatterMap.this.removeValueAt(v11);
                            return true;
                        }
                        return false;
                    label_26:
                        v4 += 8;
                        v3 = v6 + v4 & v2;
                    }
                }

                @Override
                public boolean removeAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    long[] arr_v = mutableScatterMap0.metadata;
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
                                        for(Object object0: collection0) {
                                            if(Intrinsics.areEqual(((Map.Entry)object0).getKey(), mutableScatterMap0.keys[v5]) && Intrinsics.areEqual(((Map.Entry)object0).getValue(), mutableScatterMap0.values[v5])) {
                                                mutableScatterMap0.removeValueAt(v5);
                                                z = true;
                                                break;
                                            }
                                            if(false) {
                                                break;
                                            }
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

                @Override
                public boolean retainAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    long[] arr_v = mutableScatterMap0.metadata;
                    int v = arr_v.length - 2;
                    if(v >= 0) {
                        int v1 = 0;
                        boolean z = false;
                        while(true) {
                            long v2 = arr_v[v1];
                            if((~v2 << 7 & v2 & 0x8080808080808080L) != 0x8080808080808080L) {
                                int v3 = 8 - (~(v1 - v) >>> 0x1F);
                                int v4 = 0;
                                while(v4 < v3) {
                                    if((0xFFL & v2) < 0x80L) {
                                        int v5 = (v1 << 3) + v4;
                                        Iterator iterator0 = collection0.iterator();
                                        while(true) {
                                            if(iterator0.hasNext()) {
                                                Object object0 = iterator0.next();
                                                if(Intrinsics.areEqual(((Map.Entry)object0).getKey(), mutableScatterMap0.keys[v5]) && Intrinsics.areEqual(((Map.Entry)object0).getValue(), mutableScatterMap0.values[v5])) {
                                                    break;
                                                }
                                                else {
                                                    continue;
                                                }
                                            }
                                            mutableScatterMap0.removeValueAt(v5);
                                            z = true;
                                            break;
                                        }
                                    }
                                    v2 >>= 8;
                                    ++v4;
                                }
                                if(v3 != 8) {
                                    return z;
                                }
                            }
                            if(v1 == v) {
                                break;
                            }
                            ++v1;
                        }
                        return z;
                    }
                    return false;
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

        @Override  // androidx.collection.ScatterMap$MapWrapper
        public Set getKeys() {
            return new Object() {
                @Override
                public boolean add(Object object0) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public boolean addAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    throw new UnsupportedOperationException();
                }

                @Override
                public void clear() {
                    MutableScatterMap.this.clear();
                }

                @Override
                public boolean contains(Object object0) {
                    return MutableScatterMap.this.containsKey(object0);
                }

                @Override
                public boolean containsAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    if(collection0.isEmpty()) {
                        return true;
                    }
                    for(Object object0: collection0) {
                        if(!mutableScatterMap0.containsKey(object0)) {
                            return false;
                        }
                        if(false) {
                            break;
                        }
                    }
                    return true;
                }

                public int getSize() {
                    return MutableScatterMap.this._size;
                }

                @Override
                public boolean isEmpty() {
                    return MutableScatterMap.this.isEmpty();
                }

                @Override
                public Iterator iterator() {
                    return new Object() {
                        private int current;
                        private final Iterator iterator;

                        {
                            this.iterator = SequencesKt.iterator(new Function2(null) {
                                int I$0;
                                int I$1;
                                int I$2;
                                int I$3;
                                long J$0;
                                private Object L$0;
                                Object L$1;
                                int label;

                                {
                                    MutableScatterMap.this = mutableScatterMap0;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    androidx.collection.MutableScatterMap.MutableMapWrapper.keys.1.iterator.1.iterator.1 mutableScatterMap$MutableMapWrapper$keys$1$iterator$1$iterator$10 = new androidx.collection.MutableScatterMap.MutableMapWrapper.keys.1.iterator.1.iterator.1(MutableScatterMap.this, continuation0);
                                    mutableScatterMap$MutableMapWrapper$keys$1$iterator$1$iterator$10.L$0 = object0;
                                    return mutableScatterMap$MutableMapWrapper$keys$1$iterator$1$iterator$10;
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                                    return ((androidx.collection.MutableScatterMap.MutableMapWrapper.keys.1.iterator.1.iterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    int v1;
                                    int v;
                                    long[] arr_v;
                                    SequenceScope sequenceScope0;
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            sequenceScope0 = (SequenceScope)this.L$0;
                                            arr_v = MutableScatterMap.this.metadata;
                                            v = arr_v.length - 2;
                                            if(v >= 0) {
                                                v1 = 0;
                                                goto label_39;
                                            }
                                            break;
                                        }
                                        case 1: {
                                            int v2 = this.I$3;
                                            int v3 = this.I$2;
                                            long v4 = this.J$0;
                                            int v5 = this.I$1;
                                            int v6 = this.I$0;
                                            long[] arr_v1 = (long[])this.L$1;
                                            SequenceScope sequenceScope1 = (SequenceScope)this.L$0;
                                            ResultKt.throwOnFailure(object0);
                                        alab1:
                                            while(true) {
                                                while(true) {
                                                    v4 >>= 8;
                                                    ++v2;
                                                label_20:
                                                    if(v2 >= v3) {
                                                        break alab1;
                                                    }
                                                    if((0xFFL & v4) >= 0x80L) {
                                                        break;
                                                    }
                                                    this.L$0 = sequenceScope1;
                                                    this.L$1 = arr_v1;
                                                    this.I$0 = v6;
                                                    this.I$1 = v5;
                                                    this.J$0 = v4;
                                                    this.I$2 = v3;
                                                    this.I$3 = v2;
                                                    this.label = 1;
                                                    if(sequenceScope1.yield(Boxing.boxInt((v5 << 3) + v2), this) != object1) {
                                                        break;
                                                    }
                                                    return object1;
                                                }
                                            }
                                            if(v3 == 8) {
                                                v1 = v5;
                                                v = v6;
                                                arr_v = arr_v1;
                                                sequenceScope0 = sequenceScope1;
                                                while(true) {
                                                    if(v1 == v) {
                                                        break;
                                                    }
                                                    ++v1;
                                                label_39:
                                                    long v7 = arr_v[v1];
                                                    if((~v7 << 7 & v7 & 0x8080808080808080L) == 0x8080808080808080L) {
                                                        continue;
                                                    }
                                                    arr_v1 = arr_v;
                                                    v3 = 8 - (~(v1 - v) >>> 0x1F);
                                                    sequenceScope1 = sequenceScope0;
                                                    v2 = 0;
                                                    v6 = v;
                                                    v5 = v1;
                                                    v4 = v7;
                                                    goto label_20;
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
                            this.current = -1;
                        }

                        @Override
                        public boolean hasNext() {
                            return this.iterator.hasNext();
                        }

                        @Override
                        public Object next() {
                            Object object0 = this.iterator.next();
                            this.current = ((Number)object0).intValue();
                            return MutableScatterMap.this.keys[this.current];
                        }

                        @Override
                        public void remove() {
                            int v = this.current;
                            if(v >= 0) {
                                MutableScatterMap.this.removeValueAt(v);
                                this.current = -1;
                            }
                        }
                    };
                }

                @Override
                public boolean remove(Object object0) {
                    int v10;
                    ScatterMap scatterMap0 = MutableScatterMap.this;
                    int v = object0 == null ? 0 : object0.hashCode();
                    int v1 = v * 0xCC9E2D51 ^ v * 0xCC9E2D51 << 16;
                    int v2 = scatterMap0._capacity;
                    int v3 = v1 >>> 7 & v2;
                    int v4 = 0;
                    while(true) {
                        int v5 = (v3 & 7) << 3;
                        long v6 = scatterMap0.metadata[(v3 >> 3) + 1] << 0x40 - v5 & -((long)v5) >> 0x3F | scatterMap0.metadata[v3 >> 3] >>> v5;
                        long v7 = ((long)(v1 & 0x7F)) * 0x101010101010101L ^ v6;
                        long v8 = ~v7 & v7 - 0x101010101010101L & 0x8080808080808080L;
                        while(v8 != 0L) {
                            int v9 = (Long.numberOfTrailingZeros(v8) >> 3) + v3 & v2;
                            if(Intrinsics.areEqual(scatterMap0.keys[v9], object0)) {
                                v10 = v9;
                                goto label_19;
                            }
                            v8 &= v8 - 1L;
                        }
                        if((v6 & ~v6 << 6 & 0x8080808080808080L) == 0L) {
                            goto label_23;
                        }
                        else {
                            v10 = -1;
                        }
                    label_19:
                        if(v10 >= 0) {
                            MutableScatterMap.this.removeValueAt(v10);
                            return true;
                        }
                        return false;
                    label_23:
                        v4 += 8;
                        v3 = v3 + v4 & v2;
                    }
                }

                @Override
                public boolean removeAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    long[] arr_v = mutableScatterMap0.metadata;
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
                                        if(CollectionsKt.contains(collection0, mutableScatterMap0.keys[v5])) {
                                            mutableScatterMap0.removeValueAt(v5);
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

                @Override
                public boolean retainAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    long[] arr_v = mutableScatterMap0.metadata;
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
                                        if(!CollectionsKt.contains(collection0, mutableScatterMap0.keys[v5])) {
                                            mutableScatterMap0.removeValueAt(v5);
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

        @Override  // androidx.collection.ScatterMap$MapWrapper
        public Collection getValues() {
            return new Object() {
                @Override
                public boolean add(Object object0) {
                    throw new UnsupportedOperationException();
                }

                @Override
                public boolean addAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    throw new UnsupportedOperationException();
                }

                @Override
                public void clear() {
                    MutableScatterMap.this.clear();
                }

                @Override
                public boolean contains(Object object0) {
                    return MutableScatterMap.this.containsValue(object0);
                }

                @Override
                public boolean containsAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    if(collection0.isEmpty()) {
                        return true;
                    }
                    for(Object object0: collection0) {
                        if(!mutableScatterMap0.containsValue(object0)) {
                            return false;
                        }
                        if(false) {
                            break;
                        }
                    }
                    return true;
                }

                public int getSize() {
                    return MutableScatterMap.this._size;
                }

                @Override
                public boolean isEmpty() {
                    return MutableScatterMap.this.isEmpty();
                }

                @Override
                public Iterator iterator() {
                    return new Object() {
                        private int current;
                        private final Iterator iterator;

                        {
                            this.iterator = SequencesKt.iterator(new Function2(null) {
                                int I$0;
                                int I$1;
                                int I$2;
                                int I$3;
                                long J$0;
                                private Object L$0;
                                Object L$1;
                                int label;

                                {
                                    MutableScatterMap.this = mutableScatterMap0;
                                    super(2, continuation0);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Continuation create(Object object0, Continuation continuation0) {
                                    androidx.collection.MutableScatterMap.MutableMapWrapper.values.1.iterator.1.iterator.1 mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$10 = new androidx.collection.MutableScatterMap.MutableMapWrapper.values.1.iterator.1.iterator.1(MutableScatterMap.this, continuation0);
                                    mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$10.L$0 = object0;
                                    return mutableScatterMap$MutableMapWrapper$values$1$iterator$1$iterator$10;
                                }

                                @Override  // kotlin.jvm.functions.Function2
                                public Object invoke(Object object0, Object object1) {
                                    return this.invoke(((SequenceScope)object0), ((Continuation)object1));
                                }

                                public final Object invoke(SequenceScope sequenceScope0, Continuation continuation0) {
                                    return ((androidx.collection.MutableScatterMap.MutableMapWrapper.values.1.iterator.1.iterator.1)this.create(sequenceScope0, continuation0)).invokeSuspend(Unit.INSTANCE);
                                }

                                @Override  // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                                public final Object invokeSuspend(Object object0) {
                                    int v1;
                                    int v;
                                    long[] arr_v;
                                    SequenceScope sequenceScope0;
                                    Object object1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                                    switch(this.label) {
                                        case 0: {
                                            ResultKt.throwOnFailure(object0);
                                            sequenceScope0 = (SequenceScope)this.L$0;
                                            arr_v = MutableScatterMap.this.metadata;
                                            v = arr_v.length - 2;
                                            if(v >= 0) {
                                                v1 = 0;
                                                goto label_39;
                                            }
                                            break;
                                        }
                                        case 1: {
                                            int v2 = this.I$3;
                                            int v3 = this.I$2;
                                            long v4 = this.J$0;
                                            int v5 = this.I$1;
                                            int v6 = this.I$0;
                                            long[] arr_v1 = (long[])this.L$1;
                                            SequenceScope sequenceScope1 = (SequenceScope)this.L$0;
                                            ResultKt.throwOnFailure(object0);
                                        alab1:
                                            while(true) {
                                                while(true) {
                                                    v4 >>= 8;
                                                    ++v2;
                                                label_20:
                                                    if(v2 >= v3) {
                                                        break alab1;
                                                    }
                                                    if((0xFFL & v4) >= 0x80L) {
                                                        break;
                                                    }
                                                    this.L$0 = sequenceScope1;
                                                    this.L$1 = arr_v1;
                                                    this.I$0 = v6;
                                                    this.I$1 = v5;
                                                    this.J$0 = v4;
                                                    this.I$2 = v3;
                                                    this.I$3 = v2;
                                                    this.label = 1;
                                                    if(sequenceScope1.yield(Boxing.boxInt((v5 << 3) + v2), this) != object1) {
                                                        break;
                                                    }
                                                    return object1;
                                                }
                                            }
                                            if(v3 == 8) {
                                                v1 = v5;
                                                v = v6;
                                                arr_v = arr_v1;
                                                sequenceScope0 = sequenceScope1;
                                                while(true) {
                                                    if(v1 == v) {
                                                        break;
                                                    }
                                                    ++v1;
                                                label_39:
                                                    long v7 = arr_v[v1];
                                                    if((~v7 << 7 & v7 & 0x8080808080808080L) == 0x8080808080808080L) {
                                                        continue;
                                                    }
                                                    arr_v1 = arr_v;
                                                    v3 = 8 - (~(v1 - v) >>> 0x1F);
                                                    sequenceScope1 = sequenceScope0;
                                                    v2 = 0;
                                                    v6 = v;
                                                    v5 = v1;
                                                    v4 = v7;
                                                    goto label_20;
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
                            this.current = -1;
                        }

                        @Override
                        public boolean hasNext() {
                            return this.iterator.hasNext();
                        }

                        @Override
                        public Object next() {
                            Object object0 = this.iterator.next();
                            this.current = ((Number)object0).intValue();
                            return MutableScatterMap.this.values[this.current];
                        }

                        @Override
                        public void remove() {
                            int v = this.current;
                            if(v >= 0) {
                                MutableScatterMap.this.removeValueAt(v);
                                this.current = -1;
                            }
                        }
                    };
                }

                @Override
                public boolean remove(Object object0) {
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    long[] arr_v = mutableScatterMap0.metadata;
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
                                        if(Intrinsics.areEqual(mutableScatterMap0.values[v5], object0)) {
                                            mutableScatterMap0.removeValueAt(v5);
                                            return true;
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
                    return false;
                }

                @Override
                public boolean removeAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    long[] arr_v = mutableScatterMap0.metadata;
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
                                        if(CollectionsKt.contains(collection0, mutableScatterMap0.values[v5])) {
                                            mutableScatterMap0.removeValueAt(v5);
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

                @Override
                public boolean retainAll(Collection collection0) {
                    Intrinsics.checkNotNullParameter(collection0, "elements");
                    MutableScatterMap mutableScatterMap0 = MutableScatterMap.this;
                    long[] arr_v = mutableScatterMap0.metadata;
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
                                        if(!CollectionsKt.contains(collection0, mutableScatterMap0.values[v5])) {
                                            mutableScatterMap0.removeValueAt(v5);
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

        @Override  // androidx.collection.ScatterMap$MapWrapper
        public Object put(Object object0, Object object1) {
            return MutableScatterMap.this.put(object0, object1);
        }

        @Override  // androidx.collection.ScatterMap$MapWrapper
        public void putAll(Map map0) {
            Intrinsics.checkNotNullParameter(map0, "from");
            for(Object object0: map0.entrySet()) {
                this.put(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
            }
        }

        @Override  // androidx.collection.ScatterMap$MapWrapper
        public Object remove(Object object0) {
            return MutableScatterMap.this.remove(object0);
        }
    }

    private int growthLimit;

    public MutableScatterMap() {
        this(0, 1, null);
    }

    public MutableScatterMap(int v) {
        super(null);
        if(v < 0) {
            throw new IllegalArgumentException("Capacity must be a positive value.");
        }
        this.initializeStorage(ScatterMapKt.unloadedCapacity(v));
    }

    public MutableScatterMap(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 6;
        }
        this(v);
    }

    private final void adjustStorage() {
        if(this._capacity > 8 && UByte..ExternalSyntheticBackport0.m(((long)this._size) * 0x20L, ((long)this._capacity) * 25L) <= 0) {
            this.resizeStorage(this._capacity);
            return;
        }
        this.resizeStorage(ScatterMapKt.nextCapacity(this._capacity));
    }

    public final Map asMutableMap() {
        return new MutableMapWrapper(this);
    }

    public final void clear() {
        this._size = 0;
        if(this.metadata != ScatterMapKt.EmptyGroup) {
            ArraysKt.fill$default(this.metadata, 0x8080808080808080L, 0, 0, 6, null);
            int v = this._capacity >> 3;
            this.metadata[v] |= 0xFFL << ((this._capacity & 7) << 3);
        }
        ArraysKt.fill(this.values, null, 0, this._capacity);
        ArraysKt.fill(this.keys, null, 0, this._capacity);
        this.initializeGrowth();
    }

    public final Object compute(Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "computeBlock");
        int v = this.findInsertIndex(object0);
        Object object1 = function20.invoke(object0, (v >= 0 ? null : this.values[v]));
        if(v < 0) {
            this.keys[~v] = object0;
            this.values[~v] = object1;
            return object1;
        }
        this.values[v] = object1;
        return object1;
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

    public final int findInsertIndex(Object object0) {
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
                if(Intrinsics.areEqual(this.keys[v9], object0)) {
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
                return ~v10;
            }
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
    }

    public final Object getOrPut(Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        Object object1 = this.get(object0);
        if(object1 == null) {
            Object object2 = function00.invoke();
            this.set(object0, object2);
            return object2;
        }
        return object1;
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
        this.keys = new Object[v1];
        this.values = new Object[v1];
    }

    public final void minusAssign(ObjectList objectList0) {
        Intrinsics.checkNotNullParameter(objectList0, "keys");
        Object[] arr_object = objectList0.content;
        int v = objectList0._size;
        for(int v1 = 0; v1 < v; ++v1) {
            this.remove(arr_object[v1]);
        }
    }

    public final void minusAssign(ScatterSet scatterSet0) {
        Intrinsics.checkNotNullParameter(scatterSet0, "keys");
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
                            this.remove(arr_object[(v1 << 3) + v4]);
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
        Intrinsics.checkNotNullParameter(iterable0, "keys");
        for(Object object0: iterable0) {
            this.remove(object0);
        }
    }

    public final void minusAssign(Object object0) {
        this.remove(object0);
    }

    public final void minusAssign(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "keys");
        for(Object object0: sequence0) {
            this.remove(object0);
        }
    }

    public final void minusAssign(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "keys");
        for(int v = 0; v < arr_object.length; ++v) {
            this.remove(arr_object[v]);
        }
    }

    public final void plusAssign(ScatterMap scatterMap0) {
        Intrinsics.checkNotNullParameter(scatterMap0, "from");
        this.putAll(scatterMap0);
    }

    public final void plusAssign(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "pairs");
        this.putAll(iterable0);
    }

    public final void plusAssign(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "from");
        this.putAll(map0);
    }

    public final void plusAssign(Pair pair0) {
        Intrinsics.checkNotNullParameter(pair0, "pair");
        this.set(pair0.getFirst(), pair0.getSecond());
    }

    public final void plusAssign(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "pairs");
        this.putAll(sequence0);
    }

    public final void plusAssign(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        this.putAll(arr_pair);
    }

    public final Object put(Object object0, Object object1) {
        int v = this.findInsertIndex(object0);
        if(v < 0) {
            v = ~v;
        }
        Object object2 = this.values[v];
        this.keys[v] = object0;
        this.values[v] = object1;
        return object2;
    }

    public final void putAll(ScatterMap scatterMap0) {
        Intrinsics.checkNotNullParameter(scatterMap0, "from");
        Object[] arr_object = scatterMap0.keys;
        Object[] arr_object1 = scatterMap0.values;
        long[] arr_v = scatterMap0.metadata;
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
                            this.set(arr_object[v5], arr_object1[v5]);
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

    public final void putAll(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "pairs");
        for(Object object0: iterable0) {
            this.set(((Pair)object0).component1(), ((Pair)object0).component2());
        }
    }

    public final void putAll(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "from");
        for(Object object0: map0.entrySet()) {
            this.set(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
        }
    }

    public final void putAll(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "pairs");
        for(Object object0: sequence0) {
            this.set(((Pair)object0).component1(), ((Pair)object0).component2());
        }
    }

    public final void putAll(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        for(int v = 0; v < arr_pair.length; ++v) {
            Pair pair0 = arr_pair[v];
            this.set(pair0.component1(), pair0.component2());
        }
    }

    public final Object remove(Object object0) {
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
                    return v10 < 0 ? null : this.removeValueAt(v10);
                }
            }
            if((v7 & ~v7 << 6 & 0x8080808080808080L) != 0L) {
                return null;
            }
            v += 8;
        }
    }

    public final boolean remove(Object object0, Object object1) {
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
                if(Intrinsics.areEqual(this.keys[v9], object0)) {
                    v10 = v9;
                    goto label_18;
                }
                v8 &= v8 - 1L;
            }
            if((v6 & ~v6 << 6 & 0x8080808080808080L) == 0L) {
                goto label_22;
            }
            else {
                v10 = -1;
            }
        label_18:
            if(v10 >= 0 && Intrinsics.areEqual(this.values[v10], object1)) {
                this.removeValueAt(v10);
                return true;
            }
            return false;
        label_22:
            v4 += 8;
            v3 = v3 + v4 & v2;
        }
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

    public final void removeIf(Function2 function20) {
        Intrinsics.checkNotNullParameter(function20, "predicate");
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
                            if(((Boolean)function20.invoke(this.keys[v5], this.values[v5])).booleanValue()) {
                                this.removeValueAt(v5);
                            }
                        }
                        v2 >>= 8;
                    }
                    if(v3 == 8) {
                        goto label_18;
                    }
                    break;
                }
            label_18:
                if(v1 == v) {
                    break;
                }
                ++v1;
            }
        }
    }

    public final Object removeValueAt(int v) {
        --this._size;
        long[] arr_v = this.metadata;
        int v1 = (v & 7) << 3;
        arr_v[v >> 3] = arr_v[v >> 3] & ~(0xFFL << v1) | 0xFEL << v1;
        int v2 = (v - 7 & this._capacity) + (this._capacity & 7);
        int v3 = (v2 & 7) << 3;
        arr_v[v2 >> 3] = arr_v[v2 >> 3] & ~(0xFFL << v3) | 0xFEL << v3;
        this.keys[v] = null;
        Object object0 = this.values[v];
        this.values[v] = null;
        return object0;
    }

    private final void resizeStorage(int v) {
        int v7;
        long[] arr_v = this.metadata;
        Object[] arr_object = this.keys;
        Object[] arr_object1 = this.values;
        int v1 = this._capacity;
        this.initializeStorage(v);
        Object[] arr_object2 = this.keys;
        Object[] arr_object3 = this.values;
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
                arr_v1[v8 >> 3] = arr_v1[v8 >> 3] & ~(0xFFL << v9) | ((long)(v4 & 0x7F)) << v9;
                arr_object2[v5] = object0;
                arr_object3[v5] = arr_object1[v7];
            }
            else {
                v7 = v2;
            }
        }
    }

    public final void set(Object object0, Object object1) {
        int v = this.findInsertIndex(object0);
        if(v < 0) {
            v = ~v;
        }
        this.keys[v] = object0;
        this.values[v] = object1;
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

