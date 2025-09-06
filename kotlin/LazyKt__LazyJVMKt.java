package kotlin;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001A \u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001A*\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0005\u001A\u0004\u0018\u00010\u00062\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001A(\u0010\u0000\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0007\u001A\u00020\b2\f\u0010\u0003\u001A\b\u0012\u0004\u0012\u0002H\u00020\u0004¨\u0006\t"}, d2 = {"lazy", "Lkotlin/Lazy;", "T", "initializer", "Lkotlin/Function0;", "lock", "", "mode", "Lkotlin/LazyThreadSafetyMode;", "kotlin-stdlib"}, k = 5, mv = {1, 9, 0}, xi = 49, xs = "kotlin/LazyKt")
class LazyKt__LazyJVMKt {
    @Metadata(k = 3, mv = {1, 9, 0}, xi = 0x30)
    public final class WhenMappings {
        public static final int[] $EnumSwitchMapping$0;

        static {
            int[] arr_v = new int[LazyThreadSafetyMode.values().length];
            try {
                arr_v[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            try {
                arr_v[LazyThreadSafetyMode.NONE.ordinal()] = 3;
            }
            catch(NoSuchFieldError unused_ex) {
            }
            WhenMappings.$EnumSwitchMapping$0 = arr_v;
        }
    }

    public static final Lazy lazy(Object object0, Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "initializer");
        return new SynchronizedLazyImpl(function00, object0);
    }

    public static final Lazy lazy(LazyThreadSafetyMode lazyThreadSafetyMode0, Function0 function00) {
        Intrinsics.checkNotNullParameter(lazyThreadSafetyMode0, "mode");
        Intrinsics.checkNotNullParameter(function00, "initializer");
        int v = WhenMappings.$EnumSwitchMapping$0[lazyThreadSafetyMode0.ordinal()];
        switch(v) {
            case 1: {
                return new SynchronizedLazyImpl(function00, null, 2, null);
            }
            case 2: {
                return new SafePublicationLazyImpl(function00);
            }
            default: {
                if(v != 3) {
                    throw new NoWhenBranchMatchedException();
                }
                return new UnsafeLazyImpl(function00);
            }
        }
    }

    public static final Lazy lazy(Function0 function00) {
        Intrinsics.checkNotNullParameter(function00, "initializer");
        return new SynchronizedLazyImpl(function00, null, 2, null);
    }
}

