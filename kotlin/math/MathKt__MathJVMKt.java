package kotlin.math;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b8\u001A\u0011\u0010\u0016\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010\u0016\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010\u0016\u001A\u00020\t2\u0006\u0010\u0018\u001A\u00020\tH\u0087\b\u001A\u0011\u0010\u0016\u001A\u00020\f2\u0006\u0010\u0018\u001A\u00020\fH\u0087\b\u001A\u0011\u0010\u0019\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010\u0019\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0010\u0010\u001A\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0007\u001A\u0011\u0010\u001A\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010\u001B\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010\u001B\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0010\u0010\u001C\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0007\u001A\u0011\u0010\u001C\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010\u001D\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010\u001D\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0019\u0010\u001E\u001A\u00020\u00012\u0006\u0010\u001F\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0019\u0010\u001E\u001A\u00020\u00062\u0006\u0010\u001F\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0010\u0010 \u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0007\u001A\u0011\u0010 \u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010!\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010!\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010\"\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010\"\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010#\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010#\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010$\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010$\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010%\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010%\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010&\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010&\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010\'\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010\'\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0019\u0010(\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u00012\u0006\u0010\u001F\u001A\u00020\u0001H\u0087\b\u001A\u0019\u0010(\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u00062\u0006\u0010\u001F\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010)\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010)\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010*\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010*\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0018\u0010+\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u00012\u0006\u0010,\u001A\u00020\u0001H\u0007\u001A\u0018\u0010+\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u00062\u0006\u0010,\u001A\u00020\u0006H\u0007\u001A\u0011\u0010-\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010-\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0010\u0010.\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0007\u001A\u0010\u0010.\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0007\u001A\u0019\u0010/\u001A\u00020\u00012\u0006\u00100\u001A\u00020\u00012\u0006\u00101\u001A\u00020\u0001H\u0087\b\u001A\u0019\u0010/\u001A\u00020\u00062\u0006\u00100\u001A\u00020\u00062\u0006\u00101\u001A\u00020\u0006H\u0087\b\u001A\u0019\u0010/\u001A\u00020\t2\u0006\u00100\u001A\u00020\t2\u0006\u00101\u001A\u00020\tH\u0087\b\u001A\u0019\u0010/\u001A\u00020\f2\u0006\u00100\u001A\u00020\f2\u0006\u00101\u001A\u00020\fH\u0087\b\u001A\u0019\u00102\u001A\u00020\u00012\u0006\u00100\u001A\u00020\u00012\u0006\u00101\u001A\u00020\u0001H\u0087\b\u001A\u0019\u00102\u001A\u00020\u00062\u0006\u00100\u001A\u00020\u00062\u0006\u00101\u001A\u00020\u0006H\u0087\b\u001A\u0019\u00102\u001A\u00020\t2\u0006\u00100\u001A\u00020\t2\u0006\u00101\u001A\u00020\tH\u0087\b\u001A\u0019\u00102\u001A\u00020\f2\u0006\u00100\u001A\u00020\f2\u0006\u00101\u001A\u00020\fH\u0087\b\u001A\u0011\u00103\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u00103\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u0010\u000F\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u0010\u000F\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u00104\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u00104\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u00105\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u00105\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u00106\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u00106\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u00107\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u00107\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0011\u00108\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0011\u00108\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0010\u00109\u001A\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0007\u001A\u0010\u00109\u001A\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0007\u001A\u0015\u0010:\u001A\u00020\u0001*\u00020\u00012\u0006\u0010;\u001A\u00020\u0001H\u0087\b\u001A\u0015\u0010:\u001A\u00020\u0006*\u00020\u00062\u0006\u0010;\u001A\u00020\u0006H\u0087\b\u001A\r\u0010<\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\r\u0010<\u001A\u00020\u0006*\u00020\u0006H\u0087\b\u001A\u0015\u0010=\u001A\u00020\u0001*\u00020\u00012\u0006\u0010>\u001A\u00020\u0001H\u0087\b\u001A\u0015\u0010=\u001A\u00020\u0006*\u00020\u00062\u0006\u0010>\u001A\u00020\u0006H\u0087\b\u001A\r\u0010?\u001A\u00020\u0001*\u00020\u0001H\u0087\b\u001A\r\u0010?\u001A\u00020\u0006*\u00020\u0006H\u0087\b\u001A\u0015\u0010@\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0017\u001A\u00020\u0001H\u0087\b\u001A\u0015\u0010@\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0018\u001A\u00020\tH\u0087\b\u001A\u0015\u0010@\u001A\u00020\u0006*\u00020\u00062\u0006\u0010\u0017\u001A\u00020\u0006H\u0087\b\u001A\u0015\u0010@\u001A\u00020\u0006*\u00020\u00062\u0006\u0010\u0018\u001A\u00020\tH\u0087\b\u001A\f\u0010A\u001A\u00020\t*\u00020\u0001H\u0007\u001A\f\u0010A\u001A\u00020\t*\u00020\u0006H\u0007\u001A\f\u0010B\u001A\u00020\f*\u00020\u0001H\u0007\u001A\f\u0010B\u001A\u00020\f*\u00020\u0006H\u0007\u001A\u0015\u0010C\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u000F\u001A\u00020\u0001H\u0087\b\u001A\u0015\u0010C\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u000F\u001A\u00020\tH\u0087\b\u001A\u0015\u0010C\u001A\u00020\u0006*\u00020\u00062\u0006\u0010\u000F\u001A\u00020\u0006H\u0087\b\u001A\u0015\u0010C\u001A\u00020\u0006*\u00020\u00062\u0006\u0010\u000F\u001A\u00020\tH\u0087\b\"\u001F\u0010\u0000\u001A\u00020\u0001*\u00020\u00018\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001A\u0004\b\u0004\u0010\u0005\"\u001F\u0010\u0000\u001A\u00020\u0006*\u00020\u00068\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0002\u0010\u0007\u001A\u0004\b\u0004\u0010\b\"\u001F\u0010\u0000\u001A\u00020\t*\u00020\t8\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0002\u0010\n\u001A\u0004\b\u0004\u0010\u000B\"\u001F\u0010\u0000\u001A\u00020\f*\u00020\f8\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0002\u0010\r\u001A\u0004\b\u0004\u0010\u000E\"\u001F\u0010\u000F\u001A\u00020\u0001*\u00020\u00018\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0010\u0010\u0003\u001A\u0004\b\u0011\u0010\u0005\"\u001F\u0010\u000F\u001A\u00020\u0006*\u00020\u00068\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0010\u0010\u0007\u001A\u0004\b\u0011\u0010\b\"\u001E\u0010\u000F\u001A\u00020\t*\u00020\t8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0010\u0010\n\u001A\u0004\b\u0011\u0010\u000B\"\u001E\u0010\u000F\u001A\u00020\t*\u00020\f8FX\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0010\u0010\r\u001A\u0004\b\u0011\u0010\u0012\"\u001F\u0010\u0013\u001A\u00020\u0001*\u00020\u00018\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0014\u0010\u0003\u001A\u0004\b\u0015\u0010\u0005\"\u001F\u0010\u0013\u001A\u00020\u0006*\u00020\u00068\u00C6\u0002X\u0087\u0004\u00A2\u0006\f\u0012\u0004\b\u0014\u0010\u0007\u001A\u0004\b\u0015\u0010\b\u00A8\u0006D"}, d2 = {"absoluteValue", "", "getAbsoluteValue$annotations", "(D)V", "getAbsoluteValue", "(D)D", "", "(F)V", "(F)F", "", "(I)V", "(I)I", "", "(J)V", "(J)J", "sign", "getSign$annotations", "getSign", "(J)I", "ulp", "getUlp$annotations", "getUlp", "abs", "x", "n", "acos", "acosh", "asin", "asinh", "atan", "atan2", "y", "atanh", "cbrt", "ceil", "cos", "cosh", "exp", "expm1", "floor", "hypot", "ln", "ln1p", "log", "base", "log10", "log2", "max", "a", "b", "min", "round", "sin", "sinh", "sqrt", "tan", "tanh", "truncate", "IEEErem", "divisor", "nextDown", "nextTowards", "to", "nextUp", "pow", "roundToInt", "roundToLong", "withSign", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/math/MathKt")
class MathKt__MathJVMKt extends MathKt__MathHKt {
    private static final double IEEErem(double f, double f1) {
        return Math.IEEEremainder(f, f1);
    }

    private static final float IEEErem(float f, float f1) {
        return (float)Math.IEEEremainder(f, f1);
    }

    private static final double abs(double f) {
        return Math.abs(f);
    }

    private static final float abs(float f) {
        return Math.abs(f);
    }

    private static final int abs(int v) {
        return Math.abs(v);
    }

    private static final long abs(long v) {
        return Math.abs(v);
    }

    private static final double acos(double f) {
        return Math.acos(f);
    }

    private static final float acos(float f) {
        return (float)Math.acos(f);
    }

    public static final double acosh(double f) {
        if(f < 1.0) {
            return NaN;
        }
        if(f > Constants.upper_taylor_2_bound) {
            return Math.log(f) + Constants.LN2;
        }
        if(f - 1.0 >= Constants.taylor_n_bound) {
            return Math.log(f + Math.sqrt(f * f - 1.0));
        }
        double f1 = Math.sqrt(f - 1.0);
        return 1.414214 * (f1 >= Constants.taylor_2_bound ? f1 - f1 * f1 * f1 / 12.0 : Math.sqrt(f - 1.0));
    }

    private static final float acosh(float f) {
        return (float)MathKt.acosh(f);
    }

    private static final double asin(double f) {
        return Math.asin(f);
    }

    private static final float asin(float f) {
        return (float)Math.asin(f);
    }

    public static final double asinh(double f) {
        if(f >= Constants.taylor_n_bound) {
            if(f > Constants.upper_taylor_n_bound) {
                return f > Constants.upper_taylor_2_bound ? Math.log(f) + Constants.LN2 : Math.log(f * 2.0 + 1.0 / (f * 2.0));
            }
            return Math.log(f + Math.sqrt(f * f + 1.0));
        }
        if(f <= -Constants.taylor_n_bound) {
            return -MathKt.asinh(-f);
        }
        return Math.abs(f) >= Constants.taylor_2_bound ? f - f * f * f / 6.0 : f;
    }

    private static final float asinh(float f) {
        return (float)MathKt.asinh(f);
    }

    private static final double atan(double f) {
        return Math.atan(f);
    }

    private static final float atan(float f) {
        return (float)Math.atan(f);
    }

    private static final double atan2(double f, double f1) {
        return Math.atan2(f, f1);
    }

    private static final float atan2(float f, float f1) {
        return (float)Math.atan2(f, f1);
    }

    public static final double atanh(double f) {
        if(Math.abs(f) < Constants.taylor_n_bound) {
            return Math.abs(f) > Constants.taylor_2_bound ? f + f * f * f / 3.0 : f;
        }
        return Math.log((f + 1.0) / (1.0 - f)) / 2.0;
    }

    private static final float atanh(float f) {
        return (float)MathKt.atanh(f);
    }

    private static final double cbrt(double f) {
        return Math.cbrt(f);
    }

    private static final float cbrt(float f) {
        return (float)Math.cbrt(f);
    }

    private static final double ceil(double f) {
        return Math.ceil(f);
    }

    private static final float ceil(float f) {
        return (float)Math.ceil(f);
    }

    private static final double cos(double f) {
        return Math.cos(f);
    }

    private static final float cos(float f) {
        return (float)Math.cos(f);
    }

    private static final double cosh(double f) {
        return Math.cosh(f);
    }

    private static final float cosh(float f) {
        return (float)Math.cosh(f);
    }

    private static final double exp(double f) {
        return Math.exp(f);
    }

    private static final float exp(float f) {
        return (float)Math.exp(f);
    }

    private static final double expm1(double f) {
        return Math.expm1(f);
    }

    private static final float expm1(float f) {
        return (float)Math.expm1(f);
    }

    private static final double floor(double f) {
        return Math.floor(f);
    }

    private static final float floor(float f) {
        return (float)Math.floor(f);
    }

    private static final double getAbsoluteValue(double f) {
        return Math.abs(f);
    }

    private static final float getAbsoluteValue(float f) {
        return Math.abs(f);
    }

    private static final int getAbsoluteValue(int v) {
        return Math.abs(v);
    }

    private static final long getAbsoluteValue(long v) {
        return Math.abs(v);
    }

    public static void getAbsoluteValue$annotations(double f) {
    }

    public static void getAbsoluteValue$annotations(float f) {
    }

    public static void getAbsoluteValue$annotations(int v) {
    }

    public static void getAbsoluteValue$annotations(long v) {
    }

    private static final double getSign(double f) {
        return Math.signum(f);
    }

    private static final float getSign(float f) {
        return Math.signum(f);
    }

    public static final int getSign(int v) {
        if(v < 0) {
            return -1;
        }
        return v <= 0 ? 0 : 1;
    }

    public static final int getSign(long v) {
        int v1 = Long.compare(v, 0L);
        if(v1 < 0) {
            return -1;
        }
        return v1 <= 0 ? 0 : 1;
    }

    public static void getSign$annotations(double f) {
    }

    public static void getSign$annotations(float f) {
    }

    public static void getSign$annotations(int v) {
    }

    public static void getSign$annotations(long v) {
    }

    private static final double getUlp(double f) {
        return Math.ulp(f);
    }

    private static final float getUlp(float f) {
        return Math.ulp(f);
    }

    public static void getUlp$annotations(double f) {
    }

    public static void getUlp$annotations(float f) {
    }

    private static final double hypot(double f, double f1) {
        return Math.hypot(f, f1);
    }

    private static final float hypot(float f, float f1) {
        return (float)Math.hypot(f, f1);
    }

    private static final double ln(double f) {
        return Math.log(f);
    }

    private static final float ln(float f) {
        return (float)Math.log(f);
    }

    private static final double ln1p(double f) {
        return Math.log1p(f);
    }

    private static final float ln1p(float f) {
        return (float)Math.log1p(f);
    }

    public static final double log(double f, double f1) {
        return f1 <= 0.0 || f1 == 1.0 ? NaN : Math.log(f) / Math.log(f1);
    }

    public static final float log(float f, float f1) {
        return f1 <= 0.0f || f1 == 1.0f ? NaNf : ((float)(Math.log(f) / Math.log(f1)));
    }

    private static final double log10(double f) {
        return Math.log10(f);
    }

    private static final float log10(float f) {
        return (float)Math.log10(f);
    }

    public static final double log2(double f) {
        return Math.log(f) / Constants.LN2;
    }

    public static final float log2(float f) {
        return (float)(Math.log(f) / Constants.LN2);
    }

    private static final double max(double f, double f1) {
        return Math.max(f, f1);
    }

    private static final float max(float f, float f1) {
        return Math.max(f, f1);
    }

    private static final int max(int v, int v1) {
        return Math.max(v, v1);
    }

    private static final long max(long v, long v1) {
        return Math.max(v, v1);
    }

    private static final double min(double f, double f1) {
        return Math.min(f, f1);
    }

    private static final float min(float f, float f1) {
        return Math.min(f, f1);
    }

    private static final int min(int v, int v1) {
        return Math.min(v, v1);
    }

    private static final long min(long v, long v1) {
        return Math.min(v, v1);
    }

    private static final double nextDown(double f) {
        return Math.nextAfter(f, -Infinity);
    }

    private static final float nextDown(float f) {
        return Math.nextAfter(f, -Infinity);
    }

    private static final double nextTowards(double f, double f1) {
        return Math.nextAfter(f, f1);
    }

    private static final float nextTowards(float f, float f1) {
        return Math.nextAfter(f, ((double)f1));
    }

    private static final double nextUp(double f) {
        return Math.nextUp(f);
    }

    private static final float nextUp(float f) {
        return Math.nextUp(f);
    }

    private static final double pow(double f, double f1) {
        return Math.pow(f, f1);
    }

    private static final double pow(double f, int v) {
        return Math.pow(f, v);
    }

    private static final float pow(float f, float f1) {
        return (float)Math.pow(f, f1);
    }

    private static final float pow(float f, int v) {
        return (float)Math.pow(f, v);
    }

    private static final double round(double f) {
        return Math.rint(f);
    }

    private static final float round(float f) {
        return (float)Math.rint(f);
    }

    public static final int roundToInt(double f) {
        if(Double.isNaN(f)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        if(f > 2147483647.0) {
            return 0x7FFFFFFF;
        }
        return f < -2147483648.0 ? 0x80000000 : ((int)Math.round(f));
    }

    public static final int roundToInt(float f) {
        if(Float.isNaN(f)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(f);
    }

    public static final long roundToLong(double f) {
        if(Double.isNaN(f)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(f);
    }

    public static final long roundToLong(float f) {
        return MathKt.roundToLong(f);
    }

    private static final double sign(double f) {
        return Math.signum(f);
    }

    private static final float sign(float f) {
        return Math.signum(f);
    }

    private static final double sin(double f) {
        return Math.sin(f);
    }

    private static final float sin(float f) {
        return (float)Math.sin(f);
    }

    private static final double sinh(double f) {
        return Math.sinh(f);
    }

    private static final float sinh(float f) {
        return (float)Math.sinh(f);
    }

    private static final double sqrt(double f) {
        return Math.sqrt(f);
    }

    private static final float sqrt(float f) {
        return (float)Math.sqrt(f);
    }

    private static final double tan(double f) {
        return Math.tan(f);
    }

    private static final float tan(float f) {
        return (float)Math.tan(f);
    }

    private static final double tanh(double f) {
        return Math.tanh(f);
    }

    private static final float tanh(float f) {
        return (float)Math.tanh(f);
    }

    public static final double truncate(double f) {
        if(!Double.isNaN(f) && !Double.isInfinite(f)) {
            return f > 0.0 ? Math.floor(f) : Math.ceil(f);
        }
        return f;
    }

    public static final float truncate(float f) {
        if(!Float.isNaN(f) && !Float.isInfinite(f)) {
            return f > 0.0f ? ((float)Math.floor(f)) : ((float)Math.ceil(f));
        }
        return f;
    }

    private static final double withSign(double f, double f1) {
        return Math.copySign(f, f1);
    }

    private static final double withSign(double f, int v) {
        return Math.copySign(f, v);
    }

    private static final float withSign(float f, float f1) {
        return Math.copySign(f, f1);
    }

    private static final float withSign(float f, int v) {
        return Math.copySign(f, v);
    }
}

