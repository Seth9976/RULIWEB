package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0015\u001A\u0012\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\u0012\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\n\u001A7\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t\u00A2\u0006\u0002\u0010\r\u001AG\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0010\u001AW\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0013\u001Ag\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t2\u0006\u0010\u0014\u001A\u0002H\u00052\u0006\u0010\u0015\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0016\u001A\u0012\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0019\u001A7\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001A\u001AG\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001B\u001AW\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001C\u001Ag\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t2\u0006\u0010\u0014\u001A\u0002H\u00052\u0006\u0010\u0015\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001D\"\u0016\u0010\u0000\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001E"}, d2 = {"EmptyObjectIntMap", "Landroidx/collection/MutableObjectIntMap;", "", "emptyObjectIntMap", "Landroidx/collection/ObjectIntMap;", "K", "mutableObjectIntMapOf", "key1", "value1", "", "(Ljava/lang/Object;I)Landroidx/collection/MutableObjectIntMap;", "key2", "value2", "(Ljava/lang/Object;ILjava/lang/Object;I)Landroidx/collection/MutableObjectIntMap;", "key3", "value3", "(Ljava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;I)Landroidx/collection/MutableObjectIntMap;", "key4", "value4", "(Ljava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;I)Landroidx/collection/MutableObjectIntMap;", "key5", "value5", "(Ljava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;I)Landroidx/collection/MutableObjectIntMap;", "objectIntMap", "objectIntMapOf", "(Ljava/lang/Object;I)Landroidx/collection/ObjectIntMap;", "(Ljava/lang/Object;ILjava/lang/Object;I)Landroidx/collection/ObjectIntMap;", "(Ljava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;I)Landroidx/collection/ObjectIntMap;", "(Ljava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;I)Landroidx/collection/ObjectIntMap;", "(Ljava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;I)Landroidx/collection/ObjectIntMap;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ObjectIntMapKt {
    private static final MutableObjectIntMap EmptyObjectIntMap;

    static {
        ObjectIntMapKt.EmptyObjectIntMap = new MutableObjectIntMap(0);
    }

    public static final ObjectIntMap emptyObjectIntMap() {
        Intrinsics.checkNotNull(ObjectIntMapKt.EmptyObjectIntMap, "null cannot be cast to non-null type androidx.collection.ObjectIntMap<K of androidx.collection.ObjectIntMapKt.emptyObjectIntMap>");
        return ObjectIntMapKt.EmptyObjectIntMap;
    }

    public static final MutableObjectIntMap mutableObjectIntMapOf() {
        return new MutableObjectIntMap(0, 1, null);
    }

    public static final MutableObjectIntMap mutableObjectIntMapOf(Object object0, int v) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        return mutableObjectIntMap0;
    }

    public static final MutableObjectIntMap mutableObjectIntMapOf(Object object0, int v, Object object1, int v1) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        mutableObjectIntMap0.set(object1, v1);
        return mutableObjectIntMap0;
    }

    public static final MutableObjectIntMap mutableObjectIntMapOf(Object object0, int v, Object object1, int v1, Object object2, int v2) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        mutableObjectIntMap0.set(object1, v1);
        mutableObjectIntMap0.set(object2, v2);
        return mutableObjectIntMap0;
    }

    public static final MutableObjectIntMap mutableObjectIntMapOf(Object object0, int v, Object object1, int v1, Object object2, int v2, Object object3, int v3) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        mutableObjectIntMap0.set(object1, v1);
        mutableObjectIntMap0.set(object2, v2);
        mutableObjectIntMap0.set(object3, v3);
        return mutableObjectIntMap0;
    }

    public static final MutableObjectIntMap mutableObjectIntMapOf(Object object0, int v, Object object1, int v1, Object object2, int v2, Object object3, int v3, Object object4, int v4) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        mutableObjectIntMap0.set(object1, v1);
        mutableObjectIntMap0.set(object2, v2);
        mutableObjectIntMap0.set(object3, v3);
        mutableObjectIntMap0.set(object4, v4);
        return mutableObjectIntMap0;
    }

    public static final ObjectIntMap objectIntMap() {
        Intrinsics.checkNotNull(ObjectIntMapKt.EmptyObjectIntMap, "null cannot be cast to non-null type androidx.collection.ObjectIntMap<K of androidx.collection.ObjectIntMapKt.objectIntMap>");
        return ObjectIntMapKt.EmptyObjectIntMap;
    }

    public static final ObjectIntMap objectIntMapOf(Object object0, int v) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        return mutableObjectIntMap0;
    }

    public static final ObjectIntMap objectIntMapOf(Object object0, int v, Object object1, int v1) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        mutableObjectIntMap0.set(object1, v1);
        return mutableObjectIntMap0;
    }

    public static final ObjectIntMap objectIntMapOf(Object object0, int v, Object object1, int v1, Object object2, int v2) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        mutableObjectIntMap0.set(object1, v1);
        mutableObjectIntMap0.set(object2, v2);
        return mutableObjectIntMap0;
    }

    public static final ObjectIntMap objectIntMapOf(Object object0, int v, Object object1, int v1, Object object2, int v2, Object object3, int v3) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        mutableObjectIntMap0.set(object1, v1);
        mutableObjectIntMap0.set(object2, v2);
        mutableObjectIntMap0.set(object3, v3);
        return mutableObjectIntMap0;
    }

    public static final ObjectIntMap objectIntMapOf(Object object0, int v, Object object1, int v1, Object object2, int v2, Object object3, int v3, Object object4, int v4) {
        MutableObjectIntMap mutableObjectIntMap0 = new MutableObjectIntMap(0, 1, null);
        mutableObjectIntMap0.set(object0, v);
        mutableObjectIntMap0.set(object1, v1);
        mutableObjectIntMap0.set(object2, v2);
        mutableObjectIntMap0.set(object3, v3);
        mutableObjectIntMap0.set(object4, v4);
        return mutableObjectIntMap0;
    }
}

