package kotlin.jvm.internal;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001E\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A#\u0010\u0006\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\bH\u0007¢\u0006\u0004\b\t\u0010\n\u001A5\u0010\u0006\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\b2\u0010\u0010\u000B\u001A\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0001H\u0007¢\u0006\u0004\b\t\u0010\f\u001A~\u0010\r\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0010\u0007\u001A\u0006\u0012\u0002\b\u00030\b2\u0014\u0010\u000E\u001A\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u000F2\u001A\u0010\u0010\u001A\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u00112(\u0010\u0012\u001A$\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00010\u0013H\u0082\b¢\u0006\u0002\u0010\u0014\"\u0018\u0010\u0000\u001A\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000E\u0010\u0004\u001A\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"EMPTY", "", "", "[Ljava/lang/Object;", "MAX_SIZE", "", "collectionToArray", "collection", "", "toArray", "(Ljava/util/Collection;)[Ljava/lang/Object;", "a", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "toArrayImpl", "empty", "Lkotlin/Function0;", "alloc", "Lkotlin/Function1;", "trim", "Lkotlin/Function2;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)[Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class CollectionToArray {
    private static final Object[] EMPTY = null;
    private static final int MAX_SIZE = 0x7FFFFFFD;

    static {
        CollectionToArray.EMPTY = new Object[0];
    }

    public static final Object[] toArray(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "collection");
        int v = collection0.size();
        if(v == 0) {
            return CollectionToArray.EMPTY;
        }
        Iterator iterator0 = collection0.iterator();
        if(!iterator0.hasNext()) {
            return CollectionToArray.EMPTY;
        }
        Object[] arr_object = new Object[v];
        for(int v1 = 0; true; ++v1) {
            Object object0 = iterator0.next();
            arr_object[v1] = object0;
            if(v1 + 1 >= arr_object.length) {
                if(!iterator0.hasNext()) {
                    return arr_object;
                }
                int v2 = (v1 + 1) * 3 + 1 >>> 1;
                if(v2 <= v1 + 1 && v1 + 1 >= 0x7FFFFFFD) {
                    throw new OutOfMemoryError();
                }
                arr_object = Arrays.copyOf(arr_object, v2);
                Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(result, newSize)");
            }
            else if(!iterator0.hasNext()) {
                Object[] arr_object1 = Arrays.copyOf(arr_object, v1 + 1);
                Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(result, size)");
                return arr_object1;
            }
        }
    }

    public static final Object[] toArray(Collection collection0, Object[] arr_object) {
        Object[] arr_object1;
        Intrinsics.checkNotNullParameter(collection0, "collection");
        arr_object.getClass();
        int v = collection0.size();
        if(v == 0) {
            if(arr_object.length <= 0) {
                return arr_object;
            }
            arr_object[0] = null;
            return arr_object;
        }
        Iterator iterator0 = collection0.iterator();
        if(!iterator0.hasNext()) {
            if(arr_object.length > 0) {
                arr_object[0] = null;
            }
            return arr_object;
        }
        if(v <= arr_object.length) {
            arr_object1 = arr_object;
        }
        else {
            Object object0 = Array.newInstance(arr_object.getClass().getComponentType(), v);
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            arr_object1 = (Object[])object0;
        }
        for(int v1 = 0; true; ++v1) {
            Object object1 = iterator0.next();
            arr_object1[v1] = object1;
            if(v1 + 1 >= arr_object1.length) {
                if(!iterator0.hasNext()) {
                    return arr_object1;
                }
                int v2 = (v1 + 1) * 3 + 1 >>> 1;
                if(v2 <= v1 + 1 && v1 + 1 >= 0x7FFFFFFD) {
                    throw new OutOfMemoryError();
                }
                arr_object1 = Arrays.copyOf(arr_object1, v2);
                Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(result, newSize)");
            }
            else if(!iterator0.hasNext()) {
                if(arr_object1 == arr_object) {
                    arr_object[v1 + 1] = null;
                    return arr_object;
                }
                Object[] arr_object2 = Arrays.copyOf(arr_object1, v1 + 1);
                Intrinsics.checkNotNullExpressionValue(arr_object2, "copyOf(result, size)");
                return arr_object2;
            }
        }
    }

    private static final Object[] toArrayImpl(Collection collection0, Function0 function00, Function1 function10, Function2 function20) {
        int v = collection0.size();
        if(v == 0) {
            return (Object[])function00.invoke();
        }
        Iterator iterator0 = collection0.iterator();
        if(!iterator0.hasNext()) {
            return (Object[])function00.invoke();
        }
        Object[] arr_object = (Object[])function10.invoke(v);
        for(int v1 = 0; true; ++v1) {
            Object object0 = iterator0.next();
            arr_object[v1] = object0;
            if(v1 + 1 >= arr_object.length) {
                if(!iterator0.hasNext()) {
                    return arr_object;
                }
                int v2 = (v1 + 1) * 3 + 1 >>> 1;
                if(v2 <= v1 + 1 && v1 + 1 >= 0x7FFFFFFD) {
                    throw new OutOfMemoryError();
                }
                arr_object = Arrays.copyOf(arr_object, v2);
                Intrinsics.checkNotNullExpressionValue(arr_object, "copyOf(result, newSize)");
            }
            else if(!iterator0.hasNext()) {
                return (Object[])function20.invoke(arr_object, ((int)(v1 + 1)));
            }
        }
    }
}

