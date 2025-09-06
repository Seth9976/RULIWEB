package kotlin.jdk7;

import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.UByte..ExternalSyntheticBackport0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001C\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A\u0018\u0010\u0000\u001A\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001A\u0004\u0018\u00010\u0004H\u0001\u001AH\u0010\u0005\u001A\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001A\u000E\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\u0087\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\n\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000B"}, d2 = {"closeFinally", "", "Ljava/lang/AutoCloseable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib-jdk7"}, k = 2, mv = {1, 9, 0}, pn = "kotlin", xi = 0x30)
public final class AutoCloseableKt {
    public static final void closeFinally(AutoCloseable autoCloseable0, Throwable throwable0) {
        if(autoCloseable0 != null) {
            if(throwable0 == null) {
                UByte..ExternalSyntheticBackport0.m(autoCloseable0);
                return;
            }
            try {
                UByte..ExternalSyntheticBackport0.m(autoCloseable0);
            }
            catch(Throwable throwable1) {
                ExceptionsKt.addSuppressed(throwable0, throwable1);
            }
        }
    }

    private static final Object use(AutoCloseable autoCloseable0, Function1 function10) {
        Object object0;
        Intrinsics.checkNotNullParameter(function10, "block");
        try {
            object0 = function10.invoke(autoCloseable0);
        }
        catch(Throwable throwable0) {
            AutoCloseableKt.closeFinally(autoCloseable0, throwable0);
            throw throwable0;
        }
        AutoCloseableKt.closeFinally(autoCloseable0, null);
        return object0;
    }
}

