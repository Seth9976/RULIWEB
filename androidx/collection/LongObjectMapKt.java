package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0015\u001A\u0012\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\u0012\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\n\u001A7\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\r\u001AG\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0010\u001AW\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0013\u001Ag\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u00052\u0006\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0015\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0016\u001A\u0012\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0018\u001A7\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0019\u001AG\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001A\u001AW\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001B\u001Ag\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u00052\u0006\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0015\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001C\"\u0014\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001D"}, d2 = {"EmptyLongObjectMap", "Landroidx/collection/MutableLongObjectMap;", "", "emptyLongObjectMap", "Landroidx/collection/LongObjectMap;", "V", "longObjectMapOf", "key1", "", "value1", "(JLjava/lang/Object;)Landroidx/collection/LongObjectMap;", "key2", "value2", "(JLjava/lang/Object;JLjava/lang/Object;)Landroidx/collection/LongObjectMap;", "key3", "value3", "(JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;)Landroidx/collection/LongObjectMap;", "key4", "value4", "(JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;)Landroidx/collection/LongObjectMap;", "key5", "value5", "(JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;)Landroidx/collection/LongObjectMap;", "mutableLongObjectMapOf", "(JLjava/lang/Object;)Landroidx/collection/MutableLongObjectMap;", "(JLjava/lang/Object;JLjava/lang/Object;)Landroidx/collection/MutableLongObjectMap;", "(JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;)Landroidx/collection/MutableLongObjectMap;", "(JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;)Landroidx/collection/MutableLongObjectMap;", "(JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;)Landroidx/collection/MutableLongObjectMap;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class LongObjectMapKt {
    private static final MutableLongObjectMap EmptyLongObjectMap;

    static {
        LongObjectMapKt.EmptyLongObjectMap = new MutableLongObjectMap(0);
    }

    public static final LongObjectMap emptyLongObjectMap() {
        Intrinsics.checkNotNull(LongObjectMapKt.EmptyLongObjectMap, "null cannot be cast to non-null type androidx.collection.LongObjectMap<V of androidx.collection.LongObjectMapKt.emptyLongObjectMap>");
        return LongObjectMapKt.EmptyLongObjectMap;
    }

    public static final LongObjectMap longObjectMapOf() {
        Intrinsics.checkNotNull(LongObjectMapKt.EmptyLongObjectMap, "null cannot be cast to non-null type androidx.collection.LongObjectMap<V of androidx.collection.LongObjectMapKt.longObjectMapOf>");
        return LongObjectMapKt.EmptyLongObjectMap;
    }

    public static final LongObjectMap longObjectMapOf(long v, Object object0) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        return mutableLongObjectMap0;
    }

    public static final LongObjectMap longObjectMapOf(long v, Object object0, long v1, Object object1) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        mutableLongObjectMap0.set(v1, object1);
        return mutableLongObjectMap0;
    }

    public static final LongObjectMap longObjectMapOf(long v, Object object0, long v1, Object object1, long v2, Object object2) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        mutableLongObjectMap0.set(v1, object1);
        mutableLongObjectMap0.set(v2, object2);
        return mutableLongObjectMap0;
    }

    public static final LongObjectMap longObjectMapOf(long v, Object object0, long v1, Object object1, long v2, Object object2, long v3, Object object3) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        mutableLongObjectMap0.set(v1, object1);
        mutableLongObjectMap0.set(v2, object2);
        mutableLongObjectMap0.set(v3, object3);
        return mutableLongObjectMap0;
    }

    public static final LongObjectMap longObjectMapOf(long v, Object object0, long v1, Object object1, long v2, Object object2, long v3, Object object3, long v4, Object object4) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        mutableLongObjectMap0.set(v1, object1);
        mutableLongObjectMap0.set(v2, object2);
        mutableLongObjectMap0.set(v3, object3);
        mutableLongObjectMap0.set(v4, object4);
        return mutableLongObjectMap0;
    }

    public static final MutableLongObjectMap mutableLongObjectMapOf() {
        return new MutableLongObjectMap(0, 1, null);
    }

    public static final MutableLongObjectMap mutableLongObjectMapOf(long v, Object object0) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        return mutableLongObjectMap0;
    }

    public static final MutableLongObjectMap mutableLongObjectMapOf(long v, Object object0, long v1, Object object1) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        mutableLongObjectMap0.set(v1, object1);
        return mutableLongObjectMap0;
    }

    public static final MutableLongObjectMap mutableLongObjectMapOf(long v, Object object0, long v1, Object object1, long v2, Object object2) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        mutableLongObjectMap0.set(v1, object1);
        mutableLongObjectMap0.set(v2, object2);
        return mutableLongObjectMap0;
    }

    public static final MutableLongObjectMap mutableLongObjectMapOf(long v, Object object0, long v1, Object object1, long v2, Object object2, long v3, Object object3) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        mutableLongObjectMap0.set(v1, object1);
        mutableLongObjectMap0.set(v2, object2);
        mutableLongObjectMap0.set(v3, object3);
        return mutableLongObjectMap0;
    }

    public static final MutableLongObjectMap mutableLongObjectMapOf(long v, Object object0, long v1, Object object1, long v2, Object object2, long v3, Object object3, long v4, Object object4) {
        MutableLongObjectMap mutableLongObjectMap0 = new MutableLongObjectMap(0, 1, null);
        mutableLongObjectMap0.set(v, object0);
        mutableLongObjectMap0.set(v1, object1);
        mutableLongObjectMap0.set(v2, object2);
        mutableLongObjectMap0.set(v3, object3);
        mutableLongObjectMap0.set(v4, object4);
        return mutableLongObjectMap0;
    }
}

