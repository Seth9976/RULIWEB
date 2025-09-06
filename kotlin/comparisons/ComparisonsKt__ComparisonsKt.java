package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000B\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001A>\u0010\u0000\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u001A\b\u0004\u0010\u0004\u001A\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00F8\u0001\u0000\u001AY\u0010\u0000\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u000226\u0010\u0007\u001A\u001C\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\b\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005\u00A2\u0006\u0002\u0010\t\u001AZ\u0010\u0000\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00F8\u0001\u0000\u001A>\u0010\f\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u00022\u001A\b\u0004\u0010\u0004\u001A\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00F8\u0001\u0000\u001AZ\u0010\f\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00F8\u0001\u0000\u001A-\u0010\r\u001A\u00020\u000E\"\f\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00062\b\u0010\u000F\u001A\u0004\u0018\u0001H\u00022\b\u0010\u0010\u001A\u0004\u0018\u0001H\u0002\u00A2\u0006\u0002\u0010\u0011\u001AA\u0010\u0012\u001A\u00020\u000E\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000F\u001A\u0002H\u00022\u0006\u0010\u0010\u001A\u0002H\u00022\u0018\u0010\u0004\u001A\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0013\u001AY\u0010\u0012\u001A\u00020\u000E\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000F\u001A\u0002H\u00022\u0006\u0010\u0010\u001A\u0002H\u000226\u0010\u0007\u001A\u001C\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\b\"\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005\u00A2\u0006\u0002\u0010\u0014\u001A]\u0010\u0012\u001A\u00020\u000E\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n2\u0006\u0010\u000F\u001A\u0002H\u00022\u0006\u0010\u0010\u001A\u0002H\u00022\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0012\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001AG\u0010\u0016\u001A\u00020\u000E\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u000F\u001A\u0002H\u00022\u0006\u0010\u0010\u001A\u0002H\u00022 \u0010\u0007\u001A\u001C\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u00050\bH\u0002\u00A2\u0006\u0004\b\u0017\u0010\u0014\u001A&\u0010\u0018\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u000E\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001A-\u0010\u0019\u001A\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\u000E\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087\b\u001A@\u0010\u0019\u001A\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001A2\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003\u001A-\u0010\u001B\u001A\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\u000E\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006H\u0087\b\u001A@\u0010\u001B\u001A\u0016\u0012\u0006\u0012\u0004\u0018\u0001H\u00020\u0001j\n\u0012\u0006\u0012\u0004\u0018\u0001H\u0002`\u0003\"\b\b\u0000\u0010\u0002*\u00020\u001A2\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003\u001A&\u0010\u001C\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u000E\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0006\u001A0\u0010\u001D\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\u001AO\u0010\u001E\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003H\u0086\u0004\u001AR\u0010\u001F\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001A\b\u0004\u0010\u0004\u001A\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00F8\u0001\u0000\u001An\u0010\u001F\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00F8\u0001\u0000\u001AR\u0010 \u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001A\b\u0004\u0010\u0004\u001A\u0014\u0012\u0004\u0012\u0002H\u0002\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00060\u0005H\u0087\b\u00F8\u0001\u0000\u001An\u0010 \u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\n*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\n0\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\n`\u00032\u0014\b\u0004\u0010\u0004\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\n0\u0005H\u0087\b\u00F8\u0001\u0000\u001Ap\u0010!\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u000328\b\u0004\u0010\"\u001A2\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u000F\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b$\u0012\b\b%\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u000E0#H\u0087\b\u00F8\u0001\u0000\u001AO\u0010&\u001A\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u0003\"\u0004\b\u0000\u0010\u0002*\u0012\u0012\u0004\u0012\u0002H\u00020\u0001j\b\u0012\u0004\u0012\u0002H\u0002`\u00032\u001A\u0010\u000B\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0001j\n\u0012\u0006\b\u0000\u0012\u0002H\u0002`\u0003H\u0086\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006\'"}, d2 = {"compareBy", "Ljava/util/Comparator;", "T", "Lkotlin/Comparator;", "selector", "Lkotlin/Function1;", "", "selectors", "", "([Lkotlin/jvm/functions/Function1;)Ljava/util/Comparator;", "K", "comparator", "compareByDescending", "compareValues", "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)I", "compareValuesBy", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;[Lkotlin/jvm/functions/Function1;)I", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/Comparator;Lkotlin/jvm/functions/Function1;)I", "compareValuesByImpl", "compareValuesByImpl$ComparisonsKt__ComparisonsKt", "naturalOrder", "nullsFirst", "", "nullsLast", "reverseOrder", "reversed", "then", "thenBy", "thenByDescending", "thenComparator", "comparison", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "thenDescending", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/comparisons/ComparisonsKt")
class ComparisonsKt__ComparisonsKt {
    private static final Comparator compareBy(Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                Object object2 = function10.invoke(object0);
                Object object3 = function10.invoke(object1);
                return comparator0.compare(object2, object3);
            }
        };
    }

    private static final Comparator compareBy(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "selector");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                return ComparisonsKt.compareValues(((Comparable)function10.invoke(object0)), ((Comparable)function10.invoke(object1)));
            }
        };
    }

    public static final Comparator compareBy(Function1[] arr_function1) {
        Intrinsics.checkNotNullParameter(arr_function1, "selectors");
        if(arr_function1.length <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        return new ComparisonsKt__ComparisonsKt..ExternalSyntheticLambda3(arr_function1);
    }

    private static final int compareBy$lambda$0$ComparisonsKt__ComparisonsKt(Function1[] arr_function1, Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(arr_function1, "$selectors");
        return ComparisonsKt__ComparisonsKt.compareValuesByImpl$ComparisonsKt__ComparisonsKt(object0, object1, arr_function1);
    }

    private static final Comparator compareByDescending(Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                Object object2 = function10.invoke(object1);
                Object object3 = function10.invoke(object0);
                return comparator0.compare(object2, object3);
            }
        };
    }

    private static final Comparator compareByDescending(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "selector");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                return ComparisonsKt.compareValues(((Comparable)function10.invoke(object1)), ((Comparable)function10.invoke(object0)));
            }
        };
    }

    public static final int compareValues(Comparable comparable0, Comparable comparable1) {
        if(comparable0 == comparable1) {
            return 0;
        }
        if(comparable0 == null) {
            return -1;
        }
        return comparable1 == null ? 1 : comparable0.compareTo(comparable1);
    }

    private static final int compareValuesBy(Object object0, Object object1, Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        return comparator0.compare(function10.invoke(object0), function10.invoke(object1));
    }

    private static final int compareValuesBy(Object object0, Object object1, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "selector");
        return ComparisonsKt.compareValues(((Comparable)function10.invoke(object0)), ((Comparable)function10.invoke(object1)));
    }

    public static final int compareValuesBy(Object object0, Object object1, Function1[] arr_function1) {
        Intrinsics.checkNotNullParameter(arr_function1, "selectors");
        if(arr_function1.length <= 0) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        return ComparisonsKt__ComparisonsKt.compareValuesByImpl$ComparisonsKt__ComparisonsKt(object0, object1, arr_function1);
    }

    private static final int compareValuesByImpl$ComparisonsKt__ComparisonsKt(Object object0, Object object1, Function1[] arr_function1) {
        for(int v = 0; v < arr_function1.length; ++v) {
            Function1 function10 = arr_function1[v];
            int v1 = ComparisonsKt.compareValues(((Comparable)function10.invoke(object0)), ((Comparable)function10.invoke(object1)));
            if(v1 != 0) {
                return v1;
            }
        }
        return 0;
    }

    public static final Comparator naturalOrder() {
        Intrinsics.checkNotNull(NaturalOrderComparator.INSTANCE, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.naturalOrder> }");
        return NaturalOrderComparator.INSTANCE;
    }

    private static final Comparator nullsFirst() {
        return ComparisonsKt.nullsFirst(ComparisonsKt.naturalOrder());
    }

    public static final Comparator nullsFirst(Comparator comparator0) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return new ComparisonsKt__ComparisonsKt..ExternalSyntheticLambda4(comparator0);
    }

    private static final int nullsFirst$lambda$3$ComparisonsKt__ComparisonsKt(Comparator comparator0, Object object0, Object object1) {
        Intrinsics.checkNotNullParameter(comparator0, "$comparator");
        if(object0 == object1) {
            return 0;
        }
        if(object0 == null) {
            return -1;
        }
        return object1 == null ? 1 : comparator0.compare(object0, object1);
    }

    private static final Comparator nullsLast() {
        return ComparisonsKt.nullsLast(ComparisonsKt.naturalOrder());
    }

    public static final Comparator nullsLast(Comparator comparator0) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (Object object0, Object object1) -> {
            Intrinsics.checkNotNullParameter(comparator0, "$comparator");
            if(object0 == object1) {
                return 0;
            }
            if(object0 == null) {
                return 1;
            }
            return object1 == null ? -1 : comparator0.compare(object0, object1);
        };
    }

    // 检测为 Lambda 实现
    private static final int nullsLast$lambda$4$ComparisonsKt__ComparisonsKt(Comparator comparator0, Object object0, Object object1) [...]

    public static final Comparator reverseOrder() {
        Intrinsics.checkNotNull(ReverseOrderComparator.INSTANCE, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reverseOrder> }");
        return ReverseOrderComparator.INSTANCE;
    }

    public static final Comparator reversed(Comparator comparator0) {
        Intrinsics.checkNotNullParameter(comparator0, "<this>");
        if(comparator0 instanceof ReversedComparator) {
            return ((ReversedComparator)comparator0).getComparator();
        }
        if(Intrinsics.areEqual(comparator0, NaturalOrderComparator.INSTANCE)) {
            Intrinsics.checkNotNull(ReverseOrderComparator.INSTANCE, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed> }");
            return ReverseOrderComparator.INSTANCE;
        }
        if(Intrinsics.areEqual(comparator0, ReverseOrderComparator.INSTANCE)) {
            Intrinsics.checkNotNull(NaturalOrderComparator.INSTANCE, "null cannot be cast to non-null type java.util.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed>{ kotlin.TypeAliasesKt.Comparator<T of kotlin.comparisons.ComparisonsKt__ComparisonsKt.reversed> }");
            return NaturalOrderComparator.INSTANCE;
        }
        return new ReversedComparator(comparator0);
    }

    public static final Comparator then(Comparator comparator0, Comparator comparator1) {
        Intrinsics.checkNotNullParameter(comparator0, "<this>");
        Intrinsics.checkNotNullParameter(comparator1, "comparator");
        return (Object object0, Object object1) -> {
            Intrinsics.checkNotNullParameter(comparator0, "$this_then");
            Intrinsics.checkNotNullParameter(comparator1, "$comparator");
            int v = comparator0.compare(object0, object1);
            return v == 0 ? comparator1.compare(object0, object1) : v;
        };
    }

    // 检测为 Lambda 实现
    private static final int then$lambda$1$ComparisonsKt__ComparisonsKt(Comparator comparator0, Comparator comparator1, Object object0, Object object1) [...]

    private static final Comparator thenBy(Comparator comparator0, Comparator comparator1, Function1 function10) {
        Intrinsics.checkNotNullParameter(comparator0, "<this>");
        Intrinsics.checkNotNullParameter(comparator1, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                int v = comparator0.compare(object0, object1);
                if(v != 0) {
                    return v;
                }
                Object object2 = function10.invoke(object0);
                Object object3 = function10.invoke(object1);
                return comparator1.compare(object2, object3);
            }
        };
    }

    private static final Comparator thenBy(Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(comparator0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                int v = comparator0.compare(object0, object1);
                return v == 0 ? ComparisonsKt.compareValues(((Comparable)function10.invoke(object0)), ((Comparable)function10.invoke(object1))) : v;
            }
        };
    }

    private static final Comparator thenByDescending(Comparator comparator0, Comparator comparator1, Function1 function10) {
        Intrinsics.checkNotNullParameter(comparator0, "<this>");
        Intrinsics.checkNotNullParameter(comparator1, "comparator");
        Intrinsics.checkNotNullParameter(function10, "selector");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                int v = comparator0.compare(object0, object1);
                if(v != 0) {
                    return v;
                }
                Object object2 = function10.invoke(object1);
                Object object3 = function10.invoke(object0);
                return comparator1.compare(object2, object3);
            }
        };
    }

    private static final Comparator thenByDescending(Comparator comparator0, Function1 function10) {
        Intrinsics.checkNotNullParameter(comparator0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                int v = comparator0.compare(object0, object1);
                return v == 0 ? ComparisonsKt.compareValues(((Comparable)function10.invoke(object1)), ((Comparable)function10.invoke(object0))) : v;
            }
        };
    }

    private static final Comparator thenComparator(Comparator comparator0, Function2 function20) {
        Intrinsics.checkNotNullParameter(comparator0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "comparison");
        return new Comparator() {
            @Override
            public final int compare(Object object0, Object object1) {
                int v = comparator0.compare(object0, object1);
                return v == 0 ? ((Number)function20.invoke(object0, object1)).intValue() : v;
            }
        };
    }

    public static final Comparator thenDescending(Comparator comparator0, Comparator comparator1) {
        Intrinsics.checkNotNullParameter(comparator0, "<this>");
        Intrinsics.checkNotNullParameter(comparator1, "comparator");
        return (Object object0, Object object1) -> {
            Intrinsics.checkNotNullParameter(comparator0, "$this_thenDescending");
            Intrinsics.checkNotNullParameter(comparator1, "$comparator");
            int v = comparator0.compare(object0, object1);
            return v == 0 ? comparator1.compare(object1, object0) : v;
        };
    }

    // 检测为 Lambda 实现
    private static final int thenDescending$lambda$2$ComparisonsKt__ComparisonsKt(Comparator comparator0, Comparator comparator1, Object object0, Object object1) [...]
}

