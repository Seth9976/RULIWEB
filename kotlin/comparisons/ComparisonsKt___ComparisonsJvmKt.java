package kotlin.comparisons;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\b\u0002\n\u0002\u0010\u000F\n\u0002\b\u0006\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\u0010\n\n\u0002\u0010\u0017\n\u0002\b\u0002\u001A-\u0010\u0000\u001A\u0002H\u0001\"\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001A\u0002H\u00012\u0006\u0010\u0004\u001A\u0002H\u0001H\u0007\u00A2\u0006\u0002\u0010\u0005\u001A5\u0010\u0000\u001A\u0002H\u0001\"\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001A\u0002H\u00012\u0006\u0010\u0004\u001A\u0002H\u00012\u0006\u0010\u0006\u001A\u0002H\u0001H\u0007\u00A2\u0006\u0002\u0010\u0007\u001A9\u0010\u0000\u001A\u0002H\u0001\"\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001A\u0002H\u00012\u0012\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\t\"\u0002H\u0001H\u0007\u00A2\u0006\u0002\u0010\n\u001A\u0019\u0010\u0000\u001A\u00020\u000B2\u0006\u0010\u0003\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u000BH\u0087\b\u001A!\u0010\u0000\u001A\u00020\u000B2\u0006\u0010\u0003\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u000B2\u0006\u0010\u0006\u001A\u00020\u000BH\u0087\b\u001A\u001C\u0010\u0000\u001A\u00020\u000B2\u0006\u0010\u0003\u001A\u00020\u000B2\n\u0010\b\u001A\u00020\f\"\u00020\u000BH\u0007\u001A\u0019\u0010\u0000\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\r2\u0006\u0010\u0004\u001A\u00020\rH\u0087\b\u001A!\u0010\u0000\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\r2\u0006\u0010\u0004\u001A\u00020\r2\u0006\u0010\u0006\u001A\u00020\rH\u0087\b\u001A\u001C\u0010\u0000\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\r2\n\u0010\b\u001A\u00020\u000E\"\u00020\rH\u0007\u001A\u0019\u0010\u0000\u001A\u00020\u000F2\u0006\u0010\u0003\u001A\u00020\u000F2\u0006\u0010\u0004\u001A\u00020\u000FH\u0087\b\u001A!\u0010\u0000\u001A\u00020\u000F2\u0006\u0010\u0003\u001A\u00020\u000F2\u0006\u0010\u0004\u001A\u00020\u000F2\u0006\u0010\u0006\u001A\u00020\u000FH\u0087\b\u001A\u001C\u0010\u0000\u001A\u00020\u000F2\u0006\u0010\u0003\u001A\u00020\u000F2\n\u0010\b\u001A\u00020\u0010\"\u00020\u000FH\u0007\u001A\u0019\u0010\u0000\u001A\u00020\u00112\u0006\u0010\u0003\u001A\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u0011H\u0087\b\u001A!\u0010\u0000\u001A\u00020\u00112\u0006\u0010\u0003\u001A\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u00112\u0006\u0010\u0006\u001A\u00020\u0011H\u0087\b\u001A\u001C\u0010\u0000\u001A\u00020\u00112\u0006\u0010\u0003\u001A\u00020\u00112\n\u0010\b\u001A\u00020\u0012\"\u00020\u0011H\u0007\u001A\u0019\u0010\u0000\u001A\u00020\u00132\u0006\u0010\u0003\u001A\u00020\u00132\u0006\u0010\u0004\u001A\u00020\u0013H\u0087\b\u001A!\u0010\u0000\u001A\u00020\u00132\u0006\u0010\u0003\u001A\u00020\u00132\u0006\u0010\u0004\u001A\u00020\u00132\u0006\u0010\u0006\u001A\u00020\u0013H\u0087\b\u001A\u001C\u0010\u0000\u001A\u00020\u00132\u0006\u0010\u0003\u001A\u00020\u00132\n\u0010\b\u001A\u00020\u0014\"\u00020\u0013H\u0007\u001A\u0019\u0010\u0000\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u00152\u0006\u0010\u0004\u001A\u00020\u0015H\u0087\b\u001A!\u0010\u0000\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u00152\u0006\u0010\u0004\u001A\u00020\u00152\u0006\u0010\u0006\u001A\u00020\u0015H\u0087\b\u001A\u001C\u0010\u0000\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u00152\n\u0010\b\u001A\u00020\u0016\"\u00020\u0015H\u0007\u001A-\u0010\u0017\u001A\u0002H\u0001\"\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001A\u0002H\u00012\u0006\u0010\u0004\u001A\u0002H\u0001H\u0007\u00A2\u0006\u0002\u0010\u0005\u001A5\u0010\u0017\u001A\u0002H\u0001\"\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001A\u0002H\u00012\u0006\u0010\u0004\u001A\u0002H\u00012\u0006\u0010\u0006\u001A\u0002H\u0001H\u0007\u00A2\u0006\u0002\u0010\u0007\u001A9\u0010\u0017\u001A\u0002H\u0001\"\u000E\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u0003\u001A\u0002H\u00012\u0012\u0010\b\u001A\n\u0012\u0006\b\u0001\u0012\u0002H\u00010\t\"\u0002H\u0001H\u0007\u00A2\u0006\u0002\u0010\n\u001A\u0019\u0010\u0017\u001A\u00020\u000B2\u0006\u0010\u0003\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u000BH\u0087\b\u001A!\u0010\u0017\u001A\u00020\u000B2\u0006\u0010\u0003\u001A\u00020\u000B2\u0006\u0010\u0004\u001A\u00020\u000B2\u0006\u0010\u0006\u001A\u00020\u000BH\u0087\b\u001A\u001C\u0010\u0017\u001A\u00020\u000B2\u0006\u0010\u0003\u001A\u00020\u000B2\n\u0010\b\u001A\u00020\f\"\u00020\u000BH\u0007\u001A\u0019\u0010\u0017\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\r2\u0006\u0010\u0004\u001A\u00020\rH\u0087\b\u001A!\u0010\u0017\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\r2\u0006\u0010\u0004\u001A\u00020\r2\u0006\u0010\u0006\u001A\u00020\rH\u0087\b\u001A\u001C\u0010\u0017\u001A\u00020\r2\u0006\u0010\u0003\u001A\u00020\r2\n\u0010\b\u001A\u00020\u000E\"\u00020\rH\u0007\u001A\u0019\u0010\u0017\u001A\u00020\u000F2\u0006\u0010\u0003\u001A\u00020\u000F2\u0006\u0010\u0004\u001A\u00020\u000FH\u0087\b\u001A!\u0010\u0017\u001A\u00020\u000F2\u0006\u0010\u0003\u001A\u00020\u000F2\u0006\u0010\u0004\u001A\u00020\u000F2\u0006\u0010\u0006\u001A\u00020\u000FH\u0087\b\u001A\u001C\u0010\u0017\u001A\u00020\u000F2\u0006\u0010\u0003\u001A\u00020\u000F2\n\u0010\b\u001A\u00020\u0010\"\u00020\u000FH\u0007\u001A\u0019\u0010\u0017\u001A\u00020\u00112\u0006\u0010\u0003\u001A\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u0011H\u0087\b\u001A!\u0010\u0017\u001A\u00020\u00112\u0006\u0010\u0003\u001A\u00020\u00112\u0006\u0010\u0004\u001A\u00020\u00112\u0006\u0010\u0006\u001A\u00020\u0011H\u0087\b\u001A\u001C\u0010\u0017\u001A\u00020\u00112\u0006\u0010\u0003\u001A\u00020\u00112\n\u0010\b\u001A\u00020\u0012\"\u00020\u0011H\u0007\u001A\u0019\u0010\u0017\u001A\u00020\u00132\u0006\u0010\u0003\u001A\u00020\u00132\u0006\u0010\u0004\u001A\u00020\u0013H\u0087\b\u001A!\u0010\u0017\u001A\u00020\u00132\u0006\u0010\u0003\u001A\u00020\u00132\u0006\u0010\u0004\u001A\u00020\u00132\u0006\u0010\u0006\u001A\u00020\u0013H\u0087\b\u001A\u001C\u0010\u0017\u001A\u00020\u00132\u0006\u0010\u0003\u001A\u00020\u00132\n\u0010\b\u001A\u00020\u0014\"\u00020\u0013H\u0007\u001A\u0019\u0010\u0017\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u00152\u0006\u0010\u0004\u001A\u00020\u0015H\u0087\b\u001A!\u0010\u0017\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u00152\u0006\u0010\u0004\u001A\u00020\u00152\u0006\u0010\u0006\u001A\u00020\u0015H\u0087\b\u001A\u001C\u0010\u0017\u001A\u00020\u00152\u0006\u0010\u0003\u001A\u00020\u00152\n\u0010\b\u001A\u00020\u0016\"\u00020\u0015H\u0007\u00A8\u0006\u0018"}, d2 = {"maxOf", "T", "", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "c", "(Ljava/lang/Comparable;Ljava/lang/Comparable;Ljava/lang/Comparable;)Ljava/lang/Comparable;", "other", "", "(Ljava/lang/Comparable;[Ljava/lang/Comparable;)Ljava/lang/Comparable;", "", "", "", "", "", "", "", "", "", "", "", "", "minOf", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/comparisons/ComparisonsKt")
class ComparisonsKt___ComparisonsJvmKt extends ComparisonsKt__ComparisonsKt {
    private static final byte maxOf(byte b, byte b1) {
        return (byte)Math.max(b, b1);
    }

    private static final byte maxOf(byte b, byte b1, byte b2) {
        return (byte)Math.max(b, Math.max(b1, b2));
    }

    public static final byte maxOf(byte b, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        for(int v = 0; v < arr_b.length; ++v) {
            b = (byte)Math.max(b, arr_b[v]);
        }
        return b;
    }

    private static final double maxOf(double f, double f1) {
        return Math.max(f, f1);
    }

    private static final double maxOf(double f, double f1, double f2) {
        return Math.max(f, Math.max(f1, f2));
    }

    public static final double maxOf(double f, double[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "other");
        for(int v = 0; v < arr_f.length; ++v) {
            f = Math.max(f, arr_f[v]);
        }
        return f;
    }

    private static final float maxOf(float f, float f1) {
        return Math.max(f, f1);
    }

    private static final float maxOf(float f, float f1, float f2) {
        return Math.max(f, Math.max(f1, f2));
    }

    public static final float maxOf(float f, float[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "other");
        for(int v = 0; v < arr_f.length; ++v) {
            f = Math.max(f, arr_f[v]);
        }
        return f;
    }

    private static final int maxOf(int v, int v1) {
        return Math.max(v, v1);
    }

    private static final int maxOf(int v, int v1, int v2) {
        return Math.max(v, Math.max(v1, v2));
    }

    public static final int maxOf(int v, int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            v = Math.max(v, arr_v[v1]);
        }
        return v;
    }

    private static final long maxOf(long v, long v1) {
        return Math.max(v, v1);
    }

    private static final long maxOf(long v, long v1, long v2) {
        return Math.max(v, Math.max(v1, v2));
    }

    public static final long maxOf(long v, long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            v = Math.max(v, arr_v[v1]);
        }
        return v;
    }

    public static final Comparable maxOf(Comparable comparable0, Comparable comparable1) {
        Intrinsics.checkNotNullParameter(comparable0, "a");
        Intrinsics.checkNotNullParameter(comparable1, "b");
        return comparable0.compareTo(comparable1) < 0 ? comparable1 : comparable0;
    }

    public static final Comparable maxOf(Comparable comparable0, Comparable comparable1, Comparable comparable2) {
        Intrinsics.checkNotNullParameter(comparable0, "a");
        Intrinsics.checkNotNullParameter(comparable1, "b");
        Intrinsics.checkNotNullParameter(comparable2, "c");
        return ComparisonsKt.maxOf(comparable0, ComparisonsKt.maxOf(comparable1, comparable2));
    }

    public static final Comparable maxOf(Comparable comparable0, Comparable[] arr_comparable) {
        Intrinsics.checkNotNullParameter(comparable0, "a");
        Intrinsics.checkNotNullParameter(arr_comparable, "other");
        for(int v = 0; v < arr_comparable.length; ++v) {
            comparable0 = ComparisonsKt.maxOf(comparable0, arr_comparable[v]);
        }
        return comparable0;
    }

    private static final short maxOf(short v, short v1) {
        return (short)Math.max(v, v1);
    }

    private static final short maxOf(short v, short v1, short v2) {
        return (short)Math.max(v, Math.max(v1, v2));
    }

    public static final short maxOf(short v, short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            v = (short)Math.max(v, arr_v[v1]);
        }
        return v;
    }

    private static final byte minOf(byte b, byte b1) {
        return (byte)Math.min(b, b1);
    }

    private static final byte minOf(byte b, byte b1, byte b2) {
        return (byte)Math.min(b, Math.min(b1, b2));
    }

    public static final byte minOf(byte b, byte[] arr_b) {
        Intrinsics.checkNotNullParameter(arr_b, "other");
        for(int v = 0; v < arr_b.length; ++v) {
            b = (byte)Math.min(b, arr_b[v]);
        }
        return b;
    }

    private static final double minOf(double f, double f1) {
        return Math.min(f, f1);
    }

    private static final double minOf(double f, double f1, double f2) {
        return Math.min(f, Math.min(f1, f2));
    }

    public static final double minOf(double f, double[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "other");
        for(int v = 0; v < arr_f.length; ++v) {
            f = Math.min(f, arr_f[v]);
        }
        return f;
    }

    private static final float minOf(float f, float f1) {
        return Math.min(f, f1);
    }

    private static final float minOf(float f, float f1, float f2) {
        return Math.min(f, Math.min(f1, f2));
    }

    public static final float minOf(float f, float[] arr_f) {
        Intrinsics.checkNotNullParameter(arr_f, "other");
        for(int v = 0; v < arr_f.length; ++v) {
            f = Math.min(f, arr_f[v]);
        }
        return f;
    }

    private static final int minOf(int v, int v1) {
        return Math.min(v, v1);
    }

    private static final int minOf(int v, int v1, int v2) {
        return Math.min(v, Math.min(v1, v2));
    }

    public static final int minOf(int v, int[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            v = Math.min(v, arr_v[v1]);
        }
        return v;
    }

    private static final long minOf(long v, long v1) {
        return Math.min(v, v1);
    }

    private static final long minOf(long v, long v1, long v2) {
        return Math.min(v, Math.min(v1, v2));
    }

    public static final long minOf(long v, long[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            v = Math.min(v, arr_v[v1]);
        }
        return v;
    }

    public static final Comparable minOf(Comparable comparable0, Comparable comparable1) {
        Intrinsics.checkNotNullParameter(comparable0, "a");
        Intrinsics.checkNotNullParameter(comparable1, "b");
        return comparable0.compareTo(comparable1) > 0 ? comparable1 : comparable0;
    }

    public static final Comparable minOf(Comparable comparable0, Comparable comparable1, Comparable comparable2) {
        Intrinsics.checkNotNullParameter(comparable0, "a");
        Intrinsics.checkNotNullParameter(comparable1, "b");
        Intrinsics.checkNotNullParameter(comparable2, "c");
        return ComparisonsKt.minOf(comparable0, ComparisonsKt.minOf(comparable1, comparable2));
    }

    public static final Comparable minOf(Comparable comparable0, Comparable[] arr_comparable) {
        Intrinsics.checkNotNullParameter(comparable0, "a");
        Intrinsics.checkNotNullParameter(arr_comparable, "other");
        for(int v = 0; v < arr_comparable.length; ++v) {
            comparable0 = ComparisonsKt.minOf(comparable0, arr_comparable[v]);
        }
        return comparable0;
    }

    private static final short minOf(short v, short v1) {
        return (short)Math.min(v, v1);
    }

    private static final short minOf(short v, short v1, short v2) {
        return (short)Math.min(v, Math.min(v1, v2));
    }

    public static final short minOf(short v, short[] arr_v) {
        Intrinsics.checkNotNullParameter(arr_v, "other");
        for(int v1 = 0; v1 < arr_v.length; ++v1) {
            v = (short)Math.min(v, arr_v[v1]);
        }
        return v;
    }
}

