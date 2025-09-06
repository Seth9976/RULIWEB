package kotlin.sequences;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001F\n\u0002\b\u0004\n\u0002\u0010\u000F\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001A(\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00012\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001AA\u0010\u0005\u001A\u0002H\u0006\"\u0010\b\u0000\u0010\u0006*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u0007\"\u0004\b\u0001\u0010\u0002*\u0006\u0012\u0002\b\u00030\u00012\u0006\u0010\b\u001A\u0002H\u00062\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004\u00A2\u0006\u0002\u0010\t\u001A)\u0010\n\u001A\u0004\u0018\u0001H\u000B\"\u000E\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\f*\b\u0012\u0004\u0012\u0002H\u000B0\u0001H\u0007\u00A2\u0006\u0002\u0010\r\u001A\u0019\u0010\n\u001A\u0004\u0018\u00010\u000E*\b\u0012\u0004\u0012\u00020\u000E0\u0001H\u0007\u00A2\u0006\u0002\u0010\u000F\u001A\u0019\u0010\n\u001A\u0004\u0018\u00010\u0010*\b\u0012\u0004\u0012\u00020\u00100\u0001H\u0007\u00A2\u0006\u0002\u0010\u0011\u001AG\u0010\u0012\u001A\u0004\u0018\u0001H\u000B\"\u0004\b\u0000\u0010\u000B\"\u000E\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f*\b\u0012\u0004\u0012\u0002H\u000B0\u00012\u0012\u0010\u0013\u001A\u000E\u0012\u0004\u0012\u0002H\u000B\u0012\u0004\u0012\u0002H\u00020\u0014H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001A;\u0010\u0016\u001A\u0004\u0018\u0001H\u000B\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00012\u001A\u0010\u0017\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000B0\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u000B`\u0019H\u0007\u00A2\u0006\u0002\u0010\u001A\u001A)\u0010\u001B\u001A\u0004\u0018\u0001H\u000B\"\u000E\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\f*\b\u0012\u0004\u0012\u0002H\u000B0\u0001H\u0007\u00A2\u0006\u0002\u0010\r\u001A\u0019\u0010\u001B\u001A\u0004\u0018\u00010\u000E*\b\u0012\u0004\u0012\u00020\u000E0\u0001H\u0007\u00A2\u0006\u0002\u0010\u000F\u001A\u0019\u0010\u001B\u001A\u0004\u0018\u00010\u0010*\b\u0012\u0004\u0012\u00020\u00100\u0001H\u0007\u00A2\u0006\u0002\u0010\u0011\u001AG\u0010\u001C\u001A\u0004\u0018\u0001H\u000B\"\u0004\b\u0000\u0010\u000B\"\u000E\b\u0001\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f*\b\u0012\u0004\u0012\u0002H\u000B0\u00012\u0012\u0010\u0013\u001A\u000E\u0012\u0004\u0012\u0002H\u000B\u0012\u0004\u0012\u0002H\u00020\u0014H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\u0010\u0015\u001A;\u0010\u001D\u001A\u0004\u0018\u0001H\u000B\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00012\u001A\u0010\u0017\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000B0\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u000B`\u0019H\u0007\u00A2\u0006\u0002\u0010\u001A\u001A5\u0010\u001E\u001A\u00020\u001F\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00012\u0012\u0010\u0013\u001A\u000E\u0012\u0004\u0012\u0002H\u000B\u0012\u0004\u0012\u00020\u001F0\u0014H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\b \u001A5\u0010\u001E\u001A\u00020!\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00012\u0012\u0010\u0013\u001A\u000E\u0012\u0004\u0012\u0002H\u000B\u0012\u0004\u0012\u00020!0\u0014H\u0087\b\u00F8\u0001\u0000\u00A2\u0006\u0002\b\"\u001A&\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u000B0$\"\u000E\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\f*\b\u0012\u0004\u0012\u0002H\u000B0\u0001\u001A8\u0010#\u001A\b\u0012\u0004\u0012\u0002H\u000B0$\"\u0004\b\u0000\u0010\u000B*\b\u0012\u0004\u0012\u0002H\u000B0\u00012\u001A\u0010\u0017\u001A\u0016\u0012\u0006\b\u0000\u0012\u0002H\u000B0\u0018j\n\u0012\u0006\b\u0000\u0012\u0002H\u000B`\u0019\u0082\u0002\u0007\n\u0005\b\u009920\u0001\u00A8\u0006%"}, d2 = {"filterIsInstance", "Lkotlin/sequences/Sequence;", "R", "klass", "Ljava/lang/Class;", "filterIsInstanceTo", "C", "", "destination", "(Lkotlin/sequences/Sequence;Ljava/util/Collection;Ljava/lang/Class;)Ljava/util/Collection;", "max", "T", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Comparable;", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Double;", "", "(Lkotlin/sequences/Sequence;)Ljava/lang/Float;", "maxBy", "selector", "Lkotlin/Function1;", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Lkotlin/sequences/Sequence;Ljava/util/Comparator;)Ljava/lang/Object;", "min", "minBy", "minWith", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "toSortedSet", "Ljava/util/SortedSet;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/sequences/SequencesKt")
class SequencesKt___SequencesJvmKt extends SequencesKt__SequencesKt {
    public static final Sequence filterIsInstance(Sequence sequence0, Class class0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(class0, "klass");
        Sequence sequence1 = SequencesKt.filter(sequence0, new Function1(class0) {
            final Class $klass;

            {
                this.$klass = class0;
                super(1);
            }

            public final Boolean invoke(Object object0) {
                return Boolean.valueOf(this.$klass.isInstance(object0));
            }

            @Override  // kotlin.jvm.functions.Function1
            public Object invoke(Object object0) {
                return this.invoke(object0);
            }
        });
        Intrinsics.checkNotNull(sequence1, "null cannot be cast to non-null type kotlin.sequences.Sequence<R of kotlin.sequences.SequencesKt___SequencesJvmKt.filterIsInstance>");
        return sequence1;
    }

    public static final Collection filterIsInstanceTo(Sequence sequence0, Collection collection0, Class class0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(collection0, "destination");
        Intrinsics.checkNotNullParameter(class0, "klass");
        for(Object object0: sequence0) {
            if(class0.isInstance(object0)) {
                collection0.add(object0);
            }
        }
        return collection0;
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Comparable max(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt.maxOrNull(sequence0);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Double max(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt.maxOrNull(sequence0);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Float max(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt.maxOrNull(sequence0);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Object maxBy(Sequence sequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = sequence0.iterator();
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
    public static final Object maxWith(Sequence sequence0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return SequencesKt.maxWithOrNull(sequence0, comparator0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Comparable min(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt.minOrNull(sequence0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Double min(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt.minOrNull(sequence0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Float min(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return SequencesKt.minOrNull(sequence0);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Object minBy(Sequence sequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        Iterator iterator0 = sequence0.iterator();
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
    public static final Object minWith(Sequence sequence0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return SequencesKt.minWithOrNull(sequence0, comparator0);
    }

    private static final BigDecimal sumOfBigDecimal(Sequence sequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigDecimal bigDecimal0 = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this.toLong())");
        for(Object object0: sequence0) {
            bigDecimal0 = bigDecimal0.add(((BigDecimal)function10.invoke(object0)));
            Intrinsics.checkNotNullExpressionValue(bigDecimal0, "this.add(other)");
        }
        return bigDecimal0;
    }

    private static final BigInteger sumOfBigInteger(Sequence sequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigInteger bigInteger0 = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this.toLong())");
        for(Object object0: sequence0) {
            bigInteger0 = bigInteger0.add(((BigInteger)function10.invoke(object0)));
            Intrinsics.checkNotNullExpressionValue(bigInteger0, "this.add(other)");
        }
        return bigInteger0;
    }

    public static final SortedSet toSortedSet(Sequence sequence0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        return (SortedSet)SequencesKt.toCollection(sequence0, new TreeSet());
    }

    public static final SortedSet toSortedSet(Sequence sequence0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(sequence0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return (SortedSet)SequencesKt.toCollection(sequence0, new TreeSet(comparator0));
    }
}

