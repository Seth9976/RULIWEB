package kotlin.math;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001A\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001A\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001A\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001A\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001A\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001A\u00020\u00048\u0000X\u0081\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lkotlin/math/Constants;", "", "()V", "LN2", "", "epsilon", "taylor_2_bound", "taylor_n_bound", "upper_taylor_2_bound", "upper_taylor_n_bound", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
final class Constants {
    public static final Constants INSTANCE;
    public static final double LN2;
    public static final double epsilon;
    public static final double taylor_2_bound;
    public static final double taylor_n_bound;
    public static final double upper_taylor_2_bound;
    public static final double upper_taylor_n_bound;

    static {
        Constants.INSTANCE = new Constants();
        Constants.LN2 = 0.693147;
        Constants.epsilon = 2.220446E-16;
        Constants.taylor_2_bound = 1.490116E-08;
        Constants.taylor_n_bound = 0.000122;
        Constants.upper_taylor_2_bound = 67108864.0;
        Constants.upper_taylor_n_bound = 8192.0;
    }
}

