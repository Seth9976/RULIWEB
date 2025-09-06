package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000B\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0006\n\u0002\u0010\u0013\n\u0000\n\u0002\u0010\u0007\n\u0002\u0010\u0014\n\u0000\n\u0002\u0010\b\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\t\n\u0002\u0010\u0016\n\u0002\b\u0002\u001A\u001B\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001A\u00020\u0006H\u0002¢\u0006\u0002\u0010\u0007\u001A\u001B\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\u0005\u001A\u00020\tH\u0002¢\u0006\u0002\u0010\n\u001A\u001B\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u000B0\u00032\u0006\u0010\u0005\u001A\u00020\fH\u0002¢\u0006\u0002\u0010\r\u001A\u001B\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u000E0\u00032\u0006\u0010\u0005\u001A\u00020\u000FH\u0002¢\u0006\u0002\u0010\u0010\u001A\u001B\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00110\u00032\u0006\u0010\u0005\u001A\u00020\u0012H\u0002¢\u0006\u0002\u0010\u0013\u001A\u001B\u0010\u0002\u001A\b\u0012\u0004\u0012\u00020\u00140\u00032\u0006\u0010\u0005\u001A\u00020\u0015H\u0002¢\u0006\u0002\u0010\u0016\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"TAG", "", "convertPrimitiveArray", "", "", "value", "", "([Z)[Ljava/lang/Boolean;", "", "", "([B)[Ljava/lang/Byte;", "", "", "([D)[Ljava/lang/Double;", "", "", "([F)[Ljava/lang/Float;", "", "", "([I)[Ljava/lang/Integer;", "", "", "([J)[Ljava/lang/Long;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class Data_Kt {
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-Data", "tagWithPrefix(\"Data\")");
        Data_Kt.TAG = "WM-Data";
    }

    // 去混淆评级： 低(20)
    public static final String access$getTAG$p() [...] // 潜在的解密器

    private static final Boolean[] convertPrimitiveArray(boolean[] arr_z) {
        Boolean[] arr_boolean = new Boolean[arr_z.length];
        for(int v = 0; v < arr_z.length; ++v) {
            arr_boolean[v] = Boolean.valueOf(arr_z[v]);
        }
        return arr_boolean;
    }

    private static final Byte[] convertPrimitiveArray(byte[] arr_b) {
        Byte[] arr_byte = new Byte[arr_b.length];
        for(int v = 0; v < arr_b.length; ++v) {
            arr_byte[v] = (byte)arr_b[v];
        }
        return arr_byte;
    }

    private static final Double[] convertPrimitiveArray(double[] arr_f) {
        Double[] arr_double = new Double[arr_f.length];
        for(int v = 0; v < arr_f.length; ++v) {
            arr_double[v] = (double)arr_f[v];
        }
        return arr_double;
    }

    private static final Float[] convertPrimitiveArray(float[] arr_f) {
        Float[] arr_float = new Float[arr_f.length];
        for(int v = 0; v < arr_f.length; ++v) {
            arr_float[v] = (float)arr_f[v];
        }
        return arr_float;
    }

    private static final Integer[] convertPrimitiveArray(int[] arr_v) {
        Integer[] arr_integer = new Integer[arr_v.length];
        for(int v = 0; v < arr_v.length; ++v) {
            arr_integer[v] = (int)arr_v[v];
        }
        return arr_integer;
    }

    private static final Long[] convertPrimitiveArray(long[] arr_v) {
        Long[] arr_long = new Long[arr_v.length];
        for(int v = 0; v < arr_v.length; ++v) {
            arr_long[v] = (long)arr_v[v];
        }
        return arr_long;
    }
}

