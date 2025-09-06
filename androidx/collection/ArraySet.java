package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableCollection;
import kotlin.jvm.internal.markers.KMutableSet;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001F\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\u001E\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010)\n\u0002\b\n\n\u0002\u0010\u000E\n\u0002\b\u0003\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001?B\u0019\b\u0016\u0012\u0010\u0010\u0004\u001A\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\u0000\u00A2\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u000E\u0010\u0004\u001A\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006\u00A2\u0006\u0002\u0010\u0007B\u0019\b\u0016\u0012\u0010\u0010\b\u001A\f\u0012\u0006\b\u0001\u0012\u00028\u0000\u0018\u00010\t\u00A2\u0006\u0002\u0010\nB\u0011\b\u0007\u0012\b\b\u0002\u0010\u000B\u001A\u00020\f\u00A2\u0006\u0002\u0010\rJ\u0015\u0010\u001F\u001A\u00020 2\u0006\u0010!\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\"J\u0016\u0010#\u001A\u00020$2\u000E\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0000J\u0016\u0010#\u001A\u00020 2\f\u0010%\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\b\u0010&\u001A\u00020$H\u0016J\u0016\u0010\'\u001A\u00020 2\u0006\u0010!\u001A\u00028\u0000H\u0096\u0002\u00A2\u0006\u0002\u0010\"J\u0016\u0010(\u001A\u00020 2\f\u0010%\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u000E\u0010)\u001A\u00020$2\u0006\u0010*\u001A\u00020\fJ\u0013\u0010+\u001A\u00020 2\b\u0010,\u001A\u0004\u0018\u00010\u0012H\u0096\u0002J\b\u0010-\u001A\u00020\fH\u0016J\u0010\u0010.\u001A\u00020\f2\b\u0010/\u001A\u0004\u0018\u00010\u0012J\b\u00100\u001A\u00020 H\u0016J\u000F\u00101\u001A\b\u0012\u0004\u0012\u00028\u000002H\u0096\u0002J\u0015\u00103\u001A\u00020 2\u0006\u0010!\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\"J\u0016\u00104\u001A\u00020 2\u000E\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0000J\u0016\u00104\u001A\u00020 2\f\u0010%\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u0013\u00105\u001A\u00028\u00002\u0006\u00106\u001A\u00020\f\u00A2\u0006\u0002\u00107J\u0016\u00108\u001A\u00020 2\f\u0010%\u001A\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0016J\u0013\u00109\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\t\u00A2\u0006\u0002\u0010\u0014J%\u00109\u001A\b\u0012\u0004\u0012\u0002H:0\t\"\u0004\b\u0001\u0010:2\f\u0010\b\u001A\b\u0012\u0004\u0012\u0002H:0\t\u00A2\u0006\u0002\u0010;J\b\u0010<\u001A\u00020=H\u0016J\u0013\u0010>\u001A\u00028\u00002\u0006\u00106\u001A\u00020\f\u00A2\u0006\u0002\u00107R\u001A\u0010\u000E\u001A\u00020\fX\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u000F\u0010\u0010\"\u0004\b\u0011\u0010\rR$\u0010\b\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\tX\u0080\u000E\u00A2\u0006\u0010\n\u0002\u0010\u0016\u001A\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\nR\u001A\u0010\u0017\u001A\u00020\u0018X\u0080\u000E\u00A2\u0006\u000E\n\u0000\u001A\u0004\b\u0019\u0010\u001A\"\u0004\b\u001B\u0010\u001CR\u0014\u0010\u001D\u001A\u00020\f8VX\u0096\u0004\u00A2\u0006\u0006\u001A\u0004\b\u001E\u0010\u0010\u00A8\u0006@"}, d2 = {"Landroidx/collection/ArraySet;", "E", "", "", "set", "(Landroidx/collection/ArraySet;)V", "", "(Ljava/util/Collection;)V", "array", "", "([Ljava/lang/Object;)V", "capacity", "", "(I)V", "_size", "get_size$collection", "()I", "set_size$collection", "", "getArray$collection", "()[Ljava/lang/Object;", "setArray$collection", "[Ljava/lang/Object;", "hashes", "", "getHashes$collection", "()[I", "setHashes$collection", "([I)V", "size", "getSize", "add", "", "element", "(Ljava/lang/Object;)Z", "addAll", "", "elements", "clear", "contains", "containsAll", "ensureCapacity", "minimumCapacity", "equals", "other", "hashCode", "indexOf", "key", "isEmpty", "iterator", "", "remove", "removeAll", "removeAt", "index", "(I)Ljava/lang/Object;", "retainAll", "toArray", "T", "([Ljava/lang/Object;)[Ljava/lang/Object;", "toString", "", "valueAt", "ElementIterator", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public final class ArraySet implements Collection, Set, KMutableCollection, KMutableSet {
    @Metadata(d1 = {"\u0000\u001A\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001A\u00028\u00002\u0006\u0010\u0004\u001A\u00020\u0005H\u0014¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001A\u00020\b2\u0006\u0010\u0004\u001A\u00020\u0005H\u0014¨\u0006\t"}, d2 = {"Landroidx/collection/ArraySet$ElementIterator;", "Landroidx/collection/IndexBasedArrayIterator;", "(Landroidx/collection/ArraySet;)V", "elementAt", "index", "", "(I)Ljava/lang/Object;", "removeAt", "", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
    final class ElementIterator extends IndexBasedArrayIterator {
        public ElementIterator() {
            super(arraySet0.get_size$collection());
        }

        @Override  // androidx.collection.IndexBasedArrayIterator
        protected Object elementAt(int v) {
            return ArraySet.this.valueAt(v);
        }

        @Override  // androidx.collection.IndexBasedArrayIterator
        protected void removeAt(int v) {
            ArraySet.this.removeAt(v);
        }
    }

    private int _size;
    private Object[] array;
    private int[] hashes;

    public ArraySet() {
        this(0, 1, null);
    }

    public ArraySet(int v) {
        this.hashes = ContainerHelpersKt.EMPTY_INTS;
        this.array = ContainerHelpersKt.EMPTY_OBJECTS;
        if(v > 0) {
            ArraySetKt.allocArrays(this, v);
        }
    }

    public ArraySet(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        this(v);
    }

    public ArraySet(ArraySet arraySet0) {
        this(0);
        if(arraySet0 != null) {
            this.addAll(arraySet0);
        }
    }

    public ArraySet(Collection collection0) {
        this(0);
        if(collection0 != null) {
            this.addAll(collection0);
        }
    }

    public ArraySet(Object[] arr_object) {
        this(0);
        if(arr_object != null) {
            for(Object object0: arr_object) {
                this.add(object0);
            }
        }
    }

    @Override
    public boolean add(Object object0) {
        int v2;
        int v1;
        int v = this.get_size$collection();
        if(object0 == null) {
            v1 = ArraySetKt.indexOfNull(this);
            v2 = 0;
        }
        else {
            int v3 = object0.hashCode();
            v2 = v3;
            v1 = ArraySetKt.indexOf(this, object0, v3);
        }
        int v4 = 8;
        if(v1 >= 0) {
            return false;
        }
        if(v >= this.getHashes$collection().length) {
            if(v >= 8) {
                v4 = (v >> 1) + v;
            }
            else if(v < 4) {
                v4 = 4;
            }
            int[] arr_v = this.getHashes$collection();
            Object[] arr_object = this.getArray$collection();
            ArraySetKt.allocArrays(this, v4);
            if(v != this.get_size$collection()) {
                throw new ConcurrentModificationException();
            }
            if(this.getHashes$collection().length != 0) {
                ArraysKt.copyInto$default(arr_v, this.getHashes$collection(), 0, 0, arr_v.length, 6, null);
                ArraysKt.copyInto$default(arr_object, this.getArray$collection(), 0, 0, arr_object.length, 6, null);
            }
        }
        if(~v1 < v) {
            ArraysKt.copyInto(this.getHashes$collection(), this.getHashes$collection(), -v1, ~v1, v);
            ArraysKt.copyInto(this.getArray$collection(), this.getArray$collection(), -v1, ~v1, v);
        }
        if(v != this.get_size$collection() || ~v1 >= this.getHashes$collection().length) {
            throw new ConcurrentModificationException();
        }
        this.getHashes$collection()[~v1] = v2;
        this.getArray$collection()[~v1] = object0;
        this.set_size$collection(this.get_size$collection() + 1);
        return true;
    }

    public final void addAll(ArraySet arraySet0) {
        Intrinsics.checkNotNullParameter(arraySet0, "array");
        int v = arraySet0.get_size$collection();
        this.ensureCapacity(this.get_size$collection() + v);
        if(this.get_size$collection() != 0) {
            for(int v1 = 0; v1 < v; ++v1) {
                this.add(arraySet0.valueAt(v1));
            }
        }
        else if(v > 0) {
            ArraysKt.copyInto$default(arraySet0.getHashes$collection(), this.getHashes$collection(), 0, 0, v, 6, null);
            ArraysKt.copyInto$default(arraySet0.getArray$collection(), this.getArray$collection(), 0, 0, v, 6, null);
            if(this.get_size$collection() != 0) {
                throw new ConcurrentModificationException();
            }
            this.set_size$collection(v);
        }
    }

    @Override
    public boolean addAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        this.ensureCapacity(this.get_size$collection() + collection0.size());
        boolean z = false;
        for(Object object0: collection0) {
            z |= this.add(object0);
        }
        return z;
    }

    @Override
    public void clear() {
        if(this.get_size$collection() != 0) {
            this.setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
            this.setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
            this.set_size$collection(0);
        }
        if(this.get_size$collection() != 0) {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    public boolean contains(Object object0) {
        return this.indexOf(object0) >= 0;
    }

    @Override
    public boolean containsAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        for(Object object0: collection0) {
            if(!this.contains(object0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public final void ensureCapacity(int v) {
        int v1 = this.get_size$collection();
        if(this.getHashes$collection().length < v) {
            int[] arr_v = this.getHashes$collection();
            Object[] arr_object = this.getArray$collection();
            ArraySetKt.allocArrays(this, v);
            if(this.get_size$collection() > 0) {
                ArraysKt.copyInto$default(arr_v, this.getHashes$collection(), 0, 0, this.get_size$collection(), 6, null);
                ArraysKt.copyInto$default(arr_object, this.getArray$collection(), 0, 0, this.get_size$collection(), 6, null);
            }
        }
        if(this.get_size$collection() != v1) {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        if(!(object0 instanceof Set) || this.size() != ((Set)object0).size()) {
            return false;
        }
        try {
            int v = this.get_size$collection();
            for(int v1 = 0; true; ++v1) {
                if(v1 >= v) {
                    return true;
                }
                if(!((Set)object0).contains(this.valueAt(v1))) {
                    break;
                }
            }
        }
        catch(NullPointerException | ClassCastException unused_ex) {
        }
        return false;
    }

    public final Object[] getArray$collection() {
        return this.array;
    }

    public final int[] getHashes$collection() {
        return this.hashes;
    }

    public int getSize() {
        return this._size;
    }

    public final int get_size$collection() {
        return this._size;
    }

    @Override
    public int hashCode() {
        int[] arr_v = this.getHashes$collection();
        int v = this.get_size$collection();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += arr_v[v1];
        }
        return v2;
    }

    public final int indexOf(Object object0) {
        return object0 == null ? ArraySetKt.indexOfNull(this) : ArraySetKt.indexOf(this, object0, object0.hashCode());
    }

    @Override
    public boolean isEmpty() {
        return this.get_size$collection() <= 0;
    }

    @Override
    public Iterator iterator() {
        return new ElementIterator(this);
    }

    @Override
    public boolean remove(Object object0) {
        int v = this.indexOf(object0);
        if(v >= 0) {
            this.removeAt(v);
            return true;
        }
        return false;
    }

    public final boolean removeAll(ArraySet arraySet0) {
        Intrinsics.checkNotNullParameter(arraySet0, "array");
        int v = arraySet0.get_size$collection();
        int v1 = this.get_size$collection();
        for(int v2 = 0; v2 < v; ++v2) {
            this.remove(arraySet0.valueAt(v2));
        }
        return v1 != this.get_size$collection();
    }

    @Override
    public boolean removeAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        boolean z = false;
        for(Object object0: collection0) {
            z |= this.remove(object0);
        }
        return z;
    }

    public final Object removeAt(int v) {
        Object[] arr_object1;
        int v1 = 8;
        int v2 = this.get_size$collection();
        Object object0 = this.getArray$collection()[v];
        if(v2 <= 1) {
            this.clear();
            return object0;
        }
        if(this.getHashes$collection().length <= 8 || this.get_size$collection() >= this.getHashes$collection().length / 3) {
            if(v < v2 - 1) {
                ArraysKt.copyInto(this.getHashes$collection(), this.getHashes$collection(), v, v + 1, v2);
                ArraysKt.copyInto(this.getArray$collection(), this.getArray$collection(), v, v + 1, v2);
            }
            this.getArray$collection()[v2 - 1] = null;
        }
        else {
            if(this.get_size$collection() > 8) {
                v1 = this.get_size$collection() + (this.get_size$collection() >> 1);
            }
            int[] arr_v = this.getHashes$collection();
            Object[] arr_object = this.getArray$collection();
            ArraySetKt.allocArrays(this, v1);
            if(v > 0) {
                ArraysKt.copyInto$default(arr_v, this.getHashes$collection(), 0, 0, v, 6, null);
                arr_object1 = arr_object;
                ArraysKt.copyInto$default(arr_object1, this.getArray$collection(), 0, 0, v, 6, null);
            }
            else {
                arr_object1 = arr_object;
            }
            if(v < v2 - 1) {
                ArraysKt.copyInto(arr_v, this.getHashes$collection(), v, v + 1, v2);
                ArraysKt.copyInto(arr_object1, this.getArray$collection(), v, v + 1, v2);
            }
        }
        if(v2 != this.get_size$collection()) {
            throw new ConcurrentModificationException();
        }
        this.set_size$collection(v2 - 1);
        return object0;
    }

    @Override
    public boolean retainAll(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "elements");
        int v = this.get_size$collection() - 1;
        boolean z = false;
        while(-1 < v) {
            if(!CollectionsKt.contains(collection0, this.getArray$collection()[v])) {
                this.removeAt(v);
                z = true;
            }
            --v;
        }
        return z;
    }

    public final void setArray$collection(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "<set-?>");
        this.array = arr_object;
    }

    public final void setHashes$collection(int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "<set-?>");
        this.hashes = arr_v;
    }

    public final void set_size$collection(int v) {
        this._size = v;
    }

    @Override
    public final int size() {
        return this.getSize();
    }

    @Override
    public final Object[] toArray() {
        return ArraysKt.copyOfRange(this.array, 0, this._size);
    }

    @Override
    public final Object[] toArray(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "array");
        Object[] arr_object1 = ArraySetJvmUtil.resizeForToArray(arr_object, this._size);
        ArraysKt.copyInto(this.array, arr_object1, 0, 0, this._size);
        Intrinsics.checkNotNullExpressionValue(arr_object1, "result");
        return arr_object1;
    }

    @Override
    public String toString() {
        if(this.isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder(this.get_size$collection() * 14);
        stringBuilder0.append('{');
        int v = this.get_size$collection();
        for(int v1 = 0; v1 < v; ++v1) {
            if(v1 > 0) {
                stringBuilder0.append(", ");
            }
            Object object0 = this.valueAt(v1);
            if(object0 == this) {
                stringBuilder0.append("(this Set)");
            }
            else {
                stringBuilder0.append(object0);
            }
        }
        stringBuilder0.append('}');
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder(capacity).…builderAction).toString()");
        return s;
    }

    public final Object valueAt(int v) {
        return this.getArray$collection()[v];
    }
}

