package androidx.collection;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000E\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u001A\u0012\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007\u001A\u0015\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00070\t\"\u0004\b\u0000\u0010\u0007H\u0086\b\u001A\u001F\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00070\t\"\u0004\b\u0000\u0010\u00072\u0006\u0010\n\u001A\u0002H\u0007\u00A2\u0006\u0002\u0010\u000B\u001A\'\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00070\t\"\u0004\b\u0000\u0010\u00072\u0006\u0010\n\u001A\u0002H\u00072\u0006\u0010\f\u001A\u0002H\u0007\u00A2\u0006\u0002\u0010\r\u001A/\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00070\t\"\u0004\b\u0000\u0010\u00072\u0006\u0010\n\u001A\u0002H\u00072\u0006\u0010\f\u001A\u0002H\u00072\u0006\u0010\u000E\u001A\u0002H\u0007\u00A2\u0006\u0002\u0010\u000F\u001A.\u0010\b\u001A\b\u0012\u0004\u0012\u0002H\u00070\t\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0010\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0001\"\u0002H\u0007H\u0086\b\u00A2\u0006\u0002\u0010\u0011\u001A\u0012\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u0007\u001A\u001F\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u00072\u0006\u0010\n\u001A\u0002H\u0007\u00A2\u0006\u0002\u0010\u0013\u001A\'\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u00072\u0006\u0010\n\u001A\u0002H\u00072\u0006\u0010\f\u001A\u0002H\u0007\u00A2\u0006\u0002\u0010\u0014\u001A/\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u00072\u0006\u0010\n\u001A\u0002H\u00072\u0006\u0010\f\u001A\u0002H\u00072\u0006\u0010\u000E\u001A\u0002H\u0007\u00A2\u0006\u0002\u0010\u0015\u001A+\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0005\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0010\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0001\"\u0002H\u0007\u00A2\u0006\u0002\u0010\u0016\u001A\u0018\u0010\u0017\u001A\u00020\u0018*\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u001A\u001A\u00020\u001BH\u0002\u001A \u0010\u001C\u001A\u00020\u0018*\u0006\u0012\u0002\b\u00030\u00192\u0006\u0010\u001D\u001A\u00020\u001B2\u0006\u0010\u001E\u001A\u00020\u001BH\u0002\"\u0018\u0010\u0000\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004\u00A2\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0005X\u0082\u0004\u00A2\u0006\u0002\n\u0000\u00A8\u0006\u001F"}, d2 = {"EmptyArray", "", "", "[Ljava/lang/Object;", "EmptyObjectList", "Landroidx/collection/ObjectList;", "emptyObjectList", "E", "mutableObjectListOf", "Landroidx/collection/MutableObjectList;", "element1", "(Ljava/lang/Object;)Landroidx/collection/MutableObjectList;", "element2", "(Ljava/lang/Object;Ljava/lang/Object;)Landroidx/collection/MutableObjectList;", "element3", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Landroidx/collection/MutableObjectList;", "elements", "([Ljava/lang/Object;)Landroidx/collection/MutableObjectList;", "objectListOf", "(Ljava/lang/Object;)Landroidx/collection/ObjectList;", "(Ljava/lang/Object;Ljava/lang/Object;)Landroidx/collection/ObjectList;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Landroidx/collection/ObjectList;", "([Ljava/lang/Object;)Landroidx/collection/ObjectList;", "checkIndex", "", "", "index", "", "checkSubIndex", "fromIndex", "toIndex", "collection"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class ObjectListKt {
    private static final Object[] EmptyArray;
    private static final ObjectList EmptyObjectList;

    static {
        ObjectListKt.EmptyArray = new Object[0];
        ObjectListKt.EmptyObjectList = new MutableObjectList(0);
    }

    private static final void checkIndex(List list0, int v) {
        int v1 = list0.size();
        if(v < 0 || v >= v1) {
            throw new IndexOutOfBoundsException("Index " + v + " is out of bounds. The list has " + v1 + " elements.");
        }
    }

    private static final void checkSubIndex(List list0, int v, int v1) {
        int v2 = list0.size();
        if(v > v1) {
            throw new IllegalArgumentException("Indices are out of order. fromIndex (" + v + ") is greater than toIndex (" + v1 + ").");
        }
        if(v < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + v + ") is less than 0.");
        }
        if(v1 > v2) {
            throw new IndexOutOfBoundsException("toIndex (" + v1 + ") is more than than the list size (" + v2 + ')');
        }
    }

    public static final ObjectList emptyObjectList() {
        Intrinsics.checkNotNull(ObjectListKt.EmptyObjectList, "null cannot be cast to non-null type androidx.collection.ObjectList<E of androidx.collection.ObjectListKt.emptyObjectList>");
        return ObjectListKt.EmptyObjectList;
    }

    public static final MutableObjectList mutableObjectListOf() {
        return new MutableObjectList(0, 1, null);
    }

    public static final MutableObjectList mutableObjectListOf(Object object0) {
        MutableObjectList mutableObjectList0 = new MutableObjectList(1);
        mutableObjectList0.add(object0);
        return mutableObjectList0;
    }

    public static final MutableObjectList mutableObjectListOf(Object object0, Object object1) {
        MutableObjectList mutableObjectList0 = new MutableObjectList(2);
        mutableObjectList0.add(object0);
        mutableObjectList0.add(object1);
        return mutableObjectList0;
    }

    public static final MutableObjectList mutableObjectListOf(Object object0, Object object1, Object object2) {
        MutableObjectList mutableObjectList0 = new MutableObjectList(3);
        mutableObjectList0.add(object0);
        mutableObjectList0.add(object1);
        mutableObjectList0.add(object2);
        return mutableObjectList0;
    }

    public static final MutableObjectList mutableObjectListOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        MutableObjectList mutableObjectList0 = new MutableObjectList(arr_object.length);
        mutableObjectList0.plusAssign(arr_object);
        return mutableObjectList0;
    }

    public static final ObjectList objectListOf() {
        Intrinsics.checkNotNull(ObjectListKt.EmptyObjectList, "null cannot be cast to non-null type androidx.collection.ObjectList<E of androidx.collection.ObjectListKt.objectListOf>");
        return ObjectListKt.EmptyObjectList;
    }

    public static final ObjectList objectListOf(Object object0) {
        return ObjectListKt.mutableObjectListOf(object0);
    }

    public static final ObjectList objectListOf(Object object0, Object object1) {
        return ObjectListKt.mutableObjectListOf(object0, object1);
    }

    public static final ObjectList objectListOf(Object object0, Object object1, Object object2) {
        return ObjectListKt.mutableObjectListOf(object0, object1, object2);
    }

    public static final ObjectList objectListOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        MutableObjectList mutableObjectList0 = new MutableObjectList(arr_object.length);
        mutableObjectList0.plusAssign(arr_object);
        return mutableObjectList0;
    }
}

