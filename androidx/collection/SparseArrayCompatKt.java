package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u001B\n\u0002\u0010\u000E\n\u0002\b\u0005\u001A.\u0010\u0002\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010\t\u001A\u0019\u0010\n\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u0080\b\u001A!\u0010\u000B\u001A\u00020\f\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0080\b\u001A&\u0010\r\u001A\u00020\f\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\b\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010\u000E\u001A\'\u0010\u000F\u001A\u0004\u0018\u0001H\u0004\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0000\u00A2\u0006\u0002\u0010\u0010\u001A-\u0010\u000F\u001A\u0002H\u0004\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\u0011\u001A\u0002H\u0004H\u0000\u00A2\u0006\u0002\u0010\u0012\u001A!\u0010\u0013\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0080\b\u001A&\u0010\u0014\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\b\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010\u0015\u001A\u0019\u0010\u0016\u001A\u00020\f\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u0080\b\u001A!\u0010\u0017\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0018\u001A\u00020\u0007H\u0080\b\u001A.\u0010\u0019\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010\t\u001A)\u0010\u001A\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u000E\u0010\u001B\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00040\u0005H\u0080\b\u001A0\u0010\u001C\u001A\u0004\u0018\u0001H\u0004\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010\u0012\u001A \u0010\u001D\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u0007H\u0000\u001A+\u0010\u001D\u001A\u00020\f\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u00072\b\u0010\b\u001A\u0004\u0018\u00010\u0001H\u0080\b\u001A!\u0010\u001E\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0018\u001A\u00020\u0007H\u0080\b\u001A)\u0010\u001F\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0018\u001A\u00020\u00072\u0006\u0010 \u001A\u00020\u0007H\u0080\b\u001A0\u0010!\u001A\u0004\u0018\u0001H\u0004\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\b\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010\u0012\u001A6\u0010!\u001A\u00020\f\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\"\u001A\u0002H\u00042\u0006\u0010#\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010$\u001A.\u0010%\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0018\u001A\u00020\u00072\u0006\u0010\b\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010\t\u001A\u0019\u0010&\u001A\u00020\u0007\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u0080\b\u001A\u0019\u0010\'\u001A\u00020(\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u0080\b\u001A&\u0010)\u001A\u0002H\u0004\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0018\u001A\u00020\u0007H\u0080\b\u00A2\u0006\u0002\u0010\u0010\u001A\u0018\u0010*\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u0002\u001A:\u0010+\u001A\u0002H,\"\u0004\b\u0000\u0010\u0004\"\n\b\u0001\u0010,*\u0004\u0018\u0001H\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\u0006\u001A\u00020\u00072\u0006\u0010\u0011\u001A\u0002H,H\u0082\b\u00A2\u0006\u0002\u0010\u0012\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006-"}, d2 = {"DELETED", "", "commonAppend", "", "E", "Landroidx/collection/SparseArrayCompat;", "key", "", "value", "(Landroidx/collection/SparseArrayCompat;ILjava/lang/Object;)V", "commonClear", "commonContainsKey", "", "commonContainsValue", "(Landroidx/collection/SparseArrayCompat;Ljava/lang/Object;)Z", "commonGet", "(Landroidx/collection/SparseArrayCompat;I)Ljava/lang/Object;", "defaultValue", "(Landroidx/collection/SparseArrayCompat;ILjava/lang/Object;)Ljava/lang/Object;", "commonIndexOfKey", "commonIndexOfValue", "(Landroidx/collection/SparseArrayCompat;Ljava/lang/Object;)I", "commonIsEmpty", "commonKeyAt", "index", "commonPut", "commonPutAll", "other", "commonPutIfAbsent", "commonRemove", "commonRemoveAt", "commonRemoveAtRange", "size", "commonReplace", "oldValue", "newValue", "(Landroidx/collection/SparseArrayCompat;ILjava/lang/Object;Ljava/lang/Object;)Z", "commonSetValueAt", "commonSize", "commonToString", "", "commonValueAt", "gc", "internalGet", "T", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class SparseArrayCompatKt {
    private static final Object DELETED;

    static {
        SparseArrayCompatKt.DELETED = new Object();
    }

    public static final void commonAppend(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.size != 0 && v <= sparseArrayCompat0.keys[sparseArrayCompat0.size - 1]) {
            sparseArrayCompat0.put(v, object0);
            return;
        }
        if(sparseArrayCompat0.garbage && sparseArrayCompat0.size >= sparseArrayCompat0.keys.length) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
        }
        int v1 = sparseArrayCompat0.size;
        if(v1 >= sparseArrayCompat0.keys.length) {
            int v2 = ContainerHelpersKt.idealIntArraySize(v1 + 1);
            int[] arr_v = Arrays.copyOf(sparseArrayCompat0.keys, v2);
            Intrinsics.checkNotNullExpressionValue(arr_v, "copyOf(this, newSize)");
            sparseArrayCompat0.keys = arr_v;
            Object[] arr_object = Arrays.copyOf(sparseArrayCompat0.values, v2);
            Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
            sparseArrayCompat0.values = arr_object;
        }
        sparseArrayCompat0.keys[v1] = v;
        sparseArrayCompat0.values[v1] = object0;
        sparseArrayCompat0.size = v1 + 1;
    }

    public static final void commonClear(SparseArrayCompat sparseArrayCompat0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v = sparseArrayCompat0.size;
        Object[] arr_object = sparseArrayCompat0.values;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_object[v1] = null;
        }
        sparseArrayCompat0.size = 0;
        sparseArrayCompat0.garbage = false;
    }

    public static final boolean commonContainsKey(SparseArrayCompat sparseArrayCompat0, int v) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return sparseArrayCompat0.indexOfKey(v) >= 0;
    }

    public static final boolean commonContainsValue(SparseArrayCompat sparseArrayCompat0, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.garbage) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
        }
        int v = sparseArrayCompat0.size;
        int v1;
        for(v1 = 0; true; ++v1) {
            if(v1 >= v) {
                v1 = -1;
                break;
            }
            if(sparseArrayCompat0.values[v1] == object0) {
                break;
            }
        }
        return v1 >= 0;
    }

    public static final Object commonGet(SparseArrayCompat sparseArrayCompat0, int v) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
        return v1 < 0 || sparseArrayCompat0.values[v1] == SparseArrayCompatKt.DELETED ? null : sparseArrayCompat0.values[v1];
    }

    public static final Object commonGet(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
        return v1 < 0 || sparseArrayCompat0.values[v1] == SparseArrayCompatKt.DELETED ? object0 : sparseArrayCompat0.values[v1];
    }

    public static final int commonIndexOfKey(SparseArrayCompat sparseArrayCompat0, int v) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.garbage) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
        }
        return ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
    }

    public static final int commonIndexOfValue(SparseArrayCompat sparseArrayCompat0, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.garbage) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
        }
        int v = sparseArrayCompat0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(sparseArrayCompat0.values[v1] == object0) {
                return v1;
            }
        }
        return -1;
    }

    public static final boolean commonIsEmpty(SparseArrayCompat sparseArrayCompat0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        return sparseArrayCompat0.size() == 0;
    }

    public static final int commonKeyAt(SparseArrayCompat sparseArrayCompat0, int v) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.garbage) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
        }
        return sparseArrayCompat0.keys[v];
    }

    public static final void commonPut(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
        if(v1 >= 0) {
            sparseArrayCompat0.values[v1] = object0;
            return;
        }
        int v2 = ~v1;
        if(v2 < sparseArrayCompat0.size && sparseArrayCompat0.values[v2] == SparseArrayCompatKt.DELETED) {
            sparseArrayCompat0.keys[v2] = v;
            sparseArrayCompat0.values[v2] = object0;
            return;
        }
        if(sparseArrayCompat0.garbage && sparseArrayCompat0.size >= sparseArrayCompat0.keys.length) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
            v2 = ~ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
        }
        if(sparseArrayCompat0.size >= sparseArrayCompat0.keys.length) {
            int v3 = ContainerHelpersKt.idealIntArraySize(sparseArrayCompat0.size + 1);
            int[] arr_v = Arrays.copyOf(sparseArrayCompat0.keys, v3);
            Intrinsics.checkNotNullExpressionValue(arr_v, "copyOf(this, newSize)");
            sparseArrayCompat0.keys = arr_v;
            Object[] arr_object = Arrays.copyOf(sparseArrayCompat0.values, v3);
            Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
            sparseArrayCompat0.values = arr_object;
        }
        if(sparseArrayCompat0.size - v2 != 0) {
            ArraysKt.copyInto(sparseArrayCompat0.keys, sparseArrayCompat0.keys, v2 + 1, v2, sparseArrayCompat0.size);
            ArraysKt.copyInto(sparseArrayCompat0.values, sparseArrayCompat0.values, v2 + 1, v2, sparseArrayCompat0.size);
        }
        sparseArrayCompat0.keys[v2] = v;
        sparseArrayCompat0.values[v2] = object0;
        ++sparseArrayCompat0.size;
    }

    public static final void commonPutAll(SparseArrayCompat sparseArrayCompat0, SparseArrayCompat sparseArrayCompat1) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        Intrinsics.checkNotNullParameter(sparseArrayCompat1, "other");
        int v = sparseArrayCompat1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            int v2 = sparseArrayCompat1.keyAt(v1);
            Object object0 = sparseArrayCompat1.valueAt(v1);
            int v3 = ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v2);
            if(v3 >= 0) {
                sparseArrayCompat0.values[v3] = object0;
            }
            else {
                int v4 = ~v3;
                if(v4 >= sparseArrayCompat0.size || sparseArrayCompat0.values[v4] != SparseArrayCompatKt.DELETED) {
                    if(sparseArrayCompat0.garbage && sparseArrayCompat0.size >= sparseArrayCompat0.keys.length) {
                        SparseArrayCompatKt.gc(sparseArrayCompat0);
                        v4 = ~ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v2);
                    }
                    if(sparseArrayCompat0.size >= sparseArrayCompat0.keys.length) {
                        int v5 = ContainerHelpersKt.idealIntArraySize(sparseArrayCompat0.size + 1);
                        int[] arr_v = Arrays.copyOf(sparseArrayCompat0.keys, v5);
                        Intrinsics.checkNotNullExpressionValue(arr_v, "copyOf(this, newSize)");
                        sparseArrayCompat0.keys = arr_v;
                        Object[] arr_object = Arrays.copyOf(sparseArrayCompat0.values, v5);
                        Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
                        sparseArrayCompat0.values = arr_object;
                    }
                    if(sparseArrayCompat0.size - v4 != 0) {
                        ArraysKt.copyInto(sparseArrayCompat0.keys, sparseArrayCompat0.keys, v4 + 1, v4, sparseArrayCompat0.size);
                        ArraysKt.copyInto(sparseArrayCompat0.values, sparseArrayCompat0.values, v4 + 1, v4, sparseArrayCompat0.size);
                    }
                    sparseArrayCompat0.keys[v4] = v2;
                    sparseArrayCompat0.values[v4] = object0;
                    ++sparseArrayCompat0.size;
                }
                else {
                    sparseArrayCompat0.keys[v4] = v2;
                    sparseArrayCompat0.values[v4] = object0;
                }
            }
        }
    }

    public static final Object commonPutIfAbsent(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        Object object1 = SparseArrayCompatKt.commonGet(sparseArrayCompat0, v);
        if(object1 == null) {
            int v1 = ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
            if(v1 >= 0) {
                sparseArrayCompat0.values[v1] = object0;
                return null;
            }
            int v2 = ~v1;
            if(v2 < sparseArrayCompat0.size && sparseArrayCompat0.values[v2] == SparseArrayCompatKt.DELETED) {
                sparseArrayCompat0.keys[v2] = v;
                sparseArrayCompat0.values[v2] = object0;
                return null;
            }
            if(sparseArrayCompat0.garbage && sparseArrayCompat0.size >= sparseArrayCompat0.keys.length) {
                SparseArrayCompatKt.gc(sparseArrayCompat0);
                v2 = ~ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
            }
            if(sparseArrayCompat0.size >= sparseArrayCompat0.keys.length) {
                int v3 = ContainerHelpersKt.idealIntArraySize(sparseArrayCompat0.size + 1);
                int[] arr_v = Arrays.copyOf(sparseArrayCompat0.keys, v3);
                Intrinsics.checkNotNullExpressionValue(arr_v, "copyOf(this, newSize)");
                sparseArrayCompat0.keys = arr_v;
                Object[] arr_object = Arrays.copyOf(sparseArrayCompat0.values, v3);
                Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(this, newSize)");
                sparseArrayCompat0.values = arr_object;
            }
            if(sparseArrayCompat0.size - v2 != 0) {
                ArraysKt.copyInto(sparseArrayCompat0.keys, sparseArrayCompat0.keys, v2 + 1, v2, sparseArrayCompat0.size);
                ArraysKt.copyInto(sparseArrayCompat0.values, sparseArrayCompat0.values, v2 + 1, v2, sparseArrayCompat0.size);
            }
            sparseArrayCompat0.keys[v2] = v;
            sparseArrayCompat0.values[v2] = object0;
            ++sparseArrayCompat0.size;
        }
        return object1;
    }

    public static final void commonRemove(SparseArrayCompat sparseArrayCompat0, int v) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
        if(v1 >= 0) {
            Object object0 = SparseArrayCompatKt.DELETED;
            if(sparseArrayCompat0.values[v1] != object0) {
                sparseArrayCompat0.values[v1] = object0;
                sparseArrayCompat0.garbage = true;
            }
        }
    }

    public static final boolean commonRemove(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v1 = sparseArrayCompat0.indexOfKey(v);
        if(v1 >= 0 && Intrinsics.areEqual(object0, sparseArrayCompat0.valueAt(v1))) {
            sparseArrayCompat0.removeAt(v1);
            return true;
        }
        return false;
    }

    public static final void commonRemoveAt(SparseArrayCompat sparseArrayCompat0, int v) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.values[v] != SparseArrayCompatKt.DELETED) {
            sparseArrayCompat0.values[v] = SparseArrayCompatKt.DELETED;
            sparseArrayCompat0.garbage = true;
        }
    }

    public static final void commonRemoveAtRange(SparseArrayCompat sparseArrayCompat0, int v, int v1) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v2 = Math.min(v1, v + v1);
        while(v < v2) {
            sparseArrayCompat0.removeAt(v);
            ++v;
        }
    }

    public static final Object commonReplace(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v1 = sparseArrayCompat0.indexOfKey(v);
        if(v1 >= 0) {
            Object object1 = sparseArrayCompat0.values[v1];
            sparseArrayCompat0.values[v1] = object0;
            return object1;
        }
        return null;
    }

    public static final boolean commonReplace(SparseArrayCompat sparseArrayCompat0, int v, Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        int v1 = sparseArrayCompat0.indexOfKey(v);
        if(v1 >= 0 && Intrinsics.areEqual(sparseArrayCompat0.values[v1], object0)) {
            sparseArrayCompat0.values[v1] = object1;
            return true;
        }
        return false;
    }

    public static final void commonSetValueAt(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.garbage) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
        }
        sparseArrayCompat0.values[v] = object0;
    }

    public static final int commonSize(SparseArrayCompat sparseArrayCompat0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.garbage) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
        }
        return sparseArrayCompat0.size;
    }

    public static final String commonToString(SparseArrayCompat sparseArrayCompat0) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder(sparseArrayCompat0.size * 28);
        stringBuilder0.append('{');
        int v = sparseArrayCompat0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(v1 > 0) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(sparseArrayCompat0.keyAt(v1));
            stringBuilder0.append('=');
            Object object0 = sparseArrayCompat0.valueAt(v1);
            if(object0 == sparseArrayCompat0) {
                stringBuilder0.append("(this Map)");
            }
            else {
                stringBuilder0.append(object0);
            }
        }
        stringBuilder0.append('}');
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "buffer.toString()");
        return s;
    }

    public static final Object commonValueAt(SparseArrayCompat sparseArrayCompat0, int v) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat0, "<this>");
        if(sparseArrayCompat0.garbage) {
            SparseArrayCompatKt.gc(sparseArrayCompat0);
        }
        return sparseArrayCompat0.values[v];
    }

    private static final void gc(SparseArrayCompat sparseArrayCompat0) {
        int v = sparseArrayCompat0.size;
        int[] arr_v = sparseArrayCompat0.keys;
        Object[] arr_object = sparseArrayCompat0.values;
        int v2 = 0;
        for(int v1 = 0; v1 < v; ++v1) {
            Object object0 = arr_object[v1];
            if(object0 != SparseArrayCompatKt.DELETED) {
                if(v1 != v2) {
                    arr_v[v2] = arr_v[v1];
                    arr_object[v2] = object0;
                    arr_object[v1] = null;
                }
                ++v2;
            }
        }
        sparseArrayCompat0.garbage = false;
        sparseArrayCompat0.size = v2;
    }

    private static final Object internalGet(SparseArrayCompat sparseArrayCompat0, int v, Object object0) {
        int v1 = ContainerHelpersKt.binarySearch(sparseArrayCompat0.keys, sparseArrayCompat0.size, v);
        return v1 < 0 || sparseArrayCompat0.values[v1] == SparseArrayCompatKt.DELETED ? object0 : sparseArrayCompat0.values[v1];
    }
}

