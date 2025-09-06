package kotlin;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u000F\u0012\b\b\u0002\u0010\u0003\u001A\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/NotImplementedError;", "Ljava/lang/Error;", "Lkotlin/Error;", "message", "", "(Ljava/lang/String;)V", "kotlin-stdlib"}, k = 1, mv = {1, 9, 0}, xi = 0x30)
public final class NotImplementedError extends Error {
    public NotImplementedError() {
        this(null, 1, null);
    }

    public NotImplementedError(String s) {
        Intrinsics.checkNotNullParameter(s, "message");
        super(s);
    }

    public NotImplementedError(String s, int v, DefaultConstructorMarker defaultConstructorMarker0) {
        if((v & 1) != 0) {
            s = "An operation is not implemented.";
        }
        this(s);
    }
}

