package com.kakao.sdk.auth;

import com.kakao.sdk.common.util.SdkLog;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000\f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001A$\u0010\u0000\u001A\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u00012\f\u0010\u0002\u001A\b\u0012\u0004\u0012\u0002H\u00010\u0003H\u0082\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"parseOrNull", "T", "f", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "auth_release"}, k = 2, mv = {1, 5, 1}, xi = 0x30)
public final class TokenManagerKt {
    private static final Object parseOrNull(Function0 function00) {
        try {
            return function00.invoke();
        }
        catch(Exception exception0) {
            SdkLog.Companion.e(exception0);
            return null;
        }
    }
}

