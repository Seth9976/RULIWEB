package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u001E\n\u0002\b\u000E\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u000E\n\u0002\b\u0002\u001A\u0015\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u0004H\u0086\b\u001A+\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\u0012\u0010\u0005\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00040\u0006\"\u0002H\u0004\u00A2\u0006\u0002\u0010\u0007\u001A)\u0010\b\u001A\u00020\t\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u000E\u0010\u000B\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\n0\u0003H\u0080\b\u001A\'\u0010\b\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\n0\u000EH\u0080\b\u001A&\u0010\u000F\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u0006\u0010\u0010\u001A\u0002H\nH\u0080\b\u00A2\u0006\u0002\u0010\u0011\u001A \u0010\u0012\u001A\u00020\t\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u0006\u0010\u0013\u001A\u00020\u0001H\u0000\u001A \u0010\u0014\u001A\u00020\u0001\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u0006\u0010\u0015\u001A\u00020\u0001H\u0000\u001A\u0019\u0010\u0016\u001A\u00020\t\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u0003H\u0080\b\u001A\'\u0010\u0017\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\n0\u000EH\u0080\b\u001A&\u0010\u0018\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u0006\u0010\u0010\u001A\u0002H\nH\u0080\b\u00A2\u0006\u0002\u0010\u0011\u001A!\u0010\u0019\u001A\u00020\t\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u0006\u0010\u001A\u001A\u00020\u0001H\u0080\b\u001A#\u0010\u001B\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\b\u0010\u001C\u001A\u0004\u0018\u00010\u001DH\u0080\b\u001A\u0019\u0010\u001E\u001A\u00020\u0001\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u0003H\u0080\b\u001A*\u0010\u001F\u001A\u00020\u0001\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\b\u0010 \u001A\u0004\u0018\u00010\u001D2\u0006\u0010\u0015\u001A\u00020\u0001H\u0000\u001A#\u0010!\u001A\u00020\u0001\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\b\u0010 \u001A\u0004\u0018\u00010\u001DH\u0080\b\u001A\u0018\u0010\"\u001A\u00020\u0001\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u0003H\u0000\u001A\u0019\u0010#\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u0003H\u0080\b\u001A)\u0010$\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u000E\u0010\u000B\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\n0\u0003H\u0080\b\u001A\'\u0010$\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\n0\u000EH\u0080\b\u001A&\u0010%\u001A\u0002H\n\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u0006\u0010&\u001A\u00020\u0001H\u0080\b\u00A2\u0006\u0002\u0010\'\u001A&\u0010(\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u0006\u0010\u0010\u001A\u0002H\nH\u0080\b\u00A2\u0006\u0002\u0010\u0011\u001A\'\u0010)\u001A\u00020\f\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\f\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\n0\u000EH\u0080\b\u001A\u0019\u0010*\u001A\u00020+\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u0003H\u0080\b\u001A&\u0010,\u001A\u0002H\n\"\u0004\b\u0000\u0010\n*\b\u0012\u0004\u0012\u0002H\n0\u00032\u0006\u0010&\u001A\u00020\u0001H\u0080\b\u00A2\u0006\u0002\u0010\'\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0080T\u00A2\u0006\u0002\n\u0000\u00A8\u0006-"}, d2 = {"ARRAY_SET_BASE_SIZE", "", "arraySetOf", "Landroidx/collection/ArraySet;", "T", "values", "", "([Ljava/lang/Object;)Landroidx/collection/ArraySet;", "addAllInternal", "", "E", "array", "", "elements", "", "addInternal", "element", "(Landroidx/collection/ArraySet;Ljava/lang/Object;)Z", "allocArrays", "size", "binarySearchInternal", "hash", "clearInternal", "containsAllInternal", "containsInternal", "ensureCapacityInternal", "minimumCapacity", "equalsInternal", "other", "", "hashCodeInternal", "indexOf", "key", "indexOfInternal", "indexOfNull", "isEmptyInternal", "removeAllInternal", "removeAtInternal", "index", "(Landroidx/collection/ArraySet;I)Ljava/lang/Object;", "removeInternal", "retainAllInternal", "toStringInternal", "", "valueAtInternal", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ArraySetKt {
    public static final int ARRAY_SET_BASE_SIZE = 4;

    public static final void addAllInternal(ArraySet arraySet0, ArraySet arraySet1) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        Intrinsics.checkNotNullParameter(arraySet1, "array");
        int v = arraySet1.get_size$collection();
        arraySet0.ensureCapacity(arraySet0.get_size$collection() + v);
        if(arraySet0.get_size$collection() != 0) {
            for(int v1 = 0; v1 < v; ++v1) {
                arraySet0.add(arraySet1.valueAt(v1));
            }
        }
        else if(v > 0) {
            ArraysKt.copyInto$default(arraySet1.getHashes$collection(), arraySet0.getHashes$collection(), 0, 0, v, 6, null);
            ArraysKt.copyInto$default(arraySet1.getArray$collection(), arraySet0.getArray$collection(), 0, 0, v, 6, null);
            if(arraySet0.get_size$collection() != 0) {
                throw new ConcurrentModificationException();
            }
            arraySet0.set_size$collection(v);
        }
    }

    public static final boolean addAllInternal(ArraySet arraySet0, Collection collection0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "elements");
        arraySet0.ensureCapacity(arraySet0.get_size$collection() + collection0.size());
        boolean z = false;
        for(Object object0: collection0) {
            z |= arraySet0.add(object0);
        }
        return z;
    }

    public static final boolean addInternal(ArraySet arraySet0, Object object0) {
        int v2;
        int v1;
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        int v = arraySet0.get_size$collection();
        if(object0 == null) {
            v1 = ArraySetKt.indexOfNull(arraySet0);
            v2 = 0;
        }
        else {
            int v3 = object0.hashCode();
            v2 = v3;
            v1 = ArraySetKt.indexOf(arraySet0, object0, v3);
        }
        int v4 = 8;
        if(v1 >= 0) {
            return false;
        }
        if(v >= arraySet0.getHashes$collection().length) {
            if(v >= 8) {
                v4 = (v >> 1) + v;
            }
            else if(v < 4) {
                v4 = 4;
            }
            int[] arr_v = arraySet0.getHashes$collection();
            Object[] arr_object = arraySet0.getArray$collection();
            ArraySetKt.allocArrays(arraySet0, v4);
            if(v != arraySet0.get_size$collection()) {
                throw new ConcurrentModificationException();
            }
            if(arraySet0.getHashes$collection().length != 0) {
                ArraysKt.copyInto$default(arr_v, arraySet0.getHashes$collection(), 0, 0, arr_v.length, 6, null);
                ArraysKt.copyInto$default(arr_object, arraySet0.getArray$collection(), 0, 0, arr_object.length, 6, null);
            }
        }
        if(~v1 < v) {
            ArraysKt.copyInto(arraySet0.getHashes$collection(), arraySet0.getHashes$collection(), -v1, ~v1, v);
            ArraysKt.copyInto(arraySet0.getArray$collection(), arraySet0.getArray$collection(), -v1, ~v1, v);
        }
        if(v != arraySet0.get_size$collection() || ~v1 >= arraySet0.getHashes$collection().length) {
            throw new ConcurrentModificationException();
        }
        arraySet0.getHashes$collection()[~v1] = v2;
        arraySet0.getArray$collection()[~v1] = object0;
        arraySet0.set_size$collection(arraySet0.get_size$collection() + 1);
        return true;
    }

    public static final void allocArrays(ArraySet arraySet0, int v) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        arraySet0.setHashes$collection(new int[v]);
        arraySet0.setArray$collection(new Object[v]);
    }

    public static final ArraySet arraySetOf() {
        return new ArraySet(0, 1, null);
    }

    public static final ArraySet arraySetOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "values");
        ArraySet arraySet0 = new ArraySet(arr_object.length);
        for(int v = 0; v < arr_object.length; ++v) {
            arraySet0.add(arr_object[v]);
        }
        return arraySet0;
    }

    public static final int binarySearchInternal(ArraySet arraySet0, int v) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        try {
            return ContainerHelpersKt.binarySearch(arraySet0.getHashes$collection(), arraySet0.get_size$collection(), v);
        }
        catch(IndexOutOfBoundsException unused_ex) {
            throw new ConcurrentModificationException();
        }
    }

    public static final void clearInternal(ArraySet arraySet0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        if(arraySet0.get_size$collection() != 0) {
            arraySet0.setHashes$collection(ContainerHelpersKt.EMPTY_INTS);
            arraySet0.setArray$collection(ContainerHelpersKt.EMPTY_OBJECTS);
            arraySet0.set_size$collection(0);
        }
        if(arraySet0.get_size$collection() != 0) {
            throw new ConcurrentModificationException();
        }
    }

    public static final boolean containsAllInternal(ArraySet arraySet0, Collection collection0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "elements");
        for(Object object0: collection0) {
            if(!arraySet0.contains(object0)) {
                return false;
            }
            if(false) {
                break;
            }
        }
        return true;
    }

    public static final boolean containsInternal(ArraySet arraySet0, Object object0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        return arraySet0.indexOf(object0) >= 0;
    }

    public static final void ensureCapacityInternal(ArraySet arraySet0, int v) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        int v1 = arraySet0.get_size$collection();
        if(arraySet0.getHashes$collection().length < v) {
            int[] arr_v = arraySet0.getHashes$collection();
            Object[] arr_object = arraySet0.getArray$collection();
            ArraySetKt.allocArrays(arraySet0, v);
            if(arraySet0.get_size$collection() > 0) {
                ArraysKt.copyInto$default(arr_v, arraySet0.getHashes$collection(), 0, 0, arraySet0.get_size$collection(), 6, null);
                ArraysKt.copyInto$default(arr_object, arraySet0.getArray$collection(), 0, 0, arraySet0.get_size$collection(), 6, null);
            }
        }
        if(arraySet0.get_size$collection() != v1) {
            throw new ConcurrentModificationException();
        }
    }

    public static final boolean equalsInternal(ArraySet arraySet0, Object object0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        if(arraySet0 == object0) {
            return true;
        }
        if(!(object0 instanceof Set) || arraySet0.size() != ((Set)object0).size()) {
            return false;
        }
        try {
            int v = arraySet0.get_size$collection();
            for(int v1 = 0; true; ++v1) {
                if(v1 >= v) {
                    return true;
                }
                if(!((Set)object0).contains(arraySet0.valueAt(v1))) {
                    break;
                }
            }
        }
        catch(NullPointerException | ClassCastException unused_ex) {
        }
        return false;
    }

    public static final int hashCodeInternal(ArraySet arraySet0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        int[] arr_v = arraySet0.getHashes$collection();
        int v = arraySet0.get_size$collection();
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            v2 += arr_v[v1];
        }
        return v2;
    }

    public static final int indexOf(ArraySet arraySet0, Object object0, int v) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        int v1 = arraySet0.get_size$collection();
        if(v1 == 0) {
            return -1;
        }
        int v2 = ArraySetKt.binarySearchInternal(arraySet0, v);
        if(v2 < 0 || Intrinsics.areEqual(object0, arraySet0.getArray$collection()[v2])) {
            return v2;
        }
        int v3;
        for(v3 = v2 + 1; v3 < v1 && arraySet0.getHashes$collection()[v3] == v; ++v3) {
            if(Intrinsics.areEqual(object0, arraySet0.getArray$collection()[v3])) {
                return v3;
            }
        }
        for(int v4 = v2 - 1; v4 >= 0 && arraySet0.getHashes$collection()[v4] == v; --v4) {
            if(Intrinsics.areEqual(object0, arraySet0.getArray$collection()[v4])) {
                return v4;
            }
        }
        return ~v3;
    }

    public static final int indexOfInternal(ArraySet arraySet0, Object object0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        return object0 == null ? ArraySetKt.indexOfNull(arraySet0) : ArraySetKt.indexOf(arraySet0, object0, object0.hashCode());
    }

    public static final int indexOfNull(ArraySet arraySet0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        return ArraySetKt.indexOf(arraySet0, null, 0);
    }

    public static final boolean isEmptyInternal(ArraySet arraySet0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        return arraySet0.get_size$collection() <= 0;
    }

    public static final boolean removeAllInternal(ArraySet arraySet0, ArraySet arraySet1) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        Intrinsics.checkNotNullParameter(arraySet1, "array");
        int v = arraySet1.get_size$collection();
        int v1 = arraySet0.get_size$collection();
        for(int v2 = 0; v2 < v; ++v2) {
            arraySet0.remove(arraySet1.valueAt(v2));
        }
        return v1 != arraySet0.get_size$collection();
    }

    public static final boolean removeAllInternal(ArraySet arraySet0, Collection collection0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "elements");
        boolean z = false;
        for(Object object0: collection0) {
            z |= arraySet0.remove(object0);
        }
        return z;
    }

    public static final Object removeAtInternal(ArraySet arraySet0, int v) {
        Object[] arr_object1;
        int v1 = 8;
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        int v2 = arraySet0.get_size$collection();
        Object object0 = arraySet0.getArray$collection()[v];
        if(v2 <= 1) {
            arraySet0.clear();
            return object0;
        }
        if(arraySet0.getHashes$collection().length <= 8 || arraySet0.get_size$collection() >= arraySet0.getHashes$collection().length / 3) {
            if(v < v2 - 1) {
                ArraysKt.copyInto(arraySet0.getHashes$collection(), arraySet0.getHashes$collection(), v, v + 1, v2);
                ArraysKt.copyInto(arraySet0.getArray$collection(), arraySet0.getArray$collection(), v, v + 1, v2);
            }
            arraySet0.getArray$collection()[v2 - 1] = null;
        }
        else {
            if(arraySet0.get_size$collection() > 8) {
                v1 = arraySet0.get_size$collection() + (arraySet0.get_size$collection() >> 1);
            }
            int[] arr_v = arraySet0.getHashes$collection();
            Object[] arr_object = arraySet0.getArray$collection();
            ArraySetKt.allocArrays(arraySet0, v1);
            if(v > 0) {
                ArraysKt.copyInto$default(arr_v, arraySet0.getHashes$collection(), 0, 0, v, 6, null);
                arr_object1 = arr_object;
                ArraysKt.copyInto$default(arr_object1, arraySet0.getArray$collection(), 0, 0, v, 6, null);
            }
            else {
                arr_object1 = arr_object;
            }
            if(v < v2 - 1) {
                ArraysKt.copyInto(arr_v, arraySet0.getHashes$collection(), v, v + 1, v2);
                ArraysKt.copyInto(arr_object1, arraySet0.getArray$collection(), v, v + 1, v2);
            }
        }
        if(v2 != arraySet0.get_size$collection()) {
            throw new ConcurrentModificationException();
        }
        arraySet0.set_size$collection(v2 - 1);
        return object0;
    }

    public static final boolean removeInternal(ArraySet arraySet0, Object object0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        int v = arraySet0.indexOf(object0);
        if(v >= 0) {
            arraySet0.removeAt(v);
            return true;
        }
        return false;
    }

    public static final boolean retainAllInternal(ArraySet arraySet0, Collection collection0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "elements");
        int v = arraySet0.get_size$collection() - 1;
        boolean z = false;
        while(-1 < v) {
            if(!CollectionsKt.contains(collection0, arraySet0.getArray$collection()[v])) {
                arraySet0.removeAt(v);
                z = true;
            }
            --v;
        }
        return z;
    }

    public static final String toStringInternal(ArraySet arraySet0) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        if(arraySet0.isEmpty()) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder(arraySet0.get_size$collection() * 14);
        stringBuilder0.append('{');
        int v = arraySet0.get_size$collection();
        for(int v1 = 0; v1 < v; ++v1) {
            if(v1 > 0) {
                stringBuilder0.append(", ");
            }
            Object object0 = arraySet0.valueAt(v1);
            if(object0 == arraySet0) {
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

    public static final Object valueAtInternal(ArraySet arraySet0, int v) {
        Intrinsics.checkNotNullParameter(arraySet0, "<this>");
        return arraySet0.getArray$collection()[v];
    }
}

