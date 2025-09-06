package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001A\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b \n\u0002\u0010\u000E\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0003\u001A\u00020\u0004\u00A2\u0006\u0002\u0010\u0005J\u001D\u0010\u000F\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001A\u00020\u0010H\u0016J\u000E\u0010\u0016\u001A\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0016J\u0010\u0010\u0017\u001A\u00020\u00072\u0006\u0010\u0011\u001A\u00020\u0012H\u0016J\u0015\u0010\u0018\u001A\u00020\u00072\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u0019J\u0010\u0010\u001A\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0017J\u0018\u0010\u001B\u001A\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001A\u00020\u0012H\u0096\u0002\u00A2\u0006\u0002\u0010\u001CJ\u001D\u0010\u001B\u001A\u00028\u00002\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u001D\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u001EJ\u0010\u0010\u001F\u001A\u00020\u00042\u0006\u0010\u0011\u001A\u00020\u0012H\u0016J\u0015\u0010 \u001A\u00020\u00042\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010!J\b\u0010\"\u001A\u00020\u0007H\u0016J\u0010\u0010#\u001A\u00020\u00122\u0006\u0010$\u001A\u00020\u0004H\u0016J\u001D\u0010%\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u0014J\u0018\u0010&\u001A\u00020\u00102\u000E\u0010\'\u001A\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u0000H\u0016J\u001F\u0010(\u001A\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u001EJ\u0010\u0010)\u001A\u00020\u00102\u0006\u0010\u0011\u001A\u00020\u0012H\u0016J\u001D\u0010)\u001A\u00020\u00072\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010*J\u0010\u0010+\u001A\u00020\u00102\u0006\u0010$\u001A\u00020\u0004H\u0016J\u001F\u0010,\u001A\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010\u001EJ%\u0010,\u001A\u00020\u00072\u0006\u0010\u0011\u001A\u00020\u00122\u0006\u0010-\u001A\u00028\u00002\u0006\u0010.\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u0010/J\u001D\u00100\u001A\u00020\u00102\u0006\u0010$\u001A\u00020\u00042\u0006\u0010\u0013\u001A\u00028\u0000H\u0016\u00A2\u0006\u0002\u00101J\b\u0010\n\u001A\u00020\u0004H\u0016J\b\u00102\u001A\u000203H\u0016J\u0015\u00104\u001A\u00028\u00002\u0006\u0010$\u001A\u00020\u0004H\u0016\u00A2\u0006\u0002\u00105R\u0012\u0010\u0006\u001A\u00020\u00078\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\b\u001A\u00020\t8\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u0012\u0010\n\u001A\u00020\u00048\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0002\n\u0000R\u001C\u0010\u000B\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f8\u0000@\u0000X\u0081\u000E\u00A2\u0006\u0004\n\u0002\u0010\u000E\u00A8\u00066"}, d2 = {"Landroidx/collection/LongSparseArray;", "E", "", "initialCapacity", "", "(I)V", "garbage", "", "keys", "", "size", "values", "", "", "[Ljava/lang/Object;", "append", "", "key", "", "value", "(JLjava/lang/Object;)V", "clear", "clone", "containsKey", "containsValue", "(Ljava/lang/Object;)Z", "delete", "get", "(J)Ljava/lang/Object;", "defaultValue", "(JLjava/lang/Object;)Ljava/lang/Object;", "indexOfKey", "indexOfValue", "(Ljava/lang/Object;)I", "isEmpty", "keyAt", "index", "put", "putAll", "other", "putIfAbsent", "remove", "(JLjava/lang/Object;)Z", "removeAt", "replace", "oldValue", "newValue", "(JLjava/lang/Object;Ljava/lang/Object;)Z", "setValueAt", "(ILjava/lang/Object;)V", "toString", "", "valueAt", "(I)Ljava/lang/Object;", "collection"}, k = 1, mv = {1, 8, 0}, xi = 0x30)
public class LongSparseArray implements Cloneable {
    public boolean garbage;
    public long[] keys;
    public int size;
    public Object[] values;

    public LongSparseArray() {
        this(0, 1, null);
    }

    public LongSparseArray(int v) {
        if(v == 0) {
            this.keys = ContainerHelpersKt.EMPTY_LONGS;
            this.values = ContainerHelpersKt.EMPTY_OBJECTS;
            return;
        }
        int v1 = ContainerHelpersKt.idealLongArraySize(v);
        this.keys = new long[v1];
        this.values = new Object[v1];
    }

    public LongSparseArray(int v, int v1, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v1 & 1) != 0) {
            v = 10;
        }
        this(v);
    }

    public void append(long v, Object object0) {
        int v1 = this.size;
        if(v1 != 0 && v <= this.keys[v1 - 1]) {
            this.put(v, object0);
            return;
        }
        if(this.garbage) {
            long[] arr_v = this.keys;
            if(v1 >= arr_v.length) {
                Object[] arr_object = this.values;
                int v3 = 0;
                for(int v2 = 0; v2 < v1; ++v2) {
                    Object object1 = arr_object[v2];
                    if(object1 != LongSparseArrayKt.DELETED) {
                        if(v2 != v3) {
                            arr_v[v3] = arr_v[v2];
                            arr_object[v3] = object1;
                            arr_object[v2] = null;
                        }
                        ++v3;
                    }
                }
                this.garbage = false;
                this.size = v3;
            }
        }
        int v4 = this.size;
        if(v4 >= this.keys.length) {
            int v5 = ContainerHelpersKt.idealLongArraySize(v4 + 1);
            long[] arr_v1 = Arrays.copyOf(this.keys, v5);
            Intrinsics.checkNotNullExpressionValue(arr_v1, "copyOf(this, newSize)");
            this.keys = arr_v1;
            Object[] arr_object1 = Arrays.copyOf(this.values, v5);
            Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
            this.values = arr_object1;
        }
        this.keys[v4] = v;
        this.values[v4] = object0;
        this.size = v4 + 1;
    }

    public void clear() {
        int v = this.size;
        Object[] arr_object = this.values;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_object[v1] = null;
        }
        this.size = 0;
        this.garbage = false;
    }

    public LongSparseArray clone() {
        Object object0 = super.clone();
        Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type androidx.collection.LongSparseArray<E of androidx.collection.LongSparseArray>");
        ((LongSparseArray)object0).keys = (long[])this.keys.clone();
        ((LongSparseArray)object0).values = (Object[])this.values.clone();
        return (LongSparseArray)object0;
    }

    @Override
    public Object clone() {
        return this.clone();
    }

    public boolean containsKey(long v) {
        return this.indexOfKey(v) >= 0;
    }

    public boolean containsValue(Object object0) {
        return this.indexOfValue(object0) >= 0;
    }

    @Deprecated(message = "Alias for `remove(key)`.", replaceWith = @ReplaceWith(expression = "remove(key)", imports = {}))
    public void delete(long v) {
        int v1 = ContainerHelpersKt.binarySearch(this.keys, this.size, v);
        if(v1 >= 0 && this.values[v1] != LongSparseArrayKt.DELETED) {
            this.values[v1] = LongSparseArrayKt.DELETED;
            this.garbage = true;
        }
    }

    public Object get(long v) {
        int v1 = ContainerHelpersKt.binarySearch(this.keys, this.size, v);
        return v1 < 0 || this.values[v1] == LongSparseArrayKt.DELETED ? null : this.values[v1];
    }

    public Object get(long v, Object object0) {
        int v1 = ContainerHelpersKt.binarySearch(this.keys, this.size, v);
        return v1 < 0 || this.values[v1] == LongSparseArrayKt.DELETED ? object0 : this.values[v1];
    }

    public int indexOfKey(long v) {
        if(this.garbage) {
            int v1 = this.size;
            long[] arr_v = this.keys;
            Object[] arr_object = this.values;
            int v3 = 0;
            for(int v2 = 0; v2 < v1; ++v2) {
                Object object0 = arr_object[v2];
                if(object0 != LongSparseArrayKt.DELETED) {
                    if(v2 != v3) {
                        arr_v[v3] = arr_v[v2];
                        arr_object[v3] = object0;
                        arr_object[v2] = null;
                    }
                    ++v3;
                }
            }
            this.garbage = false;
            this.size = v3;
        }
        return ContainerHelpersKt.binarySearch(this.keys, this.size, v);
    }

    public int indexOfValue(Object object0) {
        if(this.garbage) {
            int v1 = this.size;
            long[] arr_v = this.keys;
            Object[] arr_object = this.values;
            int v3 = 0;
            for(int v2 = 0; v2 < v1; ++v2) {
                Object object1 = arr_object[v2];
                if(object1 != LongSparseArrayKt.DELETED) {
                    if(v2 != v3) {
                        arr_v[v3] = arr_v[v2];
                        arr_object[v3] = object1;
                        arr_object[v2] = null;
                    }
                    ++v3;
                }
            }
            this.garbage = false;
            this.size = v3;
        }
        int v4 = this.size;
        for(int v = 0; v < v4; ++v) {
            if(this.values[v] == object0) {
                return v;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public long keyAt(int v) {
        if(v >= 0) {
            int v1 = this.size;
            if(v < v1) {
                if(this.garbage) {
                    long[] arr_v = this.keys;
                    Object[] arr_object = this.values;
                    int v3 = 0;
                    for(int v2 = 0; v2 < v1; ++v2) {
                        Object object0 = arr_object[v2];
                        if(object0 != LongSparseArrayKt.DELETED) {
                            if(v2 != v3) {
                                arr_v[v3] = arr_v[v2];
                                arr_object[v3] = object0;
                                arr_object[v2] = null;
                            }
                            ++v3;
                        }
                    }
                    this.garbage = false;
                    this.size = v3;
                }
                return this.keys[v];
            }
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
    }

    public void put(long v, Object object0) {
        int v1 = ContainerHelpersKt.binarySearch(this.keys, this.size, v);
        if(v1 >= 0) {
            this.values[v1] = object0;
            return;
        }
        int v2 = ~v1;
        if(v2 < this.size && this.values[v2] == LongSparseArrayKt.DELETED) {
            this.keys[v2] = v;
            this.values[v2] = object0;
            return;
        }
        if(this.garbage) {
            int v3 = this.size;
            long[] arr_v = this.keys;
            if(v3 >= arr_v.length) {
                Object[] arr_object = this.values;
                int v5 = 0;
                for(int v4 = 0; v4 < v3; ++v4) {
                    Object object1 = arr_object[v4];
                    if(object1 != LongSparseArrayKt.DELETED) {
                        if(v4 != v5) {
                            arr_v[v5] = arr_v[v4];
                            arr_object[v5] = object1;
                            arr_object[v4] = null;
                        }
                        ++v5;
                    }
                }
                this.garbage = false;
                this.size = v5;
                v2 = ~ContainerHelpersKt.binarySearch(this.keys, v5, v);
            }
        }
        int v6 = this.size;
        if(v6 >= this.keys.length) {
            int v7 = ContainerHelpersKt.idealLongArraySize(v6 + 1);
            long[] arr_v1 = Arrays.copyOf(this.keys, v7);
            Intrinsics.checkNotNullExpressionValue(arr_v1, "copyOf(this, newSize)");
            this.keys = arr_v1;
            Object[] arr_object1 = Arrays.copyOf(this.values, v7);
            Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
            this.values = arr_object1;
        }
        int v8 = this.size;
        if(v8 - v2 != 0) {
            ArraysKt.copyInto(this.keys, this.keys, v2 + 1, v2, v8);
            ArraysKt.copyInto(this.values, this.values, v2 + 1, v2, this.size);
        }
        this.keys[v2] = v;
        this.values[v2] = object0;
        ++this.size;
    }

    public void putAll(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "other");
        int v = longSparseArray0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            this.put(longSparseArray0.keyAt(v1), longSparseArray0.valueAt(v1));
        }
    }

    public Object putIfAbsent(long v, Object object0) {
        Object object1 = this.get(v);
        if(object1 == null) {
            this.put(v, object0);
        }
        return object1;
    }

    public void remove(long v) {
        int v1 = ContainerHelpersKt.binarySearch(this.keys, this.size, v);
        if(v1 >= 0 && this.values[v1] != LongSparseArrayKt.DELETED) {
            this.values[v1] = LongSparseArrayKt.DELETED;
            this.garbage = true;
        }
    }

    public boolean remove(long v, Object object0) {
        int v1 = this.indexOfKey(v);
        if(v1 >= 0 && Intrinsics.areEqual(object0, this.valueAt(v1))) {
            this.removeAt(v1);
            return true;
        }
        return false;
    }

    public void removeAt(int v) {
        if(this.values[v] != LongSparseArrayKt.DELETED) {
            this.values[v] = LongSparseArrayKt.DELETED;
            this.garbage = true;
        }
    }

    public Object replace(long v, Object object0) {
        int v1 = this.indexOfKey(v);
        if(v1 >= 0) {
            Object[] arr_object = this.values;
            Object object1 = arr_object[v1];
            arr_object[v1] = object0;
            return object1;
        }
        return null;
    }

    public boolean replace(long v, Object object0, Object object1) {
        int v1 = this.indexOfKey(v);
        if(v1 >= 0 && Intrinsics.areEqual(this.values[v1], object0)) {
            this.values[v1] = object1;
            return true;
        }
        return false;
    }

    public void setValueAt(int v, Object object0) {
        if(v >= 0) {
            int v1 = this.size;
            if(v < v1) {
                if(this.garbage) {
                    long[] arr_v = this.keys;
                    Object[] arr_object = this.values;
                    int v3 = 0;
                    for(int v2 = 0; v2 < v1; ++v2) {
                        Object object1 = arr_object[v2];
                        if(object1 != LongSparseArrayKt.DELETED) {
                            if(v2 != v3) {
                                arr_v[v3] = arr_v[v2];
                                arr_object[v3] = object1;
                                arr_object[v2] = null;
                            }
                            ++v3;
                        }
                    }
                    this.garbage = false;
                    this.size = v3;
                }
                this.values[v] = object0;
                return;
            }
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
    }

    public int size() {
        if(this.garbage) {
            int v = this.size;
            long[] arr_v = this.keys;
            Object[] arr_object = this.values;
            int v2 = 0;
            for(int v1 = 0; v1 < v; ++v1) {
                Object object0 = arr_object[v1];
                if(object0 != LongSparseArrayKt.DELETED) {
                    if(v1 != v2) {
                        arr_v[v2] = arr_v[v1];
                        arr_object[v2] = object0;
                        arr_object[v1] = null;
                    }
                    ++v2;
                }
            }
            this.garbage = false;
            this.size = v2;
        }
        return this.size;
    }

    @Override
    public String toString() {
        if(this.size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder(this.size * 28);
        stringBuilder0.append('{');
        int v = this.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(v1 > 0) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(this.keyAt(v1));
            stringBuilder0.append('=');
            Object object0 = this.valueAt(v1);
            if(object0 == stringBuilder0) {
                stringBuilder0.append("(this Map)");
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

    public Object valueAt(int v) {
        if(v >= 0) {
            int v1 = this.size;
            if(v < v1) {
                if(this.garbage) {
                    long[] arr_v = this.keys;
                    Object[] arr_object = this.values;
                    int v3 = 0;
                    for(int v2 = 0; v2 < v1; ++v2) {
                        Object object0 = arr_object[v2];
                        if(object0 != LongSparseArrayKt.DELETED) {
                            if(v2 != v3) {
                                arr_v[v3] = arr_v[v2];
                                arr_object[v3] = object0;
                                arr_object[v2] = null;
                            }
                            ++v3;
                        }
                    }
                    this.garbage = false;
                    this.size = v3;
                }
                return this.values[v];
            }
        }
        throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
    }
}

