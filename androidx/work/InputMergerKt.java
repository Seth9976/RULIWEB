package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0012\u0010\u0002\u001A\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001A\u00020\u0001H\u0000\"\u000E\u0010\u0000\u001A\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"TAG", "", "fromClassName", "Landroidx/work/InputMerger;", "className", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class InputMergerKt {
    private static final String TAG;

    // 去混淆评级： 低(20)
    static {
        Intrinsics.checkNotNullExpressionValue("WM-InputMerger", "tagWithPrefix(\"InputMerger\")");
        InputMergerKt.TAG = "WM-InputMerger";
    }

    public static final InputMerger fromClassName(String s) {
        Intrinsics.checkNotNullParameter(s, "className");
        try {
            Object object0 = Class.forName(s).getDeclaredConstructor(null).newInstance(null);
            Intrinsics.checkNotNull(object0, "null cannot be cast to non-null type androidx.work.InputMerger");
            return (InputMerger)object0;
        }
        catch(Exception exception0) {
            Logger.get().error("WM-InputMerger", "Trouble instantiating " + s, exception0);
            return null;
        }
    }
}

