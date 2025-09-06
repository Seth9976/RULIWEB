package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\u001AG\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001A\u0002H\u00012\u0006\u0010\u0003\u001A\u0002H\u00012\u0006\u0010\u0004\u001A\u0002H\u00012\u001A\u0010\u0005\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\b\u001A?\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001A\u0002H\u00012\u0006\u0010\u0003\u001A\u0002H\u00012\u001A\u0010\u0005\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\t\u001AK\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001A\u0002H\u00012\u0012\u0010\n\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u000B\"\u0002H\u00012\u001A\u0010\u0005\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\f\u001AG\u0010\r\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001A\u0002H\u00012\u0006\u0010\u0003\u001A\u0002H\u00012\u0006\u0010\u0004\u001A\u0002H\u00012\u001A\u0010\u0005\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\b\u001A?\u0010\r\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001A\u0002H\u00012\u0006\u0010\u0003\u001A\u0002H\u00012\u001A\u0010\u0005\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\t\u001AK\u0010\r\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001A\u0002H\u00012\u0012\u0010\n\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\u000B\"\u0002H\u00012\u001A\u0010\u0005\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00010\u0006j\n\u0012\u0006\b\u0000\u0012\u0002H\u0001`\u0007H\u0007¢\u0006\u0002\u0010\f¨\u0006\u000E"}, d2 = {"maxOf", "T", "a", "b", "c", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "other", "", "(Ljava/lang/Object;[Ljava/lang/Object;Ljava/util/Comparator;)Ljava/lang/Object;", "minOf", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/comparisons/ComparisonsKt")
class ComparisonsKt___ComparisonsKt extends ComparisonsKt___ComparisonsJvmKt {
    public static final Object maxOf(Object object0, Object object1, Object object2, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return ComparisonsKt.maxOf(object0, ComparisonsKt.maxOf(object1, object2, comparator0), comparator0);
    }

    public static final Object maxOf(Object object0, Object object1, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return comparator0.compare(object0, object1) < 0 ? object1 : object0;
    }

    public static final Object maxOf(Object object0, Object[] arr_object, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_object, "other");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        for(int v = 0; v < arr_object.length; ++v) {
            Object object1 = arr_object[v];
            if(comparator0.compare(object0, object1) < 0) {
                object0 = object1;
            }
        }
        return object0;
    }

    public static final Object minOf(Object object0, Object object1, Object object2, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return ComparisonsKt.minOf(object0, ComparisonsKt.minOf(object1, object2, comparator0), comparator0);
    }

    public static final Object minOf(Object object0, Object object1, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return comparator0.compare(object0, object1) > 0 ? object1 : object0;
    }

    public static final Object minOf(Object object0, Object[] arr_object, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(arr_object, "other");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        for(int v = 0; v < arr_object.length; ++v) {
            Object object1 = arr_object[v];
            if(comparator0.compare(object0, object1) > 0) {
                object0 = object1;
            }
        }
        return object0;
    }
}

