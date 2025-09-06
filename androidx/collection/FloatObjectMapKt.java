package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0015\u001A\u0012\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\u0012\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\n\u001A7\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\r\u001AG\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0010\u001AW\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0013\u001Ag\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u00052\u0006\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0015\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0016\u001A\u0012\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0018\u001A7\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0019\u001AG\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001A\u001AW\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001B\u001Ag\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u00052\u0006\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0015\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001C\"\u0014\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001D"}, d2 = {"EmptyFloatObjectMap", "Landroidx/collection/MutableFloatObjectMap;", "", "emptyFloatObjectMap", "Landroidx/collection/FloatObjectMap;", "V", "floatObjectMapOf", "key1", "", "value1", "(FLjava/lang/Object;)Landroidx/collection/FloatObjectMap;", "key2", "value2", "(FLjava/lang/Object;FLjava/lang/Object;)Landroidx/collection/FloatObjectMap;", "key3", "value3", "(FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;)Landroidx/collection/FloatObjectMap;", "key4", "value4", "(FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;)Landroidx/collection/FloatObjectMap;", "key5", "value5", "(FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;)Landroidx/collection/FloatObjectMap;", "mutableFloatObjectMapOf", "(FLjava/lang/Object;)Landroidx/collection/MutableFloatObjectMap;", "(FLjava/lang/Object;FLjava/lang/Object;)Landroidx/collection/MutableFloatObjectMap;", "(FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;)Landroidx/collection/MutableFloatObjectMap;", "(FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;)Landroidx/collection/MutableFloatObjectMap;", "(FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;FLjava/lang/Object;)Landroidx/collection/MutableFloatObjectMap;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class FloatObjectMapKt {
    private static final MutableFloatObjectMap EmptyFloatObjectMap;

    static {
        FloatObjectMapKt.EmptyFloatObjectMap = new MutableFloatObjectMap(0);
    }

    public static final FloatObjectMap emptyFloatObjectMap() {
        Intrinsics.checkNotNull(FloatObjectMapKt.EmptyFloatObjectMap, "null cannot be cast to non-null type androidx.collection.FloatObjectMap<V of androidx.collection.FloatObjectMapKt.emptyFloatObjectMap>");
        return FloatObjectMapKt.EmptyFloatObjectMap;
    }

    public static final FloatObjectMap floatObjectMapOf() {
        Intrinsics.checkNotNull(FloatObjectMapKt.EmptyFloatObjectMap, "null cannot be cast to non-null type androidx.collection.FloatObjectMap<V of androidx.collection.FloatObjectMapKt.floatObjectMapOf>");
        return FloatObjectMapKt.EmptyFloatObjectMap;
    }

    public static final FloatObjectMap floatObjectMapOf(float f, Object object0) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        return mutableFloatObjectMap0;
    }

    public static final FloatObjectMap floatObjectMapOf(float f, Object object0, float f1, Object object1) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        mutableFloatObjectMap0.set(f1, object1);
        return mutableFloatObjectMap0;
    }

    public static final FloatObjectMap floatObjectMapOf(float f, Object object0, float f1, Object object1, float f2, Object object2) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        mutableFloatObjectMap0.set(f1, object1);
        mutableFloatObjectMap0.set(f2, object2);
        return mutableFloatObjectMap0;
    }

    public static final FloatObjectMap floatObjectMapOf(float f, Object object0, float f1, Object object1, float f2, Object object2, float f3, Object object3) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        mutableFloatObjectMap0.set(f1, object1);
        mutableFloatObjectMap0.set(f2, object2);
        mutableFloatObjectMap0.set(f3, object3);
        return mutableFloatObjectMap0;
    }

    public static final FloatObjectMap floatObjectMapOf(float f, Object object0, float f1, Object object1, float f2, Object object2, float f3, Object object3, float f4, Object object4) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        mutableFloatObjectMap0.set(f1, object1);
        mutableFloatObjectMap0.set(f2, object2);
        mutableFloatObjectMap0.set(f3, object3);
        mutableFloatObjectMap0.set(f4, object4);
        return mutableFloatObjectMap0;
    }

    public static final MutableFloatObjectMap mutableFloatObjectMapOf() {
        return new MutableFloatObjectMap(0, 1, null);
    }

    public static final MutableFloatObjectMap mutableFloatObjectMapOf(float f, Object object0) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        return mutableFloatObjectMap0;
    }

    public static final MutableFloatObjectMap mutableFloatObjectMapOf(float f, Object object0, float f1, Object object1) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        mutableFloatObjectMap0.set(f1, object1);
        return mutableFloatObjectMap0;
    }

    public static final MutableFloatObjectMap mutableFloatObjectMapOf(float f, Object object0, float f1, Object object1, float f2, Object object2) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        mutableFloatObjectMap0.set(f1, object1);
        mutableFloatObjectMap0.set(f2, object2);
        return mutableFloatObjectMap0;
    }

    public static final MutableFloatObjectMap mutableFloatObjectMapOf(float f, Object object0, float f1, Object object1, float f2, Object object2, float f3, Object object3) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        mutableFloatObjectMap0.set(f1, object1);
        mutableFloatObjectMap0.set(f2, object2);
        mutableFloatObjectMap0.set(f3, object3);
        return mutableFloatObjectMap0;
    }

    public static final MutableFloatObjectMap mutableFloatObjectMapOf(float f, Object object0, float f1, Object object1, float f2, Object object2, float f3, Object object3, float f4, Object object4) {
        MutableFloatObjectMap mutableFloatObjectMap0 = new MutableFloatObjectMap(0, 1, null);
        mutableFloatObjectMap0.set(f, object0);
        mutableFloatObjectMap0.set(f1, object1);
        mutableFloatObjectMap0.set(f2, object2);
        mutableFloatObjectMap0.set(f3, object3);
        mutableFloatObjectMap0.set(f4, object4);
        return mutableFloatObjectMap0;
    }
}

