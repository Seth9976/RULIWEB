package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000B\n\u0002\b)\n\u0002\u0010\u000E\n\u0002\b\u0002\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003B!\b\u0016\u0012\u0018\u0010\u0004\u001A\u0014\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0006\b\u0001\u0012\u00028\u0001\u0018\u00010\u0000\u00A2\u0006\u0002\u0010\u0005B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0006\u001A\u00020\u0007\u00A2\u0006\u0002\u0010\bJ\b\u0010\u000F\u001A\u00020\u0010H\u0016J\u0015\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u0014J\u0015\u0010\u0015\u001A\u00020\u00122\u0006\u0010\u0016\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u0014J\u0010\u0010\u0017\u001A\u00020\u00102\u0006\u0010\u0018\u001A\u00020\u0007H\u0016J\u0013\u0010\u0019\u001A\u00020\u00122\b\u0010\u001A\u001A\u0004\u0018\u00010\u0003H\u0096\u0002J\u0018\u0010\u001B\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001A\u00028\u0000H\u0096\u0002\u00A2\u0006\u0002\u0010\u001CJ\u001F\u0010\u001D\u001A\u00028\u00012\b\u0010\u0013\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u001E\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u001FJ,\u0010 \u001A\u0002H!\"\n\b\u0002\u0010!*\u0004\u0018\u00018\u00012\b\u0010\u0013\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u001E\u001A\u0002H!H\u0082\b\u00A2\u0006\u0002\u0010\u001FJ\b\u0010\"\u001A\u00020\u0007H\u0016J\u001D\u0010#\u001A\u00020\u00072\u0006\u0010\u0013\u001A\u00028\u00002\u0006\u0010$\u001A\u00020\u0007H\u0002\u00A2\u0006\u0002\u0010%J\u0015\u0010&\u001A\u00020\u00072\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\'J\b\u0010(\u001A\u00020\u0007H\u0002J\u0017\u0010)\u001A\u00020\u00072\u0006\u0010\u0016\u001A\u00028\u0001H\u0001\u00A2\u0006\u0004\b*\u0010\'J\b\u0010+\u001A\u00020\u0012H\u0016J\u0015\u0010,\u001A\u00028\u00002\u0006\u0010-\u001A\u00020\u0007H\u0016\u00A2\u0006\u0002\u0010.J\u001F\u0010/\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001A\u00028\u00002\u0006\u0010\u0016\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u001FJ \u00100\u001A\u00020\u00102\u0016\u0010\u0004\u001A\u0012\u0012\u0006\b\u0001\u0012\u00028\u0000\u0012\u0006\b\u0001\u0012\u00028\u00010\u0000H\u0016J\u001F\u00101\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001A\u00028\u00002\u0006\u0010\u0016\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u001FJ\u0017\u00102\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u001CJ\u001D\u00102\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u00002\u0006\u0010\u0016\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u00103J\u0015\u00104\u001A\u00028\u00012\u0006\u0010-\u001A\u00020\u0007H\u0016\u00A2\u0006\u0002\u0010.J\u001F\u00105\u001A\u0004\u0018\u00018\u00012\u0006\u0010\u0013\u001A\u00028\u00002\u0006\u0010\u0016\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010\u001FJ%\u00105\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u00002\u0006\u00106\u001A\u00028\u00012\u0006\u00107\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u00108J\u001D\u00109\u001A\u00028\u00012\u0006\u0010-\u001A\u00020\u00072\u0006\u0010\u0016\u001A\u00028\u0001H\u0016\u00A2\u0006\u0002\u0010:J\b\u0010\u000E\u001A\u00020\u0007H\u0016J\b\u0010;\u001A\u00020<H\u0016J\u0015\u0010=\u001A\u00028\u00012\u0006\u0010-\u001A\u00020\u0007H\u0016\u00A2\u0006\u0002\u0010.R\u0018\u0010\t\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\nX\u0082\u000E\u00A2\u0006\u0004\n\u0002\u0010\u000BR\u000E\u0010\f\u001A\u00020\rX\u0082\u000E\u00A2\u0006\u0002\n\u0000R\u000E\u0010\u000E\u001A\u00020\u0007X\u0082\u000E\u00A2\u0006\u0002\n\u0000\u00A8\u0006>"}, d2 = {"Landroidx/collection/SimpleArrayMap;", "K", "V", "", "map", "(Landroidx/collection/SimpleArrayMap;)V", "capacity", "", "(I)V", "array", "", "[Ljava/lang/Object;", "hashes", "", "size", "clear", "", "containsKey", "", "key", "(Ljava/lang/Object;)Z", "containsValue", "value", "ensureCapacity", "minimumCapacity", "equals", "other", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "getOrDefault", "defaultValue", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getOrDefaultInternal", "T", "hashCode", "indexOf", "hash", "(Ljava/lang/Object;I)I", "indexOfKey", "(Ljava/lang/Object;)I", "indexOfNull", "indexOfValue", "__restricted$indexOfValue", "isEmpty", "keyAt", "index", "(I)Ljava/lang/Object;", "put", "putAll", "putIfAbsent", "remove", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "removeAt", "replace", "oldValue", "newValue", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Z", "setValueAt", "(ILjava/lang/Object;)Ljava/lang/Object;", "toString", "", "valueAt", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class SimpleArrayMap {
    private Object[] array;
    private int[] hashes;
    private int size;

    public SimpleArrayMap() {
        this(0, 1, null);
    }

    public SimpleArrayMap(int v) {
        this.hashes = v == 0 ? ContainerHelpersKt.EMPTY_INTS : new int[v];
        this.array = v == 0 ? ContainerHelpersKt.EMPTY_OBJECTS : new Object[v << 1];
    }

    public SimpleArrayMap(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        this(v);
    }

    public SimpleArrayMap(SimpleArrayMap simpleArrayMap0) {
        this(0, 1, null);
        if(simpleArrayMap0 != null) {
            this.putAll(simpleArrayMap0);
        }
    }

    public final int __restricted$indexOfValue(Object object0) {
        int v = this.size * 2;
        Object[] arr_object = this.array;
        if(object0 == null) {
            for(int v1 = 1; v1 < v; v1 += 2) {
                if(arr_object[v1] == null) {
                    return v1 >> 1;
                }
            }
            return -1;
        }
        for(int v2 = 1; v2 < v; v2 += 2) {
            if(Intrinsics.areEqual(object0, arr_object[v2])) {
                return v2 >> 1;
            }
        }
        return -1;
    }

    public void clear() {
        if(this.size > 0) {
            this.hashes = ContainerHelpersKt.EMPTY_INTS;
            this.array = ContainerHelpersKt.EMPTY_OBJECTS;
            this.size = 0;
        }
        if(this.size > 0) {
            throw new ConcurrentModificationException();
        }
    }

    public boolean containsKey(Object object0) {
        return this.indexOfKey(object0) >= 0;
    }

    public boolean containsValue(Object object0) {
        return this.__restricted$indexOfValue(object0) >= 0;
    }

    public void ensureCapacity(int v) {
        int v1 = this.size;
        int[] arr_v = this.hashes;
        if(arr_v.length < v) {
            int[] arr_v1 = Arrays.copyOf(arr_v, v);
            Intrinsics.checkNotNullExpressionValue(arr_v1, "copyOf(this, newSize)");
            this.hashes = arr_v1;
            Object[] arr_object = Arrays.copyOf(this.array, v * 2);
            Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
            this.array = arr_object;
        }
        if(this.size != v1) {
            throw new ConcurrentModificationException();
        }
    }

    @Override
    public boolean equals(Object object0) {
        if(this == object0) {
            return true;
        }
        try {
            if(object0 instanceof SimpleArrayMap) {
                if(this.size() != ((SimpleArrayMap)object0).size()) {
                    return false;
                }
                int v = this.size;
                for(int v1 = 0; v1 < v; ++v1) {
                    Object object1 = this.keyAt(v1);
                    Object object2 = this.valueAt(v1);
                    Object object3 = ((SimpleArrayMap)object0).get(object1);
                    if(object2 == null) {
                        if(object3 != null || !((SimpleArrayMap)object0).containsKey(object1)) {
                            return false;
                        }
                    }
                    else if(!Intrinsics.areEqual(object2, object3)) {
                        return false;
                    }
                }
                return true;
            }
            if(!(object0 instanceof Map) || this.size() != ((Map)object0).size()) {
                return false;
            }
            int v2 = this.size;
            for(int v3 = 0; v3 < v2; ++v3) {
                Object object4 = this.keyAt(v3);
                Object object5 = this.valueAt(v3);
                Object object6 = ((Map)object0).get(object4);
                if(object5 == null) {
                    if(object6 != null || !((Map)object0).containsKey(object4)) {
                        return false;
                    }
                }
                else if(!Intrinsics.areEqual(object5, object6)) {
                    return false;
                }
            }
            return true;
        }
        catch(NullPointerException | ClassCastException unused_ex) {
        }
        return false;
    }

    public Object get(Object object0) {
        int v = this.indexOfKey(object0);
        return v < 0 ? null : this.array[(v << 1) + 1];
    }

    public Object getOrDefault(Object object0, Object object1) {
        int v = this.indexOfKey(object0);
        return v < 0 ? object1 : this.array[(v << 1) + 1];
    }

    private final Object getOrDefaultInternal(Object object0, Object object1) {
        int v = this.indexOfKey(object0);
        return v < 0 ? object1 : this.array[(v << 1) + 1];
    }

    @Override
    public int hashCode() {
        int[] arr_v = this.hashes;
        Object[] arr_object = this.array;
        int v = this.size;
        int v2 = 0;
        int v3 = 0;
        for(int v1 = 1; v2 < v; v1 += 2) {
            Object object0 = arr_object[v1];
            v3 += (object0 == null ? 0 : object0.hashCode()) ^ arr_v[v2];
            ++v2;
        }
        return v3;
    }

    private final int indexOf(Object object0, int v) {
        int v1 = this.size;
        if(v1 == 0) {
            return -1;
        }
        int v2 = ContainerHelpersKt.binarySearch(this.hashes, v1, v);
        if(v2 < 0 || Intrinsics.areEqual(object0, this.array[v2 << 1])) {
            return v2;
        }
        int v3;
        for(v3 = v2 + 1; v3 < v1 && this.hashes[v3] == v; ++v3) {
            if(Intrinsics.areEqual(object0, this.array[v3 << 1])) {
                return v3;
            }
        }
        for(int v4 = v2 - 1; v4 >= 0 && this.hashes[v4] == v; --v4) {
            if(Intrinsics.areEqual(object0, this.array[v4 << 1])) {
                return v4;
            }
        }
        return ~v3;
    }

    public int indexOfKey(Object object0) {
        return object0 == null ? this.indexOfNull() : this.indexOf(object0, object0.hashCode());
    }

    private final int indexOfNull() {
        int v = this.size;
        if(v == 0) {
            return -1;
        }
        int v1 = ContainerHelpersKt.binarySearch(this.hashes, v, 0);
        if(v1 < 0 || this.array[v1 << 1] == null) {
            return v1;
        }
        int v2;
        for(v2 = v1 + 1; v2 < v && this.hashes[v2] == 0; ++v2) {
            if(this.array[v2 << 1] == null) {
                return v2;
            }
        }
        for(int v3 = v1 - 1; v3 >= 0 && this.hashes[v3] == 0; --v3) {
            if(this.array[v3 << 1] == null) {
                return v3;
            }
        }
        return ~v2;
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    public Object keyAt(int v) {
        if(v < 0 || v >= this.size) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
        }
        return this.array[v << 1];
    }

    public Object put(Object object0, Object object1) {
        int v = this.size;
        int v1 = object0 == null ? 0 : object0.hashCode();
        int v2 = object0 == null ? this.indexOfNull() : this.indexOf(object0, v1);
        if(v2 >= 0) {
            int v3 = (v2 << 1) + 1;
            Object[] arr_object = this.array;
            Object object2 = arr_object[v3];
            arr_object[v3] = object1;
            return object2;
        }
        int v4 = 8;
        int[] arr_v = this.hashes;
        if(v >= arr_v.length) {
            if(v >= 8) {
                v4 = (v >> 1) + v;
            }
            else if(v < 4) {
                v4 = 4;
            }
            int[] arr_v1 = Arrays.copyOf(arr_v, v4);
            Intrinsics.checkNotNullExpressionValue(arr_v1, "copyOf(this, newSize)");
            this.hashes = arr_v1;
            Object[] arr_object1 = Arrays.copyOf(this.array, v4 << 1);
            Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
            this.array = arr_object1;
            if(v != this.size) {
                throw new ConcurrentModificationException();
            }
        }
        if(~v2 < v) {
            ArraysKt.copyInto(this.hashes, this.hashes, -v2, ~v2, v);
            ArraysKt.copyInto(this.array, this.array, -v2 << 1, ~v2 << 1, this.size << 1);
        }
        int v5 = this.size;
        if(v == v5) {
            int[] arr_v2 = this.hashes;
            if(~v2 < arr_v2.length) {
                arr_v2[~v2] = v1;
                Object[] arr_object2 = this.array;
                arr_object2[~v2 << 1] = object0;
                arr_object2[(~v2 << 1) + 1] = object1;
                this.size = v5 + 1;
                return null;
            }
        }
        throw new ConcurrentModificationException();
    }

    public void putAll(SimpleArrayMap simpleArrayMap0) {
        Intrinsics.checkNotNullParameter(simpleArrayMap0, "map");
        int v = simpleArrayMap0.size;
        this.ensureCapacity(this.size + v);
        if(this.size != 0) {
            for(int v1 = 0; v1 < v; ++v1) {
                this.put(simpleArrayMap0.keyAt(v1), simpleArrayMap0.valueAt(v1));
            }
        }
        else if(v > 0) {
            ArraysKt.copyInto(simpleArrayMap0.hashes, this.hashes, 0, 0, v);
            ArraysKt.copyInto(simpleArrayMap0.array, this.array, 0, 0, v << 1);
            this.size = v;
        }
    }

    public Object putIfAbsent(Object object0, Object object1) {
        Object object2 = this.get(object0);
        return object2 == null ? this.put(object0, object1) : object2;
    }

    public Object remove(Object object0) {
        int v = this.indexOfKey(object0);
        return v < 0 ? null : this.removeAt(v);
    }

    public boolean remove(Object object0, Object object1) {
        int v = this.indexOfKey(object0);
        if(v >= 0 && Intrinsics.areEqual(object1, this.valueAt(v))) {
            this.removeAt(v);
            return true;
        }
        return false;
    }

    public Object removeAt(int v) {
        if(v >= 0) {
            int v1 = this.size;
            if(v < v1) {
                Object[] arr_object = this.array;
                Object object0 = arr_object[(v << 1) + 1];
                if(v1 <= 1) {
                    this.clear();
                    return object0;
                }
                int[] arr_v = this.hashes;
                int v2 = 8;
                if(arr_v.length <= 8 || v1 >= arr_v.length / 3) {
                    if(v < v1 - 1) {
                        ArraysKt.copyInto(arr_v, arr_v, v, v + 1, v1);
                        ArraysKt.copyInto(this.array, this.array, v << 1, v + 1 << 1, v1 << 1);
                    }
                    Object[] arr_object2 = this.array;
                    int v3 = v1 - 1 << 1;
                    arr_object2[v3] = null;
                    arr_object2[v3 + 1] = null;
                }
                else {
                    if(v1 > 8) {
                        v2 = v1 + (v1 >> 1);
                    }
                    int[] arr_v1 = Arrays.copyOf(arr_v, v2);
                    Intrinsics.checkNotNullExpressionValue(arr_v1, "copyOf(this, newSize)");
                    this.hashes = arr_v1;
                    Object[] arr_object1 = Arrays.copyOf(this.array, v2 << 1);
                    Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
                    this.array = arr_object1;
                    if(v1 != this.size) {
                        throw new ConcurrentModificationException();
                    }
                    if(v > 0) {
                        ArraysKt.copyInto(arr_v, this.hashes, 0, 0, v);
                        ArraysKt.copyInto(arr_object, this.array, 0, 0, v << 1);
                    }
                    if(v < v1 - 1) {
                        ArraysKt.copyInto(arr_v, this.hashes, v, v + 1, v1);
                        ArraysKt.copyInto(arr_object, this.array, v << 1, v + 1 << 1, v1 << 1);
                    }
                }
                if(v1 != this.size) {
                    throw new ConcurrentModificationException();
                }
                this.size = v1 - 1;
                return object0;
            }
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
    }

    public Object replace(Object object0, Object object1) {
        int v = this.indexOfKey(object0);
        return v < 0 ? null : this.setValueAt(v, object1);
    }

    public boolean replace(Object object0, Object object1, Object object2) {
        int v = this.indexOfKey(object0);
        if(v >= 0 && Intrinsics.areEqual(object1, this.valueAt(v))) {
            this.setValueAt(v, object2);
            return true;
        }
        return false;
    }

    public Object setValueAt(int v, Object object0) {
        if(v < 0 || v >= this.size) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
        }
        int v1 = (v << 1) + 1;
        Object[] arr_object = this.array;
        Object object1 = arr_object[v1];
        arr_object[v1] = object0;
        return object1;
    }

    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        if(this.isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder(this.size * 28);
        stringBuilder0.append('{');
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(v1 > 0) {
                stringBuilder0.append(", ");
            }
            Object object0 = this.keyAt(v1);
            if(object0 == stringBuilder0) {
                stringBuilder0.append("(this Map)");
            }
            else {
                stringBuilder0.append(object0);
            }
            stringBuilder0.append('=');
            Object object1 = this.valueAt(v1);
            if(object1 == stringBuilder0) {
                stringBuilder0.append("(this Map)");
            }
            else {
                stringBuilder0.append(object1);
            }
        }
        stringBuilder0.append('}');
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "StringBuilder(capacity).…builderAction).toString()");
        return s;
    }

    public Object valueAt(int v) {
        if(v < 0 || v >= this.size) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
        }
        return this.array[(v << 1) + 1];
    }
}

