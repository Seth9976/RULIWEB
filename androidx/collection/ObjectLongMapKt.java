package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0015\u001A\u0012\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\u0012\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\n\u001A7\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t\u00A2\u0006\u0002\u0010\r\u001AG\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0010\u001AW\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0013\u001Ag\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t2\u0006\u0010\u0014\u001A\u0002H\u00052\u0006\u0010\u0015\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0016\u001A\u0012\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0019\u001A7\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001A\u001AG\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001B\u001AW\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001C\u001Ag\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t2\u0006\u0010\u0014\u001A\u0002H\u00052\u0006\u0010\u0015\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001D\"\u0016\u0010\u0000\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001E"}, d2 = {"EmptyObjectLongMap", "Landroidx/collection/MutableObjectLongMap;", "", "emptyObjectLongMap", "Landroidx/collection/ObjectLongMap;", "K", "mutableObjectLongMapOf", "key1", "value1", "", "(Ljava/lang/Object;J)Landroidx/collection/MutableObjectLongMap;", "key2", "value2", "(Ljava/lang/Object;JLjava/lang/Object;J)Landroidx/collection/MutableObjectLongMap;", "key3", "value3", "(Ljava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;J)Landroidx/collection/MutableObjectLongMap;", "key4", "value4", "(Ljava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;J)Landroidx/collection/MutableObjectLongMap;", "key5", "value5", "(Ljava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;J)Landroidx/collection/MutableObjectLongMap;", "objectLongMap", "objectLongMapOf", "(Ljava/lang/Object;J)Landroidx/collection/ObjectLongMap;", "(Ljava/lang/Object;JLjava/lang/Object;J)Landroidx/collection/ObjectLongMap;", "(Ljava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;J)Landroidx/collection/ObjectLongMap;", "(Ljava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;J)Landroidx/collection/ObjectLongMap;", "(Ljava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;JLjava/lang/Object;J)Landroidx/collection/ObjectLongMap;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ObjectLongMapKt {
    private static final MutableObjectLongMap EmptyObjectLongMap;

    static {
        ObjectLongMapKt.EmptyObjectLongMap = new MutableObjectLongMap(0);
    }

    public static final ObjectLongMap emptyObjectLongMap() {
        Intrinsics.checkNotNull(ObjectLongMapKt.EmptyObjectLongMap, "null cannot be cast to non-null type androidx.collection.ObjectLongMap<K of androidx.collection.ObjectLongMapKt.emptyObjectLongMap>");
        return ObjectLongMapKt.EmptyObjectLongMap;
    }

    public static final MutableObjectLongMap mutableObjectLongMapOf() {
        return new MutableObjectLongMap(0, 1, null);
    }

    public static final MutableObjectLongMap mutableObjectLongMapOf(Object object0, long v) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        return mutableObjectLongMap0;
    }

    public static final MutableObjectLongMap mutableObjectLongMapOf(Object object0, long v, Object object1, long v1) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        mutableObjectLongMap0.set(object1, v1);
        return mutableObjectLongMap0;
    }

    public static final MutableObjectLongMap mutableObjectLongMapOf(Object object0, long v, Object object1, long v1, Object object2, long v2) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        mutableObjectLongMap0.set(object1, v1);
        mutableObjectLongMap0.set(object2, v2);
        return mutableObjectLongMap0;
    }

    public static final MutableObjectLongMap mutableObjectLongMapOf(Object object0, long v, Object object1, long v1, Object object2, long v2, Object object3, long v3) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        mutableObjectLongMap0.set(object1, v1);
        mutableObjectLongMap0.set(object2, v2);
        mutableObjectLongMap0.set(object3, v3);
        return mutableObjectLongMap0;
    }

    public static final MutableObjectLongMap mutableObjectLongMapOf(Object object0, long v, Object object1, long v1, Object object2, long v2, Object object3, long v3, Object object4, long v4) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        mutableObjectLongMap0.set(object1, v1);
        mutableObjectLongMap0.set(object2, v2);
        mutableObjectLongMap0.set(object3, v3);
        mutableObjectLongMap0.set(object4, v4);
        return mutableObjectLongMap0;
    }

    public static final ObjectLongMap objectLongMap() {
        Intrinsics.checkNotNull(ObjectLongMapKt.EmptyObjectLongMap, "null cannot be cast to non-null type androidx.collection.ObjectLongMap<K of androidx.collection.ObjectLongMapKt.objectLongMap>");
        return ObjectLongMapKt.EmptyObjectLongMap;
    }

    public static final ObjectLongMap objectLongMapOf(Object object0, long v) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        return mutableObjectLongMap0;
    }

    public static final ObjectLongMap objectLongMapOf(Object object0, long v, Object object1, long v1) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        mutableObjectLongMap0.set(object1, v1);
        return mutableObjectLongMap0;
    }

    public static final ObjectLongMap objectLongMapOf(Object object0, long v, Object object1, long v1, Object object2, long v2) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        mutableObjectLongMap0.set(object1, v1);
        mutableObjectLongMap0.set(object2, v2);
        return mutableObjectLongMap0;
    }

    public static final ObjectLongMap objectLongMapOf(Object object0, long v, Object object1, long v1, Object object2, long v2, Object object3, long v3) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        mutableObjectLongMap0.set(object1, v1);
        mutableObjectLongMap0.set(object2, v2);
        mutableObjectLongMap0.set(object3, v3);
        return mutableObjectLongMap0;
    }

    public static final ObjectLongMap objectLongMapOf(Object object0, long v, Object object1, long v1, Object object2, long v2, Object object3, long v3, Object object4, long v4) {
        MutableObjectLongMap mutableObjectLongMap0 = new MutableObjectLongMap(0, 1, null);
        mutableObjectLongMap0.set(object0, v);
        mutableObjectLongMap0.set(object1, v1);
        mutableObjectLongMap0.set(object2, v2);
        mutableObjectLongMap0.set(object3, v3);
        mutableObjectLongMap0.set(object4, v4);
        return mutableObjectLongMap0;
    }
}

