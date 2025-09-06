package kotlin.text;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\f\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000F\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u0004H\u0087\b\u001A\u0013\u0010\u0005\u001A\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0006\u001A;\u0010\u0007\u001A\u0004\u0018\u00010\u0001\"\u000E\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t*\u00020\u00022\u0012\u0010\n\u001A\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\b0\u000BH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\f\u001A/\u0010\r\u001A\u0004\u0018\u00010\u0001*\u00020\u00022\u001A\u0010\u000E\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010\u000Fj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001A\u0013\u0010\u0012\u001A\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¢\u0006\u0002\u0010\u0006\u001A;\u0010\u0013\u001A\u0004\u0018\u00010\u0001\"\u000E\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t*\u00020\u00022\u0012\u0010\n\u001A\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\b0\u000BH\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\f\u001A/\u0010\u0014\u001A\u0004\u0018\u00010\u0001*\u00020\u00022\u001A\u0010\u000E\u001A\u0016\u0012\u0006\b\u0000\u0012\u00020\u00010\u000Fj\n\u0012\u0006\b\u0000\u0012\u00020\u0001`\u0010H\u0007¢\u0006\u0002\u0010\u0011\u001A)\u0010\u0015\u001A\u00020\u0016*\u00020\u00022\u0012\u0010\n\u001A\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u000BH\u0087\bø\u0001\u0000¢\u0006\u0002\b\u0017\u001A)\u0010\u0015\u001A\u00020\u0018*\u00020\u00022\u0012\u0010\n\u001A\u000E\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00180\u000BH\u0087\bø\u0001\u0000¢\u0006\u0002\b\u0019\u001A\u0010\u0010\u001A\u001A\b\u0012\u0004\u0012\u00020\u00010\u001B*\u00020\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001C"}, d2 = {"elementAt", "", "", "index", "", "max", "(Ljava/lang/CharSequence;)Ljava/lang/Character;", "maxBy", "R", "", "selector", "Lkotlin/Function1;", "(Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function1;)Ljava/lang/Character;", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/lang/CharSequence;Ljava/util/Comparator;)Ljava/lang/Character;", "min", "minBy", "minWith", "sumOf", "Ljava/math/BigDecimal;", "sumOfBigDecimal", "Ljava/math/BigInteger;", "sumOfBigInteger", "toSortedSet", "Ljava/util/SortedSet;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/text/StringsKt")
class StringsKt___StringsJvmKt extends StringsKt__StringsKt {
    private static final char elementAt(CharSequence charSequence0, int v) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        return charSequence0.charAt(v);
    }

    @Deprecated(message = "Use maxOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Character max(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        return StringsKt.maxOrNull(charSequence0);
    }

    @Deprecated(message = "Use maxByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Character maxBy(CharSequence charSequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(charSequence0.length() == 0) {
            return null;
        }
        int v = charSequence0.charAt(0);
        int v1 = StringsKt.getLastIndex(charSequence0);
        if(v1 == 0) {
            return Character.valueOf(((char)v));
        }
        Comparable comparable0 = (Comparable)function10.invoke(Character.valueOf(((char)v)));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = charSequence0.charAt(intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(Character.valueOf(((char)v2)));
            if(comparable0.compareTo(comparable1) < 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return Character.valueOf(((char)v));
    }

    @Deprecated(message = "Use maxWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.maxWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Character maxWith(CharSequence charSequence0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return StringsKt.maxWithOrNull(charSequence0, comparator0);
    }

    @Deprecated(message = "Use minOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minOrNull()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Character min(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        return StringsKt.minOrNull(charSequence0);
    }

    @Deprecated(message = "Use minByOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minByOrNull(selector)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Character minBy(CharSequence charSequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        if(charSequence0.length() == 0) {
            return null;
        }
        int v = charSequence0.charAt(0);
        int v1 = StringsKt.getLastIndex(charSequence0);
        if(v1 == 0) {
            return Character.valueOf(((char)v));
        }
        Comparable comparable0 = (Comparable)function10.invoke(Character.valueOf(((char)v)));
        IntIterator intIterator0 = new IntRange(1, v1).iterator();
        while(intIterator0.hasNext()) {
            int v2 = charSequence0.charAt(intIterator0.nextInt());
            Comparable comparable1 = (Comparable)function10.invoke(Character.valueOf(((char)v2)));
            if(comparable0.compareTo(comparable1) > 0) {
                v = v2;
                comparable0 = comparable1;
            }
        }
        return Character.valueOf(((char)v));
    }

    @Deprecated(message = "Use minWithOrNull instead.", replaceWith = @ReplaceWith(expression = "this.minWithOrNull(comparator)", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "1.6", warningSince = "1.4")
    public static final Character minWith(CharSequence charSequence0, Comparator comparator0) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        Intrinsics.checkNotNullParameter(comparator0, "comparator");
        return StringsKt.minWithOrNull(charSequence0, comparator0);
    }

    private static final BigDecimal sumOfBigDecimal(CharSequence charSequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigDecimal bigDecimal0 = BigDecimal.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this.toLong())");
        for(int v = 0; v < charSequence0.length(); ++v) {
            bigDecimal0 = bigDecimal0.add(((BigDecimal)function10.invoke(Character.valueOf(charSequence0.charAt(v)))));
            Intrinsics.checkNotNullExpressionValue(bigDecimal0, "this.add(other)");
        }
        return bigDecimal0;
    }

    private static final BigInteger sumOfBigInteger(CharSequence charSequence0, Function1 function10) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        Intrinsics.checkNotNullParameter(function10, "selector");
        BigInteger bigInteger0 = BigInteger.valueOf(0L);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this.toLong())");
        for(int v = 0; v < charSequence0.length(); ++v) {
            bigInteger0 = bigInteger0.add(((BigInteger)function10.invoke(Character.valueOf(charSequence0.charAt(v)))));
            Intrinsics.checkNotNullExpressionValue(bigInteger0, "this.add(other)");
        }
        return bigInteger0;
    }

    public static final SortedSet toSortedSet(CharSequence charSequence0) {
        Intrinsics.checkNotNullParameter(charSequence0, "<this>");
        return (SortedSet)StringsKt.toCollection(charSequence0, new TreeSet());
    }
}

