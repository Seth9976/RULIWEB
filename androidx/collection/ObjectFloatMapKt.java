package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0015\u001A\u0012\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\u0012\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\n\u001A7\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t\u00A2\u0006\u0002\u0010\r\u001AG\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0010\u001AW\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0013\u001Ag\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t2\u0006\u0010\u0014\u001A\u0002H\u00052\u0006\u0010\u0015\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0016\u001A\u0012\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t\u00A2\u0006\u0002\u0010\u0019\u001A7\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001A\u001AG\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001B\u001AW\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001C\u001Ag\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u0002H\u00052\u0006\u0010\b\u001A\u00020\t2\u0006\u0010\u000B\u001A\u0002H\u00052\u0006\u0010\f\u001A\u00020\t2\u0006\u0010\u000E\u001A\u0002H\u00052\u0006\u0010\u000F\u001A\u00020\t2\u0006\u0010\u0011\u001A\u0002H\u00052\u0006\u0010\u0012\u001A\u00020\t2\u0006\u0010\u0014\u001A\u0002H\u00052\u0006\u0010\u0015\u001A\u00020\t\u00A2\u0006\u0002\u0010\u001D\"\u0016\u0010\u0000\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001E"}, d2 = {"EmptyObjectFloatMap", "Landroidx/collection/MutableObjectFloatMap;", "", "emptyObjectFloatMap", "Landroidx/collection/ObjectFloatMap;", "K", "mutableObjectFloatMapOf", "key1", "value1", "", "(Ljava/lang/Object;F)Landroidx/collection/MutableObjectFloatMap;", "key2", "value2", "(Ljava/lang/Object;FLjava/lang/Object;F)Landroidx/collection/MutableObjectFloatMap;", "key3", "value3", "(Ljava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;F)Landroidx/collection/MutableObjectFloatMap;", "key4", "value4", "(Ljava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;F)Landroidx/collection/MutableObjectFloatMap;", "key5", "value5", "(Ljava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;F)Landroidx/collection/MutableObjectFloatMap;", "objectFloatMap", "objectFloatMapOf", "(Ljava/lang/Object;F)Landroidx/collection/ObjectFloatMap;", "(Ljava/lang/Object;FLjava/lang/Object;F)Landroidx/collection/ObjectFloatMap;", "(Ljava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;F)Landroidx/collection/ObjectFloatMap;", "(Ljava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;F)Landroidx/collection/ObjectFloatMap;", "(Ljava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;F)Landroidx/collection/ObjectFloatMap;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ObjectFloatMapKt {
    private static final MutableObjectFloatMap EmptyObjectFloatMap;

    static {
        ObjectFloatMapKt.EmptyObjectFloatMap = new MutableObjectFloatMap(0);
    }

    public static final ObjectFloatMap emptyObjectFloatMap() {
        Intrinsics.checkNotNull(ObjectFloatMapKt.EmptyObjectFloatMap, "null cannot be cast to non-null type androidx.collection.ObjectFloatMap<K of androidx.collection.ObjectFloatMapKt.emptyObjectFloatMap>");
        return ObjectFloatMapKt.EmptyObjectFloatMap;
    }

    public static final MutableObjectFloatMap mutableObjectFloatMapOf() {
        return new MutableObjectFloatMap(0, 1, null);
    }

    public static final MutableObjectFloatMap mutableObjectFloatMapOf(Object object0, float f) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        return mutableObjectFloatMap0;
    }

    public static final MutableObjectFloatMap mutableObjectFloatMapOf(Object object0, float f, Object object1, float f1) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        mutableObjectFloatMap0.set(object1, f1);
        return mutableObjectFloatMap0;
    }

    public static final MutableObjectFloatMap mutableObjectFloatMapOf(Object object0, float f, Object object1, float f1, Object object2, float f2) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        mutableObjectFloatMap0.set(object1, f1);
        mutableObjectFloatMap0.set(object2, f2);
        return mutableObjectFloatMap0;
    }

    public static final MutableObjectFloatMap mutableObjectFloatMapOf(Object object0, float f, Object object1, float f1, Object object2, float f2, Object object3, float f3) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        mutableObjectFloatMap0.set(object1, f1);
        mutableObjectFloatMap0.set(object2, f2);
        mutableObjectFloatMap0.set(object3, f3);
        return mutableObjectFloatMap0;
    }

    public static final MutableObjectFloatMap mutableObjectFloatMapOf(Object object0, float f, Object object1, float f1, Object object2, float f2, Object object3, float f3, Object object4, float f4) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        mutableObjectFloatMap0.set(object1, f1);
        mutableObjectFloatMap0.set(object2, f2);
        mutableObjectFloatMap0.set(object3, f3);
        mutableObjectFloatMap0.set(object4, f4);
        return mutableObjectFloatMap0;
    }

    public static final ObjectFloatMap objectFloatMap() {
        Intrinsics.checkNotNull(ObjectFloatMapKt.EmptyObjectFloatMap, "null cannot be cast to non-null type androidx.collection.ObjectFloatMap<K of androidx.collection.ObjectFloatMapKt.objectFloatMap>");
        return ObjectFloatMapKt.EmptyObjectFloatMap;
    }

    public static final ObjectFloatMap objectFloatMapOf(Object object0, float f) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        return mutableObjectFloatMap0;
    }

    public static final ObjectFloatMap objectFloatMapOf(Object object0, float f, Object object1, float f1) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        mutableObjectFloatMap0.set(object1, f1);
        return mutableObjectFloatMap0;
    }

    public static final ObjectFloatMap objectFloatMapOf(Object object0, float f, Object object1, float f1, Object object2, float f2) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        mutableObjectFloatMap0.set(object1, f1);
        mutableObjectFloatMap0.set(object2, f2);
        return mutableObjectFloatMap0;
    }

    public static final ObjectFloatMap objectFloatMapOf(Object object0, float f, Object object1, float f1, Object object2, float f2, Object object3, float f3) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        mutableObjectFloatMap0.set(object1, f1);
        mutableObjectFloatMap0.set(object2, f2);
        mutableObjectFloatMap0.set(object3, f3);
        return mutableObjectFloatMap0;
    }

    public static final ObjectFloatMap objectFloatMapOf(Object object0, float f, Object object1, float f1, Object object2, float f2, Object object3, float f3, Object object4, float f4) {
        MutableObjectFloatMap mutableObjectFloatMap0 = new MutableObjectFloatMap(0, 1, null);
        mutableObjectFloatMap0.set(object0, f);
        mutableObjectFloatMap0.set(object1, f1);
        mutableObjectFloatMap0.set(object2, f2);
        mutableObjectFloatMap0.set(object3, f3);
        mutableObjectFloatMap0.set(object4, f4);
        return mutableObjectFloatMap0;
    }
}

