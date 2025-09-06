package kotlin.collections.builders;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000E\n\u0002\b\u0002\u001A!\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0005\u001A+\u0010\u0006\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0001\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\b\u001A\u00020\u0004H\u0000¢\u0006\u0002\u0010\t\u001A%\u0010\n\u001A\u00020\u000B\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\f\u001A\u00020\u0004H\u0000¢\u0006\u0002\u0010\r\u001A-\u0010\u000E\u001A\u00020\u000B\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u000F\u001A\u00020\u00042\u0006\u0010\u0010\u001A\u00020\u0004H\u0000¢\u0006\u0002\u0010\u0011\u001A9\u0010\u0012\u001A\u00020\u0013\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00020\u00042\n\u0010\u0016\u001A\u0006\u0012\u0002\b\u00030\u0017H\u0002¢\u0006\u0002\u0010\u0018\u001A-\u0010\u0019\u001A\u00020\u0004\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00020\u0004H\u0002¢\u0006\u0002\u0010\u001A\u001A/\u0010\u001B\u001A\u00020\u001C\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u00012\u0006\u0010\u0014\u001A\u00020\u00042\u0006\u0010\u0015\u001A\u00020\u0004H\u0002¢\u0006\u0002\u0010\u001D¨\u0006\u001E"}, d2 = {"arrayOfUninitializedElements", "", "E", "size", "", "(I)[Ljava/lang/Object;", "copyOfUninitializedElements", "T", "newSize", "([Ljava/lang/Object;I)[Ljava/lang/Object;", "resetAt", "", "index", "([Ljava/lang/Object;I)V", "resetRange", "fromIndex", "toIndex", "([Ljava/lang/Object;II)V", "subarrayContentEquals", "", "offset", "length", "other", "", "([Ljava/lang/Object;IILjava/util/List;)Z", "subarrayContentHashCode", "([Ljava/lang/Object;II)I", "subarrayContentToString", "", "([Ljava/lang/Object;II)Ljava/lang/String;", "kotlin-stdlib"}, k = 2, mv = {1, 9, 0}, xi = 0x30)
public final class ListBuilderKt {
    public static final boolean access$subarrayContentEquals(Object[] arr_object, int v, int v1, List list0) {
        return ListBuilderKt.subarrayContentEquals(arr_object, v, v1, list0);
    }

    public static final Object[] arrayOfUninitializedElements(int v) {
        if(v < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        return new Object[v];
    }

    public static final Object[] copyOfUninitializedElements(Object[] arr_object, int v) {
        Intrinsics.checkNotNullParameter(arr_object, "<this>");
        Object[] arr_object1 = Arrays.copyOf(arr_object, v);
        Intrinsics.checkNotNullExpressionValue(arr_object1, "copyOf(this, newSize)");
        return arr_object1;
    }

    public static final void resetAt(Object[] arr_object, int v) {
        Intrinsics.checkNotNullParameter(arr_object, "<this>");
        arr_object[v] = null;
    }

    public static final void resetRange(Object[] arr_object, int v, int v1) {
        Intrinsics.checkNotNullParameter(arr_object, "<this>");
        while(v < v1) {
            ListBuilderKt.resetAt(arr_object, v);
            ++v;
        }
    }

    private static final boolean subarrayContentEquals(Object[] arr_object, int v, int v1, List list0) {
        if(v1 != list0.size()) {
            return false;
        }
        for(int v2 = 0; v2 < v1; ++v2) {
            if(!Intrinsics.areEqual(arr_object[v + v2], list0.get(v2))) {
                return false;
            }
        }
        return true;
    }

    private static final int subarrayContentHashCode(Object[] arr_object, int v, int v1) {
        int v2 = 1;
        for(int v3 = 0; v3 < v1; ++v3) {
            Object object0 = arr_object[v + v3];
            v2 = v2 * 0x1F + (object0 == null ? 0 : object0.hashCode());
        }
        return v2;
    }

    private static final String subarrayContentToString(Object[] arr_object, int v, int v1) {
        StringBuilder stringBuilder0 = new StringBuilder(v1 * 3 + 2);
        stringBuilder0.append("[");
        for(int v2 = 0; v2 < v1; ++v2) {
            if(v2 > 0) {
                stringBuilder0.append(", ");
            }
            stringBuilder0.append(arr_object[v + v2]);
        }
        stringBuilder0.append("]");
        String s = stringBuilder0.toString();
        Intrinsics.checkNotNullExpressionValue(s, "sb.toString()");
        return s;
    }
}

