package kotlin;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\u001A\r\u0010\u0000\u001A\u00020\u0001*\u00020\u0001H\u0087\n\u001A\u0015\u0010\u0002\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0001H\u0087\n\u001A\r\u0010\u0004\u001A\u00020\u0001*\u00020\u0001H\u0087\n\u001A\u0015\u0010\u0005\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0001H\u0087\n\u001A\u0015\u0010\u0006\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0001H\u0087\n\u001A\u0015\u0010\u0007\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0001H\u0087\n\u001A\u0015\u0010\b\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0003\u001A\u00020\u0001H\u0087\n\u001A\r\u0010\t\u001A\u00020\u0001*\u00020\nH\u0087\b\u001A\u0015\u0010\t\u001A\u00020\u0001*\u00020\n2\u0006\u0010\u000B\u001A\u00020\fH\u0087\b\u001A\r\u0010\t\u001A\u00020\u0001*\u00020\rH\u0087\b\u001A\u0015\u0010\t\u001A\u00020\u0001*\u00020\r2\u0006\u0010\u000B\u001A\u00020\fH\u0087\b\u001A\r\u0010\t\u001A\u00020\u0001*\u00020\u000EH\u0087\b\u001A\u0015\u0010\t\u001A\u00020\u0001*\u00020\u000E2\u0006\u0010\u000B\u001A\u00020\fH\u0087\b\u001A\r\u0010\t\u001A\u00020\u0001*\u00020\u000FH\u0087\b\u001A\u0015\u0010\t\u001A\u00020\u0001*\u00020\u000F2\u0006\u0010\u000B\u001A\u00020\fH\u0087\b\u001A\r\u0010\u0010\u001A\u00020\u0001*\u00020\u0001H\u0087\n¨\u0006\u0011"}, d2 = {"dec", "Ljava/math/BigDecimal;", "div", "other", "inc", "minus", "plus", "rem", "times", "toBigDecimal", "", "mathContext", "Ljava/math/MathContext;", "", "", "", "unaryMinus", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/NumbersKt")
class NumbersKt__BigDecimalsKt {
    private static final BigDecimal dec(BigDecimal bigDecimal0) {
        Intrinsics.checkNotNullParameter(bigDecimal0, "<this>");
        BigDecimal bigDecimal1 = bigDecimal0.subtract(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimal1, "this.subtract(BigDecimal.ONE)");
        return bigDecimal1;
    }

    private static final BigDecimal div(BigDecimal bigDecimal0, BigDecimal bigDecimal1) {
        Intrinsics.checkNotNullParameter(bigDecimal0, "<this>");
        Intrinsics.checkNotNullParameter(bigDecimal1, "other");
        BigDecimal bigDecimal2 = bigDecimal0.divide(bigDecimal1, RoundingMode.HALF_EVEN);
        Intrinsics.checkNotNullExpressionValue(bigDecimal2, "this.divide(other, RoundingMode.HALF_EVEN)");
        return bigDecimal2;
    }

    private static final BigDecimal inc(BigDecimal bigDecimal0) {
        Intrinsics.checkNotNullParameter(bigDecimal0, "<this>");
        BigDecimal bigDecimal1 = bigDecimal0.add(BigDecimal.ONE);
        Intrinsics.checkNotNullExpressionValue(bigDecimal1, "this.add(BigDecimal.ONE)");
        return bigDecimal1;
    }

    private static final BigDecimal minus(BigDecimal bigDecimal0, BigDecimal bigDecimal1) {
        Intrinsics.checkNotNullParameter(bigDecimal0, "<this>");
        Intrinsics.checkNotNullParameter(bigDecimal1, "other");
        BigDecimal bigDecimal2 = bigDecimal0.subtract(bigDecimal1);
        Intrinsics.checkNotNullExpressionValue(bigDecimal2, "this.subtract(other)");
        return bigDecimal2;
    }

    private static final BigDecimal plus(BigDecimal bigDecimal0, BigDecimal bigDecimal1) {
        Intrinsics.checkNotNullParameter(bigDecimal0, "<this>");
        Intrinsics.checkNotNullParameter(bigDecimal1, "other");
        BigDecimal bigDecimal2 = bigDecimal0.add(bigDecimal1);
        Intrinsics.checkNotNullExpressionValue(bigDecimal2, "this.add(other)");
        return bigDecimal2;
    }

    private static final BigDecimal rem(BigDecimal bigDecimal0, BigDecimal bigDecimal1) {
        Intrinsics.checkNotNullParameter(bigDecimal0, "<this>");
        Intrinsics.checkNotNullParameter(bigDecimal1, "other");
        BigDecimal bigDecimal2 = bigDecimal0.remainder(bigDecimal1);
        Intrinsics.checkNotNullExpressionValue(bigDecimal2, "this.remainder(other)");
        return bigDecimal2;
    }

    private static final BigDecimal times(BigDecimal bigDecimal0, BigDecimal bigDecimal1) {
        Intrinsics.checkNotNullParameter(bigDecimal0, "<this>");
        Intrinsics.checkNotNullParameter(bigDecimal1, "other");
        BigDecimal bigDecimal2 = bigDecimal0.multiply(bigDecimal1);
        Intrinsics.checkNotNullExpressionValue(bigDecimal2, "this.multiply(other)");
        return bigDecimal2;
    }

    private static final BigDecimal toBigDecimal(double f) {
        return new BigDecimal(String.valueOf(f));
    }

    private static final BigDecimal toBigDecimal(double f, MathContext mathContext0) {
        Intrinsics.checkNotNullParameter(mathContext0, "mathContext");
        return new BigDecimal(String.valueOf(f), mathContext0);
    }

    private static final BigDecimal toBigDecimal(float f) {
        return new BigDecimal(String.valueOf(f));
    }

    private static final BigDecimal toBigDecimal(float f, MathContext mathContext0) {
        Intrinsics.checkNotNullParameter(mathContext0, "mathContext");
        return new BigDecimal(String.valueOf(f), mathContext0);
    }

    private static final BigDecimal toBigDecimal(int v) {
        BigDecimal bigDecimal0 = BigDecimal.valueOf(v);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this.toLong())");
        return bigDecimal0;
    }

    private static final BigDecimal toBigDecimal(int v, MathContext mathContext0) {
        Intrinsics.checkNotNullParameter(mathContext0, "mathContext");
        return new BigDecimal(v, mathContext0);
    }

    private static final BigDecimal toBigDecimal(long v) {
        BigDecimal bigDecimal0 = BigDecimal.valueOf(v);
        Intrinsics.checkNotNullExpressionValue(bigDecimal0, "valueOf(this)");
        return bigDecimal0;
    }

    private static final BigDecimal toBigDecimal(long v, MathContext mathContext0) {
        Intrinsics.checkNotNullParameter(mathContext0, "mathContext");
        return new BigDecimal(v, mathContext0);
    }

    private static final BigDecimal unaryMinus(BigDecimal bigDecimal0) {
        Intrinsics.checkNotNullParameter(bigDecimal0, "<this>");
        BigDecimal bigDecimal1 = bigDecimal0.negate();
        Intrinsics.checkNotNullExpressionValue(bigDecimal1, "this.negate()");
        return bigDecimal1;
    }
}

