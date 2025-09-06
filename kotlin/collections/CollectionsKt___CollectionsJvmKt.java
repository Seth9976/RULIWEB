package kotlin.collections;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001F\n\u0002\b\u0004\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001A(\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00032\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005\u001AA\u0010\u0006\u001A\u0002H\u0007\"\u0010\b\u0000\u0010\u0007*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\b\"\u0004\b\u0001\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00032\u0006\u0010\t\u001A\u0002H\u00072\f\u0010\u0004\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0005\u00A2\u0006\u0002\u0010\n\u001A)\u0010\u000B\u001A\u0004\u0018\u0001H\f\"\u000E\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\r*\b\u0012\u0004\u0012\u0002H\f0\u0003H\u0007\u00A2\u0006\u0002\u0010\u000E\u001A\u0019\u0010\u000B\u001A\u0004\u0018\u00010\u000F*\b\u0012\u0004\u0012\u00020\u000F0\u0003H\u0007\u00A2\u0006\u0002\u0010\u0010\u001A\u0019\u0010\u000B\u001A\u0004\u0018\u00010\u0011*\b\u0012\u0004\u0012\u00020\u00110\u0003H\u0007\u00A2\u0006\u0002\u0010\u0012\u001AG\u0010\u0013\u001A\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f\"\u000E\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r*\b\u0012\u0004\u0012\u0002H\f0\u00032\u0012\u0010\u0014\u001A\u000E\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u00020\u0015H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0016\u001A;\u0010\u0017\u001A\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00032\u001A\u0010\u0018\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\f0\u0019j\n\u0012\u0006\b\u0000\u0012\u0002H\f`\u001AH\u0007\u00A2\u0006\u0002\u0010\u001B\u001A)\u0010\u001C\u001A\u0004\u0018\u0001H\f\"\u000E\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\r*\b\u0012\u0004\u0012\u0002H\f0\u0003H\u0007\u00A2\u0006\u0002\u0010\u000E\u001A\u0019\u0010\u001C\u001A\u0004\u0018\u00010\u000F*\b\u0012\u0004\u0012\u00020\u000F0\u0003H\u0007\u00A2\u0006\u0002\u0010\u0010\u001A\u0019\u0010\u001C\u001A\u0004\u0018\u00010\u0011*\b\u0012\u0004\u0012\u00020\u00110\u0003H\u0007\u00A2\u0006\u0002\u0010\u0012\u001AG\u0010\u001D\u001A\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f\"\u000E\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\r*\b\u0012\u0004\u0012\u0002H\f0\u00032\u0012\u0010\u0014\u001A\u000E\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\u00020\u0015H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0016\u001A;\u0010\u001E\u001A\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00032\u001A\u0010\u0018\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\f0\u0019j\n\u0012\u0006\b\u0000\u0012\u0002H\f`\u001AH\u0007\u00A2\u0006\u0002\u0010\u001B\u001A\u0016\u0010\u001F\u001A\u00020 \"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0!\u001A5\u0010\"\u001A\u00020#\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00032\u0012\u0010\u0014\u001A\u000E\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u00020#0\u0015H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\b$\u001A5\u0010\"\u001A\u00020%\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00032\u0012\u0010\u0014\u001A\u000E\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u00020%0\u0015H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\b&\u001A&\u0010\'\u001A\b\u0012\u0004\u0012\u0002H\f0(\"\u000E\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\r*\b\u0012\u0004\u0012\u0002H\f0\u0003\u001A8\u0010\'\u001A\b\u0012\u0004\u0012\u0002H\f0(\"\u0004\b\u0000\u0010\f*\b\u0012\u0004\u0012\u0002H\f0\u00032\u001A\u0010\u0018\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\f0\u0019j\n\u0012\u0006\b\u0000\u0012\u0002H\f`\u001A\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006)"}, d2 = {"filterIsInstance", "", "R", "", "klass", "Ljava/lang/Class;", "filterIsInstanceTo", "C", "", "destination", "(Ljava/lang/Iterable;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "max", "T", "", "(Ljava/lang/Iterable;)Ljava/lang/Comparable;", "", "(Ljava/lang/Iterable;)Ljava/lang/Double;", "", "(Ljava/lang/Iterable;)Ljava/lang/Float;", "maxBy", "selector", "Lkotlin/Function1;", "(Ljava/lang/Iterable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/Iterable;Ljava/util/Comparator;)Ljava/lang/Object;", "min", "minBy", "minWith", "reverse", "", "", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "toSortedSet", "Ljava/util/SortedSet;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/collections/CollectionsKt")
class CollectionsKt___CollectionsJvmKt extends CollectionsKt__ReversedViewsKt {
    public static final List filterIsInstance(Iterable iterable0, Class class0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(class0, "klass");
        return (List)CollectionsKt.filterIsInstanceTo(iterable0, new ArrayList(), class0);
    }

    public static final Collection filterIsInstanceTo(Iterable iterable0, Collection collection0, Class class0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "destination");
        Intrinsics.checkNotNullParameter(class0, "klass");
        for(Object object0: iterable0) {
            if(class0.isInstance(object0)) {
                collection0.add(object0);
            }
        }
        return collection0;
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Comparable max(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return CollectionsKt.maxOrNull(iterable0);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Double max(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return CollectionsKt.maxOrNull(iterable0);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Float max(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return CollectionsKt.maxOrNull(iterable0);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Object maxBy(Iterable iterable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = iterable0.iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return object0;
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
        return object0;
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Object maxWith(Iterable iterable0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return CollectionsKt.maxWithOrNull(iterable0, comparator0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Comparable min(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return CollectionsKt.minOrNull(iterable0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Double min(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return CollectionsKt.minOrNull(iterable0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Float min(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return CollectionsKt.minOrNull(iterable0);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Object minBy(Iterable iterable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = iterable0.iterator();
        if(!iterator0.hasNext()) {
            return null;
        }
        Object object0 = iterator0.next();
        if(!iterator0.hasNext()) {
            return object0;
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
        return object0;
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Object minWith(Iterable iterable0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return CollectionsKt.minWithOrNull(iterable0, comparator0);
    }

    public static final void reverse(List list0) {
        Intrinsics.checkNotNullParameter(list0, "<this>");
        Collections.reverse(list0);
    }

    private static final BigDecimal sumOfBigDecimal(Iterable iterable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigDecimal bigDecimal0 = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this.toLong())");
        for(Object object0: iterable0) {
            bigDecimal0 = bigDecimal0.add(((BigDecimal)function10.invoke(object0)));
            Intrinsics.checkNotNullExpressionValue(bigDecimal0, "this.add(other)");
        }
        return bigDecimal0;
    }

    private static final BigInteger sumOfBigInteger(Iterable iterable0, Function1 function10) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigInteger bigInteger0 = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this.toLong())");
        for(Object object0: iterable0) {
            bigInteger0 = bigInteger0.add(((BigInteger)function10.invoke(object0)));
            Intrinsics.checkNotNullExpressionValue(bigInteger0, "this.add(other)");
        }
        return bigInteger0;
    }

    public static final SortedSet toSortedSet(Iterable iterable0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        return (SortedSet)CollectionsKt.toCollection(iterable0, new TreeSet());
    }

    public static final SortedSet toSortedSet(Iterable iterable0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(iterable0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (SortedSet)CollectionsKt.toCollection(iterable0, new TreeSet(comparator0));
    }
}

