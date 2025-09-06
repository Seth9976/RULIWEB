package kotlin.collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010&\n\u0002\b\u0003\n\u0002\u0010\u000F\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001Ah\u0010\u0000\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00062\u001E\u0010\u0007\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0004\u0012\u0002H\u00040\bH\u0087\bø\u0001\u0000\u001Ai\u0010\t\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000622\u0010\n\u001A.\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00010\u000Bj\u0016\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001`\fH\u0087\b\u001Ah\u0010\r\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000E\b\u0002\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00040\u0005*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00062\u001E\u0010\u0007\u001A\u001A\u0012\u0010\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\u0012\u0004\u0012\u0002H\u00040\bH\u0087\bø\u0001\u0000\u001Ah\u0010\u000E\u001A\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000622\u0010\n\u001A.\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00010\u000Bj\u0016\u0012\u0012\b\u0000\u0012\u000E\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001`\fH\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000F"}, d2 = {"maxBy", "", "K", "V", "R", "", "", "selector", "Lkotlin/Function1;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "minBy", "minWith", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/MapsKt")
class MapsKt___MapsJvmKt extends MapsKt__MapsKt {
    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final Map.Entry maxBy(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return (Map.Entry)object0;
        }
        Comparable comparable0 = (Comparable)function10.invoke(object0);
        do {
            Object object1 = iterator0.next();
            Comparable comparable1 = (Comparable)function10.invoke(object1);
            if(comparable0.compareTo(comparable1) < 0) {
                object0 = object1;
                comparable0 = comparable1;
            }
        }
        while(iterator0.hasNext());
        return (Map.Entry)object0;
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    private static final Map.Entry maxWith(Map map0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (Map.Entry)CollectionsKt.maxWithOrNull(map0.entrySet(), comparator0);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Map.Entry minBy(Map map0, Function1 function10) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = map0.entrySet().iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return (Map.Entry)object0;
        }
        Comparable comparable0 = (Comparable)function10.invoke(object0);
        do {
            Object object1 = iterator0.next();
            Comparable comparable1 = (Comparable)function10.invoke(object1);
            if(comparable0.compareTo(comparable1) > 0) {
                object0 = object1;
                comparable0 = comparable1;
            }
        }
        while(iterator0.hasNext());
        return (Map.Entry)object0;
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Map.Entry minWith(Map map0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(map0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (Map.Entry)CollectionsKt.minWithOrNull(map0.entrySet(), comparator0);
    }
}

