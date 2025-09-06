package kotlin.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010#\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\u001AN\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001A\u00020\u00042\u001F\b\u0001\u0010\u0005\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00A2\u0006\u0002\b\tH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001AF\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u001F\b\u0001\u0010\u0005\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u00A2\u0006\u0002\b\tH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001A\u0012\u0010\n\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0001\"\u0004\b\u0000\u0010\u000B\u001A\u001F\u0010\f\u001A\u0012\u0012\u0004\u0012\u0002H\u000B0\rj\b\u0012\u0004\u0012\u0002H\u000B`\u000E\"\u0004\b\u0000\u0010\u000BH\u0087\b\u001A5\u0010\f\u001A\u0012\u0012\u0004\u0012\u0002H\u000B0\rj\b\u0012\u0004\u0012\u0002H\u000B`\u000E\"\u0004\b\u0000\u0010\u000B2\u0012\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u000B0\u0010\"\u0002H\u000B\u00A2\u0006\u0002\u0010\u0011\u001A\u001F\u0010\u0012\u001A\u0012\u0012\u0004\u0012\u0002H\u000B0\u0013j\b\u0012\u0004\u0012\u0002H\u000B`\u0014\"\u0004\b\u0000\u0010\u000BH\u0087\b\u001A5\u0010\u0012\u001A\u0012\u0012\u0004\u0012\u0002H\u000B0\u0013j\b\u0012\u0004\u0012\u0002H\u000B`\u0014\"\u0004\b\u0000\u0010\u000B2\u0012\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u000B0\u0010\"\u0002H\u000B\u00A2\u0006\u0002\u0010\u0015\u001A\u0015\u0010\u0016\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0007\"\u0004\b\u0000\u0010\u000BH\u0087\b\u001A+\u0010\u0016\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0007\"\u0004\b\u0000\u0010\u000B2\u0012\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u000B0\u0010\"\u0002H\u000B\u00A2\u0006\u0002\u0010\u0017\u001A\u0015\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0001\"\u0004\b\u0000\u0010\u000BH\u0087\b\u001A+\u0010\u0018\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0001\"\u0004\b\u0000\u0010\u000B2\u0012\u0010\u000F\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u000B0\u0010\"\u0002H\u000B\u00A2\u0006\u0002\u0010\u0017\u001A\'\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0001\"\b\b\u0000\u0010\u000B*\u00020\u001A2\b\u0010\u001B\u001A\u0004\u0018\u0001H\u000BH\u0007\u00A2\u0006\u0002\u0010\u001C\u001A5\u0010\u0019\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0001\"\b\b\u0000\u0010\u000B*\u00020\u001A2\u0016\u0010\u000F\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u000B0\u0010\"\u0004\u0018\u0001H\u000BH\u0007\u00A2\u0006\u0002\u0010\u0017\u001A\u001E\u0010\u001D\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0001\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u0001H\u0000\u001A!\u0010\u001E\u001A\b\u0012\u0004\u0012\u0002H\u000B0\u0001\"\u0004\b\u0000\u0010\u000B*\n\u0012\u0004\u0012\u0002H\u000B\u0018\u00010\u0001H\u0087\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006\u001F"}, d2 = {"buildSet", "", "E", "capacity", "", "builderAction", "Lkotlin/Function1;", "", "", "Lkotlin/ExtensionFunctionType;", "emptySet", "T", "hashSetOf", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "elements", "", "([Ljava/lang/Object;)Ljava/util/HashSet;", "linkedSetOf", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "([Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "mutableSetOf", "([Ljava/lang/Object;)Ljava/util/Set;", "setOf", "setOfNotNull", "", "element", "(Ljava/lang/Object;)Ljava/util/Set;", "optimizeReadOnlySet", "orEmpty", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/SetsKt")
class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
    private static final Set buildSet(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        Set set0 = SetsKt.createSetBuilder(v);
        function10.invoke(set0);
        return SetsKt.build(set0);
    }

    private static final Set buildSet(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        Set set0 = SetsKt.createSetBuilder();
        function10.invoke(set0);
        return SetsKt.build(set0);
    }

    public static final Set emptySet() {
        return EmptySet.INSTANCE;
    }

    private static final HashSet hashSetOf() {
        return new HashSet();
    }

    public static final HashSet hashSetOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return (HashSet)ArraysKt.toCollection(arr_object, new HashSet(MapsKt.mapCapacity(arr_object.length)));
    }

    private static final LinkedHashSet linkedSetOf() {
        return new LinkedHashSet();
    }

    public static final LinkedHashSet linkedSetOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return (LinkedHashSet)ArraysKt.toCollection(arr_object, new LinkedHashSet(MapsKt.mapCapacity(arr_object.length)));
    }

    private static final Set mutableSetOf() {
        return new LinkedHashSet();
    }

    public static final Set mutableSetOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return (Set)ArraysKt.toCollection(arr_object, new LinkedHashSet(MapsKt.mapCapacity(arr_object.length)));
    }

    public static final Set optimizeReadOnlySet(Set set0) {
        Intrinsics.checkNotNullParameter(set0, "<this>");
        switch(set0.size()) {
            case 0: {
                return SetsKt.emptySet();
            }
            case 1: {
                Object object0 = set0.iterator().next();
                return SetsKt.setOf(object0);
            }
            default: {
                return set0;
            }
        }
    }

    private static final Set orEmpty(Set set0) {
        return set0 == null ? SetsKt.emptySet() : set0;
    }

    private static final Set setOf() {
        return SetsKt.emptySet();
    }

    public static final Set setOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return arr_object.length <= 0 ? SetsKt.emptySet() : ArraysKt.toSet(arr_object);
    }

    public static final Set setOfNotNull(Object object0) {
        return object0 == null ? SetsKt.emptySet() : SetsKt.setOf(object0);
    }

    public static final Set setOfNotNull(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return (Set)ArraysKt.filterNotNullTo(arr_object, new LinkedHashSet());
    }
}

