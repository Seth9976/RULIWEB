package com.navercorp.nid.log;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000E\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001A\u0012\u0010\u0000\u001A\u00020\u0001*\n\u0018\u00010\u0002j\u0004\u0018\u0001`\u0003¨\u0006\u0004"}, d2 = {"toMessage", "", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Nid-OAuth_release"}, k = 2, mv = {1, 6, 0}, xi = 0x30)
public final class NidLogKt {
    public static final String toMessage(Exception exception0) {
        if(exception0 == null) {
            return "Unknown Exception";
        }
        String s = exception0.getLocalizedMessage();
        Intrinsics.checkNotNullExpressionValue(s, "this.localizedMessage");
        return s;
    }
}

