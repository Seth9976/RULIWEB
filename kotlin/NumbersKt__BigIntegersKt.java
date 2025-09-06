package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000B\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u001A\u0015\u0010\u0000\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\f\u001A\r\u0010\u0003\u001A\u00020\u0001*\u00020\u0001H\u0087\n\u001A\u0015\u0010\u0004\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\n\u001A\r\u0010\u0005\u001A\u00020\u0001*\u00020\u0001H\u0087\n\u001A\r\u0010\u0006\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\u0015\u0010\u0007\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\n\u001A\u0015\u0010\b\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\f\u001A\u0015\u0010\t\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\n\u001A\u0015\u0010\n\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\n\u001A\u0015\u0010\u000B\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001A\u00020\rH\u0087\f\u001A\u0015\u0010\u000E\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\f\u001A\u00020\rH\u0087\f\u001A\u0015\u0010\u000F\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\n\u001A\r\u0010\u0010\u001A\u00020\u0011*\u00020\u0001H\u0087\b\u001A!\u0010\u0010\u001A\u00020\u0011*\u00020\u00012\b\b\u0002\u0010\u0012\u001A\u00020\r2\b\b\u0002\u0010\u0013\u001A\u00020\u0014H\u0087\b\u001A\r\u0010\u0015\u001A\u00020\u0001*\u00020\rH\u0087\b\u001A\r\u0010\u0015\u001A\u00020\u0001*\u00020\u0016H\u0087\b\u001A\r\u0010\u0017\u001A\u00020\u0001*\u00020\u0001H\u0087\n\u001A\u0015\u0010\u0018\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0001H\u0087\f¨\u0006\u0019"}, d2 = {"and", "Ljava/math/BigInteger;", "other", "dec", "div", "inc", "inv", "minus", "or", "plus", "rem", "shl", "n", "", "shr", "times", "toBigDecimal", "Ljava/math/BigDecimal;", "scale", "mathContext", "Ljava/math/MathContext;", "toBigInteger", "", "unaryMinus", "xor", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/NumbersKt")
class NumbersKt__BigIntegersKt extends NumbersKt__BigDecimalsKt {
    private static final BigInteger and(BigInteger bigInteger0, BigInteger bigInteger1) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(bigInteger1, "other");
        BigInteger bigInteger2 = bigInteger0.and(bigInteger1);
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "this.and(other)");
        return bigInteger2;
    }

    private static final BigInteger dec(BigInteger bigInteger0) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        BigInteger bigInteger1 = bigInteger0.subtract(BigInteger.ONE);
        Intrinsics.checkNotNullExpressionValue(bigInteger1, "this.subtract(BigInteger.ONE)");
        return bigInteger1;
    }

    private static final BigInteger div(BigInteger bigInteger0, BigInteger bigInteger1) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(bigInteger1, "other");
        BigInteger bigInteger2 = bigInteger0.divide(bigInteger1);
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "this.divide(other)");
        return bigInteger2;
    }

    private static final BigInteger inc(BigInteger bigInteger0) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        BigInteger bigInteger1 = bigInteger0.add(BigInteger.ONE);
        Intrinsics.checkNotNullExpressionValue(bigInteger1, "this.add(BigInteger.ONE)");
        return bigInteger1;
    }

    private static final BigInteger inv(BigInteger bigInteger0) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        BigInteger bigInteger1 = bigInteger0.not();
        Intrinsics.checkNotNullExpressionValue(bigInteger1, "this.not()");
        return bigInteger1;
    }

    private static final BigInteger minus(BigInteger bigInteger0, BigInteger bigInteger1) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(bigInteger1, "other");
        BigInteger bigInteger2 = bigInteger0.subtract(bigInteger1);
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "this.subtract(other)");
        return bigInteger2;
    }

    private static final BigInteger or(BigInteger bigInteger0, BigInteger bigInteger1) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(bigInteger1, "other");
        BigInteger bigInteger2 = bigInteger0.or(bigInteger1);
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "this.or(other)");
        return bigInteger2;
    }

    private static final BigInteger plus(BigInteger bigInteger0, BigInteger bigInteger1) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(bigInteger1, "other");
        BigInteger bigInteger2 = bigInteger0.add(bigInteger1);
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "this.add(other)");
        return bigInteger2;
    }

    private static final BigInteger rem(BigInteger bigInteger0, BigInteger bigInteger1) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(bigInteger1, "other");
        BigInteger bigInteger2 = bigInteger0.remainder(bigInteger1);
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "this.remainder(other)");
        return bigInteger2;
    }

    private static final BigInteger shl(BigInteger bigInteger0, int v) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        BigInteger bigInteger1 = bigInteger0.shiftLeft(v);
        Intrinsics.checkNotNullExpressionValue(bigInteger1, "this.shiftLeft(n)");
        return bigInteger1;
    }

    private static final BigInteger shr(BigInteger bigInteger0, int v) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        BigInteger bigInteger1 = bigInteger0.shiftRight(v);
        Intrinsics.checkNotNullExpressionValue(bigInteger1, "this.shiftRight(n)");
        return bigInteger1;
    }

    private static final BigInteger times(BigInteger bigInteger0, BigInteger bigInteger1) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(bigInteger1, "other");
        BigInteger bigInteger2 = bigInteger0.multiply(bigInteger1);
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "this.multiply(other)");
        return bigInteger2;
    }

    private static final BigDecimal toBigDecimal(BigInteger bigInteger0) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        return new BigDecimal(bigInteger0);
    }

    private static final BigDecimal toBigDecimal(BigInteger bigInteger0, int v, MathContext mathContext0) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(mathContext0, "mathContext");
        return new BigDecimal(bigInteger0, v, mathContext0);
    }

    static BigDecimal toBigDecimal$default(BigInteger bigInteger0, int v, MathContext mathContext0, int v1, Object object0) {
        if((v1 & 1) != 0) {
            v = 0;
        }
        if((v1 & 2) != 0) {
            mathContext0 = MathContext.UNLIMITED;
            Intrinsics.checkNotNullExpressionValue(mathContext0, "UNLIMITED");
        }
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(mathContext0, "mathContext");
        return new BigDecimal(bigInteger0, v, mathContext0);
    }

    private static final BigInteger toBigInteger(int v) {
        BigInteger bigInteger0 = BigInteger.valueOf(v);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this.toLong())");
        return bigInteger0;
    }

    private static final BigInteger toBigInteger(long v) {
        BigInteger bigInteger0 = BigInteger.valueOf(v);
        Intrinsics.checkNotNullExpressionValue(bigInteger0, "valueOf(this)");
        return bigInteger0;
    }

    private static final BigInteger unaryMinus(BigInteger bigInteger0) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        BigInteger bigInteger1 = bigInteger0.negate();
        Intrinsics.checkNotNullExpressionValue(bigInteger1, "this.negate()");
        return bigInteger1;
    }

    private static final BigInteger xor(BigInteger bigInteger0, BigInteger bigInteger1) {
        Intrinsics.checkNotNullParameter(bigInteger0, "<this>");
        Intrinsics.checkNotNullParameter(bigInteger1, "other");
        BigInteger bigInteger2 = bigInteger0.xor(bigInteger1);
        Intrinsics.checkNotNullExpressionValue(bigInteger2, "this.xor(other)");
        return bigInteger2;
    }
}

