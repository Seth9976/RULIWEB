package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.collections.builders.SetBuilder;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\"\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0001\u001A?\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0006\u001A\u00020\u00072\u001D\u0010\b\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000BH\u0081\bø\u0001\u0000\u001A7\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001D\u0010\b\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0004\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000BH\u0081\bø\u0001\u0000\u001A\u0014\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u0002H\u0001\u001A\u001C\u0010\f\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0006\u001A\u00020\u0007H\u0001\u001A\u001F\u0010\r\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u0001\"\u0004\b\u0000\u0010\u000E2\u0006\u0010\u000F\u001A\u0002H\u000E¢\u0006\u0002\u0010\u0010\u001A+\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u0012\"\u0004\b\u0000\u0010\u000E2\u0012\u0010\u0013\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u000E0\u0014\"\u0002H\u000E¢\u0006\u0002\u0010\u0015\u001AG\u0010\u0011\u001A\b\u0012\u0004\u0012\u0002H\u000E0\u0012\"\u0004\b\u0000\u0010\u000E2\u001A\u0010\u0016\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000E0\u0017j\n\u0012\u0006\b\u0000\u0012\u0002H\u000E`\u00182\u0012\u0010\u0013\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u000E0\u0014\"\u0002H\u000E¢\u0006\u0002\u0010\u0019\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001A"}, d2 = {"build", "", "E", "builder", "", "buildSetInternal", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "createSetBuilder", "setOf", "T", "element", "(Ljava/lang/Object;)Ljava/util/Set;", "sortedSetOf", "Ljava/util/TreeSet;", "elements", "", "([Ljava/lang/Object;)Ljava/util/TreeSet;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Comparator;[Ljava/lang/Object;)Ljava/util/TreeSet;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/SetsKt")
class SetsKt__SetsJVMKt {
    public static final Set build(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "builder");
        return ((SetBuilder)set0).build();
    }

    private static final Set buildSetInternal(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        Set set0 = SetsKt.createSetBuilder(v);
        function10.invoke(set0);
        return SetsKt.build(set0);
    }

    private static final Set buildSetInternal(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        Set set0 = SetsKt.createSetBuilder();
        function10.invoke(set0);
        return SetsKt.build(set0);
    }

    public static final Set createSetBuilder() {
        return new SetBuilder();
    }

    public static final Set createSetBuilder(int v) {
        return new SetBuilder(v);
    }

    public static final Set setOf(Object object0) {
        Set set0 = Collections.singleton(object0);
        Intrinsics.checkNotNullExpressionValue(set0, "singleton(element)");
        return set0;
    }

    public static final TreeSet sortedSetOf(Comparator comparator0, Object[] arr_object) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return (TreeSet)ArraysKt.toCollection(arr_object, new TreeSet(comparator0));
    }

    public static final TreeSet sortedSetOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return (TreeSet)ArraysKt.toCollection(arr_object, new TreeSet());
    }
}

