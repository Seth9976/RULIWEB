package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u001E\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000E\n\u0002\b\u0003\b\'\u0018\u0000 )*\u0004\b\u0000\u0010\u0001*\u0006\b\u0001\u0010\u0002 \u00012\u000E\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003:\u0001)B\u0007\b\u0004¢\u0006\u0002\u0010\u0004J\u001F\u0010\u0013\u001A\u00020\u00142\u0010\u0010\u0015\u001A\f\u0012\u0002\b\u0003\u0012\u0002\b\u0003\u0018\u00010\u0016H\u0000¢\u0006\u0002\b\u0017J\u0015\u0010\u0018\u001A\u00020\u00142\u0006\u0010\u0019\u001A\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001AJ\u0015\u0010\u001B\u001A\u00020\u00142\u0006\u0010\u001C\u001A\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001AJ\u0013\u0010\u001D\u001A\u00020\u00142\b\u0010\u001E\u001A\u0004\u0018\u00010\u001FH\u0096\u0002J\u0018\u0010 \u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0019\u001A\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010!J\b\u0010\"\u001A\u00020\rH\u0016J#\u0010#\u001A\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00162\u0006\u0010\u0019\u001A\u00028\u0000H\u0002¢\u0006\u0002\u0010$J\b\u0010%\u001A\u00020\u0014H\u0016J\b\u0010&\u001A\u00020\'H\u0016J\u0012\u0010&\u001A\u00020\'2\b\u0010(\u001A\u0004\u0018\u00010\u001FH\u0002J\u001C\u0010&\u001A\u00020\'2\u0012\u0010\u0015\u001A\u000E\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0016H\bR\u0016\u0010\u0005\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0082\u000E¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001A\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\bX\u0088\u000E¢\u0006\u0002\n\u0000R\u001A\u0010\t\u001A\b\u0012\u0004\u0012\u00028\u00000\u00068VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\n\u0010\u000BR\u0014\u0010\f\u001A\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u000E\u0010\u000FR\u001A\u0010\u0010\u001A\b\u0012\u0004\u0012\u00028\u00010\b8VX\u0096\u0004¢\u0006\u0006\u001A\u0004\b\u0011\u0010\u0012¨\u0006*"}, d2 = {"Lkotlin/collections/AbstractMap;", "K", "V", "", "()V", "_keys", "", "_values", "", "keys", "getKeys", "()Ljava/util/Set;", "size", "", "getSize", "()I", "values", "getValues", "()Ljava/util/Collection;", "containsEntry", "", "entry", "", "containsEntry$kotlin_stdlib", "containsKey", "key", "(Ljava/lang/Object;)Z", "containsValue", "value", "equals", "other", "", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", "implFindEntry", "(Ljava/lang/Object;)Ljava/util/Map$Entry;", "isEmpty", "toString", "", "o", "Companion", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public abstract class AbstractMap implements Map, KMappedMarker {
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\'\u0010\u0003\u001A\u00020\u00042\u000E\u0010\u0005\u001A\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00062\b\u0010\u0007\u001A\u0004\u0018\u00010\u0001H\u0000¢\u0006\u0002\b\bJ\u001D\u0010\t\u001A\u00020\n2\u000E\u0010\u0005\u001A\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000BJ\u001D\u0010\f\u001A\u00020\r2\u000E\u0010\u0005\u001A\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006H\u0000¢\u0006\u0002\b\u000E¨\u0006\u000F"}, d2 = {"Lkotlin/collections/AbstractMap$Companion;", "", "()V", "entryEquals", "", "e", "", "other", "entryEquals$kotlin_stdlib", "entryHashCode", "", "entryHashCode$kotlin_stdlib", "entryToString", "", "entryToString$kotlin_stdlib", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
    public static final class Companion {
        private Companion() {
        }

        public Companion(DefaultConstructorMarker defaultConstructorMarker0) {
        }

        public final boolean entryEquals$kotlin_stdlib(Map.Entry map$Entry0, Object object0) {
            Intrinsics.checkNotNullParameter(map$Entry0, "e");
            return object0 instanceof Map.Entry ? Intrinsics.areEqual(map$Entry0.getKey(), ((Map.Entry)object0).getKey()) && Intrinsics.areEqual(map$Entry0.getValue(), ((Map.Entry)object0).getValue()) : false;
        }

        public final int entryHashCode$kotlin_stdlib(Map.Entry map$Entry0) {
            Intrinsics.checkNotNullParameter(map$Entry0, "e");
            Object object0 = map$Entry0.getKey();
            int v = 0;
            int v1 = object0 == null ? 0 : object0.hashCode();
            Object object1 = map$Entry0.getValue();
            if(object1 != null) {
                v = object1.hashCode();
            }
            return v1 ^ v;
        }

        public final String entryToString$kotlin_stdlib(Map.Entry map$Entry0) {
            Intrinsics.checkNotNullParameter(map$Entry0, "e");
            return map$Entry0.getKey() + '=' + map$Entry0.getValue();
        }
    }

    public static final Companion Companion;
    private volatile Set _keys;
    private volatile Collection _values;

    static {
        AbstractMap.Companion = new Companion(null);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public final boolean containsEntry$kotlin_stdlib(Map.Entry map$Entry0) {
        if(map$Entry0 == null) {
            return false;
        }
        Object object0 = map$Entry0.getKey();
        Object object1 = map$Entry0.getValue();
        Intrinsics.checkNotNull(this, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.get, V of kotlin.collections.MapsKt__MapsKt.get>");
        Object object2 = this.get(object0);
        if(!Intrinsics.areEqual(object1, object2)) {
            return false;
        }
        if(object2 == null) {
            Intrinsics.checkNotNull(this, "null cannot be cast to non-null type kotlin.collections.Map<K of kotlin.collections.MapsKt__MapsKt.containsKey, *>");
            return this.containsKey(object0);
        }
        return true;
    }

    @Override
    public boolean containsKey(Object object0) {
        return this.implFindEntry(object0) != null;
    }

    @Override
    public boolean containsValue(Object object0) {
        Iterable iterable0 = this.entrySet();
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return false;
        }
        for(Object object1: iterable0) {
            if(Intrinsics.areEqual(((Map.Entry)object1).getValue(), object0)) {
                return true;
            }
            if(false) {
                break;
            }
        }
        return false;
    }

    @Override
    public final Set entrySet() {
        return this.getEntries();
    }

    @Override
    public boolean equals(Object object0) {
        if(object0 == this) {
            return true;
        }
        if(!(object0 instanceof Map)) {
            return false;
        }
        if(this.size() != ((Map)object0).size()) {
            return false;
        }
        Iterable iterable0 = ((Map)object0).entrySet();
        if(iterable0 instanceof Collection && ((Collection)iterable0).isEmpty()) {
            return true;
        }
        for(Object object1: iterable0) {
            if(!this.containsEntry$kotlin_stdlib(((Map.Entry)object1))) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    @Override
    public Object get(Object object0) {
        Map.Entry map$Entry0 = this.implFindEntry(object0);
        return map$Entry0 == null ? null : map$Entry0.getValue();
    }

    public abstract Set getEntries();

    public Set getKeys() {
        if(this._keys == null) {
            this._keys = new AbstractSet() {
                @Override  // kotlin.collections.AbstractCollection
                public boolean contains(Object object0) {
                    return AbstractMap.this.containsKey(object0);
                }

                @Override  // kotlin.collections.AbstractCollection
                public int getSize() {
                    return AbstractMap.this.size();
                }

                @Override  // kotlin.collections.AbstractSet
                public Iterator iterator() {
                    return new Object() {
                        @Override
                        public boolean hasNext() {
                            return this.$entryIterator.hasNext();
                        }

                        @Override
                        public Object next() {
                            Object object0 = this.$entryIterator.next();
                            return ((Map.Entry)object0).getKey();
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
                        }
                    };
                }
            };
        }
        Set set0 = this._keys;
        Intrinsics.checkNotNull(set0);
        return set0;
    }

    public int getSize() {
        return this.entrySet().size();
    }

    public Collection getValues() {
        if(this._values == null) {
            this._values = new AbstractCollection() {
                @Override  // kotlin.collections.AbstractCollection
                public boolean contains(Object object0) {
                    return AbstractMap.this.containsValue(object0);
                }

                @Override  // kotlin.collections.AbstractCollection
                public int getSize() {
                    return AbstractMap.this.size();
                }

                @Override  // kotlin.collections.AbstractCollection
                public Iterator iterator() {
                    return new Object() {
                        @Override
                        public boolean hasNext() {
                            return this.$entryIterator.hasNext();
                        }

                        @Override
                        public Object next() {
                            Object object0 = this.$entryIterator.next();
                            return ((Map.Entry)object0).getValue();
                        }

                        @Override
                        public void remove() {
                            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
                        }
                    };
                }
            };
        }
        Collection collection0 = this._values;
        Intrinsics.checkNotNull(collection0);
        return collection0;
    }

    @Override
    public int hashCode() {
        return this.entrySet().hashCode();
    }

    private final Map.Entry implFindEntry(Object object0) {
        for(Object object1: this.entrySet()) {
            if(Intrinsics.areEqual(((Map.Entry)object1).getKey(), object0)) {
                return (Map.Entry)object1;
            }
            if(false) {
                break;
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public final Set keySet() {
        return this.getKeys();
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
    public Object remove(Object object0) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override
    public final int size() {
        return this.getSize();
    }

    private final String toString(Object object0) {
        return object0 == this ? "(this Map)" : String.valueOf(object0);
    }

    private final String toString(Map.Entry map$Entry0) {
        return this.toString(map$Entry0.getKey()) + '=' + this.toString(map$Entry0.getValue());
    }

    @Override
    public String toString() {
        return CollectionsKt.joinToString$default(this.entrySet(), ", ", "{", "}", 0, null, new Function1() {
            {
                AbstractMap.this = abstractMap0;
                super(1);
            }

            public final CharSequence invoke(Map.Entry map$Entry0) {
                Intrinsics.checkNotNullParameter(map$Entry0, "it");
                return AbstractMap.this.toString(map$Entry0);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(((Map.Entry)object0));
            }
        }, 24, null);
    }

    @Override
    public final Collection values() {
        return this.getValues();
    }
}

