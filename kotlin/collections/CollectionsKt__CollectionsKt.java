package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.random.Random;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000\u0088\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001E\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000F\n\u0002\b\u0007\n\u0002\u0010\u000B\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001C\n\u0000\n\u0002\u0018\u0002\n\u0000\u001AC\u0010\u000B\u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u00072\u0006\u0010\f\u001A\u00020\u00062!\u0010\r\u001A\u001D\u0012\u0013\u0012\u00110\u0006\u00A2\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u00070\u000EH\u0087\b\u00F8\u0001\u0000\u001AC\u0010\u0012\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u00072\u0006\u0010\f\u001A\u00020\u00062!\u0010\r\u001A\u001D\u0012\u0013\u0012\u00110\u0006\u00A2\u0006\f\b\u000F\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u00070\u000EH\u0087\b\u00F8\u0001\u0000\u001A\u001F\u0010\u0014\u001A\u0012\u0012\u0004\u0012\u0002H\u00070\u0015j\b\u0012\u0004\u0012\u0002H\u0007`\u0016\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001A5\u0010\u0014\u001A\u0012\u0012\u0004\u0012\u0002H\u00070\u0015j\b\u0012\u0004\u0012\u0002H\u0007`\u0016\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007\u00A2\u0006\u0002\u0010\u0019\u001AN\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u001B0\b\"\u0004\b\u0000\u0010\u001B2\u0006\u0010\u001C\u001A\u00020\u00062\u001F\b\u0001\u0010\u001D\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001B0\u0013\u0012\u0004\u0012\u00020\u001E0\u000E\u00A2\u0006\u0002\b\u001FH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001\u001AF\u0010\u001A\u001A\b\u0012\u0004\u0012\u0002H\u001B0\b\"\u0004\b\u0000\u0010\u001B2\u001F\b\u0001\u0010\u001D\u001A\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u001B0\u0013\u0012\u0004\u0012\u00020\u001E0\u000E\u00A2\u0006\u0002\b\u001FH\u0087\b\u00F8\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001\u001A\u0012\u0010 \u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007\u001A\u0015\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001A+\u0010!\u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007\u00A2\u0006\u0002\u0010\"\u001A%\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\b\b\u0000\u0010\u0007*\u00020$2\b\u0010%\u001A\u0004\u0018\u0001H\u0007\u00A2\u0006\u0002\u0010&\u001A3\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\b\b\u0000\u0010\u0007*\u00020$2\u0016\u0010\u0017\u001A\f\u0012\b\b\u0001\u0012\u0004\u0018\u0001H\u00070\u0018\"\u0004\u0018\u0001H\u0007\u00A2\u0006\u0002\u0010\"\u001A\u0015\u0010\'\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u0007H\u0087\b\u001A+\u0010\'\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0013\"\u0004\b\u0000\u0010\u00072\u0012\u0010\u0017\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018\"\u0002H\u0007\u00A2\u0006\u0002\u0010\"\u001A%\u0010(\u001A\u00020\u001E2\u0006\u0010\f\u001A\u00020\u00062\u0006\u0010)\u001A\u00020\u00062\u0006\u0010*\u001A\u00020\u0006H\u0002\u00A2\u0006\u0002\b+\u001A\b\u0010,\u001A\u00020\u001EH\u0001\u001A\b\u0010-\u001A\u00020\u001EH\u0001\u001A%\u0010.\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0002\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0001\u0012\u0002H\u00070\u0018H\u0000\u00A2\u0006\u0002\u0010/\u001AS\u00100\u001A\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\u0006\u0010%\u001A\u0002H\u00072\u001A\u00101\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000702j\n\u0012\u0006\b\u0000\u0012\u0002H\u0007`32\b\b\u0002\u0010)\u001A\u00020\u00062\b\b\u0002\u0010*\u001A\u00020\u0006\u00A2\u0006\u0002\u00104\u001A>\u00100\u001A\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\b\b\u0002\u0010)\u001A\u00020\u00062\b\b\u0002\u0010*\u001A\u00020\u00062\u0012\u00105\u001A\u000E\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00060\u000E\u001AE\u00100\u001A\u00020\u0006\"\u000E\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u000706*\n\u0012\u0006\u0012\u0004\u0018\u0001H\u00070\b2\b\u0010%\u001A\u0004\u0018\u0001H\u00072\b\b\u0002\u0010)\u001A\u00020\u00062\b\b\u0002\u0010*\u001A\u00020\u0006\u00A2\u0006\u0002\u00107\u001Ag\u00108\u001A\u00020\u0006\"\u0004\b\u0000\u0010\u0007\"\u000E\b\u0001\u00109*\b\u0012\u0004\u0012\u0002H906*\b\u0012\u0004\u0012\u0002H\u00070\b2\b\u0010:\u001A\u0004\u0018\u0001H92\b\b\u0002\u0010)\u001A\u00020\u00062\b\b\u0002\u0010*\u001A\u00020\u00062\u0016\b\u0004\u0010;\u001A\u0010\u0012\u0004\u0012\u0002H\u0007\u0012\u0006\u0012\u0004\u0018\u0001H90\u000EH\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010<\u001A,\u0010=\u001A\u00020>\"\t\b\u0000\u0010\u0007\u00A2\u0006\u0002\b?*\b\u0012\u0004\u0012\u0002H\u00070\u00022\f\u0010\u0017\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0002H\u0087\b\u001A;\u0010@\u001A\u0002HA\"\u0010\b\u0000\u0010B*\u0006\u0012\u0002\b\u00030\u0002*\u0002HA\"\u0004\b\u0001\u0010A*\u0002HB2\f\u0010C\u001A\b\u0012\u0004\u0012\u0002HA0DH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010E\u001A\u0019\u0010F\u001A\u00020>\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u0002H\u0087\b\u001A,\u0010G\u001A\u00020>\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u0002H\u0087\b\u0082\u0002\u000E\n\f\b\u0000\u0012\u0002\u0018\u0001\u001A\u0004\b\u0003\u0010\u0000\u001A\u001E\u0010H\u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\bH\u0000\u001A!\u0010I\u001A\b\u0012\u0004\u0012\u0002H\u00070\u0002\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\u0002H\u0087\b\u001A!\u0010I\u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\n\u0012\u0004\u0012\u0002H\u0007\u0018\u00010\bH\u0087\b\u001A&\u0010J\u001A\b\u0012\u0004\u0012\u0002H\u00070\b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070K2\u0006\u0010L\u001A\u00020MH\u0007\"\u0019\u0010\u0000\u001A\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00028F\u00A2\u0006\u0006\u001A\u0004\b\u0003\u0010\u0004\"!\u0010\u0005\u001A\u00020\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b8F\u00A2\u0006\u0006\u001A\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006N"}, d2 = {"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/util/Collection;)Lkotlin/ranges/IntRange;", "lastIndex", "", "T", "", "getLastIndex", "(Ljava/util/List;)I", "List", "size", "init", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "MutableList", "", "arrayListOf", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "elements", "", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "buildList", "E", "capacity", "builderAction", "", "Lkotlin/ExtensionFunctionType;", "emptyList", "listOf", "([Ljava/lang/Object;)Ljava/util/List;", "listOfNotNull", "", "element", "(Ljava/lang/Object;)Ljava/util/List;", "mutableListOf", "rangeCheck", "fromIndex", "toIndex", "rangeCheck$CollectionsKt__CollectionsKt", "throwCountOverflow", "throwIndexOverflow", "asCollection", "([Ljava/lang/Object;)Ljava/util/Collection;", "binarySearch", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;II)I", "comparison", "", "(Ljava/util/List;Ljava/lang/Comparable;II)I", "binarySearchBy", "K", "key", "selector", "(Ljava/util/List;Ljava/lang/Comparable;IILkotlin/jvm/functions/Function1;)I", "containsAll", "", "Lkotlin/internal/OnlyInputTypes;", "ifEmpty", "R", "C", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/Collection;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isNotEmpty", "isNullOrEmpty", "optimizeReadOnlyList", "orEmpty", "shuffled", "", "random", "Lkotlin/random/Random;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
    private static final List List(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "init");
        ArrayList arrayList0 = new ArrayList(v);
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList0.add(function10.invoke(v1));
        }
        return arrayList0;
    }

    private static final List MutableList(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "init");
        ArrayList arrayList0 = new ArrayList(v);
        for(int v1 = 0; v1 < v; ++v1) {
            arrayList0.add(function10.invoke(v1));
        }
        return arrayList0;
    }

    private static final ArrayList arrayListOf() {
        return new ArrayList();
    }

    public static final ArrayList arrayListOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return arr_object.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(arr_object, true));
    }

    public static final Collection asCollection(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "<this>");
        return new ArrayAsCollection(arr_object, false);
    }

    public static final int binarySearch(List list0, int v, int v1, Function1 function10) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "comparison");
        CollectionsKt__CollectionsKt.rangeCheck$CollectionsKt__CollectionsKt(list0.size(), v, v1);
        int v2 = v1 - 1;
        while(v <= v2) {
            int v3 = v + v2 >>> 1;
            int v4 = ((Number)function10.invoke(list0.get(v3))).intValue();
            if(v4 < 0) {
                v = v3 + 1;
                continue;
            }
            if(v4 > 0) {
                v2 = v3 - 1;
                continue;
            }
            return v3;
        }
        return -(v + 1);
    }

    public static final int binarySearch(List list0, Comparable comparable0, int v, int v1) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        CollectionsKt__CollectionsKt.rangeCheck$CollectionsKt__CollectionsKt(list0.size(), v, v1);
        int v2 = v1 - 1;
        while(v <= v2) {
            int v3 = v + v2 >>> 1;
            int v4 = ComparisonsKt.compareValues(((Comparable)list0.get(v3)), comparable0);
            if(v4 < 0) {
                v = v3 + 1;
                continue;
            }
            if(v4 > 0) {
                v2 = v3 - 1;
                continue;
            }
            return v3;
        }
        return -(v + 1);
    }

    public static final int binarySearch(List list0, Object object0, Comparator comparator0, int v, int v1) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        CollectionsKt__CollectionsKt.rangeCheck$CollectionsKt__CollectionsKt(list0.size(), v, v1);
        int v2 = v1 - 1;
        while(v <= v2) {
            int v3 = v + v2 >>> 1;
            int v4 = comparator0.compare(list0.get(v3), object0);
            if(v4 < 0) {
                v = v3 + 1;
                continue;
            }
            if(v4 > 0) {
                v2 = v3 - 1;
                continue;
            }
            return v3;
        }
        return -(v + 1);
    }

    public static int binarySearch$default(List list0, int v, int v1, Function1 function10, int v2, Object object0) {
        if((v2 & 1) != 0) {
            v = 0;
        }
        if((v2 & 2) != 0) {
            v1 = list0.size();
        }
        return CollectionsKt.binarySearch(list0, v, v1, function10);
    }

    public static int binarySearch$default(List list0, Comparable comparable0, int v, int v1, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = list0.size();
        }
        return CollectionsKt.binarySearch(list0, comparable0, v, v1);
    }

    public static int binarySearch$default(List list0, Object object0, Comparator comparator0, int v, int v1, int v2, Object object1) {
        if((v2 & 4) != 0) {
            v = 0;
        }
        if((v2 & 8) != 0) {
            v1 = list0.size();
        }
        return CollectionsKt.binarySearch(list0, object0, comparator0, v, v1);
    }

    public static final int binarySearchBy(List list0, Comparable comparable0, int v, int v1, Function1 function10) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        return CollectionsKt.binarySearch(list0, v, v1, new Function1(function10, comparable0) {
            final Comparable $key;
            final Function1 $selector;

            {
                this.$selector = function10;
                this.$key = comparable0;
                super(1);
            }

            public final Integer invoke(Object object0) {
                return ComparisonsKt.compareValues(((Comparable)this.$selector.invoke(object0)), this.$key);
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        });
    }

    public static int binarySearchBy$default(List list0, Comparable comparable0, int v, int v1, Function1 function10, int v2, Object object0) {
        if((v2 & 2) != 0) {
            v = 0;
        }
        if((v2 & 4) != 0) {
            v1 = list0.size();
        }
        return CollectionsKt.binarySearch(list0, v, v1, new kotlin.collections.CollectionsKt__CollectionsKt.binarySearchBy.1(function10, comparable0));
    }

    private static final List buildList(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        List list0 = CollectionsKt.createListBuilder(v);
        function10.invoke(list0);
        return CollectionsKt.build(list0);
    }

    private static final List buildList(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        List list0 = CollectionsKt.createListBuilder();
        function10.invoke(list0);
        return CollectionsKt.build(list0);
    }

    private static final boolean containsAll(Collection collection0, Collection collection1) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        Intrinsics.checkNotNullParameter(collection1, "elements");
        return collection0.containsAll(collection1);
    }

    public static final List emptyList() {
        return EmptyList.INSTANCE;
    }

    public static final IntRange getIndices(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        return new IntRange(0, collection0.size() - 1);
    }

    public static final int getLastIndex(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        return list0.size() - 1;
    }

    private static final Object ifEmpty(Collection collection0, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        return collection0.isEmpty() ? function00.invoke() : collection0;
    }

    private static final boolean isNotEmpty(Collection collection0) {
        Intrinsics.checkNotNullParameter(collection0, "<this>");
        return !collection0.isEmpty();
    }

    private static final boolean isNullOrEmpty(Collection collection0) {
        return collection0 == null || collection0.isEmpty();
    }

    private static final List listOf() {
        return CollectionsKt.emptyList();
    }

    public static final List listOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return arr_object.length <= 0 ? CollectionsKt.emptyList() : ArraysKt.asList(arr_object);
    }

    public static final List listOfNotNull(Object object0) {
        return object0 == null ? CollectionsKt.emptyList() : CollectionsKt.listOf(object0);
    }

    public static final List listOfNotNull(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return ArraysKt.filterNotNull(arr_object);
    }

    private static final List mutableListOf() {
        return new ArrayList();
    }

    public static final List mutableListOf(Object[] arr_object) {
        Intrinsics.checkNotNullParameter(arr_object, "elements");
        return arr_object.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(arr_object, true));
    }

    public static final List optimizeReadOnlyList(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        switch(list0.size()) {
            case 0: {
                return CollectionsKt.emptyList();
            }
            case 1: {
                return CollectionsKt.listOf(list0.get(0));
            }
            default: {
                return list0;
            }
        }
    }

    private static final Collection orEmpty(Collection collection0) {
        return collection0 == null ? CollectionsKt.emptyList() : collection0;
    }

    private static final List orEmpty(List list0) {
        return list0 == null ? CollectionsKt.emptyList() : list0;
    }

    private static final void rangeCheck$CollectionsKt__CollectionsKt(int v, int v1, int v2) {
        if(v1 > v2) {
            throw new IllegalArgumentException("fromIndex (" + v1 + ") is greater than toIndex (" + v2 + ").");
        }
        if(v1 < 0) {
            throw new IndexOutOfBoundsException("fromIndex (" + v1 + ") is less than zero.");
        }
        if(v2 > v) {
            throw new IndexOutOfBoundsException("toIndex (" + v2 + ") is greater than size (" + v + ").");
        }
    }

    public static final List shuffled(Iterable iterable0, Random random0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(random0, "random");
        List list0 = CollectionsKt.toMutableList(iterable0);
        CollectionsKt.shuffle(list0, random0);
        return list0;
    }

    public static final void throwCountOverflow() {
        throw new ArithmeticException("Count overflow has happened.");
    }

    public static final void throwIndexOverflow() {
        throw new ArithmeticException("Index overflow has happened.");
    }
}

