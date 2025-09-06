package kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.builders.MapBuilder;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u000F\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000E\n\u0002\b\u0004\u001A4\u0010\u0002\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007H\u0001\u001AQ\u0010\b\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\t\u001A\u00020\u00012#\u0010\n\u001A\u001F\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\u0012\u0004\u0012\u00020\f0\u000B\u00A2\u0006\u0002\b\rH\u0081\b\u00F8\u0001\u0000\u001AI\u0010\b\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052#\u0010\n\u001A\u001F\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\u0012\u0004\u0012\u00020\f0\u000B\u00A2\u0006\u0002\b\rH\u0081\b\u00F8\u0001\u0000\u001A \u0010\u000E\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005H\u0001\u001A(\u0010\u000E\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0007\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0006\u0010\t\u001A\u00020\u0001H\u0001\u001A\u0010\u0010\u000F\u001A\u00020\u00012\u0006\u0010\u0010\u001A\u00020\u0001H\u0001\u001A2\u0010\u0011\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0012\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0013\u001Aa\u0010\u0014\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0015\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u000E\u0010\u0016\u001A\n\u0012\u0006\b\u0000\u0012\u0002H\u00040\u00172*\u0010\u0018\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00130\u0019\"\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0013H\u0007\u00A2\u0006\u0002\u0010\u001A\u001AY\u0010\u0014\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0015\"\u000E\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u001B\"\u0004\b\u0001\u0010\u00052*\u0010\u0018\u001A\u0016\u0012\u0012\b\u0001\u0012\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00130\u0019\"\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0013\u00A2\u0006\u0002\u0010\u001C\u001AC\u0010\u001D\u001A\u0002H\u0005\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u001E2\u0006\u0010\u001F\u001A\u0002H\u00042\f\u0010 \u001A\b\u0012\u0004\u0012\u0002H\u00050!H\u0086\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\"\u001A\u0019\u0010#\u001A\u00020$*\u000E\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020%0\u0003H\u0087\b\u001A2\u0010&\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0000\u001A1\u0010\'\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003H\u0081\b\u001A:\u0010(\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0015\"\u000E\b\u0000\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u001B\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0003\u001A@\u0010(\u001A\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u0015\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00032\u000E\u0010\u0016\u001A\n\u0012\u0006\b\u0000\u0012\u0002H\u00040\u0017\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082T\u00A2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006)"}, d2 = {"INT_MAX_POWER_OF_TWO", "", "build", "", "K", "V", "builder", "", "buildMapInternal", "capacity", "builderAction", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "createMapBuilder", "mapCapacity", "expectedSize", "mapOf", "pair", "Lkotlin/Pair;", "sortedMapOf", "Ljava/util/SortedMap;", "comparator", "Ljava/util/Comparator;", "pairs", "", "(Ljava/util/Comparator;[Lkotlin/Pair;)Ljava/util/SortedMap;", "", "([Lkotlin/Pair;)Ljava/util/SortedMap;", "getOrPut", "Ljava/util/concurrent/ConcurrentMap;", "key", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toProperties", "Ljava/util/Properties;", "", "toSingletonMap", "toSingletonMapOrSelf", "toSortedMap", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
class MapsKt__MapsJVMKt extends MapsKt__MapWithDefaultKt {
    private static final int INT_MAX_POWER_OF_TWO = 0x40000000;

    public static final Map build(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "builder");
        return ((MapBuilder)map0).build();
    }

    private static final Map buildMapInternal(int v, Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        Map map0 = MapsKt.createMapBuilder(v);
        function10.invoke(map0);
        return MapsKt.build(map0);
    }

    private static final Map buildMapInternal(Function1 function10) {
        Intrinsics.checkNotNullParameter(function10, "builderAction");
        Map map0 = MapsKt.createMapBuilder();
        function10.invoke(map0);
        return MapsKt.build(map0);
    }

    public static final Map createMapBuilder() {
        return new MapBuilder();
    }

    public static final Map createMapBuilder(int v) {
        return new MapBuilder(v);
    }

    public static final Object getOrPut(ConcurrentMap concurrentMap0, Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(concurrentMap0, "<this>");
        Intrinsics.checkNotNullParameter(function00, "defaultValue");
        Object object1 = concurrentMap0.get(object0);
        if(object1 == null) {
            Object object2 = function00.invoke();
            Object object3 = concurrentMap0.putIfAbsent(object0, object2);
            return object3 == null ? object2 : object3;
        }
        return object1;
    }

    public static final int mapCapacity(int v) {
        if(v < 0) {
            return v;
        }
        if(v < 3) {
            return v + 1;
        }
        return v >= 0x40000000 ? 0x7FFFFFFF : ((int)(((float)v) / 0.75f + 1.0f));
    }

    public static final Map mapOf(Pair pair0) {
        Intrinsics.checkNotNullParameter(pair0, "pair");
        Map map0 = Collections.singletonMap(pair0.getFirst(), pair0.getSecond());
        Intrinsics.checkNotNullExpressionValue(map0, "singletonMap(pair.first, pair.second)");
        return map0;
    }

    public static final SortedMap sortedMapOf(Comparator comparator0, Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        TreeMap treeMap0 = new TreeMap(comparator0);
        MapsKt.putAll(treeMap0, arr_pair);
        return treeMap0;
    }

    public static final SortedMap sortedMapOf(Pair[] arr_pair) {
        Intrinsics.checkNotNullParameter(arr_pair, "pairs");
        TreeMap treeMap0 = new TreeMap();
        MapsKt.putAll(treeMap0, arr_pair);
        return treeMap0;
    }

    private static final Properties toProperties(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Properties properties0 = new Properties();
        properties0.putAll(map0);
        return properties0;
    }

    public static final Map toSingletonMap(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Object object0 = map0.entrySet().iterator().next();
        Map map1 = Collections.singletonMap(((Map.Entry)object0).getKey(), ((Map.Entry)object0).getValue());
        Intrinsics.checkNotNullExpressionValue(map1, "with(entries.iterator().…ingletonMap(key, value) }");
        return map1;
    }

    private static final Map toSingletonMapOrSelf(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return MapsKt.toSingletonMap(map0);
    }

    public static final SortedMap toSortedMap(Map map0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        return new TreeMap(map0);
    }

    public static final SortedMap toSortedMap(Map map0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        TreeMap treeMap0 = new TreeMap(comparator0);
        treeMap0.putAll(map0);
        return treeMap0;
    }
}

