package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000B\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001A\u009E\u0001\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052b\u0010\u0006\u001A^\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000E\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b\u00F8\u0001\u0000\u001A\u00B7\u0001\u0010\u000F\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001A\u0002H\u00102b\u0010\u0006\u001A^\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000E\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0013\u001AI\u0010\u0014\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0016\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0011*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001A\u0002H\u0010H\u0007\u00A2\u0006\u0002\u0010\u0016\u001A\u00BF\u0001\u0010\u0017\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u000526\u0010\u0018\u001A2\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001AG\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001AH\u0087\b\u00F8\u0001\u0000\u001A\u007F\u0010\u0017\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u001B\u001A\u0002H\u000326\u0010\u0006\u001A2\u0012\u0013\u0012\u0011H\u0003\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001C\u001A\u00D8\u0001\u0010\u001D\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001A\u0002H\u001026\u0010\u0018\u001A2\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u00192K\u0010\u0006\u001AG\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001AH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001E\u001A\u0093\u0001\u0010\u001D\u001A\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001A\u0002H\u00102\u0006\u0010\u001B\u001A\u0002H\u000326\u0010\u0006\u001A2\u0012\u0013\u0012\u0011H\u0003\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u0019H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u001F\u001A\u008B\u0001\u0010 \u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0001\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052K\u0010\u0006\u001AG\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001AH\u0087\b\u00F8\u0001\u0000\u001A\u00A4\u0001\u0010\"\u001A\u0002H\u0010\"\u0004\b\u0000\u0010!\"\b\b\u0001\u0010\u0004*\u0002H!\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H!0\u0011*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001A\u0002H\u00102K\u0010\u0006\u001AG\u0012\u0013\u0012\u0011H\u0002\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H!\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000B\u0012\u0013\u0012\u0011H\u0004\u00A2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H!0\u001AH\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006$"}, d2 = {"aggregate", "", "K", "R", "T", "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "aggregateTo", "M", "", "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "eachCountTo", "", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/GroupingKt")
class GroupingKt__GroupingKt extends GroupingKt__GroupingJVMKt {
    public static final Map aggregate(Grouping grouping0, Function4 function40) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(function40, "operation");
        Map map0 = new LinkedHashMap();
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = grouping0.keyOf(object0);
            Object object2 = map0.get(object1);
            map0.put(object1, function40.invoke(object1, object2, object0, Boolean.valueOf(object2 == null && !map0.containsKey(object1))));
        }
        return map0;
    }

    public static final Map aggregateTo(Grouping grouping0, Map map0, Function4 function40) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(map0, "destination");
        Intrinsics.checkNotNullParameter(function40, "operation");
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = grouping0.keyOf(object0);
            Object object2 = map0.get(object1);
            map0.put(object1, function40.invoke(object1, object2, object0, Boolean.valueOf(object2 == null && !map0.containsKey(object1))));
        }
        return map0;
    }

    public static final Map eachCountTo(Grouping grouping0, Map map0) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(map0, "destination");
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = grouping0.keyOf(object0);
            Integer integer0 = map0.get(object1);
            if(integer0 == null && !map0.containsKey(object1)) {
                integer0 = 0;
            }
            map0.put(object1, ((int)(integer0.intValue() + 1)));
        }
        return map0;
    }

    public static final Map fold(Grouping grouping0, Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "operation");
        Map map0 = new LinkedHashMap();
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object1 = iterator0.next();
            Object object2 = grouping0.keyOf(object1);
            Object object3 = map0.get(object2);
            if(object3 == null && !map0.containsKey(object2)) {
                object3 = object0;
            }
            map0.put(object2, function20.invoke(object3, object1));
        }
        return map0;
    }

    public static final Map fold(Grouping grouping0, Function2 function20, Function3 function30) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(function20, "initialValueSelector");
        Intrinsics.checkNotNullParameter(function30, "operation");
        Map map0 = new LinkedHashMap();
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = grouping0.keyOf(object0);
            Object object2 = map0.get(object1);
            if(object2 == null && !map0.containsKey(object1)) {
                object2 = function20.invoke(object1, object0);
            }
            map0.put(object1, function30.invoke(object1, object2, object0));
        }
        return map0;
    }

    public static final Map foldTo(Grouping grouping0, Map map0, Object object0, Function2 function20) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(map0, "destination");
        Intrinsics.checkNotNullParameter(function20, "operation");
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object1 = iterator0.next();
            Object object2 = grouping0.keyOf(object1);
            Object object3 = map0.get(object2);
            if(object3 == null && !map0.containsKey(object2)) {
                object3 = object0;
            }
            map0.put(object2, function20.invoke(object3, object1));
        }
        return map0;
    }

    public static final Map foldTo(Grouping grouping0, Map map0, Function2 function20, Function3 function30) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(map0, "destination");
        Intrinsics.checkNotNullParameter(function20, "initialValueSelector");
        Intrinsics.checkNotNullParameter(function30, "operation");
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = grouping0.keyOf(object0);
            Object object2 = map0.get(object1);
            if(object2 == null && !map0.containsKey(object1)) {
                object2 = function20.invoke(object1, object0);
            }
            map0.put(object1, function30.invoke(object1, object2, object0));
        }
        return map0;
    }

    public static final Map reduce(Grouping grouping0, Function3 function30) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(function30, "operation");
        Map map0 = new LinkedHashMap();
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = grouping0.keyOf(object0);
            Object object2 = map0.get(object1);
            if(object2 != null || map0.containsKey(object1)) {
                object0 = function30.invoke(object1, object2, object0);
            }
            map0.put(object1, object0);
        }
        return map0;
    }

    public static final Map reduceTo(Grouping grouping0, Map map0, Function3 function30) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Intrinsics.checkNotNullParameter(map0, "destination");
        Intrinsics.checkNotNullParameter(function30, "operation");
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = grouping0.keyOf(object0);
            Object object2 = map0.get(object1);
            if(object2 != null || map0.containsKey(object1)) {
                object0 = function30.invoke(object1, object2, object0);
            }
            map0.put(object1, object0);
        }
        return map0;
    }
}

