package androidx.core.math;

public class MathUtils {
    public static int addExact(int v, int v1) {
        int v2 = v + v1;
        int v3 = 1;
        if((v < 0 ? 0 : 1) == (v1 < 0 ? 0 : 1)) {
            if(v2 < 0) {
                v3 = 0;
            }
            if((v < 0 ? 0 : 1) != v3) {
                throw new ArithmeticException("integer overflow");
            }
        }
        return v2;
    }

    public static long addExact(long v, long v1) {
        long v2 = v + v1;
        int v3 = 1;
        int v4 = Long.compare(v, 0L);
        if((v4 < 0 ? 0 : 1) == (v1 < 0L ? 0 : 1)) {
            if(v2 < 0L) {
                v3 = 0;
            }
            if((v4 < 0 ? 0 : 1) != v3) {
                throw new ArithmeticException("integer overflow");
            }
        }
        return v2;
    }

    public static double clamp(double f, double f1, double f2) {
        if(f < f1) {
            return f1;
        }
        return f > f2 ? f2 : f;
    }

    public static float clamp(float f, float f1, float f2) {
        if(f < f1) {
            return f1;
        }
        return f > f2 ? f2 : f;
    }

    public static int clamp(int v, int v1, int v2) {
        if(v < v1) {
            return v1;
        }
        return v <= v2 ? v : v2;
    }

    public static long clamp(long v, long v1, long v2) {
        if(v < v1) {
            return v1;
        }
        return v <= v2 ? v : v2;
    }

    public static int decrementExact(int v) {
        if(v == 0x80000000) {
            throw new ArithmeticException("integer overflow");
        }
        return v - 1;
    }

    public static long decrementExact(long v) {
        if(v == 0x8000000000000000L) {
            throw new ArithmeticException("integer overflow");
        }
        return v - 1L;
    }

    public static int incrementExact(int v) {
        if(v == 0x7FFFFFFF) {
            throw new ArithmeticException("integer overflow");
        }
        return v + 1;
    }

    public static long incrementExact(long v) {
        if(v == 0x7FFFFFFFFFFFFFFFL) {
            throw new ArithmeticException("integer overflow");
        }
        return v + 1L;
    }

    public static int multiplyExact(int v, int v1) {
        int v2 = v * v1;
        if(v != 0 && v1 != 0 && (v2 / v != v1 || v2 / v1 != v)) {
            throw new ArithmeticException("integer overflow");
        }
        return v2;
    }

    public static long multiplyExact(long v, long v1) {
        long v2 = v * v1;
        if(v != 0L && v1 != 0L && (v2 / v != v1 || v2 / v1 != v)) {
            throw new ArithmeticException("integer overflow");
        }
        return v2;
    }

    public static int negateExact(int v) {
        if(v == 0x80000000) {
            throw new ArithmeticException("integer overflow");
        }
        return -v;
    }

    public static long negateExact(long v) {
        if(v == 0x8000000000000000L) {
            throw new ArithmeticException("integer overflow");
        }
        return -v;
    }

    public static int subtractExact(int v, int v1) {
        int v2 = v - v1;
        int v3 = 1;
        if((v >= 0 ? 0 : 1) != (v1 >= 0 ? 0 : 1)) {
            if(v2 >= 0) {
                v3 = 0;
            }
            if((v >= 0 ? 0 : 1) != v3) {
                throw new ArithmeticException("integer overflow");
            }
        }
        return v2;
    }

    public static long subtractExact(long v, long v1) {
        long v2 = v - v1;
        int v3 = 1;
        int v4 = Long.compare(v, 0L);
        if((v4 >= 0 ? 0 : 1) != (v1 >= 0L ? 0 : 1)) {
            if(v2 >= 0L) {
                v3 = 0;
            }
            if((v4 >= 0 ? 0 : 1) != v3) {
                throw new ArithmeticException("integer overflow");
            }
        }
        return v2;
    }

    public static int toIntExact(long v) {
        if(v > 0x7FFFFFFFL || v < 0xFFFFFFFF80000000L) {
            throw new ArithmeticException("integer overflow");
        }
        return (int)v;
    }
}

