package com.kakao.sdk.auth.network;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;

@Metadata(d1 = {"\u0000\u000E\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000E\n\u0000\u001A\u0012\u0010\u0000\u001A\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001A\u00020\u0003¨\u0006\u0004"}, d2 = {"withAccessToken", "Lokhttp3/Request;", "accessToken", "", "auth_release"}, k = 2, mv = {1, 5, 1}, xi = 0x30)
public final class AccessTokenInterceptorKt {
    public static final Request withAccessToken(Request request0, String s) {
        Intrinsics.checkNotNullParameter(request0, "<this>");
        Intrinsics.checkNotNullParameter(s, "accessToken");
        return request0.newBuilder().removeHeader("Authorization").addHeader("Authorization", "Bearer " + s).build();
    }
}

