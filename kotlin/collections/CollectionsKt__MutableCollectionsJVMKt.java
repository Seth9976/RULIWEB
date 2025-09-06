package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000F\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A&\u0010\u0000\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001A\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0005\u001A\u0019\u0010\u0006\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b\u001A!\u0010\u0006\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0007\u001A\u00020\bH\u0087\b\u001A \u0010\t\u001A\u00020\u0001\"\u000E\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n*\b\u0012\u0004\u0012\u0002H\u00020\u0003\u001A6\u0010\t\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0018\u0010\u000B\u001A\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\r0\fH\u0087\bø\u0001\u0000\u001A5\u0010\t\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001A\u0010\u000E\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000Fj\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0010H\u0087\b\u001A2\u0010\u0011\u001A\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u001A\u0010\u000E\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u000Fj\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"fill", "", "T", "", "value", "(Ljava/util/List;Ljava/lang/Object;)V", "shuffle", "random", "Ljava/util/Random;", "sort", "", "comparison", "Lkotlin/Function2;", "", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "sortWith", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
    private static final void fill(List list0, Object object0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Collections.fill(list0, object0);
    }

    private static final void shuffle(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Collections.shuffle(list0);
    }

    private static final void shuffle(List list0, Random random0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(random0, "random");
        Collections.shuffle(list0, random0);
    }

    public static final void sort(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        if(list0.size() > 1) {
            Collections.sort(list0);
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(comparator) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(comparator)", imports = {}))
    private static final void sort(List list0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        throw new NotImplementedError(null, 1, null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use sortWith(Comparator(comparison)) instead.", replaceWith = @ReplaceWith(expression = "this.sortWith(Comparator(comparison))", imports = {}))
    private static final void sort(List list0, Function2 function20) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "comparison");
        throw new NotImplementedError(null, 1, null);
    }

    public static final void sortWith(List list0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        if(list0.size() > 1) {
            Collections.sort(list0, comparator0);
        }
    }
}

