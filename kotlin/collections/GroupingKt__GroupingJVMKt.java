package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.TypeIntrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0000\u001A0\u0010\u0000\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000E\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001AZ\u0010\u0006\u001A\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\t\"\u0004\b\u0002\u0010\b*\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00072\u001E\u0010\n\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\f\u0012\u0004\u0012\u0002H\b0\u000BH\u0081\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"eachCount", "", "K", "", "T", "Lkotlin/collections/Grouping;", "mapValuesInPlace", "", "R", "V", "f", "Lkotlin/Function1;", "", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/GroupingKt")
class GroupingKt__GroupingJVMKt {
    public static final Map eachCount(Grouping grouping0) {
        Intrinsics.checkNotNullParameter(grouping0, "<this>");
        Map map0 = new LinkedHashMap();
        Iterator iterator0 = grouping0.sourceIterator();
        while(iterator0.hasNext()) {
            Object object0 = iterator0.next();
            Object object1 = grouping0.keyOf(object0);
            IntRef ref$IntRef0 = map0.get(object1);
            if(ref$IntRef0 == null && !map0.containsKey(object1)) {
                ref$IntRef0 = new IntRef();
            }
            ++ref$IntRef0.element;
            map0.put(object1, ref$IntRef0);
        }
        for(Object object2: map0.entrySet()) {
            Intrinsics.checkNotNull(((Map.Entry)object2), "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda$4, R of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda$4>");
            TypeIntrinsics.asMutableMapEntry(((Map.Entry)object2)).setValue(((IntRef)((Map.Entry)object2).getValue()).element);
        }
        return TypeIntrinsics.asMutableMap(map0);
    }

    private static final Map mapValuesInPlace(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "f");
        for(Object object0: map0.entrySet()) {
            Intrinsics.checkNotNull(((Map.Entry)object0), "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda$4, R of kotlin.collections.GroupingKt__GroupingJVMKt.mapValuesInPlace$lambda$4>");
            TypeIntrinsics.asMutableMapEntry(((Map.Entry)object0)).setValue(function10.invoke(((Map.Entry)object0)));
        }
        return TypeIntrinsics.asMutableMap(map0);
    }
}

