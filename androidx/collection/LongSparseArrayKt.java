package androidx.collection;

import androidx.collection.internal.ContainerHelpersKt;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u001D\n\u0002\u0010\u000E\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010(\n\u0000\u001A.\u0010\n\u001A\u00020\u000B\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010\u0010\u001A\u0019\u0010\u0011\u001A\u00020\u000B\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u0005H\u0080\b\u001A!\u0010\u0012\u001A\u00020\u0013\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000EH\u0080\b\u001A&\u0010\u0014\u001A\u00020\u0013\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\u000F\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010\u0015\u001A\u0019\u0010\u0016\u001A\u00020\u000B\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u0005H\u0080\b\u001A(\u0010\u0017\u001A\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000EH\u0080\b\u00A2\u0006\u0002\u0010\u0018\u001A.\u0010\u0017\u001A\u0002H\f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0019\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010\u001A\u001A:\u0010\u001B\u001A\u0002H\u0004\"\n\b\u0000\u0010\u0004*\u0004\u0018\u0001H\f\"\u0004\b\u0001\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0019\u001A\u0002H\u0004H\u0080\b\u00A2\u0006\u0002\u0010\u001A\u001A!\u0010\u001C\u001A\u00020\u0003\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000EH\u0080\b\u001A&\u0010\u001D\u001A\u00020\u0003\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\u000F\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010\u001E\u001A\u0019\u0010\u001F\u001A\u00020\u0013\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u0005H\u0080\b\u001A!\u0010 \u001A\u00020\u000E\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010!\u001A\u00020\u0003H\u0080\b\u001A.\u0010\"\u001A\u00020\u000B\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010\u0010\u001A)\u0010#\u001A\u00020\u000B\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u000E\u0010$\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\f0\u0005H\u0080\b\u001A0\u0010%\u001A\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010\u001A\u001A!\u0010&\u001A\u00020\u000B\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000EH\u0080\b\u001A.\u0010&\u001A\u00020\u0013\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010\'\u001A!\u0010(\u001A\u00020\u000B\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010!\u001A\u00020\u0003H\u0080\b\u001A0\u0010)\u001A\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010\u001A\u001A6\u0010)\u001A\u00020\u0013\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010*\u001A\u0002H\f2\u0006\u0010+\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010,\u001A.\u0010-\u001A\u00020\u000B\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010!\u001A\u00020\u00032\u0006\u0010\u000F\u001A\u0002H\fH\u0080\b\u00A2\u0006\u0002\u0010.\u001A\u0019\u0010/\u001A\u00020\u0003\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u0005H\u0080\b\u001A\u0019\u00100\u001A\u000201\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u0005H\u0080\b\u001A&\u00102\u001A\u0002H\f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00052\u0006\u0010!\u001A\u00020\u0003H\u0080\b\u00A2\u0006\u0002\u00103\u001A!\u00104\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\r\u001A\u00020\u000EH\u0086\n\u001AT\u00105\u001A\u00020\u000B\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u000526\u00106\u001A2\u0012\u0013\u0012\u00110\u000E\u00A2\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(\r\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b8\u0012\b\b9\u0012\u0004\b\b(\u000F\u0012\u0004\u0012\u00020\u000B07H\u0086\b\u00F8\u0001\u0000\u001A.\u0010:\u001A\u0002H\u0004\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u0019\u001A\u0002H\u0004H\u0086\b\u00A2\u0006\u0002\u0010\u001A\u001A7\u0010;\u001A\u0002H\u0004\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\r\u001A\u00020\u000E2\f\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u00040<H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010=\u001A\u0019\u0010>\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u0086\b\u001A\u0016\u0010?\u001A\u00020@\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005\u001A-\u0010A\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0005\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\f\u0010$\u001A\b\u0012\u0004\u0012\u0002H\u00040\u0005H\u0086\u0002\u001A-\u0010B\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u0002H\u0004H\u0007\u00A2\u0006\u0002\u0010\'\u001A.\u0010C\u001A\u00020\u000B\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00052\u0006\u0010\r\u001A\u00020\u000E2\u0006\u0010\u000F\u001A\u0002H\u0004H\u0086\n\u00A2\u0006\u0002\u0010\u0010\u001A\u001C\u0010D\u001A\b\u0012\u0004\u0012\u0002H\u00040E\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\"(\u0010\u0002\u001A\u00020\u0003\"\u0004\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u00058\u00C6\u0002\u00A2\u0006\f\u0012\u0004\b\u0006\u0010\u0007\u001A\u0004\b\b\u0010\t\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006F"}, d2 = {"DELETED", "", "size", "", "T", "Landroidx/collection/LongSparseArray;", "getSize$annotations", "(Landroidx/collection/LongSparseArray;)V", "getSize", "(Landroidx/collection/LongSparseArray;)I", "commonAppend", "", "E", "key", "", "value", "(Landroidx/collection/LongSparseArray;JLjava/lang/Object;)V", "commonClear", "commonContainsKey", "", "commonContainsValue", "(Landroidx/collection/LongSparseArray;Ljava/lang/Object;)Z", "commonGc", "commonGet", "(Landroidx/collection/LongSparseArray;J)Ljava/lang/Object;", "defaultValue", "(Landroidx/collection/LongSparseArray;JLjava/lang/Object;)Ljava/lang/Object;", "commonGetInternal", "commonIndexOfKey", "commonIndexOfValue", "(Landroidx/collection/LongSparseArray;Ljava/lang/Object;)I", "commonIsEmpty", "commonKeyAt", "index", "commonPut", "commonPutAll", "other", "commonPutIfAbsent", "commonRemove", "(Landroidx/collection/LongSparseArray;JLjava/lang/Object;)Z", "commonRemoveAt", "commonReplace", "oldValue", "newValue", "(Landroidx/collection/LongSparseArray;JLjava/lang/Object;Ljava/lang/Object;)Z", "commonSetValueAt", "(Landroidx/collection/LongSparseArray;ILjava/lang/Object;)V", "commonSize", "commonToString", "", "commonValueAt", "(Landroidx/collection/LongSparseArray;I)Ljava/lang/Object;", "contains", "forEach", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "getOrElse", "Lkotlin/Function0;", "(Landroidx/collection/LongSparseArray;JLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "keyIterator", "Lkotlin/collections/LongIterator;", "plus", "remove", "set", "valueIterator", "", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LongSparseArrayKt {
    private static final Object DELETED;

    static {
        LongSparseArrayKt.DELETED = new Object();
    }

    public static final void commonAppend(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(longSparseArray0.size != 0 && v <= longSparseArray0.keys[longSparseArray0.size - 1]) {
            longSparseArray0.put(v, object0);
            return;
        }
        if(longSparseArray0.garbage && longSparseArray0.size >= longSparseArray0.keys.length) {
            int v1 = longSparseArray0.size;
            long[] arr_v = longSparseArray0.keys;
            Object[] arr_object = longSparseArray0.values;
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
            longSparseArray0.garbage = false;
            longSparseArray0.size = v3;
        }
        int v4 = longSparseArray0.size;
        if(v4 >= longSparseArray0.keys.length) {
            int v5 = ContainerHelpersKt.idealLongArraySize(v4 + 1);
            long[] arr_v1 = Arrays.copyOf(longSparseArray0.keys, v5);
            Intrinsics.checkNotNullExpressionValue(arr_v1, "copyOf(this, newSize)");
            longSparseArray0.keys = arr_v1;
            Object[] arr_object1 = Arrays.copyOf(longSparseArray0.values, v5);
            Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
            longSparseArray0.values = arr_object1;
        }
        longSparseArray0.keys[v4] = v;
        longSparseArray0.values[v4] = object0;
        longSparseArray0.size = v4 + 1;
    }

    public static final void commonClear(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v = longSparseArray0.size;
        Object[] arr_object = longSparseArray0.values;
        for(int v1 = 0; v1 < v; ++v1) {
            arr_object[v1] = null;
        }
        longSparseArray0.size = 0;
        longSparseArray0.garbage = false;
    }

    public static final boolean commonContainsKey(LongSparseArray longSparseArray0, long v) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return longSparseArray0.indexOfKey(v) >= 0;
    }

    public static final boolean commonContainsValue(LongSparseArray longSparseArray0, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return longSparseArray0.indexOfValue(object0) >= 0;
    }

    public static final void commonGc(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v = longSparseArray0.size;
        long[] arr_v = longSparseArray0.keys;
        Object[] arr_object = longSparseArray0.values;
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
        longSparseArray0.garbage = false;
        longSparseArray0.size = v2;
    }

    public static final Object commonGet(LongSparseArray longSparseArray0, long v) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(longSparseArray0.keys, longSparseArray0.size, v);
        return v1 < 0 || longSparseArray0.values[v1] == LongSparseArrayKt.DELETED ? null : longSparseArray0.values[v1];
    }

    public static final Object commonGet(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(longSparseArray0.keys, longSparseArray0.size, v);
        return v1 < 0 || longSparseArray0.values[v1] == LongSparseArrayKt.DELETED ? object0 : longSparseArray0.values[v1];
    }

    public static final Object commonGetInternal(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(longSparseArray0.keys, longSparseArray0.size, v);
        return v1 < 0 || longSparseArray0.values[v1] == LongSparseArrayKt.DELETED ? object0 : longSparseArray0.values[v1];
    }

    public static final int commonIndexOfKey(LongSparseArray longSparseArray0, long v) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(longSparseArray0.garbage) {
            int v1 = longSparseArray0.size;
            long[] arr_v = longSparseArray0.keys;
            Object[] arr_object = longSparseArray0.values;
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
            longSparseArray0.garbage = false;
            longSparseArray0.size = v3;
        }
        return ContainerHelpersKt.binarySearch(longSparseArray0.keys, longSparseArray0.size, v);
    }

    public static final int commonIndexOfValue(LongSparseArray longSparseArray0, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(longSparseArray0.garbage) {
            int v1 = longSparseArray0.size;
            long[] arr_v = longSparseArray0.keys;
            Object[] arr_object = longSparseArray0.values;
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
            longSparseArray0.garbage = false;
            longSparseArray0.size = v3;
        }
        int v4 = longSparseArray0.size;
        for(int v = 0; v < v4; ++v) {
            if(longSparseArray0.values[v] == object0) {
                return v;
            }
        }
        return -1;
    }

    public static final boolean commonIsEmpty(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return longSparseArray0.size() == 0;
    }

    public static final long commonKeyAt(LongSparseArray longSparseArray0, int v) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(v < 0 || v >= longSparseArray0.size) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
        }
        if(longSparseArray0.garbage) {
            int v1 = longSparseArray0.size;
            long[] arr_v = longSparseArray0.keys;
            Object[] arr_object = longSparseArray0.values;
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
            longSparseArray0.garbage = false;
            longSparseArray0.size = v3;
        }
        return longSparseArray0.keys[v];
    }

    public static final void commonPut(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(longSparseArray0.keys, longSparseArray0.size, v);
        if(v1 >= 0) {
            longSparseArray0.values[v1] = object0;
            return;
        }
        int v2 = ~v1;
        if(v2 < longSparseArray0.size && longSparseArray0.values[v2] == LongSparseArrayKt.DELETED) {
            longSparseArray0.keys[v2] = v;
            longSparseArray0.values[v2] = object0;
            return;
        }
        if(longSparseArray0.garbage && longSparseArray0.size >= longSparseArray0.keys.length) {
            int v3 = longSparseArray0.size;
            long[] arr_v = longSparseArray0.keys;
            Object[] arr_object = longSparseArray0.values;
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
            longSparseArray0.garbage = false;
            longSparseArray0.size = v5;
            v2 = ~ContainerHelpersKt.binarySearch(longSparseArray0.keys, longSparseArray0.size, v);
        }
        if(longSparseArray0.size >= longSparseArray0.keys.length) {
            int v6 = ContainerHelpersKt.idealLongArraySize(longSparseArray0.size + 1);
            long[] arr_v1 = Arrays.copyOf(longSparseArray0.keys, v6);
            Intrinsics.checkNotNullExpressionValue(arr_v1, "copyOf(this, newSize)");
            longSparseArray0.keys = arr_v1;
            Object[] arr_object1 = Arrays.copyOf(longSparseArray0.values, v6);
            Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
            longSparseArray0.values = arr_object1;
        }
        if(longSparseArray0.size - v2 != 0) {
            ArraysKt.copyInto(longSparseArray0.keys, longSparseArray0.keys, v2 + 1, v2, longSparseArray0.size);
            ArraysKt.copyInto(longSparseArray0.values, longSparseArray0.values, v2 + 1, v2, longSparseArray0.size);
        }
        longSparseArray0.keys[v2] = v;
        longSparseArray0.values[v2] = object0;
        ++longSparseArray0.size;
    }

    public static final void commonPutAll(LongSparseArray longSparseArray0, LongSparseArray longSparseArray1) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        Intrinsics.checkNotNullParameter(longSparseArray1, "other");
        int v = longSparseArray1.size();
        for(int v1 = 0; v1 < v; ++v1) {
            longSparseArray0.put(longSparseArray1.keyAt(v1), longSparseArray1.valueAt(v1));
        }
    }

    public static final Object commonPutIfAbsent(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        Object object1 = longSparseArray0.get(v);
        if(object1 == null) {
            longSparseArray0.put(v, object0);
        }
        return object1;
    }

    public static final void commonRemove(LongSparseArray longSparseArray0, long v) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v1 = ContainerHelpersKt.binarySearch(longSparseArray0.keys, longSparseArray0.size, v);
        if(v1 >= 0 && longSparseArray0.values[v1] != LongSparseArrayKt.DELETED) {
            longSparseArray0.values[v1] = LongSparseArrayKt.DELETED;
            longSparseArray0.garbage = true;
        }
    }

    public static final boolean commonRemove(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v1 = longSparseArray0.indexOfKey(v);
        if(v1 >= 0 && Intrinsics.areEqual(object0, longSparseArray0.valueAt(v1))) {
            longSparseArray0.removeAt(v1);
            return true;
        }
        return false;
    }

    public static final void commonRemoveAt(LongSparseArray longSparseArray0, int v) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(longSparseArray0.values[v] != LongSparseArrayKt.DELETED) {
            longSparseArray0.values[v] = LongSparseArrayKt.DELETED;
            longSparseArray0.garbage = true;
        }
    }

    public static final Object commonReplace(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v1 = longSparseArray0.indexOfKey(v);
        if(v1 >= 0) {
            Object object1 = longSparseArray0.values[v1];
            longSparseArray0.values[v1] = object0;
            return object1;
        }
        return null;
    }

    public static final boolean commonReplace(LongSparseArray longSparseArray0, long v, Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        int v1 = longSparseArray0.indexOfKey(v);
        if(v1 >= 0 && Intrinsics.areEqual(longSparseArray0.values[v1], object0)) {
            longSparseArray0.values[v1] = object1;
            return true;
        }
        return false;
    }

    public static final void commonSetValueAt(LongSparseArray longSparseArray0, int v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(v < 0 || v >= longSparseArray0.size) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
        }
        if(longSparseArray0.garbage) {
            int v1 = longSparseArray0.size;
            long[] arr_v = longSparseArray0.keys;
            Object[] arr_object = longSparseArray0.values;
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
            longSparseArray0.garbage = false;
            longSparseArray0.size = v3;
        }
        longSparseArray0.values[v] = object0;
    }

    public static final int commonSize(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(longSparseArray0.garbage) {
            int v = longSparseArray0.size;
            long[] arr_v = longSparseArray0.keys;
            Object[] arr_object = longSparseArray0.values;
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
            longSparseArray0.garbage = false;
            longSparseArray0.size = v2;
        }
        return longSparseArray0.size;
    }

    public static final String commonToString(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(longSparseArray0.size() <= 0) {
            return "{}";
        }
        StringBuilder stringBuilder0 = new StringBuilder(longSparseArray0.size * 28);
        stringBuilder0.append('{');
        int v = longSparseArray0.size;
        for(int v1 = 0; v1 < v; ++v1) {
            if(v1 > 0) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(longSparseArray0.keyAt(v1));
            stringBuilder0.append('=');
            Object object0 = longSparseArray0.valueAt(v1);
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

    public static final Object commonValueAt(LongSparseArray longSparseArray0, int v) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        if(v < 0 || v >= longSparseArray0.size) {
            throw new IllegalArgumentException(("Expected index to be within 0..size()-1, but was " + v).toString());
        }
        if(longSparseArray0.garbage) {
            int v1 = longSparseArray0.size;
            long[] arr_v = longSparseArray0.keys;
            Object[] arr_object = longSparseArray0.values;
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
            longSparseArray0.garbage = false;
            longSparseArray0.size = v3;
        }
        return longSparseArray0.values[v];
    }

    public static final boolean contains(LongSparseArray longSparseArray0, long v) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return longSparseArray0.containsKey(v);
    }

    public static final void forEach(LongSparseArray longSparseArray0, Function2 function20) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "action");
        int v = longSparseArray0.size();
        for(int v1 = 0; v1 < v; ++v1) {
            function20.invoke(longSparseArray0.keyAt(v1), longSparseArray0.valueAt(v1));
        }
    }

    public static final Object getOrDefault(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return longSparseArray0.get(v, object0);
    }

    public static final Object getOrElse(LongSparseArray longSparseArray0, long v, Function0 function00) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        Object object0 = longSparseArray0.get(v);
        return object0 == null ? function00.invoke() : object0;
    }

    public static final int getSize(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return longSparseArray0.size();
    }

    public static void getSize$annotations(LongSparseArray longSparseArray0) {
    }

    public static final boolean isNotEmpty(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return !longSparseArray0.isEmpty();
    }

    public static final LongIterator keyIterator(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return new LongIterator() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < longSparseArray0.size();
            }

            @Override  // kotlin.collections.LongIterator
            public long nextLong() {
                int v = this.index;
                this.index = v + 1;
                return longSparseArray0.keyAt(v);
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }

    public static final LongSparseArray plus(LongSparseArray longSparseArray0, LongSparseArray longSparseArray1) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        Intrinsics.checkNotNullParameter(longSparseArray1, "other");
        LongSparseArray longSparseArray2 = new LongSparseArray(longSparseArray0.size() + longSparseArray1.size());
        longSparseArray2.putAll(longSparseArray0);
        longSparseArray2.putAll(longSparseArray1);
        return longSparseArray2;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced with member function. Remove extension import!")
    public static final boolean remove(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return longSparseArray0.remove(v, object0);
    }

    public static final void set(LongSparseArray longSparseArray0, long v, Object object0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        longSparseArray0.put(v, object0);
    }

    public static final Iterator valueIterator(LongSparseArray longSparseArray0) {
        Intrinsics.checkNotNullParameter(longSparseArray0, "<this>");
        return new Object() {
            private int index;

            public final int getIndex() {
                return this.index;
            }

            @Override
            public boolean hasNext() {
                return this.index < longSparseArray0.size();
            }

            @Override
            public Object next() {
                int v = this.index;
                this.index = v + 1;
                return longSparseArray0.valueAt(v);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Operation is not supported for read-only collection");
            }

            public final void setIndex(int v) {
                this.index = v;
            }
        };
    }
}

