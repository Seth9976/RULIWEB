package androidx.work;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A1\u0010\u0000\u001A\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001A\u00020\u00042\f\u0010\u0005\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\b"}, d2 = {"traced", "T", "Landroidx/work/Tracer;", "label", "", "block", "Lkotlin/Function0;", "(Landroidx/work/Tracer;Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "work-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 0x30)
public final class TracerKt {
    public static final Object traced(Tracer tracer0, String s, Function0 function00) {
        Object object0;
        Intrinsics.checkNotNullParameter(tracer0, "<this>");
        Intrinsics.checkNotNullParameter(s, "label");
        Intrinsics.checkNotNullParameter(function00, "block");
        boolean z = tracer0.isEnabled();
        try {
            if(z) {
                tracer0.beginSection(s);
            }
            object0 = function00.invoke();
        }
        catch(Throwable throwable0) {
            if(z) {
                tracer0.endSection();
            }
            throw throwable0;
        }
        if(z) {
            tracer0.endSection();
        }
        return object0;
    }
}

