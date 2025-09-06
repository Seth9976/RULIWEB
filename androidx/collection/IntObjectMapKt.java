package androidx.collection;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0015\u001A\u0012\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\u0012\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\n\u001A7\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\r\u001AG\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0010\u001AW\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0013\u001Ag\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u00052\u0006\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0015\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0016\u001A\u0012\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u0005\u001A\'\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0018\u001A7\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u0019\u001AG\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001A\u001AW\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001B\u001Ag\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00050\u0001\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0007\u001A\u00020\b2\u0006\u0010\t\u001A\u0002H\u00052\u0006\u0010\u000B\u001A\u00020\b2\u0006\u0010\f\u001A\u0002H\u00052\u0006\u0010\u000E\u001A\u00020\b2\u0006\u0010\u000F\u001A\u0002H\u00052\u0006\u0010\u0011\u001A\u00020\b2\u0006\u0010\u0012\u001A\u0002H\u00052\u0006\u0010\u0014\u001A\u00020\b2\u0006\u0010\u0015\u001A\u0002H\u0005\u00A2\u0006\u0002\u0010\u001C\"\u0014\u0010\u0000\u001A\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001D"}, d2 = {"EmptyIntObjectMap", "Landroidx/collection/MutableIntObjectMap;", "", "emptyIntObjectMap", "Landroidx/collection/IntObjectMap;", "V", "intObjectMapOf", "key1", "", "value1", "(ILjava/lang/Object;)Landroidx/collection/IntObjectMap;", "key2", "value2", "(ILjava/lang/Object;ILjava/lang/Object;)Landroidx/collection/IntObjectMap;", "key3", "value3", "(ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)Landroidx/collection/IntObjectMap;", "key4", "value4", "(ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)Landroidx/collection/IntObjectMap;", "key5", "value5", "(ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)Landroidx/collection/IntObjectMap;", "mutableIntObjectMapOf", "(ILjava/lang/Object;)Landroidx/collection/MutableIntObjectMap;", "(ILjava/lang/Object;ILjava/lang/Object;)Landroidx/collection/MutableIntObjectMap;", "(ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)Landroidx/collection/MutableIntObjectMap;", "(ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)Landroidx/collection/MutableIntObjectMap;", "(ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;ILjava/lang/Object;)Landroidx/collection/MutableIntObjectMap;", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class IntObjectMapKt {
    private static final MutableIntObjectMap EmptyIntObjectMap;

    static {
        IntObjectMapKt.EmptyIntObjectMap = new MutableIntObjectMap(0);
    }

    public static final IntObjectMap emptyIntObjectMap() {
        Intrinsics.checkNotNull(IntObjectMapKt.EmptyIntObjectMap, "null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.emptyIntObjectMap>");
        return IntObjectMapKt.EmptyIntObjectMap;
    }

    public static final IntObjectMap intObjectMapOf() {
        Intrinsics.checkNotNull(IntObjectMapKt.EmptyIntObjectMap, "null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.intObjectMapOf>");
        return IntObjectMapKt.EmptyIntObjectMap;
    }

    public static final IntObjectMap intObjectMapOf(int v, Object object0) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        return mutableIntObjectMap0;
    }

    public static final IntObjectMap intObjectMapOf(int v, Object object0, int v1, Object object1) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        mutableIntObjectMap0.set(v1, object1);
        return mutableIntObjectMap0;
    }

    public static final IntObjectMap intObjectMapOf(int v, Object object0, int v1, Object object1, int v2, Object object2) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        mutableIntObjectMap0.set(v1, object1);
        mutableIntObjectMap0.set(v2, object2);
        return mutableIntObjectMap0;
    }

    public static final IntObjectMap intObjectMapOf(int v, Object object0, int v1, Object object1, int v2, Object object2, int v3, Object object3) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        mutableIntObjectMap0.set(v1, object1);
        mutableIntObjectMap0.set(v2, object2);
        mutableIntObjectMap0.set(v3, object3);
        return mutableIntObjectMap0;
    }

    public static final IntObjectMap intObjectMapOf(int v, Object object0, int v1, Object object1, int v2, Object object2, int v3, Object object3, int v4, Object object4) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        mutableIntObjectMap0.set(v1, object1);
        mutableIntObjectMap0.set(v2, object2);
        mutableIntObjectMap0.set(v3, object3);
        mutableIntObjectMap0.set(v4, object4);
        return mutableIntObjectMap0;
    }

    public static final MutableIntObjectMap mutableIntObjectMapOf() {
        return new MutableIntObjectMap(0, 1, null);
    }

    public static final MutableIntObjectMap mutableIntObjectMapOf(int v, Object object0) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        return mutableIntObjectMap0;
    }

    public static final MutableIntObjectMap mutableIntObjectMapOf(int v, Object object0, int v1, Object object1) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        mutableIntObjectMap0.set(v1, object1);
        return mutableIntObjectMap0;
    }

    public static final MutableIntObjectMap mutableIntObjectMapOf(int v, Object object0, int v1, Object object1, int v2, Object object2) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        mutableIntObjectMap0.set(v1, object1);
        mutableIntObjectMap0.set(v2, object2);
        return mutableIntObjectMap0;
    }

    public static final MutableIntObjectMap mutableIntObjectMapOf(int v, Object object0, int v1, Object object1, int v2, Object object2, int v3, Object object3) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        mutableIntObjectMap0.set(v1, object1);
        mutableIntObjectMap0.set(v2, object2);
        mutableIntObjectMap0.set(v3, object3);
        return mutableIntObjectMap0;
    }

    public static final MutableIntObjectMap mutableIntObjectMapOf(int v, Object object0, int v1, Object object1, int v2, Object object2, int v3, Object object3, int v4, Object object4) {
        MutableIntObjectMap mutableIntObjectMap0 = new MutableIntObjectMap(0, 1, null);
        mutableIntObjectMap0.set(v, object0);
        mutableIntObjectMap0.set(v1, object1);
        mutableIntObjectMap0.set(v2, object2);
        mutableIntObjectMap0.set(v3, object3);
        mutableIntObjectMap0.set(v4, object4);
        return mutableIntObjectMap0;
    }
}

